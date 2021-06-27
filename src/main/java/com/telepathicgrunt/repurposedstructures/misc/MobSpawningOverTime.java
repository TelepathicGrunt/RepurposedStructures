package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MobSpawningOverTime {

    // Needed so that config api can handle reading/writing the entry to and from file.
    public static class PublicMobSpawnEntry {
        public final String type;
        public final int weight;
        public final int minGroupSize;
        public final int maxGroupSize;

        public PublicMobSpawnEntry(String type, int weight, int minGroupSize, int maxGroupSize){
            this.type = type;
            this.weight = weight;
            this.minGroupSize = minGroupSize;
            this.maxGroupSize = maxGroupSize;
        }
    }

    public static final Map<SpawnGroup, Map<StructureFeature<?>, List<SpawnSettings.SpawnEntry>>> REPLACE_MOB_SPAWNING = new HashMap<>();
    public static final Map<SpawnGroup, Map<StructureFeature<?>, List<SpawnSettings.SpawnEntry>>> APPEND_MOB_SPAWNING = new HashMap<>();

    public static void setupMobSpawningMaps(){
        Arrays.stream(SpawnGroup.values()).forEach(group -> REPLACE_MOB_SPAWNING.put(group, new HashMap<>()));
        Arrays.stream(SpawnGroup.values()).forEach(group -> APPEND_MOB_SPAWNING.put(group, new HashMap<>()));

        // Parse and add all the structure mobs to the maps
        setupMap(REPLACE_MOB_SPAWNING, RepurposedStructures.omegaMobSpawnConfig.replaceMobSpawns, "replace mob spawning");
        setupMap(APPEND_MOB_SPAWNING, RepurposedStructures.omegaMobSpawnConfig.appendMobSpawns, "append mob spawning");
    }

    private static void setupMap(Map<SpawnGroup, Map<StructureFeature<?>, List<SpawnSettings.SpawnEntry>>> mapToFillWithMobSpawns,
                                 Map<String, List<PublicMobSpawnEntry>> configMap,
                                 String errorMsg) {

        for(Map.Entry<String, List<PublicMobSpawnEntry>> configMapEntry : configMap.entrySet()){
            // validate to make sure we only are affecting Repurposed Structures's structures.
            if(!configMapEntry.getKey().equals("all") && !configMapEntry.getKey().contains(RepurposedStructures.MODID)) {
                RepurposedStructures.LOGGER.warn("Found key that is not a repurposed structure's structure in {} config: {} Skipping that entry...", errorMsg, configMapEntry.getKey());
                continue;
            }


            // Adds to all of RS's Structures
            if(configMapEntry.getKey().equals("all")){
                RSStructures.RS_STRUCTURES.forEach((key, value) -> {
                    for(PublicMobSpawnEntry spawnEntry : configMapEntry.getValue()){
                        // Parse and make sure the entity type exists
                        Optional<EntityType<?>> entityType = Registry.ENTITY_TYPE.getOrEmpty(new Identifier(spawnEntry.type));
                        if(entityType.isEmpty()){
                            RepurposedStructures.LOGGER.warn("Unknown EntityType {} was found in the {} config. Skipping that entry...", configMapEntry.getKey(), errorMsg);
                            continue;
                        }
                        SpawnSettings.SpawnEntry entryToAdd = new SpawnSettings.SpawnEntry(
                                entityType.get(),
                                spawnEntry.weight,
                                spawnEntry.minGroupSize,
                                spawnEntry.maxGroupSize);


                        // Add the spawn entry for the structure into map.
                        // No usage of .merge due to performance reasons of making a new list every time for merge.
                        Map<StructureFeature<?>, List<SpawnSettings.SpawnEntry>> structureMobMap = mapToFillWithMobSpawns.get(entryToAdd.type.getSpawnGroup());
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
                StructureFeature<?> structureFeature = Registry.STRUCTURE_FEATURE.get(new Identifier(configMapEntry.getKey()));
                if(structureFeature == null){
                    RepurposedStructures.LOGGER.warn("Unknown key {} was found in the {} config. Skipping that entry...", configMapEntry.getKey(), errorMsg);
                    continue;
                }

                for(PublicMobSpawnEntry spawnEntry : configMapEntry.getValue()){
                    // Parse and make sure the entity type exists
                    Optional<EntityType<?>> entityType = Registry.ENTITY_TYPE.getOrEmpty(new Identifier(spawnEntry.type));
                    if(entityType.isEmpty()){
                        RepurposedStructures.LOGGER.warn("Unknown EntityType {} was found in the {} config. Skipping that entry...", configMapEntry.getKey(), errorMsg);
                        continue;
                    }
                    SpawnSettings.SpawnEntry entryToAdd = new SpawnSettings.SpawnEntry(
                            entityType.get(),
                            spawnEntry.weight,
                            spawnEntry.minGroupSize,
                            spawnEntry.maxGroupSize);

                    // Add the spawn entry for the structure into map.
                    // No usage of .merge due to performance reasons of making a new list every time for merge.
                    Map<StructureFeature<?>, List<SpawnSettings.SpawnEntry>> structureMobMap = mapToFillWithMobSpawns.get(entryToAdd.type.getSpawnGroup());
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
    public static Pool<SpawnSettings.SpawnEntry> getStructureSpawns(Biome biome, StructureAccessor accessor, SpawnGroup spawnGroup, BlockPos pos){
        // Replace spawns
        for(Map.Entry<StructureFeature<?>, List<SpawnSettings.SpawnEntry>> structureEntry : MobSpawningOverTime.REPLACE_MOB_SPAWNING.get(spawnGroup).entrySet()){
            if (!structureEntry.getValue().isEmpty() && accessor.getStructureAt(pos, true, structureEntry.getKey()).hasChildren()) {
                return Pool.of(structureEntry.getValue());
            }
        }

        // Appended spawns (combined with other structure appended spawns and to biome spawns)
        List<SpawnSettings.SpawnEntry> appendedSpawn = null;
        for(Map.Entry<StructureFeature<?>, List<SpawnSettings.SpawnEntry>> structureEntry : MobSpawningOverTime.APPEND_MOB_SPAWNING.get(spawnGroup).entrySet()){
            if (!structureEntry.getValue().isEmpty() && accessor.getStructureAt(pos, true, structureEntry.getKey()).hasChildren()) {
                if(appendedSpawn == null) appendedSpawn = new ArrayList<>(biome.getSpawnSettings().getSpawnEntries(spawnGroup).getEntries());
                appendedSpawn.addAll(structureEntry.getValue());
            }
        }
        return appendedSpawn == null ? null : Pool.of(appendedSpawn);
    }
}
