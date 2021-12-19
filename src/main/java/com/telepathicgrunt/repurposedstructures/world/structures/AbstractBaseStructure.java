package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.configs.RSMiscConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.StructureCheckResult;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractBaseStructure<C extends FeatureConfiguration> extends StructureFeature<C> {

    public AbstractBaseStructure(Codec<C> codec, Predicate<PieceGeneratorSupplier.Context<C>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<C>, Optional<PieceGenerator<C>>> pieceCreationPredicate) {
        this(codec, locationCheckPredicate, pieceCreationPredicate, PostPlacementProcessor.NONE);
    }

    public AbstractBaseStructure(Codec<C> codec, Predicate<PieceGeneratorSupplier.Context<C>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<C>, Optional<PieceGenerator<C>>> pieceCreationPredicate, PostPlacementProcessor postPlacementProcessor) {
        super(codec, (context) -> {
                    if (!locationCheckPredicate.test(context)) {
                        return Optional.empty();
                    }
                    else {
                        return pieceCreationPredicate.apply(context);
                    }
                },
                postPlacementProcessor);
    }

    @Override
    public BlockPos getNearestGeneratedFeature(LevelReader worldView, StructureFeatureManager structureManager, BlockPos blockPos, int radius, boolean skipExistingChunks, long seed, StructureFeatureConfiguration structureConfig) {
        return getNearestGeneratedFeatureExpandedAndTimed(worldView, structureManager, blockPos, radius, skipExistingChunks, seed, structureConfig);
    }

    protected BlockPos getNearestGeneratedFeatureExpandedAndTimed(LevelReader worldView, StructureFeatureManager structureManager, BlockPos blockPos, int radius, boolean skipExistingChunks, long seed, StructureFeatureConfiguration structureConfig) {
        int spacing = structureConfig.spacing();
        int chunkX = SectionPos.blockToSectionCoord(blockPos.getX());
        int chunkZ = SectionPos.blockToSectionCoord(blockPos.getZ());
        int maxRadius = radius < 100 ? radius : 50000/structureConfig.spacing();
        long startTime = System.currentTimeMillis();
        long abortTime = (long)(RSMiscConfig.locateMaxTime.get() * 1000D);

        for(int currentRadius = 0; currentRadius <= maxRadius; ++currentRadius) {
            for(int xRadius = -currentRadius; xRadius <= currentRadius; ++xRadius) {
                boolean xEdge = xRadius == -currentRadius || xRadius == currentRadius;

                for(int zRadius = -currentRadius; zRadius <= currentRadius; ++zRadius) {
                    boolean zEdge = zRadius == -currentRadius || zRadius == currentRadius;
                    if (xEdge || zEdge) {
                        int trueChunkX = chunkX + spacing  * xRadius;
                        int trueChunkZ = chunkZ + spacing  * zRadius;
                        ChunkPos chunkpos = this.getPotentialFeatureChunk(structureConfig, seed, trueChunkX, trueChunkZ);
                        StructureCheckResult structurecheckresult = structureManager.checkStructurePresence(chunkpos, this, skipExistingChunks);
                        if (structurecheckresult != StructureCheckResult.START_NOT_PRESENT) {
                            if (!skipExistingChunks && structurecheckresult == StructureCheckResult.START_PRESENT) {
                                return this.getLocatePos(chunkpos);
                            }

                            ChunkAccess chunkaccess = worldView.getChunk(chunkpos.x, chunkpos.z, ChunkStatus.STRUCTURE_STARTS);
                            StructureStart<?> structurestart = structureManager.getStartForFeature(SectionPos.bottomOf(chunkaccess), this, chunkaccess);
                            if (structurestart != null && structurestart.isValid()) {
                                if (skipExistingChunks && structurestart.canBeReferenced()) {
                                    structureManager.addReference(structurestart);
                                    return this.getLocatePos(structurestart.getChunkPos());
                                }

                                if (!skipExistingChunks) {
                                    return this.getLocatePos(structurestart.getChunkPos());
                                }
                            }
                        }
                    }
                    else {
                        zRadius = currentRadius - 1;
                    }
                }
            }

            if(currentRadius % 3 == 0 && (System.currentTimeMillis() - startTime) > abortTime) {
                return null;
            }
        }

        return null;
    }
}
