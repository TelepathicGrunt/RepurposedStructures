package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
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
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;


public class RSMineshaftBirchStructure extends AbstractMineshaftStructure {
    public RSMineshaftBirchStructure(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    public double mineshaftProbability() {
        return RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.birchMineshaftSpawnrate;
    }

    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int x, int z, Biome biome, ChunkPos chunkPos, MineshaftFeatureConfig mineshaftFeatureConfig) {
        chunkRandom.setCarverSeed(seed, x, z);
        double d = (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.birchMineshaftSpawnrate / 10000D);
        return chunkRandom.nextDouble() < d;
    }

    @Override
    public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return RSMineshaftBirchStructure.Start::new;
    }

    public static class Start extends StructureStart<DefaultFeatureConfig> {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }


        @Override
        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            RSMineshaftPieces.Room structuremineshaftpiecesua$room = new RSMineshaftPieces.Room(0, this.random, (chunkX << 4) + 2, (chunkZ << 4) + 2, RSMineshaftPieces.Type.BIRCH);
            this.children.add(structuremineshaftpiecesua$room);

            structuremineshaftpiecesua$room.placeJigsaw(structuremineshaftpiecesua$room, this.children, this.random);
            this.setBoundingBoxFromChildren();

            int minimum = RepurposedStructures.RSAllConfig.RSMineshaftsConfig.minHeight.birchMineshaftMinHeight;
            int maximum = Math.max(RepurposedStructures.RSAllConfig.RSMineshaftsConfig.maxHeight.birchMineshaftMaxHeight, minimum) + 1;

            int offset = this.random.nextInt(maximum - minimum) + minimum;
            this.boundingBox.offset(0, offset - 50, 0);

            for (StructurePiece structurepiece : this.children) {
                structurepiece.translate(0, offset - 50, 0);
            }
        }
    }

}
