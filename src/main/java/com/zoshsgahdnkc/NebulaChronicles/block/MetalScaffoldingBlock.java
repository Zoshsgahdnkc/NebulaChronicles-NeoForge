package com.zoshsgahdnkc.NebulaChronicles.block;

import com.mojang.serialization.MapCodec;
import com.zoshsgahdnkc.NebulaChronicles.datagen.tags.ModBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MetalScaffoldingBlock extends Block implements SimpleWaterloggedBlock {
    public static final MapCodec<MetalScaffoldingBlock> CODEC = simpleCodec(MetalScaffoldingBlock::new);
    private static final int MAX_DISTANCE = 11;
    private static final VoxelShape SHAPE;
    public static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 0, 99);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;


    @Override
    public MapCodec<MetalScaffoldingBlock> codec() {
        return CODEC;
    }

    public MetalScaffoldingBlock(BlockBehaviour.Properties p_56021_) {
        super(p_56021_);
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(DISTANCE, Integer.valueOf(MAX_DISTANCE))
                        .setValue(WATERLOGGED, Boolean.valueOf(false))
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(DISTANCE, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos blockpos = pContext.getClickedPos();
        Level level = pContext.getLevel();
        int i = getDistance(level, blockpos);
        return this.defaultBlockState()
                .setValue(WATERLOGGED, Boolean.valueOf(level.getFluidState(blockpos).getType() == Fluids.WATER))
                .setValue(DISTANCE, Integer.valueOf(i));
    }

    @Override
    protected void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (!pLevel.isClientSide) {
            pLevel.scheduleTick(pPos, this, 1);
        }
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    @Override
    protected BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        if (!pLevel.isClientSide()) {
            pLevel.scheduleTick(pCurrentPos, this, 1);
        }

        return pState;
    }

    @Override
    protected void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        int i = getDistance(pLevel, pPos);
        BlockState blockstate = pState.setValue(DISTANCE, Integer.valueOf(i));
        if (blockstate.getValue(DISTANCE) == MAX_DISTANCE) {
            if (pState.getValue(DISTANCE) == MAX_DISTANCE) {
                FallingBlockEntity.fall(pLevel, pPos, blockstate);
            } else {
                pLevel.destroyBlock(pPos, true);
            }
        } else if (pState != blockstate) {
            pLevel.setBlock(pPos, blockstate, 3);
        }
    }

//    @Override
//    protected boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
//        return getDistance(pLevel, pPos) < 7;
//    }

    @Override
    protected VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pContext.isAbove(Shapes.block(), pPos, true) && !pContext.isDescending()) {
            return SHAPE;
        } else {
            return Shapes.empty();
        }
    }

    @Override
    protected FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    public static int getDistance(BlockGetter pLevel, BlockPos pPos) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.mutable().move(Direction.DOWN);
        BlockState blockstate = pLevel.getBlockState(blockpos$mutableblockpos);
        int i = MAX_DISTANCE;
        if (blockstate.is(ModBlockTags.METAL_SCAFFOLDING)) {
            i = blockstate.getValue(DISTANCE);
        } else if (blockstate.isFaceSturdy(pLevel, blockpos$mutableblockpos, Direction.UP)) {
            return 0;
        }

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockState blockstate1 = pLevel.getBlockState(blockpos$mutableblockpos.setWithOffset(pPos, direction));
            if (blockstate1.is(ModBlockTags.METAL_SCAFFOLDING)) {
                i = Math.min(i, blockstate1.getValue(DISTANCE) + 1);
                if (i == 1) {
                    break;
                }
            }
        }

        return i;
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    static {
        VoxelShape voxelshape = Block.box(0.0, 13.0, 0.0, 16.0, 16.0, 16.0);
        VoxelShape voxelshape1 = Block.box(0.0, 0.0, 0.0, 16.0, 13.0, 2.0);
        VoxelShape voxelshape2 = Block.box(0.0, 0.0, 14.0, 16.0, 13.0, 16.0);
        VoxelShape voxelshape3 = Block.box(0.0, 0.0, 2.0, 2.0, 13.0, 14.0);
        VoxelShape voxelshape4 = Block.box(14.0, 0.0, 2.0, 16.0, 13.0, 14.0);
        VoxelShape bottom = Block.box(2.0, 0.0, 2.0, 14.0, 1.0, 14.0);
        SHAPE = Shapes.or(voxelshape, voxelshape1, voxelshape2, voxelshape3, voxelshape4, bottom);
    }
}
