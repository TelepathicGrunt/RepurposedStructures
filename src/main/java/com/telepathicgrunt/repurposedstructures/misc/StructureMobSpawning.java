package com.telepathicgrunt.repurposedstructures.misc;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.List;

public class StructureMobSpawning {

    /**
     * Using my internal tag system, we can return the correct list of mobs to spawn for the structure and whether
     * the mob list returned will override or merge with the biome's mob list as well.
     */
    public static Pool<SpawnSettings.SpawnEntry> getStructureSpawns(Biome biome, StructureAccessor accessor, SpawnGroup group, BlockPos pos){
        if(group == SpawnGroup.MONSTER){
            for(StructureFeature<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.APPEND_WITH_NATURAL_MOBS)){
                if (!structureFeature.getMonsterSpawns().isEmpty() && accessor.getStructureAt(pos, true, structureFeature).hasChildren()) {
                    return Pool.of(Lists.newArrayList(Iterators.concat(biome.getSpawnSettings().getSpawnEntries(SpawnGroup.MONSTER).getEntries().iterator(), structureFeature.getMonsterSpawns().getEntries().iterator())));
                }
            }

            for(StructureFeature<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.REPLACE_NATURAL_MOBS)){
                if (!structureFeature.getMonsterSpawns().isEmpty() && accessor.getStructureAt(pos, true, structureFeature).hasChildren()) {
                    return structureFeature.getMonsterSpawns();
                }
            }
        }

        else if(group == SpawnGroup.CREATURE){
            for(StructureFeature<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.APPEND_WITH_NATURAL_MOBS)){
                if (!structureFeature.getCreatureSpawns().isEmpty() && accessor.getStructureAt(pos, true, structureFeature).hasChildren()) {
                    return Pool.of(Lists.newArrayList(Iterators.concat(biome.getSpawnSettings().getSpawnEntries(SpawnGroup.CREATURE).getEntries().iterator(), structureFeature.getMonsterSpawns().getEntries().iterator())));
                }
            }

            for(StructureFeature<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.REPLACE_NATURAL_MOBS)){
                if (!structureFeature.getCreatureSpawns().isEmpty() && accessor.getStructureAt(pos, true, structureFeature).hasChildren()) {
                    return structureFeature.getCreatureSpawns();
                }
            }
        }

        return null;
    }
}
