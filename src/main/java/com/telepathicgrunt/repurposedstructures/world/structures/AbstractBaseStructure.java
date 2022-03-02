package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
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
}
