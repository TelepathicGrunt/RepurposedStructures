package com.telepathicgrunt.repurposedstructures;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

import java.util.Optional;

public class RSAddFeatures {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MINESHAFTS //

    public static void addMineshafts(Biome biome, ResourceLocation biomeID) {

        if (RepurposedStructures.RSMineshaftsConfig.birchMineshaftSpawnrate.get() != 0 && biomeID.getPath().contains("birch") &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            if (biome.getGenerationSettings().hasStructureFeature(Structure.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.BIRCH_MINESHAFT);
            }
        }
        else if (RepurposedStructures.RSMineshaftsConfig.jungleMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.JUNGLE &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            if (biome.getGenerationSettings().hasStructureFeature(Structure.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.JUNGLE_MINESHAFT);
            }
        }
        else if (RepurposedStructures.RSMineshaftsConfig.desertMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.DESERT &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            if (biome.getGenerationSettings().hasStructureFeature(Structure.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.DESERT_MINESHAFT);
            }
        }
        else if (RepurposedStructures.RSMineshaftsConfig.stoneMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.EXTREME_HILLS &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            if (biome.getGenerationSettings().hasStructureFeature(Structure.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.STONE_MINESHAFT);
            }
        }
        else if (RepurposedStructures.RSMineshaftsConfig.savannaMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.SAVANNA &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            if (biome.getGenerationSettings().hasStructureFeature(Structure.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.SAVANNA_MINESHAFT);
            }
        }
        else if (RepurposedStructures.RSMineshaftsConfig.icyMineshaftSpawnrate.get() != 0 &&
                (biome.getCategory() == Category.ICY || biomeID.getPath().contains("snowy")) &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            if (biome.getGenerationSettings().hasStructureFeature(Structure.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.ICY_MINESHAFT);
            }
        }
        else if (RepurposedStructures.RSMineshaftsConfig.oceanMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.OCEAN &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            if (biome.getGenerationSettings().hasStructureFeature(Structure.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.OCEAN_MINESHAFT);
            }
        }
        else if (RepurposedStructures.RSMineshaftsConfig.taigaMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.TAIGA &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            if (biome.getGenerationSettings().hasStructureFeature(Structure.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.TAIGA_MINESHAFT);
            }
        }
        else if (RepurposedStructures.RSMineshaftsConfig.swampAndDarkForestMineshaftSpawnrate.get() != 0 && (biome.getCategory() == Category.SWAMP || biomeID.getPath().contains("dark_forest") || biomeID.getPath().contains("dark_oak")) &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            if (biome.getGenerationSettings().hasStructureFeature(Structure.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.SWAMP_OR_DARK_FOREST_MINESHAFT);
            }
        }
        else if (RepurposedStructures.RSMineshaftsConfig.endMineshaftSpawnrate.get() != 0 &&
                biome.getCategory() == Category.THEEND && !biomeID.equals(new ResourceLocation("minecraft:the_end")) &&
                (RepurposedStructures.RSMineshaftsConfig.barrensIslandsEndMineshafts.get() ||
                        (!biomeID.equals(new ResourceLocation("minecraft:end_barrens")) && !biomeID.equals(new ResourceLocation("minecraft:small_end_islands")))) &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.END_MINESHAFT);
        }
        else if (RepurposedStructures.RSMineshaftsConfig.netherMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.NETHER &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.NETHER_MINESHAFT);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DUNGEONS //

