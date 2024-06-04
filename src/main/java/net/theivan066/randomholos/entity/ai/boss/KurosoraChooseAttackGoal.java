package net.theivan066.randomholos.entity.ai.boss;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.theivan066.randomholos.entity.custom.boss.KurosoraEntity;

import java.util.Random;

public class KurosoraChooseAttackGoal extends Goal {
    private final KurosoraEntity mob;
    private final double speed;
    private final Random random = new Random();

    private RunTowardsPlayerGoal runTowardsPlayerGoal;
    private SprayAttackGoal sprayAttackGoal;
    private DartAttackGoal dartAttackGoal;

    public KurosoraChooseAttackGoal(KurosoraEntity mob, double speed) {
        this.mob = mob;
        this.speed = speed;
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.mob.getTarget();
        return target != null && target.isAlive() && this.mob.distanceTo(target) > 4.0;
    }

    @Override
    public void start() {
        int choice = random.nextInt(3);
        switch (choice) {
            case 0:
                runTowardsPlayerGoal = new RunTowardsPlayerGoal(mob, speed, 8.0);
                mob.addGoal(0, runTowardsPlayerGoal);
                if (sprayAttackGoal != null) {
                    mob.removeGoal(sprayAttackGoal);
                }
                if (dartAttackGoal != null) {
                    mob.removeGoal(dartAttackGoal);
                }
                break;
            case 1:
                sprayAttackGoal = new SprayAttackGoal(mob, 0.17, 1, 1);
                mob.addGoal(0, sprayAttackGoal);
                if (runTowardsPlayerGoal != null) {
                    mob.removeGoal(runTowardsPlayerGoal);
                }
                if (dartAttackGoal != null) {
                    mob.removeGoal(dartAttackGoal);
                }
                break;
            case 2:
                dartAttackGoal = new DartAttackGoal(mob);
                mob.addGoal(0, dartAttackGoal);
                if (runTowardsPlayerGoal != null) {
                    mob.removeGoal(runTowardsPlayerGoal);
                }
                if (sprayAttackGoal != null) {
                    mob.removeGoal(sprayAttackGoal);
                }
                break;
        }
    }

    @Override
    public void stop() {
        if (runTowardsPlayerGoal != null) {
            mob.removeGoal(runTowardsPlayerGoal);
        }
        if (sprayAttackGoal != null) {
            mob.removeGoal(sprayAttackGoal);
        }
        if (dartAttackGoal != null) {
            mob.removeGoal(dartAttackGoal);
        }
    }

    @Override
    public void tick() {
        LivingEntity target = this.mob.getTarget();
        if (target != null) {
            this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
            this.mob.getNavigation().moveTo(target, this.speed);
        }
    }
}
