package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.features.*;
import com.telepathicgrunt.repurposedstructures.world.features.configs.GenericMobConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.MinecartConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.MineshaftSupportConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtDungeonConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtFeatureConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndRangeConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetChanceConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetLengthRangeConfig;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;


public final class RSFeatures {
    private RSFeatures() {}

    //Static instance of our structure so we can reference it and add it to biomes easily.
    public static Feature<NbtDungeonConfig> BADLANDS_DUNGEONS = new NbtDungeon(NbtDungeonConfig.CODEC);
    public static Feature<NbtDungeonConfig> DARK_FOREST_DUNGEONS = new NbtDungeon(NbtDungeonConfig.CODEC);
    public static Feature<NbtDungeonConfig> DESERT_DUNGEONS = new NbtDungeon(NbtDungeonConfig.CODEC);
    public static Feature<NbtDungeonConfig> END_DUNGEONS = new NbtDungeon(NbtDungeonConfig.CODEC);
    public static Feature<NbtDungeonConfig> NETHER_DUNGEONS = new NbtDungeon(NbtDungeonConfig.CODEC);
    public static Feature<NbtDungeonConfig> SNOW_DUNGEONS = new NbtDungeon(NbtDungeonConfig.CODEC);
    public static Feature<NbtDungeonConfig> ICY_DUNGEONS = new NbtDungeon(NbtDungeonConfig.CODEC);
    public static Feature<NbtDungeonConfig> SWAMP_DUNGEONS = new NbtDungeon(NbtDungeonConfig.CODEC);
    public static Feature<NbtDungeonConfig> MUSHROOM_DUNGEONS = new NbtDungeon(NbtDungeonConfig.CODEC);
    public static Feature<NbtDungeonConfig> JUNGLE_DUNGEONS = new NbtDungeon(NbtDungeonConfig.CODEC);
    public static Feature<NbtDungeonConfig> OCEAN_DUNGEONS = new NbtDungeon(NbtDungeonConfig.CODEC);

    public static Feature<NbtFeatureConfig> BADLANDS_WELL = new NbtFeature();
    public static Feature<NbtFeatureConfig> NETHER_WELL = new NbtFeature();
    public static Feature<NbtFeatureConfig> SNOW_WELL = new NbtFeature();
    public static Feature<NbtFeatureConfig> MOSSY_STONE_WELL = new NbtFeature();
    public static Feature<NbtFeatureConfig> FOREST_WELL = new NbtFeature();
    public static Feature<NbtFeatureConfig> MUSHROOM_WELL = new NbtFeature();

    public static Feature<NoneFeatureConfiguration> WITHER_SKELETON_WITH_BOW = new WitherSkeletonWithBow();
    public static Feature<NoneFeatureConfiguration> SHULKER_MOB = new ShulkerMob();
    public static Feature<NoneFeatureConfiguration> DROWNED_WITH_ARMOR = new DrownedWithArmor();
    public static Feature<GenericMobConfig> SKELETON = new Skeletons();
    public static Feature<GenericMobConfig> SKELETON_HORSEMAN = new SkeletonHorseman();

