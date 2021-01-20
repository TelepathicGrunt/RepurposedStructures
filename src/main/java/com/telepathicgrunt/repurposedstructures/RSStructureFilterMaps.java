package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class RSStructureFilterMaps {
    // Was originally going to make all structures use maps like below but then Fabric came out with Biome Modification API
    // which is incompatible with maps like below. So now Mineshafts are stuck using this legacy map.

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MINESHAFTS //

    public static final Map<StructureFeature<?, ?>, Predicate<BiomeLoadingEvent>> MINESHAFT_TYPE_AND_CONDITIONS = new HashMap<>();
    static {
        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.BIRCH_MINESHAFT, (event) ->
                RepurposedStructures.RSMineshaftsConfig.birchMineshaftSpawnrate.get() != 0 &&
                BiomeSelection.hasName(event, "birch"));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.JUNGLE_MINESHAFT, (event) ->
                RepurposedStructures.RSMineshaftsConfig.jungleMineshaftSpawnrate.get() != 0 &&
                BiomeSelection.haveCategories(event, Category.JUNGLE));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.DESERT_MINESHAFT, (event) ->
                RepurposedStructures.RSMineshaftsConfig.desertMineshaftSpawnrate.get() != 0 &&
                BiomeSelection.haveCategories(event, Category.DESERT));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.STONE_MINESHAFT, (event) ->
                RepurposedStructures.RSMineshaftsConfig.stoneMineshaftSpawnrate.get() != 0 &&
                BiomeSelection.haveCategories(event, Category.EXTREME_HILLS));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.SAVANNA_MINESHAFT, (event) ->
                RepurposedStructures.RSMineshaftsConfig.savannaMineshaftSpawnrate.get() != 0 &&
                BiomeSelection.haveCategories(event, Category.SAVANNA));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.ICY_MINESHAFT, (event) ->
                RepurposedStructures.RSMineshaftsConfig.icyMineshaftSpawnrate.get() != 0 &&
                (BiomeSelection.haveCategories(event, Category.ICY) || BiomeSelection.hasName(event, "snowy")));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.OCEAN_MINESHAFT, (event) ->
                RepurposedStructures.RSMineshaftsConfig.oceanMineshaftSpawnrate.get() != 0 &&
                BiomeSelection.haveCategories(event, Category.OCEAN));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.TAIGA_MINESHAFT, (event) ->
                RepurposedStructures.RSMineshaftsConfig.taigaMineshaftSpawnrate.get() != 0 &&
                BiomeSelection.haveCategories(event, Category.TAIGA));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.SWAMP_OR_DARK_FOREST_MINESHAFT, (event) ->
                RepurposedStructures.RSMineshaftsConfig.swampAndDarkForestMineshaftSpawnrate.get() != 0 &&
                (BiomeSelection.haveCategories(event, Category.SWAMP) ||
                    (BiomeSelection.haveCategories(event, Category.FOREST) &&
                    BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted"))));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.END_MINESHAFT, (event) ->
                RepurposedStructures.RSMineshaftsConfig.endMineshaftSpawnrate.get() != 0 &&
                BiomeSelection.haveCategories(event, Category.THEEND) &&
                !BiomeSelection.isBiome(event, Biomes.THE_END) &&
                (RepurposedStructures.RSMineshaftsConfig.barrensIslandsEndMineshafts.get() ||
                    !BiomeSelection.isBiome(event, Biomes.END_BARRENS, Biomes.SMALL_END_ISLANDS)));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.NETHER_MINESHAFT, (event) ->
                RepurposedStructures.RSMineshaftsConfig.netherMineshaftSpawnrate.get() != 0 &&
                BiomeSelection.haveCategories(event, Category.NETHER));
    }
}
