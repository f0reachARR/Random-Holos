package net.theivan066.randomholos.entity.ai.boss;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.theivan066.randomholos.entity.custom.boss.KurosoraEntity;
import net.theivan066.randomholos.entity.custom.projectile.DartProjectileEntity;

public class DartAttackGoal extends Goal {
    private final KurosoraEntity mob;
    private int attackTick;

    public DartAttackGoal(KurosoraEntity mob) {
        this.mob = mob;
        this.attackTick = 0;
    }

    @Override
    public boolean canUse() {
        return this.mob.getTarget() != null && this.mob.getTarget().isAlive();
    }

    @Override
    public boolean canContinueToUse() {
        return this.attackTick < 50; // 2.5 seconds (50 ticks)
    }

    @Override
    public void start() {
        this.attackTick = 0;
    }

    @Override
    public void stop() {
        // Reset any states if necessary
    }

    @Override
    public void tick() {
        LivingEntity target = this.mob.getTarget();
        if (target != null) {
            if (this.attackTick < 12) { // 0.6 seconds (12 ticks)
                // Start animation until timeline 0.75s
                mob.resetStates();
                mob.setDart(true);
                mob.dartAnimationState.start(mob.tickCount);
                this.mob.lookAt(target, 30, 30);
            } else if (this.attackTick >= 12 && this.attackTick < 20) { // 0.6 seconds (8 ticks)
                // Charge up the projectile until timeline 1.33s
                if (this.attackTick % 2 == 0) {
                    this.spawnChargedProjectile();
                }
                this.mob.lookAt(target, 30, 30);
            } else if (this.attackTick >= 20 && this.attackTick < 29) { // Timeline 1.46s
                // End animation and fire the projectile
                this.fireProjectiles(target);
            } else if (this.attackTick > 29 && this.attackTick < 50) {
                this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
            } else {
                mob.dartAnimationState.stop();
                mob.resetStates();
            }
            this.attackTick++;
        }
    }

    private void spawnChargedProjectile() {
        Level level = this.mob.level();
        DartProjectileEntity projectile = new DartProjectileEntity(level, this.mob, 30);
        Vec3 spawnPos = this.mob.position();

        Vec3 vTarget = this.mob.getTarget().position().add(this.mob.getTarget().getDeltaMovement());
        Vec3 vPos = this.mob.position();
        Vec3 vDirection = vTarget.subtract(vPos);

        projectile.setPos(spawnPos.x, spawnPos.y + 1.0, spawnPos.z);
        projectile.shoot(vDirection.x, vDirection.y, vDirection.z, 1.5f, 0.1f);
        level.addFreshEntity(projectile);
    }

    private void fireProjectiles(LivingEntity target) {
        Level level = this.mob.level();
        for (int i = 0; i < 3; i++) {
            this.mob.lookAt(target, 30, 30);

            Vec3 vTarget = this.mob.getTarget().position().add(this.mob.getTarget().getDeltaMovement());
            Vec3 vPos = this.mob.position();
            Vec3 vDirection = vTarget.subtract(vPos);

            DartProjectileEntity projectile = new DartProjectileEntity(level, this.mob, 30);
            Vec3 facing = this.mob.getLookAngle().normalize();
            projectile.setPos(this.mob.getX() + facing.x, this.mob.getY() + facing.y, this.mob.getZ() + facing.z);

            projectile.shoot(vDirection.x, vDirection.y, vDirection.z, 1.5f, 0.1f);
            level.addFreshEntity(projectile);
        }
    }
}
