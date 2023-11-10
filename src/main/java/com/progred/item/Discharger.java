package com.progred.item;

import com.progred.block.TestBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Objects;

import static com.progred.block.ModBlocks.*;

public class Discharger extends Item {
    public Discharger(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState blockState = context.getWorld().getBlockState(context.getBlockPos());
        Block block = blockState.getBlock();
        if(block != TEST_BLOCK) {
            Objects.requireNonNull(context.getPlayer()).playSound(SoundEvents.ENTITY_VILLAGER_NO, 1, 1);
        }
        return super.useOnBlock(context);
    }
}
