package com.telepathicgrunt.repurposedstructures.world.features;

import java.util.Random;

import com.telepathicgrunt.repurposedstructures.utils.RegUtil;
import com.telepathicgrunt.repurposedstructures.world.features.structures.BadlandsTempleStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.DummyMineshaftStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.IglooGrassyStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.IglooStoneStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.JungleFortressStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.NetherTempleStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftBirchStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftDesertStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftEndStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftNetherStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftIcyStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftJungleStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftOceanStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftSavannaStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftStoneStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftSwampOrDarkForestStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftTaigaStructure;
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
	public static Feature<NoFeatureConfig>					DARK_FOREST_DUNGEONS		= new DungeonDarkForest(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					DESERT_DUNGEONS			= new DungeonDesert(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					END_DUNGEONS			= new DungeonEnd(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					NETHER_DUNGEONS			= new DungeonNether(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					SNOW_DUNGEONS			= new DungeonSnow(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					SWAMP_DUNGEONS			= new DungeonSwamp(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					MUSHROOM_DUNGEONS		= new DungeonMushroom(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					JUNGLE_DUNGEONS			= new DungeonJungle(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					OCEAN_DUNGEONS			= new DungeonOcean(NoFeatureConfig::deserialize);

	public static Feature<NoFeatureConfig>					BADLANDS_WELL			= new WellBadlands(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					NETHER_WELL			= new WellNether(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					SNOW_WELL			= new WellSnow(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					MOSSY_STONE_WELL		= new WellMossyStone(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					FOREST_WELL			= new WellForest(NoFeatureConfig::deserialize);

	public static Feature<NoFeatureConfig>					BOULDER_GIANT			= new BoulderGiant(NoFeatureConfig::deserialize);
	public static Feature<NoFeatureConfig>					BOULDER_TINY			= new BoulderTiny(NoFeatureConfig::deserialize);
	public static AbstractTreeFeature<TreeFeatureConfig>			HORNED_SWAMP_TREE		= new TreeSwampHorned(TreeFeatureConfig::func_227338_a_);
	public static Feature<NoFeatureConfig>					SHORT_VINES			= new VinesShort(NoFeatureConfig::deserialize);

	public static Structure<NoFeatureConfig>				BIRCH_MINESHAFT			= new RSMineshaftBirchStructure(NoFeatureConfig::deserialize);
	public static Structure<NoFeatureConfig>				DESERT_MINESHAFT		= new RSMineshaftDesertStructure(NoFeatureConfig::deserialize);
	public static Structure<NoFeatureConfig>				END_MINESHAFT			= new RSMineshaftEndStructure(NoFeatureConfig::deserialize);
	public static Structure<NoFeatureConfig>				HELL_MINESHAFT			= new RSMineshaftNetherStructure(NoFeatureConfig::deserialize);
	public static Structure<NoFeatureConfig>				ICY_MINESHAFT			= new RSMineshaftIcyStructure(NoFeatureConfig::deserialize);
	public static Structure<NoFeatureConfig>				JUNGLE_MINESHAFT		= new RSMineshaftJungleStructure(NoFeatureConfig::deserialize);
	public static Structure<NoFeatureConfig>				OCEAN_MINESHAFT			= new RSMineshaftOceanStructure(NoFeatureConfig::deserialize);
	public static Structure<NoFeatureConfig>				SAVANNA_MINESHAFT		= new RSMineshaftSavannaStructure(NoFeatureConfig::deserialize);
	public static Structure<NoFeatureConfig>				STONE_MINESHAFT			= new RSMineshaftStoneStructure(NoFeatureConfig::deserialize);
	public static Structure<NoFeatureConfig>				SWAMP_OR_DARK_FOREST_MINESHAFT	= new RSMineshaftSwampOrDarkForestStructure(NoFeatureConfig::deserialize);
	public static Structure<NoFeatureConfig>				TAIGA_MINESHAFT			= new RSMineshaftTaigaStructure(NoFeatureConfig::deserialize);
	public static Structure<NoFeatureConfig>				STRONGHOLD			= new RSStrongholdStructure(NoFeatureConfig::deserialize);
	public static Structure<NoFeatureConfig>				JUNGLE_FORTRESS			= new JungleFortressStructure(NoFeatureConfig::deserialize);
	public static Structure<NoFeatureConfig>				NETHER_TEMPLE			= new NetherTempleStructure(NoFeatureConfig::deserialize);
	public static Structure<NoFeatureConfig>				BADLANDS_TEMPLE			= new BadlandsTempleStructure(NoFeatureConfig::deserialize);
	public static Structure<NoFeatureConfig>				GRASSY_IGLOO			= new IglooGrassyStructure(NoFeatureConfig::deserialize);
	public static Structure<NoFeatureConfig>				STONE_IGLOO			= new IglooStoneStructure(NoFeatureConfig::deserialize);
	public static Structure<NoFeatureConfig>				DUMMY_MINESHAFT_STRUCTURE			= new DummyMineshaftStructure(NoFeatureConfig::deserialize);


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
		RegUtil.register(registry, OCEAN_DUNGEONS, "dungeons_ocean");

		RegUtil.register(registry, BADLANDS_WELL, "well_badlands");
		RegUtil.register(registry, NETHER_WELL, "well_nether");
		RegUtil.register(registry, SNOW_WELL, "well_snow");
		RegUtil.register(registry, MOSSY_STONE_WELL, "well_mossy_stone");
		RegUtil.register(registry, FOREST_WELL, "well_forest");
		
		RegUtil.register(registry, BOULDER_GIANT, "boulder_giant");
		RegUtil.register(registry, BOULDER_TINY, "boulder_tiny");
		RegUtil.register(registry, HORNED_SWAMP_TREE, "horned_swamp_tree");
		RegUtil.register(registry, SHORT_VINES, "short_vines");

		RegUtil.register(registry, BIRCH_MINESHAFT, "birch_mineshaft");
		RegUtil.register(registry, DESERT_MINESHAFT, "desert_mineshaft");
		RegUtil.register(registry, END_MINESHAFT, "end_mineshaft");
		RegUtil.register(registry, HELL_MINESHAFT, "hell_mineshaft");
		RegUtil.register(registry, ICY_MINESHAFT, "icy_mineshaft");
		RegUtil.register(registry, JUNGLE_MINESHAFT, "jungle_mineshaft");
		RegUtil.register(registry, OCEAN_MINESHAFT, "ocean_mineshaft");
		RegUtil.register(registry, SAVANNA_MINESHAFT, "savanna_mineshaft");
		RegUtil.register(registry, STONE_MINESHAFT, "stone_mineshaft");
		RegUtil.register(registry, SWAMP_OR_DARK_FOREST_MINESHAFT, "swamp_or_dark_forest_mineshaft");
		RegUtil.register(registry, TAIGA_MINESHAFT, "taiga_mineshaft");
		RegUtil.register(registry, JUNGLE_FORTRESS, "jungle_fortress");
		RegUtil.register(registry, NETHER_TEMPLE, "nether_temple");
		RegUtil.register(registry, BADLANDS_TEMPLE, "badlands_temple");
		RegUtil.register(registry, GRASSY_IGLOO, "grassy_igloo");
		RegUtil.register(registry, STONE_IGLOO, "stone_igloo");

		STRONGHOLD.setRegistryName(new ResourceLocation("minecraft:stronghold"));
		registry.register(STRONGHOLD);
		
		//registers the structure pieces.
		StructurePieces.registerStructurePieces();
		
		//Legacy structure to replace with dummy structure 
		RegUtil.register(registry, DUMMY_MINESHAFT_STRUCTURE, "mineshaft");
	}
	

	/**
	 * Helper method that will return a random dungeon mob that other mods can influence.
	 */
	public static EntityType<?> pickRandomDungeonMob(Random random)
	{
		return net.minecraftforge.common.DungeonHooks.getRandomDungeonMob(random);
	}

}
