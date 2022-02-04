package com.telepathicgrunt.repurposedstructures.utils;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.BiomeDimensionAllowDisallow;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
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

    public static boolean haveCategories(BiomeSelectionContext context, Biome.BiomeCategory... categories) {
        Set<Biome.BiomeCategory> categorySet = new HashSet<>(Arrays.asList(categories));
        return categorySet.contains(context.getBiome().getBiomeCategory());
    }

    public static boolean isBiomeAllowed(BiomeSelectionContext context, PlacedFeature placedFeature, Supplier<Boolean> defaultCondition) {
        return (BiomeSelection.isBiomeAllowed(context, placedFeature, BuiltinRegistries.PLACED_FEATURE) ||
                (!BiomeSelection.isBiomeDisallowed(context, placedFeature, BuiltinRegistries.PLACED_FEATURE) &&
                        defaultCondition.get()));
    }

    public static <T> boolean isBiomeAllowed(BiomeSelectionContext context, T worldgenObject, Registry<T> registry) {
        ResourceLocation registryId = registry.getKey(worldgenObject);
        String biomeID = context.getBiomeKey().location().toString();
        return BiomeDimensionAllowDisallow.BIOME_ALLOW.getOrDefault(registryId, new ArrayList<>()).stream()
                .anyMatch(pattern -> {
                    if(pattern.pattern().startsWith("#")) {
                        String cleanedUpCategoryString = pattern.pattern().trim().toLowerCase(Locale.ROOT).replace("#", "");
                        Biome.BiomeCategory category = Biome.BiomeCategory.byName(cleanedUpCategoryString);
                        if(category == null) {
                            RepurposedStructures.LOGGER.warn("Unknown biome category detected in one of the biome allow configs: {}", cleanedUpCategoryString);
                        }
                        else{
                            return context.getBiome().getBiomeCategory().equals(category);
                        }
                    }

                    return pattern.matcher(biomeID).matches();
                });
    }

    public static <T> boolean isBiomeDisallowed(BiomeSelectionContext context, T worldgenObject, Registry<T> registry) {
        ResourceLocation registryId = registry.getKey(worldgenObject);
        String biomeID = context.getBiomeKey().location().toString();
        return BiomeDimensionAllowDisallow.BIOME_DISALLOW.getOrDefault(registryId, new ArrayList<>()).stream()
                .anyMatch(pattern -> {
                    if(pattern.pattern().startsWith("#")) {
                        String cleanedUpCategoryString = pattern.pattern().trim().toLowerCase(Locale.ROOT).replace("#", "");
                        Biome.BiomeCategory category = Biome.BiomeCategory.byName(cleanedUpCategoryString);
                        if(category == null) {
                            RepurposedStructures.LOGGER.warn("Unknown biome category detected in one of the biome disallow configs: {}", cleanedUpCategoryString);
                        }
                        else{
                            return context.getBiome().getBiomeCategory().equals(category);
                        }
                    }

                    return pattern.matcher(biomeID).matches();
                });
    }

    public static boolean doesNotHaveStructureType(BiomeSelectionContext context, RSStructureTagMap.STRUCTURE_TAGS tag) {
        return RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(tag).stream().noneMatch(structure -> context
                .hasStructure(ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, Registry.STRUCTURE_FEATURE.getKey(structure))));
    }

    public static boolean isBiomeAllowed(BiomeSelectionContext context, StructureFeature<?> structureFeature, Supplier<Boolean> defaultCondition) {
        return (BiomeSelection.isBiomeAllowed(context, structureFeature, Registry.STRUCTURE_FEATURE) ||
                (!BiomeSelection.isBiomeDisallowed(context, structureFeature, Registry.STRUCTURE_FEATURE) &&
                        defaultCondition.get()));
    }
}
