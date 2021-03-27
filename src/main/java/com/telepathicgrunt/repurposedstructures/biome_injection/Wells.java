package com.telepathicgrunt.repurposedstructures.biome_injection;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.ChunkGeneratorAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
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

public class Wells {

    public static void addWells(BiomeLoadingEvent event) {

        if (BiomeSelection.haveCategories(event, Category.MESA) &&
            RepurposedStructures.RSWellsConfig.badlandsWellRarityPerChunk.get() != 10000 &&
            wellAllowedByNamespaceAndConfig(event))
        {
            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.BADLANDS_WELL);
        }
        
        else if (BiomeSelection.haveCategories(event, Category.NETHER) &&
                RepurposedStructures.RSWellsConfig.netherWellRarityPerChunk.get() != 10000 &&
                wellAllowedByNamespaceAndConfig(event)) {

            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.NETHER_WELL);
        }

        else if ((BiomeSelection.haveCategories(event, Category.ICY) || BiomeSelection.hasName(event, "snow")) &&
                RepurposedStructures.RSWellsConfig.snowWellRarityPerChunk.get() != 10000 &&
                wellAllowedByNamespaceAndConfig(event))
        {
            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.SNOW_WELL);
        }

        else if ((BiomeSelection.haveCategories(event, Category.SWAMP, Category.JUNGLE) ||
                (BiomeSelection.haveCategories(event, Category.FOREST) && BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted"))) &&
                RepurposedStructures.RSWellsConfig.mossyStoneWellRarityPerChunk.get() != 10000 &&
                wellAllowedByNamespaceAndConfig(event))
        {
            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.MOSSY_STONE_WELL);
        }

        else if (BiomeSelection.haveCategories(event, Category.FOREST) && !(BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted")) &&
                RepurposedStructures.RSWellsConfig.forestWellRarityPerChunk.get() != 10000 &&
                wellAllowedByNamespaceAndConfig(event))
        {

            event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                    .add(() -> RSConfiguredFeatures.FOREST_WELL);
        }
    }

    private static boolean wellAllowedByNamespaceAndConfig(BiomeLoadingEvent event) {
        return BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSWellsConfig.addWellsToModdedBiomes.get();
    }
}
