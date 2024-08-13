package com.zoshsgahdnkc.NebulaChronicles.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public class AetherRootHairBlock extends Block {
    public AetherRootHairBlock(Properties properties) {
        super(properties);
    }
    public static BooleanProperty END = PipeBlock.DOWN;

    @Override
    protected float getMaxHorizontalOffset() {
        return 0f;
    }

    @Override
    protected float getMaxVerticalOffset() {
        return 0f;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        boolean end = !context.getLevel().getBlockState(pos.below()).is(this);
        return super.getStateForPlacement(context).setValue(END, end);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        boolean isEnd = !level.getBlockState(pos.below()).is(this);
        level.setBlock(pos, state.setValue(END, isEnd), 3);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor levelAccessor, BlockPos pos, BlockPos pos1) {
        if (!canSurvive(state, levelAccessor, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        levelAccessor.scheduleTick(pos, this, 1);
        return super.updateShape(state, direction, state1, levelAccessor, pos, pos1);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockState above = levelReader.getBlockState(blockPos.above());
        return above.is(this) || above.isFaceSturdy(levelReader, blockPos.above(), Direction.DOWN);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(END);
    }
}
