package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;

public final class Bastions {
    private Bastions() {}

    public static void addBastions() {

        BiomeInjection.addStructure(RSConfiguredStructures.BASTION_UNDERGROUND, (event) ->
            (RepurposedStructures.RSAllConfig.RSBastionsConfig.bastionUndergroundAverageChunkDistance != 10001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.BASTION_UNDERGROUND,
            () -> !BiomeSelection.haveCategories(event, Biome.BiomeCategory.BEACH, Biome.BiomeCategory.OCEAN, Biome.BiomeCategory.NETHER, Biome.BiomeCategory.THEEND, Biome.BiomeCategory.NONE)))
        );
    }
}
