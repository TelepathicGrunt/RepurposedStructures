package com.telepathicgrunt.repurposedstructures.mixin;

import com.google.common.collect.BiMap;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.Bootstrap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ChunkSerializer;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;


@Mixin(ChunkSerializer.class)
public class ChunkSerializerMixin {

    //Prevents worlds from being wrecked if I do decide to remove/change structure names.
    //However, logs will still be spammed to hell and back.
    @Inject(method = "readStructureReferences",
            at = @At(value = "TAIL"))
    private static void emitMissingStructureMessage(ChunkPos pos, CompoundTag tag, CallbackInfoReturnable<Map<StructureFeature<?>, LongSet>> cir) {
        cir.getReturnValue().remove(null);
    }
}
