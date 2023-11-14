package com.progred.datagen;

import com.progred.registry.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class BlockLootTablesGenerator extends FabricBlockLootTableProvider {
    public BlockLootTablesGenerator(FabricDataOutput dataOutput){
        super(dataOutput);
    }

    @Override
    public void generate() {
        this.addDrop(ModBlocks.TEST_BLOCK.getLeft());
        this.addDrop(ModBlocks.WEBSOCKET_BLOCK.getLeft());
    }
}
