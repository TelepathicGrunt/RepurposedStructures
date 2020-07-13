package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.PositionTracker;
import net.minecraft.nbt.Tag;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@Mixin(CompoundTag.class)
public class Debug3Mixin {

    @Shadow  @Final
    private Map<String, Tag> tags;

    /**
     * @author TelepathicGrunt - Repurposed Structures
     *
     * @reason MOJANG! YOU NEED TO CHECK FOR NULL!!!!!
     */
    @Overwrite
    public void write(DataOutput output) throws IOException {
        Iterator var2 = this.tags.keySet().iterator();

        while(var2.hasNext()) {
            String string = (String)var2.next();
            if(string == null) continue;

            Tag tag = this.tags.get(string);
            if(tag == null) continue;

            DebugInvoker.invokeWrite(string, tag, output);
        }

        output.writeByte(0);
    }
}