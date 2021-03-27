package com.telepathicgrunt.repurposedstructures.modinit;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtDungeonConfig;
import net.minecraft.block.*;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

import java.util.List;
import java.util.Optional;


public class RSConfiguredFeatures {

    // Dungeons

    private static NbtDungeonConfig getDefaultNbtDungeonConfig(String dungeonType){
        return getNbtDungeonConfig(dungeonType, dungeonType);
    }

    private static NbtDungeonConfig getNbtDungeonConfig(String dungeonType, String spawnerType){
        return new NbtDungeonConfig(
                false,1, 14, 2,
                false, Optional.empty(), 0, Blocks.CHEST.getDefaultState(),
                new ResourceLocation(RepurposedStructures.MODID, "chests/dungeon/"+dungeonType),
                new ResourceLocation(RepurposedStructures.MODID, "dungeon_"+spawnerType),
                new ResourceLocation(RepurposedStructures.MODID, "dungeons/"+dungeonType),
                ImmutableList.of(
                        Pair.of(new ResourceLocation(RepurposedStructures.MODID, "dungeons/"+dungeonType+"_1"), 1),
                        Pair.of(new ResourceLocation(RepurposedStructures.MODID, "dungeons/"+dungeonType+"_2"), 1),
                        Pair.of(new ResourceLocation(RepurposedStructures.MODID, "dungeons/"+dungeonType+"_3"), 1)
                ));
    }

    private static final List<BlockState> UNREPLACEABLE_OCEAN_DUNGEON_BLOCKS = ImmutableList.of(
            Blocks.DARK_PRISMARINE.getDefaultState(),
            Blocks.DARK_PRISMARINE_STAIRS.getDefaultState().with(StairsBlock.WATERLOGGED, true).with(StairsBlock.FACING, Direction.SOUTH),
            Blocks.DARK_PRISMARINE_STAIRS.getDefaultState().with(StairsBlock.WATERLOGGED, true).with(StairsBlock.FACING, Direction.NORTH),
            Blocks.DARK_PRISMARINE_STAIRS.getDefaultState().with(StairsBlock.WATERLOGGED, true).with(StairsBlock.FACING, Direction.EAST),
            Blocks.DARK_PRISMARINE_STAIRS.getDefaultState().with(StairsBlock.WATERLOGGED, true).with(StairsBlock.FACING, Direction.WEST),
            Blocks.PRISMARINE_WALL.getDefaultState().with(WallBlock.WATERLOGGED, true).with(WallBlock.UP, true),
            Blocks.PRISMARINE_WALL.getDefaultState().with(WallBlock.WATERLOGGED, true).with(WallBlock.UP, true)
                    .with(WallBlock.WEST_SHAPE, WallHeight.LOW).with(WallBlock.EAST_SHAPE, WallHeight.LOW).with(WallBlock.NORTH_SHAPE, WallHeight.LOW).with(WallBlock.SOUTH_SHAPE, WallHeight.LOW)
    );

