package com.telepathicgrunt.repurposedstructures.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.world.biome.provider.BiomeProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BiomeProvider.class)
public interface BiomeProviderInvoker {
    @Invoker("codec")
    Codec<? extends BiomeProvider> repurposedstructures_callCodec();
}
