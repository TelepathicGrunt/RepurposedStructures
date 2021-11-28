package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.GenericNetherJigsawStructureCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class GenericNetherJigsawStructure extends GenericJigsawStructure {

    public GenericNetherJigsawStructure(Predicate<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>, Optional<PieceGenerator<NoneFeatureConfiguration>>> pieceCreationPredicate) {
        super(locationCheckPredicate, pieceCreationPredicate);
    }

    // Need this constructor wrapper so we can hackly call `this` in the predicates that Minecraft requires in constructors
    public static GenericNetherJigsawStructure create(GenericNetherJigsawStructureCodeConfig genericNetherJigsawStructureCodeConfig) {
        final Mutable<GenericNetherJigsawStructure> box = new MutableObject<>();
        final GenericNetherJigsawStructure finalInstance = new GenericNetherJigsawStructure(
                (context) -> box.getValue().isFeatureChunk(context, genericNetherJigsawStructureCodeConfig),
                (context) -> box.getValue().generatePieces(context, genericNetherJigsawStructureCodeConfig)
        );
        box.setValue(finalInstance);
        return finalInstance;
    }

    protected Optional<PieceGenerator<NoneFeatureConfiguration>> generatePieces(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, GenericNetherJigsawStructureCodeConfig config) {
        BlockPos blockpos = new BlockPos(context.chunkPos().getMinBlockX(), config.fixedYSpawn, context.chunkPos().getMinBlockZ());

        ResourceLocation structureID = Registry.STRUCTURE_FEATURE.getKey(this);
        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(() -> context.registryAccess().registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(config.startPool), config.structureSize),
                structureID,
                blockpos,
                config.useHeightmap,
                config.useHeightmap,
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                (structurePiecesBuilder, pieces) -> {
                    GeneralUtils.centerAllPieces(blockpos, pieces);
                    pieces.get(0).move(0, config.centerOffset, 0);

                    WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
                    random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
                    BlockPos placementPos;

                    if(config.highestLandSearch) {
                        placementPos = GeneralUtils.getHighestLand(context.chunkGenerator(), structurePiecesBuilder.getBoundingBox(), context.heightAccessor(), config.canPlaceOnLiquid);
                    }
                    else{
                        placementPos = GeneralUtils.getLowestLand(context.chunkGenerator(), structurePiecesBuilder.getBoundingBox(), context.heightAccessor(), config.canPlaceOnLiquid);
                    }

                    if (placementPos.getY() >= context.chunkGenerator().getGenDepth() || placementPos.getY() <= context.chunkGenerator().getSeaLevel() + 1) {
                        structurePiecesBuilder.moveInsideHeights(random, context.chunkGenerator().getSeaLevel() + config.ledgeSpotOffset, context.chunkGenerator().getSeaLevel() + (config.ledgeSpotOffset + 1));
                    }
                    else {
                        structurePiecesBuilder.moveInsideHeights(random, placementPos.getY() + config.liquidSpotOffset, placementPos.getY() + (config.liquidSpotOffset + 1));
                    }
                });
    }
}