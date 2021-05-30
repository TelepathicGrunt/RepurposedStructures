package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Bastions {

    public static void addBastions(BiomeLoadingEvent event) {

        if (!BiomeSelection.haveCategories(event, Category.BEACH, Category.OCEAN, Category.NETHER, Category.NONE, Category.THEEND) &&
            RepurposedStructures.RSMainConfig.bastionUndergroundMaxChunkDistance.get() != 10001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSMainConfig.addBastionUndergroundToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.BASTION_UNDERGROUND);
        }
    }
}
