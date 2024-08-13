package com.zoshsgahdnkc.NebulaChronicles.item;

import com.google.common.collect.ImmutableList;
import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.datagen.worldgen.ModDimensions;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LemonItem extends Item {
    public LemonItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, ImmutableList.of(Component.translatable("tooltip." + NebulaChronicles.MODID + ".lemon").withStyle(ChatFormatting.LIGHT_PURPLE)), pTooltipFlag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (level.isClientSide) return InteractionResultHolder.fail(player.getItemInHand(interactionHand));
        player.sendSystemMessage(Component.literal("You are in " + level.dimension().location().getPath()));
            if (level.dimension() != ModDimensions.SB_LEVEL) {
                player.sendSystemMessage(Component.literal("Teleporting to silverblanc"));
                placePlayer(player, level.getServer().getLevel(ModDimensions.SB_LEVEL));
            }
        if (level.dimension() == ModDimensions.SB_LEVEL) {
            player.sendSystemMessage(Component.literal("Teleporting to overWorld"));
            placePlayer(player, level.getServer().getLevel(Level.OVERWORLD));
        }
        return super.use(level, player, interactionHand);
    }

    private void placePlayer(Player player, ServerLevel destinationLevel) {
        BlockPos currentPos = player.getOnPos();
        BlockPos destination = new BlockPos(currentPos.getX(), 160, currentPos.getZ());
        while(!destinationLevel.getBlockState(destination).isAir() ||
                !destinationLevel.getBlockState(destination.above()).isAir() ||
                destinationLevel.getBlockState(destination.below()).isAir()) {
            destination = destination.below();
        }
        player.teleportTo(destinationLevel, destination.getX(), destination.getY(), destination.getZ(),
                new HashSet<>(), 0f, 0f);
    }
}
