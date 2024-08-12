package com.zoshsgahdnkc.NebulaChronicles.item;

import com.zoshsgahdnkc.NebulaChronicles.planet.Planet;
import com.zoshsgahdnkc.NebulaChronicles.planet.PlanetUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LemonItem extends Item {
    public LemonItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (level.isClientSide) return InteractionResultHolder.fail(player.getItemInHand(interactionHand));
        String dim = player.level().dimension().location().getPath();
        if (!dim.equals("silverblanc")) {
        }
        player.sendSystemMessage(Component.literal(""));
        return super.use(level, player, interactionHand);
    }
}
