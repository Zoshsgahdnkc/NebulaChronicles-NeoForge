package com.zoshsgahdnkc.NebulaChronicles.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractGeneratorBlock extends Block {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public AbstractGeneratorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.getStateDefinition().any()
                        .setValue(POWERED, false)
        );
    }
    public abstract boolean canGeneratePower(BlockState state, LevelReader level, BlockPos pos);
    public abstract float getPower(BlockState state, Level level, BlockPos pos, RandomSource random);

    public boolean isConnectedToGrid() {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(POWERED);
    }

    @Override
    protected void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pNeighborBlock, BlockPos pNeighborPos, boolean pMovedByPiston) {
        super.neighborChanged(pState, pLevel, pPos, pNeighborBlock, pNeighborPos, pMovedByPiston);
        if (!pLevel.isClientSide()) {
            boolean isCharged = pLevel.hasNeighborSignal(pPos);
            if (pState.getValue(POWERED) != isCharged) {
                pState.setValue(POWERED, isCharged);
            }
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        if (pContext.getLevel().hasNeighborSignal(pContext.getClickedPos())) {
            return this.defaultBlockState().setValue(POWERED, true);
        }
        return this.defaultBlockState();
    }
}
