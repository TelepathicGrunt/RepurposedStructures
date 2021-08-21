package com.telepathicgrunt.repurposedstructures.mixin;

import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.utils.StructureLocator;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.DebugBuffer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.PalettedContainer;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PalettedContainer.class)
public class PalettedContainerMixin {

//    @Shadow
//    @Final
//    @Mutable
//    private DebugBuffer<Pair<Thread, StackTraceElement[]>> traces;
//
//    @Inject(
//            method = "<init>(Lnet/minecraft/world/level/chunk/Palette;Lnet/minecraft/core/IdMapper;Ljava/util/function/Function;Ljava/util/function/Function;Ljava/lang/Object;)V",
//            at = @At(value = "RETURN")
//    )
//    private void rsdebug(CallbackInfo ci) {
//        traces = new DebugBuffer(3);
//    }
}