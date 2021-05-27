package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Wells {

    public static void addWells(BiomeLoadingEvent event) {

        if (BiomeSelection.haveCategories(event, Category.MESA) &&
            RepurposedStructures.RSWellsConfig.badlandsWellRarityPerChunk.get() != 10000 &&
            wellAllowedByNamespaceAndConfig(event))
        {
            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.BADLANDS_WELL);
        }
        
        else if (BiomeSelection.haveCategories(event, Category.NETHER) &&
                RepurposedStructures.RSWellsConfig.netherWellRarityPerChunk.get() != 10000 &&
                wellAllowedByNamespaceAndConfig(event)) {

            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.NETHER_WELL);
        }

        else if ((BiomeSelection.haveCategories(event, Category.ICY) || BiomeSelection.hasName(event, "snow")) &&
                RepurposedStructures.RSWellsConfig.snowWellRarityPerChunk.get() != 10000 &&
                wellAllowedByNamespaceAndConfig(event))
        {
            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.SNOW_WELL);
        }

        else if ((BiomeSelection.haveCategories(event, Category.SWAMP, Category.JUNGLE) ||
                (BiomeSelection.haveCategories(event, Category.FOREST) && BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted"))) &&
                RepurposedStructures.RSWellsConfig.mossyStoneWellRarityPerChunk.get() != 10000 &&
                wellAllowedByNamespaceAndConfig(event))
        {
            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.MOSSY_STONE_WELL);
        }

        else if (BiomeSelection.haveCategories(event, Category.FOREST) && !(BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted")) &&
                RepurposedStructures.RSWellsConfig.forestWellRarityPerChunk.get() != 10000 &&
                wellAllowedByNamespaceAndConfig(event))
        {

            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.FOREST_WELL);
        }

        else if (BiomeSelection.haveCategories(event, Category.MUSHROOM) &&
                RepurposedStructures.RSWellsConfig.mushroomWellRarityPerChunk.get() != 10000 &&
                wellAllowedByNamespaceAndConfig(event))
        {

            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.FOREST_WELL);
        }
    }

    private static boolean wellAllowedByNamespaceAndConfig(BiomeLoadingEvent event) {
        return BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSWellsConfig.addWellsToModdedBiomes.get();
    }
}
