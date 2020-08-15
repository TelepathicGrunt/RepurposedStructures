package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.List;
import java.util.Random;


public class GeneralJigsawGenerator {
    public static void addPieces(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, BlockPos pos, List<StructurePiece> pieces, ChunkRandom random, StructurePool startPool, int size) {
        StructurePoolBasedGenerator.method_30419(dynamicRegistryManager, new StructurePoolFeatureConfig(() -> startPool, size), GeneralJigsawGenerator.Piece::new, chunkGenerator, structureManager, pos, pieces, random, true, false);
    }

    public static class Piece extends PoolStructurePiece {
        public Piece(StructureManager structureManager, StructurePoolElement structurePoolElement, BlockPos pos, int groundLevelDelta, BlockRotation rotation, BlockBox boundingBox) {
            super(structureManager, structurePoolElement, pos, groundLevelDelta, rotation, boundingBox);
        }

        public Piece(StructureManager structureManager, CompoundTag tag) {
            super(structureManager, tag);
        }
    }
}