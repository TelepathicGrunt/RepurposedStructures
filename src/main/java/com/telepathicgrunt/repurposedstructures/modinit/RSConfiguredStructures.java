package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.NetherShipwreckConfig;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;


public class RSConfiguredStructures {

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> BIRCH_MINESHAFT = RSStructures.MINESHAFT_BIRCH.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> DARK_FOREST_MINESHAFT = RSStructures.MINESHAFT_DARK_FOREST.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> DESERT_MINESHAFT = RSStructures.MINESHAFT_DESERT.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> END_MINESHAFT = RSStructures.MINESHAFT_END.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> NETHER_MINESHAFT = RSStructures.MINESHAFT_NETHER.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> CRIMSON_MINESHAFT = RSStructures.MINESHAFT_CRIMSON.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> WARPED_MINESHAFT = RSStructures.MINESHAFT_WARPED.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> ICY_MINESHAFT = RSStructures.MINESHAFT_ICY.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> JUNGLE_MINESHAFT = RSStructures.MINESHAFT_JUNGLE.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OCEAN_MINESHAFT = RSStructures.MINESHAFT_OCEAN.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> SAVANNA_MINESHAFT = RSStructures.MINESHAFT_SAVANNA.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> STONE_MINESHAFT = RSStructures.MINESHAFT_STONE.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> SWAMP_MINESHAFT = RSStructures.MINESHAFT_SWAMP.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> TAIGA_MINESHAFT = RSStructures.MINESHAFT_TAIGA.configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> NETHER_STRONGHOLD = RSStructures.STRONGHOLD_NETHER.configured(FeatureConfiguration.NONE);
    // regexpos1

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> JUNGLE_FORTRESS = RSStructures.FORTRESS_JUNGLE.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> GRASSY_IGLOO = RSStructures.IGLOO_GRASSY.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> STONE_IGLOO = RSStructures.IGLOO_STONE.configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> NETHER_WASTELAND_TEMPLE = RSStructures.TEMPLE_NETHER_WASTELAND.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> NETHER_BASALT_TEMPLE = RSStructures.TEMPLE_NETHER_BASALT.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> NETHER_WARPED_TEMPLE = RSStructures.TEMPLE_NETHER_WARPED.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> NETHER_CRIMSON_TEMPLE = RSStructures.TEMPLE_NETHER_CRIMSON.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> NETHER_SOUL_TEMPLE = RSStructures.TEMPLE_NETHER_SOUL.configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> NETHER_BRICK_OUTPOST = RSStructures.OUTPOST_NETHER_BRICK.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> WARPED_OUTPOST = RSStructures.OUTPOST_WARPED.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> CRIMSON_OUTPOST = RSStructures.OUTPOST_CRIMSON.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_BIRCH = RSStructures.OUTPOST_BIRCH.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_JUNGLE = RSStructures.OUTPOST_JUNGLE.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_GIANT_TREE_TAIGA = RSStructures.OUTPOST_GIANT_TREE_TAIGA.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_DESERT = RSStructures.OUTPOST_DESERT.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_BADLANDS = RSStructures.OUTPOST_BADLANDS.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_SNOWY = RSStructures.OUTPOST_SNOWY.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_ICY = RSStructures.OUTPOST_ICY.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_TAIGA = RSStructures.OUTPOST_TAIGA.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_OAK = RSStructures.OUTPOST_OAK.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_END = RSStructures.OUTPOST_END.configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> NETHER_PYRAMID = RSStructures.PYRAMID_NETHER.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> BADLANDS_PYRAMID = RSStructures.PYRAMID_BADLANDS.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_END = RSStructures.PYRAMID_END.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_ICY = RSStructures.PYRAMID_ICY.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_SNOWY = RSStructures.PYRAMID_SNOWY.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_JUNGLE = RSStructures.PYRAMID_JUNGLE.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_MUSHROOM = RSStructures.PYRAMID_MUSHROOM.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_OCEAN = RSStructures.PYRAMID_OCEAN.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_GIANT_TREE_TAIGA = RSStructures.PYRAMID_GIANT_TREE_TAIGA.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_FLOWER_FOREST = RSStructures.PYRAMID_FLOWER_FOREST.configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> END_SHIPWRECK = RSStructures.SHIPWRECK_END.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NetherShipwreckConfig, ? extends StructureFeature<NetherShipwreckConfig>> NETHER_BRICKS_SHIPWRECK_FLYING = RSStructures.SHIPWRECK_NETHER_BRICKS.configured(new NetherShipwreckConfig(true));
    public static ConfiguredStructureFeature<NetherShipwreckConfig, ? extends StructureFeature<NetherShipwreckConfig>> NETHER_BRICKS_SHIPWRECK = RSStructures.SHIPWRECK_NETHER_BRICKS.configured(new NetherShipwreckConfig(false));
    public static ConfiguredStructureFeature<NetherShipwreckConfig, ? extends StructureFeature<NetherShipwreckConfig>> CRIMSON_SHIPWRECK = RSStructures.SHIPWRECK_CRIMSON.configured(new NetherShipwreckConfig(false));
    public static ConfiguredStructureFeature<NetherShipwreckConfig, ? extends StructureFeature<NetherShipwreckConfig>> WARPED_SHIPWRECK = RSStructures.SHIPWRECK_WARPED.configured(new NetherShipwreckConfig(false));

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> BADLANDS_VILLAGE = RSStructures.VILLAGE_BADLANDS.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> BIRCH_VILLAGE = RSStructures.VILLAGE_BIRCH.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> DARK_FOREST_VILLAGE = RSStructures.VILLAGE_DARK_FOREST.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> JUNGLE_VILLAGE = RSStructures.VILLAGE_JUNGLE.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> SWAMP_VILLAGE = RSStructures.VILLAGE_SWAMP.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MOUNTAINS_VILLAGE = RSStructures.VILLAGE_MOUNTAINS.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> GIANT_TAIGA_VILLAGE = RSStructures.VILLAGE_GIANT_TAIGA.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> CRIMSON_VILLAGE = RSStructures.VILLAGE_CRIMSON.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> WARPED_VILLAGE = RSStructures.VILLAGE_WARPED.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> VILLAGE_OAK = RSStructures.VILLAGE_OAK.configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> RUINED_PORTAL_END = RSStructures.RUINED_PORTAL_END.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> RUINS_NETHER = RSStructures.RUINS_NETHER.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> RUINS_LAND_WARM = RSStructures.RUINS_LAND_WARM.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> RUINS_LAND_HOT = RSStructures.RUINS_LAND_HOT.configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> CITY_NETHER = RSStructures.CITY_NETHER.configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MANSION_BIRCH = RSStructures.MANSION_BIRCH.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MANSION_JUNGLE = RSStructures.MANSION_JUNGLE.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MANSION_OAK = RSStructures.MANSION_OAK.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MANSION_SAVANNA = RSStructures.MANSION_SAVANNA.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MANSION_TAIGA = RSStructures.MANSION_TAIGA.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MANSION_DESERT = RSStructures.MANSION_DESERT.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MANSION_SNOWY = RSStructures.MANSION_SNOWY.configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> WITCH_HUTS_OAK = RSStructures.WITCH_HUTS_OAK.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> WITCH_HUTS_TAIGA = RSStructures.WITCH_HUTS_TAIGA.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> WITCH_HUTS_BIRCH = RSStructures.WITCH_HUTS_BIRCH.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> WITCH_HUTS_DARK_FOREST = RSStructures.WITCH_HUTS_DARK_FOREST.configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> WITCH_HUTS_GIANT_TREE_TAIGA = RSStructures.WITCH_HUTS_GIANT_TREE_TAIGA.configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> BASTION_UNDERGROUND = RSStructures.BASTION_UNDERGROUND.configured(FeatureConfiguration.NONE);

