package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
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


public class PyramidBadlandsPiece extends StructurePieceWithDimensions {
    public static final Identifier CHESTS_BADLANDS_TEMPLE = new Identifier("repurposed_structures:chests/temple_badlands_chest");


    public PyramidBadlandsPiece(Random random, int x, int z) {
        super(StructurePieces.BADLANDS_PYRAMID_PIECE, random, x, 64, z, 21, 15, 21);
    }


    public PyramidBadlandsPiece(StructureManager templateManager, CompoundTag data) {
        super(StructurePieces.BADLANDS_PYRAMID_PIECE, data);
    }


    /**
     * (abstract) Helper method to read subclass data from NBT
     */
    protected void toNbt(CompoundTag data) {
        super.toNbt(data);
    }


    public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
        int minY = 225;
        for (int x = 0; x < this.width; x++) {
            for (int z = 0; z < this.width; z++) {
                minY = Math.max(Math.min(generator.getHeightInGround(this.applyXTransform(x, z), this.applyZTransform(x, z), Heightmap.Type.WORLD_SURFACE_WG), minY), generator.getSeaLevel());
            }
        }

        this.boundingBox.offset(0, this.hPos - this.boundingBox.minY + minY + 1, 0);

        this.fillWithOutline(world, boundingBox, 0, -4, 0, this.width - 1, 0, this.depth - 1, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.RED_SANDSTONE.getDefaultState(), false);

