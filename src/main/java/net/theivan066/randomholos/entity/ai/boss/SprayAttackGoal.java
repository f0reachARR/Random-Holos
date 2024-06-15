package net.theivan066.randomholos.entity.ai.boss;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.custom.boss.KurosoraEntity;
import net.theivan066.randomholos.entity.custom.projectile.NoteProjectileEntity;

import java.util.Random;

public class SprayAttackGoal extends Goal {
    private final KurosoraEntity mob;
    private final double flyUpSpeed;
    private final double projectileSpeed;
    private final int attackCooldown;
    private int attackTick;
    private Random random = new Random();

    public SprayAttackGoal(KurosoraEntity mob, double flyUpSpeed, double projectileSpeed, int attackCooldown) {
        this.mob = mob;
        this.flyUpSpeed = flyUpSpeed;
        this.projectileSpeed = projectileSpeed;
        this.attackCooldown = attackCooldown;
        this.attackTick = 0;
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.mob.getTarget();
        return target != null && target.isAlive();
    }

    @Override
    public void start() {
        this.attackTick = 0;
    }

    @Override
    public void stop() {
        this.mob.setNoGravity(false);
    }

    @Override
    public void tick() {
        LivingEntity target = this.mob.getTarget();
        if (target != null) {
            if (this.attackTick < 47) {
                mob.resetStates();
                mob.setSpraying(true);
                mob.sprayAnimationState.start(mob.tickCount);
                Vec3 mobPos = this.mob.position();
                Vec3 targetPos = target.position();
                this.mob.setNoGravity(true);
                this.mob.setDeltaMovement(0, this.flyUpSpeed, 0);
                this.mob.getNavigation().stop();
            } else if (this.attackTick == 47) {
                this.mob.setDeltaMovement(Vec3.ZERO);
            } else if (this.attackTick >= 48 && this.attackTick < 65) {
                if (this.attackTick % 4 == 0) {
                    this.spawnProjectiles(target);
                }
            } else if (this.attackTick >= 65 && this.attackTick < 70) {
                mob.sprayAnimationState.stop();
                mob.resetStates();
                this.mob.setNoGravity(false);
                this.mob.resetFallDistance();
            }
            this.attackTick++;
        }
    }

    private void spawnProjectiles(LivingEntity target) {
        Level level = this.mob.level();
        for (int i = 0; i < 10; i++) {
            double angle = Math.toRadians(i * 18 - 90);
            Vec3 direction = new Vec3(Math.cos(angle), 0, Math.sin(angle));
            Vec3 spawnPos = this.mob.position().add(direction);

            NoteProjectileEntity projectile = new NoteProjectileEntity(level, this.mob, target, 5);
            projectile.setOwner(mob);
            projectile.setPos(spawnPos);
            projectile.shoot(target.getX(), target.getY(), target.getZ(), random.nextFloat(0, (float) this.projectileSpeed), 0.1F);
            level.addFreshEntity(projectile);
        }
    }
}
