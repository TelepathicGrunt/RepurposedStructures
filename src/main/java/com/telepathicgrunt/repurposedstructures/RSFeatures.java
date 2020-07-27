package com.telepathicgrunt.repurposedstructures;

import java.util.Arrays;
import java.util.List;

import com.telepathicgrunt.repurposedstructures.world.features.BoulderGiant;
import com.telepathicgrunt.repurposedstructures.world.features.BoulderTiny;
import com.telepathicgrunt.repurposedstructures.world.features.DungeonBadlands;
import com.telepathicgrunt.repurposedstructures.world.features.DungeonDarkForest;
import com.telepathicgrunt.repurposedstructures.world.features.DungeonDesert;
import com.telepathicgrunt.repurposedstructures.world.features.DungeonEnd;
import com.telepathicgrunt.repurposedstructures.world.features.DungeonJungle;
import com.telepathicgrunt.repurposedstructures.world.features.DungeonMushroom;
import com.telepathicgrunt.repurposedstructures.world.features.DungeonNether;
import com.telepathicgrunt.repurposedstructures.world.features.DungeonOcean;
import com.telepathicgrunt.repurposedstructures.world.features.DungeonSnow;
import com.telepathicgrunt.repurposedstructures.world.features.DungeonSwamp;
import com.telepathicgrunt.repurposedstructures.world.features.FortressBreakage;
import com.telepathicgrunt.repurposedstructures.world.features.JungleStructuresVines;
import com.telepathicgrunt.repurposedstructures.world.features.StrongholdChains;
import com.telepathicgrunt.repurposedstructures.world.features.SwampVillageVines;
import com.telepathicgrunt.repurposedstructures.world.features.TreeSwampHorned;
import com.telepathicgrunt.repurposedstructures.world.features.VinesShort;
import com.telepathicgrunt.repurposedstructures.world.features.WellBadlands;
import com.telepathicgrunt.repurposedstructures.world.features.WellForest;
import com.telepathicgrunt.repurposedstructures.world.features.WellMossyStone;
import com.telepathicgrunt.repurposedstructures.world.features.WellNether;
import com.telepathicgrunt.repurposedstructures.world.features.WellSnow;
import com.telepathicgrunt.repurposedstructures.world.features.structures.EndShipwreckStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.FortressJungleStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.IglooGrassyStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.IglooStoneStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.OutpostCrimsonStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.OutpostNetherBrickStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.OutpostWarpedStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.PyramidBadlandsStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.PyramidNetherStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftBirchStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftDesertStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftEndStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftIcyStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftJungleStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftNetherStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftOceanStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftSavannaStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftStoneStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftSwampOrDarkForestStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftTaigaStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSNetherStrongholdStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSStonebrickStrongholdStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.StructurePieces;
import com.telepathicgrunt.repurposedstructures.world.features.structures.TempleNetherBasaltStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.TempleNetherCrimsonStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.TempleNetherSoulStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.TempleNetherWarpedStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.TempleNetherWastelandStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.VillageBadlandsPools;
import com.telepathicgrunt.repurposedstructures.world.features.structures.VillageBadlandsStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.VillageBirchPools;
import com.telepathicgrunt.repurposedstructures.world.features.structures.VillageBirchStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.VillageCrimsonPools;
import com.telepathicgrunt.repurposedstructures.world.features.structures.VillageCrimsonStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.VillageDarkForestPools;
import com.telepathicgrunt.repurposedstructures.world.features.structures.VillageDarkForestStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.VillageGiantTaigaPools;
import com.telepathicgrunt.repurposedstructures.world.features.structures.VillageGiantTaigaStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.VillageJunglePools;
import com.telepathicgrunt.repurposedstructures.world.features.structures.VillageJungleStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.VillageMountainsPools;
import com.telepathicgrunt.repurposedstructures.world.features.structures.VillageMountainsStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.VillageSwampPools;
import com.telepathicgrunt.repurposedstructures.world.features.structures.VillageSwampStructure;
import com.telepathicgrunt.repurposedstructures.world.features.structures.VillageWarpedPools;
import com.telepathicgrunt.repurposedstructures.world.features.structures.VillageWarpedStructure;

import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockStateProvidingFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;


public class RSFeatures {
    public static final BlockStateProvidingFeatureConfig COBBLESTONE_PILE_CONFIG = new BlockStateProvidingFeatureConfig(new SimpleBlockStateProvider(Blocks.COBBLESTONE.getDefaultState()));

