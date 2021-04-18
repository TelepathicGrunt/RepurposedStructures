package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.utils.StructureLocator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EnderEyeItem.class)
public class EnderEyeStrongholdLocatingMixin {

    @ModifyVariable(
            method = "onItemRightClick",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/gen/ChunkGenerator;locateStructure(Lnet/minecraft/world/server/ServerWorld;Lnet/minecraft/world/gen/feature/structure/Structure;Lnet/minecraft/util/math/BlockPos;IZ)Lnet/minecraft/util/math/BlockPos;")
    )
    private BlockPos locateRSStrongholds(BlockPos blockPos, World world, PlayerEntity user) {
        return StructureLocator.returnClosestStronghold(blockPos, (ServerWorld)world, user.getBlockPos());
    }
}