package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;

public final class Fortresses {
    private Fortresses() {}

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // JUNGLE FORTRESS //

    public static void addJungleFortress() {

        BiomeInjection.addStructure(RSConfiguredStructures.FORTRESS_JUNGLE, (event) ->
            (RepurposedStructures.RSAllConfig.RSFortressesConfig.jungleFortress.jungleFortressAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.FORTRESS_JUNGLE,
            () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.JUNGLE)))
        );
    }
}
