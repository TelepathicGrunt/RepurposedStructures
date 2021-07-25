package com.telepathicgrunt.repurposedstructures.mixin.structures;

import com.telepathicgrunt.repurposedstructures.utils.StructureLocator;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EnderEyeItem.class)
public class EnderEyeStrongholdLocatingMixin {

    @ModifyVariable(
            method = "use",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/level/chunk/ChunkGenerator;findNearestMapFeature(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/levelgen/feature/StructureFeature;Lnet/minecraft/core/BlockPos;IZ)Lnet/minecraft/core/BlockPos;")
    )
    private BlockPos repurposedstructures_locateRSStrongholds(BlockPos blockPos, Level world, Player user) {
        return StructureLocator.returnClosestStronghold(blockPos, (ServerLevel)world, user.blockPosition());
    }
}