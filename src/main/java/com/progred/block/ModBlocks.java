package com.progred.block;

import com.progred.item.AdjustedRedstoneItem;
import com.progred.item.InfiniwireItem;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.ItemTags;
import com.progred.block.mrp.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.tuple.Pair;
import static com.progred.Main.MODID;

import java.util.HashMap;

public class ModBlocks {
    public static final WebSocketBlock WEBSOCKET_BLOCK = WebSocketBlock.WEBSOCKETBLOCK;  //new WebSocketBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f));
    public static final Item WEBSOCKET_BLOCK_ITEM = new BlockItem(WEBSOCKET_BLOCK,new Item.Settings());
    public static final TestBlock TEST_BLOCK = new TestBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    public static final Item TEST_BLOCK_ITEM = new BlockItem(TEST_BLOCK,new Item.Settings());

    public static HashMap<String, Float> COLORS = new HashMap<>(); // TODO: Pair<Float, TagKey<Item>> instead of just Float. Maybe it's better to set just Item. (You can get any of them using Items.ID).
    public static HashMap<String, AdjustedRedstoneWireBlock> WIRE_BLOCKS;
    public static final HashMap<String, AdjustedRedstoneWireBlock> WIRE_BLOCKS_EXCLUDING_REDSTONE;
    public static final HashMap<String, AdjustedRedstoneItem> WIRE_BLOCK_ITEMS;
    public static final HashMap<String, AdjustedRedstoneItem> WIRE_BLOCK_ITEMS_EXCLUDING_REDSTONE;
    public static final HashMap<String, InfiniwireBlock> INFINIWIRE_BLOCKS;
    public static final HashMap<String, InfiniwireItem> INFINIWIRE_BLOCK_ITEMS;

    static{
        COLORS.put("blue", 2.0F / 3.0F);
        COLORS.put("green", 1.0F / 3.0F);
        COLORS.put("orange", 1.0F / 12.0F);
        COLORS.put("pink", 5.0F / 6.0F);
        COLORS.put("yellow", 1.0F / 6.0F);
        WIRE_BLOCKS = new HashMap<>(COLORS.size()+1);
        WIRE_BLOCKS_EXCLUDING_REDSTONE = new HashMap<>(COLORS.size());
        WIRE_BLOCK_ITEMS = new HashMap<>(COLORS.size() + 1);
        WIRE_BLOCK_ITEMS_EXCLUDING_REDSTONE = new HashMap<>(COLORS.size());
        for (String color : COLORS.keySet()) {
            String id = color + "_wire";
            AdjustedRedstoneWireBlock wireBlockObject =
                    Registry.register(Registries.BLOCK ,new Identifier(MODID,id), new AdjustedRedstoneWireBlock(COLORS.get(color)));
            WIRE_BLOCKS.put(color, wireBlockObject);
            WIRE_BLOCKS_EXCLUDING_REDSTONE.put(color, wireBlockObject);
            AdjustedRedstoneItem wireItemObject = Registry.register(Registries.ITEM, new Identifier(MODID, id),
                    WIRE_BLOCKS.get(color).createBlockItem(ItemTags.WOOL_CARPETS)); // TODO: Replace WOOL_CARPETS with dye tag (CHECK MoreWires MOD).
            WIRE_BLOCK_ITEMS.put(color, wireItemObject);
            WIRE_BLOCK_ITEMS_EXCLUDING_REDSTONE.put(color, wireItemObject);
        }
        String red = "red";
        COLORS.put(red, 0.0F);
        WIRE_BLOCKS.put(red, Registry
                .register(Registries.BLOCK, new Identifier(MODID,"redstone_wire"), new AdjustedRedstoneWireBlock(COLORS.get(red))));
        WIRE_BLOCK_ITEMS.put(red, Registry
                .register(Registries.ITEM,new Identifier(MODID,"redstone"), WIRE_BLOCKS.get(red).createBlockItem(ItemTags.WOOL_CARPETS)));
        INFINIWIRE_BLOCKS = new HashMap<>(COLORS.size());
        INFINIWIRE_BLOCK_ITEMS = new HashMap<>(COLORS.size());
        for (String color : COLORS.keySet()) {
            String id = color + "_infiniwire";
            INFINIWIRE_BLOCKS.put(color, Registry.register(Registries.BLOCK , new Identifier(MODID, id), new InfiniwireBlock(COLORS.get(color))));
            INFINIWIRE_BLOCK_ITEMS.put(color, (InfiniwireItem) Registry.register(Registries.ITEM , new Identifier(MODID, id),
                    INFINIWIRE_BLOCKS.get(color).createBlockItem(ItemTags.WOOL_CARPETS)));
        }

    }
//    public static final InfiniwireBlock RED_INFINIWIRE = new InfiniwireBlock(1.0F); // а можно я буду настраиватьэто злоебучее свечение с помощью присутствия/отсутствия редстоуновых ламп под проводом?
//    public static final InfiniwireBlock GREEN_INFINIWIRE = new InfiniwireBlock(0.5F);
//    public static final InfiniwireBlock BLUE_INFINIWIRE = new InfiniwireBlock(0.3F);
//    public static final InfiniwireBlock YELLOW_INFINIWIRE = new InfiniwireBlock(0.7F);
//    public static final InfiniwireBlock PINK_INFINIWIRE = new InfiniwireBlock(0.1F);
//    public static final Item RED_INFINIWIRE_ITEM = RED_INFINIWIRE.createBlockItem(ItemTags.WOOL_CARPETS);
//    public static final Item GREEN_INFINIWIRE_ITEM = GREEN_INFINIWIRE.createBlockItem(ItemTags.WOOL_CARPETS);
//    public static final Item BLUE_INFINIWIRE_ITEM = BLUE_INFINIWIRE.createBlockItem(ItemTags.WOOL_CARPETS);
//    public static final Item YELLOW_INFINIWIRE_ITEM = YELLOW_INFINIWIRE.createBlockItem(ItemTags.WOOL_CARPETS);
//    public static final Item PINK_INFINIWIRE_ITEM = PINK_INFINIWIRE.createBlockItem(ItemTags.WOOL_CARPETS);
    // а ещё можно пиздыцкнуть такой блок который будет вести себя точно так же как и binary wire но его можно будет передвигать поршнем
}
