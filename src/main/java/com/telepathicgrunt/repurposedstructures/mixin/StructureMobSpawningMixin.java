package com.telepathicgrunt.repurposedstructures.mixin;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
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
        if (group == SpawnGroup.MONSTER) {

            for(StructureFeature<?> outpost : RSStructures.NETHER_OUTPOSTS_LIST){
                if (accessor.getStructureAt(pos, true, outpost).hasChildren()) {
                    return outpost.getMonsterSpawns();
                }
            }

            for(StructureFeature<?> shipwreck : RSStructures.NETHER_SHIPWRECKS_LIST){
                if (accessor.getStructureAt(pos, true, shipwreck).hasChildren()) {
                    return Lists.newArrayList(Iterators.concat(biome.getSpawnSettings().getSpawnEntry(SpawnGroup.MONSTER).iterator(), shipwreck.getMonsterSpawns().iterator()));
                }
            }

            if (accessor.getStructureAt(pos, true, RSStructures.NETHER_STRONGHOLD).hasChildren()) {
                return RSStructures.NETHER_STRONGHOLD.getMonsterSpawns();
            }

            if (accessor.getStructureAt(pos, true, RSStructures.JUNGLE_FORTRESS).hasChildren()) {
                return Lists.newArrayList(Iterators.concat(biome.getSpawnSettings().getSpawnEntry(SpawnGroup.MONSTER).iterator(), RSStructures.JUNGLE_FORTRESS.getMonsterSpawns().iterator()));
            }

            if (accessor.getStructureAt(pos, true, RSStructures.END_MINESHAFT).hasChildren()) {
                return Lists.newArrayList(Iterators.concat(biome.getSpawnSettings().getSpawnEntry(SpawnGroup.MONSTER).iterator(), RSStructures.END_MINESHAFT.getMonsterSpawns().iterator()));
            }
        }

        return null;
    }
}