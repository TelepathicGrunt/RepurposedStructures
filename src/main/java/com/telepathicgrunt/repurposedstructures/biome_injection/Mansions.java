package com.telepathicgrunt.repurposedstructures.biome_injection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Mansions {

    public static void addMansions(BiomeLoadingEvent event) {
        if (BiomeSelection.hasName(event, "birch")  &&
            RepurposedStructures.RSMansionsConfig.mansionBirchMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSMansionsConfig.addMansionBirchToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MANSION_BIRCH);
        }

        else if (BiomeSelection.haveCategories(event, Category.JUNGLE) &&
                RepurposedStructures.RSMansionsConfig.mansionJungleMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSMansionsConfig.addMansionJungleToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MANSION_JUNGLE);
        }

        else if ((BiomeSelection.haveCategories(event, Category.FOREST) && !BiomeSelection.hasName(event, "birch", "dark", "spooky", "dead", "haunted")) &&
                RepurposedStructures.RSMansionsConfig.mansionOakMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSMansionsConfig.addMansionOakToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MANSION_OAK);
        }

        else if (BiomeSelection.haveCategories(event, Category.SAVANNA) &&
                !BiomeSelection.isBiome(event, Biomes.SAVANNA_PLATEAU) &&
                RepurposedStructures.RSMansionsConfig.mansionSavannaMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSMansionsConfig.addMansionSavannaToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MANSION_SAVANNA);
        }

        else if (BiomeSelection.haveCategories(event, Category.TAIGA) && event.getClimate().precipitation != Biome.RainType.SNOW &&
                !BiomeSelection.hasName(event, "giant", "redwood", "snow", "ice", "icy", "glacier", "frozen")  &&
                RepurposedStructures.RSMansionsConfig.mansionTaigaMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSMansionsConfig.addMansionTaigaToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MANSION_TAIGA);
        }

        else if (BiomeSelection.haveCategories(event, Category.DESERT) &&
                RepurposedStructures.RSMansionsConfig.mansionDesertMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSMansionsConfig.addMansionDesertToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MANSION_DESERT);
        }

        else if ((BiomeSelection.haveCategories(event, Category.ICY) || (BiomeSelection.haveCategories(event, Category.TAIGA) && event.getClimate().precipitation == Biome.RainType.SNOW)) &&
                RepurposedStructures.RSMansionsConfig.mansionSnowyMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSMansionsConfig.addMansionSnowyToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MANSION_SNOWY);
        }
    }
}
