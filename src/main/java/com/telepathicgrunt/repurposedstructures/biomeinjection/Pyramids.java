package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Pyramids {

    public static void addPyramids(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSPyramidsConfig.netherPyramidMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_NETHER.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_PYRAMID);
        }

        if (RepurposedStructures.RSPyramidsConfig.badlandsPyramidMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_BADLANDS.get(),
                    () -> BiomeSelection.haveCategories(event, Category.MESA)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.BADLANDS_TEMPLE);
        }

        if (RepurposedStructures.RSPyramidsConfig.pyramidIcyMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_ICY.get(),
                    () -> BiomeSelection.haveCategories(event, Category.ICY) &&
                    (BiomeSelection.hasName(event, "icy", "ice", "frozen") || (event.getClimate().temperature < 0 && !BiomeSelection.hasName(event, "snow")))))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_ICY);
        }

        if (RepurposedStructures.RSPyramidsConfig.pyramidSnowyMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_SNOWY.get(),
                    () -> (BiomeSelection.haveCategories(event, Category.ICY) && !(BiomeSelection.hasName(event, "icy", "ice", "frozen") || (event.getClimate().temperature < 0 && !BiomeSelection.hasName(event, "snow")))) ||
                    (BiomeSelection.haveCategories(event, Category.TAIGA) && event.getClimate().precipitation == Biome.RainType.SNOW)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_SNOWY);
        }

        if (RepurposedStructures.RSPyramidsConfig.pyramidJungleMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_JUNGLE.get(),
                    () -> BiomeSelection.haveCategories(event, Category.JUNGLE)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_JUNGLE);
        }

        if (RepurposedStructures.RSPyramidsConfig.pyramidMushroomMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_MUSHROOM.get(),
                    () -> BiomeSelection.haveCategories(event, Category.MUSHROOM) &&
                    !BiomeSelection.isBiome(event, Biomes.MUSHROOM_FIELD_SHORE)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_MUSHROOM);
        }

        if (RepurposedStructures.RSPyramidsConfig.pyramidOceanMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_OCEAN.get(),
                    () -> BiomeSelection.haveCategories(event, Category.OCEAN)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_OCEAN);
        }

        if (RepurposedStructures.RSPyramidsConfig.pyramidGiantTreeTaigaMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_GIANT_TREE_TAIGA.get(),
                    () -> BiomeSelection.haveCategories(event, Category.TAIGA) && BiomeSelection.hasName(event, "giant", "redwood")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_GIANT_TREE_TAIGA);
        }

        if (RepurposedStructures.RSPyramidsConfig.pyramidFlowerForestMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_FLOWER_FOREST.get(),
                    () -> BiomeSelection.haveCategories(event, Category.PLAINS, Category.FOREST) && !BiomeSelection.isBiome(event, Biomes.SUNFLOWER_PLAINS) &&
                    BiomeSelection.hasName(event, "flower", "blossom")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_FLOWER_FOREST);
        }

        if (RepurposedStructures.RSPyramidsConfig.pyramidEndMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.PYRAMID_END.get(),
                    () -> BiomeSelection.haveCategories(event, Category.THEEND) &&
                    !BiomeSelection.isBiome(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_END);
        }
    }
}
