package com.telepathicgrunt.repurposedstructures;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.telepathicgrunt.repurposedstructures.world.structures.*;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSMineshaftPieces;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePieces;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

import java.util.*;


public class RSStructures {
    /**
     * --------------------------------------------------------------------------
     * |															        	|
     * |			HELLO READERS! IF YOU'RE HERE, YOU'RE PROBABLY		    	|
     * |			LOOKING FOR A TUTORIAL ON HOW TO DO STRUCTURES	    		|
     * |																        |
     * -------------------------------------------------------------------------
     *
     * Don't worry, I actually have a structure tutorial
     * mod already setup for you to check out! It's full
     * of comments on what does what and how to make structures.
     *
     * Here's the link! https://github.com/TelepathicGrunt/StructureTutorialMod
     *
     * Good luck and have fun modding!
     */

    public static final Map<Structure<?>, StructureSeparationSettings> RS_STRUCTURES = new HashMap<>();
    public static final Set<ResourceLocation> RS_STRUCTURE_START_PIECES = new HashSet<>();

    public static Structure<NoFeatureConfig> BIRCH_MINESHAFT = new RSMineshaftStructure(NoFeatureConfig.CODEC, RSMineshaftPieces.Type.BIRCH, RepurposedStructures.RSMineshaftsConfig.birchMineshaftSpawnrate.get(),RepurposedStructures.RSMineshaftsConfig.birchMineshaftMaxHeight.get(),RepurposedStructures.RSMineshaftsConfig.birchMineshaftMinHeight.get());
    public static Structure<NoFeatureConfig> DESERT_MINESHAFT = new RSMineshaftStructure(NoFeatureConfig.CODEC, RSMineshaftPieces.Type.DESERT, RepurposedStructures.RSMineshaftsConfig.desertMineshaftSpawnrate.get(),RepurposedStructures.RSMineshaftsConfig.desertMineshaftMaxHeight.get(),RepurposedStructures.RSMineshaftsConfig.desertMineshaftMinHeight.get());
    public static Structure<NoFeatureConfig> END_MINESHAFT = new RSMineshaftEndStructure(NoFeatureConfig.CODEC, RSMineshaftPieces.Type.END, RepurposedStructures.RSMineshaftsConfig.endMineshaftSpawnrate.get(),RepurposedStructures.RSMineshaftsConfig.endMineshaftMaxHeight.get(),RepurposedStructures.RSMineshaftsConfig.endMineshaftMinHeight.get());
    public static Structure<NoFeatureConfig> NETHER_MINESHAFT = new RSMineshaftStructure(NoFeatureConfig.CODEC, RSMineshaftPieces.Type.NETHER, RepurposedStructures.RSMineshaftsConfig.netherMineshaftSpawnrate.get(),RepurposedStructures.RSMineshaftsConfig.netherMineshaftMaxHeight.get(),RepurposedStructures.RSMineshaftsConfig.netherMineshaftMinHeight.get());
    public static Structure<NoFeatureConfig> ICY_MINESHAFT = new RSMineshaftStructure(NoFeatureConfig.CODEC, RSMineshaftPieces.Type.ICY, RepurposedStructures.RSMineshaftsConfig.icyMineshaftSpawnrate.get(),RepurposedStructures.RSMineshaftsConfig.icyMineshaftMaxHeight.get(),RepurposedStructures.RSMineshaftsConfig.icyMineshaftMinHeight.get());
    public static Structure<NoFeatureConfig> JUNGLE_MINESHAFT = new RSMineshaftStructure(NoFeatureConfig.CODEC, RSMineshaftPieces.Type.JUNGLE, RepurposedStructures.RSMineshaftsConfig.jungleMineshaftSpawnrate.get(),RepurposedStructures.RSMineshaftsConfig.jungleMineshaftMaxHeight.get(),RepurposedStructures.RSMineshaftsConfig.jungleMineshaftMinHeight.get());
    public static Structure<NoFeatureConfig> OCEAN_MINESHAFT = new RSMineshaftStructure(NoFeatureConfig.CODEC, RSMineshaftPieces.Type.OCEAN, RepurposedStructures.RSMineshaftsConfig.oceanMineshaftSpawnrate.get(),RepurposedStructures.RSMineshaftsConfig.oceanMineshaftMaxHeight.get(),RepurposedStructures.RSMineshaftsConfig.oceanMineshaftMinHeight.get());
    public static Structure<NoFeatureConfig> SAVANNA_MINESHAFT = new RSMineshaftStructure(NoFeatureConfig.CODEC, RSMineshaftPieces.Type.SAVANNA, RepurposedStructures.RSMineshaftsConfig.savannaMineshaftSpawnrate.get(),RepurposedStructures.RSMineshaftsConfig.savannaMineshaftMaxHeight.get(),RepurposedStructures.RSMineshaftsConfig.savannaMineshaftMinHeight.get());
    public static Structure<NoFeatureConfig> STONE_MINESHAFT = new RSMineshaftStructure(NoFeatureConfig.CODEC, RSMineshaftPieces.Type.STONE, RepurposedStructures.RSMineshaftsConfig.stoneMineshaftSpawnrate.get(),RepurposedStructures.RSMineshaftsConfig.stoneMineshaftMaxHeight.get(),RepurposedStructures.RSMineshaftsConfig.stoneMineshaftMinHeight.get());
    public static Structure<NoFeatureConfig> SWAMP_OR_DARK_FOREST_MINESHAFT = new RSMineshaftStructure(NoFeatureConfig.CODEC, RSMineshaftPieces.Type.SWAMPORDARKFOREST, RepurposedStructures.RSMineshaftsConfig.swampAndDarkForestMineshaftSpawnrate.get(),RepurposedStructures.RSMineshaftsConfig.swampAndDarkForestMineshaftMaxHeight.get(),RepurposedStructures.RSMineshaftsConfig.swampAndDarkForestMineshaftMinHeight.get());
    public static Structure<NoFeatureConfig> TAIGA_MINESHAFT = new RSMineshaftStructure(NoFeatureConfig.CODEC, RSMineshaftPieces.Type.TAIGA, RepurposedStructures.RSMineshaftsConfig.taigaMineshaftSpawnrate.get(),RepurposedStructures.RSMineshaftsConfig.taigaMineshaftMaxHeight.get(),RepurposedStructures.RSMineshaftsConfig.taigaMineshaftMinHeight.get());

