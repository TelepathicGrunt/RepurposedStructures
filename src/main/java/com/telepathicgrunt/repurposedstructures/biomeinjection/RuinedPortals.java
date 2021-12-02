package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSRuinedPortalsConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class RuinedPortals {
    private RuinedPortals() {}

    public static void addRuinedPortals(TemporaryBiomeInjection.BiomeInjectionHelper event) {

        if (RSRuinedPortalsConfig.ruinedPortalEndAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.RUINED_PORTAL_END.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.THEEND) && !BiomeSelection.isBiomeTemp(event, Biomes.THE_END)))
        {
            event.addStructure(RSConfiguredStructures.RUINED_PORTAL_END);
        }
    }
}
