package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.List;


public class GeneralJigsawGenerator {
    public static void addPieces(ChunkGenerator chunkGenerator, StructureManager structureManager, BlockPos pos, List<StructurePiece> pieces, ChunkRandom random, Identifier startPool, int size) {
        StructurePoolBasedGenerator.addPieces(startPool, size, GeneralJigsawGenerator.Piece::new, chunkGenerator, structureManager, pos, pieces, random, true, false);
    }

    public static class Piece extends PoolStructurePiece {
        public Piece(StructureManager structureManager, StructurePoolElement structurePoolElement, BlockPos pos, int groundLevelDelta, BlockRotation rotation, BlockBox boundingBox) {
            super(StructurePieces.GENERAL_JIGSAW_GENERATOR_PIECE, structureManager, structurePoolElement, pos, groundLevelDelta, rotation, boundingBox);
        }

        public Piece(StructureManager structureManager, CompoundTag tag) {
            super(structureManager, tag, StructurePieces.GENERAL_JIGSAW_GENERATOR_PIECE);
        }
    }
}