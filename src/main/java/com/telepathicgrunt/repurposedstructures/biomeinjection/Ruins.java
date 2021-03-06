package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Ruins {

    public static void addRuins(BiomeLoadingEvent event) {
        if (BiomeSelection.haveCategories(event, Category.NETHER) &&
                RepurposedStructures.RSMainConfig.ruinsNetherMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSMainConfig.addRuinsNetherToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.RUINS_NETHER);
        }

        if (BiomeSelection.haveCategories(event, Category.PLAINS, Category.FOREST, Category.TAIGA, Category.SWAMP) &&
            !BiomeSelection.hasName(event, "snow", "ice", "frozen") &&
            event.getClimate().temperature >= 0.25f &&
            RepurposedStructures.RSMainConfig.ruinsLandWarmMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSMainConfig.addRuinsLandWarmToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.RUINS_LAND_WARM);
        }
        if (BiomeSelection.haveCategories(event, Category.DESERT) &&
            RepurposedStructures.RSMainConfig.ruinsLandHotMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSMainConfig.addRuinsLandHotToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.RUINS_LAND_HOT);
        }
        // regexpos1
    }
}
