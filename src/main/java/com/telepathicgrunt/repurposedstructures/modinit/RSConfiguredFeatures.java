package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
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

    // Dungeons

    public static ConfiguredFeature<?, ?> BADLANDS_DUNGEONS = RSFeatures.BADLANDS_DUNGEONS.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.badlandsDungeonMinHeight.get(),
                    0,
                    RepurposedStructures.RSDungeonsConfig.badlandsDungeonMaxHeight.get()))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.badlandsDungeonAttemptsPerChunk.get()))));

    public static ConfiguredFeature<?, ?> DARK_FOREST_DUNGEONS = RSFeatures.DARK_FOREST_DUNGEONS.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.darkForestDungeonMinHeight.get(),
                    0,
                    RepurposedStructures.RSDungeonsConfig.darkForestDungeonMaxHeight.get()))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.darkForestDungeonAttemptsPerChunk.get()))));

    public static ConfiguredFeature<?, ?> DESERT_DUNGEONS = RSFeatures.DESERT_DUNGEONS.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.desertDungeonMinHeight.get(),
                    0,
                    RepurposedStructures.RSDungeonsConfig.desertDungeonMaxHeight.get()))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.desertDungeonAttemptsPerChunk.get()))));

    public static ConfiguredFeature<?, ?> END_DUNGEONS = RSFeatures.END_DUNGEONS.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.endDungeonMinHeight.get(),
                    0,
                    RepurposedStructures.RSDungeonsConfig.endDungeonMaxHeight.get()))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.endDungeonAttemptsPerChunk.get()))));

    public static ConfiguredFeature<?, ?> NETHER_DUNGEONS = RSFeatures.NETHER_DUNGEONS.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.netherDungeonMinHeight.get(),
                    0,
                    RepurposedStructures.RSDungeonsConfig.netherDungeonMaxHeight.get()))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.netherDungeonAttemptsPerChunk.get()))));

    public static ConfiguredFeature<?, ?> SNOW_DUNGEONS = RSFeatures.SNOW_DUNGEONS.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.snowDungeonMinHeight.get(),
                    0,
                    RepurposedStructures.RSDungeonsConfig.snowDungeonMaxHeight.get()))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.snowDungeonAttemptsPerChunk.get()))));

    public static ConfiguredFeature<?, ?> SWAMP_DUNGEONS = RSFeatures.SWAMP_DUNGEONS.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.swampDungeonMinHeight.get(),
                    0,
                    RepurposedStructures.RSDungeonsConfig.swampDungeonMaxHeight.get()))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.swampDungeonAttemptsPerChunk.get()))));

    public static ConfiguredFeature<?, ?> MUSHROOM_DUNGEONS = RSFeatures.MUSHROOM_DUNGEONS.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.mushroomDungeonMinHeight.get(),
                    0,
                    RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get()))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.mushroomDungeonAttemptsPerChunk.get()))));

    public static ConfiguredFeature<?, ?> JUNGLE_DUNGEONS = RSFeatures.JUNGLE_DUNGEONS.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.jungleDungeonMinHeight.get(),
                    0,
                    RepurposedStructures.RSDungeonsConfig.jungleDungeonMaxHeight.get()))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.get().configure(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.jungleDungeonAttemptsPerChunk.get()))));

    public static ConfiguredFeature<?, ?> OCEAN_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.get().configure(IFeatureConfig.NO_FEATURE_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.get().configure(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMinHeight.get(),
                    0,
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
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_mushroom"), MUSHROOM_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_jungle"), JUNGLE_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_ocean"), OCEAN_DUNGEONS);

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
