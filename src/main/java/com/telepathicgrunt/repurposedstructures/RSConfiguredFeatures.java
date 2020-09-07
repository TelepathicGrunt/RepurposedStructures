package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.world.features.*;
import com.telepathicgrunt.repurposedstructures.world.placements.RSPlacements;
import com.telepathicgrunt.repurposedstructures.world.structures.*;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSMineshaftPieces;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePieces;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.Arrays;
import java.util.List;


public class RSConfiguredFeatures {

    // Dungeons

    public static ConfiguredFeature<?, ?> BADLANDS_DUNGEONS = RSFeatures.BADLANDS_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.badlandsDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.badlandsDungeonMaxHeight))
            .repeat(RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.badlandsDungeonSpawnrate));

    public static ConfiguredFeature<?, ?> DARK_FOREST_DUNGEONS = RSFeatures.DARK_FOREST_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.darkForestDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.darkForestDungeonMaxHeight))
                    .repeat(RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.darkForestDungeonSpawnrate));

    public static ConfiguredFeature<?, ?> DESERT_DUNGEONS = RSFeatures.DESERT_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.desertDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.desertDungeonMaxHeight))
                    .repeat(RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.desertDungeonSpawnrate));

    public static ConfiguredFeature<?, ?> END_DUNGEONS = RSFeatures.END_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.endDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.endDungeonMaxHeight))
                    .repeat(RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.endDungeonSpawnrate));

    public static ConfiguredFeature<?, ?> NETHER_DUNGEONS = RSFeatures.NETHER_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.netherDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.netherDungeonMaxHeight))
                    .repeat(RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.netherDungeonSpawnrate));

    public static ConfiguredFeature<?, ?> SNOW_DUNGEONS = RSFeatures.SNOW_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.snowDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.snowDungeonMaxHeight))
                    .repeat(RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.snowDungeonSpawnrate));

    public static ConfiguredFeature<?, ?> SWAMP_DUNGEONS = RSFeatures.SWAMP_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.swampDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.swampDungeonMaxHeight))
                    .repeat(RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.swampDungeonSpawnrate));

    public static ConfiguredFeature<?, ?> MUSHROOM_DUNGEONS = RSFeatures.MUSHROOM_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight))
                    .repeat(RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.mushroomDungeonSpawnrate));

    public static ConfiguredFeature<?, ?> JUNGLE_DUNGEONS = RSFeatures.JUNGLE_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.jungleDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.jungleDungeonMaxHeight))
                    .repeat(RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.jungleDungeonSpawnrate));

    public static ConfiguredFeature<?, ?> OCEAN_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))
                    .repeat(RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.oceanDungeonSpawnrate));


    // Wells

    public static ConfiguredFeature<?, ?> BADLANDS_WELL = RSFeatures.BADLANDS_WELL.configure(FeatureConfig.DEFAULT)
                    .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                    .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.badlandsWellSpawnrate);

    public static ConfiguredFeature<?, ?> NETHER_WELL = RSFeatures.NETHER_WELL.configure(FeatureConfig.DEFAULT)
                    .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(30, 0, 91)))
                    .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.netherWellSpawnrate);

    public static ConfiguredFeature<?, ?> SNOW_WELL = RSFeatures.SNOW_WELL.configure(FeatureConfig.DEFAULT)
                    .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                    .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.snowWellSpawnrate);

    public static ConfiguredFeature<?, ?> MOSSY_STONE_WELL = RSFeatures.MOSSY_STONE_WELL.configure(FeatureConfig.DEFAULT)
                    .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                    .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.mossyStoneWellSpawnrate);

    public static ConfiguredFeature<?, ?> FOREST_WELL = RSFeatures.FOREST_WELL.configure(FeatureConfig.DEFAULT)
                    .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                    .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.forestWellSpawnrate);

    // Misc

    private static final TreeFeatureConfig TREE_FEATURE_CONFIG = (new TreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
            new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()),
            new BlobFoliagePlacer(UniformIntDistribution.of(3), UniformIntDistribution.of(0), 3),
            new StraightTrunkPlacer(5, 3, 0),
            new TwoLayersFeatureSize(1, 0, 1))).build();

    public static ConfiguredFeature<?, ?> HORNED_SWAMP_TREE_UNCOMMON = RSFeatures.HORNED_SWAMP_TREE.configure(TREE_FEATURE_CONFIG)
            .decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.7F, 1))
                    .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP));

    public static ConfiguredFeature<?, ?> HORNED_SWAMP_TREE_COMMON = RSFeatures.HORNED_SWAMP_TREE.configure(TREE_FEATURE_CONFIG)
            .decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(5, 0.8F, 1))
            .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP));

    public static ConfiguredFeature<?, ?> BOULDER_GIANT = RSFeatures.BOULDER_GIANT.configure(FeatureConfig.DEFAULT)
            .decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(
                    (int) RepurposedStructures.RSAllConfig.RSMainConfig.misc.giantBouldersPerChunk, // Intentional cast. Need to floor to whole number
                    (float) (RepurposedStructures.RSAllConfig.RSMainConfig.misc.giantBouldersPerChunk - ((int)RepurposedStructures.RSAllConfig.RSMainConfig.misc.giantBouldersPerChunk)),
                    1))
                    .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP));

    public static ConfiguredFeature<?, ?> BOULDER_TINY = RSFeatures.BOULDER_TINY.configure(FeatureConfig.DEFAULT)
            .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
            .repeat(2);

    public static ConfiguredFeature<?, ?> SWAMP_VILLAGE_VINES = RSFeatures.SWAMP_VILLAGE_VINES.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_VINE_PLACEMENT.configure(new RangeDecoratorConfig(30, 0,8))
                    .repeat(16));

    public static ConfiguredFeature<?, ?> JUNGLE_VILLAGE_VINES = RSFeatures.JUNGLE_STRUCTURES_VINES.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_VINE_PLACEMENT.configure(new RangeDecoratorConfig(30, 0, 8))
                    .repeat(16));
    public static ConfiguredFeature<?, ?> JUNGLE_FORTRESS_VINES = RSFeatures.JUNGLE_STRUCTURES_VINES.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_VINE_PLACEMENT.configure(new RangeDecoratorConfig(40, 0, 2))
                    .repeat(20));


    public static ConfiguredFeature<?, ?> FORTRESS_BREAKAGE = RSFeatures.FORTRESS_BREAKAGE.configure(FeatureConfig.DEFAULT)
            .decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(1, 0.2F, 1))
                    .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP));

    public static ConfiguredFeature<?, ?> STONEBRICK_STRONGHOLD_CHAINS = RSFeatures.STRONGHOLD_CHAINS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    5,
                    0,
                    Math.max(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdMaxHeight,
                                    RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdMinHeight+1)+15))
                    .repeat(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdChainSpawnrate));

    public static ConfiguredFeature<?, ?> NETHER_STRONGHOLD_CHAINS = RSFeatures.STRONGHOLD_CHAINS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    5,
                    0,
                    Math.max(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdMaxHeight,
                                    RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdMinHeight+1)+15))
                    .repeat(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdChainSpawnrate));

    public static ConfiguredFeature<?, ?> LILY_OF_THE_VALLEY_FEATURE = Feature.field_26361.configure(
            (new RandomPatchFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.LILY_OF_THE_VALLEY.getDefaultState()),
                    SimpleBlockPlacer.INSTANCE))
            .tries(64)
            .build());

    public static ConfiguredFeature<?, ?> CRIMSON_FUNGI_NOT_PLANTED = Feature.HUGE_FUNGUS.configure(HugeFungusFeatureConfig.CRIMSON_FUNGUS_NOT_PLANTED_CONFIG);

    public static ConfiguredFeature<?, ?> WARPED_FUNGI_NOT_PLANTED = Feature.HUGE_FUNGUS.configure(HugeFungusFeatureConfig.WARPED_FUNGUS_NOT_PLANTED_CONFIG);

    public static ConfiguredFeature<?, ?> COBBLESTONE_PATCH = Feature.BLOCK_PILE.configure(new BlockPileFeatureConfig(
            new SimpleBlockStateProvider(Blocks.COBBLESTONE.getDefaultState())));

    public static void registerConfiguredFeatures() {
        MutableRegistry<ConfiguredFeature<?, ?>> registry = (MutableRegistry<ConfiguredFeature<?, ?>>) BuiltinRegistries.CONFIGURED_FEATURE;

        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_badlands"), BADLANDS_DUNGEONS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_dark_forest"), DARK_FOREST_DUNGEONS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_desert"), DESERT_DUNGEONS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_end"), END_DUNGEONS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_nether"), NETHER_DUNGEONS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_snow"), SNOW_DUNGEONS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_swamp"), SWAMP_DUNGEONS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_mushroom"), MUSHROOM_DUNGEONS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_jungle"), JUNGLE_DUNGEONS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_ocean"), OCEAN_DUNGEONS);

        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "well_badlands"), BADLANDS_WELL);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "well_nether"), NETHER_WELL);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "well_snow"), SNOW_WELL);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "well_mossy_stone"), MOSSY_STONE_WELL);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "well_forest"), FOREST_WELL);

        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "boulder_giant"), BOULDER_GIANT);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "boulder_tiny"), BOULDER_TINY);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "horned_swamp_tree_uncommon"), HORNED_SWAMP_TREE_UNCOMMON);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "horned_swamp_tree_common"), HORNED_SWAMP_TREE_COMMON);

        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "swamp_village_vines"), SWAMP_VILLAGE_VINES);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "jungle_fortress_vines"), JUNGLE_FORTRESS_VINES);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "jungle_village_vines"), JUNGLE_VILLAGE_VINES);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "fortress_breakage"), FORTRESS_BREAKAGE);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "stonebrick_stronghold_chains"), STONEBRICK_STRONGHOLD_CHAINS);

        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "nether_stronghold_chains"), NETHER_STRONGHOLD_CHAINS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "lily_of_the_valley"), LILY_OF_THE_VALLEY_FEATURE);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "crimson_fungi_not_planted"), CRIMSON_FUNGI_NOT_PLANTED);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "warped_fungi_not_planted"), WARPED_FUNGI_NOT_PLANTED);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "cobblestone_patch"), COBBLESTONE_PATCH);

    }
}
