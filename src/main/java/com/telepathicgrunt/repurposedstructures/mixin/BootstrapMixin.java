package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.Bootstrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Bootstrap.class)
public class BootstrapMixin {

    @Inject(
            method = "initialize",
            at = @At(value = "TAIL")
    )
    private static void earlyRegister(CallbackInfo ci) {
        RepurposedStructures.initialize();
    }
}
