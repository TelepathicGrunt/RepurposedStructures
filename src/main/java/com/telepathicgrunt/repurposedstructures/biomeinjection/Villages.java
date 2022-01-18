package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Villages {
    private Villages() {}

    public static void addVillages(BiomeInjection.BiomeInjectionHelper event) {
        
        if (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.badlandsVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_BADLANDS,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MESA)))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_BADLANDS);
        }

        if (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.birchVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_BIRCH,
                    () -> BiomeSelection.hasNameTemp(event, "birch")))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_BIRCH);
        }

        if (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.darkForestVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_DARK_FOREST,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.FOREST) &&
                    BiomeSelection.hasNameTemp(event, "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony")))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_DARK_FOREST);
        }

        if (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.jungleVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_JUNGLE,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.JUNGLE)))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_JUNGLE);
        }

        if (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.swampVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_SWAMP,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.SWAMP)))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_SWAMP);
        }

        if (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.mountainsVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_MOUNTAINS,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.EXTREME_HILLS, Biome.BiomeCategory.MOUNTAIN)))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_MOUNTAINS);
        }

        if (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.giantTaigaVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_GIANT_TAIGA,
                    () -> BiomeSelection.isBiomeTemp(event, Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA) ||
                    (!BiomeSelection.hasNamespaceTemp(event, "minecraft") && BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA) && BiomeSelection.hasNameTemp(event, "giant", "redwood", "old_growth"))))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_GIANT_TAIGA);
        }

        if (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.oakVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_OAK,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.FOREST) &&
                    !BiomeSelection.hasNameTemp(event, "birch", "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony")))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_OAK);
        }

        if (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.crimsonVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_CRIMSON,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "crimson", "red_")))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_CRIMSON);
        }

        if (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.warpedVillageAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_WARPED,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "warped", "blue")))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_WARPED);
        }

        if (RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.villageMushroomAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_MUSHROOM,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MUSHROOM)))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_MUSHROOM);
        }
        // regexpos1
    }
}
