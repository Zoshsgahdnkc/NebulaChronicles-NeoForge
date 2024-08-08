package com.zoshsgahdnkc.NebulaChronicles.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class WhiteBudBlock extends BushBlock {
    public static final MapCodec<WhiteBudBlock> CODEC = simpleCodec(WhiteBudBlock::new);
    public WhiteBudBlock(Properties copy) {
        super(copy);
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter p_51043_, BlockPos p_51044_) {
        return state.is(BlockTags.DIRT) || state.is(Blocks.FARMLAND);
    }

    @Override
    @NotNull
    public VoxelShape getShape(BlockState p_273399_, BlockGetter p_273568_, BlockPos p_273314_, CollisionContext p_273274_) {
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    }
}
