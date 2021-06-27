package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BiomeKeys;

public class RuinedPortals {

    public static void addRuinedPortals() {
        GeneralUtils.addToBiome("ruined_portal_end",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.RUINED_PORTAL_END,
                                () -> BiomeSelection.haveCategories(context, Category.THEEND)
                                && !BiomeSelection.isBiome(context, BiomeKeys.THE_END))
                        && RepurposedStructures.RSAllConfig.RSRuinedPortalsConfig.ruinedPortalEndAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.RUINED_PORTAL_END));
    }
}
