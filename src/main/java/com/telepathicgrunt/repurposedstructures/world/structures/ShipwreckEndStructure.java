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
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return ShipwreckEndStructure.Start::new;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator generator, BiomeProvider biomeProvider, long seed, SharedSeedRandom random, int x, int z, Biome biome, ChunkPos chunkPos, NoFeatureConfig config) {
        return getYPosForStructure(x, z, generator) >= Math.min(generator.getGenDepth(), 20);
    }

    private static int getYPosForStructure(int x, int z, ChunkGenerator generator) {
        Random random = new Random(x + z * 10387313);
        Rotation rotation = Rotation.getRandom(random);
        int i = 5;
        int j = 5;
        if (rotation == Rotation.CLOCKWISE_90) {
            i = -5;
        } else if (rotation == Rotation.CLOCKWISE_180) {
            i = -5;
            j = -5;
        } else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
            j = -5;
        }

        int k = (x << 4) + 7;
        int l = (z << 4) + 7;
        int i1 = generator.getFirstOccupiedHeight(k, l, Heightmap.Type.WORLD_SURFACE_WG);
        int j1 = generator.getFirstOccupiedHeight(k, l + j, Heightmap.Type.WORLD_SURFACE_WG);
        int k1 = generator.getFirstOccupiedHeight(k + i, l, Heightmap.Type.WORLD_SURFACE_WG);
        int l1 = generator.getFirstOccupiedHeight(k + i, l + j, Heightmap.Type.WORLD_SURFACE_WG);
        return Math.min(Math.min(i1, j1), Math.min(k1, l1));
    }

    public class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            BlockPos blockPos = new BlockPos(chunkX * 16, 64, chunkZ * 16);
            JigsawManager.addPieces(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(
                            Registry.TEMPLATE_POOL_REGISTRY).get(START_POOL),
                            1),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockPos,
                    this.pieces,
                    this.random,
                    true,
                    false);
            this.calculateBoundingBox();

            BlockPos blockPos2 = new BlockPos(this.pieces.get(0).getBoundingBox().getCenter());
            int highestLandPos = chunkGenerator.getBaseHeight(blockPos2.getX(), blockPos2.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
            highestLandPos = Math.max(30, highestLandPos);
            this.moveInsideHeights(this.random, highestLandPos-5, highestLandPos-3);
        }
    }
}