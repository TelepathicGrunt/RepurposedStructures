package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biomes;

public final class RuinedPortals {
    private RuinedPortals() {}

    public static void addRuinedPortals() {

        GeneralUtils.addToBiome("ruined_portal_end",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.RUINED_PORTAL_END,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.THEEND)
                                && !BiomeSelection.isBiome(context, Biomes.THE_END))
                        && RepurposedStructures.RSAllConfig.RSRuinedPortalsConfig.ruinedPortalEndAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.RUINED_PORTAL_END));
    }
}
