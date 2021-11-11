package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSTemplesConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public final class Temples {
    private Temples() {}

    public static void addTemples(BiomeLoadingEvent event) {

        if (RSTemplesConfig.netherBasaltTempleAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.TEMPLE_NETHER_BASALT.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    BiomeSelection.hasName(event, "basalt", "blackstone")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.TEMPLE_NETHER_BASALT);
        }

        if (RSTemplesConfig.netherCrimsonTempleAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.TEMPLE_NETHER_BASALT.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    BiomeSelection.hasName(event, "crimson", "red_")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.TEMPLE_NETHER_CRIMSON);
        }

        if (RSTemplesConfig.netherWarpedTempleAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.TEMPLE_NETHER_BASALT.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    BiomeSelection.hasName(event, "warped", "blue")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.TEMPLE_NETHER_WARPED);
        }

        if (RSTemplesConfig.netherSoulTempleAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.TEMPLE_NETHER_BASALT.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    BiomeSelection.hasName(event, "soul")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.TEMPLE_NETHER_SOUL);
        }

        if (RSTemplesConfig.netherWastelandTempleAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.TEMPLE_NETHER_BASALT.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    !BiomeSelection.hasName(event, "basalt", "blackstone", "crimson", "red_", "warped", "blue", "soul")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.TEMPLE_NETHER_WASTELAND);
        }
    }
}
