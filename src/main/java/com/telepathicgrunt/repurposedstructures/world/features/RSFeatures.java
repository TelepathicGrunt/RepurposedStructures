package com.telepathicgrunt.repurposedstructures.world.features;

import java.util.Random;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.RegUtil;
import com.telepathicgrunt.repurposedstructures.world.features.structures.JungleFortressStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftConfig;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSStrongholdStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.StructurePieces;

import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.IForgeRegistry;


public class RSFeatures
{
	//Static instance of our structure so we can reference it and add it to biomes easily.
	public static Feature<NoFeatureConfig>					BADLANDS_DUNGEONS		= new DungeonBadlands(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					DARK_FOREST_DUNGEONS	= new DungeonDarkForest(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					DESERT_DUNGEONS			= new DungeonDesert(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					END_DUNGEONS			= new DungeonEnd(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					NETHER_DUNGEONS			= new DungeonNether(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					SNOW_DUNGEONS			= new DungeonSnow(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					SWAMP_DUNGEONS			= new DungeonSwamp(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					MUSHROOM_DUNGEONS		= new DungeonMushroom(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					JUNGLE_DUNGEONS			= new DungeonJungle(NoFeatureConfig::deserialize);

	public static Feature<NoFeatureConfig>					BOULDER_GIANT			= new BoulderGiant(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					BOULDER_TINY			= new BoulderTiny(NoFeatureConfig::deserialize);
	public static AbstractTreeFeature<TreeFeatureConfig>	HORNED_SWAMP_TREE		= new TreeSwampHorned(TreeFeatureConfig::func_227338_a_);
	public static Feature<NoFeatureConfig>					SHORT_VINES				= new VinesShort(NoFeatureConfig::deserialize);

	public static Structure<RSMineshaftConfig>				MINESHAFT				= new RSMineshaftStructure(RSMineshaftConfig::deserialize);
	public static Structure<NoFeatureConfig>				STRONGHOLD				= new RSStrongholdStructure(NoFeatureConfig::deserialize);
	public static Structure<NoFeatureConfig>				JUNGLE_FORTRESS			= new JungleFortressStructure(NoFeatureConfig::deserialize);


	public static void registerFeatures(Register<Feature<?>> event)
	{
		IForgeRegistry<Feature<?>> registry = event.getRegistry();
		RegUtil.register(registry, BADLANDS_DUNGEONS, "dungeons_badlands");
		RegUtil.register(registry, DARK_FOREST_DUNGEONS, "dungeons_dark_forest");
		RegUtil.register(registry, DESERT_DUNGEONS, "dungeons_desert");
		RegUtil.register(registry, END_DUNGEONS, "dungeons_end");
		RegUtil.register(registry, NETHER_DUNGEONS, "dungeons_nether");
		RegUtil.register(registry, SNOW_DUNGEONS, "dungeons_snow");
		RegUtil.register(registry, SWAMP_DUNGEONS, "dungeons_swamp");
		RegUtil.register(registry, MUSHROOM_DUNGEONS, "dungeons_mushroom");
		RegUtil.register(registry, JUNGLE_DUNGEONS, "dungeons_jungle");
		
		RegUtil.register(registry, BOULDER_GIANT, "boulder_giant");
		RegUtil.register(registry, BOULDER_TINY, "boulder_tiny");
		RegUtil.register(registry, HORNED_SWAMP_TREE, "horned_swamp_tree");
		RegUtil.register(registry, SHORT_VINES, "short_vines");
		
		RegUtil.register(registry, MINESHAFT, "mineshaft");
		RegUtil.register(registry, JUNGLE_FORTRESS, "jungle_fortress");


		//RegUtil.register(registry, STRONGHOLD, "stronghold");

		STRONGHOLD.setRegistryName(new ResourceLocation("minecraft:stronghold"));
		registry.register(STRONGHOLD);
		
		//registers the structure pieces.
		StructurePieces.registerStructurePieces();
	}


	/**
	 * Helper method that will return a random dungeon mob that other mods can influence.
	 */
	public static EntityType<?> pickRandomDungeonMob(Random random)
	{
		return net.minecraftforge.common.DungeonHooks.getRandomDungeonMob(random);
	}

}
