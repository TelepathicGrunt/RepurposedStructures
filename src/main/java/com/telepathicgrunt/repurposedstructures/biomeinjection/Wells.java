package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSWellsConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.function.Supplier;

public final class Wells {
    private Wells() {}

    public static void addWells(BiomeLoadingEvent event) {

        if (RSWellsConfig.wellBadlandsRarityPerChunk.get() != 10000 &&
                    genericWellCheck(event, RSConfiguredFeatures.BADLANDS_WELL_PLACED,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.MESA)))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.BADLANDS_WELL_PLACED);
        }
        
        if (RSWellsConfig.wellNetherRarityPerChunk.get() != 10000 &&
            genericWellCheck(event, RSConfiguredFeatures.NETHER_WELL_PLACED,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER)))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.NETHER_WELL_PLACED);
        }

        if (RSWellsConfig.wellSnowRarityPerChunk.get() != 10000 &&
            genericWellCheck(event, RSConfiguredFeatures.SNOW_WELL_PLACED,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.ICY) ||
                    BiomeSelection.hasName(event, "snow")))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.SNOW_WELL_PLACED);
        }

        if (RSWellsConfig.wellMossyStoneRarityPerChunk.get() != 10000 &&
            genericWellCheck(event, RSConfiguredFeatures.MOSSY_STONE_WELL_PLACED,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.SWAMP, Biome.BiomeCategory.JUNGLE) ||
                    (BiomeSelection.haveCategories(event, Biome.BiomeCategory.FOREST) && BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted"))))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.MOSSY_STONE_WELL_PLACED);
        }

        if (RSWellsConfig.wellForestRarityPerChunk.get() != 10000 &&
            genericWellCheck(event, RSConfiguredFeatures.FOREST_WELL_PLACED,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.FOREST) &&
                    !BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted")))
        {

            event.getGeneration().getFeatures(GenerationStep.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.FOREST_WELL_PLACED);
        }

        if (RSWellsConfig.wellMushroomRarityPerChunk.get() != 10000 &&
            genericWellCheck(event, RSConfiguredFeatures.MUSHROOM_WELL_PLACED,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.MUSHROOM)))
        {

            event.getGeneration().getFeatures(GenerationStep.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.MUSHROOM_WELL_PLACED);
        }
    }

    private static boolean genericWellCheck(BiomeLoadingEvent context, PlacedFeature placedFeature, Supplier<Boolean> condition) {
        return BiomeSelection.isBiomeAllowed(context, placedFeature,
                () -> RSConfiguredFeatures.RS_WELLS.stream().noneMatch(pf -> BiomeSelection.hasFeature(context, pf)) && condition.get());
    }
}
