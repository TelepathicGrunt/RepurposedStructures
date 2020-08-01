package com.telepathicgrunt.repurposedstructures.world.features.structures;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;


public class GeneralJigsawGenerator {
    public static void addPieces(ChunkGenerator chunkGenerator, TemplateManager structureManager, BlockPos pos, List<StructurePiece> pieces, SharedSeedRandom random, ResourceLocation startPool, int size) {
        JigsawManager.addPieces(startPool, size, GeneralJigsawGenerator.Piece::new, chunkGenerator, structureManager, pos, pieces, random, true, false);
    }

    public static class Piece extends AbstractVillagePiece {
        public Piece(TemplateManager structureManager, JigsawPiece structurePoolElement, BlockPos pos, int groundLevelDelta, Rotation rotation, MutableBoundingBox boundingBox) {
            super(StructurePieces.GENERAL_JIGSAW_GENERATOR_PIECE, structureManager, structurePoolElement, pos, groundLevelDelta, rotation, boundingBox);
        }

        public Piece(TemplateManager structureManager, CompoundNBT tag) {
            super(structureManager, tag, StructurePieces.GENERAL_JIGSAW_GENERATOR_PIECE);
        }
    }
}