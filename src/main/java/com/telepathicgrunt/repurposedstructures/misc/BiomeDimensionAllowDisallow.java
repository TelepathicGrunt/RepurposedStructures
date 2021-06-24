package com.telepathicgrunt.repurposedstructures.misc;

import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class BiomeDimensionAllowDisallow {
    public static final Map<Identifier, List<Pattern>> DIMENSION_DISALLOW = new HashMap<>();
    public static final Map<Identifier, List<Pattern>> DIMENSION_ALLOW = new HashMap<>();
    public static final Map<Identifier, List<Pattern>> BIOME_ALLOW = new HashMap<>();
    public static final Map<Identifier, List<Pattern>> BIOME_DISALLOW = new HashMap<>();

    public static void setupAllowDisallowMaps(){

    }

}
