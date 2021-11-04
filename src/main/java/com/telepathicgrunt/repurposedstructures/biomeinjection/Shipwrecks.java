package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biomes;

public final class Shipwrecks {
    private Shipwrecks() {}

    public static void addShipwrecks() {

        GeneralUtils.addToBiome("end_shipwreck",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.SHIPWRECK_END,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.THEEND)
                                && !BiomeSelection.isBiome(context, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS))
                        && RepurposedStructures.RSAllConfig.RSShipwrecksConfig.endShipwreckAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.SHIPWRECK_END));


        //Nether based Shipwrecks
        GeneralUtils.addToBiome("crimson_shipwreck",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.SHIPWRECK_CRIMSON,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.NETHER)
                                && BiomeSelection.hasName(context, "crimson", "red_")
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_SHIPWRECK))
                        && RepurposedStructures.RSAllConfig.RSShipwrecksConfig.crimsonShipwreckAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.SHIPWRECK_CRIMSON));

        GeneralUtils.addToBiome("warped_shipwreck",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.SHIPWRECK_WARPED,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.NETHER)
                                && BiomeSelection.hasName(context, "warped", "blue")
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_SHIPWRECK))
                        && RepurposedStructures.RSAllConfig.RSShipwrecksConfig.warpedShipwreckAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.SHIPWRECK_WARPED));

        GeneralUtils.addToBiome("nether_bricks_shipwreck",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.SHIPWRECK_NETHER_BRICKS,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.NETHER)
                                && BiomeSelection.hasName(context, "soul")
                                && !BiomeSelection.hasName(context, "crimson", "red_", "warped", "blue")
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_SHIPWRECK))
                        && RepurposedStructures.RSAllConfig.RSShipwrecksConfig.netherBricksShipwreckAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.SHIPWRECK_NETHER_BRICKS_FLYING));

        GeneralUtils.addToBiome("nether_bricks_shipwreck_flying",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.SHIPWRECK_NETHER_BRICKS,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.NETHER)
                                && !BiomeSelection.hasName(context, "crimson", "red_", "warped", "blue", "soul")
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_SHIPWRECK))
                && RepurposedStructures.RSAllConfig.RSShipwrecksConfig.netherBricksShipwreckAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.SHIPWRECK_NETHER_BRICKS));

    }
}
