package com.telepathicgrunt.repurposedstructures.biome_injection;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.ChunkGeneratorAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Boulders {

    public static void addBoulderFeatures(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSMainConfig.boulderGiant.get() && 
            !BiomeSelection.hasNamespace(event, "ultra_amplified_dimension") &&
            (BiomeSelection.isBiome(event, Biomes.GIANT_SPRUCE_TAIGA_HILLS, Biomes.GIANT_TREE_TAIGA_HILLS) ||
                (RepurposedStructures.RSMainConfig.addGiantBouldersModdedBiomes.get() && 
                    !BiomeSelection.hasNamespace(event, "minecraft") &&
                    BiomeSelection.haveCategories(event, Category.TAIGA) && 
                    BiomeSelection.hasName(event, "giant", "redwood")))) 
        {
            // replace the boulders with our own
            event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS)
                    .removeIf(configuredFeature -> configuredFeature.get().config instanceof DecoratedFeatureConfig &&
                            GeneralUtils.serializeAndCompareFeature(configuredFeature.get(), Features.FOREST_ROCK));

            event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS)
                    .add(() -> RSConfiguredFeatures.BOULDER_GIANT);
        }
        
        else if (RepurposedStructures.RSMainConfig.boulderTiny.get() && 
                !BiomeSelection.hasNamespace(event, "ultra_amplified_dimension") &&
                (BiomeSelection.isBiome(event, Biomes.SNOWY_TAIGA_MOUNTAINS, Biomes.TAIGA_MOUNTAINS) ||
                    (RepurposedStructures.RSMainConfig.addTinyBouldersModdedBiomes.get() && 
                        !BiomeSelection.hasNamespace(event, "minecraft") &&
                        BiomeSelection.haveCategories(event, Category.TAIGA) && 
                        BiomeSelection.hasName(event, "mountain", "hill"))))
        {
            event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS)
                    .add(() -> RSConfiguredFeatures.BOULDER_TINY);
        }
    }
}
