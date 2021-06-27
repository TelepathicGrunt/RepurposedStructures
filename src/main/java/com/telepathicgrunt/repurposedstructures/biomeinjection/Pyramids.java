package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BiomeKeys;

public class Pyramids {

    public static void addPyramids() {

        GeneralUtils.addToBiome("nether_pyramid",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.NETHER_PYRAMID,
                                () ->BiomeSelection.haveCategories(context, Category.NETHER))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidNetherAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_PYRAMID));

        GeneralUtils.addToBiome("badlands_pyramid",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.BADLANDS_PYRAMID,
                                () -> BiomeSelection.haveCategories(context, Category.MESA))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidBadlandsAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.BADLANDS_PYRAMID));

        GeneralUtils.addToBiome("pyramid_snowy",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.PYRAMID_SNOWY,
                                () -> ((BiomeSelection.haveCategories(context, Category.ICY)
                                    && !(BiomeSelection.hasName(context, "icy", "ice", "frozen") // inverted icy check
                                    || (context.getBiome().getTemperature() < 0 && !BiomeSelection.hasName(context, "snow"))))
                                || (BiomeSelection.haveCategories(context, Category.TAIGA)
                                    && context.getBiome().getPrecipitation() == Biome.Precipitation.SNOW)))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidSnowyAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_SNOWY));

        GeneralUtils.addToBiome("pyramid_end",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.PYRAMID_END,
                                () -> BiomeSelection.haveCategories(context, Category.THEEND)
                                && !BiomeSelection.isBiome(context, BiomeKeys.THE_END, BiomeKeys.SMALL_END_ISLANDS, BiomeKeys.END_BARRENS))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidEndAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_END));

        GeneralUtils.addToBiome("pyramid_icy",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.PYRAMID_ICY,
                                () -> BiomeSelection.haveCategories(context, Category.ICY)
                                && (BiomeSelection.hasName(context, "icy", "ice", "frozen")
                                    || (context.getBiome().getTemperature() < 0 && !BiomeSelection.hasName(context, "snow"))))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidIcyAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_ICY));

        GeneralUtils.addToBiome("pyramid_jungle",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.PYRAMID_JUNGLE,
                                () -> BiomeSelection.haveCategories(context, Category.JUNGLE))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidJungleAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_JUNGLE));

        GeneralUtils.addToBiome("pyramid_mushroom",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.PYRAMID_MUSHROOM,
                                () -> BiomeSelection.haveCategories(context, Category.MUSHROOM))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidMushroomAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_MUSHROOM));

        GeneralUtils.addToBiome("pyramid_ocean",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.PYRAMID_OCEAN,
                                () -> BiomeSelection.haveCategories(context, Category.OCEAN)
                                && !BiomeSelection.isBiome(context, BiomeKeys.MUSHROOM_FIELD_SHORE))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidOceanAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_OCEAN));

        GeneralUtils.addToBiome("pyramid_giant_tree_taiga",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.PYRAMID_GIANT_TREE_TAIGA,
                                () -> BiomeSelection.haveCategories(context, Category.TAIGA)
                                && BiomeSelection.hasName(context, "giant", "redwood"))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidGiantTreeTaigaAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_GIANT_TREE_TAIGA));

        GeneralUtils.addToBiome("pyramid_flower_forest",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.PYRAMID_FLOWER_FOREST,
                                () -> BiomeSelection.haveCategories(context, Category.PLAINS, Category.FOREST)
                                && !BiomeSelection.isBiome(context, BiomeKeys.SUNFLOWER_PLAINS)
                                && BiomeSelection.hasName(context, "flower", "blossom"))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidFlowerForestAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_FLOWER_FOREST));
    }
}
