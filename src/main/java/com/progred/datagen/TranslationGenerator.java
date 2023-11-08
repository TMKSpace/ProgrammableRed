package com.progred.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.HashMap;

import static com.progred.block.ModBlocks.*;

public class TranslationGenerator extends FabricLanguageProvider {
    public TranslationGenerator(FabricDataOutput dataOutput){
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
     //   translationBuilder.add(); // TODO: MAYBE complete this s#it.
        for(String color : WIRE_BLOCKS.keySet()){
            addWireTranslation(WIRE_BLOCKS.get(color),translationBuilder);
            addWireTranslation(INFINIWIRE_BLOCKS.get(color),translationBuilder);
        }
        translationBuilder.add(TEST_BLOCK, "Test block");
        translationBuilder.add(WEBSOCKET_BLOCK, "WebSocket block");
    }

    protected void addWireTranslation(Block block, TranslationBuilder translationBuilder){
        String name = Registries.BLOCK.getId(block).getPath().replace("_", " ");
        for (int i = 0; i < name.length() - 1; i++)
        {
            if (i == 0)
            {
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
            } else if (name.charAt(i - 1) == ' ')
            {
                name = name.substring(0, i) + name.substring(i, i + 1).toUpperCase() + name.substring(i + 1);
            }
        }
        translationBuilder.add(block, name);
    }
}
