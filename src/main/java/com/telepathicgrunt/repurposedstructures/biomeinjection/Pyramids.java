package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BiomeKeys;

public class Pyramids {

    public static void addPyramids() {

        GeneralUtils.addToBiome("nether_pyramid",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.netherPyramidMaxChunkDistance != 1001
                        && BiomeSelection.isBiomeAllowed(context, "temples")),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_PYRAMID));

        GeneralUtils.addToBiome("badlands_pyramid",
                (context) -> BiomeSelection.haveCategories(context, Category.MESA)
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.badlandsPyramidMaxChunkDistance != 1001
                        && BiomeSelection.isBiomeAllowed(context, "temples")),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.BADLANDS_PYRAMID));

        GeneralUtils.addToBiome("pyramid_snowy",
                (context) -> ((BiomeSelection.haveCategories(context, Category.ICY)
                            && !(BiomeSelection.hasName(context, "icy", "ice", "frozen") // inverted icy check
                            || (context.getBiome().getTemperature() < 0 && !BiomeSelection.hasName(context, "snow"))))
                        || (BiomeSelection.haveCategories(context, Category.TAIGA)
                            && context.getBiome().getPrecipitation() == Biome.Precipitation.SNOW))
                        && BiomeSelection.isBiomeAllowed(context, "pyramids")
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfignowyMaxChunkDistance != 1001),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_SNOWY));

        GeneralUtils.addToBiome("pyramid_end",
                (context) -> (BiomeSelection.hasNamespace(context, "minecraft") ||
                        RepurposedStructures.RSAllConfig.RSPyramidsConfig.addPyramidEndToModdedBiomes)
                        && BiomeSelection.haveCategories(context, Category.THEEND)
                        && !BiomeSelection.isBiome(context, BiomeKeys.THE_END, BiomeKeys.SMALL_END_ISLANDS, BiomeKeys.END_BARRENS)
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidEndMaxChunkDistance != 1001
                        && BiomeSelection.isBiomeAllowed(context, "shipwrecks"),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_END));

        GeneralUtils.addToBiome("pyramid_icy",
                (context) -> BiomeSelection.haveCategories(context, Category.ICY)
                        && (BiomeSelection.hasName(context, "icy", "ice", "frozen")
                            || (context.getBiome().getTemperature() < 0 && !BiomeSelection.hasName(context, "snow")))
						&& BiomeSelection.isBiomeAllowed(context, "pyramids")
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidIcyMaxChunkDistance != 1001),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_ICY));

        GeneralUtils.addToBiome("pyramid_jungle",
                (context) -> BiomeSelection.haveCategories(context, Category.JUNGLE)
						&& BiomeSelection.isBiomeAllowed(context, "pyramids")
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidJungleMaxChunkDistance != 1001),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_JUNGLE));

        GeneralUtils.addToBiome("pyramid_mushroom",
                (context) -> BiomeSelection.haveCategories(context, Category.MUSHROOM)
						&& BiomeSelection.isBiomeAllowed(context, "pyramids")
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidMushroomMaxChunkDistance != 1001),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_MUSHROOM));

        GeneralUtils.addToBiome("pyramid_ocean",
                (context) -> BiomeSelection.haveCategories(context, Category.OCEAN)
                        && !BiomeSelection.isBiome(context, BiomeKeys.MUSHROOM_FIELD_SHORE)
						&& BiomeSelection.isBiomeAllowed(context, "pyramids")
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidOceanMaxChunkDistance != 1001),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_OCEAN));

        GeneralUtils.addToBiome("pyramid_giant_tree_taiga",
                (context) -> BiomeSelection.haveCategories(context, Category.TAIGA)
                        && BiomeSelection.hasName(context, "giant", "redwood")
						&& BiomeSelection.isBiomeAllowed(context, "pyramids")
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidGiantTreeTaigaMaxChunkDistance != 1001),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_GIANT_TREE_TAIGA));

        GeneralUtils.addToBiome("pyramid_flower_forest",
                (context) -> (BiomeSelection.haveCategories(context, Category.PLAINS, Category.FOREST))
                        && !BiomeSelection.isBiome(context, BiomeKeys.SUNFLOWER_PLAINS)
                        && BiomeSelection.hasName(context, "flower", "blossom")
						&& BiomeSelection.isBiomeAllowed(context, "pyramids")
                        && RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidFlowerForestMaxChunkDistance != 1001),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_FLOWER_FOREST));
    }
}
