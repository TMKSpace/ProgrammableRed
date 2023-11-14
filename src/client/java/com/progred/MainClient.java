package com.progred;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

import static com.progred.registry.ModBlocks.TRANSISTOR;

public class MainClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
//		BlockRenderLayerMap.INSTANCE.putBlock(TRANSISTOR.getLeft(), RenderLayer.getCutout());
	}
}