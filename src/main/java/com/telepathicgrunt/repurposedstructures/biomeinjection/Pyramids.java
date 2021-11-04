package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biomes;

public final class Pyramids {
    private Pyramids() {}

    public static void addPyramids() {

        GeneralUtils.addToBiome("nether_pyramid",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.PYRAMID_NETHER,
                                () ->BiomeSelection.haveCategories(context, BiomeCategory.NETHER))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidNetherAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_NETHER));

        GeneralUtils.addToBiome("badlands_pyramid",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.PYRAMID_BADLANDS,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.MESA))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidBadlandsAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_BADLANDS));

        GeneralUtils.addToBiome("pyramid_snowy",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.PYRAMID_SNOWY,
                                () -> ((BiomeSelection.haveCategories(context, BiomeCategory.ICY)
                                    && !(BiomeSelection.hasName(context, "icy", "ice", "frozen") // inverted icy check
                                    || (context.getBiome().getBaseTemperature() < 0 && !BiomeSelection.hasName(context, "snow"))))
                                || (BiomeSelection.haveCategories(context, BiomeCategory.TAIGA)
                                    && context.getBiome().getPrecipitation() == Biome.Precipitation.SNOW)))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidSnowyAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_SNOWY));

        GeneralUtils.addToBiome("pyramid_end",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.PYRAMID_END,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.THEEND)
                                && !BiomeSelection.isBiome(context, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidEndAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_END));

        GeneralUtils.addToBiome("pyramid_icy",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.PYRAMID_ICY,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.ICY)
                                && (BiomeSelection.hasName(context, "icy", "ice", "frozen")
                                    || (context.getBiome().getBaseTemperature() < 0 && !BiomeSelection.hasName(context, "snow"))))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidIcyAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_ICY));

        GeneralUtils.addToBiome("pyramid_jungle",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.PYRAMID_JUNGLE,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.JUNGLE))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidJungleAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_JUNGLE));

        GeneralUtils.addToBiome("pyramid_mushroom",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.PYRAMID_MUSHROOM,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.MUSHROOM))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidMushroomAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_MUSHROOM));

        GeneralUtils.addToBiome("pyramid_ocean",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.PYRAMID_OCEAN,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.OCEAN)
                                && !BiomeSelection.isBiome(context, Biomes.MUSHROOM_FIELD_SHORE))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidOceanAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_OCEAN));

        GeneralUtils.addToBiome("pyramid_giant_tree_taiga",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.PYRAMID_GIANT_TREE_TAIGA,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.TAIGA)
                                && BiomeSelection.hasName(context, "giant", "redwood"))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidGiantTreeTaigaAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_GIANT_TREE_TAIGA));

        GeneralUtils.addToBiome("pyramid_flower_forest",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.PYRAMID_FLOWER_FOREST,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.PLAINS, BiomeCategory.FOREST)
                                && !BiomeSelection.isBiome(context, Biomes.SUNFLOWER_PLAINS)
                                && BiomeSelection.hasName(context, "flower", "blossom"))
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidFlowerForestAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_FLOWER_FOREST));
    }
}
