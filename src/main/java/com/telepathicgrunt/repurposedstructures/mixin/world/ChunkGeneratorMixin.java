package com.telepathicgrunt.repurposedstructures.mixin.world;

import com.telepathicgrunt.repurposedstructures.misc.NoiseSettingsDeepCopier;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructuresConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkGenerator.class)
public class ChunkGeneratorMixin {

    @Mutable
    @Final
    @Shadow
    private StructuresConfig structuresConfig;

    @Inject(method = "<init>(Lnet/minecraft/world/biome/source/BiomeSource;Lnet/minecraft/world/biome/source/BiomeSource;Lnet/minecraft/world/gen/chunk/StructuresConfig;J)V",
            at = @At(value = "RETURN"))
    private void repurposedstructures_deepCopyNoiseSettings(BiomeSource populationSource, BiomeSource biomeSource, StructuresConfig structuresConfig, long worldSeed, CallbackInfo ci) {
        structuresConfig = NoiseSettingsDeepCopier.deepCopyDimensionStructuresSettings(structuresConfig);
    }
}