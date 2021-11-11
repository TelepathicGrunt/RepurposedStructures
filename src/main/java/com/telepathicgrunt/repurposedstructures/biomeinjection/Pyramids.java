package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSPyramidsConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public final class Pyramids {
    private Pyramids() {}

    public static void addPyramids(BiomeLoadingEvent event) {

        if (RSPyramidsConfig.pyramidNetherAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_NETHER.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_NETHER);
        }

        if (RSPyramidsConfig.pyramidBadlandsAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_BADLANDS.get(),
                    () -> BiomeSelection.haveCategories(event, Category.MESA)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_BADLANDS);
        }

        if (RSPyramidsConfig.pyramidIcyAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_ICY.get(),
                    () -> BiomeSelection.haveCategories(event, Category.ICY) &&
                    (BiomeSelection.hasName(event, "icy", "ice", "frozen") || (event.getClimate().temperature < 0 && !BiomeSelection.hasName(event, "snow")))))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_ICY);
        }

        if (RSPyramidsConfig.pyramidSnowyAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_SNOWY.get(),
                    () -> (BiomeSelection.haveCategories(event, Category.ICY) && !(BiomeSelection.hasName(event, "icy", "ice", "frozen") || (event.getClimate().temperature < 0 && !BiomeSelection.hasName(event, "snow")))) ||
                    (BiomeSelection.haveCategories(event, Category.TAIGA) && event.getClimate().precipitation == Biome.RainType.SNOW)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_SNOWY);
        }

        if (RSPyramidsConfig.pyramidJungleAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_JUNGLE.get(),
                    () -> BiomeSelection.haveCategories(event, Category.JUNGLE)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_JUNGLE);
        }

        if (RSPyramidsConfig.pyramidMushroomAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_MUSHROOM.get(),
                    () -> BiomeSelection.haveCategories(event, Category.MUSHROOM) &&
                    !BiomeSelection.isBiome(event, Biomes.MUSHROOM_FIELD_SHORE)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_MUSHROOM);
        }

        if (RSPyramidsConfig.pyramidOceanAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_OCEAN.get(),
                    () -> BiomeSelection.haveCategories(event, Category.OCEAN)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_OCEAN);
        }

        if (RSPyramidsConfig.pyramidGiantTreeTaigaAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_GIANT_TREE_TAIGA.get(),
                    () -> BiomeSelection.haveCategories(event, Category.TAIGA) && BiomeSelection.hasName(event, "giant", "redwood")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_GIANT_TREE_TAIGA);
        }

        if (RSPyramidsConfig.pyramidFlowerForestAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_FLOWER_FOREST.get(),
                    () -> BiomeSelection.haveCategories(event, Category.PLAINS, Category.FOREST) && !BiomeSelection.isBiome(event, Biomes.SUNFLOWER_PLAINS) &&
                    BiomeSelection.hasName(event, "flower", "blossom")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_FLOWER_FOREST);
        }

        if (RSPyramidsConfig.pyramidEndAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_END.get(),
                    () -> BiomeSelection.haveCategories(event, Category.THEEND) &&
                    !BiomeSelection.isBiome(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_END);
        }
    }
}
