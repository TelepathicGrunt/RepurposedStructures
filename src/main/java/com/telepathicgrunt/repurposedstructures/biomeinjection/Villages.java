package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BiomeKeys;

public class Villages {

    public static void addVillages() {

        GeneralUtils.addToBiome("badlands_village",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.BADLANDS_VILLAGE,
                                () -> BiomeSelection.haveCategories(context, Category.MESA)
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.badlandsVillageMaxChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.BADLANDS_VILLAGE));

        GeneralUtils.addToBiome("birch_village",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.BIRCH_VILLAGE,
                                () -> BiomeSelection.hasName(context, "birch")
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.birchVillageMaxChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.BIRCH_VILLAGE));

        GeneralUtils.addToBiome("dark_forest_village",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.DARK_FOREST_VILLAGE,
                                () -> BiomeSelection.haveCategories(context, Category.FOREST)
                                && BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted")
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.darkForestVillageMaxChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.DARK_FOREST_VILLAGE));

        GeneralUtils.addToBiome("jungle_village",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.JUNGLE_VILLAGE,
                                () -> BiomeSelection.haveCategories(context, Category.JUNGLE)
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.jungleVillageMaxChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.JUNGLE_VILLAGE));

        GeneralUtils.addToBiome("swamp_village",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.SWAMP_VILLAGE,
                                () -> BiomeSelection.haveCategories(context, Category.SWAMP)
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.swampVillageMaxChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.SWAMP_VILLAGE));

        GeneralUtils.addToBiome("mountains_village",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.MOUNTAINS_VILLAGE,
                                () -> BiomeSelection.haveCategories(context, Category.EXTREME_HILLS)
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.mountainsVillageMaxChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MOUNTAINS_VILLAGE));

        GeneralUtils.addToBiome("giant_taiga_village",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.GIANT_TAIGA_VILLAGE,
                                () -> (BiomeSelection.isBiome(context, BiomeKeys.GIANT_SPRUCE_TAIGA, BiomeKeys.GIANT_TREE_TAIGA)
                                || (BiomeSelection.haveCategories(context, Category.TAIGA) && BiomeSelection.hasName(context, "giant", "redwood")))
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.giantTaigaVillageMaxChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.GIANT_TAIGA_VILLAGE));

        GeneralUtils.addToBiome("crimson_village",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.CRIMSON_VILLAGE,
                                () -> BiomeSelection.haveCategories(context, Category.NETHER)
                                && BiomeSelection.hasName(context, "crimson", "red_")
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.crimsonVillageMaxChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.CRIMSON_VILLAGE));


        GeneralUtils.addToBiome("warped_village",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.WARPED_VILLAGE,
                                () -> BiomeSelection.haveCategories(context, Category.NETHER)
                                && BiomeSelection.hasName(context, "warped", "blue")
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.warpedVillageMaxChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.WARPED_VILLAGE));

        GeneralUtils.addToBiome("village_oak",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.VILLAGE_OAK,
                                () -> BiomeSelection.haveCategories(context, Category.FOREST)
                                && !(BiomeSelection.hasName(context, "birch", "dark", "spooky", "dead", "haunted")))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.villageOakMaxChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.VILLAGE_OAK));
    }
}
