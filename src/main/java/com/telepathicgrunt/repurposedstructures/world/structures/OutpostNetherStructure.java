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


public class OutpostNetherStructure extends AbstractBaseStructure<DefaultFeatureConfig> {
    private final Identifier START_POOL;

    public OutpostNetherStructure(Identifier pieceRL) {
        super(DefaultFeatureConfig.CODEC);
        START_POOL = pieceRL;
        RSStructures.RS_STRUCTURE_START_PIECES.add(START_POOL);
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return OutpostNetherStructure.Start::new;
    }

    public class Start extends AbstractNetherStructure.AbstractStart{
        public Start(StructureFeature<DefaultFeatureConfig> structureFeature, ChunkPos chunkPos, int referenceIn, long seed) {
            super(structureFeature, chunkPos, referenceIn, seed);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos pos, Biome biome, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
            BlockPos blockPos = new BlockPos(pos.getStartX(), 0, pos.getStartZ());
            StructurePoolBasedGenerator.method_30419(
                    dynamicRegistryManager,
                    new StructurePoolFeatureConfig(() -> dynamicRegistryManager.get(Registry.STRUCTURE_POOL_KEY).get(START_POOL), 11),
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

            BlockPos lowestLandPos = getHighestLand(chunkGenerator, heightLimitView);
            if (lowestLandPos.getY() >= chunkGenerator.getWorldHeight() || lowestLandPos.getY() <= chunkGenerator.getSeaLevel() + 5) {
                this.randomUpwardTranslation(this.random, chunkGenerator.getSeaLevel() - 13, chunkGenerator.getSeaLevel() - 12);
            }
            else {
                this.randomUpwardTranslation(this.random, lowestLandPos.getY() - 15, lowestLandPos.getY() - 14);
            }
        }
    }
}