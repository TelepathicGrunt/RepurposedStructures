package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.function.Supplier;

public class Wells {

    public static void addWells() {

        GeneralUtils.addToBiome("badlands_well",
                (context) -> 
                        genericWellCheck(context, RSConfiguredFeatures.BADLANDS_WELL,
                                () -> BiomeSelection.haveCategories(context, Category.MESA))
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.badlandsWellRarityPerChunk != 10000,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.BADLANDS_WELL));

        GeneralUtils.addToBiome("nether_well",
                (context) -> 
                        genericWellCheck(context, RSConfiguredFeatures.NETHER_WELL,
                                () -> BiomeSelection.haveCategories(context, Category.NETHER))
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.netherWellRarityPerChunk != 10000,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.NETHER_WELL));

        GeneralUtils.addToBiome("snow_well",
                (context) -> 
                        genericWellCheck(context, RSConfiguredFeatures.SNOW_WELL,
                                () -> BiomeSelection.haveCategories(context, Category.ICY) || BiomeSelection.hasName(context, "snow"))
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.snowWellRarityPerChunk != 10000,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.SNOW_WELL));

        GeneralUtils.addToBiome("mossy_stone_well",
                (context) -> 
                        genericWellCheck(context, RSConfiguredFeatures.MOSSY_STONE_WELL,
                                () ->  BiomeSelection.haveCategories(context, Category.SWAMP, Category.JUNGLE)
                                        || (BiomeSelection.haveCategories(context, Category.FOREST) && BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted")))
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.mossyStoneWellRarityPerChunk != 10000,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.MOSSY_STONE_WELL));

        GeneralUtils.addToBiome("forest_well",
                (context) -> 
                        genericWellCheck(context, RSConfiguredFeatures.FOREST_WELL,
                                () -> BiomeSelection.haveCategories(context, Category.FOREST)
                                        && !BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted"))
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.forestWellRarityPerChunk != 10000,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.FOREST_WELL));

        GeneralUtils.addToBiome("mushroom_well",
                (context) -> 
                        genericWellCheck(context, RSConfiguredFeatures.MUSHROOM_WELL,
                                () -> BiomeSelection.haveCategories(context, Category.MUSHROOM))
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.mushroomWellRarityPerChunk != 10000,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.MUSHROOM_WELL));
    }

    private static boolean genericWellCheck(BiomeSelectionContext context, ConfiguredFeature<?,?> configuredFeatures, Supplier<Boolean> condition) {
        return RSConfiguredFeatures.RS_WELLS.stream().noneMatch(context::hasBuiltInFeature)
                && BiomeSelection.isBiomeAllowed(context, configuredFeatures, condition);
    }
}
