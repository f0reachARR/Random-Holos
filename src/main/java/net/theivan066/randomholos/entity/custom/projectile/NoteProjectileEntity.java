package net.theivan066.randomholos.entity.custom.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.theivan066.randomholos.entity.ModEntities;

import javax.annotation.Nullable;
import java.util.Random;

import static net.theivan066.randomholos.util.HitTracker.noteHit;

@SuppressWarnings({"deprecation"})
public class NoteProjectileEntity extends Projectile {

    @Nullable
    private Entity finalTarget;
    private static final EntityDataAccessor<Boolean> HIT =
            SynchedEntityData.defineId(NoteProjectileEntity.class, EntityDataSerializers.BOOLEAN);
    private int counter = 0;

    private double damage;

    public NoteProjectileEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.noPhysics = true;
    }

    public NoteProjectileEntity(Level pLevel, LivingEntity entity, @org.jetbrains.annotations.Nullable Entity pFinalTarget, float dmg) {
        super(ModEntities.NOTE_PROJECTILE.get(), pLevel);
        this.setDamage(dmg);
        setOwner(entity);
        this.finalTarget = pFinalTarget;
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

        if (this.tickCount >= 600) {
            this.remove(RemovalReason.DISCARDED);
        }

        if (this.finalTarget != null) {
            Vec3 vTarget = this.finalTarget.position();
            Vec3 vThis = this.position();
            Vec3 toTargetVec = vTarget.subtract(vThis);
            Vec3 vTargetMovement = this.finalTarget.getDeltaMovement();
            Vec3 vFinal = toTargetVec.add(vTargetMovement).normalize().scale(0.5);

            double d0 = this.getX() + vFinal.x;
            double d1 = this.getY() + vFinal.y;
            double d2 = this.getZ() + vFinal.z;

            HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (hitresult.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, hitresult))
                this.onHit(hitresult);

            this.updateRotation();
            this.setDeltaMovement(vFinal);
            this.setPos(d0, d1, d2);
        } else {
            Random ran = new Random();
            Vec3 vRan = new Vec3(ran.nextFloat(0.5F, 2), ran.nextFloat(0.5F, 2), ran.nextFloat(0.5F, 2));

            HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (hitresult.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, hitresult))
                this.onHit(hitresult);

            this.updateRotation();
            this.setDeltaMovement(vRan);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity hitEntity = pResult.getEntity();
        Entity owner = this.getOwner();

        if (noteHit < 29) {
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.NOTE_BLOCK_FLUTE, SoundSource.NEUTRAL,
                    1F, starStarStart[noteHit]);
            noteHit++;
        } else {
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.NOTE_BLOCK_FLUTE, SoundSource.NEUTRAL,
                    1F, starStarStart[noteHit]);
            noteHit = 0;
        }

        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.NOTE_BLOCK_FLUTE, SoundSource.NEUTRAL,
                1F, random.nextFloat());

        LivingEntity livingentity = owner instanceof LivingEntity ? (LivingEntity) owner : null;
        if (!(hitEntity == owner)) {
            hitEntity.hurt(this.damageSources().mobProjectile(this, livingentity), (float) damage);
            ((LivingEntity) hitEntity).hurtTime = 0;
        }
    }

    private static float FS4 = 0.5f;
    private static float G4 = (float) Math.pow(2, ((double) -11 / 12));
    private static float GS4 = (float) Math.pow(2, ((double) -10 / 12));
    private static float A4 = (float) Math.pow(2, ((double) -9 / 12));
    private static float AS4 = (float) Math.pow(2, ((double) -8 / 12));
    private static float B4 = (float) Math.pow(2, ((double) -7 / 12));
    private static float C5 = (float) Math.pow(2, ((double) -6 / 12));
    private static float CS5 = (float) Math.pow(2, ((double) -5 / 12));
    private static float D5 = (float) Math.pow(2, ((double) -4 / 12));
    private static float DS5 = (float) Math.pow(2, ((double) -3 / 12));
    private static float E5 = (float) Math.pow(2, ((double) -2 / 12));
    private static float F5 = (float) Math.pow(2, ((double) -1 / 12));
    private static float FS5 = 1;
    private static float G5 = (float) Math.pow(2, ((double) 1 / 12));
    private static float GS5 = (float) Math.pow(2, ((double) 2 / 12));
    private static float A5 = (float) Math.pow(2, ((double) 3 / 12));
    private static float AS5 = (float) Math.pow(2, ((double) 4 / 12));
    private static float B5 = (float) Math.pow(2, ((double) 5 / 12));
    private static float C6 = (float) Math.pow(2, ((double) 6 / 12));
    private static float CS6 = (float) Math.pow(2, ((double) 7 / 12));
    private static float D6 = (float) Math.pow(2, ((double) 8 / 12));
    private static float DS6 = (float) Math.pow(2, ((double) 9 / 12));
    private static float E6 = (float) Math.pow(2, ((double) 10 / 12));
    private static float F6 = (float) Math.pow(2, ((double) 11 / 12));
    private static float FS6 = 2;

    private static final float[] starStarStart = {
            A4, A4, A4, A4, B4, B4, B4, C5, C5, C5, C5, D5, D5, D5, D5, E5, E5, E5, E5, G5, G5, B5, B5, D6, D6, FS5, F5, DS5, FS4, FS4
    };

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
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(HIT, false);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entity) {
        return new ClientboundAddEntityPacket(this, entity);
    }
}
