package com.telepathicgrunt.repurposedstructures.mixin.fabricbase.resources;

import com.telepathicgrunt.repurposedstructures.modinit.RSConditionsRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BuiltInRegistries.class)
public class BuiltInRegistriesMixin {

    /**
     * Creates and inits our custom registry at game startup
     * @author TelepathicGrunt
     */
    @Inject(method = "<clinit>",
            at = @At(value = "RETURN"))
    private static void repurposedstructures_initCustomRegistries(CallbackInfo ci) {
        RSConditionsRegistry.RS_JSON_CONDITIONS_REGISTRY.init();
    }
}
