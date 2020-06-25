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

        if (RepurposedStructures.RSMineshaftsConfig.spawnrate.birchMineshaftSpawnrate != 0 && biomePath.contains("birch") &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.MINESHAFT);
                biome.addStructureFeature(RSFeatures.BIRCH_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        } else if (RepurposedStructures.RSMineshaftsConfig.spawnrate.jungleMineshaftSpawnrate != 0 && biome.getCategory() == Category.JUNGLE &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.MINESHAFT);
                biome.addStructureFeature(RSFeatures.JUNGLE_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        } else if (RepurposedStructures.RSMineshaftsConfig.spawnrate.desertMineshaftSpawnrate != 0 && biome.getCategory() == Category.DESERT &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.MINESHAFT);
                biome.addStructureFeature(RSFeatures.DESERT_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        } else if (RepurposedStructures.RSMineshaftsConfig.spawnrate.stoneMineshaftSpawnrate != 0 && biome.getCategory() == Category.EXTREME_HILLS &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.MINESHAFT);
                biome.addStructureFeature(RSFeatures.STONE_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        } else if (RepurposedStructures.RSMineshaftsConfig.spawnrate.savannaMineshaftSpawnrate != 0 && biome.getCategory() == Category.SAVANNA &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.MINESHAFT);
                biome.addStructureFeature(RSFeatures.SAVANNA_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        } else if (RepurposedStructures.RSMineshaftsConfig.spawnrate.icyMineshaftSpawnrate != 0 && (biome.getCategory() == Category.ICY || biomePath.contains("snowy")) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.MINESHAFT);
                biome.addStructureFeature(RSFeatures.ICY_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        } else if (RepurposedStructures.RSMineshaftsConfig.spawnrate.oceanMineshaftSpawnrate != 0 && biome.getCategory() == Category.OCEAN &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.MINESHAFT);
                biome.addStructureFeature(RSFeatures.OCEAN_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        } else if (RepurposedStructures.RSMineshaftsConfig.spawnrate.taigaMineshaftSpawnrate != 0 && biome.getCategory() == Category.TAIGA &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.MINESHAFT);
                biome.addStructureFeature(RSFeatures.TAIGA_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        } else if (RepurposedStructures.RSMineshaftsConfig.spawnrate.swampAndDarkForestMineshaftSpawnrate != 0 && (biome.getCategory() == Category.SWAMP || biomePath.contains("dark_forest") || biomePath.contains("dark_oak")) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            if (biome.hasStructureFeature(StructureFeature.MINESHAFT)) {
                // replace vanilla mineshaft with our own
                ((BiomeStructureAccessor) biome).getStructureFeatures().remove(StructureFeature.MINESHAFT);
                biome.addStructureFeature(RSFeatures.SWAMP_OR_DARK_FOREST_MINESHAFT.configure(FeatureConfig.DEFAULT));
            }
        } else if (RepurposedStructures.RSMineshaftsConfig.spawnrate.endMineshaftSpawnrate != 0 && (biome.getCategory() == Category.THEEND && biome != Biomes.THE_END && biome != Biomes.END_BARRENS) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            biome.addStructureFeature(RSFeatures.END_MINESHAFT.configure(FeatureConfig.DEFAULT));
        } else if (RepurposedStructures.RSMineshaftsConfig.spawnrate.netherMineshaftSpawnrate != 0 && biome.getCategory() == Category.NETHER &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes)) {
            biome.addStructureFeature(RSFeatures.NETHER_MINESHAFT.configure(FeatureConfig.DEFAULT));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // JUNGLE FORTRESS //


    public static void addJungleFortress(Biome biome, String biomeNamespace, String biomePath) {
        if (RepurposedStructures.RSMainConfig.jungleFortress.jungleFortressSpawnrate != 1001 && biome.getCategory() == Category.JUNGLE &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMainConfig.jungleFortress.addJungleFortressToModdedBiomes)) {
            biome.addStructureFeature(RSFeatures.JUNGLE_FORTRESS.configure(FeatureConfig.DEFAULT));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DUNGEONS //


    public static void addDungeons(Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSDungeonsConfig.spawnrate.jungleDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.JUNGLE && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.JUNGLE_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.spawnrate.jungleDungeonSpawnrate,
                    RepurposedStructures.RSDungeonsConfig.minHeight.jungleDungeonMinHeight,
                    RepurposedStructures.RSDungeonsConfig.maxHeight.jungleDungeonMaxHeight);
        } else if (RepurposedStructures.RSDungeonsConfig.spawnrate.badlandsDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.MESA && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.SNOW_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.spawnrate.snowDungeonSpawnrate,
                    RepurposedStructures.RSDungeonsConfig.minHeight.snowDungeonMinHeight,
                    RepurposedStructures.RSDungeonsConfig.maxHeight.snowDungeonMaxHeight);
        } else if (RepurposedStructures.RSDungeonsConfig.spawnrate.darkForestDungeonSpawnrate != 0 &&
                biomePath.contains("dark_forest") && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.DARK_FOREST_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.spawnrate.darkForestDungeonSpawnrate,
                    RepurposedStructures.RSDungeonsConfig.minHeight.darkForestDungeonMinHeight,
                    RepurposedStructures.RSDungeonsConfig.maxHeight.darkForestDungeonMaxHeight);
        } else if (RepurposedStructures.RSDungeonsConfig.spawnrate.desertDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.DESERT && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.DESERT_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.spawnrate.desertDungeonSpawnrate,
                    RepurposedStructures.RSDungeonsConfig.minHeight.desertDungeonMinHeight,
                    RepurposedStructures.RSDungeonsConfig.maxHeight.desertDungeonMaxHeight);
        } else if (RepurposedStructures.RSDungeonsConfig.spawnrate.mushroomDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.MUSHROOM && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.SNOW_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.spawnrate.snowDungeonSpawnrate,
                    RepurposedStructures.RSDungeonsConfig.minHeight.snowDungeonMinHeight,
                    RepurposedStructures.RSDungeonsConfig.maxHeight.snowDungeonMaxHeight);
        } else if (RepurposedStructures.RSDungeonsConfig.spawnrate.swampDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.SWAMP && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.SWAMP_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.spawnrate.swampDungeonSpawnrate,
                    RepurposedStructures.RSDungeonsConfig.minHeight.swampDungeonMinHeight,
                    RepurposedStructures.RSDungeonsConfig.maxHeight.swampDungeonMaxHeight);
        } else if (RepurposedStructures.RSDungeonsConfig.spawnrate.snowDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.ICY && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            // replace vanilla dungeon with our own
            replaceOrAddDungeon(
                    true,
                    biome,
                    RSFeatures.SNOW_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.spawnrate.snowDungeonSpawnrate,
                    RepurposedStructures.RSDungeonsConfig.minHeight.snowDungeonMinHeight,
                    RepurposedStructures.RSDungeonsConfig.maxHeight.snowDungeonMaxHeight);
        } else if (RepurposedStructures.RSDungeonsConfig.spawnrate.netherDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.NETHER && dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            replaceOrAddDungeon(
                    false,
                    biome,
                    RSFeatures.NETHER_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.spawnrate.netherDungeonSpawnrate,
                    RepurposedStructures.RSDungeonsConfig.minHeight.netherDungeonMinHeight,
                    RepurposedStructures.RSDungeonsConfig.maxHeight.netherDungeonMaxHeight);
        } else if (RepurposedStructures.RSDungeonsConfig.spawnrate.endDungeonSpawnrate != 0 &&
                (biome.getCategory() == Category.THEEND && biome != Biomes.THE_END && biome != Biomes.SMALL_END_ISLANDS) &&
                dungeonAllowedByNamespaceAndConfigUA(biomeNamespace)) {

            replaceOrAddDungeon(
                    false,
                    biome,
                    RSFeatures.END_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.spawnrate.endDungeonSpawnrate,
                    RepurposedStructures.RSDungeonsConfig.minHeight.endDungeonMinHeight,
                    RepurposedStructures.RSDungeonsConfig.maxHeight.endDungeonMaxHeight);
        } else if (RepurposedStructures.RSDungeonsConfig.spawnrate.oceanDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.OCEAN && dungeonAllowedByNamespaceAndConfig(biomeNamespace)) {

            replaceOrAddDungeon(
                    false,
                    biome,
                    RSFeatures.OCEAN_DUNGEONS,
                    RepurposedStructures.RSDungeonsConfig.spawnrate.oceanDungeonSpawnrate,
                    RepurposedStructures.RSDungeonsConfig.minHeight.oceanDungeonMinHeight,
                    RepurposedStructures.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight);
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
        if (biomeNamespace.equals("minecraft") || RepurposedStructures.RSDungeonsConfig.addDungeonsToModdedBiomes) {
            return true;
        }

        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WELLS //


    public static void addWells(Biome biome, String biomeNamespace, String biomePath) {

        if (RepurposedStructures.RSWellsConfig.spawnrate.badlandsWellSpawnrate != 10000 &&
                biome.getCategory() == Category.MESA && wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSFeatures.BADLANDS_WELL.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(RepurposedStructures.RSWellsConfig.spawnrate.badlandsWellSpawnrate))));
        } else if (RepurposedStructures.RSWellsConfig.spawnrate.netherWellSpawnrate != 10000 &&
                biome.getCategory() == Category.NETHER && wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSFeatures.NETHER_WELL.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.CHANCE_RANGE.configure(new ChanceRangeDecoratorConfig(1F / RepurposedStructures.RSWellsConfig.spawnrate.netherWellSpawnrate, 30, 0, 98))));
        } else if (RepurposedStructures.RSWellsConfig.spawnrate.snowWellSpawnrate != 10000 &&
                (biome.getCategory() == Category.ICY || biomePath.contains("snow")) && wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSFeatures.SNOW_WELL.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(RepurposedStructures.RSWellsConfig.spawnrate.snowWellSpawnrate))));
        } else if (RepurposedStructures.RSWellsConfig.spawnrate.mossyStoneWellSpawnrate != 10000 &&
                (biome.getCategory() == Category.SWAMP || biome.getCategory() == Category.JUNGLE ||
                        biomePath.contains("dark_forest") || biomePath.contains("dark_oak")) &&
                wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSFeatures.MOSSY_STONE_WELL.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(RepurposedStructures.RSWellsConfig.spawnrate.mossyStoneWellSpawnrate))));
        } else if (RepurposedStructures.RSWellsConfig.spawnrate.forestWellSpawnrate != 10000 &&
                (biome.getCategory() == Category.FOREST && !(biomePath.contains("dark_forest") || biomePath.contains("dark_oak"))) &&
                wellAllowedByNamespaceAndConfig(biomeNamespace)) {

            biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSFeatures.FOREST_WELL.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(RepurposedStructures.RSWellsConfig.spawnrate.forestWellSpawnrate))));
        }
    }


    private static boolean wellAllowedByNamespaceAndConfig(String biomeNamespace) {
        if (biomeNamespace.equals("minecraft") || RepurposedStructures.RSWellsConfig.addWellsToModdedBiomes) {
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
        if (RepurposedStructures.RSMainConfig.misc.hornedSwampTree && !biomeNamespace.equals("ultra_amplified_dimension") && biome == Biomes.SWAMP_HILLS) {

            biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSFeatures.HORNED_SWAMP_TREE.configure(DefaultBiomeFeatures.SWAMP_TREE_CONFIG).createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(0, 0.7F, 1))));
        }


        // can exist in modded biomes too
        else if (RepurposedStructures.RSMainConfig.misc.hornedSwampTree && !biomeNamespace.equals("ultra_amplified_dimension") &&
                RepurposedStructures.RSMainConfig.misc.addMiscToModdedBiomes &&
                !biomeNamespace.equals("minecraft") && biome.getCategory() == Category.SWAMP) {

            // replace the swamp tree with our own
            biome.getFeaturesForStep(GenerationStep.Feature.VEGETAL_DECORATION).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && serializeAndCompareFeature(configuredFeature, VANILLA_SWAMP_TREE));
            biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSFeatures.HORNED_SWAMP_TREE.configure(DefaultBiomeFeatures.SWAMP_TREE_CONFIG).createDecoratedFeature(Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(2, 0.8F, 1))));
        } else if (RepurposedStructures.RSMainConfig.misc.boulderGiant && !biomeNamespace.equals("ultra_amplified_dimension") &&
                ((biome == Biomes.GIANT_SPRUCE_TAIGA_HILLS || biome == Biomes.GIANT_TREE_TAIGA_HILLS) ||
                        (RepurposedStructures.RSMainConfig.misc.addMiscToModdedBiomes && !biomeNamespace.equals("minecraft") &&
                                ((biomePath.contains("giant") && biomePath.contains("taiga")) || biomePath.contains("redwood"))))) {

            // replace the boulders with our own
            biome.getFeaturesForStep(GenerationStep.Feature.LOCAL_MODIFICATIONS).removeIf(configuredFeature -> configuredFeature.config instanceof DecoratedFeatureConfig && serializeAndCompareFeature(configuredFeature, VANILLA_BOULDER));
            biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, RSFeatures.BOULDER_GIANT.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.COUNT_TOP_SOLID.configure(new CountDecoratorConfig(2))));
        } else if (RepurposedStructures.RSMainConfig.misc.boulderTiny && !biomeNamespace.equals("ultra_amplified_dimension") &&
                ((biome == Biomes.SNOWY_TAIGA_MOUNTAINS || biome == Biomes.TAIGA_MOUNTAINS) ||
                        (RepurposedStructures.RSMainConfig.misc.addMiscToModdedBiomes && !biomeNamespace.equals("minecraft") && biomePath.contains("taiga")))) {

            biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, RSFeatures.BOULDER_TINY.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.COUNT_TOP_SOLID.configure(new CountDecoratorConfig(2))));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // NETHER TEMPLE //


    public static void addNetherTemple(Biome biome, String biomeNamespace, String biomePath) {
        if (RepurposedStructures.RSMainConfig.temples.netherTempleSpawnrate != 1001 && biome.getCategory() == Category.NETHER &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMainConfig.temples.addNetherTempleToModdedBiomes)) {
            biome.addStructureFeature(RSFeatures.NETHER_TEMPLE.configure(FeatureConfig.DEFAULT));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BADLANDS TEMPLE //


    public static void addBadlandsTemple(Biome biome, String biomeNamespace, String biomePath) {
        if (RepurposedStructures.RSMainConfig.temples.badlandsTempleSpawnrate != 1001 && biome.getCategory() == Category.MESA &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMainConfig.temples.addBadlandsTempleToModdedBiomes)) {
            biome.addStructureFeature(RSFeatures.BADLANDS_TEMPLE.configure(FeatureConfig.DEFAULT));
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // IGLOOS //


    public static void addIgloos(Biome biome, String biomeNamespace, String biomePath) {
        if (RepurposedStructures.RSMainConfig.igloos.grassyIglooSpawnrate != 1001) {
            if ((biome.getCategory() == Category.FOREST || biome.getCategory() == Category.PLAINS) &&
                    (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMainConfig.igloos.addGrassyIglooToModdedBiomes)) {
                biome.addStructureFeature(RSFeatures.GRASSY_IGLOO.configure(FeatureConfig.DEFAULT));
            }
        }

        if (RepurposedStructures.RSMainConfig.igloos.stoneIglooSpawnrate != 1001) {
            String BiomeName = Registry.BIOME.getId(biome).getPath();
            if ((biome.getCategory() == Category.TAIGA && BiomeName.contains("giant")) &&
                    (biomeNamespace.equals("minecraft") || RepurposedStructures.RSMainConfig.igloos.addStoneIglooToModdedBiomes)) {
                biome.addStructureFeature(RSFeatures.STONE_IGLOO.configure(FeatureConfig.DEFAULT));
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // VILLAGES //


    public static void addVillages(Biome biome, String biomeNamespace, String biomePath) {
        if ((biome.getCategory() == Category.MESA && !biomePath.contains("plateau")) &&
                (biomeNamespace.equals("minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSVillagesConfig.badlandsVillageSpawnrate != 1001) {
                biome.addStructureFeature(RSFeatures.BADLANDS_VILLAGE.configure(FeatureConfig.DEFAULT));
            }
        }

        else if (biomePath.contains("birch") && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSVillagesConfig.birchVillageSpawnrate != 1001) {
                biome.addStructureFeature(RSFeatures.BIRCH_VILLAGE.configure(FeatureConfig.DEFAULT));
            }
        }

        else if (biomePath.contains("dark_forest") && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSVillagesConfig.darkForestVillageSpawnrate != 1001) {
                biome.addStructureFeature(RSFeatures.DARK_FOREST_VILLAGE.configure(FeatureConfig.DEFAULT));
            }
        }

        else if (biome.getCategory() == Category.JUNGLE && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSVillagesConfig.jungleVillageSpawnrate != 1001) {
                biome.addStructureFeature(RSFeatures.JUNGLE_VILLAGE.configure(FeatureConfig.DEFAULT));
            }
        }

        else if (biome.getCategory() == Category.SWAMP && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSVillagesConfig.swampVillageSpawnrate != 1001) {
                biome.addStructureFeature(RSFeatures.SWAMP_VILLAGE.configure(FeatureConfig.DEFAULT));
            }
        }

        else if (biome.getCategory() == Category.EXTREME_HILLS && (biomeNamespace.equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSVillagesConfig.mountainsVillageSpawnrate != 1001) {
                biome.addStructureFeature(RSFeatures.MOUNTAINS_VILLAGE.configure(FeatureConfig.DEFAULT));
            }
        }

        else if ((biome == Biomes.GIANT_SPRUCE_TAIGA || biome == Biomes.GIANT_TREE_TAIGA) ||
                (RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes &&
                        ((biomePath.contains("giant") && biomePath.contains("taiga")) || biomePath.contains("redwood")))) {
            if (RepurposedStructures.RSVillagesConfig.giantTaigaVillageSpawnrate != 1001) {
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
        try {
            //	    Map<DynamicOps<Tag>, DynamicOps<Tag>> feature1Map = feature1.CODEC.serialize(NbtOps.INSTANCE).getMapValues();
            //	    Map<DynamicOps<Tag>, DynamicOps<Tag>> feature2Map = feature2.serialize(NbtOps.INSTANCE).getMapValues();
            //
            //	    if (feature1Map != null && feature2Map != null) {
            //		return feature1Map.equals(feature2Map);
            //	    }
        } catch (Exception e) {
            // One of the features cannot be serialized which can only happen with custom modded features
            // Check if the features are the same feature even though the placement or config for the feature might be different.
            // This is the best way we can remove duplicate modded features as best as we can. (I think)
            if ((feature1.config instanceof DecoratedFeatureConfig && feature2.config instanceof DecoratedFeatureConfig) &&
                    ((DecoratedFeatureConfig) feature1.config).feature.feature == ((DecoratedFeatureConfig) feature2.config).feature.feature) {
                return true;
            }
        }

        return false;
    }
}
