package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.world.structures.*;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSMineshaftPieces;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePieces;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.*;


public class RSStructures {
    public static final Map<StructureFeature<?>, StructureConfig> RS_STRUCTURES = new HashMap<>();
    public static final Set<Identifier> RS_STRUCTURE_START_PIECES = new HashSet<>();

    public static StructureFeature<DefaultFeatureConfig> BIRCH_MINESHAFT = new RSMineshaftStructure(RSMineshaftPieces.Type.BIRCH, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.birchMineshaftSpawnrate, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.maxHeight.birchMineshaftMaxHeight, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.minHeight.birchMineshaftMinHeight);
    public static StructureFeature<DefaultFeatureConfig> DESERT_MINESHAFT = new RSMineshaftStructure(RSMineshaftPieces.Type.DESERT, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.desertMineshaftSpawnrate, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.maxHeight.desertMineshaftMaxHeight, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.minHeight.desertMineshaftMinHeight);
    public static StructureFeature<DefaultFeatureConfig> END_MINESHAFT = new RSMineshaftEndStructure(RSMineshaftPieces.Type.END, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.endMineshaftSpawnrate, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.maxHeight.endMineshaftMaxHeight, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.minHeight.endMineshaftMinHeight);
    public static StructureFeature<DefaultFeatureConfig> NETHER_MINESHAFT = new RSMineshaftStructure(RSMineshaftPieces.Type.NETHER, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.netherMineshaftSpawnrate, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.maxHeight.netherMineshaftMaxHeight, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.minHeight.netherMineshaftMinHeight);
    public static StructureFeature<DefaultFeatureConfig> ICY_MINESHAFT = new RSMineshaftStructure(RSMineshaftPieces.Type.ICY, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.icyMineshaftSpawnrate, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.maxHeight.icyMineshaftMaxHeight, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.minHeight.icyMineshaftMinHeight);
    public static StructureFeature<DefaultFeatureConfig> JUNGLE_MINESHAFT = new RSMineshaftStructure(RSMineshaftPieces.Type.JUNGLE, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.jungleMineshaftSpawnrate, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.maxHeight.jungleMineshaftMaxHeight, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.minHeight.jungleMineshaftMinHeight);
    public static StructureFeature<DefaultFeatureConfig> OCEAN_MINESHAFT = new RSMineshaftStructure(RSMineshaftPieces.Type.OCEAN, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.oceanMineshaftSpawnrate, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.maxHeight.oceanMineshaftMaxHeight, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.minHeight.oceanMineshaftMinHeight);
    public static StructureFeature<DefaultFeatureConfig> SAVANNA_MINESHAFT = new RSMineshaftStructure(RSMineshaftPieces.Type.SAVANNA, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.savannaMineshaftSpawnrate, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.maxHeight.savannaMineshaftMaxHeight, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.minHeight.savannaMineshaftMinHeight);
    public static StructureFeature<DefaultFeatureConfig> STONE_MINESHAFT = new RSMineshaftStructure(RSMineshaftPieces.Type.STONE, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.stoneMineshaftSpawnrate, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.maxHeight.stoneMineshaftMaxHeight, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.minHeight.stoneMineshaftMinHeight);
    public static StructureFeature<DefaultFeatureConfig> SWAMP_OR_DARK_FOREST_MINESHAFT = new RSMineshaftStructure(RSMineshaftPieces.Type.SWAMPORDARKFOREST, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.swampAndDarkForestMineshaftSpawnrate, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.maxHeight.swampAndDarkForestMineshaftMaxHeight, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.minHeight.swampAndDarkForestMineshaftMinHeight);
    public static StructureFeature<DefaultFeatureConfig> TAIGA_MINESHAFT = new RSMineshaftStructure(RSMineshaftPieces.Type.TAIGA, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.taigaMineshaftSpawnrate, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.maxHeight.taigaMineshaftMaxHeight, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.minHeight.taigaMineshaftMinHeight);

    public static StructureFeature<DefaultFeatureConfig> STONEBRICK_STRONGHOLD = new RSStonebrickStrongholdStructure();
    public static StructureFeature<DefaultFeatureConfig> NETHER_STRONGHOLD = new RSNetherStrongholdStructure();

    public static StructureFeature<DefaultFeatureConfig> JUNGLE_FORTRESS = new FortressJungleStructure();
    public static StructureFeature<DefaultFeatureConfig> GRASSY_IGLOO = new VillageBaseStructure(new Identifier(RepurposedStructures.MODID, "igloos/grassy_top"), 3);
    public static StructureFeature<DefaultFeatureConfig> STONE_IGLOO = new RSIglooStructure(new Identifier(RepurposedStructures.MODID, "igloos/stone_top"));

