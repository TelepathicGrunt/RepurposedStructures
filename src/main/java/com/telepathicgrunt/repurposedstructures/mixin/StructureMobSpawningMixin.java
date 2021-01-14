package com.telepathicgrunt.repurposedstructures.mixin;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(NoiseChunkGenerator.class)
public class StructureMobSpawningMixin {

    /**
     * This is needed so we can do structure spawns appended to biome spawns instead of replacing biome spawns.
     * Forge's hook only replaces biome spawns so by tagging a structure with APPEND_WITH_NATURAL_MOBS, we run this
     * mixin instead which runs before Forge's hook. I asked if they could do appending in the PR and they said "no".
     * Heh. I remember when Forge kept saying they have all the hooks for everyone's "use case". My ass.
     *
     * @author TelepathicGrunt
     * @reason Return list of structure mob spawn combined with biome's mob spawn.
     */
    @Inject(
            method = "getEntitySpawnList(Lnet/minecraft/world/biome/Biome;Lnet/minecraft/world/gen/feature/structure/StructureManager;Lnet/minecraft/entity/EntityClassification;Lnet/minecraft/util/math/BlockPos;)Ljava/util/List;",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void structureMobs(Biome biome, StructureManager accessor, EntityClassification group, BlockPos pos, CallbackInfoReturnable<List<MobSpawnInfo.Spawners>> cir) {
        List<MobSpawnInfo.Spawners> list = getStructureSpawns(biome, accessor, group, pos);
        if(list != null) cir.setReturnValue(list);
    }


    private static List<MobSpawnInfo.Spawners>  getStructureSpawns(Biome biome, StructureManager accessor, EntityClassification group, BlockPos pos){

        if(group == EntityClassification.MONSTER){
            for(Structure<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.APPEND_WITH_NATURAL_MOBS)){
                if (!structureFeature.getDefaultSpawnList().isEmpty() && accessor.getStructureAt(pos, true, structureFeature).isValid()) {
                    return Lists.newArrayList(Iterators.concat(biome.getSpawnSettings().getSpawnEntry(EntityClassification.MONSTER).iterator(), structureFeature.getDefaultSpawnList().iterator()));
                }
            }
        }

        else if(group == EntityClassification.CREATURE){
            for(Structure<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.APPEND_WITH_NATURAL_MOBS)){
                if (!structureFeature.getDefaultCreatureSpawnList().isEmpty() && accessor.getStructureAt(pos, true, structureFeature).isValid()) {
                    return Lists.newArrayList(Iterators.concat(biome.getSpawnSettings().getSpawnEntry(EntityClassification.CREATURE).iterator(), structureFeature.getDefaultCreatureSpawnList().iterator()));
                }
            }
        }

        return null;
    }
}