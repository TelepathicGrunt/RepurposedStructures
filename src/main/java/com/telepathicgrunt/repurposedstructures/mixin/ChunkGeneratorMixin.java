package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.misc.NoiseSettingsDeepCopier;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
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
    private DimensionStructuresSettings settings;

    @Inject(method = "<init>(Lnet/minecraft/world/biome/provider/BiomeProvider;Lnet/minecraft/world/biome/provider/BiomeProvider;Lnet/minecraft/world/gen/settings/DimensionStructuresSettings;J)V",
            at = @At(value = "RETURN"))
    private void repurposedstructures_deepCopyNoiseSettings(BiomeProvider biomeProvider1, BiomeProvider biomeProvider2, DimensionStructuresSettings dimensionStructuresSettings, long seed, CallbackInfo ci) {
        settings = NoiseSettingsDeepCopier.deepCopyDimensionStructuresSettings(settings);
    }
}