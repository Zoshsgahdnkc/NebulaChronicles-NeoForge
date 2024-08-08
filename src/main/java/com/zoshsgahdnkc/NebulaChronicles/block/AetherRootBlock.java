package com.zoshsgahdnkc.NebulaChronicles.block;

import com.zoshsgahdnkc.NebulaChronicles.registries.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class AetherRootBlock extends RotatedPillarBlock implements BonemealableBlock {
    public AetherRootBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level p_220878_, RandomSource p_220879_, BlockPos p_220880_, BlockState p_220881_) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource randomSource, BlockPos pos, BlockState blockState) {
        level.addFreshEntity(new ItemEntity(
                level, pos.getX() + 0.5,
                pos.getY() + 1,
                pos.getZ() + 0.5,
                new ItemStack(ModItems.AETHER_ROOT_SPORE.get()),
                0,
                0.1,
                0));
    }
}
