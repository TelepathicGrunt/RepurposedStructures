package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class ShipwreckEndStructure extends AbstractBaseStructure<NoFeatureConfig> {
    // Special thanks to cannon_foddr for allowing me to use his End Shipwreck design!

    private final ResourceLocation START_POOL;

    public ShipwreckEndStructure() {
        super(NoFeatureConfig.CODEC);
        START_POOL = new ResourceLocation(RepurposedStructures.MODID, "shipwrecks/end");
        RSStructures.RS_STRUCTURE_START_PIECES.add(START_POOL);
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig defaultFeatureConfig) {
        return getGenerationHeight(new ChunkPos(chunkX, chunkZ), chunkGenerator) >= Math.min(chunkGenerator.getGenDepth(), 20);
    }

    private static int getGenerationHeight(ChunkPos chunkPos1, ChunkGenerator chunkGenerator) {
        int x = chunkPos1.x * 16;
        int z = chunkPos1.z * 16;
        int heightmap1 = chunkGenerator.getFirstOccupiedHeight(x + 5, z, Heightmap.Type.WORLD_SURFACE_WG);
        int heightmap2 = chunkGenerator.getFirstOccupiedHeight(x, z + 5, Heightmap.Type.WORLD_SURFACE_WG);
        int heightmap3 = chunkGenerator.getFirstOccupiedHeight(x - 5, z, Heightmap.Type.WORLD_SURFACE_WG);
        int heightmap4 = chunkGenerator.getFirstOccupiedHeight(x, z - 5, Heightmap.Type.WORLD_SURFACE_WG);
        return Math.min(Math.min(heightmap1, heightmap2), Math.min(heightmap3, heightmap4));
    }

    public class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox box, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, box, referenceIn, seedIn);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig defaultFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX * 16, 64, chunkZ * 16);
            JigsawManager.addPieces(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(START_POOL), 1),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.pieces,
                    random,
                    true,
                    false);
            GeneralUtils.centerAllPieces(blockpos, this.pieces);
            this.calculateBoundingBox();

            BlockPos blockPos = new BlockPos(this.pieces.get(0).getBoundingBox().getCenter());
            int highestLandPos = chunkGenerator.getBaseHeight(blockPos.getX(), blockPos.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
            highestLandPos = Math.max(30, highestLandPos);
            this.moveInsideHeights(this.random, highestLandPos-5, highestLandPos-3);
        }
    }
}