package com.progred.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TestBlock extends Block {
    public static final IntProperty CHARGE = IntProperty.of("power",0,15);
    public TestBlock(Settings settings){
        super(settings);
        setDefaultState(getDefaultState().with(CHARGE,0));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder){
        builder.add(CHARGE);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        float pitch = (float) (1 + (world.getBlockState(pos).get(CHARGE) * 0.1));
        player.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE,1,pitch);
        if(world.getBlockState(pos).get(CHARGE)<15) {
            world.setBlockState(pos, state.cycle(CHARGE));
        }
        return ActionResult.SUCCESS;
    }
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if(world.getBlockState(pos).get(CHARGE)>=15){
            LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);
            assert lightningEntity != null;
            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(pos));
            world.spawnEntity(lightningEntity);
        }
        world.setBlockState(pos,state.with(CHARGE,0));
        super.onSteppedOn(world, pos, state, entity);
    }
}
