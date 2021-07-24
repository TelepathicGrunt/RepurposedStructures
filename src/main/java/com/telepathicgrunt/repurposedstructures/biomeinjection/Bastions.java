package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Bastions {

    public static void addBastions(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSBastionsConfig.bastionUndergroundMaxChunkDistance.get() != 10001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.BASTION_UNDERGROUND.get(),
                () -> !BiomeSelection.haveCategories(event, Category.BEACH, Category.OCEAN, Category.NETHER, Category.NONE, Category.THEEND)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.BASTION_UNDERGROUND);
        }
    }
}
