package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.world.placements.RSPlacements;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BuiltInBiomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.function.Supplier;

public class RSAddFeatures {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MINESHAFTS //

    public static void addMineshafts(MutableRegistry<Biome> biomeReg, Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.birchMineshaftSpawnrate != 0 && biomePath.contains("birch") &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.getGenerationSettings().hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == StructureFeature.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.BIRCH_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.jungleMineshaftSpawnrate != 0 && biome.getCategory() == Category.JUNGLE &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.getGenerationSettings().hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == StructureFeature.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.JUNGLE_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.desertMineshaftSpawnrate != 0 && biome.getCategory() == Category.DESERT &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.getGenerationSettings().hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == StructureFeature.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.DESERT_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.stoneMineshaftSpawnrate != 0 && biome.getCategory() == Category.EXTREME_HILLS &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.getGenerationSettings().hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == StructureFeature.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.STONE_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.savannaMineshaftSpawnrate != 0 && biome.getCategory() == Category.SAVANNA &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.getGenerationSettings().hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == StructureFeature.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.SAVANNA_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.icyMineshaftSpawnrate != 0 && (biome.getCategory() == Category.ICY || biomePath.contains("snowy")) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.getGenerationSettings().hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == StructureFeature.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.ICY_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.oceanMineshaftSpawnrate != 0 && biome.getCategory() == Category.OCEAN &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.getGenerationSettings().hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == StructureFeature.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.OCEAN_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.taigaMineshaftSpawnrate != 0 && biome.getCategory() == Category.TAIGA &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.getGenerationSettings().hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == StructureFeature.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.TAIGA_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.swampAndDarkForestMineshaftSpawnrate != 0 && (biome.getCategory() == Category.SWAMP || biomePath.contains("dark_forest") || biomePath.contains("dark_oak")) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.getGenerationSettings().hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == StructureFeature.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.SWAMP_OR_DARK_FOREST_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.endMineshaftSpawnrate != 0 &&
                biome.getCategory() == Category.THEEND && biome != biomeReg.get(BuiltInBiomes.THE_END) &&
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.misc.barrensIslandsEndMineshafts ||
                        (biome != biomeReg.get(BuiltInBiomes.END_BARRENS) && biome != biomeReg.get(BuiltInBiomes.SMALL_END_ISLANDS))) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.END_MINESHAFT.configure(FeatureConfig.DEFAULT));
        }
        else if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.netherMineshaftSpawnrate != 0 && biome.getCategory() == Category.NETHER &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.NETHER_MINESHAFT.configure(FeatureConfig.DEFAULT));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DUNGEONS //

    public static void addDungeons(MutableRegistry<Biome> biomeReg, Biome biome, String biomeNamespace, String biomePath) {

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
                (biome.getCategory() == Category.THEEND &&
                        biome != biomeReg.get(BuiltInBiomes.THE_END) &&
                        biome != biomeReg.get(BuiltInBiomes.SMALL_END_ISLANDS)) &&
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
            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.UNDERGROUND_STRUCTURES.ordinal())
                .removeIf(supplier ->
                    supplier.get().config instanceof DecoratedFeatureConfig &&
                    ((DecoratedFeatureConfig)supplier.get().config).feature.get().config instanceof DecoratedFeatureConfig &&
                    ((DecoratedFeatureConfig)((DecoratedFeatureConfig)supplier.get().config).feature.get().config).feature.get().config instanceof DecoratedFeatureConfig &&
                    ((DecoratedFeatureConfig)((DecoratedFeatureConfig)((DecoratedFeatureConfig)supplier.get().config).feature.get().config).feature.get().config).feature.get().feature == Feature.MONSTER_ROOM);
        }

        //add given dungeon
        biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.UNDERGROUND_STRUCTURES.ordinal())
                .add(() -> rsDungeon.configure(FeatureConfig.DEFAULT)
                            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(minHeight, 0, maxheight))
                                    .repeat(spawnrate)));
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

            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSFeatures.BADLANDS_WELL.configure(FeatureConfig.DEFAULT)
                                    .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                                    .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.badlandsWellSpawnrate));
        }
        else if (RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.netherWellSpawnrate != 10000 &&
                biome.getCategory() == Category.NETHER && wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSFeatures.NETHER_WELL.configure(FeatureConfig.DEFAULT)
                            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(30, 0, 98)))
                            .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.netherWellSpawnrate));
        }
        else if (RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.snowWellSpawnrate != 10000 &&
                (biome.getCategory() == Category.ICY || biomePath.contains("snow")) && wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSFeatures.SNOW_WELL.configure(FeatureConfig.DEFAULT)
                            .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                            .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.snowWellSpawnrate));
        }
        else if (RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.mossyStoneWellSpawnrate != 10000 &&
                (biome.getCategory() == Category.SWAMP || biome.getCategory() == Category.JUNGLE ||
                        biomePath.contains("dark_forest") || biomePath.contains("dark_oak")) &&
                wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSFeatures.MOSSY_STONE_WELL.configure(FeatureConfig.DEFAULT)
                            .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                            .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.mossyStoneWellSpawnrate));
        }
        else if (RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.forestWellSpawnrate != 10000 &&
                (biome.getCategory() == Category.FOREST && !(biomePath.contains("dark_forest") || biomePath.contains("dark_oak"))) &&
                wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSFeatures.FOREST_WELL.configure(FeatureConfig.DEFAULT)
                            .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                            .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.forestWellSpawnrate));
        }
    }


    private static boolean wellAllowedByNamespaceAndConfig(String biomeNamespace) {
        return biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSWellsConfig.addWellsToModdedBiomes;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SWAMP TREE FEATURES //

    private static final TreeFeatureConfig TREE_FEATURE_CONFIG = (new TreeFeatureConfig.Builder(
                                            new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()), 
                                            new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()), 
                                            new BlobFoliagePlacer(UniformIntDistribution.of(3), UniformIntDistribution.of(0), 3),
                                            new StraightTrunkPlacer(5, 3, 0), 
                                            new TwoLayersFeatureSize(1, 0, 1))).build();
    
    public static void addSwampTreeFeatures(MutableRegistry<Biome> biomeReg, Biome biome, String biomeNamespace, String biomePath) {

        // only exists in vanilla biomes
        if (RepurposedStructures.RSAllConfig.RSMainConfig.misc.hornedSwampTree && biome == biomeReg.get(BuiltInBiomes.SWAMP)) {
            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.VEGETAL_DECORATION.ordinal())
                    .add(() -> RSFeatures.HORNED_SWAMP_TREE.configure(TREE_FEATURE_CONFIG)
                            .decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.7F, 1))
                            .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)));
        }

        // can exist in modded biomes too
        else if (RepurposedStructures.RSAllConfig.RSMainConfig.misc.hornedSwampTree &&
                (biome == biomeReg.get(BuiltInBiomes.SWAMP_HILLS) ||
                        (RepurposedStructures.RSAllConfig.RSMainConfig.misc.addSwampTreeToModdedBiomes &&
                                biome.getCategory() == Category.SWAMP &&
                                !biomeNamespace.equals("ultra_amplified_dimension") &&
                                !biomeNamespace.equals("minecraft")))) {

            // replace the swamp tree with our own
            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.VEGETAL_DECORATION.ordinal()).removeIf(configuredFeature -> configuredFeature.get().config instanceof DecoratedFeatureConfig && serializeAndCompareFeature(configuredFeature.get(), ConfiguredFeatures.SWAMP_TREE));
            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.VEGETAL_DECORATION.ordinal())
                    .add(() -> RSFeatures.HORNED_SWAMP_TREE.configure(TREE_FEATURE_CONFIG)
                            .decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(2, 0.8F, 1))
                            .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MISC FEATURES //

    public static void addBoulderFeatures(MutableRegistry<Biome> biomeReg, Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSAllConfig.RSMainConfig.misc.boulderGiant &&
                ((biome == biomeReg.get(BuiltInBiomes.GIANT_SPRUCE_TAIGA_HILLS) || biome == biomeReg.get(BuiltInBiomes.GIANT_TREE_TAIGA_HILLS)) ||
                        (RepurposedStructures.RSAllConfig.RSMainConfig.misc.addBoulderToModdedBiomes && !biomeNamespace.equals("minecraft") &&
                                ((biomePath.contains("giant") && biomePath.contains("taiga")) || biomePath.contains("redwood"))))){

            // replace the boulders with our own
            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.LOCAL_MODIFICATIONS.ordinal()).removeIf(configuredFeature -> configuredFeature.get().config instanceof DecoratedFeatureConfig && serializeAndCompareFeature(configuredFeature.get(), ConfiguredFeatures.FOREST_ROCK));
            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.LOCAL_MODIFICATIONS.ordinal())
                    .add(() -> RSFeatures.BOULDER_GIANT.configure(FeatureConfig.DEFAULT)
                            .decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig((int)RepurposedStructures.RSAllConfig.RSMainConfig.misc.giantBouldersPerChunk, (float) (RepurposedStructures.RSAllConfig.RSMainConfig.misc.giantBouldersPerChunk - ((int)RepurposedStructures.RSAllConfig.RSMainConfig.misc.giantBouldersPerChunk)), 1))
                            .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)));
        }
        else if (RepurposedStructures.RSAllConfig.RSMainConfig.misc.boulderTiny &&
                ((biome == biomeReg.get(BuiltInBiomes.SNOWY_TAIGA_MOUNTAINS) || biome == biomeReg.get(BuiltInBiomes.TAIGA_MOUNTAINS)) ||
                        (RepurposedStructures.RSAllConfig.RSMainConfig.misc.addBoulderToModdedBiomes && !biomeNamespace.equals("minecraft") &&
                                biomePath.contains("taiga") && (biomePath.contains("mountain") || biomePath.contains("hill"))))) {

            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.LOCAL_MODIFICATIONS.ordinal())
                    .add(() -> RSFeatures.BOULDER_TINY.configure(FeatureConfig.DEFAULT).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).repeat(2));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // STRONGHOLDS //

    public static void addStrongholds(Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.allowStonebrickStronghold &&
                RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdSpawnrate != 1001 &&
                biome.getCategory() != Category.NETHER &&
                (biome.getGenerationSettings().hasStructureFeature(StructureFeature.STRONGHOLD) ||
                        (!biomeNamespace.equals("minecraft") && RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.addStonebrickStrongholdToModdedBiomes))) {

            //replace vanilla stronghold with ours if vanilla's is present
            biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == StructureFeature.STRONGHOLD);
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.STONEBRICK_STRONGHOLD.configure(FeatureConfig.DEFAULT));
            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.UNDERGROUND_DECORATION.ordinal())
                    .add(() -> RSFeatures.STRONGHOLD_CHAINS.configure(FeatureConfig.DEFAULT).decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(5, 0, Math.max(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdMaxHeight, RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdMinHeight+1)+15)).repeat(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdChainSpawnrate)));
        }

        else if (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSpawnrate != 1001 && biome.getCategory() == Category.NETHER &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.addNetherStrongholdToModdedBiomes)) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.NETHER_STRONGHOLD.configure(FeatureConfig.DEFAULT));
            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.UNDERGROUND_DECORATION.ordinal())
                    .add(() -> RSFeatures.STRONGHOLD_CHAINS.configure(FeatureConfig.DEFAULT).decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(5, 0, Math.max(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdMaxHeight, RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdMinHeight+1)+15)).repeat(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdChainSpawnrate)));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OUTPOSTS //

    public static void addOutposts(Biome biome, String biomeNamespace, String biomePath) {

        //Nether based Outposts
        if(biome.getCategory() == Category.NETHER)
        {
            if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.crimsonOutpostSpawnrate != 1001 &&
                    biomePath.contains("crimson") &&
                    (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addCrimsonOutpostToModdedBiomes)) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.CRIMSON_OUTPOST.configure(FeatureConfig.DEFAULT));
            }
            else if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.warpedOutpostSpawnrate != 1001 &&
                    biomePath.contains("warped") &&
                    (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addWarpedOutpostToModdedBiomes)) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.WARPED_OUTPOST.configure(FeatureConfig.DEFAULT));
            }
            else if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.netherBrickOutpostSpawnrate != 1001 &&
                    (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addNetherBrickOutpostToModdedBiomes)) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.NETHER_BRICK_OUTPOST.configure(FeatureConfig.DEFAULT));
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OUTPOSTS //

    public static void addShipwrecks(MutableRegistry<Biome> biomeReg, Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSAllConfig.RSMainConfig.shipwrecks.endShipwreckSpawnrate != 1001 &&
                (biome == biomeReg.get(BuiltInBiomes.END_HIGHLANDS) ||
                (!biomeNamespace.equals("minecraft") && biome.getCategory() == Category.THEEND &&
                RepurposedStructures.RSAllConfig.RSMainConfig.shipwrecks.addEndShipwreckToModdedBiomes))) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.END_SHIPWRECK.configure(FeatureConfig.DEFAULT));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // JUNGLE FORTRESS //

    public static void addJungleFortress(Biome biome, String biomeNamespace, String biomePath) {
        if(RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.jungleFortressSpawnrate != 1001)
        {
            if ( biome.getCategory() == Category.JUNGLE &&
                    (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.addJungleFortressToModdedBiomes)) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.JUNGLE_FORTRESS.configure(FeatureConfig.DEFAULT));
            }
            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.VEGETAL_DECORATION.ordinal())
                    .add(() -> RSFeatures.JUNGLE_STRUCTURES_VINES.configure(FeatureConfig.DEFAULT).decorate(RSPlacements.RS_VINE_PLACEMENT.configure(new RangeDecoratorConfig(40, 0, 2)).repeat(20)));
            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.UNDERGROUND_ORES.ordinal())
                    .add(() -> RSFeatures.FORTRESS_BREAKAGE.configure(FeatureConfig.DEFAULT).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(1, 0.2F, 1)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TEMPLES //

    public static void addTemples(Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherBasaltTempleSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER && (biomePath.contains("basalt") || biomePath.contains("blackstone")) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherBasaltTempleToModdedBiomes)) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.NETHER_BASALT_TEMPLE.configure(FeatureConfig.DEFAULT));
        }
        else if (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherCrimsonTempleSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER && biomePath.contains("crimson") &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherCrimsonTempleToModdedBiomes)) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.NETHER_CRIMSON_TEMPLE.configure(FeatureConfig.DEFAULT));
        }
        else if (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWarpedTempleSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER && biomePath.contains("warped") &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherWarpedTempleToModdedBiomes)) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.NETHER_WARPED_TEMPLE.configure(FeatureConfig.DEFAULT));
        }
        else if (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherSoulTempleSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER && biomePath.contains("soul") &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherSoulTempleToModdedBiomes)) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.NETHER_SOUL_TEMPLE.configure(FeatureConfig.DEFAULT));
        }
        else if (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWastelandTempleSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherWastelandTempleToModdedBiomes)) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.NETHER_WASTELAND_TEMPLE.configure(FeatureConfig.DEFAULT));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Pyramids //

    public static void addPyramids(Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.netherPyramidSpawnrate != 1001 && biome.getCategory() == Category.NETHER &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.addNetherPyramidToModdedBiomes)) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.NETHER_PYRAMID.configure(FeatureConfig.DEFAULT));
        }

        if (RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.badlandsPyramidSpawnrate != 1001 && biome.getCategory() == Category.MESA &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.addBadlandsPyramidToModdedBiomes)) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.BADLANDS_TEMPLE.configure(FeatureConfig.DEFAULT));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // IGLOOS //

    public static void addIgloos(Biome biome, String biomeNamespace, String biomePath) {
        if (RepurposedStructures.RSAllConfig.RSMainConfig.igloos.grassyIglooSpawnrate != 1001) {
            if ((biome.getCategory() == Category.FOREST || biome.getCategory() == Category.PLAINS) &&
                    (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMainConfig.igloos.addGrassyIglooToModdedBiomes)) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.GRASSY_IGLOO.configure(FeatureConfig.DEFAULT));
            }
        }

        if (RepurposedStructures.RSAllConfig.RSMainConfig.igloos.stoneIglooSpawnrate != 1001) {
            if ((biome.getCategory() == Category.TAIGA && biomePath.contains("giant")) &&
                    (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSMainConfig.igloos.addStoneIglooToModdedBiomes)) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.STONE_IGLOO.configure(FeatureConfig.DEFAULT));
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // VILLAGES //

    public static void addVillages(MutableRegistry<Biome> biomeReg, Biome biome, String biomeNamespace, String biomePath) {
        if ((biome.getCategory() == Category.MESA && !biomePath.contains("plateau")) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.badlandsVillageSpawnrate != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.BADLANDS_VILLAGE.configure(FeatureConfig.DEFAULT));
            }
        }

        else if (biomePath.contains("birch") && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.birchVillageSpawnrate != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.BIRCH_VILLAGE.configure(FeatureConfig.DEFAULT));
            }
        }

        else if (biomePath.contains("dark_forest") && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.darkForestVillageSpawnrate != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.DARK_FOREST_VILLAGE.configure(FeatureConfig.DEFAULT));
            }
        }

        else if (biome.getCategory() == Category.JUNGLE && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.jungleVillageSpawnrate != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.JUNGLE_VILLAGE.configure(FeatureConfig.DEFAULT));
                biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.VEGETAL_DECORATION.ordinal())
                        .add(() -> RSFeatures.JUNGLE_STRUCTURES_VINES.configure(FeatureConfig.DEFAULT).decorate(RSPlacements.RS_VINE_PLACEMENT.configure(new RangeDecoratorConfig(30, 0, 8)).repeat(16)));
            }
        }

        else if (biome.getCategory() == Category.SWAMP && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.swampVillageSpawnrate != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.SWAMP_VILLAGE.configure(FeatureConfig.DEFAULT));
                biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.VEGETAL_DECORATION.ordinal())
                        .add(() -> RSFeatures.SWAMP_VILLAGE_VINES.configure(FeatureConfig.DEFAULT).decorate(RSPlacements.RS_VINE_PLACEMENT.configure(new RangeDecoratorConfig(30, 0,8)).repeat(16)));
            }
        }

        else if (biome.getCategory() == Category.EXTREME_HILLS && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.mountainsVillageSpawnrate != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.MOUNTAINS_VILLAGE.configure(FeatureConfig.DEFAULT));
            }
        }

        else if ((biome == biomeReg.get(BuiltInBiomes.GIANT_SPRUCE_TAIGA) || biome == biomeReg.get(BuiltInBiomes.GIANT_TREE_TAIGA)) ||
                (!biomeNamespace.equals("minecraft") &&
                  RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes &&
                ((biomePath.contains("giant") && biomePath.contains("taiga")) || biomePath.contains("redwood")))) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.giantTaigaVillageSpawnrate != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.GIANT_TAIGA_VILLAGE.configure(FeatureConfig.DEFAULT));
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSVillagesConfig.crimsonVillageSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER && biomePath.contains("crimson") &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.CRIMSON_VILLAGE.configure(FeatureConfig.DEFAULT));
        }
        else if (RepurposedStructures.RSAllConfig.RSVillagesConfig.warpedVillageSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER && biomePath.contains("warped") &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.WARPED_VILLAGE.configure(FeatureConfig.DEFAULT));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GENERAL UTILITIES //

    /**
     * Will serialize (if possible) both features and check if they are the same feature. If cannot serialize, compare the feature itself to see if it is the same
     * Doesn't actually serialize because I don't know how to do that in 1.16.1 lmao
     */
    private static boolean serializeAndCompareFeature(ConfiguredFeature<?, ?> feature1, ConfiguredFeature<?, ?> feature2) {

        // One of the features cannot be serialized which can only happen with custom modded features
        // Check if the features are the same feature even though the placement or config for the feature might be different.
        // This is the best way we can remove duplicate modded features as best as we can. (I think)
        if ((feature1.config instanceof DecoratedFeatureConfig && feature2.config instanceof DecoratedFeatureConfig) &&
                ((DecoratedFeatureConfig) feature1.config).feature.get().feature == ((DecoratedFeatureConfig) feature2.config).feature.get().feature) {
            return true;
        }

        return false;
    }

}
