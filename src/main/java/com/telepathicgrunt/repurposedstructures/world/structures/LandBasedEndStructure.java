package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.HeightLimitView;
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

    public LandBasedEndStructure(Identifier poolID, int structureSize, int centerOffset, int biomeRange,
                                  int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet,
                                  int allowTerrainHeightRange, int terrainHeightRadius, int minHeightLimit,
                                 int fixedYSpawn, boolean useHeightmap, boolean cannotSpawnInWater)
    {
        super(
                poolID,
                structureSize,
                centerOffset,
                biomeRange,
                structureBlacklistRange,
                avoidStructuresSet,
                allowTerrainHeightRange,
                terrainHeightRadius,
                minHeightLimit,
                fixedYSpawn,
                useHeightmap,
                cannotSpawnInWater
        );
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
        return getTerrainHeight(chunkPos1, chunkGenerator, heightLimitView) >= Math.min(chunkGenerator.getWorldHeight(), 50);
    }

    // must be on land
    private static int getTerrainHeight(ChunkPos chunkPos1, ChunkGenerator chunkGenerator, HeightLimitView heightLimitView) {
        int xPos = chunkPos1.x << 4;
        int zPos = chunkPos1.z << 4;
        int height = chunkGenerator.getHeightInGround(xPos, zPos, Heightmap.Type.WORLD_SURFACE_WG, heightLimitView);

        BlockPos pos = new BlockPos(xPos, chunkGenerator.getWorldHeight(), zPos);
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for(Direction direction : Direction.Type.HORIZONTAL){
            mutable.set(pos).move(direction, 16);
            height = Math.min(height, chunkGenerator.getHeightInGround(mutable.getX(), mutable.getZ(), Heightmap.Type.WORLD_SURFACE_WG, heightLimitView));
        }

        return height;
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return LandBasedEndStructure.Start::new;
    }


    public class Start extends MainStart {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
        }

        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
            super.init(dynamicRegistryManager, chunkGenerator, structureManager, chunkPos1, biome, defaultFeatureConfig, heightLimitView);

            BlockBox box = this.children.get(0).getBoundingBox();
            BlockPos centerPos = new BlockPos(box.getCenter());
            int radius = (int) Math.sqrt((box.getDimensions().getX() * box.getDimensions().getX()) + (box.getDimensions().getZ() * box.getDimensions().getZ())) / 2;

            List<Integer> landHeights = new ArrayList<>();
            for(int xOffset = -radius; xOffset <= radius; xOffset += (radius/2)){
                for(int zOffset = -radius; zOffset <= radius; zOffset += (radius/2)){
                    int landHeight = chunkGenerator.getHeightInGround(centerPos.getX() + xOffset, centerPos.getZ() + zOffset, Heightmap.Type.WORLD_SURFACE_WG, heightLimitView);
                    landHeights.add(landHeight);
                }
            }

            // Offset structure to average land around it
            int avgHeight = (int) Math.max(landHeights.stream().mapToInt(val -> val).average().orElse(0), 50);
            int parentHeight = this.children.get(0).getBoundingBox().getMinY();
            int offsetAmount = (avgHeight - parentHeight) + centerOffset;
            this.children.forEach(child -> child.translate(0, offsetAmount, 0));
            this.setBoundingBoxFromChildren();
        }
    }

    public static class Builder<T extends GenericJigsawStructure.Builder<T>> extends GenericJigsawStructure.Builder<T> {

        public Builder(Identifier startPool) {
            super(startPool);
        }

        public LandBasedEndStructure build() {
            return new LandBasedEndStructure(
                    startPool,
                    structureSize,
                    centerOffset,
                    biomeRange,
                    structureBlacklistRange,
                    avoidStructuresSet,
                    allowTerrainHeightRange,
                    terrainHeightRadius,
                    minHeightLimit,
                    fixedYSpawn,
                    useHeightmap,
                    cannotSpawnInWater);
        }
    }
}