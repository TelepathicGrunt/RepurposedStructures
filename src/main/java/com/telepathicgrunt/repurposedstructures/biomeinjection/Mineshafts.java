package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.feature.StructureFeature;

public class Mineshafts {

    public static void addMineshafts() {
        GeneralUtils.addToBiome("birch_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.birchMineshaftSpawnrate != 0
                        && BiomeSelection.hasName(context, "birch")),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.BIRCH_MINESHAFT));

        GeneralUtils.addToBiome("jungle_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.jungleMineshaftSpawnrate != 0
                        && BiomeSelection.haveCategories(context, Category.JUNGLE)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.JUNGLE_MINESHAFT));

        GeneralUtils.addToBiome("desert_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.desertMineshaftSpawnrate != 0
                        && BiomeSelection.haveCategories(context, Category.DESERT)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.DESERT_MINESHAFT));

        GeneralUtils.addToBiome("stone_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.stoneMineshaftSpawnrate != 0
                        && BiomeSelection.haveCategories(context, Category.EXTREME_HILLS)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.STONE_MINESHAFT));

        GeneralUtils.addToBiome("savanna_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.savannaMineshaftSpawnrate != 0
                        && BiomeSelection.haveCategories(context, Category.SAVANNA)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.SAVANNA_MINESHAFT));

        GeneralUtils.addToBiome("icy_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.icyMineshaftSpawnrate != 0
                        && (BiomeSelection.haveCategories(context, Category.ICY) || BiomeSelection.hasName(context, "snowy"))),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.ICY_MINESHAFT));

        GeneralUtils.addToBiome("ocean_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.oceanMineshaftSpawnrate != 0
                        && BiomeSelection.haveCategories(context, Category.OCEAN)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.OCEAN_MINESHAFT));

        GeneralUtils.addToBiome("taiga_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.taigaMineshaftSpawnrate != 0
                        && BiomeSelection.haveCategories(context, Category.TAIGA)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.TAIGA_MINESHAFT));

        GeneralUtils.addToBiome("dark_forest_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.darkForestMineshaftSpawnrate != 0
                        && BiomeSelection.haveCategories(context, Category.FOREST) && BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted")),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.DARK_FOREST_MINESHAFT));

        GeneralUtils.addToBiome("swamp_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.swampMineshaftSpawnrate != 0
                        && BiomeSelection.haveCategories(context, Category.SWAMP)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.SWAMP_MINESHAFT));

        GeneralUtils.addToBiome("end_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.endMineshaftSpawnrate != 0
                        && BiomeSelection.haveCategories(context, Category.THEEND) && !BiomeSelection.isBiome(context, BiomeKeys.THE_END)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.misc.barrensIslandsEndMineshafts
                        || (!BiomeSelection.isBiome(context, BiomeKeys.END_BARRENS) &&
                            !BiomeSelection.isBiome(context, BiomeKeys.SMALL_END_ISLANDS)))),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.END_MINESHAFT));

        GeneralUtils.addToBiome("nether_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.netherMineshaftSpawnrate != 0
                        && !BiomeSelection.hasName(context, "crimson", "_red", "warped", "blue")
                        && BiomeSelection.haveCategories(context, Category.NETHER)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_MINESHAFT));

        GeneralUtils.addToBiome("crimson_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.crimsonMineshaftSpawnrate != 0
                        && BiomeSelection.hasName(context, "crimson", "_red")
                        && BiomeSelection.haveCategories(context, Category.NETHER)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.CRIMSON_MINESHAFT));

        GeneralUtils.addToBiome("warped_mineshaft",
                (context) -> genericMineshaftCheck(context)
                        && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.warpedMineshaftSpawnrate != 0
                        && BiomeSelection.hasName(context, "warped", "blue")
                        && BiomeSelection.haveCategories(context, Category.NETHER)),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.WARPED_MINESHAFT));


        //Remove vanilla mineshafts from biomes we added our mineshafts to
        BiomeModifications.create(new Identifier(RepurposedStructures.MODID, "remove_vanilla_mineshafts")).add(
                ModificationPhase.REMOVALS,
                context -> BiomeSelection.hasStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.MINESHAFT),
                context -> context.getGenerationSettings().removeStructure(StructureFeature.MINESHAFT));
    }

    private static boolean genericMineshaftCheck(BiomeSelectionContext context) {
        return BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.MINESHAFT)
                && BiomeSelection.isBiomeAllowed(context, "mineshafts")
                && (BiomeSelection.hasNamespace(context, "minecraft") || RepurposedStructures.RSAllConfig.RSMineshaftsConfig.addMineshaftsToModdedBiomes);
    }
}
