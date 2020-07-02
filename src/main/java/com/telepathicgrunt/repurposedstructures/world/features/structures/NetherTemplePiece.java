package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.block.enums.WireConnection;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePieceWithDimensions;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Random;


public class NetherTemplePiece extends StructurePieceWithDimensions {
    private static final NetherTemplePiece.Selector NETHER_BLOCK_SELECTOR = new NetherTemplePiece.Selector();
    public static final Identifier CHESTS_NETHER_TEMPLE = new Identifier("repurposed_structures:chests/nether_temple_chest");
    public static final Identifier DISPENSER_NETHER_TEMPLE = new Identifier("repurposed_structures:chests/nether_temple_dispenser");


    public NetherTemplePiece(Random random, int x, int z) {
        super(StructurePieces.NETHER_TEMPLE_PIECE, random, x, 64, z, 12, 10, 15);
    }


    public NetherTemplePiece(StructureManager templateManager, CompoundTag data) {
        super(StructurePieces.NETHER_TEMPLE_PIECE, data);
    }


    /**
     * (abstract) Helper method to read subclass data from NBT
     */
    protected void toNbt(CompoundTag tagCompound) {
        super.toNbt(tagCompound);
    }


    public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
        BlockPos.Mutable mutable = new BlockPos.Mutable((boundingBox.maxX - boundingBox.minX) / 2 + boundingBox.minX, 35, (boundingBox.maxZ - boundingBox.minZ) / 2 + boundingBox.minZ);
        BlockState currentBlockstate = world.getBlockState(mutable);
        while (mutable.getY() <= 108) {

            if(!world.isAir(mutable) && world.isAir(mutable.up(5)) &&
                    !world.isAir(mutable.add(2,-1,2)) &&
                    !world.isAir(mutable.add(-2,-1,2)) &&
                    !world.isAir(mutable.add(2,-1,-2)) &&
                    !world.isAir(mutable.add(-2,-1,-2)) &&
                    (BlockTags.INFINIBURN_NETHER.contains(currentBlockstate.getBlock()) ||
                     BlockTags.VALID_SPAWN.contains(currentBlockstate.getBlock()) ||
                     BlockTags.SAND.contains(currentBlockstate.getBlock()) ||
                     BlockTags.NYLIUM.contains(currentBlockstate.getBlock()) ||
                     BlockTags.ICE.contains(currentBlockstate.getBlock()) ||
                     BlockTags.PLANKS.contains(currentBlockstate.getBlock()) ||
                     BlockTags.STONE_BRICKS.contains(currentBlockstate.getBlock()) ||
                     BlockTags.WITHER_IMMUNE.contains(currentBlockstate.getBlock()) ||
                     BlockTags.WOOL.contains(currentBlockstate.getBlock()) ||
                     currentBlockstate.getMaterial() == Material.AGGREGATE ||
                     currentBlockstate.getMaterial() == Material.STONE ||
                     currentBlockstate.getMaterial() == Material.SOIL))
            {
                break;
            }

            mutable.move(Direction.UP);
            currentBlockstate = world.getBlockState(mutable);
        }

        if (mutable.getY() >= 108 || mutable.getY() <= 33) {
            this.boundingBox.offset(0, this.hPos - this.boundingBox.minY + 33, 0);
        } else {
            this.boundingBox.offset(0, this.hPos - this.boundingBox.minY + mutable.getY(), 0);
        }

