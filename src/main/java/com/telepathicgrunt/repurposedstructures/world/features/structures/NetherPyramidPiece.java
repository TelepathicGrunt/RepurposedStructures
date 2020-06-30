package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePieceWithDimensions;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Random;


public class NetherPyramidPiece extends StructurePieceWithDimensions {
    // Special thanks to /r/l-ll-ll-l_IsDisLoss for allowing me to mimic his nether pyramid design!

    public static final Identifier CHESTS_NETHER_PYRAMID = new Identifier("repurposed_structures:chests/nether_pyramid_chest");

    public NetherPyramidPiece(Random random, int x, int z) {
        super(StructurePieces.NETHER_PYRAMID_PIECE, random, x, 64, z, 21, 15, 21);
    }


    public NetherPyramidPiece(StructureManager templateManager, CompoundTag data) {
        super(StructurePieces.NETHER_PYRAMID_PIECE, data);
    }


    /**
     * (abstract) Helper method to read subclass data from NBT
     */
    protected void toNbt(CompoundTag data) {
        super.toNbt(data);
    }


    public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
        BlockPos.Mutable mutable = new BlockPos.Mutable((boundingBox.maxX - boundingBox.minX) / 2 + boundingBox.minX, 30, (boundingBox.maxZ - boundingBox.minZ) / 2 + boundingBox.minZ);
        while (mutable.getY() >= world.getHeight() - 20) {
            if(!world.isAir(mutable)){
                mutable.move(Direction.UP);
                continue;
            }
            else if(world.getBlockState(mutable.add(6,2,6)).getMaterial() == Material.AIR &&
                    world.getBlockState(mutable.add(-6,2,6)).getMaterial() == Material.AIR &&
                    world.getBlockState(mutable.add(6,2,-6)).getMaterial() == Material.AIR &&
                    world.getBlockState(mutable.add(-6,2,-6)).getMaterial() == Material.AIR )
            {
                break;
            }
        }

        if (mutable.getY() >= world.getHeight() - 20 || mutable.getY() <= 32) {
            this.boundingBox.offset(0, this.hPos - this.boundingBox.minY + 32, 0);
        } else {
            this.boundingBox.offset(0, this.hPos - this.boundingBox.minY + mutable.getY(), 0);
        }


        this.fillWithOutline(world, boundingBox, 0, -4, 0, this.width - 1, 0, this.depth - 1, Blocks.BLACKSTONE.getDefaultState(), Blocks.BLACKSTONE.getDefaultState(), false);

