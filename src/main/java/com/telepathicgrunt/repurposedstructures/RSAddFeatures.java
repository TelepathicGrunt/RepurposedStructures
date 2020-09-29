package com.telepathicgrunt.repurposedstructures;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiPredicate;

public class RSAddFeatures {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MINESHAFTS //

    private static final Map<ConfiguredStructureFeature<?, ?>, BiPredicate<Identifier, Biome>> MINESHAFT_TYPE_AND_CONDITIONS = new HashMap<>();
    static {
        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.BIRCH_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.birchMineshaftSpawnrate != 0 &&
                biomeIDIn.getPath().contains("birch")));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.JUNGLE_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.jungleMineshaftSpawnrate != 0 &&
                biomeIn.getCategory() == Category.JUNGLE));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.DESERT_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.desertMineshaftSpawnrate != 0 &&
                biomeIn.getCategory() == Category.DESERT));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.STONE_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.stoneMineshaftSpawnrate != 0 &&
                biomeIn.getCategory() == Category.EXTREME_HILLS));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.SAVANNA_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.savannaMineshaftSpawnrate != 0 &&
                biomeIn.getCategory() == Category.SAVANNA));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.ICY_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.icyMineshaftSpawnrate != 0 &&
                (biomeIn.getCategory() == Category.ICY || biomeIDIn.getPath().contains("snowy"))));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.OCEAN_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.oceanMineshaftSpawnrate != 0 &&
                biomeIn.getCategory() == Category.OCEAN));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.TAIGA_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.taigaMineshaftSpawnrate != 0 &&
                biomeIn.getCategory() == Category.TAIGA));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.SWAMP_OR_DARK_FOREST_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.swampAndDarkForestMineshaftSpawnrate != 0 &&
                (biomeIn.getCategory() == Category.SWAMP || biomeIDIn.getPath().contains("dark_forest") || biomeIDIn.getPath().contains("dark_oak"))));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.END_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.endMineshaftSpawnrate != 0 &&
                biomeIn.getCategory() == Category.THEEND && !biomeIDIn.equals(new Identifier("minecraft:the_end")) &&
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.misc.barrensIslandsEndMineshafts ||
                (!biomeIDIn.equals(new Identifier("minecraft:end_barrens")) && !biomeIDIn.equals(new Identifier("minecraft:small_end_islands"))))));

        MINESHAFT_TYPE_AND_CONDITIONS.put(RSConfiguredStructures.NETHER_MINESHAFT, (biomeIDIn, biomeIn) ->
                (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.netherMineshaftSpawnrate != 0 && biomeIn.getCategory() == Category.NETHER));
    }


    public static void addMineshafts(Biome biome, Identifier biomeID) {
        // Testing out this short circuit idea I had.
        // Takes a condition and tries to add the mineshaft.
        // If fails, move on to next Mineshaft type. If it succeeds, stop and exit method.
        for(Map.Entry<ConfiguredStructureFeature<?, ?>, BiPredicate<Identifier, Biome>> mineshaftTypeAndCondition : MINESHAFT_TYPE_AND_CONDITIONS.entrySet()){
            if(attemptToAddMineshaft(biome, biomeID, mineshaftTypeAndCondition.getKey(), mineshaftTypeAndCondition.getValue())){
                break;
            }
        }
    }

    // helper stuff to allow a cleaner way of passing in mineshaft types
    // and their conditions to see if they should be added to the biome
    private static boolean attemptToAddMineshaft(Biome biome, Identifier biomeID, ConfiguredStructureFeature<?,?> configuredStructureFeature, BiPredicate<Identifier, Biome> predicate){
        if (predicate.test(biomeID, biome) &&
                (biomeID.getNamespace().equals("minecraft") ||
                 RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes))
        {
            // replace vanilla mineshaft with our own (only removes vanilla mineshaft if it exists)
            // Do not remove when yungs is on. They need the mineshaft to replace it.
            if (RepurposedStructures.yungsBetterMineshaftIsNotOn){
                biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == StructureFeature.MINESHAFT);
            }
            biome.getGenerationSettings().getStructureFeatures().add(() -> configuredStructureFeature);
            return true; // Do not proceed to next mineshaft type.
        }
        return false; // continue to next mineshaft type
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DUNGEONS //

    public static void addDungeons(Biome biome, Identifier biomeID) {

        if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.jungleDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.JUNGLE && dungeonAllowedByNamespaceAndConfigUA(biomeID.getNamespace())) {
            replaceOrAddDungeon(true, biome, RSConfiguredFeatures.JUNGLE_DUNGEONS);
        }
        else if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.badlandsDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.MESA && dungeonAllowedByNamespaceAndConfigUA(biomeID.getNamespace())) {
            replaceOrAddDungeon(true, biome, RSConfiguredFeatures.BADLANDS_DUNGEONS);
        }
        else if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.darkForestDungeonSpawnrate != 0 &&
                biomeID.getPath().contains("dark_forest") && dungeonAllowedByNamespaceAndConfigUA(biomeID.getNamespace())) {
            replaceOrAddDungeon(true, biome, RSConfiguredFeatures.DARK_FOREST_DUNGEONS);
        }
        else if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.desertDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.DESERT && dungeonAllowedByNamespaceAndConfigUA(biomeID.getNamespace())) {
            replaceOrAddDungeon(true, biome, RSConfiguredFeatures.DESERT_DUNGEONS);
        }
        else if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.mushroomDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.MUSHROOM && dungeonAllowedByNamespaceAndConfigUA(biomeID.getNamespace())) {
            replaceOrAddDungeon(true, biome, RSConfiguredFeatures.MUSHROOM_DUNGEONS);
        }
        else if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.swampDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.SWAMP && dungeonAllowedByNamespaceAndConfigUA(biomeID.getNamespace())) {
            replaceOrAddDungeon(true, biome, RSConfiguredFeatures.SWAMP_DUNGEONS);
        }
        else if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.snowDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.ICY && dungeonAllowedByNamespaceAndConfigUA(biomeID.getNamespace())) {
            replaceOrAddDungeon(true, biome, RSConfiguredFeatures.SNOW_DUNGEONS);
        }
        else if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.netherDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.NETHER && dungeonAllowedByNamespaceAndConfigUA(biomeID.getNamespace())) {
            replaceOrAddDungeon(false, biome, RSConfiguredFeatures.NETHER_DUNGEONS);
        }
        else if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.endDungeonSpawnrate != 0 &&
                (biome.getCategory() == Category.THEEND &&
                        !biomeID.equals(new Identifier("minecraft:the_end")) &&
                        !biomeID.equals(new Identifier("minecraft:small_end_islands"))) &&
                dungeonAllowedByNamespaceAndConfigUA(biomeID.getNamespace())) {
            replaceOrAddDungeon(false, biome, RSConfiguredFeatures.END_DUNGEONS);
        }
        else if (RepurposedStructures.RSAllConfig.RSDungeonsConfig.spawnrate.oceanDungeonSpawnrate != 0 &&
                biome.getCategory() == Category.OCEAN && dungeonAllowedByNamespaceAndConfig(biomeID.getNamespace())) {
            replaceOrAddDungeon(false, biome, RSConfiguredFeatures.OCEAN_DUNGEONS);
        }
    }


    /**
     * Adds RS's dungeon to the biome along with option to remove vanilla dungeon as well.
     */
    private static void replaceOrAddDungeon(boolean replacing, Biome biome, ConfiguredFeature<?, ?> rsDungeon) {

        // Remove vanilla dungeon no matter the form
        if (replacing) {
            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.UNDERGROUND_STRUCTURES.ordinal())
                .removeIf(supplier ->
                    supplier.get().config instanceof DecoratedFeatureConfig &&
                    ((DecoratedFeatureConfig)supplier.get().config).feature.get().config instanceof DecoratedFeatureConfig &&
                    ((DecoratedFeatureConfig)((DecoratedFeatureConfig)supplier.get().config).feature.get().config).feature.get().config instanceof DecoratedFeatureConfig &&
                    ((DecoratedFeatureConfig)((DecoratedFeatureConfig)((DecoratedFeatureConfig)supplier.get().config).feature.get().config).feature.get().config).feature.get().feature == Feature.MONSTER_ROOM);
        }

        // Add given dungeon
        biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.UNDERGROUND_STRUCTURES.ordinal()).add(() -> rsDungeon);
    }


    /**
     * Will not return true for Ultra Amplified Dimension's biomes as that mod already has the dungeon type.
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
        return biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSDungeonsConfig.addDungeonsToModdedBiomes;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WELLS //

    public static void addWells(Biome biome, Identifier biomeID) {

        if (RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.badlandsWellSpawnrate != 10000 &&
                biome.getCategory() == Category.MESA && wellAllowedByNamespaceAndConfig(biomeID.getNamespace())) {
            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSConfiguredFeatures.BADLANDS_WELL);
        }

        else if (RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.netherWellSpawnrate != 10000 &&
                biome.getCategory() == Category.NETHER && wellAllowedByNamespaceAndConfig(biomeID.getNamespace())) {

            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSConfiguredFeatures.NETHER_WELL);
        }

        else if (RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.snowWellSpawnrate != 10000 &&
                (biome.getCategory() == Category.ICY || biomeID.getPath().contains("snow")) && wellAllowedByNamespaceAndConfig(biomeID.getNamespace())) {

            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSConfiguredFeatures.SNOW_WELL);
        }

        else if (RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.mossyStoneWellSpawnrate != 10000 &&
                (biome.getCategory() == Category.SWAMP || biome.getCategory() == Category.JUNGLE ||
                        biomeID.getPath().contains("dark_forest") || biomeID.getPath().contains("dark_oak")) &&
                wellAllowedByNamespaceAndConfig(biomeID.getNamespace())) {

            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSConfiguredFeatures.MOSSY_STONE_WELL);
        }

        else if (RepurposedStructures.RSAllConfig.RSWellsConfig.spawnrate.forestWellSpawnrate != 10000 &&
                (biome.getCategory() == Category.FOREST && !(biomeID.getPath().contains("dark_forest") || biomeID.getPath().contains("dark_oak"))) &&
                wellAllowedByNamespaceAndConfig(biomeID.getNamespace())) {

            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.SURFACE_STRUCTURES.ordinal())
                    .add(() -> RSConfiguredFeatures.FOREST_WELL);
        }
    }


    private static boolean wellAllowedByNamespaceAndConfig(String biomeNamespace) {
        return biomeNamespace.equals("minecraft") || RepurposedStructures.RSAllConfig.RSWellsConfig.addWellsToModdedBiomes;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SWAMP TREE FEATURES //

    
    public static void addSwampTreeFeatures(Biome biome, Identifier biomeID) {

        // Exists in vanilla Swamp and can be in modded swamp biomes
        if (RepurposedStructures.RSAllConfig.RSMainConfig.misc.hornedSwampTree &&
                (biomeID.equals(new Identifier("minecraft:swamp")) ||
                    (RepurposedStructures.RSAllConfig.RSMainConfig.misc.addSwampTreeToModdedBiomes &&
                        biome.getCategory() == Category.SWAMP &&
                        !biomeID.getNamespace().equals("ultra_amplified_dimension") &&
                        !biomeID.getNamespace().equals("minecraft")))){

            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.VEGETAL_DECORATION.ordinal())
                    .add(() -> RSConfiguredFeatures.HORNED_SWAMP_TREE_UNCOMMON);
        }

        // Only exists in vanilla Swamp Hills biomes
        else if (RepurposedStructures.RSAllConfig.RSMainConfig.misc.hornedSwampTree &&
                (biomeID.equals(new Identifier("minecraft:swamp_hills")))) {

            // replace the swamp tree with our own
            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.VEGETAL_DECORATION.ordinal())
                    .removeIf(configuredFeature -> configuredFeature.get().config instanceof DecoratedFeatureConfig &&
                            serializeAndCompareFeature(configuredFeature.get(), ConfiguredFeatures.SWAMP_TREE));

            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.VEGETAL_DECORATION.ordinal())
                    .add(() -> RSConfiguredFeatures.HORNED_SWAMP_TREE_COMMON);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MISC FEATURES //

    public static void addBoulderFeatures(Biome biome, Identifier biomeID) {

        if (RepurposedStructures.RSAllConfig.RSMainConfig.misc.boulderGiant &&
                ((biomeID.equals(new Identifier("minecraft:giant_spruce_taiga_hills")) || biomeID.equals(new Identifier("minecraft:giant_tree_taiga_hills"))) ||
                        (RepurposedStructures.RSAllConfig.RSMainConfig.misc.addBoulderToModdedBiomes && !biomeID.getNamespace().equals("minecraft") &&
                                ((biomeID.getPath().contains("giant") && biomeID.getPath().contains("taiga")) || biomeID.getPath().contains("redwood"))))){

            // replace the boulders with our own
            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.LOCAL_MODIFICATIONS.ordinal())
                    .removeIf(configuredFeature -> configuredFeature.get().config instanceof DecoratedFeatureConfig &&
                            serializeAndCompareFeature(configuredFeature.get(), ConfiguredFeatures.FOREST_ROCK));

            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.LOCAL_MODIFICATIONS.ordinal())
                    .add(() -> RSConfiguredFeatures.BOULDER_GIANT);
        }

        else if (RepurposedStructures.RSAllConfig.RSMainConfig.misc.boulderTiny &&
                ((biomeID.equals(new Identifier("minecraft:snowy_taiga_mountains")) || biomeID.equals(new Identifier("minecraft:taiga_mountains"))) ||
                        (RepurposedStructures.RSAllConfig.RSMainConfig.misc.addBoulderToModdedBiomes && !biomeID.getNamespace().equals("minecraft") &&
                                biomeID.getPath().contains("taiga") && (biomeID.getPath().contains("mountain") || biomeID.getPath().contains("hill"))))) {

            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.LOCAL_MODIFICATIONS.ordinal())
                    .add(() -> RSConfiguredFeatures.BOULDER_TINY);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // STRONGHOLDS //

    public static void addStrongholds(Biome biome, Identifier biomeID) {

        if (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.allowStonebrickStronghold &&
                RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdSpawnrate != 1001 &&
                biome.getCategory() != Category.NETHER &&
                (biome.getGenerationSettings().hasStructureFeature(StructureFeature.STRONGHOLD) ||
                        (!biomeID.getNamespace().equals("minecraft") && RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.addStonebrickStrongholdToModdedBiomes))) {

            //replace vanilla stronghold with ours if vanilla's is present
            biome.getGenerationSettings().getStructureFeatures().removeIf((supplier) -> supplier.get().feature == StructureFeature.STRONGHOLD);
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.STONEBRICK_STRONGHOLD);
            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.UNDERGROUND_DECORATION.ordinal())
                    .add(() -> RSConfiguredFeatures.STONEBRICK_STRONGHOLD_CHAINS);
        }

        else if (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSpawnrate != 1001 && biome.getCategory() == Category.NETHER &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.addNetherStrongholdToModdedBiomes)) {

            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.NETHER_STRONGHOLD);
            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.UNDERGROUND_DECORATION.ordinal())
                    .add(() -> RSConfiguredFeatures.NETHER_STRONGHOLD_CHAINS);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OUTPOSTS //

    public static void addOutposts(Biome biome, Identifier biomeID) {

        //Nether based Outposts
        if(biome.getCategory() == Category.NETHER)
        {
            if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.crimsonOutpostSpawnrate != 1001 &&
                    biomeID.getPath().contains("crimson") &&
                    (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addCrimsonOutpostToModdedBiomes)) {

                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.CRIMSON_OUTPOST);
            }

            else if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.warpedOutpostSpawnrate != 1001 &&
                    biomeID.getPath().contains("warped") &&
                    (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addWarpedOutpostToModdedBiomes)) {

                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.WARPED_OUTPOST);
            }

            else if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.netherBrickOutpostSpawnrate != 1001 &&
                    (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addNetherBrickOutpostToModdedBiomes)) {

                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.NETHER_BRICK_OUTPOST);
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OUTPOSTS //

    public static void addShipwrecks(Biome biome, Identifier biomeID) {

        if (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.spawnrate.endShipwreckSpawnrate != 1001 &&
                (biomeID.equals(new Identifier("minecraft:end_highlands")) ||
                (!biomeID.getNamespace().equals("minecraft") && biome.getCategory() == Category.THEEND &&
                RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.addEndShipwreckToModdedBiomes))) {

            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.END_SHIPWRECK);
        }

        //Nether based Shipwrecks
        if(biome.getCategory() == Category.NETHER)
        {
            if (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.spawnrate.crimsonShipwreckSpawnrate != 1001 &&
                    biomeID.getPath().contains("crimson") &&
                    (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.addCrimsonShipwreckToModdedBiomes)) {

                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.CRIMSON_SHIPWRECK);
            }

            else if (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.spawnrate.warpedShipwreckSpawnrate != 1001 &&
                    biomeID.getPath().contains("warped") &&
                    (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.addCrimsonShipwreckToModdedBiomes)) {

                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.WARPED_SHIPWRECK);
            }

            else if (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.spawnrate.netherBricksShipwreckSpawnrate != 1001 &&
                    (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.addNetherBricksShipwreckToModdedBiomes)) {

                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.NETHER_BRICKS_SHIPWRECK);
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // JUNGLE FORTRESS //

    public static void addJungleFortress(Biome biome, Identifier biomeID) {
        if(RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.jungleFortressSpawnrate != 1001)
        {
            if ( biome.getCategory() == Category.JUNGLE &&
                    (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.addJungleFortressToModdedBiomes)) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.JUNGLE_FORTRESS);
            }

            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.VEGETAL_DECORATION.ordinal())
                    .add(() -> RSConfiguredFeatures.JUNGLE_FORTRESS_VINES);

            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.UNDERGROUND_ORES.ordinal())
                    .add(() -> RSConfiguredFeatures.FORTRESS_BREAKAGE);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TEMPLES //

    public static void addTemples(Biome biome, Identifier biomeID) {

        if (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherBasaltTempleSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER && (biomeID.getPath().contains("basalt") || biomeID.getPath().contains("blackstone")) &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherBasaltTempleToModdedBiomes)) {

            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.NETHER_BASALT_TEMPLE);
        }

        else if (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherCrimsonTempleSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER && biomeID.getPath().contains("crimson") &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherCrimsonTempleToModdedBiomes)) {

            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.NETHER_CRIMSON_TEMPLE);
        }

        else if (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWarpedTempleSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER && biomeID.getPath().contains("warped") &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherWarpedTempleToModdedBiomes)) {

            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.NETHER_WARPED_TEMPLE);
        }

        else if (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherSoulTempleSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER && biomeID.getPath().contains("soul") &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherSoulTempleToModdedBiomes)) {

            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.NETHER_SOUL_TEMPLE);
        }

        else if (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWastelandTempleSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherWastelandTempleToModdedBiomes)) {

            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.NETHER_WASTELAND_TEMPLE);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Pyramids //

    public static void addPyramids(Biome biome, Identifier biomeID) {

        if (RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.netherPyramidSpawnrate != 1001 && biome.getCategory() == Category.NETHER &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.addNetherPyramidToModdedBiomes)) {

            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.NETHER_PYRAMID);
        }

        if (RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.badlandsPyramidSpawnrate != 1001 && biome.getCategory() == Category.MESA &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.addBadlandsPyramidToModdedBiomes)) {

            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.BADLANDS_TEMPLE);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // IGLOOS //

    public static void addIgloos(Biome biome, Identifier biomeID) {
        if (RepurposedStructures.RSAllConfig.RSMainConfig.igloos.grassyIglooSpawnrate != 1001) {
            if ((biome.getCategory() == Category.FOREST || biome.getCategory() == Category.PLAINS) &&
                    (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSMainConfig.igloos.addGrassyIglooToModdedBiomes)) {

                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.GRASSY_IGLOO);
            }
        }

        if (RepurposedStructures.RSAllConfig.RSMainConfig.igloos.stoneIglooSpawnrate != 1001) {
            if ((biome.getCategory() == Category.TAIGA && biomeID.getPath().contains("giant")) &&
                    (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSMainConfig.igloos.addStoneIglooToModdedBiomes)) {

                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.STONE_IGLOO);
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // VILLAGES //

    public static void addVillages(Biome biome, Identifier biomeID) {
        if ((biome.getCategory() == Category.MESA && !biomeID.getPath().contains("plateau")) &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.badlandsVillageSpawnrate != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.BADLANDS_VILLAGE);
            }
        }

        else if (biomeID.getPath().contains("birch") && (biomeID.getNamespace().equals("minecraft") ||
                RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.birchVillageSpawnrate != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.BIRCH_VILLAGE);
            }
        }

        else if (biomeID.getPath().contains("dark_forest") && (biomeID.getNamespace().equals("minecraft") ||
                RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.darkForestVillageSpawnrate != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.DARK_FOREST_VILLAGE);
            }
        }

        else if (biome.getCategory() == Category.JUNGLE && (biomeID.getNamespace().equals("minecraft") ||
                RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.jungleVillageSpawnrate != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.JUNGLE_VILLAGE);
                biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.VEGETAL_DECORATION.ordinal())
                        .add(() -> RSConfiguredFeatures.JUNGLE_VILLAGE_VINES);
            }
        }

        else if (biome.getCategory() == Category.SWAMP && (biomeID.getNamespace().equals("minecraft") ||
                RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.swampVillageSpawnrate != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.SWAMP_VILLAGE);
                biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.VEGETAL_DECORATION.ordinal())
                        .add(() -> RSConfiguredFeatures.SWAMP_VILLAGE_VINES);
            }
        }

        else if (biome.getCategory() == Category.EXTREME_HILLS && (biomeID.getNamespace().equals("minecraft") ||
                RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.mountainsVillageSpawnrate != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.MOUNTAINS_VILLAGE);
            }
        }

        else if ((biomeID.equals(new Identifier("minecraft:giant_spruce_taiga")) || biomeID.equals(new Identifier("minecraft:giant_tree_taiga"))) ||
                (!biomeID.getNamespace().equals("minecraft") &&
                  RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes &&
                ((biomeID.getPath().contains("giant") && biomeID.getPath().contains("taiga")) || biomeID.getPath().contains("redwood")))) {
            if (RepurposedStructures.RSAllConfig.RSVillagesConfig.giantTaigaVillageSpawnrate != 1001) {
                biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.GIANT_TAIGA_VILLAGE);
            }
        }
        else if (RepurposedStructures.RSAllConfig.RSVillagesConfig.crimsonVillageSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER && biomeID.getPath().contains("crimson") &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
            biome.getGenerationSettings().getStructureFeatures().add(() -> RSConfiguredStructures.CRIMSON_VILLAGE);
        }
        else if (RepurposedStructures.RSAllConfig.RSVillagesConfig.warpedVillageSpawnrate != 1001 &&
                biome.getCategory() == Category.NETHER && biomeID.getPath().contains("warped") &&
                (biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)) {
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
        if(!configuredFeatureJSON1.isPresent() || !configuredFeatureJSON2.isPresent()){
            if ((configuredFeature1.config instanceof DecoratedFeatureConfig && configuredFeature2.config instanceof DecoratedFeatureConfig) &&
                    ((DecoratedFeatureConfig) configuredFeature1.config).feature.get().feature == ((DecoratedFeatureConfig) configuredFeature2.config).feature.get().feature) {
                return true;
            }
        }

        // Compare the JSON to see if it's the same ConfiguredFeature in the end.
        return configuredFeatureJSON1.equals(configuredFeatureJSON2);
    }

}
