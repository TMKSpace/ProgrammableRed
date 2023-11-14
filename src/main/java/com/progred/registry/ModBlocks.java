package com.progred.registry;

import com.progred.block.*;
import com.progred.block.logic.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.tuple.Pair;

import static com.progred.Main.MODID;
public class ModBlocks {
    private static Pair<Block, Item> block(Block block, Item.Settings settings) {
        return Pair.of(block, new BlockItem(block, settings));
    }
    private static Pair<Block, Item> block(Block block){
        return block(block, new FabricItemSettings());
    }
    private static void registerBlock(Pair<Block, Item> blockItemPair, Identifier id){
        Registry.register(Registries.BLOCK, id, blockItemPair.getLeft());
        Registry.register(Registries.ITEM, id, blockItemPair.getRight());
    }
    public static final Pair<Block, Item> WEBSOCKET_BLOCK = block(WebSocketBlock.WEBSOCKETBLOCK);
    public static final Pair<Block, Item> TEST_BLOCK = block(new TestBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f)));
    public static final Pair<Block, Item> TRANSISTOR = block(new TransistorBlock(FabricBlockSettings.of(Material.METAL)));

    public static void registerBlocks(){
        registerBlock(WEBSOCKET_BLOCK, new Identifier(MODID, "websocket_block"));
        registerBlock(TEST_BLOCK, new Identifier(MODID, "test_block"));
        registerBlock(TRANSISTOR, new Identifier(MODID, "transistor"));
    }

    // а можно я буду настраиватьэто злоебучее свечение с помощью присутствия/отсутствия редстоуновых ламп под проводом?
    //а ещё можно пиздыцкнуть такой блок который будет вести себя точно так же как и binary wire но его можно будет передвигать поршнем
}
