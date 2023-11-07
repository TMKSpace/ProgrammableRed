package com.progred;

import com.progred.item.mrp.AdjustedRedstoneItem;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

import static com.progred.block.ModBlocks.*;

public class ColoredItems {
    public static void registerColoredItems(){
        for(String color : WIRE_BLOCK_ITEMS.keySet()){
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((AdjustedRedstoneItem) stack.getItem()).getColor(),WIRE_BLOCK_ITEMS.get(color), INFINIWIRE_BLOCK_ITEMS.get(color));
        }
    }
}
