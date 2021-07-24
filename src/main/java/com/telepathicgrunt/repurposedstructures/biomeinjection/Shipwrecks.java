package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Shipwrecks {

    public static void addShipwrecks(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSShipwrecksConfig.endShipwreckMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.SHIPWRECK_END.get(),
                    () -> BiomeSelection.haveCategories(event, Category.THEEND) &&
                    !BiomeSelection.isBiome(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.SHIPWRECK_END);
        }

        //Nether based Shipwrecks
        if (RepurposedStructures.RSShipwrecksConfig.crimsonShipwreckMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.SHIPWRECK_CRIMSON.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    BiomeSelection.hasName(event, "crimson", "red_")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.SHIPWRECK_CRIMSON);
        }

        if (RepurposedStructures.RSShipwrecksConfig.warpedShipwreckMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.SHIPWRECK_WARPED.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    BiomeSelection.hasName(event, "warped", "blue")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.SHIPWRECK_WARPED);
        }

        if (RepurposedStructures.RSShipwrecksConfig.netherBricksShipwreckMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.SHIPWRECK_NETHER_BRICKS.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    !BiomeSelection.hasName(event, "crimson", "red_", "warped", "blue")))
        {
            if(BiomeSelection.hasName(event, "soul")){
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.SHIPWRECK_NETHER_BRICKS_FLYING);
            }
            else{
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.SHIPWRECK_NETHER_BRICKS);
            }
        }
    }
}
