package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Objects;

public final class RSConfiguredStructures {
    private RSConfiguredStructures() {}

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MINESHAFT_BIRCH = RSStructures.MINESHAFT_BIRCH.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MINESHAFT_DARK_FOREST = RSStructures.MINESHAFT_DARK_FOREST.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MINESHAFT_DESERT = RSStructures.MINESHAFT_DESERT.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MINESHAFT_ICY = RSStructures.MINESHAFT_ICY.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MINESHAFT_JUNGLE = RSStructures.MINESHAFT_JUNGLE.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MINESHAFT_OCEAN = RSStructures.MINESHAFT_OCEAN.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MINESHAFT_SAVANNA = RSStructures.MINESHAFT_SAVANNA.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MINESHAFT_STONE = RSStructures.MINESHAFT_STONE.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MINESHAFT_SWAMP = RSStructures.MINESHAFT_SWAMP.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MINESHAFT_TAIGA = RSStructures.MINESHAFT_TAIGA.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MINESHAFT_NETHER = RSStructures.MINESHAFT_NETHER.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MINESHAFT_CRIMSON = RSStructures.MINESHAFT_CRIMSON.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MINESHAFT_WARPED = RSStructures.MINESHAFT_WARPED.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MINESHAFT_END = RSStructures.MINESHAFT_END.get().configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> STRONGHOLD_NETHER = RSStructures.STRONGHOLD_NETHER.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> STRONGHOLD_END = RSStructures.STRONGHOLD_END.get().configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> FORTRESS_JUNGLE = RSStructures.FORTRESS_JUNGLE.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> IGLOO_GRASSY = RSStructures.IGLOO_GRASSY.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> IGLOO_STONE = RSStructures.IGLOO_STONE.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> IGLOO_MUSHROOM = RSStructures.IGLOO_MUSHROOM.get().configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> TEMPLE_NETHER_WASTELAND = RSStructures.TEMPLE_NETHER_WASTELAND.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> TEMPLE_NETHER_BASALT = RSStructures.TEMPLE_NETHER_BASALT.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> TEMPLE_NETHER_WARPED = RSStructures.TEMPLE_NETHER_WARPED.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> TEMPLE_NETHER_CRIMSON = RSStructures.TEMPLE_NETHER_CRIMSON.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> TEMPLE_NETHER_SOUL = RSStructures.TEMPLE_NETHER_SOUL.get().configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_BIRCH = RSStructures.OUTPOST_BIRCH.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_JUNGLE = RSStructures.OUTPOST_JUNGLE.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_GIANT_TREE_TAIGA = RSStructures.OUTPOST_GIANT_TREE_TAIGA.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_DESERT = RSStructures.OUTPOST_DESERT.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_BADLANDS = RSStructures.OUTPOST_BADLANDS.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_SNOWY = RSStructures.OUTPOST_SNOWY.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_ICY = RSStructures.OUTPOST_ICY.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_TAIGA = RSStructures.OUTPOST_TAIGA.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_OAK = RSStructures.OUTPOST_OAK.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_NETHER_BRICK = RSStructures.OUTPOST_NETHER_BRICK.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_WARPED = RSStructures.OUTPOST_WARPED.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_CRIMSON = RSStructures.OUTPOST_CRIMSON.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> OUTPOST_END = RSStructures.OUTPOST_END.get().configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_BADLANDS = RSStructures.PYRAMID_BADLANDS.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_DARK_FOREST = RSStructures.PYRAMID_DARK_FOREST.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_SNOWY = RSStructures.PYRAMID_SNOWY.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_END = RSStructures.PYRAMID_END.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_ICY = RSStructures.PYRAMID_ICY.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_JUNGLE = RSStructures.PYRAMID_JUNGLE.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_MUSHROOM = RSStructures.PYRAMID_MUSHROOM.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_OCEAN = RSStructures.PYRAMID_OCEAN.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_GIANT_TREE_TAIGA = RSStructures.PYRAMID_GIANT_TREE_TAIGA.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_FLOWER_FOREST = RSStructures.PYRAMID_FLOWER_FOREST.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> PYRAMID_NETHER = RSStructures.PYRAMID_NETHER.get().configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> SHIPWRECK_END = RSStructures.SHIPWRECK_END.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> SHIPWRECK_NETHER_BRICKS = RSStructures.SHIPWRECK_NETHER_BRICKS.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> SHIPWRECK_CRIMSON = RSStructures.SHIPWRECK_CRIMSON.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> SHIPWRECK_WARPED = RSStructures.SHIPWRECK_WARPED.get().configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> VILLAGE_BADLANDS = RSStructures.VILLAGE_BADLANDS.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> VILLAGE_BIRCH = RSStructures.VILLAGE_BIRCH.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> VILLAGE_DARK_FOREST = RSStructures.VILLAGE_DARK_FOREST.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> VILLAGE_JUNGLE = RSStructures.VILLAGE_JUNGLE.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> VILLAGE_SWAMP = RSStructures.VILLAGE_SWAMP.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> VILLAGE_MOUNTAINS = RSStructures.VILLAGE_MOUNTAINS.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> VILLAGE_GIANT_TAIGA = RSStructures.VILLAGE_GIANT_TAIGA.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> VILLAGE_OAK = RSStructures.VILLAGE_OAK.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> VILLAGE_CRIMSON = RSStructures.VILLAGE_CRIMSON.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> VILLAGE_WARPED = RSStructures.VILLAGE_WARPED.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> VILLAGE_MUSHROOM = RSStructures.VILLAGE_MUSHROOM.get().configured(FeatureConfiguration.NONE);
    // regexpos1

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> RUINED_PORTAL_END = RSStructures.RUINED_PORTAL_END.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> RUINS_NETHER = RSStructures.RUINS_NETHER.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> RUINS_LAND_WARM = RSStructures.RUINS_LAND_WARM.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> RUINS_LAND_HOT = RSStructures.RUINS_LAND_HOT.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> RUINS_LAND_COLD = RSStructures.RUINS_LAND_COLD.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> RUINS_LAND_ICY = RSStructures.RUINS_LAND_ICY.get().configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> CITY_NETHER = RSStructures.CITY_NETHER.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> CITY_OVERWORLD = RSStructures.CITY_OVERWORLD.get().configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MANSION_BIRCH = RSStructures.MANSION_BIRCH.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MANSION_JUNGLE = RSStructures.MANSION_JUNGLE.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MANSION_OAK = RSStructures.MANSION_OAK.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MANSION_SAVANNA = RSStructures.MANSION_SAVANNA.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MANSION_TAIGA = RSStructures.MANSION_TAIGA.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MANSION_DESERT = RSStructures.MANSION_DESERT.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> MANSION_SNOWY = RSStructures.MANSION_SNOWY.get().configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> WITCH_HUTS_OAK = RSStructures.WITCH_HUTS_OAK.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> WITCH_HUTS_TAIGA = RSStructures.WITCH_HUTS_TAIGA.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> WITCH_HUTS_BIRCH = RSStructures.WITCH_HUTS_BIRCH.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> WITCH_HUTS_DARK_FOREST = RSStructures.WITCH_HUTS_DARK_FOREST.get().configured(FeatureConfiguration.NONE);
    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> WITCH_HUTS_GIANT_TREE_TAIGA = RSStructures.WITCH_HUTS_GIANT_TREE_TAIGA.get().configured(FeatureConfiguration.NONE);

