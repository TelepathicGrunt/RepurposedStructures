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
        return returnClosestStronghold(blockPos, world, user);
    }

    private static BlockPos returnClosestStronghold(BlockPos blockPos, World world, PlayerEntity user){
        BlockPos closestPos = returnCloserPos(blockPos, ((ServerWorld)world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld)world, RSFeatures.STONEBRICK_STRONGHOLD, user.getBlockPos(), 100, false), user.getBlockPos());
        closestPos = returnCloserPos(closestPos, ((ServerWorld)world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld)world, RSFeatures.NETHER_STRONGHOLD, user.getBlockPos(), 100, false), user.getBlockPos());
        return closestPos;
    }

    private static BlockPos returnCloserPos(BlockPos blockPos1, BlockPos blockPos2, BlockPos centerPos){
        if(blockPos1 == null && blockPos2 != null){
            return blockPos2;
        }
        else if(blockPos1 != null && blockPos2 == null){
            return blockPos1;
        }
        else if(blockPos1 != null && blockPos2 != null &&
                Math.pow(blockPos1.getX() - centerPos.getX(), 2) + Math.pow(blockPos1.getZ() - centerPos.getZ(), 2) >
                        Math.pow(blockPos2.getX() - centerPos.getX(), 2) + Math.pow(blockPos2.getZ() - centerPos.getZ(), 2)){
            return blockPos2;
        }

        return null;
    }
}