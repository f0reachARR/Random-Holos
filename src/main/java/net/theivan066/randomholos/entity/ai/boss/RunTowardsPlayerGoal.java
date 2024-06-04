package net.theivan066.randomholos.entity.ai.boss;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.theivan066.randomholos.entity.custom.boss.KurosoraEntity;
import net.theivan066.randomholos.math.MathUtil;

import java.util.EnumSet;

public class RunTowardsPlayerGoal extends Goal {
    private final KurosoraEntity mob;
    private final double speed;
    private final double teleportDistance;

    private int tick;
    private Player target;

    public RunTowardsPlayerGoal(KurosoraEntity mob, double speed, double teleportDistance) {
        this.mob = mob;
        this.speed = speed;
        this.teleportDistance = teleportDistance;
        this.tick = 0;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }
    @Override
    public boolean canUse() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity instanceof Player) {
            if (!((Player) livingentity).isCreative()) {
                this.target = (Player) livingentity;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.target != null && this.target.isAlive() && this.mob.distanceToSqr(this.target) > MathUtil.square(this.teleportDistance);
    }

    @Override
    public void start() {
        this.mob.getNavigation().moveTo(this.target, this.speed);
    }

    @Override
    public void stop() {
        this.target = null;
        this.mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (this.mob.distanceToSqr(this.target) < this.teleportDistance * this.teleportDistance && this.mob.getRandom().nextInt(10) == 0) {
            dash();
        } else {
            this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
            this.mob.getNavigation().moveTo(this.target, this.speed);
        }
    }

    private void dash() {
        if (tick <= 0) {
            mob.resetStates();
            mob.setDashing(true);
            mob.dashAnimationState.start(mob.tickCount);
            this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
        } else if (tick > 0 && tick < 5) {
            this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
        } else if (tick == 6) {
            Vec3 vec = target.getLookAngle();
            Vec3 oppVec = vec.reverse();
            Vec3 uniVec = vec.normalize().scale(1.5);
            this.mob.lookAt(EntityAnchorArgument.Anchor.EYES, oppVec);
            this.mob.teleportTo(this.target.getX() + uniVec.x, this.target.getY() + uniVec.y, this.target.getZ() + uniVec.z);
        } else if (tick > 6 && tick < 20) {
            this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
        } else {
            mob.dashAnimationState.stop();
            mob.resetStates();
        }
        tick ++;
    }
}
