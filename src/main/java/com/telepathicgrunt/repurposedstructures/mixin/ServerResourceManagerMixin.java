package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.resource.ReloadableResourceManager;
import net.minecraft.resource.ServerResourceManager;
import net.minecraft.server.command.CommandManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ServerResourceManager.class)
public class ServerResourceManagerMixin {

    @Shadow @Final private ReloadableResourceManager resourceManager;

    @Inject(
            method = "<init>(Lnet/minecraft/server/command/CommandManager$RegistrationEnvironment;I)V",
            at = @At(value = "TAIL")
    )
    private void registerDataManagers(CommandManager.RegistrationEnvironment registrationEnvironment, int i, CallbackInfo ci) {
        //loads the RS specific json files for mob spawner chances
        this.resourceManager.registerListener(RepurposedStructures.mobSpawnerManager);
    }
}