    public static StructureFeature<DefaultFeatureConfig> NETHER_WASTELAND_TEMPLE = new TempleNetherStructure(new Identifier(RepurposedStructures.MODID,"temples/temple_nether_wasteland"));
    public static StructureFeature<DefaultFeatureConfig> NETHER_BASALT_TEMPLE = new TempleNetherStructure(new Identifier(RepurposedStructures.MODID,"temples/temple_nether_basalt"));
    public static StructureFeature<DefaultFeatureConfig> NETHER_WARPED_TEMPLE = new TempleNetherStructure(new Identifier(RepurposedStructures.MODID,"temples/temple_nether_warped"));
    public static StructureFeature<DefaultFeatureConfig> NETHER_CRIMSON_TEMPLE = new TempleNetherStructure(new Identifier(RepurposedStructures.MODID,"temples/temple_nether_crimson"));
    public static StructureFeature<DefaultFeatureConfig> NETHER_SOUL_TEMPLE = new TempleNetherStructure(new Identifier(RepurposedStructures.MODID,"temples/temple_nether_soul"));

    public static StructureFeature<DefaultFeatureConfig> NETHER_BRICK_OUTPOST = new OutpostNetherStructure(new Identifier(RepurposedStructures.MODID,"outposts/nether_brick/base_plates"));
    public static StructureFeature<DefaultFeatureConfig> WARPED_OUTPOST = new OutpostNetherStructure(new Identifier(RepurposedStructures.MODID,"outposts/warped/base_plates"));
    public static StructureFeature<DefaultFeatureConfig> CRIMSON_OUTPOST = new OutpostNetherStructure(new Identifier(RepurposedStructures.MODID,"outposts/crimson/base_plates"));

    public static StructureFeature<DefaultFeatureConfig> NETHER_PYRAMID = new PyramidNetherStructure();
    public static StructureFeature<DefaultFeatureConfig> BADLANDS_TEMPLE = new PyramidBadlandsStructure();

    public static StructureFeature<DefaultFeatureConfig> END_SHIPWRECK = new ShipwreckEndStructure();
    public static StructureFeature<DefaultFeatureConfig> NETHER_BRICKS_SHIPWRECK = new ShipwreckNetherStructure(new Identifier(RepurposedStructures.MODID, "shipwrecks/nether_bricks"), false);
    public static StructureFeature<DefaultFeatureConfig> CRIMSON_SHIPWRECK = new ShipwreckNetherStructure(new Identifier(RepurposedStructures.MODID, "shipwrecks/crimson"), true);
    public static StructureFeature<DefaultFeatureConfig> WARPED_SHIPWRECK = new ShipwreckNetherStructure(new Identifier(RepurposedStructures.MODID, "shipwrecks/warped"), true);

    public static StructureFeature<DefaultFeatureConfig> BADLANDS_VILLAGE = new VillageBaseStructure(new Identifier(RepurposedStructures.MODID, "village/badlands/town_centers"), 10);
    public static StructureFeature<DefaultFeatureConfig> BIRCH_VILLAGE = new VillageBaseStructure(new Identifier(RepurposedStructures.MODID, "village/birch/town_centers"), 6);
    public static StructureFeature<DefaultFeatureConfig> DARK_FOREST_VILLAGE = new VillageBaseStructure(new Identifier(RepurposedStructures.MODID, "village/dark_forest/town_centers"), 6);
    public static StructureFeature<DefaultFeatureConfig> JUNGLE_VILLAGE = new VillageBaseStructure(new Identifier(RepurposedStructures.MODID, "village/jungle/town_centers"), 8);
    public static StructureFeature<DefaultFeatureConfig> SWAMP_VILLAGE = new VillageSwampStructure(new Identifier(RepurposedStructures.MODID, "village/swamp/town_centers"), 6);
    public static StructureFeature<DefaultFeatureConfig> MOUNTAINS_VILLAGE = new VillageBaseStructure(new Identifier(RepurposedStructures.MODID, "village/mountains/town_centers"), 6);
    public static StructureFeature<DefaultFeatureConfig> GIANT_TAIGA_VILLAGE = new VillageBaseStructure(new Identifier(RepurposedStructures.MODID, "village/giant_taiga/town_centers"), 6);
    public static StructureFeature<DefaultFeatureConfig> CRIMSON_VILLAGE = new VillageNetherStructure(new Identifier(RepurposedStructures.MODID, "village/crimson/town_centers"), 6);
    public static StructureFeature<DefaultFeatureConfig> WARPED_VILLAGE = new VillageNetherStructure(new Identifier(RepurposedStructures.MODID, "village/warped/town_centers"), 6);

