package com.progred.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;

import java.util.HashMap;

public class TranslationGenerator extends FabricLanguageProvider {
    public TranslationGenerator(FabricDataOutput dataOutput){
        super(dataOutput, "en_us");
    }
    private static HashMap<String, Block> Blocks = new HashMap<>();
    private static HashMap Names = new HashMap<>();

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
     //   translationBuilder.add();
    }
}
