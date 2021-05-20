package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStep;

public class Fortresses {

    public static void addJungleFortress() {

        GeneralUtils.addToBiome("jungle_fortress",
                (context) -> BiomeSelection.haveCategories(context, Category.JUNGLE)
                        && RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.jungleFortressMaxChunkDistance != 1001
                        && BiomeSelection.isBiomeAllowed(context, "fortresses")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.addJungleFortressToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.JUNGLE_FORTRESS));
    }
}
