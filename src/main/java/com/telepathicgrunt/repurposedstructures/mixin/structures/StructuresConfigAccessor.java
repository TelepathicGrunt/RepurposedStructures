package com.telepathicgrunt.repurposedstructures.mixin.structures;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;

@Mixin(StructureSettings.class)
public interface StructuresConfigAccessor {

    @Accessor("structureConfig")
    void repurposedstructures_setStructureConfig(Map<StructureFeature<?>, StructureFeatureConfiguration> structuresSpacingMap);
}