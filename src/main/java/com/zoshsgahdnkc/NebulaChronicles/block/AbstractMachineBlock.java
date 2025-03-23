package com.zoshsgahdnkc.NebulaChronicles.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
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

import javax.annotation.ParametersAreNonnullByDefault;

public abstract class AbstractMachineBlock extends Block {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty WORKING = BooleanProperty.create("working");

    public AbstractMachineBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.getStateDefinition().any()
                        .setValue(POWERED, false)
                        .setValue(WORKING, false)
        );
    }

    public abstract float getPowerConsumption(BlockState state, Level level, BlockPos pos, RandomSource random);

    public void onPowerSufficientTick() {}
    public void onPowerInsufficientTick() {}
    public void onSwitchOn() {}
    public void onSwitchOff() {}

    //TODO: rewrite ticking part after using block entity
    @Override
    protected void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        super.onPlace(pState, pLevel, pPos, pOldState, pMovedByPiston);
        pLevel.scheduleTick(pPos, pState.getBlock(), 1);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(WORKING) != isPowerEnough(state, level, pos, random)) {
            if (!state.getValue(WORKING)) {
                level.setBlock(pos, state.setValue(WORKING, true), 3);
                onSwitchOn();
            }
            if (state.getValue(WORKING)) {
                level.setBlock(pos, state.setValue(WORKING, false), 3);
                onSwitchOff();
            }
        } else {
            if (!state.getValue(WORKING)) onPowerInsufficientTick();
            if (state.getValue(WORKING)) onPowerSufficientTick();
        }
        level.scheduleTick(pos, state.getBlock(), 1);
    }

    public boolean isPowerEnough(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (!this.isConnectedToGrid()) {
            BlockState aboveState = level.getBlockState(pos.above());
            if (aboveState.getBlock() instanceof AbstractGeneratorBlock generator) {
                if (generator.canGeneratePower(aboveState, level, pos.above())) {
                    return generator.getPower(aboveState, level , pos.above(), random) >= this.getPowerConsumption(state, level, pos, random);
                }
            }
        }
        return false;
    }

    public boolean isConnectedToGrid() {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(POWERED, WORKING);
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
        return this.defaultBlockState().setValue(WORKING, false);
    }
}
