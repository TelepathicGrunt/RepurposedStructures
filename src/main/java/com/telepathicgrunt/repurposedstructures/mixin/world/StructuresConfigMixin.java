package com.telepathicgrunt.repurposedstructures.mixin.world;

import com.google.common.collect.Maps;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.Optional;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StrongholdConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;

@Mixin(StructureSettings.class)
public class StructuresConfigMixin {

    @Mutable
    @Final
    @Shadow
    private Map<StructureFeature<?>, StructureFeatureConfiguration> structureConfig;

    @Inject(method = "<init>(Ljava/util/Optional;Ljava/util/Map;)V",
            at = @At(value = "RETURN"))
    private void repurposedstructures_deepCopyStructuresConfig(Optional<StrongholdConfiguration> stronghold, Map<StructureFeature<?>, StructureFeatureConfiguration> structures, CallbackInfo ci) {
        structureConfig = Maps.newHashMap(structures);
    }
}