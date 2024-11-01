package com.zoshsgahdnkc.NebulaChronicles.Entity.VerdhelmBeetle;

import com.zoshsgahdnkc.NebulaChronicles.registries.ModSounds;
import com.zoshsgahdnkc.NebulaChronicles.utils.Utils;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class SpikedVerdhelmBeetleEntity extends Monster {
    private static final int ATTACK_RANGE_SQR = 196;
    private static final int RANGED_ATTACK_INTERVAL = 80;
    private static final int HARD_RANGED_ATTACK_INTERVAL = 50;
    public AnimationState idleState = new AnimationState();

    public SpikedVerdhelmBeetleEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(PathType.DANGER_FIRE, 16.0F);
        this.setPathfindingMalus(PathType.DAMAGE_FIRE, -1.0F);
        this.xpReward = 8;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AttributeSupplier.builder()
                .add(Attributes.MAX_HEALTH, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.45)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ARMOR, 11)
                .add(Attributes.ARMOR_TOUGHNESS)
                .add(Attributes.MAX_ABSORPTION)
                .add(Attributes.STEP_HEIGHT)
                .add(Attributes.SCALE)
                .add(Attributes.GRAVITY, 0.16)
                .add(Attributes.SAFE_FALL_DISTANCE)
                .add(Attributes.FALL_DAMAGE_MULTIPLIER)
                .add(Attributes.JUMP_STRENGTH)
                .add(Attributes.OXYGEN_BONUS)
                .add(Attributes.BURNING_TIME)
                .add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE)
                .add(Attributes.WATER_MOVEMENT_EFFICIENCY)
                .add(Attributes.MOVEMENT_EFFICIENCY)
                .add(Attributes.ATTACK_KNOCKBACK)
                .add(Attributes.ATTACK_DAMAGE, 3)
                .add(Attributes.FOLLOW_RANGE, 28)
                .add(Attributes.STEP_HEIGHT, 1)
                .add(NeoForgeMod.SWIM_SPEED)
                .add(NeoForgeMod.NAMETAG_DISTANCE);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new ShootRockGoal());
        this.goalSelector.addGoal(3, new SpikedBeetleAttackGoal(this, 1.25f, false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
//        this.targetSelector.addGoal(1, new LairHitByTargetGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide()) {
            setUpAnimationState();
        }
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        boolean toReturn = super.doHurtTarget(pEntity);
        if (toReturn && pEntity instanceof LivingEntity entity) {
            entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, (level().getDifficulty().getId() - 1) * 80));
        }
        return toReturn;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.SPIKED_VERDHELM_BEETLE_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSounds.SPIKED_VERDHELM_BEETLE_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.SPIKED_VERDHELM_BEETLE_DEATH.get();
    }

    private void setUpAnimationState() {
        idleState.startIfStopped(this.tickCount);
    }

    // Shooting Rock to Distant Target While Moving Towards It
    public class ShootRockGoal extends Goal {
        protected int nextAttackTickCount;
        private int attackTime;
        private int lastSeen;

        public ShootRockGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }
        @Override
        public boolean canUse() {
            SpikedVerdhelmBeetleEntity entity = SpikedVerdhelmBeetleEntity.this;
            LivingEntity target = entity.getTarget();
            if (target == null || !target.isAlive() || !entity.canAttack(target)) return false;
            if (entity.distanceToSqr(target) < 9 || !entity.hasLineOfSight(target)) return false;
            return true;
        }
        @Override
        public void stop() {
            this.lastSeen = 0;
        }
        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            System.out.println("-----------ticking");
            this.attackTime--;
            SpikedVerdhelmBeetleEntity entity = SpikedVerdhelmBeetleEntity.this;
            LivingEntity target = entity.getTarget();
            if (target != null) {
                boolean hasSight = entity.getSensing().hasLineOfSight(target);
                if (hasSight) {
                    this.lastSeen = 0;
                } else {
                    this.lastSeen++;
                }
                double distance = entity.distanceToSqr(target);
                if (distance < ATTACK_RANGE_SQR && hasSight) {
                    entity.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 1.0);
                    if (attackTime <= 0) {
                        RandomSource random = entity.getRandom();
                        double x = target.getX() - entity.getX();
                        double y = target.getY(0.5) - entity.getY(0.5);
                        double z = target.getZ() - entity.getZ();
                        Vec3 shootingVec = new Vec3(Utils.slightlyRandom(x, 0.06f, random), y, Utils.slightlyRandom(z, 0.06f, random)).normalize();
                        ShootingRockEntity shootingRock = new ShootingRockEntity(entity, level(), shootingVec);
                        shootingRock.setPos(entity.getX(), entity.getY(0.5), entity.getZ());
                        entity.level().addFreshEntity(shootingRock);
                        playSound(ModSounds.SPIKED_VERDHELM_BEETLE_THROW_STONE.get());
                        attackTime = entity.level().getDifficulty() == Difficulty.HARD ? HARD_RANGED_ATTACK_INTERVAL : RANGED_ATTACK_INTERVAL;
                    }
                } else if (this.lastSeen < 5) {
                    entity.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 1.0);
                };
                entity.getLookControl().setLookAt(target, 10.0F, 10.0F);
            }
        }
    }

    protected class SpikedBeetleAttackGoal extends MeleeAttackGoal {
        SpikedVerdhelmBeetleEntity entity;
        public SpikedBeetleAttackGoal(SpikedVerdhelmBeetleEntity entity, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            super(entity, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
            this.entity = entity;
        }

        @Override
        public void stop() {
            super.stop();
            entity.setAggressive(false);
        }

        @Override
        public void start() {
            super.start();
            entity.setAggressive(true);
        }
    }

}
