package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;


public class TempleNetherStructure extends AbstractBaseStructure<DefaultFeatureConfig> {
    private final Identifier START_POOL;

    public TempleNetherStructure(Identifier pieceID) {
        super(DefaultFeatureConfig.CODEC);
        START_POOL = pieceID;
        RSStructures.RS_STRUCTURE_START_PIECES.add(START_POOL);
    }

    @Override
    public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return TempleNetherStructure.Start::new;
    }

    public class Start extends AbstractNetherStructure.AbstractStart{
        public Start(StructureFeature<DefaultFeatureConfig> structureFeature, ChunkPos chunkPos, int referenceIn, long seed) {
            super(structureFeature, chunkPos, referenceIn, seed);
        }

        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos, Biome biome, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
            BlockPos blockPos = new BlockPos(chunkPos.getStartX(), chunkGenerator.getSeaLevel() + 3, chunkPos.getStartZ());
            StructurePoolBasedGenerator.method_30419(
                    dynamicRegistryManager,
                    new StructurePoolFeatureConfig(() -> dynamicRegistryManager.get(Registry.STRUCTURE_POOL_KEY).get(START_POOL), 1),
                    PoolStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockPos,
                    this,
                    random,
                    true,
                    false,
                    heightLimitView);
            this.setBoundingBoxFromChildren();

            BlockPos lowestLandPos = getLowestLand(chunkGenerator, heightLimitView);
            if (lowestLandPos.getY() >= chunkGenerator.getWorldHeight() || lowestLandPos.getY() <= chunkGenerator.getSeaLevel() + 1) {
                this.randomUpwardTranslation(this.random, chunkGenerator.getSeaLevel() - 16, chunkGenerator.getSeaLevel() - 15);
            }
            else {
                this.randomUpwardTranslation(this.random, lowestLandPos.getY() - 16, lowestLandPos.getY() - 15);
            }
        }
    }
}