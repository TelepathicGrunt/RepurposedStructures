package com.telepathicgrunt.repurposedstructures.utils;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.VanillaLayeredBiomeSourceAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BiomeSelection {

    private static Set<RegistryKey<Biome>> OVERWORLD_BIOMES = new HashSet<>();

    public static void setupOverworldBiomesSet() {
        // Get all vanilla and modded biomes added to the Biomes list in the overworld biome source
        // Fabric API adds to this list and so should all other mods.
        OVERWORLD_BIOMES.addAll(VanillaLayeredBiomeSourceAccessor.rs_getBIOMES());

        // Add the two missing vanilla biomes from the overworld biome source.
        OVERWORLD_BIOMES.add(BiomeKeys.BAMBOO_JUNGLE);
        OVERWORLD_BIOMES.add(BiomeKeys.BAMBOO_JUNGLE_HILLS);
    }

    public static boolean isOverworldBiome(BiomeSelectionContext context) {
        if(OVERWORLD_BIOMES.isEmpty()) {
            setupOverworldBiomesSet();
        }
        return OVERWORLD_BIOMES.contains(context.getBiomeKey());
    }

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

    public static boolean isBiomeAllowed(BiomeSelectionContext context, String structureType) {
        return RepurposedStructures.ALL_BIOME_BLACKLISTS.get(structureType).stream().noneMatch(blacklistedBiome -> blacklistedBiome.equals(context.getBiomeKey().getValue().toString()));
    }
}
