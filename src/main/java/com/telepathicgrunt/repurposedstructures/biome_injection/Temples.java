package com.telepathicgrunt.repurposedstructures.biome_injection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Temples {

    public static void addTemples(BiomeLoadingEvent event) {

        if(BiomeSelection.haveCategories(event, Category.NETHER)){
            if (BiomeSelection.hasName(event, "basalt", "blackstone") &&
                RepurposedStructures.RSTemplesConfig.netherBasaltTempleMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addNetherBasaltTempleToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_BASALT_TEMPLE);
            }

            else if (BiomeSelection.hasName(event, "crimson", "red_") &&
                    RepurposedStructures.RSTemplesConfig.netherCrimsonTempleMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addNetherCrimsonTempleToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_CRIMSON_TEMPLE);
            }

            else if (BiomeSelection.hasName(event, "warped", "blue") &&
                    RepurposedStructures.RSTemplesConfig.netherWarpedTempleMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addNetherWarpedTempleToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_WARPED_TEMPLE);
            }

            else if (BiomeSelection.hasName(event, "soul") &&
                    RepurposedStructures.RSTemplesConfig.netherSoulTempleMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addNetherSoulTempleToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_SOUL_TEMPLE);
            }

            else if (RepurposedStructures.RSTemplesConfig.netherWastelandTempleMaxChunkDistance.get() != 1001 &&
                    (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addNetherWastelandTempleToModdedBiomes.get()))
            {
                event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_WASTELAND_TEMPLE);
            }
        }
    }
}
