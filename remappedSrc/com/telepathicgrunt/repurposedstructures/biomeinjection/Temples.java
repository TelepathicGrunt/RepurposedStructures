package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.level.biome.Biome.BiomeCategory;

public class Temples {

    public static void addTemples() {

        GeneralUtils.addToBiome("nether_basalt_temple",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.TEMPLE_NETHER_BASALT,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.NETHER)
                                && BiomeSelection.hasName(context, "basalt", "blackstone")
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE))
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.netherBasaltTempleAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_BASALT_TEMPLE));

        GeneralUtils.addToBiome("nether_crimson_temple",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.TEMPLE_NETHER_CRIMSON,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.NETHER)
                                && BiomeSelection.hasName(context, "crimson", "red_")
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE))
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.netherCrimsonTempleAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_CRIMSON_TEMPLE));

        GeneralUtils.addToBiome("nether_warped_temple",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.TEMPLE_NETHER_WARPED,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.NETHER)
                                && BiomeSelection.hasName(context, "warped", "blue")
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE)
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.netherWarpedTempleAverageChunkDistance != 1001),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_WARPED_TEMPLE));

        GeneralUtils.addToBiome("nether_soul_temple",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.TEMPLE_NETHER_SOUL,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.NETHER)
                                && BiomeSelection.hasName(context, "soul")
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE)
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.netherSoulTempleAverageChunkDistance != 1001),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_SOUL_TEMPLE));

        GeneralUtils.addToBiome("nether_wasteland_temple",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.TEMPLE_NETHER_WASTELAND,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.NETHER)
                                && !BiomeSelection.hasName(context, "crimson", "red_", "warped", "blue", "soul", "basalt", "blackstone")
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE))
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.netherWastelandTempleAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_WASTELAND_TEMPLE));

    }
}
