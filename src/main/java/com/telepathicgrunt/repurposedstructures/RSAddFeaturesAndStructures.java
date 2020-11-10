package com.telepathicgrunt.repurposedstructures;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RSAddFeaturesAndStructures {


    public static void addDimensionalSpacing(final WorldEvent.Load event) {
        //add our structure spacing to all chunkgenerators including modded one and datapack ones.
        List<String> dimensionBlacklist = Arrays.stream(RepurposedStructures.RSMainConfig.blacklistedDimensions.get().split(",")).map(String::trim).collect(Collectors.toList());

        if (event.getWorld() instanceof ServerWorld){
            ServerWorld serverWorld = (ServerWorld) event.getWorld();

            // Need temp map as some mods use custom chunk generators with immutable maps in themselves.
            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkProvider().generator.getStructuresConfig().getStructures());
            if(dimensionBlacklist.stream().anyMatch(blacklist -> blacklist.equals((serverWorld.getRegistryKey().getValue().toString())))
                || (serverWorld.getChunkProvider().getChunkGenerator() instanceof FlatChunkGenerator && serverWorld.getRegistryKey().equals(World.OVERWORLD)))
            {
                // make absolutely sure dimension cannot spawn RS structures
                tempMap.keySet().removeAll(RSStructures.RS_STRUCTURES.keySet());
            }
            else{
                // make absolutely sure dimension can spawn RS structures
                tempMap.putAll(RSStructures.RS_STRUCTURES);
            }
            serverWorld.getChunkProvider().generator.getStructuresConfig().structures = tempMap;

            // Load up the nbt files for several structures at startup instead of during worldgen.
            // (Yes ik this fires multiple times but this event is the closest to what I need
            //  before actual worldgen and after dynamicregistries are made... I think?)
            for(ResourceLocation identifier : RSStructures.RS_STRUCTURE_START_PIECES){
                JigsawPattern structurePool = serverWorld.getRegistryManager().get(Registry.TEMPLATE_POOL_WORLDGEN).getOrDefault(identifier);
                if(structurePool != null){
                    List<JigsawPiece> elements = structurePool.getShuffledPieces(new Random());
                    for(JigsawPiece element: elements){
                        // This loads the structure piece to nbt
                        element.getBoundingBox(serverWorld.getStructureTemplateManager(), new BlockPos(0,0,0), Rotation.NONE);
                    }
                }
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MINESHAFTS //

    public static void addMineshafts(BiomeLoadingEvent event) {
        // Testing out this short circuit idea I had.
        // Takes a condition and tries to add the mineshaft.
        // If fails, move on to next Mineshaft type. If it succeeds, stop and exit method.
        for(Map.Entry<StructureFeature<?, ?>, Predicate<BiomeLoadingEvent>> mineshaftTypeAndCondition : RSStructureFilterMaps.MINESHAFT_TYPE_AND_CONDITIONS.entrySet()){
            if(attemptToAddMineshaft(event, mineshaftTypeAndCondition.getKey(), mineshaftTypeAndCondition.getValue())){
                break;
            }
        }
    }

    // helper stuff to allow a cleaner way of passing in mineshaft types
    // and their conditions to see if they should be added to the biome
    private static boolean attemptToAddMineshaft(BiomeLoadingEvent event, StructureFeature<?,?> configuredStructureFeature, Predicate<BiomeLoadingEvent> predicate){
        if (predicate.test(event) &&
                (event.getName().getNamespace().equals("minecraft") ||
                        RepurposedStructures.RSMineshaftsConfig.addMineshaftsToModdedBiomes.get()))
        {
            // replace vanilla mineshaft with our own (only removes vanilla mineshaft if it exists)
            // Do not remove when yungs is on. They need the mineshaft to replace it.
            if (RepurposedStructures.yungsBetterMineshaftIsNotOn){
                event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature == Structure.MINESHAFT);
            }
            event.getGeneration().getStructures().add(() -> configuredStructureFeature);
            return true; // Do not proceed to next mineshaft type.
        }
        return false; // continue to next mineshaft type
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DUNGEONS //

    public static void addDungeons(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSDungeonsConfig.jungleDungeonAttemptsPerChunk.get() != 0 &&
                event.getCategory() == Category.JUNGLE && dungeonAllowedByNamespaceAndConfig(event.getName())) {

            replaceOrAddDungeon(true, event, RSConfiguredFeatures.JUNGLE_DUNGEONS);
        }
        else if (RepurposedStructures.RSDungeonsConfig.badlandsDungeonAttemptsPerChunk.get() != 0 &&
                event.getCategory() == Category.MESA && dungeonAllowedByNamespaceAndConfig(event.getName())) {

            replaceOrAddDungeon(true, event, RSConfiguredFeatures.BADLANDS_DUNGEONS);
        }
        else if (RepurposedStructures.RSDungeonsConfig.darkForestDungeonAttemptsPerChunk.get() != 0 &&
                event.getName().getPath().contains("dark_forest") && dungeonAllowedByNamespaceAndConfig(event.getName())) {

            replaceOrAddDungeon(true, event, RSConfiguredFeatures.DARK_FOREST_DUNGEONS);
        }
        else if (RepurposedStructures.RSDungeonsConfig.desertDungeonAttemptsPerChunk.get() != 0 &&
                event.getCategory() == Category.DESERT && dungeonAllowedByNamespaceAndConfig(event.getName())) {

            replaceOrAddDungeon(true, event, RSConfiguredFeatures.DESERT_DUNGEONS);
        }
        else if (RepurposedStructures.RSDungeonsConfig.mushroomDungeonAttemptsPerChunk.get() != 0 &&
                event.getCategory() == Category.MUSHROOM && dungeonAllowedByNamespaceAndConfig(event.getName())) {

            replaceOrAddDungeon(true, event, RSConfiguredFeatures.MUSHROOM_DUNGEONS);
        }
        else if (RepurposedStructures.RSDungeonsConfig.swampDungeonAttemptsPerChunk.get() != 0 &&
                event.getCategory() == Category.SWAMP && dungeonAllowedByNamespaceAndConfig(event.getName())) {

            replaceOrAddDungeon(true, event, RSConfiguredFeatures.SWAMP_DUNGEONS);
        }
        else if (RepurposedStructures.RSDungeonsConfig.snowDungeonAttemptsPerChunk.get() != 0 &&
                event.getCategory() == Category.ICY && dungeonAllowedByNamespaceAndConfig(event.getName())) {

            replaceOrAddDungeon(true, event, RSConfiguredFeatures.SNOW_DUNGEONS);
        }
        else if (RepurposedStructures.RSDungeonsConfig.netherDungeonAttemptsPerChunk.get() != 0 &&
                event.getCategory() == Category.NETHER && dungeonAllowedByNamespaceAndConfig(event.getName())) {

            replaceOrAddDungeon(false, event, RSConfiguredFeatures.NETHER_DUNGEONS);
        }
        else if (RepurposedStructures.RSDungeonsConfig.endDungeonAttemptsPerChunk.get() != 0 &&
                (event.getCategory() == Category.THEEND &&
                        !event.getName().equals(new ResourceLocation("minecraft:the_end")) &&
                        !event.getName().equals(new ResourceLocation("minecraft:small_end_islands"))) &&
                dungeonAllowedByNamespaceAndConfig(event.getName())) {

            replaceOrAddDungeon(false, event, RSConfiguredFeatures.END_DUNGEONS);
        }
        else if (RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get() != 0 &&
                event.getCategory() == Category.OCEAN && dungeonAllowedByNamespaceAndConfig(event.getName())) {

            replaceOrAddDungeon(false, event, RSConfiguredFeatures.OCEAN_DUNGEONS);
        }
    }


    /**
     * Adds RS's dungeon to the biome along with option to remove vanilla dungeon as well.
     */
    private static void replaceOrAddDungeon(boolean replacing, BiomeLoadingEvent event, ConfiguredFeature<?, ?> rsDungeon) {

        //remove vanilla dungeon
        if (replacing) {
            event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(supplier -> supplier.get().feature.equals(Features.MONSTER_ROOM.feature));
        }

        //add given dungeon
        event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).add(() -> rsDungeon);
    }
    

    /**
     * Will check if the dungeon is allowed in modded biomes based on config but will always return true for vanilla biomes.
     */
    private static boolean dungeonAllowedByNamespaceAndConfig(ResourceLocation biomeID) {
        return biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSDungeonsConfig.addDungeonsToModdedBiomes.get();
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WELLS //

    public static void addWells(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSWellsConfig.badlandsWellRarityPerChunk.get() != 10000 &&
                event.getCategory() == Category.MESA && wellAllowedByNamespaceAndConfig(event.getName())) {

            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.BADLANDS_WELL);
        }
        else if (RepurposedStructures.RSWellsConfig.netherWellRarityPerChunk.get() != 10000 &&
                event.getCategory() == Category.NETHER && wellAllowedByNamespaceAndConfig(event.getName())) {

            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.NETHER_WELL);
        }
        else if (RepurposedStructures.RSWellsConfig.snowWellRarityPerChunk.get() != 10000 &&
                (event.getCategory() == Category.ICY || event.getName().getPath().contains("snow")) && wellAllowedByNamespaceAndConfig(event.getName())) {

            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.SNOW_WELL);
        }
        else if (RepurposedStructures.RSWellsConfig.mossyStoneWellRarityPerChunk.get() != 10000 &&
                (event.getCategory() == Category.SWAMP || event.getCategory() == Category.JUNGLE ||
                        event.getName().getPath().contains("dark_forest") || event.getName().getPath().contains("dark_oak")) &&
                wellAllowedByNamespaceAndConfig(event.getName())) {

            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.MOSSY_STONE_WELL);
        }
        else if (RepurposedStructures.RSWellsConfig.forestWellRarityPerChunk.get() != 10000 &&
                (event.getCategory() == Category.FOREST && !(event.getName().getPath().contains("dark_forest") || event.getName().getPath().contains("dark_oak"))) &&
                wellAllowedByNamespaceAndConfig(event.getName())) {

            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.FOREST_WELL);
        }
    }


    private static boolean wellAllowedByNamespaceAndConfig(ResourceLocation biomeID) {
        return biomeID.getNamespace().equals("minecraft") || RepurposedStructures.RSWellsConfig.addWellsToModdedBiomes.get();
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SWAMP TREE FEATURES //

    public static void addSwampTreeFeatures(BiomeLoadingEvent event) {

        // Exists in vanilla Swamp and can be in modded swamp biomes
        if (RepurposedStructures.RSMainConfig.hornedSwampTree.get() &&
                (event.getName().equals(new ResourceLocation("minecraft:swamp")) ||
                (RepurposedStructures.RSMainConfig.addLargeSwampTreeModdedBiomes.get() &&
                        event.getCategory() == Category.SWAMP &&
                        !event.getName().getNamespace().equals("ultra_amplified_dimension") &&
                        !event.getName().getNamespace().equals("minecraft")))){

            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION)
                    .add(() -> RSConfiguredFeatures.HORNED_SWAMP_TREE_UNCOMMON);
        }

        // Only exists in vanilla Swamp Hills biomes
        else if (RepurposedStructures.RSMainConfig.hornedSwampTree.get() &&
                (event.getName().equals(new ResourceLocation("minecraft:swamp_hills")))) {

            // replace the swamp tree with our own
            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION)
                    .removeIf(configuredFeature -> configuredFeature.get().config instanceof DecoratedFeatureConfig &&
                            serializeAndCompareFeature(configuredFeature.get(), Features.SWAMP_TREE));

            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION)
                    .add(() -> RSConfiguredFeatures.HORNED_SWAMP_TREE_COMMON);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BOULDER FEATURES //

    public static void addBoulderFeatures(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSMainConfig.boulderGiant.get() && !event.getName().getNamespace().equals("ultra_amplified_dimension") &&
                ((event.getName().equals(new ResourceLocation("minecraft:giant_spruce_taiga_hills")) || event.getName().equals(new ResourceLocation("minecraft:giant_tree_taiga_hills"))) ||
                        (RepurposedStructures.RSMainConfig.addGiantBouldersModdedBiomes.get() && !event.getName().getNamespace().equals("minecraft") &&
                                ((event.getName().getPath().contains("giant") && event.getName().getPath().contains("taiga")) || event.getName().getPath().contains("redwood"))))) {

            // replace the boulders with our own
            event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS)
                    .removeIf(configuredFeature -> configuredFeature.get().config instanceof DecoratedFeatureConfig &&
                            serializeAndCompareFeature(configuredFeature.get(), Features.FOREST_ROCK));

            event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS)
                    .add(() -> RSConfiguredFeatures.BOULDER_GIANT);
        }
        else if (RepurposedStructures.RSMainConfig.boulderTiny.get() && !event.getName().getNamespace().equals("ultra_amplified_dimension") &&
                ((event.getName().equals(new ResourceLocation("minecraft:snowy_taiga_mountains")) || event.getName().equals(new ResourceLocation("minecraft:taiga_mountains"))) ||
                        (RepurposedStructures.RSMainConfig.addTinyBouldersModdedBiomes.get() && !event.getName().getNamespace().equals("minecraft") &&
                                event.getName().getPath().contains("taiga") && (event.getName().getPath().contains("mountain") || event.getName().getPath().contains("hill"))))) {

            event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS)
                    .add(() -> RSConfiguredFeatures.BOULDER_TINY);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // STRONGHOLDS //

    public static void addStrongholds(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdMaxChunkDistance.get() != 1001 &&
            event.getCategory() != Category.NETHER && event.getCategory() != Category.THEEND &&
                ((RepurposedStructures.RSStrongholdsConfig.allowStonebrickStrongholdToVanillaBiomes.get() &&
                    event.getName().getNamespace().equals("minecraft") &&
                    event.getGeneration().getStructures().stream().anyMatch(structureFeatureSupplier -> structureFeatureSupplier.get().feature.equals(Structure.STRONGHOLD))) ||
                (RepurposedStructures.RSStrongholdsConfig.addStonebrickStrongholdToModdedBiomes.get() &&
                    !event.getName().getNamespace().equals("minecraft"))))
        {

            //replace vanilla stronghold with ours if vanilla's is present
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.STONEBRICK_STRONGHOLD);

            event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_DECORATION)
                    .add(() -> RSConfiguredFeatures.STONEBRICK_STRONGHOLD_CHAINS);

            event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.STRONGHOLD));
        }

        else if (RepurposedStructures.RSStrongholdsConfig.netherStrongholdMaxChunkDistance.get() != 1001 && event.getCategory() == Category.NETHER &&
                (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSStrongholdsConfig.addNetherStrongholdToModdedBiomes.get())) {

            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_STRONGHOLD);

            event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_DECORATION)
                    .add(() -> RSConfiguredFeatures.NETHER_STRONGHOLD_CHAINS);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OUTPOSTS //

    public static void addOutposts(BiomeLoadingEvent event) {

        //Nether based Outposts
        if(event.getCategory() == Category.NETHER)
        {
            if (RepurposedStructures.RSOutpostsConfig.crimsonOutpostMaxChunkDistance.get() != 1001 &&
                    event.getName().getPath().contains("crimson") &&
                    (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSOutpostsConfig.addCrimsonOutpostToModdedBiomes.get())) {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.CRIMSON_OUTPOST);
            }
            else if (RepurposedStructures.RSOutpostsConfig.warpedOutpostMaxChunkDistance.get() != 1001 &&
                    event.getName().getPath().contains("warped") &&
                    (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSOutpostsConfig.addWarpedOutpostToModdedBiomes.get())) {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WARPED_OUTPOST);
            }
            else if (RepurposedStructures.RSOutpostsConfig.netherBrickOutpostMaxChunkDistance.get() != 1001 &&
                    (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSOutpostsConfig.addNetherBrickOutpostToModdedBiomes.get())) {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_BRICK_OUTPOST);
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OUTPOSTS //

    public static void addShipwrecks(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSShipwrecksConfig.endShipwreckMaxChunkDistance.get() != 1001 &&
                (event.getName().equals(new ResourceLocation("minecraft:end_highlands")) ||
                (!event.getName().getNamespace().equals("minecraft") && event.getCategory() == Category.THEEND &&
                RepurposedStructures.RSShipwrecksConfig.addEndShipwreckToModdedBiomes.get()))) {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.END_SHIPWRECK);
        }


        //Nether based Shipwrecks
        if(event.getCategory() == Category.NETHER)
        {
            if (RepurposedStructures.RSShipwrecksConfig.crimsonShipwreckMaxChunkDistance.get() != 1001 &&
                    event.getName().getPath().contains("crimson") &&
                    (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSShipwrecksConfig.addCrimsonShipwreckToModdedBiomes.get())) {

                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.CRIMSON_SHIPWRECK);
            }

            else if (RepurposedStructures.RSShipwrecksConfig.warpedShipwreckMaxChunkDistance.get() != 1001 &&
                    event.getName().getPath().contains("warped") &&
                    (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSShipwrecksConfig.addWarpedShipwreckToModdedBiomes.get())) {

                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WARPED_SHIPWRECK);
            }

            else if (RepurposedStructures.RSShipwrecksConfig.netherBricksShipwreckMaxChunkDistance.get() != 1001 &&
                    (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSShipwrecksConfig.addNetherBricksShipwreckToModdedBiomes.get())) {

                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_BRICKS_SHIPWRECK);
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // JUNGLE FORTRESS //

    public static void addJungleFortress(BiomeLoadingEvent event) {
        if(RepurposedStructures.RSMainConfig.jungleFortressMaxChunkDistance.get() != 1001)
        {
            if ( event.getCategory() == Category.JUNGLE &&
                    (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSMainConfig.addJungleFortressToModdedBiomes.get())) {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.JUNGLE_FORTRESS);
            }
            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION)
                    .add(() -> RSConfiguredFeatures.JUNGLE_FORTRESS_VINES);

            event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES)
                    .add(() -> RSConfiguredFeatures.FORTRESS_BREAKAGE);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TEMPLES //

    public static void addTemples(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSTemplesConfig.netherBasaltTempleMaxChunkDistance.get() != 1001 &&
                event.getCategory() == Category.NETHER && (event.getName().getPath().contains("basalt") || event.getName().getPath().contains("blackstone")) &&
                (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSTemplesConfig.addNetherBasaltTempleToModdedBiomes.get())) {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_BASALT_TEMPLE);
        }
        else if (RepurposedStructures.RSTemplesConfig.netherCrimsonTempleMaxChunkDistance.get() != 1001 &&
                event.getCategory() == Category.NETHER && event.getName().getPath().contains("crimson") &&
                (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSTemplesConfig.addNetherCrimsonTempleToModdedBiomes.get())) {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_CRIMSON_TEMPLE);
        }
        else if (RepurposedStructures.RSTemplesConfig.netherWarpedTempleMaxChunkDistance.get() != 1001 &&
                event.getCategory() == Category.NETHER && event.getName().getPath().contains("warped") &&
                (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSTemplesConfig.addNetherWarpedTempleToModdedBiomes.get())) {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_WARPED_TEMPLE);
        }
        else if (RepurposedStructures.RSTemplesConfig.netherSoulTempleMaxChunkDistance.get() != 1001 &&
                event.getCategory() == Category.NETHER && event.getName().getPath().contains("soul") &&
                (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSTemplesConfig.addNetherSoulTempleToModdedBiomes.get())) {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_SOUL_TEMPLE);
        }
        else if (RepurposedStructures.RSTemplesConfig.netherWastelandTempleMaxChunkDistance.get() != 1001 &&
                event.getCategory() == Category.NETHER &&
                (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSTemplesConfig.addNetherWastelandTempleToModdedBiomes.get())) {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_WASTELAND_TEMPLE);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Pyramids //

    public static void addPyramids(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSTemplesConfig.netherPyramidMaxChunkDistance.get() != 1001 && event.getCategory() == Category.NETHER &&
                (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSTemplesConfig.addNetherPyramidToModdedBiomes.get())) {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_PYRAMID);
        }

        if (RepurposedStructures.RSTemplesConfig.badlandsPyramidMaxChunkDistance.get() != 1001 && event.getCategory() == Category.MESA &&
                (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSTemplesConfig.addBadlandsPyramidToModdedBiomes.get())) {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.BADLANDS_TEMPLE);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // IGLOOS //

    public static void addIgloos(BiomeLoadingEvent event) {
        if (RepurposedStructures.RSMainConfig.grassyIglooMaxChunkDistance.get() != 1001) {
            if ((event.getCategory() == Category.FOREST || event.getCategory() == Category.PLAINS) &&
                    (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSMainConfig.addGrassyIglooToModdedBiomes.get())) {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.GRASSY_IGLOO);
            }
        }

        if (RepurposedStructures.RSMainConfig.stoneIglooMaxChunkDistance.get() != 1001) {
            if ((event.getCategory() == Category.TAIGA && event.getName().getPath().contains("giant")) &&
                    (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSMainConfig.addStoneIglooToModdedBiomes.get())) {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.STONE_IGLOO);
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // VILLAGES //

    public static void addVillages(BiomeLoadingEvent event) {
        if ((event.getCategory() == Category.MESA && !event.getName().getPath().contains("plateau")) &&
                (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            if (RepurposedStructures.RSVillagesConfig.badlandsVillageMaxChunkDistance.get() != 1001) {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.BADLANDS_VILLAGE);
            }
        }

        else if (event.getName().getPath().contains("birch") && (event.getName().getNamespace().equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            if (RepurposedStructures.RSVillagesConfig.birchVillageMaxChunkDistance.get() != 1001) {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.BIRCH_VILLAGE);
            }
        }

        else if (event.getCategory() == Category.FOREST && event.getName().getPath().contains("dark") && (event.getName().getNamespace().equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            if (RepurposedStructures.RSVillagesConfig.darkForestVillageMaxChunkDistance.get() != 1001) {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.DARK_FOREST_VILLAGE);
            }
        }

        else if (event.getCategory() == Category.JUNGLE && (event.getName().getNamespace().equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            if (RepurposedStructures.RSVillagesConfig.jungleVillageMaxChunkDistance.get() != 1001) {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.JUNGLE_VILLAGE);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION)
                        .add(() -> RSConfiguredFeatures.JUNGLE_VILLAGE_VINES);
            }
        }

        else if (event.getCategory() == Category.SWAMP && (event.getName().getNamespace().equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            if (RepurposedStructures.RSVillagesConfig.swampVillageMaxChunkDistance.get() != 1001) {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.SWAMP_VILLAGE);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION)
                        .add(() -> RSConfiguredFeatures.SWAMP_VILLAGE_VINES);
            }
        }

        else if (event.getCategory() == Category.EXTREME_HILLS && (event.getName().getNamespace().equals("minecraft") ||
                RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            if (RepurposedStructures.RSVillagesConfig.mountainsVillageMaxChunkDistance.get() != 1001) {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MOUNTAINS_VILLAGE);
            }
        }

        else if ((event.getName().equals(new ResourceLocation("minecraft:giant_spruce_taiga")) || event.getName().equals(new ResourceLocation("minecraft:giant_tree_taiga"))) ||
                (!event.getName().getNamespace().equals("minecraft") &&
                  RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get() &&
                ((event.getName().getPath().contains("giant") && event.getName().getPath().contains("taiga")) || event.getName().getPath().contains("redwood")))) {
            if (RepurposedStructures.RSVillagesConfig.giantTaigaVillageMaxChunkDistance.get() != 1001) {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.GIANT_TAIGA_VILLAGE);
            }
        }
        else if (RepurposedStructures.RSVillagesConfig.crimsonVillageMaxChunkDistance.get() != 1001 &&
                event.getCategory() == Category.NETHER && event.getName().getPath().contains("crimson") &&
                (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.CRIMSON_VILLAGE);
        }
        else if (RepurposedStructures.RSVillagesConfig.warpedVillageMaxChunkDistance.get() != 1001 &&
                event.getCategory() == Category.NETHER && event.getName().getPath().contains("warped") &&
                (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WARPED_VILLAGE);
        }
        else if (RepurposedStructures.RSVillagesConfig.villageOakMaxChunkDistance.get() != 1001) {
            if ((event.getCategory() == Category.FOREST && !event.getName().getPath().contains("birch") && !event.getName().getPath().contains("dark")) &&
                    (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get())) {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.VILLAGE_OAK);
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // RUINED PORTALS //

    public static void addRuinedPortals(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSMainConfig.ruinedPortalEndMaxChunkDistance.get() != 1001) {
            if (event.getCategory() == Category.THEEND && !event.getName().equals(new ResourceLocation("minecraft:the_end")) &&
                    (event.getName().getNamespace().equals("minecraft") || RepurposedStructures.RSMainConfig.addRuinedPortalEndToModdedBiomes.get())) {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.RUINED_PORTAL_END);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GENERAL UTILITIES //

    /**
     * Will serialize (if possible) both features and check if they are the same feature.
     * If cannot serialize, compare the feature itself to see if it is the same.
     */
    private static boolean serializeAndCompareFeature(ConfiguredFeature<?, ?> configuredFeature1, ConfiguredFeature<?, ?> configuredFeature2) {

        Optional<JsonElement> configuredFeatureJSON1 = ConfiguredFeature.field_25833.encode(configuredFeature1, JsonOps.INSTANCE, JsonOps.INSTANCE.empty()).get().left();
        Optional<JsonElement> configuredFeatureJSON2 = ConfiguredFeature.field_25833.encode(configuredFeature2, JsonOps.INSTANCE, JsonOps.INSTANCE.empty()).get().left();

        // One of the configuredfeatures cannot be serialized
        if(!configuredFeatureJSON1.isPresent() || !configuredFeatureJSON2.isPresent()) {
            return false;
        }

        // Compare the JSON to see if it's the same ConfiguredFeature in the end.
        return configuredFeatureJSON1.equals(configuredFeatureJSON2);
    }

}
