package com.progred;

import net.fabricmc.api.ClientModInitializer;
import com.progred.ColoredItems;

public class MainClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ColoredItems.registerColoredItems();
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}