    //Static instance of our structure so we can reference it and add it to biomes easily.
    public static Feature<NoFeatureConfig> BADLANDS_DUNGEONS = new DungeonBadlands(NoFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> DARK_FOREST_DUNGEONS = new DungeonDarkForest(NoFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> DESERT_DUNGEONS = new DungeonDesert(NoFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> END_DUNGEONS = new DungeonEnd(NoFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> NETHER_DUNGEONS = new DungeonNether(NoFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> SNOW_DUNGEONS = new DungeonSnow(NoFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> SWAMP_DUNGEONS = new DungeonSwamp(NoFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> MUSHROOM_DUNGEONS = new DungeonMushroom(NoFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> JUNGLE_DUNGEONS = new DungeonJungle(NoFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> OCEAN_DUNGEONS = new DungeonOcean(NoFeatureConfig.field_236558_a_);

    public static Feature<NoFeatureConfig> BADLANDS_WELL = new WellBadlands(NoFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> NETHER_WELL = new WellNether(NoFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> SNOW_WELL = new WellSnow(NoFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> MOSSY_STONE_WELL = new WellMossyStone(NoFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> FOREST_WELL = new WellForest(NoFeatureConfig.field_236558_a_);

    public static Feature<NoFeatureConfig> BOULDER_GIANT = new BoulderGiant(NoFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> BOULDER_TINY = new BoulderTiny(NoFeatureConfig.field_236558_a_);
    public static Feature<TreeFeatureConfig> HORNED_SWAMP_TREE = new TreeSwampHorned(TreeFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> SHORT_VINES = new VinesShort(NoFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> SWAMP_VILLAGE_VINES = new SwampVillageVines(NoFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> JUNGLE_STRUCTURES_VINES = new JungleStructuresVines(NoFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> FORTRESS_BREAKAGE = new FortressBreakage(NoFeatureConfig.field_236558_a_);
    public static Feature<NoFeatureConfig> STRONGHOLD_CHAINS = new StrongholdChains(NoFeatureConfig.field_236558_a_);

    public static Structure<NoFeatureConfig> BIRCH_MINESHAFT = new RSMineshaftBirchStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> DESERT_MINESHAFT = new RSMineshaftDesertStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> END_MINESHAFT = new RSMineshaftEndStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> NETHER_MINESHAFT = new RSMineshaftNetherStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> ICY_MINESHAFT = new RSMineshaftIcyStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> JUNGLE_MINESHAFT = new RSMineshaftJungleStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> OCEAN_MINESHAFT = new RSMineshaftOceanStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> SAVANNA_MINESHAFT = new RSMineshaftSavannaStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> STONE_MINESHAFT = new RSMineshaftStoneStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> SWAMP_OR_DARK_FOREST_MINESHAFT = new RSMineshaftSwampOrDarkForestStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> TAIGA_MINESHAFT = new RSMineshaftTaigaStructure(NoFeatureConfig.field_236558_a_);

    public static Structure<NoFeatureConfig> STONEBRICK_STRONGHOLD = new RSStonebrickStrongholdStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> NETHER_STRONGHOLD = new RSNetherStrongholdStructure(NoFeatureConfig.field_236558_a_);

    public static Structure<NoFeatureConfig> JUNGLE_FORTRESS = new FortressJungleStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> GRASSY_IGLOO = new IglooGrassyStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> STONE_IGLOO = new IglooStoneStructure(NoFeatureConfig.field_236558_a_);

    public static Structure<NoFeatureConfig> NETHER_WASTELAND_TEMPLE = new TempleNetherWastelandStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> NETHER_BASALT_TEMPLE = new TempleNetherBasaltStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> NETHER_WARPED_TEMPLE = new TempleNetherWarpedStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> NETHER_CRIMSON_TEMPLE = new TempleNetherCrimsonStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> NETHER_SOUL_TEMPLE = new TempleNetherSoulStructure(NoFeatureConfig.field_236558_a_);

    public static Structure<NoFeatureConfig> NETHER_BRICK_OUTPOST = new OutpostNetherBrickStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> WARPED_OUTPOST = new OutpostWarpedStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> CRIMSON_OUTPOST = new OutpostCrimsonStructure(NoFeatureConfig.field_236558_a_);

    public static Structure<NoFeatureConfig> NETHER_PYRAMID = new PyramidNetherStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> BADLANDS_TEMPLE = new PyramidBadlandsStructure(NoFeatureConfig.field_236558_a_);

    public static Structure<NoFeatureConfig> END_SHIPWRECK = new EndShipwreckStructure(NoFeatureConfig.field_236558_a_);

    public static Structure<NoFeatureConfig> BADLANDS_VILLAGE = new VillageBadlandsStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> BIRCH_VILLAGE = new VillageBirchStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> DARK_FOREST_VILLAGE = new VillageDarkForestStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> JUNGLE_VILLAGE = new VillageJungleStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> SWAMP_VILLAGE = new VillageSwampStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> MOUNTAINS_VILLAGE = new VillageMountainsStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> GIANT_TAIGA_VILLAGE = new VillageGiantTaigaStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> CRIMSON_VILLAGE = new VillageCrimsonStructure(NoFeatureConfig.field_236558_a_);
    public static Structure<NoFeatureConfig> WARPED_VILLAGE = new VillageWarpedStructure(NoFeatureConfig.field_236558_a_);
    public static List<Structure<NoFeatureConfig>> OVERWORLD_VILLAGE_LIST = Arrays.asList(
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
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "jungle_structures_vines", JUNGLE_STRUCTURES_VINES);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "fortress_breakage", FORTRESS_BREAKAGE);
        Registry.register(Registry.FEATURE, RepurposedStructures.MODID + "stronghold_chains", STRONGHOLD_CHAINS);

        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_birch"), BIRCH_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117345), BIRCH_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_desert"), DESERT_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117346), DESERT_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_end"), END_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117347), END_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_nether"), NETHER_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117348), NETHER_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_icy"), ICY_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117349), ICY_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_jungle"), JUNGLE_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117350), JUNGLE_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_ocean"), OCEAN_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117351), OCEAN_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_savanna"), SAVANNA_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117352), SAVANNA_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_stone"), STONE_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117353), STONE_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_swamp_or_dark_forest"), SWAMP_OR_DARK_FOREST_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117354), SWAMP_OR_DARK_FOREST_MINESHAFT.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_taiga"), TAIGA_MINESHAFT, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(1, 0, 399117355), TAIGA_MINESHAFT.configure(FeatureConfig.DEFAULT));

        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "stronghold_stonebrick"), STONEBRICK_STRONGHOLD, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdSpawnrate * 0.75f), 399117356), STONEBRICK_STRONGHOLD.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "stronghold_nether"), NETHER_STRONGHOLD, GenerationStep.Feature.UNDERGROUND_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSpawnrate * 0.75f), 399117357), NETHER_STRONGHOLD.configure(FeatureConfig.DEFAULT));

        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "fortress_jungle"), JUNGLE_FORTRESS, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.jungleFortressSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.jungleFortressSpawnrate * 0.75f), 399117358), JUNGLE_FORTRESS.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "igloo_grassy"), GRASSY_IGLOO, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSMainConfig.igloos.grassyIglooSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSMainConfig.igloos.grassyIglooSpawnrate * 0.75f), 399117362), GRASSY_IGLOO.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "igloo_stone"), STONE_IGLOO, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSMainConfig.igloos.stoneIglooSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSMainConfig.igloos.stoneIglooSpawnrate * 0.75f), 399117363), STONE_IGLOO.configure(FeatureConfig.DEFAULT));

        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_wasteland"), NETHER_WASTELAND_TEMPLE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWastelandTempleSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWastelandTempleSpawnrate * 0.75f), 399117359), NETHER_WASTELAND_TEMPLE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_soul"), NETHER_SOUL_TEMPLE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherSoulTempleSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherSoulTempleSpawnrate * 0.75f), 399117359), NETHER_SOUL_TEMPLE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_basalt"), NETHER_BASALT_TEMPLE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherBasaltTempleSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherBasaltTempleSpawnrate * 0.75f), 399117359), NETHER_BASALT_TEMPLE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_crimson"), NETHER_CRIMSON_TEMPLE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherCrimsonTempleSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherCrimsonTempleSpawnrate * 0.75f), 399117359), NETHER_CRIMSON_TEMPLE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_warped"), NETHER_WARPED_TEMPLE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWarpedTempleSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWarpedTempleSpawnrate * 0.75f), 399117359), NETHER_WARPED_TEMPLE.configure(FeatureConfig.DEFAULT));

        LibStructure.registerSurfaceAdjustingStructure(new ResourceLocation(RepurposedStructures.MODID, "outpost_nether_brick"), NETHER_BRICK_OUTPOST, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.netherBrickOutpostSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.netherBrickOutpostSpawnrate * 0.75f), 399117371), NETHER_BRICK_OUTPOST.configure(FeatureConfig.DEFAULT));
        LibStructure.registerSurfaceAdjustingStructure(new ResourceLocation(RepurposedStructures.MODID, "outpost_warped"), WARPED_OUTPOST, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.warpedOutpostSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.warpedOutpostSpawnrate * 0.75f), 399117372), WARPED_OUTPOST.configure(FeatureConfig.DEFAULT));
        LibStructure.registerSurfaceAdjustingStructure(new ResourceLocation(RepurposedStructures.MODID, "outpost_crimson"), CRIMSON_OUTPOST, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.crimsonOutpostSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.crimsonOutpostSpawnrate * 0.75f), 399117373), CRIMSON_OUTPOST.configure(FeatureConfig.DEFAULT));

        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "pyramid_badlands"), BADLANDS_TEMPLE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.badlandsPyramidSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.badlandsPyramidSpawnrate * 0.75f), 399117360), BADLANDS_TEMPLE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerSurfaceAdjustingStructure(new ResourceLocation(RepurposedStructures.MODID, "pyramid_nether"), NETHER_PYRAMID, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.netherPyramidSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.netherPyramidSpawnrate * 0.75f), 399117361), NETHER_PYRAMID.configure(FeatureConfig.DEFAULT));

        LibStructure.registerStructure(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_end"), END_SHIPWRECK, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSMainConfig.shipwrecks.endShipwreckSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSMainConfig.shipwrecks.endShipwreckSpawnrate * 0.75f), 399117374), END_SHIPWRECK.configure(FeatureConfig.DEFAULT));

        LibStructure.registerSurfaceAdjustingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_badlands"), BADLANDS_VILLAGE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSVillagesConfig.badlandsVillageSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSVillagesConfig.badlandsVillageSpawnrate * 0.75f), 399117364), BADLANDS_VILLAGE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerSurfaceAdjustingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_birch"), BIRCH_VILLAGE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSVillagesConfig.birchVillageSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSVillagesConfig.birchVillageSpawnrate * 0.75f), 399117365), BIRCH_VILLAGE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerSurfaceAdjustingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_dark_oak"), DARK_FOREST_VILLAGE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSVillagesConfig.darkForestVillageSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSVillagesConfig.darkForestVillageSpawnrate * 0.75f), 399117366), DARK_FOREST_VILLAGE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerSurfaceAdjustingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_jungle"), JUNGLE_VILLAGE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSVillagesConfig.jungleVillageSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSVillagesConfig.jungleVillageSpawnrate * 0.75f), 399117367), JUNGLE_VILLAGE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerSurfaceAdjustingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_swamp"), SWAMP_VILLAGE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSVillagesConfig.swampVillageSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSVillagesConfig.swampVillageSpawnrate * 0.75f), 399117368), SWAMP_VILLAGE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerSurfaceAdjustingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_mountains"), MOUNTAINS_VILLAGE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSVillagesConfig.mountainsVillageSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSVillagesConfig.mountainsVillageSpawnrate * 0.75f), 399117369), MOUNTAINS_VILLAGE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerSurfaceAdjustingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_giant_taiga"), GIANT_TAIGA_VILLAGE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSVillagesConfig.giantTaigaVillageSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSVillagesConfig.giantTaigaVillageSpawnrate * 0.75f), 399117370), GIANT_TAIGA_VILLAGE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerSurfaceAdjustingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_crimson"), CRIMSON_VILLAGE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSVillagesConfig.crimsonVillageSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSVillagesConfig.crimsonVillageSpawnrate * 0.75f), 399117375), CRIMSON_VILLAGE.configure(FeatureConfig.DEFAULT));
        LibStructure.registerSurfaceAdjustingStructure(new ResourceLocation(RepurposedStructures.MODID, "village_warped"), WARPED_VILLAGE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(RepurposedStructures.RSAllConfig.RSVillagesConfig.warpedVillageSpawnrate, (int) (RepurposedStructures.RSAllConfig.RSVillagesConfig.warpedVillageSpawnrate * 0.75f), 399117376), WARPED_VILLAGE.configure(FeatureConfig.DEFAULT));

        //Next avaliable seed: 399117377

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
        VillageCrimsonPools.init();
        VillageWarpedPools.init();
    }
}
