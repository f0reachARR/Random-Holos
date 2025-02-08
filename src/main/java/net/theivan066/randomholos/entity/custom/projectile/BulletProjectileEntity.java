package net.theivan066.randomholos.entity.custom.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.theivan066.randomholos.entity.ModEntities;


public class BulletProjectileEntity extends Projectile {

    private int pelletGpCount;
    private static final EntityDataAccessor<Boolean> HIT =
            SynchedEntityData.defineId(BulletProjectileEntity.class, EntityDataSerializers.BOOLEAN);
    private int counter = 0;

    private double damage;

    public BulletProjectileEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BulletProjectileEntity(Level pLevel, Player player, float dmg, int pelletGpCount) {
        super(ModEntities.BULLET_PROJECTILE.get(), pLevel);
        this.setDamage(dmg);
        this.pelletGpCount = pelletGpCount;
        setOwner(player);
        BlockPos blockpos = player.blockPosition();
        double d0 = (double) blockpos.getX() + 0.5D;
        double d1 = (double) blockpos.getY() + 1.75D;
        double d2 = (double) blockpos.getZ() + 0.5D;
        this.moveTo(d0, d1, d2, this.getYRot(), this.getXRot());
    }

    public BulletProjectileEntity(Level pLevel, Entity entity, float dmg, int pelletGpCount) {
        super(ModEntities.BULLET_PROJECTILE.get(), pLevel);
        this.setDamage(dmg);
        this.pelletGpCount = pelletGpCount;
        setOwner(entity);
        BlockPos blockpos = entity.blockPosition();
        double d0 = (double) blockpos.getX() + 0.5D;
        double d1 = (double) blockpos.getY() + 1.75D;
        double d2 = (double) blockpos.getZ() + 0.5D;
        this.moveTo(d0, d1, d2, this.getYRot(), this.getXRot());
    }

    public void setDamage(double pDamage) {
        this.damage = pDamage;
    }

    public double getDamage() {
        return this.damage;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.entityData.get(HIT)) {
            if (this.tickCount >= counter) {
                this.discard();
            }
        }

        if (this.tickCount >= 1200) {
            this.remove(RemovalReason.DISCARDED);
        }

        Vec3 vec3 = this.getDeltaMovement();
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, hitresult))
            this.onHit(hitresult);

        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.updateRotation();

        double d5 = vec3.x;
        double d6 = vec3.y;
        double d7 = vec3.z;
        for (int i = 0; i < 4; ++i) {
            this.level().addParticle(ParticleTypes.CRIT, this.getX() + d5 * (double) i / 4.0D, this.getY() + d6 * (double) i / 4.0D, this.getZ() + d7 * (double) i / 4.0D, -d5, -d6 + 0.2D, -d1);
        }

        if (this.level().getBlockStates(this.getBoundingBox()).noneMatch(BlockBehaviour.BlockStateBase::isAir)) {
            this.discard();
        } else {
            this.setDeltaMovement(vec3.scale(0.995F));
            this.setPos(d0, d1, d2);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity hitEntity = pResult.getEntity();
        Entity owner = this.getOwner();

        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ARROW_HIT, SoundSource.NEUTRAL,
                2F, 1F);

        LivingEntity livingentity = owner instanceof LivingEntity ? (LivingEntity) owner : null;
        boolean hurt = hitEntity.hurt(this.damageSources().mobProjectile(this, livingentity), (float) damage);
        // effect
//        if (hurt) {
//            if(hitEntity instanceof LivingEntity livingHitEntity) {
//                livingHitEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1), owner);
//            }
//        }
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
//        for(int x = 0; x < 18; ++x) {
//            for(int y = 0; y < 18; ++y) {
//                this.level().addParticle(ParticleTypes.SNEEZE, this.getX(), this.getY(), this.getZ(),
//                        Math.cos(x*20) * 0.15d, Math.cos(y*20) * 0.15d, Math.sin(x*20) * 0.15d);
//            }
//        }
        if (this.level().isClientSide()) {
            return;
        }

        if (pResult.getType() == HitResult.Type.ENTITY && pResult instanceof EntityHitResult) {
            this.entityData.set(HIT, true);
            counter = this.tickCount + 5;
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(HIT, false);
    }
}
