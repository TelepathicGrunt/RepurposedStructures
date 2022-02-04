package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Villages {
    private Villages() {}

    public static void addVillages() {
        
        BiomeInjection.addStructure(RSConfiguredStructures.VILLAGE_BADLANDS, (event) ->
            (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.badlandsVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_BADLANDS,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.MESA)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.VILLAGE_BIRCH, (event) ->
            (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.birchVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_BIRCH,
                    () -> BiomeSelection.hasName(event, "birch")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.VILLAGE_DARK_FOREST, (event) ->
            (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.darkForestVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_DARK_FOREST,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.FOREST) &&
                    BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.VILLAGE_JUNGLE, (event) ->
            (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.jungleVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_JUNGLE,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.JUNGLE)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.VILLAGE_SWAMP, (event) ->
            (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.swampVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_SWAMP,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.SWAMP)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.VILLAGE_MOUNTAINS, (event) ->
            (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.mountainsVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_MOUNTAINS,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.EXTREME_HILLS, Biome.BiomeCategory.MOUNTAIN)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.VILLAGE_GIANT_TAIGA, (event) ->
            (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.giantTaigaVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_GIANT_TAIGA,
                    () -> BiomeSelection.isBiome(event, Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA) ||
                    (!BiomeSelection.hasNamespace(event, "minecraft") && BiomeSelection.haveCategories(event, Biome.BiomeCategory.TAIGA) && BiomeSelection.hasName(event, "giant", "redwood", "old_growth"))))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.VILLAGE_OAK, (event) ->
            (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.oakVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_OAK,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.FOREST) &&
                    !BiomeSelection.hasName(event, "birch", "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.VILLAGE_CRIMSON, (event) ->
            (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.crimsonVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_CRIMSON,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasName(event, "crimson", "red_")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.VILLAGE_WARPED, (event) ->
            (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.warpedVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_WARPED,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasName(event, "warped", "blue")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.VILLAGE_MUSHROOM, (event) ->
            (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.villageMushroomAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.VILLAGE_MUSHROOM,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.MUSHROOM)))
        );
        // regexpos1
    }
}
