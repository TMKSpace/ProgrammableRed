package com.progred;

import com.progred.block.mrp.AdjustedRedstoneWireBlock;
import com.progred.item.mrp.AdjustedRedstoneItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.render.RenderLayer;

import java.awt.*;

import static com.progred.block.ModBlocks.*;

public class MainClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		for(String color : WIRE_BLOCK_ITEMS.keySet()){
			//ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((AdjustedRedstoneItem) stack.getItem()).getColor(),WIRE_BLOCK_ITEMS.get(color), INFINIWIRE_BLOCK_ITEMS.get(color));
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((AdjustedRedstoneItem) stack.getItem()).getColor(),WIRE_BLOCK_ITEMS.get(color), INFINIWIRE_BLOCK_ITEMS.get(color));
			ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> AdjustedRedstoneWireBlock.getColor(state), WIRE_BLOCKS.get(color), INFINIWIRE_BLOCKS.get(color));
		}
		for(String color : WIRE_BLOCKS.keySet()){
			BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), WIRE_BLOCKS.get(color), INFINIWIRE_BLOCKS.get(color));
		}
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}