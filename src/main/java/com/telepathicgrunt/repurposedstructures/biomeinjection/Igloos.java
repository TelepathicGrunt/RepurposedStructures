package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Igloos {

    public static void addIgloos(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSIgloosConfig.grassyIglooMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.IGLOO_GRASSY.get(),
                    () -> BiomeSelection.haveCategories(event, Category.FOREST, Category.PLAINS)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.GRASSY_IGLOO);
        }

        if (RepurposedStructures.RSIgloosConfig.stoneIglooMaxChunkDistance.get() != 1001 &&
                BiomeSelection.isBiomeAllowed(event, RSStructures.IGLOO_STONE.get(),
                        () -> BiomeSelection.haveCategories(event, Category.TAIGA) &&
                        BiomeSelection.hasName(event, "giant", "redwood")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.STONE_IGLOO);
        }
    }
}
