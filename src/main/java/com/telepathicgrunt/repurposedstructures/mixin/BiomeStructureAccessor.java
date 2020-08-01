package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(Biome.class)
public interface BiomeStructureAccessor {

    @Accessor("structures")
    Map<Structure<?>, StructureFeature<?, ?>> getStructures();
}
