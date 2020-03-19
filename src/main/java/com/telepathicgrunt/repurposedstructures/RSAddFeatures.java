package com.telepathicgrunt.repurposedstructures;

import java.util.Map;

import com.mojang.datafixers.Dynamic;
import com.telepathicgrunt.repurposedstructures.world.features.RSFeatures;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftConfig;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftStructure;

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
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class RSAddFeatures
{
	public static void addMineshafts(Biome biome, String biomeNamespace, String biomePath)
	{
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.BIRCH)).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		
		if(RSConfig.mineshaftSpawnrate == 0)
		{
			return;
		}
		
		
		if(RSConfig.birchMineshafts && 
				biomePath.contains("birch") && 
				(biomeNamespace.equals("minecraft") || RSConfig.addMineshaftsToModdedBiomes))
		{
			if(biome.hasStructure(Feature.MINESHAFT) || RSConfig.addMineshaftsToModdedBiomes)
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.BIRCH)));
			}
		}
		
		else if(RSConfig.jungleMineshafts && 
				biome.getCategory() == Category.JUNGLE && 
				(biomeNamespace.equals("minecraft") || RSConfig.addMineshaftsToModdedBiomes))
		{
			if(biome.hasStructure(Feature.MINESHAFT) || RSConfig.addMineshaftsToModdedBiomes)
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.JUNGLE)));
			}
		}
		
		else if(RSConfig.desertMineshafts && 
				biome.getCategory() == Category.DESERT && 
				(biomeNamespace.equals("minecraft") || RSConfig.addMineshaftsToModdedBiomes))
		{
			if(biome.hasStructure(Feature.MINESHAFT) || RSConfig.addMineshaftsToModdedBiomes)
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.DESERT)));
			}
		}
		
		else if(RSConfig.birchMineshafts && 
				biome.getCategory() == Category.EXTREME_HILLS && 
				(biomeNamespace.equals("minecraft") || RSConfig.addMineshaftsToModdedBiomes))
		{
			if(biome.hasStructure(Feature.MINESHAFT) || RSConfig.addMineshaftsToModdedBiomes)
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.STONE)));
			}
		}
		
		else if(RSConfig.savannaMineshafts && 
				biome.getCategory() == Category.SAVANNA && 
				(biomeNamespace.equals("minecraft") || RSConfig.addMineshaftsToModdedBiomes))
		{
			if(biome.hasStructure(Feature.MINESHAFT) || RSConfig.addMineshaftsToModdedBiomes)
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.SAVANNA)));
			}
		}
		
		else if(RSConfig.icyMineshafts && 
				(biome.getCategory() == Category.ICY || biomePath.contains("snowy")) && 
				(biomeNamespace.equals("minecraft") || RSConfig.addMineshaftsToModdedBiomes))
		{
			if(biome.hasStructure(Feature.MINESHAFT) || RSConfig.addMineshaftsToModdedBiomes)
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.ICEY)));
			}
		}
		
		else if(RSConfig.oceanMineshafts && 
				biome.getCategory() == Category.OCEAN && 
				(biomeNamespace.equals("minecraft") || RSConfig.addMineshaftsToModdedBiomes))
		{
			if(biome.hasStructure(Feature.MINESHAFT) || RSConfig.addMineshaftsToModdedBiomes)
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.OCEAN)));
			}
		}
		
		else if(RSConfig.taigaMineshafts && 
				biome.getCategory() == Category.TAIGA && 
				(biomeNamespace.equals("minecraft") || RSConfig.addMineshaftsToModdedBiomes))
		{
			if(biome.hasStructure(Feature.MINESHAFT) || RSConfig.addMineshaftsToModdedBiomes)
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.TAIGA)));
			}
		}
		
		else if(RSConfig.swampAndDarkForestMineshafts && 
				(biome.getCategory() == Category.SWAMP || biomePath.contains("dark_forest") || biomePath.contains("dark_oak")) && 
				(biomeNamespace.equals("minecraft") || RSConfig.addMineshaftsToModdedBiomes))
		{
			if(biome.hasStructure(Feature.MINESHAFT) || RSConfig.addMineshaftsToModdedBiomes)
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.SWAMPORDARKFOREST)));
			}
		}
		
		else if(RSConfig.endMineshafts && 
				(biome.getCategory() == Category.THEEND && biome != Biomes.THE_END && biome != Biomes.SMALL_END_ISLANDS) && 
				(biomeNamespace.equals("minecraft") || RSConfig.addMineshaftsToModdedBiomes))
		{
			biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.END)));
		}
		
		else if(RSConfig.netherMineshafts && 
				biome.getCategory() == Category.NETHER && 
				(biomeNamespace.equals("minecraft") || RSConfig.addMineshaftsToModdedBiomes))
		{
			biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.HELL)));
		}
	}
	

	public static void addJungleFortress(Biome biome, String biomeNamespace, String biomePath)
	{
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.JUNGLE_FORTRESS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	
		if(RSConfig.jungleFortressSpawnrate == 1001)
		{
			return;
		}
		
		if(biome.getCategory() == Category.JUNGLE && 
				(biomeNamespace.equals("minecraft") || RSConfig.addJungleFortressToModdedBiomes))
		{
			biome.addStructure(RSFeatures.JUNGLE_FORTRESS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		}
	}
	
	public static void addDungeons(Biome biome, String biomeNamespace, String biomePath)
	{
		if(RSConfig.jungleDungeons && 
				biome.getCategory() == Category.JUNGLE && 
				(biomeNamespace.equals("minecraft") || RSConfig.addDungeonsToModdedBiomes))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig)configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.JUNGLE_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
		}
		
		else if(RSConfig.badlandsDungeons && 
				biome.getCategory() == Category.MESA && 
				(biomeNamespace.equals("minecraft") || RSConfig.addDungeonsToModdedBiomes))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig)configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.BADLANDS_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
		}
		
		else if(RSConfig.darkForestDungeons && 
				biomePath.contains("dark_forest") && 
				(biomeNamespace.equals("minecraft") || RSConfig.addDungeonsToModdedBiomes))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig)configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.DARK_FOREST_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
		}
		
		else if(RSConfig.desertDungeons && 
				biome.getCategory() == Category.DESERT && 
				(biomeNamespace.equals("minecraft") || RSConfig.addDungeonsToModdedBiomes))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig)configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.DESERT_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
		}
		
		else if(RSConfig.mushroomDungeons && 
				biome.getCategory() == Category.MUSHROOM && 
				(biomeNamespace.equals("minecraft") || RSConfig.addDungeonsToModdedBiomes))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig)configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.MUSHROOM_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
		}
		
		else if(RSConfig.swampDungeons && 
				biome.getCategory() == Category.SWAMP && 
				(biomeNamespace.equals("minecraft") || RSConfig.addDungeonsToModdedBiomes))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig)configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.SWAMP_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
		}
		
		else if(RSConfig.snowDungeons && 
				biome.getCategory() == Category.ICY && 
				(biomeNamespace.equals("minecraft") || RSConfig.addDungeonsToModdedBiomes))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && ((DecoratedFeatureConfig)configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.SNOW_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
		}
		
		else if(RSConfig.netherDungeons && 
				biome.getCategory() == Category.NETHER && 
				(biomeNamespace.equals("minecraft") || RSConfig.addDungeonsToModdedBiomes))
		{
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.NETHER_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
		}
		
		else if(RSConfig.endDungeons && 
				(biome.getCategory() == Category.THEEND && biome != Biomes.THE_END && biome != Biomes.SMALL_END_ISLANDS) && 
				(biomeNamespace.equals("minecraft") || RSConfig.addDungeonsToModdedBiomes))
		{
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.END_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
		}
	}
	
	private static final ConfiguredFeature<?,?> VANILLA_SWAMP_TREE = Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.SWAMP_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 1)));
	private static final ConfiguredFeature<?,?> VANILLA_BOULDER = Feature.FOREST_ROCK.withConfiguration(new BlockBlobConfig(Blocks.MOSSY_COBBLESTONE.getDefaultState(), 0)).withPlacement(Placement.FOREST_ROCK.configure(new FrequencyConfig(3)));
	public static void addMiscFeatures(Biome biome, String biomeNamespace, String biomePath)
	{
		//only exists in vanilla biomes
		if(RSConfig.hornedSwampTree && biome == Biomes.SWAMP && biomeNamespace.equals("minecraft"))
		{
			biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RSFeatures.HORNED_SWAMP_TREE.withConfiguration(DefaultBiomeFeatures.SWAMP_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.7F, 1))));
		}
		
		//can exist in modded biomes too
		else if(RSConfig.hornedSwampTree && 
				((biome == Biomes.SWAMP_HILLS && biomeNamespace.equals("minecraft")) ||
				(RSConfig.addMiscToModdedBiomes && !biomeNamespace.equals("minecraft") && biomePath.contains("swamp") && biome != Biomes.SWAMP)))
		{
			//replace the swamp tree with our own
			biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && serializeAndCompareFeature(configuredFeature, VANILLA_SWAMP_TREE));
			biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RSFeatures.HORNED_SWAMP_TREE.withConfiguration(DefaultBiomeFeatures.SWAMP_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.8F, 1))));
		}
		
		else if(RSConfig.boulderTiny && 
				(((biome == Biomes.TAIGA_MOUNTAINS || biome == Biomes.SNOWY_TAIGA_MOUNTAINS) && biomeNamespace.equals("minecraft")) ||
				(RSConfig.addMiscToModdedBiomes && !biomeNamespace.equals("minecraft") && biomePath.contains("taiga"))))
		{
			biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, RSFeatures.BOULDER_TINY.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.FOREST_ROCK.configure(new FrequencyConfig(2))));
		}
		
		else if(RSConfig.boulderGiant && 
				(((biome == Biomes.GIANT_SPRUCE_TAIGA_HILLS || biome == Biomes.GIANT_TREE_TAIGA_HILLS) && biomeNamespace.equals("minecraft")) ||
				(RSConfig.addMiscToModdedBiomes && !biomeNamespace.equals("minecraft") && ((biomePath.contains("giant") && biomePath.contains("taiga")) || biomePath.contains("redwood")))))
		{
			//replace the boulders with our own
			biome.getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && serializeAndCompareFeature(configuredFeature, VANILLA_BOULDER));
			biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, RSFeatures.BOULDER_GIANT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.FOREST_ROCK.configure(new FrequencyConfig(2))));
		}
	}
	
	
	public static void addStronghold(Biome biome, String biomeNamespace, String biomePath)
	{
		//remove vanilla stronghold
		if(biome.structures.containsKey(Feature.STRONGHOLD))
		{
			biome.structures.remove(Feature.STRONGHOLD);
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && serializeAndCompareFeature(configuredFeature, Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)));
			
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));

			if(RSConfig.strongholdSpawnrate != 1001)
			{
				biome.addStructure(RSFeatures.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			}
		}
	}
	

	
	
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
