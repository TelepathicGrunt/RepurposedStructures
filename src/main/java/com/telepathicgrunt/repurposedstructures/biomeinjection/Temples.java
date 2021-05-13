package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.biome.Biome.Category;

public class Temples {

    public static void addTemples() {

        GeneralUtils.addToBiome("nether_basalt_temple",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "basalt", "blackstone")
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherBasaltTempleMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE)
                        && BiomeSelection.isBiomeAllowed(context, "temples")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherBasaltTempleToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_BASALT_TEMPLE));

        GeneralUtils.addToBiome("nether_crimson_temple",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "crimson", "red_")
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherCrimsonTempleMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE)
                        && BiomeSelection.isBiomeAllowed(context, "temples")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherCrimsonTempleToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_CRIMSON_TEMPLE));

        GeneralUtils.addToBiome("nether_warped_temple",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "warped", "blue")
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWarpedTempleMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE)
                        && BiomeSelection.isBiomeAllowed(context, "temples")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherWarpedTempleToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_WARPED_TEMPLE));

        GeneralUtils.addToBiome("nether_soul_temple",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "soul")
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherSoulTempleMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE)
                        && BiomeSelection.isBiomeAllowed(context, "temples")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherSoulTempleToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_SOUL_TEMPLE));

        GeneralUtils.addToBiome("nether_wasteland_temple",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && !BiomeSelection.hasName(context, "crimson", "red_", "warped", "blue", "soul", "basalt", "blackstone")
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWastelandTempleMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE)
                        && BiomeSelection.isBiomeAllowed(context, "temples")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherWastelandTempleToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_WASTELAND_TEMPLE));

    }
}
