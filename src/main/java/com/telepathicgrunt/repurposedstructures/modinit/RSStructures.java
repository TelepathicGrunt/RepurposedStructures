package com.telepathicgrunt.repurposedstructures.modinit;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.PillagerOutpostStructureAccessor;
import com.telepathicgrunt.repurposedstructures.world.structures.*;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.NetherShipwreckConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionPieces;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSMineshaftPieces;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class RSStructures {
    /**
     * --------------------------------------------------------------------------
     * |															        	|
     * |			HELLO READERS! IF YOU'RE HERE, YOU'RE PROBABLY		    	|
     * |			LOOKING FOR A TUTORIAL ON HOW TO DO STRUCTURES	    		|
     * |																        |
     * --------------------------------------------------------------------------
     *
     * Don't worry, I actually have a structure tutorial
     * mod already setup for you to check out! It's full
     * of comments on what does what and how to make structures.
     *
     * Here's the link! https://github.com/TelepathicGrunt/StructureTutorialMod
     *
     * Good luck and have fun modding!
     */

	public static final DeferredRegister<Structure<?>> STRUCTURE_FEATURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, RepurposedStructures.MODID);
    public static final Map<Structure<?>, StructureSeparationSettings> RS_STRUCTURES = new HashMap<>();
    public static final Set<ResourceLocation> RS_STRUCTURE_START_PIECES = new HashSet<>();

    //Mineshafts
    public static final RegistryObject<Structure<NoFeatureConfig>> BIRCH_MINESHAFT = addToStructureMaps("mineshaft_birch", () -> (new RSMineshaftStructure(RSMineshaftPieces.Type.BIRCH, RepurposedStructures.RSMineshaftsConfig.birchMineshaftSpawnrate.get(), RepurposedStructures.RSMineshaftsConfig.birchMineshaftMaxHeight.get(), RepurposedStructures.RSMineshaftsConfig.birchMineshaftMinHeight.get())));
    public static final RegistryObject<Structure<NoFeatureConfig>> DESERT_MINESHAFT = addToStructureMaps("mineshaft_desert", () -> (new RSMineshaftStructure(RSMineshaftPieces.Type.DESERT, RepurposedStructures.RSMineshaftsConfig.desertMineshaftSpawnrate.get(), RepurposedStructures.RSMineshaftsConfig.desertMineshaftMaxHeight.get(), RepurposedStructures.RSMineshaftsConfig.desertMineshaftMinHeight.get())));
    public static final RegistryObject<Structure<NoFeatureConfig>> END_MINESHAFT = addToStructureMaps("mineshaft_end", () -> (new RSMineshaftEndStructure(RSMineshaftPieces.Type.END, RepurposedStructures.RSMineshaftsConfig.endMineshaftSpawnrate.get(), RepurposedStructures.RSMineshaftsConfig.endMineshaftMaxHeight.get(), RepurposedStructures.RSMineshaftsConfig.endMineshaftMinHeight.get())));
    public static final RegistryObject<Structure<NoFeatureConfig>> NETHER_MINESHAFT = addToStructureMaps("mineshaft_nether", () -> (new RSMineshaftStructure(RSMineshaftPieces.Type.NETHER, RepurposedStructures.RSMineshaftsConfig.netherMineshaftSpawnrate.get(), RepurposedStructures.RSMineshaftsConfig.netherMineshaftMaxHeight.get(), RepurposedStructures.RSMineshaftsConfig.netherMineshaftMinHeight.get())));
    public static final RegistryObject<Structure<NoFeatureConfig>> CRIMSON_MINESHAFT = addToStructureMaps("mineshaft_crimson", () -> (new RSMineshaftStructure(RSMineshaftPieces.Type.CRIMSON, RepurposedStructures.RSMineshaftsConfig.crimsonMineshaftSpawnrate.get(), RepurposedStructures.RSMineshaftsConfig.crimsonMineshaftMaxHeight.get(), RepurposedStructures.RSMineshaftsConfig.crimsonMineshaftMinHeight.get())));
    public static final RegistryObject<Structure<NoFeatureConfig>> WARPED_MINESHAFT = addToStructureMaps("mineshaft_warped", () -> (new RSMineshaftStructure(RSMineshaftPieces.Type.WARPED, RepurposedStructures.RSMineshaftsConfig.warpedMineshaftSpawnrate.get(), RepurposedStructures.RSMineshaftsConfig.warpedMineshaftMaxHeight.get(), RepurposedStructures.RSMineshaftsConfig.warpedMineshaftMinHeight.get())));
    public static final RegistryObject<Structure<NoFeatureConfig>> ICY_MINESHAFT = addToStructureMaps("mineshaft_icy", () -> (new RSMineshaftStructure(RSMineshaftPieces.Type.ICY, RepurposedStructures.RSMineshaftsConfig.icyMineshaftSpawnrate.get(), RepurposedStructures.RSMineshaftsConfig.icyMineshaftMaxHeight.get(), RepurposedStructures.RSMineshaftsConfig.icyMineshaftMinHeight.get())));
    public static final RegistryObject<Structure<NoFeatureConfig>> JUNGLE_MINESHAFT = addToStructureMaps("mineshaft_jungle", () -> (new RSMineshaftStructure(RSMineshaftPieces.Type.JUNGLE, RepurposedStructures.RSMineshaftsConfig.jungleMineshaftSpawnrate.get(), RepurposedStructures.RSMineshaftsConfig.jungleMineshaftMaxHeight.get(), RepurposedStructures.RSMineshaftsConfig.jungleMineshaftMinHeight.get())));
    public static final RegistryObject<Structure<NoFeatureConfig>> OCEAN_MINESHAFT = addToStructureMaps("mineshaft_ocean", () -> (new RSMineshaftStructure(RSMineshaftPieces.Type.OCEAN, RepurposedStructures.RSMineshaftsConfig.oceanMineshaftSpawnrate.get(), RepurposedStructures.RSMineshaftsConfig.oceanMineshaftMaxHeight.get(), RepurposedStructures.RSMineshaftsConfig.oceanMineshaftMinHeight.get())));
    public static final RegistryObject<Structure<NoFeatureConfig>> SAVANNA_MINESHAFT = addToStructureMaps("mineshaft_savanna", () -> (new RSMineshaftStructure(RSMineshaftPieces.Type.SAVANNA, RepurposedStructures.RSMineshaftsConfig.savannaMineshaftSpawnrate.get(), RepurposedStructures.RSMineshaftsConfig.savannaMineshaftMaxHeight.get(), RepurposedStructures.RSMineshaftsConfig.savannaMineshaftMinHeight.get())));
    public static final RegistryObject<Structure<NoFeatureConfig>> STONE_MINESHAFT = addToStructureMaps("mineshaft_stone", () -> (new RSMineshaftStructure(RSMineshaftPieces.Type.STONE, RepurposedStructures.RSMineshaftsConfig.stoneMineshaftSpawnrate.get(), RepurposedStructures.RSMineshaftsConfig.stoneMineshaftMaxHeight.get(), RepurposedStructures.RSMineshaftsConfig.stoneMineshaftMinHeight.get())));
    public static final RegistryObject<Structure<NoFeatureConfig>> SWAMP_OR_DARK_FOREST_MINESHAFT = addToStructureMaps("mineshaft_swamp_or_dark_forest", () -> (new RSMineshaftStructure(RSMineshaftPieces.Type.SWAMPORDARKFOREST, RepurposedStructures.RSMineshaftsConfig.swampAndDarkForestMineshaftSpawnrate.get(), RepurposedStructures.RSMineshaftsConfig.swampAndDarkForestMineshaftMaxHeight.get(), RepurposedStructures.RSMineshaftsConfig.swampAndDarkForestMineshaftMinHeight.get())));
    public static final RegistryObject<Structure<NoFeatureConfig>> TAIGA_MINESHAFT = addToStructureMaps("mineshaft_taiga", () -> (new RSMineshaftStructure(RSMineshaftPieces.Type.TAIGA, RepurposedStructures.RSMineshaftsConfig.taigaMineshaftSpawnrate.get(), RepurposedStructures.RSMineshaftsConfig.taigaMineshaftMaxHeight.get(), RepurposedStructures.RSMineshaftsConfig.taigaMineshaftMinHeight.get())));

    //Strongholds
    public static final RegistryObject<Structure<NoFeatureConfig>> STONEBRICK_STRONGHOLD = addToStructureMaps("stronghold_stonebrick", RSStonebrickStrongholdStructure::new);
    public static final RegistryObject<Structure<NoFeatureConfig>> NETHER_STRONGHOLD = addToStructureMaps("stronghold_nether", RSNetherStrongholdStructure::new);
    
    //Igloos and Fortress
    public static final RegistryObject<Structure<NoFeatureConfig>> JUNGLE_FORTRESS = addToStructureMaps("fortress_jungle", FortressJungleStructure::new);
    public static final RegistryObject<Structure<NoFeatureConfig>> GRASSY_IGLOO = addToStructureMaps("igloo_grassy", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "igloos/grassy_top"), 20, 0, 0, 0, new HashSet<>())));
    public static final RegistryObject<Structure<NoFeatureConfig>> STONE_IGLOO = addToStructureMaps("igloo_stone", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "igloos/stone_top"), 20, 0, 0, 0, new HashSet<>())));

    //Temples
    public static final RegistryObject<Structure<NoFeatureConfig>> NETHER_WASTELAND_TEMPLE = addToStructureMaps("temple_nether_wasteland", () -> (new TempleNetherStructure(new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_wasteland"))));
    public static final RegistryObject<Structure<NoFeatureConfig>> NETHER_BASALT_TEMPLE = addToStructureMaps("temple_nether_basalt", () -> (new TempleNetherStructure(new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_basalt"))));
    public static final RegistryObject<Structure<NoFeatureConfig>> NETHER_WARPED_TEMPLE = addToStructureMaps("temple_nether_warped", () -> (new TempleNetherStructure(new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_warped"))));
    public static final RegistryObject<Structure<NoFeatureConfig>> NETHER_CRIMSON_TEMPLE = addToStructureMaps("temple_nether_crimson", () -> (new TempleNetherStructure(new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_crimson"))));
    public static final RegistryObject<Structure<NoFeatureConfig>> NETHER_SOUL_TEMPLE = addToStructureMaps("temple_nether_soul", () -> (new TempleNetherStructure(new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_soul"))));

    //Outposts
    public static final RegistryObject<Structure<NoFeatureConfig>> NETHER_BRICK_OUTPOST = addToStructureMaps("outpost_nether_brick", () -> (new OutpostNetherStructure(new ResourceLocation(RepurposedStructures.MODID,"outposts/nether_brick/base_plates"))));
	public static final RegistryObject<Structure<NoFeatureConfig>> WARPED_OUTPOST = addToStructureMaps("outpost_warped", () -> (new OutpostNetherStructure(new ResourceLocation(RepurposedStructures.MODID,"outposts/warped/base_plates"))));
	public static final RegistryObject<Structure<NoFeatureConfig>> CRIMSON_OUTPOST = addToStructureMaps("outpost_crimson", () -> (new OutpostNetherStructure(new ResourceLocation(RepurposedStructures.MODID,"outposts/crimson/base_plates"))));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_BIRCH = addToStructureMaps("outpost_birch", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "outposts/birch/base_plates"), 11, 0, 0, 5, Stream.of(RSStructureTagMap.STRUCTURE_TAGS.VILLAGE).collect(Collectors.toSet()), PillagerOutpostStructureAccessor.rs_getPILLAGE_OUTPOST_ENEMIES(), ImmutableList.of())));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_JUNGLE = addToStructureMaps("outpost_jungle", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "outposts/jungle/base_plates"), 11, 0, 1, 5, Stream.of(RSStructureTagMap.STRUCTURE_TAGS.VILLAGE).collect(Collectors.toSet()), PillagerOutpostStructureAccessor.rs_getPILLAGE_OUTPOST_ENEMIES(), ImmutableList.of())));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_GIANT_TREE_TAIGA = addToStructureMaps("outpost_giant_tree_taiga", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "outposts/giant_tree_taiga/base_plates"), 11, 0, 0, 5, Stream.of(RSStructureTagMap.STRUCTURE_TAGS.VILLAGE).collect(Collectors.toSet()), PillagerOutpostStructureAccessor.rs_getPILLAGE_OUTPOST_ENEMIES(), ImmutableList.of())));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_DESERT = addToStructureMaps("outpost_desert", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "outposts/desert/base_plates"), 11, 0, 1, 5, Stream.of(RSStructureTagMap.STRUCTURE_TAGS.VILLAGE).collect(Collectors.toSet()), PillagerOutpostStructureAccessor.rs_getPILLAGE_OUTPOST_ENEMIES(), ImmutableList.of())));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_BADLANDS = addToStructureMaps("outpost_badlands", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "outposts/badlands/base_plates"), 11, 0, 0, 5, Stream.of(RSStructureTagMap.STRUCTURE_TAGS.VILLAGE).collect(Collectors.toSet()), 20, 1, PillagerOutpostStructureAccessor.rs_getPILLAGE_OUTPOST_ENEMIES(), ImmutableList.of())));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_SNOWY = addToStructureMaps("outpost_snowy", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "outposts/snowy/base_plates"), 11, 0, 1, 5, Stream.of(RSStructureTagMap.STRUCTURE_TAGS.VILLAGE).collect(Collectors.toSet()), PillagerOutpostStructureAccessor.rs_getPILLAGE_OUTPOST_ENEMIES(), ImmutableList.of())));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_ICY = addToStructureMaps("outpost_icy", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "outposts/icy/base_plates"), 11, 0, 0, 5, Stream.of(RSStructureTagMap.STRUCTURE_TAGS.VILLAGE).collect(Collectors.toSet()), PillagerOutpostStructureAccessor.rs_getPILLAGE_OUTPOST_ENEMIES(), ImmutableList.of())));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_TAIGA = addToStructureMaps("outpost_taiga", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "outposts/taiga/base_plates"), 11, 0, 1, 5, Stream.of(RSStructureTagMap.STRUCTURE_TAGS.VILLAGE).collect(Collectors.toSet()), PillagerOutpostStructureAccessor.rs_getPILLAGE_OUTPOST_ENEMIES(), ImmutableList.of())));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_OAK = addToStructureMaps("outpost_oak", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "outposts/oak/base_plates"), 11, 0, 1, 5, Stream.of(RSStructureTagMap.STRUCTURE_TAGS.VILLAGE).collect(Collectors.toSet()), PillagerOutpostStructureAccessor.rs_getPILLAGE_OUTPOST_ENEMIES(), ImmutableList.of())));

	//Pyramids
	public static final RegistryObject<Structure<NoFeatureConfig>> NETHER_PYRAMID = addToStructureMaps("pyramid_nether", () -> (new GenericNetherJigsawHighStructure(new ResourceLocation(RepurposedStructures.MODID, ":temples/pyramid_nether"), 1, -4, 0)));
	public static final RegistryObject<Structure<NoFeatureConfig>> BADLANDS_PYRAMID = addToStructureMaps("pyramid_badlands", () -> (new BuriableStructure(new ResourceLocation(RepurposedStructures.MODID, "temples/pyramid_badlands"))));
    public static final RegistryObject<Structure<NoFeatureConfig>> PYRAMID_SNOWY = addToStructureMaps("pyramid_snowy", () -> (new BuriableStructure(new ResourceLocation(RepurposedStructures.MODID, "temples/pyramid_snowy"))));

    //Shipwrecks
	public static final RegistryObject<Structure<NoFeatureConfig>> END_SHIPWRECK = addToStructureMaps("shipwreck_end", ShipwreckEndStructure::new);
	public static final RegistryObject<Structure<NetherShipwreckConfig>> NETHER_BRICKS_SHIPWRECK = addToStructureMaps("shipwreck_nether_bricks", () -> (new ShipwreckNetherStructure(new ResourceLocation(RepurposedStructures.MODID, "shipwrecks/nether_bricks"), -3)));
	public static final RegistryObject<Structure<NetherShipwreckConfig>> CRIMSON_SHIPWRECK = addToStructureMaps("shipwreck_crimson", () -> (new ShipwreckNetherStructure(new ResourceLocation(RepurposedStructures.MODID, "shipwrecks/crimson"), -4)));
	public static final RegistryObject<Structure<NetherShipwreckConfig>> WARPED_SHIPWRECK = addToStructureMaps("shipwreck_warped", () -> (new ShipwreckNetherStructure(new ResourceLocation(RepurposedStructures.MODID, "shipwrecks/warped"), -4)));
	
	//Villages
	public static final RegistryObject<Structure<NoFeatureConfig>> BADLANDS_VILLAGE = addToStructureMaps("village_badlands", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "village/badlands/town_centers"), 10, 0, 1, 5, Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION).collect(Collectors.toSet()), 20, 2)));
	public static final RegistryObject<Structure<NoFeatureConfig>> BIRCH_VILLAGE = addToStructureMaps("village_birch", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "village/birch/town_centers"), 6, 0, 1, 5, Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION).collect(Collectors.toSet()))));
	public static final RegistryObject<Structure<NoFeatureConfig>> DARK_FOREST_VILLAGE = addToStructureMaps("village_dark_oak", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "village/dark_forest/town_centers"), 6, 0, 1, 5, Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION).collect(Collectors.toSet()))));
	public static final RegistryObject<Structure<NoFeatureConfig>> JUNGLE_VILLAGE = addToStructureMaps("village_jungle", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "village/jungle/town_centers"), 8, 0, 1, 5, Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION).collect(Collectors.toSet()))));
	public static final RegistryObject<Structure<NoFeatureConfig>> SWAMP_VILLAGE = addToStructureMaps("village_swamp", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "village/swamp/town_centers"), 6, -1, 1, 5, Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION).collect(Collectors.toSet()))));
	public static final RegistryObject<Structure<NoFeatureConfig>> MOUNTAINS_VILLAGE = addToStructureMaps("village_mountains", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "village/mountains/town_centers"), 6, 0, 1, 5, Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION).collect(Collectors.toSet()))));
	public static final RegistryObject<Structure<NoFeatureConfig>> GIANT_TAIGA_VILLAGE = addToStructureMaps("village_giant_taiga", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "village/giant_taiga/town_centers"), 6, 1, 0, 5, Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION).collect(Collectors.toSet()))));
  	public static final RegistryObject<Structure<NoFeatureConfig>> CRIMSON_VILLAGE = addToStructureMaps("village_crimson", () -> (new VillageNetherStructure(new ResourceLocation(RepurposedStructures.MODID, "village/crimson/town_centers"), 6, 0, 1, 0, new HashSet<>())));
  	public static final RegistryObject<Structure<NoFeatureConfig>> WARPED_VILLAGE = addToStructureMaps("village_warped", () -> (new VillageNetherStructure(new ResourceLocation(RepurposedStructures.MODID, "village/warped/town_centers"), 6, 0, 1, 0, new HashSet<>())));
    public static final RegistryObject<Structure<NoFeatureConfig>> VILLAGE_OAK = addToStructureMaps("village_oak", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "village/oak/town_centers"), 6, 0, 1, 5, Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION).collect(Collectors.toSet()))));

  	//Ruined Portals
    public static final RegistryObject<Structure<NoFeatureConfig>> RUINED_PORTAL_END = addToStructureMaps("ruined_portal_end", () -> (new RuinedPortalEndStructure(new ResourceLocation(RepurposedStructures.MODID, "ruined_portal/end"), 20, -6, 0, 0, new HashSet<>())));

    //Ruins
    public static final RegistryObject<Structure<NoFeatureConfig>> RUINS_NETHER = addToStructureMaps("ruins_nether", () -> (new GenericNetherJigsawHighStructure(new ResourceLocation(RepurposedStructures.MODID, "ruins/nether/start_pool"), 1, -4, -1)));

    //Cities
    public static final RegistryObject<Structure<NoFeatureConfig>> CITY_NETHER = addToStructureMaps("city_nether", () -> (new CityNetherStructure(new ResourceLocation(RepurposedStructures.MODID, "cities/nether/start_pool"), 5, 0, 0, 4, Stream.of(RSStructureTagMap.STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()), ImmutableList.of(new MobSpawnInfo.Spawners(EntityType.BLAZE, 120, 1, 4), new MobSpawnInfo.Spawners(EntityType.WITHER_SKELETON, 10, 2, 3)), ImmutableList.of())));

    //Mansions
    public static final RegistryObject<Structure<NoFeatureConfig>> MANSION_BIRCH = addToStructureMaps("mansion_birch", () -> (new MansionStructure(MansionPieces.MansionTemplate.MANSIONTYPE.BIRCH)));
    public static final RegistryObject<Structure<NoFeatureConfig>> MANSION_JUNGLE = addToStructureMaps("mansion_jungle", () -> (new MansionStructure(MansionPieces.MansionTemplate.MANSIONTYPE.JUNGLE)));
    public static final RegistryObject<Structure<NoFeatureConfig>> MANSION_OAK = addToStructureMaps("mansion_oak", () -> (new MansionStructure(MansionPieces.MansionTemplate.MANSIONTYPE.OAK)));
    public static final RegistryObject<Structure<NoFeatureConfig>> MANSION_SAVANNA = addToStructureMaps("mansion_savanna", () -> (new MansionStructure(MansionPieces.MansionTemplate.MANSIONTYPE.SAVANNA)));
    public static final RegistryObject<Structure<NoFeatureConfig>> MANSION_TAIGA = addToStructureMaps("mansion_taiga", () -> (new MansionStructure(MansionPieces.MansionTemplate.MANSIONTYPE.TAIGA)));
    public static final RegistryObject<Structure<NoFeatureConfig>> MANSION_DESERT = addToStructureMaps("mansion_desert", () -> (new MansionStructure(MansionPieces.MansionTemplate.MANSIONTYPE.DESERT)));
    public static final RegistryObject<Structure<NoFeatureConfig>> MANSION_SNOWY = addToStructureMaps("mansion_snowy", () -> (new MansionStructure(MansionPieces.MansionTemplate.MANSIONTYPE.SNOWY)));

    //Witch Huts
    public static final RegistryObject<Structure<NoFeatureConfig>> WITCH_HUTS_OAK = addToStructureMaps("witch_huts_oak", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/oak_start_pool"), 11, 0, 0, 0, new HashSet<>(), ImmutableList.of(new  MobSpawnInfo.Spawners(EntityType.WITCH, 1, 1, 1)), ImmutableList.of(new  MobSpawnInfo.Spawners(EntityType.CAT, 1, 1, 1)))));
    public static final RegistryObject<Structure<NoFeatureConfig>> WITCH_HUTS_TAIGA = addToStructureMaps("witch_huts_taiga", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/taiga_start_pool"), 11, 0, 0, 0, new HashSet<>(), ImmutableList.of(new  MobSpawnInfo.Spawners(EntityType.WITCH, 1, 1, 1)), ImmutableList.of(new  MobSpawnInfo.Spawners(EntityType.CAT, 1, 1, 1)))));
    public static final RegistryObject<Structure<NoFeatureConfig>> WITCH_HUTS_BIRCH = addToStructureMaps("witch_huts_birch", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/birch_start_pool"), 11, 0, 0, 0, new HashSet<>(), ImmutableList.of(new  MobSpawnInfo.Spawners(EntityType.WITCH, 1, 1, 1)), ImmutableList.of(new  MobSpawnInfo.Spawners(EntityType.CAT, 1, 1, 1)))));
    public static final RegistryObject<Structure<NoFeatureConfig>> WITCH_HUTS_DARK_FOREST = addToStructureMaps("witch_huts_dark_forest", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/dark_forest_start_pool"), 11, 0, 0, 0, new HashSet<>(), ImmutableList.of(new  MobSpawnInfo.Spawners(EntityType.WITCH, 1, 1, 1)), ImmutableList.of(new  MobSpawnInfo.Spawners(EntityType.CAT, 1, 1, 1)))));
    public static final RegistryObject<Structure<NoFeatureConfig>> WITCH_HUTS_GIANT_TREE_TAIGA = addToStructureMaps("witch_huts_giant_tree_taiga", () -> (new GenericJigsawStructure(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/giant_tree_taiga_start_pool"), 11, 0, 0, 0, new HashSet<>(), ImmutableList.of(new  MobSpawnInfo.Spawners(EntityType.WITCH, 1, 1, 1)), ImmutableList.of(new  MobSpawnInfo.Spawners(EntityType.CAT, 1, 1, 1)))));
    // regexpos1

    private static <T extends Structure<?>> RegistryObject<T> addToStructureMaps(String name, Supplier<T> structure)
    {   
        return STRUCTURE_FEATURES.register(name, structure);
    }
    
	public static void setupStructures()
	{
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_birch"), BIRCH_MINESHAFT.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 399117345));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_desert"), DESERT_MINESHAFT.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1990612785));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_end"), END_MINESHAFT.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 2057488602));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_nether"), NETHER_MINESHAFT.get(), GenerationStage.Decoration.VEGETAL_DECORATION, new StructureSeparationSettings(1, 0, 1220811654));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_crimson"), CRIMSON_MINESHAFT.get(), GenerationStage.Decoration.VEGETAL_DECORATION, new StructureSeparationSettings(1, 0, 1153019610));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_warped"), WARPED_MINESHAFT.get(), GenerationStage.Decoration.VEGETAL_DECORATION, new StructureSeparationSettings(1, 0, 1095888662));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_icy"), ICY_MINESHAFT.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1451015246));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_jungle"), JUNGLE_MINESHAFT.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1434412876));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_ocean"), OCEAN_MINESHAFT.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1774808662));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_savanna"), SAVANNA_MINESHAFT.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1960212212));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_stone"), STONE_MINESHAFT.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1436736620));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_swamp_or_dark_forest"), SWAMP_OR_DARK_FOREST_MINESHAFT.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 2037177700));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_taiga"), TAIGA_MINESHAFT.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1383003172));

        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "stronghold_stonebrick"), STONEBRICK_STRONGHOLD.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdMaxChunkDistance.get(), (int) (RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdMaxChunkDistance.get() * 0.5f), 1098192663));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "stronghold_nether"), NETHER_STRONGHOLD.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSStrongholdsConfig.netherStrongholdMaxChunkDistance.get(), (int) (RepurposedStructures.RSStrongholdsConfig.netherStrongholdMaxChunkDistance.get() * 0.5f), 1731422513));

        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "fortress_jungle"), JUNGLE_FORTRESS.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMainConfig.jungleFortressMaxChunkDistance.get(), (int) (RepurposedStructures.RSMainConfig.jungleFortressMaxChunkDistance.get() * 0.5f), 1464189157));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "igloo_grassy"), GRASSY_IGLOO.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMainConfig.grassyIglooMaxChunkDistance.get(), (int) (RepurposedStructures.RSMainConfig.grassyIglooMaxChunkDistance.get() * 0.5f), 1460835582));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "igloo_stone"), STONE_IGLOO.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMainConfig.stoneIglooMaxChunkDistance.get(), (int) (RepurposedStructures.RSMainConfig.stoneIglooMaxChunkDistance.get() * 0.5f), 1327429039));

        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_wasteland"), NETHER_WASTELAND_TEMPLE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.netherWastelandTempleMaxChunkDistance.get(), (int) (RepurposedStructures.RSTemplesConfig.netherWastelandTempleMaxChunkDistance.get() * 0.5f), 1435489909));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_soul"), NETHER_SOUL_TEMPLE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.netherSoulTempleMaxChunkDistance.get(), (int) (RepurposedStructures.RSTemplesConfig.netherSoulTempleMaxChunkDistance.get() * 0.5f), 1799485937));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_basalt"), NETHER_BASALT_TEMPLE.get(), GenerationStage.Decoration.VEGETAL_DECORATION, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.netherBasaltTempleMaxChunkDistance.get(), (int) (RepurposedStructures.RSTemplesConfig.netherBasaltTempleMaxChunkDistance.get() * 0.5f), 1063117750));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_crimson"), NETHER_CRIMSON_TEMPLE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.netherCrimsonTempleMaxChunkDistance.get(), (int) (RepurposedStructures.RSTemplesConfig.netherCrimsonTempleMaxChunkDistance.get() * 0.5f), 1898896156));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_warped"), NETHER_WARPED_TEMPLE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.netherWarpedTempleMaxChunkDistance.get(), (int) (RepurposedStructures.RSTemplesConfig.netherWarpedTempleMaxChunkDistance.get() * 0.5f), 1635542708));

        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_nether_brick"), NETHER_BRICK_OUTPOST.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSOutpostsConfig.netherBrickOutpostMaxChunkDistance.get(), (int) (RepurposedStructures.RSOutpostsConfig.netherBrickOutpostMaxChunkDistance.get() * 0.5f), 1305971394));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_warped"), WARPED_OUTPOST.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSOutpostsConfig.warpedOutpostMaxChunkDistance.get(), (int) (RepurposedStructures.RSOutpostsConfig.warpedOutpostMaxChunkDistance.get() * 0.5f), 1928816918));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_crimson"), CRIMSON_OUTPOST.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSOutpostsConfig.crimsonOutpostMaxChunkDistance.get(), (int) (RepurposedStructures.RSOutpostsConfig.crimsonOutpostMaxChunkDistance.get() * 0.5f), 1951425662));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_birch"), OUTPOST_BIRCH.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSOutpostsConfig.outpostBirchMaxChunkDistance.get(), (int) (RepurposedStructures.RSOutpostsConfig.outpostBirchMaxChunkDistance.get() * 0.5f), 1676743168));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_jungle"), OUTPOST_JUNGLE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSOutpostsConfig.outpostJungleMaxChunkDistance.get(), (int) (RepurposedStructures.RSOutpostsConfig.outpostJungleMaxChunkDistance.get() * 0.5f), 548433028));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_giant_tree_taiga"), OUTPOST_GIANT_TREE_TAIGA.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSOutpostsConfig.outpostGiantTreeTaigaMaxChunkDistance.get(), (int) (RepurposedStructures.RSOutpostsConfig.outpostGiantTreeTaigaMaxChunkDistance.get() * 0.5f), 993252541));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_desert"), OUTPOST_DESERT.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSOutpostsConfig.outpostDesertMaxChunkDistance.get(), (int) (RepurposedStructures.RSOutpostsConfig.outpostDesertMaxChunkDistance.get() * 0.5f), 593099376));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_badlands"), OUTPOST_BADLANDS.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSOutpostsConfig.outpostBadlandsMaxChunkDistance.get(), (int) (RepurposedStructures.RSOutpostsConfig.outpostBadlandsMaxChunkDistance.get() * 0.5f), 1702026868));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_snowy"), OUTPOST_SNOWY.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSOutpostsConfig.outpostSnowyMaxChunkDistance.get(), (int) (RepurposedStructures.RSOutpostsConfig.outpostSnowyMaxChunkDistance.get() * 0.5f), 849388460));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_icy"), OUTPOST_ICY.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSOutpostsConfig.outpostIcyMaxChunkDistance.get(), (int) (RepurposedStructures.RSOutpostsConfig.outpostIcyMaxChunkDistance.get() * 0.5f), 935294633));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_taiga"), OUTPOST_TAIGA.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSOutpostsConfig.outpostTaigaMaxChunkDistance.get(), (int) (RepurposedStructures.RSOutpostsConfig.outpostTaigaMaxChunkDistance.get() * 0.5f), 272805097));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_oak"), OUTPOST_OAK.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSOutpostsConfig.outpostOakMaxChunkDistance.get(), (int) (RepurposedStructures.RSOutpostsConfig.outpostOakMaxChunkDistance.get() * 0.5f), 698118385));

        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "pyramid_badlands"), BADLANDS_PYRAMID.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.badlandsPyramidMaxChunkDistance.get(), (int) (RepurposedStructures.RSTemplesConfig.badlandsPyramidMaxChunkDistance.get() * 0.5f), 1718729448));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "pyramid_nether"), NETHER_PYRAMID.get(), GenerationStage.Decoration.VEGETAL_DECORATION, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.netherPyramidMaxChunkDistance.get(), (int) (RepurposedStructures.RSTemplesConfig.netherPyramidMaxChunkDistance.get() * 0.5f), 2054372964));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "pyramid_snowy"), PYRAMID_SNOWY.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.pyramidSnowyMaxChunkDistance.get(), (int) (RepurposedStructures.RSTemplesConfig.pyramidSnowyMaxChunkDistance.get() * 0.5f), 1630533493));

        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_end"), END_SHIPWRECK.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSShipwrecksConfig.endShipwreckMaxChunkDistance.get(), (int) (RepurposedStructures.RSShipwrecksConfig.endShipwreckMaxChunkDistance.get() * 0.5f), 1605500075));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_nether_bricks"), NETHER_BRICKS_SHIPWRECK.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSShipwrecksConfig.netherBricksShipwreckMaxChunkDistance.get(), (int) (RepurposedStructures.RSShipwrecksConfig.netherBricksShipwreckMaxChunkDistance.get() * 0.5f), 2073308006), new NetherShipwreckConfig(true));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_crimson"), CRIMSON_SHIPWRECK.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSShipwrecksConfig.crimsonShipwreckMaxChunkDistance.get(), (int) (RepurposedStructures.RSShipwrecksConfig.crimsonShipwreckMaxChunkDistance.get() * 0.5f), 1019716871), new NetherShipwreckConfig(false));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_warped"), WARPED_SHIPWRECK.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSShipwrecksConfig.warpedShipwreckMaxChunkDistance.get(), (int) (RepurposedStructures.RSShipwrecksConfig.warpedShipwreckMaxChunkDistance.get() * 0.5f), 2072979641), new NetherShipwreckConfig(false));

        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_badlands"), BADLANDS_VILLAGE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.badlandsVillageMaxChunkDistance.get(), (int) (RepurposedStructures.RSVillagesConfig.badlandsVillageMaxChunkDistance.get() * 0.5f), 1319707555));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_birch"), BIRCH_VILLAGE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.birchVillageMaxChunkDistance.get(), (int) (RepurposedStructures.RSVillagesConfig.birchVillageMaxChunkDistance.get() * 0.5f), 1102567365));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_dark_oak"), DARK_FOREST_VILLAGE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.darkForestVillageMaxChunkDistance.get(), (int) (RepurposedStructures.RSVillagesConfig.darkForestVillageMaxChunkDistance.get() * 0.5f), 1921339358));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_jungle"), JUNGLE_VILLAGE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.jungleVillageMaxChunkDistance.get(), (int) (RepurposedStructures.RSVillagesConfig.jungleVillageMaxChunkDistance.get() * 0.5f), 1229975218));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_swamp"), SWAMP_VILLAGE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.swampVillageMaxChunkDistance.get(), (int) (RepurposedStructures.RSVillagesConfig.swampVillageMaxChunkDistance.get() * 0.5f), 1559650945));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_mountains"), MOUNTAINS_VILLAGE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.mountainsVillageMaxChunkDistance.get(), (int) (RepurposedStructures.RSVillagesConfig.mountainsVillageMaxChunkDistance.get() * 0.5f), 2010875989));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_giant_taiga"), GIANT_TAIGA_VILLAGE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.giantTaigaVillageMaxChunkDistance.get(), (int) (RepurposedStructures.RSVillagesConfig.giantTaigaVillageMaxChunkDistance.get() * 0.5f), 1559528842));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_crimson"), CRIMSON_VILLAGE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.crimsonVillageMaxChunkDistance.get(), (int) (RepurposedStructures.RSVillagesConfig.crimsonVillageMaxChunkDistance.get() * 0.5f), 1854750198));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_warped"), WARPED_VILLAGE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.warpedVillageMaxChunkDistance.get(), (int) (RepurposedStructures.RSVillagesConfig.warpedVillageMaxChunkDistance.get() * 0.5f), 1298332136));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_oak"), VILLAGE_OAK.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.villageOakMaxChunkDistance.get(), (int) (RepurposedStructures.RSVillagesConfig.villageOakMaxChunkDistance.get() * 0.5f), 2112891039));

        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "ruined_portal_end"), RUINED_PORTAL_END.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMainConfig.ruinedPortalEndMaxChunkDistance.get(), (int) (RepurposedStructures.RSMainConfig.ruinedPortalEndMaxChunkDistance.get() * 0.5f), 532404086));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "ruins_nether"), RUINS_NETHER.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMainConfig.ruinsNetherMaxChunkDistance.get(), (int) (RepurposedStructures.RSMainConfig.ruinsNetherMaxChunkDistance.get() * 0.5f), 1336047555));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "city_nether"), CITY_NETHER.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMainConfig.citiesNetherMaxChunkDistance.get(), (int) (RepurposedStructures.RSMainConfig.citiesNetherMaxChunkDistance.get() * 0.5f), 2082652405));

        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mansion_birch"), MANSION_BIRCH.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMansionsConfig.mansionBirchMaxChunkDistance.get(), (int) (RepurposedStructures.RSMansionsConfig.mansionBirchMaxChunkDistance.get() * 0.5f), 182367035));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mansion_jungle"), MANSION_JUNGLE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMansionsConfig.mansionJungleMaxChunkDistance.get(), (int) (RepurposedStructures.RSMansionsConfig.mansionJungleMaxChunkDistance.get() * 0.5f), 1267916621));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mansion_oak"), MANSION_OAK.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMansionsConfig.mansionOakMaxChunkDistance.get(), (int) (RepurposedStructures.RSMansionsConfig.mansionOakMaxChunkDistance.get() * 0.5f), 147853731));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mansion_savanna"), MANSION_SAVANNA.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMansionsConfig.mansionSavannaMaxChunkDistance.get(), (int) (RepurposedStructures.RSMansionsConfig.mansionSavannaMaxChunkDistance.get() * 0.5f), 2024558925));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mansion_taiga"), MANSION_TAIGA.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMansionsConfig.mansionTaigaMaxChunkDistance.get(), (int) (RepurposedStructures.RSMansionsConfig.mansionTaigaMaxChunkDistance.get() * 0.5f), 418506505));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mansion_desert"), MANSION_DESERT.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMansionsConfig.mansionDesertMaxChunkDistance.get(), (int) (RepurposedStructures.RSMansionsConfig.mansionDesertMaxChunkDistance.get() * 0.5f), 724317387));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mansion_snowy"), MANSION_SNOWY.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMansionsConfig.mansionSnowyMaxChunkDistance.get(), (int) (RepurposedStructures.RSMansionsConfig.mansionSnowyMaxChunkDistance.get() * 0.5f), 1115107889));

        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "witch_huts_oak"), WITCH_HUTS_OAK.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSWitchHutsConfig.witchHutsOakMaxChunkDistance.get(), (int) (RepurposedStructures.RSWitchHutsConfig.witchHutsOakMaxChunkDistance.get() * 0.5f), 741641348));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "witch_huts_taiga"), WITCH_HUTS_TAIGA.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSWitchHutsConfig.witchHutsTaigaMaxChunkDistance.get(), (int) (RepurposedStructures.RSWitchHutsConfig.witchHutsTaigaMaxChunkDistance.get() * 0.5f), 1925189659));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "witch_huts_birch"), WITCH_HUTS_BIRCH.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSWitchHutsConfig.witchHutsBirchMaxChunkDistance.get(), (int) (RepurposedStructures.RSWitchHutsConfig.witchHutsBirchMaxChunkDistance.get() * 0.5f), 904634508));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "witch_huts_dark_forest"), WITCH_HUTS_DARK_FOREST.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSWitchHutsConfig.witchHutsDarkForestMaxChunkDistance.get(), (int) (RepurposedStructures.RSWitchHutsConfig.witchHutsDarkForestMaxChunkDistance.get() * 0.5f), 165100151));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "witch_huts_giant_tree_taiga"), WITCH_HUTS_GIANT_TREE_TAIGA.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSWitchHutsConfig.witchHutsGiantTreeTaigaMaxChunkDistance.get(), (int) (RepurposedStructures.RSWitchHutsConfig.witchHutsGiantTreeTaigaMaxChunkDistance.get() * 0.5f), 200289401));
        // regexpos2

        //registers the structure pieces.
        RSStructurePieces.registerStructurePieces();
    }

    public static <C extends IFeatureConfig, F extends Structure<C>> void addToTerraformingAndStructureMaps(ResourceLocation resourceLocation, F structure, GenerationStage.Decoration stage, StructureSeparationSettings StructureSeparationSettings, C config)
    {
        Structure.JIGSAW_STRUCTURES = ImmutableList.<Structure<?>>builder().addAll(Structure.JIGSAW_STRUCTURES).add(structure).build();
        addToStructureMaps(resourceLocation, structure, stage, StructureSeparationSettings, config);
    }

    public static <F extends Structure<NoFeatureConfig>> void addToTerraformingAndStructureMaps(ResourceLocation resourceLocation, F structure, GenerationStage.Decoration stage, StructureSeparationSettings StructureSeparationSettings)
    {
        Structure.JIGSAW_STRUCTURES = ImmutableList.<Structure<?>>builder().addAll(Structure.JIGSAW_STRUCTURES).add(structure).build();
        addToStructureMaps(resourceLocation, structure, stage, StructureSeparationSettings);
    }

    public static <C extends IFeatureConfig, F extends Structure<C>> void addToStructureMaps(ResourceLocation resourceLocation, F structure, GenerationStage.Decoration stage, StructureSeparationSettings structureSeparationSettings, C config)
    {
        Structure.STRUCTURES.put(resourceLocation.toString().toLowerCase(Locale.ROOT), structure);

        // This is only for myself. Others should override func_236396_f_() in
        // their structure's class to return their generation stage instead.
        Structure.STRUCTURE_TO_GENERATION_STEP.put(structure, stage);

        DimensionStructuresSettings.DEFAULT_STRUCTURES = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder().putAll(DimensionStructuresSettings.DEFAULT_STRUCTURES).put(structure, structureSeparationSettings).build();
        FlatGenerationSettings.STRUCTURES.put(structure, structure.configure(config));
        RS_STRUCTURES.put(structure, structureSeparationSettings);
    }

	public static <F extends Structure<NoFeatureConfig>> void addToStructureMaps(ResourceLocation resourceLocation, F structure, GenerationStage.Decoration stage, StructureSeparationSettings structureSeparationSettings)
	{
		Structure.STRUCTURES.put(resourceLocation.toString().toLowerCase(Locale.ROOT), structure);

		// This is only for myself. Others should override func_236396_f_() in
        // their structure's class to return their generation stage instead.
		Structure.STRUCTURE_TO_GENERATION_STEP.put(structure, stage);

		DimensionStructuresSettings.DEFAULT_STRUCTURES = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder().putAll(DimensionStructuresSettings.DEFAULT_STRUCTURES).put(structure, structureSeparationSettings).build();
		FlatGenerationSettings.STRUCTURES.put(structure, structure.configure(IFeatureConfig.NO_FEATURE_CONFIG));
		RS_STRUCTURES.put(structure, structureSeparationSettings);
	}
}
