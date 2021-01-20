package com.telepathicgrunt.repurposedstructures;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
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
            if(dimensionBlacklist.stream().anyMatch(blacklist -> blacklist.equals((serverWorld.getRegistryKey().getValue().toString()))))
            {
                // make absolutely sure dimension cannot spawn RS structures
                tempMap.keySet().removeAll(RSStructures.RS_STRUCTURES.keySet());
            }
            else if (serverWorld.getChunkProvider().getChunkGenerator() instanceof FlatChunkGenerator && serverWorld.getRegistryKey().equals(World.OVERWORLD)) {
                // make absolutely sure superflat dimension cannot spawn RS structures
                tempMap.keySet().removeAll(RSStructures.RS_STRUCTURES.keySet());
            }
            else{
                // make absolutely sure dimension can spawn RS structures
                Map<Structure<?>, StructureSeparationSettings> spacingToAdd = new Reference2ObjectOpenHashMap<>();
                spacingToAdd.putAll(RSStructures.RS_STRUCTURES);

                // Do not spawn strongholds in end.
                if(serverWorld.getRegistryKey().equals(World.END)){
                    spacingToAdd.remove(RSStructures.STONEBRICK_STRONGHOLD.get());
                    spacingToAdd.remove(RSStructures.NETHER_STRONGHOLD.get());
                }

                spacingToAdd.forEach(tempMap::putIfAbsent);
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
            (BiomeSelection.hasNamespace(event, "minecraft") ||
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

        if (BiomeSelection.haveCategories(event, Category.JUNGLE) &&
                RepurposedStructures.RSDungeonsConfig.jungleDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event))
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.JUNGLE_DUNGEONS);
        }
        
        else if (BiomeSelection.haveCategories(event, Category.MESA) &&
                RepurposedStructures.RSDungeonsConfig.badlandsDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event))
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.BADLANDS_DUNGEONS);
        }
        
        else if (BiomeSelection.haveCategories(event, Category.FOREST) &&
                BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted") &&
                RepurposedStructures.RSDungeonsConfig.darkForestDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event))
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.DARK_FOREST_DUNGEONS);
        }
        
        else if (BiomeSelection.haveCategories(event, Category.DESERT) &&
                RepurposedStructures.RSDungeonsConfig.desertDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event))
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.DESERT_DUNGEONS);
        }
        
        else if (BiomeSelection.haveCategories(event, Category.MUSHROOM) &&
                RepurposedStructures.RSDungeonsConfig.mushroomDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event))
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.MUSHROOM_DUNGEONS);
        }
        
        else if (BiomeSelection.haveCategories(event, Category.SWAMP) &&
                RepurposedStructures.RSDungeonsConfig.swampDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event)) 
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.SWAMP_DUNGEONS);
        }
        
        else if (BiomeSelection.haveCategories(event, Category.ICY) &&
                RepurposedStructures.RSDungeonsConfig.snowDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event)) 
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.SNOW_DUNGEONS);
        }
        
        else if (BiomeSelection.haveCategories(event, Category.NETHER) &&
                RepurposedStructures.RSDungeonsConfig.netherDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event)) 
        {
            replaceOrAddDungeon(false, event, RSConfiguredFeatures.NETHER_DUNGEONS);
        }
        
        else if (RepurposedStructures.RSDungeonsConfig.endDungeonAttemptsPerChunk.get() != 0 &&
                BiomeSelection.haveCategories(event, Category.THEEND) &&
                !BiomeSelection.isBiome(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS) &&
                dungeonAllowedByNamespaceAndConfig(event)) 
        {
            replaceOrAddDungeon(false, event, RSConfiguredFeatures.END_DUNGEONS);
        }
        
        else if (BiomeSelection.haveCategories(event, Category.OCEAN) &&
                RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event)) 
        {
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
    private static boolean dungeonAllowedByNamespaceAndConfig(BiomeLoadingEvent event) {
        return BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSDungeonsConfig.addDungeonsToModdedBiomes.get();
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WELLS //

    public static void addWells(BiomeLoadingEvent event) {

        if (BiomeSelection.haveCategories(event, Category.MESA) &&
            RepurposedStructures.RSWellsConfig.badlandsWellRarityPerChunk.get() != 10000 &&
            wellAllowedByNamespaceAndConfig(event))
        {
            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.BADLANDS_WELL);
        }
        
        else if (BiomeSelection.haveCategories(event, Category.NETHER) &&
                RepurposedStructures.RSWellsConfig.netherWellRarityPerChunk.get() != 10000 &&
                wellAllowedByNamespaceAndConfig(event)) {

            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.NETHER_WELL);
        }

        else if ((BiomeSelection.haveCategories(event, Category.ICY) || BiomeSelection.hasName(event, "snow")) &&
                RepurposedStructures.RSWellsConfig.snowWellRarityPerChunk.get() != 10000 &&
                wellAllowedByNamespaceAndConfig(event))
        {
            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.SNOW_WELL);
        }

        else if ((BiomeSelection.haveCategories(event, Category.SWAMP, Category.JUNGLE) ||
                (BiomeSelection.haveCategories(event, Category.FOREST) && BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted"))) &&
                RepurposedStructures.RSWellsConfig.mossyStoneWellRarityPerChunk.get() != 10000 &&
                wellAllowedByNamespaceAndConfig(event))
        {
            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.MOSSY_STONE_WELL);
        }

        else if (BiomeSelection.haveCategories(event, Category.FOREST) && !(BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted")) &&
                RepurposedStructures.RSWellsConfig.forestWellRarityPerChunk.get() != 10000 &&
                wellAllowedByNamespaceAndConfig(event))
        {

            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.FOREST_WELL);
        }
    }


    private static boolean wellAllowedByNamespaceAndConfig(BiomeLoadingEvent event) {
        return BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSWellsConfig.addWellsToModdedBiomes.get();
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SWAMP TREE FEATURES //

    public static void addSwampTreeFeatures(BiomeLoadingEvent event) {

        // Exists in vanilla Swamp and can be in modded swamp biomes
        if (RepurposedStructures.RSMainConfig.hornedSwampTree.get() &&
            (BiomeSelection.isBiome(event, Biomes.SWAMP) ||
            (RepurposedStructures.RSMainConfig.addLargeSwampTreeModdedBiomes.get() &&
                !BiomeSelection.hasNamespace(event, "minecraft") &&
                BiomeSelection.haveCategories(event, Category.SWAMP))))
        {
            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION)
                    .add(() -> RSConfiguredFeatures.HORNED_SWAMP_TREE_UNCOMMON);
        }

        // Only exists in vanilla Swamp Hills biomes
        else if (RepurposedStructures.RSMainConfig.hornedSwampTree.get() && BiomeSelection.isBiome(event, Biomes.SWAMP_HILLS)) 
        {
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

        if (RepurposedStructures.RSMainConfig.boulderGiant.get() && 
            !BiomeSelection.hasNamespace(event, "ultra_amplified_dimension") &&
            (BiomeSelection.isBiome(event, Biomes.GIANT_SPRUCE_TAIGA_HILLS, Biomes.GIANT_TREE_TAIGA_HILLS) ||
                (RepurposedStructures.RSMainConfig.addGiantBouldersModdedBiomes.get() && 
                    !BiomeSelection.hasNamespace(event, "minecraft") &&
                    BiomeSelection.haveCategories(event, Category.TAIGA) && 
                    BiomeSelection.hasName(event, "giant", "redwood")))) 
        {
            // replace the boulders with our own
            event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS)
                    .removeIf(configuredFeature -> configuredFeature.get().config instanceof DecoratedFeatureConfig &&
                            serializeAndCompareFeature(configuredFeature.get(), Features.FOREST_ROCK));

            event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS)
                    .add(() -> RSConfiguredFeatures.BOULDER_GIANT);
        }
        
        else if (RepurposedStructures.RSMainConfig.boulderTiny.get() && 
                !BiomeSelection.hasNamespace(event, "ultra_amplified_dimension") &&
                (BiomeSelection.isBiome(event, Biomes.SNOWY_TAIGA_MOUNTAINS, Biomes.TAIGA_MOUNTAINS) ||
                    (RepurposedStructures.RSMainConfig.addTinyBouldersModdedBiomes.get() && 
                        !BiomeSelection.hasNamespace(event, "minecraft") &&
                        BiomeSelection.haveCategories(event, Category.TAIGA) && 
                        BiomeSelection.hasName(event, "mountain", "hill"))))
        {
            event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS)
                    .add(() -> RSConfiguredFeatures.BOULDER_TINY);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // STRONGHOLDS //

    public static void addStrongholds(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdMaxChunkDistance.get() != 1001 &&
            !BiomeSelection.haveCategories(event, Category.NETHER, Category.THEEND) &&
            ((RepurposedStructures.RSStrongholdsConfig.allowStonebrickStrongholdToVanillaBiomes.get() &&
                BiomeSelection.hasNamespace(event, "minecraft") &&
                BiomeSelection.hasStructure(event, Structure.STRONGHOLD)) ||
            (RepurposedStructures.RSStrongholdsConfig.addStonebrickStrongholdToModdedBiomes.get() &&
                !BiomeSelection.hasNamespace(event, "minecraft"))))
        {
            //replace vanilla stronghold with ours if vanilla's is present
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.STONEBRICK_STRONGHOLD);

            event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_DECORATION)
                    .add(() -> RSConfiguredFeatures.STONEBRICK_STRONGHOLD_CHAINS);

            event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.STRONGHOLD));
        }

        else if (RepurposedStructures.RSStrongholdsConfig.netherStrongholdMaxChunkDistance.get() != 1001 &&
                BiomeSelection.haveCategories(event, Category.NETHER) &&
                (BiomeSelection.hasNamespace(event, "minecraft") ||
                    RepurposedStructures.RSStrongholdsConfig.addNetherStrongholdToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_STRONGHOLD);

            event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_DECORATION)
                    .add(() -> RSConfiguredFeatures.NETHER_STRONGHOLD_CHAINS);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OUTPOSTS //

    public static void addOutposts(BiomeLoadingEvent event) {

        //Nether based Outposts
        if(BiomeSelection.haveCategories(event, Category.NETHER))
        {
            if (BiomeSelection.hasName(event, "crimson", "red_") &&
                RepurposedStructures.RSOutpostsConfig.crimsonOutpostMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addCrimsonOutpostToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.CRIMSON_OUTPOST);
            }

            else if (BiomeSelection.hasName(event, "warped", "blue") &&
                    RepurposedStructures.RSOutpostsConfig.warpedOutpostMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addWarpedOutpostToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WARPED_OUTPOST);
            }

            else if (RepurposedStructures.RSOutpostsConfig.netherBrickOutpostMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addNetherBrickOutpostToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_BRICK_OUTPOST);
            }
        }
        else{
            if (BiomeSelection.hasName(event, "birch") &&
                RepurposedStructures.RSOutpostsConfig.outpostBirchMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addOutpostBirchToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_BIRCH);
                event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
            }

            else if (BiomeSelection.haveCategories(event, Category.JUNGLE) &&
                    RepurposedStructures.RSOutpostsConfig.outpostJungleMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addOutpostJungleToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_JUNGLE);
                event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
            }

            else if (BiomeSelection.haveCategories(event, Category.TAIGA) && BiomeSelection.hasName(event, "giant", "redwood") &&
                    RepurposedStructures.RSOutpostsConfig.outpostGiantTreeTaigaMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addOutpostGiantTreeTaigaToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_GIANT_TREE_TAIGA);
                event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
            }

            else if (BiomeSelection.haveCategories(event, Category.DESERT) &&
                    RepurposedStructures.RSOutpostsConfig.outpostDesertMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addOutpostDesertToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_DESERT);
                event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
            }

            else if (BiomeSelection.haveCategories(event, Category.MESA) && !BiomeSelection.hasName(event, "plateau") &&
                    RepurposedStructures.RSOutpostsConfig.outpostBadlandsMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addOutpostBadlandsToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_BADLANDS);
                event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
            }

            else if ((BiomeSelection.hasName(event, "snow") ||
                        (BiomeSelection.haveCategories(event, Category.ICY) && !BiomeSelection.hasName(event, "ice", "icy", "glacier", "frozen"))) &&
                    RepurposedStructures.RSOutpostsConfig.outpostSnowyMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addOutpostSnowyToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_SNOWY);
                event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
            }

            else if (BiomeSelection.haveCategories(event, Category.ICY) && BiomeSelection.hasName(event, "ice", "icy", "glacier", "frozen") &&
                    RepurposedStructures.RSOutpostsConfig.outpostIcyMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addOutpostIcyToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_ICY);
                event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OUTPOSTS //

    public static void addShipwrecks(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSShipwrecksConfig.endShipwreckMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.isBiome(event, Biomes.END_MIDLANDS) ||
                (RepurposedStructures.RSShipwrecksConfig.addEndShipwreckToModdedBiomes.get() &&
                    !BiomeSelection.hasNamespace(event, "minecraft") &&
                    BiomeSelection.haveCategories(event, Category.THEEND))))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.END_SHIPWRECK);
        }

        //Nether based Shipwrecks
        if(BiomeSelection.haveCategories(event, Category.NETHER))
        {
            if (BiomeSelection.hasName(event, "crimson", "red_") &&
                RepurposedStructures.RSShipwrecksConfig.crimsonShipwreckMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSShipwrecksConfig.addCrimsonShipwreckToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.CRIMSON_SHIPWRECK);
            }

            else if (BiomeSelection.hasName(event, "warped", "blue") &&
                    RepurposedStructures.RSShipwrecksConfig.warpedShipwreckMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSShipwrecksConfig.addWarpedShipwreckToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WARPED_SHIPWRECK);
            }

            else if (RepurposedStructures.RSShipwrecksConfig.netherBricksShipwreckMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSShipwrecksConfig.addNetherBricksShipwreckToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_BRICKS_SHIPWRECK);
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // JUNGLE FORTRESS //

    public static void addJungleFortress(BiomeLoadingEvent event) {
        if(RepurposedStructures.RSMainConfig.jungleFortressMaxChunkDistance.get() != 1001)
        {
            if (BiomeSelection.haveCategories(event, Category.JUNGLE) &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSMainConfig.addJungleFortressToModdedBiomes.get()))
            {
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

        if(BiomeSelection.haveCategories(event, Category.NETHER)){
            if (BiomeSelection.hasName(event, "basalt", "blackstone") &&
                RepurposedStructures.RSTemplesConfig.netherBasaltTempleMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addNetherBasaltTempleToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_BASALT_TEMPLE);
            }

            else if (BiomeSelection.hasName(event, "crimson", "red_") &&
                    RepurposedStructures.RSTemplesConfig.netherCrimsonTempleMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addNetherCrimsonTempleToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_CRIMSON_TEMPLE);
            }

            else if (BiomeSelection.hasName(event, "warped", "blue") &&
                    RepurposedStructures.RSTemplesConfig.netherWarpedTempleMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addNetherWarpedTempleToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_WARPED_TEMPLE);
            }

            else if (BiomeSelection.hasName(event, "soul") &&
                    RepurposedStructures.RSTemplesConfig.netherSoulTempleMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addNetherSoulTempleToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_SOUL_TEMPLE);
            }

            else if (RepurposedStructures.RSTemplesConfig.netherWastelandTempleMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addNetherWastelandTempleToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_WASTELAND_TEMPLE);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Pyramids //

    public static void addPyramids(BiomeLoadingEvent event) {

        if (BiomeSelection.haveCategories(event, Category.NETHER) &&
            RepurposedStructures.RSTemplesConfig.netherPyramidMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addNetherPyramidToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_PYRAMID);
        }

        if (BiomeSelection.haveCategories(event, Category.MESA) &&
            RepurposedStructures.RSTemplesConfig.badlandsPyramidMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addBadlandsPyramidToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.BADLANDS_TEMPLE);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // IGLOOS //

    public static void addIgloos(BiomeLoadingEvent event) {
        if (BiomeSelection.haveCategories(event, Category.FOREST, Category.PLAINS) &&
            RepurposedStructures.RSMainConfig.grassyIglooMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSMainConfig.addGrassyIglooToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.GRASSY_IGLOO);
        }

        else if (BiomeSelection.haveCategories(event, Category.TAIGA) && BiomeSelection.hasName(event, "giant", "redwood") &&
                RepurposedStructures.RSMainConfig.stoneIglooMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSMainConfig.addStoneIglooToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.STONE_IGLOO);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // VILLAGES //

    public static void addVillages(BiomeLoadingEvent event) {
        if (BiomeSelection.haveCategories(event, Category.MESA) &&
            RepurposedStructures.RSVillagesConfig.badlandsVillageMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.BADLANDS_VILLAGE);
        }

        else if (BiomeSelection.hasName(event, "birch") &&
                RepurposedStructures.RSVillagesConfig.birchVillageMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.BIRCH_VILLAGE);
        }

        else if (BiomeSelection.haveCategories(event, Category.FOREST) && BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted") &&
                RepurposedStructures.RSVillagesConfig.darkForestVillageMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.DARK_FOREST_VILLAGE);
        }

        else if (BiomeSelection.haveCategories(event, Category.JUNGLE) &&
                RepurposedStructures.RSVillagesConfig.jungleVillageMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.JUNGLE_VILLAGE);
            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> RSConfiguredFeatures.JUNGLE_VILLAGE_VINES);
        }

        else if (BiomeSelection.haveCategories(event, Category.SWAMP) &&
                RepurposedStructures.RSVillagesConfig.swampVillageMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.SWAMP_VILLAGE);
            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> RSConfiguredFeatures.SWAMP_VILLAGE_VINES);
        }

        else if (BiomeSelection.haveCategories(event, Category.EXTREME_HILLS) &&
                RepurposedStructures.RSVillagesConfig.mountainsVillageMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.MOUNTAINS_VILLAGE);
        }

        else if (BiomeSelection.isBiome(event, Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_TREE_TAIGA) ||
                (RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get() &&
                    !BiomeSelection.hasNamespace(event, "minecraft") &&
                    BiomeSelection.haveCategories(event, Category.TAIGA) &&
                    BiomeSelection.hasName(event, "giant", "redwood")))
        {
            if (RepurposedStructures.RSVillagesConfig.giantTaigaVillageMaxChunkDistance.get() != 1001) {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.GIANT_TAIGA_VILLAGE);
            }
        }

        else if (BiomeSelection.haveCategories(event, Category.NETHER) && BiomeSelection.hasName(event, "crimson", "red_") &&
                RepurposedStructures.RSVillagesConfig.crimsonVillageMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.CRIMSON_VILLAGE);
        }

        else if (BiomeSelection.haveCategories(event, Category.NETHER) &&
                BiomeSelection.hasName(event, "warped", "blue") &&
                RepurposedStructures.RSVillagesConfig.warpedVillageMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WARPED_VILLAGE);
        }

        else if ((BiomeSelection.haveCategories(event, Category.FOREST) && !BiomeSelection.hasName(event, "birch", "dark", "spooky", "dead", "haunted")) &&
                RepurposedStructures.RSVillagesConfig.villageOakMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSVillagesConfig.addVillagesToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.VILLAGE_OAK);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // RUINED PORTALS //

    public static void addRuinedPortals(BiomeLoadingEvent event) {

        if (BiomeSelection.haveCategories(event, Category.THEEND) && !BiomeSelection.isBiome(event, Biomes.THE_END) &&
            RepurposedStructures.RSMainConfig.ruinedPortalEndMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSMainConfig.addRuinedPortalEndToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.RUINED_PORTAL_END);
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
