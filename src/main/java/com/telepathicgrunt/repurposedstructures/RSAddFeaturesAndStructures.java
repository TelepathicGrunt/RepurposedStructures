package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.mixin.StructuresConfigAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
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
import net.minecraft.world.biome.BiomeKeys;
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
        ServerWorldEvents.LOAD.register((MinecraftServer minecraftServer, ServerWorld serverWorld) -> {

            //add our structure spacing to all chunkgenerators including modded one and datapack ones.
            List<String> dimensionBlacklist = Arrays.stream(RepurposedStructures.RSAllConfig.RSMainConfig.blacklistedDimensions.split(",")).map(String::trim).collect(Collectors.toList());

            // Need temp map as some mods use custom chunk generators with immutable maps in themselves.
            Map<StructureFeature<?>, StructureConfig> tempMap = new HashMap<>(serverWorld.getChunkManager().getChunkGenerator().getStructuresConfig().getStructures());
            if (dimensionBlacklist.stream().anyMatch(blacklist -> blacklist.equals((serverWorld.getRegistryKey().getValue().toString())))){
                // make absolutely sure dimension cannot spawn RS structures
                tempMap.keySet().removeAll(RSStructures.RS_STRUCTURES.keySet());
            }
            else if (serverWorld.getChunkManager().getChunkGenerator() instanceof FlatChunkGenerator && serverWorld.getRegistryKey().equals(World.OVERWORLD)) {
                // make absolutely sure superflat dimension cannot spawn RS structures
                tempMap.keySet().removeAll(RSStructures.RS_STRUCTURES.keySet());
            }
            else {
                // make absolutely sure dimension can spawn RS structures
                Map<StructureFeature<?>, StructureConfig> spacingToAdd = new Reference2ObjectOpenHashMap<>();
                spacingToAdd.putAll(RSStructures.RS_STRUCTURES);

                // Do not spawn strongholds in end.
                if(serverWorld.getRegistryKey().equals(World.END)){
                    spacingToAdd.remove(RSStructures.STONEBRICK_STRONGHOLD);
                    spacingToAdd.remove(RSStructures.NETHER_STRONGHOLD);
                }

                spacingToAdd.forEach(tempMap::putIfAbsent);
            }

            ((StructuresConfigAccessor) serverWorld.getChunkManager().getChunkGenerator().getStructuresConfig()).rs_setStructures(tempMap);
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
        addRuinedPortals();
    }

    // Helper method to help reduce amount of code we need to write for adding structures to biomes
    private static void addToBiome(String modificationName, Predicate<BiomeSelectionContext> selectorPredicate, Consumer<BiomeModificationContext> biomeAdditionConsumer) {
        BiomeModifications.create(new Identifier(RepurposedStructures.MODID, modificationName)).add(ModificationPhase.ADDITIONS, selectorPredicate, biomeAdditionConsumer);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MINESHAFTS //

    public static void addMineshafts() {
        addToBiome("birch_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.birchMineshaftSpawnrate != 0
                        && BiomeSelection.hasName(context, "birch")),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.BIRCH_MINESHAFT));

        addToBiome("jungle_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.jungleMineshaftSpawnrate != 0
                        && BiomeSelection.haveCategories(context, Category.JUNGLE)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.JUNGLE_MINESHAFT));

        addToBiome("desert_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.desertMineshaftSpawnrate != 0
                        && BiomeSelection.haveCategories(context, Category.DESERT)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.DESERT_MINESHAFT));

        addToBiome("stone_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.stoneMineshaftSpawnrate != 0
                        && BiomeSelection.haveCategories(context, Category.EXTREME_HILLS)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.STONE_MINESHAFT));

        addToBiome("savanna_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.savannaMineshaftSpawnrate != 0
                        && BiomeSelection.haveCategories(context, Category.SAVANNA)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.SAVANNA_MINESHAFT));

        addToBiome("icy_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.icyMineshaftSpawnrate != 0
                        && (BiomeSelection.haveCategories(context, Category.ICY) || BiomeSelection.hasName(context, "snowy"))),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.ICY_MINESHAFT));

        addToBiome("ocean_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.oceanMineshaftSpawnrate != 0
                        && BiomeSelection.haveCategories(context, Category.OCEAN)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OCEAN_MINESHAFT));

        addToBiome("taiga_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.taigaMineshaftSpawnrate != 0
                        && BiomeSelection.haveCategories(context, Category.TAIGA)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.TAIGA_MINESHAFT));

        addToBiome("swamp_or_dark_forest_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.swampAndDarkForestMineshaftSpawnrate != 0
                        && (BiomeSelection.haveCategories(context, Category.SWAMP) ||
                            (BiomeSelection.haveCategories(context, Category.FOREST) && BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted")))),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.SWAMP_OR_DARK_FOREST_MINESHAFT));

        addToBiome("end_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.endMineshaftSpawnrate != 0
                        && BiomeSelection.haveCategories(context, Category.THEEND) && !BiomeSelection.isBiome(context, BiomeKeys.THE_END)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.misc.barrensIslandsEndMineshafts
                        || (!BiomeSelection.isBiome(context, BiomeKeys.END_BARRENS) &&
                            !BiomeSelection.isBiome(context, BiomeKeys.SMALL_END_ISLANDS)))),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.END_MINESHAFT));

        addToBiome("nether_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.netherMineshaftSpawnrate != 0
                        && BiomeSelection.haveCategories(context, Category.NETHER)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_MINESHAFT));


        //Remove vanilla mineshafts from biomes we added our mineshafts to
        BiomeModifications.create(new Identifier(RepurposedStructures.MODID, "remove_vanilla_mineshafts")).add(
                ModificationPhase.REMOVALS,
                context -> BiomeSelection.hasStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.MINESHAFT),
                context -> context.getGenerationSettings().removeStructure(StructureFeature.MINESHAFT));
    }

    private static boolean genericMineshaftCheck(BiomeSelectionContext context) {
        return BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.MINESHAFT)
                && BiomeSelection.isBiomeAllowed(context, "mineshaft")
                && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DUNGEONS //

    public static void addDungeons() {

        addToBiome("jungle_dungeon",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.jungleDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.JUNGLE),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.JUNGLE_DUNGEONS));

        addToBiome("badlands_dungeon",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.badlandsDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.MESA),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.BADLANDS_DUNGEONS));

        addToBiome("dark_forest_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.darkForestDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.FOREST)
                        && BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted"),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.DARK_FOREST_DUNGEONS));

        addToBiome("desert_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.desertDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.DESERT),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.DESERT_DUNGEONS));

        addToBiome("mushroom_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.mushroomDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.MUSHROOM),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.MUSHROOM_DUNGEONS));

        addToBiome("swamp_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.swampDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.SWAMP),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.SWAMP_DUNGEONS));

        addToBiome("snow_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.snowDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.ICY),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.SNOW_DUNGEONS));

        addToBiome("nether_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.netherDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.NETHER),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.NETHER_DUNGEONS));

        addToBiome("end_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.endDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.THEEND)
                        && !BiomeSelection.isBiome(context, BiomeKeys.THE_END, BiomeKeys.SMALL_END_ISLANDS),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, RSConfiguredFeatures.END_DUNGEONS));

        addToBiome("ocean_dungeons",
                (context) -> genericDungeonCheck(context)
                        && RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk != 0
                        && BiomeSelection.haveCategories(context, Category.OCEAN)
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSDungeonsConfig.addDungeonsToModdedBiomes),
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

    private static boolean genericDungeonCheck(BiomeSelectionContext context) {
        return RSConfiguredFeatures.RS_DUNGEONS.stream().noneMatch(context::hasBuiltInFeature)
                && BiomeSelection.isBiomeAllowed(context, "dungeon")
                && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSDungeonsConfig.addDungeonsToModdedBiomes);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WELLS //

    public static void addWells() {

        addToBiome("badlands_well",
                (context) -> genericWellCheck(context)
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.badlandsWellRarityPerChunk != 10000
                        && BiomeSelection.haveCategories(context, Category.MESA),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.BADLANDS_WELL));

        addToBiome("nether_well",
                (context) -> genericWellCheck(context)
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.netherWellRarityPerChunk != 10000
                        && BiomeSelection.haveCategories(context, Category.NETHER),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.NETHER_WELL));

        addToBiome("snow_well",
                (context) -> genericWellCheck(context)
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.snowWellRarityPerChunk != 10000
                        && (BiomeSelection.haveCategories(context, Category.ICY) || BiomeSelection.hasName(context, "snow")),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.SNOW_WELL));

        addToBiome("mossy_stone_well",
                (context) -> genericWellCheck(context)
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.mossyStoneWellRarityPerChunk != 10000
                        && (BiomeSelection.haveCategories(context, Category.SWAMP, Category.JUNGLE)
                            || (BiomeSelection.haveCategories(context, Category.FOREST) && BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted"))),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.MOSSY_STONE_WELL));

        addToBiome("forest_well",
                (context) -> genericWellCheck(context)
                        && RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.forestWellRarityPerChunk != 10000
                        && BiomeSelection.haveCategories(context, Category.FOREST)
                        && !BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted"),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.SURFACE_STRUCTURES, RSConfiguredFeatures.FOREST_WELL));
    }

    private static boolean genericWellCheck(BiomeSelectionContext context) {
        return RSConfiguredFeatures.RS_WELLS.stream().noneMatch(context::hasBuiltInFeature)
                && BiomeSelection.isBiomeAllowed(context, "well")
                && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSWellsConfig.addWellsToModdedBiomes);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SWAMP TREE FEATURES //


    public static void addSwampTreeFeatures() {

        // Exists in vanilla Swamp and can be in modded swamp biomes
        addToBiome("horned_swamp_tree_uncommon",
                (context) -> context.hasBuiltInFeature(ConfiguredFeatures.SWAMP_TREE)
                        && BiomeSelection.isBiomeAllowed(context, "swamp_tree")
                        && RepurposedStructures.RSAllConfig.RSMainConfig.misc.hornedSwampTree
                        && (BiomeSelection.isBiome(context, BiomeKeys.SWAMP)
                            || (RepurposedStructures.RSAllConfig.RSMainConfig.misc.addSwampTreeToModdedBiomes
                                && BiomeSelection.haveCategories(context, Category.SWAMP)
                                && !BiomeSelection.hasNamespace(context, "minecraft"))),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSConfiguredFeatures.HORNED_SWAMP_TREE_UNCOMMON));


        // Only exists in vanilla Swamp Hills biomes
        addToBiome("horned_swamp_tree_common",
                (context) -> context.hasBuiltInFeature(ConfiguredFeatures.SWAMP_TREE)
                        && BiomeSelection.isBiomeAllowed(context, "swamp_tree")
                        && RepurposedStructures.RSAllConfig.RSMainConfig.misc.hornedSwampTree
                        && BiomeSelection.isBiome(context, BiomeKeys.SWAMP_HILLS),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSConfiguredFeatures.HORNED_SWAMP_TREE_COMMON));

        // Remove vanilla swamp tree from Swamp Hills biomes
        BiomeModifications.create(new Identifier(RepurposedStructures.MODID, "remove_vanilla_swamp_trees")).add(
                ModificationPhase.REMOVALS,
                context -> BiomeSelection.isBiome(context, BiomeKeys.SWAMP_HILLS) && context.hasBuiltInFeature(RSConfiguredFeatures.HORNED_SWAMP_TREE_COMMON),
                context -> context.getGenerationSettings().removeBuiltInFeature(ConfiguredFeatures.SWAMP_TREE));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MISC FEATURES //

    public static void addBoulderFeatures() {

        addToBiome("boulder_giant",
                (context) -> BiomeSelection.isBiomeAllowed(context, "boulder")
                        && !BiomeSelection.hasNamespace(context, "ultra_amplified_dimension")
                        && RepurposedStructures.RSAllConfig.RSMainConfig.misc.boulderGiant
                        && (BiomeSelection.isBiome(context, BiomeKeys.GIANT_SPRUCE_TAIGA_HILLS, BiomeKeys.GIANT_TREE_TAIGA_HILLS)
                            || (RepurposedStructures.RSAllConfig.RSMainConfig.misc.addBoulderToModdedBiomes
                                && !BiomeSelection.hasNamespace(context, "minecraft")
                                && BiomeSelection.haveCategories(context, Category.TAIGA)
                                && BiomeSelection.hasName(context, "giant", "redwood"))),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, RSConfiguredFeatures.BOULDER_GIANT));

        // Remove vanilla forest rock
        BiomeModifications.create(new Identifier(RepurposedStructures.MODID, "remove_vanilla_boulder")).add(
                ModificationPhase.REMOVALS,
                context -> context.hasBuiltInFeature(RSConfiguredFeatures.BOULDER_GIANT),
                context -> context.getGenerationSettings().removeBuiltInFeature(ConfiguredFeatures.FOREST_ROCK));

        addToBiome("boulder_tiny",
                (context) -> BiomeSelection.isBiomeAllowed(context, "boulder")
                        && !BiomeSelection.hasNamespace(context, "ultra_amplified_dimension")
                        && RepurposedStructures.RSAllConfig.RSMainConfig.misc.boulderTiny
                        && (BiomeSelection.isBiome(context, BiomeKeys.SNOWY_TAIGA_MOUNTAINS, BiomeKeys.TAIGA_MOUNTAINS)
                            || (RepurposedStructures.RSAllConfig.RSMainConfig.misc.addBoulderToModdedBiomes
                                && !BiomeSelection.hasNamespace(context, "minecraft")
                                && BiomeSelection.haveCategories(context, Category.TAIGA)
                                && BiomeSelection.hasName(context, "mountain", "hill"))),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, RSConfiguredFeatures.BOULDER_TINY));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // STRONGHOLDS //

    public static void addStrongholds() {
        addToBiome("stonebrick_stronghold",
                (context) -> !BiomeSelection.haveCategories(context, Category.NETHER, Category.THEEND)
                        && RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.STRONGHOLD)
                        && BiomeSelection.isBiomeAllowed(context, "stronghold")
                        && ((RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.allowStonebrickStrongholdToVanillaBiomes
                            && BiomeSelection.hasNamespace(context, "minecraft")
                            && context.getBiome().getGenerationSettings().hasStructureFeature(StructureFeature.STRONGHOLD))
                        || (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.addStonebrickStrongholdToModdedBiomes
                            && !BiomeSelection.hasNamespace(context, "minecraft"))),
                context -> {
                    context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.STONEBRICK_STRONGHOLD);
                    context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, RSConfiguredFeatures.STONEBRICK_STRONGHOLD_CHAINS);
                });


        addToBiome("nether_stronghold",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.STRONGHOLD)
                        && BiomeSelection.isBiomeAllowed(context, "stronghold")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.addNetherStrongholdToModdedBiomes),
                context -> {
                    context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_STRONGHOLD);
                    context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, RSConfiguredFeatures.NETHER_STRONGHOLD_CHAINS);
                });


        // Remove vanilla stronghold from biomes we added stonebrick stronghold to
        BiomeModifications.create(new Identifier(RepurposedStructures.MODID, "remove_vanilla_stronghold")).add(
                ModificationPhase.REMOVALS,
                context -> BiomeSelection.isBiomeAllowed(context, "stronghold") 
                        && BiomeSelection.hasStructure(context, RSStructures.STONEBRICK_STRONGHOLD),
                context -> context.getGenerationSettings().removeStructure(StructureFeature.STRONGHOLD));

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OUTPOSTS //

    public static void addOutposts() {

        //Nether based Outposts
        addToBiome("crimson_outpost",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "crimson", "red_")
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.crimsonOutpostMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outpost")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addCrimsonOutpostToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.CRIMSON_OUTPOST));

        addToBiome("warped_outpost",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "warped", "blue")
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.warpedOutpostMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outpost")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addWarpedOutpostToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.WARPED_OUTPOST));

        addToBiome("nether_brick_outpost",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && !BiomeSelection.hasName(context, "crimson", "red_", "warped", "blue")
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.netherBrickOutpostMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outpost")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addNetherBrickOutpostToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_BRICK_OUTPOST));


        addToBiome("outpost_birch",
                (context) -> BiomeSelection.hasName(context, "birch")
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.outpostBirchMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outpost")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addOutpostBirchToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_BIRCH));


        addToBiome("outpost_jungle",
                (context) -> BiomeSelection.haveCategories(context, Category.JUNGLE)
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.outpostJungleMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outpost")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addOutpostJungleToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_JUNGLE));

        addToBiome("outpost_giant_tree_taiga",
                (context) -> (BiomeSelection.haveCategories(context, Category.TAIGA)
                        && BiomeSelection.hasName(context, "giant", "redwood"))
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.outpostGiantTreeTaigaMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outpost")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addOutpostGiantTreeTaigaToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_GIANT_TREE_TAIGA));

        addToBiome("outpost_desert",
                (context) -> BiomeSelection.haveCategories(context, Category.DESERT)
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.outpostDesertMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outpost")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addOutpostDesertToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_DESERT));

        addToBiome("outpost_badlands",
                (context) -> BiomeSelection.haveCategories(context, Category.MESA)
                        && !BiomeSelection.hasName(context, "plateau")
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.outpostBadlandsMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outpost")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addOutpostBadlandsToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_BADLANDS));

        addToBiome("outpost_snowy",
                (context) -> (BiomeSelection.hasName(context, "snow")
                            || (BiomeSelection.haveCategories(context, Category.ICY)
                                && !(BiomeSelection.hasName(context, "ice", "icy", "glacier", "frozen"))))
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.outpostSnowyMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outpost")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addOutpostSnowyToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_SNOWY));

        addToBiome("outpost_icy",
                (context) -> BiomeSelection.haveCategories(context, Category.ICY)
                        && BiomeSelection.hasName(context, "ice", "icy", "glacier", "frozen")
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.outpostIcyMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outpost")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addOutpostIcyToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_ICY));

        //Remove vanilla outposts from biomes we added our outpost to
        BiomeModifications.create(new Identifier(RepurposedStructures.MODID, "remove_vanilla_outposts")).add(
                ModificationPhase.REMOVALS,
                context -> BiomeSelection.hasStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST),
                context -> context.getGenerationSettings().removeStructure(StructureFeature.PILLAGER_OUTPOST));
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SHIPWRECK //

    public static void addShipwrecks() {

        addToBiome("end_shipwreck",
                (context) -> (BiomeSelection.isBiome(context, BiomeKeys.END_HIGHLANDS)
                            || (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.addEndShipwreckToModdedBiomes
                                && BiomeSelection.haveCategories(context, Category.THEEND)))
                        && RepurposedStructures.RSAllConfig.RSShipwrecksConfig.maxChunkDistance.endShipwreckMaxChunkDistance != 1001
                        && BiomeSelection.isBiomeAllowed(context, "shipwreck"),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.END_SHIPWRECK));


        //Nether based Shipwrecks
        addToBiome("crimson_shipwreck",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "crimson", "red_")
                        && RepurposedStructures.RSAllConfig.RSShipwrecksConfig.maxChunkDistance.crimsonShipwreckMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_SHIPWRECK)
                        && BiomeSelection.isBiomeAllowed(context, "shipwreck")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.addCrimsonShipwreckToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.CRIMSON_SHIPWRECK));

        addToBiome("warped_shipwreck",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "warped", "blue")
                        && RepurposedStructures.RSAllConfig.RSShipwrecksConfig.maxChunkDistance.warpedShipwreckMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_SHIPWRECK)
                        && BiomeSelection.isBiomeAllowed(context, "shipwreck")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.addWarpedShipwreckToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.WARPED_SHIPWRECK));

        addToBiome("nether_bricks_shipwreck",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && !BiomeSelection.hasName(context, "crimson", "red_", "warped", "blue")
                        && RepurposedStructures.RSAllConfig.RSShipwrecksConfig.maxChunkDistance.netherBricksShipwreckMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_SHIPWRECK)
                        && BiomeSelection.isBiomeAllowed(context, "shipwreck")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.addNetherBricksShipwreckToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_BRICKS_SHIPWRECK));

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // JUNGLE FORTRESS //

    public static void addJungleFortress() {

        addToBiome("jungle_fortress",
                (context) -> BiomeSelection.haveCategories(context, Category.JUNGLE)
                        && RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.jungleFortressMaxChunkDistance != 1001
                        && BiomeSelection.isBiomeAllowed(context, "fortress")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.addJungleFortressToModdedBiomes),
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
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "basalt", "blackstone")
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherBasaltTempleMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE)
                        && BiomeSelection.isBiomeAllowed(context, "temple")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherBasaltTempleToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_BASALT_TEMPLE));

        addToBiome("nether_crimson_temple",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "crimson", "red_")
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherCrimsonTempleMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE)
                        && BiomeSelection.isBiomeAllowed(context, "temple")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherCrimsonTempleToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_CRIMSON_TEMPLE));

        addToBiome("nether_warped_temple",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "warped", "blue")
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWarpedTempleMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE)
                        && BiomeSelection.isBiomeAllowed(context, "temple")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherWarpedTempleToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_WARPED_TEMPLE));

        addToBiome("nether_soul_temple",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "soul")
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherSoulTempleMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE)
                        && BiomeSelection.isBiomeAllowed(context, "temple")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherSoulTempleToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_SOUL_TEMPLE));

        addToBiome("nether_wasteland_temple",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && !BiomeSelection.hasName(context, "crimson", "red_", "warped", "blue", "soul", "basalt", "blackstone")
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.netherWastelandTempleMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_TEMPLE)
                        && BiomeSelection.isBiomeAllowed(context, "temple")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.addNetherWastelandTempleToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_WASTELAND_TEMPLE));

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Pyramids //

    public static void addPyramids() {

        addToBiome("nether_pyramid",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.netherPyramidMaxChunkDistance != 1001
                        && BiomeSelection.isBiomeAllowed(context, "temple")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.addNetherPyramidToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_PYRAMID));

        addToBiome("badlands_pyramid",
                (context) -> BiomeSelection.haveCategories(context, Category.MESA)
                        && RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.badlandsPyramidMaxChunkDistance != 1001
                        && BiomeSelection.isBiomeAllowed(context, "temple")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.addBadlandsPyramidToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.BADLANDS_PYRAMID));

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // IGLOOS //

    public static void addIgloos() {

        addToBiome("grassy_igloo",
                (context) -> BiomeSelection.haveCategories(context, Category.FOREST, Category.PLAINS)
                        && RepurposedStructures.RSAllConfig.RSMainConfig.igloos.grassyIglooMaxChunkDistance != 1001
                        && BiomeSelection.isBiomeAllowed(context, "igloo")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSMainConfig.igloos.addGrassyIglooToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.GRASSY_IGLOO));

        addToBiome("stone_igloo",
                (context) -> BiomeSelection.haveCategories(context, Category.TAIGA)
                        && BiomeSelection.hasName(context, "giant", "redwood")
                        && RepurposedStructures.RSAllConfig.RSMainConfig.igloos.stoneIglooMaxChunkDistance != 1001
                        && BiomeSelection.isBiomeAllowed(context, "igloo")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSMainConfig.igloos.addStoneIglooToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.STONE_IGLOO));
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // VILLAGES //

    public static void addVillages() {

        addToBiome("badlands_village",
                (context) -> BiomeSelection.haveCategories(context, Category.MESA)
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.badlandsVillageMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE)
                        && BiomeSelection.isBiomeAllowed(context, "village")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.BADLANDS_VILLAGE));

        addToBiome("birch_village",
                (context) -> BiomeSelection.hasName(context, "birch")
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.birchVillageMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE)
                        && BiomeSelection.isBiomeAllowed(context, "village")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.BIRCH_VILLAGE));

        addToBiome("dark_forest_village",
                (context) -> BiomeSelection.haveCategories(context, Category.FOREST)
                        && BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted")
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.darkForestVillageMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE)
                        && BiomeSelection.isBiomeAllowed(context, "village")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.DARK_FOREST_VILLAGE));

        addToBiome("jungle_village",
                (context) -> BiomeSelection.haveCategories(context, Category.JUNGLE)
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.jungleVillageMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE)
                        && BiomeSelection.isBiomeAllowed(context, "village")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes),
                context -> {
                    context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.JUNGLE_VILLAGE);
                    context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSConfiguredFeatures.JUNGLE_VILLAGE_VINES);
                });

        addToBiome("swamp_village",
                (context) -> BiomeSelection.haveCategories(context, Category.SWAMP)
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.swampVillageMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE)
                        && BiomeSelection.isBiomeAllowed(context, "village")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes),
                context -> {
                    context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.SWAMP_VILLAGE);
                    context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSConfiguredFeatures.SWAMP_VILLAGE_VINES);
                });


        addToBiome("mountains_village",
                (context) -> BiomeSelection.haveCategories(context, Category.EXTREME_HILLS)
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.mountainsVillageMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE)
                        && BiomeSelection.isBiomeAllowed(context, "village")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MOUNTAINS_VILLAGE));

        addToBiome("giant_taiga_village",
                (context) -> (BiomeSelection.isBiome(context, BiomeKeys.GIANT_SPRUCE_TAIGA, BiomeKeys.GIANT_TREE_TAIGA)
                            || (RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes
                                && !BiomeSelection.hasNamespace(context, "minecraft")
                                && BiomeSelection.haveCategories(context, Category.TAIGA)
                                && BiomeSelection.hasName(context, "giant", "redwood")))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.giantTaigaVillageMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE)
                        && BiomeSelection.isBiomeAllowed(context, "village"),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.GIANT_TAIGA_VILLAGE));

        addToBiome("crimson_village",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "crimson", "red_")
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.crimsonVillageMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE)
                        && BiomeSelection.isBiomeAllowed(context, "village")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.CRIMSON_VILLAGE));


        addToBiome("warped_village",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "warped", "blue")
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.warpedVillageMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE)
                        && BiomeSelection.isBiomeAllowed(context, "village")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.WARPED_VILLAGE));

        addToBiome("village_oak",
                (context) -> BiomeSelection.haveCategories(context, Category.FOREST)
                        && !(BiomeSelection.hasName(context, "birch", "dark", "spooky", "dead", "haunted"))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.villageOakMaxChunkDistance != 1001
                        && BiomeSelection.isBiomeAllowed(context, "village")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.VILLAGE_OAK));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // RUINED PORTALS //

    public static void addRuinedPortals() {
        addToBiome("ruined_portal_end",
                (context) -> BiomeSelection.haveCategories(context, Category.THEEND)
                        && !BiomeSelection.isBiome(context, BiomeKeys.THE_END)
                        && RepurposedStructures.RSAllConfig.RSMainConfig.ruinedPortals.ruinedPortalEndMaxChunkDistance != 1001
                        && BiomeSelection.isBiomeAllowed(context, "ruined_portals")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSMainConfig.ruinedPortals.addRuinedPortalEndToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.RUINED_PORTAL_END));
    }
}
