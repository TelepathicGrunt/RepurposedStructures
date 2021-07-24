package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.NetherShipwreckConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;

public class RSConfiguredStructures {

    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> BIRCH_MINESHAFT = RSStructures.MINESHAFT_BIRCH.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> DARK_FOREST_MINESHAFT = RSStructures.MINESHAFT_DARK_FOREST.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> DESERT_MINESHAFT = RSStructures.MINESHAFT_DESERT.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> END_MINESHAFT = RSStructures.MINESHAFT_END.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> NETHER_MINESHAFT = RSStructures.MINESHAFT_NETHER.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> CRIMSON_MINESHAFT = RSStructures.MINESHAFT_CRIMSON.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> WARPED_MINESHAFT = RSStructures.MINESHAFT_WARPED.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> ICY_MINESHAFT = RSStructures.MINESHAFT_ICY.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> JUNGLE_MINESHAFT = RSStructures.MINESHAFT_JUNGLE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> OCEAN_MINESHAFT = RSStructures.MINESHAFT_OCEAN.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> SAVANNA_MINESHAFT = RSStructures.MINESHAFT_SAVANNA.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> STONE_MINESHAFT = RSStructures.MINESHAFT_STONE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> SWAMP_MINESHAFT = RSStructures.MINESHAFT_SWAMP.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> TAIGA_MINESHAFT = RSStructures.MINESHAFT_TAIGA.get().configured(IFeatureConfig.NONE);

    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> NETHER_STRONGHOLD = RSStructures.STRONGHOLD_NETHER.get().configured(IFeatureConfig.NONE);

    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> JUNGLE_FORTRESS = RSStructures.FORTRESS_JUNGLE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> GRASSY_IGLOO = RSStructures.IGLOO_GRASSY.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> STONE_IGLOO = RSStructures.IGLOO_STONE.get().configured(IFeatureConfig.NONE);

    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> NETHER_WASTELAND_TEMPLE = RSStructures.TEMPLE_NETHER_WASTELAND.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> NETHER_BASALT_TEMPLE = RSStructures.TEMPLE_NETHER_BASALT.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> NETHER_WARPED_TEMPLE = RSStructures.TEMPLE_NETHER_WARPED.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> NETHER_CRIMSON_TEMPLE = RSStructures.TEMPLE_NETHER_CRIMSON.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> NETHER_SOUL_TEMPLE = RSStructures.TEMPLE_NETHER_SOUL.get().configured(IFeatureConfig.NONE);

    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> NETHER_BRICK_OUTPOST = RSStructures.OUTPOST_NETHER_BRICK.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> WARPED_OUTPOST = RSStructures.OUTPOST_WARPED.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> CRIMSON_OUTPOST = RSStructures.OUTPOST_CRIMSON.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> OUTPOST_BIRCH = RSStructures.OUTPOST_BIRCH.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> OUTPOST_JUNGLE = RSStructures.OUTPOST_JUNGLE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> OUTPOST_GIANT_TREE_TAIGA = RSStructures.OUTPOST_GIANT_TREE_TAIGA.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> OUTPOST_DESERT = RSStructures.OUTPOST_DESERT.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> OUTPOST_BADLANDS = RSStructures.OUTPOST_BADLANDS.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> OUTPOST_SNOWY = RSStructures.OUTPOST_SNOWY.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> OUTPOST_ICY = RSStructures.OUTPOST_ICY.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> OUTPOST_TAIGA = RSStructures.OUTPOST_TAIGA.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> OUTPOST_OAK = RSStructures.OUTPOST_OAK.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> OUTPOST_END = RSStructures.OUTPOST_END.get().configured(IFeatureConfig.NONE);

    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> NETHER_PYRAMID = RSStructures.PYRAMID_NETHER.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> BADLANDS_TEMPLE = RSStructures.PYRAMID_BADLANDS.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> PYRAMID_SNOWY = RSStructures.PYRAMID_SNOWY.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> PYRAMID_END = RSStructures.PYRAMID_END.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> PYRAMID_ICY = RSStructures.PYRAMID_ICY.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> PYRAMID_JUNGLE = RSStructures.PYRAMID_JUNGLE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> PYRAMID_MUSHROOM = RSStructures.PYRAMID_MUSHROOM.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> PYRAMID_OCEAN = RSStructures.PYRAMID_OCEAN.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> PYRAMID_GIANT_TREE_TAIGA = RSStructures.PYRAMID_GIANT_TREE_TAIGA.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> PYRAMID_FLOWER_FOREST = RSStructures.PYRAMID_FLOWER_FOREST.get().configured(IFeatureConfig.NONE);

    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> END_SHIPWRECK = RSStructures.SHIPWRECK_END.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NetherShipwreckConfig, ? extends Structure<NetherShipwreckConfig>> NETHER_BRICKS_SHIPWRECK_FLYING = RSStructures.SHIPWRECK_NETHER_BRICKS.get().configured(new NetherShipwreckConfig(true));
    public static StructureFeature<NetherShipwreckConfig, ? extends Structure<NetherShipwreckConfig>> NETHER_BRICKS_SHIPWRECK = RSStructures.SHIPWRECK_NETHER_BRICKS.get().configured(new NetherShipwreckConfig(false));
    public static StructureFeature<NetherShipwreckConfig, ? extends Structure<NetherShipwreckConfig>> CRIMSON_SHIPWRECK = RSStructures.SHIPWRECK_CRIMSON.get().configured( new NetherShipwreckConfig(false));
    public static StructureFeature<NetherShipwreckConfig, ? extends Structure<NetherShipwreckConfig>> WARPED_SHIPWRECK = RSStructures.SHIPWRECK_WARPED.get().configured(new NetherShipwreckConfig(false));

    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> BADLANDS_VILLAGE = RSStructures.VILLAGE_BADLANDS.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> BIRCH_VILLAGE = RSStructures.VILLAGE_BIRCH.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> DARK_FOREST_VILLAGE = RSStructures.VILLAGE_DARK_FOREST.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> JUNGLE_VILLAGE = RSStructures.VILLAGE_JUNGLE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> SWAMP_VILLAGE = RSStructures.VILLAGE_SWAMP.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> MOUNTAINS_VILLAGE = RSStructures.VILLAGE_MOUNTAINS.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> GIANT_TAIGA_VILLAGE = RSStructures.VILLAGE_GIANT_TAIGA.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> CRIMSON_VILLAGE = RSStructures.VILLAGE_CRIMSON.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> WARPED_VILLAGE = RSStructures.VILLAGE_WARPED.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> VILLAGE_OAK = RSStructures.VILLAGE_OAK.get().configured(IFeatureConfig.NONE);

    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> RUINED_PORTAL_END = RSStructures.RUINED_PORTAL_END.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> RUINS_NETHER = RSStructures.RUINS_NETHER.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> RUINS_LAND_WARM = RSStructures.RUINS_LAND_WARM.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> RUINS_LAND_HOT = RSStructures.RUINS_LAND_HOT.get().configured(IFeatureConfig.NONE);
    // regexpos1

    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> CITY_NETHER = RSStructures.CITY_NETHER.get().configured(IFeatureConfig.NONE);

    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> MANSION_BIRCH = RSStructures.MANSION_BIRCH.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> MANSION_JUNGLE = RSStructures.MANSION_JUNGLE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> MANSION_OAK = RSStructures.MANSION_OAK.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> MANSION_SAVANNA = RSStructures.MANSION_SAVANNA.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> MANSION_TAIGA = RSStructures.MANSION_TAIGA.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> MANSION_DESERT = RSStructures.MANSION_DESERT.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> MANSION_SNOWY = RSStructures.MANSION_SNOWY.get().configured(IFeatureConfig.NONE);

    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> WITCH_HUTS_OAK = RSStructures.WITCH_HUTS_OAK.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> WITCH_HUTS_TAIGA = RSStructures.WITCH_HUTS_TAIGA.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> WITCH_HUTS_BIRCH = RSStructures.WITCH_HUTS_BIRCH.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> WITCH_HUTS_DARK_FOREST = RSStructures.WITCH_HUTS_DARK_FOREST.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> WITCH_HUTS_GIANT_TREE_TAIGA = RSStructures.WITCH_HUTS_GIANT_TREE_TAIGA.get().configured(IFeatureConfig.NONE);

    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> BASTION_UNDERGROUND = RSStructures.BASTION_UNDERGROUND.get().configured(IFeatureConfig.NONE);