    public static Structure<NoFeatureConfig> STONEBRICK_STRONGHOLD = new RSStonebrickStrongholdStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> NETHER_STRONGHOLD = new RSNetherStrongholdStructure(NoFeatureConfig.CODEC);

    public static Structure<NoFeatureConfig> JUNGLE_FORTRESS = new FortressJungleStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> GRASSY_IGLOO = new VillageBaseStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID + ":igloos/grassy_top"), 3);
    public static Structure<NoFeatureConfig> STONE_IGLOO = new VillageBaseStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID + ":igloos/stone_top"), 3);

    public static Structure<NoFeatureConfig> NETHER_WASTELAND_TEMPLE = new TempleNetherStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_wasteland"));
    public static Structure<NoFeatureConfig> NETHER_BASALT_TEMPLE = new TempleNetherStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_basalt"));
    public static Structure<NoFeatureConfig> NETHER_WARPED_TEMPLE = new TempleNetherStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_warped"));
    public static Structure<NoFeatureConfig> NETHER_CRIMSON_TEMPLE = new TempleNetherStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_crimson"));
    public static Structure<NoFeatureConfig> NETHER_SOUL_TEMPLE = new TempleNetherStructure(NoFeatureConfig.CODEC,new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_soul"));

    public static Structure<NoFeatureConfig> NETHER_BRICK_OUTPOST = new OutpostNetherStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID,"outposts/nether_brick/base_plates"));
    public static Structure<NoFeatureConfig> WARPED_OUTPOST = new OutpostNetherStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID,"outposts/warped/base_plates"));
    public static Structure<NoFeatureConfig> CRIMSON_OUTPOST = new OutpostNetherStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID,"outposts/crimson/base_plates"));

    public static Structure<NoFeatureConfig> NETHER_PYRAMID = new PyramidNetherStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> BADLANDS_TEMPLE = new PyramidBadlandsStructure(NoFeatureConfig.CODEC);

    public static Structure<NoFeatureConfig> END_SHIPWRECK = new ShipwreckEndStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> NETHER_BRICKS_SHIPWRECK = new ShipwreckNetherStructure(new ResourceLocation(RepurposedStructures.MODID, "shipwrecks/nether_bricks"), false);
    public static Structure<NoFeatureConfig> CRIMSON_SHIPWRECK = new ShipwreckNetherStructure(new ResourceLocation(RepurposedStructures.MODID, "shipwrecks/crimson"), true);
    public static Structure<NoFeatureConfig> WARPED_SHIPWRECK = new ShipwreckNetherStructure(new ResourceLocation(RepurposedStructures.MODID, "shipwrecks/warped"), true);


    public static Structure<NoFeatureConfig> BADLANDS_VILLAGE = new VillageBaseStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID + ":village/badlands/town_centers"), 10);
    public static Structure<NoFeatureConfig> BIRCH_VILLAGE = new VillageBaseStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID + ":village/birch/town_centers"), 6);
    public static Structure<NoFeatureConfig> DARK_FOREST_VILLAGE = new VillageBaseStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID + ":village/dark_forest/town_centers"), 6);
    public static Structure<NoFeatureConfig> JUNGLE_VILLAGE = new VillageBaseStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID + ":village/jungle/town_centers"), 8);
    public static Structure<NoFeatureConfig> SWAMP_VILLAGE = new VillageSwampStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID + ":village/swamp/town_centers"), 6);
    public static Structure<NoFeatureConfig> MOUNTAINS_VILLAGE = new VillageBaseStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID + ":village/mountains/town_centers"), 6);
    public static Structure<NoFeatureConfig> GIANT_TAIGA_VILLAGE = new VillageBaseStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID + ":village/giant_taiga/town_centers"), 6);
    public static Structure<NoFeatureConfig> CRIMSON_VILLAGE = new VillageNetherStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID + ":village/crimson/town_centers"), 6);
    public static Structure<NoFeatureConfig> WARPED_VILLAGE = new VillageNetherStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID + ":village/warped/town_centers"), 6);
    public static List<Structure<?>> OVERWORLD_VILLAGE_LIST = Arrays.asList(
            BADLANDS_VILLAGE,
            BIRCH_VILLAGE,
            DARK_FOREST_VILLAGE,
            JUNGLE_VILLAGE,
            SWAMP_VILLAGE,
            MOUNTAINS_VILLAGE,
            GIANT_TAIGA_VILLAGE
    );
    public static List<Structure<?>> NETHER_VILLAGE_LIST = Arrays.asList(
            CRIMSON_VILLAGE,
            WARPED_VILLAGE
    );
    public static List<Structure<?>> NETHER_OUTPOSTS_LIST = Arrays.asList(
            NETHER_BRICK_OUTPOST,
            WARPED_OUTPOST,
            CRIMSON_OUTPOST
    );
    public static List<Structure<?>> NETHER_TEMPLE_LIST = Arrays.asList(
            NETHER_WASTELAND_TEMPLE,
            NETHER_BASALT_TEMPLE,
            NETHER_WARPED_TEMPLE,
            NETHER_CRIMSON_TEMPLE,
            NETHER_SOUL_TEMPLE,
            NETHER_PYRAMID
    );
    public static List<Structure<?>> NETHER_SHIPWRECKS_LIST = Arrays.asList(
            NETHER_BRICKS_SHIPWRECK,
            WARPED_SHIPWRECK,
            CRIMSON_SHIPWRECK
    );
    public static List<Structure<?>> LARGE_VANILLA_NETHER_STRUCTURE_LIST = Arrays.asList(
            Structure.FORTRESS,
            Structure.BASTION_REMNANT
    );

    public static void registerStructures() {

        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_birch"), BIRCH_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 399117345));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_desert"), DESERT_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1990612785));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_end"), END_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 2057488602));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_nether"), NETHER_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1220811654));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_icy"), ICY_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1451015246));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_jungle"), JUNGLE_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1434412876));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_ocean"), OCEAN_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1774808662));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_savanna"), SAVANNA_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1960212212));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_stone"), STONE_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1436736620));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_swamp_or_dark_forest"), SWAMP_OR_DARK_FOREST_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 2037177700));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_taiga"), TAIGA_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 1383003172));

        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "stronghold_stonebrick"), STONEBRICK_STRONGHOLD, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdSpawnrate.get(), (int) (RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdSpawnrate.get() * 0.5f), 1098192663));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "stronghold_nether"), NETHER_STRONGHOLD, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSStrongholdsConfig.netherStrongholdSpawnrate.get(), (int) (RepurposedStructures.RSStrongholdsConfig.netherStrongholdSpawnrate.get() * 0.5f), 1731422513));

        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "fortress_jungle"), JUNGLE_FORTRESS, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMainConfig.jungleFortressSpawnrate.get(), (int) (RepurposedStructures.RSMainConfig.jungleFortressSpawnrate.get() * 0.5f), 1464189157));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "igloo_grassy"), GRASSY_IGLOO, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMainConfig.grassyIglooSpawnrate.get(), (int) (RepurposedStructures.RSMainConfig.grassyIglooSpawnrate.get() * 0.5f), 1460835582));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "igloo_stone"), STONE_IGLOO, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMainConfig.stoneIglooSpawnrate.get(), (int) (RepurposedStructures.RSMainConfig.stoneIglooSpawnrate.get() * 0.5f), 1327429039));

        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_wasteland"), NETHER_WASTELAND_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.netherWastelandTempleSpawnrate.get(), (int) (RepurposedStructures.RSTemplesConfig.netherWastelandTempleSpawnrate.get() * 0.5f), 1435489909));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_soul"), NETHER_SOUL_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.netherSoulTempleSpawnrate.get(), (int) (RepurposedStructures.RSTemplesConfig.netherSoulTempleSpawnrate.get() * 0.5f), 1799485937));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_basalt"), NETHER_BASALT_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.netherBasaltTempleSpawnrate.get(), (int) (RepurposedStructures.RSTemplesConfig.netherBasaltTempleSpawnrate.get() * 0.5f), 1063117750));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_crimson"), NETHER_CRIMSON_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.netherCrimsonTempleSpawnrate.get(), (int) (RepurposedStructures.RSTemplesConfig.netherCrimsonTempleSpawnrate.get() * 0.5f), 1898896156));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_warped"), NETHER_WARPED_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.netherWarpedTempleSpawnrate.get(), (int) (RepurposedStructures.RSTemplesConfig.netherWarpedTempleSpawnrate.get() * 0.5f), 1635542708));

        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "outpost_nether_brick"), NETHER_BRICK_OUTPOST, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSOutpostsConfig.netherBrickOutpostSpawnrate.get(), (int) (RepurposedStructures.RSOutpostsConfig.netherBrickOutpostSpawnrate.get() * 0.5f), 1305971394));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "outpost_warped"), WARPED_OUTPOST, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSOutpostsConfig.warpedOutpostSpawnrate.get(), (int) (RepurposedStructures.RSOutpostsConfig.warpedOutpostSpawnrate.get() * 0.5f), 1928816918));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "outpost_crimson"), CRIMSON_OUTPOST, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSOutpostsConfig.crimsonOutpostSpawnrate.get(), (int) (RepurposedStructures.RSOutpostsConfig.crimsonOutpostSpawnrate.get() * 0.5f), 1951425662));

        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "pyramid_badlands"), BADLANDS_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.badlandsPyramidSpawnrate.get(), (int) (RepurposedStructures.RSTemplesConfig.badlandsPyramidSpawnrate.get() * 0.5f), 1718729448));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "pyramid_nether"), NETHER_PYRAMID, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.netherPyramidSpawnrate.get(), (int) (RepurposedStructures.RSTemplesConfig.netherPyramidSpawnrate.get() * 0.5f), 2054372964));

        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_end"), END_SHIPWRECK, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSShipwrecksConfig.endShipwreckSpawnrate.get(), (int) (RepurposedStructures.RSShipwrecksConfig.endShipwreckSpawnrate.get() * 0.5f), 1605500075));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_nether_bricks"), NETHER_BRICKS_SHIPWRECK, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSShipwrecksConfig.netherBricksShipwreckSpawnrate.get(), (int) (RepurposedStructures.RSShipwrecksConfig.netherBricksShipwreckSpawnrate.get() * 0.5f), 2073308006));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_crimson"), CRIMSON_SHIPWRECK, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSShipwrecksConfig.crimsonShipwreckSpawnrate.get(), (int) (RepurposedStructures.RSShipwrecksConfig.crimsonShipwreckSpawnrate.get() * 0.5f), 1019716871));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_warped"), WARPED_SHIPWRECK, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSShipwrecksConfig.warpedShipwreckSpawnrate.get(), (int) (RepurposedStructures.RSShipwrecksConfig.warpedShipwreckSpawnrate.get() * 0.5f), 2072979641));

        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_badlands"), BADLANDS_VILLAGE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.badlandsVillageSpawnrate.get(), (int) (RepurposedStructures.RSVillagesConfig.badlandsVillageSpawnrate.get() * 0.5f), 1319707555));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_birch"), BIRCH_VILLAGE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.birchVillageSpawnrate.get(), (int) (RepurposedStructures.RSVillagesConfig.birchVillageSpawnrate.get() * 0.5f), 1102567365));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_dark_oak"), DARK_FOREST_VILLAGE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.darkForestVillageSpawnrate.get(), (int) (RepurposedStructures.RSVillagesConfig.darkForestVillageSpawnrate.get() * 0.5f), 1921339358));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_jungle"), JUNGLE_VILLAGE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.jungleVillageSpawnrate.get(), (int) (RepurposedStructures.RSVillagesConfig.jungleVillageSpawnrate.get() * 0.5f), 1229975218));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_swamp"), SWAMP_VILLAGE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.swampVillageSpawnrate.get(), (int) (RepurposedStructures.RSVillagesConfig.swampVillageSpawnrate.get() * 0.5f), 1559650945));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_mountains"), MOUNTAINS_VILLAGE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.mountainsVillageSpawnrate.get(), (int) (RepurposedStructures.RSVillagesConfig.mountainsVillageSpawnrate.get() * 0.5f), 2010875989));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_giant_taiga"), GIANT_TAIGA_VILLAGE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.giantTaigaVillageSpawnrate.get(), (int) (RepurposedStructures.RSVillagesConfig.giantTaigaVillageSpawnrate.get() * 0.5f), 1559528842));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_crimson"), CRIMSON_VILLAGE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.crimsonVillageSpawnrate.get(), (int) (RepurposedStructures.RSVillagesConfig.crimsonVillageSpawnrate.get() * 0.5f), 1854750198));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_warped"), WARPED_VILLAGE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.warpedVillageSpawnrate.get(), (int) (RepurposedStructures.RSVillagesConfig.warpedVillageSpawnrate.get() * 0.5f), 1298332136));

        //Next available seed: https://www.google.com/search?q=random+number

        //registers the structure pieces.
        StructurePieces.registerStructurePieces();
    }

    public static <F extends Structure<NoFeatureConfig>> void registerLandscapeTransformingStructure(
            ResourceLocation resourceLocation,
            F structure,
            GenerationStage.Decoration stage,
            StructureSeparationSettings StructureSeparationSettings
    ) {
        Structure.JIGSAW_STRUCTURES =
                ImmutableList.<Structure<?>>builder()
                        .addAll(Structure.JIGSAW_STRUCTURES)
                        .add(structure)
                        .build();

        registerStructure(resourceLocation, structure, stage, StructureSeparationSettings);
    }


    public static <F extends Structure<NoFeatureConfig>> void registerStructure(
            ResourceLocation resourceLocation,
            F structure,
            GenerationStage.Decoration stage,
            StructureSeparationSettings structureSeparationSettings
    ) {
        structure.setRegistryName(resourceLocation);
        Structure.register(resourceLocation.toString(), structure, stage);

        FlatGenerationSettings.STRUCTURES.put(structure, structure.configure(IFeatureConfig.NO_FEATURE_CONFIG));
        RS_STRUCTURES.put(structure, structureSeparationSettings);
    }
}
