package com.telepathicgrunt.repurposedstructures.utils;

import com.google.common.collect.Multimap;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.biomeinjection.temp.TemporaryBiomeInjection;
import com.telepathicgrunt.repurposedstructures.misc.BiomeDimensionAllowDisallow;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
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
    public static boolean hasStructure(BiomeSelectionContext context, StructureFeature<?> structureFeature) {
        //return context.getBiome().getGenerationSettings().isValidStart(structureFeature);
        return false;
    }

    public static boolean hasStructureType(BiomeSelectionContext context, RSStructureTagMap.STRUCTURE_TAGS tag) {
        //return RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(tag).stream().anyMatch(structure -> context.getBiome().getGenerationSettings().isValidStart(structure));
        return false;
    }

    public static boolean doesNotHaveStructureType(BiomeSelectionContext context, RSStructureTagMap.STRUCTURE_TAGS tag) {
        //return RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(tag).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().isValidStart(structure));
        return true;
    }


    public static boolean isBiomeAllowed(BiomeSelectionContext context, StructureFeature<?> structureFeature, Supplier<Boolean> defaultCondition) {
        return (BiomeSelection.isBiomeAllowed(context, structureFeature, Registry.STRUCTURE_FEATURE) ||
                    (!BiomeSelection.isBiomeDisallowed(context, structureFeature, Registry.STRUCTURE_FEATURE) &&
                            defaultCondition.get()));
    }

    public static boolean isBiomeAllowed(BiomeSelectionContext context, ConfiguredFeature<?, ?> configuredFeature, Supplier<Boolean> defaultCondition) {
        return (BiomeSelection.isBiomeAllowed(context, configuredFeature, BuiltinRegistries.CONFIGURED_FEATURE) ||
                (!BiomeSelection.isBiomeDisallowed(context, configuredFeature, BuiltinRegistries.CONFIGURED_FEATURE) &&
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

    //////////////////////////////////////////////////////
    // Temporary until BiomeModification API works again for structures

    @SafeVarargs
    public static boolean isBiomeTemp(TemporaryBiomeInjection.BiomeInjectionHelper context, ResourceKey<Biome>... keys) {
        return Arrays.stream(keys).anyMatch(key -> context.getBiomeKey().equals(key));
    }

    public static boolean hasNameTemp(TemporaryBiomeInjection.BiomeInjectionHelper context, String... names) {
        return Arrays.stream(names).anyMatch(name -> context.biomeKey.location().getPath().contains(name));
    }

    public static boolean hasNamespaceTemp(TemporaryBiomeInjection.BiomeInjectionHelper context, String... namespace) {
        return Arrays.stream(namespace).anyMatch(name -> context.getBiomeKey().location().getNamespace().contains(name));
    }

    public static boolean haveCategoriesTemp(TemporaryBiomeInjection.BiomeInjectionHelper context, Biome.BiomeCategory... categories) {
        Set<Biome.BiomeCategory> categorySet = new HashSet<>(Arrays.asList(categories));
        return categorySet.contains(context.biome.getBiomeCategory());
    }

    public static boolean doesNotHaveStructureTypeTemp(TemporaryBiomeInjection.BiomeInjectionHelper context, RSStructureTagMap.STRUCTURE_TAGS tag) {
        return RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(tag).stream().noneMatch(structure -> {
            Multimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> structureFeatureResourceKeyMultimap = context.structureToMultiMap.get(structure);
            if(structureFeatureResourceKeyMultimap == null) return false;
            return structureFeatureResourceKeyMultimap.entries().stream().anyMatch(entry -> entry.getValue().equals(context.biomeKey));
        });
    }

    public static boolean isBiomeAllowedTemp(TemporaryBiomeInjection.BiomeInjectionHelper context, StructureFeature<?> structureFeature, Supplier<Boolean> defaultCondition) {
        return (BiomeSelection.isBiomeAllowedTemp(context, structureFeature, Registry.STRUCTURE_FEATURE) ||
                (!BiomeSelection.isBiomeDisallowedTemp(context, structureFeature, Registry.STRUCTURE_FEATURE) &&
                        defaultCondition.get()));
    }

    public static boolean isBiomeAllowedPlacedFeature(BiomeSelectionContext context, PlacedFeature placedFeature, Supplier<Boolean> defaultCondition) {
        return (BiomeSelection.isBiomeAllowed(context, placedFeature, BuiltinRegistries.PLACED_FEATURE) ||
                (!BiomeSelection.isBiomeDisallowed(context, placedFeature, BuiltinRegistries.PLACED_FEATURE) &&
                        defaultCondition.get()));
    }

    public static <T> boolean isBiomeAllowedTemp(TemporaryBiomeInjection.BiomeInjectionHelper context, T worldgenObject, Registry<T> registry) {
        ResourceLocation registryId = registry.getKey(worldgenObject);
        String biomeID = context.biomeRegistry.getKey(context.biome).toString();
        return BiomeDimensionAllowDisallow.BIOME_ALLOW.getOrDefault(registryId, new ArrayList<>()).stream()
                .anyMatch(pattern -> {
                    if(pattern.pattern().startsWith("#")) {
                        String cleanedUpCategoryString = pattern.pattern().trim().toLowerCase(Locale.ROOT).replace("#", "");
                        Biome.BiomeCategory category = Biome.BiomeCategory.byName(cleanedUpCategoryString);
                        if(category == null) {
                            RepurposedStructures.LOGGER.warn("Unknown biome category detected in one of the biome allow configs: {}", cleanedUpCategoryString);
                        }
                        else{
                            return context.biome.getBiomeCategory().equals(category);
                        }
                    }

                    return pattern.matcher(biomeID).matches();
                });
    }

    public static <T> boolean isBiomeDisallowedTemp(TemporaryBiomeInjection.BiomeInjectionHelper context, T worldgenObject, Registry<T> registry) {
        ResourceLocation registryId = registry.getKey(worldgenObject);
        String biomeID = context.biomeRegistry.getKey(context.biome).toString();
        return BiomeDimensionAllowDisallow.BIOME_DISALLOW.getOrDefault(registryId, new ArrayList<>()).stream()
                .anyMatch(pattern -> {
                    if(pattern.pattern().startsWith("#")) {
                        String cleanedUpCategoryString = pattern.pattern().trim().toLowerCase(Locale.ROOT).replace("#", "");
                        Biome.BiomeCategory category = Biome.BiomeCategory.byName(cleanedUpCategoryString);
                        if(category == null) {
                            RepurposedStructures.LOGGER.warn("Unknown biome category detected in one of the biome disallow configs: {}", cleanedUpCategoryString);
                        }
                        else{
                            return context.biome.getBiomeCategory().equals(category);
                        }
                    }

                    return pattern.matcher(biomeID).matches();
                });
    }
}
