package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.biome.Biome.Category;

public class Ruins {

    public static void addRuins() {
        GeneralUtils.addToBiome("ruins_nether",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.RUINS_NETHER,
                                () -> BiomeSelection.haveCategories(context, Category.NETHER))
                && RepurposedStructures.RSAllConfig.RSRuinsConfig.ruinsNetherAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.RUINS_NETHER));

        GeneralUtils.addToBiome("ruins_land_warm",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.RUINS_LAND_WARM,
                                () -> BiomeSelection.haveCategories(context, Category.PLAINS, Category.FOREST, Category.TAIGA, Category.SWAMP)
                                && !BiomeSelection.hasName(context, "snow", "ice", "frozen")
                                && context.getBiome().getTemperature() >= 0.25f)
                        && RepurposedStructures.RSAllConfig.RSRuinsConfig.ruinsLandWarmAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.RUINS_LAND_WARM));

        GeneralUtils.addToBiome("ruins_land_hot",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.RUINS_LAND_HOT,
                                () -> BiomeSelection.haveCategories(context, Category.DESERT))
                        && RepurposedStructures.RSAllConfig.RSRuinsConfig.ruinsLandHotAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.RUINS_LAND_HOT));
        // regexpos1
    }
}
