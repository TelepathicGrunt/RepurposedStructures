package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Strongholds {
    private Strongholds() {}

    public static void addStrongholds() {

        BiomeInjection.addStructure(RSConfiguredStructures.STRONGHOLD_NETHER, (event) ->
            (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.STRONGHOLD_NETHER,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.STRONGHOLD_END, (event) ->
            (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.end.endStrongholdAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.STRONGHOLD_END,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.THEEND) &&
                    !BiomeSelection.isBiome(event, Biomes.SMALL_END_ISLANDS)))
        );
    }
}
