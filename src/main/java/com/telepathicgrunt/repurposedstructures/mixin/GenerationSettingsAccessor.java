package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.function.Supplier;

@Mixin(GenerationSettings.class)
public interface GenerationSettingsAccessor {

    @Accessor("features")
    List<List<Supplier<ConfiguredFeature<?, ?>>>> getGSFeatures();

    @Accessor("features")
    void setGSFeatures(List<List<Supplier<ConfiguredFeature<?, ?>>>> features);

    @Accessor("structureFeatures")
    List<Supplier<ConfiguredStructureFeature<?, ?>>> getGSStructureFeatures();

    @Accessor("structureFeatures")
    void setGSStructureFeatures(List<Supplier<ConfiguredStructureFeature<?, ?>>> structureFeatures);
}