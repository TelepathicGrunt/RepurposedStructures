package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.processors.*;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RSProcessors {

    public static StructureProcessorType<AirProcessor> AIR_PROCESSORS = () -> AirProcessor.CODEC;
    public static StructureProcessorType<DataBlockProcessor> DATA_BLOCK_PROCESSORS = () -> DataBlockProcessor.CODEC;
    public static StructureProcessorType<WaterloggingFixProcessor> WATER_FIX_PROCESSORS = () -> WaterloggingFixProcessor.CODEC;
    public static StructureProcessorType<WallVinePostProcessor> WALL_VINE_POST_PROCESSORS = () -> WallVinePostProcessor.CODEC;
    public static StructureProcessorType<CeilingVinePostProcessor> CEILING_VINE_POST_PROCESSORS = () -> CeilingVinePostProcessor.CODEC;

    public static void registerProcessors() {
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "air_processor"), AIR_PROCESSORS);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "data_block_processor"), DATA_BLOCK_PROCESSORS);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "water_fix_processor"), WATER_FIX_PROCESSORS);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "wall_vine_post_processor"), WALL_VINE_POST_PROCESSORS);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "ceiling_vine_post_processor"), CEILING_VINE_POST_PROCESSORS);
    }
}
