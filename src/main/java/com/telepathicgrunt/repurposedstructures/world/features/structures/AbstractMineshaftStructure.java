package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.mojang.serialization.Codec;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;


public abstract class AbstractMineshaftStructure extends AbstractBaseStructure {

    public AbstractMineshaftStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    public abstract double getProbability();

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int x, int z, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        chunkRandom.setLargeFeatureSeed(seed, x, z);
        double d = (getProbability() / 10000D);
        return chunkRandom.nextDouble() < d;
    }

    public static abstract class AbstractStart extends StructureStart<NoFeatureConfig> {
        public AbstractStart(Structure<NoFeatureConfig> feature, int chunkX, int chunkZ, MutableBoundingBox box, int references, long seed) {
            super(feature, chunkX, chunkZ, box, references, seed);
        }

        public abstract RSMineshaftPieces.Type getMineshaftType();
        public abstract int getMaxHeight();
        public abstract int getMinHeight();

        @Override
        public void init(ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            RSMineshaftPieces.Room structuremineshaftpiecesua$room = new RSMineshaftPieces.Room(0, this.rand, (chunkX << 4) + 2, (chunkZ << 4) + 2, getMineshaftType());
            this.components.add(structuremineshaftpiecesua$room);

            structuremineshaftpiecesua$room.buildComponent(structuremineshaftpiecesua$room, this.components, this.rand);
            this.recalculateStructureSize();

            int minimum = getMinHeight();
            int maximum = Math.max(getMaxHeight(), minimum) + 1;

            int offset = this.rand.nextInt(maximum - minimum) + minimum;
            this.bounds.offset(0, offset - 50, 0);

            for (StructurePiece structurepiece : this.components) {
                structurepiece.offset(0, offset - 50, 0);
            }
        }
    }
}