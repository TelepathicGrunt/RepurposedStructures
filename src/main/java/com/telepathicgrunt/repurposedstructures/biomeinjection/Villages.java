package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSVillagesConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Villages {
    private Villages() {}

    public static void addVillages(TemporaryBiomeInjection.BiomeInjectionHelper event) {
        
        if (RSVillagesConfig.villageBadlandsAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_BADLANDS.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MESA)))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_BADLANDS);
        }

        if (RSVillagesConfig.villageBirchAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_BIRCH.get(),
                    () -> BiomeSelection.hasNameTemp(event, "birch")))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_BIRCH);
        }

        if (RSVillagesConfig.villageDarkForestAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_DARK_FOREST.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.FOREST) &&
                    BiomeSelection.hasNameTemp(event, "dark", "spooky", "dead", "haunted")))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_DARK_FOREST);
        }

        if (RSVillagesConfig.villageJungleAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_JUNGLE.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.JUNGLE)))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_JUNGLE);
        }

        if (RSVillagesConfig.villageSwampAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_SWAMP.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.SWAMP)))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_SWAMP);
        }

        if (RSVillagesConfig.villageMountainsAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_MOUNTAINS.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.EXTREME_HILLS, Biome.BiomeCategory.MOUNTAIN)))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_MOUNTAINS);
        }

        if (RSVillagesConfig.villageGiantTaigaAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_GIANT_TAIGA.get(),
                    () -> BiomeSelection.isBiomeTemp(event, Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA) ||
                    (!BiomeSelection.hasNamespaceTemp(event, "minecraft") && BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA) && BiomeSelection.hasNameTemp(event, "giant", "redwood", "old_growth"))))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_GIANT_TAIGA);
        }

        if (RSVillagesConfig.villageOakAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_OAK.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.FOREST) &&
                    !BiomeSelection.hasNameTemp(event, "birch", "dark", "spooky", "dead", "haunted")))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_OAK);
        }

        if (RSVillagesConfig.villageCrimsonAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_CRIMSON.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "crimson", "red_")))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_CRIMSON);
        }

        if (RSVillagesConfig.villageWarpedAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_WARPED.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "warped", "blue")))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_WARPED);
        }

        if (RSVillagesConfig.villageMushroomAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.VILLAGE_MUSHROOM.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MUSHROOM)))
        {
            event.addStructure(RSConfiguredStructures.VILLAGE_MUSHROOM);
        }
        // regexpos1
    }
}
