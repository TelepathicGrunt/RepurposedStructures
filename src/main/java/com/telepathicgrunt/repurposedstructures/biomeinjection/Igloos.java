package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.level.biome.Biome.BiomeCategory;

public final class Igloos {
    private Igloos() {}

    public static void addIgloos() {

        GeneralUtils.addToBiome("grassy_igloo",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.IGLOO_GRASSY,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.FOREST, BiomeCategory.PLAINS))
                        && RepurposedStructures.RSAllConfig.RSIgloosConfig.grassyIglooAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.IGLOO_GRASSY));

        GeneralUtils.addToBiome("stone_igloo",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.IGLOO_STONE,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.TAIGA)
                                && BiomeSelection.hasName(context, "giant", "redwood"))
                        && RepurposedStructures.RSAllConfig.RSIgloosConfig.stoneIglooAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.IGLOO_STONE));
    }
}
