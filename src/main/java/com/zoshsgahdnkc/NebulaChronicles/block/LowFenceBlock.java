package com.zoshsgahdnkc.NebulaChronicles.block;

import com.zoshsgahdnkc.NebulaChronicles.utils.VoxelBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class LowFenceBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public LowFenceBlock(Properties pProperties) {
        super(pProperties);
    }
    private static final VoxelBuilder builder = new VoxelBuilder()
            .add(12,0,0,4,13,4)
            .add(0,0,0,4,13,4)
            .add(4,9,0,8,4,4);

    public static final VoxelShape SHAPE_N = builder.buildNorth();
    public static final VoxelShape SHAPE_S = builder.buildSouth();
    public static final VoxelShape SHAPE_W = builder.buildWest();
    public static final VoxelShape SHAPE_E = builder.buildEast();

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case NORTH -> SHAPE_N;
            case SOUTH -> SHAPE_S;
            case EAST -> SHAPE_E;
            case WEST -> SHAPE_W;
            default -> SHAPE_N;
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING,pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }
}
