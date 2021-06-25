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
import com.telepathicgrunt.repurposedstructures.world.placements.configs.SingleIntConfig;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.HeightmapDecoratorConfig;
import net.minecraft.world.gen.decorator.NopeDecoratorConfig;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.BlockPileFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

import java.util.ArrayList;
import java.util.List;


public class RSConfiguredFeatures {
    private static final Identifier EMPTY_ID = new Identifier("minecraft:empty");

    // Dungeons

    // Need this field so we can test to make sure we do not add the dungeons to biomes that
    // we already added an RS dungeon to. (Due to BiomeModification API running separately for each feature)
    public static List<ConfiguredFeature<?, ?>> RS_DUNGEONS = new ArrayList<>();

    private static final NbtDungeonConfig BADLANDS_DUNGEON_CONFIG = new NbtDungeonConfig("badlands", EMPTY_ID);
    public static ConfiguredFeature<?, ?> BADLANDS_DUNGEONS = RSFeatures.BADLANDS_DUNGEONS.configure(BADLANDS_DUNGEON_CONFIG)
            .decorate(RSPlacements.DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(UniformHeightProvider.create(
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.badlandsDungeonMinHeight),
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.badlandsDungeonMaxHeight))))
                    .decorate(RSPlacements.UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.badlandsDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig DARK_FOREST_DUNGEON_CONFIG = new NbtDungeonConfig("dark_forest", new Identifier(RepurposedStructures.MODID, "dungeons/dark_forest_post_process"));
    public static ConfiguredFeature<?, ?> DARK_FOREST_DUNGEONS = RSFeatures.DARK_FOREST_DUNGEONS.configure(DARK_FOREST_DUNGEON_CONFIG)
            .decorate(RSPlacements.DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(UniformHeightProvider.create(
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.darkForestDungeonMinHeight),
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.darkForestDungeonMaxHeight))))
                    .decorate(RSPlacements.UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.darkForestDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig DESERT_DUNGEON_CONFIG = new NbtDungeonConfig("desert", EMPTY_ID);
    public static ConfiguredFeature<?, ?> DESERT_DUNGEONS = RSFeatures.DESERT_DUNGEONS.configure(DESERT_DUNGEON_CONFIG)
            .decorate(RSPlacements.DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(UniformHeightProvider.create(
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.desertDungeonMinHeight),
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.desertDungeonMaxHeight))))
            .decorate(RSPlacements.UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.desertDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig END_DUNGEON_CONFIG = new NbtDungeonConfig("end", "shulker_boxes", EMPTY_ID, 20, Blocks.SHULKER_BOX.getDefaultState());
    public static ConfiguredFeature<?, ?> END_DUNGEONS = RSFeatures.END_DUNGEONS.configure(END_DUNGEON_CONFIG)
            .decorate(RSPlacements.DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(UniformHeightProvider.create(
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.endDungeonMinHeight),
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.endDungeonMaxHeight))))
            .decorate(RSPlacements.UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.endDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig NETHER_DUNGEON_CONFIG = new NbtDungeonConfig("nether", EMPTY_ID);
    public static ConfiguredFeature<?, ?> NETHER_DUNGEONS = RSFeatures.NETHER_DUNGEONS.configure(NETHER_DUNGEON_CONFIG)
            .decorate(RSPlacements.DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(UniformHeightProvider.create(
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.netherDungeonMinHeight),
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.netherDungeonMaxHeight))))
            .decorate(RSPlacements.UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.netherDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig SNOW_DUNGEON_CONFIG = new NbtDungeonConfig("snow", EMPTY_ID);
    public static ConfiguredFeature<?, ?> SNOW_DUNGEONS = RSFeatures.SNOW_DUNGEONS.configure(SNOW_DUNGEON_CONFIG)
            .decorate(RSPlacements.DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(UniformHeightProvider.create(
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.snowDungeonMinHeight),
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.snowDungeonMaxHeight))))
            .decorate(RSPlacements.UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.snowDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig ICY_DUNGEON_CONFIG = new NbtDungeonConfig("icy", EMPTY_ID);
    public static ConfiguredFeature<?, ?> ICY_DUNGEONS = RSFeatures.ICY_DUNGEONS.configure(ICY_DUNGEON_CONFIG)
            .decorate(RSPlacements.DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(UniformHeightProvider.create(
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.icyDungeonMinHeight),
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.icyDungeonMaxHeight))))
                    .decorate(RSPlacements.UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.icyDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig SWAMP_DUNGEON_CONFIG = new NbtDungeonConfig("swamp", EMPTY_ID);
    public static ConfiguredFeature<?, ?> SWAMP_DUNGEONS = RSFeatures.SWAMP_DUNGEONS.configure(SWAMP_DUNGEON_CONFIG)
            .decorate(RSPlacements.DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(UniformHeightProvider.create(
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.swampDungeonMinHeight),
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.swampDungeonMaxHeight))))
            .decorate(RSPlacements.UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.swampDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig MUSHROOM_HIGH_DUNGEON_CONFIG = new NbtDungeonConfig("mushroom", "mushroom_high", new Identifier(RepurposedStructures.MODID, "dungeons/mushroom_high"), EMPTY_ID);
    public static ConfiguredFeature<?, ?> MUSHROOM_HIGH_DUNGEONS = RSFeatures.MUSHROOM_DUNGEONS.configure(MUSHROOM_HIGH_DUNGEON_CONFIG)
            .decorate(RSPlacements.DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(UniformHeightProvider.create(
                    YOffset.fixed(Math.max(63, RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight)),
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight))))
            .decorate(RSPlacements.UNLIMITED_COUNT.configure(new CountConfig(Math.round(Math.max(0,
                    ((RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight - 62F) /
                    (RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight - RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight)) *
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.mushroomDungeonAttemptsPerChunk
            ))))));

    private static final NbtDungeonConfig MUSHROOM_LOW_DUNGEON_CONFIG = new NbtDungeonConfig("mushroom", "mushroom_low", new Identifier(RepurposedStructures.MODID, "dungeons/mushroom_low"), EMPTY_ID);
    public static ConfiguredFeature<?, ?> MUSHROOM_LOW_DUNGEONS = RSFeatures.MUSHROOM_DUNGEONS.configure(MUSHROOM_LOW_DUNGEON_CONFIG)
            .decorate(RSPlacements.DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(UniformHeightProvider.create(
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight),
                    YOffset.fixed(Math.min(62, RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight)))))
            .decorate(RSPlacements.UNLIMITED_COUNT.configure(new CountConfig(Math.round(Math.max(0,
                    ((62F - RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight) /
                    (RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight - RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight)) *
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.mushroomDungeonAttemptsPerChunk
            ))))));

    private static final NbtDungeonConfig JUNGLE_DUNGEON_CONFIG = new NbtDungeonConfig("jungle", new Identifier(RepurposedStructures.MODID, "dungeons/jungle_post_process"));
    public static ConfiguredFeature<?, ?> JUNGLE_DUNGEONS = RSFeatures.JUNGLE_DUNGEONS.configure(JUNGLE_DUNGEON_CONFIG)
            .decorate(RSPlacements.DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(UniformHeightProvider.create(
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.jungleDungeonMinHeight),
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.jungleDungeonMaxHeight))))
            .decorate(RSPlacements.UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.jungleDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig NEUTRAL_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_neutral", new Identifier(RepurposedStructures.MODID, "dungeons/ocean_neutral"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_NEUTRAL_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configure(NEUTRAL_DUNGEON_CONFIG)
            .decorate(RSPlacements.DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(UniformHeightProvider.create(
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))))
            .decorate(RSPlacements.UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig COLD_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_cold", new Identifier(RepurposedStructures.MODID, "dungeons/ocean_cold"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_COLD_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configure(COLD_DUNGEON_CONFIG)
            .decorate(RSPlacements.DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(UniformHeightProvider.create(
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))))
                    .decorate(RSPlacements.UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig FROZEN_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_frozen", new Identifier(RepurposedStructures.MODID, "dungeons/ocean_frozen"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_FROZEN_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configure(FROZEN_DUNGEON_CONFIG)
            .decorate(RSPlacements.DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(UniformHeightProvider.create(
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))))
                    .decorate(RSPlacements.UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig LUKEWARM_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_lukewarm", new Identifier(RepurposedStructures.MODID, "dungeons/ocean_lukewarm"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_LUKEWARM_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configure(LUKEWARM_DUNGEON_CONFIG)
            .decorate(RSPlacements.DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(UniformHeightProvider.create(
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))))
                    .decorate(RSPlacements.UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig WARM_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_warm", new Identifier(RepurposedStructures.MODID, "dungeons/ocean_warm"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_WARM_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configure(WARM_DUNGEON_CONFIG)
            .decorate(RSPlacements.DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(UniformHeightProvider.create(
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    YOffset.fixed(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))))
                    .decorate(RSPlacements.UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));


    // Wells
    public static ConfiguredFeature<?, ?> BADLANDS_WELL = RSFeatures.BADLANDS_WELL
            .configure(new NbtFeatureConfig(
                    false,
                    -2,
                    ImmutableList.of(Pair.of(new Identifier(RepurposedStructures.MODID, "wells/badlands"), 1)),
                    new Identifier(RepurposedStructures.MODID, "wells/badlands")))
            .decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.WORLD_SURFACE_WG)).spreadHorizontally())
            .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.badlandsWellRarityPerChunk);

    public static ConfiguredFeature<?, ?> NETHER_WELL = RSFeatures.NETHER_WELL
            .configure(new NbtFeatureConfig(
                    false,
                    -1,
                    ImmutableList.of(Pair.of(new Identifier(RepurposedStructures.MODID, "wells/nether"), 1)),
                    new Identifier(RepurposedStructures.MODID, "wells/nether")))
            .decorate(RSPlacements.SNAP_TO_LOWER_NON_AIR_PLACEMENT.configure(NopeDecoratorConfig.INSTANCE))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(
                    YOffset.aboveBottom(30),
                    YOffset.belowTop(35)))))
            .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.netherWellRarityPerChunk);

    public static ConfiguredFeature<?, ?> SNOW_WELL = RSFeatures.SNOW_WELL
            .configure(new NbtFeatureConfig(
                    false,
                    -2,
                    ImmutableList.of(Pair.of(new Identifier(RepurposedStructures.MODID, "wells/snow"), 1)),
                    new Identifier(RepurposedStructures.MODID, "wells/snow")))
            .decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.WORLD_SURFACE_WG)).spreadHorizontally())
            .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.snowWellRarityPerChunk);

    public static ConfiguredFeature<?, ?> MOSSY_STONE_WELL = RSFeatures.MOSSY_STONE_WELL
            .configure(new NbtFeatureConfig(
                    true,
                    -2,
                    ImmutableList.of(Pair.of(new Identifier(RepurposedStructures.MODID, "wells/mossy"), 1)),
                    new Identifier(RepurposedStructures.MODID, "wells/mossy")))
            .decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.OCEAN_FLOOR_WG)).spreadHorizontally())
            .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.mossyStoneWellRarityPerChunk);

    public static ConfiguredFeature<?, ?> FOREST_WELL = RSFeatures.FOREST_WELL
            .configure(new NbtFeatureConfig(
                    true,
                    -6,
                    ImmutableList.of(Pair.of(new Identifier(RepurposedStructures.MODID, "wells/forest"), 1)),
                    new Identifier(RepurposedStructures.MODID, "wells/forest")))
            .decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.WORLD_SURFACE_WG)).spreadHorizontally())
            .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.forestWellRarityPerChunk);

    public static ConfiguredFeature<?, ?> MUSHROOM_WELL = RSFeatures.MUSHROOM_WELL
            .configure(new NbtFeatureConfig(
                    false,
                    -2,
                    ImmutableList.of(Pair.of(new Identifier(RepurposedStructures.MODID, "wells/mushroom"), 1)),
                    new Identifier(RepurposedStructures.MODID, "wells/mushroom")))
            .decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.WORLD_SURFACE_WG)).spreadHorizontally())
            .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.mushroomWellRarityPerChunk);

    public static List<ConfiguredFeature<?, ?>> RS_WELLS = new ArrayList<>();

    // Misc

    public static ConfiguredFeature<?, ?> LILY_OF_THE_VALLEY_FEATURE = Feature.FLOWER.configure(
            (new RandomPatchFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.LILY_OF_THE_VALLEY.getDefaultState()),
                    SimpleBlockPlacer.INSTANCE))
            .tries(64)
            .build());

    public static ConfiguredFeature<?, ?> CRIMSON_FUNGI_NOT_PLANTED = Feature.HUGE_FUNGUS.configure(HugeFungusFeatureConfig.CRIMSON_FUNGUS_NOT_PLANTED_CONFIG);
    public static ConfiguredFeature<?, ?> WARPED_FUNGI_NOT_PLANTED = Feature.HUGE_FUNGUS.configure(HugeFungusFeatureConfig.WARPED_FUNGUS_NOT_PLANTED_CONFIG);
    public static ConfiguredFeature<?, ?> COBBLESTONE_PATCH = Feature.BLOCK_PILE.configure(new BlockPileFeatureConfig(new SimpleBlockStateProvider(Blocks.COBBLESTONE.getDefaultState())));
    public static ConfiguredFeature<?, ?> CHORUS_PLANT = Feature.CHORUS_PLANT.configure(FeatureConfig.DEFAULT);

    public static ConfiguredFeature<?, ?> WITHER_SKELETON_WITH_BOW = RSFeatures.WITHER_SKELETON_WITH_BOW.configure(FeatureConfig.DEFAULT);
    public static ConfiguredFeature<?, ?> SHULKER_MOB = RSFeatures.SHULKER_MOB.configure(FeatureConfig.DEFAULT);
    public static ConfiguredFeature<?, ?> DROWNED_WITH_ARMOR = RSFeatures.DROWNED_WITH_ARMOR.configure(FeatureConfig.DEFAULT);
    public static ConfiguredFeature<?, ?> SKELETON_BOW = RSFeatures.SKELETON.configure(new GenericMobConfig(null, null, null, null, null, 0.25f, 14));
    public static ConfiguredFeature<?, ?> SKELETON_BOW_DEADLY = RSFeatures.SKELETON.configure(new GenericMobConfig(null, Items.LEATHER_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.LEATHER_LEGGINGS, null, 0.23f, 18));
    public static ConfiguredFeature<?, ?> SKELETON_BOW_DEADLIEST = RSFeatures.SKELETON.configure(new GenericMobConfig(null, Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, null, 0.21f, 24));
    public static ConfiguredFeature<?, ?> SKELETON_SWORD = RSFeatures.SKELETON.configure(new GenericMobConfig(Items.STONE_SWORD, null, null, null, null, 0.25f, 14));
    public static ConfiguredFeature<?, ?> SKELETON_SWORD_DEADLY = RSFeatures.SKELETON.configure(new GenericMobConfig(Items.STONE_SWORD, Items.LEATHER_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.LEATHER_LEGGINGS, null, 0.29f, 18));
    public static ConfiguredFeature<?, ?> SKELETON_SWORD_DEADLIEST = RSFeatures.SKELETON.configure(new GenericMobConfig(Items.STONE_SWORD, Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, null, 0.33f, 24));
    public static ConfiguredFeature<?, ?> SKELETON_HORSEMAN_SWORD = RSFeatures.SKELETON_HORSEMAN.configure(new GenericMobConfig(Items.IRON_SWORD, Items.IRON_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.IRON_BOOTS, 0.29f, 28));
    public static ConfiguredFeature<?, ?> SKELETON_HORSEMAN_BOW = RSFeatures.SKELETON_HORSEMAN.configure(new GenericMobConfig(Items.BOW, Items.IRON_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.IRON_BOOTS, 0.25f, 24));

    public static ConfiguredFeature<?, ?> POST_PROCESS_CONNECTING_BLOCKS = RSFeatures.POST_PROCESS_CONNECTING_BLOCKS.configure(FeatureConfig.DEFAULT);
    public static ConfiguredFeature<?, ?> JUNGLE_FORTRESS_STRUCTURE_BREAKAGE = RSFeatures.STRUCTURE_BREAKAGE.configure(new StructureTargetChanceConfig(0.366f));
    public static ConfiguredFeature<?, ?> JUNGLE_FORTRESS_STRUCTURE_VINE_BREAKAGE = RSFeatures.STRUCTURE_VINE_BREAKAGE.configure(new StructureTargetAndLengthConfig(15, 4));
    public static ConfiguredFeature<?, ?> JUNGLE_FORTRESS_STRUCTURE_VINES = RSFeatures.STRUCTURE_VINES.configure(new StructureTargetLengthRangeConfig(10, 3, 3));
    public static ConfiguredFeature<?, ?> NETHER_STRONGHOLD_CHAINS = RSFeatures.STRUCTURE_CHAINS.configure(new StructureTargetConfig(7));
    public static ConfiguredFeature<?, ?> END_MINESHAFT_CHORUS = RSFeatures.STRUCTURE_CHORUS.configure(new StructureTargetConfig(4));
    public static ConfiguredFeature<?, ?> END_MINESHAFT_DENSE_CHORUS = RSFeatures.STRUCTURE_CHORUS.configure(new StructureTargetConfig(60));
    public static ConfiguredFeature<?, ?> CRIMSON_MINESHAFT_PLANTS = RSFeatures.STRUCTURE_CRIMSON_PLANTS.configure(new StructureTargetAndLengthConfig(40, 3));
    public static ConfiguredFeature<?, ?> CRIMSON_MINESHAFT_DENSE_PLANTS = RSFeatures.STRUCTURE_CRIMSON_PLANTS.configure(new StructureTargetAndLengthConfig(100, 3));
    public static ConfiguredFeature<?, ?> CRIMSON_MINESHAFT_PLANTS_LONG = RSFeatures.STRUCTURE_CRIMSON_PLANTS.configure(new StructureTargetAndLengthConfig(85, 7));
    public static ConfiguredFeature<?, ?> NETHER_MINESHAFT_FIRE = RSFeatures.STRUCTURE_FIRE.configure(new StructureTargetConfig(3));
    public static ConfiguredFeature<?, ?> NETHER_MINESHAFT_DENSE_FIRE = RSFeatures.STRUCTURE_FIRE.configure(new StructureTargetConfig(40));
    public static ConfiguredFeature<?, ?> NETHER_MINESHAFT_NETHERWART = RSFeatures.STRUCTURE_NETHERWART.configure(new StructureTargetConfig(40));
    public static ConfiguredFeature<?, ?> OCEAN_MINESHAFT_PLANTS = RSFeatures.STRUCTURE_SEAGRASS.configure(new StructureTargetConfig(40));
    public static ConfiguredFeature<?, ?> OCEAN_MINESHAFT_DENSE_PLANTS = RSFeatures.STRUCTURE_SEAGRASS.configure(new StructureTargetConfig(80));
    public static ConfiguredFeature<?, ?> DARK_FOREST_MINESHAFT_VINES = RSFeatures.STRUCTURE_VINES.configure(new StructureTargetLengthRangeConfig(20, 2, 3));
    public static ConfiguredFeature<?, ?> DARK_FOREST_MINESHAFT_VINES_LONG = RSFeatures.STRUCTURE_VINES.configure(new StructureTargetLengthRangeConfig(23, 8, 1));
    public static ConfiguredFeature<?, ?> SWAMP_MINESHAFT_VINES = RSFeatures.STRUCTURE_VINES.configure(new StructureTargetLengthRangeConfig(10, 1, 3));
    public static ConfiguredFeature<?, ?> SWAMP_MINESHAFT_VINES_LONG = RSFeatures.STRUCTURE_VINES.configure(new StructureTargetLengthRangeConfig(20, 8, 1));
    public static ConfiguredFeature<?, ?> JUNGLE_MINESHAFT_LEAVES_AND_VINES = RSFeatures.STRUCTURE_VINES_AND_LEAVES.configure(new StructureTargetAndLengthConfig(30, 3));
    public static ConfiguredFeature<?, ?> JUNGLE_MINESHAFT_VINES_LONG = RSFeatures.STRUCTURE_VINES.configure(new StructureTargetLengthRangeConfig(25, 8, 1));
    public static ConfiguredFeature<?, ?> WARPED_MINESHAFT_PLANTS = RSFeatures.STRUCTURE_WARPED_PLANTS.configure(new StructureTargetAndLengthConfig(25, 3));
    public static ConfiguredFeature<?, ?> WARPED_MINESHAFT_DENSE_PLANTS = RSFeatures.STRUCTURE_WARPED_PLANTS.configure(new StructureTargetAndLengthConfig(80, 3));
    public static ConfiguredFeature<?, ?> WARPED_MINESHAFT_PLANTS_LONG = RSFeatures.STRUCTURE_WARPED_PLANTS.configure(new StructureTargetAndLengthConfig(60, 7));
    public static ConfiguredFeature<?, ?> JUNGLE_PYRAMID_STRUCTURE_VINES = RSFeatures.STRUCTURE_VINES.configure(new StructureTargetLengthRangeConfig(17, 4, 3));
    public static ConfiguredFeature<?, ?> JUNGLE_PYRAMID_STRUCTURE_VINES_NARROW = RSFeatures.STRUCTURE_VINES.configure(new StructureTargetLengthRangeConfig(17, 2, 1));
    public static ConfiguredFeature<?, ?> OCEAN_PYRAMID_STRUCTURE_PLANTS = RSFeatures.STRUCTURE_SEAGRASS.configure(new StructureTargetConfig(12));
    public static ConfiguredFeature<?, ?> FLOWER_FOREST_PYRAMID_STRUCTURE_GRASS = RSFeatures.STRUCTURE_GRASS.configure(new StructureTargetAndRangeConfig(24, 3));
    public static ConfiguredFeature<?, ?> FLOWER_FOREST_PYRAMID_STRUCTURE_FLOWERS = RSFeatures.STRUCTURE_FLOWERS.configure(new StructureTargetAndRangeConfig(8, 3));
    public static ConfiguredFeature<?, ?> SWAMP_VILLAGE_STRUCTURE_VINES = RSFeatures.STRUCTURE_VINES.configure(new StructureTargetLengthRangeConfig(10, 3, 4, 10));

    public static ConfiguredFeature<?, ?> WARM_LAND_RUINS_STRUCTURE_GRASS = Feature.RANDOM_PATCH
            .configure(ConfiguredFeatures.Configs.TALL_GRASS_CONFIG)
            .decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.OCEAN_FLOOR_WG)).spreadHorizontally())
                .decorate(RSPlacements.MINUS_EIGHT_PLACEMENT.configure(NopeDecoratorConfig.DEFAULT))
                .repeat(2);

    public static ConfiguredFeature<?, ?> HOT_LAND_RUINS_STRUCTURE_DEAD_BUSH = Feature.RANDOM_PATCH
            .configure(ConfiguredFeatures.Configs.DEAD_BUSH_CONFIG)
            .decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.OCEAN_FLOOR_WG)).spreadHorizontally())
                .decorate(RSPlacements.MINUS_EIGHT_PLACEMENT.configure(NopeDecoratorConfig.DEFAULT))
                .repeat(8);

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
        RS_WELLS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "well_mushroom"), MUSHROOM_WELL));

        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "lily_of_the_valley"), LILY_OF_THE_VALLEY_FEATURE);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "crimson_fungi_not_planted"), CRIMSON_FUNGI_NOT_PLANTED);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "warped_fungi_not_planted"), WARPED_FUNGI_NOT_PLANTED);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "cobblestone_patch"), COBBLESTONE_PATCH);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "chorus_plant"), CHORUS_PLANT);

        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "wither_skeleton_with_bow"), WITHER_SKELETON_WITH_BOW);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "shulker_mob"), SHULKER_MOB);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "drowned_with_armor"), DROWNED_WITH_ARMOR);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "skeleton_bow"), SKELETON_BOW);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "skeleton_bow_deadly"), SKELETON_BOW_DEADLY);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "skeleton_bow_deadliest"), SKELETON_BOW_DEADLIEST);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "skeleton_sword"), SKELETON_SWORD);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "skeleton_sword_deadly"), SKELETON_SWORD_DEADLY);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "skeleton_sword_deadliest"), SKELETON_SWORD_DEADLIEST);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "skeleton_horseman_sword"), SKELETON_HORSEMAN_SWORD);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "skeleton_horseman_bow"), SKELETON_HORSEMAN_BOW);

        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "post_process_connecting_blocks"), POST_PROCESS_CONNECTING_BLOCKS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "jungle_fortress_structure_breakage"), JUNGLE_FORTRESS_STRUCTURE_BREAKAGE);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "jungle_fortress_structure_vine_breakage"), JUNGLE_FORTRESS_STRUCTURE_VINE_BREAKAGE);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "jungle_fortress_structure_vines"), JUNGLE_FORTRESS_STRUCTURE_VINES);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "nether_stronghold_chains"), NETHER_STRONGHOLD_CHAINS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "end_mineshaft_chorus"), END_MINESHAFT_CHORUS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "end_mineshaft_dense_chorus"), END_MINESHAFT_DENSE_CHORUS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "crimson_mineshaft_plants"), CRIMSON_MINESHAFT_PLANTS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "crimson_mineshaft_dense_plants"), CRIMSON_MINESHAFT_DENSE_PLANTS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "crimson_mineshaft_plants_long"), CRIMSON_MINESHAFT_PLANTS_LONG);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "nether_mineshaft_fire"), NETHER_MINESHAFT_FIRE);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "nether_mineshaft_dense_fire"), NETHER_MINESHAFT_DENSE_FIRE);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "nether_mineshaft_netherwart"), NETHER_MINESHAFT_NETHERWART);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "ocean_mineshaft_plants"), OCEAN_MINESHAFT_PLANTS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "ocean_mineshaft_dense_plants"), OCEAN_MINESHAFT_DENSE_PLANTS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dark_forest_mineshaft_vines"), DARK_FOREST_MINESHAFT_VINES);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dark_forest_mineshaft_vines_long"), DARK_FOREST_MINESHAFT_VINES_LONG);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "swamp_mineshaft_vines"), SWAMP_MINESHAFT_VINES);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "swamp_mineshaft_vines_long"), SWAMP_MINESHAFT_VINES_LONG);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "jungle_mineshaft_leaves_and_vines"), JUNGLE_MINESHAFT_LEAVES_AND_VINES);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "jungle_mineshaft_vines_long"), JUNGLE_MINESHAFT_VINES_LONG);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "warped_mineshaft_plants"), WARPED_MINESHAFT_PLANTS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "warped_mineshaft_dense_plants"), WARPED_MINESHAFT_DENSE_PLANTS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "warped_mineshaft_plants_long"), WARPED_MINESHAFT_PLANTS_LONG);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "jungle_pyramid_structure_vines"), JUNGLE_PYRAMID_STRUCTURE_VINES);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "jungle_pyramid_structure_vines_narrow"), JUNGLE_PYRAMID_STRUCTURE_VINES_NARROW);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "ocean_pyramid_structure_plants"), OCEAN_PYRAMID_STRUCTURE_PLANTS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "flower_forest_pyramid_structure_grass"), FLOWER_FOREST_PYRAMID_STRUCTURE_GRASS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "flower_forest_pyramid_structure_flowers"), FLOWER_FOREST_PYRAMID_STRUCTURE_FLOWERS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "swamp_village_structure_vines"), SWAMP_VILLAGE_STRUCTURE_VINES);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "warm_land_ruins_structure_grass"), WARM_LAND_RUINS_STRUCTURE_GRASS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "hot_land_ruins_structure_dead_bush"), HOT_LAND_RUINS_STRUCTURE_DEAD_BUSH);
    }
}
