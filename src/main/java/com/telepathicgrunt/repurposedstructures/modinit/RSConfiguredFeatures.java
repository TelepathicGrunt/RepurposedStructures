package com.telepathicgrunt.repurposedstructures.modinit;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.configs.NbtDungeonConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class RSConfiguredFeatures {

    // Dungeons
    private static final NbtDungeonConfig BADLANDS_DUNGEON_CONFIG = getDefaultNbtDungeonConfig("badlands");

    private static NbtDungeonConfig getDefaultNbtDungeonConfig(String dungeonType){
        return new NbtDungeonConfig(
                false,1, 14, 2,
                false, Optional.empty(), 0, Blocks.CHEST.getDefaultState(),
                new Identifier(RepurposedStructures.MODID, "chests/dungeon/"+dungeonType),
                new Identifier(RepurposedStructures.MODID, "dungeon_"+dungeonType),
                new Identifier(RepurposedStructures.MODID, "dungeons/"+dungeonType),
                ImmutableList.of(
                        Pair.of(new Identifier(RepurposedStructures.MODID, "dungeons/"+dungeonType+"_1"), 1),
                        Pair.of(new Identifier(RepurposedStructures.MODID, "dungeons/"+dungeonType+"_2"), 1),
                        Pair.of(new Identifier(RepurposedStructures.MODID, "dungeons/"+dungeonType+"_3"), 1)
                ));
    }

    public static ConfiguredFeature<?, ?> test = RSFeatures.test.configure(BADLANDS_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.badlandsDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.badlandsDungeonMaxHeight))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.badlandsDungeonAttemptsPerChunk))));

    public static ConfiguredFeature<?, ?> BADLANDS_DUNGEONS = RSFeatures.BADLANDS_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.badlandsDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.badlandsDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.badlandsDungeonAttemptsPerChunk))));

    public static ConfiguredFeature<?, ?> DARK_FOREST_DUNGEONS = RSFeatures.DARK_FOREST_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.darkForestDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.darkForestDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.darkForestDungeonAttemptsPerChunk))));

    public static ConfiguredFeature<?, ?> DESERT_DUNGEONS = RSFeatures.DESERT_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.desertDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.desertDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.desertDungeonAttemptsPerChunk))));

    public static ConfiguredFeature<?, ?> END_DUNGEONS = RSFeatures.END_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.endDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.endDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.endDungeonAttemptsPerChunk))));

    public static ConfiguredFeature<?, ?> NETHER_DUNGEONS = RSFeatures.NETHER_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.netherDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.netherDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.netherDungeonAttemptsPerChunk))));

    public static ConfiguredFeature<?, ?> SNOW_DUNGEONS = RSFeatures.SNOW_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.snowDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.snowDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.snowDungeonAttemptsPerChunk))));

    public static ConfiguredFeature<?, ?> SWAMP_DUNGEONS = RSFeatures.SWAMP_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.swampDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.swampDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.swampDungeonAttemptsPerChunk))));

    public static ConfiguredFeature<?, ?> MUSHROOM_DUNGEONS = RSFeatures.MUSHROOM_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.mushroomDungeonAttemptsPerChunk))));

    public static ConfiguredFeature<?, ?> JUNGLE_DUNGEONS = RSFeatures.JUNGLE_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.jungleDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.jungleDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.jungleDungeonAttemptsPerChunk))));

    public static ConfiguredFeature<?, ?> OCEAN_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight,
                    0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));

    public static List<ConfiguredFeature<?, ?>> RS_DUNGEONS = new ArrayList<>();

    // Wells

    public static ConfiguredFeature<?, ?> BADLANDS_WELL = RSFeatures.BADLANDS_WELL.configure(FeatureConfig.DEFAULT)
                    .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                    .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.badlandsWellRarityPerChunk);

    public static ConfiguredFeature<?, ?> NETHER_WELL = RSFeatures.NETHER_WELL.configure(FeatureConfig.DEFAULT)
                    .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(30, 0, 91)))
                    .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.netherWellRarityPerChunk);

    public static ConfiguredFeature<?, ?> SNOW_WELL = RSFeatures.SNOW_WELL.configure(FeatureConfig.DEFAULT)
                    .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                    .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.snowWellRarityPerChunk);

    public static ConfiguredFeature<?, ?> MOSSY_STONE_WELL = RSFeatures.MOSSY_STONE_WELL.configure(FeatureConfig.DEFAULT)
                    .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                    .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.mossyStoneWellRarityPerChunk);

    public static ConfiguredFeature<?, ?> FOREST_WELL = RSFeatures.FOREST_WELL.configure(FeatureConfig.DEFAULT)
                    .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                    .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.forestWellRarityPerChunk);

    public static List<ConfiguredFeature<?, ?>> RS_WELLS = new ArrayList<>();

    // Misc

    public static ConfiguredFeature<?, ?> BOULDER_GIANT = RSFeatures.BOULDER_GIANT.configure(FeatureConfig.DEFAULT)
            .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
            .decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(
                    (int) RepurposedStructures.RSAllConfig.RSMainConfig.misc.giantBouldersPerChunk, // Intentional cast. Need to floor to whole number
                    (float) (RepurposedStructures.RSAllConfig.RSMainConfig.misc.giantBouldersPerChunk - ((int)RepurposedStructures.RSAllConfig.RSMainConfig.misc.giantBouldersPerChunk)),
                    1)));

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
            .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
            .decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(1, 0.2F, 1)));

    public static ConfiguredFeature<?, ?> STONEBRICK_STRONGHOLD_CHAINS = RSFeatures.STRONGHOLD_CHAINS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    5,
                    0,
                    Math.max(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdMaxHeight,
                                    RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdMinHeight+1)+15))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdChainSpawnrate))));

    public static ConfiguredFeature<?, ?> NETHER_STRONGHOLD_CHAINS = RSFeatures.STRONGHOLD_CHAINS.configure(FeatureConfig.DEFAULT)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    5,
                    0,
                    Math.max(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdMaxHeight,
                                    RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdMinHeight+1)+15))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdChainSpawnrate))));

    public static ConfiguredFeature<?, ?> LILY_OF_THE_VALLEY_FEATURE = Feature.FLOWER.configure(
            (new RandomPatchFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.LILY_OF_THE_VALLEY.getDefaultState()),
                    SimpleBlockPlacer.INSTANCE))
            .tries(64)
            .build());

    public static ConfiguredFeature<?, ?> CRIMSON_FUNGI_NOT_PLANTED = Feature.HUGE_FUNGUS.configure(HugeFungusFeatureConfig.CRIMSON_FUNGUS_NOT_PLANTED_CONFIG);

    public static ConfiguredFeature<?, ?> WARPED_FUNGI_NOT_PLANTED = Feature.HUGE_FUNGUS.configure(HugeFungusFeatureConfig.WARPED_FUNGUS_NOT_PLANTED_CONFIG);

    public static ConfiguredFeature<?, ?> COBBLESTONE_PATCH = Feature.BLOCK_PILE.configure(new BlockPileFeatureConfig(new SimpleBlockStateProvider(Blocks.COBBLESTONE.getDefaultState())));

    public static void registerConfiguredFeatures() {
        Registry<ConfiguredFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_FEATURE;
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "test"), test));

        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_badlands"), BADLANDS_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_dark_forest"), DARK_FOREST_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_desert"), DESERT_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_end"), END_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_nether"), NETHER_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_snow"), SNOW_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_swamp"), SWAMP_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_mushroom"), MUSHROOM_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_jungle"), JUNGLE_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_ocean"), OCEAN_DUNGEONS));

        RS_WELLS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "well_badlands"), BADLANDS_WELL));
        RS_WELLS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "well_nether"), NETHER_WELL));
        RS_WELLS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "well_snow"), SNOW_WELL));
        RS_WELLS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "well_mossy_stone"), MOSSY_STONE_WELL));
        RS_WELLS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "well_forest"), FOREST_WELL));

        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "boulder_giant"), BOULDER_GIANT);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "boulder_tiny"), BOULDER_TINY);

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
