package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.biome.Biome.Category;

public class Bastions {

    public static void addBastions() {

        GeneralUtils.addToBiome("bastion_underground",
                (context) -> !BiomeSelection.haveCategories(context, Category.OCEAN, Category.BEACH, Category.NETHER, Category.NONE, Category.THEEND)
						&& BiomeSelection.isBiomeAllowed(context, "underground_bastions")
                        && RepurposedStructures.RSAllConfig.RSBastionsConfig.maxChunkDistance.bastionUndergroundMaxChunkDistance != 10001
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSBastionsConfig.blacklist.addBastionUndergroundToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.BASTION_UNDERGROUND));
        // regexpos1
    }
}
