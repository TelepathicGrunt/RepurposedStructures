package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.world.placements.RSPlacements;
import com.telepathicgrunt.repurposedstructures.world.structures.*;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSMineshaftPieces;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;


public class RSConfiguredStructures {

    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> BIRCH_MINESHAFT = RSFeatures.BIRCH_MINESHAFT.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> DESERT_MINESHAFT = RSFeatures.DESERT_MINESHAFT.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> END_MINESHAFT = RSFeatures.END_MINESHAFT.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> NETHER_MINESHAFT = RSFeatures.NETHER_MINESHAFT.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> ICY_MINESHAFT = RSFeatures.ICY_MINESHAFT.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> JUNGLE_MINESHAFT = RSFeatures.JUNGLE_MINESHAFT.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> OCEAN_MINESHAFT = RSFeatures.OCEAN_MINESHAFT.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> SAVANNA_MINESHAFT = RSFeatures.SAVANNA_MINESHAFT.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> STONE_MINESHAFT = RSFeatures.STONE_MINESHAFT.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> SWAMP_OR_DARK_FOREST_MINESHAFT = RSFeatures.SWAMP_OR_DARK_FOREST_MINESHAFT.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> TAIGA_MINESHAFT = RSFeatures.TAIGA_MINESHAFT.configure(FeatureConfig.DEFAULT);

    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> STONEBRICK_STRONGHOLD = RSFeatures.STONEBRICK_STRONGHOLD.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> NETHER_STRONGHOLD = RSFeatures.NETHER_STRONGHOLD.configure(FeatureConfig.DEFAULT);

    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> JUNGLE_FORTRESS = RSFeatures.JUNGLE_FORTRESS.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> GRASSY_IGLOO = RSFeatures.GRASSY_IGLOO.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> STONE_IGLOO = RSFeatures.STONE_IGLOO.configure(FeatureConfig.DEFAULT);

    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> NETHER_WASTELAND_TEMPLE = RSFeatures.NETHER_WASTELAND_TEMPLE.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> NETHER_BASALT_TEMPLE = RSFeatures.NETHER_BASALT_TEMPLE.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> NETHER_WARPED_TEMPLE = RSFeatures.NETHER_WARPED_TEMPLE.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> NETHER_CRIMSON_TEMPLE = RSFeatures.NETHER_CRIMSON_TEMPLE.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> NETHER_SOUL_TEMPLE = RSFeatures.NETHER_SOUL_TEMPLE.configure(FeatureConfig.DEFAULT);

    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> NETHER_BRICK_OUTPOST = RSFeatures.NETHER_BRICK_OUTPOST.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> WARPED_OUTPOST = RSFeatures.WARPED_OUTPOST.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> CRIMSON_OUTPOST = RSFeatures.CRIMSON_OUTPOST.configure(FeatureConfig.DEFAULT);

    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> NETHER_PYRAMID = RSFeatures.NETHER_PYRAMID.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> BADLANDS_TEMPLE = RSFeatures.BADLANDS_TEMPLE.configure(FeatureConfig.DEFAULT);

    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> END_SHIPWRECK = RSFeatures.END_SHIPWRECK.configure(FeatureConfig.DEFAULT);

    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> BADLANDS_VILLAGE = RSFeatures.BADLANDS_VILLAGE.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> BIRCH_VILLAGE = RSFeatures.BIRCH_VILLAGE.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> DARK_FOREST_VILLAGE = RSFeatures.DARK_FOREST_VILLAGE.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> JUNGLE_VILLAGE = RSFeatures.JUNGLE_VILLAGE.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> SWAMP_VILLAGE = RSFeatures.SWAMP_VILLAGE.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> MOUNTAINS_VILLAGE = RSFeatures.MOUNTAINS_VILLAGE.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> GIANT_TAIGA_VILLAGE = RSFeatures.GIANT_TAIGA_VILLAGE.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> CRIMSON_VILLAGE = RSFeatures.CRIMSON_VILLAGE.configure(FeatureConfig.DEFAULT);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> WARPED_VILLAGE = RSFeatures.WARPED_VILLAGE.configure(FeatureConfig.DEFAULT);

