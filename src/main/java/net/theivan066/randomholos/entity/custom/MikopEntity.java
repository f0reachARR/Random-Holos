package net.theivan066.randomholos.entity.custom;

import com.google.common.collect.Sets;
import net.minecraft.Util;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.theivan066.randomholos.entity.ModEntities;
import net.theivan066.randomholos.entity.trade.ModTrades;
import net.theivan066.randomholos.entity.variant.MikopVariant;
import net.theivan066.randomholos.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class MikopEntity extends Animal implements Npc, Merchant {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT =
            SynchedEntityData.defineId(MikopEntity.class, EntityDataSerializers.INT);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    @Nullable
    private Player customer;
    @Nullable
    protected MerchantOffers offers;
    private long lastRestockGameTime;
    private int numberOfRestocksToday;
    private long lastRestockCheckDayTime;
    private int restockTimer = 24000;
    public MikopEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.2D, Ingredient.of(Items.COOKED_BEEF), true));

        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1d));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));

        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 25D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.FOLLOW_RANGE, 64D)
                .add(Attributes.ATTACK_DAMAGE, 4f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.6f);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.MIKOP.get().create(pLevel);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    protected void updateWalkAnimation(float v) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(v * 6.0F, 1.0F);
        } else {
            f = 0.0F;
        }

        this.walkAnimation.update(f, 0.2F);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }

        if (restockTimer <= 0) {
            restockTimer = random.nextInt(12000, 28000);
            if (this.shouldRestock()) {
                this.restock();
            }
        } else {
            restockTimer --;
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
    }


    //VARIANT

    public MikopVariant getVariant() {
        return MikopVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(MikopVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason,
                                        @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        MikopVariant variant = Util.getRandom(MikopVariant.values(), this.random);
        this.setVariant(variant);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(DATA_ID_TYPE_VARIANT, pCompound.getInt("Variant"));
        this.lastRestockGameTime = pCompound.getLong("LastRestock");
        this.numberOfRestocksToday = pCompound.getInt("RestocksToday");
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getTypeVariant());
        pCompound.putLong("LastRestock", this.lastRestockGameTime);
        pCompound.putInt("RestocksToday", this.numberOfRestocksToday);
    }

    //Breeding
    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.COOKED_BEEF);
    }

    //Mob-specifics

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemInHand = pPlayer.getItemInHand(pHand);
        if (itemInHand.getItem() != ModItems.MIKOP_SPAWN_EGG.get() && itemInHand.getItem() != Items.COOKED_BEEF && this.isAlive() && !this.hasCustomer()) {
            if (!this.getOffers().isEmpty()) {
                if (!this.level().isClientSide()) {
                    this.setTradingPlayer(pPlayer);
                    this.openTradingScreen(pPlayer, this.getDisplayName(), 1);
                }
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide());
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }

    @Override
    public void setTradingPlayer(@Nullable Player pTradingPlayer) {
        this.customer = pTradingPlayer;
    }

    @Nullable
    @Override
    public Player getTradingPlayer() {
        return this.customer;
    }

    @Override
    public MerchantOffers getOffers() {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
            this.populateTradeData();
        }
        return this.offers;
    }

    protected void populateTradeData() {
        ModTrades.ItemListing[] trades = ModTrades.MIKOP_TRADES.get(1);
        if (trades != null) {
            MerchantOffers merchantoffers = this.getOffers();
            this.addTrades(merchantoffers, trades, 4);
        }
    }

    protected void addTrades(MerchantOffers givenMerchantOffers, ModTrades.ItemListing[] newTrades, int maxNumbers) {
        Set<Integer> set = Sets.newHashSet();
        if (newTrades.length > maxNumbers) {
            while (set.size() < maxNumbers) {
                set.add(this.random.nextInt(newTrades.length));
            }
        } else {
            for (int i = 0; i < newTrades.length; ++i) {
                set.add(i);
            }
        }

        for (Integer integer : set) {
            ModTrades.ItemListing modtrades$itrade = newTrades[integer];
            MerchantOffer merchantoffer = modtrades$itrade.getOffer(this, this.random);
            if (merchantoffer != null) {
                givenMerchantOffers.add(merchantoffer);
            }
        }
    }

    @Override
    public void overrideOffers(MerchantOffers pOffers) {}

    @Override
    public void notifyTrade(MerchantOffer pOffer) {
        pOffer.increaseUses();
        this.ambientSoundTime = -this.getAmbientSoundInterval();
        this.onTrade(pOffer);
//        if (this.customer instanceof ServerPlayer) {
//            CriteriaTriggers.TRADE.trigger((ServerPlayer)this.customer, this, pOffer.getResult());
//        }
    }

    protected void onTrade(MerchantOffer offer) {
        if (offer.shouldRewardExp()) {
            int i = 3 + this.getRandom().nextInt(4);
            this.level().addFreshEntity(new ExperienceOrb(this.level(), this.getX(), this.getY() + 0.5D, this.getZ(), i));
        }
    }

    @Override
    public void notifyTradeUpdated(ItemStack pStack) {
        if (!this.level().isClientSide() && this.ambientSoundTime > -this.getAmbientSoundInterval() + 20) {
            this.ambientSoundTime = -this.getAmbientSoundInterval();
            this.playSound(this.getYesOrNoSound(!pStack.isEmpty()), this.getSoundVolume(), this.getVoicePitch());
        }
    }
    protected SoundEvent getYesOrNoSound(boolean getYesSound) {
        return getYesSound ? SoundEvents.VILLAGER_YES : SoundEvents.VILLAGER_NO;
    }
    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int pXp) {}

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return SoundEvents.VILLAGER_TRADE;
    }

    @Override
    public boolean isClientSide() {
        return this.level().isClientSide();
    }

    public boolean hasCustomer() {
        return this.customer != null;
    }

    public boolean canRestock() {
        return true;
    }


    public void restock() {
        for(MerchantOffer merchantoffer : this.getOffers()) {
            merchantoffer.resetUses();
        }
        this.resendOffersToTradingPlayer();
        this.lastRestockGameTime = this.level().getGameTime();
        ++this.numberOfRestocksToday;
    }

    private void resendOffersToTradingPlayer() {
        MerchantOffers merchantoffers = this.getOffers();
        Player player = this.getTradingPlayer();
        if (player != null && !merchantoffers.isEmpty()) {
            player.sendMerchantOffers(player.containerMenu.containerId, merchantoffers, 0, 0, false, this.canRestock());
        }
    }

    private boolean needsToRestock() {
        for(MerchantOffer merchantoffer : this.getOffers()) {
            if (merchantoffer.needsRestock()) {
                return true;
            }
        }
        return false;
    }

    private boolean allowedToRestock() {
        return this.numberOfRestocksToday == 0 || this.numberOfRestocksToday < 2 && this.level().getGameTime() > this.lastRestockGameTime + 2400L;
    }

    public boolean shouldRestock() {
        long i = this.lastRestockGameTime + 12000L;
        long j = this.level().getGameTime();
        boolean flag = j > i;
        long k = this.level().getDayTime();
        if (this.lastRestockCheckDayTime > 0L) {
            long l = this.lastRestockCheckDayTime / 24000L;
            long i1 = k / 24000L;
            flag |= i1 > l;
        }

        this.lastRestockCheckDayTime = k;
        if (flag) {
            this.lastRestockGameTime = j;
            this.resetNumberOfRestocks();
        }

        return this.allowedToRestock() && this.needsToRestock();
    }

    private void resetNumberOfRestocks() {
        this.numberOfRestocksToday = 0;
    }
}
