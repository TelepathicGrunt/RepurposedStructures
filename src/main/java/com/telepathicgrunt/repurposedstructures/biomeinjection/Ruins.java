package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.biome.Biome.Category;

public class Ruins {

    public static void addRuins() {
        GeneralUtils.addToBiome("ruins_nether",
                (context) -> BiomeSelection.isBiomeAllowed(context, "ruins")
                        && RepurposedStructures.RSAllConfig.RSMainConfig.ruins.ruinsNetherMaxChunkDistance != 1001
                        && BiomeSelection.haveCategories(context, Category.NETHER)
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSMainConfig.ruins.addRuinsNetherToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.RUINS_NETHER));

        GeneralUtils.addToBiome("ruins_land_warm",
                (context) -> BiomeSelection.haveCategories(context, Category.PLAINS, Category.FOREST, Category.TAIGA)
                        && !BiomeSelection.hasName(context, "snow", "ice", "frozen")
						&& context.getBiome().getTemperature() >= 0.25f
                        && BiomeSelection.isBiomeAllowed(context, "ruins")
                        && RepurposedStructures.RSAllConfig.RSMainConfig.ruins.ruinsLandWarmMaxChunkDistance != 1001
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSMainConfig.ruins.addRuinsLandWarmToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.RUINS_LAND_WARM));

        GeneralUtils.addToBiome("ruins_land_hot",
                (context) -> BiomeSelection.haveCategories(context, Category.DESERT)
						&& BiomeSelection.isBiomeAllowed(context, "ruins")
                        && RepurposedStructures.RSAllConfig.RSMainConfig.ruins.ruinsLandHotMaxChunkDistance != 1001
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSMainConfig.ruins.addRuinsLandHotToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.RUINS_LAND_HOT));
        // regexpos1
    }
}
