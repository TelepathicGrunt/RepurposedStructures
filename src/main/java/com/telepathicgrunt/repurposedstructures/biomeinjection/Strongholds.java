package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSStrongholdsConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Strongholds {
    private Strongholds() {}

    public static void addStrongholds(TemporaryBiomeInjection.BiomeInjectionHelper event) {

        if (RSStrongholdsConfig.strongholdNetherAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.STRONGHOLD_NETHER.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER)))
        {
            event.addStructure(RSConfiguredStructures.STRONGHOLD_NETHER);
        }

        if (RSStrongholdsConfig.strongholdEndAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.STRONGHOLD_END.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.THEEND) &&
                    !BiomeSelection.isBiomeTemp(event, Biomes.SMALL_END_ISLANDS)))
        {
            event.addStructure(RSConfiguredStructures.STRONGHOLD_END);
        }
    }
}
