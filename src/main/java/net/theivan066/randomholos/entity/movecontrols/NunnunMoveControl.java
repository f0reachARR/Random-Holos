package net.theivan066.randomholos.entity.movecontrols;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.theivan066.randomholos.entity.custom.NunnunEntity;

public class NunnunMoveControl extends MoveControl {
    private float yRot;
    private int jumpDelay;
    private final NunnunEntity nunnunEntity;
    public NunnunMoveControl(Mob pMob) {
        super(pMob);
        this.nunnunEntity = (NunnunEntity) pMob;
        this.yRot = 180.0F * pMob.getYRot() / (float)Math.PI;
    }

    public void setDirection(float pYRot) {
        this.yRot = pYRot;
    }

    public void setWantedMovement(double pSpeed) {
        this.speedModifier = pSpeed;
        this.operation = MoveControl.Operation.MOVE_TO;
    }

    public void tick() {
        this.mob.setYRot(this.rotlerp(this.mob.getYRot(), this.yRot, 90.0F));
        this.mob.yHeadRot = this.mob.getYRot();
        this.mob.yBodyRot = this.mob.getYRot();
        if (this.operation != MoveControl.Operation.MOVE_TO) {
            this.mob.setZza(0.0F);
        } else {
            this.operation = MoveControl.Operation.WAIT;
            if (this.mob.onGround()) {
                this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                if (this.jumpDelay-- <= 0) {
                    this.jumpDelay = this.nunnunEntity.getJumpDelay();
                    this.nunnunEntity.getJumpControl().jump();
                    if (this.nunnunEntity.doPlayJumpSound()) {
                        this.nunnunEntity.playSound(this.nunnunEntity.getJumpSound(), this.nunnunEntity.getSoundVolume(), this.nunnunEntity.getSoundPitch());
                    }
                } else {
                    this.nunnunEntity.xxa = 0.0F;
                    this.nunnunEntity.zza = 0.0F;
                    this.mob.setSpeed(0.0F);
                }
            } else {
                this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
            }

        }
    }
}
