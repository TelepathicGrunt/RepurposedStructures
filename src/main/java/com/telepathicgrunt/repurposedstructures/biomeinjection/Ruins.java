package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.level.biome.Biome.BiomeCategory;

public final class Ruins {
    private Ruins() {}

    public static void addRuins() {

        GeneralUtils.addToBiome("ruins_nether",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.RUINS_NETHER,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.NETHER))
                && RepurposedStructures.RSAllConfig.RSRuinsConfig.ruinsNetherAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.RUINS_NETHER));

        GeneralUtils.addToBiome("ruins_land_warm",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.RUINS_LAND_WARM,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.PLAINS, BiomeCategory.FOREST, BiomeCategory.TAIGA, BiomeCategory.SWAMP)
                                && !BiomeSelection.hasName(context, "snow", "ice", "frozen")
                                && context.getBiome().getBaseTemperature() >= 0.25f)
                        && RepurposedStructures.RSAllConfig.RSRuinsConfig.ruinsLandWarmAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.RUINS_LAND_WARM));

        GeneralUtils.addToBiome("ruins_land_hot",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.RUINS_LAND_HOT,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.DESERT))
                        && RepurposedStructures.RSAllConfig.RSRuinsConfig.ruinsLandHotAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.RUINS_LAND_HOT));
    }
}
