package com.telepathicgrunt.repurposedstructures.modinit;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.AdvancedDistanceJigsawStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.AdvancedJigsawStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.BuriableStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.CityNetherStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.GenericJigsawStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.GenericNetherJigsawStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.LandBasedEndStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.MansionStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.MineshaftEndStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.MineshaftStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.ShipwreckEndStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.ShipwreckNetherStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.StrongholdEndStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.NetherShipwreckConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionPieces;
import net.minecraft.util.ResourceLocation;
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
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
     * <p>
     * Don't worry, I actually have a structure tutorial
     * mod already setup for you to check out! It's full
     * of comments on what does what and how to make structures.
     * <p>
     * Here's the link! https://github.com/TelepathicGrunt/StructureTutorialMod
     * <p>
     * Good luck and have fun modding!
     */

    public static final DeferredRegister<Structure<?>> STRUCTURE_FEATURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, RepurposedStructures.MODID);
    public static final Map<Structure<?>, StructureSeparationSettings> RS_STRUCTURES = new HashMap<>();
    public static final Set<ResourceLocation> RS_STRUCTURE_START_PIECES = new HashSet<>();

    //Mineshafts
    public static RegistryObject<Structure<NoFeatureConfig>> MINESHAFT_BIRCH = addToStructureMaps("mineshaft_birch", () -> (new MineshaftStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/birch/start_pool")).setStructureSize(RepurposedStructures.RSMineshaftsConfig.birchMineshaftSize).setMaxY(RepurposedStructures.RSMineshaftsConfig.birchMineshaftMaxHeight).setMinY(RepurposedStructures.RSMineshaftsConfig.birchMineshaftMinHeight).setProbability(RepurposedStructures.RSMineshaftsConfig.birchMineshaftSpawnrate).build()));
    public static RegistryObject<Structure<NoFeatureConfig>> MINESHAFT_DARK_FOREST = addToStructureMaps("mineshaft_dark_forest", () -> (new MineshaftStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/dark_forest/start_pool")).setStructureSize(RepurposedStructures.RSMineshaftsConfig.darkForestMineshaftSize).setMaxY(RepurposedStructures.RSMineshaftsConfig.darkForestMineshaftMaxHeight).setMinY(RepurposedStructures.RSMineshaftsConfig.darkForestMineshaftMinHeight).setProbability(RepurposedStructures.RSMineshaftsConfig.darkForestMineshaftSpawnrate).build()));
    public static RegistryObject<Structure<NoFeatureConfig>> MINESHAFT_DESERT = addToStructureMaps("mineshaft_desert", () -> (new MineshaftStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/desert/start_pool")).setStructureSize(RepurposedStructures.RSMineshaftsConfig.desertMineshaftSize).setMaxY(RepurposedStructures.RSMineshaftsConfig.desertMineshaftMaxHeight).setMinY(RepurposedStructures.RSMineshaftsConfig.desertMineshaftMinHeight).setProbability(RepurposedStructures.RSMineshaftsConfig.desertMineshaftSpawnrate).build()));
    public static RegistryObject<Structure<NoFeatureConfig>> MINESHAFT_ICY = addToStructureMaps("mineshaft_icy", () -> (new MineshaftStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/icy/start_pool")).setStructureSize(RepurposedStructures.RSMineshaftsConfig.icyMineshaftSize).setMaxY(RepurposedStructures.RSMineshaftsConfig.icyMineshaftMaxHeight).setMinY(RepurposedStructures.RSMineshaftsConfig.icyMineshaftMinHeight).setProbability(RepurposedStructures.RSMineshaftsConfig.icyMineshaftSpawnrate).build()));
    public static RegistryObject<Structure<NoFeatureConfig>> MINESHAFT_JUNGLE = addToStructureMaps("mineshaft_jungle", () -> (new MineshaftStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/jungle/start_pool")).setStructureSize(RepurposedStructures.RSMineshaftsConfig.jungleMineshaftSize).setMaxY(RepurposedStructures.RSMineshaftsConfig.jungleMineshaftMaxHeight).setMinY(RepurposedStructures.RSMineshaftsConfig.jungleMineshaftMinHeight).setProbability(RepurposedStructures.RSMineshaftsConfig.jungleMineshaftSpawnrate).build()));
    public static RegistryObject<Structure<NoFeatureConfig>> MINESHAFT_OCEAN = addToStructureMaps("mineshaft_ocean", () -> (new MineshaftStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/ocean/start_pool")).setStructureSize(RepurposedStructures.RSMineshaftsConfig.oceanMineshaftSize).setMaxY(RepurposedStructures.RSMineshaftsConfig.oceanMineshaftMaxHeight).setMinY(RepurposedStructures.RSMineshaftsConfig.oceanMineshaftMinHeight).setProbability(RepurposedStructures.RSMineshaftsConfig.oceanMineshaftSpawnrate).build()));
    public static RegistryObject<Structure<NoFeatureConfig>> MINESHAFT_SAVANNA = addToStructureMaps("mineshaft_savanna", () -> (new MineshaftStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/savanna/start_pool")).setStructureSize(RepurposedStructures.RSMineshaftsConfig.savannaMineshaftSize).setMaxY(RepurposedStructures.RSMineshaftsConfig.savannaMineshaftMaxHeight).setMinY(RepurposedStructures.RSMineshaftsConfig.savannaMineshaftMinHeight).setProbability(RepurposedStructures.RSMineshaftsConfig.savannaMineshaftSpawnrate).build()));
    public static RegistryObject<Structure<NoFeatureConfig>> MINESHAFT_STONE = addToStructureMaps("mineshaft_stone", () -> (new MineshaftStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/stone/start_pool")).setStructureSize(RepurposedStructures.RSMineshaftsConfig.stoneMineshaftSize).setMaxY(RepurposedStructures.RSMineshaftsConfig.stoneMineshaftMaxHeight).setMinY(RepurposedStructures.RSMineshaftsConfig.stoneMineshaftMinHeight).setProbability(RepurposedStructures.RSMineshaftsConfig.stoneMineshaftSpawnrate).build()));
    public static RegistryObject<Structure<NoFeatureConfig>> MINESHAFT_SWAMP = addToStructureMaps("mineshaft_swamp", () -> (new MineshaftStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/swamp/start_pool")).setStructureSize(RepurposedStructures.RSMineshaftsConfig.swampMineshaftSize).setMaxY(RepurposedStructures.RSMineshaftsConfig.swampMineshaftMaxHeight).setMinY(RepurposedStructures.RSMineshaftsConfig.swampMineshaftMinHeight).setProbability(RepurposedStructures.RSMineshaftsConfig.swampMineshaftSpawnrate).build()));
    public static RegistryObject<Structure<NoFeatureConfig>> MINESHAFT_TAIGA = addToStructureMaps("mineshaft_taiga", () -> (new MineshaftStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/taiga/start_pool")).setStructureSize(RepurposedStructures.RSMineshaftsConfig.taigaMineshaftSize).setMaxY(RepurposedStructures.RSMineshaftsConfig.taigaMineshaftMaxHeight).setMinY(RepurposedStructures.RSMineshaftsConfig.taigaMineshaftMinHeight).setProbability(RepurposedStructures.RSMineshaftsConfig.taigaMineshaftSpawnrate).build()));
    public static RegistryObject<Structure<NoFeatureConfig>> MINESHAFT_NETHER = addToStructureMaps("mineshaft_nether", () -> (new MineshaftStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/nether/start_pool")).setStructureSize(RepurposedStructures.RSMineshaftsConfig.netherMineshaftSize).setMaxY(RepurposedStructures.RSMineshaftsConfig.netherMineshaftMaxHeight).setMinY(RepurposedStructures.RSMineshaftsConfig.netherMineshaftMinHeight).setProbability(RepurposedStructures.RSMineshaftsConfig.netherMineshaftSpawnrate).build()));
    public static RegistryObject<Structure<NoFeatureConfig>> MINESHAFT_CRIMSON = addToStructureMaps("mineshaft_crimson", () -> (new MineshaftStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/crimson/start_pool")).setStructureSize(RepurposedStructures.RSMineshaftsConfig.crimsonMineshaftSize).setMaxY(RepurposedStructures.RSMineshaftsConfig.crimsonMineshaftMaxHeight).setMinY(RepurposedStructures.RSMineshaftsConfig.crimsonMineshaftMinHeight).setProbability(RepurposedStructures.RSMineshaftsConfig.crimsonMineshaftSpawnrate).build()));
    public static RegistryObject<Structure<NoFeatureConfig>> MINESHAFT_WARPED = addToStructureMaps("mineshaft_warped", () -> (new MineshaftStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/warped/start_pool")).setStructureSize(RepurposedStructures.RSMineshaftsConfig.warpedMineshaftSize).setMaxY(RepurposedStructures.RSMineshaftsConfig.warpedMineshaftMaxHeight).setMinY(RepurposedStructures.RSMineshaftsConfig.warpedMineshaftMinHeight).setProbability(RepurposedStructures.RSMineshaftsConfig.warpedMineshaftSpawnrate).build()));
    public static RegistryObject<Structure<NoFeatureConfig>> MINESHAFT_END = addToStructureMaps("mineshaft_end", () -> (new MineshaftEndStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/end/start_pool")).setStructureSize(RepurposedStructures.RSMineshaftsConfig.endMineshaftSize).setProbability(RepurposedStructures.RSMineshaftsConfig.endMineshaftSpawnrate).build()));

    //Strongholds
    public static final RegistryObject<Structure<NoFeatureConfig>> STRONGHOLD_NETHER = addToStructureMaps("stronghold_nether", () -> (new AdvancedDistanceJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/start_pool")).setStructureSize(RepurposedStructures.RSStrongholdsConfig.netherStrongholdSize).setMaxY(RepurposedStructures.RSStrongholdsConfig.netherStrongholdMaxHeight).setMinY(RepurposedStructures.RSStrongholdsConfig.netherStrongholdMinHeight).setDistanceFromWorldOrigin(2817).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> STRONGHOLD_END = addToStructureMaps("stronghold_end", () -> (new StrongholdEndStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "strongholds/end/start_pool")).setStructureSize(RepurposedStructures.RSStrongholdsConfig.strongholdEndSize).setMaxY(RepurposedStructures.RSStrongholdsConfig.strongholdEndMaxHeight).setMinY(RepurposedStructures.RSStrongholdsConfig.strongholdEndMinHeight).setVerticalRange(RepurposedStructures.RSStrongholdsConfig.strongholdEndVerticalRange).setDistanceFromWorldOrigin(8000).doNotClipOutOfBoundsPieces().build()));

    //Igloos and Fortress
    public static final RegistryObject<Structure<NoFeatureConfig>> FORTRESS_JUNGLE = addToStructureMaps("fortress_jungle", () -> (new AdvancedJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "fortresses/jungle/start_pool")).setStructureSize(RepurposedStructures.RSFortressesConfig.jungleFortressSize).setBiomeRange(4).setMaxY(RepurposedStructures.RSFortressesConfig.jungleFortressMaxHeight).setMinY(RepurposedStructures.RSFortressesConfig.jungleFortressMinHeight).setVerticalRange(RepurposedStructures.RSFortressesConfig.jungleFortressVerticalRange).doNotClipOutOfBoundsPieces().build()));

    public static final RegistryObject<Structure<NoFeatureConfig>> IGLOO_GRASSY = addToStructureMaps("igloo_grassy", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "igloos/grassy_top")).setStructureSize(20).cannotSpawnInWater().build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> IGLOO_STONE = addToStructureMaps("igloo_stone", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "igloos/stone_top")).setStructureSize(20).cannotSpawnInWater().build()));

    //Temples
    public static final RegistryObject<Structure<NoFeatureConfig>> TEMPLE_NETHER_WASTELAND = addToStructureMaps("temple_nether_wasteland", () -> (new GenericNetherJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/temple_nether_wasteland")).setStructureSize(8).canSpawnOnLiquid().setLedgeSpotOffset(-16).setLiquidSpotOffset(-16).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> TEMPLE_NETHER_BASALT = addToStructureMaps("temple_nether_basalt", () -> (new GenericNetherJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/temple_nether_basalt")).setStructureSize(8).canSpawnOnLiquid().setLedgeSpotOffset(-16).setLiquidSpotOffset(-16).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> TEMPLE_NETHER_WARPED = addToStructureMaps("temple_nether_warped", () -> (new GenericNetherJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/temple_nether_warped")).setStructureSize(8).canSpawnOnLiquid().setLedgeSpotOffset(-16).setLiquidSpotOffset(-16).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> TEMPLE_NETHER_CRIMSON = addToStructureMaps("temple_nether_crimson", () -> (new GenericNetherJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/temple_nether_crimson")).setStructureSize(8).canSpawnOnLiquid().setLedgeSpotOffset(-16).setLiquidSpotOffset(-16).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> TEMPLE_NETHER_SOUL = addToStructureMaps("temple_nether_soul", () -> (new GenericNetherJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/temple_nether_soul")).setStructureSize(8).canSpawnOnLiquid().setLedgeSpotOffset(-16).setLiquidSpotOffset(-16).build()));

    //Outposts
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_BIRCH = addToStructureMaps("outpost_birch", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/birch/base_plates")).setStructureSize(11).setStructureBlacklistRange(5).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_JUNGLE = addToStructureMaps("outpost_jungle", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/jungle/base_plates")).setStructureSize(11).setBiomeRange(1).setStructureBlacklistRange(5).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_GIANT_TREE_TAIGA = addToStructureMaps("outpost_giant_tree_taiga", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/giant_tree_taiga/base_plates")).setStructureSize(11).setStructureBlacklistRange(5).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_DESERT = addToStructureMaps("outpost_desert", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/desert/base_plates")).setStructureSize(11).setBiomeRange(1).setStructureBlacklistRange(5).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_BADLANDS = addToStructureMaps("outpost_badlands", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/badlands/base_plates")).setStructureSize(11).setTerrainHeightRadius(1).setAllowTerrainHeightRange(20).setStructureBlacklistRange(5).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_SNOWY = addToStructureMaps("outpost_snowy", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/snowy/base_plates")).setStructureSize(11).setStructureBlacklistRange(5).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_ICY = addToStructureMaps("outpost_icy", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/icy/base_plates")).setStructureSize(11).setBiomeRange(1).setStructureBlacklistRange(5).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_TAIGA = addToStructureMaps("outpost_taiga", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/taiga/base_plates")).setStructureSize(11).setBiomeRange(1).setStructureBlacklistRange(5).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_OAK = addToStructureMaps("outpost_oak", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/oak/base_plates")).setStructureSize(11).setBiomeRange(1).setStructureBlacklistRange(5).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_NETHER_BRICK = addToStructureMaps("outpost_nether_brick", () -> (new GenericNetherJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/nether_brick/base_plates")).setStructureSize(11).canSpawnOnLiquid().setLedgeSpotOffset(-13).setLiquidSpotOffset(-11).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_WARPED = addToStructureMaps("outpost_warped", () -> (new GenericNetherJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/warped/base_plates")).setStructureSize(11).canSpawnOnLiquid().setLedgeSpotOffset(-13).setLiquidSpotOffset(-11).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_CRIMSON = addToStructureMaps("outpost_crimson", () -> (new GenericNetherJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/crimson/base_plates")).setStructureSize(11).canSpawnOnLiquid().setLedgeSpotOffset(-13).setLiquidSpotOffset(-11).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> OUTPOST_END = addToStructureMaps("outpost_end", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/end/base_plates")).setStructureSize(11).setBiomeRange(2).setAllowTerrainHeightRange(3).setAllowTerrainHeightRange(15).setMinHeightLimit(55).setStructureBlacklistRange(4).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.END_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));

    //Pyramids
    public static final RegistryObject<Structure<NoFeatureConfig>> PYRAMID_BADLANDS = addToStructureMaps("pyramid_badlands", () -> (new BuriableStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/pyramid_badlands")).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> PYRAMID_SNOWY = addToStructureMaps("pyramid_snowy", () -> (new BuriableStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/pyramid_snowy")).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> PYRAMID_ICY = addToStructureMaps("pyramid_icy", () -> (new BuriableStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/pyramid_icy")).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> PYRAMID_JUNGLE = addToStructureMaps("pyramid_jungle", () -> (new BuriableStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/pyramid_jungle")).setOffsetAmount(20).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> PYRAMID_MUSHROOM = addToStructureMaps("pyramid_mushroom", () -> (new BuriableStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/pyramid_mushroom")).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> PYRAMID_OCEAN = addToStructureMaps("pyramid_ocean", () -> (new BuriableStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/pyramid_ocean")).cannotSpawnInWater().useOceanHeightmap().build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> PYRAMID_GIANT_TREE_TAIGA = addToStructureMaps("pyramid_giant_tree_taiga", () -> (new BuriableStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/pyramid_giant_tree_taiga")).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> PYRAMID_FLOWER_FOREST = addToStructureMaps("pyramid_flower_forest", () -> (new BuriableStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/pyramid_flower_forest")).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> PYRAMID_NETHER = addToStructureMaps("pyramid_nether", () -> (new GenericNetherJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/pyramid_nether")).setStructureSize(8).searchForHighestLand().canSpawnOnLiquid().setLedgeSpotOffset(-14).setLiquidSpotOffset(-13).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> PYRAMID_END = addToStructureMaps("pyramid_end", () -> (new LandBasedEndStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/pyramid_end")).setStructureSize(11).setCenterOffset(-2).build()));

    //Shipwrecks
    public static final RegistryObject<Structure<NoFeatureConfig>> SHIPWRECK_END = addToStructureMaps("shipwreck_end", ShipwreckEndStructure::new);
    public static final RegistryObject<Structure<NetherShipwreckConfig>> SHIPWRECK_NETHER_BRICKS = addToStructureMaps("shipwreck_nether_bricks", () -> (new ShipwreckNetherStructure(new ResourceLocation(RepurposedStructures.MODID, "shipwrecks/nether_bricks"), -3)));
    public static final RegistryObject<Structure<NetherShipwreckConfig>> SHIPWRECK_CRIMSON = addToStructureMaps("shipwreck_crimson", () -> (new ShipwreckNetherStructure(new ResourceLocation(RepurposedStructures.MODID, "shipwrecks/crimson"), -4)));
    public static final RegistryObject<Structure<NetherShipwreckConfig>> SHIPWRECK_WARPED = addToStructureMaps("shipwreck_warped", () -> (new ShipwreckNetherStructure(new ResourceLocation(RepurposedStructures.MODID, "shipwrecks/warped"), -4)));

    //Villages
    public static final RegistryObject<Structure<NoFeatureConfig>> VILLAGE_BADLANDS = addToStructureMaps("village_badlands", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "village/badlands/town_centers")).setStructureSize(RepurposedStructures.RSVillagesConfig.badlandsVillageSize).setBiomeRange(1).setTerrainHeightRadius(2).setAllowTerrainHeightRange(20).setStructureBlacklistRange(6).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> VILLAGE_BIRCH = addToStructureMaps("village_birch", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "village/birch/town_centers")).setStructureSize(RepurposedStructures.RSVillagesConfig.birchVillageSize).setBiomeRange(1).setStructureBlacklistRange(6).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> VILLAGE_DARK_FOREST = addToStructureMaps("village_dark_oak", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "village/dark_forest/town_centers")).setStructureSize(RepurposedStructures.RSVillagesConfig.darkForestVillageSize).setBiomeRange(1).setStructureBlacklistRange(6).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> VILLAGE_JUNGLE = addToStructureMaps("village_jungle", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "village/jungle/town_centers")).setStructureSize(RepurposedStructures.RSVillagesConfig.jungleVillageSize).setBiomeRange(1).setStructureBlacklistRange(6).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> VILLAGE_SWAMP = addToStructureMaps("village_swamp", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "village/swamp/town_centers")).setStructureSize(RepurposedStructures.RSVillagesConfig.swampVillageSize).setCenterOffset(-1).setBiomeRange(1).setStructureBlacklistRange(6).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> VILLAGE_MOUNTAINS = addToStructureMaps("village_mountains", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "village/mountains/town_centers")).setStructureSize(RepurposedStructures.RSVillagesConfig.mountainsVillageSize).setBiomeRange(1).setStructureBlacklistRange(6).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> VILLAGE_GIANT_TAIGA = addToStructureMaps("village_giant_taiga", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "village/giant_taiga/town_centers")).setStructureSize(RepurposedStructures.RSVillagesConfig.giantTaigaVillageSize).setBiomeRange(1).setStructureBlacklistRange(6).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> VILLAGE_OAK = addToStructureMaps("village_oak", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "village/oak/town_centers")).setStructureSize(RepurposedStructures.RSVillagesConfig.oakVillageSize).setBiomeRange(1).setStructureBlacklistRange(6).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> VILLAGE_CRIMSON = addToStructureMaps("village_crimson", () -> (new GenericNetherJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "village/crimson/town_centers")).setStructureSize(RepurposedStructures.RSVillagesConfig.crimsonVillageSize).searchForHighestLand().setLedgeSpotOffset(-12).setLiquidSpotOffset(-11).setBiomeRange(1).setStructureBlacklistRange(10).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> VILLAGE_WARPED = addToStructureMaps("village_warped", () -> (new GenericNetherJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "village/warped/town_centers")).setStructureSize(RepurposedStructures.RSVillagesConfig.warpedVillageSize).searchForHighestLand().setLedgeSpotOffset(-12).setLiquidSpotOffset(-11).setBiomeRange(1).setStructureBlacklistRange(10).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST).collect(Collectors.toSet())).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> VILLAGE_MUSHROOM = addToStructureMaps("village_mushroom", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "village/mushroom/town_center")).setStructureSize(RepurposedStructures.RSVillagesConfig.mushroomVillageSize).setBiomeRange(1).setStructureBlacklistRange(6).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));

    // regexpos1

    //Ruined Portals
    public static final RegistryObject<Structure<NoFeatureConfig>> RUINED_PORTAL_END = addToStructureMaps("ruined_portal_end", () -> (new LandBasedEndStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "ruined_portal/end")).setStructureSize(20).setCenterOffset(-6).build()));

    //Ruins
    public static final RegistryObject<Structure<NoFeatureConfig>> RUINS_NETHER = addToStructureMaps("ruins_nether", () -> (new GenericNetherJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "ruins/nether/start_pool")).setStructureSize(1).searchForHighestLand().canSpawnOnLiquid().setLedgeSpotOffset(-14).setLiquidSpotOffset(-13).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> RUINS_LAND_WARM = addToStructureMaps("ruins_land_warm", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "ruins/land_warm/start_pool")).setStructureSize(2).setTerrainHeightRadius(2).setAllowTerrainHeightRange(5).cannotSpawnInWater().build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> RUINS_LAND_HOT = addToStructureMaps("ruins_land_hot", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "ruins/land_hot/start_pool")).setStructureSize(2).setTerrainHeightRadius(2).setAllowTerrainHeightRange(5).cannotSpawnInWater().build()));

    //Cities
    public static final RegistryObject<Structure<NoFeatureConfig>> CITY_NETHER = addToStructureMaps("city_nether", () -> (new CityNetherStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "cities/nether/start_pool")).setStructureSize(5).setStructureBlacklistRange(4).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet())).build()));

    //Mansions
    public static final RegistryObject<Structure<NoFeatureConfig>> MANSION_BIRCH = addToStructureMaps("mansion_birch", () -> (new MansionStructure(MansionPieces.MansionTemplate.MANSIONTYPE.BIRCH)));
    public static final RegistryObject<Structure<NoFeatureConfig>> MANSION_JUNGLE = addToStructureMaps("mansion_jungle", () -> (new MansionStructure(MansionPieces.MansionTemplate.MANSIONTYPE.JUNGLE)));
    public static final RegistryObject<Structure<NoFeatureConfig>> MANSION_OAK = addToStructureMaps("mansion_oak", () -> (new MansionStructure(MansionPieces.MansionTemplate.MANSIONTYPE.OAK)));
    public static final RegistryObject<Structure<NoFeatureConfig>> MANSION_SAVANNA = addToStructureMaps("mansion_savanna", () -> (new MansionStructure(MansionPieces.MansionTemplate.MANSIONTYPE.SAVANNA)));
    public static final RegistryObject<Structure<NoFeatureConfig>> MANSION_TAIGA = addToStructureMaps("mansion_taiga", () -> (new MansionStructure(MansionPieces.MansionTemplate.MANSIONTYPE.TAIGA)));
    public static final RegistryObject<Structure<NoFeatureConfig>> MANSION_DESERT = addToStructureMaps("mansion_desert", () -> (new MansionStructure(MansionPieces.MansionTemplate.MANSIONTYPE.DESERT)));
    public static final RegistryObject<Structure<NoFeatureConfig>> MANSION_SNOWY = addToStructureMaps("mansion_snowy", () -> (new MansionStructure(MansionPieces.MansionTemplate.MANSIONTYPE.SNOWY)));

    //Witch Huts
    public static final RegistryObject<Structure<NoFeatureConfig>> WITCH_HUTS_OAK = addToStructureMaps("witch_hut_oak", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/oak_start_pool")).setStructureSize(11).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> WITCH_HUTS_TAIGA = addToStructureMaps("witch_hut_taiga", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/taiga_start_pool")).setStructureSize(11).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> WITCH_HUTS_BIRCH = addToStructureMaps("witch_hut_birch", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/birch_start_pool")).setStructureSize(11).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> WITCH_HUTS_DARK_FOREST = addToStructureMaps("witch_hut_dark_forest", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/dark_forest_start_pool")).setStructureSize(11).build()));
    public static final RegistryObject<Structure<NoFeatureConfig>> WITCH_HUTS_GIANT_TREE_TAIGA = addToStructureMaps("witch_hut_giant_tree_taiga", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/giant_tree_taiga_start_pool")).setStructureSize(11).build()));

    // Bastions
    public static final RegistryObject<Structure<NoFeatureConfig>> BASTION_UNDERGROUND = addToStructureMaps("bastion_underground", () -> (new GenericJigsawStructure.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "bastions/underground/starts")).setStructureSize(10).setFixedYSpawn(12).doNotUseHeightmap().setBiomeRange(7).setStructureBlacklistRange(5).setAvoidStructuresSet(ImmutableSet.of(RSStructureTagMap.STRUCTURE_TAGS.BASTION_AVOID_STRUCTURE)).build()));


    private static <T extends Structure<?>> RegistryObject<T> addToStructureMaps(String name, Supplier<T> structure) {
        return STRUCTURE_FEATURES.register(name, structure);
    }

    public static void setupStructures() {
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_birch"), MINESHAFT_BIRCH.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 399117345));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_dark_forest"), MINESHAFT_DARK_FOREST.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 2011511156));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_desert"), MINESHAFT_DESERT.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1990612785));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_end"), MINESHAFT_END.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 2057488602));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_nether"), MINESHAFT_NETHER.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1220811654));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_crimson"), MINESHAFT_CRIMSON.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1153019610));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_warped"), MINESHAFT_WARPED.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1095888662));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_icy"), MINESHAFT_ICY.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1451015246));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_jungle"), MINESHAFT_JUNGLE.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1434412876));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_ocean"), MINESHAFT_OCEAN.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1774808662));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_savanna"), MINESHAFT_SAVANNA.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1960212212));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_stone"), MINESHAFT_STONE.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1436736620));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_swamp"), MINESHAFT_SWAMP.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 2037177700));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_taiga"), MINESHAFT_TAIGA.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1383003172));

        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "stronghold_nether"), STRONGHOLD_NETHER.get(), GenerationStage.Decoration.TOP_LAYER_MODIFICATION, createSpacingAndSalt(RepurposedStructures.RSStrongholdsConfig.netherStrongholdMaxChunkDistance.get(), 0.5f, 1731422513));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "stronghold_end"), STRONGHOLD_END.get(), GenerationStage.Decoration.STRONGHOLDS, new StructureSeparationSettings(RepurposedStructures.RSStrongholdsConfig.strongholdEndMaxChunkDistance.get(), (int) (RepurposedStructures.RSStrongholdsConfig.strongholdEndMaxChunkDistance.get() * 0.5f), 1922886435));

        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "fortress_jungle"), FORTRESS_JUNGLE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSFortressesConfig.jungleFortressMaxChunkDistance.get(), 0.5f, 1464189157));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "igloo_grassy"), IGLOO_GRASSY.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSIgloosConfig.grassyIglooMaxChunkDistance.get(), 0.5f, 1460835582));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "igloo_stone"), IGLOO_STONE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSIgloosConfig.stoneIglooMaxChunkDistance.get(), 0.5f, 1327429039));

        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_wasteland"), TEMPLE_NETHER_WASTELAND.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSTemplesConfig.netherWastelandTempleMaxChunkDistance.get(), 0.5f, 1435489909));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_soul"), TEMPLE_NETHER_SOUL.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSTemplesConfig.netherSoulTempleMaxChunkDistance.get(), 0.5f, 1799485937));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_basalt"), TEMPLE_NETHER_BASALT.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSTemplesConfig.netherBasaltTempleMaxChunkDistance.get(), 0.5f, 1063117750));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_crimson"), TEMPLE_NETHER_CRIMSON.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSTemplesConfig.netherCrimsonTempleMaxChunkDistance.get(), 0.5f, 1898896156));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_warped"), TEMPLE_NETHER_WARPED.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSTemplesConfig.netherWarpedTempleMaxChunkDistance.get(), 0.5f, 1635542708));

        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_nether_brick"), OUTPOST_NETHER_BRICK.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSOutpostsConfig.netherBrickOutpostMaxChunkDistance.get(), 0.5f, 1305971394));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_warped"), OUTPOST_WARPED.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSOutpostsConfig.warpedOutpostMaxChunkDistance.get(), 0.5f, 1928816918));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_crimson"), OUTPOST_CRIMSON.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSOutpostsConfig.crimsonOutpostMaxChunkDistance.get(), 0.5f, 1951425662));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_birch"), OUTPOST_BIRCH.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSOutpostsConfig.outpostBirchMaxChunkDistance.get(), 0.5f, 1676743168));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_jungle"), OUTPOST_JUNGLE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSOutpostsConfig.outpostJungleMaxChunkDistance.get(), 0.5f, 548433028));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_giant_tree_taiga"), OUTPOST_GIANT_TREE_TAIGA.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSOutpostsConfig.outpostGiantTreeTaigaMaxChunkDistance.get(), 0.5f, 993252541));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_desert"), OUTPOST_DESERT.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSOutpostsConfig.outpostDesertMaxChunkDistance.get(), 0.5f, 593099376));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_badlands"), OUTPOST_BADLANDS.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSOutpostsConfig.outpostBadlandsMaxChunkDistance.get(), 0.5f, 1702026868));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_snowy"), OUTPOST_SNOWY.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSOutpostsConfig.outpostSnowyMaxChunkDistance.get(), 0.5f, 849388460));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_icy"), OUTPOST_ICY.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSOutpostsConfig.outpostIcyMaxChunkDistance.get(), 0.5f, 935294633));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_taiga"), OUTPOST_TAIGA.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSOutpostsConfig.outpostTaigaMaxChunkDistance.get(), 0.5f, 272805097));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_oak"), OUTPOST_OAK.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSOutpostsConfig.outpostOakMaxChunkDistance.get(), 0.5f, 698118385));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "outpost_end"), OUTPOST_END.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSOutpostsConfig.outpostEndMaxChunkDistance.get(), 0.5f, 831830630));

        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "pyramid_badlands"), PYRAMID_BADLANDS.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSPyramidsConfig.badlandsPyramidMaxChunkDistance.get(), 0.5f, 1718729448));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "pyramid_nether"), PYRAMID_NETHER.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSPyramidsConfig.netherPyramidMaxChunkDistance.get(), 0.5f, 2054372964));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "pyramid_snowy"), PYRAMID_SNOWY.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSPyramidsConfig.pyramidSnowyMaxChunkDistance.get(), 0.5f, 1630533493));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "pyramid_end"), PYRAMID_END.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSPyramidsConfig.pyramidEndMaxChunkDistance.get(), 0.5f, 1145023315));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "pyramid_icy"), PYRAMID_ICY.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSPyramidsConfig.pyramidIcyMaxChunkDistance.get(), 0.5f, 884076931));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "pyramid_jungle"), PYRAMID_JUNGLE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSPyramidsConfig.pyramidJungleMaxChunkDistance.get(), 0.5f, 1483015905));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "pyramid_mushroom"), PYRAMID_MUSHROOM.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSPyramidsConfig.pyramidMushroomMaxChunkDistance.get(), 0.5f, 1035759391));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "pyramid_ocean"), PYRAMID_OCEAN.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSPyramidsConfig.pyramidOceanMaxChunkDistance.get(), 0.5f, 777281414));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "pyramid_giant_tree_taiga"), PYRAMID_GIANT_TREE_TAIGA.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSPyramidsConfig.pyramidGiantTreeTaigaMaxChunkDistance.get(), 0.5f, 1977974973));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "pyramid_flower_forest"), PYRAMID_FLOWER_FOREST.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSPyramidsConfig.pyramidFlowerForestMaxChunkDistance.get(), 0.5f, 1984904323));

        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_end"), SHIPWRECK_END.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSShipwrecksConfig.endShipwreckMaxChunkDistance.get(), 0.5f, 1605500075));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_nether_bricks"), SHIPWRECK_NETHER_BRICKS.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSShipwrecksConfig.netherBricksShipwreckMaxChunkDistance.get(), 0.5f, 2073308006), new NetherShipwreckConfig(true));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_crimson"), SHIPWRECK_CRIMSON.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSShipwrecksConfig.crimsonShipwreckMaxChunkDistance.get(), 0.5f, 1019716871), new NetherShipwreckConfig(false));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_warped"), SHIPWRECK_WARPED.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSShipwrecksConfig.warpedShipwreckMaxChunkDistance.get(), 0.5f, 2072979641), new NetherShipwreckConfig(false));

        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_badlands"), VILLAGE_BADLANDS.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSVillagesConfig.badlandsVillageMaxChunkDistance.get(), 0.5f, 1319707555));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_birch"), VILLAGE_BIRCH.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSVillagesConfig.birchVillageMaxChunkDistance.get(), 0.5f, 1102567365));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_dark_oak"), VILLAGE_DARK_FOREST.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSVillagesConfig.darkForestVillageMaxChunkDistance.get(), 0.5f, 1921339358));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_jungle"), VILLAGE_JUNGLE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSVillagesConfig.jungleVillageMaxChunkDistance.get(), 0.5f, 1229975218));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_swamp"), VILLAGE_SWAMP.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSVillagesConfig.swampVillageMaxChunkDistance.get(), 0.5f, 1559650945));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_mountains"), VILLAGE_MOUNTAINS.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSVillagesConfig.mountainsVillageMaxChunkDistance.get(), 0.5f, 2010875989));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_giant_taiga"), VILLAGE_GIANT_TAIGA.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSVillagesConfig.giantTaigaVillageMaxChunkDistance.get(), 0.5f, 1559528842));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_crimson"), VILLAGE_CRIMSON.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSVillagesConfig.crimsonVillageMaxChunkDistance.get(), 0.5f, 1854750198));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_warped"), VILLAGE_WARPED.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSVillagesConfig.warpedVillageMaxChunkDistance.get(), 0.5f, 1298332136));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_oak"), VILLAGE_OAK.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSVillagesConfig.oakVillageMaxChunkDistance.get(), 0.5f, 2112891039));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "village_mushroom"), VILLAGE_MUSHROOM.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSVillagesConfig.villageMushroomAverageChunkDistance.get(), 0.5f, 1150624897));
        // regexpos2

        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "ruined_portal_end"), RUINED_PORTAL_END.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSRuinedPortalsConfig.ruinedPortalEndMaxChunkDistance.get(), 0.5f, 532404086));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "ruins_nether"), RUINS_NETHER.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSRuinsConfig.ruinsNetherMaxChunkDistance.get(), 0.5f, 1336047555));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "ruins_land_warm"), RUINS_LAND_WARM.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSRuinsConfig.ruinsLandWarmMaxChunkDistance.get(), 0.25f, 18646107));
        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "ruins_land_hot"), RUINS_LAND_HOT.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSRuinsConfig.ruinsLandHotMaxChunkDistance.get(), 0.25f, 1243670027));

        addToTerraformingAndStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "city_nether"), CITY_NETHER.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSCitiesConfig.citiesNetherMaxChunkDistance.get(), 0.5f, 2082652405));

        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mansion_birch"), MANSION_BIRCH.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSMansionsConfig.mansionBirchMaxChunkDistance.get(), 0.5f, 182367035));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mansion_jungle"), MANSION_JUNGLE.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSMansionsConfig.mansionJungleMaxChunkDistance.get(), 0.5f, 1267916621));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mansion_oak"), MANSION_OAK.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSMansionsConfig.mansionOakMaxChunkDistance.get(), 0.5f, 147853731));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mansion_savanna"), MANSION_SAVANNA.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSMansionsConfig.mansionSavannaMaxChunkDistance.get(), 0.5f, 2024558925));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mansion_taiga"), MANSION_TAIGA.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSMansionsConfig.mansionTaigaMaxChunkDistance.get(), 0.5f, 418506505));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mansion_desert"), MANSION_DESERT.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSMansionsConfig.mansionDesertMaxChunkDistance.get(), 0.5f, 724317387));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "mansion_snowy"), MANSION_SNOWY.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSMansionsConfig.mansionSnowyMaxChunkDistance.get(), 0.5f, 1115107889));

        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "witch_hut_oak"), WITCH_HUTS_OAK.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSWitchHutsConfig.witchHutsOakMaxChunkDistance.get(), 0.5f, 741641348));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "witch_hut_taiga"), WITCH_HUTS_TAIGA.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSWitchHutsConfig.witchHutsTaigaMaxChunkDistance.get(), 0.5f, 1925189659));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "witch_hut_birch"), WITCH_HUTS_BIRCH.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSWitchHutsConfig.witchHutsBirchMaxChunkDistance.get(), 0.5f, 904634508));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "witch_hut_dark_forest"), WITCH_HUTS_DARK_FOREST.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSWitchHutsConfig.witchHutsDarkForestMaxChunkDistance.get(), 0.5f, 165100151));
        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "witch_hut_giant_tree_taiga"), WITCH_HUTS_GIANT_TREE_TAIGA.get(), GenerationStage.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSWitchHutsConfig.witchHutsGiantTreeTaigaMaxChunkDistance.get(), 0.5f, 200289401));

        addToStructureMaps(new ResourceLocation(RepurposedStructures.MODID, "bastion_underground"), BASTION_UNDERGROUND.get(), GenerationStage.Decoration.UNDERGROUND_STRUCTURES, createSpacingAndSalt(RepurposedStructures.RSBastionsConfig.bastionUndergroundMaxChunkDistance.get(), 0.5f, 1359301629));

        //registers the structure pieces.
        RSStructurePieces.registerStructurePieces();
    }

    private static StructureSeparationSettings createSpacingAndSalt(int averageSpacing, float percentageForMinSpacing, int salt){
        return new StructureSeparationSettings(averageSpacing, (int) (averageSpacing * percentageForMinSpacing), salt);
    }

    public static <F extends Structure<NoFeatureConfig>> void addToTerraformingAndStructureMaps(ResourceLocation resourceLocation, F structure, GenerationStage.Decoration stage, StructureSeparationSettings StructureSeparationSettings) {
        Structure.NOISE_AFFECTING_FEATURES = ImmutableList.<Structure<?>>builder().addAll(Structure.NOISE_AFFECTING_FEATURES).add(structure).build();
        addToStructureMaps(resourceLocation, structure, stage, StructureSeparationSettings);
    }

    public static <C extends IFeatureConfig, F extends Structure<C>> void addToStructureMaps(ResourceLocation resourceLocation, F structure, GenerationStage.Decoration stage, StructureSeparationSettings structureSeparationSettings, C config) {
        Structure.STRUCTURES_REGISTRY.put(resourceLocation.toString().toLowerCase(Locale.ROOT), structure);

        // This is only for myself. Others should override step() in
        // their structure's class to return their generation stage instead.
        Structure.STEP.put(structure, stage);

        DimensionStructuresSettings.DEFAULTS = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder().putAll(DimensionStructuresSettings.DEFAULTS).put(structure, structureSeparationSettings).build();
        FlatGenerationSettings.STRUCTURE_FEATURES.put(structure, structure.configured(config));
        RS_STRUCTURES.put(structure, structureSeparationSettings);
    }

    public static <F extends Structure<NoFeatureConfig>> void addToStructureMaps(ResourceLocation resourceLocation, F structure, GenerationStage.Decoration stage, StructureSeparationSettings structureSeparationSettings) {
        Structure.STRUCTURES_REGISTRY.put(resourceLocation.toString().toLowerCase(Locale.ROOT), structure);

        // This is only for myself. Others should override step() in their structure's class to return their generation stage instead.
        Structure.STEP.put(structure, stage);

        DimensionStructuresSettings.DEFAULTS = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder().putAll(DimensionStructuresSettings.DEFAULTS).put(structure, structureSeparationSettings).build();
        FlatGenerationSettings.STRUCTURE_FEATURES.put(structure, structure.configured(IFeatureConfig.NONE));
        RS_STRUCTURES.put(structure, structureSeparationSettings);
    }
}
