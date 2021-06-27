package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.feature.StructureFeature;

public class Cities {

    public static void addCities() {
        GeneralUtils.addToBiome("city_nether",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.CITY_NETHER,
                            () -> BiomeSelection.haveCategories(context, Category.NETHER))
                        && RepurposedStructures.RSAllConfig.RSCitiesConfig.cityNetherAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.CITY_NETHER));
    }
}
