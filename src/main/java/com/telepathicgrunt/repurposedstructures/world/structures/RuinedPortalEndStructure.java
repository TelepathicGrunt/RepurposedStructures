package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Set;

public class RuinedPortalEndStructure extends GenericJigsawStructure {
    public RuinedPortalEndStructure(ResourceLocation poolRL, int structureSize, int centerOffset, int biomeRange, int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet) {
        super(poolRL, structureSize, centerOffset, biomeRange, structureBlacklistRange, avoidStructuresSet);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        return getTerrainHeight(chunkX, chunkZ, chunkGenerator) >= 50;
    }

    // must be on land
    private static int getTerrainHeight(int chunkX, int chunkZ, ChunkGenerator chunkGenerator) {
        int xPos = (chunkX << 4) + 7;
        int zPos = (chunkZ << 4) + 7;
        int height1 = chunkGenerator.func_222531_c(xPos, zPos, Heightmap.Type.WORLD_SURFACE_WG);
        int height2 = chunkGenerator.func_222531_c(xPos, zPos + 10, Heightmap.Type.WORLD_SURFACE_WG);
        int height3 = chunkGenerator.func_222531_c(xPos + 10, zPos, Heightmap.Type.WORLD_SURFACE_WG);
        int height4 = chunkGenerator.func_222531_c(xPos, zPos - 10, Heightmap.Type.WORLD_SURFACE_WG);
        int height5 = chunkGenerator.func_222531_c(xPos - 10, zPos, Heightmap.Type.WORLD_SURFACE_WG);
        return Math.min(Math.min(Math.min(height1, height2), Math.min(height3, height4)), height5);
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return RuinedPortalEndStructure.Start::new;
    }

    public class Start extends MainStart {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void init(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            super.init(dynamicRegistryManager, chunkGenerator, structureManager, chunkX, chunkZ, biome, NoFeatureConfig);
            // If portal is off the island somehow, offset it to y = 50.
            if(this.components.get(0).getBoundingBox().minY < 43){
                this.components.get(0).offset(0, 50 - this.components.get(0).getBoundingBox().minY, 0);
            }
            this.recalculateStructureSize();
        }
    }
}