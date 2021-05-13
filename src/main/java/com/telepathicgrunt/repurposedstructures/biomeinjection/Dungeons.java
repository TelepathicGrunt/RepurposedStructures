package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Dungeons {

    public static void addDungeons(BiomeLoadingEvent event) {

        if (BiomeSelection.haveCategories(event, Category.JUNGLE) &&
                RepurposedStructures.RSDungeonsConfig.jungleDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event))
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.JUNGLE_DUNGEONS);
        }
        
        else if (BiomeSelection.haveCategories(event, Category.MESA) &&
                RepurposedStructures.RSDungeonsConfig.badlandsDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event))
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.BADLANDS_DUNGEONS);
        }
        
        else if (BiomeSelection.haveCategories(event, Category.FOREST) &&
                BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted") &&
                RepurposedStructures.RSDungeonsConfig.darkForestDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event))
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.DARK_FOREST_DUNGEONS);
        }
        
        else if (BiomeSelection.haveCategories(event, Category.DESERT) &&
                RepurposedStructures.RSDungeonsConfig.desertDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event))
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.DESERT_DUNGEONS);
        }
        
        else if (BiomeSelection.haveCategories(event, Category.MUSHROOM) &&
                RepurposedStructures.RSDungeonsConfig.mushroomDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event))
        {
            if(RepurposedStructures.RSDungeonsConfig.mushroomDungeonMaxHeight.get() > 62)
                replaceOrAddDungeon(true, event, RSConfiguredFeatures.MUSHROOM_HIGH_DUNGEONS);
            if(RepurposedStructures.RSDungeonsConfig.mushroomDungeonMinHeight.get() <= 62)
                replaceOrAddDungeon(true, event, RSConfiguredFeatures.MUSHROOM_LOW_DUNGEONS);
        }
        
        else if (BiomeSelection.haveCategories(event, Category.SWAMP) &&
                RepurposedStructures.RSDungeonsConfig.swampDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event)) 
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.SWAMP_DUNGEONS);
        }

        else if (BiomeSelection.haveCategories(event, Category.ICY) &&
                (BiomeSelection.hasName(event, "icy", "ice", "frozen") ||
                    (event.getClimate().temperature < 0 && !BiomeSelection.hasName(event, "snow"))) &&
                RepurposedStructures.RSDungeonsConfig.icyDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event))
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.ICY_DUNGEONS);
        }
        
        else if (BiomeSelection.haveCategories(event, Category.ICY) &&
                RepurposedStructures.RSDungeonsConfig.snowDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event)) 
        {
            replaceOrAddDungeon(true, event, RSConfiguredFeatures.SNOW_DUNGEONS);
        }
        
        else if (BiomeSelection.haveCategories(event, Category.NETHER) &&
                RepurposedStructures.RSDungeonsConfig.netherDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event)) 
        {
            // Vegetal to match Nether Mineshafts
            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> RSConfiguredFeatures.NETHER_DUNGEONS);
        }
        
        else if (RepurposedStructures.RSDungeonsConfig.endDungeonAttemptsPerChunk.get() != 0 &&
                BiomeSelection.haveCategories(event, Category.THEEND) &&
                !BiomeSelection.isBiome(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS) &&
                dungeonAllowedByNamespaceAndConfig(event)) 
        {
            replaceOrAddDungeon(false, event, RSConfiguredFeatures.END_DUNGEONS);
        }

        else if (BiomeSelection.haveCategories(event, Category.OCEAN) &&
                RepurposedStructures.RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get() != 0 &&
                dungeonAllowedByNamespaceAndConfig(event)) 
        {
            // Thanks to vanilla oceans all being same temperature, we have to use the has Namespace to correctly get them.
            if(BiomeSelection.hasName(event, "lukewarm") ||
                (!BiomeSelection.hasNamespace(event, "minecraft") &&
                event.getClimate().temperature >= 0.9f && event.getClimate().temperature < 1.5f))
            {
                replaceOrAddDungeon(false, event, RSConfiguredFeatures.OCEAN_LUKEWARM_DUNGEONS);
            }
            else if(BiomeSelection.hasName(event, "lukewarm") ||
                    (!BiomeSelection.hasNamespace(event, "minecraft") &&
                    event.getClimate().temperature >= 1.5f))
            {
                replaceOrAddDungeon(false, event, RSConfiguredFeatures.OCEAN_WARM_DUNGEONS);
            }
            else if(BiomeSelection.hasName(event, "cold") ||
                    (!BiomeSelection.hasNamespace(event, "minecraft") &&
                    event.getClimate().temperature >= 0.0f && event.getClimate().temperature < 0.5f))
            {
                replaceOrAddDungeon(false, event, RSConfiguredFeatures.OCEAN_COLD_DUNGEONS);
            }
            else if(BiomeSelection.hasName(event, "frozen", "snow", "ice") ||
                    (!BiomeSelection.hasNamespace(event, "minecraft") &&
                    event.getClimate().temperature >= 0.9f && event.getClimate().temperature < 1.5f))
            {
                replaceOrAddDungeon(false, event, RSConfiguredFeatures.OCEAN_FROZEN_DUNGEONS);
            }
            else{
                replaceOrAddDungeon(false, event, RSConfiguredFeatures.OCEAN_NEUTRAL_DUNGEONS);
            }
        }
    }


    /**
     * Adds RS's dungeon to the biome along with option to remove vanilla dungeon as well.
     */
    private static void replaceOrAddDungeon(boolean replacing, BiomeLoadingEvent event, ConfiguredFeature<?, ?> rsDungeon) {

        //remove vanilla dungeon
        if (replacing) {
            event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).removeIf(supplier -> supplier.get().feature.equals(Features.MONSTER_ROOM.feature));
        }

        //add given dungeon
        event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_STRUCTURES).add(() -> rsDungeon);
    }
    

    /**
     * Will check if the dungeon is allowed in modded biomes based on config but will always return true for vanilla biomes.
     */
    private static boolean dungeonAllowedByNamespaceAndConfig(BiomeLoadingEvent event) {
        return BiomeSelection.hasNamespace(event, "minecraft") || RepurposedStructures.RSDungeonsConfig.addDungeonsToModdedBiomes.get();
    }
}
