package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Set;

public class VillageNetherStructure extends GenericJigsawStructure {
    // Special thanks to /r/l-ll-ll-l_IsDisLoss for allowing me to use his nether village design!

    public VillageNetherStructure(Identifier poolID, int structureSize, int centerOffset, int biomeRange, int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet) {
        super(poolID, structureSize, centerOffset, biomeRange, structureBlacklistRange, avoidStructuresSet);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {

        for (int curChunkX = chunkPos1.x - 10; curChunkX <= chunkPos1.x + 10; curChunkX++) {
            for (int curChunkZ = chunkPos1.z - 10; curChunkZ <= chunkPos1.z + 10; curChunkZ++) {
                if(curChunkX == chunkPos1.x && curChunkZ == chunkPos1.z) continue; // Prevent detecting the structure itself and thus, never spawning if structure is in its own blacklist

                for(StructureFeature<?> outpost : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST)){
                    StructureConfig structureConfig = chunkGenerator.getStructuresConfig().getForType(outpost);
                    if(structureConfig != null && structureConfig.getSpacing() > 8) {
                        ChunkPos chunkPos2 = outpost.getStartChunk(structureConfig, seed, chunkRandom, curChunkX, curChunkZ);
                        if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                            return false;
                        }
                    }
                }
            }
        }
        return super.shouldStartAt(chunkGenerator, biomeSource, seed, chunkRandom, chunkPos1, biome, chunkPos, defaultFeatureConfig, heightLimitView);
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return VillageNetherStructure.Start::new;
    }

    public class Start extends MainStart {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
        }

        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
            super.init(dynamicRegistryManager, chunkGenerator, structureManager, chunkPos1, biome, defaultFeatureConfig, heightLimitView);

            BlockPos lowestLandPos = getHighestLand(chunkGenerator, this.calculateBoundingBox(), heightLimitView);
            if (lowestLandPos.getY() >= chunkGenerator.getWorldHeight() || lowestLandPos.getY() <= chunkGenerator.getSeaLevel() + 1) {
                this.randomUpwardTranslation(this.random, chunkGenerator.getSeaLevel() - 12, chunkGenerator.getSeaLevel() - 11);
            }
            else {
                this.randomUpwardTranslation(this.random, lowestLandPos.getY() - 13, lowestLandPos.getY() - 12);
            }
        }
    }

    //Helper methods//

    public static BlockPos getHighestLand(ChunkGenerator chunkGenerator, BlockBox boundingBox, HeightLimitView heightLimitView) {
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(boundingBox.getCenter().getX(), chunkGenerator.getWorldHeight() - 20, boundingBox.getCenter().getZ());
        VerticalBlockSample blockView = chunkGenerator.getColumnSample(mutable.getX(), mutable.getZ(), heightLimitView);
        BlockState currentBlockstate;
        while (mutable.getY() > chunkGenerator.getSeaLevel() + 1) {
            currentBlockstate = blockView.getState(mutable);
            if (!currentBlockstate.isOpaque()) {
                mutable.move(Direction.DOWN);
                continue;
            }
            else if (blockView.getState(mutable.add(0, 3, 0)).getMaterial() == Material.AIR && currentBlockstate.isOpaque()) {
                break;
            }
            mutable.move(Direction.DOWN);
        }

        return mutable;
    }
}