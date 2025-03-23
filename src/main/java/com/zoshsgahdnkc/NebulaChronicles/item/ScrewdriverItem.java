package com.zoshsgahdnkc.NebulaChronicles.item;

import com.zoshsgahdnkc.NebulaChronicles.block.AbstractGeneratorBlock;
import com.zoshsgahdnkc.NebulaChronicles.block.AbstractMachineBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class ScrewdriverItem extends Item {
    public ScrewdriverItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        LevelReader level = pContext.getLevel();
        if (level.isClientSide()) return super.useOn(pContext);
        BlockState state = level.getBlockState(pContext.getClickedPos());
        if (state.getBlock() instanceof AbstractGeneratorBlock generator) {
            Player player = pContext.getPlayer();
            if (player != null) {
                player.sendSystemMessage(Component.literal("canUse:" + generator.canGeneratePower(state, level, pContext.getClickedPos())));
                player.sendSystemMessage(Component.literal("power:" + generator.getPower(state, player.level(), pContext.getClickedPos(), player.level().getRandom())));
            }
        }
        if (state.getBlock() instanceof AbstractMachineBlock machine) {
            Player player = pContext.getPlayer();
            if (player != null) {
                player.sendSystemMessage(Component.literal("isPowerEnough:" + machine.isPowerEnough(state, player.level(), pContext.getClickedPos(), player.level().getRandom())));
            }
        }
        return super.useOn(pContext);
    }
}
