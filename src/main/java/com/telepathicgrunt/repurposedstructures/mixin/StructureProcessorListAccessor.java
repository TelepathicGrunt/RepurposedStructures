package com.telepathicgrunt.repurposedstructures.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.gen.feature.template.ProcessorLists;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.StructureProcessorList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ProcessorLists.class)
public interface StructureProcessorListAccessor {

    @Invoker("register")
    static StructureProcessorList rs_invokeRegister(String id, ImmutableList<StructureProcessor> processorList) {
        throw new UnsupportedOperationException();
    }
}