package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.mojang.serialization.Codec;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public abstract class AbstractBaseStructure extends StructureFeature<DefaultFeatureConfig> {

    public AbstractBaseStructure(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public BlockPos locateStructure(WorldView worldView, StructureAccessor structureAccessor, BlockPos blockPos, int radius, boolean skipExistingChunks, long seed, StructureConfig structureConfig) {
        return AbstractBaseStructure.locateStructureFast(worldView, structureAccessor, blockPos, radius, skipExistingChunks, seed, structureConfig, this);
    }

    public static BlockPos locateStructureFast(WorldView worldView, StructureAccessor structureAccessor, BlockPos blockPos, int radius, boolean skipExistingChunks, long seed, StructureConfig structureConfig, StructureFeature<DefaultFeatureConfig> structure) {
        int spacing = structureConfig.getSpacing();
        int chunkX = blockPos.getX() >> 4;
        int chunkZ = blockPos.getZ() >> 4;
        int currentRadius = 0;
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(ChunkRandom chunkRandom = new ChunkRandom(); currentRadius <= 100000 / structureConfig.getSpacing(); ++currentRadius) {
            for(int xRadius = -currentRadius; xRadius <= currentRadius; ++xRadius) {
                boolean xEdge = xRadius == -currentRadius || xRadius == currentRadius;

                for(int zRadius = -currentRadius; zRadius <= currentRadius; ++zRadius) {
                    boolean zEdge = zRadius == -currentRadius || zRadius == currentRadius;
                    if (xEdge || zEdge) {
                        int trueChunkX = chunkX + spacing * xRadius;
                        int trueChunkZ = chunkZ + spacing * zRadius;
                        if(worldView.getBiome(mutable.set(trueChunkX << 4, 1, trueChunkZ << 4)).hasStructureFeature(structure)) {
                            ChunkPos chunkPos = structure.method_27218(structureConfig, seed, chunkRandom, trueChunkX, trueChunkZ);
                            Chunk chunk = worldView.getChunk(chunkPos.x, chunkPos.z, ChunkStatus.STRUCTURE_STARTS);
                            StructureStart<?> structureStart = structureAccessor.getStructureStart(ChunkSectionPos.from(chunk.getPos(), 0), structure, chunk);
                            if (structureStart != null && structureStart.hasChildren()) {
                                if (skipExistingChunks && structureStart.isInExistingChunk()) {
                                    structureStart.incrementReferences();
                                    return structureStart.getPos();
                                }

                                if (!skipExistingChunks) {
                                    return structureStart.getPos();
                                }
                            }
                        }
                    }
                    else{
                        zRadius = currentRadius - 1;
                    }
                }
            }
        }

        return null;
    }
}
