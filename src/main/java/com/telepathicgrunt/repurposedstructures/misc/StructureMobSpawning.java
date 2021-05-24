package com.telepathicgrunt.repurposedstructures.misc;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.List;

public class StructureMobSpawning {
    /**
     * Using my internal tag system, we can return the correct list of mobs to spawn for the structure
     * that will merge with the biome's mob list as well. Forge's hook does not do this extra functionality.
     */
    public static List<MobSpawnInfo.Spawners> getStructureSpawns(Biome biome, StructureManager accessor, EntityClassification group, BlockPos pos){

        if(group == EntityClassification.MONSTER){
            for(Structure<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.APPEND_WITH_NATURAL_MOBS)){
                if (!structureFeature.getDefaultSpawnList().isEmpty() && accessor.getStructureAt(pos, true, structureFeature).isValid()) {
                    return Lists.newArrayList(Iterators.concat(biome.getMobSettings().getMobs(EntityClassification.MONSTER).iterator(), structureFeature.getDefaultSpawnList().iterator()));
                }
            }
        }

        else if(group == EntityClassification.CREATURE){
            for(Structure<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.APPEND_WITH_NATURAL_MOBS)){
                if (!structureFeature.getDefaultCreatureSpawnList().isEmpty() && accessor.getStructureAt(pos, true, structureFeature).isValid()) {
                    return Lists.newArrayList(Iterators.concat(biome.getMobSettings().getMobs(EntityClassification.CREATURE).iterator(), structureFeature.getDefaultCreatureSpawnList().iterator()));
                }
            }
        }

        return null;
    }
}
