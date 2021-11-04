package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.feature.StructureFeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class MobSpawningOverTime {
    private MobSpawningOverTime() {}

    // Needed so that config api can handle reading/writing the entry to and from file.
    public static class PublicMobSpawnEntry {
        public final String type;
        public final int weight;
        public final int minGroupSize;
        public final int maxGroupSize;
        public final boolean logErrorIfNotFound;

        public PublicMobSpawnEntry(String type, int weight, int minGroupSize, int maxGroupSize, boolean logErrorIfNotFound){
            this.type = type;
            this.weight = weight;
            this.minGroupSize = minGroupSize;
            this.maxGroupSize = maxGroupSize;
            this.logErrorIfNotFound = logErrorIfNotFound;
        }

        public PublicMobSpawnEntry(String type, int weight, int minGroupSize, int maxGroupSize){
            this(type, weight, minGroupSize, maxGroupSize, true);
        }
    }

    public static final Map<MobCategory, Map<StructureFeature<?>, List<MobSpawnSettings.SpawnerData>>> REPLACE_MOB_SPAWNING = new HashMap<>();
    public static final Map<MobCategory, Map<StructureFeature<?>, List<MobSpawnSettings.SpawnerData>>> APPEND_MOB_SPAWNING = new HashMap<>();

    public static void setupMobSpawningMaps(){
        Arrays.stream(MobCategory.values()).forEach(group -> REPLACE_MOB_SPAWNING.put(group, new HashMap<>()));
        Arrays.stream(MobCategory.values()).forEach(group -> APPEND_MOB_SPAWNING.put(group, new HashMap<>()));

        // Parse and add all the structure mobs to the maps
        setupMap(REPLACE_MOB_SPAWNING, RepurposedStructures.omegaMobSpawnConfig.replaceMobSpawns, "replace mob spawning");
        setupMap(APPEND_MOB_SPAWNING, RepurposedStructures.omegaMobSpawnConfig.appendMobSpawns, "append mob spawning");
    }

    private static void setupMap(Map<MobCategory, Map<StructureFeature<?>, List<MobSpawnSettings.SpawnerData>>> mapToFillWithMobSpawns,
                                 Map<String, List<PublicMobSpawnEntry>> configMap,
                                 String errorMsg) {

        for(Map.Entry<String, List<PublicMobSpawnEntry>> configMapEntry : configMap.entrySet()){
            // validate to make sure we only are affecting Repurposed Structures's structures.
            if(!configMapEntry.getKey().equals("all") && !configMapEntry.getKey().contains(RepurposedStructures.MODID)) {
                RepurposedStructures.LOGGER.warn("Repurposed Structures: Found key that is not a repurposed structure's structure in {} config: {} Skipping that entry...", errorMsg, configMapEntry.getKey());
                continue;
            }


            // Adds to all of RS's Structures
            if(configMapEntry.getKey().equals("all")){
                RSStructures.RS_STRUCTURES.forEach((key, value) -> {
                    for(PublicMobSpawnEntry spawnEntry : configMapEntry.getValue()){
                        // Parse and make sure the entity type exists
                        Optional<EntityType<?>> entityType = Registry.ENTITY_TYPE.getOptional(new ResourceLocation(spawnEntry.type));
                        if(entityType.isEmpty()){
                            if(spawnEntry.logErrorIfNotFound) {
                                RepurposedStructures.LOGGER.warn("Repurposed Structures (first): Unknown EntityType {} was found in the {} config. Skipping that entry...", spawnEntry.type, errorMsg);
                            }
                            continue;
                        }
                        MobSpawnSettings.SpawnerData entryToAdd = new MobSpawnSettings.SpawnerData(
                                entityType.get(),
                                spawnEntry.weight,
                                spawnEntry.minGroupSize,
                                spawnEntry.maxGroupSize);


                        // Add the spawn entry for the structure into map.
                        // No usage of .merge due to performance reasons of making a new list every time for merge.
                        Map<StructureFeature<?>, List<MobSpawnSettings.SpawnerData>> structureMobMap = mapToFillWithMobSpawns.get(entryToAdd.type.getCategory());
                        if(structureMobMap.containsKey(key)){
                            structureMobMap.get(key).add(entryToAdd);
                        }
                        else{
                            structureMobMap.put(key, new ArrayList<>());
                            structureMobMap.get(key).add(entryToAdd);
                        }
                    }
                });
            }
            // Adds to a targeted RS's Structure
            else{
                StructureFeature<?> structureFeature = Registry.STRUCTURE_FEATURE.get(new ResourceLocation(configMapEntry.getKey()));
                if(structureFeature == null){
                    RepurposedStructures.LOGGER.warn("Repurposed Structures: Unknown key {} was found in the {} config. Skipping that entry...", configMapEntry.getKey(), errorMsg);
                    continue;
                }

                for(PublicMobSpawnEntry spawnEntry : configMapEntry.getValue()){
                    // Parse and make sure the entity type exists
                    Optional<EntityType<?>> entityType = Registry.ENTITY_TYPE.getOptional(new ResourceLocation(spawnEntry.type));
                    if(entityType.isEmpty()){
                        if(spawnEntry.logErrorIfNotFound) {
                            RepurposedStructures.LOGGER.warn("Repurposed Structures (second): Unknown EntityType {} was found in the {} config. Skipping that entry...", spawnEntry.type, errorMsg);
                        }
                        continue;
                    }
                    MobSpawnSettings.SpawnerData entryToAdd = new MobSpawnSettings.SpawnerData(
                            entityType.get(),
                            spawnEntry.weight,
                            spawnEntry.minGroupSize,
                            spawnEntry.maxGroupSize);

                    // Add the spawn entry for the structure into map.
                    // No usage of .merge due to performance reasons of making a new list every time for merge.
                    Map<StructureFeature<?>, List<MobSpawnSettings.SpawnerData>> structureMobMap = mapToFillWithMobSpawns.get(entryToAdd.type.getCategory());
                    if(structureMobMap.containsKey(structureFeature)){
                        structureMobMap.get(structureFeature).add(entryToAdd);
                    }
                    else{
                        structureMobMap.put(structureFeature, new ArrayList<>());
                        structureMobMap.get(structureFeature).add(entryToAdd);
                    }
                }
            }
        }
    }


    /**
     * Handles actual structure mob spacing. Call this in StructureMobSpawningMixin that hooks into NoiseChunkGenerator
     */
    public static WeightedRandomList<MobSpawnSettings.SpawnerData> getStructureSpawns(Biome biome, StructureFeatureManager accessor, MobCategory spawnGroup, BlockPos pos){
        // Replace spawns
        for(Map.Entry<StructureFeature<?>, List<MobSpawnSettings.SpawnerData>> structureEntry : MobSpawningOverTime.REPLACE_MOB_SPAWNING.get(spawnGroup).entrySet()){
            if (!structureEntry.getValue().isEmpty() && accessor.getStructureAt(pos, true, structureEntry.getKey()).isValid()) {
                return WeightedRandomList.create(structureEntry.getValue());
            }
        }

        // Appended spawns (combined with other structure appended spawns and to biome spawns)
        List<MobSpawnSettings.SpawnerData> appendedSpawn = null;
        for(Map.Entry<StructureFeature<?>, List<MobSpawnSettings.SpawnerData>> structureEntry : MobSpawningOverTime.APPEND_MOB_SPAWNING.get(spawnGroup).entrySet()){
            if (!structureEntry.getValue().isEmpty() && accessor.getStructureAt(pos, true, structureEntry.getKey()).isValid()) {
                if(appendedSpawn == null) appendedSpawn = new ArrayList<>(biome.getMobSettings().getMobs(spawnGroup).unwrap());
                appendedSpawn.addAll(structureEntry.getValue());
            }
        }
        return appendedSpawn == null ? null : WeightedRandomList.create(appendedSpawn);
    }
}
