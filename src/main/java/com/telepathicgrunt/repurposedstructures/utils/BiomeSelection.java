package com.telepathicgrunt.repurposedstructures.utils;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BiomeSelection {

    private static Set<RegistryKey<Biome>> OVERWORLD_BIOMES = new HashSet<>();

    public static void setupOverworldBiomesSet() {
        // Get all vanilla and modded biomes with overworld tag
        OVERWORLD_BIOMES.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.OVERWORLD));

        // Get all modded overworld biomes that doesn't have overworld tag
        OVERWORLD_BIOMES.addAll(BiomeManager.getAdditionalOverworldBiomes());
    }

    public static boolean isOverworldBiome(BiomeLoadingEvent event) {
        RegistryKey<Biome> biomeKey = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        return OVERWORLD_BIOMES.contains(biomeKey);
    }

    public static boolean hasName(BiomeLoadingEvent context, String... names) {
        return Arrays.stream(names).anyMatch(name -> context.getName().getPath().contains(name));
    }

    public static boolean hasNamespace(BiomeLoadingEvent context, String... namespace) {
        return Arrays.stream(namespace).anyMatch(name -> context.getName().getNamespace().contains(name));
    }

    @SafeVarargs
    public static boolean isBiome(BiomeLoadingEvent context, RegistryKey<Biome>... keys) {
        return Arrays.stream(keys).anyMatch(key -> context.getName().equals(key.location()));
    }

    public static boolean haveCategories(BiomeLoadingEvent context, Biome.Category... categories) {
        Set<Biome.Category> categorySet = new HashSet<>(Arrays.asList(categories));
        return categorySet.contains(context.getCategory());
    }

    public static boolean hasStructure(BiomeLoadingEvent context, Structure<?> structureFeature) {
        return context.getGeneration().getStructures().stream().anyMatch(structureFeatureSupplier -> structureFeatureSupplier.get().feature.equals(structureFeature));
    }

    public static boolean hasStructureType(BiomeLoadingEvent context, RSStructureTagMap.STRUCTURE_TAGS tag) {
        return RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(tag).stream().anyMatch(structure -> context.getGeneration().getStructures().stream().anyMatch(structureFeatureSupplier -> structureFeatureSupplier.get().feature.equals(structure)));
    }

    public static boolean doesNotHaveStructureType(BiomeLoadingEvent context, RSStructureTagMap.STRUCTURE_TAGS tag) {
        return RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(tag).stream().noneMatch(structure -> context.getGeneration().getStructures().stream().anyMatch(structureFeatureSupplier -> structureFeatureSupplier.get().feature.equals(structure)));
    }
}
