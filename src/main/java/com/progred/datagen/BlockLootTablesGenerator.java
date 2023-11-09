package com.progred.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class BlockLootTablesGenerator extends FabricBlockLootTableProvider {
    public BlockLootTablesGenerator(FabricDataOutput dataOutput){
        super(dataOutput);
    }

    @Override
    public void generate() {
    }
}