    public static List<StructureFeature<DefaultFeatureConfig>> OVERWORLD_VILLAGE_LIST = Arrays.asList(
            BADLANDS_VILLAGE,
            BIRCH_VILLAGE,
            DARK_FOREST_VILLAGE,
            JUNGLE_VILLAGE,
            SWAMP_VILLAGE,
            MOUNTAINS_VILLAGE,
            GIANT_TAIGA_VILLAGE
    );
    public static List<StructureFeature<DefaultFeatureConfig>> NETHER_VILLAGE_LIST = Arrays.asList(
            CRIMSON_VILLAGE,
            WARPED_VILLAGE
    );
    public static List<StructureFeature<?>> NETHER_OUTPOSTS_LIST = Arrays.asList(
            NETHER_BRICK_OUTPOST,
            WARPED_OUTPOST,
            CRIMSON_OUTPOST
    );
    public static List<StructureFeature<?>> NETHER_TEMPLE_LIST = Arrays.asList(
            NETHER_WASTELAND_TEMPLE,
            NETHER_BASALT_TEMPLE,
            NETHER_WARPED_TEMPLE,
            NETHER_CRIMSON_TEMPLE,
            NETHER_SOUL_TEMPLE,
            NETHER_PYRAMID
    );
    public static List<StructureFeature<?>> NETHER_SHIPWRECKS_LIST = Arrays.asList(
            NETHER_BRICKS_SHIPWRECK,
            WARPED_SHIPWRECK,
            CRIMSON_SHIPWRECK
    );
    public static List<StructureFeature<?>> LARGE_VANILLA_NETHER_STRUCTURE_LIST = Arrays.asList(
            StructureFeature.FORTRESS,
            StructureFeature.BASTION_REMNANT
    );


