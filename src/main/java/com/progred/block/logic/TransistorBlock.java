package com.progred.block.logic;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class TransistorBlock extends Block {
    public static final BooleanProperty POWERED = Properties.POWERED;
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
        super.appendProperties(builder);
    }

    public TransistorBlock(AbstractBlock.Settings settings){
        super(settings);
        setDefaultState(getDefaultState().with(POWERED, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        world.setBlockState(pos, state.cycle(POWERED));
        player.playSound(SoundEvents.BLOCK_LEVER_CLICK, 1, 1);
        return ActionResult.SUCCESS;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if(state.get(POWERED)) return 15;
        return 0;
    }
    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }
}