        for (int i = 1; i <= 9; ++i) {
            this.fillWithOutline(world, boundingBox, i, i, i, this.width - 1 - i, i, this.depth - 1 - i, Blocks.BLACKSTONE.getDefaultState(), Blocks.BLACKSTONE.getDefaultState(), false);
            this.fillWithOutline(world, boundingBox, i + 1, i, i + 1, this.width - 2 - i, i, this.depth - 2 - i, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        }

        for (int k1 = 0; k1 < this.width; ++k1) {
            for (int j = 0; j < this.depth; ++j) {
                this.method_14936(world, Blocks.BLACKSTONE.getDefaultState(), k1, -5, j, boundingBox);
            }
        }

        BlockState blockstate1 = Blocks.BLACKSTONE_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
        BlockState blockstate2 = Blocks.BLACKSTONE_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.SOUTH);
        BlockState blockstate3 = Blocks.BLACKSTONE_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.EAST);
        BlockState blockstate = Blocks.BLACKSTONE_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.WEST);
        this.fillWithOutline(world, boundingBox, 0, 0, 0, 4, 9, 4, Blocks.BLACKSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 1, 10, 1, 3, 10, 3, Blocks.BLACKSTONE.getDefaultState(), Blocks.BLACKSTONE.getDefaultState(), false);
        this.addBlock(world, blockstate1, 2, 10, 0, boundingBox);
        this.addBlock(world, blockstate2, 2, 10, 4, boundingBox);
        this.addBlock(world, blockstate3, 0, 10, 2, boundingBox);
        this.addBlock(world, blockstate, 4, 10, 2, boundingBox);
        this.fillWithOutline(world, boundingBox, this.width - 5, 0, 0, this.width - 1, 9, 4, Blocks.BLACKSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, this.width - 4, 10, 1, this.width - 2, 10, 3, Blocks.BLACKSTONE.getDefaultState(), Blocks.BLACKSTONE.getDefaultState(), false);
        this.addBlock(world, blockstate1, this.width - 3, 10, 0, boundingBox);
        this.addBlock(world, blockstate2, this.width - 3, 10, 4, boundingBox);
        this.addBlock(world, blockstate3, this.width - 5, 10, 2, boundingBox);
        this.addBlock(world, blockstate, this.width - 1, 10, 2, boundingBox);
        this.fillWithOutline(world, boundingBox, 8, 0, 0, 12, 4, 4, Blocks.BLACKSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 9, 1, 0, 11, 3, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), 9, 1, 1, boundingBox);
        this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), 9, 2, 1, boundingBox);
        this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), 9, 3, 1, boundingBox);
        this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), 10, 3, 1, boundingBox);
        this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), 11, 3, 1, boundingBox);
        this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), 11, 2, 1, boundingBox);
        this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), 11, 1, 1, boundingBox);
        this.fillWithOutline(world, boundingBox, 4, 1, 1, 8, 3, 3, Blocks.BLACKSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 4, 1, 2, 8, 2, 2, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 12, 1, 1, 16, 3, 3, Blocks.BLACKSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 12, 1, 2, 16, 2, 2, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 5, 4, 5, this.width - 6, 4, this.depth - 6, Blocks.BLACKSTONE.getDefaultState(), Blocks.BLACKSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 9, 4, 9, 11, 4, 11, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 8, 1, 8, 8, 3, 8, Blocks.POLISHED_BLACKSTONE.getDefaultState(), Blocks.POLISHED_BLACKSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 12, 1, 8, 12, 3, 8, Blocks.POLISHED_BLACKSTONE.getDefaultState(), Blocks.POLISHED_BLACKSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 8, 1, 12, 8, 3, 12, Blocks.POLISHED_BLACKSTONE.getDefaultState(), Blocks.POLISHED_BLACKSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 12, 1, 12, 12, 3, 12, Blocks.POLISHED_BLACKSTONE.getDefaultState(), Blocks.POLISHED_BLACKSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 1, 1, 5, 4, 4, 11, Blocks.BLACKSTONE.getDefaultState(), Blocks.BLACKSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, this.width - 5, 1, 5, this.width - 2, 4, 11, Blocks.BLACKSTONE.getDefaultState(), Blocks.BLACKSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 6, 7, 9, 6, 7, 11, Blocks.BLACKSTONE.getDefaultState(), Blocks.BLACKSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, this.width - 7, 7, 9, this.width - 7, 7, 11, Blocks.BLACKSTONE.getDefaultState(), Blocks.BLACKSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 5, 5, 9, 5, 7, 11, Blocks.POLISHED_BLACKSTONE.getDefaultState(), Blocks.POLISHED_BLACKSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, this.width - 6, 5, 9, this.width - 6, 7, 11, Blocks.POLISHED_BLACKSTONE.getDefaultState(), Blocks.POLISHED_BLACKSTONE.getDefaultState(), false);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 5, 5, 10, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 5, 6, 10, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 6, 6, 10, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), this.width - 6, 5, 10, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), this.width - 6, 6, 10, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), this.width - 7, 6, 10, boundingBox);
        this.fillWithOutline(world, boundingBox, 2, 4, 4, 2, 6, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, this.width - 3, 4, 4, this.width - 3, 6, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.addBlock(world, blockstate1, 2, 4, 5, boundingBox);
        this.addBlock(world, blockstate1, 2, 3, 4, boundingBox);
        this.addBlock(world, blockstate1, this.width - 3, 4, 5, boundingBox);
        this.addBlock(world, blockstate1, this.width - 3, 3, 4, boundingBox);
        this.fillWithOutline(world, boundingBox, 1, 1, 3, 2, 2, 3, Blocks.BLACKSTONE.getDefaultState(), Blocks.BLACKSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, this.width - 3, 1, 3, this.width - 2, 2, 3, Blocks.BLACKSTONE.getDefaultState(), Blocks.BLACKSTONE.getDefaultState(), false);
        this.addBlock(world, Blocks.BLACKSTONE.getDefaultState(), 1, 1, 2, boundingBox);
        this.addBlock(world, Blocks.BLACKSTONE.getDefaultState(), this.width - 2, 1, 2, boundingBox);
        this.addBlock(world, Blocks.BLACKSTONE_SLAB.getDefaultState(), 1, 2, 2, boundingBox);
        this.addBlock(world, Blocks.BLACKSTONE_SLAB.getDefaultState(), this.width - 2, 2, 2, boundingBox);
        this.addBlock(world, blockstate, 2, 1, 2, boundingBox);
        this.addBlock(world, blockstate3, this.width - 3, 1, 2, boundingBox);
        this.fillWithOutline(world, boundingBox, 4, 3, 5, 4, 3, 17, Blocks.BLACKSTONE.getDefaultState(), Blocks.BLACKSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, this.width - 5, 3, 5, this.width - 5, 3, 17, Blocks.BLACKSTONE.getDefaultState(), Blocks.BLACKSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 3, 1, 5, 4, 2, 16, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, this.width - 6, 1, 5, this.width - 5, 2, 16, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

        for (int l = 5; l <= 17; l += 2) {
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), 4, 1, l, boundingBox);
            this.addBlock(world, Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState(), 4, 2, l, boundingBox);
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), this.width - 5, 1, l, boundingBox);
            this.addBlock(world, Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState(), this.width - 5, 2, l, boundingBox);
        }

        this.addBlock(world, Blocks.CHISELED_NETHER_BRICKS.getDefaultState(), 10, 0, 7, boundingBox);
        this.addBlock(world, Blocks.CHISELED_NETHER_BRICKS.getDefaultState(), 10, 0, 8, boundingBox);
        this.addBlock(world, Blocks.CHISELED_NETHER_BRICKS.getDefaultState(), 9, 0, 9, boundingBox);
        this.addBlock(world, Blocks.CHISELED_NETHER_BRICKS.getDefaultState(), 11, 0, 9, boundingBox);
        this.addBlock(world, Blocks.CHISELED_NETHER_BRICKS.getDefaultState(), 8, 0, 10, boundingBox);
        this.addBlock(world, Blocks.CHISELED_NETHER_BRICKS.getDefaultState(), 12, 0, 10, boundingBox);
        this.addBlock(world, Blocks.CHISELED_NETHER_BRICKS.getDefaultState(), 7, 0, 10, boundingBox);
        this.addBlock(world, Blocks.CHISELED_NETHER_BRICKS.getDefaultState(), 13, 0, 10, boundingBox);
        this.addBlock(world, Blocks.CHISELED_NETHER_BRICKS.getDefaultState(), 9, 0, 11, boundingBox);
        this.addBlock(world, Blocks.CHISELED_NETHER_BRICKS.getDefaultState(), 11, 0, 11, boundingBox);
        this.addBlock(world, Blocks.CHISELED_NETHER_BRICKS.getDefaultState(), 10, 0, 12, boundingBox);
        this.addBlock(world, Blocks.CHISELED_NETHER_BRICKS.getDefaultState(), 10, 0, 13, boundingBox);
        this.addBlock(world, Blocks.CRYING_OBSIDIAN.getDefaultState(), 10, 0, 10, boundingBox);
        this.fillWithOutline(world, boundingBox, 8, 1, 10, 12, 5, 10, Blocks.OBSIDIAN.getDefaultState(), Blocks.AIR.getDefaultState(), false);


        for (int sectionOffset = 0; sectionOffset <= this.width - 1; sectionOffset += this.width - 1) {
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), sectionOffset, 2, 1, boundingBox);
            this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), sectionOffset, 2, 2, boundingBox);
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), sectionOffset, 2, 3, boundingBox);
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), sectionOffset, 3, 1, boundingBox);
            this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), sectionOffset, 3, 2, boundingBox);
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), sectionOffset, 3, 3, boundingBox);
            this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), sectionOffset, 4, 1, boundingBox);
            this.addBlock(world, Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState(), sectionOffset, 4, 2, boundingBox);
            this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), sectionOffset, 4, 3, boundingBox);
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), sectionOffset, 5, 1, boundingBox);
            this.addBlock(world, Blocks.OBSIDIAN.getDefaultState(), sectionOffset, 5, 2, boundingBox);
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), sectionOffset, 5, 3, boundingBox);
            this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), sectionOffset, 6, 1, boundingBox);
            this.addBlock(world, Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState(), sectionOffset, 6, 2, boundingBox);
            this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), sectionOffset, 6, 3, boundingBox);
            this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), sectionOffset, 7, 1, boundingBox);
            this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), sectionOffset, 7, 2, boundingBox);
            this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), sectionOffset, 7, 3, boundingBox);
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), sectionOffset, 8, 1, boundingBox);
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), sectionOffset, 8, 2, boundingBox);
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), sectionOffset, 8, 3, boundingBox);
        }

        for (int sectionOffset = 2; sectionOffset <= this.width - 3; sectionOffset += this.width - 3 - 2) {
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), sectionOffset - 1, 2, 0, boundingBox);
            this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), sectionOffset, 2, 0, boundingBox);
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), sectionOffset + 1, 2, 0, boundingBox);
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), sectionOffset - 1, 3, 0, boundingBox);
            this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), sectionOffset, 3, 0, boundingBox);
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), sectionOffset + 1, 3, 0, boundingBox);
            this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), sectionOffset - 1, 4, 0, boundingBox);
            this.addBlock(world, Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState(), sectionOffset, 4, 0, boundingBox);
            this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), sectionOffset + 1, 4, 0, boundingBox);
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), sectionOffset - 1, 5, 0, boundingBox);
            this.addBlock(world, Blocks.OBSIDIAN.getDefaultState(), sectionOffset, 5, 0, boundingBox);
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), sectionOffset + 1, 5, 0, boundingBox);
            this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), sectionOffset - 1, 6, 0, boundingBox);
            this.addBlock(world, Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState(), sectionOffset, 6, 0, boundingBox);
            this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), sectionOffset + 1, 6, 0, boundingBox);
            this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), sectionOffset - 1, 7, 0, boundingBox);
            this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), sectionOffset, 7, 0, boundingBox);
            this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), sectionOffset + 1, 7, 0, boundingBox);
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), sectionOffset - 1, 8, 0, boundingBox);
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), sectionOffset, 8, 0, boundingBox);
            this.addBlock(world, Blocks.POLISHED_BLACKSTONE.getDefaultState(), sectionOffset + 1, 8, 0, boundingBox);
        }

        this.addBlock(world, Blocks.AIR.getDefaultState(), 2, 0, 2, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 18, 0, 2, boundingBox);

        this.addBlock(world, Blocks.SPAWNER.getDefaultState(), 2, 0, 2, boundingBox);
        this.addBlock(world, Blocks.SPAWNER.getDefaultState(), 18, 0, 2, boundingBox);


        BlockPos blockpos = new BlockPos(this.applyXTransform(2, 2), this.applyYTransform(0), this.applyZTransform(2, 2));
        BlockEntity tileentity = world.getBlockEntity(blockpos);
        if (tileentity instanceof MobSpawnerBlockEntity) {
            ((MobSpawnerBlockEntity) tileentity).getLogic().setEntityId(EntityType.ZOMBIFIED_PIGLIN);
        }

        blockpos = new BlockPos(this.applyXTransform(18, 2), this.applyYTransform(0), this.applyZTransform(18, 2));
        tileentity = world.getBlockEntity(blockpos);
        if (tileentity instanceof MobSpawnerBlockEntity) {
            ((MobSpawnerBlockEntity) tileentity).getLogic().setEntityId(EntityType.ZOMBIFIED_PIGLIN);
        }

        this.fillWithOutline(world, boundingBox, 8, 4, 0, 12, 6, 0, Blocks.POLISHED_BLACKSTONE.getDefaultState(), Blocks.POLISHED_BLACKSTONE.getDefaultState(), false);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 8, 6, 0, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 12, 6, 0, boundingBox);
        this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), 9, 5, 0, boundingBox);
        this.addBlock(world, Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState(), 10, 5, 0, boundingBox);
        this.addBlock(world, Blocks.NETHERRACK.getDefaultState(), 11, 5, 0, boundingBox);

        this.addBlock(world, Blocks.TNT.getDefaultState(), 10, -1, 10, boundingBox);
        blockpos = new BlockPos(10, this.applyYTransform(1), 10);

        if(RepurposedStructures.RSMainConfig.temples.lootChestsNP){
            BlockState chest = method_14916(world, blockpos, Blocks.TRAPPED_CHEST.getDefaultState());
            world.setBlockState(blockpos, chest, 2);
            tileentity = world.getBlockEntity(blockpos);
            if (tileentity instanceof ChestBlockEntity) {
                ((ChestBlockEntity) tileentity).setLootTable(CHESTS_NETHER_PYRAMID, random.nextLong());
            }
        }

        return true;
    }
}