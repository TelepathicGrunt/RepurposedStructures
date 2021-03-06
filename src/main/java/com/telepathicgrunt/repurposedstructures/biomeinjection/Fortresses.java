package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Fortresses {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // JUNGLE FORTRESS //

    public static void addJungleFortress(BiomeLoadingEvent event) {
        if(RepurposedStructures.RSMainConfig.jungleFortressMaxChunkDistance.get() != 1001)
        {
            if (BiomeSelection.haveCategories(event, Category.JUNGLE) &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSMainConfig.addJungleFortressToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.JUNGLE_FORTRESS);
            }
        }
    }
}
