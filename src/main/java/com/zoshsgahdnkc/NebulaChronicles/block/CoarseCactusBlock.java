package com.zoshsgahdnkc.NebulaChronicles.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CoarseCactusBlock extends Block {
    private static final VoxelShape voxelShape = Block.box(2,0,2,14,16,14);
    private static final VoxelShape voxelShapeTop = Block.box(2,0,2,14,8,14);
    public static final BooleanProperty TOP = BooleanProperty.create("top");
    public static final IntegerProperty AGE = BlockStateProperties.AGE_15;
    public CoarseCactusBlock(Properties properties ) {
        super(properties);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        BlockPos top = pos;
        do {
            top = top.above();
        } while (level.getBlockState(top).is(this));
        if (stack.is(this.asItem()) && level.getBlockState(top.above()).isAir()) {
            level.setBlock(top.below(), this.defaultBlockState().setValue(TOP, false), 3);
            level.setBlock(top, this.defaultBlockState().setValue(TOP, true), 3);
            if (!player.getAbilities().instabuild) stack.shrink(1);
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.FAIL;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        if (!level.isAreaLoaded(pos, 1)) return;
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        } else if (!level.getBlockState(pos.above()).is(this)) {
            level.setBlock(pos, this.defaultBlockState().setValue(TOP, true), 3);
        }
    }
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor levelAccessor, BlockPos pos, BlockPos pos1) {
        if (!state.canSurvive(levelAccessor, pos)) {
            levelAccessor.scheduleTick(pos, this, 1);
        }
        if (!levelAccessor.getBlockState(pos.above()).is(this)) levelAccessor.setBlock(pos, this.defaultBlockState().setValue(TOP, true), 3);

        return super.updateShape(state, direction, state1, levelAccessor, pos, pos1);
    }
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (!context.getLevel().getBlockState(context.getClickedPos().above()).is(this)) {
            return this.defaultBlockState().setValue(TOP, true).setValue(AGE, 0);
        }
        return this.defaultBlockState().setValue(TOP, false).setValue(AGE, 0);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return state.getValue(TOP)? voxelShapeTop:voxelShape;
    }
    @Override
    public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
        BlockState below = levelReader.getBlockState(pos.below());
        BlockState above = levelReader.getBlockState(pos.above());
        return isCoarseCactusSustainable(below) && !above.liquid();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TOP, AGE);
    }
    private boolean isCoarseCactusSustainable(BlockState state) {
        return state.is(this) || state.is(BlockTags.SAND);
    }
}
