package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.function.Supplier;

public class Dungeons {

    public static void addDungeons(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSDungeonsConfig.jungleDungeonAttemptsPerChunk.get() != 0 &&
            genericDungeonCheck(event, RSConfiguredFeatures.JUNGLE_DUNGEONS,
                () -> BiomeSelection.haveCategories(event, Category.JUNGLE)))
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.JUNGLE_DUNGEONS);
        }
        
        if (RepurposedStructures.RSDungeonsConfig.badlandsDungeonAttemptsPerChunk.get() != 0 &&
            genericDungeonCheck(event, RSConfiguredFeatures.BADLANDS_DUNGEONS,
                () -> BiomeSelection.haveCategories(event, Category.MESA)))
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.BADLANDS_DUNGEONS);
        }
        
        if (RepurposedStructures.RSDungeonsConfig.darkForestDungeonAttemptsPerChunk.get() != 0 &&
            genericDungeonCheck(event, RSConfiguredFeatures.DARK_FOREST_DUNGEONS,
                () -> BiomeSelection.haveCategories(event, Category.FOREST) &&
                BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted")))
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.DARK_FOREST_DUNGEONS);
        }
        
        if (RepurposedStructures.RSDungeonsConfig.desertDungeonAttemptsPerChunk.get() != 0 &&
            genericDungeonCheck(event, RSConfiguredFeatures.DESERT_DUNGEONS,
                () -> BiomeSelection.haveCategories(event, Category.DESERT)))
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.DESERT_DUNGEONS);
        }
        
        if (RepurposedStructures.RSDungeonsConfig.mushroomDungeonAttemptsPerChunk.get() != 0)
        {
            if(BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.MUSHROOM_HIGH_DUNGEONS,
                        () -> BiomeSelection.haveCategories(event, Category.MUSHROOM) &&
                        RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get() > 62))
            {
                replaceOrAddDungeon(true, event, RSConfiguredFeatures.MUSHROOM_HIGH_DUNGEONS);
            }

            if(BiomeSelection.isBiomeAllowed(event, RSConfiguredFeatures.MUSHROOM_HIGH_DUNGEONS,
                        () -> BiomeSelection.haveCategories(event, Category.MUSHROOM) &&
                        RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get() <= 62))
            {
                replaceOrAddDungeon(true, event, RSConfiguredFeatures.MUSHROOM_LOW_DUNGEONS);
            }
        }
        
        if (RepurposedStructures.RSDungeonsConfig.swampDungeonAttemptsPerChunk.get() != 0 &&
            genericDungeonCheck(event, RSConfiguredFeatures.SWAMP_DUNGEONS,
                () -> BiomeSelection.haveCategories(event, Category.SWAMP)))
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.SWAMP_DUNGEONS);
        }

        if (RepurposedStructures.RSDungeonsConfig.icyDungeonAttemptsPerChunk.get() != 0 &&
            genericDungeonCheck(event, RSConfiguredFeatures.ICY_DUNGEONS,
                () -> BiomeSelection.haveCategories(event, Category.ICY) &&
                (BiomeSelection.hasName(event, "icy", "ice", "frozen") || (event.getClimate().temperature < 0 && !BiomeSelection.hasName(event, "snow")))))
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.ICY_DUNGEONS);
        }
        
        if (RepurposedStructures.RSDungeonsConfig.snowDungeonAttemptsPerChunk.get() != 0 &&
            genericDungeonCheck(event, RSConfiguredFeatures.SNOW_DUNGEONS,
                () -> BiomeSelection.haveCategories(event, Category.ICY) &&
                !(BiomeSelection.hasName(event, "icy", "ice", "frozen") || (event.getClimate().temperature < 0 && !BiomeSelection.hasName(event, "snow")))))
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.SNOW_DUNGEONS);
        }
        
        if (RepurposedStructures.RSDungeonsConfig.netherDungeonAttemptsPerChunk.get() != 0 &&
            genericDungeonCheck(event, RSConfiguredFeatures.NETHER_DUNGEONS,
                () -> BiomeSelection.haveCategories(event, Category.NETHER)))
        {
            // Vegetal to match Nether Mineshafts
            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> RSConfiguredFeatures.NETHER_DUNGEONS);
        }
        
        if (RepurposedStructures.RSDungeonsConfig.endDungeonAttemptsPerChunk.get() != 0 &&
            genericDungeonCheck(event, RSConfiguredFeatures.END_DUNGEONS,
                () -> BiomeSelection.haveCategories(event, Category.THEEND) &&
                !BiomeSelection.isBiome(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS)))
        {
            replaceOrAddDungeon(false, event, RSConfiguredFeatures.END_DUNGEONS);
        }

        if (RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get() != 0)
        {
            // Thanks to vanilla oceans all being same temperature, we have to use the has Namespace to correctly get them.
            if(genericDungeonCheck(event, RSConfiguredFeatures.OCEAN_LUKEWARM_DUNGEONS,
                    () -> BiomeSelection.haveCategories(event, Category.OCEAN) &&
                    (BiomeSelection.hasName(event, "lukewarm") ||
                    (!BiomeSelection.hasNamespace(event, "minecraft") && event.getClimate().temperature >= 0.9f && event.getClimate().temperature < 1.5f))))
            {
                replaceOrAddDungeon(false, event, RSConfiguredFeatures.OCEAN_LUKEWARM_DUNGEONS);
            }

            if(genericDungeonCheck(event, RSConfiguredFeatures.OCEAN_WARM_DUNGEONS,
                    () -> BiomeSelection.haveCategories(event, Category.OCEAN) &&
                    ((BiomeSelection.hasName(event, "hot", "tropic", "warm") && !BiomeSelection.hasName(event, "lukewarm")) ||
                    (!BiomeSelection.hasNamespace(event, "minecraft") && event.getClimate().temperature >= 1.5f))))
            {
                replaceOrAddDungeon(false, event, RSConfiguredFeatures.OCEAN_WARM_DUNGEONS);
            }

            if(genericDungeonCheck(event, RSConfiguredFeatures.OCEAN_COLD_DUNGEONS,
                    () -> BiomeSelection.haveCategories(event, Category.OCEAN) &&
                    (BiomeSelection.hasName(event, "cold") ||
                    (!BiomeSelection.hasNamespace(event, "minecraft") && event.getClimate().temperature >= 0.0f && event.getClimate().temperature < 0.5f))))
            {
                replaceOrAddDungeon(false, event, RSConfiguredFeatures.OCEAN_COLD_DUNGEONS);
            }

            if(genericDungeonCheck(event, RSConfiguredFeatures.OCEAN_FROZEN_DUNGEONS,
                    () -> BiomeSelection.haveCategories(event, Category.OCEAN) &&
                    (BiomeSelection.hasName(event, "frozen", "snow", "ice") ||
                    (!BiomeSelection.hasNamespace(event, "minecraft") && event.getClimate().temperature >= 0.9f && event.getClimate().temperature < 1.5f))))
            {
                replaceOrAddDungeon(false, event, RSConfiguredFeatures.OCEAN_FROZEN_DUNGEONS);
            }

            if(genericDungeonCheck(event, RSConfiguredFeatures.OCEAN_NEUTRAL_DUNGEONS,
                    () -> BiomeSelection.haveCategories(event, Category.OCEAN) &&
                    (!BiomeSelection.hasName(event, "hot", "tropic", "warm", "cold", "frozen", "snow", "ice") ||
                    (!BiomeSelection.hasNamespace(event, "minecraft") && event.getClimate().temperature >= 0.5f && event.getClimate().temperature < 0.9f))))
            {
                replaceOrAddDungeon(false, event, RSConfiguredFeatures.OCEAN_NEUTRAL_DUNGEONS);
            }
        }
    }

    private static boolean genericDungeonCheck(BiomeLoadingEvent context, ConfiguredFeature<?,?> configuredFeature, Supplier<Boolean> condition) {
        return BiomeSelection.isBiomeAllowed(context, configuredFeature,
                () -> RSConfiguredFeatures.RS_DUNGEONS.stream().noneMatch(cf -> BiomeSelection.hasFeature(context, cf)) && condition.get());
    }

    /**
     * Adds RS's dungeon to the biome along with option to remove vanilla dungeon as well.
     */
    private static void replaceOrAddDungeon(boolean replacing, BiomeLoadingEvent event, ConfiguredFeature<?, ?> rsDungeon) {

        //remove vanilla dungeon
        if (replacing && RepurposedStructures.yungsBetterDungeonsIsNotOn) {
            event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(supplier -> supplier.get().feature.equals(Features.MONSTER_ROOM.feature));
        }

        //add given dungeon
        event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).add(() -> rsDungeon);
    }
}
