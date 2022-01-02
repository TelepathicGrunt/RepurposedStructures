package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;

public final class WitchHuts {
    private WitchHuts() {}

    public static void addWitchHuts(BiomeInjection.BiomeInjectionHelper event) {

        if (RepurposedStructures.RSAllConfig.RSWitchHutsConfig.witchHutsOakAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.WITCH_HUTS_OAK,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.FOREST) &&
                    !BiomeSelection.hasNameTemp(event, "birch", "dark", "spooky", "dead", "haunted")))
        {
            event.addStructure(RSConfiguredStructures.WITCH_HUTS_OAK);
        }

        if (RepurposedStructures.RSAllConfig.RSWitchHutsConfig.witchHutsTaigaAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.WITCH_HUTS_TAIGA,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA)  &&
                    !BiomeSelection.hasNameTemp(event, "giant", "redwood", "old_growth")))
        {
            event.addStructure(RSConfiguredStructures.WITCH_HUTS_TAIGA);
        }

        if (RepurposedStructures.RSAllConfig.RSWitchHutsConfig.witchHutsBirchAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.WITCH_HUTS_BIRCH,
                    () -> BiomeSelection.hasNameTemp(event, "birch")))
        {
            event.addStructure(RSConfiguredStructures.WITCH_HUTS_BIRCH);
        }

        if (RepurposedStructures.RSAllConfig.RSWitchHutsConfig.witchHutsDarkForestAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.WITCH_HUTS_DARK_FOREST,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.FOREST) &&
                    BiomeSelection.hasNameTemp(event, "dark", "spooky", "dead", "haunted")))
        {
            event.addStructure(RSConfiguredStructures.WITCH_HUTS_DARK_FOREST);
        }

        if (RepurposedStructures.RSAllConfig.RSWitchHutsConfig.witchHutsGiantTreeTaigaAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.WITCH_HUTS_GIANT_TREE_TAIGA,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA) &&
                    BiomeSelection.hasNameTemp(event, "giant", "redwood", "old_growth")))
        {
            event.addStructure(RSConfiguredStructures.WITCH_HUTS_GIANT_TREE_TAIGA);
        }
    }
}
