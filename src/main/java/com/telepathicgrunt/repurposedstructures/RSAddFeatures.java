package com.telepathicgrunt.repurposedstructures;

import java.util.Map;

import com.mojang.datafixers.Dynamic;
import com.telepathicgrunt.repurposedstructures.world.features.RSFeatures;

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
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class RSAddFeatures
{
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// MINESHAFTS //
	
	public static void addMineshafts(Biome biome, String biomeNamespace, String biomePath)
	{
		addRSMineshafts(biome);
		
		if(RepurposedStructures.RSConfig.birchMineshaftSpawnrate.get() != 0 && 
				biomePath.contains("birch") && 
				(biomeNamespace.equals("minecraft") || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get()))
		{
			if(biome.hasStructure(Feature.MINESHAFT) || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get())
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.BIRCH_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			}
		}
		
		else if(RepurposedStructures.RSConfig.jungleMineshaftSpawnrate.get() != 0 && 
				biome.getCategory() == Category.JUNGLE && 
				(biomeNamespace.equals("minecraft") || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get()))
		{
			if(biome.hasStructure(Feature.MINESHAFT) || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get())
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.JUNGLE_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			}
		}
		
		else if(RepurposedStructures.RSConfig.desertMineshaftSpawnrate.get() != 0 && 
				biome.getCategory() == Category.DESERT && 
				(biomeNamespace.equals("minecraft") || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get()))
		{
			if(biome.hasStructure(Feature.MINESHAFT) || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get())
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.DESERT_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			}
		}
		
		else if(RepurposedStructures.RSConfig.birchMineshaftSpawnrate.get() != 0 && 
				biome.getCategory() == Category.EXTREME_HILLS && 
				(biomeNamespace.equals("minecraft") || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get()))
		{
			if(biome.hasStructure(Feature.MINESHAFT) || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get())
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.STONE_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			}
		}
		
		else if(RepurposedStructures.RSConfig.savannaMineshaftSpawnrate.get() != 0 && 
				biome.getCategory() == Category.SAVANNA && 
				(biomeNamespace.equals("minecraft") || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get()))
		{
			if(biome.hasStructure(Feature.MINESHAFT) || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get())
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.SAVANNA_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			}
		}
		
		else if(RepurposedStructures.RSConfig.icyMineshaftSpawnrate.get() != 0 && 
				(biome.getCategory() == Category.ICY || biomePath.contains("snowy")) && 
				(biomeNamespace.equals("minecraft") || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get()))
		{
			if(biome.hasStructure(Feature.MINESHAFT) || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get())
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.ICY_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			}
		}
		
		else if(RepurposedStructures.RSConfig.oceanMineshaftSpawnrate.get() != 0 && 
				biome.getCategory() == Category.OCEAN && 
				(biomeNamespace.equals("minecraft") || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get()))
		{
			if(biome.hasStructure(Feature.MINESHAFT) || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get())
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.OCEAN_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			}
		}
		
		else if(RepurposedStructures.RSConfig.taigaMineshaftSpawnrate.get() != 0 && 
				biome.getCategory() == Category.TAIGA && 
				(biomeNamespace.equals("minecraft") || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get()))
		{
			if(biome.hasStructure(Feature.MINESHAFT) || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get())
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.TAIGA_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			}
		}
		
		else if(RepurposedStructures.RSConfig.swampAndDarkForestMineshaftSpawnrate.get() != 0 && 
				(biome.getCategory() == Category.SWAMP || biomePath.contains("dark_forest") || biomePath.contains("dark_oak")) && 
				(biomeNamespace.equals("minecraft") || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get()))
		{
			if(biome.hasStructure(Feature.MINESHAFT) || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get())
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.SWAMP_OR_DARK_FOREST_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			}
		}
		
		else if(RepurposedStructures.RSConfig.endMineshaftSpawnrate.get() != 0 && 
				(biome.getCategory() == Category.THEEND && biome != Biomes.THE_END && biome != Biomes.SMALL_END_ISLANDS) && 
				(biomeNamespace.equals("minecraft") || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get()))
		{
			biome.addStructure(RSFeatures.END_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		}
		
		else if(RepurposedStructures.RSConfig.netherMineshaftSpawnrate.get() != 0 && 
				biome.getCategory() == Category.NETHER && 
				(biomeNamespace.equals("minecraft") || RepurposedStructures.RSConfig.addMineshaftsToModdedBiomes.get()))
		{
			biome.addStructure(RSFeatures.HELL_MINESHAFT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		}
	}
	
	public static void addRSMineshafts(Biome biome)
	{
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

	public static void addJungleFortress(Biome biome, String biomeNamespace, String biomePath)
	{
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.JUNGLE_FORTRESS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	
		if(RepurposedStructures.RSConfig.jungleFortressSpawnrate.get() == 1001)
		{
			return;
		}
		
		if(biome.getCategory() == Category.JUNGLE && 
				(biomeNamespace.equals("minecraft") || RepurposedStructures.RSConfig.addJungleFortressToModdedBiomes.get()))
		{
			biome.addStructure(RSFeatures.JUNGLE_FORTRESS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// DUNGEONS //
	
	public static void addDungeons(Biome biome, String biomeNamespace, String biomePath)
	{
		if(RepurposedStructures.RSConfig.jungleDungeonSpawnrate.get() != 0 && 
				biome.getCategory() == Category.JUNGLE && 
				dungeonAllowedByNamespaceAndConfigUA(biomeNamespace))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig)configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.JUNGLE_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(RepurposedStructures.RSConfig.jungleDungeonSpawnrate.get()))));
		}
		
		else if(RepurposedStructures.RSConfig.badlandsDungeonSpawnrate.get() != 0 && 
				biome.getCategory() == Category.MESA && 
				dungeonAllowedByNamespaceAndConfigUA(biomeNamespace))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig)configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.BADLANDS_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(RepurposedStructures.RSConfig.badlandsDungeonSpawnrate.get()))));
		}
		
		else if(RepurposedStructures.RSConfig.darkForestDungeonSpawnrate.get() != 0 && 
				biomePath.contains("dark_forest") && 
				dungeonAllowedByNamespaceAndConfigUA(biomeNamespace))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig)configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.DARK_FOREST_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(RepurposedStructures.RSConfig.darkForestDungeonSpawnrate.get()))));
		}
		
		else if(RepurposedStructures.RSConfig.desertDungeonSpawnrate.get() != 0 && 
				biome.getCategory() == Category.DESERT && 
				dungeonAllowedByNamespaceAndConfigUA(biomeNamespace))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig)configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.DESERT_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(RepurposedStructures.RSConfig.desertDungeonSpawnrate.get()))));
		}
		
		else if(RepurposedStructures.RSConfig.mushroomDungeonSpawnrate.get() != 0 && 
				biome.getCategory() == Category.MUSHROOM && 
				dungeonAllowedByNamespaceAndConfigUA(biomeNamespace))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig)configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.MUSHROOM_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(RepurposedStructures.RSConfig.mushroomDungeonSpawnrate.get()))));
		}
		
		else if(RepurposedStructures.RSConfig.swampDungeonSpawnrate.get() != 0 && 
				biome.getCategory() == Category.SWAMP && 
				dungeonAllowedByNamespaceAndConfigUA(biomeNamespace))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig)configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.SWAMP_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(RepurposedStructures.RSConfig.swampDungeonSpawnrate.get()))));
		}
		
		else if(RepurposedStructures.RSConfig.snowDungeonSpawnrate.get() != 0 && 
				biome.getCategory() == Category.ICY && 
				dungeonAllowedByNamespaceAndConfigUA(biomeNamespace))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig)configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.SNOW_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(RepurposedStructures.RSConfig.snowDungeonSpawnrate.get()))));
		}
		
		else if(RepurposedStructures.RSConfig.netherDungeonSpawnrate.get() != 0 && 
				biome.getCategory() == Category.NETHER && 
				dungeonAllowedByNamespaceAndConfigUA(biomeNamespace))
		{
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.NETHER_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(RepurposedStructures.RSConfig.netherDungeonSpawnrate.get()))));
		}
		
		else if(RepurposedStructures.RSConfig.endDungeonSpawnrate.get() != 0 && 
				(biome.getCategory() == Category.THEEND && biome != Biomes.THE_END && biome != Biomes.SMALL_END_ISLANDS) && 
				dungeonAllowedByNamespaceAndConfigUA(biomeNamespace))
		{
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.END_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(RepurposedStructures.RSConfig.endDungeonSpawnrate.get()))));
		}
		
		else if(RepurposedStructures.RSConfig.oceanDungeonSpawnrate.get() != 0 && 
				biome.getCategory() == Category.OCEAN && 
				dungeonAllowedByNamespaceAndConfig(biomeNamespace))
		{
			//replace vanilla dungeon with our own
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.OCEAN_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(RepurposedStructures.RSConfig.oceanDungeonSpawnrate.get()))));
		}
	}
	
	/**
	 * Will not return true for Ultra Amplified Dimension's biomes as that mod already has the dungeon type.
	 * 
	 * And will check if the dungeon is allowed in modded biomes based on config but will always return true for vanilla biomes.
	 */
	private static boolean dungeonAllowedByNamespaceAndConfigUA(String biomeNamespace)
	{
		if(!biomeNamespace.equals("ultra_amplified_dimension"))
		{
			return dungeonAllowedByNamespaceAndConfig(biomeNamespace);
		}
		
		return false;
	}
	
	/**
	 * Will check if the dungeon is allowed in modded biomes based on config but will always return true for vanilla biomes.
	 */
	private static boolean dungeonAllowedByNamespaceAndConfig(String biomeNamespace)
	{
		if(biomeNamespace.equals("minecraft") || RepurposedStructures.RSConfig.addDungeonsToModdedBiomes.get())
		{
			return true;
		}
		
		return false;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// WELLS //
	
	public static void addWells(Biome biome, String biomeNamespace, String biomePath)
	{
		if(RepurposedStructures.RSConfig.badlandsWellSpawnrate.get() != 10000 && 
			biome.getCategory() == Category.MESA && 
			wellAllowedByNamespaceAndConfig(biomeNamespace))
		{
			biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.BADLANDS_WELL.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP.configure(new ChanceConfig(RepurposedStructures.RSConfig.badlandsWellSpawnrate.get()))));
		}
		else if(RepurposedStructures.RSConfig.netherWellSpawnrate.get() != 10000 && 
				biome.getCategory() == Category.NETHER && 
				wellAllowedByNamespaceAndConfig(biomeNamespace))
		{
			biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.NETHER_WELL.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_RANGE.configure(new ChanceRangeConfig(1F/RepurposedStructures.RSConfig.netherWellSpawnrate.get(), 30, 0, 98))));
		}
		else if(RepurposedStructures.RSConfig.snowWellSpawnrate.get() != 10000 && 
				(biome.getCategory() == Category.ICY || biomePath.contains("snow")) && 
				wellAllowedByNamespaceAndConfig(biomeNamespace))
		{
			biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.SNOW_WELL.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP.configure(new ChanceConfig(RepurposedStructures.RSConfig.snowWellSpawnrate.get()))));
		}
		else if(RepurposedStructures.RSConfig.mossyStoneWellSpawnrate.get() != 10000 && 
				(biome.getCategory() == Category.JUNGLE || biome.getCategory() == Category.SWAMP || biomePath.contains("dark_forest") || biomePath.contains("dark_oak")) && 
				wellAllowedByNamespaceAndConfig(biomeNamespace))
		{
			biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.MOSSY_STONE_WELL.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP.configure(new ChanceConfig(RepurposedStructures.RSConfig.mossyStoneWellSpawnrate.get()))));
		}
		else if(RepurposedStructures.RSConfig.forestWellSpawnrate.get() != 10000 && 
				(biome.getCategory() == Category.FOREST && !(biomePath.contains("dark_forest") || biomePath.contains("dark_oak"))) && 
				wellAllowedByNamespaceAndConfig(biomeNamespace))
		{
			biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.FOREST_WELL.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP.configure(new ChanceConfig(RepurposedStructures.RSConfig.forestWellSpawnrate.get()))));
		}
	}
	
	private static boolean wellAllowedByNamespaceAndConfig(String biomeNamespace)
	{
		if(biomeNamespace.equals("minecraft") || RepurposedStructures.RSConfig.addWellsToModdedBiomes.get())
		{
			return true;
		}
		
		return false;
	}

	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// MISC FEATURES //
	
	private static final ConfiguredFeature<?,?> VANILLA_SWAMP_TREE = Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.SWAMP_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 1)));
	private static final ConfiguredFeature<?,?> VANILLA_BOULDER = Feature.FOREST_ROCK.withConfiguration(new BlockBlobConfig(Blocks.MOSSY_COBBLESTONE.getDefaultState(), 0)).withPlacement(Placement.FOREST_ROCK.configure(new FrequencyConfig(3)));
	public static void addMiscFeatures(Biome biome, String biomeNamespace, String biomePath)
	{
		//only exists in vanilla biomes
		if(RepurposedStructures.RSConfig.hornedSwampTree.get() && !biomeNamespace.equals("ultra_amplified_dimension") && biome == Biomes.SWAMP && biomeNamespace.equals("minecraft"))
		{
			biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RSFeatures.HORNED_SWAMP_TREE.withConfiguration(DefaultBiomeFeatures.SWAMP_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.7F, 1))));
		}
		
		//can exist in modded biomes too
		else if(RepurposedStructures.RSConfig.hornedSwampTree.get() && !biomeNamespace.equals("ultra_amplified_dimension") && 
				((biome == Biomes.SWAMP_HILLS && biomeNamespace.equals("minecraft")) ||
				(RepurposedStructures.RSConfig.addMiscToModdedBiomes.get() && !biomeNamespace.equals("minecraft") && biomePath.contains("swamp") && biome != Biomes.SWAMP)))
		{
			//replace the swamp tree with our own
			biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && serializeAndCompareFeature(configuredFeature, VANILLA_SWAMP_TREE));
			biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RSFeatures.HORNED_SWAMP_TREE.withConfiguration(DefaultBiomeFeatures.SWAMP_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.8F, 1))));
		}
		
		else if(RepurposedStructures.RSConfig.boulderTiny.get() && !biomeNamespace.equals("ultra_amplified_dimension") && 
				(((biome == Biomes.TAIGA_MOUNTAINS || biome == Biomes.SNOWY_TAIGA_MOUNTAINS) && biomeNamespace.equals("minecraft")) ||
				(RepurposedStructures.RSConfig.addMiscToModdedBiomes.get() && !biomeNamespace.equals("minecraft") && biomePath.contains("taiga"))))
		{
			biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, RSFeatures.BOULDER_TINY.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.FOREST_ROCK.configure(new FrequencyConfig(2))));
		}
		
		else if(RepurposedStructures.RSConfig.boulderGiant.get() && !biomeNamespace.equals("ultra_amplified_dimension") &&
				(((biome == Biomes.GIANT_SPRUCE_TAIGA_HILLS || biome == Biomes.GIANT_TREE_TAIGA_HILLS) && biomeNamespace.equals("minecraft")) ||
				(RepurposedStructures.RSConfig.addMiscToModdedBiomes.get() && !biomeNamespace.equals("minecraft") && ((biomePath.contains("giant") && biomePath.contains("taiga")) || biomePath.contains("redwood")))))
		{
			//replace the boulders with our own
			biome.getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && serializeAndCompareFeature(configuredFeature, VANILLA_BOULDER));
			biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, RSFeatures.BOULDER_GIANT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.FOREST_ROCK.configure(new FrequencyConfig(2))));
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// STRONGHOLDS //
	
	public static void addStronghold(Biome biome, String biomeNamespace, String biomePath)
	{
		
		if(!RepurposedStructures.RSConfig.useVanillaStronghold.get() && biome.getCategory() == Category.NETHER && RepurposedStructures.RSConfig.allowNetherStronghold.get())
		{
			if(RepurposedStructures.RSConfig.strongholdSpawnrate.get() != 1001)
			{
				biome.addStructure(RSFeatures.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			}
		}
		else
		{
			//remove vanilla stronghold
			if(biome.structures.containsKey(Feature.STRONGHOLD))
			{
				//only remove from structures list if we are using strictly our own stronghold. 
				//If we are using vanilla, vanilla's needs to be in the structure list or else 
				//vanilla's stronghold's methods for generation will fail as biome doesnt contain vanilla stronghold.
				if(!RepurposedStructures.RSConfig.useVanillaStronghold.get())
					biome.structures.remove(Feature.STRONGHOLD);
			
				
				biome.features.get(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(
												configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && 
												serializeAndCompareFeature(configuredFeature, Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG))));
				
				
				if(RepurposedStructures.RSConfig.strongholdSpawnrate.get() != 1001)
				{
					biome.addStructure(RSFeatures.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
				}
			}
		}
		
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	}
	

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// NETHER TEMPLE //
	
	public static void addNetherTemple(Biome biome, String biomeNamespace, String biomePath)
	{
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.NETHER_TEMPLE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		
		if(RepurposedStructures.RSConfig.netherTempleSpawnrate.get() == 1001)
		{
			return;
		}
		
		if(biome.getCategory() == Category.NETHER && 
				(biomeNamespace.equals("minecraft") || RepurposedStructures.RSConfig.addNetherTempleToModdedBiomes.get()))
		{
			biome.addStructure(RSFeatures.NETHER_TEMPLE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// BADLANDS TEMPLE //

	public static void addBadlandsTemple(Biome biome, String biomeNamespace, String biomePath)
	{
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.BADLANDS_TEMPLE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		
		if(RepurposedStructures.RSConfig.badlandsTempleSpawnrate.get() == 1001)
		{
			return;
		}
		
		if(biome.getCategory() == Category.MESA && 
				(biomeNamespace.equals("minecraft") || RepurposedStructures.RSConfig.addBadlandsTempleToModdedBiomes.get()))
		{
			biome.addStructure(RSFeatures.BADLANDS_TEMPLE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		}
	}


	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// IGLOOS //

	public static void addIgloos(Biome biome, String biomeNamespace, String biomePath)
	{
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.GRASSY_IGLOO.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.STONE_IGLOO.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		
		if(RepurposedStructures.RSConfig.grassyIglooSpawnrate.get() != 1001)
		{
			if((biome.getCategory() == Category.PLAINS || biome.getCategory() == Category.FOREST) && 
					(biomeNamespace.equals("minecraft") || RepurposedStructures.RSConfig.addGrassyIglooToModdedBiomes.get()))
			{
				biome.addStructure(RSFeatures.GRASSY_IGLOO.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			}
		}
		
		if(RepurposedStructures.RSConfig.stoneIglooSpawnrate.get() != 1001)
		{
			String BiomeName = biome.getRegistryName().getPath().toString();
			if((biome.getCategory() == Category.TAIGA && BiomeName.contains("giant")) && 
					(biomeNamespace.equals("minecraft") || RepurposedStructures.RSConfig.addStoneIglooToModdedBiomes.get()))
			{
				biome.addStructure(RSFeatures.STONE_IGLOO.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			}
		}
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// GENERAL UTILITIES //
	
	
	/**
	 * Will serialize (if possible) both features and check if they are the same feature.
	 * If cannot serialize, compare the feature itself to see if it is the same
	 */
	private static boolean serializeAndCompareFeature(ConfiguredFeature<?, ?> feature1, ConfiguredFeature<?, ?> feature2)
	{
		try
		{
			Map<Dynamic<INBT>, Dynamic<INBT>> feature1Map = feature1.serialize(NBTDynamicOps.INSTANCE).getMapValues().get();
			Map<Dynamic<INBT>, Dynamic<INBT>> feature2Map = feature2.serialize(NBTDynamicOps.INSTANCE).getMapValues().get();

			if (feature1Map != null && feature2Map != null)
			{
				return feature1Map.equals(feature2Map);
			}
		}
		catch (Exception e)
		{
			//One of the features cannot be serialized which can only happen with custom modded features
			//Check if the features are the same feature even though the placement or config for the feature might be different. 
			//This is the best way we can remove duplicate modded features as best as we can. (I think)
			if ((feature1.config instanceof DecoratedFeatureConfig && feature2.config instanceof DecoratedFeatureConfig) && 
				((DecoratedFeatureConfig) feature1.config).feature.feature == ((DecoratedFeatureConfig) feature2.config).feature.feature)
			{
				return true;
			}
		}

		return false;
	}
}
