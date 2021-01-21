package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;


public class GenericNetherJigsawHighStructure extends AbstractBaseStructure {

    private final Identifier startPool;
    private final int size;
    private final int heightOffset;
    private final int lavaOffset;

    public GenericNetherJigsawHighStructure(Identifier poolID, int size, int heightOffset, int lavaOffset) {
        super(DefaultFeatureConfig.CODEC);
        this.startPool = poolID;
        this.size = size;
        this.heightOffset = heightOffset;
        this.lavaOffset = lavaOffset;
        RSStructures.RS_STRUCTURE_START_PIECES.add(startPool);
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return GenericNetherJigsawHighStructure.Start::new;
    }

    public class Start extends AbstractNetherStructure.AbstractStart {

        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX << 4, 0, chunkZ << 4);
            StructurePoolBasedGenerator.method_30419(
                    dynamicRegistryManager,
                    new StructurePoolFeatureConfig(() -> dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN).get(startPool), size),
                    PoolStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.children,
                    random,
                    true,
                    false);
            this.setBoundingBoxFromChildren();

            // Needed because the offsetting method offsets the bounds but the structure piece
            // is actually 10 blocks higher than the bound's minimum Y. Wack.
            int boundOffset = -10;
            BlockPos highestLandPos = getHighestLand(chunkGenerator);
            this.randomUpwardTranslation(this.random,
                    Math.max((highestLandPos.getY() + heightOffset) - 1, 29 + lavaOffset) + boundOffset,
                    Math.max(highestLandPos.getY() + heightOffset, 30 + lavaOffset) + boundOffset);
        }
    }
}