        this.fillWithOutline(world, boundingBox, 0, -4, 0, this.width - 1, 0, this.depth - 1, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 2, 1, 2, 9, 2, 2, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 2, 1, 12, 9, 2, 12, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 2, 1, 3, 2, 2, 11, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 9, 1, 3, 9, 2, 11, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 1, 3, 1, 10, 6, 1, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 1, 3, 13, 10, 6, 13, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 1, 3, 2, 1, 6, 12, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 10, 3, 2, 10, 6, 12, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 2, 3, 2, 9, 3, 12, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 2, 6, 2, 9, 6, 12, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 3, 7, 3, 8, 7, 11, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 4, 8, 4, 7, 8, 10, false, random, NETHER_BLOCK_SELECTOR);
        this.fill(world, boundingBox, 3, 1, 3, 8, 2, 11);
        this.fill(world, boundingBox, 4, 3, 6, 7, 3, 9);
        this.fill(world, boundingBox, 2, 4, 2, 9, 5, 12);
        this.fill(world, boundingBox, 4, 6, 5, 7, 6, 9);
        this.fill(world, boundingBox, 5, 7, 6, 6, 7, 8);
        this.fill(world, boundingBox, 5, 1, 2, 6, 2, 2);
        this.fill(world, boundingBox, 5, 2, 12, 6, 2, 12);
        this.fill(world, boundingBox, 5, 5, 1, 6, 5, 1);
        this.fill(world, boundingBox, 5, 5, 13, 6, 5, 13);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 1, 5, 5, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 10, 5, 5, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 1, 5, 9, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 10, 5, 9, boundingBox);

        for (int i = 0; i <= 14; i += 14) {
            this.fillWithOutline(world, boundingBox, 2, 4, i, 2, 5, i, false, random, NETHER_BLOCK_SELECTOR);
            this.fillWithOutline(world, boundingBox, 4, 4, i, 4, 5, i, false, random, NETHER_BLOCK_SELECTOR);
            this.fillWithOutline(world, boundingBox, 7, 4, i, 7, 5, i, false, random, NETHER_BLOCK_SELECTOR);
            this.fillWithOutline(world, boundingBox, 9, 4, i, 9, 5, i, false, random, NETHER_BLOCK_SELECTOR);
        }

        this.fillWithOutline(world, boundingBox, 5, 6, 0, 6, 6, 0, false, random, NETHER_BLOCK_SELECTOR);

        for (int l = 0; l <= 11; l += 11) {
            for (int j = 2; j <= 12; j += 2) {
                this.fillWithOutline(world, boundingBox, l, 4, j, l, 5, j, false, random, NETHER_BLOCK_SELECTOR);
            }

            this.fillWithOutline(world, boundingBox, l, 6, 5, l, 6, 5, false, random, NETHER_BLOCK_SELECTOR);
            this.fillWithOutline(world, boundingBox, l, 6, 9, l, 6, 9, false, random, NETHER_BLOCK_SELECTOR);
        }

        this.fillWithOutline(world, boundingBox, 2, 7, 2, 2, 9, 2, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 9, 7, 2, 9, 9, 2, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 2, 7, 12, 2, 9, 12, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 9, 7, 12, 9, 9, 12, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 4, 9, 4, 4, 9, 4, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 7, 9, 4, 7, 9, 4, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 4, 9, 10, 4, 9, 10, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 7, 9, 10, 7, 9, 10, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 5, 9, 7, 6, 9, 7, false, random, NETHER_BLOCK_SELECTOR);
        BlockState blockstate3 = Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.EAST);
        BlockState blockstate4 = Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.WEST);
        BlockState blockstate = Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.SOUTH);
        BlockState blockstate1 = Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
        this.addBlock(world, blockstate1, 5, 9, 6, boundingBox);
        this.addBlock(world, blockstate1, 6, 9, 6, boundingBox);
        this.addBlock(world, blockstate, 5, 9, 8, boundingBox);
        this.addBlock(world, blockstate, 6, 9, 8, boundingBox);
        this.addBlock(world, blockstate1, 4, 0, 0, boundingBox);
        this.addBlock(world, blockstate1, 5, 0, 0, boundingBox);
        this.addBlock(world, blockstate1, 6, 0, 0, boundingBox);
        this.addBlock(world, blockstate1, 7, 0, 0, boundingBox);
        this.addBlock(world, blockstate1, 4, 1, 8, boundingBox);
        this.addBlock(world, blockstate1, 4, 2, 9, boundingBox);
        this.addBlock(world, blockstate1, 4, 3, 10, boundingBox);
        this.addBlock(world, blockstate1, 7, 1, 8, boundingBox);
        this.addBlock(world, blockstate1, 7, 2, 9, boundingBox);
        this.addBlock(world, blockstate1, 7, 3, 10, boundingBox);
        this.fillWithOutline(world, boundingBox, 4, 1, 9, 4, 1, 9, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 7, 1, 9, 7, 1, 9, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 4, 1, 10, 7, 2, 10, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 5, 4, 5, 6, 4, 5, false, random, NETHER_BLOCK_SELECTOR);
        this.addBlock(world, blockstate3, 4, 4, 5, boundingBox);
        this.addBlock(world, blockstate4, 7, 4, 5, boundingBox);

        for (int k = 0; k < 4; ++k) {
            this.addBlock(world, blockstate, 5, 0 - k, 6 + k, boundingBox);
            this.addBlock(world, blockstate, 6, 0 - k, 6 + k, boundingBox);
            this.fill(world, boundingBox, 5, 0 - k, 7 + k, 6, 0 - k, 9 + k);
        }

        this.fill(world, boundingBox, 1, -3, 12, 10, -1, 13);
        this.fill(world, boundingBox, 1, -3, 1, 3, -1, 13);
        this.fill(world, boundingBox, 1, -3, 1, 9, -1, 5);

        for (int i1 = 1; i1 <= 13; i1 += 2) {
            this.fillWithOutline(world, boundingBox, 1, -3, i1, 1, -2, i1, false, random, NETHER_BLOCK_SELECTOR);
        }

        for (int j1 = 2; j1 <= 12; j1 += 2) {
            this.fillWithOutline(world, boundingBox, 1, -1, j1, 3, -1, j1, false, random, NETHER_BLOCK_SELECTOR);
        }

        this.fillWithOutline(world, boundingBox, 2, -2, 1, 5, -2, 1, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 7, -2, 1, 9, -2, 1, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 6, -3, 1, 6, -3, 1, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 6, -1, 1, 6, -1, 1, false, random, NETHER_BLOCK_SELECTOR);
        this.addBlock(world, Blocks.TRIPWIRE_HOOK.getDefaultState().with(TripwireHookBlock.FACING, Direction.EAST).with(TripwireHookBlock.ATTACHED, Boolean.valueOf(true)), 1, -3, 8, boundingBox);
        this.addBlock(world, Blocks.TRIPWIRE_HOOK.getDefaultState().with(TripwireHookBlock.FACING, Direction.WEST).with(TripwireHookBlock.ATTACHED, Boolean.valueOf(true)), 4, -3, 8, boundingBox);
        this.addBlock(world, Blocks.TRIPWIRE.getDefaultState().with(TripwireBlock.EAST, Boolean.valueOf(true)).with(TripwireBlock.WEST, Boolean.valueOf(true)).with(TripwireBlock.ATTACHED, Boolean.valueOf(true)), 2, -3, 8, boundingBox);
        this.addBlock(world, Blocks.TRIPWIRE.getDefaultState().with(TripwireBlock.EAST, Boolean.valueOf(true)).with(TripwireBlock.WEST, Boolean.valueOf(true)).with(TripwireBlock.ATTACHED, Boolean.valueOf(true)), 3, -3, 8, boundingBox);
        BlockState blockstate5 = Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.WIRE_CONNECTION_NORTH, WireConnection.SIDE).with(RedstoneWireBlock.WIRE_CONNECTION_SOUTH, WireConnection.SIDE);
        this.addBlock(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.WIRE_CONNECTION_SOUTH, WireConnection.SIDE), 5, -3, 7, boundingBox);
        this.addBlock(world, blockstate5, 5, -3, 6, boundingBox);
        this.addBlock(world, blockstate5, 5, -3, 5, boundingBox);
        this.addBlock(world, blockstate5, 5, -3, 4, boundingBox);
        this.addBlock(world, blockstate5, 5, -3, 3, boundingBox);
        this.addBlock(world, blockstate5, 5, -3, 2, boundingBox);
        this.addBlock(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.WIRE_CONNECTION_NORTH, WireConnection.SIDE).with(RedstoneWireBlock.WIRE_CONNECTION_WEST, WireConnection.SIDE), 5, -3, 1, boundingBox);
        this.addBlock(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.WIRE_CONNECTION_EAST, WireConnection.SIDE), 4, -3, 1, boundingBox);
        this.addBlock(world, Blocks.MAGMA_BLOCK.getDefaultState(), 3, -3, 1, boundingBox);
        this.addDispenser(world, boundingBox, random, 3, -2, 1, Direction.NORTH, DISPENSER_NETHER_TEMPLE);


        this.addBlock(world, Blocks.WEEPING_VINES.getDefaultState(), 3, -3, 2, boundingBox);
        this.addBlock(world, Blocks.WEEPING_VINES_PLANT.getDefaultState(), 3, -2, 2, boundingBox);
        this.addBlock(world, Blocks.TRIPWIRE_HOOK.getDefaultState().with(TripwireHookBlock.FACING, Direction.NORTH).with(TripwireHookBlock.ATTACHED, Boolean.valueOf(true)), 7, -3, 1, boundingBox);
        this.addBlock(world, Blocks.TRIPWIRE_HOOK.getDefaultState().with(TripwireHookBlock.FACING, Direction.SOUTH).with(TripwireHookBlock.ATTACHED, Boolean.valueOf(true)), 7, -3, 5, boundingBox);
        this.addBlock(world, Blocks.TRIPWIRE.getDefaultState().with(TripwireBlock.NORTH, Boolean.valueOf(true)).with(TripwireBlock.SOUTH, Boolean.valueOf(true)).with(TripwireBlock.ATTACHED, Boolean.valueOf(true)), 7, -3, 2, boundingBox);
        this.addBlock(world, Blocks.TRIPWIRE.getDefaultState().with(TripwireBlock.NORTH, Boolean.valueOf(true)).with(TripwireBlock.SOUTH, Boolean.valueOf(true)).with(TripwireBlock.ATTACHED, Boolean.valueOf(true)), 7, -3, 3, boundingBox);
        this.addBlock(world, Blocks.TRIPWIRE.getDefaultState().with(TripwireBlock.NORTH, Boolean.valueOf(true)).with(TripwireBlock.SOUTH, Boolean.valueOf(true)).with(TripwireBlock.ATTACHED, Boolean.valueOf(true)), 7, -3, 4, boundingBox);
        this.addBlock(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.WIRE_CONNECTION_EAST, WireConnection.SIDE), 8, -3, 6, boundingBox);
        this.addBlock(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.WIRE_CONNECTION_WEST, WireConnection.SIDE).with(RedstoneWireBlock.WIRE_CONNECTION_SOUTH, WireConnection.SIDE), 9, -3, 6, boundingBox);
        this.addBlock(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.WIRE_CONNECTION_NORTH, WireConnection.SIDE).with(RedstoneWireBlock.WIRE_CONNECTION_SOUTH, WireConnection.UP), 9, -3, 5, boundingBox);
        this.addBlock(world, Blocks.MAGMA_BLOCK.getDefaultState(), 9, -3, 4, boundingBox);
        this.addBlock(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.WIRE_CONNECTION_NORTH, WireConnection.SIDE), 9, -2, 4, boundingBox);
        this.addDispenser(world, boundingBox, random, 9, -2, 3, Direction.WEST, DISPENSER_NETHER_TEMPLE);


        this.addBlock(world, Blocks.WEEPING_VINES.getDefaultState(), 8, -3, 3, boundingBox);
        this.addBlock(world, Blocks.WEEPING_VINES_PLANT.getDefaultState(), 8, -2, 3, boundingBox);
        this.addBlock(world, Blocks.WEEPING_VINES_PLANT.getDefaultState(), 8, -1, 3, boundingBox);
        this.addChest(world, boundingBox, random, 9, -3, 3, CHESTS_NETHER_TEMPLE);


        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 9, -3, 2, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 8, -3, 1, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 4, -3, 5, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 5, -2, 5, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 5, -1, 5, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 6, -3, 5, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 7, -2, 5, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 7, -1, 5, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 8, -3, 5, boundingBox);
        this.fillWithOutline(world, boundingBox, 9, -1, 1, 9, -1, 5, false, random, NETHER_BLOCK_SELECTOR);
        this.fill(world, boundingBox, 8, -3, 8, 10, -1, 10);
        this.addBlock(world, Blocks.RED_NETHER_BRICKS.getDefaultState(), 8, -2, 11, boundingBox);
        this.addBlock(world, Blocks.RED_NETHER_BRICKS.getDefaultState(), 9, -2, 11, boundingBox);
        this.addBlock(world, Blocks.RED_NETHER_BRICKS.getDefaultState(), 10, -2, 11, boundingBox);
        BlockState blockstate2 = Blocks.LEVER.getDefaultState().with(LeverBlock.FACING, Direction.NORTH).with(LeverBlock.FACE, WallMountLocation.WALL);
        this.addBlock(world, blockstate2, 8, -2, 12, boundingBox);
        this.addBlock(world, blockstate2, 9, -2, 12, boundingBox);
        this.addBlock(world, blockstate2, 10, -2, 12, boundingBox);
        this.fillWithOutline(world, boundingBox, 8, -3, 8, 8, -3, 10, false, random, NETHER_BLOCK_SELECTOR);
        this.fillWithOutline(world, boundingBox, 10, -3, 8, 10, -3, 10, false, random, NETHER_BLOCK_SELECTOR);
        this.addBlock(world, Blocks.NETHER_BRICKS.getDefaultState(), 10, -2, 9, boundingBox);
        this.addBlock(world, Blocks.NETHER_BRICKS.getDefaultState(), 9, -1, 10, boundingBox);
        this.addBlock(world, Blocks.NETHER_BRICKS.getDefaultState(), 9, -1, 9, boundingBox);
        this.addBlock(world, Blocks.NETHER_BRICKS.getDefaultState(), 8, -1, 10, boundingBox);
        this.addBlock(world, Blocks.NETHER_BRICKS.getDefaultState(), 8, -1, 9, boundingBox);
        this.addBlock(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.WIRE_CONNECTION_NORTH, WireConnection.SIDE), 8, -2, 9, boundingBox);
        this.addBlock(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.WIRE_CONNECTION_SOUTH, WireConnection.SIDE), 8, -2, 10, boundingBox);
        this.addBlock(world, Blocks.REDSTONE_WIRE.getDefaultState(), 10, -1, 9, boundingBox);
        this.addBlock(world, Blocks.STICKY_PISTON.getDefaultState().with(PistonBlock.FACING, Direction.UP), 9, -2, 8, boundingBox);
        this.addBlock(world, Blocks.STICKY_PISTON.getDefaultState().with(PistonBlock.FACING, Direction.WEST), 10, -2, 8, boundingBox);
        this.addBlock(world, Blocks.STICKY_PISTON.getDefaultState().with(PistonBlock.FACING, Direction.WEST), 10, -1, 8, boundingBox);
        this.addBlock(world, Blocks.REPEATER.getDefaultState().with(RepeaterBlock.FACING, Direction.NORTH), 10, -2, 10, boundingBox);

        if (RepurposedStructures.RSAllConfig.RSMainConfig.temples.lootChestsNT) {
            this.addChest(world, boundingBox, random, 9, -3, 10, CHESTS_NETHER_TEMPLE);
            this.addChest(world, boundingBox, random, 9, -3, 8, CHESTS_NETHER_TEMPLE);
        } else {
            this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 9, -3, 10, boundingBox);
            this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 9, -3, 8, boundingBox);
        }

        BlockPos blockpos = new BlockPos(this.applyXTransform(9, 9), this.applyYTransform(-3), this.applyZTransform(9, 9));
        world.setBlockState(blockpos, Blocks.SPAWNER.getDefaultState(), 2);
        BlockEntity tileentity = world.getBlockEntity(blockpos);

        if (tileentity instanceof MobSpawnerBlockEntity) {
            ((MobSpawnerBlockEntity) tileentity).getLogic().setEntityId(EntityType.ZOMBIFIED_PIGLIN);
        }

        return true;
    }

    static class Selector extends StructurePiece.BlockRandomizer {
        private Selector() {
        }


        public void setBlock(Random rand, int x, int y, int z, boolean wall) {
            float chance = rand.nextFloat();
            if (chance < 0.015F) {
                this.block = Blocks.MAGMA_BLOCK.getDefaultState();
            } else if (chance < 0.12f) {
                this.block = Blocks.BLACK_TERRACOTTA.getDefaultState();
            } else if (chance < 0.175f) {
                this.block = Blocks.RED_NETHER_BRICKS.getDefaultState();
            } else {
                this.block = Blocks.NETHER_BRICKS.getDefaultState();
            }
        }
    }
}