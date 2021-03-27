package com.telepathicgrunt.repurposedstructures.biome_injection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.biome.Biome.Category;

public class Cities {

    public static void addCities() {
        GeneralUtils.addToBiome("city_nether",
                (context) -> BiomeSelection.isBiomeAllowed(context, "cities")
                        && RepurposedStructures.RSAllConfig.RSMainConfig.cities.cityNetherMaxChunkDistance != 1001
                        && BiomeSelection.haveCategories(context, Category.NETHER)
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSMainConfig.cities.addCityNetherToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.CITY_NETHER));
    }
}
