package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.world.features.RSFeatures;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftConfig;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftStructure;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BlockBlobConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
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
		
		
		if(biomePath.contains("birch") && biomeNamespace.equals("minecraft"))
		{
			if(biome.hasStructure(Feature.MINESHAFT))
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.BIRCH)));
			}
		}
		else if(biome.getCategory() == Category.JUNGLE && biomeNamespace.equals("minecraft"))
		{
			if(biome.hasStructure(Feature.MINESHAFT))
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.JUNGLE)));
			}
		}
		else if(biome.getCategory() == Category.DESERT && biomeNamespace.equals("minecraft"))
		{
			if(biome.hasStructure(Feature.MINESHAFT))
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.DESERT)));
			}
		}
		else if(biome.getCategory() == Category.EXTREME_HILLS && biomeNamespace.equals("minecraft"))
		{
			if(biome.hasStructure(Feature.MINESHAFT))
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.STONE)));
			}
		}
		else if(biome.getCategory() == Category.SAVANNA && biomeNamespace.equals("minecraft"))
		{
			if(biome.hasStructure(Feature.MINESHAFT))
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.SAVANNA)));
			}
		}
		else if((biome.getCategory() == Category.ICY || biomePath.contains("snowy")) && biomeNamespace.equals("minecraft"))
		{
			if(biome.hasStructure(Feature.MINESHAFT))
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.ICEY)));
			}
		}
		else if(biome.getCategory() == Category.OCEAN && biomeNamespace.equals("minecraft"))
		{
			if(biome.hasStructure(Feature.MINESHAFT))
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.OCEAN)));
			}
		}
		else if(biome.getCategory() == Category.TAIGA && biomeNamespace.equals("minecraft"))
		{
			if(biome.hasStructure(Feature.MINESHAFT))
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.TAIGA)));
			}
		}
		else if((biome.getCategory() == Category.SWAMP || biomePath.contains("dark_forest")) && biomeNamespace.equals("minecraft"))
		{
			if(biome.hasStructure(Feature.MINESHAFT))
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.SWAMPORDARKFOREST)));
			}
		}
		else if((biome.getCategory() == Category.THEEND && biome != Biomes.THE_END && biome != Biomes.SMALL_END_ISLANDS) && biomeNamespace.equals("minecraft"))
		{
			if(biome.hasStructure(Feature.MINESHAFT))
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.END)));
			}
		}
		else if(biome.getCategory() == Category.NETHER && biomeNamespace.equals("minecraft"))
		{
			if(biome.hasStructure(Feature.MINESHAFT))
			{
				//replace vanilla mineshaft with our own
				biome.structures.remove(Feature.MINESHAFT);
				biome.addStructure(RSFeatures.MINESHAFT.withConfiguration(new RSMineshaftConfig(RSMineshaftStructure.Type.HELL)));
			}
		}
	}
	

	public static void addJungleFortress(Biome biome, String biomeNamespace, String biomePath)
	{
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RSFeatures.JUNGLE_FORTRESS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
	
		if(RSConfig.jungleFortressSpawnrate == 1001)
		{
			return;
		}
		
		if(biome.getCategory() == Category.JUNGLE && biomeNamespace.equals("minecraft"))
		{
			biome.addStructure(RSFeatures.JUNGLE_FORTRESS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		}
	}
	
	private static final ConfiguredFeature<?,?> VANILLA_DUNGEON = Feature.MONSTER_ROOM.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8)));
	public static void addDungeons(Biome biome, String biomeNamespace, String biomePath)
	{
		if(RSConfig.jungleDungeons && biome.getCategory() == Category.JUNGLE && biomeNamespace.equals("minecraft"))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).remove(VANILLA_DUNGEON);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.JUNGLE_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
		}
		else if(RSConfig.badlandsDungeons && biome.getCategory() == Category.MESA && biomeNamespace.equals("minecraft"))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).remove(VANILLA_DUNGEON);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.BADLANDS_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
		}
		else if(RSConfig.darkForestDungeons && biomePath.contains("dark_forest") && biomeNamespace.equals("minecraft"))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).remove(VANILLA_DUNGEON);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.DARK_FOREST_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
		}
		else if(RSConfig.desertDungeons && biome.getCategory() == Category.DESERT && biomeNamespace.equals("minecraft"))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).remove(VANILLA_DUNGEON);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.DESERT_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
		}
		else if(RSConfig.mushroomDungeons && biome.getCategory() == Category.MUSHROOM && biomeNamespace.equals("minecraft"))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).remove(VANILLA_DUNGEON);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.MUSHROOM_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
		}
		else if(RSConfig.swampDungeons && biome.getCategory() == Category.SWAMP && biomeNamespace.equals("minecraft"))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).remove(VANILLA_DUNGEON);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.SWAMP_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
		}
		else if(RSConfig.snowDungeons && biome.getCategory() == Category.ICY && biomeNamespace.equals("minecraft"))
		{
			//replace vanilla dungeon with our own
			biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).remove(VANILLA_DUNGEON);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.SNOW_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
		}
		else if(RSConfig.netherDungeons && biome.getCategory() == Category.NETHER && biomeNamespace.equals("minecraft"))
		{
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.NETHER_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
		}
		else if(RSConfig.endDungeons && (biome.getCategory() == Category.THEEND && biome != Biomes.THE_END && biome != Biomes.SMALL_END_ISLANDS) && biomeNamespace.equals("minecraft"))
		{
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, RSFeatures.END_DUNGEONS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
		}
	}
	
	public static void addMiscFeatures(Biome biome, String biomeNamespace, String biomePath)
	{
		if(RSConfig.hornedSwampTree && biome == Biomes.SWAMP && biomeNamespace.equals("minecraft"))
		{
			biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RSFeatures.HORNED_SWAMP_TREE.withConfiguration(DefaultBiomeFeatures.SWAMP_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.7F, 1))));
		}
		else if(RSConfig.hornedSwampTree && biome == Biomes.SWAMP_HILLS && biomeNamespace.equals("minecraft"))
		{
			//replace the swamp tree with our own
			biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).remove(Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.SWAMP_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
			biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RSFeatures.HORNED_SWAMP_TREE.withConfiguration(DefaultBiomeFeatures.SWAMP_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.8F, 1))));
		}
		else if(RSConfig.boulderTiny && (biome == Biomes.TAIGA_MOUNTAINS || biome == Biomes.SNOWY_TAIGA_MOUNTAINS) && biomeNamespace.equals("minecraft"))
		{
			biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, RSFeatures.BOULDER_TINY.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.FOREST_ROCK.configure(new FrequencyConfig(2))));
		}
		else if(RSConfig.boulderGiant && (biome == Biomes.GIANT_SPRUCE_TAIGA_HILLS || biome == Biomes.GIANT_TREE_TAIGA_HILLS) && biomeNamespace.equals("minecraft"))
		{
			//replace the boulders with our own
			biome.getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS).remove(Feature.FOREST_ROCK.withConfiguration(new BlockBlobConfig(Blocks.MOSSY_COBBLESTONE.getDefaultState(), 0)).withPlacement(Placement.FOREST_ROCK.configure(new FrequencyConfig(3))));
			biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, RSFeatures.BOULDER_TINY.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.FOREST_ROCK.configure(new FrequencyConfig(2))));
		}
	}
	
	
	public static void removeStronghold(Biome biome, String biomeNamespace, String biomePath)
	{
		//remove stronghold as config states it will not spawn now
		biome.structures.remove(Feature.STRONGHOLD);
	}
}
