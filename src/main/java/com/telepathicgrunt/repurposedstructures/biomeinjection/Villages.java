package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.level.biome.Biome.BiomeCategory;

public class Villages {

    public static void addVillages() {

        GeneralUtils.addToBiome("badlands_village",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.VILLAGE_BADLANDS,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.MESA)
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.badlandsVillageAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.VILLAGE_BADLANDS));

        GeneralUtils.addToBiome("birch_village",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.VILLAGE_BIRCH,
                                () -> BiomeSelection.hasName(context, "birch")
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.birchVillageAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.VILLAGE_BIRCH));

        GeneralUtils.addToBiome("dark_forest_village",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.VILLAGE_DARK_FOREST,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.FOREST)
                                && BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted")
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.darkForestVillageAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.VILLAGE_DARK_FOREST));

        GeneralUtils.addToBiome("jungle_village",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.VILLAGE_JUNGLE,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.JUNGLE)
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.jungleVillageAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.VILLAGE_JUNGLE));

        GeneralUtils.addToBiome("swamp_village",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.VILLAGE_SWAMP,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.SWAMP)
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.swampVillageAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.VILLAGE_SWAMP));

        GeneralUtils.addToBiome("mountains_village",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.VILLAGE_MOUNTAINS,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.EXTREME_HILLS)
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.mountainsVillageAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.VILLAGE_MOUNTAINS));

        GeneralUtils.addToBiome("giant_taiga_village",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.VILLAGE_GIANT_TAIGA,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.TAIGA) && BiomeSelection.hasName(context, "giant", "redwood")
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.giantTaigaVillageAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.VILLAGE_GIANT_TAIGA));

        GeneralUtils.addToBiome("crimson_village",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.VILLAGE_CRIMSON,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.NETHER)
                                && BiomeSelection.hasName(context, "crimson", "red_")
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.crimsonVillageAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.VILLAGE_CRIMSON));


        GeneralUtils.addToBiome("warped_village",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.VILLAGE_WARPED,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.NETHER)
                                && BiomeSelection.hasName(context, "warped", "blue")
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.warpedVillageAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.VILLAGE_WARPED));

        GeneralUtils.addToBiome("village_oak",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.VILLAGE_OAK,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.FOREST)
                                && !(BiomeSelection.hasName(context, "birch", "dark", "spooky", "dead", "haunted")))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.oakVillageAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.VILLAGE_OAK));

        // regexpos1
    }
}
