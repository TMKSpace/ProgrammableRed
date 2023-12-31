package com.progred.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

import java.util.Optional;

import static com.progred.Main.MODID;

public class ModelDataGenerator extends FabricModelProvider {
    public ModelDataGenerator(FabricDataOutput generator){
        super(generator);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    private static Model item(String parent) {
        return new Model(Optional.of(new Identifier(MODID,"item/" + parent)), Optional.empty());
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator){
    }
}
