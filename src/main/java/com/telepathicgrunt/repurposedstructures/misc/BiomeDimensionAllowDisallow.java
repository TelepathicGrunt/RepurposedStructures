package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BiomeDimensionAllowDisallow {
    public static final Map<Identifier, List<Pattern>> DIMENSION_DISALLOW = new HashMap<>();
    public static final Map<Identifier, List<Pattern>> DIMENSION_ALLOW = new HashMap<>();
    public static final Map<Identifier, List<Pattern>> BIOME_ALLOW = new HashMap<>();
    public static final Map<Identifier, List<Pattern>> BIOME_DISALLOW = new HashMap<>();

    public static void setupAllowDisallowMaps(){
        // init maps with all structures/some configuredfeatures and blank lists
        RSStructures.RS_STRUCTURES.forEach((key, value) -> {
            Identifier id = Registry.STRUCTURE_FEATURE.getId(key);
            DIMENSION_DISALLOW.put(id, new ArrayList<>());
            DIMENSION_ALLOW.put(id, new ArrayList<>());
            BIOME_ALLOW.put(id, new ArrayList<>());
            BIOME_DISALLOW.put(id, new ArrayList<>());
        });
        RSConfiguredFeatures.RS_DUNGEONS.forEach((configuredFeature) -> {
            Identifier id = BuiltinRegistries.CONFIGURED_FEATURE.getId(configuredFeature);
            DIMENSION_DISALLOW.put(id, new ArrayList<>());
            DIMENSION_ALLOW.put(id, new ArrayList<>());
            BIOME_ALLOW.put(id, new ArrayList<>());
            BIOME_DISALLOW.put(id, new ArrayList<>());
        });
        RSConfiguredFeatures.RS_WELLS.forEach((configuredFeature) -> {
            Identifier id = BuiltinRegistries.CONFIGURED_FEATURE.getId(configuredFeature);
            DIMENSION_DISALLOW.put(id, new ArrayList<>());
            DIMENSION_ALLOW.put(id, new ArrayList<>());
            BIOME_ALLOW.put(id, new ArrayList<>());
            BIOME_DISALLOW.put(id, new ArrayList<>());
        });

        // Parse and add all the allow/disallows
        setupMap(DIMENSION_DISALLOW, RepurposedStructures.RSAllConfig.RSAllowDisallowConfig.disallowedDimensions, "dimension disallow");
        setupMap(DIMENSION_ALLOW, RepurposedStructures.RSAllConfig.RSAllowDisallowConfig.allowedDimensions, "dimension allow");
        setupMap(BIOME_DISALLOW, RepurposedStructures.RSAllConfig.RSAllowDisallowConfig.disallowedBiomes, "biome disallow");
        setupMap(BIOME_ALLOW, RepurposedStructures.RSAllConfig.RSAllowDisallowConfig.allowedBiomes, "biome allow");
    }

    private static void setupMap(Map<Identifier, List<Pattern>> map, Map<String, String> config, String errorMsg) {
        for(Map.Entry<String, String> dimDisEntry : config.entrySet()){
            List<String> parsedValues = Arrays.stream(dimDisEntry.getValue().split(",")).map(String::trim).collect(Collectors.toList());
            Identifier id = new Identifier(dimDisEntry.getKey());
            if(dimDisEntry.getKey().equals("all")){
                map.values().forEach(list -> parsedValues.forEach(dim -> list.add(Pattern.compile(dim))));
            }
            else if(map.containsKey(id)){
                parsedValues.forEach(dim -> map.get(id).add(Pattern.compile(dim)));
            }
            else{
                RepurposedStructures.LOGGER.warn("Unknown key {} was found in the {} config. Skipping that entry...", id, errorMsg);
            }
        }
    }
}
