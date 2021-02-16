package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.processors.AirProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.DataBlockProcessor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;

public class RSProcessors {

    public static IStructureProcessorType<DataBlockProcessor> DATA_BLOCK_PROCESSORS = () -> DataBlockProcessor.CODEC;
    public static IStructureProcessorType<AirProcessor> AIR_PROCESSORS = () -> AirProcessor.CODEC;

    public static void registerProcessors() {
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "data_block_processors"), DATA_BLOCK_PROCESSORS);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "air_processors"), AIR_PROCESSORS);
    }
}
