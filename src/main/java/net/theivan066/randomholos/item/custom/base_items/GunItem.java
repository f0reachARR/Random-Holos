package net.theivan066.randomholos.item.custom.base_items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.theivan066.randomholos.entity.custom.projectile.BulletProjectileEntity;
import net.theivan066.randomholos.util.InventoryUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

@SuppressWarnings("deprecation")
public class GunItem extends ProjectileWeaponItem {

    public final Random random;
    private final float gunDamage;
    private final float bulletSpeed;
    private final int rateOfFire;
    private final int magSize;
    public final Item ammoType;
    private final int reloadCooldown;
    private final float[] bulletSpread;
    private final float[] gunRecoil;
    private final int pelletCount;
    private final SoundEvent reloadSound;
    private final SoundEvent shootSound;
    private final int reloadCycles;
    private final boolean isScoped;
    private final boolean unscopeAfterShot;
    private final int reloadStage1;
    private final int reloadStage2;
    private final int reloadStage3;
    private final LoadingType loadingType;
    public final FiringType firingType;
    public boolean isReloading = false;
    public int reloadingTicks = 0;
    private int clip;


    public GunItem(Properties pProperties, float gunDamage, float bulletSpeed, int rateOfFire, int magSize, Item ammoType, int reloadCooldown, float[] bulletSpread,
                   float[] gunRecoil, int pelletCount, SoundEvent reloadSound, SoundEvent shootSound, int reloadCycles, boolean isScoped, boolean unscopeAfterShot,
                   int reloadStage1, int reloadStage2, int reloadStage3, LoadingType loadingType, FiringType firingType) {
        super(pProperties);
        this.random = new Random();
        this.gunDamage = gunDamage;
        this.bulletSpeed = bulletSpeed;
        this.rateOfFire = rateOfFire;
        this.magSize = magSize;
        this.ammoType = ammoType;
        this.reloadCooldown = reloadCooldown;
        this.bulletSpread = bulletSpread;
        this.gunRecoil = gunRecoil;
        this.pelletCount = pelletCount;
        this.loadingType = loadingType;
        this.shootSound = shootSound;
        this.reloadSound = reloadSound;
        this.reloadCycles = reloadCycles;
        this.isScoped = isScoped;
        this.unscopeAfterShot = unscopeAfterShot;
        this.reloadStage1 = reloadStage1;
        this.reloadStage2 = reloadStage2;
        this.reloadStage3 = reloadStage3;
        this.firingType = firingType;
        this.clip = magSize;
    }

//    public static void fireCheck(Level pLevel, Player pPlayer, InteractionHand pUsedHand, GunItem item) {
//        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
//        if (pUsedHand == InteractionHand.MAIN_HAND && item.getClip() > 0) {
//            if (!pPlayer.getCooldowns().isOnCooldown(item)) {
//                item.shoot(pLevel, pPlayer, stack);
//                pPlayer.getCooldowns().addCooldown(item, item.rateOfFire);
//            }
//        } else if (item.getClip() <= 0) {
//            item.reload(pPlayer, stack);
//        }
//    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (this.getClip() > 0) {
            this.shoot(pLevel, pPlayer, stack);
            pPlayer.getCooldowns().addCooldown(this, this.rateOfFire);
        } else {
            this.reload(pPlayer, stack);
        }
        return InteractionResultHolder.fail(stack);
    }

    public void shoot(Level pLevel, Player pPlayer, ItemStack itemStack) {

        float h_kick = getRecoilX(itemStack);
        float v_kick = getRecoilY(itemStack);


        if (!pLevel.isClientSide) {
            this.setClip(this.getClip() - 1);
            for (int i = 0; i < this.pelletCount; i++) {
                BulletProjectileEntity bullet = new BulletProjectileEntity(pLevel, pPlayer, this.gunDamage, this.pelletCount);
                bullet.setPos(pPlayer.getX(), pPlayer.getEyeY(), pPlayer.getZ());

                Vec3 v0 = pPlayer.getDeltaMovement();
                Vec3 lookDirect = pPlayer.getLookAngle();
                Vec3 v1 = lookDirect.scale(bulletSpeed).add(v0);
                float spreadModifier = (float) ((pPlayer.isPushedByFluid() ? 1.5 : 0) + (pPlayer.isSwimming() ? 2.5 : 0)
                        + (pPlayer.isSprinting() ? 2.5 : 0) + (pPlayer.isCrouching() ? -0.5 : 0));
                float verticalSpread = random.nextFloat(-bulletSpread[0] * Math.max(spreadModifier, 0), bulletSpread[0] * Math.min(spreadModifier, 0));
                float horizontalSpread = random.nextFloat(-bulletSpread[1] * Math.max(spreadModifier, 0), bulletSpread[1] * Math.min(spreadModifier, 0));

                bullet.setDeltaMovement((v1.x + horizontalSpread), v1.y + verticalSpread, (v1.z + horizontalSpread));
                bullet.setOwner(pPlayer);

                pLevel.addFreshEntity(bullet);
            }
            pPlayer.setYRot(pPlayer.getYRot() + v_kick);
            pPlayer.setXRot(pPlayer.getXRot() + random.nextFloat(-h_kick, h_kick));
        }
        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), this.shootSound, SoundSource.PLAYERS, 1.0f, 1.0f);
    }

    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        super.onCraftedBy(pStack, pLevel, pPlayer);
        this.setClip(0);
    }

    private void reload(Player player, ItemStack stack) {
        this.isReloading = true;
        player.playSound(reloadSound,1,1);
        switch (this.loadingType) {
            case MAGAZINE ->
            {
                player.getCooldowns().addCooldown(this, reloadCooldown);
                if (reserveAmmoCount(player, this.ammoType) > 0) {
                    if (!player.level().isClientSide) {
                        player.displayClientMessage(Component.translatable("messages.randomholos.reloading"), true);
                    }
                    int count = Math.min(reserveAmmoCount(player, this.ammoType), magSize);
                    InventoryUtil.removeItem(player, this.ammoType, count);
                    this.setClip(count);
                } else {
                    if (!player.level().isClientSide) {
                        player.displayClientMessage(Component.translatable("messages.randomholos.no_ammo"), true);
                    }
                }
            }
            //not fixed yet
            case PER_CARTRIDGE ->
            {
                int ammo = reserveAmmoCount(player, this.ammoType);
                if (ammo > magSize) {
                    player.displayClientMessage(Component.translatable("messages.randomholos.reloading"), true);
                    for (int j = 0; j < magSize; j++) {
                        player.getCooldowns().addCooldown(this, reloadCooldown);
                        this.setClip(this.getClip() + 1);
                        InventoryUtil.removeItem(player, this.ammoType, 1);
                    }
                } else if (ammo > 0 && ammo < magSize) {
                    player.displayClientMessage(Component.translatable("messages.randomholos.reloading"), true);
                    for (int j = 0; j < ammo; j++) {
                        player.getCooldowns().addCooldown(this, reloadCooldown);
                        this.setClip(this.getClip() + 1);
                        InventoryUtil.removeItem(player, this.ammoType, 1);
                    }
                } else {
                    player.displayClientMessage(Component.translatable("messages.randomholos.no_ammo"), true);
                }
            }
        }
        this.isReloading = false;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        int tick = this.getReloadTicks();
        if (this.isReloading) {
            if (tick < reloadCooldown) {
                this.setReloadTicks(tick + 1);
            } else {
                this.setReloadTicks(0);
            }
        } else {
            this.setReloadTicks(0);
        }
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return null;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 0;
    }

    public float getRecoilX(ItemStack stack) {
        boolean ran = this.random.nextBoolean();
        return stack.getOrCreateTag().getBoolean("isAiming") ?
                (ran ? this.gunRecoil[0] : -this.gunRecoil[0]) / 2 :
                (ran ? this.gunRecoil[0] : -this.gunRecoil[0]);
    }

    public float getRecoilY(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("isAiming") ? this.gunRecoil[1] / 2 : this.gunRecoil[1];
    }

    public int getRateOfFire() {
        return this.rateOfFire;
    }

    public int getReloadCooldown() {
        return this.reloadCooldown;
    }

    public int getClip() {
        return this.clip;
    }

    public void setClip(int i){
        clip = i;
    }

    public int getReloadTicks() {
        return this.reloadingTicks;
    }

    public int getMagSize() {
        return this.magSize;
    }

    public void setReloadTicks(int i){
        reloadingTicks = i;
    }

    public boolean shouldUnscopeAfterShot() {
        return this.unscopeAfterShot;
    }

    public static int remainingAmmo(ItemStack stack) {
        CompoundTag nbtCompound = stack.getOrCreateTag();
        return nbtCompound.getInt("Clip");
    }

    public static int reserveAmmoCount(Player player, Item item) {
        return player.getInventory().countItem(item);
    }

    public static boolean isLoaded(ItemStack stack) {
        return remainingAmmo(stack) > 0;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

    public FiringType getFiringType() {
        return this.firingType;
    }

    public boolean isScoped() {
        return this.isScoped;
    }

    public boolean isReloading() {
        return this.isReloading;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.randomholos.gun_ammo").append(Component.literal(": " + this.getClip() + " / " + this.getMagSize())));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    //Types
    public enum LoadingType
    {
        MAGAZINE,
        PER_CARTRIDGE
    }

    public enum FiringType
    {
        SEMI_AUTO,
        BURST,
        AUTO
    }
}
