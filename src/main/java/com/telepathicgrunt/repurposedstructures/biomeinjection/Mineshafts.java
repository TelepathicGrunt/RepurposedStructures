package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class Mineshafts {

    public static void addMineshafts(BiomeLoadingEvent event) {
        // Testing out this short circuit idea I had.
        // Takes a condition and tries to add the mineshaft.
        // If fails, move on to next Mineshaft type. If it succeeds, stop and exit method.
        for(Map.Entry<StructureFeature<?, ?>, Predicate<BiomeLoadingEvent>> mineshaftTypeAndCondition : MINESHAFT_TYPE_AND_CONDITIONS.entrySet()){
            if(attemptToAddMineshaft(event, mineshaftTypeAndCondition.getKey(), mineshaftTypeAndCondition.getValue())){
                break;
            }
        }
    }

    // helper stuff to allow a cleaner way of passing in mineshaft types
    // and their conditions to see if they should be added to the biome
    private static boolean attemptToAddMineshaft(BiomeLoadingEvent event, StructureFeature<?,?> configuredStructureFeature, Predicate<BiomeLoadingEvent> predicate){
        if (predicate.test(event) &&
            (BiomeSelection.hasNamespace(event, "minecraft") ||
            RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get()))
        {
            // replace vanilla mineshaft with our own (only removes vanilla mineshaft if it exists)
            // Do not remove when yungs is on. They need the mineshaft to replace it.
            if (RepurposedStructures.yungsBetterMineshaftIsNotOn){
                event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
            }
            event.getGeneration().getStructures().add(() -> configuredStructureFeature);
            return true; // Do not proceed to next mineshaft type.
        }
        return false; // continue to next mineshaft type
    }


    // Was originally going to make all structures use maps like below but then Fabric came out with Biome Modification API
    // which is incompatible with maps like below. So now Mineshafts are stuck using this legacy map.
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
                        !BiomeSelection.hasName(event, "crimson", "_red", "warped", "blue") &&
                        BiomeSelection.haveCategories(event, Category.NETHER));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.CRIMSON_MINESHAFT, (event) ->
                RepurposedStructures.RSMineshaftsConfig.crimsonMineshaftSpawnrate.get() != 0 &&
                        BiomeSelection.hasName(event, "crimson", "_red") &&
                        BiomeSelection.haveCategories(event, Category.NETHER));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.WARPED_MINESHAFT, (event) ->
                RepurposedStructures.RSMineshaftsConfig.warpedMineshaftSpawnrate.get() != 0 &&
                        BiomeSelection.hasName(event, "warped", "blue") &&
                        BiomeSelection.haveCategories(event, Category.NETHER));
    }
}
