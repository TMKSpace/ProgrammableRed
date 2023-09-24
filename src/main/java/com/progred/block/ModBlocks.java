package com.progred.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class ModBlocks {
    public static final Block WEBSOCKET_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0f));
    public static final Item WEBSOCKET_BLOCK_ITEM = new BlockItem(WEBSOCKET_BLOCK,new Item.Settings());
}
