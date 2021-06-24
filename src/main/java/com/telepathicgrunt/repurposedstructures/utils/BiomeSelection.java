package com.telepathicgrunt.repurposedstructures.utils;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.BiomeDimensionAllowDisallow;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class BiomeSelection {

    public static boolean hasName(BiomeSelectionContext context, String... names){
        return Arrays.stream(names).anyMatch(name -> context.getBiomeKey().getValue().getPath().contains(name));
    }

    public static boolean hasNamespace(BiomeSelectionContext context, String... namespace){
        return Arrays.stream(namespace).anyMatch(name -> context.getBiomeKey().getValue().getNamespace().contains(name));
    }

    @SafeVarargs
    public static boolean isBiome(BiomeSelectionContext context, RegistryKey<Biome>... keys){
        return Arrays.stream(keys).anyMatch(key -> context.getBiomeKey().equals(key));
    }

    public static boolean haveCategories(BiomeSelectionContext context, Biome.Category... categories) {
        Set<Biome.Category> categorySet = new HashSet<>(Arrays.asList(categories));
        return categorySet.contains(context.getBiome().getCategory());
    }

    public static boolean hasStructure(BiomeSelectionContext context, StructureFeature<?> structureFeature){
        return context.getBiome().getGenerationSettings().hasStructureFeature(structureFeature);
    }

    public static boolean hasStructureType(BiomeSelectionContext context, RSStructureTagMap.STRUCTURE_TAGS tag){
        return RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(tag).stream().anyMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure));
    }

    public static boolean doesNotHaveStructureType(BiomeSelectionContext context, RSStructureTagMap.STRUCTURE_TAGS tag){
        return RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(tag).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure));
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
        Identifier registryId = registry.getId(worldgenObject);
        String biomeID = context.getBiomeKey().toString();
        return BiomeDimensionAllowDisallow.BIOME_ALLOW.getOrDefault(registryId, new ArrayList<>()).stream().anyMatch(pattern -> pattern.matcher(biomeID).matches());
    }

    public static <T> boolean isBiomeDisallowed(BiomeSelectionContext context, T worldgenObject, Registry<T> registry) {
        Identifier registryId = registry.getId(worldgenObject);
        String biomeID = context.getBiomeKey().toString();
        return BiomeDimensionAllowDisallow.BIOME_DISALLOW.getOrDefault(registryId, new ArrayList<>()).stream().anyMatch(pattern -> pattern.matcher(biomeID).matches());
    }
}
