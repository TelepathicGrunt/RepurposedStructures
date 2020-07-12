package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.mixin.BiomeStructureAccessor;
import com.telepathicgrunt.repurposedstructures.world.features.RSFeatures;
import com.telepathicgrunt.repurposedstructures.world.placements.RSPlacements;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;

public class RSAddFeatures {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MINESHAFTS //

    public static void addMineshafts(Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.birchMineshaftSpawnrate != 0 && biomePath.contains("birch") &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.MINESHAFT);
                biome.addStructureFeature(RSFeatures.BIRCH_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.jungleMineshaftSpawnrate != 0 && biome.getCategory() == Category.JUNGLE &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.MINESHAFT);
                biome.addStructureFeature(RSFeatures.JUNGLE_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.desertMineshaftSpawnrate != 0 && biome.getCategory() == Category.DESERT &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.MINESHAFT);
                biome.addStructureFeature(RSFeatures.DESERT_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.stoneMineshaftSpawnrate != 0 && biome.getCategory() == Category.EXTREME_HILLS &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.MINESHAFT);
                biome.addStructureFeature(RSFeatures.STONE_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.savannaMineshaftSpawnrate != 0 && biome.getCategory() == Category.SAVANNA &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.MINESHAFT);
                biome.addStructureFeature(RSFeatures.SAVANNA_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.icyMineshaftSpawnrate != 0 && (biome.getCategory() == Category.ICY || biomePath.contains("snowy")) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.MINESHAFT);
                biome.addStructureFeature(RSFeatures.ICY_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.oceanMineshaftSpawnrate != 0 && biome.getCategory() == Category.OCEAN &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.MINESHAFT);
                biome.addStructureFeature(RSFeatures.OCEAN_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.taigaMineshaftSpawnrate != 0 && biome.getCategory() == Category.TAIGA &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.MINESHAFT);
                biome.addStructureFeature(RSFeatures.TAIGA_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.swampAndDarkForestMineshaftSpawnrate != 0 && (biome.getCategory() == Category.SWAMP || biomePath.contains("dark_forest") || biomePath.contains("dark_oak")) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.MINESHAFT);
                biome.addStructureFeature(RSFeatures.SWAMP_OR_DARK_FOREST_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.endMineshaftSpawnrate != 0 &&
                (biome.getCategory() == Category.THEEND && biome != Biomes.THE_END && biome != Biomes.END_BARRENS && biome != Biomes.SMALL_END_ISLANDS) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            biome.addStructureFeature(RSFeatures.END_MINESHAFT.configure(FeatureConfig.DEFAULT));
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.netherMineshaftSpawnrate != 0 && biome.getCategory() == Category.NETHER &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            biome.addStructureFeature(RSFeatures.NETHER_MINESHAFT.configure(FeatureConfig.DEFAULT));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DUNGEONS //


    public static void addDungeons(Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.jungleDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.JUNGLE && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.JUNGLE_DUNGEONS,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.jungleDungeonSpawnrate,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.jungleDungeonMinHeight,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.jungleDungeonMaxHeight);
        }
        else if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.badlandsDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.MESA && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.BADLANDS_DUNGEONS,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.badlandsDungeonSpawnrate,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.badlandsDungeonMinHeight,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.badlandsDungeonMaxHeight);
        }
        else if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.darkForestDungeonSpawnrate != 0 &&
                biomePath.contains("dark_forest") && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.DARK_FOREST_DUNGEONS,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.darkForestDungeonSpawnrate,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.darkForestDungeonMinHeight,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.darkForestDungeonMaxHeight);
        }
        else if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.desertDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.DESERT && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.DESERT_DUNGEONS,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.desertDungeonSpawnrate,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.desertDungeonMinHeight,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.desertDungeonMaxHeight);
        }
        else if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.mushroomDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.MUSHROOM && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.MUSHROOM_DUNGEONS,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.mushroomDungeonSpawnrate,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight);
        }
        else if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.swampDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.SWAMP && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.SWAMP_DUNGEONS,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.swampDungeonSpawnrate,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.swampDungeonMinHeight,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.swampDungeonMaxHeight);
        }
        else if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.snowDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.ICY && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.SNOW_DUNGEONS,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.snowDungeonSpawnrate,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.snowDungeonMinHeight,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.snowDungeonMaxHeight);
        }
        else if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.netherDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.NETHER && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            replaceOrAddDungeon(
                    false,
                    biome,
                    RSFeatures.NETHER_DUNGEONS,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.netherDungeonSpawnrate,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.netherDungeonMinHeight,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.netherDungeonMaxHeight);
        }
        else if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.endDungeonSpawnrate != 0 &&
                (biome.getCategory() == Category.THEEND && biome != Biomes.THE_END && biome != Biomes.SMALL_END_ISLANDS) &&
                dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            replaceOrAddDungeon(
                    false,
                    biome,
                    RSFeatures.END_DUNGEONS,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.endDungeonSpawnrate,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.endDungeonMinHeight,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.endDungeonMaxHeight);
        }
        else if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.oceanDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.OCEAN && dungeonAllowedByNamespaceAndConfig(biomeNamespace)) {

            replaceOrAddDungeon(
                    false,
                    biome,
                    RSFeatures.OCEAN_DUNGEONS,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.oceanDungeonSpawnrate,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight);
        }
    }


    /**
     * Adds RS's dungeon to the biome along with option to remove vanilla dungeon as well.
     */
    private static void replaceOrAddDungeon(boolean replacing, Biome biome, Feature<DefaultFeatureConfig> rsDungeon, int spawnrate, int minHeight, int maxheight) {

        //remove vanilla dungeon
        if (replacing) {
            biome.getFeaturesForStep(GenerationStep.Feature.UNDERGROUND_STRUCTURES)
                    .removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig &&
                            ((DecoratedFeatureConfig) configuredFeature.config).feature.feature == Feature.MONSTER_ROOM);
        }

        //add given dungeon
        biome.addFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, rsDungeon.configure(FeatureConfig.DEFAULT)
                .createDecoratedFeature(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(spawnrate, minHeight, 0, maxheight))));
    }


    /**
     * Will not return true for Ultra Amplified Dimension's biomes as that mod already has the dungeon type.
     * <p>
     * And will check if the dungeon is allowed in modded biomes based on config but will always return true for vanilla biomes.
     */
    private static boolean dungeonAllowedByNamespaceAndConfigUA(String biomeNamespace) {
        if (!biomeNamespace.equals("ultra_amplified_dimension")) {
            return dungeonAllowedByNamespaceAndConfig(biomeNamespace);
        }
        return false;
    }


    /**
     * Will check if the dungeon is allowed in modded biomes based on config but will always return true for vanilla biomes.
     */
    private static boolean dungeonAllowedByNamespaceAndConfig(String biomeNamespace) {
        if (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSDungeonsConfig.addDungeonsToModdedBiomes) {
            return true;
        }

        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WELLS //


    public static void addWells(Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.badlandsWellSpawnrate != 10000 &&
                biome.getCategory() == Category.MESA && wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                    RSFeatures.BADLANDS_WELL.configure(FeatureConfig.DEFAULT)
                            .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP
                                    .configure(new ChanceDecoratorConfig(RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.badlandsWellSpawnrate))));
        }
        else if (RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.netherWellSpawnrate != 10000 &&
                biome.getCategory() == Category.NETHER && wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                    RSFeatures.NETHER_WELL.configure(FeatureConfig.DEFAULT)
                            .createDecoratedFeature(Decorator.CHANCE_RANGE
                                    .configure(new ChanceRangeDecoratorConfig(1F / RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.netherWellSpawnrate, 30, 0, 98))));
        }
        else if (RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.snowWellSpawnrate != 10000 &&
                (biome.getCategory() == Category.ICY || biomePath.contains("snow")) && wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSFeatures.SNOW_WELL.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.snowWellSpawnrate))));
        }
        else if (RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.mossyStoneWellSpawnrate != 10000 &&
                (biome.getCategory() == Category.SWAMP || biome.getCategory() == Category.JUNGLE ||
                        biomePath.contains("dark_forest") || biomePath.contains("dark_oak")) &&
                wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                    RSFeatures.MOSSY_STONE_WELL.configure(FeatureConfig.DEFAULT)
                            .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP
                                    .configure(new ChanceDecoratorConfig(RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.mossyStoneWellSpawnrate))));
        }
        else if (RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.forestWellSpawnrate != 10000 &&
                (biome.getCategory() == Category.FOREST && !(biomePath.contains("dark_forest") || biomePath.contains("dark_oak"))) &&
                wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                    RSFeatures.FOREST_WELL.configure(FeatureConfig.DEFAULT)
                            .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP
                                    .configure(new ChanceDecoratorConfig(RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.forestWellSpawnrate))));
        }
    }


    private static boolean wellAllowedByNamespaceAndConfig(String biomeNamespace) {
        if (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSWellsConfig.addWellsToModdedBiomes) {
            return true;
        }

        return false;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MISC FEATURES //

    private static final ConfiguredFeature<?, ?> VANILLA_SWAMP_TREE = Feature.TREE.configure(DefaultBiomeFeatures.SWAMP_TREE_CONFIG).createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(2, 0.1F, 1)));
    private static final ConfiguredFeature<?, ?> VANILLA_BOULDER = Feature.FOREST_ROCK.configure(new ForestRockFeatureConfig(Blocks.MOSSY_COBBLESTONE.getDefaultState(), 0)).createDecoratedFeature(Decorator.FOREST_ROCK.configure(new CountDecoratorConfig(3)));


    public static void addMiscFeatures(Biome biome, String biomeNamespace, String biomePath) {

        // only exists in vanilla biomes
        if (RepurposedStructures.RSAllConfig.RSMainConfig.misc.hornedSwampTree && !biomeNamespace.equals("ultra_amplified_dimension") && biome == Biomes.SWAMP) {
            biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSFeatures.HORNED_SWAMP_TREE.configure(DefaultBiomeFeatures.SWAMP_TREE_CONFIG).createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(0, 0.7F, 1))));
        }

        // can exist in modded biomes too
        else if (RepurposedStructures.RSAllConfig.RSMainConfig.misc.hornedSwampTree &&
                (biome == Biomes.SWAMP_HILLS ||
                        (RepurposedStructures.RSAllConfig.RSMainConfig.misc.addMiscToModdedBiomes &&
                                biome.getCategory() == Category.SWAMP &&
                                !biomeNamespace.equals("ultra_amplified_dimension") &&
                                !biomeNamespace.equals("minecraft")))) {

            // replace the swamp tree with our own
            biome.getFeaturesForStep(GenerationStep.Feature.VEGETAL_DECORATION).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && serializeAndCompareFeature(configuredFeature, VANILLA_SWAMP_TREE));
            biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSFeatures.HORNED_SWAMP_TREE.configure(DefaultBiomeFeatures.SWAMP_TREE_CONFIG).createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(2, 0.8F, 1))));
        }

        if (RepurposedStructures.RSAllConfig.RSMainConfig.misc.boulderGiant && !biomeNamespace.equals("ultra_amplified_dimension") &&
                ((biome == Biomes.GIANT_SPRUCE_TAIGA_HILLS || biome == Biomes.GIANT_TREE_TAIGA_HILLS) ||
                        (RepurposedStructures.RSAllConfig.RSMainConfig.misc.addMiscToModdedBiomes && !biomeNamespace.equals("minecraft") &&
                                ((biomePath.contains("giant") && biomePath.contains("taiga")) || biomePath.contains("redwood"))))) {

            // replace the boulders with our own
            biome.getFeaturesForStep(GenerationStep.Feature.LOCAL_MODIFICATIONS).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && serializeAndCompareFeature(configuredFeature, VANILLA_BOULDER));
            biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, RSFeatures.BOULDER_GIANT.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.COUNT_TOP_SOLID.configure(new CountDecoratorConfig(biomeNamespace.equals("minecraft") ? 2 : 1))));
        }
        else if (RepurposedStructures.RSAllConfig.RSMainConfig.misc.boulderTiny && !biomeNamespace.equals("ultra_amplified_dimension") &&
                ((biome == Biomes.SNOWY_TAIGA_MOUNTAINS || biome == Biomes.TAIGA_MOUNTAINS) ||
                        (RepurposedStructures.RSAllConfig.RSMainConfig.misc.addMiscToModdedBiomes && !biomeNamespace.equals("minecraft") &&
                                biomePath.contains("taiga") && (biomePath.contains("mountain") || biomePath.contains("hill"))))) {

            biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, RSFeatures.BOULDER_TINY.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.COUNT_TOP_SOLID.configure(new CountDecoratorConfig(2))));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // STRONGHOLDS //


    public static void addStrongholds(Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.allowStonebrickStronghold &&
                RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdSpawnrate != 1001 &&
                biome.getCategory() != Category.NETHER &&
                (biome.hasStructureFeature(StructureFeature.STRONGHOLD) ||
                        (!biomeNamespace.equals("minecraft") && RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.addStonebrickStrongholdToModdedBiomes))) {

            //replace vanilla stronghold with ours if vanilla's is present
            ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.STRONGHOLD);
            biome.addStructureFeature(RSFeatures.STONEBRICK_STRONGHOLD.configure(FeatureConfig.DEFAULT));

        }

        else if (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSpawnrate != 1001 && biome.getCategory() == Category.NETHER &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.addNetherStrongholdToModdedBiomes)) {
            biome.addStructureFeature(RSFeatures.NETHER_STRONGHOLD.configure(FeatureConfig.DEFAULT));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OUTPOSTS //

    public static void addOutposts(Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.netherBrickOutpostSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addNetherBrickOutpostToModdedBiomes)) {
            biome.addStructureFeature(RSFeatures.NETHER_BRICK_OUTPOST.configure(FeatureConfig.DEFAULT));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // JUNGLE FORTRESS //


    public static void addJungleFortress(Biome biome, String biomeNamespace, String biomePath) {
        if(RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.jungleFortressSpawnrate != 1001)
        {
            if ( biome.getCategory() == Category.JUNGLE &&
                    (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.addJungleFortressToModdedBiomes)) {
                biome.addStructureFeature(RSFeatures.JUNGLE_FORTRESS.configure(FeatureConfig.DEFAULT));
            }
            biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSFeatures.JUNGLE_STRUCTURES_VINES.configure(FeatureConfig.DEFAULT).createDecoratedFeature(RSPlacements.RS_VINE_PLACEMENT.configure(new RangeDecoratorConfig(40, 0, 2, 20))));
            biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, RSFeatures.FORTRESS_BREAKAGE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(1, 0.2F, 1))));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TEMPLES //


    public static void addTemplesAndPyramids(Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherBasaltTempleSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER && (biomePath.contains("basalt") || biomePath.contains("blackstone")) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherBasaltTempleToModdedBiomes)) {
            biome.addStructureFeature(RSFeatures.NETHER_BASALT_TEMPLE.configure(FeatureConfig.DEFAULT));
        }
        else if (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherCrimsonTempleSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER && biomePath.contains("crimson") &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherCrimsonTempleToModdedBiomes)) {
            biome.addStructureFeature(RSFeatures.NETHER_CRIMSON_TEMPLE.configure(FeatureConfig.DEFAULT));
        }
        else if (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWarpedTempleSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER && biomePath.contains("warped") &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherWarpedTempleToModdedBiomes)) {
            biome.addStructureFeature(RSFeatures.NETHER_WARPED_TEMPLE.configure(FeatureConfig.DEFAULT));
        }
        else if (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherSoulTempleSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER && biomePath.contains("soul") &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherSoulTempleToModdedBiomes)) {
            biome.addStructureFeature(RSFeatures.NETHER_SOUL_TEMPLE.configure(FeatureConfig.DEFAULT));
        }
        else if (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWastelandTempleSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherWastelandTempleToModdedBiomes)) {
            biome.addStructureFeature(RSFeatures.NETHER_WASTELAND_TEMPLE.configure(FeatureConfig.DEFAULT));
        }


        if (RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.netherPyramidSpawnrate != 1001 && biome.getCategory() == Category.NETHER &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.addNetherPyramidToModdedBiomes)) {
            biome.addStructureFeature(RSFeatures.NETHER_PYRAMID.configure(FeatureConfig.DEFAULT));
        }

        if (RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.badlandsPyramidSpawnrate != 1001 && biome.getCategory() == Category.MESA &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.addBadlandsPyramidToModdedBiomes)) {
            biome.addStructureFeature(RSFeatures.BADLANDS_TEMPLE.configure(FeatureConfig.DEFAULT));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // IGLOOS //


    public static void addIgloos(Biome biome, String biomeNamespace, String biomePath) {
        if (RepurposedStructures.RSAllConfig.RSMainConfig.igloos.grassyIglooSpawnrate != 1001) {
            if ((biome.getCategory() == Category.FOREST || biome.getCategory() == Category.PLAINS) &&
                    (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMainConfig.igloos.addGrassyIglooToModdedBiomes)) {
                biome.addStructureFeature(RSFeatures.GRASSY_IGLOO.configure(FeatureConfig.DEFAULT));
            }
        }

        if (RepurposedStructures.RSAllConfig.RSMainConfig.igloos.stoneIglooSpawnrate != 1001) {
            String BiomeName = Registry.BIOME.getId(biome).getPath();
            if ((biome.getCategory() == Category.TAIGA && BiomeName.contains("giant")) &&
                    (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMainConfig.igloos.addStoneIglooToModdedBiomes)) {
                biome.addStructureFeature(RSFeatures.STONE_IGLOO.configure(FeatureConfig.DEFAULT));
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // VILLAGES //


    public static void addVillages(Biome biome, String biomeNamespace, String biomePath) {
        if ((biome.getCategory() == Category.MESA && !biomePath.contains("plateau")) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.badlandsVillageSpawnrate != 1001) {
                biome.addStructureFeature(RSFeatures.BADLANDS_VILLAGE.configure(FeatureConfig.DEFAULT));
            }
        }

        else if (biomePath.contains("birch") && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.birchVillageSpawnrate != 1001) {
                biome.addStructureFeature(RSFeatures.BIRCH_VILLAGE.configure(FeatureConfig.DEFAULT));
            }
        }

        else if (biomePath.contains("dark_forest") && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.darkForestVillageSpawnrate != 1001) {
                biome.addStructureFeature(RSFeatures.DARK_FOREST_VILLAGE.configure(FeatureConfig.DEFAULT));
            }
        }

        else if (biome.getCategory() == Category.JUNGLE && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.jungleVillageSpawnrate != 1001) {
                biome.addStructureFeature(RSFeatures.JUNGLE_VILLAGE.configure(FeatureConfig.DEFAULT));
                biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSFeatures.JUNGLE_STRUCTURES_VINES.configure(FeatureConfig.DEFAULT).createDecoratedFeature(RSPlacements.RS_VINE_PLACEMENT.configure(new RangeDecoratorConfig(30, 0, 8, 16))));
            }
        }

        else if (biome.getCategory() == Category.SWAMP && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.swampVillageSpawnrate != 1001) {
                biome.addStructureFeature(RSFeatures.SWAMP_VILLAGE.configure(FeatureConfig.DEFAULT));
                biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSFeatures.SWAMP_VILLAGE_VINES.configure(FeatureConfig.DEFAULT).createDecoratedFeature(RSPlacements.RS_VINE_PLACEMENT.configure(new RangeDecoratorConfig(30, 0,8, 16))));
            }
        }

        else if (biome.getCategory() == Category.EXTREME_HILLS && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.mountainsVillageSpawnrate != 1001) {
                biome.addStructureFeature(RSFeatures.MOUNTAINS_VILLAGE.configure(FeatureConfig.DEFAULT));
            }
        }

        else if ((biome == Biomes.GIANT_SPRUCE_TAIGA || biome == Biomes.GIANT_TREE_TAIGA) ||
                (RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes &&
                        ((biomePath.contains("giant") && biomePath.contains("taiga")) || biomePath.contains("redwood")))) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.giantTaigaVillageSpawnrate != 1001) {
                biome.addStructureFeature(RSFeatures.GIANT_TAIGA_VILLAGE.configure(FeatureConfig.DEFAULT));
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GENERAL UTILITIES //


    /**
     * Will serialize (if possible) both features and check if they are the same feature. If cannot serialize, compare the feature itself to see if it is the same
     */
    private static boolean serializeAndCompareFeature(ConfiguredFeature<?, ?> feature1, ConfiguredFeature<?, ?> feature2) {


        // One of the features cannot be serialized which can only happen with custom modded features
        // Check if the features are the same feature even though the placement or config for the feature might be different.
        // This is the best way we can remove duplicate modded features as best as we can. (I think)
        if ((feature1.config instanceof DecoratedFeatureConfig && feature2.config instanceof DecoratedFeatureConfig) &&
                ((DecoratedFeatureConfig) feature1.config).feature.feature == ((DecoratedFeatureConfig) feature2.config).feature.feature) {
            return true;
        }

        return false;
    }
}
