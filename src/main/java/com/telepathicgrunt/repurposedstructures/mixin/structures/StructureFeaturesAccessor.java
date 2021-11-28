package com.telepathicgrunt.repurposedstructures.mixin.structures;

import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StructureFeatures.class)
public interface StructureFeaturesAccessor {
    @Accessor("PILLAGER_OUTPOST")
    static ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> getPILLAGER_OUTPOST() {
        throw new UnsupportedOperationException();
    }
}
