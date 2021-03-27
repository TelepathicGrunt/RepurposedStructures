package com.telepathicgrunt.repurposedstructures.biome_injection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Outposts {

    public static void addOutposts(BiomeLoadingEvent event) {

        //Nether based Outposts
        if(BiomeSelection.haveCategories(event, Category.NETHER))
        {
            if (BiomeSelection.hasName(event, "crimson", "red_") &&
                RepurposedStructures.RSOutpostsConfig.crimsonOutpostMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addCrimsonOutpostToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.CRIMSON_OUTPOST);
            }

            else if (BiomeSelection.hasName(event, "warped", "blue") &&
                    RepurposedStructures.RSOutpostsConfig.warpedOutpostMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addWarpedOutpostToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WARPED_OUTPOST);
            }

            else if (RepurposedStructures.RSOutpostsConfig.netherBrickOutpostMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addNetherBrickOutpostToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_BRICK_OUTPOST);
            }
        }
        else{
            if (BiomeSelection.hasName(event, "birch") &&
                RepurposedStructures.RSOutpostsConfig.outpostBirchMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addOutpostBirchToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_BIRCH);
                event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
            }

            else if (BiomeSelection.haveCategories(event, Category.JUNGLE) &&
                    RepurposedStructures.RSOutpostsConfig.outpostJungleMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addOutpostJungleToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_JUNGLE);
                event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
            }

            else if (BiomeSelection.haveCategories(event, Category.TAIGA) && BiomeSelection.hasName(event, "giant", "redwood") &&
                    RepurposedStructures.RSOutpostsConfig.outpostGiantTreeTaigaMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addOutpostGiantTreeTaigaToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_GIANT_TREE_TAIGA);
                event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
            }

            else if (BiomeSelection.haveCategories(event, Category.DESERT) &&
                    RepurposedStructures.RSOutpostsConfig.outpostDesertMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addOutpostDesertToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_DESERT);
                event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
            }

            else if (BiomeSelection.haveCategories(event, Category.MESA) &&
                    RepurposedStructures.RSOutpostsConfig.outpostBadlandsMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addOutpostBadlandsToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_BADLANDS);
                event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
            }

            else if ((BiomeSelection.hasName(event, "snow") ||
                        (BiomeSelection.haveCategories(event, Category.ICY) && !BiomeSelection.hasName(event, "ice", "icy", "glacier", "frozen"))) &&
                    RepurposedStructures.RSOutpostsConfig.outpostSnowyMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addOutpostSnowyToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_SNOWY);
                event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
            }

            else if (BiomeSelection.haveCategories(event, Category.ICY) && BiomeSelection.hasName(event, "ice", "icy", "glacier", "frozen") &&
                    RepurposedStructures.RSOutpostsConfig.outpostIcyMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addOutpostIcyToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_ICY);
                event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
            }

            else if (BiomeSelection.haveCategories(event, Category.TAIGA) &&
                    !BiomeSelection.hasName(event, "giant", "redwood", "snow", "ice", "icy", "glacier", "frozen") &&
                    RepurposedStructures.RSOutpostsConfig.outpostTaigaMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addOutpostTaigaToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_TAIGA);
                event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
            }

            else if (BiomeSelection.haveCategories(event, Category.FOREST) &&
                    !(BiomeSelection.hasName(event, "birch", "dark", "spooky", "dead", "haunted")) &&
                    RepurposedStructures.RSOutpostsConfig.outpostOakMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSOutpostsConfig.addOutpostOakToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.OUTPOST_OAK);
                event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature.equals(Structure.PILLAGER_OUTPOST));
            }
        }
    }
}
