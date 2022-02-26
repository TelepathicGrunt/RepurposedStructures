package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class BiomeDimensionAllowDisallow {
    private BiomeDimensionAllowDisallow() {}

    public static final Map<ResourceLocation, List<Pattern>> DIMENSION_DISALLOW = new HashMap<>();
    public static final Map<ResourceLocation, List<Pattern>> DIMENSION_ALLOW = new HashMap<>();
    public static final Map<ResourceLocation, List<Pattern>> BIOME_ALLOW = new HashMap<>();
    public static final Map<ResourceLocation, List<Pattern>> BIOME_DISALLOW = new HashMap<>();

    public static void setupAllowDisallowMaps() {
        // init maps with all structures/some configuredfeatures and blank lists
        RSConfiguredFeatures.RS_DUNGEONS.forEach((placedFeature) -> {
            ResourceLocation id = BuiltinRegistries.PLACED_FEATURE.getKey(placedFeature);
            DIMENSION_DISALLOW.put(id, new ArrayList<>());
            DIMENSION_ALLOW.put(id, new ArrayList<>());
            BIOME_ALLOW.put(id, new ArrayList<>());
            BIOME_DISALLOW.put(id, new ArrayList<>());
        });
        RSConfiguredFeatures.RS_WELLS.forEach((placedFeature) -> {
            ResourceLocation id = BuiltinRegistries.PLACED_FEATURE.getKey(placedFeature);
            DIMENSION_DISALLOW.put(id, new ArrayList<>());
            DIMENSION_ALLOW.put(id, new ArrayList<>());
            BIOME_ALLOW.put(id, new ArrayList<>());
            BIOME_DISALLOW.put(id, new ArrayList<>());
        });

        // Parse and add all the allow/disallows
        setupMap(DIMENSION_DISALLOW, RepurposedStructures.omegaBiomeDimConfig.disallowedFeatureDimensions, "dimension disallow");
        setupMap(DIMENSION_ALLOW, RepurposedStructures.omegaBiomeDimConfig.allowedFeatureDimensions, "dimension allow");
        setupMap(BIOME_DISALLOW, RepurposedStructures.omegaBiomeDimConfig.disallowedFeatureBiomes, "biome disallow");
        setupMap(BIOME_ALLOW, RepurposedStructures.omegaBiomeDimConfig.allowedFeatureBiomes, "biome allow");
    }

    private static void setupMap(Map<ResourceLocation, List<Pattern>> mapToFillWithPatterns, Map<String, String> configMap, String errorMsg) {
        for(Map.Entry<String, String> configMapEntry : configMap.entrySet()) {
            List<String> parsedValues = Arrays.stream(configMapEntry.getValue().split(",")).map(String::trim).collect(Collectors.toList());
            ResourceLocation worldgenObjectID = new ResourceLocation(configMapEntry.getKey());

            // If the key is "all", take the value patterns and give it to all of the entries in the map
            if(configMapEntry.getKey().equals("all")) {
                mapToFillWithPatterns.values().forEach(patternList -> parsedValues.forEach(pattern -> patternList.add(Pattern.compile(pattern))));
            }
            // Add the patterns to the key ID of the worldgen thing.
            else if(mapToFillWithPatterns.containsKey(worldgenObjectID)) {
                parsedValues.forEach(patternList -> mapToFillWithPatterns.get(worldgenObjectID).add(Pattern.compile(patternList)));
            }
            // Error msg for unknown keys (typo'ed structure registry names etc)
            else{
                RepurposedStructures.LOGGER.warn("Repurposed Structures: Unknown key {} was found in the {} config. Skipping that entry...", worldgenObjectID, errorMsg);
            }
        }
    }
}
