package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSOutpostsConfig;
import com.telepathicgrunt.repurposedstructures.mixin.structures.StructureFeaturesAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Outposts {
    private Outposts() {}

    public static void addOutposts(TemporaryBiomeInjection.BiomeInjectionHelper event) {

        if (RSOutpostsConfig.outpostCrimsonAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_CRIMSON.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "crimson", "red_")))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_CRIMSON);
        }

        if (RSOutpostsConfig.outpostWarpedAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_WARPED.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "warped", "blue")))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_WARPED);
        }

        if (RSOutpostsConfig.outpostNetherBrickAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_NETHER_BRICK.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    !BiomeSelection.hasNameTemp(event, "crimson", "red_", "warped", "blue")))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_NETHER_BRICK);
        }

        if (RSOutpostsConfig.outpostEndAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_END.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.THEEND)
                    && !BiomeSelection.isBiomeTemp(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS)))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_END);
        }

        if (RSOutpostsConfig.outpostBirchAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_BIRCH.get(),
                    () -> BiomeSelection.hasNameTemp(event, "birch")))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_BIRCH);
            event.removeStructure(StructureFeaturesAccessor.getPILLAGER_OUTPOST());
        }

        if (RSOutpostsConfig.outpostJungleAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_JUNGLE.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.JUNGLE)))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_JUNGLE);
            event.removeStructure(StructureFeaturesAccessor.getPILLAGER_OUTPOST());
        }

        if (RSOutpostsConfig.outpostGiantTreeTaigaAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_GIANT_TREE_TAIGA.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA) &&
                    BiomeSelection.hasNameTemp(event, "giant", "redwood", "old_growth")))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_GIANT_TREE_TAIGA);
            event.removeStructure(StructureFeaturesAccessor.getPILLAGER_OUTPOST());
        }

        if (RSOutpostsConfig.outpostDesertAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_DESERT.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.DESERT)))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_DESERT);
            event.removeStructure(StructureFeaturesAccessor.getPILLAGER_OUTPOST());
        }

        if (RSOutpostsConfig.outpostBadlandsAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_BADLANDS.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MESA)))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_BADLANDS);
            event.removeStructure(StructureFeaturesAccessor.getPILLAGER_OUTPOST());
        }

        if (RSOutpostsConfig.outpostSnowyAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_SNOWY.get(),
                    () -> BiomeSelection.isBiomeTemp(event, Biomes.GROVE) ||
                            (!BiomeSelection.hasNameTemp(event, "ice", "icy", "glacier", "glacial", "frozen") &&
                                ((BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.ICY) && BiomeSelection.hasNameTemp(event, "snow")) ||
                                (BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MOUNTAIN) && (event.biome.getBaseTemperature() < 0 || BiomeSelection.hasNameTemp(event, "snow")))))))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_SNOWY);
            event.removeStructure(StructureFeaturesAccessor.getPILLAGER_OUTPOST());
        }

        if (RSOutpostsConfig.outpostIcyAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_ICY.get(),
                    () -> BiomeSelection.hasNameTemp(event, "ice", "icy", "glacier", "glacial", "frozen") &&
                            (BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.ICY) ||
                            (BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MOUNTAIN) && event.biome.getBaseTemperature() < 0))))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_ICY);
            event.removeStructure(StructureFeaturesAccessor.getPILLAGER_OUTPOST());
        }

        if (RSOutpostsConfig.outpostTaigaAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_TAIGA.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA) && !BiomeSelection.isBiomeTemp(event, Biomes.GROVE) &&
                    !BiomeSelection.hasNameTemp(event, "giant", "redwood", "snow", "ice", "icy", "glacier", "glacial", "frozen")))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_TAIGA);
            event.removeStructure(StructureFeaturesAccessor.getPILLAGER_OUTPOST());
        }

        if (RSOutpostsConfig.outpostOakAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_OAK.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.FOREST) &&
                    !(BiomeSelection.hasNameTemp(event, "birch", "dark", "spooky", "dead", "haunted"))))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_OAK);
            event.removeStructure(StructureFeaturesAccessor.getPILLAGER_OUTPOST());
        }
    }
}
