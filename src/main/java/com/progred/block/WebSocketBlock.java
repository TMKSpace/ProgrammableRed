package com.progred.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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

public class WebSocketBlock extends Block {
    public static final BooleanProperty POWERED = Properties.POWERED;
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder){
        builder.add(POWERED);
    };
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        player.playSound(SoundEvents.BLOCK_LEVER_CLICK,1,1);
        world.setBlockState(pos, state.cycle(POWERED));
        return ActionResult.SUCCESS;
    }
    int getPower(BlockView world, BlockPos pos){
        if(world.getBlockState(pos).get(POWERED)) return 15;
        return 0;
    }
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return getWeakRedstonePower(state,world,pos,direction);
    }
    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return getPower(world,pos);
    }
    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }
    public static final WebSocketBlock WEBSOCKETBLOCK = new WebSocketBlock(FabricBlockSettings.copyOf(Blocks.STONE));
    public WebSocketBlock(Settings settings){
        super(settings);
        setDefaultState(getDefaultState().with(POWERED,false));
    }
}
