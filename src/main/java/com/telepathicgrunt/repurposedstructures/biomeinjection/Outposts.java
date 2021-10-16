package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.feature.StructureFeature;

public class Outposts {

    public static void addOutposts() {

        //Nether based Outposts
        GeneralUtils.addToBiome("crimson_outpost",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.OUTPOST_CRIMSON,
                            () -> BiomeSelection.haveCategories(context, BiomeCategory.NETHER)
                            && BiomeSelection.hasName(context, "crimson", "red_")
                            && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST))
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.crimsonOutpostAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_CRIMSON));

        GeneralUtils.addToBiome("warped_outpost",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.OUTPOST_WARPED,
                            () -> BiomeSelection.haveCategories(context, BiomeCategory.NETHER)
                            && BiomeSelection.hasName(context, "warped", "blue")
                            && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST))
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.warpedOutpostAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_WARPED));

        GeneralUtils.addToBiome("nether_brick_outpost",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.OUTPOST_NETHER_BRICK,
                            () -> BiomeSelection.haveCategories(context, BiomeCategory.NETHER)
                            && !BiomeSelection.hasName(context, "crimson", "red_", "warped", "blue")
                            && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST))
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.netherBrickOutpostAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_NETHER_BRICK));


        GeneralUtils.addToBiome("outpost_birch",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.OUTPOST_BIRCH,
                            () -> BiomeSelection.hasName(context, "birch")
                            && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST))
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostBirchAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_BIRCH));


        GeneralUtils.addToBiome("outpost_jungle",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.OUTPOST_JUNGLE,
                            () -> BiomeSelection.haveCategories(context, BiomeCategory.JUNGLE)
                            && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST))
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostJungleAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_JUNGLE));

        GeneralUtils.addToBiome("outpost_giant_tree_taiga",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.OUTPOST_GIANT_TREE_TAIGA,
                            () -> (BiomeSelection.haveCategories(context, BiomeCategory.TAIGA)
                            && BiomeSelection.hasName(context, "giant", "redwood"))
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST))
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostGiantTreeTaigaAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_GIANT_TREE_TAIGA));

        GeneralUtils.addToBiome("outpost_desert",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.OUTPOST_DESERT,
                            () -> BiomeSelection.haveCategories(context, BiomeCategory.DESERT)
                            && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST))
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostDesertAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_DESERT));

        GeneralUtils.addToBiome("outpost_badlands",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.OUTPOST_BADLANDS,
                            () -> BiomeSelection.haveCategories(context, BiomeCategory.MESA)
                            && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST))
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostBadlandsAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_BADLANDS));

        GeneralUtils.addToBiome("outpost_snowy",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.OUTPOST_SNOWY,
                            () -> (BiomeSelection.hasName(context, "snow")
                            || (BiomeSelection.haveCategories(context, BiomeCategory.ICY) && !(BiomeSelection.hasName(context, "ice", "icy", "glacier", "frozen"))))
                                && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST))
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostSnowyAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_SNOWY));

        GeneralUtils.addToBiome("outpost_icy",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.OUTPOST_ICY,
                            () -> BiomeSelection.haveCategories(context, BiomeCategory.ICY)
                            && BiomeSelection.hasName(context, "ice", "icy", "glacier", "frozen")
                            && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST))
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostIcyAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_ICY));

        GeneralUtils.addToBiome("outpost_taiga",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.OUTPOST_TAIGA,
                            () -> BiomeSelection.haveCategories(context, BiomeCategory.TAIGA)
                            && !BiomeSelection.hasName(context, "giant", "redwood", "snow", "ice", "icy", "glacier", "frozen")
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostTaigaAverageChunkDistance != 1001),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_TAIGA));

        GeneralUtils.addToBiome("outpost_oak",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.OUTPOST_OAK,
                            () -> BiomeSelection.haveCategories(context, BiomeCategory.FOREST)
                            && !(BiomeSelection.hasName(context, "birch", "dark", "spooky", "dead", "haunted"))
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostOakAverageChunkDistance != 1001),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_OAK));

        GeneralUtils.addToBiome("outpost_end",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.OUTPOST_END,
                            () -> BiomeSelection.haveCategories(context, BiomeCategory.THEEND)
                            && !BiomeSelection.isBiome(context, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS)
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostEndAverageChunkDistance != 1001),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_END));

        //Remove vanilla outposts from biomes we added our outpost to
        BiomeModifications.create(new ResourceLocation(RepurposedStructures.MODID, "remove_vanilla_outposts")).add(
                ModificationPhase.REMOVALS,
                context -> BiomeSelection.hasStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST),
                context -> context.getGenerationSettings().removeStructure(StructureFeature.PILLAGER_OUTPOST));
    }
}
