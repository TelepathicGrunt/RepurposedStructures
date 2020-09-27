package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.world.features.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;


public class RSFeatures {
    //Static instance of our structure so we can reference it and add it to biomes easily.
    public static Feature<NoFeatureConfig> BADLANDS_DUNGEONS = new DungeonBadlands(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> DARK_FOREST_DUNGEONS = new DungeonDarkForest(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> DESERT_DUNGEONS = new DungeonDesert(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> END_DUNGEONS = new DungeonEnd(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> NETHER_DUNGEONS = new DungeonNether(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> SNOW_DUNGEONS = new DungeonSnow(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> SWAMP_DUNGEONS = new DungeonSwamp(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> MUSHROOM_DUNGEONS = new DungeonMushroom(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> JUNGLE_DUNGEONS = new DungeonJungle(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> OCEAN_DUNGEONS = new DungeonOcean(NoFeatureConfig.CODEC);

    public static Feature<NoFeatureConfig> BADLANDS_WELL = new WellBadlands(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> NETHER_WELL = new WellNether(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> SNOW_WELL = new WellSnow(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> MOSSY_STONE_WELL = new WellMossyStone(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> FOREST_WELL = new WellForest(NoFeatureConfig.CODEC);

    public static Feature<NoFeatureConfig> BOULDER_GIANT = new BoulderGiant(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> BOULDER_TINY = new BoulderTiny(NoFeatureConfig.CODEC);
    public static Feature<BaseTreeFeatureConfig> HORNED_SWAMP_TREE = new TreeSwampHorned(BaseTreeFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> SHORT_VINES = new VinesShort(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> SWAMP_VILLAGE_VINES = new SwampVillageVines(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> JUNGLE_STRUCTURES_VINES = new JungleStructuresVines(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> FORTRESS_BREAKAGE = new FortressBreakage(NoFeatureConfig.CODEC);
    public static Feature<NoFeatureConfig> STRONGHOLD_CHAINS = new StrongholdChains(NoFeatureConfig.CODEC);

    public static void registerFeatures() {
        Registry<Feature<?>> featureRegistry = Registry.FEATURE;

        Registry.register(featureRegistry, RepurposedStructures.MODID + ":dungeons_badlands", BADLANDS_DUNGEONS);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":dungeons_dark_forest", DARK_FOREST_DUNGEONS);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":dungeons_desert", DESERT_DUNGEONS);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":dungeons_end", END_DUNGEONS);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":dungeons_nether", NETHER_DUNGEONS);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":dungeons_snow", SNOW_DUNGEONS);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":dungeons_swamp", SWAMP_DUNGEONS);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":dungeons_mushroom", MUSHROOM_DUNGEONS);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":dungeons_jungle", JUNGLE_DUNGEONS);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":dungeons_ocean", OCEAN_DUNGEONS);

        Registry.register(featureRegistry, RepurposedStructures.MODID + ":well_badlands", BADLANDS_WELL);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":well_nether", NETHER_WELL);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":well_snow", SNOW_WELL);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":well_mossy_stone", MOSSY_STONE_WELL);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":well_forest", FOREST_WELL);

        Registry.register(featureRegistry, RepurposedStructures.MODID + ":boulder_giant", BOULDER_GIANT);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":boulder_tiny", BOULDER_TINY);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":horned_swamp_tree", HORNED_SWAMP_TREE);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":short_vines", SHORT_VINES);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":swamp_village_vines", SWAMP_VILLAGE_VINES);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":jungle_structures_vines", JUNGLE_STRUCTURES_VINES);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":fortress_breakage", FORTRESS_BREAKAGE);
        Registry.register(featureRegistry, RepurposedStructures.MODID + ":stronghold_chains", STRONGHOLD_CHAINS);
    }
}
