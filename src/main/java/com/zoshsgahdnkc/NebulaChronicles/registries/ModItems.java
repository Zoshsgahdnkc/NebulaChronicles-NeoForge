package com.zoshsgahdnkc.NebulaChronicles.registries;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.item.AethorRootSporeItem;
import com.zoshsgahdnkc.NebulaChronicles.item.LemonItem;
import com.zoshsgahdnkc.NebulaChronicles.item.ScrewdriverItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.List;
import java.util.Optional;

public class ModItems {
   public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NebulaChronicles.MODID);
    public static final DeferredItem<Item> MUSIC_DISC_HALFWAY = ITEMS.register(
            "music_disc_halfway",() -> new Item(new Item.Properties().stacksTo(1)
                    .rarity(Rarity.RARE).jukeboxPlayable(ModJukeboxSongs.MUSIC_DISC_HALFWAY))
    );

    // special block items
 public static final DeferredItem<? extends Item> ULTRALLOY_BLOCK = ITEMS.registerSimpleBlockItem(
         ModBlocks.ULTRALLOY_BLOCK, new Item.Properties().fireResistant()
    );
 public static final DeferredItem<? extends Item> LEMURIUM_BLOCK = ITEMS.registerSimpleBlockItem(
         ModBlocks.LEMURIUM_BLOCK, new Item.Properties().fireResistant()
 );


 public static final DeferredItem<Item> WHITE_BUD_LEAVES = ITEMS.registerSimpleItem("white_bud_leaves");
 public static final DeferredItem<Item> PEBBLE_BERRIES = ITEMS.registerSimpleItem("pebble_berries", new Item.Properties().food(
         new FoodProperties(1, 0.3f, false, 2.4f, Optional.empty(), List.of())));
 public static final DeferredItem<Item> DUOCURRENT_CRYSTAL_SHARD = ITEMS.registerSimpleItem("duocurrent_crystal_shard");
 public static final DeferredItem<Item> WHITE_BUD_STEW = ITEMS.registerSimpleItem("white_bud_stew", new Item.Properties().stacksTo(16).food(
         new FoodProperties(4, 0.05f, false, 2.4f, Optional.of(new ItemStack(Items.BOWL)), List.of())));
 public static final DeferredItem<Item> RAW_NICKEL = ITEMS.registerSimpleItem("raw_nickel");
 public static final DeferredItem<Item> NICKEL_INGOT = ITEMS.registerSimpleItem("nickel_ingot");
 public static final DeferredItem<Item> NICKEL_NUGGET = ITEMS.registerSimpleItem("nickel_nugget");
 public static final DeferredItem<Item> ORGANIC_PLASTIC = ITEMS.registerSimpleItem("organic_plastic");
 public static final DeferredItem<Item> ORGANIC_PLASTIC_NUGGET = ITEMS.registerSimpleItem("organic_plastic_nugget");
 public static final DeferredItem<Item> NICKELSTEEL_PLASTIC = ITEMS.registerSimpleItem("nickelsteel_plastic");
 public static final DeferredItem<Item> NICKELSTEEL_PLASTIC_NUGGET = ITEMS.registerSimpleItem("nickelsteel_plastic_nugget");
 public static final DeferredItem<Item> THULIUM_188_INGOT = ITEMS.registerSimpleItem("thulium_188_ingot");
 public static final DeferredItem<Item> THULIUM_188_NUGGET = ITEMS.registerSimpleItem("thulium_188_nugget");
 public static final DeferredItem<Item> ULTRALLOY_INGOT = ITEMS.registerSimpleItem("ultralloy_ingot", new Item.Properties().fireResistant());
 public static final DeferredItem<Item> ULTRALLOY_NUGGET = ITEMS.registerSimpleItem("ultralloy_nugget", new Item.Properties().fireResistant());
 public static final DeferredItem<Item> LEMURIUM_INGOT = ITEMS.registerSimpleItem("lemurium_ingot", new Item.Properties().fireResistant());
 public static final DeferredItem<Item> LEMURIUM_NUGGET = ITEMS.registerSimpleItem("lemurium_nugget", new Item.Properties().fireResistant());
 public static final DeferredItem<Item> NETHERITE_NUGGET = ITEMS.registerSimpleItem("netherite_nugget", new Item.Properties().fireResistant());
 public static final DeferredItem<Item> COAL_DUST = ITEMS.registerSimpleItem("coal_dust");
 public static final DeferredItem<Item> COPPER_COIL = ITEMS.registerSimpleItem("copper_coil");
 public static final DeferredItem<Item> GOLDEN_COIL = ITEMS.registerSimpleItem("golden_coil");
 public static final DeferredItem<Item> ULTRALLOY_COIL = ITEMS.registerSimpleItem("ultralloy_coil");
 public static final DeferredItem<Item> EMPTY_BATTERY = ITEMS.registerSimpleItem("empty_battery", new Item.Properties().stacksTo(16));
 public static final DeferredItem<Item> REDSTONE_BATTERY = ITEMS.registerSimpleItem("redstone_battery", new Item.Properties().stacksTo(16));
 public static final DeferredItem<Item> BIOMASS_BATTERY = ITEMS.registerSimpleItem("biomass_battery", new Item.Properties().stacksTo(16));
 public static final DeferredItem<Item> DUOCURRENT_BATTERY = ITEMS.registerSimpleItem("duocurrent_battery", new Item.Properties().stacksTo(16));
 public static final DeferredItem<Item> BATTERY_WASTE = ITEMS.registerSimpleItem("battery_waste");
 public static final DeferredItem<Item> VACUUM_TUBE = ITEMS.registerSimpleItem("vacuum_tube", new Item.Properties().stacksTo(4));
 public static final DeferredItem<Item> CALCITE_CASING = ITEMS.registerSimpleItem("calcite_casing");
 public static final DeferredItem<Item> CPU = ITEMS.registerSimpleItem("cpu", new Item.Properties().stacksTo(16));
 public static final DeferredItem<Item> ROCKET_FUEL = ITEMS.registerSimpleItem("rocket_fuel");
 public static final DeferredItem<Item> LEMON = ITEMS.register("lemon", () -> new LemonItem(new Item.Properties()));
 public static final DeferredItem<Item> AETHER_ROOT_SPORE = ITEMS.register("aether_root_spore", () -> new AethorRootSporeItem(new Item.Properties()));
 public static final DeferredItem<Item> VERDHELM_BEETLE_SPAWN_EGG = ITEMS.registerItem("verdhelm_beetle_spawn_egg",
            (properties) -> new DeferredSpawnEggItem(ModEntities.VERDHELM_BEETLE, 0x998580, 0x118877, properties));
 public static final DeferredItem<Item> SPIKED_VERDHELM_BEETLE_SPAWN_EGG = ITEMS.registerItem("spiked_verdhelm_beetle_spawn_egg",
         (properties) -> new DeferredSpawnEggItem(ModEntities.SPIKED_VERDHELM_BEETLE, 0x999999, 0x678C84, properties));
 public static final DeferredItem<Item> SCREWDRIVER = ITEMS.register("screwdriver" ,() -> new ScrewdriverItem(new Item.Properties().stacksTo(1)));

}
