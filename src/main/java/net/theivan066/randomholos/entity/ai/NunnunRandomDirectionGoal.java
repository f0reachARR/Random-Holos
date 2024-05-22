package net.theivan066.randomholos.entity.ai;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.theivan066.randomholos.entity.custom.NunnunEntity;
import net.theivan066.randomholos.entity.movecontrols.NunnunMoveControl;

import java.util.EnumSet;

public class NunnunRandomDirectionGoal extends Goal {
    private final NunnunEntity nunnunEntity;
    private float chosenDegrees;
    private int nextRandomizeTime;

    public NunnunRandomDirectionGoal(NunnunEntity nunnunEntity) {
        this.nunnunEntity = nunnunEntity;
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        return this.nunnunEntity.getTarget() == null && (this.nunnunEntity.onGround() || this.nunnunEntity.isInWater() || this.nunnunEntity.isInLava() || this.nunnunEntity.hasEffect(MobEffects.LEVITATION)) && this.nunnunEntity.getMoveControl() instanceof NunnunMoveControl;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        if (--this.nextRandomizeTime <= 0) {
            this.nextRandomizeTime = this.adjustedTickDelay(40 + this.nunnunEntity.getRandom().nextInt(60));
            this.chosenDegrees = (float)this.nunnunEntity.getRandom().nextInt(360);
        }

        MoveControl movecontrol = this.nunnunEntity.getMoveControl();
        if (movecontrol instanceof NunnunMoveControl nunnun$nunnunmovecontrol) {
            nunnun$nunnunmovecontrol.setDirection(this.chosenDegrees);
        }

    }
}
