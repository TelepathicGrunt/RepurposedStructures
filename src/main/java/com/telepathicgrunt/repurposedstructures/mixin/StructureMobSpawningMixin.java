package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.misc.StructureMobSpawning;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.NoiseChunkGenerator;
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
            method = "getMobsAt(Lnet/minecraft/world/biome/Biome;Lnet/minecraft/world/gen/feature/structure/StructureManager;Lnet/minecraft/entity/EntityClassification;Lnet/minecraft/util/math/BlockPos;)Ljava/util/List;",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void repurposedstructures_structureMobs(Biome biome, StructureManager accessor, EntityClassification group, BlockPos pos, CallbackInfoReturnable<List<MobSpawnInfo.Spawners>> cir) {
        List<MobSpawnInfo.Spawners> list = StructureMobSpawning.getStructureSpawns(biome, accessor, group, pos);
        if(list != null) cir.setReturnValue(list);
    }
}