package com.telepathicgrunt.repurposedstructures.modinit;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.features.configs.GenericMobConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtDungeonConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtFeatureConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndRangeConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetChanceConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetLengthRangeConfig;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateProvidingFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.HugeFungusConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

import java.util.ArrayList;
import java.util.List;


public final class RSConfiguredFeatures {
    private RSConfiguredFeatures() {}

    private static final ResourceLocation EMPTY_ID = new ResourceLocation("minecraft:empty");

    // Need this field for dimension/biome blacklisting
    public static List<ConfiguredFeature<?, ?>> RS_DUNGEONS = new ArrayList<>();

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

    private static final NbtDungeonConfig END_DUNGEON_CONFIG = new NbtDungeonConfig("end", EMPTY_ID, 20, RepurposedStructures.RSDungeonsConfig.shulkerBoxInEndDungeons.get() ? Blocks.SHULKER_BOX.defaultBlockState() : Blocks.CHEST.defaultBlockState());
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

    private static final NbtDungeonConfig MUSHROOM_HIGH_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_mushroom_high"), "mushroom", "mushroom_high", new ResourceLocation(RepurposedStructures.MODID, "dungeons/mushroom_high"), EMPTY_ID);
    public static ConfiguredFeature<?, ?> MUSHROOM_HIGH_DUNGEONS = RSFeatures.MUSHROOM_DUNGEONS.get().configured(MUSHROOM_HIGH_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    Math.max(63, RepurposedStructures.RSDungeonsConfig.mushroomDungeonMinHeight.get()), 0,
                    RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(Math.round(Math.max(0,
                            ((RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get() - 62F) /
                                    (RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get() - RepurposedStructures.RSDungeonsConfig.mushroomDungeonMinHeight.get())) *
                                    RepurposedStructures.RSDungeonsConfig.mushroomDungeonAttemptsPerChunk.get()
                    ))))));

    private static final NbtDungeonConfig MUSHROOM_LOW_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_mushroom_low"), "mushroom", "mushroom_low", new ResourceLocation(RepurposedStructures.MODID, "dungeons/mushroom_low"), EMPTY_ID);
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

    private static final NbtDungeonConfig NEUTRAL_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_neutral_ocean"), "ocean", "ocean_neutral", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_neutral"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_NEUTRAL_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.get().configured(NEUTRAL_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig COLD_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_cold_ocean"), "ocean", "ocean_cold", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_cold"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_COLD_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.get().configured(COLD_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig FROZEN_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_frozen_ocean"), "ocean", "ocean_frozen", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_frozen"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_FROZEN_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.get().configured(FROZEN_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig LUKEWARM_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_lukewarm_ocean"), "ocean", "ocean_lukewarm", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_lukewarm"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_LUKEWARM_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.get().configured(LUKEWARM_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()))));

    private static final NbtDungeonConfig WARM_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_warm_ocean"), "ocean", "ocean_warm", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_warm"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_WARM_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.get().configured(WARM_DUNGEON_CONFIG)
            .decorated(RSPlacements.RS_DUNGEON_PLACEMENT.get().configured(new TopSolidRangeConfig(
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMinHeight.get(), 0,
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMaxHeight.get()))
                    .decorated(RSPlacements.RS_UNLIMITED_COUNT.get().configured(new FeatureSpreadConfig(RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()))));


    // Wells

    // Need this field for dimension/biome blacklisting
    public static List<ConfiguredFeature<?, ?>> RS_WELLS = new ArrayList<>();

    public static ConfiguredFeature<?, ?> BADLANDS_WELL = RSFeatures.BADLANDS_WELL.get().configured(new NbtFeatureConfig(
                new ResourceLocation(RepurposedStructures.MODID, "well_badlands"),
                false,
                -2,
                ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/badlands"), 1)),
                new ResourceLocation(RepurposedStructures.MODID, "wells/badlands")))
            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
            .chance(RepurposedStructures.RSWellsConfig.badlandsWellRarityPerChunk.get());

    public static ConfiguredFeature<?, ?> NETHER_WELL = RSFeatures.NETHER_WELL.get().configured(new NbtFeatureConfig(
                new ResourceLocation(RepurposedStructures.MODID, "well_nether"),
                false,
                -1,
                ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/nether"), 1)),
                new ResourceLocation(RepurposedStructures.MODID, "wells/nether")))
            .decorated(RSPlacements.SNAP_TO_LOWER_NON_AIR_PLACEMENT.get().configured(NoPlacementConfig.INSTANCE))
            .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(30, 0, 91)))
            .chance(RepurposedStructures.RSWellsConfig.netherWellRarityPerChunk.get());

    public static ConfiguredFeature<?, ?> SNOW_WELL = RSFeatures.SNOW_WELL.get()
            .configured(new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_snow"),
                    false,
                    -2,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/snow"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/snow")))
            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
            .chance(RepurposedStructures.RSWellsConfig.snowWellRarityPerChunk.get());

    public static ConfiguredFeature<?, ?> MOSSY_STONE_WELL = RSFeatures.MOSSY_STONE_WELL.get()
            .configured(new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_mossy_stone"),
                    true,
                    -2,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/mossy"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/mossy")))
            .decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE)
            .chance(RepurposedStructures.RSWellsConfig.mossyStoneWellRarityPerChunk.get());

    public static ConfiguredFeature<?, ?> FOREST_WELL = RSFeatures.FOREST_WELL.get()
            .configured(new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_forest"),
                    true,
                    -6,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/forest"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/forest")))
            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
            .chance(RepurposedStructures.RSWellsConfig.forestWellRarityPerChunk.get());

    public static ConfiguredFeature<?, ?> MUSHROOM_WELL = RSFeatures.MUSHROOM_WELL.get()
            .configured(new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_mushroom"),
                    false,
                    -2,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/mushroom"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/mushroom")))
            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
            .chance(RepurposedStructures.RSWellsConfig.mushroomWellRarityPerChunk.get());

    // Misc

    public static ConfiguredFeature<?, ?> LILY_OF_THE_VALLEY_FEATURE = Feature.NO_BONEMEAL_FLOWER.configured(
            (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.LILY_OF_THE_VALLEY.defaultBlockState()), SimpleBlockPlacer.INSTANCE))
                    .tries(64)
                    .build());

    public static ConfiguredFeature<?, ?> CRIMSON_FUNGI_NOT_PLANTED = Feature.HUGE_FUNGUS.configured(HugeFungusConfig.HUGE_CRIMSON_FUNGI_NOT_PLANTED_CONFIG);
    public static ConfiguredFeature<?, ?> WARPED_FUNGI_NOT_PLANTED = Feature.HUGE_FUNGUS.configured(HugeFungusConfig.HUGE_WARPED_FUNGI_NOT_PLANTED_CONFIG);
    public static ConfiguredFeature<?, ?> COBBLESTONE_PATCH = Feature.BLOCK_PILE.configured(new BlockStateProvidingFeatureConfig(new SimpleBlockStateProvider(Blocks.COBBLESTONE.defaultBlockState())));
    public static ConfiguredFeature<?, ?> CHORUS_PLANT = Feature.CHORUS_PLANT.configured(IFeatureConfig.NONE);

    public static ConfiguredFeature<?, ?> WITHER_SKELETON_WITH_BOW = RSFeatures.WITHER_SKELETON_WITH_BOW.get().configured(IFeatureConfig.NONE);
    public static ConfiguredFeature<?, ?> SHULKER_MOB = RSFeatures.SHULKER_MOB.get().configured(IFeatureConfig.NONE);
    public static ConfiguredFeature<?, ?> DROWNED_WITH_ARMOR = RSFeatures.DROWNED_WITH_ARMOR.get().configured(IFeatureConfig.NONE);
    public static ConfiguredFeature<?, ?> SKELETON_BOW = RSFeatures.SKELETON.get().configured(new GenericMobConfig(null, null, null, null, null, 0.25f, 14));
    public static ConfiguredFeature<?, ?> SKELETON_BOW_DEADLY = RSFeatures.SKELETON.get().configured(new GenericMobConfig(null, Items.LEATHER_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.LEATHER_LEGGINGS, null, 0.23f, 18));
    public static ConfiguredFeature<?, ?> SKELETON_BOW_DEADLIEST = RSFeatures.SKELETON.get().configured(new GenericMobConfig(null, Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, null, 0.21f, 24));
    public static ConfiguredFeature<?, ?> SKELETON_SWORD = RSFeatures.SKELETON.get().configured(new GenericMobConfig(Items.STONE_SWORD, null, null, null, null, 0.25f, 14));
    public static ConfiguredFeature<?, ?> SKELETON_SWORD_DEADLY = RSFeatures.SKELETON.get().configured(new GenericMobConfig(Items.STONE_SWORD, Items.LEATHER_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.LEATHER_LEGGINGS, null, 0.29f, 18));
    public static ConfiguredFeature<?, ?> SKELETON_SWORD_DEADLIEST = RSFeatures.SKELETON.get().configured(new GenericMobConfig(Items.STONE_SWORD, Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, null, 0.33f, 24));
    public static ConfiguredFeature<?, ?> SKELETON_HORSEMAN_SWORD = RSFeatures.SKELETON_HORSEMAN.get().configured(new GenericMobConfig(Items.IRON_SWORD, Items.IRON_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.IRON_BOOTS, 0.29f, 28));
    public static ConfiguredFeature<?, ?> SKELETON_HORSEMAN_BOW = RSFeatures.SKELETON_HORSEMAN.get().configured(new GenericMobConfig(Items.BOW, Items.IRON_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.IRON_BOOTS, 0.25f, 24));

    public static ConfiguredFeature<?, ?> POST_PROCESS_CONNECTING_BLOCKS = RSFeatures.POST_PROCESS_CONNECTING_BLOCKS.get().configured(IFeatureConfig.NONE);
    public static ConfiguredFeature<?, ?> JUNGLE_FORTRESS_STRUCTURE_BREAKAGE = RSFeatures.STRUCTURE_BREAKAGE.get().configured(new StructureTargetChanceConfig(RSStructures.FORTRESS_JUNGLE.get(), 0.366f));
    public static ConfiguredFeature<?, ?> JUNGLE_FORTRESS_STRUCTURE_VINE_BREAKAGE = RSFeatures.STRUCTURE_VINE_BREAKAGE.get().configured(new StructureTargetAndLengthConfig(RSStructures.FORTRESS_JUNGLE.get(), 15, 4));
    public static ConfiguredFeature<?, ?> JUNGLE_FORTRESS_STRUCTURE_VINES = RSFeatures.STRUCTURE_VINES.get().configured(new StructureTargetLengthRangeConfig(RSStructures.FORTRESS_JUNGLE.get(), 10, 3, 3));
    public static ConfiguredFeature<?, ?> NETHER_STRONGHOLD_CHAINS = RSFeatures.STRUCTURE_CHAINS.get().configured(new StructureTargetConfig(RSStructures.STRONGHOLD_NETHER.get(), 7));
    public static ConfiguredFeature<?, ?> END_STRONGHOLD_CHAINS = RSFeatures.STRUCTURE_END_ROD_CHAINS.get().configured(new StructureTargetConfig(RSStructures.STRONGHOLD_END.get(), 4));
    public static ConfiguredFeature<?, ?> END_MINESHAFT_CHORUS = RSFeatures.STRUCTURE_CHORUS.get().configured(new StructureTargetConfig(RSStructures.MINESHAFT_END.get(), 4));
    public static ConfiguredFeature<?, ?> END_MINESHAFT_DENSE_CHORUS = RSFeatures.STRUCTURE_CHORUS.get().configured(new StructureTargetConfig(RSStructures.MINESHAFT_END.get(), 60));
    public static ConfiguredFeature<?, ?> CRIMSON_MINESHAFT_PLANTS = RSFeatures.STRUCTURE_CRIMSON_PLANTS.get().configured(new StructureTargetAndLengthConfig(RSStructures.MINESHAFT_CRIMSON.get(), 40, 3));
    public static ConfiguredFeature<?, ?> CRIMSON_MINESHAFT_DENSE_PLANTS = RSFeatures.STRUCTURE_CRIMSON_PLANTS.get().configured(new StructureTargetAndLengthConfig(RSStructures.MINESHAFT_CRIMSON.get(), 100, 3));
    public static ConfiguredFeature<?, ?> CRIMSON_MINESHAFT_PLANTS_LONG = RSFeatures.STRUCTURE_CRIMSON_PLANTS.get().configured(new StructureTargetAndLengthConfig(RSStructures.MINESHAFT_CRIMSON.get(), 85, 7));
    public static ConfiguredFeature<?, ?> NETHER_MINESHAFT_FIRE = RSFeatures.STRUCTURE_FIRE.get().configured(new StructureTargetConfig(RSStructures.MINESHAFT_NETHER.get(), 3));
    public static ConfiguredFeature<?, ?> NETHER_MINESHAFT_DENSE_FIRE = RSFeatures.STRUCTURE_FIRE.get().configured(new StructureTargetConfig(RSStructures.MINESHAFT_NETHER.get(), 40));
    public static ConfiguredFeature<?, ?> NETHER_MINESHAFT_NETHERWART = RSFeatures.STRUCTURE_NETHERWART.get().configured(new StructureTargetConfig(RSStructures.MINESHAFT_NETHER.get(), 40));
    public static ConfiguredFeature<?, ?> OCEAN_MINESHAFT_PLANTS = RSFeatures.STRUCTURE_SEAGRASS.get().configured(new StructureTargetConfig(RSStructures.MINESHAFT_OCEAN.get(), 40));
    public static ConfiguredFeature<?, ?> OCEAN_MINESHAFT_DENSE_PLANTS = RSFeatures.STRUCTURE_SEAGRASS.get().configured(new StructureTargetConfig(RSStructures.MINESHAFT_OCEAN.get(), 80));
    public static ConfiguredFeature<?, ?> DARK_FOREST_MINESHAFT_VINES = RSFeatures.STRUCTURE_VINES.get().configured(new StructureTargetLengthRangeConfig(RSStructures.MINESHAFT_DARK_FOREST.get(), 20, 2, 3));
    public static ConfiguredFeature<?, ?> DARK_FOREST_MINESHAFT_VINES_LONG = RSFeatures.STRUCTURE_VINES.get().configured(new StructureTargetLengthRangeConfig(RSStructures.MINESHAFT_DARK_FOREST.get(), 23, 8, 1));
    public static ConfiguredFeature<?, ?> SWAMP_MINESHAFT_VINES = RSFeatures.STRUCTURE_VINES.get().configured(new StructureTargetLengthRangeConfig(RSStructures.MINESHAFT_SWAMP.get(), 10, 1, 3));
    public static ConfiguredFeature<?, ?> SWAMP_MINESHAFT_VINES_LONG = RSFeatures.STRUCTURE_VINES.get().configured(new StructureTargetLengthRangeConfig(RSStructures.MINESHAFT_SWAMP.get(), 20, 8, 1));
    public static ConfiguredFeature<?, ?> JUNGLE_MINESHAFT_LEAVES_AND_VINES = RSFeatures.STRUCTURE_VINES_AND_LEAVES.get().configured(new StructureTargetAndLengthConfig(RSStructures.MINESHAFT_JUNGLE.get(), 30, 3));
    public static ConfiguredFeature<?, ?> JUNGLE_MINESHAFT_VINES_LONG = RSFeatures.STRUCTURE_VINES.get().configured(new StructureTargetLengthRangeConfig(RSStructures.MINESHAFT_JUNGLE.get(), 25, 8, 1));
    public static ConfiguredFeature<?, ?> WARPED_MINESHAFT_PLANTS = RSFeatures.STRUCTURE_WARPED_PLANTS.get().configured(new StructureTargetAndLengthConfig(RSStructures.MINESHAFT_WARPED.get(), 25, 3));
    public static ConfiguredFeature<?, ?> WARPED_MINESHAFT_DENSE_PLANTS = RSFeatures.STRUCTURE_WARPED_PLANTS.get().configured(new StructureTargetAndLengthConfig(RSStructures.MINESHAFT_WARPED.get(), 80, 3));
    public static ConfiguredFeature<?, ?> WARPED_MINESHAFT_PLANTS_LONG = RSFeatures.STRUCTURE_WARPED_PLANTS.get().configured(new StructureTargetAndLengthConfig(RSStructures.MINESHAFT_WARPED.get(), 60, 7));
    public static ConfiguredFeature<?, ?> JUNGLE_PYRAMID_STRUCTURE_VINES = RSFeatures.STRUCTURE_VINES.get().configured(new StructureTargetLengthRangeConfig(RSStructures.PYRAMID_JUNGLE.get(), 17, 4, 3));
    public static ConfiguredFeature<?, ?> JUNGLE_PYRAMID_STRUCTURE_VINES_NARROW = RSFeatures.STRUCTURE_VINES.get().configured(new StructureTargetLengthRangeConfig(RSStructures.PYRAMID_JUNGLE.get(), 17, 2, 1));
    public static ConfiguredFeature<?, ?> OCEAN_PYRAMID_STRUCTURE_PLANTS = RSFeatures.STRUCTURE_SEAGRASS.get().configured(new StructureTargetConfig(RSStructures.PYRAMID_OCEAN.get(), 12));
    public static ConfiguredFeature<?, ?> FLOWER_FOREST_PYRAMID_STRUCTURE_GRASS = RSFeatures.STRUCTURE_GRASS.get().configured(new StructureTargetAndRangeConfig(RSStructures.PYRAMID_FLOWER_FOREST.get(), 24, 3));
    public static ConfiguredFeature<?, ?> FLOWER_FOREST_PYRAMID_STRUCTURE_FLOWERS = RSFeatures.STRUCTURE_FLOWERS.get().configured(new StructureTargetAndRangeConfig(RSStructures.PYRAMID_FLOWER_FOREST.get(), 8, 3));

    public static ConfiguredFeature<?, ?> WARM_LAND_RUINS_STRUCTURE_GRASS = Feature.RANDOM_PATCH
            .configured(Features.Configs.TALL_GRASS_CONFIG)
            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
            .decorated(RSPlacements.RS_MINUS_EIGHT_PLACEMENT.get().configured(NoPlacementConfig.INSTANCE))
            .count(2);

    public static ConfiguredFeature<?, ?> HOT_LAND_RUINS_STRUCTURE_DEAD_BUSH = Feature.RANDOM_PATCH
            .configured(Features.Configs.DEAD_BUSH_CONFIG)
            .decorated(Features.Placements.HEIGHTMAP_SQUARE)
            .decorated(RSPlacements.RS_MINUS_EIGHT_PLACEMENT.get().configured(NoPlacementConfig.INSTANCE))
            .count(8);


    public static void registerConfiguredFeatures() {
        Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_badlands"), BADLANDS_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_dark_forest"), DARK_FOREST_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_desert"), DESERT_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_end"), END_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_nether"), NETHER_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_snow"), SNOW_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_icy"), ICY_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_swamp"), SWAMP_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_mushroom_high"), MUSHROOM_HIGH_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_mushroom_low"), MUSHROOM_LOW_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_jungle"), JUNGLE_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_neutral_ocean"), OCEAN_NEUTRAL_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_lukewarm_ocean"), OCEAN_LUKEWARM_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_frozen_ocean"), OCEAN_FROZEN_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_cold_ocean"), OCEAN_COLD_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_warm_ocean"), OCEAN_WARM_DUNGEONS));

        RS_WELLS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_badlands"), BADLANDS_WELL));
        RS_WELLS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_nether"), NETHER_WELL));
        RS_WELLS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_snow"), SNOW_WELL));
        RS_WELLS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_mossy_stone"), MOSSY_STONE_WELL));
        RS_WELLS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_forest"), FOREST_WELL));
        RS_WELLS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_mushroom"), MUSHROOM_WELL));

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "lily_of_the_valley"), LILY_OF_THE_VALLEY_FEATURE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "crimson_fungi_not_planted"), CRIMSON_FUNGI_NOT_PLANTED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "warped_fungi_not_planted"), WARPED_FUNGI_NOT_PLANTED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "cobblestone_patch"), COBBLESTONE_PATCH);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "chorus_plant"), CHORUS_PLANT);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "wither_skeleton_with_bow"), WITHER_SKELETON_WITH_BOW);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "shulker_mob"), SHULKER_MOB);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "drowned_with_armor"), DROWNED_WITH_ARMOR);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "skeleton_bow"), SKELETON_BOW);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "skeleton_bow_deadly"), SKELETON_BOW_DEADLY);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "skeleton_bow_deadliest"), SKELETON_BOW_DEADLIEST);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "skeleton_sword"), SKELETON_SWORD);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "skeleton_sword_deadly"), SKELETON_SWORD_DEADLY);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "skeleton_sword_deadliest"), SKELETON_SWORD_DEADLIEST);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "skeleton_horseman_sword"), SKELETON_HORSEMAN_SWORD);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "skeleton_horseman_bow"), SKELETON_HORSEMAN_BOW);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "post_process_connecting_blocks"), POST_PROCESS_CONNECTING_BLOCKS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_fortress_structure_breakage"), JUNGLE_FORTRESS_STRUCTURE_BREAKAGE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_fortress_structure_vine_breakage"), JUNGLE_FORTRESS_STRUCTURE_VINE_BREAKAGE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_fortress_structure_vines"), JUNGLE_FORTRESS_STRUCTURE_VINES);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_stronghold_chains"), NETHER_STRONGHOLD_CHAINS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "end_stronghold_chains"), END_STRONGHOLD_CHAINS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "end_mineshaft_chorus"), END_MINESHAFT_CHORUS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "end_mineshaft_dense_chorus"), END_MINESHAFT_DENSE_CHORUS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "crimson_mineshaft_plants"), CRIMSON_MINESHAFT_PLANTS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "crimson_mineshaft_dense_plants"), CRIMSON_MINESHAFT_DENSE_PLANTS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "crimson_mineshaft_plants_long"), CRIMSON_MINESHAFT_PLANTS_LONG);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_mineshaft_fire"), NETHER_MINESHAFT_FIRE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_mineshaft_dense_fire"), NETHER_MINESHAFT_DENSE_FIRE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_mineshaft_netherwart"), NETHER_MINESHAFT_NETHERWART);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "ocean_mineshaft_plants"), OCEAN_MINESHAFT_PLANTS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "ocean_mineshaft_dense_plants"), OCEAN_MINESHAFT_DENSE_PLANTS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dark_forest_mineshaft_vines"), DARK_FOREST_MINESHAFT_VINES);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dark_forest_mineshaft_vines_long"), DARK_FOREST_MINESHAFT_VINES_LONG);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "swamp_mineshaft_vines"), SWAMP_MINESHAFT_VINES);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "swamp_mineshaft_vines_long"), SWAMP_MINESHAFT_VINES_LONG);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_mineshaft_leaves_and_vines"), JUNGLE_MINESHAFT_LEAVES_AND_VINES);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_mineshaft_vines_long"), JUNGLE_MINESHAFT_VINES_LONG);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "warped_mineshaft_plants"), WARPED_MINESHAFT_PLANTS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "warped_mineshaft_dense_plants"), WARPED_MINESHAFT_DENSE_PLANTS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "warped_mineshaft_plants_long"), WARPED_MINESHAFT_PLANTS_LONG);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_pyramid_structure_vines"), JUNGLE_PYRAMID_STRUCTURE_VINES);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_pyramid_structure_vines_narrow"), JUNGLE_PYRAMID_STRUCTURE_VINES_NARROW);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "ocean_pyramid_structure_plants"), OCEAN_PYRAMID_STRUCTURE_PLANTS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "flower_forest_pyramid_structure_grass"), FLOWER_FOREST_PYRAMID_STRUCTURE_GRASS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "flower_forest_pyramid_structure_flowers"), FLOWER_FOREST_PYRAMID_STRUCTURE_FLOWERS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "warm_land_ruins_structure_grass"), WARM_LAND_RUINS_STRUCTURE_GRASS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "hot_land_ruins_structure_dead_bush"), HOT_LAND_RUINS_STRUCTURE_DEAD_BUSH);
    }
}
