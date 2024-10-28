package com.zoshsgahdnkc.NebulaChronicles.block;

import com.mojang.serialization.MapCodec;
import com.zoshsgahdnkc.NebulaChronicles.Entity.VerdhelmBeetle.VerdhelmBeetleEntity;
import com.zoshsgahdnkc.NebulaChronicles.Entity.VerdhelmLarvaeDummyEntity;
import com.zoshsgahdnkc.NebulaChronicles.registries.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VerdhelmLarvaeBlock extends DirectionalBlock {
    public VerdhelmLarvaeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
        this.registerDefaultState(this.stateDefinition.any().setValue(STATIC, false));
    }
    public static BooleanProperty STATIC = BooleanProperty.create("static");

    @Override
    protected void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        super.onPlace(pState, pLevel, pPos, pOldState, pMovedByPiston);
        pLevel.scheduleTick(pPos, pState.getBlock(), 1);
    }

    @Override
    protected void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        super.tick(pState, pLevel, pPos, pRandom);
        if (pRandom.nextFloat() < 0.1f) {
            VerdhelmLarvaeDummyEntity overseer = new VerdhelmLarvaeDummyEntity(pPos.getX(), pPos.getY(), pPos.getZ(), pLevel, pState.getValue(FACING));
            pLevel.addFreshEntity(overseer);
            pLevel.gameEvent(overseer, GameEvent.ENTITY_PLACE, pPos);
            pLevel.destroyBlock(pPos, false);
        }
        pLevel.scheduleTick(pPos, pState.getBlock(), 1);
    }

    public static final MapCodec<VerdhelmLarvaeBlock> CODEC = simpleCodec(VerdhelmLarvaeBlock::new);
    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction direction = pContext.getClickedFace();
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos().relative(direction.getOpposite()));
        return blockstate.is(this) && blockstate.getValue(FACING) == direction
                ? this.defaultBlockState().setValue(FACING, direction.getOpposite())
                : this.defaultBlockState().setValue(FACING, direction);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
        pBuilder.add(STATIC);
    }

    public static final VoxelShape UP = Block.box(4, 0, 4, 12, 2, 12);
    public static final VoxelShape DOWN = Block.box(4, 14, 4, 12, 16, 12);
    public static final VoxelShape EAST = Block.box(0, 4, 4, 2, 12, 12);
    public static final VoxelShape WEST = Block.box(14, 4, 4, 16, 12, 12);
    public static final VoxelShape SOUTH = Block.box(4, 4, 0, 12, 12, 2);
    public static final VoxelShape NORTH = Block.box(4, 4, 14, 12, 12, 16);

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        VoxelShape SHAPE = UP;
        switch (pState.getValue(FACING)) {
            case DOWN -> SHAPE = DOWN;
            case EAST -> SHAPE = EAST;
            case WEST -> SHAPE = WEST;
            case SOUTH -> SHAPE = SOUTH;
            case NORTH -> SHAPE = NORTH;
        }
        return SHAPE;
    }
}
