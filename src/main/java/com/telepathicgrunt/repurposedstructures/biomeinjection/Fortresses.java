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

    public static void addJungleFortress(BiomeInjection.BiomeInjectionHelper event) {

        if(RepurposedStructures.RSAllConfig.RSFortressesConfig.jungleFortress.jungleFortressAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.FORTRESS_JUNGLE,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.JUNGLE)))
        {
            event.addStructure(RSConfiguredStructures.FORTRESS_JUNGLE);
        }
    }
}
