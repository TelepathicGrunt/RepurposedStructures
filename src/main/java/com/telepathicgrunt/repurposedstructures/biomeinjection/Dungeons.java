package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public final class Dungeons {
    private Dungeons() {}

    public static void addDungeons() {

        GeneralUtils.addToBiome("jungle_dungeon",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.JUNGLE_DUNGEONS_PLACED,
                                () -> BiomeSelection.haveCategories(context, BiomeTags.IS_JUNGLE))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.jungleDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.JUNGLE_DUNGEONS_PLACED));

        GeneralUtils.addToBiome("badlands_dungeon",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.BADLANDS_DUNGEONS_PLACED,
                                () -> BiomeSelection.haveCategories(context, BiomeTags.IS_BADLANDS))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.badlandsDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.BADLANDS_DUNGEONS_PLACED));

        GeneralUtils.addToBiome("dark_forest_dungeons",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.DARK_FOREST_DUNGEONS_PLACED,
                                () -> BiomeSelection.haveCategories(context, BiomeTags.IS_FOREST)
                                && BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony"))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.darkForestDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.DARK_FOREST_DUNGEONS_PLACED));

        GeneralUtils.addToBiome("desert_dungeons",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.DESERT_DUNGEONS_PLACED,
                                () -> BiomeSelection.haveCategories(context, RSTags.DESERTS))
                                && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.desertDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.DESERT_DUNGEONS_PLACED));

        GeneralUtils.addToBiome("deep_dungeons",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.DEEP_DUNGEONS_PLACED, () -> true)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.deepDungeonAttemptsPerChunk != 0,
                context ->  {
                    context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.DEEP_DUNGEONS_PLACED);
                    context.getGenerationSettings().removeBuiltInFeature(CavePlacements.MONSTER_ROOM_DEEP.value());
                });

        GeneralUtils.addToBiome("mushroom_dungeons",
                (context) -> 
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.MUSHROOM_DUNGEONS_PLACED,
                                () -> BiomeSelection.haveCategories(context, RSTags.MUSHROOMS))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.mushroomDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.MUSHROOM_DUNGEONS_PLACED));

        GeneralUtils.addToBiome("swamp_dungeons",
                (context) -> 
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.SWAMP_DUNGEONS_PLACED,
                                () -> BiomeSelection.haveCategories(context, RSTags.SWAMPS))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.swampDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.SWAMP_DUNGEONS_PLACED));

        GeneralUtils.addToBiome("snow_dungeons",
                (context) -> 
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.SNOW_DUNGEONS_PLACED,
                                () -> !BiomeSelection.hasName(context, "icy", "ice", "frozen", "glacier", "glacial") &&
                                        (BiomeSelection.hasName(context, "snow") ||
                                        BiomeSelection.haveCategories(context, RSTags.ICY) ||
                                        (BiomeSelection.haveCategories(context, RSTags.MOUNTAINS) && context.getBiome().getBaseTemperature() < 0)))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.snowDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.SNOW_DUNGEONS_PLACED));

        GeneralUtils.addToBiome("icy_dungeons",
                (context) -> 
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.ICY_DUNGEONS_PLACED,
                                () -> BiomeSelection.hasName(context, "icy", "ice", "frozen", "glacier", "glacial") &&
                                    (BiomeSelection.haveCategories(context, RSTags.ICY) ||
                                    (BiomeSelection.haveCategories(context, RSTags.MOUNTAINS) && context.getBiome().getBaseTemperature() < 0)))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.icyDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.ICY_DUNGEONS_PLACED));

        // Vegetal to match Nether Mineshafts
        GeneralUtils.addToBiome("nether_dungeons",
                (context) -> 
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.NETHER_DUNGEONS_PLACED,
                                () -> BiomeSelection.haveCategories(context, BiomeTags.IS_NETHER))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.netherDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RSConfiguredFeatures.NETHER_DUNGEONS_PLACED));


        GeneralUtils.addToBiome("end_dungeons",
                (context) -> 
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.END_DUNGEONS_PLACED,
                                () -> BiomeSelection.haveCategories(context, RSTags.END_ISLAND_LAND, RSTags.END_VOIDS)
                                && !BiomeSelection.isBiome(context, Biomes.THE_END, Biomes.SMALL_END_ISLANDS))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.endDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.END_DUNGEONS_PLACED));

        // Have to do this abomination to get neutral ocean dungeons only in biomes that the other ocean dungeon types won't touch.
        // All due to BiomeModification API being per feature instead of an event like Forge's Biome Modification event.
        GeneralUtils.addToBiome("ocean_neutral_dungeons",
                (context) -> 
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.OCEAN_NEUTRAL_DUNGEONS_PLACED,
                                () -> BiomeSelection.haveCategories(context, RSTags.OCEANS)
                                && (!BiomeSelection.hasName(context, "cold", "frozen", "snow", "ice", "warm", "hot", "tropic", "lukewarm") || // Thanks to vanilla oceans all being same temperature...
                                (!BiomeSelection.hasNamespace(context, "minecraft")
                                        && context.getBiome().getBaseTemperature() >= 0.5f && context.getBiome().getBaseTemperature() < 0.9f)))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.OCEAN_NEUTRAL_DUNGEONS_PLACED));

        GeneralUtils.addToBiome("ocean_cold_dungeons",
                (context) -> 
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.OCEAN_COLD_DUNGEONS_PLACED,
                                () -> BiomeSelection.haveCategories(context, RSTags.OCEANS)
                                && (BiomeSelection.hasName(context, "cold") || // Thanks to vanilla oceans all being same temperature...
                                (!BiomeSelection.hasNamespace(context, "minecraft")
                                        && context.getBiome().getBaseTemperature() >= 0.0f && context.getBiome().getBaseTemperature() < 0.5f)))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.OCEAN_COLD_DUNGEONS_PLACED));

        GeneralUtils.addToBiome("ocean_frozen_dungeons",
                (context) -> 
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.OCEAN_FROZEN_DUNGEONS_PLACED,
                                () -> BiomeSelection.haveCategories(context, RSTags.OCEANS)
                                && (BiomeSelection.hasName(context, "frozen", "snow", "ice") || // Thanks to vanilla oceans all being same temperature...
                                (!BiomeSelection.hasNamespace(context, "minecraft")
                                        && context.getBiome().getBaseTemperature() < 0f)))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.OCEAN_FROZEN_DUNGEONS_PLACED));

        GeneralUtils.addToBiome("ocean_lukewarm_dungeons",
                (context) -> 
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.OCEAN_LUKEWARM_DUNGEONS_PLACED,
                                () -> BiomeSelection.haveCategories(context, RSTags.OCEANS)
                                && (BiomeSelection.hasName(context, "lukewarm") || // Thanks to vanilla oceans all being same temperature...
                                (!BiomeSelection.hasNamespace(context, "minecraft")
                                        && context.getBiome().getBaseTemperature() >= 0.9f && context.getBiome().getBaseTemperature() < 1.5f)))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.OCEAN_LUKEWARM_DUNGEONS_PLACED));

        GeneralUtils.addToBiome("ocean_warm_dungeons",
                (context) -> 
                        BiomeSelection.isBiomeAllowed(context, RSConfiguredFeatures.OCEAN_WARM_DUNGEONS_PLACED,
                                () -> BiomeSelection.haveCategories(context, RSTags.OCEANS)
                                && (BiomeSelection.hasName(context, "warm", "hot", "tropic") || // Thanks to vanilla oceans all being same temperature...
                                (!BiomeSelection.hasNamespace(context, "minecraft")
                                        && context.getBiome().getBaseTemperature() >= 1.5f)))
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk != 0,
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.OCEAN_WARM_DUNGEONS_PLACED));
    }
}
