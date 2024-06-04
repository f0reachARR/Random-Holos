package net.theivan066.randomholos.entity.custom;

import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.theivan066.randomholos.entity.ai.MikoAttackGoal;
import net.theivan066.randomholos.entity.variant.MikoVariant;
import net.theivan066.randomholos.item.ModItems;
import net.theivan066.randomholos.util.InventoryUtil;
import org.jetbrains.annotations.Nullable;

public class MikoEntity extends Animal {
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(MikoEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT =
            SynchedEntityData.defineId(MikoEntity.class, EntityDataSerializers.INT);


    private static final EntityDataAccessor<Boolean> WITH_AHOGE =
            SynchedEntityData.defineId(MikoEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public MikoEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new MikoAttackGoal(this, 1.5, true));

        this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.1d));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));

        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 25D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.FOLLOW_RANGE, 32D)
                .add(Attributes.ATTACK_DAMAGE, 1f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.05f);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if (this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 20; // Length in ticks of your animation
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if (!this.isAttacking()) {
            attackAnimationState.stop();
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
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemInHand = pPlayer.getItemInHand(pHand);
        int countSapling = pPlayer.getInventory().countItem(Items.CHERRY_SAPLING);
        int countPetal = pPlayer.getInventory().countItem(Items.PINK_PETALS);
        boolean hasItemForCleansing = itemInHand.is(Items.LAVA_BUCKET) && (countSapling >= 4 || countPetal >= 4);
        if (hasItemForCleansing) {
            if (countSapling >= 4) {
                InventoryUtil.removeItem(pPlayer, Items.CHERRY_SAPLING, 4);
                cleanseLava(pPlayer, itemInHand);
            } else {
                InventoryUtil.removeItem(pPlayer, Items.PINK_PETALS, 4);
                cleanseLava(pPlayer, itemInHand);
            }
        }

        if (itemInHand.is(Items.SHEARS)) {
            if (this.entityData.get(WITH_AHOGE)) {
                this.entityData.set(WITH_AHOGE, false);
                itemInHand.setDamageValue(itemInHand.getDamageValue() - 1);
                pPlayer.level().playSound(pPlayer, this.getX(), this.getY(), this.getZ(), SoundEvents.SHEEP_SHEAR, SoundSource.PLAYERS, 1, 1);
                pPlayer.addItem(new ItemStack(ModItems.AHOGE.get(), 1));
            } else {
                if (this.level().isClientSide) {
                    pPlayer.sendSystemMessage(Component.translatable("messages.randomholos.no_ahoge"));
                    pPlayer.level().playSound(pPlayer, this.getX(), this.getY(), this.getZ(), SoundEvents.VILLAGER_NO, SoundSource.PLAYERS, 1, 1);
                }
            }
        }
        return super.mobInteract(pPlayer, pHand);
    }

    private void cleanseLava(Player pPlayer, ItemStack lava) {
        lava.setCount(lava.getCount() - 1);
        pPlayer.addItem(new ItemStack(ModItems.ELITE_LAVA_BUCKET.get(), 1));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
        this.entityData.define(WITH_AHOGE, true);
    }


    //VARIANT

    public MikoVariant getVariant() {
        return MikoVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(MikoVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason,
                                        @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        MikoVariant variant = Util.getRandom(MikoVariant.values(), this.random);
        this.setVariant(variant);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(DATA_ID_TYPE_VARIANT, pCompound.getInt("Variant"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getTypeVariant());
    }

    //Mob-specifics

    @Override
    public InteractionResult interactAt(Player pPlayer, Vec3 pVec, InteractionHand pHand) {
        return super.interactAt(pPlayer, pVec, pHand);
    }


    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return super.getDeathSound();
    }

}

