package net.theivan066.randomholos.entity.ai;

import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.theivan066.randomholos.entity.custom.NunnunEntity;
import net.theivan066.randomholos.entity.movecontrols.NunnunMoveControl;

import java.util.EnumSet;

public class NunnunFloatGoal extends Goal {
    private final NunnunEntity nunnunEntity;

    public NunnunFloatGoal(NunnunEntity nunnunEntity) {
        this.nunnunEntity = nunnunEntity;
        this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        nunnunEntity.getNavigation().setCanFloat(true);
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        return (this.nunnunEntity.isInWater() || this.nunnunEntity.isInLava()) && this.nunnunEntity.getMoveControl() instanceof NunnunMoveControl;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        if (this.nunnunEntity.getRandom().nextFloat() < 0.8F) {
            this.nunnunEntity.getJumpControl().jump();
        }

        MoveControl movecontrol = this.nunnunEntity.getMoveControl();
        if (movecontrol instanceof NunnunMoveControl nunnun$nunnunmovecontrol) {
            nunnun$nunnunmovecontrol.setWantedMovement(1.2D);
        }

    }
}
