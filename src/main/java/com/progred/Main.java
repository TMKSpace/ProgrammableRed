package com.progred;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.progred.block.ModBlocks.*;

public class Main implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MODID = "progred";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		Registry.register(Registries.BLOCK, new Identifier(MODID, "websocket_block"), WEBSOCKET_BLOCK);
		Registry.register(Registries.ITEM, new Identifier(MODID, "websocket_block"), WEBSOCKET_BLOCK_ITEM);
		Registry.register(Registries.BLOCK, new Identifier(MODID, "test_block"), TEST_BLOCK);
		Registry.register(Registries.ITEM, new Identifier(MODID, "test_block"), TEST_BLOCK_ITEM);
		Registry.register(Registries.BLOCK, new Identifier(MODID,"red_infiniwire"),RED_INFINIWIRE);
		Registry.register(Registries.ITEM,new Identifier(MODID,"red_infiniwire"),RED_INFINIWIRE_ITEM);
		Registry.register(Registries.BLOCK, new Identifier(MODID,"green_infiniwire"),GREEN_INFINIWIRE);
		Registry.register(Registries.ITEM,new Identifier(MODID,"green_infiniwire"),GREEN_INFINIWIRE_ITEM);
		Registry.register(Registries.BLOCK, new Identifier(MODID,"blue_infiniwire"),BLUE_INFINIWIRE);
		Registry.register(Registries.ITEM,new Identifier(MODID,"blue_infiniwire"),BLUE_INFINIWIRE_ITEM);
		Registry.register(Registries.BLOCK, new Identifier(MODID,"yellow_infiniwire"),YELLOW_INFINIWIRE);
		Registry.register(Registries.ITEM,new Identifier(MODID,"yellow_infiniwire"),YELLOW_INFINIWIRE_ITEM);
		Registry.register(Registries.BLOCK, new Identifier(MODID,"pink_infiniwire"),PINK_INFINIWIRE);
		Registry.register(Registries.ITEM,new Identifier(MODID,"pink_infiniwire"),PINK_INFINIWIRE_ITEM);
		Registry.register(Registries.ITEM, new Identifier(MODID, "pizdec"), new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(500).saturationModifier(500f).build()).fireproof().rarity(Rarity.EPIC)));

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
			content.add(WEBSOCKET_BLOCK_ITEM);
			content.add(TEST_BLOCK_ITEM);
			content.add(RED_INFINIWIRE_ITEM);
			content.add(GREEN_INFINIWIRE_ITEM);
			content.add(BLUE_INFINIWIRE_ITEM);
			content.add(YELLOW_INFINIWIRE_ITEM);
			content.add(PINK_INFINIWIRE_ITEM);
		});
	}
}