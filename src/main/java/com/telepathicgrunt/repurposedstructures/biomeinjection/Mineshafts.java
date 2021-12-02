package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSMineshaftsConfig;
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

    public static void addMineshafts(TemporaryBiomeInjection.BiomeInjectionHelper event) {

        if (RSMineshaftsConfig.birchMineshaftSpawnrate.get() != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_BIRCH.get(),
                    () -> BiomeSelection.hasNameTemp(event, "birch")))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_BIRCH);
        }

        if (RSMineshaftsConfig.jungleMineshaftSpawnrate.get() != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_JUNGLE.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.JUNGLE)))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_JUNGLE);
        }

        if (RSMineshaftsConfig.desertMineshaftSpawnrate.get() != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_DESERT.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.DESERT)))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_DESERT);
        }

        if (RSMineshaftsConfig.savannaMineshaftSpawnrate.get() != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_SAVANNA.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.SAVANNA)))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_SAVANNA);
        }

        if (RSMineshaftsConfig.oceanMineshaftSpawnrate.get() != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_OCEAN.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.OCEAN)))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_OCEAN);
        }

        if (RSMineshaftsConfig.taigaMineshaftSpawnrate.get() != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_TAIGA.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA)))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_TAIGA);
        }

        if (RSMineshaftsConfig.icyMineshaftSpawnrate.get() != 0 &&
                genericMineshaftCheck(event, RSStructures.MINESHAFT_ICY.get(),
                        () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.ICY) ||
                                BiomeSelection.hasNameTemp(event, "snowy") ||
                                BiomeSelection.isBiomeTemp(event, Biomes.FROZEN_PEAKS)))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_ICY);
        }

        if (RSMineshaftsConfig.stoneMineshaftSpawnrate.get() != 0 &&
                genericMineshaftCheck(event, RSStructures.MINESHAFT_STONE.get(),
                        () -> (BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.MOUNTAIN) && !BiomeSelection.isBiomeTemp(event, Biomes.MEADOW, Biomes.FROZEN_PEAKS, Biomes.SNOWY_SLOPES)) ||
                                BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.EXTREME_HILLS) ||
                                BiomeSelection.isBiomeTemp(event, Biomes.STONY_PEAKS, Biomes.JAGGED_PEAKS)))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_STONE);
        }

        if (RSMineshaftsConfig.darkForestMineshaftSpawnrate.get() != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_DARK_FOREST.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.FOREST) &&
                    BiomeSelection.hasNameTemp(event, "dark", "spooky", "dead", "haunted")))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_DARK_FOREST);
        }

        if (RSMineshaftsConfig.swampMineshaftSpawnrate.get() != 0 &&
            genericMineshaftCheck(event, RSStructures.MINESHAFT_SWAMP.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.SWAMP)))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_SWAMP);
        }
        if (RSMineshaftsConfig.netherMineshaftSpawnrate.get() != 0 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MINESHAFT_CRIMSON.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "crimson", "_red")))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_CRIMSON);
        }

        if (RSMineshaftsConfig.netherMineshaftSpawnrate.get() != 0 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MINESHAFT_WARPED.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "warped", "blue")))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_WARPED);
        }

        if (RSMineshaftsConfig.netherMineshaftSpawnrate.get() != 0 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MINESHAFT_NETHER.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    !BiomeSelection.hasNameTemp(event, "crimson", "_red", "warped", "blue")))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_NETHER);
        }

        if (RSMineshaftsConfig.endMineshaftSpawnrate.get() != 0 &&
                BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MINESHAFT_END.get(),
                        () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.THEEND) && !BiomeSelection.isBiomeTemp(event, Biomes.THE_END) &&
                        (RSMineshaftsConfig.endMineshaftMinIslandThickness.get() == 0 || !BiomeSelection.isBiomeTemp(event, Biomes.END_BARRENS, Biomes.SMALL_END_ISLANDS))))
        {
            event.addStructure(RSConfiguredStructures.MINESHAFT_END);
        }
    }

    private static boolean genericMineshaftCheck(TemporaryBiomeInjection.BiomeInjectionHelper context, StructureFeature<?> structureFeature, Supplier<Boolean> condition) {
        return BiomeSelection.isBiomeAllowedTemp(context, structureFeature,
                () -> BiomeSelection.doesNotHaveStructureTypeTemp(context, RSStructureTagMap.STRUCTURE_TAGS.MINESHAFT) && condition.get());
    }
}
