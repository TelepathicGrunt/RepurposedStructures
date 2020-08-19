package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;


public class GeneralJigsawGenerator {

    public static void addPieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManager, BlockPos pos, List<StructurePiece> pieces, Random random, JigsawPattern startPool, int size) {
        JigsawManager.method_30419(dynamicRegistryManager, new VillageConfig(() -> startPool, size), GeneralJigsawGenerator.Piece::new, chunkGenerator, templateManager, pos, pieces, random, true, false);
    }

    public static class Piece extends AbstractVillagePiece {
        public Piece(TemplateManager structureManager, JigsawPiece structurePoolElement, BlockPos pos, int groundLevelDelta, Rotation rotation, MutableBoundingBox boundingBox) {
            super(structureManager, structurePoolElement, pos, groundLevelDelta, rotation, boundingBox);
        }

        public Piece(TemplateManager structureManager, CompoundNBT tag) {
            super(structureManager, tag);
        }
    }
}