package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class RuinedPortals {
    private RuinedPortals() {}

    public static void addRuinedPortals() {

        BiomeInjection.addStructure(RSConfiguredStructures.RUINED_PORTAL_END, (event) ->
            (RepurposedStructures.RSAllConfig.RSRuinedPortalsConfig.ruinedPortalEndAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.RUINED_PORTAL_END,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.THEEND) && !BiomeSelection.isBiome(event, Biomes.THE_END)))
        );
    }
}
