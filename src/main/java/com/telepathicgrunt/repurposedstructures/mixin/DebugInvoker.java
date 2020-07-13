package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.io.DataOutput;
import java.util.Map;

@Mixin(CompoundTag.class)
public interface DebugInvoker {

    @Invoker("write")
    static void invokeWrite(String key, Tag tag, DataOutput output) {
    }
}
