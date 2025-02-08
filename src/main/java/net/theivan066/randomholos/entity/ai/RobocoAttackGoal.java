package net.theivan066.randomholos.entity.ai;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.theivan066.randomholos.entity.custom.RobocoEntity;

import javax.annotation.Nullable;

public class RobocoAttackGoal extends RangedAttackGoal {
    private final RobocoEntity entity;
    private final Mob mob;
    private final RangedAttackMob rangedAttackMob;
    @Nullable
    private LivingEntity target;
    private int attackTime = -1;
    private final double speedModifier;
    private final float attackRadius;
    private final float attackRadiusSqr;
    private int attackDelay = 43;
    private boolean bolted = false;

    public RobocoAttackGoal(RangedAttackMob pRangedAttackMob, double pSpeedModifier, int pAttackInterval, float pAttackRadius) {
        super(pRangedAttackMob, pSpeedModifier, pAttackInterval, pAttackRadius);
        entity = ((RobocoEntity) pRangedAttackMob);
        this.rangedAttackMob = pRangedAttackMob;
        this.mob = (Mob) pRangedAttackMob;
        this.speedModifier = pSpeedModifier;
        this.attackRadius = pAttackRadius;
        this.attackRadiusSqr = pAttackRadius * pAttackRadius;
    }

    @Override
    public boolean canUse() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity instanceof Player && ((Player) livingentity).isCreative()) {
            entity.setAttacking(false);
            this.attackDelay = 43;
            return false;
        }
        if (livingentity != null) {
            if (livingentity.isAlive()) {
                this.target = livingentity;
                return true;
            }
        } else {
            entity.setAttacking(false);
            this.attackDelay = 43;
            return false;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return canUse();
    }

    @Override
    public void tick() {

        if (target instanceof Player && ((Player) target).isCreative()) {
            entity.setAttacking(false);
            this.attackDelay = 43;
            return;
        }

        double sqrDist = this.mob.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
        boolean hasLineOfSight = this.mob.getSensing().hasLineOfSight(this.target);
        if (!hasLineOfSight) {
            this.attackDelay = 43;
        }
        if (!(sqrDist > (double) this.attackRadiusSqr)) {
            //this.mob.getNavigation().stop();
            this.mob.lookAt(target, 60, 60);
            entity.setAttacking(true);
            this.attackDelay = Math.max(this.attackDelay - 1, 0);
            checkAttack(target);
        } else {
            this.mob.getNavigation().moveTo(this.target, this.speedModifier);
            this.attackDelay = 43;
            entity.setAttacking(false);
            entity.setCocked(false);
        }
    }

    public void checkAttack(Entity target) {
        if (this.attackDelay <= 0) {
            this.mob.getLookControl().setLookAt(target);
            this.rangedAttackMob.performRangedAttack((LivingEntity) target, 1);
            this.attackDelay = 30;
            if (!entity.isCocked()) {
                entity.setCocked(true);
            }
        }
    }
}
