package com.telepathicgrunt.repurposedstructures.mixin.structures;

import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.Map;

@Mixin(StructureFeature.class)
public interface StructureFeatureAccessor {
    @Mutable
    @Accessor("STEP")
    static Map<StructureFeature<?>, GenerationStep.Decoration> getSTEP() {
        throw new UnsupportedOperationException();
    }

    @Mutable
    @Accessor("NOISE_AFFECTING_FEATURES")
    static void setNOISE_AFFECTING_FEATURES(List<StructureFeature<?>> noiseAffectingFeatures) {
        throw new UnsupportedOperationException();
    }
}
