package com.telepathicgrunt.repurposedstructures.biomeinjection;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;

import java.util.function.Predicate;

public final class BiomeInjection {
    private BiomeInjection() {}

    public static void addStructureToBiomes() {
        Bastions.addBastions();
        Cities.addCities();
        Fortresses.addJungleFortress();
        Igloos.addIgloos();
        Mansions.addMansions();
        Mineshafts.addMineshafts();
        Outposts.addOutposts();
        Pyramids.addPyramids();
        RuinedPortals.addRuinedPortals();
        Ruins.addRuins();
        Shipwrecks.addShipwrecks();
        Strongholds.addStrongholds();
        Temples.addTemples();
        Villages.addVillages();
        WitchHuts.addWitchHuts();
    }

    public static void addStructure(ConfiguredStructureFeature<?,?> configuredStructureFeature, Predicate<BiomeSelectionContext> check) {
        BiomeModifications.create(Registry.STRUCTURE_FEATURE.getKey(configuredStructureFeature.feature)).add(
            ModificationPhase.ADDITIONS,
            check,
            context -> context.getGenerationSettings().addStructure(
                ResourceKey.create(
                    Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
                    Registry.STRUCTURE_FEATURE.getKey(configuredStructureFeature.feature)
                )
            )
    );
    }
}
