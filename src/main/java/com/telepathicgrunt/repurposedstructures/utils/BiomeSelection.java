package com.telepathicgrunt.repurposedstructures.utils;

import com.telepathicgrunt.repurposedstructures.misc.BiomeDimensionAllowDisallow;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;

public final class BiomeSelection {
    private BiomeSelection() {}

    public static boolean hasName(BiomeSelectionContext context, String... names) {
        return Arrays.stream(names).anyMatch(name -> context.getBiomeKey().location().getPath().contains(name));
    }

    public static boolean hasNamespace(BiomeSelectionContext context, String... namespace) {
        return Arrays.stream(namespace).anyMatch(name -> context.getBiomeKey().location().getNamespace().contains(name));
    }

    @SafeVarargs
    public static boolean isBiome(BiomeSelectionContext context, ResourceKey<Biome>... keys) {
        return Arrays.stream(keys).anyMatch(key -> context.getBiomeKey().equals(key));
    }

    public static boolean haveCategories(BiomeSelectionContext context, TagKey<Biome>... tagKeys) {
        return Arrays.stream(tagKeys).anyMatch(context::hasTag);
    }

    public static boolean isBiomeAllowed(BiomeSelectionContext context, PlacedFeature placedFeature, Supplier<Boolean> defaultCondition) {
        return (BiomeSelection.isBiomeAllowed(context, placedFeature, BuiltinRegistries.PLACED_FEATURE) ||
                (!BiomeSelection.isBiomeDisallowed(context, placedFeature, BuiltinRegistries.PLACED_FEATURE) &&
                        defaultCondition.get()));
    }

    public static <T> boolean isBiomeAllowed(BiomeSelectionContext context, T worldgenObject, Registry<T> registry) {
        ResourceLocation registryId = registry.getKey(worldgenObject);
        String biomeID = context.getBiomeKey().location().toString();
        return BiomeDimensionAllowDisallow.BIOME_ALLOW.getOrDefault(registryId, new ArrayList<>()).stream().anyMatch(pattern -> pattern.matcher(biomeID).matches());
    }

    public static <T> boolean isBiomeDisallowed(BiomeSelectionContext context, T worldgenObject, Registry<T> registry) {
        ResourceLocation registryId = registry.getKey(worldgenObject);
        String biomeID = context.getBiomeKey().location().toString();
        return BiomeDimensionAllowDisallow.BIOME_DISALLOW.getOrDefault(registryId, new ArrayList<>()).stream().anyMatch(pattern -> pattern.matcher(biomeID).matches());
    }

    public static boolean isBiomeAllowed(BiomeSelectionContext context, StructureFeature<?> structureFeature, Supplier<Boolean> defaultCondition) {
        return (BiomeSelection.isBiomeAllowed(context, structureFeature, Registry.STRUCTURE_FEATURE) ||
                (!BiomeSelection.isBiomeDisallowed(context, structureFeature, Registry.STRUCTURE_FEATURE) &&
                        defaultCondition.get()));
    }
}
