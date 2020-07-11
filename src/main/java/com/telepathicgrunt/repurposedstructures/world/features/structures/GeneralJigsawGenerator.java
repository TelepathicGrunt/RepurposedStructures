package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.block.enums.WireConnection;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.*;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.BastionRemnantFeatureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.List;
import java.util.Random;


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