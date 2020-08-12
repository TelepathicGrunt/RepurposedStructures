package com.telepathicgrunt.repurposedstructures.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorLists;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Set;

@Mixin(StructureProcessorLists.class)
public interface StructureProcessorListAccessor {

    @Invoker("register")
    static StructureProcessorList getRegister(String id, ImmutableList<StructureProcessor> processorList) {
        throw new UnsupportedOperationException();
    }
}