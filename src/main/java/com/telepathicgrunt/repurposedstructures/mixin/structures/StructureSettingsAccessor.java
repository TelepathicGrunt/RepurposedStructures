package com.telepathicgrunt.repurposedstructures.mixin.structures;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(StructureSettings.class)
public interface StructureSettingsAccessor {

    @Accessor("structureConfig")
    void repurposedstructures_setStructureConfig(Map<StructureFeature<?>, StructureFeatureConfiguration> structuresSpacingMap);

    @Accessor("configuredStructures")
    ImmutableMap<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> getConfiguredStructures();

    @Mutable
    @Accessor("configuredStructures")
    void setConfiguredStructures(ImmutableMap<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> configuredStructures);
}