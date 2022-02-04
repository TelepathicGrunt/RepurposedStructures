package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;

public final class WitchHuts {
    private WitchHuts() {}

    public static void addWitchHuts() {

        BiomeInjection.addStructure(RSConfiguredStructures.WITCH_HUTS_OAK, (event) ->
            (RepurposedStructures.RSAllConfig.RSWitchHutsConfig.witchHutsOakAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.WITCH_HUTS_OAK,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.FOREST) &&
                    !BiomeSelection.hasName(event, "birch", "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.WITCH_HUTS_TAIGA, (event) ->
            (RepurposedStructures.RSAllConfig.RSWitchHutsConfig.witchHutsTaigaAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.WITCH_HUTS_TAIGA,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.TAIGA)  &&
                    !BiomeSelection.hasName(event, "giant", "redwood", "old_growth")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.WITCH_HUTS_BIRCH, (event) ->
            (RepurposedStructures.RSAllConfig.RSWitchHutsConfig.witchHutsBirchAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.WITCH_HUTS_BIRCH,
                    () -> BiomeSelection.hasName(event, "birch")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.WITCH_HUTS_DARK_FOREST, (event) ->
            (RepurposedStructures.RSAllConfig.RSWitchHutsConfig.witchHutsDarkForestAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.WITCH_HUTS_DARK_FOREST,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.FOREST) &&
                    BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.WITCH_HUTS_GIANT_TREE_TAIGA, (event) ->
            (RepurposedStructures.RSAllConfig.RSWitchHutsConfig.witchHutsGiantTreeTaigaAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.WITCH_HUTS_GIANT_TREE_TAIGA,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.TAIGA) &&
                    BiomeSelection.hasName(event, "giant", "redwood", "old_growth")))
        );
    }
}
