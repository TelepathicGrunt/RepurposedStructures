package com.telepathicgrunt.repurposedstructures.mixin;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.features.RSFeatures;
import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.nbt.*;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ChunkSerializer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.DataInput;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Mixin(ChunkSerializer.class)
public class DebugMixin {

    /**
     * @author TelepathicGrunt - Repurposed Structures
     *
     * @reason MOJANG! YOU NEED TO CHECK FOR NULL!!!!!
     */
    @Overwrite
    private static CompoundTag writeStructures(ChunkPos pos, Map<StructureFeature<?>, StructureStart<?>> structureStarts, Map<StructureFeature<?>, LongSet> structureReferences) {
        CompoundTag compoundTag = new CompoundTag();
        CompoundTag compoundTag2 = new CompoundTag();
        Iterator var5 = structureStarts.entrySet().iterator();

        while(var5.hasNext()) {
            Map.Entry<StructureFeature<?>, StructureStart<?>> entry = (Map.Entry)var5.next();
            if(entry == null || entry.getKey() == null) continue;
            compoundTag2.put(entry.getKey().getName(), entry.getValue().toTag(pos.x, pos.z));
        }

        compoundTag.put("Starts", compoundTag2);
        CompoundTag compoundTag3 = new CompoundTag();
        Iterator var9 = structureReferences.entrySet().iterator();

        while(var9.hasNext()) {
            Map.Entry<StructureFeature<?>, LongSet> entry2 = (Map.Entry)var9.next();
            if(entry2 == null || entry2.getKey() == null) continue;
            compoundTag3.put(entry2.getKey().getName(), new LongArrayTag(entry2.getValue()));
        }

        compoundTag.put("References", compoundTag3);
        return compoundTag;
    }

    /**
     * @author TelepathicGrunt - Repurposed Structures
     *
     * @reason STOP SPAMMING THE F***ING LOGS
     */
    @Overwrite
    private static Map<StructureFeature<?>, StructureStart<?>> readStructureStarts(StructureManager structureManager, CompoundTag compoundTag, long l) {
        Map<StructureFeature<?>, StructureStart<?>> map = Maps.newHashMap();
        CompoundTag compoundTag2 = compoundTag.getCompound("Starts");
        Iterator var6 = compoundTag2.getKeys().iterator();

        while(var6.hasNext()) {
            String string = (String)var6.next();
            String string2 = string.toLowerCase(Locale.ROOT);
            StructureFeature<?> structureFeature = (StructureFeature)StructureFeature.STRUCTURES.get(string2);
            if (structureFeature != null){
                StructureStart<?> structureStart = StructureFeature.method_28660(structureManager, compoundTag2.getCompound(string), l);
                if (structureStart != null) {
                    map.put(structureFeature, structureStart);
                }
            }
        }

        return map;
    }
}