    public static void addDungeons(Biome biome, ResourceLocation biomeID) {

        if (RepurposedStructures.RSDungeonsConfig.jungleDungeonSpawnrate.get() != 0 &&
                biome.getCategory() == Category.JUNGLE && dungeonAllowedByNamespaceAndConfigUA(biomeID)) {

            replaceOrAddDungeon(true, biome, RSConfiguredFeatures.JUNGLE_DUNGEONS);
        }
        else if (RepurposedStructures.RSDungeonsConfig.badlandsDungeonSpawnrate.get() != 0 &&
                biome.getCategory() == Category.MESA && dungeonAllowedByNamespaceAndConfigUA(biomeID)) {

            replaceOrAddDungeon(true, biome, RSConfiguredFeatures.BADLANDS_DUNGEONS);
        }
        else if (RepurposedStructures.RSDungeonsConfig.darkForestDungeonSpawnrate.get() != 0 &&
                biomeID.getPath().contains("dark_forest") && dungeonAllowedByNamespaceAndConfigUA(biomeID)) {

            replaceOrAddDungeon(true, biome, RSConfiguredFeatures.DARK_FOREST_DUNGEONS);
        }
        else if (RepurposedStructures.RSDungeonsConfig.desertDungeonSpawnrate.get() != 0 &&
                biome.getCategory() == Category.DESERT && dungeonAllowedByNamespaceAndConfigUA(biomeID)) {

            replaceOrAddDungeon(true, biome, RSConfiguredFeatures.DESERT_DUNGEONS);
        }
        else if (RepurposedStructures.RSDungeonsConfig.mushroomDungeonSpawnrate.get() != 0 &&
                biome.getCategory() == Category.MUSHROOM && dungeonAllowedByNamespaceAndConfigUA(biomeID)) {

            replaceOrAddDungeon(true, biome, RSConfiguredFeatures.MUSHROOM_DUNGEONS);
        }
        else if (RepurposedStructures.RSDungeonsConfig.swampDungeonSpawnrate.get() != 0 &&
                biome.getCategory() == Category.SWAMP && dungeonAllowedByNamespaceAndConfigUA(biomeID)) {

            replaceOrAddDungeon(true, biome, RSConfiguredFeatures.SWAMP_DUNGEONS);
        }
        else if (RepurposedStructures.RSDungeonsConfig.snowDungeonSpawnrate.get() != 0 &&
                biome.getCategory() == Category.ICY && dungeonAllowedByNamespaceAndConfigUA(biomeID)) {

            replaceOrAddDungeon(true, biome, RSConfiguredFeatures.SNOW_DUNGEONS);
        }
        else if (RepurposedStructures.RSDungeonsConfig.netherDungeonSpawnrate.get() != 0 &&
                biome.getCategory() == Category.NETHER && dungeonAllowedByNamespaceAndConfigUA(biomeID)) {

            replaceOrAddDungeon(false, biome, RSConfiguredFeatures.NETHER_DUNGEONS);
        }
        else if (RepurposedStructures.RSDungeonsConfig.endDungeonSpawnrate.get() != 0 &&
                (biome.getCategory() == Category.THEEND &&
                        !biomeID.equals(new ResourceLocation("minecraft:the_end")) &&
                        !biomeID.equals(new ResourceLocation("minecraft:small_end_islands"))) &&
                dungeonAllowedByNamespaceAndConfigUA(biomeID)) {

            replaceOrAddDungeon(false, biome, RSConfiguredFeatures.END_DUNGEONS);
        }
        else if (RepurposedStructures.RSDungeonsConfig.oceanDungeonSpawnrate.get() != 0 &&
                biome.getCategory() == Category.OCEAN && dungeonAllowedByNamespaceAndConfig(biomeID)) {

            replaceOrAddDungeon(false, biome, RSConfiguredFeatures.OCEAN_DUNGEONS);
        }
    }


    /**
     * Adds RS's dungeon to the biome along with option to remove vanilla dungeon as well.
     */
    private static void replaceOrAddDungeon(boolean replacing, Biome biome, ConfiguredFeature<?, ?> rsDungeon) {

        //remove vanilla dungeon
        if (replacing) {
            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.UNDERGROUND_STRUCTURES.ordinal())
                    .removeIf(supplier ->
                            supplier.get().config instanceof DecoratedFeatureConfig &&
                                    ((DecoratedFeatureConfig)supplier.get().config).feature.get().config instanceof DecoratedFeatureConfig &&
                                    ((DecoratedFeatureConfig)((DecoratedFeatureConfig)supplier.get().config).feature.get().config).feature.get().config instanceof DecoratedFeatureConfig &&
                                    ((DecoratedFeatureConfig)((DecoratedFeatureConfig)((DecoratedFeatureConfig)supplier.get().config).feature.get().config).feature.get().config).feature.get().feature == Feature.MONSTER_ROOM);
        }

