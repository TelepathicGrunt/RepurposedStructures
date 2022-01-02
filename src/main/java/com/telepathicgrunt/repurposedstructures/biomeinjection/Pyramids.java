package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Pyramids {
    private Pyramids() {}

    public static void addPyramids(BiomeInjection.BiomeInjectionHelper event) {

        if (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidNetherAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_NETHER,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER)))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_NETHER);
        }

        if (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidBadlandsAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_BADLANDS,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MESA)))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_BADLANDS);
        }

        if (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidIcyAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_ICY,
                    () -> BiomeSelection.hasNameTemp(event, "ice", "icy", "glacier", "glacial", "frozen") &&
                            (BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.ICY) ||
                            (BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MOUNTAIN) && event.biome.getBaseTemperature() < 0))))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_ICY);
        }

        if (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidSnowyAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_SNOWY,
                    () -> BiomeSelection.isBiomeTemp(event, Biomes.GROVE) ||
                            (!BiomeSelection.hasNameTemp(event, "ice", "icy", "glacier", "glacial", "frozen") &&
                                ((BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.ICY) && BiomeSelection.hasNameTemp(event, "snow")) ||
                                (BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MOUNTAIN) && (event.biome.getBaseTemperature() < 0 || BiomeSelection.hasNameTemp(event, "snow")))))))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_SNOWY);
        }

        if (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidJungleAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_JUNGLE,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.JUNGLE)))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_JUNGLE);
        }

        if (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidMushroomAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_MUSHROOM,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MUSHROOM)))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_MUSHROOM);
        }

        if (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidOceanAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_OCEAN,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.OCEAN)))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_OCEAN);
        }

        if (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidGiantTreeTaigaAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_GIANT_TREE_TAIGA,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA) && BiomeSelection.hasNameTemp(event, "giant", "redwood", "old_growth")))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_GIANT_TREE_TAIGA);
        }

        if (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidFlowerForestAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_FLOWER_FOREST,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.PLAINS, Biome.BiomeCategory.FOREST) && !BiomeSelection.isBiomeTemp(event, Biomes.SUNFLOWER_PLAINS) &&
                    BiomeSelection.hasNameTemp(event, "flower", "blossom", "bloom")))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_FLOWER_FOREST);
        }

        if (RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidEndAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_END,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.THEEND) &&
                    !BiomeSelection.isBiomeTemp(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS)))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_END);
        }
    }
}
