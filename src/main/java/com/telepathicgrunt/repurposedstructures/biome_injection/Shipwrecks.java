package com.telepathicgrunt.repurposedstructures.biome_injection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Shipwrecks {

    public static void addShipwrecks(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSShipwrecksConfig.endShipwreckMaxChunkDistance.get() != 1001 &&
            BiomeSelection.haveCategories(event, Category.THEEND) &&
           !BiomeSelection.isBiome(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS) &&
            (BiomeSelection.hasNamespace(event, "minecraft") ||
                RepurposedStructures.RSShipwrecksConfig.addEndShipwreckToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.END_SHIPWRECK);
        }

        //Nether based Shipwrecks
        if(BiomeSelection.haveCategories(event, Category.NETHER))
        {
            if (BiomeSelection.hasName(event, "crimson", "red_") &&
                RepurposedStructures.RSShipwrecksConfig.crimsonShipwreckMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSShipwrecksConfig.addCrimsonShipwreckToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.CRIMSON_SHIPWRECK);
            }

            else if (BiomeSelection.hasName(event, "warped", "blue") &&
                    RepurposedStructures.RSShipwrecksConfig.warpedShipwreckMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSShipwrecksConfig.addWarpedShipwreckToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WARPED_SHIPWRECK);
            }

            else if (RepurposedStructures.RSShipwrecksConfig.netherBricksShipwreckMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSShipwrecksConfig.addNetherBricksShipwreckToModdedBiomes.get()))
            {
                if(BiomeSelection.hasName(event, "soul")){
                    event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_BRICKS_SHIPWRECK_FLYING);
                }
                else{
                    event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_BRICKS_SHIPWRECK);
                }
            }
        }
    }
}
