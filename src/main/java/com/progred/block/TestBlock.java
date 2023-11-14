package com.progred.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import static com.progred.registry.ModItems.DISCHARGER;

public class TestBlock extends Block {
    public static final IntProperty CHARGE = IntProperty.of("power",0,15);
    public TestBlock(AbstractBlock.Settings settings){
        super(settings);
        setDefaultState(getDefaultState().with(CHARGE,0));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder){
        builder.add(CHARGE);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(player.getMainHandStack().getItem() == DISCHARGER){
            if(state.get(CHARGE)>0) player.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1, 1);
            this.discharge(world, pos, state, 1);
            return ActionResult.SUCCESS;
        }
        float pitch = (float) (1 + (world.getBlockState(pos).get(CHARGE) * 0.1));
        if(world.getBlockState(pos).get(CHARGE)<15) {
            world.setBlockState(pos, state.cycle(CHARGE));
            player.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE,1,pitch);
        }
        else {
            player.playSound(SoundEvents.ENTITY_VILLAGER_NO,1,1);
        }
        return ActionResult.SUCCESS;
    }
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if(world.getBlockState(pos).get(CHARGE)>10) {
            TntEntity tnt =  EntityType.TNT.create(world);
            assert tnt != null;
            tnt.setFuse(0);
            tnt.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(pos));
            world.spawnEntity(tnt);
            world.setBlockState(pos, state.with(CHARGE, 0));
        }
        else if(world.getBlockState(pos).get(CHARGE)>=3){
            LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);
            assert lightningEntity != null;
            lightningEntity.refreshPositionAfterTeleport(entity.getPos().add(0,1,0));
            world.spawnEntity(lightningEntity);
        }
        world.setBlockState(pos, state.with(CHARGE, world.getBlockState(pos).get(CHARGE) > 0 ? world.getBlockState(pos).get(CHARGE)-1 : 0));
        super.onSteppedOn(world, pos, state, entity);
    }

    int getPower(BlockView world, BlockPos pos){
        return world.getBlockState(pos).get(CHARGE);
    }
    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }
    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return getPower(world,pos);
    }
    public void discharge(ItemUsageContext context, Integer power){
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        this.discharge(world, pos, world.getBlockState(pos), power);
    }
    public void discharge(World world,BlockPos pos,BlockState state,Integer power){
        world.setBlockState(pos, state.with(CHARGE, world.getBlockState(pos).get(CHARGE) > 0 ? world.getBlockState(pos).get(CHARGE)-power : 0));
    }
}
