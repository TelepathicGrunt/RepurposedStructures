package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.processors.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;

public class RSProcessors {

    public static IStructureProcessorType<AirProcessor> AIR_PROCESSORS = () -> AirProcessor.CODEC;
    public static IStructureProcessorType<DataBlockProcessor> DATA_BLOCK_PROCESSORS = () -> DataBlockProcessor.CODEC;
    public static IStructureProcessorType<WaterloggingFixProcessor> WATER_FIX_PROCESSORS = () -> WaterloggingFixProcessor.CODEC;
    public static IStructureProcessorType<WallVinePostProcessor> WALL_VINE_POST_PROCESSORS = () -> WallVinePostProcessor.CODEC;
    public static IStructureProcessorType<CeilingVinePostProcessor> CEILING_VINE_POST_PROCESSORS = () -> CeilingVinePostProcessor.CODEC;

    public static void registerProcessors() {
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "air_processor"), AIR_PROCESSORS);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "data_block_processor"), DATA_BLOCK_PROCESSORS);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "water_fix_processor"), WATER_FIX_PROCESSORS);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "wall_vine_post_processor"), WALL_VINE_POST_PROCESSORS);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "ceiling_vine_post_processor"), CEILING_VINE_POST_PROCESSORS);
    }
}
