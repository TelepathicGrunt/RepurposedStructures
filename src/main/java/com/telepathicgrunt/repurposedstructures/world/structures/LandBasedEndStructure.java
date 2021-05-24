package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LandBasedEndStructure extends GenericJigsawStructure {
    public LandBasedEndStructure(ResourceLocation poolRL, int structureSize, int centerOffset, int biomeRange, int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet) {
        super(poolRL, structureSize, centerOffset, biomeRange, structureBlacklistRange, avoidStructuresSet);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig noFeatureConfig) {
        return getTerrainHeight(chunkX, chunkZ, chunkGenerator) >= Math.min(chunkGenerator.getGenDepth(), 50);
    }

    // must be on land
    private static int getTerrainHeight(int chunkX, int chunkZ, ChunkGenerator chunkGenerator) {
        int xPos = chunkX << 4;
        int zPos = chunkZ << 4;
        int height = chunkGenerator.getFirstOccupiedHeight(xPos, zPos, Heightmap.Type.WORLD_SURFACE_WG);

        BlockPos pos = new BlockPos(xPos, chunkGenerator.getGenDepth(), zPos);
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for(Direction direction : Direction.Plane.HORIZONTAL){
            mutable.set(pos).move(direction, 16);
            height = Math.min(height, chunkGenerator.getFirstOccupiedHeight(mutable.getX(), mutable.getZ(), Heightmap.Type.WORLD_SURFACE_WG));
        }

        return height;
    }


    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return LandBasedEndStructure.Start::new;
    }

    public class Start extends MainStart {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            super.generatePieces(dynamicRegistryManager, chunkGenerator, structureManager, chunkX, chunkZ, biome, NoFeatureConfig);

            MutableBoundingBox box = this.pieces.get(0).getBoundingBox();
            BlockPos centerPos = new BlockPos(box.getCenter());
            int radius = (int) Math.sqrt((box.getLength().getX() * box.getLength().getX()) + (box.getLength().getZ() * box.getLength().getZ()));

            List<Integer> landHeights = new ArrayList<>();
            for(int xOffset = -radius; xOffset <= radius; xOffset += (radius/2)){
                for(int zOffset = -radius; zOffset <= radius; zOffset += (radius/2)){
                    int landHeight = chunkGenerator.getFirstOccupiedHeight(centerPos.getX() + xOffset, centerPos.getZ() + zOffset, Heightmap.Type.WORLD_SURFACE_WG);
                    landHeights.add(landHeight);
                }
            }

            // Offset structure to average land around it
            int avgHeight = (int) Math.max(landHeights.stream().mapToInt(val -> val).average().orElse(0), 50);
            int parentHeight = this.pieces.get(0).getBoundingBox().y0;
            int offsetAmount = (avgHeight - parentHeight) + centerOffset;
            this.pieces.forEach(child -> child.move(0, offsetAmount, 0));
            this.calculateBoundingBox();
        }
    }
}