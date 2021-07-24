package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Temples {

    public static void addTemples(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSTemplesConfig.netherBasaltTempleMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.TEMPLE_NETHER_BASALT.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    BiomeSelection.hasName(event, "basalt", "blackstone")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_BASALT_TEMPLE);
        }

        if (RepurposedStructures.RSTemplesConfig.netherCrimsonTempleMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.TEMPLE_NETHER_BASALT.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    BiomeSelection.hasName(event, "crimson", "red_")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_CRIMSON_TEMPLE);
        }

        if (RepurposedStructures.RSTemplesConfig.netherWarpedTempleMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.TEMPLE_NETHER_BASALT.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    BiomeSelection.hasName(event, "warped", "blue")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_WARPED_TEMPLE);
        }

        if (RepurposedStructures.RSTemplesConfig.netherSoulTempleMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.TEMPLE_NETHER_BASALT.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    BiomeSelection.hasName(event, "soul")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_SOUL_TEMPLE);
        }

        if (RepurposedStructures.RSTemplesConfig.netherWastelandTempleMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.TEMPLE_NETHER_BASALT.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    !BiomeSelection.hasName(event, "basalt", "blackstone", "crimson", "red_", "warped", "blue", "soul")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_WASTELAND_TEMPLE);
        }
    }
}
