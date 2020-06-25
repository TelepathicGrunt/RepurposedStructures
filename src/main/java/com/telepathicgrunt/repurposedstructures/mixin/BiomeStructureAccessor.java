package com.telepathicgrunt.repurposedstructures.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.StructureFeature;

@Mixin(Biome.class)
public interface BiomeStructureAccessor {

    @Accessor("structureFeatures")
    public Map<StructureFeature<?>, ConfiguredStructureFeature<?, ?>> getStructureFeatures();
}
