package com.progred.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

public class AdjustedRedstoneItem extends BlockItem{

    public AdjustedRedstoneItem(Block blockIn, Settings builder, TagKey<Item> dyeTag)
    {
        super(blockIn, builder);
    }
}
