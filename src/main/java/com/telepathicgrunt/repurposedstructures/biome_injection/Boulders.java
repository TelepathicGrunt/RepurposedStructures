package com.telepathicgrunt.repurposedstructures.biome_injection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeatures;

public class Boulders {

    public static void addBoulderFeatures() {

        GeneralUtils.addToBiome("boulder_giant",
                (context) -> BiomeSelection.isBiomeAllowed(context, "boulders")
                        && !BiomeSelection.hasNamespace(context, "ultra_amplified_dimension")
                        && RepurposedStructures.RSAllConfig.RSMainConfig.misc.boulderGiant
                        && (BiomeSelection.isBiome(context, BiomeKeys.GIANT_SPRUCE_TAIGA_HILLS, BiomeKeys.GIANT_TREE_TAIGA_HILLS)
                            || (RepurposedStructures.RSAllConfig.RSMainConfig.misc.addBoulderToModdedBiomes
                                && !BiomeSelection.hasNamespace(context, "minecraft")
                                && BiomeSelection.haveCategories(context, Category.TAIGA)
                                && BiomeSelection.hasName(context, "giant", "redwood"))),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, RSConfiguredFeatures.BOULDER_GIANT));

        // Remove vanilla forest rock
        BiomeModifications.create(new Identifier(RepurposedStructures.MODID, "remove_vanilla_boulder")).add(
                ModificationPhase.REMOVALS,
                context -> context.hasBuiltInFeature(RSConfiguredFeatures.BOULDER_GIANT),
                context -> context.getGenerationSettings().removeBuiltInFeature(ConfiguredFeatures.FOREST_ROCK));

        GeneralUtils.addToBiome("boulder_tiny",
                (context) -> BiomeSelection.isBiomeAllowed(context, "boulders")
                        && !BiomeSelection.hasNamespace(context, "ultra_amplified_dimension")
                        && RepurposedStructures.RSAllConfig.RSMainConfig.misc.boulderTiny
                        && (BiomeSelection.isBiome(context, BiomeKeys.SNOWY_TAIGA_MOUNTAINS, BiomeKeys.TAIGA_MOUNTAINS)
                            || (RepurposedStructures.RSAllConfig.RSMainConfig.misc.addBoulderToModdedBiomes
                                && !BiomeSelection.hasNamespace(context, "minecraft")
                                && BiomeSelection.haveCategories(context, Category.TAIGA)
                                && BiomeSelection.hasName(context, "mountain", "hill"))),
                context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, RSConfiguredFeatures.BOULDER_TINY));
    }
}
