package com.telepathicgrunt.repurposedstructures.biomeinjection.temp;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.structures.StructureFeaturesAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Outposts {
    private Outposts() {}

    public static void addOutposts(TemporaryBiomeInjection.BiomeInjectionHelper event) {

        if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.crimsonOutpostAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_CRIMSON,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "crimson", "red_")))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_CRIMSON);
        }

        if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.warpedOutpostAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_WARPED,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "warped", "blue")))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_WARPED);
        }

        if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.netherBrickOutpostAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_NETHER_BRICK,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    !BiomeSelection.hasNameTemp(event, "crimson", "red_", "warped", "blue")))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_NETHER_BRICK);
        }

        if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostEndAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_END,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.THEEND)
                    && !BiomeSelection.isBiomeTemp(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS)))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_END);
        }

        if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostBirchAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_BIRCH,
                    () -> BiomeSelection.hasNameTemp(event, "birch")))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_BIRCH);
            event.removeStructure(StructureFeaturesAccessor.getPILLAGER_OUTPOST());
        }

        if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostJungleAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_JUNGLE,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.JUNGLE)))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_JUNGLE);
            event.removeStructure(StructureFeaturesAccessor.getPILLAGER_OUTPOST());
        }

        if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostGiantTreeTaigaAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_GIANT_TREE_TAIGA,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA) &&
                    BiomeSelection.hasNameTemp(event, "giant", "redwood", "old_growth")))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_GIANT_TREE_TAIGA);
            event.removeStructure(StructureFeaturesAccessor.getPILLAGER_OUTPOST());
        }

        if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostDesertAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_DESERT,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.DESERT)))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_DESERT);
            event.removeStructure(StructureFeaturesAccessor.getPILLAGER_OUTPOST());
        }

        if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostBadlandsAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_BADLANDS,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MESA)))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_BADLANDS);
            event.removeStructure(StructureFeaturesAccessor.getPILLAGER_OUTPOST());
        }

        if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostSnowyAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_SNOWY,
                    () -> BiomeSelection.isBiomeTemp(event, Biomes.GROVE) ||
                            (!BiomeSelection.hasNameTemp(event, "ice", "icy", "glacier", "glacial", "frozen") &&
                                ((BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.ICY) && BiomeSelection.hasNameTemp(event, "snow")) ||
                                (BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MOUNTAIN) && (event.biome.getBaseTemperature() < 0 || BiomeSelection.hasNameTemp(event, "snow")))))))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_SNOWY);
            event.removeStructure(StructureFeaturesAccessor.getPILLAGER_OUTPOST());
        }

        if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostIcyAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_ICY,
                    () -> BiomeSelection.hasNameTemp(event, "ice", "icy", "glacier", "glacial", "frozen") &&
                            (BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.ICY) ||
                            (BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MOUNTAIN) && event.biome.getBaseTemperature() < 0))))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_ICY);
            event.removeStructure(StructureFeaturesAccessor.getPILLAGER_OUTPOST());
        }

        if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostTaigaAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_TAIGA,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA) && !BiomeSelection.isBiomeTemp(event, Biomes.GROVE) &&
                    !BiomeSelection.hasNameTemp(event, "giant", "redwood", "snow", "ice", "icy", "glacier", "glacial", "frozen")))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_TAIGA);
            event.removeStructure(StructureFeaturesAccessor.getPILLAGER_OUTPOST());
        }

        if (RepurposedStructures.RSAllConfig.RSOutpostsConfig.outpostOakAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.OUTPOST_OAK,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.FOREST) &&
                    !(BiomeSelection.hasNameTemp(event, "birch", "dark", "spooky", "dead", "haunted"))))
        {
            event.addStructure(RSConfiguredStructures.OUTPOST_OAK);
            event.removeStructure(StructureFeaturesAccessor.getPILLAGER_OUTPOST());
        }
    }
}
