package com.telepathicgrunt.repurposedstructures.biome_injection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class Villages {

    public static void addVillages() {

        GeneralUtils.addToBiome("badlands_village",
                (context) -> BiomeSelection.haveCategories(context, Category.MESA)
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.badlandsVillageMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE)
                        && BiomeSelection.isBiomeAllowed(context, "villages")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.BADLANDS_VILLAGE));

        GeneralUtils.addToBiome("birch_village",
                (context) -> BiomeSelection.hasName(context, "birch")
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.birchVillageMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE)
                        && BiomeSelection.isBiomeAllowed(context, "villages")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.BIRCH_VILLAGE));

        GeneralUtils.addToBiome("dark_forest_village",
                (context) -> BiomeSelection.haveCategories(context, Category.FOREST)
                        && BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted")
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.darkForestVillageMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE)
                        && BiomeSelection.isBiomeAllowed(context, "villages")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.DARK_FOREST_VILLAGE));

        GeneralUtils.addToBiome("jungle_village",
                (context) -> BiomeSelection.haveCategories(context, Category.JUNGLE)
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.jungleVillageMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE)
                        && BiomeSelection.isBiomeAllowed(context, "villages")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes),
                context -> {
                    context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.JUNGLE_VILLAGE);
                    context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSConfiguredFeatures.JUNGLE_VILLAGE_VINES);
                });

        GeneralUtils.addToBiome("swamp_village",
                (context) -> BiomeSelection.haveCategories(context, Category.SWAMP)
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.swampVillageMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE)
                        && BiomeSelection.isBiomeAllowed(context, "villages")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes),
                context -> {
                    context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.SWAMP_VILLAGE);
                    context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, RSConfiguredFeatures.SWAMP_VILLAGE_VINES);
                });


        GeneralUtils.addToBiome("mountains_village",
                (context) -> BiomeSelection.haveCategories(context, Category.EXTREME_HILLS)
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.mountainsVillageMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE)
                        && BiomeSelection.isBiomeAllowed(context, "villages")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MOUNTAINS_VILLAGE));

        GeneralUtils.addToBiome("giant_taiga_village",
                (context) -> (BiomeSelection.isBiome(context, BiomeKeys.GIANT_SPRUCE_TAIGA, BiomeKeys.GIANT_TREE_TAIGA)
                            || (RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes
                                && !BiomeSelection.hasNamespace(context, "minecraft")
                                && BiomeSelection.haveCategories(context, Category.TAIGA)
                                && BiomeSelection.hasName(context, "giant", "redwood")))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.giantTaigaVillageMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE)
                        && BiomeSelection.isBiomeAllowed(context, "villages"),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.GIANT_TAIGA_VILLAGE));

        GeneralUtils.addToBiome("crimson_village",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "crimson", "red_")
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.crimsonVillageMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE)
                        && BiomeSelection.isBiomeAllowed(context, "villages")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.CRIMSON_VILLAGE));


        GeneralUtils.addToBiome("warped_village",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && BiomeSelection.hasName(context, "warped", "blue")
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.warpedVillageMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.VILLAGE)
                        && BiomeSelection.isBiomeAllowed(context, "villages")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.WARPED_VILLAGE));

        GeneralUtils.addToBiome("village_oak",
                (context) -> BiomeSelection.haveCategories(context, Category.FOREST)
                        && !(BiomeSelection.hasName(context, "birch", "dark", "spooky", "dead", "haunted"))
                        && RepurposedStructures.RSAllConfig.RSVillagesConfig.villageOakMaxChunkDistance != 1001
                        && BiomeSelection.isBiomeAllowed(context, "villages")
                        && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSVillagesConfig.addVillagesToModdedBiomes),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.VILLAGE_OAK));
    }
}
