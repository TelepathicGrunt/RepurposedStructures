package com.telepathicgrunt.repurposedstructures.mixin.world;

import com.telepathicgrunt.repurposedstructures.misc.NoiseSettingsDeepCopier;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.StructureSettings;
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
    private StructureSettings settings;

    @Inject(method = "<init>(Lnet/minecraft/world/level/biome/BiomeSource;Lnet/minecraft/world/level/biome/BiomeSource;Lnet/minecraft/world/level/levelgen/StructureSettings;J)V",
            at = @At(value = "RETURN"))
    private void repurposedstructures_deepCopyNoiseSettings(BiomeSource populationSource, BiomeSource biomeSource, StructureSettings structuresConfig, long worldSeed, CallbackInfo ci) {
        settings = NoiseSettingsDeepCopier.deepCopyDimensionStructuresSettings(structuresConfig);
    }
}