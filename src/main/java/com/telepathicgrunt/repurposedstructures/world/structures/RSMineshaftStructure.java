package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSMineshaftPieces;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Objects;


public class RSMineshaftStructure extends AbstractBaseStructure {

    protected final double probability;
    protected final int maxHeight;
    protected final int minHeight;
    protected final RSMineshaftPieces.Type mineshaftType;

    public RSMineshaftStructure(RSMineshaftPieces.Type mineshaftType, double probability, int maxHeight, int minHeight) {
        super(DefaultFeatureConfig.CODEC);
        this.probability = probability;
        this.maxHeight = maxHeight;
        this.minHeight = minHeight;
        this.mineshaftType = mineshaftType;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int x, int z, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig featureConfig) {
        chunkRandom.setCarverSeed(seed + Objects.requireNonNull(chunkGenerator.getStructuresConfig().getForType(this)).getSalt(), x, z);
        double d = (probability / 10000D);
        return chunkRandom.nextDouble() < d;
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return RSMineshaftStructure.Start::new;
    }

    public class Start extends StructureStart<DefaultFeatureConfig> {
        public Start(StructureFeature<DefaultFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long seed) {
            super(feature, chunkX, chunkZ, box, references, seed);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            RSMineshaftPieces.Room structuremineshaftpiecesua$room = new RSMineshaftPieces.Room(0, this.random, (chunkX << 4) + 2, (chunkZ << 4) + 2, mineshaftType);
            this.children.add(structuremineshaftpiecesua$room);

            structuremineshaftpiecesua$room.placeJigsaw(structuremineshaftpiecesua$room, this.children, this.random);
            this.setBoundingBoxFromChildren();

            int minimum = minHeight;
            int maximum = Math.max(maxHeight, minimum) + 1;

            int offset = this.random.nextInt(maximum - minimum) + minimum;
            this.boundingBox.offset(0, offset - 50, 0);

            for (StructurePiece structurepiece : this.children) {
                structurepiece.translate(0, offset - 50, 0);
            }
        }
    }
}