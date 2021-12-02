package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSTemplesConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;

public final class Temples {
    private Temples() {}

    public static void addTemples(TemporaryBiomeInjection.BiomeInjectionHelper event) {

        if (RSTemplesConfig.netherBasaltTempleAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.TEMPLE_NETHER_BASALT.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "basalt", "blackstone")))
        {
            event.addStructure(RSConfiguredStructures.TEMPLE_NETHER_BASALT);
        }

        if (RSTemplesConfig.netherCrimsonTempleAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.TEMPLE_NETHER_BASALT.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "crimson", "red_")))
        {
            event.addStructure(RSConfiguredStructures.TEMPLE_NETHER_CRIMSON);
        }

        if (RSTemplesConfig.netherWarpedTempleAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.TEMPLE_NETHER_BASALT.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "warped", "blue")))
        {
            event.addStructure(RSConfiguredStructures.TEMPLE_NETHER_WARPED);
        }

        if (RSTemplesConfig.netherSoulTempleAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.TEMPLE_NETHER_BASALT.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "soul")))
        {
            event.addStructure(RSConfiguredStructures.TEMPLE_NETHER_SOUL);
        }

        if (RSTemplesConfig.netherWastelandTempleAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.TEMPLE_NETHER_BASALT.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    !BiomeSelection.hasNameTemp(event, "basalt", "blackstone", "crimson", "red_", "warped", "blue", "soul")))
        {
            event.addStructure(RSConfiguredStructures.TEMPLE_NETHER_WASTELAND);
        }
    }
}
