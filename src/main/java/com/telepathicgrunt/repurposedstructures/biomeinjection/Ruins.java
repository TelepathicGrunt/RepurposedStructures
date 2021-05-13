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
    }
}
