package com.telepathicgrunt.repurposedstructures.modinit;

import com.google.common.collect.ImmutableList;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtDungeonConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.enums.WallShape;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
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
    private static final Identifier EMPTY_ID = new Identifier("minecraft:empty");

    // Dungeons

    // Need this field so we can test to make sure we do not add the dungeons to biomes that
    // we already added an RS dungeon to. (Due to BiomeModification API running separately for each feature)
    public static List<ConfiguredFeature<?, ?>> RS_DUNGEONS = new ArrayList<>();

    private static final List<BlockState> UNREPLACEABLE_OCEAN_DUNGEON_BLOCKS = ImmutableList.of(
            Blocks.DARK_PRISMARINE.getDefaultState(),
            Blocks.DARK_PRISMARINE_STAIRS.getDefaultState().with(StairsBlock.WATERLOGGED, true).with(StairsBlock.FACING, Direction.SOUTH),
            Blocks.DARK_PRISMARINE_STAIRS.getDefaultState().with(StairsBlock.WATERLOGGED, true).with(StairsBlock.FACING, Direction.NORTH),
            Blocks.DARK_PRISMARINE_STAIRS.getDefaultState().with(StairsBlock.WATERLOGGED, true).with(StairsBlock.FACING, Direction.EAST),
            Blocks.DARK_PRISMARINE_STAIRS.getDefaultState().with(StairsBlock.WATERLOGGED, true).with(StairsBlock.FACING, Direction.WEST),
            Blocks.PRISMARINE_WALL.getDefaultState().with(WallBlock.WATERLOGGED, true).with(WallBlock.UP, true),
            Blocks.PRISMARINE_WALL.getDefaultState().with(WallBlock.WATERLOGGED, true).with(WallBlock.UP, true)
                    .with(WallBlock.WEST_SHAPE, WallShape.LOW).with(WallBlock.EAST_SHAPE, WallShape.LOW).with(WallBlock.NORTH_SHAPE, WallShape.LOW).with(WallBlock.SOUTH_SHAPE, WallShape.LOW)
    );
    
    private static final NbtDungeonConfig BADLANDS_DUNGEON_CONFIG = new NbtDungeonConfig("badlands", EMPTY_ID);
    public static ConfiguredFeature<?, ?> BADLANDS_DUNGEONS = RSFeatures.BADLANDS_DUNGEONS.configure(BADLANDS_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.badlandsDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.badlandsDungeonMaxHeight))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.badlandsDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig DARK_FOREST_DUNGEON_CONFIG = new NbtDungeonConfig("dark_forest", new Identifier(RepurposedStructures.MODID, "dungeons/dark_forest_post_process"));
    public static ConfiguredFeature<?, ?> DARK_FOREST_DUNGEONS = RSFeatures.DARK_FOREST_DUNGEONS.configure(DARK_FOREST_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.darkForestDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.darkForestDungeonMaxHeight))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.darkForestDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig DESERT_DUNGEON_CONFIG = new NbtDungeonConfig("desert", EMPTY_ID);
    public static ConfiguredFeature<?, ?> DESERT_DUNGEONS = RSFeatures.DESERT_DUNGEONS.configure(DESERT_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.desertDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.desertDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.desertDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig END_DUNGEON_CONFIG = new NbtDungeonConfig("end", EMPTY_ID, 20, Blocks.SHULKER_BOX.getDefaultState());
    public static ConfiguredFeature<?, ?> END_DUNGEONS = RSFeatures.END_DUNGEONS.configure(END_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.endDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.endDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.endDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig NETHER_DUNGEON_CONFIG = new NbtDungeonConfig("nether", EMPTY_ID);
    public static ConfiguredFeature<?, ?> NETHER_DUNGEONS = RSFeatures.NETHER_DUNGEONS.configure(NETHER_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.netherDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.netherDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.netherDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig SNOW_DUNGEON_CONFIG = new NbtDungeonConfig("snow", EMPTY_ID);
    public static ConfiguredFeature<?, ?> SNOW_DUNGEONS = RSFeatures.SNOW_DUNGEONS.configure(SNOW_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.snowDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.snowDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.snowDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig ICY_DUNGEON_CONFIG = new NbtDungeonConfig("icy", EMPTY_ID);
    public static ConfiguredFeature<?, ?> ICY_DUNGEONS = RSFeatures.ICY_DUNGEONS.configure(ICY_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.icyDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.icyDungeonMaxHeight))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.icyDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig SWAMP_DUNGEON_CONFIG = new NbtDungeonConfig("swamp", EMPTY_ID);
    public static ConfiguredFeature<?, ?> SWAMP_DUNGEONS = RSFeatures.SWAMP_DUNGEONS.configure(SWAMP_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.swampDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.swampDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.swampDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig MUSHROOM_HIGH_DUNGEON_CONFIG = new NbtDungeonConfig("mushroom", "mushroom_high", EMPTY_ID);
    public static ConfiguredFeature<?, ?> MUSHROOM_HIGH_DUNGEONS = RSFeatures.MUSHROOM_DUNGEONS.configure(MUSHROOM_HIGH_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    Math.max(63, RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight), 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(Math.round(Math.max(0,
                    ((RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight - 62F) /
                    (RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight - RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight)) *
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.mushroomDungeonAttemptsPerChunk
            ))))));

    private static final NbtDungeonConfig MUSHROOM_LOW_DUNGEON_CONFIG = new NbtDungeonConfig("mushroom", "mushroom_low", EMPTY_ID);
    public static ConfiguredFeature<?, ?> MUSHROOM_LOW_DUNGEONS = RSFeatures.MUSHROOM_DUNGEONS.configure(MUSHROOM_LOW_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight, 0,
                    Math.min(62, RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight)))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(Math.round(Math.max(0,
                    ((62F - RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight) /
                    (RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight - RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight)) *
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.mushroomDungeonAttemptsPerChunk
            ))))));

    private static final NbtDungeonConfig JUNGLE_DUNGEON_CONFIG = new NbtDungeonConfig("jungle", new Identifier(RepurposedStructures.MODID, "dungeons/jungle_post_process"));
    public static ConfiguredFeature<?, ?> JUNGLE_DUNGEONS = RSFeatures.JUNGLE_DUNGEONS.configure(JUNGLE_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.jungleDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.jungleDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.jungleDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig NEUTRAL_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_neutral", EMPTY_ID, 55, true, Optional.of(UNREPLACEABLE_OCEAN_DUNGEON_BLOCKS), -2);
    public static ConfiguredFeature<?, ?> OCEAN_NEUTRAL_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configure(NEUTRAL_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig COLD_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_cold", EMPTY_ID, 55, true, Optional.of(UNREPLACEABLE_OCEAN_DUNGEON_BLOCKS), -2);
    public static ConfiguredFeature<?, ?> OCEAN_COLD_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configure(COLD_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig FROZEN_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_frozen", EMPTY_ID, 55, true, Optional.of(UNREPLACEABLE_OCEAN_DUNGEON_BLOCKS), -2);
    public static ConfiguredFeature<?, ?> OCEAN_FROZEN_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configure(FROZEN_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig LUKEWARM_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_lukewarm", EMPTY_ID, 55, true, Optional.of(UNREPLACEABLE_OCEAN_DUNGEON_BLOCKS), -2);
    public static ConfiguredFeature<?, ?> OCEAN_LUKEWARM_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configure(LUKEWARM_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig WARM_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_warm", EMPTY_ID, 55, true, Optional.of(UNREPLACEABLE_OCEAN_DUNGEON_BLOCKS), -2);
    public static ConfiguredFeature<?, ?> OCEAN_WARM_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configure(WARM_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));


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
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_badlands"), BADLANDS_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_dark_forest"), DARK_FOREST_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_desert"), DESERT_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_end"), END_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_nether"), NETHER_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_snow"), SNOW_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_icy"), ICY_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_swamp"), SWAMP_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_mushroom_high"), MUSHROOM_HIGH_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_mushroom_low"), MUSHROOM_LOW_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_jungle"), JUNGLE_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_neutral_ocean"), OCEAN_NEUTRAL_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_lukewarm_ocean"), OCEAN_LUKEWARM_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_frozen_ocean"), OCEAN_FROZEN_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_cold_ocean"), OCEAN_COLD_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_warm_ocean"), OCEAN_WARM_DUNGEONS));

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
