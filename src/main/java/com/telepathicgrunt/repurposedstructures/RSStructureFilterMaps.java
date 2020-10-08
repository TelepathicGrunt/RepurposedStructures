package com.telepathicgrunt.repurposedstructures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class RSStructureFilterMaps {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MINESHAFTS //

    public static final Map<StructureFeature<?, ?>, Predicate<BiomeLoadingEvent>> MINESHAFT_TYPE_AND_CONDITIONS = new HashMap<>();
    static {
        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.BIRCH_MINESHAFT, (event) ->
                (RepurposedStructures.RSMineshaftsConfig.birchMineshaftSpawnrate.get() != 0 &&
                        event.getName().getPath().contains("birch")));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.JUNGLE_MINESHAFT, (event) ->
                (RepurposedStructures.RSMineshaftsConfig.jungleMineshaftSpawnrate.get() != 0 &&
                        event.getCategory() == Category.JUNGLE));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.DESERT_MINESHAFT, (event) ->
                (RepurposedStructures.RSMineshaftsConfig.desertMineshaftSpawnrate.get() != 0 &&
                        event.getCategory() == Category.DESERT));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.STONE_MINESHAFT, (event) ->
                (RepurposedStructures.RSMineshaftsConfig.stoneMineshaftSpawnrate.get() != 0 &&
                        event.getCategory() == Category.EXTREME_HILLS));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.SAVANNA_MINESHAFT, (event) ->
                (RepurposedStructures.RSMineshaftsConfig.savannaMineshaftSpawnrate.get() != 0 &&
                        event.getCategory() == Category.SAVANNA));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.ICY_MINESHAFT, (event) ->
                (RepurposedStructures.RSMineshaftsConfig.icyMineshaftSpawnrate.get() != 0 &&
                        (event.getCategory() == Category.ICY || event.getName().getPath().contains("snowy"))));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.OCEAN_MINESHAFT, (event) ->
                (RepurposedStructures.RSMineshaftsConfig.oceanMineshaftSpawnrate.get() != 0 &&
                        event.getCategory() == Category.OCEAN));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.TAIGA_MINESHAFT, (event) ->
                (RepurposedStructures.RSMineshaftsConfig.taigaMineshaftSpawnrate.get() != 0 &&
                        event.getCategory() == Category.TAIGA));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.SWAMP_OR_DARK_FOREST_MINESHAFT, (event) ->
                (RepurposedStructures.RSMineshaftsConfig.swampAndDarkForestMineshaftSpawnrate.get() != 0 &&
                        (event.getCategory() == Category.SWAMP || event.getName().getPath().contains("dark_forest") || event.getName().getPath().contains("dark_oak"))));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.END_MINESHAFT, (event) ->
                (RepurposedStructures.RSMineshaftsConfig.endMineshaftSpawnrate.get() != 0 &&
                        event.getCategory() == Category.THEEND && !event.getName().equals(new ResourceLocation("minecraft:the_end")) &&
                        (RepurposedStructures.RSMineshaftsConfig.barrensIslandsEndMineshafts.get() ||
                                (!event.getName().equals(new ResourceLocation("minecraft:end_barrens")) && !event.getName().equals(new ResourceLocation("minecraft:small_end_islands"))))));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.NETHER_MINESHAFT, (event) ->
                (RepurposedStructures.RSMineshaftsConfig.netherMineshaftSpawnrate.get() != 0 && event.getCategory() == Category.NETHER));
    }
}
