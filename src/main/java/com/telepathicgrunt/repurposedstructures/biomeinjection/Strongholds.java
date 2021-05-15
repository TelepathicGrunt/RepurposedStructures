package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStep;

public class Strongholds {

    public static void addStrongholds() {
        GeneralUtils.addToBiome("nether_stronghold",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.STRONGHOLD)
                        && BiomeSelection.isBiomeAllowed(context, "strongholds")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.addNetherStrongholdToModdedBiomes),
                context -> {
                    context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_STRONGHOLD);
                    context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.STRONGHOLDS, RSConfiguredFeatures.NETHER_STRONGHOLD_CHAINS);
                });
    }
}
