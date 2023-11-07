package com.progred.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;

public class InfiniwireItem extends AdjustedRedstoneItem{
    public InfiniwireItem(Block blockIn, Settings builder, Item dye, int color)
    {
        super(blockIn, builder, dye, color);
    }

    @Override
    public boolean hasGlint(ItemStack stack)
    {
        return true;
    }
}
