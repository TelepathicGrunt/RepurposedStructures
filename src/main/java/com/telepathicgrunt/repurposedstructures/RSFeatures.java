package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.world.features.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
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

        registerFeature(featureRegistry, BADLANDS_DUNGEONS, new ResourceLocation(RepurposedStructures.MODID, "dungeons_badlands"));
        registerFeature(featureRegistry, DARK_FOREST_DUNGEONS, new ResourceLocation(RepurposedStructures.MODID, "dungeons_dark_forest"));
        registerFeature(featureRegistry, DESERT_DUNGEONS, new ResourceLocation(RepurposedStructures.MODID, "dungeons_desert"));
        registerFeature(featureRegistry, END_DUNGEONS, new ResourceLocation(RepurposedStructures.MODID, "dungeons_end"));
        registerFeature(featureRegistry, NETHER_DUNGEONS, new ResourceLocation(RepurposedStructures.MODID, "dungeons_nether"));
        registerFeature(featureRegistry, SNOW_DUNGEONS, new ResourceLocation(RepurposedStructures.MODID, "dungeons_snow"));
        registerFeature(featureRegistry, SWAMP_DUNGEONS, new ResourceLocation(RepurposedStructures.MODID, "dungeons_swamp"));
        registerFeature(featureRegistry, MUSHROOM_DUNGEONS, new ResourceLocation(RepurposedStructures.MODID, "dungeons_mushroom"));
        registerFeature(featureRegistry, JUNGLE_DUNGEONS, new ResourceLocation(RepurposedStructures.MODID, "dungeons_jungle"));
        registerFeature(featureRegistry, OCEAN_DUNGEONS, new ResourceLocation(RepurposedStructures.MODID, "dungeons_ocean"));

        registerFeature(featureRegistry, BADLANDS_WELL, new ResourceLocation(RepurposedStructures.MODID, "well_badlands"));
        registerFeature(featureRegistry, NETHER_WELL, new ResourceLocation(RepurposedStructures.MODID, "well_nether"));
        registerFeature(featureRegistry, SNOW_WELL, new ResourceLocation(RepurposedStructures.MODID, "well_snow"));
        registerFeature(featureRegistry, MOSSY_STONE_WELL, new ResourceLocation(RepurposedStructures.MODID, "well_mossy_stone"));
        registerFeature(featureRegistry, FOREST_WELL, new ResourceLocation(RepurposedStructures.MODID, "well_forest"));

        registerFeature(featureRegistry, BOULDER_GIANT, new ResourceLocation(RepurposedStructures.MODID, "boulder_giant"));
        registerFeature(featureRegistry, BOULDER_TINY, new ResourceLocation(RepurposedStructures.MODID, "boulder_tiny"));
        registerFeature(featureRegistry, HORNED_SWAMP_TREE, new ResourceLocation(RepurposedStructures.MODID, "horned_swamp_tree"));
        registerFeature(featureRegistry, SHORT_VINES, new ResourceLocation(RepurposedStructures.MODID, "short_vines"));
        registerFeature(featureRegistry, SWAMP_VILLAGE_VINES, new ResourceLocation(RepurposedStructures.MODID, "swamp_village_vines"));
        registerFeature(featureRegistry, JUNGLE_STRUCTURES_VINES, new ResourceLocation(RepurposedStructures.MODID, "jungle_structures_vines"));
        registerFeature(featureRegistry, FORTRESS_BREAKAGE, new ResourceLocation(RepurposedStructures.MODID, "fortress_breakage"));
        registerFeature(featureRegistry, STRONGHOLD_CHAINS, new ResourceLocation(RepurposedStructures.MODID, "stronghold_chains"));
    }


    public static <F extends Feature<?>> void registerFeature(
            IForgeRegistry<Feature<?>> registry,
            F feature,
            ResourceLocation resourceLocation
    ) {
        feature.setRegistryName(resourceLocation);

        // Have to do this as Minecraft will otherwise think the feature isn't registered.
        // Hopefully this means people can make custom ConfiguredFeatures by datapack with the feature.
        Registry.register(Registry.FEATURE, resourceLocation, feature);
    }
}
