package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.AdvancedDistanceJigsawStructureCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;


public class AdvancedDistanceJigsawStructure extends AdvancedJigsawStructure {

    public AdvancedDistanceJigsawStructure(Predicate<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>, Optional<PieceGenerator<NoneFeatureConfiguration>>> pieceCreationPredicate) {
        super(locationCheckPredicate, pieceCreationPredicate);
    }

    // Need this constructor wrapper so we can hackly call `this` in the predicates that Minecraft requires in constructors
    public static AdvancedDistanceJigsawStructure create(AdvancedDistanceJigsawStructureCodeConfig advancedDistanceJigsawStructureCodeConfig) {
        final Mutable<AdvancedDistanceJigsawStructure> box = new MutableObject<>();
        final AdvancedDistanceJigsawStructure finalInstance = new AdvancedDistanceJigsawStructure(
                (context) -> box.getValue().isFeatureChunk(context, advancedDistanceJigsawStructureCodeConfig),
                (context) -> box.getValue().generatePieces(context, advancedDistanceJigsawStructureCodeConfig)
        );
        box.setValue(finalInstance);
        return finalInstance;
    }

    protected boolean isFeatureChunk(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, AdvancedDistanceJigsawStructureCodeConfig config) {
        int radius = config.distanceFromWorldOrigin;
        int xBlockPos = context.chunkPos().getMinBlockX();
        int zBlockPos = context.chunkPos().getMinBlockZ();
        return (xBlockPos * xBlockPos) + (zBlockPos * zBlockPos) > radius * radius;
    }

    public Optional<PieceGenerator<NoneFeatureConfiguration>> generatePieces(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, AdvancedDistanceJigsawStructureCodeConfig config) {
        BlockPos.MutableBlockPos blockpos = new BlockPos.MutableBlockPos(context.chunkPos().getMinBlockX(), 0, context.chunkPos().getMinBlockZ());
        if(config.maxY - config.minY <= 0){
            RepurposedStructures.LOGGER.error("MinY should always be less than MaxY or else a crash will occur or no pieces will spawn. Problematic structure is:" + Registry.STRUCTURE_FEATURE.getKey(this));
        }
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
        random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
        int structureStartHeight = random.nextInt(config.maxY - config.minY) + config.minY;
        blockpos.move(Direction.UP, structureStartHeight);

        int topClipOff;
        int bottomClipOff;
        if(config.verticalRange == null){
            // Help make sure the Jigsaw Blocks have room to spawn new pieces if structure is right on edge of maxY or topYLimit
            topClipOff = config.clipOutOfBoundsPieces ? config.maxY + 5 : Integer.MAX_VALUE;
            bottomClipOff = config.clipOutOfBoundsPieces ? config.minY - 5 : Integer.MIN_VALUE;
        }
        else{
            topClipOff = structureStartHeight + config.verticalRange;
            bottomClipOff = structureStartHeight - config.verticalRange;
        }

        ResourceLocation structureID = Registry.STRUCTURE_FEATURE.getKey(this);
        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(() -> context.registryAccess().registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(config.startPool), config.structureSize),
                structureID,
                blockpos,
                false,
                false,
                topClipOff,
                bottomClipOff,
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