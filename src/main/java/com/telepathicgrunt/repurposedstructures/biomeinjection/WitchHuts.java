package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSWitchHutsConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;

public final class WitchHuts {
    private WitchHuts() {}

    public static void addWitchHuts(TemporaryBiomeInjection.BiomeInjectionHelper event) {

        if (RSWitchHutsConfig.witchHutsOakAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.WITCH_HUTS_OAK.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.FOREST) &&
                    !BiomeSelection.hasNameTemp(event, "birch", "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony")))
        {
            event.addStructure(RSConfiguredStructures.WITCH_HUTS_OAK);
        }

        if (RSWitchHutsConfig.witchHutsTaigaAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.WITCH_HUTS_TAIGA.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA)  &&
                    !BiomeSelection.hasNameTemp(event, "giant", "redwood", "old_growth")))
        {
            event.addStructure(RSConfiguredStructures.WITCH_HUTS_TAIGA);
        }

        if (RSWitchHutsConfig.witchHutsBirchAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.WITCH_HUTS_BIRCH.get(),
                    () -> BiomeSelection.hasNameTemp(event, "birch")))
        {
            event.addStructure(RSConfiguredStructures.WITCH_HUTS_BIRCH);
        }

        if (RSWitchHutsConfig.witchHutsDarkForestAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.WITCH_HUTS_DARK_FOREST.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.FOREST) &&
                    BiomeSelection.hasNameTemp(event, "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony")))
        {
            event.addStructure(RSConfiguredStructures.WITCH_HUTS_DARK_FOREST);
        }

        if (RSWitchHutsConfig.witchHutsGiantTreeTaigaAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.WITCH_HUTS_GIANT_TREE_TAIGA.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA) &&
                    BiomeSelection.hasNameTemp(event, "giant", "redwood", "old_growth")))
        {
            event.addStructure(RSConfiguredStructures.WITCH_HUTS_GIANT_TREE_TAIGA);
        }
    }
}
