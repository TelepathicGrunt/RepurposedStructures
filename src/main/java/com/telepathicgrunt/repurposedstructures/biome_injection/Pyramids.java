package com.telepathicgrunt.repurposedstructures.biome_injection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class Pyramids {

    public static void addPyramids() {

        GeneralUtils.addToBiome("nether_pyramid",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.netherPyramidMaxChunkDistance != 1001
                        && BiomeSelection.isBiomeAllowed(context, "temples")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.addNetherPyramidToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_PYRAMID));

        GeneralUtils.addToBiome("badlands_pyramid",
                (context) -> BiomeSelection.haveCategories(context, Category.MESA)
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.badlandsPyramidMaxChunkDistance != 1001
                        && BiomeSelection.isBiomeAllowed(context, "temples")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.addBadlandsPyramidToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.BADLANDS_PYRAMID));

        GeneralUtils.addToBiome("pyramid_snowy",
                (context) -> (BiomeSelection.haveCategories(context, Category.ICY) || (BiomeSelection.haveCategories(context, Category.TAIGA) && context.getBiome().getPrecipitation() == Biome.Precipitation.SNOW))
                        && BiomeSelection.isBiomeAllowed(context, "pyramids")
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.pyramidSnowyMaxChunkDistance != 1001
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.addPyramidSnowyToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.PYRAMID_SNOWY));
    }
}
