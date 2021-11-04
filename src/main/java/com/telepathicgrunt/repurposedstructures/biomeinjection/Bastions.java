package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSBastionsConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public final class Bastions {
    private Bastions() {}

    public static void addBastions(BiomeLoadingEvent event) {

        if (RSBastionsConfig.bastionUndergroundMaxChunkDistance.get() != 10001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.BASTION_UNDERGROUND.get(),
                () -> !BiomeSelection.haveCategories(event, Category.BEACH, Category.OCEAN, Category.NETHER, Category.THEEND, Category.NONE)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.BASTION_UNDERGROUND);
        }
    }
}
