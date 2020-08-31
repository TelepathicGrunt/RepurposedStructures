package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.world.placements.RSPlacements;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class RSAddFeatures {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MINESHAFTS //

    public static void addMineshafts(MutableRegistry<Biome> biomeReg, Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSMineshaftsConfig.birchMineshaftSpawnrate.get() != 0 && biomePath.contains("birch") &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            if (biome.getGenerationSettings().hasStructureFeature(Structure.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.BIRCH_MINESHAFT.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }
        else if (RepurposedStructures.RSMineshaftsConfig.jungleMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.JUNGLE &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            if (biome.getGenerationSettings().hasStructureFeature(Structure.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.JUNGLE_MINESHAFT.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }
        else if (RepurposedStructures.RSMineshaftsConfig.desertMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.DESERT &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            if (biome.getGenerationSettings().hasStructureFeature(Structure.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.DESERT_MINESHAFT.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }
        else if (RepurposedStructures.RSMineshaftsConfig.stoneMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.EXTREME_HILLS &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            if (biome.getGenerationSettings().hasStructureFeature(Structure.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.STONE_MINESHAFT.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }
        else if (RepurposedStructures.RSMineshaftsConfig.savannaMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.SAVANNA &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            if (biome.getGenerationSettings().hasStructureFeature(Structure.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.SAVANNA_MINESHAFT.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }
        else if (RepurposedStructures.RSMineshaftsConfig.icyMineshaftSpawnrate.get() != 0 && (biome.getCategory() == Category.ICY || biomePath.contains("snowy")) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            if (biome.getGenerationSettings().hasStructureFeature(Structure.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.ICY_MINESHAFT.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }
        else if (RepurposedStructures.RSMineshaftsConfig.oceanMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.OCEAN &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            if (biome.getGenerationSettings().hasStructureFeature(Structure.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.OCEAN_MINESHAFT.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }
        else if (RepurposedStructures.RSMineshaftsConfig.taigaMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.TAIGA &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            if (biome.getGenerationSettings().hasStructureFeature(Structure.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.TAIGA_MINESHAFT.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }
        else if (RepurposedStructures.RSMineshaftsConfig.swampAndDarkForestMineshaftSpawnrate.get() != 0 && (biome.getCategory() == Category.SWAMP || biomePath.contains("dark_forest") || biomePath.contains("dark_oak")) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            if (biome.getGenerationSettings().hasStructureFeature(Structure.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.SWAMP_OR_DARK_FOREST_MINESHAFT.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }
        else if (RepurposedStructures.RSMineshaftsConfig.endMineshaftSpawnrate.get() != 0 &&
                biome.getCategory() == Category.THEEND && biome != biomeReg.get(Biomes.THE_END) &&
                (RepurposedStructures.RSMineshaftsConfig.barrensIslandsEndMineshafts.get() ||
                        (biome != biomeReg.get(Biomes.END_BARRENS) && biome != biomeReg.get(Biomes.SMALL_END_ISLANDS))) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.END_MINESHAFT.configure(IFeatureConfig.NO_FEATURE_CONFIG));
        }
        else if (RepurposedStructures.RSMineshaftsConfig.netherMineshaftSpawnrate.get() != 0 && biome.getCategory() == Category.NETHER &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.NETHER_MINESHAFT.configure(IFeatureConfig.NO_FEATURE_CONFIG));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DUNGEONS //

    public static void addDungeons(MutableRegistry<Biome> biomeReg, Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSDungeonsConfig.jungleDungeonSpawnrate.get() != 0 &&
                biome.getCategory() == Category.JUNGLE && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.JUNGLE_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.jungleDungeonSpawnrate.get(),
                    RepurposedStructures.RSDungeonsConfig.jungleDungeonMinHeight.get(),
                    RepurposedStructures.RSDungeonsConfig.jungleDungeonMaxHeight.get());
        }
        else if (RepurposedStructures.RSDungeonsConfig.badlandsDungeonSpawnrate.get() != 0 &&
                biome.getCategory() == Category.MESA && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.BADLANDS_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.badlandsDungeonSpawnrate.get(),
                    RepurposedStructures.RSDungeonsConfig.badlandsDungeonMinHeight.get(),
                    RepurposedStructures.RSDungeonsConfig.badlandsDungeonMaxHeight.get());
        }
        else if (RepurposedStructures.RSDungeonsConfig.darkForestDungeonSpawnrate.get() != 0 &&
                biomePath.contains("dark_forest") && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.DARK_FOREST_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.darkForestDungeonSpawnrate.get(),
                    RepurposedStructures.RSDungeonsConfig.darkForestDungeonMinHeight.get(),
                    RepurposedStructures.RSDungeonsConfig.darkForestDungeonMaxHeight.get());
        }
        else if (RepurposedStructures.RSDungeonsConfig.desertDungeonSpawnrate.get() != 0 &&
                biome.getCategory() == Category.DESERT && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.DESERT_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.desertDungeonSpawnrate.get(),
                    RepurposedStructures.RSDungeonsConfig.desertDungeonMinHeight.get(),
                    RepurposedStructures.RSDungeonsConfig.desertDungeonMaxHeight.get());
        }
        else if (RepurposedStructures.RSDungeonsConfig.mushroomDungeonSpawnrate.get() != 0 &&
                biome.getCategory() == Category.MUSHROOM && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.MUSHROOM_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.mushroomDungeonSpawnrate.get(),
                    RepurposedStructures.RSDungeonsConfig.mushroomDungeonMinHeight.get(),
                    RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get());
        }
        else if (RepurposedStructures.RSDungeonsConfig.swampDungeonSpawnrate.get() != 0 &&
                biome.getCategory() == Category.SWAMP && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.SWAMP_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.swampDungeonSpawnrate.get(),
                    RepurposedStructures.RSDungeonsConfig.swampDungeonMinHeight.get(),
                    RepurposedStructures.RSDungeonsConfig.swampDungeonMaxHeight.get());
        }
        else if (RepurposedStructures.RSDungeonsConfig.snowDungeonSpawnrate.get() != 0 &&
                biome.getCategory() == Category.ICY && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.SNOW_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.snowDungeonSpawnrate.get(),
                    RepurposedStructures.RSDungeonsConfig.snowDungeonMinHeight.get(),
                    RepurposedStructures.RSDungeonsConfig.snowDungeonMaxHeight.get());
        }
        else if (RepurposedStructures.RSDungeonsConfig.netherDungeonSpawnrate.get() != 0 &&
                biome.getCategory() == Category.NETHER && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            replaceOrAddDungeon(
                    false,
                    biome,
                    RSFeatures.NETHER_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.netherDungeonSpawnrate.get(),
                    RepurposedStructures.RSDungeonsConfig.netherDungeonMinHeight.get(),
                    RepurposedStructures.RSDungeonsConfig.netherDungeonMaxHeight.get());
        }
        else if (RepurposedStructures.RSDungeonsConfig.endDungeonSpawnrate.get() != 0 &&
                (biome.getCategory() == Category.THEEND && biome != biomeReg.get(Biomes.THE_END) && biome != biomeReg.get(Biomes.SMALL_END_ISLANDS)) &&
                dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            replaceOrAddDungeon(
                    false,
                    biome,
                    RSFeatures.END_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.endDungeonSpawnrate.get(),
                    RepurposedStructures.RSDungeonsConfig.endDungeonMinHeight.get(),
                    RepurposedStructures.RSDungeonsConfig.endDungeonMaxHeight.get());
        }
        else if (RepurposedStructures.RSDungeonsConfig.oceanDungeonSpawnrate.get() != 0 &&
                biome.getCategory() == Category.OCEAN && dungeonAllowedByNamespaceAndConfig(biomeNamespace)) {

            replaceOrAddDungeon(
                    false,
                    biome,
                    RSFeatures.OCEAN_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonSpawnrate.get(),
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMinHeight.get(),
                    RepurposedStructures.RSDungeonsConfig.oceanDungeonMaxHeight.get());
        }
    }


    /**
     * Adds RS's dungeon to the biome along with option to remove vanilla dungeon as well.
     */
    private static void replaceOrAddDungeon(boolean replacing, Biome biome, Feature<NoFeatureConfig> rsDungeon, int spawnrate, int minHeight, int maxheight) {

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
        biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.UNDERGROUND_STRUCTURES.ordinal())
                .add(() -> rsDungeon.configure(IFeatureConfig.NO_FEATURE_CONFIG)
                        .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new TopSolidRangeConfig(minHeight, 0, maxheight))
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
        if (biomeNamespace.equals("minecraft") || RepurposedStructures.RSDungeonsConfig.addDungeonsToModdedBiomes.get()) {
            return true;
        }

        return false;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WELLS //

    public static void addWells(Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSWellsConfig.badlandsWellSpawnrate.get() != 10000 &&
                biome.getCategory() == Category.MESA && wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSFeatures.BADLANDS_WELL.configure(IFeatureConfig.NO_FEATURE_CONFIG)
                            .decorate(Features.Placements.SQUARE_HEIGHTMAP)
                            .applyChance(RepurposedStructures.RSWellsConfig.badlandsWellSpawnrate.get()));
        }
        else if (RepurposedStructures.RSWellsConfig.netherWellSpawnrate.get() != 10000 &&
                biome.getCategory() == Category.NETHER && wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSFeatures.NETHER_WELL.configure(IFeatureConfig.NO_FEATURE_CONFIG)
                            .decorate(Placement.RANGE.configure(new TopSolidRangeConfig(30, 0, 91)))
                            .applyChance(RepurposedStructures.RSWellsConfig.netherWellSpawnrate.get()));
        }
        else if (RepurposedStructures.RSWellsConfig.snowWellSpawnrate.get() != 10000 &&
                (biome.getCategory() == Category.ICY || biomePath.contains("snow")) && wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSFeatures.SNOW_WELL.configure(IFeatureConfig.NO_FEATURE_CONFIG)
                            .decorate(Features.Placements.SQUARE_HEIGHTMAP)
                            .applyChance(RepurposedStructures.RSWellsConfig.snowWellSpawnrate.get()));
        }
        else if (RepurposedStructures.RSWellsConfig.mossyStoneWellSpawnrate.get() != 10000 &&
                (biome.getCategory() == Category.SWAMP || biome.getCategory() == Category.JUNGLE ||
                        biomePath.contains("dark_forest") || biomePath.contains("dark_oak")) &&
                wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSFeatures.MOSSY_STONE_WELL.configure(IFeatureConfig.NO_FEATURE_CONFIG)
                            .decorate(Features.Placements.SQUARE_HEIGHTMAP)
                            .applyChance(RepurposedStructures.RSWellsConfig.mossyStoneWellSpawnrate.get()));
        }
        else if (RepurposedStructures.RSWellsConfig.forestWellSpawnrate.get() != 10000 &&
                (biome.getCategory() == Category.FOREST && !(biomePath.contains("dark_forest") || biomePath.contains("dark_oak"))) &&
                wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSFeatures.FOREST_WELL.configure(IFeatureConfig.NO_FEATURE_CONFIG)
                            .decorate(Features.Placements.SQUARE_HEIGHTMAP)
                            .applyChance(RepurposedStructures.RSWellsConfig.forestWellSpawnrate.get()));
        }
    }


    private static boolean wellAllowedByNamespaceAndConfig(String biomeNamespace) {
        if (biomeNamespace.equals("minecraft") || RepurposedStructures.RSWellsConfig.addWellsToModdedBiomes.get()) {
            return true;
        }

        return false;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SWAMP TREE FEATURES //

    private static final BaseTreeFeatureConfig TREE_FEATURE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
            new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()),
            new BlobFoliagePlacer(FeatureSpread.of(3), FeatureSpread.of(0), 3),
            new StraightTrunkPlacer(5, 3, 0),
            new TwoLayerFeature(1, 0, 1))).build();

    public static void addSwampTreeFeatures(MutableRegistry<Biome> biomeReg, Biome biome, String biomeNamespace, String biomePath) {

        // Exists in vanilla Swamp and can be in modded swamp biomes
        if (RepurposedStructures.RSMainConfig.hornedSwampTree.get() &&
                (biome == biomeReg.get(Biomes.SWAMP) ||
                (RepurposedStructures.RSMainConfig.addLargeSwampTreeModdedBiomes.get() &&
                        biome.getCategory() == Category.SWAMP &&
                        !biomeNamespace.equals("ultra_amplified_dimension") &&
                        !biomeNamespace.equals("minecraft")))){

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal())
                    .add(() -> RSFeatures.HORNED_SWAMP_TREE.configure(TREE_FEATURE_CONFIG)
                            .decorate(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.7F, 1))
                            .decorate(Features.Placements.SQUARE_HEIGHTMAP)));
        }

        // Only exists in vanilla Swamp Hills biomes
        else if (RepurposedStructures.RSMainConfig.hornedSwampTree.get() &&
                (biome == biomeReg.get(Biomes.SWAMP_HILLS))) {

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

    public static void addBoulderFeatures(MutableRegistry<Biome> biomeReg, Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSMainConfig.boulderGiant.get() && !biomeNamespace.equals("ultra_amplified_dimension") &&
                ((biome == biomeReg.get(Biomes.GIANT_SPRUCE_TAIGA_HILLS) || biome == biomeReg.get(Biomes.GIANT_TREE_TAIGA_HILLS)) ||
                        (RepurposedStructures.RSMainConfig.addGiantBouldersModdedBiomes.get() && !biomeNamespace.equals("minecraft") &&
                                ((biomePath.contains("giant") && biomePath.contains("taiga")) || biomePath.contains("redwood"))))) {

            // replace the boulders with our own
            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.LOCAL_MODIFICATIONS.ordinal())
                    .removeIf(configuredFeature -> configuredFeature.get().config instanceof DecoratedFeatureConfig &&
                            serializeAndCompareFeature(configuredFeature.get(), Features.FOREST_ROCK));

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.LOCAL_MODIFICATIONS.ordinal())
                    .add(() -> RSFeatures.BOULDER_GIANT.configure(IFeatureConfig.NO_FEATURE_CONFIG)
                            .decorate(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig((int)RepurposedStructures.RSMainConfig.giantBouldersPerChunk.get().doubleValue(), (float) (RepurposedStructures.RSMainConfig.giantBouldersPerChunk.get() - ((int)RepurposedStructures.RSMainConfig.giantBouldersPerChunk.get().doubleValue())), 1))
                            .decorate(Features.Placements.SQUARE_HEIGHTMAP)));
        }
        else if (RepurposedStructures.RSMainConfig.boulderTiny.get() && !biomeNamespace.equals("ultra_amplified_dimension") &&
                ((biome == biomeReg.get(Biomes.SNOWY_TAIGA_MOUNTAINS) || biome == biomeReg.get(Biomes.TAIGA_MOUNTAINS)) ||
                        (RepurposedStructures.RSMainConfig.addTinyBouldersModdedBiomes.get() && !biomeNamespace.equals("minecraft") &&
                                biomePath.contains("taiga") && (biomePath.contains("mountain") || biomePath.contains("hill"))))) {

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.LOCAL_MODIFICATIONS.ordinal())
                    .add(() -> RSFeatures.BOULDER_TINY.configure(IFeatureConfig.NO_FEATURE_CONFIG)
                            .decorate(Features.Placements.SQUARE_HEIGHTMAP)
                            .repeat(2));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // STRONGHOLDS //

    public static void addStrongholds(Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSStrongholdsConfig.allowStonebrickStronghold.get() &&
                RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdSpawnrate.get() != 1001 &&
                biome.getCategory() != Category.NETHER &&
                (biome.getGenerationSettings().hasStructureFeature(Structure.STRONGHOLD)||
                        (!biomeNamespace.equals("minecraft") && RepurposedStructures.RSStrongholdsConfig.addStonebrickStrongholdToModdedBiomes.get()))) {

            //replace vanilla stronghold with ours if vanilla's is present
            biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == Structure.STRONGHOLD);
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.STONEBRICK_STRONGHOLD.configure(IFeatureConfig.NO_FEATURE_CONFIG));

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.UNDERGROUND_DECORATION.ordinal())
                    .add(() -> RSFeatures.STRONGHOLD_CHAINS.configure(IFeatureConfig.NO_FEATURE_CONFIG)
                            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(
                                    new TopSolidRangeConfig(5, 0, Math.max(RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdMaxHeight.get(), RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdMinHeight.get()+1)+15))
                            .repeat(RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdChainSpawnrate.get())));

        }

        else if (RepurposedStructures.RSStrongholdsConfig.netherStrongholdSpawnrate.get() != 1001 && biome.getCategory() == Category.NETHER &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSStrongholdsConfig.addNetherStrongholdToModdedBiomes.get())) {

            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.NETHER_STRONGHOLD.configure(IFeatureConfig.NO_FEATURE_CONFIG));

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.UNDERGROUND_DECORATION.ordinal())
                    .add(() -> RSFeatures.STRONGHOLD_CHAINS.configure(IFeatureConfig.NO_FEATURE_CONFIG)
                            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(
                                    new TopSolidRangeConfig(5, 0, Math.max(RepurposedStructures.RSStrongholdsConfig.netherStrongholdMaxHeight.get(), RepurposedStructures.RSStrongholdsConfig.netherStrongholdMinHeight.get()+1)+15))
                            .repeat(RepurposedStructures.RSStrongholdsConfig.netherStrongholdChainSpawnrate.get())));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OUTPOSTS //

    public static void addOutposts(Biome biome, String biomeNamespace, String biomePath) {

        //Nether based Outposts
        if(biome.getCategory() == Category.NETHER)
        {
            if (RepurposedStructures.RSOutpostsConfig.crimsonOutpostSpawnrate.get() != 1001 &&
                    biomePath.contains("crimson") &&
                    (biomeNamespace.equals("minecraft") || RepurposedStructures.RSOutpostsConfig.addCrimsonOutpostToModdedBiomes.get())) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.CRIMSON_OUTPOST.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
            else if (RepurposedStructures.RSOutpostsConfig.warpedOutpostSpawnrate.get() != 1001 &&
                    biomePath.contains("warped") &&
                    (biomeNamespace.equals("minecraft") || RepurposedStructures.RSOutpostsConfig.addWarpedOutpostToModdedBiomes.get())) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.WARPED_OUTPOST.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
            else if (RepurposedStructures.RSOutpostsConfig.netherBrickOutpostSpawnrate.get() != 1001 &&
                    (biomeNamespace.equals("minecraft") || RepurposedStructures.RSOutpostsConfig.addNetherBrickOutpostToModdedBiomes.get())) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.NETHER_BRICK_OUTPOST.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OUTPOSTS //

    public static void addShipwrecks(MutableRegistry<Biome> biomeReg, Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSShipwrecksConfig.endShipwreckSpawnrate.get() != 1001 &&
                (biome == biomeReg.get(Biomes.END_HIGHLANDS) ||
                (!biomeNamespace.equals("minecraft") && biome.getCategory() == Category.THEEND &&
                RepurposedStructures.RSShipwrecksConfig.addEndShipwreckToModdedBiomes.get()))) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.END_SHIPWRECK.configure(IFeatureConfig.NO_FEATURE_CONFIG));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // JUNGLE FORTRESS //

    public static void addJungleFortress(Biome biome, String biomeNamespace, String biomePath) {
        if(RepurposedStructures.RSMainConfig.jungleFortressSpawnrate.get() != 1001)
        {
            if ( biome.getCategory() == Category.JUNGLE &&
                    (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMainConfig.addJungleFortressToModdedBiomes.get())) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.JUNGLE_FORTRESS.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal())
                    .add(() -> RSFeatures.JUNGLE_STRUCTURES_VINES.configure(IFeatureConfig.NO_FEATURE_CONFIG)
                            .decorate(RSPlacements.RS_VINE_PLACEMENT.configure(new TopSolidRangeConfig(40, 0, 2))
                            .repeat(20)));

            biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.UNDERGROUND_ORES.ordinal())
                    .add(() -> RSFeatures.FORTRESS_BREAKAGE.configure(IFeatureConfig.NO_FEATURE_CONFIG)
                            .decorate(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.2F, 1))
                            .decorate(Features.Placements.SQUARE_HEIGHTMAP)));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TEMPLES //

    public static void addTemples(Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSTemplesConfig.netherBasaltTempleSpawnrate.get() != 1001 &&
                biome.getCategory() == Category.NETHER && (biomePath.contains("basalt") || biomePath.contains("blackstone")) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSTemplesConfig.addNetherBasaltTempleToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.NETHER_BASALT_TEMPLE.configure(IFeatureConfig.NO_FEATURE_CONFIG));
        }
        else if (RepurposedStructures.RSTemplesConfig.netherCrimsonTempleSpawnrate.get() != 1001 &&
                biome.getCategory() == Category.NETHER && biomePath.contains("crimson") &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSTemplesConfig.addNetherCrimsonTempleToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.NETHER_CRIMSON_TEMPLE.configure(IFeatureConfig.NO_FEATURE_CONFIG));
        }
        else if (RepurposedStructures.RSTemplesConfig.netherWarpedTempleSpawnrate.get() != 1001 &&
                biome.getCategory() == Category.NETHER && biomePath.contains("warped") &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSTemplesConfig.addNetherWarpedTempleToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.NETHER_WARPED_TEMPLE.configure(IFeatureConfig.NO_FEATURE_CONFIG));
        }
        else if (RepurposedStructures.RSTemplesConfig.netherSoulTempleSpawnrate.get() != 1001 &&
                biome.getCategory() == Category.NETHER && biomePath.contains("soul") &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSTemplesConfig.addNetherSoulTempleToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.NETHER_SOUL_TEMPLE.configure(IFeatureConfig.NO_FEATURE_CONFIG));
        }
        else if (RepurposedStructures.RSTemplesConfig.netherWastelandTempleSpawnrate.get() != 1001 &&
                biome.getCategory() == Category.NETHER &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSTemplesConfig.addNetherWastelandTempleToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.NETHER_WASTELAND_TEMPLE.configure(IFeatureConfig.NO_FEATURE_CONFIG));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Pyramids //

    public static void addPyramids(Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSTemplesConfig.netherPyramidSpawnrate.get() != 1001 && biome.getCategory() == Category.NETHER &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSTemplesConfig.addNetherPyramidToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.NETHER_PYRAMID.configure(IFeatureConfig.NO_FEATURE_CONFIG));
        }

        if (RepurposedStructures.RSTemplesConfig.badlandsPyramidSpawnrate.get() != 1001 && biome.getCategory() == Category.MESA &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSTemplesConfig.addBadlandsPyramidToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.BADLANDS_TEMPLE.configure(IFeatureConfig.NO_FEATURE_CONFIG));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // IGLOOS //

    public static void addIgloos(Biome biome, String biomeNamespace, String biomePath) {
        if (RepurposedStructures.RSMainConfig.grassyIglooSpawnrate.get() != 1001) {
            if ((biome.getCategory() == Category.FOREST || biome.getCategory() == Category.PLAINS) &&
                    (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMainConfig.addGrassyIglooToModdedBiomes.get())) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.GRASSY_IGLOO.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }

        if (RepurposedStructures.RSMainConfig.stoneIglooSpawnrate.get() != 1001) {
            if ((biome.getCategory() == Category.TAIGA && biomePath.contains("giant")) &&
                    (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMainConfig.addStoneIglooToModdedBiomes.get())) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.STONE_IGLOO.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // VILLAGES //

    public static void addVillages(MutableRegistry<Biome> biomeReg, Biome biome, String biomeNamespace, String biomePath) {
        if ((biome.getCategory() == Category.MESA && !biomePath.contains("plateau")) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            if (RepurposedStructures.RSVillagesConfig.badlandsVillageSpawnrate.get() != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.BADLANDS_VILLAGE.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }

        else if (biomePath.contains("birch") && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            if (RepurposedStructures.RSVillagesConfig.birchVillageSpawnrate.get() != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.BIRCH_VILLAGE.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }

        else if (biomePath.contains("dark_forest") && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            if (RepurposedStructures.RSVillagesConfig.darkForestVillageSpawnrate.get() != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.DARK_FOREST_VILLAGE.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }

        else if (biome.getCategory() == Category.JUNGLE && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            if (RepurposedStructures.RSVillagesConfig.jungleVillageSpawnrate.get() != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.JUNGLE_VILLAGE.configure(IFeatureConfig.NO_FEATURE_CONFIG));
                biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal())
                        .add(() -> RSFeatures.JUNGLE_STRUCTURES_VINES.configure(IFeatureConfig.NO_FEATURE_CONFIG)
                                .decorate(RSPlacements.RS_VINE_PLACEMENT.configure(new TopSolidRangeConfig(30, 0, 8))
                                .repeat(16)));
            }
        }

        else if (biome.getCategory() == Category.SWAMP && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            if (RepurposedStructures.RSVillagesConfig.swampVillageSpawnrate.get() != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.SWAMP_VILLAGE.configure(IFeatureConfig.NO_FEATURE_CONFIG));
                biome.getGenerationSettings().getFeatures().get(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal())
                        .add(() -> RSFeatures.SWAMP_VILLAGE_VINES.configure(IFeatureConfig.NO_FEATURE_CONFIG)
                                .decorate(RSPlacements.RS_VINE_PLACEMENT.configure(new TopSolidRangeConfig(30, 0,8))
                                .repeat(16)));
            }
        }

        else if (biome.getCategory() == Category.EXTREME_HILLS && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            if (RepurposedStructures.RSVillagesConfig.mountainsVillageSpawnrate.get() != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.MOUNTAINS_VILLAGE.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }

        else if ((biome == biomeReg.get(Biomes.GIANT_SPRUCE_TAIGA) || biome == biomeReg.get(Biomes.GIANT_TREE_TAIGA)) ||
                (!biomeNamespace.equals("minecraft") &&
                  RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get() &&
                ((biomePath.contains("giant") && biomePath.contains("taiga")) || biomePath.contains("redwood")))) {
            if (RepurposedStructures.RSVillagesConfig.giantTaigaVillageSpawnrate.get() != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.GIANT_TAIGA_VILLAGE.configure(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }
        else if (RepurposedStructures.RSVillagesConfig.crimsonVillageSpawnrate.get() != 1001 &&
                biome.getCategory() == Category.NETHER && biomePath.contains("crimson") &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.CRIMSON_VILLAGE.configure(IFeatureConfig.NO_FEATURE_CONFIG));
        }
        else if (RepurposedStructures.RSVillagesConfig.warpedVillageSpawnrate.get() != 1001 &&
                biome.getCategory() == Category.NETHER && biomePath.contains("warped") &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSFeatures.WARPED_VILLAGE.configure(IFeatureConfig.NO_FEATURE_CONFIG));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GENERAL UTILITIES //

    /**
     * Will serialize (if possible) both features and check if they are the same feature. If cannot serialize, compare the feature itself to see if it is the same
     * Note: no longer serializes as I have no clue how to do that in 1.16.1
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
