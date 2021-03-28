package com.telepathicgrunt.repurposedstructures.biome_injection;

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
import net.minecraft.world.gen.feature.ConfiguredFeatures;

public class Dungeons {

    public static void addDungeons() {

        GeneralUtils.addToBiome("jungle_dungeon",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.jungleDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.JUNGLE),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.JUNGLE_DUNGEONS));

        GeneralUtils.addToBiome("badlands_dungeon",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.badlandsDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.MESA),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.BADLANDS_DUNGEONS));

        GeneralUtils.addToBiome("dark_forest_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.darkForestDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.FOREST)
                        && BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted"),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.DARK_FOREST_DUNGEONS));

        GeneralUtils.addToBiome("desert_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.desertDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.DESERT),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.DESERT_DUNGEONS));

        GeneralUtils.addToBiome("mushroom_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.mushroomDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.MUSHROOM),
                context -> {
                    if(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight > 62)
                        context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.MUSHROOM_HIGH_DUNGEONS);
                    if(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight <= 62)
                        context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.MUSHROOM_LOW_DUNGEONS);
                });

        GeneralUtils.addToBiome("swamp_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.swampDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.SWAMP),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.SWAMP_DUNGEONS));

        GeneralUtils.addToBiome("snow_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.snowDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.ICY)
                        // inverted icy check
                        && !(BiomeSelection.hasName(context, "icy", "ice", "frozen") ||
                            (context.getBiome().getTemperature() < 0 && !BiomeSelection.hasName(context, "snow"))),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.SNOW_DUNGEONS));

        GeneralUtils.addToBiome("icy_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.icyDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.ICY)
                        && (BiomeSelection.hasName(context, "icy", "ice", "frozen") ||
                            (context.getBiome().getTemperature() < 0 && !BiomeSelection.hasName(context, "snow"))),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.ICY_DUNGEONS));

        GeneralUtils.addToBiome("nether_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.netherDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.NETHER),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.NETHER_DUNGEONS));


        GeneralUtils.addToBiome("end_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.endDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.THEEND)
                        && !BiomeSelection.isBiome(context, BiomeKeys.THE_END, BiomeKeys.SMALL_END_ISLANDS),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.END_DUNGEONS));

        // Have to do this abomination to get neutral ocean dungeons only in biomes that the other ocean dungeon types won't touch.
        // All due to BiomeModification API being per feature instead of an event like Forge's Biome Modification event.
        GeneralUtils.addToBiome("ocean_neutral_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.OCEAN)
                        && (!BiomeSelection.hasName(context, "cold", "frozen", "snow", "ice", "warm", "hot", "tropic", "lukewarm") || // Thanks to vanilla oceans all being same temperature...
                            (!BiomeSelection.hasNamespace(context, "minecraft") && context.getBiome().getTemperature() >= 0.5f && context.getBiome().getTemperature() < 0.9f))
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSDungeonsConfig.addDungeonsToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.OCEAN_NEUTRAL_DUNGEONS));

        GeneralUtils.addToBiome("ocean_cold_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.OCEAN)
                        && (BiomeSelection.hasName(context, "cold") || // Thanks to vanilla oceans all being same temperature...
                        (!BiomeSelection.hasNamespace(context, "minecraft") && context.getBiome().getTemperature() >= 0.0f && context.getBiome().getTemperature() < 0.5f))
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSDungeonsConfig.addDungeonsToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.OCEAN_COLD_DUNGEONS));

        GeneralUtils.addToBiome("ocean_frozen_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.OCEAN)
                        && (BiomeSelection.hasName(context, "frozen", "snow", "ice") || // Thanks to vanilla oceans all being same temperature...
                        (!BiomeSelection.hasNamespace(context, "minecraft") && context.getBiome().getTemperature() < 0f))
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSDungeonsConfig.addDungeonsToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.OCEAN_FROZEN_DUNGEONS));

        GeneralUtils.addToBiome("ocean_lukewarm_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.OCEAN)
                        && (BiomeSelection.hasName(context, "lukewarm") || // Thanks to vanilla oceans all being same temperature...
                        (!BiomeSelection.hasNamespace(context, "minecraft") && context.getBiome().getTemperature() >= 0.9f && context.getBiome().getTemperature() < 1.5f))
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSDungeonsConfig.addDungeonsToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.OCEAN_LUKEWARM_DUNGEONS));

        GeneralUtils.addToBiome("ocean_warm_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.OCEAN)
                        && (BiomeSelection.hasName(context, "warm", "hot", "tropic") || // Thanks to vanilla oceans all being same temperature...
                        (!BiomeSelection.hasNamespace(context, "minecraft") && context.getBiome().getTemperature() >= 1.5f))
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSDungeonsConfig.addDungeonsToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.OCEAN_WARM_DUNGEONS));


        //Remove vanilla dungeons from non-ocean/nether/end biomes we added our dungeons to
        BiomeModifications.create(new Identifier(RepurposedStructures.MODID, "remove_vanilla_dungeons")).add(
                ModificationPhase.REMOVALS,
                context -> RSConfiguredFeatures.RS_DUNGEONS.stream().anyMatch(dungeon ->
                        dungeon != RSConfiguredFeatures.OCEAN_NEUTRAL_DUNGEONS &&
                        dungeon != RSConfiguredFeatures.NETHER_DUNGEONS &&
                        dungeon != RSConfiguredFeatures.END_DUNGEONS &&
                        context.hasBuiltInFeature(dungeon)),
                context -> context.getGenerationSettings().removeBuiltInFeature(ConfiguredFeatures.MONSTER_ROOM));
    }

    private static boolean genericDungeonCheck(BiomeSelectionContext context) {
        return RSConfiguredFeatures.RS_DUNGEONS.stream().noneMatch(context::hasBuiltInFeature)
                && BiomeSelection.isBiomeAllowed(context, "dungeons")
                && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSDungeonsConfig.addDungeonsToModdedBiomes);
    }
}