    public static ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> BASTION_UNDERGROUND = RSStructures.BASTION_UNDERGROUND.get().configured(FeatureConfiguration.NONE);

    public static void registerStructureFeatures() {
        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;

        registerConfiguredStructure(registry, MINESHAFT_BIRCH);
        registerConfiguredStructure(registry, MINESHAFT_DESERT);
        registerConfiguredStructure(registry, MINESHAFT_END);
        registerConfiguredStructure(registry, MINESHAFT_NETHER);
        registerConfiguredStructure(registry, MINESHAFT_CRIMSON);
        registerConfiguredStructure(registry, MINESHAFT_WARPED);
        registerConfiguredStructure(registry, MINESHAFT_ICY);
        registerConfiguredStructure(registry, MINESHAFT_JUNGLE);
        registerConfiguredStructure(registry, MINESHAFT_OCEAN);
        registerConfiguredStructure(registry, MINESHAFT_SAVANNA);
        registerConfiguredStructure(registry, MINESHAFT_STONE);
        registerConfiguredStructure(registry, MINESHAFT_DARK_FOREST);
        registerConfiguredStructure(registry, MINESHAFT_SWAMP);
        registerConfiguredStructure(registry, MINESHAFT_TAIGA);

        registerConfiguredStructure(registry, STRONGHOLD_NETHER);
        registerConfiguredStructure(registry, STRONGHOLD_END);

        registerConfiguredStructure(registry, FORTRESS_JUNGLE);
        registerConfiguredStructure(registry, IGLOO_GRASSY);
        registerConfiguredStructure(registry, IGLOO_STONE);
        registerConfiguredStructure(registry, IGLOO_MUSHROOM);

        registerConfiguredStructure(registry, TEMPLE_NETHER_WASTELAND);
        registerConfiguredStructure(registry, TEMPLE_NETHER_BASALT);
        registerConfiguredStructure(registry, TEMPLE_NETHER_WARPED);
        registerConfiguredStructure(registry, TEMPLE_NETHER_CRIMSON);
        registerConfiguredStructure(registry, TEMPLE_NETHER_SOUL);

        registerConfiguredStructure(registry, OUTPOST_NETHER_BRICK);
        registerConfiguredStructure(registry, OUTPOST_WARPED);
        registerConfiguredStructure(registry, OUTPOST_CRIMSON);
        registerConfiguredStructure(registry, OUTPOST_BIRCH);
        registerConfiguredStructure(registry, OUTPOST_JUNGLE);
        registerConfiguredStructure(registry, OUTPOST_GIANT_TREE_TAIGA);
        registerConfiguredStructure(registry, OUTPOST_DESERT);
        registerConfiguredStructure(registry, OUTPOST_BADLANDS);
        registerConfiguredStructure(registry, OUTPOST_SNOWY);
        registerConfiguredStructure(registry, OUTPOST_ICY);
        registerConfiguredStructure(registry, OUTPOST_TAIGA);
        registerConfiguredStructure(registry, OUTPOST_OAK);
        registerConfiguredStructure(registry, OUTPOST_END);

        registerConfiguredStructure(registry, PYRAMID_NETHER);
        registerConfiguredStructure(registry, PYRAMID_DARK_FOREST);
        registerConfiguredStructure(registry, PYRAMID_BADLANDS);
        registerConfiguredStructure(registry, PYRAMID_SNOWY);
        registerConfiguredStructure(registry, PYRAMID_END);
        registerConfiguredStructure(registry, PYRAMID_ICY);
        registerConfiguredStructure(registry, PYRAMID_JUNGLE);
        registerConfiguredStructure(registry, PYRAMID_MUSHROOM);
        registerConfiguredStructure(registry, PYRAMID_OCEAN);
        registerConfiguredStructure(registry, PYRAMID_GIANT_TREE_TAIGA);
        registerConfiguredStructure(registry, PYRAMID_FLOWER_FOREST);

        registerConfiguredStructure(registry, SHIPWRECK_END);
        registerConfiguredStructure(registry, SHIPWRECK_NETHER_BRICKS);
        registerConfiguredStructure(registry, SHIPWRECK_CRIMSON);
        registerConfiguredStructure(registry, SHIPWRECK_WARPED);

        registerConfiguredStructure(registry, VILLAGE_BADLANDS);
        registerConfiguredStructure(registry, VILLAGE_BIRCH);
        registerConfiguredStructure(registry, VILLAGE_DARK_FOREST);
        registerConfiguredStructure(registry, VILLAGE_JUNGLE);
        registerConfiguredStructure(registry, VILLAGE_SWAMP);
        registerConfiguredStructure(registry, VILLAGE_MOUNTAINS);
        registerConfiguredStructure(registry, VILLAGE_GIANT_TAIGA);
        registerConfiguredStructure(registry, VILLAGE_CRIMSON);
        registerConfiguredStructure(registry, VILLAGE_WARPED);
        registerConfiguredStructure(registry, VILLAGE_OAK);
        registerConfiguredStructure(registry, VILLAGE_MUSHROOM);
        // regexpos2

        registerConfiguredStructure(registry, RUINED_PORTAL_END);
        registerConfiguredStructure(registry, RUINS_NETHER);
        registerConfiguredStructure(registry, RUINS_LAND_WARM);
        registerConfiguredStructure(registry, RUINS_LAND_HOT);
        registerConfiguredStructure(registry, RUINS_LAND_COLD);
        registerConfiguredStructure(registry, RUINS_LAND_ICY);

        registerConfiguredStructure(registry, CITY_NETHER);
        registerConfiguredStructure(registry, CITY_OVERWORLD);

        registerConfiguredStructure(registry, MANSION_BIRCH);
        registerConfiguredStructure(registry, MANSION_JUNGLE);
        registerConfiguredStructure(registry, MANSION_OAK);
        registerConfiguredStructure(registry, MANSION_SAVANNA);
        registerConfiguredStructure(registry, MANSION_TAIGA);
        registerConfiguredStructure(registry, MANSION_DESERT);
        registerConfiguredStructure(registry, MANSION_SNOWY);

        registerConfiguredStructure(registry, WITCH_HUTS_OAK);
        registerConfiguredStructure(registry, WITCH_HUTS_TAIGA);
        registerConfiguredStructure(registry, WITCH_HUTS_BIRCH);
        registerConfiguredStructure(registry, WITCH_HUTS_DARK_FOREST);
        registerConfiguredStructure(registry, WITCH_HUTS_GIANT_TREE_TAIGA);

        registerConfiguredStructure(registry, BASTION_UNDERGROUND);
    }

    private static void registerConfiguredStructure(Registry<ConfiguredStructureFeature<?, ?>> registry, ConfiguredStructureFeature<?, ?> configuredStructureFeature) {
        Registry.register(registry, Objects.requireNonNull(configuredStructureFeature.feature.getRegistryName()), configuredStructureFeature);
    }
}
