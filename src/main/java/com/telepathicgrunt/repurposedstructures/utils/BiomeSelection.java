package com.telepathicgrunt.repurposedstructures.utils;

import com.google.common.collect.Multimap;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.biomeinjection.TemporaryBiomeInjection;
import com.telepathicgrunt.repurposedstructures.misc.BiomeDimensionAllowDisallow;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.function.Supplier;

public final class BiomeSelection {
    private BiomeSelection() {}

    private static final Set<ResourceKey<Biome>> OVERWORLD_BIOMES = new HashSet<>();

    public static void setupOverworldBiomesSet() {
        // Get all vanilla and modded biomes with overworld tag
        OVERWORLD_BIOMES.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.OVERWORLD));

        // Get all modded overworld biomes that doesn't have overworld tag
        OVERWORLD_BIOMES.addAll(BiomeManager.getAdditionalOverworldBiomes());
    }

    public static boolean isOverworldBiome(BiomeLoadingEvent event) {
        ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
        return OVERWORLD_BIOMES.contains(biomeKey);
    }

    public static boolean hasName(BiomeLoadingEvent context, String... names) {
        return Arrays.stream(names).anyMatch(name -> context.getName().getPath().contains(name));
    }

    public static boolean hasNamespace(BiomeLoadingEvent context, String... namespace) {
        return Arrays.stream(namespace).anyMatch(name -> context.getName().getNamespace().contains(name));
    }

    @SafeVarargs
    public static boolean isBiome(BiomeLoadingEvent context, ResourceKey<Biome>... keys) {
        return Arrays.stream(keys).anyMatch(key -> context.getName().equals(key.location()));
    }

    public static boolean haveCategories(BiomeLoadingEvent context, Biome.BiomeCategory... categories) {
        Set<Biome.BiomeCategory> categorySet = new HashSet<>(Arrays.asList(categories));
        return categorySet.contains(context.getCategory());
    }

    public static boolean hasStructure(BiomeLoadingEvent context, StructureFeature<?> structureFeature) {
        return true;
        //return context.getGeneration().getStructures().stream().anyMatch(structureFeatureSupplier -> structureFeatureSupplier.get().feature.equals(structureFeature));
    }

    public static boolean hasStructureType(BiomeLoadingEvent context, RSStructureTagMap.STRUCTURE_TAGS tag) {
        return true;
        //return RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(tag).stream().anyMatch(structure -> context.getGeneration().getStructures().stream().anyMatch(structureFeatureSupplier -> structureFeatureSupplier.get().feature.equals(structure)));
    }

    public static boolean doesNotHaveStructureType(BiomeLoadingEvent context, RSStructureTagMap.STRUCTURE_TAGS tag) {
        return true;
        //return RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(tag).stream().noneMatch(structure -> context.getGeneration().getStructures().stream().anyMatch(structureFeatureSupplier -> structureFeatureSupplier.get().feature.equals(structure)));
    }

    public static boolean doesHaveStructureType(BiomeLoadingEvent context, RSStructureTagMap.STRUCTURE_TAGS tag) {
        return true;
        //return RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(tag).stream().anyMatch(structure -> context.getGeneration().getStructures().stream().anyMatch(structureFeatureSupplier -> structureFeatureSupplier.get().feature.equals(structure)));
    }

    public static boolean isBiomeAllowed(BiomeLoadingEvent context, StructureFeature<?> structureFeature, Supplier<Boolean> defaultCondition) {
        return (BiomeSelection.isBiomeAllowed(context, structureFeature, Registry.STRUCTURE_FEATURE) ||
                (!BiomeSelection.isBiomeDisallowed(context, structureFeature, Registry.STRUCTURE_FEATURE) &&
                        defaultCondition.get()));
    }

    public static boolean isBiomeAllowed(BiomeLoadingEvent context, PlacedFeature placedFeature, Supplier<Boolean> defaultCondition) {
        return (BiomeSelection.isBiomeAllowed(context, placedFeature, BuiltinRegistries.PLACED_FEATURE) ||
                (!BiomeSelection.isBiomeDisallowed(context, placedFeature, BuiltinRegistries.PLACED_FEATURE) &&
                        defaultCondition.get()));
    }

    public static <T> boolean isBiomeAllowed(BiomeLoadingEvent context, T worldgenObject, Registry<T> registry) {
        ResourceLocation registryId = registry.getKey(worldgenObject);
        String biomeID = context.getName().toString();
        return BiomeDimensionAllowDisallow.BIOME_ALLOW.getOrDefault(registryId, new ArrayList<>()).stream()
                .anyMatch(pattern -> {
                    if(pattern.pattern().startsWith("#")){
                        String cleanedUpCategoryString = pattern.pattern().trim().toLowerCase(Locale.ROOT).replace("#", "");
                        Biome.BiomeCategory category = Biome.BiomeCategory.byName(cleanedUpCategoryString);
                        if(category == null){
                            RepurposedStructures.LOGGER.warn("Unknown biome category detected in one of the biome allow configs: {}", cleanedUpCategoryString);
                        }
                        else{
                            return context.getCategory().equals(category);
                        }
                    }

                    return pattern.matcher(biomeID).matches();
                });
    }

    public static <T> boolean isBiomeDisallowed(BiomeLoadingEvent context, T worldgenObject, Registry<T> registry) {
        ResourceLocation registryId = registry.getKey(worldgenObject);
        String biomeID = context.getName().toString();
        return BiomeDimensionAllowDisallow.BIOME_DISALLOW.getOrDefault(registryId, new ArrayList<>()).stream()
                .anyMatch(pattern -> {
                    if(pattern.pattern().startsWith("#")){
                        String cleanedUpCategoryString = pattern.pattern().trim().toLowerCase(Locale.ROOT).replace("#", "");
                        Biome.BiomeCategory category = Biome.BiomeCategory.byName(cleanedUpCategoryString);
                        if(category == null){
                            RepurposedStructures.LOGGER.warn("Unknown biome category detected in one of the biome disallow configs: {}", cleanedUpCategoryString);
                        }
                        else{
                            return context.getCategory().equals(category);
                        }
                    }

                    return pattern.matcher(biomeID).matches();
                });
    }

    public static boolean hasFeature(BiomeLoadingEvent context, PlacedFeature placedFeature) {
        for(GenerationStep.Decoration stage : GenerationStep.Decoration.values()){
            for (Supplier<PlacedFeature> featureSuppliers : context.getGeneration().getFeatures(stage)) {
                if (featureSuppliers.get() == placedFeature) {
                    return true;
                }
            }
        }

        return false;
    }


    //////////////////////////////////////////////////////
    // Temporary until Forge has a working structure injection API works

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
