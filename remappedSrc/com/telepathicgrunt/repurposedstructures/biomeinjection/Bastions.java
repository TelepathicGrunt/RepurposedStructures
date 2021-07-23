package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.level.biome.Biome.BiomeCategory;

public class Bastions {

    public static void addBastions() {

        GeneralUtils.addToBiome("bastion_underground",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.BASTION_UNDERGROUND,
                                () -> !BiomeSelection.haveCategories(context, BiomeCategory.OCEAN, BiomeCategory.BEACH, BiomeCategory.NETHER, BiomeCategory.NONE, BiomeCategory.THEEND))
                        && RepurposedStructures.RSAllConfig.RSBastionsConfig.bastionUndergroundAverageChunkDistance != 10001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.BASTION_UNDERGROUND));
    }
}
