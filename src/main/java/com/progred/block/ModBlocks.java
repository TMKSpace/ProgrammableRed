package com.progred.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.ItemTags;
import com.progred.block.mrp.*;

public class ModBlocks {
    public static final WebSocketBlock WEBSOCKET_BLOCK = WebSocketBlock.WEBSOCKETBLOCK;  //new WebSocketBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f));
    public static final Item WEBSOCKET_BLOCK_ITEM = new BlockItem(WEBSOCKET_BLOCK,new Item.Settings());
    public static final TestBlock TEST_BLOCK = new TestBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    public static final Item TEST_BLOCK_ITEM = new BlockItem(TEST_BLOCK,new Item.Settings());
    public static final InfiniwireBlock RED_INFINIWIRE = new InfiniwireBlock(1.0F); // а можно я буду настраиватьэто злоебучее свечение с помощью присутствия/отсутствия редстоуновых ламп под проводом?
    public static final InfiniwireBlock GREEN_INFINIWIRE = new InfiniwireBlock(0.5F);
    public static final InfiniwireBlock BLUE_INFINIWIRE = new InfiniwireBlock(0.3F);
    public static final InfiniwireBlock YELLOW_INFINIWIRE = new InfiniwireBlock(0.7F);
    public static final InfiniwireBlock PINK_INFINIWIRE = new InfiniwireBlock(0.1F);
    public static final Item RED_INFINIWIRE_ITEM = RED_INFINIWIRE.createBlockItem(ItemTags.WOOL_CARPETS);
    public static final Item GREEN_INFINIWIRE_ITEM = GREEN_INFINIWIRE.createBlockItem(ItemTags.WOOL_CARPETS);
    public static final Item BLUE_INFINIWIRE_ITEM = BLUE_INFINIWIRE.createBlockItem(ItemTags.WOOL_CARPETS);
    public static final Item YELLOW_INFINIWIRE_ITEM = YELLOW_INFINIWIRE.createBlockItem(ItemTags.WOOL_CARPETS);
    public static final Item PINK_INFINIWIRE_ITEM = PINK_INFINIWIRE.createBlockItem(ItemTags.WOOL_CARPETS);
    // а ещё можно пиздыцкнуть такой блок который будет вести себя точно так же как и binary wire но его можно будет передвигать поршнем
}
