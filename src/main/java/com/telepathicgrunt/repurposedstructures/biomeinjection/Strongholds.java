package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Strongholds {

    public static void addStrongholds(BiomeLoadingEvent event) {
        if (RepurposedStructures.yungsBetterStrongholdsIsNotOn &&
            RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasStructure(event, Structure.STRONGHOLD) || BiomeSelection.isOverworldBiome(event)) &&
            ((RepurposedStructures.RSStrongholdsConfig.allowStonebrickStrongholdToVanillaBiomes.get() && BiomeSelection.hasNamespace(event, "minecraft")) ||
            (RepurposedStructures.RSStrongholdsConfig.addStonebrickStrongholdToModdedBiomes.get() && !BiomeSelection.hasNamespace(event, "minecraft"))))
        {

            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.STONEBRICK_STRONGHOLD);
            event.getGeneration().getFeatures(GenerationStage.Decoration.STRONGHOLDS).add(() -> RSConfiguredFeatures.STONEBRICK_STRONGHOLD_CHAINS);
        }

        else if (RepurposedStructures.RSStrongholdsConfig.netherStrongholdMaxChunkDistance.get() != 1001 &&
                BiomeSelection.haveCategories(event, Category.NETHER) &&
                (BiomeSelection.hasNamespace(event, "minecraft") ||
                    RepurposedStructures.RSStrongholdsConfig.addNetherStrongholdToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.NETHER_STRONGHOLD);
            event.getGeneration().getFeatures(GenerationStage.Decoration.STRONGHOLDS).add(() -> RSConfiguredFeatures.NETHER_STRONGHOLD_CHAINS);
        }
    }
}