    public static void registerStructureFeatures() {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "birch_mineshaft"), BIRCH_MINESHAFT);
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
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dark_forest_mineshaft"), DARK_FOREST_MINESHAFT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "swamp_mineshaft"), SWAMP_MINESHAFT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "taiga_mineshaft"), TAIGA_MINESHAFT);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_stronghold"), NETHER_STRONGHOLD);

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
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "badlands_temple"), BADLANDS_TEMPLE);
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
        // regexpos2

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "city_nether"), CITY_NETHER);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "mansion_birch"), MANSION_BIRCH);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "mansion_jungle"), MANSION_JUNGLE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "mansion_oak"), MANSION_OAK);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "mansion_savanna"), MANSION_SAVANNA);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "mansion_taiga"), MANSION_TAIGA);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "mansion_desert"), MANSION_DESERT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "mansion_snowy"), MANSION_SNOWY);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "witch_hut_oak"), WITCH_HUTS_OAK);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "witch_hut_taiga"), WITCH_HUTS_TAIGA);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "witch_hut_birch"), WITCH_HUTS_BIRCH);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "witch_hut_dark_forest"), WITCH_HUTS_DARK_FOREST);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "witch_hut_giant_tree_taiga"), WITCH_HUTS_GIANT_TREE_TAIGA);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "bastion_underground"), BASTION_UNDERGROUND);
    }
}
