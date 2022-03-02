package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSAdvancedDistanceConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;


public class AdvancedDistanceJigsawStructure <C extends RSAdvancedDistanceConfig> extends AdvancedJigsawStructure<C> {

    public AdvancedDistanceJigsawStructure(Codec<C> codec) {
        super(codec, AdvancedDistanceJigsawStructure::isDistanceFeatureChunk, AdvancedDistanceJigsawStructure::generateDistancePieces);
    }

    public AdvancedDistanceJigsawStructure(Codec<C> codec, Predicate<PieceGeneratorSupplier.Context<C>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<C>, Optional<PieceGenerator<C>>> pieceCreationPredicate) {
        super(codec, locationCheckPredicate, pieceCreationPredicate);
    }

    protected static <CC extends RSAdvancedDistanceConfig> boolean isDistanceFeatureChunk(PieceGeneratorSupplier.Context<CC> context) {
        CC config = context.config();
        int radius = config.distanceFromOrigin;
        int xBlockPos = context.chunkPos().getMinBlockX();
        int zBlockPos = context.chunkPos().getMinBlockZ();
        return (xBlockPos * xBlockPos) + (zBlockPos * zBlockPos) > radius * radius && AdvancedDistanceJigsawStructure.isAdvancedFeatureChunk(context);
    }

    public static <CC extends RSAdvancedDistanceConfig> Optional<PieceGenerator<CC>> generateDistancePieces(PieceGeneratorSupplier.Context<CC> context) {
        BlockPos.MutableBlockPos blockpos = new BlockPos.MutableBlockPos(context.chunkPos().getMinBlockX(), 0, context.chunkPos().getMinBlockZ());
        CC config = context.config();

        if(config.maxY - config.minY <= 0) {
            RepurposedStructures.LOGGER.error("MinY should always be less than MaxY or else a crash will occur or no pieces will spawn. Problematic structure is:" + config.startPool.unwrapKey().get().location());
        }
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
        random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
        int structureStartHeight = random.nextInt(config.maxY - config.minY) + config.minY;
        blockpos.move(Direction.UP, structureStartHeight);

        int topClipOff;
        int bottomClipOff;
        if(config.verticalRange.isEmpty()) {
            // Help make sure the Jigsaw Blocks have room to spawn new pieces if structure is right on edge of maxY or topYLimit
            topClipOff = config.clipOutOfBoundsPieces ? config.maxY + 5 : Integer.MAX_VALUE;
            bottomClipOff = config.clipOutOfBoundsPieces ? config.minY - 5 : Integer.MIN_VALUE;
        }
        else{
            topClipOff = structureStartHeight + config.verticalRange.get();
            bottomClipOff = structureStartHeight - config.verticalRange.get();
        }

        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(config.startPool, config.size),
                GeneralUtils.getCsfNameForConfig(config, context.registryAccess()),
                blockpos,
                false,
                false,
                topClipOff,
                bottomClipOff,
                config.poolsThatIgnoreBoundaries,
                (structurePiecesBuilder, pieces) -> {
                    Optional<PoolElementStructurePiece> lowestPiece = pieces.stream().min(Comparator.comparingInt(p -> p.getBoundingBox().minY()));
                    int minY = lowestPiece.map(poolElementStructurePiece -> poolElementStructurePiece.getBoundingBox().minY()).orElseGet(blockpos::getY);
                    if(minY < context.chunkGenerator().getMinY()) {
                        int newOffset = context.chunkGenerator().getMinY() - minY;
                        for (StructurePiece piece : pieces) {
                            piece.move(0, newOffset, 0);
                        }
                    }
                });
    }
}