package com.telepathicgrunt.repurposedstructures.utils;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.*;
import java.util.function.Predicate;

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

    public static boolean isBiomeAllowed(BiomeSelectionContext context, String structureType) {
        return RepurposedStructures.ALL_BIOME_BLACKLISTS.get(structureType).stream().noneMatch(blacklistedBiome -> blacklistedBiome.equals(context.getBiomeKey().getValue().toString()));
    }
}
