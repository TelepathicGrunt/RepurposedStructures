package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

public abstract class AbstractBaseStructure <C extends IFeatureConfig> extends Structure<C> {
    /**
     * --------------------------------------------------------------------------
     * |															        	|
     * |			HELLO READERS! IF YOU'RE HERE, YOU'RE PROBABLY		    	|
     * |			LOOKING FOR A TUTORIAL ON HOW TO DO STRUCTURES	    		|
     * |																        |
     * -------------------------------------------------------------------------
     *
     * Don't worry, I actually have a structure tutorial
     * mod already setup for you to check out! It's full
     * of comments on what does what and how to make structures.
     *
     * Here's the link! https://github.com/TelepathicGrunt/StructureTutorialMod
     *
     * Good luck and have fun modding!
     */

    public AbstractBaseStructure(Codec<C> codec) {
        super(codec);
    }

    @Override
    public BlockPos locateStructure(IWorldReader worldView, StructureManager structureAccessor, BlockPos blockPos, int radius, boolean skipExistingChunks, long seed, StructureSeparationSettings structureConfig) {
        return locateStructureFast(worldView, structureAccessor, blockPos, radius, skipExistingChunks, seed, structureConfig, this);
    }

    public static <C extends IFeatureConfig> BlockPos locateStructureFast(IWorldReader worldView, StructureManager structureAccessor, BlockPos blockPos, int radius, boolean skipExistingChunks, long seed, StructureSeparationSettings structureConfig, Structure<C> structure) {
        int spacing = structureConfig.getSpacing();
        int chunkX = blockPos.getX() >> 4;
        int chunkZ = blockPos.getZ() >> 4;
        int currentRadius = 0;

        for(SharedSeedRandom chunkRandom = new SharedSeedRandom(); currentRadius <= 100000; ++currentRadius) {
            for(int xRadius = -currentRadius; xRadius <= currentRadius; ++xRadius) {
                boolean xEdge = xRadius == -currentRadius || xRadius == currentRadius;

                for(int zRadius = -currentRadius; zRadius <= currentRadius; ++zRadius) {
                    boolean zEdge = zRadius == -currentRadius || zRadius == currentRadius;
                    if (xEdge || zEdge) {
                        int trueChunkX = chunkX + spacing * xRadius;
                        int trueChunkZ = chunkZ + spacing * zRadius;
                        ChunkPos chunkPos = structure.getStartChunk(structureConfig, seed, chunkRandom, trueChunkX, trueChunkZ);
                        if(worldView.getBiomeForNoiseGen((chunkPos.x << 2) + 2, 60, (chunkPos.z << 2) + 2).getGenerationSettings().hasStructureFeature(structure)) {
                            IChunk chunk = worldView.getChunk(chunkPos.x, chunkPos.z, ChunkStatus.STRUCTURE_STARTS);
                            StructureStart<?> structureStart = structureAccessor.getStructureStart(SectionPos.from(chunk.getPos(), 0), structure, chunk);
                            if (structureStart != null && structureStart.isValid()) {
                                if (skipExistingChunks && structureStart.isRefCountBelowMax()) {
                                    structureStart.incrementRefCount();
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
