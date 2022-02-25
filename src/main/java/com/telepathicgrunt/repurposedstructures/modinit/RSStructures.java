package com.telepathicgrunt.repurposedstructures.modinit;

import com.google.common.collect.ImmutableSet;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.AdvancedDistanceJigsawStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.AdvancedJigsawStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.BuriableStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.CityNetherStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.GenericJigsawStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.GenericNetherJigsawStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.LandBasedEndStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.MansionStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.MineshaftEndStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.MineshaftStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.ShipwreckEndStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.ShipwreckNetherStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.StrongholdEndStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.AdvancedDistanceJigsawStructureCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.AdvancedJigsawStructureCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.BuriableStructureCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.GenericJigsawStructureCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.GenericNetherJigsawStructureCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.MansionCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.MineshaftCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.ShipwreckNetherCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.StartPoolOnlyCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSMineshaftConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionPieces;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public final class RSStructures {
    private RSStructures() {}

    public static StructureFeature<NoneFeatureConfiguration> GENERIC_JIGSAW_STRUCTURE = new GenericJigsawStructure();
    public static StructureFeature<NoneFeatureConfiguration> GENERIC_NETHER_JIGSAW_STRUCTURE = new GenericNetherJigsawStructure();
    public static StructureFeature<NoneFeatureConfiguration> BURIABLE_STRUCTURE = new BuriableStructure();
    public static StructureFeature<NoneFeatureConfiguration> LAND_BASED_END_STRUCTURE = new LandBasedEndStructure();
    public static StructureFeature<NoneFeatureConfiguration> ADVANCED_JIGSAW_STRUCTURE = new AdvancedJigsawStructure();
    public static StructureFeature<NoneFeatureConfiguration> ADVANCED_DISTANCE_JIGSAW_STRUCTURE = new AdvancedDistanceJigsawStructure();
    public static StructureFeature<NoneFeatureConfiguration> MINESHAFT_GENERIC = new MineshaftStructure();
    public static StructureFeature<NoneFeatureConfiguration> MINESHAFT_END = new MineshaftEndStructure();
    public static StructureFeature<NoneFeatureConfiguration> STRONGHOLD_END = new StrongholdEndStructure();
    public static StructureFeature<NoneFeatureConfiguration> SHIPWRECK_NETHER_STRUCTRUE = new ShipwreckNetherStructure();
    public static StructureFeature<NoneFeatureConfiguration> SHIPWRECK_END_STRUCTURE = new ShipwreckEndStructure();
    public static StructureFeature<NoneFeatureConfiguration> CITY_NETHER_STRUCTURE = new CityNetherStructure();
    public static StructureFeature<NoneFeatureConfiguration> MANSION_STRUCTURE = new MansionStructure();


    // RUINS

    public static StructureFeature<NoneFeatureConfiguration> RUINS_NETHER = GenericNetherJigsawStructure.create(new GenericNetherJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "ruins/nether/start_pool")).setStructureSize(1).searchForHighestLand().canSpawnOnLiquid().setLedgeSpotOffset(-1).setLiquidSpotOffset(-2).build());
    public static StructureFeature<NoneFeatureConfiguration> RUINS_LAND_WARM = GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "ruins/land_warm/start_pool")).setStructureSize(2).setTerrainHeightRadius(2).setAllowTerrainHeightRange(5).cannotSpawnInWater().build());
    public static StructureFeature<NoneFeatureConfiguration> RUINS_LAND_HOT = GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "ruins/land_hot/start_pool")).setStructureSize(2).setTerrainHeightRadius(2).setAllowTerrainHeightRange(5).cannotSpawnInWater().build());
    public static StructureFeature<NoneFeatureConfiguration> RUINS_LAND_COLD = GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "ruins/land_cold/start_pool")).setStructureSize(2).setTerrainHeightRadius(2).setAllowTerrainHeightRange(5).cannotSpawnInWater().build());
    public static StructureFeature<NoneFeatureConfiguration> RUINS_LAND_ICY = GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "ruins/land_icy/start_pool")).setStructureSize(2).setTerrainHeightRadius(2).setAllowTerrainHeightRange(5).cannotSpawnInWater().build());

    // CITIES

    public static StructureFeature<NoneFeatureConfiguration> CITY_NETHER = CityNetherStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "cities/nether/start_pool")).setStructureSize(5).setStructureBlacklistRange(4).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet())).setPoolsThatIgnoreBounds(Set.of(new ResourceLocation(RepurposedStructures.MODID, "cities/nether/bridge_end"), new ResourceLocation(RepurposedStructures.MODID, "cities/nether/tower_top"), new ResourceLocation(RepurposedStructures.MODID, "cities/nether/fat_tower_top"))).build());
    public static StructureFeature<NoneFeatureConfiguration> CITY_OVERWORLD = GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "cities/overworld/start_pool")).setStructureSize(5).setStructureBlacklistRange(4).setAvoidStructuresSet(Stream.of(RSStructureTagMap.STRUCTURE_TAGS.MANSION_AVOID_STRUCTURE).collect(Collectors.toSet())).setPoolsThatIgnoreBounds(Set.of(new ResourceLocation(RepurposedStructures.MODID, "cities/overworld/bridge_end"), new ResourceLocation(RepurposedStructures.MODID, "cities/overworld/tower_top"), new ResourceLocation(RepurposedStructures.MODID, "cities/overworld/fat_tower_top"))).cannotSpawnInWater().build());

    // MANSIONS

    public static StructureFeature<NoneFeatureConfiguration> MANSION_BIRCH = MansionStructure.create(new MansionCodeConfig(MansionPieces.MANSIONTYPE.BIRCH));
    public static StructureFeature<NoneFeatureConfiguration> MANSION_JUNGLE = MansionStructure.create(new MansionCodeConfig(MansionPieces.MANSIONTYPE.JUNGLE));
    public static StructureFeature<NoneFeatureConfiguration> MANSION_OAK = MansionStructure.create(new MansionCodeConfig(MansionPieces.MANSIONTYPE.OAK));
    public static StructureFeature<NoneFeatureConfiguration> MANSION_SAVANNA = MansionStructure.create(new MansionCodeConfig(MansionPieces.MANSIONTYPE.SAVANNA));
    public static StructureFeature<NoneFeatureConfiguration> MANSION_TAIGA = MansionStructure.create(new MansionCodeConfig(MansionPieces.MANSIONTYPE.TAIGA));
    public static StructureFeature<NoneFeatureConfiguration> MANSION_DESERT = MansionStructure.create(new MansionCodeConfig(MansionPieces.MANSIONTYPE.DESERT));
    public static StructureFeature<NoneFeatureConfiguration> MANSION_SNOWY = MansionStructure.create(new MansionCodeConfig(MansionPieces.MANSIONTYPE.SNOWY));

    // WITCH HUTS

    public static StructureFeature<NoneFeatureConfiguration> WITCH_HUTS_OAK = GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/oak_start_pool")).setStructureSize(11).build());
    public static StructureFeature<NoneFeatureConfiguration> WITCH_HUTS_TAIGA = GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/taiga_start_pool")).setStructureSize(11).build());
    public static StructureFeature<NoneFeatureConfiguration> WITCH_HUTS_BIRCH = GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/birch_start_pool")).setStructureSize(11).build());
    public static StructureFeature<NoneFeatureConfiguration> WITCH_HUTS_DARK_FOREST = GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/dark_forest_start_pool")).setStructureSize(11).build());
    public static StructureFeature<NoneFeatureConfiguration> WITCH_HUTS_GIANT_TREE_TAIGA = GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "witch_huts/giant_tree_taiga_start_pool")).setStructureSize(11).build());

    // BASTIONS

    public static StructureFeature<NoneFeatureConfiguration> BASTION_UNDERGROUND = GenericJigsawStructure.create(new GenericJigsawStructureCodeConfig.Builder<>(new ResourceLocation(RepurposedStructures.MODID, "bastions/underground/starts")).setStructureSize(10).setFixedYSpawn(-45).doNotUseHeightmap().setBiomeRange(7).setStructureBlacklistRange(5).setAvoidStructuresSet(ImmutableSet.of(RSStructureTagMap.STRUCTURE_TAGS.BASTION_AVOID_STRUCTURE)).build());



    public static void registerStructures() {
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_birch"), MINESHAFT_BIRCH).step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_dark_forest"), MINESHAFT_DARK_FOREST).step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_desert"), MINESHAFT_DESERT).step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_nether"), MINESHAFT_NETHER).step(GenerationStep.Decoration.FLUID_SPRINGS).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_crimson"), MINESHAFT_CRIMSON).step(GenerationStep.Decoration.FLUID_SPRINGS).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_warped"), MINESHAFT_WARPED).step(GenerationStep.Decoration.FLUID_SPRINGS).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_icy"), MINESHAFT_ICY).step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_jungle"), MINESHAFT_JUNGLE).step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_ocean"), MINESHAFT_OCEAN).step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_savanna"), MINESHAFT_SAVANNA).step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_stone"), MINESHAFT_STONE).step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_swamp"), MINESHAFT_SWAMP).step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_taiga"), MINESHAFT_TAIGA).step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_end"), MINESHAFT_END).step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).register();

        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "stronghold_nether"), STRONGHOLD_NETHER).step(GenerationStep.Decoration.TOP_LAYER_MODIFICATION).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "stronghold_end"), STRONGHOLD_END).step(GenerationStep.Decoration.STRONGHOLDS).register();

        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "fortress_jungle"), FORTRESS_JUNGLE).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();

        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "igloo_grassy"), IGLOO_GRASSY).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "igloo_stone"), IGLOO_STONE).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "igloo_mushroom"), IGLOO_MUSHROOM).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();

        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_wasteland"), TEMPLE_NETHER_WASTELAND).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_soul"), TEMPLE_NETHER_SOUL).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_basalt"), TEMPLE_NETHER_BASALT).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_crimson"), TEMPLE_NETHER_CRIMSON).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "temple_nether_warped"), TEMPLE_NETHER_WARPED).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();

        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "outpost_nether_brick"), OUTPOST_NETHER_BRICK).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "outpost_warped"), OUTPOST_WARPED).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "outpost_crimson"), OUTPOST_CRIMSON).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "outpost_birch"), OUTPOST_BIRCH).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "outpost_jungle"), OUTPOST_JUNGLE).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "outpost_giant_tree_taiga"), OUTPOST_GIANT_TREE_TAIGA).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "outpost_desert"), OUTPOST_DESERT).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "outpost_badlands"), OUTPOST_BADLANDS).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "outpost_snowy"), OUTPOST_SNOWY).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "outpost_icy"), OUTPOST_ICY).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "outpost_taiga"), OUTPOST_TAIGA).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "outpost_oak"), OUTPOST_OAK).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "outpost_end"), OUTPOST_END).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();

        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "pyramid_badlands"), PYRAMID_BADLANDS).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "pyramid_nether"), PYRAMID_NETHER).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "pyramid_dark_forest"), PYRAMID_DARK_FOREST).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "pyramid_snowy"), PYRAMID_SNOWY).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "pyramid_end"), PYRAMID_END).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "pyramid_icy"), PYRAMID_ICY).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "pyramid_jungle"), PYRAMID_JUNGLE).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "pyramid_mushroom"), PYRAMID_MUSHROOM).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "pyramid_ocean"), PYRAMID_OCEAN).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "pyramid_giant_tree_taiga"), PYRAMID_GIANT_TREE_TAIGA).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "pyramid_flower_forest"), PYRAMID_FLOWER_FOREST).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();

        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_end"), SHIPWRECK_END).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_nether_bricks"), SHIPWRECK_NETHER_BRICKS).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_crimson"), SHIPWRECK_CRIMSON).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "shipwreck_warped"), SHIPWRECK_WARPED).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();

        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "village_badlands"), VILLAGE_BADLANDS).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "village_birch"), VILLAGE_BIRCH).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "village_dark_oak"), VILLAGE_DARK_FOREST).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "village_jungle"), VILLAGE_JUNGLE).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "village_swamp"), VILLAGE_SWAMP).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "village_mountains"), VILLAGE_MOUNTAINS).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "village_giant_taiga"), VILLAGE_GIANT_TAIGA).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "village_crimson"), VILLAGE_CRIMSON).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "village_warped"), VILLAGE_WARPED).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "village_oak"), VILLAGE_OAK).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "village_mushroom"), VILLAGE_MUSHROOM).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        // regexpos2

        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "ruined_portal_end"), RUINED_PORTAL_END).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "ruins_nether"), RUINS_NETHER).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "ruins_land_warm"), RUINS_LAND_WARM).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "ruins_land_hot"), RUINS_LAND_HOT).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "ruins_land_cold"), RUINS_LAND_COLD).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "ruins_land_icy"), RUINS_LAND_ICY).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();

        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "city_nether"), CITY_NETHER).step(GenerationStep.Decoration.SURFACE_STRUCTURES).adjustsSurface().register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "city_overworld"), CITY_OVERWORLD).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();

        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mansion_birch"), MANSION_BIRCH).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mansion_jungle"), MANSION_JUNGLE).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mansion_oak"), MANSION_OAK).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mansion_savanna"), MANSION_SAVANNA).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mansion_taiga"), MANSION_TAIGA).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mansion_desert"), MANSION_DESERT).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "mansion_snowy"), MANSION_SNOWY).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();

        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "witch_hut_oak"), WITCH_HUTS_OAK).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "witch_hut_taiga"), WITCH_HUTS_TAIGA).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "witch_hut_birch"), WITCH_HUTS_BIRCH).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "witch_hut_dark_forest"), WITCH_HUTS_DARK_FOREST).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();
        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "witch_hut_giant_tree_taiga"), WITCH_HUTS_GIANT_TREE_TAIGA).step(GenerationStep.Decoration.SURFACE_STRUCTURES).register();

        FabricStructureBuilder.create(new ResourceLocation(RepurposedStructures.MODID, "bastion_underground"), BASTION_UNDERGROUND).step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES).register();

        //registers the structure pieces.
        RSStructurePieces.registerStructurePieces();
    }
}
