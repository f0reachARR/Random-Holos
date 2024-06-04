package net.theivan066.randomholos.entity.custom.boss;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.theivan066.randomholos.entity.ai.boss.KurosoraChooseAttackGoal;
import net.theivan066.randomholos.entity.ai.boss.KurosoraMeleeAttackGoal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class KurosoraEntity extends PathfinderMob{
    private static final Predicate<LivingEntity> LIVING_ENTITY_SELECTOR = LivingEntity::attackable;
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(KurosoraEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SPRAY =
            SynchedEntityData.defineId(KurosoraEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DART =
            SynchedEntityData.defineId(KurosoraEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> TELEPORT =
            SynchedEntityData.defineId(KurosoraEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DASHING =
            SynchedEntityData.defineId(KurosoraEntity.class, EntityDataSerializers.BOOLEAN);

    public int timeout = 0;

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState sprayAnimationState = new AnimationState();
    public final AnimationState dartAnimationState = new AnimationState();
    public final AnimationState meleeAnimationState = new AnimationState();
    public final AnimationState dashAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private final List<Runnable> goalChanges = new ArrayList<>();
    private Goal currentGoal;

     private final ServerBossEvent bossEvent =
             (ServerBossEvent) new ServerBossEvent(Component.translatable("bossbar.randomholos.kurosora"), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.NOTCHED_20).setDarkenScreen(true).setCreateWorldFog(true);

    public KurosoraEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3, new KurosoraChooseAttackGoal(this, 1.0));
        this.goalSelector.addGoal(4, new KurosoraMeleeAttackGoal(this, 1.4, true));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 0, false, false, LIVING_ENTITY_SELECTOR));
    }


    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 444D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.FOLLOW_RANGE, 64D)
                .add(Attributes.ATTACK_DAMAGE, 10f)
                .add(Attributes.ATTACK_KNOCKBACK, 1f)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
    }


    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if (timeout <= 0) {
            if (this.isAttacking()) {
                timeout = 15; // Length in ticks of your animation
                meleeAnimationState.start(this.tickCount);
            } else if (this.isDashing()) {
                timeout = 20;
                dashAnimationState.start(this.tickCount);
            } else if (this.isSpraying()) {
                timeout = 70;
                sprayAnimationState.start(this.tickCount);
            } else if (this.isDart()) {
                timeout = 50;
                dartAnimationState.start(this.tickCount);
            }
        }

        --this.timeout;

        if (!this.isAttacking()) {
            meleeAnimationState.stop();
        }
        if (!this.isDashing()) {
            dashAnimationState.stop();
        }
        if (!this.isSpraying()) {
           sprayAnimationState.stop();
        }
        if (!this.isDart()) {
            dartAnimationState.stop();
        }
    }

    protected void updateWalkAnimation(float v) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(v * 6.0F, 1.0F);
        } else {
            f = 0.0F;
        }
        this.walkAnimation.update(f, 0.2F);
    }

    @Override
    public void tick() {
        super.tick();

        synchronized (goalChanges) {
            for (Runnable change : goalChanges) {
                change.run();
            }
            goalChanges.clear();
        }

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
        this.entityData.define(SPRAY, false);
        this.entityData.define(DART, false);
        this.entityData.define(TELEPORT, false);
        this.entityData.define(DASHING, false);
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setSpraying(boolean spray) {
        this.entityData.set(SPRAY, spray);
    }

    public boolean isSpraying() {
        return this.entityData.get(SPRAY);
    }

    public void setDart(boolean attacking) {
        this.entityData.set(DART, attacking);
    }

    public boolean isDart() {
        return this.entityData.get(DART);
    }

    public void setTeleporting(boolean teleporting) {
        this.entityData.set(TELEPORT, teleporting);
    }

    public boolean isTeleporting() {
        return this.entityData.get(TELEPORT);
    }
    public void setDashing(boolean dashing) {
        this.entityData.set(DASHING, dashing);
    }

    public boolean isDashing() {
        return this.entityData.get(DASHING);
    }

    public void resetStates() {
        this.setAttacking(false);
        this.setSpraying(false);
        this.setDart(false);
        this.setTeleporting(false);
        this.setDashing(false);
    }

    public void addGoal(int priority, Goal goal) {
        synchronized (goalChanges) {
            goalChanges.add(() -> this.goalSelector.addGoal(priority, goal));
        }
    }

    public void removeGoal(Goal goal) {
        synchronized (goalChanges) {
            goalChanges.add(() -> this.goalSelector.removeGoal(goal));
        }
    }

    public void setCurrentGoal(Goal goal) {
        this.currentGoal = goal;
    }

    public Goal getCurrentGoal() {
        return currentGoal;
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setNoGravity(false);
    }

    //bossbar

    @Override
    public void startSeenByPlayer(ServerPlayer pServerPlayer) {
        super.startSeenByPlayer(pServerPlayer);
        this.bossEvent.addPlayer(pServerPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer pServerPlayer) {
        super.stopSeenByPlayer(pServerPlayer);
        this.bossEvent.removePlayer(pServerPlayer);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }
}
