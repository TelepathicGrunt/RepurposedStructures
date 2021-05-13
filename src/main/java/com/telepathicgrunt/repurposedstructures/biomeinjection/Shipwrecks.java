package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BiomeKeys;

public class Shipwrecks {

    public static void addShipwrecks() {

        GeneralUtils.addToBiome("end_shipwreck",
                (context) -> (BiomeSelection.hasNamespace(context, "minecraft") ||
                            RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.addEndShipwreckToModdedBiomes)
                        && BiomeSelection.haveCategories(context, Category.THEEND)
                        && !BiomeSelection.isBiome(context, BiomeKeys.THE_END, BiomeKeys.SMALL_END_ISLANDS, BiomeKeys.END_BARRENS)
                        && RepurposedStructures.RSAllConfig.RSShipwrecksConfig.maxChunkDistance.endShipwreckMaxChunkDistance != 1001
                        && BiomeSelection.isBiomeAllowed(context, "shipwrecks"),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.END_SHIPWRECK));


        //Nether based Shipwrecks
        GeneralUtils.addToBiome("crimson_shipwreck",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "crimson", "red_")
                        && RepurposedStructures.RSAllConfig.RSShipwrecksConfig.maxChunkDistance.crimsonShipwreckMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_SHIPWRECK)
                        && BiomeSelection.isBiomeAllowed(context, "shipwrecks")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.addCrimsonShipwreckToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.CRIMSON_SHIPWRECK));

        GeneralUtils.addToBiome("warped_shipwreck",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "warped", "blue")
                        && RepurposedStructures.RSAllConfig.RSShipwrecksConfig.maxChunkDistance.warpedShipwreckMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_SHIPWRECK)
                        && BiomeSelection.isBiomeAllowed(context, "shipwrecks")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.addWarpedShipwreckToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.WARPED_SHIPWRECK));

        GeneralUtils.addToBiome("nether_bricks_shipwreck",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "soul")
                        && !BiomeSelection.hasName(context, "crimson", "red_", "warped", "blue")
                        && RepurposedStructures.RSAllConfig.RSShipwrecksConfig.maxChunkDistance.netherBricksShipwreckMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_SHIPWRECK)
                        && BiomeSelection.isBiomeAllowed(context, "shipwrecks")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.addNetherBricksShipwreckToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_BRICKS_SHIPWRECK_FLYING));

        GeneralUtils.addToBiome("nether_bricks_shipwreck_flying",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && !BiomeSelection.hasName(context, "crimson", "red_", "warped", "blue", "soul")
                        && RepurposedStructures.RSAllConfig.RSShipwrecksConfig.maxChunkDistance.netherBricksShipwreckMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_SHIPWRECK)
                        && BiomeSelection.isBiomeAllowed(context, "shipwrecks")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.addNetherBricksShipwreckToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_BRICKS_SHIPWRECK));

    }
}
