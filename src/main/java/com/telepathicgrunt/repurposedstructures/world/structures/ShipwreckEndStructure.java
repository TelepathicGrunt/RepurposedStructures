package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import java.util.Random;

import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;


public class ShipwreckEndStructure extends AbstractBaseStructure<NoneFeatureConfiguration> {
    // Special thanks to cannon_foddr for allowing me to use his End Shipwreck design!

    private final ResourceLocation START_POOL;

    public ShipwreckEndStructure() {
        super(NoneFeatureConfiguration.CODEC);
        START_POOL = new ResourceLocation(RepurposedStructures.MODID, "shipwrecks/end");
        RSStructures.RS_STRUCTURE_START_PIECES.add(START_POOL);
    }

    @Override
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return ShipwreckEndStructure.Start::new;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, WorldgenRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, NoneFeatureConfiguration defaultFeatureConfig, LevelHeightAccessor heightLimitView) {
        return getGenerationHeight(chunkPos1, chunkGenerator, heightLimitView) >= Math.min(chunkGenerator.getGenDepth(), 20);
    }

    private static int getGenerationHeight(ChunkPos chunkPos1, ChunkGenerator chunkGenerator, LevelHeightAccessor heightLimitView) {
        int x = chunkPos1.x * 16;
        int z = chunkPos1.z * 16;
        int heightmap1 = chunkGenerator.getFirstOccupiedHeight(x + 5, z, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        int heightmap2 = chunkGenerator.getFirstOccupiedHeight(x, z + 5, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        int heightmap3 = chunkGenerator.getFirstOccupiedHeight(x - 5, z, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        int heightmap4 = chunkGenerator.getFirstOccupiedHeight(x, z - 5, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        return Math.min(Math.min(heightmap1, heightmap2), Math.min(heightmap3, heightmap4));
    }

    public class Start extends StructureStart<NoneFeatureConfiguration> {
        public Start(StructureFeature<NoneFeatureConfiguration> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
        }

        @Override
        public void generatePieces(RegistryAccess dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, NoneFeatureConfiguration defaultFeatureConfig, LevelHeightAccessor heightLimitView) {
            BlockPos blockpos = new BlockPos(chunkPos1.getMinBlockX(), 64, chunkPos1.getMinBlockZ());
            JigsawPlacement.addPieces(
                    dynamicRegistryManager,
                    new JigsawConfiguration(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(START_POOL), 1),
                    PoolElementStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this,
                    random,
                    true,
                    false,
                    heightLimitView);
            GeneralUtils.centerAllPieces(blockpos, this.pieces);
            this.getBoundingBox();

            BlockPos blockPos = new BlockPos(this.pieces.get(0).getBoundingBox().getCenter());
            int highestLandPos = chunkGenerator.getBaseHeight(blockPos.getX(), blockPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
            highestLandPos = Math.max(30, highestLandPos);
            this.moveInsideHeights(this.random, highestLandPos-5, highestLandPos-3);
        }
    }
}