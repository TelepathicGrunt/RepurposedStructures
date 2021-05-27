package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStep;

public class Wells {

    public static void addWells() {

        GeneralUtils.addToBiome("badlands_well",
                (context) -> genericWellCheck(context)
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.badlandsWellRarityPerChunk != 10000
                        && BiomeSelection.haveCategories(context, Category.MESA),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.BADLANDS_WELL));

        GeneralUtils.addToBiome("nether_well",
                (context) -> genericWellCheck(context)
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.netherWellRarityPerChunk != 10000
                        && BiomeSelection.haveCategories(context, Category.NETHER),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.NETHER_WELL));

        GeneralUtils.addToBiome("snow_well",
                (context) -> genericWellCheck(context)
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.snowWellRarityPerChunk != 10000
                        && (BiomeSelection.haveCategories(context, Category.ICY) || BiomeSelection.hasName(context, "snow")),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.SNOW_WELL));

        GeneralUtils.addToBiome("mossy_stone_well",
                (context) -> genericWellCheck(context)
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.mossyStoneWellRarityPerChunk != 10000
                        && (BiomeSelection.haveCategories(context, Category.SWAMP, Category.JUNGLE)
                            || (BiomeSelection.haveCategories(context, Category.FOREST) && BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted"))),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.MOSSY_STONE_WELL));

        GeneralUtils.addToBiome("forest_well",
                (context) -> genericWellCheck(context)
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.forestWellRarityPerChunk != 10000
                        && BiomeSelection.haveCategories(context, Category.FOREST)
                        && !BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted"),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.FOREST_WELL));

        GeneralUtils.addToBiome("mushroom_well",
                (context) -> genericWellCheck(context)
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.mushroomWellRarityPerChunk != 10000
                        && BiomeSelection.haveCategories(context, Category.MUSHROOM),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.MUSHROOM_WELL));
    }

    private static boolean genericWellCheck(BiomeSelectionContext context) {
        return RSConfiguredFeatures.RS_WELLS.stream().noneMatch(context::hasBuiltInFeature)
                && BiomeSelection.isBiomeAllowed(context, "wells")
                && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSWellsConfig.addWellsToModdedBiomes);
    }
}
