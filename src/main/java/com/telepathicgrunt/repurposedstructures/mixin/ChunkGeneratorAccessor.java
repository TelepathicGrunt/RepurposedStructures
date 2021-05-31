package com.telepathicgrunt.repurposedstructures.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ChunkGenerator.class)
public interface ChunkGeneratorAccessor {
    @Invoker("codec")
    Codec<ChunkGenerator> repurposedstructures_getCodec();
}
