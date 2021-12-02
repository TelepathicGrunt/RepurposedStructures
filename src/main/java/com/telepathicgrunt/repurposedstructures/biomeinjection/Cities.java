package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSCitiesConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;

public final class Cities {
    private Cities() {}

    public static void addCities(TemporaryBiomeInjection.BiomeInjectionHelper event) {

        if (RSCitiesConfig.citiesNetherAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.CITY_NETHER.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER)))
        {
            event.addStructure(RSConfiguredStructures.CITY_NETHER);
        }
    }
}
