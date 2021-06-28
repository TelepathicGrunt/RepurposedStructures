package com.telepathicgrunt.repurposedstructures.mixin;

import com.google.common.collect.Maps;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.gen.settings.StructureSpreadSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.Optional;

@Mixin(DimensionStructuresSettings.class)
public class DimensionStructuresSettingsMixin {

    @Inject(method = "<init>(Ljava/util/Optional;Ljava/util/Map;)V",
            at = @At(value = "RETURN"))
    private void repurposedstructures_deepCopyStructuresConfig(Optional<StructureSpreadSettings> stronghold, Map<Structure<?>, StructureSeparationSettings> structures, CallbackInfo ci) {
        ((DimensionStructuresSettings)(Object)this).structureConfig = Maps.newHashMap(structures);
    }
}