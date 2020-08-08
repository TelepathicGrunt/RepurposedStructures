package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSMineshaftPieces;
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


public class RSMineshaftStructure extends AbstractBaseStructure {
    protected final double probability;
    protected final int maxHeight;
    protected final int minHeight;
    protected final RSMineshaftPieces.Type mineshaftType;

    public RSMineshaftStructure(Codec<NoFeatureConfig> config, RSMineshaftPieces.Type mineshaftType, double probability, int maxHeight, int minHeight) {
        super(config);
        this.probability = probability;
        this.maxHeight = maxHeight;
        this.minHeight = minHeight;
        this.mineshaftType = mineshaftType;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int x, int z, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        chunkRandom.setLargeFeatureSeed(seed, x, z);
        double d = (probability / 10000D);
        return chunkRandom.nextDouble() < d;
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
        public void init(ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            RSMineshaftPieces.Room structuremineshaftpiecesua$room = new RSMineshaftPieces.Room(0, this.rand, (chunkX << 4) + 2, (chunkZ << 4) + 2, mineshaftType);
            this.components.add(structuremineshaftpiecesua$room);

            structuremineshaftpiecesua$room.buildComponent(structuremineshaftpiecesua$room, this.components, this.rand);
            this.recalculateStructureSize();

            int minimum = minHeight;
            int maximum = Math.max(maxHeight, minimum) + 1;

            int offset = this.rand.nextInt(maximum - minimum) + minimum;
            this.bounds.offset(0, offset - 50, 0);

            for (StructurePiece structurepiece : this.components) {
                structurepiece.offset(0, offset - 50, 0);
            }
        }
    }
}