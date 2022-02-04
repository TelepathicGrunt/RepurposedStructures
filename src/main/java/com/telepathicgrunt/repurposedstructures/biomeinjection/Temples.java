package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;

public final class Temples {
    private Temples() {}

    public static void addTemples() {

        BiomeInjection.addStructure(RSConfiguredStructures.NETHER_BASALT_TEMPLE, (event) ->
            (RepurposedStructures.RSAllConfig.RSTemplesConfig.netherBasaltTempleAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.TEMPLE_NETHER_BASALT,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasName(event, "basalt", "blackstone")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.NETHER_CRIMSON_TEMPLE, (event) ->
            (RepurposedStructures.RSAllConfig.RSTemplesConfig.netherCrimsonTempleAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.TEMPLE_NETHER_BASALT,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasName(event, "crimson", "red_")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.NETHER_WARPED_TEMPLE, (event) ->
            (RepurposedStructures.RSAllConfig.RSTemplesConfig.netherWarpedTempleAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.TEMPLE_NETHER_BASALT,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasName(event, "warped", "blue")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.NETHER_SOUL_TEMPLE, (event) ->
            (RepurposedStructures.RSAllConfig.RSTemplesConfig.netherSoulTempleAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.TEMPLE_NETHER_BASALT,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasName(event, "soul")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.NETHER_WASTELAND_TEMPLE, (event) ->
            (RepurposedStructures.RSAllConfig.RSTemplesConfig.netherWastelandTempleAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.TEMPLE_NETHER_BASALT,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER) &&
                    !BiomeSelection.hasName(event, "basalt", "blackstone", "crimson", "red_", "warped", "blue", "soul")))
        );
    }
}
