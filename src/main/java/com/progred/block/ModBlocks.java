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

    public static HashMap<String, Pair<Pair<Float,Integer>,Item>> COLORS = new HashMap<>();
    public static HashMap<String, AdjustedRedstoneWireBlock> WIRE_BLOCKS;
    public static final HashMap<String, AdjustedRedstoneItem> WIRE_BLOCK_ITEMS;
    public static final HashMap<String, InfiniwireBlock> INFINIWIRE_BLOCKS;
    public static final HashMap<String, InfiniwireItem> INFINIWIRE_BLOCK_ITEMS;

    static{
        COLORS.put("blue", Pair.of(Pair.of(2.0F / 3.0F,255), Items.BLUE_DYE));
        COLORS.put("green", Pair.of(Pair.of(1.0F / 3.0F,65280), Items.GREEN_DYE));
        COLORS.put("orange", Pair.of(Pair.of(1.0F / 12.0F,16755200), Items.ORANGE_DYE));
        COLORS.put("pink", Pair.of(Pair.of(5.0F / 6.0F,16711880), Items.PINK_DYE));
        COLORS.put("yellow", Pair.of(Pair.of(1.0F / 6.0F,16776960), Items.YELLOW_DYE));
        WIRE_BLOCKS = new HashMap<>(COLORS.size()+1);
//        WIRE_BLOCKS_EXCLUDING_REDSTONE = new HashMap<>(COLORS.size());
        WIRE_BLOCK_ITEMS = new HashMap<>(COLORS.size() + 1);
//        WIRE_BLOCK_ITEMS_EXCLUDING_REDSTONE = new HashMap<>(COLORS.size());
        for (String color : COLORS.keySet()) {
            String id = color + "_wire";
            AdjustedRedstoneWireBlock wireBlockObject =
                    Registry.register(Registries.BLOCK ,new Identifier(MODID,id), new AdjustedRedstoneWireBlock(COLORS.get(color).getLeft().getLeft()));
            WIRE_BLOCKS.put(color, wireBlockObject);
//            WIRE_BLOCKS_EXCLUDING_REDSTONE.put(color, wireBlockObject);
            AdjustedRedstoneItem wireItemObject = Registry.register(Registries.ITEM, new Identifier(MODID, id),
                    WIRE_BLOCKS.get(color).createBlockItem(COLORS.get(color).getRight(), COLORS.get(color).getLeft().getRight()));
            WIRE_BLOCK_ITEMS.put(color, wireItemObject);
//            WIRE_BLOCK_ITEMS_EXCLUDING_REDSTONE.put(color, wireItemObject);
        }
        String red = "red";
        COLORS.put(red, Pair.of(Pair.of(0.0F,16711680),Items.RED_DYE));
        WIRE_BLOCKS.put(red, Registry
                .register(Registries.BLOCK, new Identifier(MODID,"red_wire"), new AdjustedRedstoneWireBlock(COLORS.get(red).getLeft().getLeft())));
        WIRE_BLOCK_ITEMS.put(red, Registry
                .register(Registries.ITEM,new Identifier(MODID,"red_wire"), WIRE_BLOCKS.get(red).createBlockItem(COLORS.get(red).getRight(), COLORS.get(red).getLeft().getRight())));
        INFINIWIRE_BLOCKS = new HashMap<>(COLORS.size());
        INFINIWIRE_BLOCK_ITEMS = new HashMap<>(COLORS.size());
        for (String color : COLORS.keySet()) {
            String id = color + "_infiniwire";
            INFINIWIRE_BLOCKS.put(color, Registry.register(Registries.BLOCK , new Identifier(MODID, id), new InfiniwireBlock(COLORS.get(color).getLeft().getLeft())));
            INFINIWIRE_BLOCK_ITEMS.put(color, Registry.register(Registries.ITEM , new Identifier(MODID, id),
                    INFINIWIRE_BLOCKS.get(color).createBlockItem(COLORS.get(color).getRight(), COLORS.get(color).getLeft().getRight())));
        }

    }
    // а можно я буду настраиватьэто злоебучее свечение с помощью присутствия/отсутствия редстоуновых ламп под проводом?
    //а ещё можно пиздыцкнуть такой блок который будет вести себя точно так же как и binary wire но его можно будет передвигать поршнем
}
