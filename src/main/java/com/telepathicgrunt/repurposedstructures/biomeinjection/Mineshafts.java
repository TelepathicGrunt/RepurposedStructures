package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.feature.StructureFeature;

import java.util.function.Supplier;

public final class Mineshafts {
    private Mineshafts() {}

    public static void addMineshafts() {

        BiomeInjection.addStructure(RSConfiguredStructures.MINESHAFT_BIRCH, (event) ->
            (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.birchMineshaftSpawnrate != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_BIRCH,
            () -> BiomeSelection.hasName(event, "birch")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.MINESHAFT_JUNGLE, (event) ->
            (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.jungleMineshaftSpawnrate != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_JUNGLE,
            () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.JUNGLE)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.MINESHAFT_DESERT, (event) ->
            (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.desertMineshaftSpawnrate != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_DESERT,
            () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.DESERT)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.MINESHAFT_SAVANNA, (event) ->
            (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.savannaMineshaftSpawnrate != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_SAVANNA,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.SAVANNA)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.MINESHAFT_OCEAN, (event) ->
            (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.oceanMineshaftSpawnrate != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_OCEAN,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.OCEAN)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.MINESHAFT_TAIGA, (event) ->
            (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.taigaMineshaftSpawnrate != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_TAIGA,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.TAIGA)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.MINESHAFT_ICY, (event) ->
            (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.icyMineshaftSpawnrate != 0 &&
                genericMineshaftCheck(event, RSStructures.MINESHAFT_ICY,
                        () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.ICY) ||
                                BiomeSelection.hasName(event, "snowy") ||
                                BiomeSelection.isBiome(event, Biomes.FROZEN_PEAKS)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.MINESHAFT_STONE, (event) ->
            (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.stoneMineshaftSpawnrate != 0 &&
                genericMineshaftCheck(event, RSStructures.MINESHAFT_STONE,
                        () -> (BiomeSelection.haveCategories(event, Biome.BiomeCategory.MOUNTAIN) && !BiomeSelection.isBiome(event, Biomes.MEADOW, Biomes.FROZEN_PEAKS, Biomes.SNOWY_SLOPES)) ||
                                BiomeSelection.haveCategories(event, Biome.BiomeCategory.EXTREME_HILLS) ||
                                BiomeSelection.isBiome(event, Biomes.STONY_PEAKS, Biomes.JAGGED_PEAKS)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.MINESHAFT_DARK_FOREST, (event) ->
            (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.darkForestMineshaftSpawnrate != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_DARK_FOREST,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.FOREST) &&
                    BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.MINESHAFT_SWAMP, (event) ->
            (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.swampMineshaftSpawnrate != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_SWAMP,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.SWAMP)))
        );
        BiomeInjection.addStructure(RSConfiguredStructures.MINESHAFT_CRIMSON, (event) ->
            (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.netherMineshaftSpawnrate != 0 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.MINESHAFT_CRIMSON,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasName(event, "crimson", "_red")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.MINESHAFT_WARPED, (event) ->
            (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.netherMineshaftSpawnrate != 0 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.MINESHAFT_WARPED,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasName(event, "warped", "blue")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.MINESHAFT_NETHER, (event) ->
            (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.netherMineshaftSpawnrate != 0 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.MINESHAFT_NETHER,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER) &&
                    !BiomeSelection.hasName(event, "crimson", "_red", "warped", "blue")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.MINESHAFT_END, (event) ->
            (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.endMineshaftSpawnrate != 0 &&
                BiomeSelection.isBiomeAllowed(event, RSStructures.MINESHAFT_END,
                        () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.THEEND) && !BiomeSelection.isBiome(event, Biomes.THE_END) &&
                        (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.misc.endMineshaftMinIslandThickness == 0 || !BiomeSelection.isBiome(event, Biomes.END_BARRENS, Biomes.SMALL_END_ISLANDS))))
        );
    }

    private static boolean genericMineshaftCheck(BiomeSelectionContext context, StructureFeature<?> structureFeature, Supplier<Boolean> condition) {
        return BiomeSelection.isBiomeAllowed(context, structureFeature,
                () -> BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.MINESHAFT) && condition.get());
    }
}
