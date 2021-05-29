package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Pyramids {

    public static void addPyramids(BiomeLoadingEvent event) {

        if (BiomeSelection.haveCategories(event, Category.NETHER) &&
            RepurposedStructures.RSTemplesConfig.netherPyramidMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addNetherPyramidToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_PYRAMID);
        }

        if (BiomeSelection.haveCategories(event, Category.MESA) &&
            RepurposedStructures.RSTemplesConfig.badlandsPyramidMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addBadlandsPyramidToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.BADLANDS_TEMPLE);
        }

        if (BiomeSelection.haveCategories(event, Category.ICY) &&
                (BiomeSelection.hasName(event, "icy", "ice", "frozen") || (event.getClimate().temperature < 0 && !BiomeSelection.hasName(event, "snow"))) &&
                RepurposedStructures.RSTemplesConfig.pyramidIcyMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addPyramidIcyToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_ICY);
        }
        else if ((BiomeSelection.haveCategories(event, Category.ICY) || (BiomeSelection.haveCategories(event, Category.TAIGA) && event.getClimate().precipitation == Biome.RainType.SNOW)) &&
                RepurposedStructures.RSTemplesConfig.pyramidSnowyMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addPyramidSnowyToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_SNOWY);
        }

        else if (BiomeSelection.haveCategories(event, Category.JUNGLE) &&
            RepurposedStructures.RSTemplesConfig.pyramidJungleMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addPyramidJungleToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_JUNGLE);
        }

        else if (BiomeSelection.haveCategories(event, Category.MUSHROOM) &&
            !BiomeSelection.isBiome(event, Biomes.MUSHROOM_FIELD_SHORE) &&
            RepurposedStructures.RSTemplesConfig.pyramidMushroomMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addPyramidMushroomToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_MUSHROOM);
        }

        else if (BiomeSelection.haveCategories(event, Category.OCEAN) &&
            RepurposedStructures.RSTemplesConfig.pyramidOceanMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addPyramidOceanToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_OCEAN);
        }

        else if (BiomeSelection.haveCategories(event, Category.TAIGA) && BiomeSelection.hasName(event, "giant", "redwood") &&
            RepurposedStructures.RSTemplesConfig.pyramidGiantTreeTaigaMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addPyramidGiantTreeTaigaToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_GIANT_TREE_TAIGA);
        }

        else if (BiomeSelection.haveCategories(event, Category.PLAINS, Category.FOREST) && !BiomeSelection.isBiome(event, Biomes.SUNFLOWER_PLAINS) &&
            BiomeSelection.hasName(event, "flower", "blossom") &&
            RepurposedStructures.RSTemplesConfig.pyramidFlowerForestMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addPyramidFlowerForestToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_FLOWER_FOREST);
        }

        if (RepurposedStructures.RSTemplesConfig.pyramidEndMaxChunkDistance.get() != 1001 &&
                BiomeSelection.haveCategories(event, Category.THEEND) &&
                !BiomeSelection.isBiome(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS) &&
                (BiomeSelection.hasNamespace(event, "minecraft") ||
                        RepurposedStructures.RSTemplesConfig.addPyramidEndToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_END);
        }
    }
}
