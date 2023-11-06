package com.progred.datagen;

import com.progred.block.ModBlocks;
import com.progred.item.AdjustedRedstoneItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class ModelDataGenerator extends FabricModelProvider {
    public ModelDataGenerator(FabricDataOutput generator){
        super(generator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) { // TODO: Write some generator code. Get vanilla redstone models.
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator){} // TODO: Write some generator code. Get vanilla redstone models. x2.

}
