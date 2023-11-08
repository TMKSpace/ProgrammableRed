package com.progred.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

import static com.progred.block.ModBlocks.INFINIWIRE_BLOCKS;
import static com.progred.block.ModBlocks.WIRE_BLOCKS;

public class BlockLootTablesGenerator extends FabricBlockLootTableProvider {
    public BlockLootTablesGenerator(FabricDataOutput dataOutput){
        super(dataOutput);
    }

    @Override
    public void generate() {
        for(String color : WIRE_BLOCKS.keySet()){
            this.addDrop(WIRE_BLOCKS.get(color));
            this.addDrop(INFINIWIRE_BLOCKS.get(color));
        }
    }
}
