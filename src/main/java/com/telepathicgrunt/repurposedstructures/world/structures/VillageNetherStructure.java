package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

import java.util.Set;

import com.telepathicgrunt.repurposedstructures.world.structures.GenericJigsawStructure.MainStart;
import net.minecraft.world.gen.feature.structure.Structure.IStartFactory;

public class VillageNetherStructure extends GenericJigsawStructure {
    public VillageNetherStructure(ResourceLocation poolRL, int structureSize, int centerOffset, int biomeRange, int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet) {
        super(poolRL, structureSize, centerOffset, biomeRange, structureBlacklistRange, avoidStructuresSet);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig noFeatureConfig) {
        for (int curChunkX = chunkX - 10; curChunkX <= chunkX + 10; curChunkX++) {
            for (int curChunkZ = chunkZ - 10; curChunkZ <= chunkZ + 10; curChunkZ++) {
                for(Structure<?> outpost : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST)){
                    StructureSeparationSettings structureConfig = chunkGenerator.getSettings().getConfig(outpost);
                    if(structureConfig != null && structureConfig.spacing() > 10) {
                        ChunkPos chunkPos2 = outpost.getPotentialFeatureChunk(structureConfig, seed, chunkRandom, curChunkX, curChunkZ);
                        if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                            return false;
                        }
                    }
                }
            }
        }
        return super.isFeatureChunk(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, noFeatureConfig);
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return VillageNetherStructure.Start::new;
    }

    public class Start extends MainStart {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            super.generatePieces(dynamicRegistryManager, chunkGenerator, structureManager, chunkX, chunkZ, biome, NoFeatureConfig);

            BlockPos lowestLandPos = getHighestLand(chunkGenerator, this.boundingBox);
            if (lowestLandPos.getY() >= chunkGenerator.getGenDepth() - 20 || lowestLandPos.getY() <= chunkGenerator.getSeaLevel() + 1) {
                this.moveInsideHeights(this.random, chunkGenerator.getSeaLevel() - 12, chunkGenerator.getSeaLevel() - 11);
            }
            else {
                this.moveInsideHeights(this.random, lowestLandPos.getY() - 13, lowestLandPos.getY() - 12);
            }
        }

    }


    //Helper methods//

    public static BlockPos getHighestLand(ChunkGenerator chunkGenerator, MutableBoundingBox bounds) {
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(bounds.getCenter().getX(), chunkGenerator.getGenDepth() - 20, bounds.getCenter().getZ());
        IBlockReader blockView = chunkGenerator.getBaseColumn(mutable.getX(), mutable.getZ());
        BlockState currentBlockstate;
        while (mutable.getY() > chunkGenerator.getSeaLevel() + 1) {
            currentBlockstate = blockView.getBlockState(mutable);
            if (!currentBlockstate.isCollisionShapeFullBlock(blockView, mutable)) {
                mutable.move(Direction.DOWN);
                continue;
            }
            else if (blockView.getBlockState(mutable.offset(0, 3, 0)).getMaterial() == Material.AIR &&
                    isValidBlock(currentBlockstate)) {
                break;
            }
            mutable.move(Direction.DOWN);
        }

        return mutable;
    }

    private static boolean isValidBlock(BlockState currentBlockstate) {
        return BlockTags.INFINIBURN_NETHER.contains(currentBlockstate.getBlock()) ||
                BlockTags.VALID_SPAWN.contains(currentBlockstate.getBlock()) ||
                BlockTags.SAND.contains(currentBlockstate.getBlock()) ||
                BlockTags.NYLIUM.contains(currentBlockstate.getBlock()) ||
                BlockTags.ICE.contains(currentBlockstate.getBlock()) ||
                BlockTags.PLANKS.contains(currentBlockstate.getBlock()) ||
                BlockTags.STONE_BRICKS.contains(currentBlockstate.getBlock()) ||
                BlockTags.WITHER_IMMUNE.contains(currentBlockstate.getBlock()) ||
                BlockTags.WOOL.contains(currentBlockstate.getBlock()) ||
                currentBlockstate.getMaterial() == Material.SAND ||
                currentBlockstate.getMaterial() == Material.STONE ||
                currentBlockstate.getMaterial() == Material.DIRT;
    }
}