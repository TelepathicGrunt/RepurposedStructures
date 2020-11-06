package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.mixin.StructuresConfigAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.FlatChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RSAddFeaturesAndStructures {


    public static void allowStructureSpawningPerDimension() {
        // Controls the dimension blacklisting
        ServerWorldEvents.LOAD.register((MinecraftServer minecraftServer, ServerWorld serverWorld)->{

            //add our structure spacing to all chunkgenerators including modded one and datapack ones.
            List<String> dimensionBlacklist = Arrays.stream(RepurposedStructures.RSAllConfig.RSMainConfig.blacklistedDimensions.split(",")).map(String::trim).collect(Collectors.toList());

            // Need temp map as some mods use custom chunk generators with immutable maps in themselves.
            Map<StructureFeature<?>, StructureConfig> tempMap = new HashMap<>(serverWorld.getChunkManager().getChunkGenerator().getStructuresConfig().getStructures());
            if(dimensionBlacklist.stream().anyMatch(blacklist -> blacklist.equals((serverWorld.getRegistryKey().getValue().toString())))
                    || (serverWorld.getChunkManager().getChunkGenerator() instanceof FlatChunkGenerator && serverWorld.getRegistryKey().equals(World.OVERWORLD)))
            {
                // make absolutely sure dimension cannot spawn RS structures
                tempMap.keySet().removeAll(RSStructures.RS_STRUCTURES.keySet());
            }
            else{
                // make absolutely sure dimension can spawn RS structures
                tempMap.putAll(RSStructures.RS_STRUCTURES);
            }

            ((StructuresConfigAccessor)serverWorld.getChunkManager().getChunkGenerator().getStructuresConfig()).setStructures(tempMap);
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BIOME MODIFICATION API //
    public static void setupBiomeModifications() {
        RepurposedStructures.getBiomeBlacklists();
        addMineshafts();
        addDungeons();
        addWells();
        addSwampTreeFeatures();
        addBoulderFeatures();
        addStrongholds();
        addOutposts();
        addShipwrecks();
        addJungleFortress();
        addTemples();
        addPyramids();
        addIgloos();
        addVillages();
    }

    // Helper method to help reduce amount of code we need to write for adding structures to biomes
    private static void addToBiome(String modificationName, Predicate<BiomeSelectionContext> selectorPredicate, Consumer<BiomeModificationContext> biomeAdditionConsumer){
        BiomeModifications.create(new Identifier(RepurposedStructures.MODID, modificationName)).add(ModificationPhase.ADDITIONS, selectorPredicate, biomeAdditionConsumer);
    }

    private static boolean isBiomeAllowed(String structureType, Identifier biomeID){
        return RepurposedStructures.ALL_BIOME_BLACKLISTS.get(structureType).stream().noneMatch(blacklistedBiome -> blacklistedBiome.equals(biomeID.toString()));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MINESHAFTS //

    public static void addMineshafts() {

        addToBiome("birch_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.birchMineshaftSpawnrate != 0
                            && context.getBiomeKey().getValue().getPath().contains("birch")),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.BIRCH_MINESHAFT));

        addToBiome("jungle_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.jungleMineshaftSpawnrate != 0
                            && context.getBiome().getCategory() == Category.JUNGLE),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.JUNGLE_MINESHAFT));
        
        addToBiome("desert_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.desertMineshaftSpawnrate != 0 
                            && context.getBiome().getCategory() == Category.DESERT),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.DESERT_MINESHAFT));
        
        addToBiome("stone_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.stoneMineshaftSpawnrate != 0 
                            && context.getBiome().getCategory() == Category.EXTREME_HILLS),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.STONE_MINESHAFT));
        
        addToBiome("savanna_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.savannaMineshaftSpawnrate != 0 
                            && context.getBiome().getCategory() == Category.SAVANNA),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.SAVANNA_MINESHAFT));
        
        addToBiome("icy_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.icyMineshaftSpawnrate != 0 
                            && (context.getBiome().getCategory() == Category.ICY || context.getBiomeKey().getValue().getPath().contains("snowy"))),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.ICY_MINESHAFT));
        
        addToBiome("ocean_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.oceanMineshaftSpawnrate != 0 
                            && context.getBiome().getCategory() == Category.OCEAN),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OCEAN_MINESHAFT));
        
        addToBiome("taiga_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.taigaMineshaftSpawnrate != 0 
                            && context.getBiome().getCategory() == Category.TAIGA),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.TAIGA_MINESHAFT));
        
        addToBiome("swamp_or_dark_forest_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.swampAndDarkForestMineshaftSpawnrate != 0 
                            && (context.getBiome().getCategory() == Category.SWAMP || context.getBiomeKey().getValue().getPath().contains("dark_forest") || context.getBiomeKey().getValue().getPath().contains("dark_oak"))),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.SWAMP_OR_DARK_FOREST_MINESHAFT));
        
        addToBiome("end_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.endMineshaftSpawnrate != 0 
                            && context.getBiome().getCategory() == Category.THEEND && !context.getBiomeKey().getValue().equals(new Identifier("minecraft:the_end")) 
                            && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.misc.barrensIslandsEndMineshafts 
                                || (!context.getBiomeKey().getValue().equals(new Identifier("minecraft:end_barrens")) && !context.getBiomeKey().getValue().equals(new Identifier("minecraft:small_end_islands"))))),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.END_MINESHAFT));
        
        addToBiome("nether_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.netherMineshaftSpawnrate != 0 
                            && context.getBiome().getCategory() == Category.NETHER),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_MINESHAFT));


        //Remove vanilla mineshafts from biomes we added our mineshafts to
        BiomeModifications.create(new Identifier(RepurposedStructures.MODID, "remove_vanilla_mineshafts")).add(
                ModificationPhase.REMOVALS,
                context -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.MINESHAFT).stream().anyMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure)),
                context -> context.getGenerationSettings().removeStructure(StructureFeature.MINESHAFT));
    }

    private static boolean genericMineshaftCheck(BiomeSelectionContext context){
        return RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.MINESHAFT).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                && isBiomeAllowed("mineshaft", context.getBiomeKey().getValue())
                && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                    || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DUNGEONS //

    public static void addDungeons() {

        addToBiome("jungle_dungeon",
                (context) -> genericDungeonCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.jungleDungeonAttemptsPerChunk != 0
                            && context.getBiome().getCategory() == Category.JUNGLE),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.JUNGLE_DUNGEONS));

        addToBiome("badlands_dungeon",
                (context) -> genericDungeonCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.badlandsDungeonAttemptsPerChunk != 0
                            && context.getBiome().getCategory() == Category.MESA),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.BADLANDS_DUNGEONS));

        addToBiome("dark_forest_dungeons",
                (context) -> genericDungeonCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.darkForestDungeonAttemptsPerChunk != 0
                            && context.getBiomeKey().getValue().getPath().contains("dark_forest")),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.DARK_FOREST_DUNGEONS));

        addToBiome("desert_dungeons",
                (context) -> genericDungeonCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.desertDungeonAttemptsPerChunk != 0
                            && context.getBiome().getCategory() == Category.DESERT),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.DESERT_DUNGEONS));

        addToBiome("mushroom_dungeons",
                (context) -> genericDungeonCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.mushroomDungeonAttemptsPerChunk != 0
                            && context.getBiome().getCategory() == Category.MUSHROOM),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.MUSHROOM_DUNGEONS));

        addToBiome("swamp_dungeons",
                (context) -> genericDungeonCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.swampDungeonAttemptsPerChunk != 0
                            && context.getBiome().getCategory() == Category.SWAMP),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.SWAMP_DUNGEONS));

        addToBiome("snow_dungeons",
                (context) -> genericDungeonCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.snowDungeonAttemptsPerChunk != 0
                            && context.getBiome().getCategory() == Category.ICY),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.SNOW_DUNGEONS));

        addToBiome("nether_dungeons",
                (context) -> genericDungeonCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.netherDungeonAttemptsPerChunk != 0
                        && context.getBiome().getCategory() == Category.NETHER),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.NETHER_DUNGEONS));

        addToBiome("end_dungeons",
                (context) -> genericDungeonCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.endDungeonAttemptsPerChunk != 0
                            && (context.getBiome().getCategory() == Category.THEEND
                                && !context.getBiomeKey().getValue().equals(new Identifier("minecraft:the_end"))
                                && !context.getBiomeKey().getValue().equals(new Identifier("minecraft:small_end_islands")))),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.END_DUNGEONS));

        addToBiome("ocean_dungeons",
                (context) -> genericDungeonCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk != 0
                            && context.getBiome().getCategory() == Category.OCEAN)
                        && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                            || RepurposedStructures.RSAllConfig.RSDungeonsConfig.addDungeonsToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.OCEAN_DUNGEONS));


        //Remove vanilla dungeons from non-ocean/nether/end biomes we added our dungeons to
        BiomeModifications.create(new Identifier(RepurposedStructures.MODID, "remove_vanilla_dungeons")).add(
                ModificationPhase.REMOVALS,
                context -> RSConfiguredFeatures.RS_DUNGEONS.stream().anyMatch(dungeon ->
                                dungeon != RSConfiguredFeatures.OCEAN_DUNGEONS &&
                                dungeon != RSConfiguredFeatures.NETHER_DUNGEONS &&
                                dungeon != RSConfiguredFeatures.END_DUNGEONS &&
                                context.hasBuiltInFeature(dungeon)),
                context -> context.getGenerationSettings().removeBuiltInFeature(ConfiguredFeatures.MONSTER_ROOM));
    }

    private static boolean genericDungeonCheck(BiomeSelectionContext context){
        return RSConfiguredFeatures.RS_DUNGEONS.stream().noneMatch(context::hasBuiltInFeature)
                && isBiomeAllowed("dungeon", context.getBiomeKey().getValue())
                && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                    || RepurposedStructures.RSAllConfig.RSDungeonsConfig.addDungeonsToModdedBiomes);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WELLS //

    public static void addWells() {

        addToBiome("badlands_well",
                (context) -> genericWellCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.badlandsWellRarityPerChunk != 10000
                            && context.getBiome().getCategory() == Category.MESA),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.BADLANDS_WELL));

        addToBiome("nether_well",
                (context) -> genericWellCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.netherWellRarityPerChunk != 10000
                            && context.getBiome().getCategory() == Category.NETHER),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.NETHER_WELL));

        addToBiome("snow_well",
                (context) -> genericWellCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.snowWellRarityPerChunk != 10000
                            && (context.getBiome().getCategory() == Category.ICY || context.getBiomeKey().getValue().getPath().contains("snow"))),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.SNOW_WELL));

        addToBiome("mossy_stone_well",
                (context) -> genericWellCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.mossyStoneWellRarityPerChunk != 10000
                            && (context.getBiome().getCategory() == Category.SWAMP
                                || context.getBiome().getCategory() == Category.JUNGLE
                                || context.getBiomeKey().getValue().getPath().contains("dark_forest")
                                || context.getBiomeKey().getValue().getPath().contains("dark_oak"))),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.MOSSY_STONE_WELL));

        addToBiome("forest_well",
                (context) -> genericWellCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.forestWellRarityPerChunk != 10000
                            && (context.getBiome().getCategory() == Category.FOREST
                                && !(context.getBiomeKey().getValue().getPath().contains("dark_forest")
                                    || context.getBiomeKey().getValue().getPath().contains("dark_oak")))),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.FOREST_WELL));
    }

    private static boolean genericWellCheck(BiomeSelectionContext context){
        return RSConfiguredFeatures.RS_WELLS.stream().noneMatch(context::hasBuiltInFeature)
                && isBiomeAllowed("well", context.getBiomeKey().getValue())
                && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                || RepurposedStructures.RSAllConfig.RSWellsConfig.addWellsToModdedBiomes);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SWAMP TREE FEATURES //

    
    public static void addSwampTreeFeatures() {

        // Exists in vanilla Swamp and can be in modded swamp biomes
        addToBiome("horned_swamp_tree_uncommon",
                (context) -> RSConfiguredFeatures.RS_WELLS.stream().noneMatch(context::hasBuiltInFeature)
                        && isBiomeAllowed("swamp_tree", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSMainConfig.misc.hornedSwampTree
                            && (context.getBiomeKey().getValue().equals(new Identifier("minecraft:swamp"))
                                || (RepurposedStructures.RSAllConfig.RSMainConfig.misc.addSwampTreeToModdedBiomes
                                    && context.getBiome().getCategory() == Category.SWAMP
                                    && !context.getBiomeKey().getValue().getNamespace().equals("minecraft")))),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSConfiguredFeatures.HORNED_SWAMP_TREE_UNCOMMON));


        // Only exists in vanilla Swamp Hills biomes
        addToBiome("horned_swamp_tree_common",
                (context) -> RSConfiguredFeatures.RS_WELLS.stream().noneMatch(context::hasBuiltInFeature)
                        && isBiomeAllowed("swamp_tree", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSMainConfig.misc.hornedSwampTree
                            && (context.getBiomeKey().getValue().equals(new Identifier("minecraft:swamp_hills")))),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSConfiguredFeatures.HORNED_SWAMP_TREE_COMMON));

        // Remove vanilla swamp tree from Swamp Hills biomes
        BiomeModifications.create(new Identifier(RepurposedStructures.MODID, "remove_vanilla_swamp_trees")).add(
                ModificationPhase.REMOVALS,
                context -> context.getBiomeKey().getValue().equals(new Identifier("minecraft:swamp_hills"))
                           && context.hasBuiltInFeature(RSConfiguredFeatures.HORNED_SWAMP_TREE_COMMON),
                context -> context.getGenerationSettings().removeBuiltInFeature(ConfiguredFeatures.SWAMP_TREE));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MISC FEATURES //

    public static void addBoulderFeatures() {

        addToBiome("boulder_giant",
                (context) -> isBiomeAllowed("boulder", context.getBiomeKey().getValue())
                            && (RepurposedStructures.RSAllConfig.RSMainConfig.misc.boulderGiant
                                && ((context.getBiomeKey().getValue().equals(new Identifier("minecraft:giant_spruce_taiga_hills"))
                                    || context.getBiomeKey().getValue().equals(new Identifier("minecraft:giant_tree_taiga_hills")))
                                || (RepurposedStructures.RSAllConfig.RSMainConfig.misc.addBoulderToModdedBiomes
                                    && !context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                                    && ((context.getBiomeKey().getValue().getPath().contains("giant")
                                            && context.getBiomeKey().getValue().getPath().contains("taiga"))
                                        || context.getBiomeKey().getValue().getPath().contains("redwood"))))),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, RSConfiguredFeatures.BOULDER_GIANT));

        // Remove vanilla forest rock
        BiomeModifications.create(new Identifier(RepurposedStructures.MODID, "remove_vanilla_boulder")).add(
                ModificationPhase.REMOVALS,
                context -> context.hasBuiltInFeature(RSConfiguredFeatures.BOULDER_GIANT),
                context -> context.getGenerationSettings().removeBuiltInFeature(ConfiguredFeatures.FOREST_ROCK));

        addToBiome("boulder_tiny",
                (context) -> isBiomeAllowed("boulder", context.getBiomeKey().getValue())
                            && (RepurposedStructures.RSAllConfig.RSMainConfig.misc.boulderTiny
                                && ((context.getBiomeKey().getValue().equals(new Identifier("minecraft:snowy_taiga_mountains"))
                                        || context.getBiomeKey().getValue().equals(new Identifier("minecraft:taiga_mountains")))
                                    || (RepurposedStructures.RSAllConfig.RSMainConfig.misc.addBoulderToModdedBiomes
                                        && !context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                                        && context.getBiomeKey().getValue().getPath().contains("taiga")
                                        && (context.getBiomeKey().getValue().getPath().contains("mountain")
                                            || context.getBiomeKey().getValue().getPath().contains("hill"))))),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, RSConfiguredFeatures.BOULDER_TINY));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // STRONGHOLDS //

    public static void addStrongholds() {

        addToBiome("stonebrick_stronghold",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.STRONGHOLD).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("stronghold", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.allowStonebrickStronghold
                            && RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdMaxChunkDistance != 1001
                            && context.getBiome().getCategory() != Category.NETHER
                            && (context.getBiome().getGenerationSettings().hasStructureFeature(StructureFeature.STRONGHOLD)
                                || (!context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                                    && RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.addStonebrickStrongholdToModdedBiomes))),
                context -> {
                        context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.STONEBRICK_STRONGHOLD);
                        context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, RSConfiguredFeatures.STONEBRICK_STRONGHOLD_CHAINS);
                });


        addToBiome("nether_stronghold",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.STRONGHOLD).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("stronghold", context.getBiomeKey().getValue())
                        && RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdMaxChunkDistance != 1001
                        && context.getBiome().getCategory() == Category.NETHER
                        && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                            || RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.addNetherStrongholdToModdedBiomes),
                context -> {
                    context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_STRONGHOLD);
                    context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, RSConfiguredFeatures.NETHER_STRONGHOLD_CHAINS);
                });


        // Remove vanilla stronghold from biomes we added stonebrick stronghold to
        BiomeModifications.create(new Identifier(RepurposedStructures.MODID, "remove_vanilla_stronghold")).add(
                ModificationPhase.REMOVALS,
                context -> isBiomeAllowed("stronghold", context.getBiomeKey().getValue())
                        && context.getBiome().getGenerationSettings().hasStructureFeature(RSStructures.STONEBRICK_STRONGHOLD),
                context -> context.getGenerationSettings().removeStructure(StructureFeature.STRONGHOLD));

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OUTPOSTS //

    public static void addOutposts() {

        //Nether based Outposts
        addToBiome("crimson_outpost",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("outpost", context.getBiomeKey().getValue())
                        && context.getBiome().getCategory() == Category.NETHER
                        && (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.crimsonOutpostMaxChunkDistance != 1001
                            && context.getBiomeKey().getValue().getPath().contains("crimson")
                            && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                                || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addCrimsonOutpostToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.CRIMSON_OUTPOST));

        addToBiome("warped_outpost",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("outpost", context.getBiomeKey().getValue())
                        && context.getBiome().getCategory() == Category.NETHER
                        && (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.warpedOutpostMaxChunkDistance != 1001
                            && context.getBiomeKey().getValue().getPath().contains("warped")
                            && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                                || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addWarpedOutpostToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.WARPED_OUTPOST));

        addToBiome("nether_brick_outpost",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("outpost", context.getBiomeKey().getValue())
                        && context.getBiome().getCategory() == Category.NETHER
                        && (!context.getBiomeKey().getValue().getPath().contains("crimson") && !context.getBiomeKey().getValue().getPath().contains("warped"))
                        && (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.netherBrickOutpostMaxChunkDistance != 1001
                            && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                                || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addNetherBrickOutpostToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_BRICK_OUTPOST));
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SHIPWRECK //

    public static void addShipwrecks() {

        addToBiome("end_shipwreck",
                (context) -> isBiomeAllowed("shipwreck", context.getBiomeKey().getValue())
                        && context.getBiome().getCategory() == Category.THEEND
                        && (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.maxChunkDistance.endShipwreckMaxChunkDistance != 1001
                            && (context.getBiomeKey().getValue().equals(new Identifier("minecraft:end_highlands"))
                                || (!context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                                    && RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.addEndShipwreckToModdedBiomes))),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.END_SHIPWRECK));


        //Nether based Shipwrecks
        addToBiome("crimson_shipwreck",
                (context) -> isBiomeAllowed("shipwreck", context.getBiomeKey().getValue())
                        && context.getBiome().getCategory() == Category.NETHER
                        && (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.maxChunkDistance.crimsonShipwreckMaxChunkDistance != 1001
                            && context.getBiomeKey().getValue().getPath().contains("crimson")
                            && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                                || RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.addCrimsonShipwreckToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.CRIMSON_SHIPWRECK));

        addToBiome("crimson_shipwreck",
                (context) -> isBiomeAllowed("shipwreck", context.getBiomeKey().getValue())
                        && context.getBiome().getCategory() == Category.NETHER
                        && (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.maxChunkDistance.warpedShipwreckMaxChunkDistance != 1001
                            && context.getBiomeKey().getValue().getPath().contains("warped")
                            && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                                || RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.addWarpedShipwreckToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.WARPED_SHIPWRECK));

        addToBiome("nether_bricks_shipwreck",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NETHER_SHIPWRECK).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("shipwreck", context.getBiomeKey().getValue())
                        && context.getBiome().getCategory() == Category.NETHER
                        && (!context.getBiomeKey().getValue().getPath().contains("crimson") && !context.getBiomeKey().getValue().getPath().contains("warped"))
                        && (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.maxChunkDistance.netherBricksShipwreckMaxChunkDistance != 1001
                            && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                                || RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.addNetherBricksShipwreckToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_BRICKS_SHIPWRECK));

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // JUNGLE FORTRESS //

    public static void addJungleFortress() {

        addToBiome("jungle_fortress",
                (context) -> isBiomeAllowed("fortress", context.getBiomeKey().getValue())
                        && context.getBiome().getCategory() == Category.JUNGLE
                        && (RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.jungleFortressMaxChunkDistance != 1001
                        && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                            || RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.addJungleFortressToModdedBiomes)),
                context -> {
                    context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.JUNGLE_FORTRESS);
                    context.getGenerationSettings().removeBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSConfiguredFeatures.JUNGLE_FORTRESS_VINES);
                    context.getGenerationSettings().removeBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSConfiguredFeatures.FORTRESS_BREAKAGE);
                });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TEMPLES //

    public static void addTemples() {

        addToBiome("nether_basalt_temple",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("temple", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherBasaltTempleMaxChunkDistance != 1001
                            && context.getBiome().getCategory() == Category.NETHER
                            && (context.getBiomeKey().getValue().getPath().contains("basalt")
                                || context.getBiomeKey().getValue().getPath().contains("blackstone"))
                            && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                                || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherBasaltTempleToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_BASALT_TEMPLE));

        addToBiome("nether_crimson_temple",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("temple", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherCrimsonTempleMaxChunkDistance != 1001
                            && context.getBiome().getCategory() == Category.NETHER
                            && context.getBiomeKey().getValue().getPath().contains("crimson")
                            && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                                || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherCrimsonTempleToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_CRIMSON_TEMPLE));

        addToBiome("nether_warped_temple",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("temple", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWarpedTempleMaxChunkDistance != 1001
                            && context.getBiome().getCategory() == Category.NETHER
                            && context.getBiomeKey().getValue().getPath().contains("warped")
                            && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                                || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherWarpedTempleToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_WARPED_TEMPLE));

        addToBiome("nether_soul_temple",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("temple", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherSoulTempleMaxChunkDistance != 1001
                            && context.getBiome().getCategory() == Category.NETHER
                            && context.getBiomeKey().getValue().getPath().contains("soul")
                            && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                                || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherSoulTempleToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_SOUL_TEMPLE));

        addToBiome("nether_wasteland_temple",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("temple", context.getBiomeKey().getValue())
                        && (!context.getBiomeKey().getValue().getPath().contains("crimson")
                            && !context.getBiomeKey().getValue().getPath().contains("warped")
                            && !context.getBiomeKey().getValue().getPath().contains("soul")
                            && !(context.getBiomeKey().getValue().getPath().contains("basalt")
                                || context.getBiomeKey().getValue().getPath().contains("blackstone")))
                        && (RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWastelandTempleMaxChunkDistance != 1001
                            && context.getBiome().getCategory() == Category.NETHER
                            && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                                || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherWastelandTempleToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_WASTELAND_TEMPLE));

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Pyramids //

    public static void addPyramids() {

        addToBiome("nether_pyramid",
                (context) -> isBiomeAllowed("temple", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.netherPyramidMaxChunkDistance != 1001
                            && context.getBiome().getCategory() == Category.NETHER
                            && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                                || RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.addNetherPyramidToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_PYRAMID));

        addToBiome("badlands_pyramid",
                (context) -> isBiomeAllowed("temple", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.badlandsPyramidMaxChunkDistance != 1001
                            && context.getBiome().getCategory() == Category.MESA
                            && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                                || RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.addBadlandsPyramidToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.BADLANDS_PYRAMID));

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // IGLOOS //

    public static void addIgloos() {

        addToBiome("grassy_igloo",
                (context) -> isBiomeAllowed("igloo", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSMainConfig.igloos.grassyIglooMaxChunkDistance != 1001
                        && (context.getBiome().getCategory() == Category.FOREST || context.getBiome().getCategory() == Category.PLAINS)
                        && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                            || RepurposedStructures.RSAllConfig.RSMainConfig.igloos.addGrassyIglooToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.GRASSY_IGLOO));

        addToBiome("stone_igloo",
                (context) -> isBiomeAllowed("igloo", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSMainConfig.igloos.stoneIglooMaxChunkDistance != 1001
                        && (context.getBiome().getCategory() == Category.TAIGA && context.getBiomeKey().getValue().getPath().contains("giant"))
                        && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                            || RepurposedStructures.RSAllConfig.RSMainConfig.igloos.addStoneIglooToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.STONE_IGLOO));
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // VILLAGES //

    public static void addVillages() {

        addToBiome("badlands_village",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.VILLAGE).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("village", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSVillagesConfig.badlandsVillageMaxChunkDistance != 1001
                        && (context.getBiome().getCategory() == Category.MESA && context.getBiomeKey().getValue().getPath().contains("plateau"))
                        && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                            || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.BADLANDS_VILLAGE));

        addToBiome("birch_village",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.VILLAGE).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("village", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSVillagesConfig.birchVillageMaxChunkDistance != 1001
                        && context.getBiomeKey().getValue().getPath().contains("birch")
                        && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                            || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.BIRCH_VILLAGE));

        addToBiome("dark_forest_village",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.VILLAGE).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("village", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSVillagesConfig.darkForestVillageMaxChunkDistance != 1001
                        && context.getBiomeKey().getValue().getPath().contains("dark_forest")
                        && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                            || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.DARK_FOREST_VILLAGE));

        addToBiome("jungle_village",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.VILLAGE).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("village", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSVillagesConfig.jungleVillageMaxChunkDistance != 1001
                        && context.getBiome().getCategory() == Category.JUNGLE
                        && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                            || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)),
                context -> {
                    context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.JUNGLE_VILLAGE);
                    context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSConfiguredFeatures.JUNGLE_VILLAGE_VINES);
                });

        addToBiome("swamp_village",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.VILLAGE).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("village", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSVillagesConfig.swampVillageMaxChunkDistance != 1001
                        && context.getBiome().getCategory() == Category.SWAMP
                        && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                            || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)),
                context -> {
                    context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.SWAMP_VILLAGE);
                    context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSConfiguredFeatures.SWAMP_VILLAGE_VINES);
                });


        addToBiome("mountains_village",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.VILLAGE).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("village", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSVillagesConfig.mountainsVillageMaxChunkDistance != 1001
                        && context.getBiome().getCategory() == Category.EXTREME_HILLS
                        && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                        || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MOUNTAINS_VILLAGE));

        addToBiome("giant_taiga_village",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.VILLAGE).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("village", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSVillagesConfig.giantTaigaVillageMaxChunkDistance != 1001
                        && ((context.getBiomeKey().getValue().equals(new Identifier("minecraft:giant_spruce_taiga"))
                                || context.getBiomeKey().getValue().equals(new Identifier("minecraft:giant_tree_taiga")))
                            || (!context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                                && RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes
                                && ((context.getBiomeKey().getValue().getPath().contains("giant")
                                        && context.getBiomeKey().getValue().getPath().contains("taiga"))
                                    || context.getBiomeKey().getValue().getPath().contains("redwood"))))
                        && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                            || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.GIANT_TAIGA_VILLAGE));

        addToBiome("crimson_village",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.VILLAGE).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("village", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSVillagesConfig.crimsonVillageMaxChunkDistance != 1001
                        && (context.getBiome().getCategory() == Category.NETHER && context.getBiomeKey().getValue().getPath().contains("crimson"))
                        && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                            || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.CRIMSON_VILLAGE));


        addToBiome("warped_village",
                (context) -> RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.VILLAGE).stream().noneMatch(structure -> context.getBiome().getGenerationSettings().hasStructureFeature(structure))
                        && isBiomeAllowed("village", context.getBiomeKey().getValue())
                        && (RepurposedStructures.RSAllConfig.RSVillagesConfig.warpedVillageMaxChunkDistance != 1001
                        && (context.getBiome().getCategory() == Category.NETHER && context.getBiomeKey().getValue().getPath().contains("warped"))
                        && (context.getBiomeKey().getValue().getNamespace().equals("minecraft")
                            || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.WARPED_VILLAGE));
    }
}