        //add given dungeon
        biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.UNDERGROUND_STRUCTURES.ordinal()).add(() -> rsDungeon);
    }


    /**
     * Will not return true for Ultra Amplified Dimension's biomes as that mod already has the dungeon type.
     * <p>
     * And will check if the dungeon is allowed in modded biomes based on config but will always return true for vanilla biomes.
     */
    private static boolean dungeonAllowedByNamespaceAndConfigUA(ResourceLocation biomeID) {
        if (!biomeID.getNamespace().equals("ultra_amplified_dimension")) {
            return dungeonAllowedByNamespaceAndConfig(biomeID);
        }
        return false;
    }


    /**
     * Will check if the dungeon is allowed in modded biomes based on config but will always return true for vanilla biomes.
     */
    private static boolean dungeonAllowedByNamespaceAndConfig(ResourceLocation biomeID) {
        return biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSDungeonsConfig.addDungeonsToModdedBiomes.get();
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WELLS //

    public static void addWells(Biome biome, ResourceLocation biomeID) {

        if (RepurposedStructures.RSWellsConfig.badlandsWellSpawnrate.get() != 10000 &&
                biome.getCategory() == Category.MESA && wellAllowedByNamespaceAndConfig(biomeID)) {

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSConfiguredFeatures.BADLANDS_WELL);
        }
        else if (RepurposedStructures.RSWellsConfig.netherWellSpawnrate.get() != 10000 &&
                biome.getCategory() == Category.NETHER && wellAllowedByNamespaceAndConfig(biomeID)) {

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSConfiguredFeatures.NETHER_WELL);
        }
        else if (RepurposedStructures.RSWellsConfig.snowWellSpawnrate.get() != 10000 &&
                (biome.getCategory() == Category.ICY || biomeID.getPath().contains("snow")) && wellAllowedByNamespaceAndConfig(biomeID)) {

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSConfiguredFeatures.SNOW_WELL);
        }
        else if (RepurposedStructures.RSWellsConfig.mossyStoneWellSpawnrate.get() != 10000 &&
                (biome.getCategory() == Category.SWAMP || biome.getCategory() == Category.JUNGLE ||
                        biomeID.getPath().contains("dark_forest") || biomeID.getPath().contains("dark_oak")) &&
                wellAllowedByNamespaceAndConfig(biomeID)) {

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSConfiguredFeatures.MOSSY_STONE_WELL);
        }
        else if (RepurposedStructures.RSWellsConfig.forestWellSpawnrate.get() != 10000 &&
                (biome.getCategory() == Category.FOREST && !(biomeID.getPath().contains("dark_forest") || biomeID.getPath().contains("dark_oak"))) &&
                wellAllowedByNamespaceAndConfig(biomeID)) {

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSConfiguredFeatures.FOREST_WELL);
        }
    }


    private static boolean wellAllowedByNamespaceAndConfig(ResourceLocation biomeID) {
        return biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSWellsConfig.addWellsToModdedBiomes.get();
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SWAMP TREE FEATURES //

    private static final BaseTreeFeatureConfig TREE_FEATURE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
            new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()),
            new BlobFoliagePlacer(FeatureSpread.of(3), FeatureSpread.of(0), 3),
            new StraightTrunkPlacer(5, 3, 0),
            new TwoLayerFeature(1, 0, 1))).build();

    public static void addSwampTreeFeatures(Biome biome, ResourceLocation biomeID) {

        // Exists in vanilla Swamp and can be in modded swamp biomes
        if (RepurposedStructures.RSMainConfig.hornedSwampTree.get() &&
                (biomeID.equals(new ResourceLocation("minecraft:swamp")) ||
                (RepurposedStructures.RSMainConfig.addLargeSwampTreeModdedBiomes.get() &&
                        biome.getCategory() == Category.SWAMP &&
                        !biomeID.getNamespace().equals("ultra_amplified_dimension") &&
                        !biomeID.getNamespace().equals("minecraft")))){

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal())
                    .add(() -> RSFeatures.HORNED_SWAMP_TREE.configure(TREE_FEATURE_CONFIG)
                            .decorate(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.7F, 1))
                            .decorate(Features.Placements.SQUARE_HEIGHTMAP)));
        }

        // Only exists in vanilla Swamp Hills biomes
        else if (RepurposedStructures.RSMainConfig.hornedSwampTree.get() &&
                (biomeID.equals(new ResourceLocation("minecraft:swamp_hills")))) {

            // replace the swamp tree with our own
            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal())
                    .removeIf(configuredFeature -> configuredFeature.get().config instanceof DecoratedFeatureConfig &&
                            serializeAndCompareFeature(configuredFeature.get(), Features.SWAMP_TREE));

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal())
                    .add(() -> RSFeatures.HORNED_SWAMP_TREE.configure(TREE_FEATURE_CONFIG)
                            .decorate(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(2, 0.8F, 1))
                            .decorate(Features.Placements.SQUARE_HEIGHTMAP)));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BOULDER FEATURES //

    public static void addBoulderFeatures(Biome biome, ResourceLocation biomeID) {

        if (RepurposedStructures.RSMainConfig.boulderGiant.get() && !biomeID.getNamespace().equals("ultra_amplified_dimension") &&
                ((biomeID.equals(new ResourceLocation("minecraft:giant_spruce_taiga_hills")) || biomeID.equals(new ResourceLocation("minecraft:giant_tree_taiga_hills"))) ||
                        (RepurposedStructures.RSMainConfig.addGiantBouldersModdedBiomes.get() && !biomeID.getNamespace().equals("minecraft") &&
                                ((biomeID.getPath().contains("giant") && biomeID.getPath().contains("taiga")) || biomeID.getPath().contains("redwood"))))) {

            // replace the boulders with our own
            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.LOCAL_MODIFICATIONS.ordinal())
                    .removeIf(configuredFeature -> configuredFeature.get().config instanceof DecoratedFeatureConfig &&
                            serializeAndCompareFeature(configuredFeature.get(), Features.FOREST_ROCK));

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.LOCAL_MODIFICATIONS.ordinal())
                    .add(() -> RSConfiguredFeatures.BOULDER_GIANT);
        }
        else if (RepurposedStructures.RSMainConfig.boulderTiny.get() && !biomeID.getNamespace().equals("ultra_amplified_dimension") &&
                ((biomeID.equals(new ResourceLocation("minecraft:snowy_taiga_mountains")) || biomeID.equals(new ResourceLocation("minecraft:taiga_mountains"))) ||
                        (RepurposedStructures.RSMainConfig.addTinyBouldersModdedBiomes.get() && !biomeID.getNamespace().equals("minecraft") &&
                                biomeID.getPath().contains("taiga") && (biomeID.getPath().contains("mountain") || biomeID.getPath().contains("hill"))))) {

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.LOCAL_MODIFICATIONS.ordinal())
                    .add(() -> RSConfiguredFeatures.BOULDER_TINY);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // STRONGHOLDS //

    public static void addStrongholds(Biome biome, ResourceLocation biomeID) {

        if (RepurposedStructures.RSStrongholdsConfig.allowStonebrickStronghold.get() &&
                RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdSpawnrate.get() != 1001 &&
                biome.getCategory() != Category.NETHER &&
                (biome.getGenerationSettings().hasStructureFeature(Structure.STRONGHOLD)||
                        (!biomeID.getNamespace().equals("minecraft") && RepurposedStructures.RSStrongholdsConfig.addStonebrickStrongholdToModdedBiomes.get()))) {

            //replace vanilla stronghold with ours if vanilla's is present
            biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.STRONGHOLD);
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.STONEBRICK_STRONGHOLD);

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.UNDERGROUND_DECORATION.ordinal())
                    .add(() -> RSConfiguredFeatures.STONEBRICK_STRONGHOLD_CHAINS);
        }

        else if (RepurposedStructures.RSStrongholdsConfig.netherStrongholdSpawnrate.get() != 1001 && biome.getCategory() == Category.NETHER &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSStrongholdsConfig.addNetherStrongholdToModdedBiomes.get())) {

            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.NETHER_STRONGHOLD);

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.UNDERGROUND_DECORATION.ordinal())
                    .add(() -> RSConfiguredFeatures.NETHER_STRONGHOLD_CHAINS);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OUTPOSTS //

    public static void addOutposts(Biome biome, ResourceLocation biomeID) {

        //Nether based Outposts
        if(biome.getCategory() == Category.NETHER)
        {
            if (RepurposedStructures.RSOutpostsConfig.crimsonOutpostSpawnrate.get() != 1001 &&
                    biomeID.getPath().contains("crimson") &&
                    (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSOutpostsConfig.addCrimsonOutpostToModdedBiomes.get())) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.CRIMSON_OUTPOST);
            }
            else if (RepurposedStructures.RSOutpostsConfig.warpedOutpostSpawnrate.get() != 1001 &&
                    biomeID.getPath().contains("warped") &&
                    (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSOutpostsConfig.addWarpedOutpostToModdedBiomes.get())) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.WARPED_OUTPOST);
            }
            else if (RepurposedStructures.RSOutpostsConfig.netherBrickOutpostSpawnrate.get() != 1001 &&
                    (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSOutpostsConfig.addNetherBrickOutpostToModdedBiomes.get())) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.NETHER_BRICK_OUTPOST);
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OUTPOSTS //

    public static void addShipwrecks(Biome biome, ResourceLocation biomeID) {

        if (RepurposedStructures.RSShipwrecksConfig.endShipwreckSpawnrate.get() != 1001 &&
                (biomeID.equals(new ResourceLocation("minecraft:end_highlands")) ||
                (!biomeID.getNamespace().equals("minecraft") && biome.getCategory() == Category.THEEND &&
                RepurposedStructures.RSShipwrecksConfig.addEndShipwreckToModdedBiomes.get()))) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.END_SHIPWRECK);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // JUNGLE FORTRESS //

    public static void addJungleFortress(Biome biome, ResourceLocation biomeID) {
        if(RepurposedStructures.RSMainConfig.jungleFortressSpawnrate.get() != 1001)
        {
            if ( biome.getCategory() == Category.JUNGLE &&
                    (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSMainConfig.addJungleFortressToModdedBiomes.get())) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.JUNGLE_FORTRESS);
            }
            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal())
                    .add(() -> RSConfiguredFeatures.JUNGLE_FORTRESS_VINES);

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.UNDERGROUND_ORES.ordinal())
                    .add(() -> RSConfiguredFeatures.FORTRESS_BREAKAGE);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TEMPLES //

    public static void addTemples(Biome biome, ResourceLocation biomeID) {

        if (RepurposedStructures.RSTemplesConfig.netherBasaltTempleSpawnrate.get() != 1001 &&
                biome.getCategory() == Category.NETHER && (biomeID.getPath().contains("basalt") || biomeID.getPath().contains("blackstone")) &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSTemplesConfig.addNetherBasaltTempleToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.NETHER_BASALT_TEMPLE);
        }
        else if (RepurposedStructures.RSTemplesConfig.netherCrimsonTempleSpawnrate.get() != 1001 &&
                biome.getCategory() == Category.NETHER && biomeID.getPath().contains("crimson") &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSTemplesConfig.addNetherCrimsonTempleToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.NETHER_CRIMSON_TEMPLE);
        }
        else if (RepurposedStructures.RSTemplesConfig.netherWarpedTempleSpawnrate.get() != 1001 &&
                biome.getCategory() == Category.NETHER && biomeID.getPath().contains("warped") &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSTemplesConfig.addNetherWarpedTempleToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.NETHER_WARPED_TEMPLE);
        }
        else if (RepurposedStructures.RSTemplesConfig.netherSoulTempleSpawnrate.get() != 1001 &&
                biome.getCategory() == Category.NETHER && biomeID.getPath().contains("soul") &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSTemplesConfig.addNetherSoulTempleToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.NETHER_SOUL_TEMPLE);
        }
        else if (RepurposedStructures.RSTemplesConfig.netherWastelandTempleSpawnrate.get() != 1001 &&
                biome.getCategory() == Category.NETHER &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSTemplesConfig.addNetherWastelandTempleToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.NETHER_WASTELAND_TEMPLE);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Pyramids //

    public static void addPyramids(Biome biome, ResourceLocation biomeID) {

        if (RepurposedStructures.RSTemplesConfig.netherPyramidSpawnrate.get() != 1001 && biome.getCategory() == Category.NETHER &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSTemplesConfig.addNetherPyramidToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.NETHER_PYRAMID);
        }

        if (RepurposedStructures.RSTemplesConfig.badlandsPyramidSpawnrate.get() != 1001 && biome.getCategory() == Category.MESA &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSTemplesConfig.addBadlandsPyramidToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.BADLANDS_TEMPLE);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // IGLOOS //

    public static void addIgloos(Biome biome, ResourceLocation biomeID) {
        if (RepurposedStructures.RSMainConfig.grassyIglooSpawnrate.get() != 1001) {
            if ((biome.getCategory() == Category.FOREST || biome.getCategory() == Category.PLAINS) &&
                    (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSMainConfig.addGrassyIglooToModdedBiomes.get())) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.GRASSY_IGLOO);
            }
        }

        if (RepurposedStructures.RSMainConfig.stoneIglooSpawnrate.get() != 1001) {
            if ((biome.getCategory() == Category.TAIGA && biomeID.getPath().contains("giant")) &&
                    (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSMainConfig.addStoneIglooToModdedBiomes.get())) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.STONE_IGLOO);
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // VILLAGES //

    public static void addVillages(Biome biome, ResourceLocation biomeID) {
        if ((biome.getCategory() == Category.MESA && !biomeID.getPath().contains("plateau")) &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            if (RepurposedStructures.RSVillagesConfig.badlandsVillageSpawnrate.get() != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.BADLANDS_VILLAGE);
            }
        }

        else if (biomeID.getPath().contains("birch") && (biomeID.getNamespace().equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            if (RepurposedStructures.RSVillagesConfig.birchVillageSpawnrate.get() != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.BIRCH_VILLAGE);
            }
        }

        else if (biomeID.getPath().contains("dark_forest") && (biomeID.getNamespace().equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            if (RepurposedStructures.RSVillagesConfig.darkForestVillageSpawnrate.get() != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.DARK_FOREST_VILLAGE);
            }
        }

        else if (biome.getCategory() == Category.JUNGLE && (biomeID.getNamespace().equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            if (RepurposedStructures.RSVillagesConfig.jungleVillageSpawnrate.get() != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.JUNGLE_VILLAGE);
                biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal())
                        .add(() -> RSConfiguredFeatures.JUNGLE_VILLAGE_VINES);
            }
        }

        else if (biome.getCategory() == Category.SWAMP && (biomeID.getNamespace().equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            if (RepurposedStructures.RSVillagesConfig.swampVillageSpawnrate.get() != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.SWAMP_VILLAGE);
                biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal())
                        .add(() -> RSConfiguredFeatures.SWAMP_VILLAGE_VINES);
            }
        }

        else if (biome.getCategory() == Category.EXTREME_HILLS && (biomeID.getNamespace().equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            if (RepurposedStructures.RSVillagesConfig.mountainsVillageSpawnrate.get() != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.MOUNTAINS_VILLAGE);
            }
        }

        else if ((biomeID.equals(new ResourceLocation("minecraft:giant_spruce_taiga")) || biomeID.equals(new ResourceLocation("minecraft:giant_tree_taiga"))) ||
                (!biomeID.getNamespace().equals("minecraft") &&
                  RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get() &&
                ((biomeID.getPath().contains("giant") && biomeID.getPath().contains("taiga")) || biomeID.getPath().contains("redwood")))) {
            if (RepurposedStructures.RSVillagesConfig.giantTaigaVillageSpawnrate.get() != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.GIANT_TAIGA_VILLAGE);
            }
        }
        else if (RepurposedStructures.RSVillagesConfig.crimsonVillageSpawnrate.get() != 1001 &&
                biome.getCategory() == Category.NETHER && biomeID.getPath().contains("crimson") &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.CRIMSON_VILLAGE);
        }
        else if (RepurposedStructures.RSVillagesConfig.warpedVillageSpawnrate.get() != 1001 &&
                biome.getCategory() == Category.NETHER && biomeID.getPath().contains("warped") &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.WARPED_VILLAGE);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GENERAL UTILITIES //

    /**
     * Will serialize (if possible) both features and check if they are the same feature.
     * If cannot serialize, compare the feature itself to see if it is the same.
     */
    private static boolean serializeAndCompareFeature(ConfiguredFeature<?, ?> configuredFeature1, ConfiguredFeature<?, ?> configuredFeature2) {

        Optional<JsonElement> configuredFeatureJSON1 = ConfiguredFeature.CODEC.encode(() -> configuredFeature1, JsonOps.INSTANCE, JsonOps.INSTANCE.empty()).get().left();
        Optional<JsonElement> configuredFeatureJSON2 = ConfiguredFeature.CODEC.encode(() -> configuredFeature2, JsonOps.INSTANCE, JsonOps.INSTANCE.empty()).get().left();

        // One of the configuredfeatures cannot be serialized which
        // shouldn't be possible but still good to do a sanity check.
        if(!configuredFeatureJSON1.isPresent() || !configuredFeatureJSON2.isPresent())
        {
            if ((configuredFeature1.config instanceof DecoratedFeatureConfig && configuredFeature2.config instanceof DecoratedFeatureConfig) &&
                    ((DecoratedFeatureConfig) configuredFeature1.config).feature.get().feature == ((DecoratedFeatureConfig) configuredFeature2.config).feature.get().feature) {
                return true;
            }
        }

        // Compare the JSON to see if it's the same ConfiguredFeature in the end.
        return configuredFeatureJSON1.equals(configuredFeatureJSON2);
    }

}
