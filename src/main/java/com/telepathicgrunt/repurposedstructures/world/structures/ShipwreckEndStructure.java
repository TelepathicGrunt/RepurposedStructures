package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
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

import java.util.Random;


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
        Random random = new Random(chunkPos1.x + chunkPos1.z * 10387313L);
        Rotation blockRotation = Rotation.getRandom(random);
        int i = 5;
        int j = 5;
        if (blockRotation == Rotation.CLOCKWISE_90) {
            i = -5;
        } else if (blockRotation == Rotation.CLOCKWISE_180) {
            i = -5;
            j = -5;
        } else if (blockRotation == Rotation.COUNTERCLOCKWISE_90) {
            j = -5;
        }

        int k = (chunkPos1.x << 4) + 7;
        int l = (chunkPos1.z << 4) + 7;
        int m = chunkGenerator.getFirstOccupiedHeight(k, l, Heightmap.Type.WORLD_SURFACE_WG);
        int n = chunkGenerator.getFirstOccupiedHeight(k, l + j, Heightmap.Type.WORLD_SURFACE_WG);
        int o = chunkGenerator.getFirstOccupiedHeight(k + i, l, Heightmap.Type.WORLD_SURFACE_WG);
        int p = chunkGenerator.getFirstOccupiedHeight(k + i, l + j, Heightmap.Type.WORLD_SURFACE_WG);
        return Math.min(Math.min(m, n), Math.min(o, p));
    }

    public class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox box, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, box, referenceIn, seedIn);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig defaultFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX << 4, 64, chunkZ << 4);
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
            this.calculateBoundingBox();

            BlockPos blockPos = new BlockPos(this.pieces.get(0).getBoundingBox().getCenter());
            int highestLandPos = chunkGenerator.getBaseHeight(blockPos.getX(), blockPos.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
            highestLandPos = Math.max(30, highestLandPos);
            this.moveInsideHeights(this.random, highestLandPos-5, highestLandPos-3);
        }
    }
}