package com.telepathicgrunt.repurposedstructures.world.biomemodifiers;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.GenerationStep;

public class BiomeModifier {
    public static void addFeatures() {
        addToBiome("dungeons/badlands", GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        addToBiome("dungeons/dark_forest", GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        addToBiome("dungeons/deep", GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        addToBiome("dungeons/desert", GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        addToBiome("dungeons/end", GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        addToBiome("dungeons/icy", GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        addToBiome("dungeons/jungle", GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        addToBiome("dungeons/mushroom", GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        addToBiome("dungeons/nether", GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        addToBiome("dungeons/ocean", GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        addToBiome("dungeons/snow", GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        addToBiome("dungeons/swamp", GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        addToBiome("wells/badlands", GenerationStep.Decoration.SURFACE_STRUCTURES);
        addToBiome("wells/forest", GenerationStep.Decoration.SURFACE_STRUCTURES);
        addToBiome("wells/mossy_stone", GenerationStep.Decoration.SURFACE_STRUCTURES);
        addToBiome("wells/mushroom", GenerationStep.Decoration.SURFACE_STRUCTURES);
        addToBiome("wells/nether", GenerationStep.Decoration.SURFACE_STRUCTURES);
        addToBiome("wells/snow", GenerationStep.Decoration.SURFACE_STRUCTURES);

        removeFromBiome(new ResourceLocation("minecraft", "monster_room_deep"), "has_structure/dungeons/deep", GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
    }

    private static void addToBiome(String featureName, GenerationStep.Decoration step) {
        BiomeModifications.create(new ResourceLocation(RepurposedStructures.MODID, featureName))
                .add(ModificationPhase.ADDITIONS,
                        (context) -> context.hasTag(TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("has_structure/" + featureName))),
                        (context) -> context.getGenerationSettings().addFeature(step, ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, new ResourceLocation(RepurposedStructures.MODID, featureName))));
    }

    private static void removeFromBiome(ResourceLocation feature, String biomeTagName, GenerationStep.Decoration step) {
        BiomeModifications.create(new ResourceLocation(RepurposedStructures.MODID, "remove_" + feature.getPath()))
                .add(ModificationPhase.REMOVALS,
                        (context) -> context.hasTag(TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(biomeTagName))),
                        (context) -> context.getGenerationSettings().removeFeature(step, ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, feature)));
    }
}
