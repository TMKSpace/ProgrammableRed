package com.progred.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.HashMap;

import static com.progred.Main.MODID;
public class ModBlocks {
    public static final WebSocketBlock WEBSOCKET_BLOCK = WebSocketBlock.WEBSOCKETBLOCK;
    public static final Item WEBSOCKET_BLOCK_ITEM = new BlockItem(WEBSOCKET_BLOCK,new Item.Settings());
    public static final TestBlock TEST_BLOCK = new TestBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    public static final Item TEST_BLOCK_ITEM = new BlockItem(TEST_BLOCK,new Item.Settings());

    public static void RegisterBlocks(){
         Registry.register(Registries.BLOCK, new Identifier(MODID, "websocket_block"), WEBSOCKET_BLOCK);
         Registry.register(Registries.ITEM, new Identifier(MODID,"websocket_block"), WEBSOCKET_BLOCK_ITEM);
         Registry.register(Registries.BLOCK, new Identifier(MODID, "test_block"), TEST_BLOCK);
         Registry.register(Registries.ITEM, new Identifier(MODID, "test_block"), TEST_BLOCK_ITEM);
    }

    // а можно я буду настраиватьэто злоебучее свечение с помощью присутствия/отсутствия редстоуновых ламп под проводом?
    //а ещё можно пиздыцкнуть такой блок который будет вести себя точно так же как и binary wire но его можно будет передвигать поршнем
}
