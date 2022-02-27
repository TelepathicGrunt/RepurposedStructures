package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public final class Wells {
    private Wells() {}

    public static void addWells() {

        GeneralUtils.addToBiome("badlands_well",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.BADLANDS_WELL_PLACED,
                                () -> BiomeSelection.haveCategories(context, BiomeTags.IS_BADLANDS))
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.badlandsWellRarityPerChunk != 10000,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, RSConfiguredFeatures.BADLANDS_WELL_PLACED));

        GeneralUtils.addToBiome("nether_well",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.NETHER_WELL_PLACED,
                                () -> BiomeSelection.haveCategories(context, BiomeTags.IS_NETHER))
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.netherWellRarityPerChunk != 10000,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, RSConfiguredFeatures.NETHER_WELL_PLACED));

        GeneralUtils.addToBiome("snow_well",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.SNOW_WELL_PLACED,
                                () -> BiomeSelection.haveCategories(context, RSTags.ICY) || BiomeSelection.hasName(context, "snow") || BiomeSelection.isBiome(context, Biomes.GROVE))
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.snowWellRarityPerChunk != 10000,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, RSConfiguredFeatures.SNOW_WELL_PLACED));

        GeneralUtils.addToBiome("mossy_stone_well",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.MOSSY_STONE_WELL_PLACED,
                                () ->  BiomeSelection.haveCategories(context, RSTags.SWAMPS, BiomeTags.IS_JUNGLE)
                                || (BiomeSelection.haveCategories(context, BiomeTags.IS_FOREST) && BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony")))
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.mossyStoneWellRarityPerChunk != 10000,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, RSConfiguredFeatures.MOSSY_STONE_WELL_PLACED));

        GeneralUtils.addToBiome("forest_well",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.FOREST_WELL_PLACED,
                                () -> (BiomeSelection.haveCategories(context, BiomeTags.IS_FOREST)
                                && !BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony"))
                                || BiomeSelection.isBiome(context, Biomes.MEADOW))
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.forestWellRarityPerChunk != 10000,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, RSConfiguredFeatures.FOREST_WELL_PLACED));

        GeneralUtils.addToBiome("mushroom_well",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.MUSHROOM_WELL_PLACED,
                                () -> BiomeSelection.haveCategories(context, RSTags.MUSHROOMS))
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.mushroomWellRarityPerChunk != 10000,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, RSConfiguredFeatures.MUSHROOM_WELL_PLACED));
    }
}
