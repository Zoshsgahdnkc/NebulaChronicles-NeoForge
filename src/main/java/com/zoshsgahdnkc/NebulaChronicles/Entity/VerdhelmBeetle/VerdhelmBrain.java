package com.zoshsgahdnkc.NebulaChronicles.Entity.VerdhelmBeetle;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModEntitySensors;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;

import java.util.Map;
import java.util.Set;

public class VerdhelmBrain {
    public static final ImmutableList<SensorType<? extends Sensor<? super VerdhelmBeetleEntity>>> SENSOR_TYPES = ImmutableList.of(
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.HURT_BY,
            ModEntitySensors.VERDHELM_SCARED_DETECTED.get()
    );
    public static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(
            MemoryModuleType.IS_PANICKING,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.HURT_BY_ENTITY,
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.PATH,
            MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
            MemoryModuleType.GAZE_COOLDOWN_TICKS,
            MemoryModuleType.DANGER_DETECTED_RECENTLY
    );


    protected static Brain<?> makeBrain(Brain<VerdhelmBeetleEntity> pBrain) {
        initCoreActivity(pBrain);
        initIdleActivity(pBrain);
        initScaredActivity(pBrain);
        pBrain.setCoreActivities(Set.of(Activity.CORE));
        pBrain.setDefaultActivity(Activity.IDLE);
        pBrain.useDefaultActivity();
        return pBrain;
    }

    private static final OneShot<VerdhelmBeetleEntity> ROLLING_OUT = BehaviorBuilder.create(
            entityInstance -> entityInstance.group(entityInstance.absent(MemoryModuleType.DANGER_DETECTED_RECENTLY))
                    .apply(entityInstance, memoryAccessor -> (serverLevel, entity, l) -> {
                        if (entity.isScared()) {
                            entity.rollOut();
                            return true;
                        } else {
                            return false;
                        }
                    })
    );

    private static void initCoreActivity(Brain<VerdhelmBeetleEntity> pBrain) {
        pBrain.addActivity(
                Activity.CORE,
                0,
                ImmutableList.of(
                        new Swim(0.8F),
                        new VerdhelmBeetleEntity.AnimState.VerdhelmPanic(2.0F),
                        new LookAtTargetSink(45, 90),
                        new MoveToTargetSink() {
                            @Override
                            protected boolean checkExtraStartConditions(ServerLevel p_316506_, Mob p_316710_) {
                                if (p_316710_ instanceof VerdhelmBeetleEntity verdhelm_beetle && verdhelm_beetle.isScared()) {
                                    return false;
                                }

                                return super.checkExtraStartConditions(p_316506_, p_316710_);
                            }
                        },
                        new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS),
                        new CountDownCooldownTicks(MemoryModuleType.GAZE_COOLDOWN_TICKS),
                        ROLLING_OUT
                )
        );
    }

    private static void initIdleActivity(Brain<VerdhelmBeetleEntity> pBrain) {
        pBrain.addActivity(
                Activity.IDLE,
                ImmutableList.of(
                        Pair.of(0, SetEntityLookTargetSometimes.create(EntityType.PLAYER, 6.0F, UniformInt.of(30, 60))),
                        Pair.of(1, new RandomLookAround(UniformInt.of(150, 250), 30.0F, 0.0F, 0.0F)),
                        Pair.of(
                                2,
                                new RunOne<>(
                                        ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT),
                                        ImmutableList.of(
                                                Pair.of(RandomStroll.stroll(1.0F), 1), Pair.of(SetWalkTargetFromLookTarget.create(1.0F, 3), 1), Pair.of(new DoNothing(30, 60), 1)
                                        )
                                )
                        )
                )
        );
    }

    private static void initScaredActivity(Brain<VerdhelmBeetleEntity> pBrain) {
        pBrain.addActivityWithConditions(
                Activity.PANIC,
                ImmutableList.of(Pair.of(0, new VerdhelmBallUp())),
                Set.of(
                        Pair.of(MemoryModuleType.DANGER_DETECTED_RECENTLY, MemoryStatus.VALUE_PRESENT),
                        Pair.of(MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_ABSENT)
                )
        );
    }

    public static void updateActivity(VerdhelmBeetleEntity verdhelmBeetle) {
        verdhelmBeetle.getBrain().setActiveActivityToFirstValid(ImmutableList.of(Activity.PANIC, Activity.IDLE));
    }

    public static class VerdhelmBallUp extends Behavior<VerdhelmBeetleEntity> {
        static final int BALL_UP_STAY_IN_STATE = 5 * TimeUtil.SECONDS_PER_MINUTE * 20;
        static final int TICKS_DELAY_TO_DETERMINE_IF_DANGER_IS_STILL_AROUND = 5;
        static final int DANGER_DETECTED_RECENTLY_DANGER_THRESHOLD = 75;
        boolean dangerWasAround;

        public VerdhelmBallUp() {
            super(Map.of(), BALL_UP_STAY_IN_STATE);
        }

        protected void tick(ServerLevel pLevel, VerdhelmBeetleEntity pOwner, long pGameTime) {
            super.tick(pLevel, pOwner, pGameTime);
            if (pOwner.shouldSwitchToScaredState()) {
                pOwner.switchToState(VerdhelmBeetleEntity.AnimState.SCARED);
//                if (pOwner.onGround()) {
//                    pOwner.playSound(SoundEvents.ARMADILLO_LAND);
//                }
            } else {
                VerdhelmBeetleEntity.AnimState state = pOwner.getState();
                long i = pOwner.getBrain().getTimeUntilExpiry(MemoryModuleType.DANGER_DETECTED_RECENTLY);
                boolean flag = i > 75L;

                this.dangerWasAround = flag;
                if (state == VerdhelmBeetleEntity.AnimState.SCARED) {

                    if (i < (long) VerdhelmBeetleEntity.AnimState.UNROLLING.animationDuration()) {
//                        pOwner.playSound(SoundEvents.ARMADILLO_UNROLL_START);
                        pOwner.switchToState(VerdhelmBeetleEntity.AnimState.UNROLLING);
                    }
                } else if (state == VerdhelmBeetleEntity.AnimState.UNROLLING && i > (long) VerdhelmBeetleEntity.AnimState.UNROLLING.animationDuration()) {
                    pOwner.switchToState(VerdhelmBeetleEntity.AnimState.SCARED);
                }
            }
        }

        protected boolean checkExtraStartConditions(ServerLevel pLevel, VerdhelmBeetleEntity pOwner) {
            return pOwner.onGround();
        }

        protected boolean canStillUse(ServerLevel pLevel, VerdhelmBeetleEntity pEntity, long pGameTime) {
            return pEntity.getState().isThreatened();
        }

        protected void start(ServerLevel pLevel, VerdhelmBeetleEntity pEntity, long pGameTime) {
            pEntity.rollUp();
        }

        protected void stop(ServerLevel pLevel, VerdhelmBeetleEntity pEntity, long pGameTime) {
            if (!pEntity.canStayRolledUp()) {
                pEntity.rollOut();
            }
        }
    }

    public static Brain.Provider<VerdhelmBeetleEntity> brainProvider() {
        return Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
    }
}
