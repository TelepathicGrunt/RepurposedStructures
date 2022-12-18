package com.telepathicgrunt.repurposedstructures.mixin.resources;

import com.telepathicgrunt.repurposedstructures.misc.structurepiececounter.JSONConditionsRegistry;
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
        JSONConditionsRegistry.createJSONConditionsRegistry();
    }
}
