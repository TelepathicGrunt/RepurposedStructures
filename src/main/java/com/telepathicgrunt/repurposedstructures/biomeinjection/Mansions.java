package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Mansions {

    public static void addMansions(BiomeLoadingEvent event) {
        if (RepurposedStructures.RSMansionsConfig.mansionBirchMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.MANSION_BIRCH.get(),
                    () -> BiomeSelection.hasName(event, "birch")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MANSION_BIRCH);
        }

        else if (RepurposedStructures.RSMansionsConfig.mansionJungleMaxChunkDistance.get() != 1001 &&
                BiomeSelection.isBiomeAllowed(event, RSStructures.MANSION_JUNGLE.get(),
                        () -> BiomeSelection.haveCategories(event, Category.JUNGLE)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MANSION_JUNGLE);
        }

        else if (RepurposedStructures.RSMansionsConfig.mansionOakMaxChunkDistance.get() != 1001 &&
                BiomeSelection.isBiomeAllowed(event, RSStructures.MANSION_OAK.get(),
                        () -> BiomeSelection.haveCategories(event, Category.FOREST) &&
                        !BiomeSelection.hasName(event, "birch", "dark", "spooky", "dead", "haunted")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MANSION_OAK);
        }

        else if (RepurposedStructures.RSMansionsConfig.mansionSavannaMaxChunkDistance.get() != 1001 &&
                BiomeSelection.isBiomeAllowed(event, RSStructures.MANSION_SAVANNA.get(),
                        () -> BiomeSelection.haveCategories(event, Category.SAVANNA) &&
                        !BiomeSelection.isBiome(event, Biomes.SAVANNA_PLATEAU)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MANSION_SAVANNA);
        }

        else if (RepurposedStructures.RSMansionsConfig.mansionTaigaMaxChunkDistance.get() != 1001 &&
                BiomeSelection.isBiomeAllowed(event, RSStructures.MANSION_TAIGA.get(),
                        () -> BiomeSelection.haveCategories(event, Category.TAIGA) && event.getClimate().precipitation != Biome.RainType.SNOW &&
                        !BiomeSelection.hasName(event, "giant", "redwood", "snow", "ice", "icy", "glacier", "frozen")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MANSION_TAIGA);
        }

        else if (RepurposedStructures.RSMansionsConfig.mansionDesertMaxChunkDistance.get() != 1001 &&
                BiomeSelection.isBiomeAllowed(event, RSStructures.MANSION_DESERT.get(),
                        () -> BiomeSelection.haveCategories(event, Category.DESERT)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MANSION_DESERT);
        }

        else if (RepurposedStructures.RSMansionsConfig.mansionSnowyMaxChunkDistance.get() != 1001 &&
                BiomeSelection.isBiomeAllowed(event, RSStructures.MANSION_SNOWY.get(),
                        () -> BiomeSelection.haveCategories(event, Category.ICY) ||
                        (BiomeSelection.haveCategories(event, Category.TAIGA) && event.getClimate().precipitation == Biome.RainType.SNOW)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MANSION_SNOWY);
        }
    }
}
