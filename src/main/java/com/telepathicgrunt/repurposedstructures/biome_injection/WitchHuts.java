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

public class WitchHuts {

    public static void addWitchHuts(BiomeLoadingEvent event) {
        if ((BiomeSelection.haveCategories(event, Category.FOREST) && !BiomeSelection.hasName(event, "birch", "dark", "spooky", "dead", "haunted")) &&
                RepurposedStructures.RSWitchHutsConfig.witchHutsOakMaxChunkDistance.get() != 1001 &&
                (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSWitchHutsConfig.addWitchHutsOakToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WITCH_HUTS_OAK);
        }
        else if (BiomeSelection.haveCategories(event, Category.TAIGA)  &&
            !BiomeSelection.hasName(event, "giant", "redwood") &&
            RepurposedStructures.RSWitchHutsConfig.witchHutsTaigaMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSWitchHutsConfig.addWitchHutsTaigaToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WITCH_HUTS_TAIGA);
        }
        else if (BiomeSelection.hasName(event, "birch") &&
            RepurposedStructures.RSWitchHutsConfig.witchHutsBirchMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSWitchHutsConfig.addWitchHutsBirchToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WITCH_HUTS_BIRCH);
        }
        else if (BiomeSelection.haveCategories(event, Category.FOREST) && BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted") &&
            RepurposedStructures.RSWitchHutsConfig.witchHutsDarkForestMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSWitchHutsConfig.addWitchHutsDarkForestToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WITCH_HUTS_DARK_FOREST);
        }
        else if (BiomeSelection.haveCategories(event, Category.TAIGA)  &&
            BiomeSelection.hasName(event, "giant", "redwood") &&
            RepurposedStructures.RSWitchHutsConfig.witchHutsGiantTreeTaigaMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSWitchHutsConfig.addWitchHutsGiantTreeTaigaToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.WITCH_HUTS_GIANT_TREE_TAIGA);
        }
        if ((BiomeSelection.haveCategories(event, Category.ICY) || (BiomeSelection.haveCategories(event, Category.TAIGA) && event.getClimate().precipitation == Biome.RainType.SNOW)) &&
            RepurposedStructures.RSTemplesConfig.pyramidSnowyMaxChunkDistance.get() != 1001 &&
            (BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSTemplesConfig.addPyramidSnowyToModdedBiomes.get()))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.PYRAMID_SNOWY);
        }
        // regexpos1
    }
}
