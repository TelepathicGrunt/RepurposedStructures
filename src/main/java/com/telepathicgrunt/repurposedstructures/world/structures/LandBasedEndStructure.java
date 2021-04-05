package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LandBasedEndStructure extends GenericJigsawStructure {

    public LandBasedEndStructure(Identifier poolID, int structureSize, int centerOffset, int biomeRange, int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet) {
        super(poolID, structureSize, centerOffset, biomeRange, structureBlacklistRange, avoidStructuresSet);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {
        return getTerrainHeight(chunkX, chunkZ, chunkGenerator) >= Math.min(chunkGenerator.getWorldHeight(), 50);
    }

    // must be on land
    private static int getTerrainHeight(int chunkX, int chunkZ, ChunkGenerator chunkGenerator) {
        int xPos = chunkX << 4;
        int zPos = chunkZ << 4;
        int height = chunkGenerator.getHeightInGround(xPos, zPos, Heightmap.Type.WORLD_SURFACE_WG);

        BlockPos pos = new BlockPos(xPos, chunkGenerator.getWorldHeight(), zPos);
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for(Direction direction : Direction.Type.HORIZONTAL){
            mutable.set(pos).move(direction, 16);
            height = Math.min(height, chunkGenerator.getHeightInGround(mutable.getX(), mutable.getZ(), Heightmap.Type.WORLD_SURFACE_WG));
        }

        return height;
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return LandBasedEndStructure.Start::new;
    }


    public class Start extends MainStart {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            super.init(dynamicRegistryManager, chunkGenerator, structureManager, chunkX, chunkZ, biome, defaultFeatureConfig);

            BlockBox box = this.children.get(0).getBoundingBox();
            BlockPos centerPos = new BlockPos(box.getCenter());
            int radius = (int) Math.sqrt((box.getDimensions().getX() * box.getDimensions().getX()) + (box.getDimensions().getZ() * box.getDimensions().getZ())) / 2;

            List<Integer> landHeights = new ArrayList<>();
            for(int xOffset = -radius; xOffset <= radius; xOffset += (radius/2)){
                for(int zOffset = -radius; zOffset <= radius; zOffset += (radius/2)){
                    int landHeight = chunkGenerator.getHeightInGround(centerPos.getX() + xOffset, centerPos.getZ() + zOffset, Heightmap.Type.WORLD_SURFACE_WG);
                    landHeights.add(landHeight);
                }
            }

            // Offset structure to average land around it
            int avgHeight = (int) Math.max(landHeights.stream().mapToInt(val -> val).average().orElse(0), 50);
            int parentHeight = this.children.get(0).getBoundingBox().minY;
            int offsetAmount = (avgHeight - parentHeight) + centerOffset;
            this.children.forEach(child -> child.translate(0, offsetAmount, 0));
            this.setBoundingBoxFromChildren();
        }
    }
}