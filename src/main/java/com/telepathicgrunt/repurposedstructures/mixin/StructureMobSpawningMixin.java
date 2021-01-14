package com.telepathicgrunt.repurposedstructures.mixin;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(NoiseChunkGenerator.class)
public class StructureMobSpawningMixin {

    @Inject(
            method = "getEntitySpawnList(Lnet/minecraft/world/biome/Biome;Lnet/minecraft/world/gen/StructureAccessor;Lnet/minecraft/entity/SpawnGroup;Lnet/minecraft/util/math/BlockPos;)Ljava/util/List;",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void structureMobs(Biome biome, StructureAccessor accessor, SpawnGroup group, BlockPos pos, CallbackInfoReturnable<List<SpawnSettings.SpawnEntry>> cir) {
        List<SpawnSettings.SpawnEntry> list = getStructureSpawns(biome, accessor, group, pos);
        if(list != null) cir.setReturnValue(list);
    }


    private static List<SpawnSettings.SpawnEntry>  getStructureSpawns(Biome biome, StructureAccessor accessor, SpawnGroup group, BlockPos pos){
        if(group == SpawnGroup.MONSTER){
            for(StructureFeature<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.APPEND_WITH_NATURAL_MOBS)){
                if (!structureFeature.getMonsterSpawns().isEmpty() && accessor.getStructureAt(pos, true, structureFeature).hasChildren()) {
                    return Lists.newArrayList(Iterators.concat(biome.getSpawnSettings().getSpawnEntry(SpawnGroup.MONSTER).iterator(), structureFeature.getMonsterSpawns().iterator()));
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
                    return Lists.newArrayList(Iterators.concat(biome.getSpawnSettings().getSpawnEntry(SpawnGroup.CREATURE).iterator(), structureFeature.getMonsterSpawns().iterator()));
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