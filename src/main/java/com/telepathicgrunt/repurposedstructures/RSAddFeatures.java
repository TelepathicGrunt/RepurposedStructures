package com.telepathicgrunt.repurposedstructures;

import java.util.Map;

import com.mojang.datafixers.Dynamic;
import com.telepathicgrunt.repurposedstructures.world.features.RSFeatures;
import com.telepathicgrunt.repurposedstructures.world.placements.RSPlacements;

import net.minecraft.block.Blocks;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.NBTDynamicOps;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BlockBlobConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.ChanceRangeConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class RSAddFeatures
{
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MINESHAFTS //

    public static void addMineshafts(Biome biome, String biomeNamespace, String biomePath) {
	addMineshaftFeatures(biome);

	if (RepurposedStructures.RSMineshaftsConfig.birchMineshaftSpawnrate.get() != 0 && biomePath.contains("birch") && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
	    if (biome.hasStructure(Feature.MINESHAFT) || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get()) {
		// replace vanilla mineshaft with our own
		biome.structures.remove(Feature.MINESHAFT);
		biome.addStructure(RSFeatures.BIRCH_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}

	else if (RepurposedStructures.RSMineshaftsConfig.jungleMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.JUNGLE && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
	    if (biome.hasStructure(Feature.MINESHAFT) || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get()) {
		// replace vanilla mineshaft with our own
		biome.structures.remove(Feature.MINESHAFT);
		biome.addStructure(RSFeatures.JUNGLE_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}

	else if (RepurposedStructures.RSMineshaftsConfig.desertMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.DESERT && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
	    if (biome.hasStructure(Feature.MINESHAFT) || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get()) {
		// replace vanilla mineshaft with our own
		biome.structures.remove(Feature.MINESHAFT);
		biome.addStructure(RSFeatures.DESERT_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}

	else if (RepurposedStructures.RSMineshaftsConfig.stoneMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.EXTREME_HILLS && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
	    if (biome.hasStructure(Feature.MINESHAFT) || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get()) {
		// replace vanilla mineshaft with our own
		biome.structures.remove(Feature.MINESHAFT);
		biome.addStructure(RSFeatures.STONE_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}

	else if (RepurposedStructures.RSMineshaftsConfig.savannaMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.SAVANNA && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
	    if (biome.hasStructure(Feature.MINESHAFT) || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get()) {
		// replace vanilla mineshaft with our own
		biome.structures.remove(Feature.MINESHAFT);
		biome.addStructure(RSFeatures.SAVANNA_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}

	else if (RepurposedStructures.RSMineshaftsConfig.icyMineshaftSpawnrate.get() != 0 && (biome.getCategory() == Category.ICY || biomePath.contains("snowy")) && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
	    if (biome.hasStructure(Feature.MINESHAFT) || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get()) {
		// replace vanilla mineshaft with our own
		biome.structures.remove(Feature.MINESHAFT);
		biome.addStructure(RSFeatures.ICY_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}

	else if (RepurposedStructures.RSMineshaftsConfig.oceanMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.OCEAN && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
	    if (biome.hasStructure(Feature.MINESHAFT) || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get()) {
		// replace vanilla mineshaft with our own
		biome.structures.remove(Feature.MINESHAFT);
		biome.addStructure(RSFeatures.OCEAN_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}

	else if (RepurposedStructures.RSMineshaftsConfig.taigaMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.TAIGA && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
	    if (biome.hasStructure(Feature.MINESHAFT) || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get()) {
		// replace vanilla mineshaft with our own
		biome.structures.remove(Feature.MINESHAFT);
		biome.addStructure(RSFeatures.TAIGA_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}

	else if (RepurposedStructures.RSMineshaftsConfig.swampAndDarkForestMineshaftSpawnrate.get() != 0 && (biome.getCategory() == Category.SWAMP || biomePath.contains("dark_forest") || biomePath.contains("dark_oak")) && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
	    if (biome.hasStructure(Feature.MINESHAFT) || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get()) {
		// replace vanilla mineshaft with our own
		biome.structures.remove(Feature.MINESHAFT);
		biome.addStructure(RSFeatures.SWAMP_OR_DARK_FOREST_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}

	else if (RepurposedStructures.RSMineshaftsConfig.endMineshaftSpawnrate.get() != 0 && (biome.getCategory() == Category.THEEND && biome != Biomes.THE_END && biome != Biomes.SMALL_END_ISLANDS) && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
	    biome.addStructure(RSFeatures.END_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	}

	else if (RepurposedStructures.RSMineshaftsConfig.netherMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.NETHER && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
	    biome.addStructure(RSFeatures.HELL_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	}
    }


    public static void addMineshaftFeatures(Biome biome) {
	biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.BIRCH_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.DESERT_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.END_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.HELL_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.ICY_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.JUNGLE_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.OCEAN_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.SAVANNA_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.STONE_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.SWAMP_OR_DARK_FOREST_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.TAIGA_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // JUNGLE FORTRESS //


    public static void addJungleFortress(Biome biome, String biomeNamespace, String biomePath) {
	biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.JUNGLE_FORTRESS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));

	if (RepurposedStructures.RSMainConfig.jungleFortressSpawnrate.get() != 1001) {
	    if (biome.getCategory() == Category.JUNGLE && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMainConfig.addJungleFortressToModdedBiomes.get())) {
		biome.addStructure(RSFeatures.JUNGLE_FORTRESS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DUNGEONS //


    public static void addDungeons(Biome biome, String biomeNamespace, String biomePath) {
	if (RepurposedStructures.RSDungeonsConfig.jungleDungeonSpawnrate.get() != 0 && biome.getCategory() == Category.JUNGLE && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {
	    // replace vanilla dungeon with our own
	    biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig) configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
	    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.JUNGLE_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(
		    RSPlacements.RS_DUNGEON_PLACEMENT.configure(new CountRangeConfig(RepurposedStructures.RSDungeonsConfig.jungleDungeonSpawnrate.get(), RepurposedStructures.RSDungeonsConfig.jungleDungeonMinHeight.get(), 0, RepurposedStructures.RSDungeonsConfig.jungleDungeonMaxHeight.get()))));
	}

	else if (RepurposedStructures.RSDungeonsConfig.badlandsDungeonSpawnrate.get() != 0 && biome.getCategory() == Category.MESA && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {
	    // replace vanilla dungeon with our own
	    biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig) configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
	    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.BADLANDS_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(
		    RSPlacements.RS_DUNGEON_PLACEMENT.configure(new CountRangeConfig(RepurposedStructures.RSDungeonsConfig.badlandsDungeonSpawnrate.get(), RepurposedStructures.RSDungeonsConfig.badlandsDungeonMinHeight.get(), 0, RepurposedStructures.RSDungeonsConfig.badlandsDungeonMaxHeight.get()))));
	}

	else if (RepurposedStructures.RSDungeonsConfig.darkForestDungeonSpawnrate.get() != 0 && biomePath.contains("dark_forest") && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {
	    // replace vanilla dungeon with our own
	    biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig) configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
	    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.DARK_FOREST_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(
		    RSPlacements.RS_DUNGEON_PLACEMENT.configure(new CountRangeConfig(RepurposedStructures.RSDungeonsConfig.darkForestDungeonSpawnrate.get(), RepurposedStructures.RSDungeonsConfig.darkForestDungeonMinHeight.get(), 0, RepurposedStructures.RSDungeonsConfig.darkForestDungeonMaxHeight.get()))));
	}

	else if (RepurposedStructures.RSDungeonsConfig.desertDungeonSpawnrate.get() != 0 && biome.getCategory() == Category.DESERT && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {
	    // replace vanilla dungeon with our own
	    biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig) configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
	    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.DESERT_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(
		    RSPlacements.RS_DUNGEON_PLACEMENT.configure(new CountRangeConfig(RepurposedStructures.RSDungeonsConfig.desertDungeonSpawnrate.get(), RepurposedStructures.RSDungeonsConfig.desertDungeonMinHeight.get(), 0, RepurposedStructures.RSDungeonsConfig.desertDungeonMaxHeight.get()))));
	}

	else if (RepurposedStructures.RSDungeonsConfig.mushroomDungeonSpawnrate.get() != 0 && biome.getCategory() == Category.MUSHROOM && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {
	    // replace vanilla dungeon with our own
	    biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig) configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
	    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.MUSHROOM_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(
		    RSPlacements.RS_DUNGEON_PLACEMENT.configure(new CountRangeConfig(RepurposedStructures.RSDungeonsConfig.mushroomDungeonSpawnrate.get(), RepurposedStructures.RSDungeonsConfig.mushroomDungeonMinHeight.get(), 0, RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get()))));
	}

	else if (RepurposedStructures.RSDungeonsConfig.swampDungeonSpawnrate.get() != 0 && biome.getCategory() == Category.SWAMP && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {
	    // replace vanilla dungeon with our own
	    biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig) configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
	    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.SWAMP_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(
		    RSPlacements.RS_DUNGEON_PLACEMENT.configure(new CountRangeConfig(RepurposedStructures.RSDungeonsConfig.swampDungeonSpawnrate.get(), RepurposedStructures.RSDungeonsConfig.swampDungeonMinHeight.get(), 0, RepurposedStructures.RSDungeonsConfig.swampDungeonMaxHeight.get()))));
	}

	else if (RepurposedStructures.RSDungeonsConfig.snowDungeonSpawnrate.get() != 0 && biome.getCategory() == Category.ICY && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {
	    // replace vanilla dungeon with our own
	    biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig) configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
	    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.SNOW_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(
		    RSPlacements.RS_DUNGEON_PLACEMENT.configure(new CountRangeConfig(RepurposedStructures.RSDungeonsConfig.snowDungeonSpawnrate.get(), RepurposedStructures.RSDungeonsConfig.snowDungeonMinHeight.get(), 0, RepurposedStructures.RSDungeonsConfig.snowDungeonMaxHeight.get()))));
	}

	else if (RepurposedStructures.RSDungeonsConfig.netherDungeonSpawnrate.get() != 0 && biome.getCategory() == Category.NETHER && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {
	    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.NETHER_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(
		    RSPlacements.RS_DUNGEON_PLACEMENT.configure(new CountRangeConfig(RepurposedStructures.RSDungeonsConfig.netherDungeonSpawnrate.get(), RepurposedStructures.RSDungeonsConfig.netherDungeonMinHeight.get(), 0, RepurposedStructures.RSDungeonsConfig.netherDungeonMaxHeight.get()))));
	}

	else if (RepurposedStructures.RSDungeonsConfig.endDungeonSpawnrate.get() != 0 && (biome.getCategory() == Category.THEEND && biome != Biomes.THE_END && biome != Biomes.SMALL_END_ISLANDS) && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {
	    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.END_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(
		    RSPlacements.RS_DUNGEON_PLACEMENT.configure(new CountRangeConfig(RepurposedStructures.RSDungeonsConfig.endDungeonSpawnrate.get(), RepurposedStructures.RSDungeonsConfig.endDungeonMinHeight.get(), 0, RepurposedStructures.RSDungeonsConfig.endDungeonMaxHeight.get()))));
	}

	else if (RepurposedStructures.RSDungeonsConfig.oceanDungeonSpawnrate.get() != 0 && biome.getCategory() == Category.OCEAN && dungeonAllowedByNamespaceAndConfig(biomeNamespace)) {
	    // replace vanilla dungeon with our own
	    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.OCEAN_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(
		    RSPlacements.RS_DUNGEON_PLACEMENT.configure(new CountRangeConfig(RepurposedStructures.RSDungeonsConfig.oceanDungeonSpawnrate.get(), RepurposedStructures.RSDungeonsConfig.oceanDungeonMinHeight.get(), 0, RepurposedStructures.RSDungeonsConfig.oceanDungeonMaxHeight.get()))));
	}
    }


    /**
     * Will not return true for Ultra Amplified Dimension's biomes as that mod already has the dungeon type.
     * 
     * And will check if the dungeon is allowed in modded biomes based on config but will always return true for vanilla biomes.
     */
    private static boolean dungeonAllowedByNamespaceAndConfigUA(String biomeNamespace) {
	if (!biomeNamespace.equals("ultra_amplified_dimension")) {
	    return dungeonAllowedByNamespaceAndConfig(biomeNamespace);
	}

	return false;
    }


    /**
     * Will check if the dungeon is allowed in modded biomes based on config but will always return true for vanilla biomes.
     */
    private static boolean dungeonAllowedByNamespaceAndConfig(String biomeNamespace) {
	if (biomeNamespace.equals("minecraft") || RepurposedStructures.RSDungeonsConfig.addDungeonsToModdedBiomes.get()) {
	    return true;
	}

	return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WELLS //


    public static void addWells(Biome biome, String biomeNamespace, String biomePath) {
	if (RepurposedStructures.RSWellsConfig.badlandsWellSpawnrate.get() != 10000 && biome.getCategory() == Category.MESA && wellAllowedByNamespaceAndConfig(biomeNamespace)) {
	    biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.BADLANDS_WELL.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP.configure(new ChanceConfig(RepurposedStructures.RSWellsConfig.badlandsWellSpawnrate.get()))));
	}
	else if (RepurposedStructures.RSWellsConfig.netherWellSpawnrate.get() != 10000 && biome.getCategory() == Category.NETHER && wellAllowedByNamespaceAndConfig(biomeNamespace)) {
	    biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.NETHER_WELL.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_RANGE.configure(new ChanceRangeConfig(1F / RepurposedStructures.RSWellsConfig.netherWellSpawnrate.get(), 30, 0, 98))));
	}
	else if (RepurposedStructures.RSWellsConfig.snowWellSpawnrate.get() != 10000 && (biome.getCategory() == Category.ICY || biomePath.contains("snow")) && wellAllowedByNamespaceAndConfig(biomeNamespace)) {
	    biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.SNOW_WELL.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP.configure(new ChanceConfig(RepurposedStructures.RSWellsConfig.snowWellSpawnrate.get()))));
	}
	else if (RepurposedStructures.RSWellsConfig.mossyStoneWellSpawnrate.get() != 10000 && (biome.getCategory() == Category.JUNGLE || biome.getCategory() == Category.SWAMP || biomePath.contains("dark_forest") || biomePath.contains("dark_oak")) && wellAllowedByNamespaceAndConfig(biomeNamespace)) {
	    biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.MOSSY_STONE_WELL.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP.configure(new ChanceConfig(RepurposedStructures.RSWellsConfig.mossyStoneWellSpawnrate.get()))));
	}
	else if (RepurposedStructures.RSWellsConfig.forestWellSpawnrate.get() != 10000 && (biome.getCategory() == Category.FOREST && !(biomePath.contains("dark_forest") || biomePath.contains("dark_oak"))) && wellAllowedByNamespaceAndConfig(biomeNamespace)) {
	    biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.FOREST_WELL.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP.configure(new ChanceConfig(RepurposedStructures.RSWellsConfig.forestWellSpawnrate.get()))));
	}
    }


    private static boolean wellAllowedByNamespaceAndConfig(String biomeNamespace) {
	if (biomeNamespace.equals("minecraft") || RepurposedStructures.RSWellsConfig.addWellsToModdedBiomes.get()) {
	    return true;
	}

	return false;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MISC FEATURES //

    private static final ConfiguredFeature<?, ?> VANILLA_SWAMP_TREE = Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.SWAMP_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 1)));
    private static final ConfiguredFeature<?, ?> VANILLA_BOULDER = Feature.FOREST_ROCK.withConfiguration(new BlockBlobConfig(Blocks.MOSSY_COBBLESTONE.getDefaultState(), 0)).withPlacement(Placement.FOREST_ROCK.configure(new FrequencyConfig(3)));

    public static void addMiscFeatures(Biome biome, String biomeNamespace, String biomePath) {
	// only exists in vanilla biomes
	if (RepurposedStructures.RSMainConfig.hornedSwampTree.get() && !biomeNamespace.equals("ultra_amplified_dimension") && biome == Biomes.SWAMP && biomeNamespace.equals("minecraft")) {
	    biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RSFeatures.HORNED_SWAMP_TREE.withConfiguration(DefaultBiomeFeatures.SWAMP_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.7F, 1))));
	}

	// can exist in modded biomes too
	else if (RepurposedStructures.RSMainConfig.hornedSwampTree.get() && !biomeNamespace.equals("ultra_amplified_dimension") && ((biome == Biomes.SWAMP_HILLS && biomeNamespace.equals("minecraft")) || (RepurposedStructures.RSMainConfig.addLargeSwampTreeModdedBiomes.get() && !biomeNamespace.equals("minecraft") && biomePath.contains("swamp") && biome != Biomes.SWAMP))) {
	    // replace the swamp tree with our own
	    biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && serializeAndCompareFeature(configuredFeature, VANILLA_SWAMP_TREE));
	    biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RSFeatures.HORNED_SWAMP_TREE.withConfiguration(DefaultBiomeFeatures.SWAMP_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.8F, 1))));
	}

	if (RepurposedStructures.RSMainConfig.boulderGiant.get() && !biomeNamespace.equals("ultra_amplified_dimension") && (((biome == Biomes.GIANT_SPRUCE_TAIGA_HILLS || biome == Biomes.GIANT_TREE_TAIGA_HILLS) && biomeNamespace.equals("minecraft")) || (RepurposedStructures.RSMainConfig.addGiantBouldersModdedBiomes.get() && !biomeNamespace.equals("minecraft") && ((biomePath.contains("giant") && biomePath.contains("taiga")) || biomePath.contains("redwood"))))) {
	    // replace the boulders with our own
	    biome.getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && serializeAndCompareFeature(configuredFeature, VANILLA_BOULDER));
	    biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, RSFeatures.BOULDER_GIANT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.FOREST_ROCK.configure(new FrequencyConfig(2))));
	}
	
	else if (RepurposedStructures.RSMainConfig.boulderTiny.get() && !biomeNamespace.equals("ultra_amplified_dimension") && (((biome == Biomes.TAIGA_MOUNTAINS || biome == Biomes.SNOWY_TAIGA_MOUNTAINS) && biomeNamespace.equals("minecraft")) || (RepurposedStructures.RSMainConfig.addTinyBouldersModdedBiomes.get() && !biomeNamespace.equals("minecraft") && biomePath.contains("taiga")))) {
	    biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, RSFeatures.BOULDER_TINY.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.FOREST_ROCK.configure(new FrequencyConfig(2))));
	}
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // STRONGHOLDS //


    public static void addStronghold(Biome biome, String biomeNamespace, String biomePath) {

	if (!RepurposedStructures.RSStrongholdsConfig.useVanillaStronghold.get() && biome.getCategory() == Category.NETHER && RepurposedStructures.RSStrongholdsConfig.allowNetherStronghold.get()) {
	    if (RepurposedStructures.RSStrongholdsConfig.strongholdSpawnrate.get() != 1001) {
		biome.addStructure(RSFeatures.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}
	else {
	    // remove vanilla stronghold
	    if (biome.structures.containsKey(Feature.STRONGHOLD)) {
		// only remove from structures list if we are using strictly our own stronghold.
		// If we are using vanilla, vanilla's needs to be in the structure list or else
		// vanilla's stronghold's methods for generation will fail as biome doesnt contain vanilla stronghold.
		if (!RepurposedStructures.RSStrongholdsConfig.useVanillaStronghold.get()) biome.structures.remove(Feature.STRONGHOLD);


		biome.features.get(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && serializeAndCompareFeature(configuredFeature, Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG))));


		if (RepurposedStructures.RSStrongholdsConfig.strongholdSpawnrate.get() != 1001) {
		    biome.addStructure(RSFeatures.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		}
	    }
	}

	biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // NETHER TEMPLE //


    public static void addNetherTemple(Biome biome, String biomeNamespace, String biomePath) {
	biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.NETHER_TEMPLE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));

	if (RepurposedStructures.RSMainConfig.netherTempleSpawnrate.get() == 1001) {
	    return;
	}

	if (biome.getCategory() == Category.NETHER && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMainConfig.addNetherTempleToModdedBiomes.get())) {
	    biome.addStructure(RSFeatures.NETHER_TEMPLE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	}
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BADLANDS TEMPLE //


    public static void addBadlandsTemple(Biome biome, String biomeNamespace, String biomePath) {
	biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.BADLANDS_TEMPLE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));

	if (RepurposedStructures.RSMainConfig.badlandsTempleSpawnrate.get() == 1001) {
	    return;
	}

	if (biome.getCategory() == Category.MESA && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMainConfig.addBadlandsTempleToModdedBiomes.get())) {
	    biome.addStructure(RSFeatures.BADLANDS_TEMPLE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	}
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // IGLOOS //


    public static void addIgloos(Biome biome, String biomeNamespace, String biomePath) {
	biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.GRASSY_IGLOO.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.STONE_IGLOO.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));

	if (RepurposedStructures.RSMainConfig.grassyIglooSpawnrate.get() != 1001) {
	    if ((biome.getCategory() == Category.PLAINS || biome.getCategory() == Category.FOREST) && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMainConfig.addGrassyIglooToModdedBiomes.get())) {
		biome.addStructure(RSFeatures.GRASSY_IGLOO.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}

	if (RepurposedStructures.RSMainConfig.stoneIglooSpawnrate.get() != 1001) {
	    if ((biome.getCategory() == Category.TAIGA && biomePath.contains("giant")) && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMainConfig.addStoneIglooToModdedBiomes.get())) {
		biome.addStructure(RSFeatures.STONE_IGLOO.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // VILLAGES //


    public static void addVillages(Biome biome, String biomeNamespace, String biomePath) {
	addVillageFeatures(biome);
	
	if ((biome.getCategory() == Category.MESA && !biomePath.contains("plateau")) && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
	    if (RepurposedStructures.RSVillagesConfig.badlandsVillageSpawnrate.get() != 1001) {
		biome.addStructure(RSFeatures.BADLANDS_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}

	else if (biomePath.contains("birch") && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
	    if (RepurposedStructures.RSVillagesConfig.birchVillageSpawnrate.get() != 1001) {
		biome.addStructure(RSFeatures.BIRCH_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}
	
	else if (biomePath.contains("dark_forest") && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
	    if (RepurposedStructures.RSVillagesConfig.darkForestVillageSpawnrate.get() != 1001) {
		biome.addStructure(RSFeatures.DARK_FOREST_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}
	
	else if (biome.getCategory() == Category.JUNGLE && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
	    if (RepurposedStructures.RSVillagesConfig.jungleVillageSpawnrate.get() != 1001) {
		biome.addStructure(RSFeatures.JUNGLE_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}
	
	else if (biome.getCategory() == Category.SWAMP && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
	    if (RepurposedStructures.RSVillagesConfig.swampVillageSpawnrate.get() != 1001) {
		biome.addStructure(RSFeatures.SWAMP_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}
	
	else if (biome.getCategory() == Category.EXTREME_HILLS && (biomeNamespace.equals("minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
	    if (RepurposedStructures.RSVillagesConfig.mountainsVillageSpawnrate.get() != 1001) {
		biome.addStructure(RSFeatures.MOUNTAINS_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}
	
	else if (((biome == Biomes.GIANT_SPRUCE_TAIGA || biome == Biomes.GIANT_TREE_TAIGA) && biomeNamespace.equals("minecraft")) || (RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get() && ((biomePath.contains("giant") && biomePath.contains("taiga")) || biomePath.contains("redwood")))) {
	    if (RepurposedStructures.RSVillagesConfig.giantTaigaVillageSpawnrate.get() != 1001) {
		biome.addStructure(RSFeatures.GIANT_TAIGA_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
	    }
	}
    }
    
    public static void addVillageFeatures(Biome biome) {
	biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.BADLANDS_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.BIRCH_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.DARK_FOREST_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.JUNGLE_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.SWAMP_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.MOUNTAINS_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.GIANT_TAIGA_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GENERAL UTILITIES //


    /**
     * Will serialize (if possible) both features and check if they are the same feature. If cannot serialize, compare the feature itself to see if it is the same
     */
    private static boolean serializeAndCompareFeature(ConfiguredFeature<?, ?> feature1, ConfiguredFeature<?, ?> feature2) {
	try {
	    Map<Dynamic<INBT>, Dynamic<INBT>> feature1Map = feature1.serialize(NBTDynamicOps.INSTANCE).getMapValues().get();
	    Map<Dynamic<INBT>, Dynamic<INBT>> feature2Map = feature2.serialize(NBTDynamicOps.INSTANCE).getMapValues().get();

	    if (feature1Map != null && feature2Map != null) {
		return feature1Map.equals(feature2Map);
	    }
	}
	catch (Exception e) {
	    // One of the features cannot be serialized which can only happen with custom modded features
	    // Check if the features are the same feature even though the placement or config for the feature might be different.
	    // This is the best way we can remove duplicate modded features as best as we can. (I think)
	    if ((feature1.config instanceof DecoratedFeatureConfig && feature2.config instanceof DecoratedFeatureConfig) && ((DecoratedFeatureConfig) feature1.config).feature.feature == ((DecoratedFeatureConfig) feature2.config).feature.feature) {
		return true;
	    }
	}

	return false;
    }
}
