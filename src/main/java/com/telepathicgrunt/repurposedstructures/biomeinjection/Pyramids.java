package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Pyramids {
    private Pyramids() {}

    public static void addPyramids() {

        BiomeInjection.addStructure(RSConfiguredStructures.PYRAMID_NETHER, (event) ->
            (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidNetherAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_NETHER,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.PYRAMID_BADLANDS, (event) ->
            (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidBadlandsAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_BADLANDS,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.MESA)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.PYRAMID_DARK_FOREST, (event) ->
            (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidDarkForestAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_DARK_FOREST,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.FOREST) &&
                    BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.PYRAMID_ICY, (event) ->
            (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidIcyAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_ICY,
                    () -> BiomeSelection.hasName(event, "ice", "icy", "glacier", "glacial", "frozen") &&
                    (BiomeSelection.haveCategories(event, Biome.BiomeCategory.ICY) ||
                    (BiomeSelection.haveCategories(event, Biome.BiomeCategory.MOUNTAIN) && event.getBiome().getBaseTemperature() < 0))))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.PYRAMID_SNOWY, (event) ->
            (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidSnowyAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_SNOWY,
                    () -> BiomeSelection.isBiome(event, Biomes.GROVE) ||
                    (!BiomeSelection.hasName(event, "ice", "icy", "glacier", "glacial", "frozen") &&
                        ((BiomeSelection.haveCategories(event, Biome.BiomeCategory.ICY) && BiomeSelection.hasName(event, "snow")) ||
                        (BiomeSelection.haveCategories(event, Biome.BiomeCategory.MOUNTAIN) && (event.getBiome().getBaseTemperature() < 0 || BiomeSelection.hasName(event, "snow")))))))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.PYRAMID_JUNGLE, (event) ->
            (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidJungleAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_JUNGLE,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.JUNGLE)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.PYRAMID_MUSHROOM, (event) ->
            (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidMushroomAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_MUSHROOM,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.MUSHROOM)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.PYRAMID_OCEAN, (event) ->
            (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidOceanAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_OCEAN,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.OCEAN)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.PYRAMID_GIANT_TREE_TAIGA, (event) ->
            (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidGiantTreeTaigaAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_GIANT_TREE_TAIGA,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.TAIGA) && BiomeSelection.hasName(event, "giant", "redwood", "old_growth")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.PYRAMID_FLOWER_FOREST, (event) ->
            (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidFlowerForestAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_FLOWER_FOREST,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.PLAINS, Biome.BiomeCategory.FOREST) && !BiomeSelection.isBiome(event, Biomes.SUNFLOWER_PLAINS) &&
                    BiomeSelection.hasName(event, "flower", "blossom", "bloom")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.PYRAMID_END, (event) ->
            (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidEndAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_END,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.THEEND) &&
                    !BiomeSelection.isBiome(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS)))
        );
    }
}
