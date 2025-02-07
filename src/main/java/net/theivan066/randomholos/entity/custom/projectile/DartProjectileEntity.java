package net.theivan066.randomholos.entity.custom.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.theivan066.randomholos.entity.ModEntities;

@SuppressWarnings({"deprecation"})
public class DartProjectileEntity extends Projectile {
    private int chargeTick;
    private boolean charged;

    private static final EntityDataAccessor<Boolean> HIT =
            SynchedEntityData.defineId(DartProjectileEntity.class, EntityDataSerializers.BOOLEAN);
    private int counter = 0;

    private double damage;

    public DartProjectileEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.chargeTick = 0;
        this.charged = false;
    }

    public DartProjectileEntity(Level pLevel, LivingEntity entity, float dmg) {
        super(ModEntities.DART_PROJECTILE.get(), pLevel);
        this.setDamage(dmg);
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

        // Charging up the projectile
        if (!this.charged && this.chargeTick < 32) { // 1.6 seconds (32 ticks)
            this.chargeTick++;
        } else if (!this.charged) {
            this.charged = true;
            // Grow the projectile
            this.setBoundingBox(this.getBoundingBox().inflate(2.0D, 2.0D, 2.0D));
        }

        // Particle effect during charging
        if (this.level().isClientSide && !this.charged) {
            double x = this.getX();
            double y = this.getY();
            double z = this.getZ();
            for (int i = 0; i < 10; i++) {
                double vx = Mth.nextDouble(this.random, -0.2, 0.2);
                double vy = Mth.nextDouble(this.random, -0.2, 0.2);
                double vz = Mth.nextDouble(this.random, -0.2, 0.2);
                this.level().addParticle(ParticleTypes.FLAME, x, y, z, vx, vy, vz);
            }
        }

        if (this.entityData.get(HIT)) {
            if (this.tickCount >= counter) {
                this.discard();
            }
        }

        if (this.tickCount >= 600) {
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
            this.level().addParticle(ParticleTypes.DRIPPING_LAVA, this.getX() + d5 * (double) i / 4.0D, this.getY() + d6 * (double) i / 4.0D, this.getZ() + d7 * (double) i / 4.0D, -d5, -d6 + 0.2D, -d1);
        }

        if (this.level().getBlockStates(this.getBoundingBox()).noneMatch(BlockBehaviour.BlockStateBase::isAir)) {
            this.discard();
        } else {
            this.setDeltaMovement(vec3.scale(0.99F));
            this.setPos(d0, d1, d2);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity hitEntity = pResult.getEntity();
        Entity owner = this.getOwner();

        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.NEUTRAL,
                2F, 1F);

        LivingEntity livingentity = owner instanceof LivingEntity ? (LivingEntity) owner : null;
        hitEntity.hurt(this.damageSources().mobProjectile(this, livingentity), (float) damage);
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (this.level().isClientSide()) {
            return;
        }

        if (pResult.getType() == HitResult.Type.ENTITY && pResult instanceof EntityHitResult) {
            this.entityData.set(HIT, true);
            counter = this.tickCount + 5;
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.discard();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(HIT, false);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        this.chargeTick = pCompound.getInt("ChargeTick");
        this.charged = pCompound.getBoolean("Charged");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putInt("ChargeTick", this.chargeTick);
        pCompound.putBoolean("Charged", this.charged);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
