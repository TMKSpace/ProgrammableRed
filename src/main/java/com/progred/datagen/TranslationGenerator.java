package com.progred.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;

import static com.progred.registry.ModBlocks.*;
import static com.progred.registry.ModItems.*;

public class TranslationGenerator extends FabricLanguageProvider {
    public TranslationGenerator(FabricDataOutput dataOutput){
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(TEST_BLOCK.getLeft(), "Test block");
        translationBuilder.add(WEBSOCKET_BLOCK.getLeft(), "WebSocket block");
        translationBuilder.add(DISCHARGER, "Discharger");
        translationBuilder.add(PIZDEC,"Pizdec");
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
