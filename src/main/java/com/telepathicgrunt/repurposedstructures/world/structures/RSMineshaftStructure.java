package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSMineshaftPieces;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.common.util.Lazy;


import net.minecraft.world.gen.feature.structure.Structure.IStartFactory;

public class RSMineshaftStructure extends AbstractBaseStructure<NoFeatureConfig> {
    protected final Lazy<Double> probability;
    protected final Lazy<Integer> maxHeight;
    protected final Lazy<Integer> minHeight;
    protected final RSMineshaftPieces.Type mineshaftType;

    public RSMineshaftStructure(RSMineshaftPieces.Type mineshaftType, Lazy<Double> probability, Lazy<Integer> maxHeight, Lazy<Integer> minHeight) {
        super(NoFeatureConfig.CODEC);
        this.probability = probability;
        this.maxHeight = maxHeight;
        this.minHeight = minHeight;
        this.mineshaftType = mineshaftType;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int x, int z, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        StructureSeparationSettings structureConfig = chunkGenerator.getSettings().getConfig(this);
        if(structureConfig != null) {
            chunkRandom.setLargeFeatureSeed(seed + structureConfig.salt(), x, z);
            double d = (probability.get() / 10000D);
            return chunkRandom.nextDouble() < d;
        }
        return false;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    public class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> feature, int chunkX, int chunkZ, MutableBoundingBox box, int references, long seed) {
            super(feature, chunkX, chunkZ, box, references, seed);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            RSMineshaftPieces.Room structuremineshaftpiecesua$room = new RSMineshaftPieces.Room(0, this.random, (chunkX << 4) + 2, (chunkZ << 4) + 2, mineshaftType);
            this.pieces.add(structuremineshaftpiecesua$room);

            structuremineshaftpiecesua$room.addChildren(structuremineshaftpiecesua$room, this.pieces, this.random);
            this.calculateBoundingBox();

            int minimum = minHeight.get();
            int maximum = Math.max(maxHeight.get(), minimum) + 1;

            int offset = this.random.nextInt(maximum - minimum) + minimum;
            this.boundingBox.move(0, offset - 50, 0);

            for (StructurePiece structurepiece : this.pieces) {
                structurepiece.move(0, offset - 50, 0);
            }
        }
    }
}