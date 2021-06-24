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
                (context) -> BiomeSelection.hasName(context, "birch")
                        && BiomeSelection.isBiomeAllowed(context, RSStructures.MANSION_BIRCH)
                        && RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionBirchMaxChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MANSION_BIRCH));

        GeneralUtils.addToBiome("mansion_jungle",
                (context) -> BiomeSelection.haveCategories(context, Category.JUNGLE)
                        && BiomeSelection.isBiomeAllowed(context, RSStructures.MANSION_JUNGLE)
                        && RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionJungleMaxChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MANSION_JUNGLE));

        GeneralUtils.addToBiome("mansion_oak",
                (context) -> BiomeSelection.haveCategories(context, Category.FOREST)
                        && !(BiomeSelection.hasName(context, "birch", "dark", "spooky", "dead", "haunted"))
                        && BiomeSelection.isBiomeAllowed(context, RSStructures.MANSION_OAK)
                        && RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionOakMaxChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MANSION_OAK));

        GeneralUtils.addToBiome("mansion_savanna",
                (context) -> BiomeSelection.haveCategories(context, Category.SAVANNA)
                        && !BiomeSelection.isBiome(context, BiomeKeys.SAVANNA_PLATEAU)
                        && BiomeSelection.isBiomeAllowed(context, RSStructures.MANSION_SAVANNA)
                        && RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionSavannaMaxChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MANSION_SAVANNA));

        GeneralUtils.addToBiome("mansion_taiga",
                (context) -> BiomeSelection.haveCategories(context, Category.TAIGA) && context.getBiome().getPrecipitation() != Biome.Precipitation.SNOW
                        && !BiomeSelection.hasName(context, "giant", "redwood", "snow", "ice", "icy", "glacier", "frozen")
                        && BiomeSelection.isBiomeAllowed(context, RSStructures.MANSION_TAIGA)
                        && RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionTaigaMaxChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MANSION_TAIGA));

        GeneralUtils.addToBiome("mansion_desert",
                (context) -> BiomeSelection.haveCategories(context, Category.DESERT)
                        && BiomeSelection.isBiomeAllowed(context, RSStructures.MANSION_DESERT)
                        && RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionDesertMaxChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MANSION_DESERT));

        GeneralUtils.addToBiome("mansion_snowy",
                (context) -> (BiomeSelection.haveCategories(context, Category.ICY) || (BiomeSelection.haveCategories(context, Category.TAIGA) && context.getBiome().getPrecipitation() == Biome.Precipitation.SNOW))
                        && BiomeSelection.isBiomeAllowed(context, RSStructures.MANSION_SNOWY)
                        && RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionSnowyMaxChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MANSION_SNOWY));
    }
}