    public static void registerConfiguredStructures() {
        WritableRegistry<ConfiguredStructureFeature<?, ?>> registry = (WritableRegistry<ConfiguredStructureFeature<?, ?>>) BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "birch_mineshaft"), BIRCH_MINESHAFT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dark_forest_mineshaft"), DARK_FOREST_MINESHAFT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "desert_mineshaft"), DESERT_MINESHAFT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "end_mineshaft"), END_MINESHAFT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_mineshaft"), NETHER_MINESHAFT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "crimson_mineshaft"), CRIMSON_MINESHAFT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "warped_mineshaft"), WARPED_MINESHAFT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "icy_mineshaft"), ICY_MINESHAFT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_mineshaft"), JUNGLE_MINESHAFT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "ocean_mineshaft"), OCEAN_MINESHAFT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "savanna_mineshaft"), SAVANNA_MINESHAFT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "stone_mineshaft"), STONE_MINESHAFT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "swamp_mineshaft"), SWAMP_MINESHAFT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "taiga_mineshaft"), TAIGA_MINESHAFT);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_stronghold"), NETHER_STRONGHOLD);
        // regexpos2

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_fortress"), JUNGLE_FORTRESS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "grassy_igloo"), GRASSY_IGLOO);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "stone_igloo"), STONE_IGLOO);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_wasteland_temple"), NETHER_WASTELAND_TEMPLE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_basalt_temple"), NETHER_BASALT_TEMPLE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_warped_temple"), NETHER_WARPED_TEMPLE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_crimson_temple"), NETHER_CRIMSON_TEMPLE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_soul_temple"), NETHER_SOUL_TEMPLE);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_brick_outpost"), NETHER_BRICK_OUTPOST);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "warped_outpost"), WARPED_OUTPOST);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "crimson_outpost"), CRIMSON_OUTPOST);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "outpost_birch"), OUTPOST_BIRCH);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "outpost_jungle"), OUTPOST_JUNGLE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "outpost_giant_tree_taiga"), OUTPOST_GIANT_TREE_TAIGA);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "outpost_desert"), OUTPOST_DESERT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "outpost_badlands"), OUTPOST_BADLANDS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "outpost_snowy"), OUTPOST_SNOWY);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "outpost_icy"), OUTPOST_ICY);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "outpost_taiga"), OUTPOST_TAIGA);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "outpost_oak"), OUTPOST_OAK);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "outpost_end"), OUTPOST_END);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_pyramid"), NETHER_PYRAMID);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "badlands_temple"), BADLANDS_PYRAMID);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "pyramid_snowy"), PYRAMID_SNOWY);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "pyramid_end"), PYRAMID_END);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "pyramid_icy"), PYRAMID_ICY);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "pyramid_jungle"), PYRAMID_JUNGLE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "pyramid_mushroom"), PYRAMID_MUSHROOM);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "pyramid_ocean"), PYRAMID_OCEAN);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "pyramid_giant_tree_taiga"), PYRAMID_GIANT_TREE_TAIGA);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "pyramid_flower_forest"), PYRAMID_FLOWER_FOREST);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "end_shipwreck"), END_SHIPWRECK);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_bricks_shipwreck_flying"), NETHER_BRICKS_SHIPWRECK_FLYING);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_bricks_shipwreck"), NETHER_BRICKS_SHIPWRECK);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "crimson_shipwreck"), CRIMSON_SHIPWRECK);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "warped_shipwreck"), WARPED_SHIPWRECK);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "badlands_village"), BADLANDS_VILLAGE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "birch_village"), BIRCH_VILLAGE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dark_forest_village"), DARK_FOREST_VILLAGE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_village"), JUNGLE_VILLAGE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "swamp_village"), SWAMP_VILLAGE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "mountains_village"), MOUNTAINS_VILLAGE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "giant_taiga_village"), GIANT_TAIGA_VILLAGE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "crimson_village"), CRIMSON_VILLAGE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "warped_village"), WARPED_VILLAGE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "village_oak"), VILLAGE_OAK);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "ruined_portal_end"), RUINED_PORTAL_END);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "ruins_nether"), RUINS_NETHER);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "ruins_land_warm"), RUINS_LAND_WARM);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "ruins_land_hot"), RUINS_LAND_HOT);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "city_nether"), CITY_NETHER);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "mansion_birch"), MANSION_BIRCH);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "mansion_jungle"), MANSION_JUNGLE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "mansion_oak"), MANSION_OAK);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "mansion_savanna"), MANSION_SAVANNA);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "mansion_taiga"), MANSION_TAIGA);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "mansion_desert"), MANSION_DESERT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "mansion_snowy"), MANSION_SNOWY);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "witch_huts_oak"), WITCH_HUTS_OAK);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "witch_huts_taiga"), WITCH_HUTS_TAIGA);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "witch_huts_birch"), WITCH_HUTS_BIRCH);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "witch_huts_dark_forest"), WITCH_HUTS_DARK_FOREST);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "witch_huts_giant_tree_taiga"), WITCH_HUTS_GIANT_TREE_TAIGA);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "bastion_underground"), BASTION_UNDERGROUND);
    }
}
