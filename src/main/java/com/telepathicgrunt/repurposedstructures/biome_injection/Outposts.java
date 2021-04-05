package com.telepathicgrunt.repurposedstructures.biome_injection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.feature.StructureFeature;

public class Outposts {

    public static void addOutposts() {

        //Nether based Outposts
        GeneralUtils.addToBiome("crimson_outpost",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "crimson", "red_")
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.crimsonOutpostMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outposts")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addCrimsonOutpostToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.CRIMSON_OUTPOST));

        GeneralUtils.addToBiome("warped_outpost",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "warped", "blue")
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.warpedOutpostMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outposts")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addWarpedOutpostToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.WARPED_OUTPOST));

        GeneralUtils.addToBiome("nether_brick_outpost",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && !BiomeSelection.hasName(context, "crimson", "red_", "warped", "blue")
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.netherBrickOutpostMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outposts")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addNetherBrickOutpostToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_BRICK_OUTPOST));


        GeneralUtils.addToBiome("outpost_birch",
                (context) -> BiomeSelection.hasName(context, "birch")
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.outpostBirchMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outposts")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addOutpostBirchToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_BIRCH));


        GeneralUtils.addToBiome("outpost_jungle",
                (context) -> BiomeSelection.haveCategories(context, Category.JUNGLE)
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.outpostJungleMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outposts")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addOutpostJungleToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_JUNGLE));

        GeneralUtils.addToBiome("outpost_giant_tree_taiga",
                (context) -> (BiomeSelection.haveCategories(context, Category.TAIGA)
                        && BiomeSelection.hasName(context, "giant", "redwood"))
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.outpostGiantTreeTaigaMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outposts")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addOutpostGiantTreeTaigaToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_GIANT_TREE_TAIGA));

        GeneralUtils.addToBiome("outpost_desert",
                (context) -> BiomeSelection.haveCategories(context, Category.DESERT)
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.outpostDesertMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outposts")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addOutpostDesertToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_DESERT));

        GeneralUtils.addToBiome("outpost_badlands",
                (context) -> BiomeSelection.haveCategories(context, Category.MESA)
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.outpostBadlandsMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outposts")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addOutpostBadlandsToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_BADLANDS));

        GeneralUtils.addToBiome("outpost_snowy",
                (context) -> (BiomeSelection.hasName(context, "snow")
                            || (BiomeSelection.haveCategories(context, Category.ICY)
                                && !(BiomeSelection.hasName(context, "ice", "icy", "glacier", "frozen"))))
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.outpostSnowyMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outposts")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addOutpostSnowyToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_SNOWY));

        GeneralUtils.addToBiome("outpost_icy",
                (context) -> BiomeSelection.haveCategories(context, Category.ICY)
                        && BiomeSelection.hasName(context, "ice", "icy", "glacier", "frozen")
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.outpostIcyMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST)
                        && BiomeSelection.isBiomeAllowed(context, "outposts")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addOutpostIcyToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_ICY));

        GeneralUtils.addToBiome("outpost_taiga",
                (context) -> BiomeSelection.haveCategories(context, Category.TAIGA)
                        && !BiomeSelection.hasName(context, "giant", "redwood", "snow", "ice", "icy", "glacier", "frozen")
                        && BiomeSelection.isBiomeAllowed(context, "outposts")
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.outpostTaigaMaxChunkDistance != 1001
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addOutpostTaigaToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_TAIGA));

        GeneralUtils.addToBiome("outpost_oak",
                (context) -> BiomeSelection.haveCategories(context, Category.FOREST)
                        && !(BiomeSelection.hasName(context, "birch", "dark", "spooky", "dead", "haunted"))
                        && BiomeSelection.isBiomeAllowed(context, "outposts")
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.outpostOakMaxChunkDistance != 1001
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addOutpostOakToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_OAK));

        GeneralUtils.addToBiome("outpost_end",
                (context) ->  BiomeSelection.haveCategories(context, Category.THEEND)
                        && !BiomeSelection.isBiome(context, BiomeKeys.THE_END, BiomeKeys.SMALL_END_ISLANDS, BiomeKeys.END_BARRENS)
                        && BiomeSelection.isBiomeAllowed(context, "outposts")
                        && RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.outpostEndMaxChunkDistance != 1001
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSOutpostsConfig.outposts.addOutpostEndToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OUTPOST_END));
        // regexpos1

        //Remove vanilla outposts from biomes we added our outpost to
        BiomeModifications.create(new Identifier(RepurposedStructures.MODID, "remove_vanilla_outposts")).add(
                ModificationPhase.REMOVALS,
                context -> BiomeSelection.hasStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_OUTPOST),
                context -> context.getGenerationSettings().removeStructure(StructureFeature.PILLAGER_OUTPOST));
    }
}
