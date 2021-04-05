package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.features.*;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtDungeonConfig;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;


public class RSFeatures {

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

    public static Feature<DefaultFeatureConfig> BADLANDS_WELL = new WellBadlands();
    public static Feature<DefaultFeatureConfig> NETHER_WELL = new WellNether();
    public static Feature<DefaultFeatureConfig> SNOW_WELL = new WellSnow();
    public static Feature<DefaultFeatureConfig> MOSSY_STONE_WELL = new WellMossyStone();
    public static Feature<DefaultFeatureConfig> FOREST_WELL = new WellForest();

    public static Feature<DefaultFeatureConfig> BOULDER_GIANT = new BoulderGiant();
    public static Feature<DefaultFeatureConfig> BOULDER_TINY = new BoulderTiny();
    public static Feature<DefaultFeatureConfig> SHORT_VINES = new VinesShort();
    public static Feature<DefaultFeatureConfig> SWAMP_VILLAGE_VINES = new SwampVillageVines();
    public static Feature<DefaultFeatureConfig> JUNGLE_STRUCTURES_VINES = new JungleStructuresVines();
    public static Feature<DefaultFeatureConfig> FORTRESS_BREAKAGE = new FortressBreakage();
    public static Feature<DefaultFeatureConfig> STRONGHOLD_CHAINS = new StrongholdChains();

    public static Feature<DefaultFeatureConfig> WITHER_SKELETON_WITH_BOW = new WitherSkeletonWithBow();
    public static Feature<DefaultFeatureConfig> SHULKER_MOB = new ShulkerMob();

    public static void registerFeatures() {
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "dungeons_badlands"), BADLANDS_DUNGEONS);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "dungeons_dark_forest"), DARK_FOREST_DUNGEONS);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "dungeons_desert"), DESERT_DUNGEONS);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "dungeons_end"), END_DUNGEONS);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "dungeons_nether"), NETHER_DUNGEONS);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "dungeons_snow"), SNOW_DUNGEONS);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "dungeons_icy"), ICY_DUNGEONS);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "dungeons_swamp"), SWAMP_DUNGEONS);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "dungeons_mushroom"), MUSHROOM_DUNGEONS);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "dungeons_jungle"), JUNGLE_DUNGEONS);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "dungeons_ocean"), OCEAN_DUNGEONS);

        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "well_badlands"), BADLANDS_WELL);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "well_nether"), NETHER_WELL);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "well_snow"), SNOW_WELL);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "well_mossy_stone"), MOSSY_STONE_WELL);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "well_forest"), FOREST_WELL);

        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "boulder_giant"), BOULDER_GIANT);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "boulder_tiny"), BOULDER_TINY);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "short_vines"), SHORT_VINES);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "swamp_village_vines"), SWAMP_VILLAGE_VINES);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "jungle_structures_vines"), JUNGLE_STRUCTURES_VINES);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "fortress_breakage"), FORTRESS_BREAKAGE);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "stronghold_chains"), STRONGHOLD_CHAINS);

        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "wither_skeleton_with_bow"), WITHER_SKELETON_WITH_BOW);
        Registry.register(Registry.FEATURE, new Identifier(RepurposedStructures.MODID, "shulker_mob"), SHULKER_MOB);
    }
}
