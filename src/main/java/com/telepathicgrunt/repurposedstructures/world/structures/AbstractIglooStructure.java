package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;


public abstract class AbstractIglooStructure extends AbstractBaseStructure {
    public AbstractIglooStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    public static abstract class AbstractStart extends StructureStart<NoFeatureConfig> {
        public AbstractStart(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public abstract ResourceLocation getTopPieceResourceLocation();

        @Override
        public void init(ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            int x = chunkX * 16;
            int z = chunkZ * 16;
            BlockPos blockpos = new BlockPos(x, 90, z);
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            RSIglooPieces.func_207617_a(structureManager, getTopPieceResourceLocation(), Blocks.PODZOL, blockpos, rotation, this.components, this.rand, IFeatureConfig.NO_FEATURE_CONFIG);
            this.recalculateStructureSize();
        }
    }
}