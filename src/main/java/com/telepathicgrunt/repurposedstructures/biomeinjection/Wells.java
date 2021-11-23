package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.function.Supplier;

public final class Wells {
    private Wells() {}

    public static void addWells() {

        GeneralUtils.addToBiome("badlands_well",
                (context) -> 
                        genericWellCheck(context, RSConfiguredFeatures.BADLANDS_WELL,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.MESA))
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.badlandsWellRarityPerChunk != 10000,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, RSConfiguredFeatures.BADLANDS_WELL_PLACED));

        GeneralUtils.addToBiome("nether_well",
                (context) -> 
                        genericWellCheck(context, RSConfiguredFeatures.NETHER_WELL,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.NETHER))
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.netherWellRarityPerChunk != 10000,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, RSConfiguredFeatures.NETHER_WELL_PLACED));

        GeneralUtils.addToBiome("snow_well",
                (context) -> 
                        genericWellCheck(context, RSConfiguredFeatures.SNOW_WELL,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.ICY) || BiomeSelection.hasName(context, "snow") || BiomeSelection.isBiome(context, Biomes.GROVE))
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.snowWellRarityPerChunk != 10000,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, RSConfiguredFeatures.SNOW_WELL_PLACED));

        GeneralUtils.addToBiome("mossy_stone_well",
                (context) -> 
                        genericWellCheck(context, RSConfiguredFeatures.MOSSY_STONE_WELL,
                                () ->  BiomeSelection.haveCategories(context, BiomeCategory.SWAMP, BiomeCategory.JUNGLE)
                                || (BiomeSelection.haveCategories(context, BiomeCategory.FOREST) && BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted")))
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.mossyStoneWellRarityPerChunk != 10000,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, RSConfiguredFeatures.MOSSY_STONE_WELL_PLACED));

        GeneralUtils.addToBiome("forest_well",
                (context) -> 
                        genericWellCheck(context, RSConfiguredFeatures.FOREST_WELL,
                                () -> (BiomeSelection.haveCategories(context, BiomeCategory.FOREST)
                                && !BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted"))
                                || BiomeSelection.isBiome(context, Biomes.MEADOW))
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.forestWellRarityPerChunk != 10000,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, RSConfiguredFeatures.FOREST_WELL_PLACED));

        GeneralUtils.addToBiome("mushroom_well",
                (context) -> 
                        genericWellCheck(context, RSConfiguredFeatures.MUSHROOM_WELL,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.MUSHROOM))
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.mushroomWellRarityPerChunk != 10000,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, RSConfiguredFeatures.MUSHROOM_WELL_PLACED));
    }

    private static boolean genericWellCheck(BiomeSelectionContext context, ConfiguredFeature<?,?> configuredFeatures, Supplier<Boolean> condition) {
        return BiomeSelection.isBiomeAllowed(context, configuredFeatures,
                () -> RSConfiguredFeatures.RS_WELLS.stream().noneMatch(context::hasBuiltInFeature) && condition.get());
    }
}
