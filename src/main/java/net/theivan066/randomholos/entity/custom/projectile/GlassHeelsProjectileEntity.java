package net.theivan066.randomholos.entity.custom.projectile;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.theivan066.randomholos.entity.ModEntities;
import net.theivan066.randomholos.item.ModItems;

public class GlassHeelsProjectileEntity extends ThrowableItemProjectile {
    public GlassHeelsProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public GlassHeelsProjectileEntity(Level pLevel) {
        this(ModEntities.GLASS_HEELS_PROJECTILE.get(), pLevel);
    }

    public GlassHeelsProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.GLASS_HEELS_PROJECTILE.get(), livingEntity, pLevel);
    }

    public GlassHeelsProjectileEntity(Level pLevel, double x, double y, double z) {
        super(ModEntities.GLASS_HEELS_PROJECTILE.get(), x, y, z, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.SINGLE_GLASS_HEEL.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Entity entity = pResult.getEntity();

        if (!this.level().isClientSide) {
            this.level().playSound(null, pResult.getEntity().getOnPos(), SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 1f, 1f);
        }
        for (int i = 0; i < 12; i++) {
            this.level().addParticle( new BlockParticleOption(ParticleTypes.BLOCK, Blocks.GLASS.defaultBlockState()),
                    entity.getX(), entity.getY(), entity.getZ(), 1, 1D, 1D);
        }
        if (!this.level().isClientSide) {
            Vec3 vec3 = this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale(0.2);
            if (vec3.lengthSqr() > 0.0D) {
                entity.push(vec3.x, 0.1D, vec3.z);
            }
        }
//        entity.hurt(entity.damageSources().mobProjectile(this, SingleGlassHeelItem.glassThrower), 0f);
       super.onHitEntity(pResult);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (!this.level().isClientSide) {
            this.level().playSound(null, pResult.getBlockPos(), SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 1f, 1f);
        }
        for (int i = 0; i < 12; i++) {
            this.level().addParticle( new BlockParticleOption(ParticleTypes.BLOCK, Blocks.GLASS.defaultBlockState()),
                    pResult.getBlockPos().getX(), pResult.getBlockPos().getY() + 1, pResult.getBlockPos().getZ(), 1, 1D, 1D);
        }   super.onHitBlock(pResult);
    }
}
