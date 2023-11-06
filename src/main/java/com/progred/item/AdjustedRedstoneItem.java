package com.progred.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

public class AdjustedRedstoneItem extends BlockItem{
    private final TagKey<Item> dyeTag;

    public AdjustedRedstoneItem(Block blockIn, Settings builder, TagKey<Item> dyeTag)
    {
        super(blockIn, builder);
        this.dyeTag = dyeTag;
    }

    public TagKey<Item> getDyeTag()
    {
        return dyeTag;
    }
}
