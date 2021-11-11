package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSWitchHutsConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public final class WitchHuts {
    private WitchHuts() {}

    public static void addWitchHuts(BiomeLoadingEvent event) {

        if (RSWitchHutsConfig.witchHutsOakAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.WITCH_HUTS_OAK.get(),
                    () -> BiomeSelection.haveCategories(event, Category.FOREST) &&
                    !BiomeSelection.hasName(event, "birch", "dark", "spooky", "dead", "haunted")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WITCH_HUTS_OAK);
        }

        if (RSWitchHutsConfig.witchHutsTaigaAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.WITCH_HUTS_TAIGA.get(),
                    () -> BiomeSelection.haveCategories(event, Category.TAIGA)  &&
                    !BiomeSelection.hasName(event, "giant", "redwood")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WITCH_HUTS_TAIGA);
        }

        if (RSWitchHutsConfig.witchHutsBirchAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.WITCH_HUTS_BIRCH.get(),
                    () -> BiomeSelection.hasName(event, "birch")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WITCH_HUTS_BIRCH);
        }

        if (RSWitchHutsConfig.witchHutsDarkForestAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.WITCH_HUTS_DARK_FOREST.get(),
                    () -> BiomeSelection.haveCategories(event, Category.FOREST) &&
                    BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WITCH_HUTS_DARK_FOREST);
        }

        if (RSWitchHutsConfig.witchHutsGiantTreeTaigaAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.WITCH_HUTS_GIANT_TREE_TAIGA.get(),
                    () -> BiomeSelection.haveCategories(event, Category.TAIGA) &&
                    BiomeSelection.hasName(event, "giant", "redwood")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WITCH_HUTS_GIANT_TREE_TAIGA);
        }
    }
}
