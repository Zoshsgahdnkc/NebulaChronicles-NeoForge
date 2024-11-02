package com.zoshsgahdnkc.NebulaChronicles.Entity.VerdhelmBeetle;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public abstract class AbstractVerdhelmBeetleEntity extends Monster {

    public static final int MAX_VOLUNTARY_STROLL_TIME = 400;
    public static final int MAX_LAIR_DISTANCE = 80;
    @Nullable
    protected BlockPos lairPos;
    protected int strollingTime = 0;

    protected AbstractVerdhelmBeetleEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected boolean hasLair() {
        return lairPos != null;
    }

    protected boolean hasLairOrValid() {
        return hasLair() && this.distanceToSqr(getLairPos().getX(), getLairPos().getY(), getLairPos().getZ()) < Mth.square(MAX_LAIR_DISTANCE);
    }

    protected BlockPos getLairPos() {
        return this.lairPos;
    }

    protected boolean wantsToEnterLair() {
        if (hasLair() && strollingTime > MAX_VOLUNTARY_STROLL_TIME && getTarget() == null) return true;
        return false;
    }

}
