package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ChunkGenerator.class)
public class ChunkGeneratorMixin {

    @Inject(method = "generateStrongholdPositions",
            at = @At(value = "HEAD"),
            cancellable = true)
    private void removeVanillaStronghold(CallbackInfo ci) {
        if(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.turnOffVanillaStrongholds){
            ci.cancel();
        }
    }
}
