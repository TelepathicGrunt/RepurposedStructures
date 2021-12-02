package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;

import java.util.Map;
import java.util.function.Consumer;

public final class TemporaryBiomeInjection {
    private TemporaryBiomeInjection() {}

    public static void addStructureToBiomes(Map<StructureFeature<?>, Multimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> structureToMultiMap, Registry<Biome> biomeRegistry) {
        addConfiguredStructureEntries(structureToMultiMap, biomeRegistry, Bastions::addBastions);
        addConfiguredStructureEntries(structureToMultiMap, biomeRegistry, Cities::addCities);
        addConfiguredStructureEntries(structureToMultiMap, biomeRegistry, Fortresses::addJungleFortress);
        addConfiguredStructureEntries(structureToMultiMap, biomeRegistry, Igloos::addIgloos);
        addConfiguredStructureEntries(structureToMultiMap, biomeRegistry, Mansions::addMansions);
        addConfiguredStructureEntries(structureToMultiMap, biomeRegistry, Mineshafts::addMineshafts);
        addConfiguredStructureEntries(structureToMultiMap, biomeRegistry, Outposts::addOutposts);
        addConfiguredStructureEntries(structureToMultiMap, biomeRegistry, Pyramids::addPyramids);
        addConfiguredStructureEntries(structureToMultiMap, biomeRegistry, RuinedPortals::addRuinedPortals);
        addConfiguredStructureEntries(structureToMultiMap, biomeRegistry, Ruins::addRuins);
        addConfiguredStructureEntries(structureToMultiMap, biomeRegistry, Shipwrecks::addShipwrecks);
        addConfiguredStructureEntries(structureToMultiMap, biomeRegistry, Strongholds::addStrongholds);
        addConfiguredStructureEntries(structureToMultiMap, biomeRegistry, Temples::addTemples);
        addConfiguredStructureEntries(structureToMultiMap, biomeRegistry, Villages::addVillages);
        addConfiguredStructureEntries(structureToMultiMap, biomeRegistry, WitchHuts::addWitchHuts);
    }

    private static void addConfiguredStructureEntries(Map<StructureFeature<?>, Multimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> structureToMultiMap, Registry<Biome> biomeRegistry, Consumer<BiomeInjectionHelper> structureAddition) {
        for(Map.Entry<ResourceKey<Biome>, Biome> biomeEntry : biomeRegistry.entrySet()) {
            structureAddition.accept(new BiomeInjectionHelper(biomeEntry, biomeRegistry, structureToMultiMap));
        }
    }

    public static class BiomeInjectionHelper {
        public final Biome biome;
        public final ResourceKey<Biome> biomeKey;
        public final Registry<Biome> biomeRegistry;
        public final Map<StructureFeature<?>, Multimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> structureToMultiMap;
        public BiomeInjectionHelper(Map.Entry<ResourceKey<Biome>, Biome> biomeEntry, Registry<Biome> biomeRegistry, Map<StructureFeature<?>, Multimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> structureToMultiMap) {
            this.biome = biomeEntry.getValue();
            this.biomeKey = biomeEntry.getKey();
            this.biomeRegistry = biomeRegistry;
            this.structureToMultiMap = structureToMultiMap;
        }

        public ResourceKey<Biome> getBiomeKey() {
            return biomeRegistry.getResourceKey(biome).get();
        }

        public void addStructure(ConfiguredStructureFeature<?, ?> configuredStructureFeature) {
            structureToMultiMap.computeIfAbsent(configuredStructureFeature.feature, (f) -> HashMultimap.create());
            structureToMultiMap.get(configuredStructureFeature.feature).put(configuredStructureFeature, this.biomeKey);
        }

        public void removeStructure(ConfiguredStructureFeature<?, ?> configuredStructureFeature) {
            Multimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> structureFeatureResourceKeyMultimap = structureToMultiMap.get(configuredStructureFeature.feature);
            structureFeatureResourceKeyMultimap.remove(configuredStructureFeature, biomeKey);
        }
    }
}
