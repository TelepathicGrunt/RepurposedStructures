package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;

public final class Bastions {
    private Bastions() {}

    public static void addBastions(BiomeInjection.BiomeInjectionHelper event) {

        if (RepurposedStructures.RSAllConfig.RSBastionsConfig.bastionUndergroundAverageChunkDistance != 10001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.BASTION_UNDERGROUND,
                () -> !BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.BEACH, Biome.BiomeCategory.OCEAN, Biome.BiomeCategory.NETHER, Biome.BiomeCategory.THEEND, Biome.BiomeCategory.NONE)))
        {
            event.addStructure(RSConfiguredStructures.BASTION_UNDERGROUND);
        }
    }
}
