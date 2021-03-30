package com.telepathicgrunt.repurposedstructures.biome_injection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.StructureFeature;

public class Strongholds {

    public static void addStrongholds() {
        GeneralUtils.addToBiome("stonebrick_stronghold",
                (context) -> (BiomeSelection.hasStructure(context, StructureFeature.STRONGHOLD) || BiomeSelection.isOverworldBiome(context))
                        && RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.stonebrickStrongholdMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.STRONGHOLD)
                        && BiomeSelection.isBiomeAllowed(context, "strongholds")
                        && ((RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.allowStonebrickStrongholdToVanillaBiomes && BiomeSelection.hasNamespace(context, "minecraft"))
                        || (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.stonebrick.addStonebrickStrongholdToModdedBiomes && !BiomeSelection.hasNamespace(context, "minecraft"))),
                context -> {
                    context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.STONEBRICK_STRONGHOLD);
                    context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.STRONGHOLDS, RSConfiguredFeatures.STONEBRICK_STRONGHOLD_CHAINS);
                });


        GeneralUtils.addToBiome("nether_stronghold",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.STRONGHOLD)
                        && BiomeSelection.isBiomeAllowed(context, "strongholds")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.addNetherStrongholdToModdedBiomes),
                context -> {
                    context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_STRONGHOLD);
                    context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.STRONGHOLDS, RSConfiguredFeatures.NETHER_STRONGHOLD_CHAINS);
                });
    }
}
