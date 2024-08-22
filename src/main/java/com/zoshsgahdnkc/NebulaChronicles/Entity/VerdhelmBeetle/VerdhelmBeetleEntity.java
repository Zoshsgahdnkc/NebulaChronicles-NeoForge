package com.zoshsgahdnkc.NebulaChronicles.Entity.VerdhelmBeetle;

import com.mojang.serialization.Dynamic;
import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModAttributes;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModEntityDataSerializers;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModParticles;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.IntFunction;

public class VerdhelmBeetleEntity extends Monster{
    public VerdhelmBeetleEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.getAttribute(ModAttributes.VERDHELM_SIZE).addTransientModifier(
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(NebulaChronicles.MODID, "verdhelm_size"),
                        this.getRandom().nextInt(-15, 15), AttributeModifier.Operation.ADD_VALUE));
    }

    private static final EntityDataAccessor<AnimState> ANIM_STATE = SynchedEntityData.defineId(VerdhelmBeetleEntity.class, ModEntityDataSerializers.VERDHELM_ANIM_STATE.get());
    @Nullable public BlockPos nestPos;
    public final AnimationState idleState = new AnimationState();
    public final AnimationState rollUpState = new AnimationState();
    public final AnimationState hidingState = new AnimationState();
    public final AnimationState rollOutState = new AnimationState();
    private long inStateTicks = 0;

    public static AttributeSupplier.Builder createAttributes() {
        return AttributeSupplier.builder()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ARMOR, 8)
                .add(Attributes.ARMOR_TOUGHNESS)
                .add(Attributes.MAX_ABSORPTION)
                .add(Attributes.STEP_HEIGHT)
                .add(Attributes.SCALE)
                .add(Attributes.GRAVITY)
                .add(Attributes.SAFE_FALL_DISTANCE)
                .add(Attributes.FALL_DAMAGE_MULTIPLIER)
                .add(Attributes.JUMP_STRENGTH)
                .add(Attributes.OXYGEN_BONUS)
                .add(Attributes.BURNING_TIME)
                .add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE)
                .add(Attributes.WATER_MOVEMENT_EFFICIENCY)
                .add(Attributes.MOVEMENT_EFFICIENCY)
                .add(Attributes.ATTACK_KNOCKBACK)
                .add(Attributes.ATTACK_DAMAGE, 2)
                .add(Attributes.FOLLOW_RANGE, 24)
                .add(net.neoforged.neoforge.common.NeoForgeMod.SWIM_SPEED)
                .add(net.neoforged.neoforge.common.NeoForgeMod.NAMETAG_DISTANCE)
                .add(ModAttributes.VERDHELM_SIZE, 100);
    }

    public boolean canSpawn() {
        return true;
    }
    public void rollUp() {
        if (!this.isScared()) {
            this.stopInPlace();
            this.gameEvent(GameEvent.ENTITY_ACTION);
            this.makeSound(SoundEvents.ARMADILLO_ROLL);
            this.makeSound(SoundEvents.ARMADILLO_ROLL);
            this.switchToState(AnimState.ROLLING);
        }
    }

    protected void addMossParticles() {
        if (!level().isClientSide()) return;
        for (int i = 0; i < 5; i++) {
            level().addParticle(ModParticles.MOSS_CLUMPS.get(), getX(), getY(), getZ(), 0, 0.1f * (0.5f + random.nextFloat()), 0);
        }
    }

    public void rollOut() {
        if (this.isScared()) {
            this.gameEvent(GameEvent.ENTITY_ACTION);
            this.makeSound(SoundEvents.ARMADILLO_UNROLL_FINISH);
            this.switchToState(AnimState.IDLE);
        }
    }
    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6f, 1f);
        } else f = 0;
        this.walkAnimation.update(f, 0.2f);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.setupAnimationStates();
            if (this.getState() == AnimState.ROLLING && this.inStateTicks == 0) {
                addMossParticles();
            }
        }

        if (this.isScared()) {
            this.clampHeadRotationToBody();
        }

        this.inStateTicks++;
    }

    private void setupAnimationStates() {
        switch (this.getState()) {
            case IDLE:
                this.idleState.startIfStopped(this.tickCount);
                this.rollOutState.stop();
                this.rollUpState.stop();
                this.hidingState.stop();
                break;
            case ROLLING:
                this.idleState.stop();
                this.rollOutState.stop();
                this.rollUpState.startIfStopped(this.tickCount);
                this.hidingState.stop();
                break;
            case SCARED:
                this.idleState.stop();
                this.rollOutState.stop();
                this.rollUpState.stop();
                if (this.inStateTicks == 0L) {
                    this.hidingState.start(this.tickCount);
                    this.hidingState.fastForward(AnimState.SCARED.animationDuration(), 1.0F);
                } else {
                    this.hidingState.startIfStopped(this.tickCount);
                }
                break;
            case UNROLLING:
                this.idleState.stop();
                this.rollOutState.startIfStopped(this.tickCount);
                this.rollUpState.stop();
                this.hidingState.stop();
        }
    }

    public boolean isScaredBy(LivingEntity entity) {
        return nestPos == null && (getLastHurtByMob() == entity);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(ANIM_STATE, AnimState.IDLE);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (ANIM_STATE.equals(pKey)) {
            this.inStateTicks = 0L;
        }

        super.onSyncedDataUpdated(pKey);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putString("state", this.getState().getSerializedName());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.switchToState(AnimState.fromName(pCompound.getString("state")));
    }

    public boolean canStayRolledUp() {
        return !this.isInLiquid() && !this.isLeashed() && !this.isPassenger() && !this.isVehicle();
    }

    public boolean shouldSwitchToScaredState() {
        return this.getState() == AnimState.ROLLING && this.inStateTicks > (long)AnimState.ROLLING.animationDuration();
    }

    @Override
    protected Brain.Provider<VerdhelmBeetleEntity> brainProvider() {
        return VerdhelmBrain.brainProvider();
    }

    @Override
    protected Brain<?> makeBrain(Dynamic<?> pDynamic) {
        return VerdhelmBrain.makeBrain(this.brainProvider().makeBrain(pDynamic));
    }

    @Override
    protected void customServerAiStep() {
        this.level().getProfiler().push("verdhelmBrain");
        ((Brain<VerdhelmBeetleEntity>)this.brain).tick((ServerLevel)this.level(), this);
        this.level().getProfiler().pop();
        this.level().getProfiler().push("verdhelmActivityUpdate");
        VerdhelmBrain.updateActivity(this);
        this.level().getProfiler().pop();

        super.customServerAiStep();
    }

    @Override
    protected void actuallyHurt(DamageSource pDamageSource, float pDamageAmount) {
        super.actuallyHurt(pDamageSource, pDamageAmount);
        if (!this.isNoAi() && !this.isDeadOrDying()) {
            if (pDamageSource.getEntity() instanceof LivingEntity) {
                this.getBrain().setMemoryWithExpiry(MemoryModuleType.DANGER_DETECTED_RECENTLY, true, 80L);
                if (this.canStayRolledUp()) {
                    this.rollUp();
                }
            } else if (pDamageSource.is(DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES)) {
                this.rollOut();
            }
        }
    }

    public boolean isScared() {
        return this.entityData.get(ANIM_STATE) != AnimState.IDLE;
    }
    public AnimState getState() {
        return this.entityData.get(ANIM_STATE);
    }

    @Override
    protected void sendDebugPackets() {
        super.sendDebugPackets();
        DebugPackets.sendEntityBrain(this);
    }

    public void switchToState(AnimState pState) {
        this.entityData.set(ANIM_STATE, pState);
    }

    public enum AnimState implements StringRepresentable {
        IDLE("idle", false, 0, 0) {
            @Override
            public boolean shouldHideInShell(long p_326483_) {
                return false;
            }
        },
        ROLLING("rolling", true, 10, 1) {
            @Override
            public boolean shouldHideInShell(long p_326211_) {
                return p_326211_ > 5L;
            }
        },
        SCARED("scared", true, 50, 2) {
            @Override
            public boolean shouldHideInShell(long p_326129_) {
                return true;
            }
        },
        UNROLLING("unrolling", true, 15, 3) {
            @Override
            public boolean shouldHideInShell(long p_326371_) {
                return p_326371_ < 26L;
            }
        };

        private static final StringRepresentable.EnumCodec<AnimState> CODEC = StringRepresentable.fromEnum(AnimState::values);
        private static final IntFunction<AnimState> BY_ID = ByIdMap.continuous(
                AnimState::id, values(), ByIdMap.OutOfBoundsStrategy.ZERO
        );
        public static final StreamCodec<ByteBuf, AnimState> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, AnimState::id);
        private final String name;
        private final boolean isThreatened;
        private final int animationDuration;
        private final int id;

        AnimState(String pName, boolean pIsThreatened, int pAnimationDuration, int pId) {
            this.name = pName;
            this.isThreatened = pIsThreatened;
            this.animationDuration = pAnimationDuration;
            this.id = pId;
        }

        public static AnimState fromName(String pName) {
            return CODEC.byName(pName, IDLE);
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }

        private int id() {
            return this.id;
        }

        public abstract boolean shouldHideInShell(long pInStateTicks);

        public boolean isThreatened() {
            return this.isThreatened;
        }

        public int animationDuration() {
            return this.animationDuration;
        }
        
        public static class VerdhelmPanic extends AnimalPanic<VerdhelmBeetleEntity> {
            public VerdhelmPanic(float p_316413_) {
                super(p_316413_, p_350284_ -> DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES);
            }

            protected void start(ServerLevel p_326201_, VerdhelmBeetleEntity p_326188_, long p_325949_) {
                p_326188_.rollOut();
                super.start(p_326201_, p_326188_, p_325949_);
            }
        }
    }
    
    
}
