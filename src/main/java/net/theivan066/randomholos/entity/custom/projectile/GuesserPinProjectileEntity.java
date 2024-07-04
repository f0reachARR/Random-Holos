package net.theivan066.randomholos.entity.custom.projectile;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.theivan066.randomholos.effect.ModEffects;
import net.theivan066.randomholos.entity.ModEntities;
import net.theivan066.randomholos.item.ModItems;
import net.theivan066.randomholos.item.custom.GuesserPinItem;

public class GuesserPinProjectileEntity extends ThrowableItemProjectile {
    public GuesserPinProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public GuesserPinProjectileEntity(Level pLevel) {
        this(ModEntities.GUESSER_PIN_PROJECTILE.get(), pLevel);
    }

    public GuesserPinProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.GUESSER_PIN_PROJECTILE.get(), livingEntity, pLevel);
    }

    public GuesserPinProjectileEntity(Level pLevel, double x, double y, double z) {
        super(ModEntities.GUESSER_PIN_PROJECTILE.get(), x, y, z, pLevel);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (pResult.getEntity() instanceof LivingEntity entity) {
            this.level().playSound(entity, entity.getOnPos(), SoundEvents.ARROW_HIT_PLAYER, SoundSource.PLAYERS, 1f, 1f);

            MobEffectInstance effect = entity.getEffect(ModEffects.ZERO_GUESSER_EFFECT.get());
            int lvl = effect != null ? effect.getAmplifier() : -1;
            float dmg = lvl > 0 ? (float) Math.pow(1.1, Math.min(lvl, 128)) : 0.5f;

            entity.hurt(entity.damageSources().mobProjectile(this, GuesserPinItem.thrower), dmg);
            entity.addEffect(new MobEffectInstance(ModEffects.ZERO_GUESSER_EFFECT.get(), 200, lvl + 1));

        }
        super.onHitEntity(pResult);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.GUESSER_PIN.get();
    }
}