        for (int i = 1; i <= 9; ++i) {
            this.fillWithOutline(world, boundingBox, i, i, i, this.width - 1 - i, i, this.depth - 1 - i, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.RED_SANDSTONE.getDefaultState(), false);
            this.fillWithOutline(world, boundingBox, i + 1, i, i + 1, this.width - 2 - i, i, this.depth - 2 - i, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        }

        for (int k1 = 0; k1 < this.width; ++k1) {
            for (int j = 0; j < this.depth; ++j) {
                this.method_14936(world, Blocks.RED_SANDSTONE.getDefaultState(), k1, -5, j, boundingBox);
            }
        }

        BlockState blockstate1 = Blocks.RED_SANDSTONE_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
        BlockState blockstate2 = Blocks.RED_SANDSTONE_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.SOUTH);
        BlockState blockstate3 = Blocks.RED_SANDSTONE_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.EAST);
        BlockState blockstate = Blocks.RED_SANDSTONE_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.WEST);
        this.fillWithOutline(world, boundingBox, 0, 0, 0, 4, 9, 4, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 1, 10, 1, 3, 10, 3, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.RED_SANDSTONE.getDefaultState(), false);
        this.addBlock(world, blockstate1, 2, 10, 0, boundingBox);
        this.addBlock(world, blockstate2, 2, 10, 4, boundingBox);
        this.addBlock(world, blockstate3, 0, 10, 2, boundingBox);
        this.addBlock(world, blockstate, 4, 10, 2, boundingBox);
        this.fillWithOutline(world, boundingBox, this.width - 5, 0, 0, this.width - 1, 9, 4, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, this.width - 4, 10, 1, this.width - 2, 10, 3, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.RED_SANDSTONE.getDefaultState(), false);
        this.addBlock(world, blockstate1, this.width - 3, 10, 0, boundingBox);
        this.addBlock(world, blockstate2, this.width - 3, 10, 4, boundingBox);
        this.addBlock(world, blockstate3, this.width - 5, 10, 2, boundingBox);
        this.addBlock(world, blockstate, this.width - 1, 10, 2, boundingBox);
        this.fillWithOutline(world, boundingBox, 8, 0, 0, 12, 4, 4, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 9, 1, 0, 11, 3, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), 9, 1, 1, boundingBox);
        this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), 9, 2, 1, boundingBox);
        this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), 9, 3, 1, boundingBox);
        this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), 10, 3, 1, boundingBox);
        this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), 11, 3, 1, boundingBox);
        this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), 11, 2, 1, boundingBox);
        this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), 11, 1, 1, boundingBox);
        this.fillWithOutline(world, boundingBox, 4, 1, 1, 8, 3, 3, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 4, 1, 2, 8, 2, 2, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 12, 1, 1, 16, 3, 3, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 12, 1, 2, 16, 2, 2, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 5, 4, 5, this.width - 6, 4, this.depth - 6, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.RED_SANDSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 9, 4, 9, 11, 4, 11, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 8, 1, 8, 8, 3, 8, Blocks.CUT_RED_SANDSTONE.getDefaultState(), Blocks.CUT_RED_SANDSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 12, 1, 8, 12, 3, 8, Blocks.CUT_RED_SANDSTONE.getDefaultState(), Blocks.CUT_RED_SANDSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 8, 1, 12, 8, 3, 12, Blocks.CUT_RED_SANDSTONE.getDefaultState(), Blocks.CUT_RED_SANDSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 12, 1, 12, 12, 3, 12, Blocks.CUT_RED_SANDSTONE.getDefaultState(), Blocks.CUT_RED_SANDSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 1, 1, 5, 4, 4, 11, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.RED_SANDSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, this.width - 5, 1, 5, this.width - 2, 4, 11, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.RED_SANDSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 6, 7, 9, 6, 7, 11, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.RED_SANDSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, this.width - 7, 7, 9, this.width - 7, 7, 11, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.RED_SANDSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 5, 5, 9, 5, 7, 11, Blocks.CUT_RED_SANDSTONE.getDefaultState(), Blocks.CUT_RED_SANDSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, this.width - 6, 5, 9, this.width - 6, 7, 11, Blocks.CUT_RED_SANDSTONE.getDefaultState(), Blocks.CUT_RED_SANDSTONE.getDefaultState(), false);
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
        this.fillWithOutline(world, boundingBox, 1, 1, 3, 2, 2, 3, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.RED_SANDSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, this.width - 3, 1, 3, this.width - 2, 2, 3, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.RED_SANDSTONE.getDefaultState(), false);
        this.addBlock(world, Blocks.RED_SANDSTONE.getDefaultState(), 1, 1, 2, boundingBox);
        this.addBlock(world, Blocks.RED_SANDSTONE.getDefaultState(), this.width - 2, 1, 2, boundingBox);
        this.addBlock(world, Blocks.RED_SANDSTONE_SLAB.getDefaultState(), 1, 2, 2, boundingBox);
        this.addBlock(world, Blocks.RED_SANDSTONE_SLAB.getDefaultState(), this.width - 2, 2, 2, boundingBox);
        this.addBlock(world, blockstate, 2, 1, 2, boundingBox);
        this.addBlock(world, blockstate3, this.width - 3, 1, 2, boundingBox);
        this.fillWithOutline(world, boundingBox, 4, 3, 5, 4, 3, 17, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.RED_SANDSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, this.width - 5, 3, 5, this.width - 5, 3, 17, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.RED_SANDSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 3, 1, 5, 4, 2, 16, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, this.width - 6, 1, 5, this.width - 5, 2, 16, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

        for (int l = 5; l <= 17; l += 2) {
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), 4, 1, l, boundingBox);
            this.addBlock(world, Blocks.CHISELED_RED_SANDSTONE.getDefaultState(), 4, 2, l, boundingBox);
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), this.width - 5, 1, l, boundingBox);
            this.addBlock(world, Blocks.CHISELED_RED_SANDSTONE.getDefaultState(), this.width - 5, 2, l, boundingBox);
        }

        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 10, 0, 7, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 10, 0, 8, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 9, 0, 9, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 11, 0, 9, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 8, 0, 10, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 12, 0, 10, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 7, 0, 10, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 13, 0, 10, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 9, 0, 11, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 11, 0, 11, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 10, 0, 12, boundingBox);
        this.addBlock(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 10, 0, 13, boundingBox);
        this.addBlock(world, Blocks.BROWN_TERRACOTTA.getDefaultState(), 10, 0, 10, boundingBox);

        for (int sectionOffset = 0; sectionOffset <= this.width - 1; sectionOffset += this.width - 1) {
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), sectionOffset, 2, 1, boundingBox);
            this.addBlock(world, Blocks.RED_TERRACOTTA.getDefaultState(), sectionOffset, 2, 2, boundingBox);
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), sectionOffset, 2, 3, boundingBox);
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), sectionOffset, 3, 1, boundingBox);
            this.addBlock(world, Blocks.RED_TERRACOTTA.getDefaultState(), sectionOffset, 3, 2, boundingBox);
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), sectionOffset, 3, 3, boundingBox);
            this.addBlock(world, Blocks.RED_TERRACOTTA.getDefaultState(), sectionOffset, 4, 1, boundingBox);
            this.addBlock(world, Blocks.CHISELED_RED_SANDSTONE.getDefaultState(), sectionOffset, 4, 2, boundingBox);
            this.addBlock(world, Blocks.RED_TERRACOTTA.getDefaultState(), sectionOffset, 4, 3, boundingBox);
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), sectionOffset, 5, 1, boundingBox);
            this.addBlock(world, Blocks.ORANGE_TERRACOTTA.getDefaultState(), sectionOffset, 5, 2, boundingBox);
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), sectionOffset, 5, 3, boundingBox);
            this.addBlock(world, Blocks.RED_TERRACOTTA.getDefaultState(), sectionOffset, 6, 1, boundingBox);
            this.addBlock(world, Blocks.CHISELED_RED_SANDSTONE.getDefaultState(), sectionOffset, 6, 2, boundingBox);
            this.addBlock(world, Blocks.RED_TERRACOTTA.getDefaultState(), sectionOffset, 6, 3, boundingBox);
            this.addBlock(world, Blocks.RED_TERRACOTTA.getDefaultState(), sectionOffset, 7, 1, boundingBox);
            this.addBlock(world, Blocks.RED_TERRACOTTA.getDefaultState(), sectionOffset, 7, 2, boundingBox);
            this.addBlock(world, Blocks.RED_TERRACOTTA.getDefaultState(), sectionOffset, 7, 3, boundingBox);
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), sectionOffset, 8, 1, boundingBox);
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), sectionOffset, 8, 2, boundingBox);
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), sectionOffset, 8, 3, boundingBox);
        }

        for (int sectionOffset = 2; sectionOffset <= this.width - 3; sectionOffset += this.width - 3 - 2) {
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), sectionOffset - 1, 2, 0, boundingBox);
            this.addBlock(world, Blocks.RED_TERRACOTTA.getDefaultState(), sectionOffset, 2, 0, boundingBox);
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), sectionOffset + 1, 2, 0, boundingBox);
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), sectionOffset - 1, 3, 0, boundingBox);
            this.addBlock(world, Blocks.RED_TERRACOTTA.getDefaultState(), sectionOffset, 3, 0, boundingBox);
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), sectionOffset + 1, 3, 0, boundingBox);
            this.addBlock(world, Blocks.RED_TERRACOTTA.getDefaultState(), sectionOffset - 1, 4, 0, boundingBox);
            this.addBlock(world, Blocks.CHISELED_RED_SANDSTONE.getDefaultState(), sectionOffset, 4, 0, boundingBox);
            this.addBlock(world, Blocks.RED_TERRACOTTA.getDefaultState(), sectionOffset + 1, 4, 0, boundingBox);
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), sectionOffset - 1, 5, 0, boundingBox);
            this.addBlock(world, Blocks.ORANGE_TERRACOTTA.getDefaultState(), sectionOffset, 5, 0, boundingBox);
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), sectionOffset + 1, 5, 0, boundingBox);
            this.addBlock(world, Blocks.RED_TERRACOTTA.getDefaultState(), sectionOffset - 1, 6, 0, boundingBox);
            this.addBlock(world, Blocks.CHISELED_RED_SANDSTONE.getDefaultState(), sectionOffset, 6, 0, boundingBox);
            this.addBlock(world, Blocks.RED_TERRACOTTA.getDefaultState(), sectionOffset + 1, 6, 0, boundingBox);
            this.addBlock(world, Blocks.RED_TERRACOTTA.getDefaultState(), sectionOffset - 1, 7, 0, boundingBox);
            this.addBlock(world, Blocks.RED_TERRACOTTA.getDefaultState(), sectionOffset, 7, 0, boundingBox);
            this.addBlock(world, Blocks.RED_TERRACOTTA.getDefaultState(), sectionOffset + 1, 7, 0, boundingBox);
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), sectionOffset - 1, 8, 0, boundingBox);
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), sectionOffset, 8, 0, boundingBox);
            this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), sectionOffset + 1, 8, 0, boundingBox);
        }

        this.addBlock(world, Blocks.AIR.getDefaultState(), 2, 0, 2, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 18, 0, 2, boundingBox);

        this.addBlock(world, Blocks.SPAWNER.getDefaultState(), 2, 0, 2, boundingBox);
        this.addBlock(world, Blocks.SPAWNER.getDefaultState(), 18, 0, 2, boundingBox);


        BlockPos blockpos = new BlockPos(this.applyXTransform(2, 2), this.applyYTransform(0), this.applyZTransform(2, 2));
        BlockEntity tileentity = world.getBlockEntity(blockpos);
        if (tileentity instanceof MobSpawnerBlockEntity) {
            ((MobSpawnerBlockEntity) tileentity).getLogic().setEntityId(EntityType.HUSK);
        }

        blockpos = new BlockPos(this.applyXTransform(18, 2), this.applyYTransform(0), this.applyZTransform(18, 2));
        tileentity = world.getBlockEntity(blockpos);
        if (tileentity instanceof MobSpawnerBlockEntity) {
            ((MobSpawnerBlockEntity) tileentity).getLogic().setEntityId(EntityType.HUSK);
        }

        this.fillWithOutline(world, boundingBox, 8, 4, 0, 12, 6, 0, Blocks.CUT_RED_SANDSTONE.getDefaultState(), Blocks.CUT_RED_SANDSTONE.getDefaultState(), false);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 8, 6, 0, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 12, 6, 0, boundingBox);
        this.addBlock(world, Blocks.BROWN_TERRACOTTA.getDefaultState(), 9, 5, 0, boundingBox);
        this.addBlock(world, Blocks.CHISELED_RED_SANDSTONE.getDefaultState(), 10, 5, 0, boundingBox);
        this.addBlock(world, Blocks.BROWN_TERRACOTTA.getDefaultState(), 11, 5, 0, boundingBox);
        this.fillWithOutline(world, boundingBox, 7, -14, 7, 13, -11, 13, Blocks.CUT_RED_SANDSTONE.getDefaultState(), Blocks.CUT_RED_SANDSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 8, -10, 8, 12, -10, 12, Blocks.CHISELED_RED_SANDSTONE.getDefaultState(), Blocks.CHISELED_RED_SANDSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 8, -9, 8, 12, -9, 12, Blocks.CUT_RED_SANDSTONE.getDefaultState(), Blocks.CUT_RED_SANDSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 8, -8, 8, 12, -1, 12, Blocks.RED_SANDSTONE.getDefaultState(), Blocks.RED_SANDSTONE.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 9, -11, 9, 11, -1, 11, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.addBlock(world, Blocks.CYAN_TERRACOTTA.getDefaultState(), 10, -12, 10, boundingBox);
        this.fillWithOutline(world, boundingBox, 9, -13, 9, 11, -13, 11, Blocks.RED_SAND.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.addBlock(world, Blocks.TNT.getDefaultState(), 10, -13, 10, boundingBox);
        this.addBlock(world, Blocks.TNT.getDefaultState(), 12, -13, 10, boundingBox);
        this.addBlock(world, Blocks.TNT.getDefaultState(), 10, -13, 12, boundingBox);
        this.addBlock(world, Blocks.TNT.getDefaultState(), 8, -13, 10, boundingBox);
        this.addBlock(world, Blocks.TNT.getDefaultState(), 10, -13, 8, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 8, -11, 10, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 8, -10, 10, boundingBox);
        this.addBlock(world, Blocks.CHISELED_RED_SANDSTONE.getDefaultState(), 7, -10, 10, boundingBox);
        this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), 7, -11, 10, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 12, -11, 10, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 12, -10, 10, boundingBox);
        this.addBlock(world, Blocks.CHISELED_RED_SANDSTONE.getDefaultState(), 13, -10, 10, boundingBox);
        this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), 13, -11, 10, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 10, -11, 8, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 10, -10, 8, boundingBox);
        this.addBlock(world, Blocks.CHISELED_RED_SANDSTONE.getDefaultState(), 10, -10, 7, boundingBox);
        this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), 10, -11, 7, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 10, -11, 12, boundingBox);
        this.addBlock(world, Blocks.AIR.getDefaultState(), 10, -10, 12, boundingBox);
        this.addBlock(world, Blocks.CHISELED_RED_SANDSTONE.getDefaultState(), 10, -10, 13, boundingBox);
        this.addBlock(world, Blocks.CUT_RED_SANDSTONE.getDefaultState(), 10, -11, 13, boundingBox);

        if(RepurposedStructures.RSAllConfig.RSTempleConfig.pyramids.lootChestsBP) {
            for (Direction direction : Direction.Type.HORIZONTAL) {
                int x = 10 + direction.getOffsetX() * 2;
                int z = 10 + direction.getOffsetZ() * 2;
                blockpos = new BlockPos(this.applyXTransform(x, z), this.applyYTransform(-11), this.applyZTransform(x, z));
                BlockState chest = method_14916(world, blockpos, Blocks.TRAPPED_CHEST.getDefaultState());

                world.setBlockState(blockpos, chest, 2);
                tileentity = world.getBlockEntity(blockpos);
                if (tileentity instanceof ChestBlockEntity) {
                    ((ChestBlockEntity) tileentity).setLootTable(CHESTS_BADLANDS_TEMPLE, random.nextLong());
                }
            }
        }

        return true;
    }
}