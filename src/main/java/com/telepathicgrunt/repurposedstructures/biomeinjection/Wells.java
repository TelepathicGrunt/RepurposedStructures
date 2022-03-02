package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSWellsConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public final class Wells {
    private Wells() {}

    public static void addWells(BiomeLoadingEvent event) {

        if (RSWellsConfig.wellBadlandsRarityPerChunk.get() != 10000 &&
                BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.BADLANDS_WELL_PLACED,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.MESA)))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.SURFACE_STRUCTURES)
                    .add(Holder.direct(RSConfiguredFeatures.BADLANDS_WELL_PLACED));
        }
        
        if (RSWellsConfig.wellNetherRarityPerChunk.get() != 10000 &&
                BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.NETHER_WELL_PLACED,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER)))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.SURFACE_STRUCTURES)
                    .add(Holder.direct(RSConfiguredFeatures.NETHER_WELL_PLACED));
        }

        if (RSWellsConfig.wellSnowRarityPerChunk.get() != 10000 &&
                BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.SNOW_WELL_PLACED,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.ICY) ||
                    BiomeSelection.hasName(event, "snow")))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.SURFACE_STRUCTURES)
                    .add(Holder.direct(RSConfiguredFeatures.SNOW_WELL_PLACED));
        }

        if (RSWellsConfig.wellMossyStoneRarityPerChunk.get() != 10000 &&
                BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.MOSSY_STONE_WELL_PLACED,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.SWAMP, Biome.BiomeCategory.JUNGLE) ||
                    (BiomeSelection.haveCategories(event, Biome.BiomeCategory.FOREST) && BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony"))))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.SURFACE_STRUCTURES)
                    .add(Holder.direct(RSConfiguredFeatures.MOSSY_STONE_WELL_PLACED));
        }

        if (RSWellsConfig.wellForestRarityPerChunk.get() != 10000 &&
                BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.FOREST_WELL_PLACED,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.FOREST) &&
                    !BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony")))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.SURFACE_STRUCTURES)
                    .add(Holder.direct(RSConfiguredFeatures.FOREST_WELL_PLACED));
        }

        if (RSWellsConfig.wellMushroomRarityPerChunk.get() != 10000 &&
                BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.MUSHROOM_WELL_PLACED,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.MUSHROOM)))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.SURFACE_STRUCTURES)
                    .add(Holder.direct(RSConfiguredFeatures.MUSHROOM_WELL_PLACED));
        }
    }
}
