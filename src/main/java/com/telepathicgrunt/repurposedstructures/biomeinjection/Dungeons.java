package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.google.gson.JsonElement;
import com.telepathicgrunt.repurposedstructures.configs.RSDungeonsConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.CodecCache;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Optional;
import java.util.function.Supplier;

public final class Dungeons {
    private Dungeons() {}

    public static void addDungeons(BiomeLoadingEvent event) {

        if (RSDungeonsConfig.jungleDungeonAttemptsPerChunk.get() != 0 &&
            BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.JUNGLE_DUNGEONS_PLACED,
                () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.JUNGLE)))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).add(() -> RSConfiguredFeatures.JUNGLE_DUNGEONS_PLACED);
        }
        
        if (RSDungeonsConfig.badlandsDungeonAttemptsPerChunk.get() != 0 &&
            BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.BADLANDS_DUNGEONS_PLACED,
                () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.MESA)))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).add(() -> RSConfiguredFeatures.BADLANDS_DUNGEONS_PLACED);
        }
        
        if (RSDungeonsConfig.darkForestDungeonAttemptsPerChunk.get() != 0 &&
            BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.DARK_FOREST_DUNGEONS_PLACED,
                () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.FOREST) &&
                BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony")))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).add(() -> RSConfiguredFeatures.DARK_FOREST_DUNGEONS_PLACED);
        }

        if (RSDungeonsConfig.desertDungeonAttemptsPerChunk.get() != 0 &&
            BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.DESERT_DUNGEONS_PLACED,
                () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.DESERT)))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).add(() -> RSConfiguredFeatures.DESERT_DUNGEONS_PLACED);
        }

        if (RSDungeonsConfig.deepDungeonAttemptsPerChunk.get() != 0 &&
            BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.DEEP_DUNGEONS_PLACED,
                () -> !BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER, Biome.BiomeCategory.THEEND, Biome.BiomeCategory.NONE)))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).add(() -> RSConfiguredFeatures.DEEP_DUNGEONS_PLACED);
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).removeIf(placedFeatureSupplier -> GeneralUtils.serializeAndCompareFeature(placedFeatureSupplier.get(), CavePlacements.MONSTER_ROOM_DEEP));
        }

        if (RSDungeonsConfig.mushroomDungeonAttemptsPerChunk.get() != 0 &&
            BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.MUSHROOM_DUNGEONS_PLACED,
                () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.MUSHROOM)))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).add(() -> RSConfiguredFeatures.MUSHROOM_DUNGEONS_PLACED);
        }
        
        if (RSDungeonsConfig.swampDungeonAttemptsPerChunk.get() != 0 &&
            BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.SWAMP_DUNGEONS_PLACED,
                () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.SWAMP)))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).add(() -> RSConfiguredFeatures.SWAMP_DUNGEONS_PLACED);
        }

        if (RSDungeonsConfig.icyDungeonAttemptsPerChunk.get() != 0 &&
            BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.ICY_DUNGEONS_PLACED,
                () -> BiomeSelection.hasName(event, "icy", "ice", "frozen", "glacier", "glacial") &&
                    (BiomeSelection.haveCategories(event, Biome.BiomeCategory.ICY) ||
                    (BiomeSelection.haveCategories(event, Biome.BiomeCategory.MOUNTAIN) && event.getClimate().temperature < 0))))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).add(() -> RSConfiguredFeatures.ICY_DUNGEONS_PLACED);
        }
        
        if (RSDungeonsConfig.snowDungeonAttemptsPerChunk.get() != 0 &&
            BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.SNOW_DUNGEONS_PLACED,
                () -> !BiomeSelection.hasName(event, "icy", "ice", "frozen", "glacier", "glacial") &&
                    (BiomeSelection.hasName(event, "snow") ||
                    BiomeSelection.haveCategories(event, Biome.BiomeCategory.ICY) ||
                    (BiomeSelection.haveCategories(event, Biome.BiomeCategory.MOUNTAIN) && event.getClimate().temperature < 0))))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).add(() -> RSConfiguredFeatures.SNOW_DUNGEONS_PLACED);
        }
        
        if (RSDungeonsConfig.netherDungeonAttemptsPerChunk.get() != 0 &&
            BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.NETHER_DUNGEONS_PLACED,
                () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER)))
        {
            // Vegetal to match Nether Mineshafts
            event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> RSConfiguredFeatures.NETHER_DUNGEONS_PLACED);
        }
        
        if (RSDungeonsConfig.endDungeonAttemptsPerChunk.get() != 0 &&
            BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.END_DUNGEONS_PLACED,
                () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.THEEND) &&
                !BiomeSelection.isBiome(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS)))
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).add(() -> RSConfiguredFeatures.END_DUNGEONS_PLACED);
        }

        if (RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get() != 0)
        {
            // Thanks to vanilla oceans all being same temperature, we have to use the has Namespace to correctly get them.
            if(BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.OCEAN_LUKEWARM_DUNGEONS_PLACED,
                () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.OCEAN) &&
                (BiomeSelection.hasName(event, "lukewarm") ||
                (!BiomeSelection.hasNamespace(event, "minecraft") && event.getClimate().temperature >= 0.9f && event.getClimate().temperature < 1.5f))))
            {
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).add(() -> RSConfiguredFeatures.OCEAN_LUKEWARM_DUNGEONS_PLACED);
            }

            if(BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.OCEAN_WARM_DUNGEONS_PLACED,
                () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.OCEAN) &&
                ((BiomeSelection.hasName(event, "hot", "tropic", "warm") && !BiomeSelection.hasName(event, "lukewarm")) ||
                (!BiomeSelection.hasNamespace(event, "minecraft") && event.getClimate().temperature >= 1.5f))))
            {
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).add(() -> RSConfiguredFeatures.OCEAN_WARM_DUNGEONS_PLACED);
            }

            if(BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.OCEAN_COLD_DUNGEONS_PLACED,
                () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.OCEAN) &&
                (BiomeSelection.hasName(event, "cold") ||
                (!BiomeSelection.hasNamespace(event, "minecraft") && event.getClimate().temperature >= 0.0f && event.getClimate().temperature < 0.5f))))
            {
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).add(() -> RSConfiguredFeatures.OCEAN_COLD_DUNGEONS_PLACED);
            }

            if(BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.OCEAN_FROZEN_DUNGEONS_PLACED,
                () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.OCEAN) &&
                (BiomeSelection.hasName(event, "frozen", "snow", "ice") ||
                (!BiomeSelection.hasNamespace(event, "minecraft") && event.getClimate().temperature >= 0.9f && event.getClimate().temperature < 1.5f))))
            {
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).add(() -> RSConfiguredFeatures.OCEAN_FROZEN_DUNGEONS_PLACED);
            }

            if(BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.OCEAN_NEUTRAL_DUNGEONS_PLACED,
                () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.OCEAN) &&
                (!BiomeSelection.hasName(event, "hot", "tropic", "warm", "cold", "frozen", "snow", "ice") ||
                (!BiomeSelection.hasNamespace(event, "minecraft") && event.getClimate().temperature >= 0.5f && event.getClimate().temperature < 0.9f))))
            {
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).add(() -> RSConfiguredFeatures.OCEAN_NEUTRAL_DUNGEONS_PLACED);
            }
        }
    }
}
