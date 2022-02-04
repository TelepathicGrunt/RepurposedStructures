package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.structures.StructureFeaturesAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Outposts {
    private Outposts() {}

    public static void addOutposts() {

        BiomeInjection.addStructure(RSConfiguredStructures.OUTPOST_CRIMSON, (event) ->
            (RepurposedStructures.RSAllConfig.RSOutpostsConfig.crimsonOutpostAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_CRIMSON,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasName(event, "crimson", "red_")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.OUTPOST_WARPED, (event) ->
            (RepurposedStructures.RSAllConfig.RSOutpostsConfig.warpedOutpostAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_WARPED,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasName(event, "warped", "blue")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.OUTPOST_NETHER_BRICK, (event) ->
            (RepurposedStructures.RSAllConfig.RSOutpostsConfig.netherBrickOutpostAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_NETHER_BRICK,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER) &&
                    !BiomeSelection.hasName(event, "crimson", "red_", "warped", "blue")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.OUTPOST_END, (event) ->
            (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostEndAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_END,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.THEEND)
                    && !BiomeSelection.isBiome(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.OUTPOST_BIRCH, (event) ->
            (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostBirchAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_BIRCH,
                    () -> BiomeSelection.hasName(event, "birch")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.OUTPOST_JUNGLE, (event) ->
            (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostJungleAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_JUNGLE,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.JUNGLE)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.OUTPOST_GIANT_TREE_TAIGA, (event) ->
            (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostGiantTreeTaigaAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_GIANT_TREE_TAIGA,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.TAIGA) &&
                    BiomeSelection.hasName(event, "giant", "redwood", "old_growth")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.OUTPOST_DESERT, (event) ->
            (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostDesertAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_DESERT,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.DESERT)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.OUTPOST_BADLANDS, (event) ->
            (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostBadlandsAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_BADLANDS,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.MESA)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.OUTPOST_SNOWY, (event) ->
            (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostSnowyAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_SNOWY,
                    () -> BiomeSelection.isBiome(event, Biomes.GROVE) ||
                            (!BiomeSelection.hasName(event, "ice", "icy", "glacier", "glacial", "frozen") &&
                                ((BiomeSelection.haveCategories(event, Biome.BiomeCategory.ICY) && BiomeSelection.hasName(event, "snow")) ||
                                (BiomeSelection.haveCategories(event, Biome.BiomeCategory.MOUNTAIN) && (event.getBiome().getBaseTemperature() < 0 || BiomeSelection.hasName(event, "snow")))))))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.OUTPOST_ICY, (event) ->
            (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostIcyAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_ICY,
                    () -> BiomeSelection.hasName(event, "ice", "icy", "glacier", "glacial", "frozen") &&
                            (BiomeSelection.haveCategories(event, Biome.BiomeCategory.ICY) ||
                            (BiomeSelection.haveCategories(event, Biome.BiomeCategory.MOUNTAIN) && event.getBiome().getBaseTemperature() < 0))))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.OUTPOST_TAIGA, (event) ->
            (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostTaigaAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_TAIGA,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.TAIGA) && !BiomeSelection.isBiome(event, Biomes.GROVE) &&
                    !BiomeSelection.hasName(event, "giant", "redwood", "snow", "ice", "icy", "glacier", "glacial", "frozen")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.OUTPOST_OAK, (event) ->
            (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostOakAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_OAK,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.FOREST) &&
                    !(BiomeSelection.hasName(event, "birch", "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony"))))
        );
    }
}
