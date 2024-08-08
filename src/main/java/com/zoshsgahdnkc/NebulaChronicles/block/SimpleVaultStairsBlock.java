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

public class SimpleVaultStairsBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public SimpleVaultStairsBlock(Properties pProperties) {
        super(pProperties);
    }

    private static final VoxelBuilder builder = new VoxelBuilder()
            .add(0,3,0,16,2,8)
            .add(0,11,8,16,2,8)
            .add(6,0,4,4,3,4)
            .add(6,3,8,4,4,4)
            .add(6,6,12,4,5,4);
    public static final VoxelShape SHAPE_N = builder.buildNorth();
    public static final VoxelShape SHAPE_S = builder.buildSouth();
    public static final VoxelShape SHAPE_E = builder.buildEast();
    public static final VoxelShape SHAPE_W = builder.buildWest();

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
