package com.progred.registry;

import com.progred.item.Discharger;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static com.progred.Main.MODID;

public class ModItems {
    public static final Item PIZDEC = new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(500).saturationModifier(500f).build()).fireproof().rarity(Rarity.EPIC));
    public static final Discharger DISCHARGER = new Discharger(new FabricItemSettings().maxCount(1).rarity(Rarity.UNCOMMON));
    public static void registerItems() {
        Registry.register(Registries.ITEM, new Identifier(MODID, "pizdec"), PIZDEC);
        Registry.register(Registries.ITEM, new Identifier(MODID, "discharger"), DISCHARGER);
    }
}