    private static NbtDungeonConfig getOceanNbtDungeonConfig(String spawnerType){
        return new NbtDungeonConfig(
                false,1, 55, 2,
                true, Optional.of(UNREPLACEABLE_OCEAN_DUNGEON_BLOCKS),
                -2, Blocks.CHEST.getDefaultState(),
                new ResourceLocation(RepurposedStructures.MODID, "chests/dungeon/ocean"),
                new ResourceLocation(RepurposedStructures.MODID, "dungeon_"+spawnerType),
                new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean"),
                ImmutableList.of(
                        Pair.of(new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_1"), 1),
                        Pair.of(new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_2"), 1),
                        Pair.of(new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_3"), 1)
                ));
    }


    private static final NbtDungeonConfig BADLANDS_DUNGEON_CONFIG = getDefaultNbtDungeonConfig("badlands");
    public static ConfiguredFeature<?, ?> BADLANDS_DUNGEONS = RSFeatures.BADLANDS_DUNGEONS.get().configure(BADLANDS_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.badlandsDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.badlandsDungeonMaxHeight.get()))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.badlandsDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig DARK_FOREST_DUNGEON_CONFIG = getDefaultNbtDungeonConfig("dark_forest");
    public static ConfiguredFeature<?, ?> DARK_FOREST_DUNGEONS = RSFeatures.DARK_FOREST_DUNGEONS.get().configure(DARK_FOREST_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.darkForestDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.darkForestDungeonMaxHeight.get()))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.darkForestDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig DESERT_DUNGEON_CONFIG = getDefaultNbtDungeonConfig("desert");
    public static ConfiguredFeature<?, ?> DESERT_DUNGEONS = RSFeatures.DESERT_DUNGEONS.get().configure(DESERT_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.desertDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.desertDungeonMaxHeight.get()))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.desertDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig END_DUNGEON_CONFIG = new NbtDungeonConfig(
            false,1, 20, 2,
            false, Optional.empty(), 0, Blocks.SHULKER_BOX.getDefaultState(),
            new ResourceLocation(RepurposedStructures.MODID, "chests/dungeon/end"),
            new ResourceLocation(RepurposedStructures.MODID, "dungeon_end"),
            new ResourceLocation(RepurposedStructures.MODID, "dungeons/end"),
            ImmutableList.of(
                    Pair.of(new ResourceLocation(RepurposedStructures.MODID, "dungeons/end_1"), 1),
                    Pair.of(new ResourceLocation(RepurposedStructures.MODID, "dungeons/end_2"), 1),
                    Pair.of(new ResourceLocation(RepurposedStructures.MODID, "dungeons/end_3"), 1)
            ));
    public static ConfiguredFeature<?, ?> END_DUNGEONS = RSFeatures.END_DUNGEONS.get().configure(END_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.endDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.endDungeonMaxHeight.get()))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.endDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig NETHER_DUNGEON_CONFIG = getDefaultNbtDungeonConfig("nether");
    public static ConfiguredFeature<?, ?> NETHER_DUNGEONS = RSFeatures.NETHER_DUNGEONS.get().configure(NETHER_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.netherDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.netherDungeonMaxHeight.get()))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.netherDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig SNOW_DUNGEON_CONFIG = getDefaultNbtDungeonConfig("snow");
    public static ConfiguredFeature<?, ?> SNOW_DUNGEONS = RSFeatures.SNOW_DUNGEONS.get().configure(SNOW_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.snowDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.snowDungeonMaxHeight.get()))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.snowDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig SWAMP_DUNGEON_CONFIG = getDefaultNbtDungeonConfig("swamp");
    public static ConfiguredFeature<?, ?> SWAMP_DUNGEONS = RSFeatures.SWAMP_DUNGEONS.get().configure(SWAMP_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.swampDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.swampDungeonMaxHeight.get()))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.swampDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig MUSHROOM_HIGH_DUNGEON_CONFIG = getNbtDungeonConfig("mushroom", "mushroom_high");
    public static ConfiguredFeature<?, ?> MUSHROOM_HIGH_DUNGEONS = RSFeatures.MUSHROOM_DUNGEONS.get().configure(MUSHROOM_HIGH_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    Math.max(63, RepurposedStructures.RSDungeonsConfig.mushroomDungeonMinHeight.get()), 0,
                    RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get()))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(Math.round(Math.max(0,
                            ((RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get() - 62F) /
                                    (RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get() - RepurposedStructures.RSDungeonsConfig.mushroomDungeonMinHeight.get())) *
                                    RepurposedStructures.RSDungeonsConfig.mushroomDungeonAttemptsPerChunk.get()
                    ))))));

    private static final NbtDungeonConfig MUSHROOM_LOW_DUNGEON_CONFIG = getNbtDungeonConfig("mushroom", "mushroom_low");
    public static ConfiguredFeature<?, ?> MUSHROOM_LOW_DUNGEONS = RSFeatures.MUSHROOM_DUNGEONS.get().configure(MUSHROOM_LOW_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.mushroomDungeonMinHeight.get(), 0,
                    Math.min(62, RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get())))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(Math.round(Math.max(0,
                            ((62F - RepurposedStructures.RSDungeonsConfig.mushroomDungeonMinHeight.get()) /
                                    (RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get() - RepurposedStructures.RSDungeonsConfig.mushroomDungeonMinHeight.get())) *
                                    RepurposedStructures.RSDungeonsConfig.mushroomDungeonAttemptsPerChunk.get()
                    ))))));

    private static final NbtDungeonConfig JUNGLE_DUNGEON_CONFIG = getDefaultNbtDungeonConfig("jungle");
    public static ConfiguredFeature<?, ?> JUNGLE_DUNGEONS = RSFeatures.JUNGLE_DUNGEONS.get().configure(JUNGLE_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.jungleDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.jungleDungeonMaxHeight.get()))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.jungleDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig OCEAN_DUNGEON_CONFIG = new NbtDungeonConfig(
            false,1, 55, 2,
            true, Optional.empty(), -2, Blocks.CHEST.getDefaultState(),
            new ResourceLocation(RepurposedStructures.MODID, "chests/dungeon/ocean"),
            new ResourceLocation(RepurposedStructures.MODID, "dungeon_ocean_neutral"),
            new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean"),
            ImmutableList.of(
                    Pair.of(new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_1"), 1),
                    Pair.of(new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_2"), 1),
                    Pair.of(new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_3"), 1)
            ));
    public static ConfiguredFeature<?, ?> OCEAN_NEUTRAL_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.get().configure(getOceanNbtDungeonConfig("ocean_neutral"))
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMaxHeight.get()))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()))));

    public static ConfiguredFeature<?, ?> OCEAN_COLD_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.get().configure(getOceanNbtDungeonConfig("ocean_cold"))
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMaxHeight.get()))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()))));

    public static ConfiguredFeature<?, ?> OCEAN_FROZEN_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.get().configure(getOceanNbtDungeonConfig("ocean_frozen"))
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMaxHeight.get()))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()))));

    public static ConfiguredFeature<?, ?> OCEAN_LUKEWARM_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.get().configure(getOceanNbtDungeonConfig("ocean_lukewarm"))
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMaxHeight.get()))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()))));

    public static ConfiguredFeature<?, ?> OCEAN_WARM_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.get().configure(getOceanNbtDungeonConfig("ocean_warm"))
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMaxHeight.get()))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()))));


    // Wells

    public static ConfiguredFeature<?, ?> BADLANDS_WELL = RSFeatures.BADLANDS_WELL.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(Features.Placements.SQUARE_HEIGHTMAP).applyChance(RepurposedStructures.RSWellsConfig.badlandsWellRarityPerChunk.get());

    public static ConfiguredFeature<?, ?> NETHER_WELL = RSFeatures.NETHER_WELL.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(Placement.RANGE.configure(new TopSolidRangeConfig(30, 0, 91))).applyChance(RepurposedStructures.RSWellsConfig.netherWellRarityPerChunk.get());

    public static ConfiguredFeature<?, ?> SNOW_WELL = RSFeatures.SNOW_WELL.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(Features.Placements.SQUARE_HEIGHTMAP).applyChance(RepurposedStructures.RSWellsConfig.snowWellRarityPerChunk.get());

    public static ConfiguredFeature<?, ?> MOSSY_STONE_WELL = RSFeatures.MOSSY_STONE_WELL.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(Features.Placements.SQUARE_HEIGHTMAP).applyChance(RepurposedStructures.RSWellsConfig.mossyStoneWellRarityPerChunk.get());

    public static ConfiguredFeature<?, ?> FOREST_WELL = RSFeatures.FOREST_WELL.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
                    .decorate(Features.Placements.SQUARE_HEIGHTMAP)
                    .applyChance(RepurposedStructures.RSWellsConfig.forestWellRarityPerChunk.get());

    // Misc
    public static ConfiguredFeature<?, ?> BOULDER_GIANT = RSFeatures.BOULDER_GIANT.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(Features.Placements.SQUARE_HEIGHTMAP)
            .decorate(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(
                    (int) RepurposedStructures.RSMainConfig.giantBouldersPerChunk.get().doubleValue(), // Intentional cast. Need to floor to whole number
                    (float) (RepurposedStructures.RSMainConfig.giantBouldersPerChunk.get() - ((int)RepurposedStructures.RSMainConfig.giantBouldersPerChunk.get().doubleValue())),
                    1)));

    public static ConfiguredFeature<?, ?> BOULDER_TINY = RSFeatures.BOULDER_TINY.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(Features.Placements.SQUARE_HEIGHTMAP)
            .repeat(2);

    public static ConfiguredFeature<?, ?> SWAMP_VILLAGE_VINES = RSFeatures.SWAMP_VILLAGE_VINES.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(RSPlacements.RS_VINE_PLACEMENT.get().configure(new TopSolidRangeConfig(30, 0,8))
            .repeat(16));

    public static ConfiguredFeature<?, ?> JUNGLE_VILLAGE_VINES = RSFeatures.JUNGLE_STRUCTURES_VINES.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(RSPlacements.RS_VINE_PLACEMENT.get().configure(new TopSolidRangeConfig(30, 0, 8))
            .repeat(16));

    public static ConfiguredFeature<?, ?> JUNGLE_FORTRESS_VINES = RSFeatures.JUNGLE_STRUCTURES_VINES.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(RSPlacements.RS_VINE_PLACEMENT.get().configure(new TopSolidRangeConfig(40, 0, 2))
            .repeat(20));


    public static ConfiguredFeature<?, ?> FORTRESS_BREAKAGE = RSFeatures.FORTRESS_BREAKAGE.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(Features.Placements.SQUARE_HEIGHTMAP)
            .decorate(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.2F, 1)));

    public static ConfiguredFeature<?, ?> STONEBRICK_STRONGHOLD_CHAINS = RSFeatures.STRONGHOLD_CHAINS.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    5,
                    0,
                    Math.max(RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdMaxHeight.get(),
                                    RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdMinHeight.get()+1)+15))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdChainSpawnrate.get()))));

    public static ConfiguredFeature<?, ?> NETHER_STRONGHOLD_CHAINS = RSFeatures.STRONGHOLD_CHAINS.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    5,
                    0,
                    Math.max(RepurposedStructures.RSStrongholdsConfig.netherStrongholdMaxHeight.get(),
                                    RepurposedStructures.RSStrongholdsConfig.netherStrongholdMinHeight.get()+1)+15))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSStrongholdsConfig.netherStrongholdChainSpawnrate.get()))));

    public static ConfiguredFeature<?, ?> LILY_OF_THE_VALLEY_FEATURE = Feature.field_26361.configure(
            (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.LILY_OF_THE_VALLEY.getDefaultState()), SimpleBlockPlacer.INSTANCE))
            .tries(64)
            .build());

    public static ConfiguredFeature<?, ?> CRIMSON_FUNGI_NOT_PLANTED = Feature.HUGE_FUNGUS.configure(HugeFungusConfig.CRIMSON_FUNGUS_NOT_PLANTED_CONFIG);

    public static ConfiguredFeature<?, ?> WARPED_FUNGI_NOT_PLANTED = Feature.HUGE_FUNGUS.configure(HugeFungusConfig.WARPED_FUNGUS_NOT_PLANTED_CONFIG);

    public static ConfiguredFeature<?, ?> COBBLESTONE_PATCH = Feature.BLOCK_PILE.configure(new BlockStateProvidingFeatureConfig(new SimpleBlockStateProvider(Blocks.COBBLESTONE.getDefaultState())));

    public static void registerConfiguredFeatures() {
        Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_badlands"), BADLANDS_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_dark_forest"), DARK_FOREST_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_desert"), DESERT_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_end"), END_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_nether"), NETHER_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_snow"), SNOW_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_swamp"), SWAMP_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_mushroom"), MUSHROOM_HIGH_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_mushroom_high"), MUSHROOM_LOW_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_jungle_low"), JUNGLE_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_neutral_ocean"), OCEAN_NEUTRAL_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_lukewarm_ocean"), OCEAN_LUKEWARM_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_frozen_ocean"), OCEAN_FROZEN_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_cold_ocean"), OCEAN_COLD_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_warm_ocean"), OCEAN_WARM_DUNGEONS);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_badlands"), BADLANDS_WELL);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_nether"), NETHER_WELL);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_snow"), SNOW_WELL);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_mossy_stone"), MOSSY_STONE_WELL);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_forest"), FOREST_WELL);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "boulder_giant"), BOULDER_GIANT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "boulder_tiny"), BOULDER_TINY);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "swamp_village_vines"), SWAMP_VILLAGE_VINES);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_fortress_vines"), JUNGLE_FORTRESS_VINES);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_village_vines"), JUNGLE_VILLAGE_VINES);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "fortress_breakage"), FORTRESS_BREAKAGE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "stonebrick_stronghold_chains"), STONEBRICK_STRONGHOLD_CHAINS);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_stronghold_chains"), NETHER_STRONGHOLD_CHAINS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "lily_of_the_valley"), LILY_OF_THE_VALLEY_FEATURE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "crimson_fungi_not_planted"), CRIMSON_FUNGI_NOT_PLANTED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "warped_fungi_not_planted"), WARPED_FUNGI_NOT_PLANTED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "cobblestone_patch"), COBBLESTONE_PATCH);

    }
}
