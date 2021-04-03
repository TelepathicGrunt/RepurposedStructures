package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.processors.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;

public class RSProcessors {

    public static IStructureProcessorType<AirProcessor> AIR_PROCESSOR = () -> AirProcessor.CODEC;
    public static IStructureProcessorType<DataBlockProcessor> DATA_BLOCK_PROCESSOR = () -> DataBlockProcessor.CODEC;
    public static IStructureProcessorType<WaterloggingFixProcessor> WATER_FIX_PROCESSOR = () -> WaterloggingFixProcessor.CODEC;
    public static IStructureProcessorType<ReplaceAirOnlyProcessor> REPLACE_AIR_ONLY_PROCESSOR = () -> ReplaceAirOnlyProcessor.CODEC;
    public static IStructureProcessorType<ReplaceLiquidOnlyProcessor> REPLACE_LIQUIDS_ONLY_PROCESSOR = () -> ReplaceLiquidOnlyProcessor.CODEC;
    public static IStructureProcessorType<SpawnerRandomizingProcessor> SPAWNER_RANDOMIZING_PROCESSOR = () -> SpawnerRandomizingProcessor.CODEC;
    public static IStructureProcessorType<RemoveFloatingBlocksProcessor> REMOVE_FLOATING_BLOCKS_PROCESSOR = () -> RemoveFloatingBlocksProcessor.CODEC;

    public static IStructureProcessorType<WallVinePostProcessor> WALL_VINE_POST_PROCESSOR = () -> WallVinePostProcessor.CODEC;
    public static IStructureProcessorType<CeilingVinePostProcessor> CEILING_VINE_POST_PROCESSOR = () -> CeilingVinePostProcessor.CODEC;

    public static void registerProcessors() {
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "air_processor"), AIR_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "data_block_processor"), DATA_BLOCK_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "water_fix_processor"), WATER_FIX_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "replace_air_only_processor"), REPLACE_AIR_ONLY_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "replace_liquids_only_processor"), REPLACE_LIQUIDS_ONLY_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "spawner_randomizing_processor"), SPAWNER_RANDOMIZING_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "remove_floating_blocks_processor"), REMOVE_FLOATING_BLOCKS_PROCESSOR);

        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "wall_vine_post_processor"), WALL_VINE_POST_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "ceiling_vine_post_processor"), CEILING_VINE_POST_PROCESSOR);
    }
}
