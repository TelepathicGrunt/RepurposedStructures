package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.mojang.serialization.Codec;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;


public abstract class AbstractMineshaftStructure extends StructureFeature<DefaultFeatureConfig> {

    public AbstractMineshaftStructure(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int x, int z, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig featureConfig) {
        chunkRandom.setCarverSeed(seed, x, z);
        double d = (getProbability() / 10000D);
        return chunkRandom.nextDouble() < d;
    }

    public abstract double getProbability();

    public static abstract class AbstractStart extends StructureStart<DefaultFeatureConfig> {
        public AbstractStart(StructureFeature<DefaultFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long seed) {
            super(feature, chunkX, chunkZ, box, references, seed);
        }

        public abstract RSMineshaftPieces.Type getMineshaftType();
        public abstract int getMaxHeight();
        public abstract int getMinHeight();

        @Override
        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            RSMineshaftPieces.Room structuremineshaftpiecesua$room = new RSMineshaftPieces.Room(0, this.random, (chunkX << 4) + 2, (chunkZ << 4) + 2, getMineshaftType());
            this.children.add(structuremineshaftpiecesua$room);

            structuremineshaftpiecesua$room.placeJigsaw(structuremineshaftpiecesua$room, this.children, this.random);
            this.setBoundingBoxFromChildren();

            int minimum = getMinHeight();
            int maximum = Math.max(getMaxHeight(), minimum) + 1;

            int offset = this.random.nextInt(maximum - minimum) + minimum;
            this.boundingBox.offset(0, offset - 50, 0);

            for (StructurePiece structurepiece : this.children) {
                structurepiece.translate(0, offset - 50, 0);
            }
        }
    }
}