    public static void registerConfiguredFeatures() {
        MutableRegistry<ConfiguredStructureFeature<?, ?>> registry = (MutableRegistry<ConfiguredStructureFeature<?, ?>>) BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;

        Registry.register(registry, RepurposedStructures.MODID + "birch_mineshaft", BIRCH_MINESHAFT);
        Registry.register(registry, RepurposedStructures.MODID + "desert_mineshaft", DESERT_MINESHAFT);
        Registry.register(registry, RepurposedStructures.MODID + "end_mineshaft", END_MINESHAFT);
        Registry.register(registry, RepurposedStructures.MODID + "nether_mineshaft", NETHER_MINESHAFT);
        Registry.register(registry, RepurposedStructures.MODID + "icy_mineshaft", ICY_MINESHAFT);
        Registry.register(registry, RepurposedStructures.MODID + "jungle_mineshaft", JUNGLE_MINESHAFT);
        Registry.register(registry, RepurposedStructures.MODID + "ocean_mineshaft", OCEAN_MINESHAFT);
        Registry.register(registry, RepurposedStructures.MODID + "savanna_mineshaft", SAVANNA_MINESHAFT);
        Registry.register(registry, RepurposedStructures.MODID + "stone_mineshaft", STONE_MINESHAFT);
        Registry.register(registry, RepurposedStructures.MODID + "swamp_or_dark_forest_mineshaft", SWAMP_OR_DARK_FOREST_MINESHAFT);
        Registry.register(registry, RepurposedStructures.MODID + "taiga_mineshaft", TAIGA_MINESHAFT);

        Registry.register(registry, RepurposedStructures.MODID + "stonebrick_stronghold", STONEBRICK_STRONGHOLD);
        Registry.register(registry, RepurposedStructures.MODID + "nether_stronghold", NETHER_STRONGHOLD);

        Registry.register(registry, RepurposedStructures.MODID + "jungle_fortress", JUNGLE_FORTRESS);
        Registry.register(registry, RepurposedStructures.MODID + "grassy_igloo", GRASSY_IGLOO);
        Registry.register(registry, RepurposedStructures.MODID + "stone_igloo", STONE_IGLOO);

        Registry.register(registry, RepurposedStructures.MODID + "nether_wasteland_temple", NETHER_WASTELAND_TEMPLE);
        Registry.register(registry, RepurposedStructures.MODID + "nether_basalt_temple", NETHER_BASALT_TEMPLE);
        Registry.register(registry, RepurposedStructures.MODID + "nether_warped_temple", NETHER_WARPED_TEMPLE);
        Registry.register(registry, RepurposedStructures.MODID + "nether_crimson_temple", NETHER_CRIMSON_TEMPLE);
        Registry.register(registry, RepurposedStructures.MODID + "nether_soul_temple", NETHER_SOUL_TEMPLE);

        Registry.register(registry, RepurposedStructures.MODID + "nether_brick_outpost", NETHER_BRICK_OUTPOST);
        Registry.register(registry, RepurposedStructures.MODID + "warped_outpost", WARPED_OUTPOST);
        Registry.register(registry, RepurposedStructures.MODID + "crimson_outpost", CRIMSON_OUTPOST);

        Registry.register(registry, RepurposedStructures.MODID + "nether_pyramid", NETHER_PYRAMID);
        Registry.register(registry, RepurposedStructures.MODID + "badlands_temple", BADLANDS_TEMPLE);

        Registry.register(registry, RepurposedStructures.MODID + "end_shipwreck", END_SHIPWRECK);

        Registry.register(registry, RepurposedStructures.MODID + "badlands_village", BADLANDS_VILLAGE);
        Registry.register(registry, RepurposedStructures.MODID + "birch_village", BIRCH_VILLAGE);
        Registry.register(registry, RepurposedStructures.MODID + "dark_forest_village", DARK_FOREST_VILLAGE);
        Registry.register(registry, RepurposedStructures.MODID + "jungle_village", JUNGLE_VILLAGE);
        Registry.register(registry, RepurposedStructures.MODID + "swamp_village", SWAMP_VILLAGE);
        Registry.register(registry, RepurposedStructures.MODID + "mountains_village", MOUNTAINS_VILLAGE);
        Registry.register(registry, RepurposedStructures.MODID + "giant_taiga_village", GIANT_TAIGA_VILLAGE);
        Registry.register(registry, RepurposedStructures.MODID + "crimson_village", CRIMSON_VILLAGE);
        Registry.register(registry, RepurposedStructures.MODID + "warped_village", WARPED_VILLAGE);

    }
}
