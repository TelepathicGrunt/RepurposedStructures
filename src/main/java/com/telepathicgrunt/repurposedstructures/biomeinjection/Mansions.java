package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Mansions {
    private Mansions() {}

    public static void addMansions() {

        BiomeInjection.addStructure(RSConfiguredStructures.MANSION_BIRCH, (event) ->
            (RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionBirchAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.MANSION_BIRCH,
            () -> BiomeSelection.hasName(event, "birch")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.MANSION_JUNGLE, (event) ->
            (RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionJungleAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.MANSION_JUNGLE,
            () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.JUNGLE)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.MANSION_OAK, (event) ->
            (RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionOakAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.MANSION_OAK,
            () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.FOREST) &&
            !BiomeSelection.hasName(event, "birch", "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.MANSION_SAVANNA, (event) ->
            (RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionSavannaAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.MANSION_SAVANNA,
            () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.SAVANNA) &&
            !BiomeSelection.isBiome(event, Biomes.SAVANNA_PLATEAU)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.MANSION_TAIGA, (event) ->
            (RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionTaigaAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.MANSION_TAIGA,
            () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.TAIGA) && event.getBiome().getPrecipitation() != Biome.Precipitation.SNOW &&
            !BiomeSelection.hasName(event, "giant", "redwood", "snow", "ice", "icy", "glacier", "glacial", "frozen")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.MANSION_DESERT, (event) ->
            (RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionDesertAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.MANSION_DESERT,
            () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.DESERT)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.MANSION_SNOWY, (event) ->
            (RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionSnowyAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.MANSION_SNOWY,
            () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.ICY) ||
            (BiomeSelection.haveCategories(event, Biome.BiomeCategory.TAIGA) && event.getBiome().getPrecipitation() == Biome.Precipitation.SNOW)))
        );
    }
}
