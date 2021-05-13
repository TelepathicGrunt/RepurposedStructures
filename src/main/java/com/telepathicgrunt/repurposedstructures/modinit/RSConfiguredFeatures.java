package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtDungeonConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;


public class RSConfiguredFeatures {
    private static final ResourceLocation EMPTY_ID = new ResourceLocation("minecraft:empty");

    // Dungeons
    private static final NbtDungeonConfig BADLANDS_DUNGEON_CONFIG = new NbtDungeonConfig("badlands", EMPTY_ID);
    public static ConfiguredFeature<?, ?> BADLANDS_DUNGEONS = RSFeatures.BADLANDS_DUNGEONS.get().configured(BADLANDS_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.badlandsDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.badlandsDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.badlandsDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig DARK_FOREST_DUNGEON_CONFIG = new NbtDungeonConfig("dark_forest", new ResourceLocation(RepurposedStructures.MODID, "dungeons/dark_forest_post_process"));
    public static ConfiguredFeature<?, ?> DARK_FOREST_DUNGEONS = RSFeatures.DARK_FOREST_DUNGEONS.get().configured(DARK_FOREST_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.darkForestDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.darkForestDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.darkForestDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig DESERT_DUNGEON_CONFIG = new NbtDungeonConfig("desert", EMPTY_ID);
    public static ConfiguredFeature<?, ?> DESERT_DUNGEONS = RSFeatures.DESERT_DUNGEONS.get().configured(DESERT_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.desertDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.desertDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.desertDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig END_DUNGEON_CONFIG = new NbtDungeonConfig("end", EMPTY_ID, 20, Blocks.SHULKER_BOX.defaultBlockState());
    public static ConfiguredFeature<?, ?> END_DUNGEONS = RSFeatures.END_DUNGEONS.get().configured(END_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.endDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.endDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.endDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig NETHER_DUNGEON_CONFIG = new NbtDungeonConfig("nether", EMPTY_ID);
    public static ConfiguredFeature<?, ?> NETHER_DUNGEONS = RSFeatures.NETHER_DUNGEONS.get().configured(NETHER_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.netherDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.netherDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.netherDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig SNOW_DUNGEON_CONFIG = new NbtDungeonConfig("snow", EMPTY_ID);
    public static ConfiguredFeature<?, ?> SNOW_DUNGEONS = RSFeatures.SNOW_DUNGEONS.get().configured(SNOW_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.snowDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.snowDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.snowDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig ICY_DUNGEON_CONFIG = new NbtDungeonConfig("icy", EMPTY_ID);
    public static ConfiguredFeature<?, ?> ICY_DUNGEONS = RSFeatures.ICY_DUNGEONS.get().configured(ICY_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.icyDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.icyDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.icyDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig SWAMP_DUNGEON_CONFIG = new NbtDungeonConfig("swamp", EMPTY_ID);
    public static ConfiguredFeature<?, ?> SWAMP_DUNGEONS = RSFeatures.SWAMP_DUNGEONS.get().configured(SWAMP_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.swampDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.swampDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.swampDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig MUSHROOM_HIGH_DUNGEON_CONFIG = new NbtDungeonConfig("mushroom", "mushroom_high", new ResourceLocation(RepurposedStructures.MODID, "dungeons/mushroom_high"), EMPTY_ID);
    public static ConfiguredFeature<?, ?> MUSHROOM_HIGH_DUNGEONS = RSFeatures.MUSHROOM_DUNGEONS.get().configured(MUSHROOM_HIGH_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    Math.max(63, RepurposedStructures.RSDungeonsConfig.mushroomDungeonMinHeight.get()), 0,
                    RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(Math.round(Math.max(0,
                            ((RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get() - 62F) /
                                    (RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get() - RepurposedStructures.RSDungeonsConfig.mushroomDungeonMinHeight.get())) *
                                    RepurposedStructures.RSDungeonsConfig.mushroomDungeonAttemptsPerChunk.get()
                    ))))));

    private static final NbtDungeonConfig MUSHROOM_LOW_DUNGEON_CONFIG = new NbtDungeonConfig("mushroom", "mushroom_low", new ResourceLocation(RepurposedStructures.MODID, "dungeons/mushroom_low"), EMPTY_ID);
    public static ConfiguredFeature<?, ?> MUSHROOM_LOW_DUNGEONS = RSFeatures.MUSHROOM_DUNGEONS.get().configured(MUSHROOM_LOW_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.mushroomDungeonMinHeight.get(), 0,
                    Math.min(62, RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get())))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(Math.round(Math.max(0,
                            ((62F - RepurposedStructures.RSDungeonsConfig.mushroomDungeonMinHeight.get()) /
                                    (RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get() - RepurposedStructures.RSDungeonsConfig.mushroomDungeonMinHeight.get())) *
                                    RepurposedStructures.RSDungeonsConfig.mushroomDungeonAttemptsPerChunk.get()
                    ))))));

    private static final NbtDungeonConfig JUNGLE_DUNGEON_CONFIG = new NbtDungeonConfig("jungle", new ResourceLocation(RepurposedStructures.MODID, "dungeons/jungle_post_process"));
    public static ConfiguredFeature<?, ?> JUNGLE_DUNGEONS = RSFeatures.JUNGLE_DUNGEONS.get().configured(JUNGLE_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.jungleDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.jungleDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.jungleDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig NEUTRAL_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_neutral", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_neutral"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_NEUTRAL_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.get().configured(NEUTRAL_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig COLD_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_cold", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_cold"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_COLD_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.get().configured(COLD_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig FROZEN_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_frozen", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_frozen"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_FROZEN_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.get().configured(FROZEN_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig LUKEWARM_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_lukewarm", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_lukewarm"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_LUKEWARM_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.get().configured(LUKEWARM_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig WARM_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_warm", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_warm"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_WARM_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.get().configured(WARM_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()))));


    // Wells

    public static ConfiguredFeature<?, ?> BADLANDS_WELL = RSFeatures.BADLANDS_WELL.get().configured(IFeatureConfig.NONE)
            .decorated(Features.Placements.HEIGHTMAP_SQUARE).chance(RepurposedStructures.RSWellsConfig.badlandsWellRarityPerChunk.get());

    public static ConfiguredFeature<?, ?> NETHER_WELL = RSFeatures.NETHER_WELL.get().configured(IFeatureConfig.NONE)
            .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(30, 0, 91))).chance(RepurposedStructures.RSWellsConfig.netherWellRarityPerChunk.get());

    public static ConfiguredFeature<?, ?> SNOW_WELL = RSFeatures.SNOW_WELL.get().configured(IFeatureConfig.NONE)
            .decorated(Features.Placements.HEIGHTMAP_SQUARE).chance(RepurposedStructures.RSWellsConfig.snowWellRarityPerChunk.get());

    public static ConfiguredFeature<?, ?> MOSSY_STONE_WELL = RSFeatures.MOSSY_STONE_WELL.get().configured(IFeatureConfig.NONE)
            .decorated(Features.Placements.HEIGHTMAP_SQUARE).chance(RepurposedStructures.RSWellsConfig.mossyStoneWellRarityPerChunk.get());

    public static ConfiguredFeature<?, ?> FOREST_WELL = RSFeatures.FOREST_WELL.get().configured(IFeatureConfig.NONE)
                    .decorated(Features.Placements.HEIGHTMAP_SQUARE)
                    .chance(RepurposedStructures.RSWellsConfig.forestWellRarityPerChunk.get());

    // Misc

    public static ConfiguredFeature<?, ?> SWAMP_VILLAGE_VINES = RSFeatures.SWAMP_VILLAGE_VINES.get().configured(IFeatureConfig.NONE)
            .decorated(RSPlacements.RS_VINE_PLACEMENT.get().configured(new TopSolidRangeConfig(30, 0,8))
            .count(16));

    public static ConfiguredFeature<?, ?> JUNGLE_VILLAGE_VINES = RSFeatures.JUNGLE_STRUCTURES_VINES.get().configured(IFeatureConfig.NONE)
            .decorated(RSPlacements.RS_VINE_PLACEMENT.get().configured(new TopSolidRangeConfig(30, 0, 8))
            .count(16));

    public static ConfiguredFeature<?, ?> JUNGLE_FORTRESS_VINES = RSFeatures.JUNGLE_STRUCTURES_VINES.get().configured(IFeatureConfig.NONE)
            .decorated(RSPlacements.RS_VINE_PLACEMENT.get().configured(new TopSolidRangeConfig(40, 0, 2))
            .count(20));


    public static ConfiguredFeature<?, ?> FORTRESS_BREAKAGE = RSFeatures.FORTRESS_BREAKAGE.get().configured(IFeatureConfig.NONE)
            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
            .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.2F, 1)));

    public static ConfiguredFeature<?, ?> NETHER_STRONGHOLD_CHAINS = RSFeatures.STRONGHOLD_CHAINS.get().configured(IFeatureConfig.NONE)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    5,
                    0,
                    Math.max(RepurposedStructures.RSStrongholdsConfig.netherStrongholdMaxHeight.get(),
                                    RepurposedStructures.RSStrongholdsConfig.netherStrongholdMinHeight.get()+1)+15))
            .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSStrongholdsConfig.netherStrongholdChainSpawnrate.get()))));

    public static ConfiguredFeature<?, ?> LILY_OF_THE_VALLEY_FEATURE = Feature.NO_BONEMEAL_FLOWER.configured(
            (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.LILY_OF_THE_VALLEY.defaultBlockState()), SimpleBlockPlacer.INSTANCE))
            .tries(64)
            .build());

    public static ConfiguredFeature<?, ?> CRIMSON_FUNGI_NOT_PLANTED = Feature.HUGE_FUNGUS.configured(HugeFungusConfig.HUGE_CRIMSON_FUNGI_NOT_PLANTED_CONFIG);

    public static ConfiguredFeature<?, ?> WARPED_FUNGI_NOT_PLANTED = Feature.HUGE_FUNGUS.configured(HugeFungusConfig.HUGE_WARPED_FUNGI_NOT_PLANTED_CONFIG);

    public static ConfiguredFeature<?, ?> COBBLESTONE_PATCH = Feature.BLOCK_PILE.configured(new BlockStateProvidingFeatureConfig(new SimpleBlockStateProvider(Blocks.COBBLESTONE.defaultBlockState())));

    public static ConfiguredFeature<?, ?> WITHER_SKELETON_WITH_BOW = RSFeatures.WITHER_SKELETON_WITH_BOW.get().configured(IFeatureConfig.NONE);

    public static ConfiguredFeature<?, ?> SHULKER_MOB = RSFeatures.SHULKER_MOB.get().configured(IFeatureConfig.NONE);

    public static ConfiguredFeature<?, ?> CHORUS_PLANT = Feature.CHORUS_PLANT.configured(IFeatureConfig.NONE);

    public static void registerConfiguredFeatures() {
        Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_badlands"), BADLANDS_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_dark_forest"), DARK_FOREST_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_desert"), DESERT_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_end"), END_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_nether"), NETHER_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_snow"), SNOW_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_icy"), ICY_DUNGEONS);
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

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "swamp_village_vines"), SWAMP_VILLAGE_VINES);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_fortress_vines"), JUNGLE_FORTRESS_VINES);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_village_vines"), JUNGLE_VILLAGE_VINES);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "fortress_breakage"), FORTRESS_BREAKAGE);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_stronghold_chains"), NETHER_STRONGHOLD_CHAINS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "lily_of_the_valley"), LILY_OF_THE_VALLEY_FEATURE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "crimson_fungi_not_planted"), CRIMSON_FUNGI_NOT_PLANTED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "warped_fungi_not_planted"), WARPED_FUNGI_NOT_PLANTED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "cobblestone_patch"), COBBLESTONE_PATCH);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "wither_skeleton_with_bow"), WITHER_SKELETON_WITH_BOW);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "shulker_mob"), SHULKER_MOB);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "chorus_plant"), CHORUS_PLANT);
    }
}
