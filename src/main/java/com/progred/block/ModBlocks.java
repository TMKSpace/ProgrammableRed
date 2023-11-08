package com.progred.block;

import com.progred.item.mrp.AdjustedRedstoneItem;
import com.progred.item.mrp.InfiniwireItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import com.progred.block.mrp.*;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.tuple.Pair;
import static com.progred.Main.MODID;

import java.util.HashMap;

public class ModBlocks {
    public static HashMap<String, Pair<Integer,Item>> COLORS = new HashMap<>();
    public static HashMap<String, AdjustedRedstoneWireBlock> WIRE_BLOCKS;
    public static final HashMap<String, AdjustedRedstoneItem> WIRE_BLOCK_ITEMS;
    public static final HashMap<String, InfiniwireBlock> INFINIWIRE_BLOCKS;
    public static final HashMap<String, InfiniwireItem> INFINIWIRE_BLOCK_ITEMS;

    static{
        COLORS.put("blue", Pair.of(255, Items.BLUE_DYE));
        COLORS.put("green", Pair.of(65280, Items.GREEN_DYE));
        COLORS.put("orange", Pair.of(16755200, Items.ORANGE_DYE));
        COLORS.put("pink", Pair.of(16711880, Items.PINK_DYE));
        COLORS.put("yellow", Pair.of(16776960, Items.YELLOW_DYE));
        WIRE_BLOCKS = new HashMap<>(COLORS.size()+1);
//        WIRE_BLOCKS_EXCLUDING_REDSTONE = new HashMap<>(COLORS.size());
        WIRE_BLOCK_ITEMS = new HashMap<>(COLORS.size() + 1);
//        WIRE_BLOCK_ITEMS_EXCLUDING_REDSTONE = new HashMap<>(COLORS.size());
        for (String color : COLORS.keySet()) {
            String id = color + "_wire";
            AdjustedRedstoneWireBlock wireBlockObject =
                    Registry.register(Registries.BLOCK ,new Identifier(MODID,id), new AdjustedRedstoneWireBlock(COLORS.get(color).getLeft()));
            WIRE_BLOCKS.put(color, wireBlockObject);
//            WIRE_BLOCKS_EXCLUDING_REDSTONE.put(color, wireBlockObject);
            AdjustedRedstoneItem wireItemObject = Registry.register(Registries.ITEM, new Identifier(MODID, id),
                    WIRE_BLOCKS.get(color).createBlockItem(COLORS.get(color).getRight()));
            WIRE_BLOCK_ITEMS.put(color, wireItemObject);
//            WIRE_BLOCK_ITEMS_EXCLUDING_REDSTONE.put(color, wireItemObject);
        }
        String red = "red";
        COLORS.put(red, Pair.of(16711680,Items.RED_DYE));
        WIRE_BLOCKS.put(red, Registry
                .register(Registries.BLOCK, new Identifier(MODID,"red_wire"), new AdjustedRedstoneWireBlock(COLORS.get(red).getLeft())));
        WIRE_BLOCK_ITEMS.put(red, Registry
                .register(Registries.ITEM,new Identifier(MODID,"red_wire"), WIRE_BLOCKS.get(red).createBlockItem(COLORS.get(red).getRight())));
        INFINIWIRE_BLOCKS = new HashMap<>(COLORS.size());
        INFINIWIRE_BLOCK_ITEMS = new HashMap<>(COLORS.size());
        for (String color : COLORS.keySet()) {
            String id = color + "_infiniwire";
            INFINIWIRE_BLOCKS.put(color, Registry.register(Registries.BLOCK , new Identifier(MODID, id), new InfiniwireBlock(COLORS.get(color).getLeft())));
            INFINIWIRE_BLOCK_ITEMS.put(color, Registry.register(Registries.ITEM , new Identifier(MODID, id),
                    INFINIWIRE_BLOCKS.get(color).createBlockItem(COLORS.get(color).getRight())));
        }

    }

    public static final WebSocketBlock WEBSOCKET_BLOCK = Registry.register(Registries.BLOCK, new Identifier(MODID, "websocket_block"), WebSocketBlock.WEBSOCKETBLOCK);
    public static final Item WEBSOCKET_BLOCK_ITEM = Registry.register(Registries.ITEM, new Identifier(MODID,"websocket_block"), new BlockItem(WEBSOCKET_BLOCK,new Item.Settings()));
    public static final TestBlock TEST_BLOCK = Registry.register(Registries.BLOCK, new Identifier(MODID, "test_block"), new TestBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f)));
    public static final Item TEST_BLOCK_ITEM = Registry.register(Registries.ITEM, new Identifier(MODID, "test_block"), new BlockItem(TEST_BLOCK,new Item.Settings()));

    // а можно я буду настраиватьэто злоебучее свечение с помощью присутствия/отсутствия редстоуновых ламп под проводом?
    //а ещё можно пиздыцкнуть такой блок который будет вести себя точно так же как и binary wire но его можно будет передвигать поршнем
}
