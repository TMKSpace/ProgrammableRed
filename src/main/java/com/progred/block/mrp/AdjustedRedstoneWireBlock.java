package com.progred.block.mrp;

import com.progred.item.AdjustedRedstoneItem;
import net.minecraft.block.*;
import net.minecraft.block.enums.WireConnection;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Util;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

public class AdjustedRedstoneWireBlock extends RedstoneWireBlock {
    private static final Vec3d[] COLORS = Util.make(new Vec3d[16], colors -> {
        for (int i = 0; i <= 15; ++i) {
            float f = 0f;
            float g = f * 0.6f + ((f = (float)i / 15.0f) > 0.0f ? 0.4f : 0.3f);
            float h = MathHelper.clamp(f * f * 0.7f - 0.5f, 0.0f, 1.0f);
            float j = MathHelper.clamp(f * f * 0.6f - 0.7f, 0.0f, 1.0f);
            colors[i] = new Vec3d(g, h, j);
        }
    });
    private static final HashMap<AdjustedRedstoneWireBlock, HashMap<Integer, Pair<Integer, Vec3d>>>
            blockAndStrengthToColorMap = new HashMap<>();
    private static final HashSet<Block> redstoneWires = new HashSet<>();
    protected static boolean globalCanProvidePower = true;

    public AdjustedRedstoneWireBlock(float hueChange) {
        this(Settings.copy(Blocks.REDSTONE_WIRE), hueChange);
    }

    protected AdjustedRedstoneWireBlock(Settings properties, float hueChange) {
        super(properties);
        redstoneWires.add(this);
        blockAndStrengthToColorMap.put(this, calculateColors(hueChange));
    }

    protected static HashMap<Integer, Pair<Integer, Vec3d>> calculateColors(float hueChange) {
        while (hueChange > 1) {
            hueChange--;
        }
        while (hueChange < 0) {
            hueChange++;
        }
        HashMap<Integer, Pair<Integer, Vec3d>> colors = new HashMap<>();
        for (int i = 0; i <= 15; i++) {
            Vec3d RGBColorVecF = COLORS[i];
            Vec3i RGBColorVecI =
                    new Vec3i((int) (RGBColorVecF.getX() * 255), (int) (RGBColorVecF.getY() * 255), (int) (RGBColorVecF.getZ() * 255));
            float[] hsb = Color.RGBtoHSB(RGBColorVecI.getX(), RGBColorVecI.getY(), RGBColorVecI.getZ(), null);
            hsb[0] += hueChange;
            if (hsb[0] > 1) {
                hsb[0]--;
            }
            int color = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
            Vec3d colorVec = new Vec3d(((color >> 16) & 0xFF) / 255.0F, ((color >> 8) & 0xFF) / 255.0F,
                    (color & 0xFF) / 255.0F);
            colors.put(i, Pair.of(color, colorVec));
        }
        return colors;
    }

    public static int getColor(BlockState state) {
        return blockAndStrengthToColorMap.get(state.getBlock()).get(state.get(POWER)).getLeft();
    }

    protected boolean isWireBlock(BlockState state) {
        return isWireBlock(state.getBlock());
    }

    protected boolean isWireBlock(Block block) {
        return redstoneWires.contains(block);
    }

    public AdjustedRedstoneItem createBlockItem(TagKey<Item> dyeTag) {
        return new AdjustedRedstoneItem(this, new Item.Settings(), dyeTag);
    }

    protected WireConnection getRenderConnectionType(BlockView reader, BlockPos pos, Direction direction, boolean nonNormalCubeAbove) {
        BlockPos offsetPos = pos.offset(direction);
        BlockState offsetState = reader.getBlockState(offsetPos);
        if (nonNormalCubeAbove) {
            boolean canPlaceOnTopOfOffset = this.canRunOnTop(reader, offsetPos, offsetState);
            if (canPlaceOnTopOfOffset &&
                    this.canThisConnectTo(reader.getBlockState(offsetPos.up()), null)) {
                if (offsetState.isSideSolidFullSquare(reader, offsetPos, direction.getOpposite())) {
                    return WireConnection.UP;
                }
                return WireConnection.SIDE;
            }
        }
        return !this.canThisConnectTo(offsetState, direction) && (offsetState.isSolidBlock(reader, offsetPos) ||
                !this.canThisConnectTo(reader.getBlockState(offsetPos.down()), null)) ? WireConnection.NONE : WireConnection.SIDE;
    }

    private boolean canRunOnTop(BlockView world, BlockPos pos, BlockState floor) {
        return floor.isSideSolidFullSquare(world, pos, Direction.UP) || floor.isOf(Blocks.HOPPER);
    }

    protected int getReceivedRedstonePower(World world, BlockPos pos) {
        globalCanProvidePower = false;
        int i = world.getReceivedRedstonePower(pos);
        globalCanProvidePower = true;
        int j = 0;
        if (i < 15) {
            for (Direction direction : Direction.Type.HORIZONTAL) {
                BlockPos offsetPos = pos.offset(direction);
                BlockState offsetState = world.getBlockState(offsetPos);
                j = Math.max(j, this.increasePower(offsetState));
                BlockPos upPos = pos.up();
                if (offsetState.isSolidBlock(world, offsetPos) &&
                        !world.getBlockState(upPos).isSolidBlock(world, upPos)) {
                    j = Math.max(j, this.increasePower(world.getBlockState(offsetPos.up())));
                } else if (!offsetState.isSolidBlock(world, offsetPos)) {
                    j = Math.max(j, this.increasePower(world.getBlockState(offsetPos.down())));
                }
            }
        }
        return Math.max(i, j - 1);
    }

