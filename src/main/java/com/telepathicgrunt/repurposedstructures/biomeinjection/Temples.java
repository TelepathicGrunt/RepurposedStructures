package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;

public final class Temples {
    private Temples() {}

    public static void addTemples(BiomeInjection.BiomeInjectionHelper event) {

        if (RepurposedStructures.RSAllConfig.RSTemplesConfig.netherBasaltTempleAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.TEMPLE_NETHER_BASALT,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "basalt", "blackstone")))
        {
            event.addStructure(RSConfiguredStructures.NETHER_BASALT_TEMPLE);
        }

        if (RepurposedStructures.RSAllConfig.RSTemplesConfig.netherCrimsonTempleAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.TEMPLE_NETHER_BASALT,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "crimson", "red_")))
        {
            event.addStructure(RSConfiguredStructures.NETHER_CRIMSON_TEMPLE);
        }

        if (RepurposedStructures.RSAllConfig.RSTemplesConfig.netherWarpedTempleAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.TEMPLE_NETHER_BASALT,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "warped", "blue")))
        {
            event.addStructure(RSConfiguredStructures.NETHER_WARPED_TEMPLE);
        }

        if (RepurposedStructures.RSAllConfig.RSTemplesConfig.netherSoulTempleAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.TEMPLE_NETHER_BASALT,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "soul")))
        {
            event.addStructure(RSConfiguredStructures.NETHER_SOUL_TEMPLE);
        }

        if (RepurposedStructures.RSAllConfig.RSTemplesConfig.netherWastelandTempleAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.TEMPLE_NETHER_BASALT,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    !BiomeSelection.hasNameTemp(event, "basalt", "blackstone", "crimson", "red_", "warped", "blue", "soul")))
        {
            event.addStructure(RSConfiguredStructures.NETHER_WASTELAND_TEMPLE);
        }
    }
}
