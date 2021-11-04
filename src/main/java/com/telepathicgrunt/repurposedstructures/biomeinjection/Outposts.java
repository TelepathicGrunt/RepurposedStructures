package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSOutpostsConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public final class Outposts {
    private Outposts() {}

    public static void addOutposts(BiomeLoadingEvent event) {

        if (RSOutpostsConfig.crimsonOutpostMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_CRIMSON.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    BiomeSelection.hasName(event, "crimson", "red_")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_CRIMSON);
        }

        if (RSOutpostsConfig.warpedOutpostMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_WARPED.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    BiomeSelection.hasName(event, "warped", "blue")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_WARPED);
        }

        if (RSOutpostsConfig.netherBrickOutpostMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_NETHER_BRICK.get(),
                    () -> BiomeSelection.haveCategories(event, Category.NETHER) &&
                    !BiomeSelection.hasName(event, "crimson", "red_", "warped", "blue")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_NETHER_BRICK);
        }

        if (RSOutpostsConfig.outpostEndMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_END.get(),
                    () -> BiomeSelection.haveCategories(event, Category.THEEND)
                    && !BiomeSelection.isBiome(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_END);
        }

        if (RSOutpostsConfig.outpostBirchMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_BIRCH.get(),
                    () -> BiomeSelection.hasName(event, "birch")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_BIRCH);
            event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
        }

        if (RSOutpostsConfig.outpostJungleMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_JUNGLE.get(),
                    () -> BiomeSelection.haveCategories(event, Category.JUNGLE)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_JUNGLE);
            event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
        }

        if (RSOutpostsConfig.outpostGiantTreeTaigaMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_GIANT_TREE_TAIGA.get(),
                    () -> BiomeSelection.haveCategories(event, Category.TAIGA) &&
                    BiomeSelection.hasName(event, "giant", "redwood")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_GIANT_TREE_TAIGA);
            event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
        }

        if (RSOutpostsConfig.outpostDesertMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_DESERT.get(),
                    () -> BiomeSelection.haveCategories(event, Category.DESERT)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_DESERT);
            event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
        }

        if (RSOutpostsConfig.outpostBadlandsMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_BADLANDS.get(),
                    () -> BiomeSelection.haveCategories(event, Category.MESA)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_BADLANDS);
            event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
        }

        if (RSOutpostsConfig.outpostSnowyMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_SNOWY.get(),
                    () -> (BiomeSelection.hasName(event, "snow") ||
                    (BiomeSelection.haveCategories(event, Category.ICY) && !BiomeSelection.hasName(event, "ice", "icy", "glacier", "frozen")))))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_SNOWY);
            event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
        }

        if (RSOutpostsConfig.outpostIcyMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_ICY.get(),
                    () -> BiomeSelection.haveCategories(event, Category.ICY) && BiomeSelection.hasName(event, "ice", "icy", "glacier", "frozen")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_ICY);
            event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
        }

        if (RSOutpostsConfig.outpostTaigaMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_TAIGA.get(),
                    () -> BiomeSelection.haveCategories(event, Category.TAIGA) &&
                    !BiomeSelection.hasName(event, "giant", "redwood", "snow", "ice", "icy", "glacier", "frozen")))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_TAIGA);
            event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
        }

        if (RSOutpostsConfig.outpostOakMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.OUTPOST_OAK.get(),
                    () -> BiomeSelection.haveCategories(event, Category.FOREST) &&
                    !(BiomeSelection.hasName(event, "birch", "dark", "spooky", "dead", "haunted"))))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_OAK);
            event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
        }
    }
}
