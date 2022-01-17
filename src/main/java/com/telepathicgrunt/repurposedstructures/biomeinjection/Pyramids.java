package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSPyramidsConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Pyramids {
    private Pyramids() {}

    public static void addPyramids(TemporaryBiomeInjection.BiomeInjectionHelper event) {

        if (RSPyramidsConfig.pyramidNetherAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_NETHER.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER)))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_NETHER);
        }

        if (RSPyramidsConfig.pyramidBadlandsAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_BADLANDS.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MESA)))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_BADLANDS);
        }

        if (RSPyramidsConfig.pyramidDarkForestAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_DARK_FOREST.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.FOREST) &&
                    BiomeSelection.hasNameTemp(event, "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony")))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_DARK_FOREST);
        }

        if (RSPyramidsConfig.pyramidIcyAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_ICY.get(),
                    () -> BiomeSelection.hasNameTemp(event, "ice", "icy", "glacier", "glacial", "frozen") &&
                            (BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.ICY) ||
                            (BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MOUNTAIN) && event.biome.getBaseTemperature() < 0))))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_ICY);
        }

        if (RSPyramidsConfig.pyramidSnowyAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_SNOWY.get(),
                    () -> BiomeSelection.isBiomeTemp(event, Biomes.GROVE) ||
                            (!BiomeSelection.hasNameTemp(event, "ice", "icy", "glacier", "glacial", "frozen") &&
                                ((BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.ICY) && BiomeSelection.hasNameTemp(event, "snow")) ||
                                (BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MOUNTAIN) && (event.biome.getBaseTemperature() < 0 || BiomeSelection.hasNameTemp(event, "snow")))))))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_SNOWY);
        }

        if (RSPyramidsConfig.pyramidJungleAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_JUNGLE.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.JUNGLE)))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_JUNGLE);
        }

        if (RSPyramidsConfig.pyramidMushroomAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_MUSHROOM.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MUSHROOM)))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_MUSHROOM);
        }

        if (RSPyramidsConfig.pyramidOceanAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_OCEAN.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.OCEAN)))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_OCEAN);
        }

        if (RSPyramidsConfig.pyramidGiantTreeTaigaAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_GIANT_TREE_TAIGA.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA) && BiomeSelection.hasNameTemp(event, "giant", "redwood", "old_growth")))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_GIANT_TREE_TAIGA);
        }

        if (RSPyramidsConfig.pyramidFlowerForestAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_FLOWER_FOREST.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.PLAINS, Biome.BiomeCategory.FOREST) && !BiomeSelection.isBiomeTemp(event, Biomes.SUNFLOWER_PLAINS) &&
                    BiomeSelection.hasNameTemp(event, "flower", "blossom", "bloom")))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_FLOWER_FOREST);
        }

        if (RSPyramidsConfig.pyramidEndAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.PYRAMID_END.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.THEEND) &&
                    !BiomeSelection.isBiomeTemp(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS)))
        {
            event.addStructure(RSConfiguredStructures.PYRAMID_END);
        }
    }
}
