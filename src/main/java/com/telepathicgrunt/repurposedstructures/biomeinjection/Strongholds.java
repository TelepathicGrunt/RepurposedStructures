package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSStrongholdsConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public final class Strongholds {
    private Strongholds() {}

    public static void addStrongholds(BiomeLoadingEvent event) {

        if (RSStrongholdsConfig.netherStrongholdMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.STRONGHOLD_NETHER.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.STRONGHOLD_NETHER);
        }

        if (RSStrongholdsConfig.strongholdEndMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.STRONGHOLD_END.get(),
                    () -> BiomeSelection.haveCategories(event, Category.THEEND) &&
                    !BiomeSelection.isBiome(event, Biomes.SMALL_END_ISLANDS)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.STRONGHOLD_END);
        }
    }
}
