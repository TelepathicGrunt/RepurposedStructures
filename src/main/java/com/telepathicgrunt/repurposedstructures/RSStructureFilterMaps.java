package com.telepathicgrunt.repurposedstructures;

import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

public class RSStructureFilterMaps {


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MINESHAFTS //

    public static final Map<ConfiguredStructureFeature<?, ?>, BiPredicate<Identifier, Biome>> MINESHAFT_TYPE_AND_CONDITIONS = new HashMap<>();
    static {
        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.BIRCH_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.birchMineshaftSpawnrate != 0 &&
                biomeIDIn.getPath().contains("birch")));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.JUNGLE_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.jungleMineshaftSpawnrate != 0 &&
                biomeIn.getCategory() == Category.JUNGLE));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.DESERT_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.desertMineshaftSpawnrate != 0 &&
                biomeIn.getCategory() == Category.DESERT));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.STONE_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.stoneMineshaftSpawnrate != 0 &&
                biomeIn.getCategory() == Category.EXTREME_HILLS));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.SAVANNA_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.savannaMineshaftSpawnrate != 0 &&
                biomeIn.getCategory() == Category.SAVANNA));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.ICY_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.icyMineshaftSpawnrate != 0 &&
                (biomeIn.getCategory() == Category.ICY || biomeIDIn.getPath().contains("snowy"))));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.OCEAN_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.oceanMineshaftSpawnrate != 0 &&
                biomeIn.getCategory() == Category.OCEAN));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.TAIGA_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.taigaMineshaftSpawnrate != 0 &&
                biomeIn.getCategory() == Category.TAIGA));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.SWAMP_OR_DARK_FOREST_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.swampAndDarkForestMineshaftSpawnrate != 0 &&
                (biomeIn.getCategory() == Category.SWAMP || biomeIDIn.getPath().contains("dark_forest") || biomeIDIn.getPath().contains("dark_oak"))));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.END_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.endMineshaftSpawnrate != 0 &&
                biomeIn.getCategory() == Category.THEEND && !biomeIDIn.equals(new Identifier("minecraft:the_end")) &&
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.misc.barrensIslandsEndMineshafts ||
                (!biomeIDIn.equals(new Identifier("minecraft:end_barrens")) && !biomeIDIn.equals(new Identifier("minecraft:small_end_islands"))))));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.NETHER_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.netherMineshaftSpawnrate != 0 && biomeIn.getCategory() == Category.NETHER));
    }
}
