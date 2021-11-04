package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSWellsConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.function.Supplier;

public final class Wells {
    private Wells() {}

    public static void addWells(BiomeLoadingEvent event) {

        if (RSWellsConfig.badlandsWellRarityPerChunk.get() != 10000 &&
                    genericWellCheck(event, RSConfiguredFeatures.BADLANDS_WELL,
                    () -> BiomeSelection.haveCategories(event, Category.MESA)))
        {
            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.BADLANDS_WELL);
        }
        
        if (RSWellsConfig.netherWellRarityPerChunk.get() != 10000 &&
            genericWellCheck(event, RSConfiguredFeatures.NETHER_WELL,
                    () -> BiomeSelection.haveCategories(event, Category.NETHER)))
        {
            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.NETHER_WELL);
        }

        if (RSWellsConfig.snowWellRarityPerChunk.get() != 10000 &&
            genericWellCheck(event, RSConfiguredFeatures.SNOW_WELL,
                    () -> BiomeSelection.haveCategories(event, Category.ICY) ||
                    BiomeSelection.hasName(event, "snow")))
        {
            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.SNOW_WELL);
        }

        if (RSWellsConfig.mossyStoneWellRarityPerChunk.get() != 10000 &&
            genericWellCheck(event, RSConfiguredFeatures.MOSSY_STONE_WELL,
                    () -> BiomeSelection.haveCategories(event, Category.SWAMP, Category.JUNGLE) ||
                    (BiomeSelection.haveCategories(event, Category.FOREST) && BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted"))))
        {
            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.MOSSY_STONE_WELL);
        }

        if (RSWellsConfig.forestWellRarityPerChunk.get() != 10000 &&
            genericWellCheck(event, RSConfiguredFeatures.FOREST_WELL,
                    () -> BiomeSelection.haveCategories(event, Category.FOREST) &&
                    !BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted")))
        {

            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.FOREST_WELL);
        }

        if (RSWellsConfig.mushroomWellRarityPerChunk.get() != 10000 &&
            genericWellCheck(event, RSConfiguredFeatures.MUSHROOM_WELL,
                    () -> BiomeSelection.haveCategories(event, Category.MUSHROOM)))
        {

            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.MUSHROOM_WELL);
        }
    }

    private static boolean genericWellCheck(BiomeLoadingEvent context, ConfiguredFeature<?,?> configuredFeature, Supplier<Boolean> condition) {
        return BiomeSelection.isBiomeAllowed(context, configuredFeature,
                () -> RSConfiguredFeatures.RS_WELLS.stream().noneMatch(cf -> BiomeSelection.hasFeature(context, cf)) && condition.get());
    }
}
