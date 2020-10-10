package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.world.features.*;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;


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

    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        IForgeRegistry<Feature<?>> featureRegistry = event.getRegistry();

        featureRegistry.register(BADLANDS_DUNGEONS.setRegistryName(RepurposedStructures.MODID, "dungeons_badlands"));
        featureRegistry.register(DARK_FOREST_DUNGEONS.setRegistryName(RepurposedStructures.MODID, "dungeons_dark_forest"));
        featureRegistry.register(DESERT_DUNGEONS.setRegistryName(RepurposedStructures.MODID, "dungeons_desert"));
        featureRegistry.register(END_DUNGEONS.setRegistryName(RepurposedStructures.MODID, "dungeons_end"));
        featureRegistry.register(NETHER_DUNGEONS.setRegistryName(RepurposedStructures.MODID, "dungeons_nether"));
        featureRegistry.register(SNOW_DUNGEONS.setRegistryName(RepurposedStructures.MODID, "dungeons_snow"));
        featureRegistry.register(SWAMP_DUNGEONS.setRegistryName(RepurposedStructures.MODID, "dungeons_swamp"));
        featureRegistry.register(MUSHROOM_DUNGEONS.setRegistryName(RepurposedStructures.MODID, "dungeons_mushroom"));
        featureRegistry.register(JUNGLE_DUNGEONS.setRegistryName(RepurposedStructures.MODID, "dungeons_jungle"));
        featureRegistry.register(OCEAN_DUNGEONS.setRegistryName(RepurposedStructures.MODID, "dungeons_ocean"));

        featureRegistry.register(BADLANDS_WELL.setRegistryName(RepurposedStructures.MODID, "well_badlands"));
        featureRegistry.register(NETHER_WELL.setRegistryName(RepurposedStructures.MODID, "well_nether"));
        featureRegistry.register(SNOW_WELL.setRegistryName(RepurposedStructures.MODID, "well_snow"));
        featureRegistry.register(MOSSY_STONE_WELL.setRegistryName(RepurposedStructures.MODID, "well_mossy_stone"));
        featureRegistry.register(FOREST_WELL.setRegistryName(RepurposedStructures.MODID, "well_forest"));

        featureRegistry.register(BOULDER_GIANT.setRegistryName(RepurposedStructures.MODID, "boulder_giant"));
        featureRegistry.register(BOULDER_TINY.setRegistryName(RepurposedStructures.MODID, "boulder_tiny"));
        featureRegistry.register(HORNED_SWAMP_TREE.setRegistryName(RepurposedStructures.MODID, "horned_swamp_tree"));
        featureRegistry.register(SHORT_VINES.setRegistryName(RepurposedStructures.MODID, "short_vines"));
        featureRegistry.register(SWAMP_VILLAGE_VINES.setRegistryName(RepurposedStructures.MODID, "swamp_village_vines"));
        featureRegistry.register(JUNGLE_STRUCTURES_VINES.setRegistryName(RepurposedStructures.MODID, "jungle_structures_vines"));
        featureRegistry.register(FORTRESS_BREAKAGE.setRegistryName(RepurposedStructures.MODID, "fortress_breakage"));
        featureRegistry.register(STRONGHOLD_CHAINS.setRegistryName(RepurposedStructures.MODID, "stronghold_chains"));
    }
}
