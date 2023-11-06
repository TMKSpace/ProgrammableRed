package com.progred.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;

public class InfiniwireItem extends AdjustedRedstoneItem{
    public InfiniwireItem(Block blockIn, Settings builder, TagKey<Item> dyeTag)
    {
        super(blockIn, builder, dyeTag);
    }

    @Override
    public boolean hasGlint(ItemStack stack)
    {
        return true;
    }
}
