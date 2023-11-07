package com.progred.datagen;

import com.progred.block.ModBlocks;
import com.progred.block.mrp.AdjustedRedstoneWireBlock;
import com.progred.item.AdjustedRedstoneItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.Optional;

import static com.progred.Main.MODID;
import static com.progred.block.ModBlocks.*;

public class ModelDataGenerator extends FabricModelProvider {
    public ModelDataGenerator(FabricDataOutput generator){
        super(generator);
    }

    private static Model block(String parent) {
        return new Model(Optional.of(new Identifier("block/" + parent)), Optional.empty());
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) { // TODO: Write some generator code. Get vanilla redstone models.
        for(AdjustedRedstoneWireBlock Block : WIRE_BLOCKS.values()){
            blockStateModelGenerator.registerStateWithModelReference(Block, Blocks.REDSTONE_WIRE);
        }
    }

    private static Model item(String parent) {
        return new Model(Optional.of(new Identifier(MODID,"item/" + parent)), Optional.empty());
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator){
        for(String key : WIRE_BLOCK_ITEMS.keySet()){
            itemModelGenerator.register(WIRE_BLOCK_ITEMS.get(key), item("template_redstone"));
        }
        for(String key : INFINIWIRE_BLOCK_ITEMS.keySet()){
            itemModelGenerator.register(INFINIWIRE_BLOCK_ITEMS.get(key), item("template_redstone"));
        }
    }
}
