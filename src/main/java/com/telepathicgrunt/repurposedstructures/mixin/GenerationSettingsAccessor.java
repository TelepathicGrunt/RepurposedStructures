package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.function.Supplier;

@Mixin(BiomeGenerationSettings.class)
public interface GenerationSettingsAccessor {

    @Accessor("features")
    List<List<Supplier<ConfiguredFeature<?, ?>>>> getGSFeatures();

    @Accessor("features")
    void setGSFeatures(List<List<Supplier<ConfiguredFeature<?, ?>>>> features);

    @Accessor("structureFeatures")
    List<Supplier<StructureFeature<?, ?>>> getGSStructureFeatures();

    @Accessor("structureFeatures")
    void setGSStructureFeatures(List<Supplier<StructureFeature<?, ?>>> structureFeatures);
}