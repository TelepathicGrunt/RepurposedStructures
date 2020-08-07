package com.telepathicgrunt.repurposedstructures;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.telepathicgrunt.repurposedstructures.utils.RegUtil;
import com.telepathicgrunt.repurposedstructures.world.features.*;
import com.telepathicgrunt.repurposedstructures.world.structures.*;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.*;


public class RSFeatures {

    public static final BlockStateProvidingFeatureConfig COBBLESTONE_PILE_CONFIG = new BlockStateProvidingFeatureConfig(new SimpleBlockStateProvider(Blocks.COBBLESTONE.getDefaultState()));

    //Static instance of our structure so we can reference it and add it to biomes easily.
    public static Feature<NoFeatureConfig> BADLANDS_DUNGEONS = new DungeonBadlands(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> DARK_FOREST_DUNGEONS = new DungeonDarkForest(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> DESERT_DUNGEONS = new DungeonDesert(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> END_DUNGEONS = new DungeonEnd(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> NETHER_DUNGEONS = new DungeonNether(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> SNOW_DUNGEONS = new DungeonSnow(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> SWAMP_DUNGEONS = new DungeonSwamp(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> MUSHROOM_DUNGEONS = new DungeonMushroom(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> JUNGLE_DUNGEONS = new DungeonJungle(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> OCEAN_DUNGEONS = new DungeonOcean(NoFeatureConfig.CODEC);

    public static Feature<NoFeatureConfig> BADLANDS_WELL = new WellBadlands(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> NETHER_WELL = new WellNether(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> SNOW_WELL = new WellSnow(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> MOSSY_STONE_WELL = new WellMossyStone(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> FOREST_WELL = new WellForest(NoFeatureConfig.CODEC);

    public static Feature<NoFeatureConfig> BOULDER_GIANT = new BoulderGiant(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> BOULDER_TINY = new BoulderTiny(NoFeatureConfig.CODEC);
    public static Feature<BaseTreeFeatureConfig> HORNED_SWAMP_TREE = new TreeSwampHorned(BaseTreeFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> SHORT_VINES = new VinesShort(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> SWAMP_VILLAGE_VINES = new SwampVillageVines(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> JUNGLE_STRUCTURES_VINES = new JungleStructuresVines(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> FORTRESS_BREAKAGE = new FortressBreakage(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> STRONGHOLD_CHAINS = new StrongholdChains(NoFeatureConfig.CODEC);

    public static Structure<NoFeatureConfig> BIRCH_MINESHAFT = new RSMineshaftBirchStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> DESERT_MINESHAFT = new RSMineshaftDesertStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> END_MINESHAFT = new RSMineshaftEndStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> NETHER_MINESHAFT = new RSMineshaftNetherStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> ICY_MINESHAFT = new RSMineshaftIcyStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> JUNGLE_MINESHAFT = new RSMineshaftJungleStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> OCEAN_MINESHAFT = new RSMineshaftOceanStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> SAVANNA_MINESHAFT = new RSMineshaftSavannaStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> STONE_MINESHAFT = new RSMineshaftStoneStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> SWAMP_OR_DARK_FOREST_MINESHAFT = new RSMineshaftSwampOrDarkForestStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> TAIGA_MINESHAFT = new RSMineshaftTaigaStructure(NoFeatureConfig.CODEC);

    public static Structure<NoFeatureConfig> STONEBRICK_STRONGHOLD = new RSStonebrickStrongholdStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> NETHER_STRONGHOLD = new RSNetherStrongholdStructure(NoFeatureConfig.CODEC);

    public static Structure<NoFeatureConfig> JUNGLE_FORTRESS = new FortressJungleStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> GRASSY_IGLOO = new IglooGrassyStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> STONE_IGLOO = new IglooStoneStructure(NoFeatureConfig.CODEC);

    public static Structure<NoFeatureConfig> NETHER_WASTELAND_TEMPLE = new TempleNetherWastelandStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> NETHER_BASALT_TEMPLE = new TempleNetherBasaltStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> NETHER_WARPED_TEMPLE = new TempleNetherWarpedStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> NETHER_CRIMSON_TEMPLE = new TempleNetherCrimsonStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> NETHER_SOUL_TEMPLE = new TempleNetherSoulStructure(NoFeatureConfig.CODEC);

    public static Structure<NoFeatureConfig> NETHER_BRICK_OUTPOST = new OutpostNetherBrickStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> WARPED_OUTPOST = new OutpostWarpedStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> CRIMSON_OUTPOST = new OutpostCrimsonStructure(NoFeatureConfig.CODEC);

    public static Structure<NoFeatureConfig> NETHER_PYRAMID = new PyramidNetherStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> BADLANDS_TEMPLE = new PyramidBadlandsStructure(NoFeatureConfig.CODEC);

    public static Structure<NoFeatureConfig> END_SHIPWRECK = new EndShipwreckStructure(NoFeatureConfig.CODEC);

    public static Structure<NoFeatureConfig> BADLANDS_VILLAGE = new VillageBadlandsStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> BIRCH_VILLAGE = new VillageBirchStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> DARK_FOREST_VILLAGE = new VillageDarkForestStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> JUNGLE_VILLAGE = new VillageJungleStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> SWAMP_VILLAGE = new VillageSwampStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> MOUNTAINS_VILLAGE = new VillageMountainsStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> GIANT_TAIGA_VILLAGE = new VillageGiantTaigaStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> CRIMSON_VILLAGE = new VillageCrimsonStructure(NoFeatureConfig.CODEC);
    public static Structure<NoFeatureConfig> WARPED_VILLAGE = new VillageWarpedStructure(NoFeatureConfig.CODEC);
    public static List<Structure<NoFeatureConfig>> OVERWORLD_VILLAGE_LIST = Arrays.asList(
            BADLANDS_VILLAGE,
            BIRCH_VILLAGE,
            DARK_FOREST_VILLAGE,
            JUNGLE_VILLAGE,
            SWAMP_VILLAGE,
            MOUNTAINS_VILLAGE,
            GIANT_TAIGA_VILLAGE
    );

    public static void registerFeatures(final RegistryEvent.Register<Feature<?>> event) {
        IForgeRegistry<Feature<?>> registry = event.getRegistry();

        RegUtil.register(registry, BADLANDS_DUNGEONS, RepurposedStructures.MODID + "dungeons_badlands");
        RegUtil.register(registry, DARK_FOREST_DUNGEONS, RepurposedStructures.MODID + "dungeons_dark_forest");
        RegUtil.register(registry, DESERT_DUNGEONS, RepurposedStructures.MODID + "dungeons_desert");
        RegUtil.register(registry, END_DUNGEONS, RepurposedStructures.MODID + "dungeons_end");
        RegUtil.register(registry, NETHER_DUNGEONS, RepurposedStructures.MODID + "dungeons_nether");
        RegUtil.register(registry, SNOW_DUNGEONS, RepurposedStructures.MODID + "dungeons_snow");
        RegUtil.register(registry, SWAMP_DUNGEONS, RepurposedStructures.MODID + "dungeons_swamp");
        RegUtil.register(registry, MUSHROOM_DUNGEONS, RepurposedStructures.MODID + "dungeons_mushroom");
        RegUtil.register(registry, JUNGLE_DUNGEONS, RepurposedStructures.MODID + "dungeons_jungle");
        RegUtil.register(registry, OCEAN_DUNGEONS, RepurposedStructures.MODID + "dungeons_ocean");

        RegUtil.register(registry, BADLANDS_WELL, RepurposedStructures.MODID + "well_badlands");
        RegUtil.register(registry, NETHER_WELL, RepurposedStructures.MODID + "well_nether");
        RegUtil.register(registry, SNOW_WELL, RepurposedStructures.MODID + "well_snow");
        RegUtil.register(registry, MOSSY_STONE_WELL, RepurposedStructures.MODID + "well_mossy_stone");
        RegUtil.register(registry, FOREST_WELL, RepurposedStructures.MODID + "well_forest");

        RegUtil.register(registry, BOULDER_GIANT, RepurposedStructures.MODID + "boulder_giant");
        RegUtil.register(registry, BOULDER_TINY, RepurposedStructures.MODID + "boulder_tiny");
        RegUtil.register(registry, HORNED_SWAMP_TREE, RepurposedStructures.MODID + "horned_swamp_tree");
        RegUtil.register(registry, SHORT_VINES, RepurposedStructures.MODID + "short_vines");
        RegUtil.register(registry, SWAMP_VILLAGE_VINES, RepurposedStructures.MODID + "swamp_village_vines");
        RegUtil.register(registry, JUNGLE_STRUCTURES_VINES, RepurposedStructures.MODID + "jungle_structures_vines");
        RegUtil.register(registry, FORTRESS_BREAKAGE, RepurposedStructures.MODID + "fortress_breakage");
        RegUtil.register(registry, STRONGHOLD_CHAINS, RepurposedStructures.MODID + "stronghold_chains");

        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_birch"), BIRCH_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 399117345));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_desert"), DESERT_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 399117346));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_end"), END_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 399117347));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_nether"), NETHER_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 399117348));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_icy"), ICY_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 399117349));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_jungle"), JUNGLE_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 399117350));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_ocean"), OCEAN_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 399117351));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_savanna"), SAVANNA_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 399117352));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_stone"), STONE_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 399117353));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_swamp_or_dark_forest"), SWAMP_OR_DARK_FOREST_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 399117354));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_taiga"), TAIGA_MINESHAFT, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(1, 0, 399117355));

        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "stronghold_stonebrick"), STONEBRICK_STRONGHOLD, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdSpawnrate.get(), (int) (RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdSpawnrate.get() * 0.75f), 399117356));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "stronghold_nether"), NETHER_STRONGHOLD, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSStrongholdsConfig.netherStrongholdSpawnrate.get(), (int) (RepurposedStructures.RSStrongholdsConfig.netherStrongholdSpawnrate.get() * 0.75f), 399117357));

        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "fortress_jungle"), JUNGLE_FORTRESS, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMainConfig.jungleFortressSpawnrate.get(), (int) (RepurposedStructures.RSMainConfig.jungleFortressSpawnrate.get() * 0.75f), 399117358));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "igloo_grassy"), GRASSY_IGLOO, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMainConfig.grassyIglooSpawnrate.get(), (int) (RepurposedStructures.RSMainConfig.grassyIglooSpawnrate.get() * 0.75f), 399117362));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "igloo_stone"), STONE_IGLOO, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSMainConfig.stoneIglooSpawnrate.get(), (int) (RepurposedStructures.RSMainConfig.stoneIglooSpawnrate.get() * 0.75f), 399117363));

        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_wasteland"), NETHER_WASTELAND_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.netherWastelandTempleSpawnrate.get(), (int) (RepurposedStructures.RSTemplesConfig.netherWastelandTempleSpawnrate.get() * 0.75f), 399117359));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_soul"), NETHER_SOUL_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.netherSoulTempleSpawnrate.get(), (int) (RepurposedStructures.RSTemplesConfig.netherSoulTempleSpawnrate.get() * 0.75f), 399117359));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_basalt"), NETHER_BASALT_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.netherBasaltTempleSpawnrate.get(), (int) (RepurposedStructures.RSTemplesConfig.netherBasaltTempleSpawnrate.get() * 0.75f), 399117359));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_crimson"), NETHER_CRIMSON_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.netherCrimsonTempleSpawnrate.get(), (int) (RepurposedStructures.RSTemplesConfig.netherCrimsonTempleSpawnrate.get() * 0.75f), 399117359));
        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_warped"), NETHER_WARPED_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.netherWarpedTempleSpawnrate.get(), (int) (RepurposedStructures.RSTemplesConfig.netherWarpedTempleSpawnrate.get() * 0.75f), 399117359));

        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "outpost_nether_brick"), NETHER_BRICK_OUTPOST, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSOutpostsConfig.netherBrickOutpostSpawnrate.get(), (int) (RepurposedStructures.RSOutpostsConfig.netherBrickOutpostSpawnrate.get() * 0.75f), 399117371));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "outpost_warped"), WARPED_OUTPOST, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSOutpostsConfig.warpedOutpostSpawnrate.get(), (int) (RepurposedStructures.RSOutpostsConfig.warpedOutpostSpawnrate.get() * 0.75f), 399117372));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "outpost_crimson"), CRIMSON_OUTPOST, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSOutpostsConfig.crimsonOutpostSpawnrate.get(), (int) (RepurposedStructures.RSOutpostsConfig.crimsonOutpostSpawnrate.get() * 0.75f), 399117373));

        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "pyramid_badlands"), BADLANDS_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.badlandsPyramidSpawnrate.get(), (int) (RepurposedStructures.RSTemplesConfig.badlandsPyramidSpawnrate.get() * 0.75f), 399117360));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "pyramid_nether"), NETHER_PYRAMID, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSTemplesConfig.netherPyramidSpawnrate.get(), (int) (RepurposedStructures.RSTemplesConfig.netherPyramidSpawnrate.get() * 0.75f), 399117361));

        registerStructure(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_end"), END_SHIPWRECK, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSShipwrecksConfig.endShipwreckSpawnrate.get(), (int) (RepurposedStructures.RSShipwrecksConfig.endShipwreckSpawnrate.get() * 0.75f), 399117374));

        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_badlands"), BADLANDS_VILLAGE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.badlandsVillageSpawnrate.get(), (int) (RepurposedStructures.RSVillagesConfig.badlandsVillageSpawnrate.get() * 0.75f), 399117364));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_birch"), BIRCH_VILLAGE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.birchVillageSpawnrate.get(), (int) (RepurposedStructures.RSVillagesConfig.birchVillageSpawnrate.get() * 0.75f), 399117365));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_dark_oak"), DARK_FOREST_VILLAGE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.darkForestVillageSpawnrate.get(), (int) (RepurposedStructures.RSVillagesConfig.darkForestVillageSpawnrate.get() * 0.75f), 399117366));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_jungle"), JUNGLE_VILLAGE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.jungleVillageSpawnrate.get(), (int) (RepurposedStructures.RSVillagesConfig.jungleVillageSpawnrate.get() * 0.75f), 399117367));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_swamp"), SWAMP_VILLAGE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.swampVillageSpawnrate.get(), (int) (RepurposedStructures.RSVillagesConfig.swampVillageSpawnrate.get() * 0.75f), 399117368));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_mountains"), MOUNTAINS_VILLAGE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.mountainsVillageSpawnrate.get(), (int) (RepurposedStructures.RSVillagesConfig.mountainsVillageSpawnrate.get() * 0.75f), 399117369));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_giant_taiga"), GIANT_TAIGA_VILLAGE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.giantTaigaVillageSpawnrate.get(), (int) (RepurposedStructures.RSVillagesConfig.giantTaigaVillageSpawnrate.get() * 0.75f), 399117370));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_crimson"), CRIMSON_VILLAGE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.crimsonVillageSpawnrate.get(), (int) (RepurposedStructures.RSVillagesConfig.crimsonVillageSpawnrate.get() * 0.75f), 399117375));
        registerLandscapeTransformingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_warped"), WARPED_VILLAGE, GenerationStage.Decoration.SURFACE_STRUCTURES, new StructureSeparationSettings(RepurposedStructures.RSVillagesConfig.warpedVillageSpawnrate.get(), (int) (RepurposedStructures.RSVillagesConfig.warpedVillageSpawnrate.get() * 0.75f), 399117376));

        //Next avaliable seed: 399117377

        //registers the structure pieces.
        StructurePieces.registerStructurePieces();
    }

    public static <F extends Structure<NoFeatureConfig>> void registerLandscapeTransformingStructure(
            ResourceLocation resourceLocation,
            F structure,
            GenerationStage.Decoration stage,
            StructureSeparationSettings StructureSeparationSettings
    ) {
        List<Structure<?>> tempList = new ArrayList<>(Structure.field_236384_t_);
        tempList.add(structure);
        Structure.field_236384_t_ = ImmutableList.copyOf(tempList);

        registerStructure(resourceLocation, structure, stage, StructureSeparationSettings);
    }


    public static <F extends Structure<NoFeatureConfig>> void registerStructure(
            ResourceLocation resourceLocation,
            F structure,
            GenerationStage.Decoration stage,
            StructureSeparationSettings StructureSeparationSettings
    ) {
        structure.setRegistryName(resourceLocation);
        Structure.register(resourceLocation.toString(), structure, stage);

        FlatGenerationSettings.STRUCTURES.put(structure, structure.configure(IFeatureConfig.NO_FEATURE_CONFIG));
        DimensionStructuresSettings.DEFAULT_STRUCTURES =
                ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                    .putAll(DimensionStructuresSettings.DEFAULT_STRUCTURES)
                    .put(structure, StructureSeparationSettings)
                    .build();
        DimensionSettings.Preset.field_236122_b_.getChunkGeneratorType().getConfig().getStructures().put(structure, StructureSeparationSettings);
        DimensionSettings.Preset.field_236123_c_.getChunkGeneratorType().getConfig().getStructures().put(structure, StructureSeparationSettings);
        DimensionSettings.Preset.field_236124_d_.getChunkGeneratorType().getConfig().getStructures().put(structure, StructureSeparationSettings);
        DimensionSettings.Preset.field_236125_e_.getChunkGeneratorType().getConfig().getStructures().put(structure, StructureSeparationSettings);
        DimensionSettings.Preset.field_236126_f_.getChunkGeneratorType().getConfig().getStructures().put(structure, StructureSeparationSettings);
        DimensionSettings.Preset.field_236127_g_.getChunkGeneratorType().getConfig().getStructures().put(structure, StructureSeparationSettings);
        DimensionSettings.Preset.BY_ID.forEach((presetResourceLocation, preset) -> preset.getChunkGeneratorType().getConfig().getStructures().put(structure, StructureSeparationSettings));
    }

    public static void registerVillagePools() {
        VillageBadlandsPools.init();
        VillageBirchPools.init();
        VillageDarkForestPools.init();
        VillageJunglePools.init();
        VillageSwampPools.init();
        VillageMountainsPools.init();
        VillageGiantTaigaPools.init();
        VillageCrimsonPools.init();
        VillageWarpedPools.init();
    }
}
