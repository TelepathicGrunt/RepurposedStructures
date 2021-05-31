package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Villages {

    public static void addVillages(BiomeLoadingEvent event) {
        if (BiomeSelection.haveCategories(event, Category.MESA) &&
            RepurposedStructures.RSVillagesConfig.badlandsVillageMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.BADLANDS_VILLAGE);
        }

        else if (BiomeSelection.hasName(event, "birch") &&
                RepurposedStructures.RSVillagesConfig.birchVillageMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.BIRCH_VILLAGE);
        }

        else if (BiomeSelection.haveCategories(event, Category.FOREST) && BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted") &&
                RepurposedStructures.RSVillagesConfig.darkForestVillageMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.DARK_FOREST_VILLAGE);
        }

        else if (BiomeSelection.haveCategories(event, Category.JUNGLE) &&
                RepurposedStructures.RSVillagesConfig.jungleVillageMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.JUNGLE_VILLAGE);
        }

        else if (BiomeSelection.haveCategories(event, Category.SWAMP) &&
                RepurposedStructures.RSVillagesConfig.swampVillageMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.SWAMP_VILLAGE);
        }

        else if (BiomeSelection.haveCategories(event, Category.EXTREME_HILLS) &&
                RepurposedStructures.RSVillagesConfig.mountainsVillageMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MOUNTAINS_VILLAGE);
        }

        else if (BiomeSelection.isBiome(event, Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_TREE_TAIGA) ||
                (RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get() &&
                    !BiomeSelection.hasNamespace(event, "minecraft") &&
                    BiomeSelection.haveCategories(event, Category.TAIGA) &&
                    BiomeSelection.hasName(event, "giant", "redwood")))
        {
            if (RepurposedStructures.RSVillagesConfig.giantTaigaVillageMaxChunkDistance.get() != 1001) {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.GIANT_TAIGA_VILLAGE);
            }
        }

        else if (BiomeSelection.haveCategories(event, Category.NETHER) && BiomeSelection.hasName(event, "crimson", "red_") &&
                RepurposedStructures.RSVillagesConfig.crimsonVillageMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.CRIMSON_VILLAGE);
        }

        else if (BiomeSelection.haveCategories(event, Category.NETHER) &&
                BiomeSelection.hasName(event, "warped", "blue") &&
                RepurposedStructures.RSVillagesConfig.warpedVillageMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WARPED_VILLAGE);
        }

        else if ((BiomeSelection.haveCategories(event, Category.FOREST) && !BiomeSelection.hasName(event, "birch", "dark", "spooky", "dead", "haunted")) &&
                RepurposedStructures.RSVillagesConfig.villageOakMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.VILLAGE_OAK);
        }
    }
}
