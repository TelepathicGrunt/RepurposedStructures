package com.telepathicgrunt.repurposedstructures.mixin.world;

import com.google.common.collect.Maps;
import com.telepathicgrunt.repurposedstructures.misc.NoiseSettingsDeepCopier;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StrongholdConfig;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.Optional;

@Mixin(StructuresConfig.class)
public class StructuresConfigMixin {

    @Mutable
    @Final
    @Shadow
    private Map<StructureFeature<?>, StructureConfig> structures;

    @Inject(method = "<init>(Ljava/util/Optional;Ljava/util/Map;)V",
            at = @At(value = "RETURN"))
    private void repurposedstructures_deepCopyStructuresConfig(Optional<StrongholdConfig> stronghold, Map<StructureFeature<?>, StructureConfig> structures, CallbackInfo ci) {
        structures = Maps.newHashMap(structures);
    }
}