    private int increasePower(BlockState state) {
        return state.isOf(this) ? state.get(POWER) : 0;
    }

    @Override
    public int getStrongRedstonePower(BlockState blockState, BlockView blockAccess, BlockPos pos, Direction side) {
        return !globalCanProvidePower ? 0 : blockState.getWeakRedstonePower(blockAccess, pos, side);
    }

    @Override
    public int getWeakRedstonePower(BlockState blockState, BlockView blockAccess, BlockPos pos, Direction side) {
        if (globalCanProvidePower && side != Direction.DOWN) {
            int i = blockState.get(POWER);
            if (i == 0) {
                return 0;
            } else {
                return side != Direction.UP &&
                        !this.getPlacementState(blockAccess, blockState, pos)
                                .get(DIRECTION_TO_WIRE_CONNECTION_PROPERTY.get(side.getOpposite()))
                                .isConnected() ? 0 : i;
            }
        } else {
            return 0;
        }
    }

    private BlockState getPlacementState(BlockView world, BlockState state, BlockPos pos) {
        boolean bl7;
        boolean bl = AdjustedRedstoneWireBlock.isNotConnected(state);
        state = this.getDefaultWireState(world, this.getDefaultState().with(POWER, state.get(POWER)), pos);
        if (bl && AdjustedRedstoneWireBlock.isNotConnected(state)) {
            return state;
        }
        boolean bl2 = state.get(WIRE_CONNECTION_NORTH).isConnected();
        boolean bl3 = state.get(WIRE_CONNECTION_SOUTH).isConnected();
        boolean bl4 = state.get(WIRE_CONNECTION_EAST).isConnected();
        boolean bl5 = state.get(WIRE_CONNECTION_WEST).isConnected();
        boolean bl6 = !bl2 && !bl3;
        boolean bl8 = bl7 = !bl4 && !bl5;
        if (!bl5 && bl6) {
            state = state.with(WIRE_CONNECTION_WEST, WireConnection.SIDE);
        }
        if (!bl4 && bl6) {
            state = state.with(WIRE_CONNECTION_EAST, WireConnection.SIDE);
        }
        if (!bl2 && bl7) {
            state = state.with(WIRE_CONNECTION_NORTH, WireConnection.SIDE);
        }
        if (!bl3 && bl7) {
            state = state.with(WIRE_CONNECTION_SOUTH, WireConnection.SIDE);
        }
        return state;
    }

    private static boolean isNotConnected(BlockState state) {
        return !state.get(WIRE_CONNECTION_NORTH).isConnected() && !state.get(WIRE_CONNECTION_SOUTH).isConnected() && !state.get(WIRE_CONNECTION_EAST).isConnected() && !state.get(WIRE_CONNECTION_WEST).isConnected();
    }

    private BlockState getDefaultWireState(BlockView world, BlockState state, BlockPos pos) {
        boolean bl = !world.getBlockState(pos.up()).isSolidBlock(world, pos);
        for (Direction direction : Direction.Type.HORIZONTAL) {
            if ((state.get(DIRECTION_TO_WIRE_CONNECTION_PROPERTY.get(direction))).isConnected()) continue;
            WireConnection wireConnection = this.getRenderConnectionType(world, pos, direction, bl);
            state = state.with(DIRECTION_TO_WIRE_CONNECTION_PROPERTY.get(direction), wireConnection);
        }
        return state;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return globalCanProvidePower;
    }

    private void addPoweredParticles(World world, Random random, BlockPos pos, Vec3d color, Direction direction, Direction direction2, float f, float g) {
        float h = g - f;
        if (random.nextFloat() >= 0.2f * h) {
            return;
        }
        float i = 0.4375f;
        float j = f + h * random.nextFloat();
        double d = 0.5 + (double)(0.4375f * (float)direction.getOffsetX()) + (double)(j * (float)direction2.getOffsetX());
        double e = 0.5 + (double)(0.4375f * (float)direction.getOffsetY()) + (double)(j * (float)direction2.getOffsetY());
        double k = 0.5 + (double)(0.4375f * (float)direction.getOffsetZ()) + (double)(j * (float)direction2.getOffsetZ());
        world.addParticle(new DustParticleEffect(color.toVector3f(), 1.0f), (double)pos.getX() + d, (double)pos.getY() + e, (double)pos.getZ() + k, 0.0, 0.0, 0.0);
    }

    @Override
    public void randomDisplayTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        int i = stateIn.get(POWER);
        if (i != 0) {
            for (Direction direction : Direction.Type.HORIZONTAL) {
                WireConnection redstoneside = stateIn.get(DIRECTION_TO_WIRE_CONNECTION_PROPERTY.get(direction));
                switch (redstoneside) {
                    case UP:
                        this.addPoweredParticles(worldIn, rand, pos,
                                blockAndStrengthToColorMap.get(this).get(i).getRight(), direction, Direction.UP, -0.5F,
                                0.5F);
                    case SIDE:
                        this.addPoweredParticles(worldIn, rand, pos,
                                blockAndStrengthToColorMap.get(this).get(i).getRight(), Direction.DOWN, direction, 0.0F,
                                0.5F);
                        break;
                    case NONE:
                    default:
                        this.addPoweredParticles(worldIn, rand, pos,
                                blockAndStrengthToColorMap.get(this).get(i).getRight(), Direction.DOWN, direction, 0.0F,
                                0.3F);
                }
            }
        }
    }

    protected boolean canThisConnectTo(BlockState blockState, @Nullable Direction side) {
        if (blockState.isOf(this)) {
            return true;
        }
        if (redstoneWires.contains(blockState.getBlock())) {
            return false;
        }
        return RedstoneWireBlock.connectsTo(blockState, side);
    }
}
