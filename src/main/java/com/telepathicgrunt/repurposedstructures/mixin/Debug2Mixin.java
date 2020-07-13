package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.PositionTracker;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.TagReader;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;

@Mixin(ChunkGenerator.class)
public class Debug2Mixin {

    @Inject(method = "Lnet/minecraft/world/gen/chunk/ChunkGenerator;method_28508(Lnet/minecraft/world/gen/feature/ConfiguredStructureFeature;Lnet/minecraft/world/gen/StructureAccessor;Lnet/minecraft/world/chunk/Chunk;Lnet/minecraft/structure/StructureManager;JLnet/minecraft/util/math/ChunkPos;Lnet/minecraft/world/biome/Biome;)V",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void read(ConfiguredStructureFeature<?, ?> configuredStructureFeature, StructureAccessor structureAccessor, Chunk chunk, StructureManager structureManager, long l, ChunkPos chunkPos, Biome biome, CallbackInfo ci) {
        if(!Registry.STRUCTURE_FEATURE.getKey(configuredStructureFeature.field_24835).isPresent()){
            // RepurposedStructures.LOGGER.log(Level.FATAL, "canceled");
            ci.cancel();
        }
    }
}