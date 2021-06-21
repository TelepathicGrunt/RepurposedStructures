package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;


public abstract class AbstractNetherStructure extends AbstractBaseStructure<DefaultFeatureConfig> {

    public AbstractNetherStructure(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, ChunkPos pos, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {

        // No one can be within 6 chunks of outpost
        boolean isNetherOutpost = RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST).contains(this);
        int radius = isNetherOutpost ? 6 : 3;
        for (int curChunkX = pos.x - radius; curChunkX <= pos.x + radius; curChunkX++) {
            for (int curChunkZ = pos.z - radius; curChunkZ <= pos.z + radius; curChunkZ++) {
                if(curChunkX == pos.x && curChunkZ == pos.z) continue; // Prevent detecting the structure itself and thus, never spawning if structure is in its own blacklist

                for(StructureFeature<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE)) {
                    StructureConfig structureConfig = chunkGenerator.getStructuresConfig().getForType(structureFeature);
                    if(structureConfig != null && structureConfig.getSpacing() > 8) {
                        ChunkPos chunkPos2 = structureFeature.getStartChunk(structureConfig, seed, chunkRandom, curChunkX, curChunkZ);
                        if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                            return false;
                        }
                    }
                }
            }
        }
        return super.shouldStartAt(chunkGenerator, biomeSource, seed, chunkRandom, pos, biome, chunkPos, defaultFeatureConfig, heightLimitView);
    }

    public static abstract class AbstractStart extends MarginedStructureStart<DefaultFeatureConfig> {
        public AbstractStart(StructureFeature<DefaultFeatureConfig> structureIn, ChunkPos pos, int referenceIn, long seedIn) {
            super(structureIn, pos, referenceIn, seedIn);
        }


        public BlockPos getHighestLand(ChunkGenerator chunkGenerator, HeightLimitView heightLimitView){
            BlockPos.Mutable mutable = new BlockPos.Mutable().set(this.setBoundingBoxFromChildren().getCenter().getX(), chunkGenerator.getWorldHeight() - 20, this.setBoundingBoxFromChildren().getCenter().getZ());
            VerticalBlockSample blockView = chunkGenerator.getColumnSample(mutable.getX(), mutable.getZ(), heightLimitView);
            BlockState currentBlockstate;
            while(mutable.getY() > chunkGenerator.getSeaLevel() - 2){
                currentBlockstate = blockView.getState(mutable);
                if(!currentBlockstate.isOpaque()){
                    mutable.move(Direction.DOWN);
                    continue;
                }
                else if(blockView.getState(mutable.add(0,3,0)).getMaterial() == Material.AIR && !currentBlockstate.isAir())
                {
                    break;
                }
                mutable.move(Direction.DOWN);
            }

            return mutable;
        }

        public BlockPos getLowestLand(ChunkGenerator chunkGenerator, HeightLimitView heightLimitView){
            BlockPos.Mutable mutable = new BlockPos.Mutable().set(this.setBoundingBoxFromChildren().getCenter().getX(), chunkGenerator.getSeaLevel() + 3, this.calculateBoundingBox().getCenter().getZ());
            VerticalBlockSample blockView = chunkGenerator.getColumnSample(mutable.getX(), mutable.getZ(), heightLimitView);
            BlockState currentBlockstate = blockView.getState(mutable);
            while (mutable.getY() <= chunkGenerator.getWorldHeight()) {

                if(blockView.getState(mutable).getMaterial() != Material.AIR &&
                        blockView.getState(mutable.up()).getMaterial() == Material.AIR &&
                        blockView.getState(mutable.up(5)).getMaterial() == Material.AIR &&
                        !currentBlockstate.isAir())
                {
                    mutable.move(Direction.UP);
                    break;
                }

                mutable.move(Direction.UP);
                currentBlockstate = blockView.getState(mutable);
            }

            return mutable;
        }
    }
}