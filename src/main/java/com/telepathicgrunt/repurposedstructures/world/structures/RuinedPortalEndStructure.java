package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Set;

public class RuinedPortalEndStructure extends GenericJigsawStructure {

    public RuinedPortalEndStructure(Identifier poolID, int structureSize, int centerOffset, int biomeRange, int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet) {
        super(poolID, structureSize, centerOffset, biomeRange, structureBlacklistRange, avoidStructuresSet);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {
        return getTerrainHeight(chunkX, chunkZ, chunkGenerator) >= Math.min(chunkGenerator.getWorldHeight(), 50);
    }

    // must be on land
    private static int getTerrainHeight(int chunkX, int chunkZ, ChunkGenerator chunkGenerator) {
        int xPos = (chunkX << 4) + 7;
        int zPos = (chunkZ << 4) + 7;
        int height1 = chunkGenerator.getHeightInGround(xPos, zPos, Heightmap.Type.WORLD_SURFACE_WG);
        int height2 = chunkGenerator.getHeightInGround(xPos, zPos + 10, Heightmap.Type.WORLD_SURFACE_WG);
        int height3 = chunkGenerator.getHeightInGround(xPos + 10, zPos, Heightmap.Type.WORLD_SURFACE_WG);
        int height4 = chunkGenerator.getHeightInGround(xPos, zPos - 10, Heightmap.Type.WORLD_SURFACE_WG);
        int height5 = chunkGenerator.getHeightInGround(xPos - 10, zPos, Heightmap.Type.WORLD_SURFACE_WG);
        return Math.min(Math.min(Math.min(height1, height2), Math.min(height3, height4)), height5);
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return RuinedPortalEndStructure.Start::new;
    }


    public class Start extends MainStart {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            super.init(dynamicRegistryManager, chunkGenerator, structureManager, chunkX, chunkZ, biome, defaultFeatureConfig);

            // If portal is off the island somehow, offset it to y = 50.
            if(this.children.get(0).getBoundingBox().minY < 43){
                this.children.get(0).translate(0, 50 - this.children.get(0).getBoundingBox().minY, 0);
            }
            this.setBoundingBoxFromChildren();
        }
    }
}