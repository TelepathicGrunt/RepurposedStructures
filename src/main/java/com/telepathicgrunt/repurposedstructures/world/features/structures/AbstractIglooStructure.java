package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;


public abstract class AbstractIglooStructure extends AbstractBaseStructure {
    public AbstractIglooStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    public static abstract class AbstractStart extends StructureStart<NoFeatureConfig> {
        public AbstractStart(StructureFeature<NoFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public abstract ResourceLocation getTopPieceResourceLocation();

        @Override
        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            int x = chunkX * 16;
            int z = chunkZ * 16;
            BlockPos blockpos = new BlockPos(x, 90, z);
            BlockRotation rotation = BlockRotation.values()[this.random.nextInt(BlockRotation.values().length)];
            RSIglooPieces.func_207617_a(structureManager, getTopPieceResourceLocation(), Blocks.PODZOL, blockpos, rotation, this.children, this.random, FeatureConfig.DEFAULT);
            this.setBoundingBoxFromChildren();
        }
    }
}