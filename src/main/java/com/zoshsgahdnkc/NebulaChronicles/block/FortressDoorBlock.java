package com.zoshsgahdnkc.NebulaChronicles.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class FortressDoorBlock extends DoorBlock {
    public FortressDoorBlock(BlockSetType blockSetType, Properties properties) {
        super(blockSetType, properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (isOpen(state)) {
            level.playSound(player, pos, SoundEvents.IRON_DOOR_OPEN, SoundSource.BLOCKS);
        } else {
            level.playSound(player, pos, SoundEvents.IRON_DOOR_CLOSE, SoundSource.BLOCKS);
        }
        state = state.cycle(OPEN);
        level.setBlock(pos, state, 10);

        level.gameEvent(player, this.isOpen(state) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

}
