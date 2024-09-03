package com.zoshsgahdnkc.NebulaChronicles.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles.MODID;


public class ModTabs {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB_MISC = TABS.register("tab_misc", () -> CreativeModeTab.builder()
            .title(Component.translatable("item_Group."+ MODID +".tab_misc"))
            .icon(() -> new ItemStack(ModBlocks.FORTRESS_BLOCK))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .displayItems((param, tab) -> {
                accept(tab, ModBlocks.FORTRESS_BLOCK);
                accept(tab, ModBlocks.FORTRESS_WALL);
                accept(tab, ModBlocks.FORTRESS_WALL_LIGHT);
                accept(tab, ModBlocks.FORTRESS_WALL_LIGHT_UNLIT);
                accept(tab, ModBlocks.FORTRESS_DOOR);
                accept(tab, ModBlocks.TECH_TILE);
                accept(tab, ModBlocks.TECH_TILE_WITH_SIGN);
                accept(tab, ModBlocks.DARK_TILE);
                accept(tab, ModBlocks.ERODED_DARK_TILE);
                accept(tab, ModBlocks.LIGHT_TILE);
                accept(tab, ModBlocks.ERODED_LIGHT_TILE);
                accept(tab, ModBlocks.IRON_BRICKS);
                accept(tab, ModBlocks.IRON_BRICKS_SLAB);
                accept(tab, ModBlocks.IRON_BRICKS_STAIRS);
                accept(tab, ModBlocks.IRON_COLLAGE);
                accept(tab, ModBlocks.BUNKER_BRICKS);
                accept(tab, ModBlocks.SIMPLE_VAULT_STAIRS);
                accept(tab, ModBlocks.THICK_VAULT_STAIRS);
                accept(tab, ModBlocks.LOW_FENCE);
                accept(tab, ModItems.MUSIC_DISC_HALFWAY);
                accept(tab, ModBlocks.COARSE_CACTUS_PLANKS);
                accept(tab, ModBlocks.COARSE_CACTUS_DOOR);
                accept(tab, ModBlocks.COARSE_CACTUS_TRAPDOOR);
                accept(tab, ModBlocks.NICKELSTEEL_PLASTIC_CONTAINER);
                accept(tab, ModBlocks.CARGO_BOX);
                accept(tab, ModBlocks.WALL_PAPER);
            })
            .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB_INDUSTRY = TABS.register("tab_industry", () -> CreativeModeTab.builder()
            .title(Component.translatable("item_Group."+ MODID +".tab_industry"))
            .icon(() -> new ItemStack(ModItems.CPU.get()))
            .withTabsBefore(TAB_MISC.getId())
            .displayItems((param, tab) -> {
                accept(tab, ModBlocks.IRON_SCAFFOLDING);
                accept(tab, ModBlocks.NICKELSTEEL_PLASTIC_SCAFFOLDING);
                accept(tab, ModBlocks.SOLAR_PANEL);
                accept(tab, ModBlocks.REDSTONE_POWER_PANEL);
                accept(tab, ModBlocks.TACHYON_PROJECTION_PANEL);
                accept(tab, ModBlocks.DARK_MATTER_RENDER_PANEL);
                accept(tab, ModItems.RAW_NICKEL);
                accept(tab, ModItems.NICKEL_INGOT);
                accept(tab, ModItems.ORGANIC_PLASTIC);
                accept(tab, ModItems.NICKELSTEEL_PLASTIC);
                accept(tab, ModItems.THULIUM_188_INGOT);
                accept(tab, ModItems.ULTRALLOY_INGOT);
                accept(tab, ModItems.LEMURIUM_INGOT);
                accept(tab, ModItems.NETHERITE_NUGGET);
                accept(tab, ModItems.NICKEL_NUGGET);
                accept(tab, ModItems.ORGANIC_PLASTIC_NUGGET);
                accept(tab, ModItems.NICKELSTEEL_PLASTIC_NUGGET);
                accept(tab, ModItems.THULIUM_188_NUGGET);
                accept(tab, ModItems.ULTRALLOY_NUGGET);
                accept(tab, ModItems.LEMURIUM_NUGGET);
                accept(tab, ModItems.COAL_DUST);
                accept(tab, ModItems.COPPER_COIL);
                accept(tab, ModItems.GOLDEN_COIL);
                accept(tab, ModItems.ULTRALLOY_COIL);
                accept(tab, ModItems.EMPTY_BATTERY);
                accept(tab, ModItems.REDSTONE_BATTERY);
                accept(tab, ModItems.BATTERY_WASTE);
                accept(tab, ModItems.VACUUM_TUBE);
                accept(tab, ModItems.CALCITE_CASING);
                accept(tab, ModItems.CPU);
                accept(tab, ModItems.ROCKET_FUEL);
                accept(tab, ModBlocks.NICKEL_BLOCK);
                accept(tab, ModBlocks.ORGANIC_PLASTIC_BLOCK);
                accept(tab, ModBlocks.NICKELSTEEL_PLASTIC_BLOCK);
                accept(tab, ModBlocks.THULIUM_188_BLOCK);
                accept(tab, ModBlocks.ULTRALLOY_BLOCK);
                accept(tab, ModBlocks.LEMURIUM_BLOCK);
            })
            .build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB_WORLD = TABS.register("tab_world", () -> CreativeModeTab.builder()
            .title(Component.translatable("item_Group."+ MODID +".tab_world"))
            .icon(() -> new ItemStack(ModItems.LEMON.get()))
            .withTabsBefore(TAB_INDUSTRY.getId())
            .displayItems((param, tab) -> {
                accept(tab, ModItems.LEMON);
                accept(tab, ModItems.AETHER_ROOT_SPORE);
                accept(tab, ModItems.VERDHELM_BEETLE_SPAWN_EGG);
                accept(tab, ModBlocks.ARCHEOVA_STONE);
                accept(tab, ModBlocks.COSMIC_SAND);
                accept(tab, ModBlocks.COSMIC_SANDSTONE);
                accept(tab, ModBlocks.COSMIC_STONE);
                accept(tab, ModBlocks.DEEPSPACE_STONE);
                accept(tab, ModBlocks.SALTY_ICE);
                accept(tab, ModBlocks.FROZEN_SOIL);
                accept(tab, ModBlocks.MOSS_FROZEN_SOIL);
                accept(tab, ModBlocks.SILVERBLANC_STONE);
                accept(tab, ModBlocks.SILVERBLANC_STONE_SLAB);
                accept(tab, ModBlocks.SILVERBLANC_STONE_STAIRS);
                accept(tab, ModBlocks.SILVERBLANC_STONE_WALL);
                accept(tab, ModBlocks.SILVERBLANC_COBBLESTONE_SLAB);
                accept(tab, ModBlocks.SILVERBLANC_COBBLESTONE_STAIRS);
                accept(tab, ModBlocks.SILVERBLANC_COBBLESTONE_WALL);
                accept(tab, ModBlocks.SILVERBLANC_COBBLESTONE);
                accept(tab, ModBlocks.MOSS_SILVERBLANC_STONE);
                accept(tab, ModBlocks.SILVERBLANC_STONE_BRICKS);
                accept(tab, ModBlocks.SILVERBLANC_STONE_BRICKS_SLAB);
                accept(tab, ModBlocks.SILVERBLANC_STONE_BRICKS_STAIRS);
                accept(tab, ModBlocks.SILVERBLANC_STONE_BRICKS_WALL);
                accept(tab, ModBlocks.NICKEL_ORE);
                accept(tab, ModBlocks.DEEPSLATE_NICKEL_ORE);
                accept(tab, ModBlocks.SILVERBLANC_COPPER_ORE);
                accept(tab, ModBlocks.SILVERBLANC_IRON_ORE);
                accept(tab, ModBlocks.SILVERBLANC_NICKEL_ORE);
                accept(tab, ModBlocks.SILVERBLANC_GOLD_ORE);
                accept(tab, ModBlocks.SILVERBLANC_LAPIS_ORE);
                accept(tab, ModBlocks.SILVERBLANC_DIAMOND_ORE);
                accept(tab, ModBlocks.WHITE_BUD);
                accept(tab, ModBlocks.STRANGE_FERN);
                accept(tab, ModBlocks.COARSE_CACTUS);
                accept(tab, ModBlocks.BLUE_KODOKU_FLOWER);
                accept(tab, ModBlocks.PURPLE_KODOKU_FLOWER);
                accept(tab, ModBlocks.WHITE_KODOKU_FLOWER);
                accept(tab, ModBlocks.AETHER_ROOT);
                accept(tab, ModBlocks.AETHER_ROOT_HAIR);
            })
            .build());
    private static void accept(CreativeModeTab.Output tab, DeferredItem<? extends ItemLike> object) {
        tab.accept(object.get());
    }
    private static void accept(CreativeModeTab.Output tab, DeferredBlock<? extends ItemLike> object) {
        tab.accept(object.get());
    }

}
