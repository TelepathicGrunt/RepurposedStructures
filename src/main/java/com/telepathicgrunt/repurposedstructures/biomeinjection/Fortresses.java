package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Fortresses {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // JUNGLE FORTRESS //

    public static void addJungleFortress(BiomeLoadingEvent event) {

        if(RepurposedStructures.RSFortressesConfig.jungleFortressMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.FORTRESS_JUNGLE.get(),
                    () -> BiomeSelection.haveCategories(event, Category.JUNGLE)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.JUNGLE_FORTRESS);
        }
    }
}
