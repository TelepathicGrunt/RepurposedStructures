package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSVillagesConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public final class Villages {
    private Villages() {}

    public static void addVillages(BiomeLoadingEvent event) {
        
        if (RSVillagesConfig.badlandsVillageMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_BADLANDS.get(),
                    () -> BiomeSelection.haveCategories(event, Category.MESA)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.VILLAGE_BADLANDS);
        }

        if (RSVillagesConfig.birchVillageMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_BIRCH.get(),
                    () -> BiomeSelection.hasName(event, "birch")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.VILLAGE_BIRCH);
        }

        if (RSVillagesConfig.darkForestVillageMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_DARK_FOREST.get(),
                    () -> BiomeSelection.haveCategories(event, Category.FOREST) &&
                    BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.VILLAGE_DARK_FOREST);
        }

        if (RSVillagesConfig.jungleVillageMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_JUNGLE.get(),
                    () -> BiomeSelection.haveCategories(event, Category.JUNGLE)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.VILLAGE_JUNGLE);
        }

        if (RSVillagesConfig.swampVillageMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_SWAMP.get(),
                    () -> BiomeSelection.haveCategories(event, Category.SWAMP)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.VILLAGE_SWAMP);
        }

        if (RSVillagesConfig.mountainsVillageMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_MOUNTAINS.get(),
                    () -> BiomeSelection.haveCategories(event, Category.EXTREME_HILLS)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.VILLAGE_MOUNTAINS);
        }

        if (RSVillagesConfig.giantTaigaVillageMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_GIANT_TAIGA.get(),
                    () -> BiomeSelection.isBiome(event, Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_TREE_TAIGA) ||
                    (!BiomeSelection.hasNamespace(event, "minecraft") && BiomeSelection.haveCategories(event, Category.TAIGA) && BiomeSelection.hasName(event, "giant", "redwood"))))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.VILLAGE_GIANT_TAIGA);
        }

        if (RSVillagesConfig.oakVillageMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_OAK.get(),
                    () -> BiomeSelection.haveCategories(event, Category.FOREST) &&
                    !BiomeSelection.hasName(event, "birch", "dark", "spooky", "dead", "haunted")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.VILLAGE_OAK);
        }

        if (RSVillagesConfig.crimsonVillageMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_CRIMSON.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    BiomeSelection.hasName(event, "crimson", "red_")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.VILLAGE_CRIMSON);
        }

        if (RSVillagesConfig.warpedVillageMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_WARPED.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    BiomeSelection.hasName(event, "warped", "blue")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.VILLAGE_WARPED);
        }

        if (RSVillagesConfig.mushroomVillageAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_MUSHROOM.get(),
                    () -> BiomeSelection.haveCategories(event, Category.MUSHROOM)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.VILLAGE_MUSHROOM);
        }
        // regexpos1
    }
}
