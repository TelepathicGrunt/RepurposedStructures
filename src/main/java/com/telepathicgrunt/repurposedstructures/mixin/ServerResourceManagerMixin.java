package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.MobSpawnerManager;
import net.minecraft.command.Commands;
import net.minecraft.resources.DataPackRegistries;
import net.minecraft.resources.IReloadableResourceManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(DataPackRegistries.class)
public class ServerResourceManagerMixin {

    @Shadow @Final private IReloadableResourceManager resourceManager;

    @Inject(
            method = "<init>(Lnet/minecraft/command/Commands$EnvironmentType;I)V",
            at = @At(value = "TAIL")
    )
    private void registerDataManagers(Commands.EnvironmentType registrationEnvironment, int i, CallbackInfo ci) {
        RepurposedStructures.mobSpawnerManager = new MobSpawnerManager();
        this.resourceManager.addReloadListener(RepurposedStructures.mobSpawnerManager);
    }
}
