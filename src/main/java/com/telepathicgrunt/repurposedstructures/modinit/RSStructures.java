package com.telepathicgrunt.repurposedstructures.modinit;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.configs.RSBastionsConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSCitiesConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSFortressesConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSIgloosConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSMansionsConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSMineshaftsConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSOutpostsConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSPyramidsConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSRuinedPortalsConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSRuinsConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSShipwrecksConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSStrongholdsConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSTemplesConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSVillagesConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSWitchHutsConfig;
import com.telepathicgrunt.repurposedstructures.mixin.structures.StructureFeatureAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.structures.StructureSettingsAccessor;
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
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.AdvancedDistanceJigsawStructureCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.AdvancedJigsawStructureCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.BuriableStructureCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.GenericJigsawStructureCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.GenericNetherJigsawStructureCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.MansionCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.MineshaftCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.ShipwreckNetherCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.StartPoolOnlyCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionPieces;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public final class RSStructures {
    private RSStructures() {}

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

    public static final DeferredRegister<StructureFeature<?>> STRUCTURE_FEATURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, RepurposedStructures.MODID);
    public static final Map<StructureFeature<?>, StructureFeatureConfiguration> RS_STRUCTURES = new HashMap<>();
    public static final Set<ResourceLocation> RS_STRUCTURE_START_PIECES = new HashSet<>();

    //Mineshafts
    public static RegistryObject<StructureFeature<NoneFeatureConfiguration>> MINESHAFT_BIRCH = addToStructureMaps("mineshaft_birch", () -> MineshaftStructure.create(new MineshaftCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/birch/start_pool")).setStructureSize(RSMineshaftsConfig.birchMineshaftSize).setMaxY(RSMineshaftsConfig.birchMineshaftMaxHeight).setMinY(RSMineshaftsConfig.birchMineshaftMinHeight).setProbability(RSMineshaftsConfig.birchMineshaftSpawnrate).build()));
    public static RegistryObject<StructureFeature<NoneFeatureConfiguration>> MINESHAFT_DARK_FOREST = addToStructureMaps("mineshaft_dark_forest", () -> MineshaftStructure.create(new MineshaftCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/dark_forest/start_pool")).setStructureSize(RSMineshaftsConfig.darkForestMineshaftSize).setMaxY(RSMineshaftsConfig.darkForestMineshaftMaxHeight).setMinY(RSMineshaftsConfig.darkForestMineshaftMinHeight).setProbability(RSMineshaftsConfig.darkForestMineshaftSpawnrate).build()));
    public static RegistryObject<StructureFeature<NoneFeatureConfiguration>> MINESHAFT_DESERT = addToStructureMaps("mineshaft_desert", () -> MineshaftStructure.create(new MineshaftCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/desert/start_pool")).setStructureSize(RSMineshaftsConfig.desertMineshaftSize).setMaxY(RSMineshaftsConfig.desertMineshaftMaxHeight).setMinY(RSMineshaftsConfig.desertMineshaftMinHeight).setProbability(RSMineshaftsConfig.desertMineshaftSpawnrate).build()));
    public static RegistryObject<StructureFeature<NoneFeatureConfiguration>> MINESHAFT_ICY = addToStructureMaps("mineshaft_icy", () -> MineshaftStructure.create(new MineshaftCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/icy/start_pool")).setStructureSize(RSMineshaftsConfig.icyMineshaftSize).setMaxY(RSMineshaftsConfig.icyMineshaftMaxHeight).setMinY(RSMineshaftsConfig.icyMineshaftMinHeight).setProbability(RSMineshaftsConfig.icyMineshaftSpawnrate).build()));
    public static RegistryObject<StructureFeature<NoneFeatureConfiguration>> MINESHAFT_JUNGLE = addToStructureMaps("mineshaft_jungle", () -> MineshaftStructure.create(new MineshaftCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/jungle/start_pool")).setStructureSize(RSMineshaftsConfig.jungleMineshaftSize).setMaxY(RSMineshaftsConfig.jungleMineshaftMaxHeight).setMinY(RSMineshaftsConfig.jungleMineshaftMinHeight).setProbability(RSMineshaftsConfig.jungleMineshaftSpawnrate).build()));
    public static RegistryObject<StructureFeature<NoneFeatureConfiguration>> MINESHAFT_OCEAN = addToStructureMaps("mineshaft_ocean", () -> MineshaftStructure.create(new MineshaftCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/ocean/start_pool")).setStructureSize(RSMineshaftsConfig.oceanMineshaftSize).setMaxY(RSMineshaftsConfig.oceanMineshaftMaxHeight).setMinY(RSMineshaftsConfig.oceanMineshaftMinHeight).setProbability(RSMineshaftsConfig.oceanMineshaftSpawnrate).build()));
    public static RegistryObject<StructureFeature<NoneFeatureConfiguration>> MINESHAFT_SAVANNA = addToStructureMaps("mineshaft_savanna", () -> MineshaftStructure.create(new MineshaftCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/savanna/start_pool")).setStructureSize(RSMineshaftsConfig.savannaMineshaftSize).setMaxY(RSMineshaftsConfig.savannaMineshaftMaxHeight).setMinY(RSMineshaftsConfig.savannaMineshaftMinHeight).setProbability(RSMineshaftsConfig.savannaMineshaftSpawnrate).build()));
    public static RegistryObject<StructureFeature<NoneFeatureConfiguration>> MINESHAFT_STONE = addToStructureMaps("mineshaft_stone", () -> MineshaftStructure.create(new MineshaftCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/stone/start_pool")).setStructureSize(RSMineshaftsConfig.stoneMineshaftSize).setMaxY(RSMineshaftsConfig.stoneMineshaftMaxHeight).setMinY(RSMineshaftsConfig.stoneMineshaftMinHeight).setProbability(RSMineshaftsConfig.stoneMineshaftSpawnrate).build()));
    public static RegistryObject<StructureFeature<NoneFeatureConfiguration>> MINESHAFT_SWAMP = addToStructureMaps("mineshaft_swamp", () -> MineshaftStructure.create(new MineshaftCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/swamp/start_pool")).setStructureSize(RSMineshaftsConfig.swampMineshaftSize).setMaxY(RSMineshaftsConfig.swampMineshaftMaxHeight).setMinY(RSMineshaftsConfig.swampMineshaftMinHeight).setProbability(RSMineshaftsConfig.swampMineshaftSpawnrate).build()));
    public static RegistryObject<StructureFeature<NoneFeatureConfiguration>> MINESHAFT_TAIGA = addToStructureMaps("mineshaft_taiga", () -> MineshaftStructure.create(new MineshaftCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/taiga/start_pool")).setStructureSize(RSMineshaftsConfig.taigaMineshaftSize).setMaxY(RSMineshaftsConfig.taigaMineshaftMaxHeight).setMinY(RSMineshaftsConfig.taigaMineshaftMinHeight).setProbability(RSMineshaftsConfig.taigaMineshaftSpawnrate).build()));
    public static RegistryObject<StructureFeature<NoneFeatureConfiguration>> MINESHAFT_NETHER = addToStructureMaps("mineshaft_nether", () -> MineshaftStructure.create(new MineshaftCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/nether/start_pool")).setStructureSize(RSMineshaftsConfig.netherMineshaftSize).setMaxY(RSMineshaftsConfig.netherMineshaftMaxHeight).setMinY(RSMineshaftsConfig.netherMineshaftMinHeight).setProbability(RSMineshaftsConfig.netherMineshaftSpawnrate).build()));
    public static RegistryObject<StructureFeature<NoneFeatureConfiguration>> MINESHAFT_CRIMSON = addToStructureMaps("mineshaft_crimson", () -> MineshaftStructure.create(new MineshaftCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/crimson/start_pool")).setStructureSize(RSMineshaftsConfig.crimsonMineshaftSize).setMaxY(RSMineshaftsConfig.crimsonMineshaftMaxHeight).setMinY(RSMineshaftsConfig.crimsonMineshaftMinHeight).setProbability(RSMineshaftsConfig.crimsonMineshaftSpawnrate).build()));
    public static RegistryObject<StructureFeature<NoneFeatureConfiguration>> MINESHAFT_WARPED = addToStructureMaps("mineshaft_warped", () -> MineshaftStructure.create(new MineshaftCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/warped/start_pool")).setStructureSize(RSMineshaftsConfig.warpedMineshaftSize).setMaxY(RSMineshaftsConfig.warpedMineshaftMaxHeight).setMinY(RSMineshaftsConfig.warpedMineshaftMinHeight).setProbability(RSMineshaftsConfig.warpedMineshaftSpawnrate).build()));
    public static RegistryObject<StructureFeature<NoneFeatureConfiguration>> MINESHAFT_END = addToStructureMaps("mineshaft_end", () -> MineshaftEndStructure.create(new MineshaftCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/end/start_pool")).setStructureSize(RSMineshaftsConfig.endMineshaftSize).setProbability(RSMineshaftsConfig.endMineshaftSpawnrate).build()));

    //Strongholds
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> STRONGHOLD_NETHER = addToStructureMaps("stronghold_nether", () -> AdvancedDistanceJigsawStructure.create(new AdvancedDistanceJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/start_pool")).setStructureSize(RSStrongholdsConfig.strongholdNetherSize).setMaxY(RSStrongholdsConfig.strongholdNetherMaxHeight).setMinY(RSStrongholdsConfig.strongholdNetherMinHeight).setDistanceFromWorldOrigin(2817).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> STRONGHOLD_END = addToStructureMaps("stronghold_end", () -> StrongholdEndStructure.create(new AdvancedDistanceJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "strongholds/end/start_pool")).setStructureSize(RSStrongholdsConfig.strongholdEndSize).setMaxY(RSStrongholdsConfig.strongholdEndMaxHeight).setMinY(RSStrongholdsConfig.strongholdEndMinHeight).setVerticalRange(RSStrongholdsConfig.strongholdEndVerticalRange).setDistanceFromWorldOrigin(8000).doNotClipOutOfBoundsPieces().build()));

    //Igloos and Fortress
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> FORTRESS_JUNGLE = addToStructureMaps("fortress_jungle", () -> AdvancedJigsawStructure.create(new AdvancedJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "fortresses/jungle/start_pool")).setStructureSize(RSFortressesConfig.jungleFortressSize).setBiomeRange(4).setMaxY(RSFortressesConfig.jungleFortressMaxHeight).setMinY(RSFortressesConfig.jungleFortressMinHeight).setVerticalRange(RSFortressesConfig.jungleFortressVerticalRange).doNotClipOutOfBoundsPieces().build()));

    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> IGLOO_GRASSY = addToStructureMaps("igloo_grassy", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "igloos/grassy_top")).setStructureSize(20).cannotSpawnInWater().build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> IGLOO_STONE = addToStructureMaps("igloo_stone", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "igloos/stone_top")).setStructureSize(20).cannotSpawnInWater().build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> IGLOO_MUSHROOM = addToStructureMaps("igloo_mushroom", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "igloos/mushroom_top")).setStructureSize(20).cannotSpawnInWater().build()));

    //Temples
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> TEMPLE_NETHER_WASTELAND = addToStructureMaps("temple_nether_wasteland", () -> GenericNetherJigsawStructure.create(new GenericNetherJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/nether_wasteland")).setStructureSize(8).canSpawnOnLiquid().build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> TEMPLE_NETHER_BASALT = addToStructureMaps("temple_nether_basalt", () -> GenericNetherJigsawStructure.create(new GenericNetherJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/nether_basalt")).setStructureSize(8).canSpawnOnLiquid().build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> TEMPLE_NETHER_WARPED = addToStructureMaps("temple_nether_warped", () -> GenericNetherJigsawStructure.create(new GenericNetherJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/nether_warped")).setStructureSize(8).canSpawnOnLiquid().build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> TEMPLE_NETHER_CRIMSON = addToStructureMaps("temple_nether_crimson", () -> GenericNetherJigsawStructure.create(new GenericNetherJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/nether_crimson")).setStructureSize(8).canSpawnOnLiquid().build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> TEMPLE_NETHER_SOUL = addToStructureMaps("temple_nether_soul", () -> GenericNetherJigsawStructure.create(new GenericNetherJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "temples/nether_soul")).setStructureSize(8).canSpawnOnLiquid().build()));

    //Outposts
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> OUTPOST_BIRCH = addToStructureMaps("outpost_birch", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/birch/base_plates")).setStructureSize(11).setStructureBlacklistRange(5).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> OUTPOST_JUNGLE = addToStructureMaps("outpost_jungle", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/jungle/base_plates")).setStructureSize(11).setBiomeRange(1).setStructureBlacklistRange(5).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> OUTPOST_GIANT_TREE_TAIGA = addToStructureMaps("outpost_giant_tree_taiga", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/giant_tree_taiga/base_plates")).setStructureSize(11).setStructureBlacklistRange(5).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> OUTPOST_DESERT = addToStructureMaps("outpost_desert", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/desert/base_plates")).setStructureSize(11).setBiomeRange(1).setStructureBlacklistRange(5).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> OUTPOST_BADLANDS = addToStructureMaps("outpost_badlands", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/badlands/base_plates")).setStructureSize(11).setTerrainHeightRadius(1).setAllowTerrainHeightRange(20).setStructureBlacklistRange(5).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> OUTPOST_SNOWY = addToStructureMaps("outpost_snowy", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/snowy/base_plates")).setStructureSize(11).setStructureBlacklistRange(5).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> OUTPOST_ICY = addToStructureMaps("outpost_icy", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/icy/base_plates")).setStructureSize(11).setBiomeRange(1).setStructureBlacklistRange(5).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> OUTPOST_TAIGA = addToStructureMaps("outpost_taiga", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/taiga/base_plates")).setStructureSize(11).setBiomeRange(1).setStructureBlacklistRange(5).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> OUTPOST_OAK = addToStructureMaps("outpost_oak", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/oak/base_plates")).setStructureSize(11).setBiomeRange(1).setStructureBlacklistRange(5).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> OUTPOST_NETHER_BRICK = addToStructureMaps("outpost_nether_brick", () -> GenericNetherJigsawStructure.create(new GenericNetherJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/nether_brick/base_plates")).setStructureSize(11).canSpawnOnLiquid().build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> OUTPOST_WARPED = addToStructureMaps("outpost_warped", () -> GenericNetherJigsawStructure.create(new GenericNetherJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/warped/base_plates")).setStructureSize(11).canSpawnOnLiquid().build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> OUTPOST_CRIMSON = addToStructureMaps("outpost_crimson", () -> GenericNetherJigsawStructure.create(new GenericNetherJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/crimson/base_plates")).setStructureSize(11).canSpawnOnLiquid().build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> OUTPOST_END = addToStructureMaps("outpost_end", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "outposts/end/base_plates")).setStructureSize(11).setBiomeRange(2).setAllowTerrainHeightRange(3).setAllowTerrainHeightRange(15).setMinHeightLimit(55).setStructureBlacklistRange(4).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.END_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));

    //Pyramids
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> PYRAMID_BADLANDS = addToStructureMaps("pyramid_badlands", () -> BuriableStructure.create(new BuriableStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "pyramids/badlands")).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> PYRAMID_DARK_FOREST = addToStructureMaps("pyramid_dark_forest", () -> BuriableStructure.create(new BuriableStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "pyramids/dark_forest")).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> PYRAMID_SNOWY = addToStructureMaps("pyramid_snowy", () -> BuriableStructure.create(new BuriableStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "pyramids/snowy")).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> PYRAMID_ICY = addToStructureMaps("pyramid_icy", () -> BuriableStructure.create(new BuriableStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "pyramids/icy")).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> PYRAMID_JUNGLE = addToStructureMaps("pyramid_jungle", () -> BuriableStructure.create(new BuriableStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "pyramids/jungle")).setOffsetAmount(20).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> PYRAMID_MUSHROOM = addToStructureMaps("pyramid_mushroom", () -> BuriableStructure.create(new BuriableStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "pyramids/mushroom")).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> PYRAMID_OCEAN = addToStructureMaps("pyramid_ocean", () -> BuriableStructure.create(new BuriableStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "pyramids/ocean")).cannotSpawnInWater().useOceanHeightmap().build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> PYRAMID_GIANT_TREE_TAIGA = addToStructureMaps("pyramid_giant_tree_taiga", () -> BuriableStructure.create(new BuriableStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "pyramids/giant_tree_taiga")).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> PYRAMID_FLOWER_FOREST = addToStructureMaps("pyramid_flower_forest", () -> BuriableStructure.create(new BuriableStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "pyramids/flower_forest")).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> PYRAMID_NETHER = addToStructureMaps("pyramid_nether", () -> GenericNetherJigsawStructure.create(new GenericNetherJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "pyramids/nether")).setStructureSize(8).searchForHighestLand().canSpawnOnLiquid().build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> PYRAMID_END = addToStructureMaps("pyramid_end", () -> LandBasedEndStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "pyramids/end")).setStructureSize(11).setCenterOffset(-2).build()));

    //Shipwrecks
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> SHIPWRECK_END = addToStructureMaps("shipwreck_end", () -> ShipwreckEndStructure.create(new StartPoolOnlyCodeConfig(new ResourceLocation(RepurposedStructures.MODID, "shipwrecks/end"))));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> SHIPWRECK_NETHER_BRICKS = addToStructureMaps("shipwreck_nether_bricks", () -> ShipwreckNetherStructure.create(new ShipwreckNetherCodeConfig(new ResourceLocation(RepurposedStructures.MODID, "shipwrecks/nether_bricks"), -3)));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> SHIPWRECK_CRIMSON = addToStructureMaps("shipwreck_crimson", () -> ShipwreckNetherStructure.create(new ShipwreckNetherCodeConfig(new ResourceLocation(RepurposedStructures.MODID, "shipwrecks/crimson"), -4)));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> SHIPWRECK_WARPED = addToStructureMaps("shipwreck_warped", () -> ShipwreckNetherStructure.create(new ShipwreckNetherCodeConfig(new ResourceLocation(RepurposedStructures.MODID, "shipwrecks/warped"), -4)));

    //Villages
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> VILLAGE_BADLANDS = addToStructureMaps("village_badlands", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "villages/badlands/town_centers")).setStructureSize(RSVillagesConfig.villageBadlandsSize).setBiomeRange(1).setTerrainHeightRadius(2).setAllowTerrainHeightRange(20).setStructureBlacklistRange(6).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> VILLAGE_BIRCH = addToStructureMaps("village_birch", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "villages/birch/town_centers")).setStructureSize(RSVillagesConfig.villageBirchSize).setBiomeRange(1).setStructureBlacklistRange(6).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> VILLAGE_DARK_FOREST = addToStructureMaps("village_dark_oak", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "villages/dark_forest/town_centers")).setStructureSize(RSVillagesConfig.villageDarkForestSize).setBiomeRange(1).setStructureBlacklistRange(6).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> VILLAGE_JUNGLE = addToStructureMaps("village_jungle", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "villages/jungle/town_centers")).setStructureSize(RSVillagesConfig.villageJungleSize).setBiomeRange(1).setStructureBlacklistRange(6).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> VILLAGE_SWAMP = addToStructureMaps("village_swamp", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "villages/swamp/town_centers")).setStructureSize(RSVillagesConfig.villageSwampSize).setCenterOffset(-1).setBiomeRange(1).setStructureBlacklistRange(6).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> VILLAGE_MOUNTAINS = addToStructureMaps("village_mountains", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "villages/mountains/town_centers")).setStructureSize(RSVillagesConfig.villageMountainsSize).setBiomeRange(1).setStructureBlacklistRange(6).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> VILLAGE_GIANT_TAIGA = addToStructureMaps("village_giant_taiga", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "villages/giant_taiga/town_centers")).setStructureSize(RSVillagesConfig.villageGiantTaigaSize).setBiomeRange(1).setStructureBlacklistRange(6).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> VILLAGE_OAK = addToStructureMaps("village_oak", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "villages/oak/town_centers")).setStructureSize(RSVillagesConfig.villageOakSize).setBiomeRange(1).setStructureBlacklistRange(6).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> VILLAGE_CRIMSON = addToStructureMaps("village_crimson", () -> GenericNetherJigsawStructure.create(new GenericNetherJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "villages/crimson/town_centers")).setStructureSize(RSVillagesConfig.villageCrimsonSize).searchForHighestLand().setBiomeRange(1).setStructureBlacklistRange(10).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> VILLAGE_WARPED = addToStructureMaps("village_warped", () -> GenericNetherJigsawStructure.create(new GenericNetherJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "villages/warped/town_centers")).setStructureSize(RSVillagesConfig.villageWarpedSize).searchForHighestLand().setBiomeRange(1).setStructureBlacklistRange(10).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST).collect(Collectors.toSet())).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> VILLAGE_MUSHROOM = addToStructureMaps("village_mushroom", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "villages/mushroom/town_centers")).setStructureSize(RSVillagesConfig.villageMushroomSize).setBiomeRange(1).setStructureBlacklistRange(6).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet())).build()));

    // regexpos1

    //Ruined Portals
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> RUINED_PORTAL_END = addToStructureMaps("ruined_portal_end", () -> LandBasedEndStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "ruined_portals/end")).setStructureSize(20).setCenterOffset(-6).build()));

    //Ruins
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> RUINS_NETHER = addToStructureMaps("ruins_nether", () -> GenericNetherJigsawStructure.create(new GenericNetherJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "ruins/nether/start_pool")).setStructureSize(1).searchForHighestLand().canSpawnOnLiquid().setLedgeSpotOffset(-1).setLiquidSpotOffset(-2).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> RUINS_LAND_WARM = addToStructureMaps("ruins_land_warm", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "ruins/land_warm/start_pool")).setStructureSize(2).setTerrainHeightRadius(2).setAllowTerrainHeightRange(5).cannotSpawnInWater().build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> RUINS_LAND_HOT = addToStructureMaps("ruins_land_hot", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "ruins/land_hot/start_pool")).setStructureSize(2).setTerrainHeightRadius(2).setAllowTerrainHeightRange(5).cannotSpawnInWater().build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> RUINS_LAND_COLD = addToStructureMaps("ruins_land_cold", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "ruins/land_cold/start_pool")).setStructureSize(2).setTerrainHeightRadius(2).setAllowTerrainHeightRange(5).cannotSpawnInWater().build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> RUINS_LAND_ICY = addToStructureMaps("ruins_land_icy", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "ruins/land_icy/start_pool")).setStructureSize(2).setTerrainHeightRadius(2).setAllowTerrainHeightRange(5).cannotSpawnInWater().build()));

    //Cities
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> CITY_NETHER = addToStructureMaps("city_nether", () -> CityNetherStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "cities/nether/start_pool")).setStructureSize(5).setStructureBlacklistRange(4).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet())).setPoolsThatIgnoreBounds(Set.of(new ResourceLocation(RepurposedStructures.MODID, "cities/nether/bridge_end"), new ResourceLocation(RepurposedStructures.MODID, "cities/nether/tower_top"), new ResourceLocation(RepurposedStructures.MODID, "cities/nether/fat_tower_top"))).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> CITY_OVERWORLD = addToStructureMaps("city_overworld", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "cities/overworld/start_pool")).setStructureSize(5).setStructureBlacklistRange(4).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION_AVOID_STRUCTURE).collect(Collectors.toSet())).setPoolsThatIgnoreBounds(Set.of(new ResourceLocation(RepurposedStructures.MODID, "cities/overworld/bridge_end"), new ResourceLocation(RepurposedStructures.MODID, "cities/overworld/tower_top"), new ResourceLocation(RepurposedStructures.MODID, "cities/overworld/fat_tower_top"))).cannotSpawnInWater().build()));

    //Mansions
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> MANSION_BIRCH = addToStructureMaps("mansion_birch", () -> MansionStructure.create(new MansionCodeConfig(MansionPieces.MANSIONTYPE.BIRCH)));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> MANSION_JUNGLE = addToStructureMaps("mansion_jungle", () -> MansionStructure.create(new MansionCodeConfig(MansionPieces.MANSIONTYPE.JUNGLE)));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> MANSION_OAK = addToStructureMaps("mansion_oak", () -> MansionStructure.create(new MansionCodeConfig(MansionPieces.MANSIONTYPE.OAK)));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> MANSION_SAVANNA = addToStructureMaps("mansion_savanna", () -> MansionStructure.create(new MansionCodeConfig(MansionPieces.MANSIONTYPE.SAVANNA)));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> MANSION_TAIGA = addToStructureMaps("mansion_taiga", () -> MansionStructure.create(new MansionCodeConfig(MansionPieces.MANSIONTYPE.TAIGA)));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> MANSION_DESERT = addToStructureMaps("mansion_desert", () -> MansionStructure.create(new MansionCodeConfig(MansionPieces.MANSIONTYPE.DESERT)));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> MANSION_SNOWY = addToStructureMaps("mansion_snowy", () -> MansionStructure.create(new MansionCodeConfig(MansionPieces.MANSIONTYPE.SNOWY)));

    //Witch Huts
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> WITCH_HUTS_OAK = addToStructureMaps("witch_hut_oak", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/oak_start_pool")).setStructureSize(11).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> WITCH_HUTS_TAIGA = addToStructureMaps("witch_hut_taiga", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/taiga_start_pool")).setStructureSize(11).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> WITCH_HUTS_BIRCH = addToStructureMaps("witch_hut_birch", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/birch_start_pool")).setStructureSize(11).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> WITCH_HUTS_DARK_FOREST = addToStructureMaps("witch_hut_dark_forest", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/dark_forest_start_pool")).setStructureSize(11).build()));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> WITCH_HUTS_GIANT_TREE_TAIGA = addToStructureMaps("witch_hut_giant_tree_taiga", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/giant_tree_taiga_start_pool")).setStructureSize(11).build()));

    // Bastions
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> BASTION_UNDERGROUND = addToStructureMaps("bastion_underground", () -> GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "bastions/underground/starts")).setStructureSize(10).setFixedYSpawn(-45).doNotUseHeightmap().setBiomeRange(7).setStructureBlacklistRange(5).setAvoidStructuresSet(ImmutableSet.of(RSStructureTagMap.STRUCTURE_TAGS.BASTION_AVOID_STRUCTURE)).build()));


    private static <T extends StructureFeature<?>> RegistryObject<T> addToStructureMaps(String name, Supplier<T> structure) {
        return STRUCTURE_FEATURES.register(name, structure);
    }

    public static void setupStructures() {
        addToStructureMaps(MINESHAFT_BIRCH.get(), GenerationStep.Decoration.UNDERGROUND_STRUCTURES, new StructureFeatureConfiguration(1, 0, 399117345));
        addToStructureMaps(MINESHAFT_DARK_FOREST.get(), GenerationStep.Decoration.UNDERGROUND_STRUCTURES, new StructureFeatureConfiguration(1, 0, 2011511156));
        addToStructureMaps(MINESHAFT_DESERT.get(), GenerationStep.Decoration.UNDERGROUND_STRUCTURES, new StructureFeatureConfiguration(1, 0, 1990612785));
        addToStructureMaps(MINESHAFT_END.get(), GenerationStep.Decoration.UNDERGROUND_STRUCTURES, new StructureFeatureConfiguration(1, 0, 2057488602));
        addToStructureMaps(MINESHAFT_NETHER.get(), GenerationStep.Decoration.FLUID_SPRINGS, new StructureFeatureConfiguration(1, 0, 1220811654));
        addToStructureMaps(MINESHAFT_CRIMSON.get(), GenerationStep.Decoration.FLUID_SPRINGS, new StructureFeatureConfiguration(1, 0, 1153019610));
        addToStructureMaps(MINESHAFT_WARPED.get(), GenerationStep.Decoration.FLUID_SPRINGS, new StructureFeatureConfiguration(1, 0, 1095888662));
        addToStructureMaps(MINESHAFT_ICY.get(), GenerationStep.Decoration.UNDERGROUND_STRUCTURES, new StructureFeatureConfiguration(1, 0, 1451015246));
        addToStructureMaps(MINESHAFT_JUNGLE.get(), GenerationStep.Decoration.UNDERGROUND_STRUCTURES, new StructureFeatureConfiguration(1, 0, 1434412876));
        addToStructureMaps(MINESHAFT_OCEAN.get(), GenerationStep.Decoration.UNDERGROUND_STRUCTURES, new StructureFeatureConfiguration(1, 0, 1774808662));
        addToStructureMaps(MINESHAFT_SAVANNA.get(), GenerationStep.Decoration.UNDERGROUND_STRUCTURES, new StructureFeatureConfiguration(1, 0, 1960212212));
        addToStructureMaps(MINESHAFT_STONE.get(), GenerationStep.Decoration.UNDERGROUND_STRUCTURES, new StructureFeatureConfiguration(1, 0, 1436736620));
        addToStructureMaps(MINESHAFT_SWAMP.get(), GenerationStep.Decoration.UNDERGROUND_STRUCTURES, new StructureFeatureConfiguration(1, 0, 2037177700));
        addToStructureMaps(MINESHAFT_TAIGA.get(), GenerationStep.Decoration.UNDERGROUND_STRUCTURES, new StructureFeatureConfiguration(1, 0, 1383003172));

        addToStructureMaps(STRONGHOLD_NETHER.get(), GenerationStep.Decoration.TOP_LAYER_MODIFICATION, createSpacingAndSalt(RSStrongholdsConfig.strongholdNetherAverageChunkDistance.get(), 0.5f, 1731422513));
        addToStructureMaps(STRONGHOLD_END.get(), GenerationStep.Decoration.STRONGHOLDS, new StructureFeatureConfiguration(RSStrongholdsConfig.strongholdEndAverageChunkDistance.get(), (int) (RSStrongholdsConfig.strongholdEndAverageChunkDistance.get() * 0.5f), 1922886435));

        addToStructureMaps(FORTRESS_JUNGLE.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSFortressesConfig.jungleFortressAverageChunkDistance.get(), 0.5f, 1464189157));
        addToTerraformingAndStructureMaps(IGLOO_GRASSY.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSIgloosConfig.grassyIglooAverageChunkDistance.get(), 0.5f, 1460835582));
        addToTerraformingAndStructureMaps(IGLOO_STONE.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSIgloosConfig.stoneIglooAverageChunkDistance.get(), 0.5f, 1327429039));
        addToTerraformingAndStructureMaps(IGLOO_MUSHROOM.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSIgloosConfig.mushroomIglooAverageChunkDistance.get(), 0.5f, 1242234354));

        addToStructureMaps(TEMPLE_NETHER_WASTELAND.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSTemplesConfig.netherWastelandTempleAverageChunkDistance.get(), 0.5f, 1435489909));
        addToStructureMaps(TEMPLE_NETHER_SOUL.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSTemplesConfig.netherSoulTempleAverageChunkDistance.get(), 0.5f, 1799485937));
        addToStructureMaps(TEMPLE_NETHER_BASALT.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSTemplesConfig.netherBasaltTempleAverageChunkDistance.get(), 0.5f, 1063117750));
        addToStructureMaps(TEMPLE_NETHER_CRIMSON.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSTemplesConfig.netherCrimsonTempleAverageChunkDistance.get(), 0.5f, 1898896156));
        addToStructureMaps(TEMPLE_NETHER_WARPED.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSTemplesConfig.netherWarpedTempleAverageChunkDistance.get(), 0.5f, 1635542708));

        addToTerraformingAndStructureMaps(OUTPOST_NETHER_BRICK.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSOutpostsConfig.outpostNetherBrickAverageChunkDistance.get(), 0.5f, 1305971394));
        addToTerraformingAndStructureMaps(OUTPOST_WARPED.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSOutpostsConfig.outpostWarpedAverageChunkDistance.get(), 0.5f, 1928816918));
        addToTerraformingAndStructureMaps(OUTPOST_CRIMSON.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSOutpostsConfig.outpostCrimsonAverageChunkDistance.get(), 0.5f, 1951425662));
        addToTerraformingAndStructureMaps(OUTPOST_BIRCH.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSOutpostsConfig.outpostBirchAverageChunkDistance.get(), 0.5f, 1676743168));
        addToTerraformingAndStructureMaps(OUTPOST_JUNGLE.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSOutpostsConfig.outpostJungleAverageChunkDistance.get(), 0.5f, 548433028));
        addToTerraformingAndStructureMaps(OUTPOST_GIANT_TREE_TAIGA.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSOutpostsConfig.outpostGiantTreeTaigaAverageChunkDistance.get(), 0.5f, 993252541));
        addToTerraformingAndStructureMaps(OUTPOST_DESERT.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSOutpostsConfig.outpostDesertAverageChunkDistance.get(), 0.5f, 593099376));
        addToTerraformingAndStructureMaps(OUTPOST_BADLANDS.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSOutpostsConfig.outpostBadlandsAverageChunkDistance.get(), 0.5f, 1702026868));
        addToTerraformingAndStructureMaps(OUTPOST_SNOWY.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSOutpostsConfig.outpostSnowyAverageChunkDistance.get(), 0.5f, 849388460));
        addToTerraformingAndStructureMaps(OUTPOST_ICY.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSOutpostsConfig.outpostIcyAverageChunkDistance.get(), 0.5f, 935294633));
        addToTerraformingAndStructureMaps(OUTPOST_TAIGA.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSOutpostsConfig.outpostTaigaAverageChunkDistance.get(), 0.5f, 272805097));
        addToTerraformingAndStructureMaps(OUTPOST_OAK.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSOutpostsConfig.outpostOakAverageChunkDistance.get(), 0.5f, 698118385));
        addToTerraformingAndStructureMaps(OUTPOST_END.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSOutpostsConfig.outpostEndAverageChunkDistance.get(), 0.5f, 831830630));

        addToStructureMaps(PYRAMID_BADLANDS.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSPyramidsConfig.pyramidBadlandsAverageChunkDistance.get(), 0.5f, 1718729448));
        addToStructureMaps(PYRAMID_DARK_FOREST.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSPyramidsConfig.pyramidDarkForestAverageChunkDistance.get(), 0.5f, 1983978348));
        addToTerraformingAndStructureMaps(PYRAMID_NETHER.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSPyramidsConfig.pyramidNetherAverageChunkDistance.get(), 0.5f, 2054372964));
        addToStructureMaps(PYRAMID_SNOWY.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSPyramidsConfig.pyramidSnowyAverageChunkDistance.get(), 0.5f, 1630533493));
        addToStructureMaps(PYRAMID_END.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSPyramidsConfig.pyramidEndAverageChunkDistance.get(), 0.5f, 1145023315));
        addToStructureMaps(PYRAMID_ICY.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSPyramidsConfig.pyramidIcyAverageChunkDistance.get(), 0.5f, 884076931));
        addToStructureMaps(PYRAMID_JUNGLE.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSPyramidsConfig.pyramidJungleAverageChunkDistance.get(), 0.5f, 1483015905));
        addToStructureMaps(PYRAMID_MUSHROOM.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSPyramidsConfig.pyramidMushroomAverageChunkDistance.get(), 0.5f, 1035759391));
        addToStructureMaps(PYRAMID_OCEAN.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSPyramidsConfig.pyramidOceanAverageChunkDistance.get(), 0.5f, 777281414));
        addToStructureMaps(PYRAMID_GIANT_TREE_TAIGA.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSPyramidsConfig.pyramidGiantTreeTaigaAverageChunkDistance.get(), 0.5f, 1977974973));
        addToStructureMaps(PYRAMID_FLOWER_FOREST.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSPyramidsConfig.pyramidFlowerForestAverageChunkDistance.get(), 0.5f, 1984904323));

        addToStructureMaps(SHIPWRECK_END.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSShipwrecksConfig.shipwreckEndAverageChunkDistance.get(), 0.5f, 1605500075));
        addToStructureMaps(SHIPWRECK_NETHER_BRICKS.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSShipwrecksConfig.shipwreckNetherBricksAverageChunkDistance.get(), 0.5f, 2073308006));
        addToStructureMaps(SHIPWRECK_CRIMSON.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSShipwrecksConfig.shipwreckCrimsonAverageChunkDistance.get(), 0.5f, 1019716871));
        addToStructureMaps(SHIPWRECK_WARPED.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSShipwrecksConfig.shipwreckWarpedAverageChunkDistance.get(), 0.5f, 2072979641));

        addToTerraformingAndStructureMaps(VILLAGE_BADLANDS.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSVillagesConfig.villageBadlandsAverageChunkDistance.get(), 0.5f, 1319707555));
        addToTerraformingAndStructureMaps(VILLAGE_BIRCH.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSVillagesConfig.villageBirchAverageChunkDistance.get(), 0.5f, 1102567365));
        addToTerraformingAndStructureMaps(VILLAGE_DARK_FOREST.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSVillagesConfig.villageDarkForestAverageChunkDistance.get(), 0.5f, 1921339358));
        addToTerraformingAndStructureMaps(VILLAGE_JUNGLE.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSVillagesConfig.villageJungleAverageChunkDistance.get(), 0.5f, 1229975218));
        addToTerraformingAndStructureMaps(VILLAGE_SWAMP.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSVillagesConfig.villageSwampAverageChunkDistance.get(), 0.5f, 1559650945));
        addToTerraformingAndStructureMaps(VILLAGE_MOUNTAINS.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSVillagesConfig.villageMountainsAverageChunkDistance.get(), 0.5f, 2010875989));
        addToTerraformingAndStructureMaps(VILLAGE_GIANT_TAIGA.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSVillagesConfig.villageGiantTaigaAverageChunkDistance.get(), 0.5f, 1559528842));
        addToTerraformingAndStructureMaps(VILLAGE_CRIMSON.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSVillagesConfig.villageCrimsonAverageChunkDistance.get(), 0.5f, 1854750198));
        addToTerraformingAndStructureMaps(VILLAGE_WARPED.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSVillagesConfig.villageWarpedAverageChunkDistance.get(), 0.5f, 1298332136));
        addToTerraformingAndStructureMaps(VILLAGE_OAK.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSVillagesConfig.villageOakAverageChunkDistance.get(), 0.5f, 2112891039));
        addToTerraformingAndStructureMaps(VILLAGE_MUSHROOM.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSVillagesConfig.villageMushroomAverageChunkDistance.get(), 0.5f, 1150624897));
        // regexpos2

        addToStructureMaps(RUINED_PORTAL_END.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSRuinedPortalsConfig.ruinedPortalEndAverageChunkDistance.get(), 0.5f, 532404086));
        addToTerraformingAndStructureMaps(RUINS_NETHER.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSRuinsConfig.ruinsNetherAverageChunkDistance.get(), 0.5f, 1336047555));
        addToTerraformingAndStructureMaps(RUINS_LAND_WARM.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSRuinsConfig.ruinsLandWarmAverageChunkDistance.get(), 0.25f, 18646107));
        addToTerraformingAndStructureMaps(RUINS_LAND_HOT.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSRuinsConfig.ruinsLandHotAverageChunkDistance.get(), 0.25f, 1243670027));
        addToTerraformingAndStructureMaps(RUINS_LAND_COLD.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSRuinsConfig.ruinsLandColdAverageChunkDistance.get(), 0.25f, 1065432023));
        addToTerraformingAndStructureMaps(RUINS_LAND_ICY.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSRuinsConfig.ruinsLandIcyAverageChunkDistance.get(), 0.25f, 298896106));

        addToTerraformingAndStructureMaps(CITY_NETHER.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSCitiesConfig.citiesNetherAverageChunkDistance.get(), 0.5f, 2082652405));
        addToStructureMaps(CITY_OVERWORLD.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSCitiesConfig.citiesOverworldAverageChunkDistance.get(), 0.5f, 1225875111));

        addToStructureMaps(MANSION_BIRCH.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSMansionsConfig.mansionBirchAverageChunkDistance.get(), 0.5f, 182367035));
        addToStructureMaps(MANSION_JUNGLE.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSMansionsConfig.mansionJungleAverageChunkDistance.get(), 0.5f, 1267916621));
        addToStructureMaps(MANSION_OAK.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSMansionsConfig.mansionOakAverageChunkDistance.get(), 0.5f, 147853731));
        addToStructureMaps(MANSION_SAVANNA.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSMansionsConfig.mansionSavannaAverageChunkDistance.get(), 0.5f, 2024558925));
        addToStructureMaps(MANSION_TAIGA.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSMansionsConfig.mansionTaigaAverageChunkDistance.get(), 0.5f, 418506505));
        addToStructureMaps(MANSION_DESERT.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSMansionsConfig.mansionDesertAverageChunkDistance.get(), 0.5f, 724317387));
        addToStructureMaps(MANSION_SNOWY.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSMansionsConfig.mansionSnowyAverageChunkDistance.get(), 0.5f, 1115107889));

        addToStructureMaps(WITCH_HUTS_OAK.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSWitchHutsConfig.witchHutsOakAverageChunkDistance.get(), 0.5f, 741641348));
        addToStructureMaps(WITCH_HUTS_TAIGA.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSWitchHutsConfig.witchHutsTaigaAverageChunkDistance.get(), 0.5f, 1925189659));
        addToStructureMaps(WITCH_HUTS_BIRCH.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSWitchHutsConfig.witchHutsBirchAverageChunkDistance.get(), 0.5f, 904634508));
        addToStructureMaps(WITCH_HUTS_DARK_FOREST.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSWitchHutsConfig.witchHutsDarkForestAverageChunkDistance.get(), 0.5f, 165100151));
        addToStructureMaps(WITCH_HUTS_GIANT_TREE_TAIGA.get(), GenerationStep.Decoration.SURFACE_STRUCTURES, createSpacingAndSalt(RSWitchHutsConfig.witchHutsGiantTreeTaigaAverageChunkDistance.get(), 0.5f, 200289401));

        addToStructureMaps(BASTION_UNDERGROUND.get(), GenerationStep.Decoration.UNDERGROUND_STRUCTURES, createSpacingAndSalt(RSBastionsConfig.bastionUndergroundAverageChunkDistance.get(), 0.5f, 1359301629));

        //registers the structure pieces.
        RSStructurePieces.registerStructurePieces();
    }

    private static StructureFeatureConfiguration createSpacingAndSalt(int averageSpacing, float percentageForMinSpacing, int salt){
        return new StructureFeatureConfiguration(averageSpacing, (int) (averageSpacing * percentageForMinSpacing), salt);
    }

    public static <F extends StructureFeature<NoneFeatureConfiguration>> void addToTerraformingAndStructureMaps(F structure, GenerationStep.Decoration stage, StructureFeatureConfiguration StructureFeatureConfiguration) {
        StructureFeatureAccessor.setNOISE_AFFECTING_FEATURES(ImmutableList.<StructureFeature<?>>builder().addAll(StructureFeature.NOISE_AFFECTING_FEATURES).add(structure).build());
        addToStructureMaps(structure, stage, StructureFeatureConfiguration);
    }

    public static <F extends StructureFeature<NoneFeatureConfiguration>> void addToStructureMaps(F structure, GenerationStep.Decoration stage, StructureFeatureConfiguration StructureFeatureConfiguration) {
        StructureFeature.STRUCTURES_REGISTRY.put(Objects.requireNonNull(structure.getRegistryName()).toString().toLowerCase(Locale.ROOT), structure);

        // This is only for myself. Others should override step() in their structure's class to return their generation stage instead.
        StructureFeatureAccessor.getSTEP().put(structure, stage);

        StructureSettingsAccessor.setDEFAULTS(ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder().putAll(StructureSettings.DEFAULTS).put(structure, StructureFeatureConfiguration).build());
        RS_STRUCTURES.put(structure, StructureFeatureConfiguration);
    }
}
