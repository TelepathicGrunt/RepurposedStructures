package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.biome.Biome.Category;

public class WitchHuts {

    public static void addWitchHuts() {
        GeneralUtils.addToBiome("witch_hut_oak",
                (context) -> BiomeSelection.haveCategories(context, Category.FOREST)
                        && !(BiomeSelection.hasName(context, "birch", "dark", "spooky", "dead", "haunted"))
                        && BiomeSelection.isBiomeAllowed(context, "witch_huts")
                        && RepurposedStructures.RSAllConfig.RSWitchHutsConfig.maxChunkDistance.witchHutsOakMaxChunkDistance != 1001
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSWitchHutsConfig.blacklist.addWitchHutsOakToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.WITCH_HUTS_OAK));

        GeneralUtils.addToBiome("witch_hut_taiga",
                (context) -> BiomeSelection.haveCategories(context, Category.TAIGA)
                        && !BiomeSelection.hasName(context, "giant", "redwood")
						&& BiomeSelection.isBiomeAllowed(context, "witch_huts")
                        && RepurposedStructures.RSAllConfig.RSWitchHutsConfig.maxChunkDistance.witchHutsTaigaMaxChunkDistance != 1001
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSWitchHutsConfig.blacklist.addWitchHutsTaigaToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.WITCH_HUTS_TAIGA));

        GeneralUtils.addToBiome("witch_hut_birch",
                (context) -> BiomeSelection.hasName(context, "birch")
						&& BiomeSelection.isBiomeAllowed(context, "witch_huts")
                        && RepurposedStructures.RSAllConfig.RSWitchHutsConfig.maxChunkDistance.witchHutsBirchMaxChunkDistance != 1001
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSWitchHutsConfig.blacklist.addWitchHutsBirchToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.WITCH_HUTS_BIRCH));

        GeneralUtils.addToBiome("witch_hut_dark_forest",
                (context) -> BiomeSelection.haveCategories(context, Category.FOREST)
                        && BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted")
						&& BiomeSelection.isBiomeAllowed(context, "witch_huts")
                        && RepurposedStructures.RSAllConfig.RSWitchHutsConfig.maxChunkDistance.witchHutsDarkForestMaxChunkDistance != 1001
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSWitchHutsConfig.blacklist.addWitchHutsDarkForestToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.WITCH_HUTS_DARK_FOREST));

        GeneralUtils.addToBiome("witch_hut_giant_tree_taiga",
                (context) -> BiomeSelection.haveCategories(context, Category.TAIGA)
                        && BiomeSelection.hasName(context, "giant", "redwood")
						&& BiomeSelection.isBiomeAllowed(context, "witch_huts")
                        && RepurposedStructures.RSAllConfig.RSWitchHutsConfig.maxChunkDistance.witchHutsGiantTreeTaigaMaxChunkDistance != 1001
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSWitchHutsConfig.blacklist.addWitchHutsGiantTreeTaigaToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.WITCH_HUTS_GIANT_TREE_TAIGA));
    }
}
