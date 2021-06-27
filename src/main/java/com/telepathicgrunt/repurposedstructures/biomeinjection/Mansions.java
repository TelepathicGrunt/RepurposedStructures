package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BiomeKeys;

public class Mansions {

    public static void addMansions() {
        GeneralUtils.addToBiome("mansion_birch",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.MANSION_BIRCH,
                                () -> BiomeSelection.hasName(context, "birch"))
                        && RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionBirchAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MANSION_BIRCH));

        GeneralUtils.addToBiome("mansion_jungle",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.MANSION_JUNGLE,
                                () -> BiomeSelection.haveCategories(context, Category.JUNGLE))
                        && RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionJungleAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MANSION_JUNGLE));

        GeneralUtils.addToBiome("mansion_oak",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.MANSION_OAK,
                                () -> BiomeSelection.haveCategories(context, Category.FOREST)
                                && !(BiomeSelection.hasName(context, "birch", "dark", "spooky", "dead", "haunted")))
                        && RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionOakAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MANSION_OAK));

        GeneralUtils.addToBiome("mansion_savanna",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.MANSION_SAVANNA,
                                () -> BiomeSelection.haveCategories(context, Category.SAVANNA)
                                && !BiomeSelection.isBiome(context, BiomeKeys.SAVANNA_PLATEAU))
                        && RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionSavannaAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MANSION_SAVANNA));

        GeneralUtils.addToBiome("mansion_taiga",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.MANSION_TAIGA,
                                () -> BiomeSelection.haveCategories(context, Category.TAIGA) && context.getBiome().getPrecipitation() != Biome.Precipitation.SNOW
                                && !BiomeSelection.hasName(context, "giant", "redwood", "snow", "ice", "icy", "glacier", "frozen"))
                        && RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionTaigaAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MANSION_TAIGA));

        GeneralUtils.addToBiome("mansion_desert",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.MANSION_DESERT,
                                () -> BiomeSelection.haveCategories(context, Category.DESERT))
                        && RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionDesertAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MANSION_DESERT));

        GeneralUtils.addToBiome("mansion_snowy",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.MANSION_SNOWY,
                                () -> (BiomeSelection.haveCategories(context, Category.ICY)
                                || (BiomeSelection.haveCategories(context, Category.TAIGA) && context.getBiome().getPrecipitation() == Biome.Precipitation.SNOW)))
                        && RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionSnowyAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MANSION_SNOWY));
    }
}
