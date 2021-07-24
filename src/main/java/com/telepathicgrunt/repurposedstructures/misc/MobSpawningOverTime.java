package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;

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

    public static final Map<EntityClassification, Map<Structure<?>, List<MobSpawnInfo.Spawners>>> REPLACE_MOB_SPAWNING = new HashMap<>();
    public static final Map<EntityClassification, Map<Structure<?>, List<MobSpawnInfo.Spawners>>> APPEND_MOB_SPAWNING = new HashMap<>();

    public static void setupMobSpawningMaps(){
        Arrays.stream(EntityClassification.values()).forEach(group -> REPLACE_MOB_SPAWNING.put(group, new HashMap<>()));
        Arrays.stream(EntityClassification.values()).forEach(group -> APPEND_MOB_SPAWNING.put(group, new HashMap<>()));

        // Parse and add all the structure mobs to the maps
//        setupMap(REPLACE_MOB_SPAWNING, RepurposedStructures.omegaMobSpawnConfig.replaceMobSpawns, "replace mob spawning");
//        setupMap(APPEND_MOB_SPAWNING, RepurposedStructures.omegaMobSpawnConfig.appendMobSpawns, "append mob spawning");
    }

    private static void setupMap(Map<EntityClassification, Map<Structure<?>, List<MobSpawnInfo.Spawners>>> mapToFillWithMobSpawns,
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
                        if(!entityType.isPresent()){
                            RepurposedStructures.LOGGER.warn("Repurposed Structures (first): Unknown EntityType {} was found in the {} config. Skipping that entry...", spawnEntry.type, errorMsg);
                            continue;
                        }
                        MobSpawnInfo.Spawners entryToAdd = new MobSpawnInfo.Spawners(
                                entityType.get(),
                                spawnEntry.weight,
                                spawnEntry.minGroupSize,
                                spawnEntry.maxGroupSize);


                        // Add the spawn entry for the structure into map.
                        // No usage of .merge due to performance reasons of making a new list every time for merge.
                        Map<Structure<?>, List<MobSpawnInfo.Spawners>> structureMobMap = mapToFillWithMobSpawns.get(entryToAdd.type.getCategory());
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
                Structure<?> structureFeature = Registry.STRUCTURE_FEATURE.get(new ResourceLocation(configMapEntry.getKey()));
                if(structureFeature == null){
                    RepurposedStructures.LOGGER.warn("Repurposed Structures: Unknown key {} was found in the {} config. Skipping that entry...", configMapEntry.getKey(), errorMsg);
                    continue;
                }

                for(PublicMobSpawnEntry spawnEntry : configMapEntry.getValue()){
                    // Parse and make sure the entity type exists
                    Optional<EntityType<?>> entityType = Registry.ENTITY_TYPE.getOptional(new ResourceLocation(spawnEntry.type));
                    if(!entityType.isPresent()){
                        RepurposedStructures.LOGGER.warn("Repurposed Structures (second): Unknown EntityType {} was found in the {} config. Skipping that entry...", spawnEntry.type, errorMsg);
                        continue;
                    }
                    MobSpawnInfo.Spawners entryToAdd = new MobSpawnInfo.Spawners(
                            entityType.get(),
                            spawnEntry.weight,
                            spawnEntry.minGroupSize,
                            spawnEntry.maxGroupSize);

                    // Add the spawn entry for the structure into map.
                    // No usage of .merge due to performance reasons of making a new list every time for merge.
                    Map<Structure<?>, List<MobSpawnInfo.Spawners>> structureMobMap = mapToFillWithMobSpawns.get(entryToAdd.type.getCategory());
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
    public static List<MobSpawnInfo.Spawners> getStructureSpawns(Biome biome, StructureManager accessor, EntityClassification spawnGroup, BlockPos pos){
        // Forge already handles replacing mob spawns for us

        // Appended spawns (combined with other structure appended spawns and to biome spawns)
        List<MobSpawnInfo.Spawners> appendedSpawn = null;
        for(Map.Entry<Structure<?>, List<MobSpawnInfo.Spawners>> structureEntry : MobSpawningOverTime.APPEND_MOB_SPAWNING.get(spawnGroup).entrySet()){
            if (!structureEntry.getValue().isEmpty() && accessor.getStructureAt(pos, true, structureEntry.getKey()).isValid()) {
                if(appendedSpawn == null) appendedSpawn = new ArrayList<>(biome.getMobSettings().getMobs(spawnGroup));
                appendedSpawn.addAll(structureEntry.getValue());
            }
        }
        return appendedSpawn == null ? null : new ArrayList<>(appendedSpawn);
    }
}
