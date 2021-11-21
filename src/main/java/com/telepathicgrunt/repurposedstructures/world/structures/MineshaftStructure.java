package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.MineshaftCodeConfig;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;


public class MineshaftStructure extends AdvancedJigsawStructure {

    public MineshaftStructure(Predicate<PieceGeneratorSupplier.Context> locationCheckPredicate, Function<PieceGeneratorSupplier.Context, Optional<PieceGenerator<NoneFeatureConfiguration>>> pieceCreationPredicate) {
        super(locationCheckPredicate, pieceCreationPredicate);
    }

    // Need this constructor wrapper so we can hackly call `this` in the predicates that Minecraft requires in constructors
    public static MineshaftStructure create(MineshaftCodeConfig mineshaftCodeConfig) {
        final Mutable<MineshaftStructure> box = new MutableObject<>();
        final MineshaftStructure finalInstance = new MineshaftStructure(
                (context) -> box.getValue().isFeatureChunk(context, mineshaftCodeConfig),
                (context) -> box.getValue().generatePieces(context, mineshaftCodeConfig)
        );
        box.setValue(finalInstance);
        return finalInstance;
    }

    protected boolean isFeatureChunk(PieceGeneratorSupplier.Context context, MineshaftCodeConfig config) {
        StructureFeatureConfiguration structureConfig = context.chunkGenerator().getSettings().getConfig(this);
        if(structureConfig != null) {
            WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
            random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
            random.setLargeFeatureSeed(context.seed() + structureConfig.salt(), context.chunkPos().x, context.chunkPos().z);
            double d = (config.probability / 10000D);
            return random.nextDouble() < d;
        }
        return false;
    }
}