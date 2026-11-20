
package com.telepathicgrunt.repurposedstructures.mixins.level;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.telepathicgrunt.repurposedstructures.configs.RSMainConfig;
import com.telepathicgrunt.repurposedstructures.services.PlatformService;
import net.minecraft.server.level.WorldGenRegion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = WorldGenRegion.class, priority = 1200)
public class WorldGenRegionMixin {

    // Silence logspam that is only solveable by Mojang by skipping the logging check in production:
    // https://report.bugs.mojang.com/servicedesk/customer/portal/2/MC-308792
    @WrapOperation(method = "getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/WorldGenRegion;warnIfReadOutsideWriteZone(II)V"),
            require = 0)
    private void repurposedstructures_skipWriteBoundCheckLogging1(WorldGenRegion instance, int chunkX, int chunkZ, Operation<Void> original) {
        if (RSMainConfig.disableLogspamHiding || PlatformService.INSTANCE.isDevEnvironment()) {
            original.call(instance, chunkX, chunkZ);
        }
    }

    @WrapOperation(method = "getFluidState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/material/FluidState;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/WorldGenRegion;warnIfReadOutsideWriteZone(II)V"),
            require = 0)
    private void repurposedstructures_skipWriteBoundCheckLogging2(WorldGenRegion instance, int chunkX, int chunkZ, Operation<Void> original) {
        if (RSMainConfig.disableLogspamHiding || PlatformService.INSTANCE.isDevEnvironment()) {
            original.call(instance, chunkX, chunkZ);
        }
    }
}