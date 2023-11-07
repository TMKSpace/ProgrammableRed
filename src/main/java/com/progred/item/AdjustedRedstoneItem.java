package com.progred.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

public class AdjustedRedstoneItem extends BlockItem{
    private final Item dye;

    public AdjustedRedstoneItem(Block blockIn, Settings builder, Item dye)
    {
        super(blockIn, builder);
        this.dye = dye;
    }
    public Item getDye()
    {
        return dye;
    }
}
