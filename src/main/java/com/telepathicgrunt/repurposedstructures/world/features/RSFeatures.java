package com.telepathicgrunt.repurposedstructures.world.features;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.features.structures.*;
import net.earthcomputer.libstructure.LibStructure;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class RSFeatures {
    public static final BlockPileFeatureConfig COBBLESTONE_PILE_CONFIG = new BlockPileFeatureConfig(new SimpleBlockStateProvider(Blocks.COBBLESTONE.getDefaultState()));

    //Static instance of our structure so we can reference it and add it to biomes easily.
    public static Feature<DefaultFeatureConfig> BADLANDS_DUNGEONS = new DungeonBadlands(DefaultFeatureConfig.CODEC);
    public static Feature<DefaultFeatureConfig> DARK_FOREST_DUNGEONS = new DungeonDarkForest(DefaultFeatureConfig.CODEC);
    public static Feature<DefaultFeatureConfig> DESERT_DUNGEONS = new DungeonDesert(DefaultFeatureConfig.CODEC);
    public static Feature<DefaultFeatureConfig> END_DUNGEONS = new DungeonEnd(DefaultFeatureConfig.CODEC);
    public static Feature<DefaultFeatureConfig> NETHER_DUNGEONS = new DungeonNether(DefaultFeatureConfig.CODEC);
    public static Feature<DefaultFeatureConfig> SNOW_DUNGEONS = new DungeonSnow(DefaultFeatureConfig.CODEC);
    public static Feature<DefaultFeatureConfig> SWAMP_DUNGEONS = new DungeonSwamp(DefaultFeatureConfig.CODEC);
    public static Feature<DefaultFeatureConfig> MUSHROOM_DUNGEONS = new DungeonMushroom(DefaultFeatureConfig.CODEC);
    public static Feature<DefaultFeatureConfig> JUNGLE_DUNGEONS = new DungeonJungle(DefaultFeatureConfig.CODEC);
    public static Feature<DefaultFeatureConfig> OCEAN_DUNGEONS = new DungeonOcean(DefaultFeatureConfig.CODEC);

    public static Feature<DefaultFeatureConfig> BADLANDS_WELL = new WellBadlands(DefaultFeatureConfig.CODEC);
    public static Feature<DefaultFeatureConfig> NETHER_WELL = new WellNether(DefaultFeatureConfig.CODEC);
    public static Feature<DefaultFeatureConfig> SNOW_WELL = new WellSnow(DefaultFeatureConfig.CODEC);
    public static Feature<DefaultFeatureConfig> MOSSY_STONE_WELL = new WellMossyStone(DefaultFeatureConfig.CODEC);
    public static Feature<DefaultFeatureConfig> FOREST_WELL = new WellForest(DefaultFeatureConfig.CODEC);

    public static Feature<DefaultFeatureConfig> BOULDER_GIANT = new BoulderGiant(DefaultFeatureConfig.CODEC);
    public static Feature<DefaultFeatureConfig> BOULDER_TINY = new BoulderTiny(DefaultFeatureConfig.CODEC);
    public static Feature<TreeFeatureConfig> HORNED_SWAMP_TREE = new TreeSwampHorned(TreeFeatureConfig.CODEC);
    public static Feature<DefaultFeatureConfig> SHORT_VINES = new VinesShort(DefaultFeatureConfig.CODEC);
    public static Feature<DefaultFeatureConfig> SWAMP_VILLAGE_VINES = new SwampVillageVines(DefaultFeatureConfig.CODEC);
    public static Feature<DefaultFeatureConfig> JUNGLE_VILLAGE_VINES = new JungleVillageVines(DefaultFeatureConfig.CODEC);

    public static StructureFeature<DefaultFeatureConfig> BIRCH_MINESHAFT = new RSMineshaftBirchStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> DESERT_MINESHAFT = new RSMineshaftDesertStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> END_MINESHAFT = new RSMineshaftEndStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> NETHER_MINESHAFT = new RSMineshaftNetherStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> ICY_MINESHAFT = new RSMineshaftIcyStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> JUNGLE_MINESHAFT = new RSMineshaftJungleStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> OCEAN_MINESHAFT = new RSMineshaftOceanStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> SAVANNA_MINESHAFT = new RSMineshaftSavannaStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> STONE_MINESHAFT = new RSMineshaftStoneStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> SWAMP_OR_DARK_FOREST_MINESHAFT = new RSMineshaftSwampOrDarkForestStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> TAIGA_MINESHAFT = new RSMineshaftTaigaStructure(DefaultFeatureConfig.CODEC);

    public static StructureFeature<DefaultFeatureConfig> STONEBRICK_STRONGHOLD = new RSStonebrickStrongholdStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> NETHER_STRONGHOLD = new RSNetherStrongholdStructure(DefaultFeatureConfig.CODEC);

    public static StructureFeature<DefaultFeatureConfig> JUNGLE_FORTRESS = new JungleFortressStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> NETHER_TEMPLE = new NetherTempleStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> BADLANDS_TEMPLE = new BadlandsTempleStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> GRASSY_IGLOO = new IglooGrassyStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> STONE_IGLOO = new IglooStoneStructure(DefaultFeatureConfig.CODEC);

    public static StructureFeature<DefaultFeatureConfig> BADLANDS_VILLAGE = new VillageBadlandsStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> BIRCH_VILLAGE = new VillageBirchStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> DARK_FOREST_VILLAGE = new VillageDarkForestStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> JUNGLE_VILLAGE = new VillageJungleStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> SWAMP_VILLAGE = new VillageSwampStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> MOUNTAINS_VILLAGE = new VillageMountainsStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<DefaultFeatureConfig> GIANT_TAIGA_VILLAGE = new VillageGiantTaigaStructure(DefaultFeatureConfig.CODEC);
    public static List<StructureFeature<DefaultFeatureConfig>> VILLAGE_LIST = Arrays.asList(
            BADLANDS_VILLAGE,
            BIRCH_VILLAGE,
            DARK_FOREST_VILLAGE,
            JUNGLE_VILLAGE,
            SWAMP_VILLAGE,
            MOUNTAINS_VILLAGE,
            GIANT_TAIGA_VILLAGE
    );

    public static void registerFeatures() {
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_badlands", BADLANDS_DUNGEONS);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_dark_forest", DARK_FOREST_DUNGEONS);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_desert", DESERT_DUNGEONS);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_end", END_DUNGEONS);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_nether", NETHER_DUNGEONS);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_snow", SNOW_DUNGEONS);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_swamp", SWAMP_DUNGEONS);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_mushroom", MUSHROOM_DUNGEONS);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_jungle", JUNGLE_DUNGEONS);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "dungeons_ocean", OCEAN_DUNGEONS);

        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "well_badlands", BADLANDS_WELL);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "well_nether", NETHER_WELL);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "well_snow", SNOW_WELL);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "well_mossy_stone", MOSSY_STONE_WELL);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "well_forest", FOREST_WELL);

        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "boulder_giant", BOULDER_GIANT);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "boulder_tiny", BOULDER_TINY);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "horned_swamp_tree", HORNED_SWAMP_TREE);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "short_vines", SHORT_VINES);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "swamp_village_vines", SWAMP_VILLAGE_VINES);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "jungle_village_vines", JUNGLE_VILLAGE_VINES);

        LibStructure.registerStructure(new Identifier(RepurposedStructures.MODID, "mineshaft_birch"), BIRCH_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117345), BIRCH_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new Identifier(RepurposedStructures.MODID, "mineshaft_desert"), DESERT_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117346), DESERT_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new Identifier(RepurposedStructures.MODID, "mineshaft_end"), END_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117347), END_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new Identifier(RepurposedStructures.MODID, "mineshaft_nether"), NETHER_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117348), NETHER_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new Identifier(RepurposedStructures.MODID, "mineshaft_icy"), ICY_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117349), ICY_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new Identifier(RepurposedStructures.MODID, "mineshaft_jungle"), JUNGLE_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117350), JUNGLE_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new Identifier(RepurposedStructures.MODID, "mineshaft_ocean"), OCEAN_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117351), OCEAN_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new Identifier(RepurposedStructures.MODID, "mineshaft_savanna"), SAVANNA_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117352), SAVANNA_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new Identifier(RepurposedStructures.MODID, "mineshaft_stone"), STONE_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117353), STONE_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new Identifier(RepurposedStructures.MODID, "mineshaft_swamp_or_dark_forest"), SWAMP_OR_DARK_FOREST_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117354), SWAMP_OR_DARK_FOREST_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new Identifier(RepurposedStructures.MODID, "mineshaft_taiga"), TAIGA_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117355), TAIGA_MINESHAFT.configure(FeatureConfig.DEFAULT));

        LibStructure.registerStructure(new Identifier(RepurposedStructures.MODID, "stronghold_stonebrick"), STONEBRICK_STRONGHOLD, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(RepurposedStructures.RSStrongholdsConfig.stonebrick.stonebrickStrongholdSpawnrate, (int) (RepurposedStructures.RSStrongholdsConfig.stonebrick.stonebrickStrongholdSpawnrate * 0.75f), 399117368), STONEBRICK_STRONGHOLD.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new Identifier(RepurposedStructures.MODID, "stronghold_nether"), NETHER_STRONGHOLD, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(RepurposedStructures.RSStrongholdsConfig.nether.netherStrongholdSpawnrate, (int) (RepurposedStructures.RSStrongholdsConfig.nether.netherStrongholdSpawnrate * 0.75f), 399117369), NETHER_STRONGHOLD.configure(FeatureConfig.DEFAULT));

        LibStructure.registerStructure(new Identifier(RepurposedStructures.MODID, "fortress_jungle"), JUNGLE_FORTRESS, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSMainConfig.jungleFortress.jungleFortressSpawnrate, (int) (RepurposedStructures.RSMainConfig.jungleFortress.jungleFortressSpawnrate * 0.75f), 399117356), JUNGLE_FORTRESS.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new Identifier(RepurposedStructures.MODID, "temple_nether"), NETHER_TEMPLE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSMainConfig.temples.netherTempleSpawnrate, (int) (RepurposedStructures.RSMainConfig.temples.netherTempleSpawnrate * 0.75f), 399117357), NETHER_TEMPLE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new Identifier(RepurposedStructures.MODID, "temple_badlands"), BADLANDS_TEMPLE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSMainConfig.temples.badlandsTempleSpawnrate, (int) (RepurposedStructures.RSMainConfig.temples.badlandsTempleSpawnrate * 0.75f), 399117358), BADLANDS_TEMPLE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new Identifier(RepurposedStructures.MODID, "igloo_grassy"), GRASSY_IGLOO, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSMainConfig.igloos.grassyIglooSpawnrate, (int) (RepurposedStructures.RSMainConfig.igloos.grassyIglooSpawnrate * 0.75f), 399117359), GRASSY_IGLOO.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new Identifier(RepurposedStructures.MODID, "igloo_stone"), STONE_IGLOO, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSMainConfig.igloos.stoneIglooSpawnrate, (int) (RepurposedStructures.RSMainConfig.igloos.stoneIglooSpawnrate * 0.75f), 399117360), STONE_IGLOO.configure(FeatureConfig.DEFAULT));

        LibStructure.registerSurfaceAdjustingStructure(new Identifier(RepurposedStructures.MODID, "village_badlands"), BADLANDS_VILLAGE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSVillagesConfig.badlandsVillageSpawnrate, (int) (RepurposedStructures.RSVillagesConfig.badlandsVillageSpawnrate * 0.75f), 399117361), BADLANDS_VILLAGE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerSurfaceAdjustingStructure(new Identifier(RepurposedStructures.MODID, "village_birch"), BIRCH_VILLAGE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSVillagesConfig.birchVillageSpawnrate, (int) (RepurposedStructures.RSVillagesConfig.birchVillageSpawnrate * 0.75f), 399117362), BIRCH_VILLAGE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerSurfaceAdjustingStructure(new Identifier(RepurposedStructures.MODID, "village_dark_oak"), DARK_FOREST_VILLAGE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSVillagesConfig.darkForestVillageSpawnrate, (int) (RepurposedStructures.RSVillagesConfig.darkForestVillageSpawnrate * 0.75f), 399117363), DARK_FOREST_VILLAGE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerSurfaceAdjustingStructure(new Identifier(RepurposedStructures.MODID, "village_jungle"), JUNGLE_VILLAGE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSVillagesConfig.jungleVillageSpawnrate, (int) (RepurposedStructures.RSVillagesConfig.jungleVillageSpawnrate * 0.75f), 399117364), JUNGLE_VILLAGE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerSurfaceAdjustingStructure(new Identifier(RepurposedStructures.MODID, "village_swamp"), SWAMP_VILLAGE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSVillagesConfig.swampVillageSpawnrate, (int) (RepurposedStructures.RSVillagesConfig.swampVillageSpawnrate * 0.75f), 399117365), SWAMP_VILLAGE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerSurfaceAdjustingStructure(new Identifier(RepurposedStructures.MODID, "village_mountains"), MOUNTAINS_VILLAGE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSVillagesConfig.mountainsVillageSpawnrate, (int) (RepurposedStructures.RSVillagesConfig.mountainsVillageSpawnrate * 0.75f), 399117366), MOUNTAINS_VILLAGE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerSurfaceAdjustingStructure(new Identifier(RepurposedStructures.MODID, "village_giant_taiga"), GIANT_TAIGA_VILLAGE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSVillagesConfig.giantTaigaVillageSpawnrate, (int) (RepurposedStructures.RSVillagesConfig.giantTaigaVillageSpawnrate * 0.75f), 399117367), GIANT_TAIGA_VILLAGE.configure(FeatureConfig.DEFAULT));

        //registers the structure pieces.
        StructurePieces.registerStructurePieces();
    }

    public static void registerVillagePools() {
        VillageBadlandsPools.init();
        VillageBirchPools.init();
        VillageDarkForestPools.init();
        VillageJunglePools.init();
        VillageSwampPools.init();
        VillageMountainsPools.init();
        VillageGiantTaigaPools.init();
    }

    /**
     * Helper method that will return a random vanilla dungeon mob
     */
    public static EntityType<?> pickRandomVillageDungeonMob(Random random) {
        int chance = random.nextInt(4);
        if (chance == 0) {
            return EntityType.SPIDER;
        } else if (chance == 0) {
            return EntityType.SKELETON;
        } else {
            return EntityType.ZOMBIE;
        }
    }

}
