package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.RSFeatures;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EnderEyeItem.class)
public class EnderEyeStrongholdLocatingMixin {

    @ModifyVariable(
            method = "use",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/gen/chunk/ChunkGenerator;locateStructure(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/world/gen/feature/StructureFeature;Lnet/minecraft/util/math/BlockPos;IZ)Lnet/minecraft/util/math/BlockPos;")
    )
    private BlockPos locateRSStrongholds(BlockPos blockPos, World world, PlayerEntity user) {
        if(blockPos == null)
            blockPos = ((ServerWorld)world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld)world, RSFeatures.STONEBRICK_STRONGHOLD, user.getBlockPos(), 100, false);
        if(blockPos == null)
            blockPos = ((ServerWorld)world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld)world, RSFeatures.NETHER_STRONGHOLD, user.getBlockPos(), 100, false);
        return blockPos;
    }
}