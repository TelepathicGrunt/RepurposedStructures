package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.feature.StructureFeature;

import java.util.function.Supplier;

public final class Mineshafts {
    private Mineshafts() {}

    public static void addMineshafts(BiomeInjection.BiomeInjectionHelper event) {

        if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.birchMineshaftSpawnrate != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_BIRCH,
                    () -> BiomeSelection.hasNameTemp(event, "birch")))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_BIRCH);
        }

        if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.jungleMineshaftSpawnrate != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_JUNGLE,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.JUNGLE)))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_JUNGLE);
        }

        if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.desertMineshaftSpawnrate != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_DESERT,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.DESERT)))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_DESERT);
        }

        if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.savannaMineshaftSpawnrate != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_SAVANNA,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.SAVANNA)))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_SAVANNA);
        }

        if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.oceanMineshaftSpawnrate != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_OCEAN,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.OCEAN)))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_OCEAN);
        }

        if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.taigaMineshaftSpawnrate != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_TAIGA,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA)))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_TAIGA);
        }

        if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.icyMineshaftSpawnrate != 0 &&
                genericMineshaftCheck(event, RSStructures.MINESHAFT_ICY,
                        () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.ICY) ||
                                BiomeSelection.hasNameTemp(event, "snowy") ||
                                BiomeSelection.isBiomeTemp(event, Biomes.FROZEN_PEAKS)))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_ICY);
        }

        if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.stoneMineshaftSpawnrate != 0 &&
                genericMineshaftCheck(event, RSStructures.MINESHAFT_STONE,
                        () -> (BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MOUNTAIN) && !BiomeSelection.isBiomeTemp(event, Biomes.MEADOW, Biomes.FROZEN_PEAKS, Biomes.SNOWY_SLOPES)) ||
                                BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.EXTREME_HILLS) ||
                                BiomeSelection.isBiomeTemp(event, Biomes.STONY_PEAKS, Biomes.JAGGED_PEAKS)))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_STONE);
        }

        if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.darkForestMineshaftSpawnrate != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_DARK_FOREST,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.FOREST) &&
                    BiomeSelection.hasNameTemp(event, "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony")))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_DARK_FOREST);
        }

        if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.swampMineshaftSpawnrate != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_SWAMP,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.SWAMP)))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_SWAMP);
        }
        if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.netherMineshaftSpawnrate != 0 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MINESHAFT_CRIMSON,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "crimson", "_red")))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_CRIMSON);
        }

        if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.netherMineshaftSpawnrate != 0 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MINESHAFT_WARPED,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "warped", "blue")))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_WARPED);
        }

        if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.netherMineshaftSpawnrate != 0 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MINESHAFT_NETHER,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    !BiomeSelection.hasNameTemp(event, "crimson", "_red", "warped", "blue")))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_NETHER);
        }

        if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.endMineshaftSpawnrate != 0 &&
                BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MINESHAFT_END,
                        () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.THEEND) && !BiomeSelection.isBiomeTemp(event, Biomes.THE_END) &&
                        (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.misc.endMineshaftMinIslandThickness == 0 || !BiomeSelection.isBiomeTemp(event, Biomes.END_BARRENS, Biomes.SMALL_END_ISLANDS))))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_END);
        }
    }

    private static boolean genericMineshaftCheck(BiomeInjection.BiomeInjectionHelper context, StructureFeature<?> structureFeature, Supplier<Boolean> condition) {
        return BiomeSelection.isBiomeAllowedTemp(context, structureFeature,
                () -> BiomeSelection.doesNotHaveStructureTypeTemp(context, RSStructureTagMap.STRUCTURE_TAGS.MINESHAFT) && condition.get());
    }
}
