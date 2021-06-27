package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;

import java.util.function.Supplier;

public class Dungeons {

    public static void addDungeons() {

        GeneralUtils.addToBiome("jungle_dungeon",
                (context) ->
                        genericDungeonCheck(context, RSConfiguredFeatures.JUNGLE_DUNGEONS,
                                () -> BiomeSelection.haveCategories(context, Category.JUNGLE))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.jungleDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.JUNGLE_DUNGEONS));

        GeneralUtils.addToBiome("badlands_dungeon",
                (context) ->
                        genericDungeonCheck(context, RSConfiguredFeatures.BADLANDS_DUNGEONS,
                                () -> BiomeSelection.haveCategories(context, Category.MESA))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.badlandsDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.BADLANDS_DUNGEONS));

        GeneralUtils.addToBiome("dark_forest_dungeons",
                (context) ->
                        genericDungeonCheck(context, RSConfiguredFeatures.DARK_FOREST_DUNGEONS,
                                () -> BiomeSelection.haveCategories(context, Category.FOREST)
                                && BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted"))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.darkForestDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.DARK_FOREST_DUNGEONS));

        GeneralUtils.addToBiome("desert_dungeons",
                (context) ->
                        genericDungeonCheck(context, RSConfiguredFeatures.DESERT_DUNGEONS,
                                () -> BiomeSelection.haveCategories(context, Category.DESERT))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.desertDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.DESERT_DUNGEONS));

        GeneralUtils.addToBiome("high_mushroom_dungeons",
                (context) -> 
                        genericDungeonCheck(context, RSConfiguredFeatures.MUSHROOM_HIGH_DUNGEONS,
                                () -> BiomeSelection.haveCategories(context, Category.MUSHROOM))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.mushroomDungeonAttemptsPerChunk != 0,
                context -> {
                    if(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight > 62)
                        context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.MUSHROOM_HIGH_DUNGEONS);
                });

        GeneralUtils.addToBiome("low_mushroom_dungeons",
                (context) -> 
                        genericDungeonCheck(context, RSConfiguredFeatures.MUSHROOM_LOW_DUNGEONS,
                                () -> BiomeSelection.haveCategories(context, Category.MUSHROOM))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.mushroomDungeonAttemptsPerChunk != 0,
                context -> {
                    if(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight <= 62)
                        context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.MUSHROOM_LOW_DUNGEONS);
                });

        GeneralUtils.addToBiome("swamp_dungeons",
                (context) -> 
                        genericDungeonCheck(context, RSConfiguredFeatures.SWAMP_DUNGEONS,
                                () -> BiomeSelection.haveCategories(context, Category.SWAMP))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.swampDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.SWAMP_DUNGEONS));

        GeneralUtils.addToBiome("snow_dungeons",
                (context) -> 
                        genericDungeonCheck(context, RSConfiguredFeatures.SNOW_DUNGEONS,
                                () -> BiomeSelection.haveCategories(context, Category.ICY)
                                // inverted icy check
                                && !(BiomeSelection.hasName(context, "icy", "ice", "frozen") ||
                                (context.getBiome().getTemperature() < 0 && !BiomeSelection.hasName(context, "snow"))))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.snowDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.SNOW_DUNGEONS));

        GeneralUtils.addToBiome("icy_dungeons",
                (context) -> 
                        genericDungeonCheck(context, RSConfiguredFeatures.ICY_DUNGEONS,
                                () -> BiomeSelection.haveCategories(context, Category.ICY)
                                && (BiomeSelection.hasName(context, "icy", "ice", "frozen") ||
                                (context.getBiome().getTemperature() < 0 && !BiomeSelection.hasName(context, "snow"))))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.icyDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.ICY_DUNGEONS));

        // Vegetal to match Nether Mineshafts
        GeneralUtils.addToBiome("nether_dungeons",
                (context) -> 
                        genericDungeonCheck(context, RSConfiguredFeatures.NETHER_DUNGEONS,
                                () -> BiomeSelection.haveCategories(context, Category.NETHER))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.netherDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSConfiguredFeatures.NETHER_DUNGEONS));


        GeneralUtils.addToBiome("end_dungeons",
                (context) -> 
                        genericDungeonCheck(context, RSConfiguredFeatures.END_DUNGEONS,
                                () -> BiomeSelection.haveCategories(context, Category.THEEND)
                                && !BiomeSelection.isBiome(context, BiomeKeys.THE_END, BiomeKeys.SMALL_END_ISLANDS))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.endDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.END_DUNGEONS));

        // Have to do this abomination to get neutral ocean dungeons only in biomes that the other ocean dungeon types won't touch.
        // All due to BiomeModification API being per feature instead of an event like Forge's Biome Modification event.
        GeneralUtils.addToBiome("ocean_neutral_dungeons",
                (context) -> 
                        genericDungeonCheck(context, RSConfiguredFeatures.OCEAN_NEUTRAL_DUNGEONS,
                                () -> BiomeSelection.haveCategories(context, Category.OCEAN)
                                && (!BiomeSelection.hasName(context, "cold", "frozen", "snow", "ice", "warm", "hot", "tropic", "lukewarm") || // Thanks to vanilla oceans all being same temperature...
                                (!BiomeSelection.hasNamespace(context, "minecraft")
                                        && context.getBiome().getTemperature() >= 0.5f && context.getBiome().getTemperature() < 0.9f)))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.OCEAN_NEUTRAL_DUNGEONS));

        GeneralUtils.addToBiome("ocean_cold_dungeons",
                (context) -> 
                        genericDungeonCheck(context, RSConfiguredFeatures.OCEAN_COLD_DUNGEONS,
                                () -> BiomeSelection.haveCategories(context, Category.OCEAN)
                                && (BiomeSelection.hasName(context, "cold") || // Thanks to vanilla oceans all being same temperature...
                                (!BiomeSelection.hasNamespace(context, "minecraft")
                                        && context.getBiome().getTemperature() >= 0.0f && context.getBiome().getTemperature() < 0.5f)))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.OCEAN_COLD_DUNGEONS));

        GeneralUtils.addToBiome("ocean_frozen_dungeons",
                (context) -> 
                        genericDungeonCheck(context, RSConfiguredFeatures.OCEAN_FROZEN_DUNGEONS,
                                () -> BiomeSelection.haveCategories(context, Category.OCEAN)
                                && (BiomeSelection.hasName(context, "frozen", "snow", "ice") || // Thanks to vanilla oceans all being same temperature...
                                (!BiomeSelection.hasNamespace(context, "minecraft")
                                        && context.getBiome().getTemperature() < 0f)))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.OCEAN_FROZEN_DUNGEONS));

        GeneralUtils.addToBiome("ocean_lukewarm_dungeons",
                (context) -> 
                        genericDungeonCheck(context, RSConfiguredFeatures.OCEAN_LUKEWARM_DUNGEONS,
                                () -> BiomeSelection.haveCategories(context, Category.OCEAN)
                                && (BiomeSelection.hasName(context, "lukewarm") || // Thanks to vanilla oceans all being same temperature...
                                (!BiomeSelection.hasNamespace(context, "minecraft")
                                        && context.getBiome().getTemperature() >= 0.9f && context.getBiome().getTemperature() < 1.5f)))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.OCEAN_LUKEWARM_DUNGEONS));

        GeneralUtils.addToBiome("ocean_warm_dungeons",
                (context) -> 
                        genericDungeonCheck(context, RSConfiguredFeatures.OCEAN_WARM_DUNGEONS,
                                () -> BiomeSelection.haveCategories(context, Category.OCEAN)
                                && (BiomeSelection.hasName(context, "warm", "hot", "tropic") || // Thanks to vanilla oceans all being same temperature...
                                (!BiomeSelection.hasNamespace(context, "minecraft")
                                        && context.getBiome().getTemperature() >= 1.5f)))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.OCEAN_WARM_DUNGEONS));


        //Remove vanilla dungeons from non-ocean/nether/end biomes we added our dungeons to
        BiomeModifications.create(new Identifier(RepurposedStructures.MODID, "remove_vanilla_dungeons")).add(
                ModificationPhase.REMOVALS,
                context -> RSConfiguredFeatures.RS_DUNGEONS.stream().anyMatch(dungeon ->
                        dungeon != RSConfiguredFeatures.OCEAN_NEUTRAL_DUNGEONS &&
                        dungeon != RSConfiguredFeatures.OCEAN_COLD_DUNGEONS &&
                        dungeon != RSConfiguredFeatures.OCEAN_FROZEN_DUNGEONS &&
                        dungeon != RSConfiguredFeatures.OCEAN_LUKEWARM_DUNGEONS &&
                        dungeon != RSConfiguredFeatures.OCEAN_WARM_DUNGEONS &&
                        dungeon != RSConfiguredFeatures.NETHER_DUNGEONS &&
                        dungeon != RSConfiguredFeatures.END_DUNGEONS &&
                        context.hasBuiltInFeature(dungeon)),
                context -> context.getGenerationSettings().removeBuiltInFeature(ConfiguredFeatures.MONSTER_ROOM));
    }

    private static boolean genericDungeonCheck(BiomeSelectionContext context, ConfiguredFeature<?,?> configuredFeatures, Supplier<Boolean> condition) {
        return RSConfiguredFeatures.RS_DUNGEONS.stream().noneMatch(context::hasBuiltInFeature)
                && BiomeSelection.isBiomeAllowed(context, configuredFeatures, condition);
    }
}
