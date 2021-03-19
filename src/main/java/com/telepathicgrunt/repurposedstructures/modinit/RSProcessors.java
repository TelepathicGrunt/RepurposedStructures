package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.processors.AirProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.DataBlockProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.WaterloggingFixProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RSProcessors {

    public static StructureProcessorType<DataBlockProcessor> DATA_BLOCK_PROCESSORS = () -> DataBlockProcessor.CODEC;
    public static StructureProcessorType<AirProcessor> AIR_PROCESSORS = () -> AirProcessor.CODEC;
    public static StructureProcessorType<WaterloggingFixProcessor> WATER_FIX_PROCESSORS = () -> WaterloggingFixProcessor.CODEC;

    public static void registerProcessors() {
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "data_block_processors"), DATA_BLOCK_PROCESSORS);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "air_processors"), AIR_PROCESSORS);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "water_fix_processors"), WATER_FIX_PROCESSORS);
    }
}
