package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSIglooPieces;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;


public class RSIglooStructure extends AbstractBaseStructure {
    private final Identifier TOP_PIECE_IDENTIFIER;

    public RSIglooStructure(Identifier topPieceID) {
        super(DefaultFeatureConfig.CODEC);
        TOP_PIECE_IDENTIFIER = topPieceID;
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return Start::new;
    }

    public class Start extends StructureStart<DefaultFeatureConfig> {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            int x = chunkX * 16;
            int z = chunkZ * 16;
            BlockPos blockpos = new BlockPos(x, 90, z);
            BlockRotation rotation = BlockRotation.values()[this.random.nextInt(BlockRotation.values().length)];
            RSIglooPieces.func_207617_a(structureManager, TOP_PIECE_IDENTIFIER, Blocks.PODZOL, blockpos, rotation, this.children, this.random, FeatureConfig.DEFAULT);
            this.setBoundingBoxFromChildren();
        }
    }
}