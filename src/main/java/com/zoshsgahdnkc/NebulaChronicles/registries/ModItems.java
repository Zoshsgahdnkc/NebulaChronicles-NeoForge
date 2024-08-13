package com.zoshsgahdnkc.NebulaChronicles.registries;

import com.zoshsgahdnkc.NebulaChronicles.NebulaChronicles;
import com.zoshsgahdnkc.NebulaChronicles.item.AethorRootSporeItem;
import com.zoshsgahdnkc.NebulaChronicles.item.LemonItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItems {
   public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NebulaChronicles.MODID);
    public static final DeferredItem<Item> MUSIC_DISC_HALFWAY = ITEMS.register(
            "music_disc_halfway",() -> new Item(new Item.Properties().stacksTo(1)
                    .rarity(Rarity.RARE).jukeboxPlayable(ModJukeboxSongs.MUSIC_DISC_HALFWAY))
    );

    public static final DeferredItem<Item> RAW_NICKEL = ITEMS.registerSimpleItem("raw_nickel", new Item.Properties());
    public static final DeferredItem<Item> NICKEL_INGOT = ITEMS.registerSimpleItem("nickel_ingot", new Item.Properties());
    public static final DeferredItem<Item> NICKEL_NUGGET = ITEMS.registerSimpleItem("nickel_nugget", new Item.Properties());
    public static final DeferredItem<Item> ORGANIC_PLASTIC = ITEMS.registerSimpleItem("organic_plastic", new Item.Properties());
    public static final DeferredItem<Item> ORGANIC_PLASTIC_NUGGET = ITEMS.registerSimpleItem("organic_plastic_nugget", new Item.Properties());
    public static final DeferredItem<Item> NICKELSTEEL_PLASTIC = ITEMS.registerSimpleItem("nickelsteel_plastic", new Item.Properties());
    public static final DeferredItem<Item> NICKELSTEEL_PLASTIC_NUGGET = ITEMS.registerSimpleItem("nickelsteel_plastic_nugget", new Item.Properties());
    public static final DeferredItem<Item> THULIUM_188_INGOT = ITEMS.registerSimpleItem("thulium_188_ingot", new Item.Properties());
    public static final DeferredItem<Item> THULIUM_188_NUGGET = ITEMS.registerSimpleItem("thulium_188_nugget", new Item.Properties());
    public static final DeferredItem<Item> ULTRALLOY_INGOT = ITEMS.registerSimpleItem("ultralloy_ingot", new Item.Properties());
    public static final DeferredItem<Item> ULTRALLOY_NUGGET = ITEMS.registerSimpleItem("ultralloy_nugget", new Item.Properties());
    public static final DeferredItem<Item> LEMURIUM_INGOT = ITEMS.registerSimpleItem("lemurium_ingot", new Item.Properties());
    public static final DeferredItem<Item> LEMURIUM_NUGGET = ITEMS.registerSimpleItem("lemurium_nugget", new Item.Properties());
    public static final DeferredItem<Item> NETHERITE_NUGGET = ITEMS.registerSimpleItem("netherite_nugget", new Item.Properties());
    public static final DeferredItem<Item> COAL_DUST = ITEMS.registerSimpleItem("coal_dust", new Item.Properties());
    public static final DeferredItem<Item> COPPER_COIL = ITEMS.registerSimpleItem("copper_coil", new Item.Properties());
    public static final DeferredItem<Item> GOLDEN_COIL = ITEMS.registerSimpleItem("golden_coil", new Item.Properties());
    public static final DeferredItem<Item> ULTRALLOY_COIL = ITEMS.registerSimpleItem("ultralloy_coil", new Item.Properties());
    public static final DeferredItem<Item> EMPTY_BATTERY = ITEMS.registerSimpleItem("empty_battery", new Item.Properties().stacksTo(16));
    public static final DeferredItem<Item> REDSTONE_BATTERY = ITEMS.registerSimpleItem("redstone_battery", new Item.Properties().stacksTo(16));
    public static final DeferredItem<Item> BATTERY_WASTE = ITEMS.registerSimpleItem("battery_waste", new Item.Properties());
    public static final DeferredItem<Item> VACUUM_TUBE = ITEMS.registerSimpleItem("vacuum_tube", new Item.Properties());
    public static final DeferredItem<Item> CALCITE_CASING = ITEMS.registerSimpleItem("calcite_casing", new Item.Properties());
    public static final DeferredItem<Item> CPU = ITEMS.registerSimpleItem("cpu", new Item.Properties().stacksTo(16));
    public static final DeferredItem<Item> ROCKET_FUEL = ITEMS.registerSimpleItem("rocket_fuel", new Item.Properties());
    public static final DeferredItem<Item> LEMON = ITEMS.register("lemon", () -> new LemonItem(new Item.Properties()));
    public static final DeferredItem<Item> AETHER_ROOT_SPORE = ITEMS.register("aether_root_spore", () -> new AethorRootSporeItem(new Item.Properties()));

}
