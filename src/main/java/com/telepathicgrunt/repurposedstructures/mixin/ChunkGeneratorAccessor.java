package com.telepathicgrunt.repurposedstructures.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ChunkGenerator.class)
public interface ChunkGeneratorAccessor {

    @Accessor("settings")
    void repurposedstructures_setSettings(DimensionStructuresSettings dimensionStructuresSettings);

    @Invoker("codec")
    Codec<ChunkGenerator> repurposedstructures_getCodec();
}
