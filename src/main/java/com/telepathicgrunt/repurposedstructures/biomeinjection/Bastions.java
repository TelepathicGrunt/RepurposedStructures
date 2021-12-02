package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSBastionsConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;

public final class Bastions {
    private Bastions() {}

    public static void addBastions(TemporaryBiomeInjection.BiomeInjectionHelper event) {

        if (RSBastionsConfig.bastionUndergroundAverageChunkDistance.get() != 10001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.BASTION_UNDERGROUND.get(),
                () -> !BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.BEACH, Biome.BiomeCategory.OCEAN, Biome.BiomeCategory.NETHER, Biome.BiomeCategory.THEEND, Biome.BiomeCategory.NONE)))
        {
            event.addStructure(RSConfiguredStructures.BASTION_UNDERGROUND);
        }
    }
}
