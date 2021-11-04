package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.feature.StructureFeature;

import java.util.function.Supplier;

public final class Mineshafts {
    private Mineshafts() {}

    public static void addMineshafts() {

        GeneralUtils.addToBiome("birch_mineshaft",
                (context) -> 
                        genericMineshaftCheck(context, RSStructures.MINESHAFT_BIRCH,
                                () -> BiomeSelection.hasName(context, "birch"))
                        && RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.birchMineshaftSpawnrate != 0,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MINESHAFT_BIRCH));

        GeneralUtils.addToBiome("jungle_mineshaft",
                (context) -> 
                        genericMineshaftCheck(context, RSStructures.MINESHAFT_JUNGLE,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.JUNGLE))
                        && RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.jungleMineshaftSpawnrate != 0,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MINESHAFT_JUNGLE));

        GeneralUtils.addToBiome("desert_mineshaft",
                (context) -> 
                        genericMineshaftCheck(context, RSStructures.MINESHAFT_DESERT,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.DESERT))
                        && RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.desertMineshaftSpawnrate != 0,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MINESHAFT_DESERT));

        GeneralUtils.addToBiome("stone_mineshaft",
                (context) -> 
                        genericMineshaftCheck(context, RSStructures.MINESHAFT_STONE,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.EXTREME_HILLS))
                        && RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.stoneMineshaftSpawnrate != 0,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MINESHAFT_STONE));

        GeneralUtils.addToBiome("savanna_mineshaft",
                (context) -> 
                        genericMineshaftCheck(context, RSStructures.MINESHAFT_SAVANNA,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.SAVANNA))
                        && RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.savannaMineshaftSpawnrate != 0,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MINESHAFT_SAVANNA));

        GeneralUtils.addToBiome("icy_mineshaft",
                (context) -> 
                        genericMineshaftCheck(context, RSStructures.MINESHAFT_ICY,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.ICY)
                                || BiomeSelection.hasName(context, "snowy"))
                        && RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.icyMineshaftSpawnrate != 0,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MINESHAFT_ICY));

        GeneralUtils.addToBiome("ocean_mineshaft",
                (context) -> 
                        genericMineshaftCheck(context, RSStructures.MINESHAFT_OCEAN,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.OCEAN))
                        && RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.oceanMineshaftSpawnrate != 0,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MINESHAFT_OCEAN));

        GeneralUtils.addToBiome("taiga_mineshaft",
                (context) -> 
                        genericMineshaftCheck(context, RSStructures.MINESHAFT_TAIGA,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.TAIGA))
                        && RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.taigaMineshaftSpawnrate != 0,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MINESHAFT_TAIGA));

        GeneralUtils.addToBiome("dark_forest_mineshaft",
                (context) -> 
                        genericMineshaftCheck(context, RSStructures.MINESHAFT_DARK_FOREST,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.FOREST) && BiomeSelection.hasName(context, "dark", "spooky", "dead", "haunted"))
                        && RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.darkForestMineshaftSpawnrate != 0,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MINESHAFT_DARK_FOREST));

        GeneralUtils.addToBiome("swamp_mineshaft",
                (context) -> 
                        genericMineshaftCheck(context, RSStructures.MINESHAFT_SWAMP,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.SWAMP))
                        && RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.swampMineshaftSpawnrate != 0,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MINESHAFT_SWAMP));

        GeneralUtils.addToBiome("end_mineshaft",
                (context) -> 
                        genericMineshaftCheck(context, RSStructures.MINESHAFT_END,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.THEEND) && !BiomeSelection.isBiome(context, Biomes.THE_END)
                                && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.misc.endMineshaftMinIslandThickness == 0
                                || (!BiomeSelection.isBiome(context, Biomes.END_BARRENS) && !BiomeSelection.isBiome(context, Biomes.SMALL_END_ISLANDS))))
                        && RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.endMineshaftSpawnrate != 0,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MINESHAFT_END));

        GeneralUtils.addToBiome("nether_mineshaft",
                (context) -> 
                        genericMineshaftCheck(context, RSStructures.MINESHAFT_NETHER,
                                () -> !BiomeSelection.hasName(context, "crimson", "_red", "warped", "blue")
                                && BiomeSelection.haveCategories(context, BiomeCategory.NETHER))
                        && RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.netherMineshaftSpawnrate != 0,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MINESHAFT_NETHER));

        GeneralUtils.addToBiome("crimson_mineshaft",
                (context) -> 
                        genericMineshaftCheck(context, RSStructures.MINESHAFT_CRIMSON,
                                () -> BiomeSelection.hasName(context, "crimson", "_red")
                                && BiomeSelection.haveCategories(context, BiomeCategory.NETHER))
                        && RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.crimsonMineshaftSpawnrate != 0,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MINESHAFT_CRIMSON));

        GeneralUtils.addToBiome("warped_mineshaft",
                (context) -> 
                        genericMineshaftCheck(context, RSStructures.MINESHAFT_WARPED,
                                () -> BiomeSelection.hasName(context, "warped", "blue")
                                && BiomeSelection.haveCategories(context, BiomeCategory.NETHER))
                        && RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.warpedMineshaftSpawnrate != 0,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.MINESHAFT_WARPED));


//        if(!RepurposedStructures.betterMineshafts){
//            //Remove vanilla mineshafts from biomes we added our mineshafts to
//            BiomeModifications.create(new Identifier(RepurposedStructures.MODID, "remove_vanilla_mineshafts")).add(
//                    ModificationPhase.REMOVALS,
//                    context -> BiomeSelection.hasStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.MINESHAFT),
//                    context -> context.getGenerationSettings().removeStructure(StructureFeature.MINESHAFT));
//        }
    }

    private static boolean genericMineshaftCheck(BiomeSelectionContext context, StructureFeature<?> structureFeature, Supplier<Boolean> condition) {
        return BiomeSelection.isBiomeAllowed(context, structureFeature,
                () -> BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.MINESHAFT) && condition.get());
    }
}
