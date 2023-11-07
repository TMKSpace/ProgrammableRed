package com.progred.item.mrp;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class AdjustedRedstoneItem extends BlockItem{
    private final Item dye;
    private final int color;

    public AdjustedRedstoneItem(Block blockIn, Settings builder, Item dye, int color)
    {
        super(blockIn, builder);
        this.dye = dye;
        this.color = color;
    }

    public Item getDye()
    {
        return dye;
    }

    public Integer getColor() {
        return color;
    }
}