    public static Feature<NoneFeatureConfiguration> POST_PROCESS_CONNECTING_BLOCKS = new StructurePostProcessConnectiveBlocks();
    public static Feature<StructureTargetChanceConfig> STRUCTURE_BREAKAGE = new StructureBreakage(StructureTargetChanceConfig.CODEC);
    public static Feature<StructureTargetConfig> STRUCTURE_CHAINS = new StructureChains(StructureTargetConfig.CODEC);
    public static Feature<StructureTargetConfig> STRUCTURE_END_ROD_CHAINS = new StructureEndRodChains(StructureTargetConfig.CODEC);
    public static Feature<StructureTargetConfig> STRUCTURE_CHORUS = new StructureChorus(StructureTargetConfig.CODEC);
    public static Feature<StructureTargetAndLengthConfig> STRUCTURE_CRIMSON_PLANTS = new StructureCrimsonPlants(StructureTargetAndLengthConfig.CODEC);
    public static Feature<StructureTargetConfig> STRUCTURE_FIRE = new StructureFire(StructureTargetConfig.CODEC);
    public static Feature<StructureTargetConfig> STRUCTURE_NETHERWART = new StructureNetherwart(StructureTargetConfig.CODEC);
    public static Feature<StructureTargetConfig> STRUCTURE_SEAGRASS = new StructureSeagrass(StructureTargetConfig.CODEC);
    public static Feature<StructureTargetLengthRangeConfig> STRUCTURE_VINES = new StructureVine(StructureTargetLengthRangeConfig.CODEC);
    public static Feature<StructureTargetAndLengthConfig> STRUCTURE_VINES_AND_LEAVES = new StructureVineAndLeaves(StructureTargetAndLengthConfig.CODEC);
    public static Feature<StructureTargetAndLengthConfig> STRUCTURE_WARPED_PLANTS = new StructureWarpedPlants(StructureTargetAndLengthConfig.CODEC);
    public static Feature<StructureTargetAndLengthConfig> STRUCTURE_VINE_BREAKAGE = new StructureVineBreakage(StructureTargetAndLengthConfig.CODEC);
    public static Feature<StructureTargetAndRangeConfig> STRUCTURE_GRASS = new StructureGrass(StructureTargetAndRangeConfig.CODEC);
    public static Feature<StructureTargetAndRangeConfig> STRUCTURE_FLOWERS = new StructureFlowers(StructureTargetAndRangeConfig.CODEC);

    public static Feature<MinecartConfig> MINESHAFT_MINECARTS = new MinecartFeature(MinecartConfig.CODEC);
    public static Feature<MineshaftSupportConfig> MINESHAFT_SUPPORTS = new MineshaftSupport(MineshaftSupportConfig.CODEC);

    public static void registerFeatures() {
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "dungeons_badlands"), BADLANDS_DUNGEONS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "dungeons_dark_forest"), DARK_FOREST_DUNGEONS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "dungeons_desert"), DESERT_DUNGEONS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "dungeons_end"), END_DUNGEONS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "dungeons_nether"), NETHER_DUNGEONS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "dungeons_snow"), SNOW_DUNGEONS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "dungeons_icy"), ICY_DUNGEONS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "dungeons_swamp"), SWAMP_DUNGEONS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "dungeons_mushroom"), MUSHROOM_DUNGEONS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "dungeons_jungle"), JUNGLE_DUNGEONS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "dungeons_ocean"), OCEAN_DUNGEONS);

        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "well_badlands"), BADLANDS_WELL);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "well_nether"), NETHER_WELL);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "well_snow"), SNOW_WELL);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "well_mossy_stone"), MOSSY_STONE_WELL);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "well_forest"), FOREST_WELL);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "well_mushroom"), MUSHROOM_WELL);

        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "wither_skeleton_with_bow"), WITHER_SKELETON_WITH_BOW);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "shulker_mob"), SHULKER_MOB);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "drowned_with_armor"), DROWNED_WITH_ARMOR);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "skeleton"), SKELETON);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "skeleton_horseman"), SKELETON_HORSEMAN);

        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "post_process_connecting_blocks"), POST_PROCESS_CONNECTING_BLOCKS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "structure_breakage"), STRUCTURE_BREAKAGE);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "structure_chains"), STRUCTURE_CHAINS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "structure_end_rod_chains"), STRUCTURE_END_ROD_CHAINS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "structure_chorus"), STRUCTURE_CHORUS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "structure_crimson_plants"), STRUCTURE_CRIMSON_PLANTS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "structure_fire"), STRUCTURE_FIRE);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "structure_netherwart"), STRUCTURE_NETHERWART);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "structure_seagrass"), STRUCTURE_SEAGRASS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "structure_vines"), STRUCTURE_VINES);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "structure_vines_and_leaves"), STRUCTURE_VINES_AND_LEAVES);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "structure_warped_plants"), STRUCTURE_WARPED_PLANTS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "structure_vine_breakage"), STRUCTURE_VINE_BREAKAGE);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "structure_grass"), STRUCTURE_GRASS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "structure_flowers"), STRUCTURE_FLOWERS);

        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "mineshaft_minecarts"), MINESHAFT_MINECARTS);
        Registry.register(Registry.FEATURE, new ResourceLocation(RepurposedStructures.MODID, "mineshaft_supports"), MINESHAFT_SUPPORTS);
    }
}