    public static void registerStructures() {

        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "mineshaft_birch"), BIRCH_MINESHAFT).step(GenerationStep.Feature.UNDERGROUND_STRUCTURES).defaultConfig(new StructureConfig(1, 0, 399117345)).superflatFeature(BIRCH_MINESHAFT.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "mineshaft_desert"), DESERT_MINESHAFT).step(GenerationStep.Feature.UNDERGROUND_STRUCTURES).defaultConfig(new StructureConfig(1, 0, 1990612785)).superflatFeature(DESERT_MINESHAFT.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "mineshaft_end"), END_MINESHAFT).step(GenerationStep.Feature.UNDERGROUND_STRUCTURES).defaultConfig(new StructureConfig(1, 0, 2057488602)).superflatFeature(END_MINESHAFT.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "mineshaft_nether"), NETHER_MINESHAFT).step(GenerationStep.Feature.UNDERGROUND_STRUCTURES).defaultConfig(new StructureConfig(1, 0, 1220811654)).superflatFeature(NETHER_MINESHAFT.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "mineshaft_icy"), ICY_MINESHAFT).step(GenerationStep.Feature.UNDERGROUND_STRUCTURES).defaultConfig(new StructureConfig(1, 0, 1451015246)).superflatFeature(ICY_MINESHAFT.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "mineshaft_jungle"), JUNGLE_MINESHAFT).step(GenerationStep.Feature.UNDERGROUND_STRUCTURES).defaultConfig(new StructureConfig(1, 0, 1434412876)).superflatFeature(JUNGLE_MINESHAFT.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "mineshaft_ocean"), OCEAN_MINESHAFT).step(GenerationStep.Feature.UNDERGROUND_STRUCTURES).defaultConfig(new StructureConfig(1, 0, 1774808662)).superflatFeature(OCEAN_MINESHAFT.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "mineshaft_savanna"), SAVANNA_MINESHAFT).step(GenerationStep.Feature.UNDERGROUND_STRUCTURES).defaultConfig(new StructureConfig(1, 0, 1960212212)).superflatFeature(SAVANNA_MINESHAFT.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "mineshaft_stone"), STONE_MINESHAFT).step(GenerationStep.Feature.UNDERGROUND_STRUCTURES).defaultConfig(new StructureConfig(1, 0, 1436736620)).superflatFeature(STONE_MINESHAFT.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "mineshaft_swamp_or_dark_forest"), SWAMP_OR_DARK_FOREST_MINESHAFT).step(GenerationStep.Feature.UNDERGROUND_STRUCTURES).defaultConfig(new StructureConfig(1, 0, 2037177700)).superflatFeature(SWAMP_OR_DARK_FOREST_MINESHAFT.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "mineshaft_taiga"), TAIGA_MINESHAFT).step(GenerationStep.Feature.UNDERGROUND_STRUCTURES).defaultConfig(new StructureConfig(1, 0, 1383003172)).superflatFeature(TAIGA_MINESHAFT.configure(FeatureConfig.DEFAULT)).register();

        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "stronghold_stonebrick"), STONEBRICK_STRONGHOLD).step(GenerationStep.Feature.UNDERGROUND_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdSpawnrate * 0.5f), 1098192663)).superflatFeature(STONEBRICK_STRONGHOLD.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "stronghold_nether"), NETHER_STRONGHOLD).step(GenerationStep.Feature.UNDERGROUND_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSpawnrate * 0.5f), 1731422513)).superflatFeature(NETHER_STRONGHOLD.configure(FeatureConfig.DEFAULT)).register();

        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "fortress_jungle"), JUNGLE_FORTRESS).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.jungleFortressSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.jungleFortressSpawnrate * 0.5f), 1464189157)).superflatFeature(JUNGLE_FORTRESS.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "igloo_grassy"), GRASSY_IGLOO).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSMainConfig.igloos.grassyIglooSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSMainConfig.igloos.grassyIglooSpawnrate * 0.5f), 1460835582)).superflatFeature(GRASSY_IGLOO.configure(FeatureConfig.DEFAULT)).adjustsSurface().register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "igloo_stone"), STONE_IGLOO).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSMainConfig.igloos.stoneIglooSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSMainConfig.igloos.stoneIglooSpawnrate * 0.5f), 1327429039)).superflatFeature(STONE_IGLOO.configure(FeatureConfig.DEFAULT)).register();

        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "temple_nether_wasteland"), NETHER_WASTELAND_TEMPLE).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWastelandTempleSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWastelandTempleSpawnrate * 0.5f), 1435489909)).superflatFeature(NETHER_WASTELAND_TEMPLE.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "temple_nether_soul"), NETHER_SOUL_TEMPLE).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherSoulTempleSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherSoulTempleSpawnrate * 0.5f), 1799485937)).superflatFeature(NETHER_SOUL_TEMPLE.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "temple_nether_basalt"), NETHER_BASALT_TEMPLE).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherBasaltTempleSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherBasaltTempleSpawnrate * 0.5f), 1063117750)).superflatFeature(NETHER_BASALT_TEMPLE.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "temple_nether_crimson"), NETHER_CRIMSON_TEMPLE).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherCrimsonTempleSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherCrimsonTempleSpawnrate * 0.5f), 1898896156)).superflatFeature(NETHER_CRIMSON_TEMPLE.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "temple_nether_warped"), NETHER_WARPED_TEMPLE).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWarpedTempleSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWarpedTempleSpawnrate * 0.5f), 1635542708)).superflatFeature(NETHER_WARPED_TEMPLE.configure(FeatureConfig.DEFAULT)).register();

        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "outpost_nether_brick"), NETHER_BRICK_OUTPOST).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.netherBrickOutpostSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.netherBrickOutpostSpawnrate * 0.5f), 1305971394)).superflatFeature(NETHER_BRICK_OUTPOST.configure(FeatureConfig.DEFAULT)).adjustsSurface().register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "outpost_warped"), WARPED_OUTPOST).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.warpedOutpostSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.warpedOutpostSpawnrate * 0.5f), 1928816918)).superflatFeature(WARPED_OUTPOST.configure(FeatureConfig.DEFAULT)).adjustsSurface().register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "outpost_crimson"), CRIMSON_OUTPOST).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.crimsonOutpostSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.crimsonOutpostSpawnrate * 0.5f), 1951425662)).superflatFeature(CRIMSON_OUTPOST.configure(FeatureConfig.DEFAULT)).adjustsSurface().register();

        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "pyramid_badlands"), BADLANDS_TEMPLE).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.badlandsPyramidSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.badlandsPyramidSpawnrate * 0.5f), 1718729448)).superflatFeature(BADLANDS_TEMPLE.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "pyramid_nether"), NETHER_PYRAMID).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.netherPyramidSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.netherPyramidSpawnrate * 0.5f), 2054372964)).superflatFeature(NETHER_PYRAMID.configure(FeatureConfig.DEFAULT)).adjustsSurface().register();

        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "shipwreck_end"), END_SHIPWRECK).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSShipwrecksConfig.spawnrate.endShipwreckSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.spawnrate.endShipwreckSpawnrate * 0.5f), 1605500075)).superflatFeature(END_SHIPWRECK.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "shipwreck_nether_bricks"), NETHER_BRICKS_SHIPWRECK).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSShipwrecksConfig.spawnrate.netherBricksShipwreckSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.spawnrate.netherBricksShipwreckSpawnrate * 0.5f), 2073308006)).superflatFeature(NETHER_BRICKS_SHIPWRECK.configure(FeatureConfig.DEFAULT)).register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "shipwreck_crimson"), CRIMSON_SHIPWRECK).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSShipwrecksConfig.spawnrate.crimsonShipwreckSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.spawnrate.crimsonShipwreckSpawnrate * 0.5f), 1019716871)).superflatFeature(CRIMSON_SHIPWRECK.configure(FeatureConfig.DEFAULT)).adjustsSurface().register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "shipwreck_warped"), WARPED_SHIPWRECK).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSShipwrecksConfig.spawnrate.warpedShipwreckSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.spawnrate.warpedShipwreckSpawnrate * 0.5f), 2072979641)).superflatFeature(WARPED_SHIPWRECK.configure(FeatureConfig.DEFAULT)).adjustsSurface().register();

        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "village_badlands"), BADLANDS_VILLAGE).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSVillagesConfig.badlandsVillageSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSVillagesConfig.badlandsVillageSpawnrate * 0.5f), 1319707555)).superflatFeature(BADLANDS_VILLAGE.configure(FeatureConfig.DEFAULT)).adjustsSurface().register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "village_birch"), BIRCH_VILLAGE).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSVillagesConfig.birchVillageSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSVillagesConfig.birchVillageSpawnrate * 0.5f), 1102567365)).superflatFeature(BIRCH_VILLAGE.configure(FeatureConfig.DEFAULT)).adjustsSurface().register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "village_dark_oak"), DARK_FOREST_VILLAGE).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSVillagesConfig.darkForestVillageSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSVillagesConfig.darkForestVillageSpawnrate * 0.5f), 1921339358)).superflatFeature(DARK_FOREST_VILLAGE.configure(FeatureConfig.DEFAULT)).adjustsSurface().register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "village_jungle"), JUNGLE_VILLAGE).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSVillagesConfig.jungleVillageSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSVillagesConfig.jungleVillageSpawnrate * 0.5f), 1229975218)).superflatFeature(JUNGLE_VILLAGE.configure(FeatureConfig.DEFAULT)).adjustsSurface().register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "village_swamp"), SWAMP_VILLAGE).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSVillagesConfig.swampVillageSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSVillagesConfig.swampVillageSpawnrate * 0.5f), 1559650945)).superflatFeature(SWAMP_VILLAGE.configure(FeatureConfig.DEFAULT)).adjustsSurface().register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "village_mountains"), MOUNTAINS_VILLAGE).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSVillagesConfig.mountainsVillageSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSVillagesConfig.mountainsVillageSpawnrate * 0.5f), 2010875989)).superflatFeature(MOUNTAINS_VILLAGE.configure(FeatureConfig.DEFAULT)).adjustsSurface().register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "village_giant_taiga"), GIANT_TAIGA_VILLAGE).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSVillagesConfig.giantTaigaVillageSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSVillagesConfig.giantTaigaVillageSpawnrate * 0.5f), 1559528842)).superflatFeature(GIANT_TAIGA_VILLAGE.configure(FeatureConfig.DEFAULT)).adjustsSurface().register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "village_crimson"), CRIMSON_VILLAGE).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSVillagesConfig.crimsonVillageSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSVillagesConfig.crimsonVillageSpawnrate * 0.5f), 1854750198)).superflatFeature(CRIMSON_VILLAGE.configure(FeatureConfig.DEFAULT)).adjustsSurface().register();
        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, "village_warped"), WARPED_VILLAGE).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig.RSVillagesConfig.warpedVillageSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSVillagesConfig.warpedVillageSpawnrate * 0.5f), 1298332136)).superflatFeature(WARPED_VILLAGE.configure(FeatureConfig.DEFAULT)).adjustsSurface().register();

        //Next avaliable seed: https://www.google.com/search?q=random+number
        RS_STRUCTURES.putAll(StructuresConfig.DEFAULT_STRUCTURES);
        RS_STRUCTURES.keySet().removeIf(key -> !key.getName().contains(RepurposedStructures.MODID));

        //registers the structure pieces.
        StructurePieces.registerStructurePieces();
    }
}
