package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.processors.AirProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.CeilingVinePostProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.CloseOffAirSourcesProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.CloseOffFluidSourcesProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.DataBlockProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.FillEndPortalFrameProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.FloodWithWaterProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.RandomReplaceWithPropertiesProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.RemoveFloatingBlocksProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.ReplaceAirOnlyProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.ReplaceLiquidOnlyProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.MineshaftSkyViewProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.SpawnerRandomizingProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.StructureVoidProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.WallVinePostProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.WaterloggingFixProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RSProcessors {

    public static StructureProcessorType<AirProcessor> AIR_PROCESSOR = () -> AirProcessor.CODEC;
    public static StructureProcessorType<MineshaftSkyViewProcessor> MINESHAFT_SKY_VIEW_PROCESSOR = () -> MineshaftSkyViewProcessor.CODEC;
    public static StructureProcessorType<DataBlockProcessor> DATA_BLOCK_PROCESSOR = () -> DataBlockProcessor.CODEC;
    public static StructureProcessorType<StructureVoidProcessor> STRUCTURE_VOID_PROCESSOR = () -> StructureVoidProcessor.CODEC;
    public static StructureProcessorType<FloodWithWaterProcessor> FLOOD_WITH_WATER_PROCESSOR = () -> FloodWithWaterProcessor.CODEC;
    public static StructureProcessorType<ReplaceAirOnlyProcessor> REPLACE_AIR_ONLY_PROCESSOR = () -> ReplaceAirOnlyProcessor.CODEC;
    public static StructureProcessorType<ReplaceLiquidOnlyProcessor> REPLACE_LIQUIDS_ONLY_PROCESSOR = () -> ReplaceLiquidOnlyProcessor.CODEC;
    public static StructureProcessorType<SpawnerRandomizingProcessor> SPAWNER_RANDOMIZING_PROCESSOR = () -> SpawnerRandomizingProcessor.CODEC;
    public static StructureProcessorType<FillEndPortalFrameProcessor> FILL_END_PORTAL_FRAME_PROCESSOR = () -> FillEndPortalFrameProcessor.CODEC;
    public static StructureProcessorType<RemoveFloatingBlocksProcessor> REMOVE_FLOATING_BLOCKS_PROCESSOR = () -> RemoveFloatingBlocksProcessor.CODEC;
    public static StructureProcessorType<CloseOffFluidSourcesProcessor> CLOSE_OFF_FLUID_SOURCES_PROCESSOR = () -> CloseOffFluidSourcesProcessor.CODEC;
    public static StructureProcessorType<CloseOffAirSourcesProcessor> CLOSE_OFF_AIR_SOURCES_PROCESSOR = () -> CloseOffAirSourcesProcessor.CODEC;
    public static StructureProcessorType<RandomReplaceWithPropertiesProcessor> RANDOM_REPLACE_WITH_PROPERTIES_PROCESSOR = () -> RandomReplaceWithPropertiesProcessor.CODEC;
    public static StructureProcessorType<WaterloggingFixProcessor> WATERLOGGING_FIX_PROCESSOR = () -> WaterloggingFixProcessor.CODEC;

    public static StructureProcessorType<WallVinePostProcessor> WALL_VINE_POST_PROCESSOR = () -> WallVinePostProcessor.CODEC;
    public static StructureProcessorType<CeilingVinePostProcessor> CEILING_VINE_POST_PROCESSOR = () -> CeilingVinePostProcessor.CODEC;

    public static void registerProcessors() {
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "air_processor"), AIR_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "mineshaft_sky_view_processor"), MINESHAFT_SKY_VIEW_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "data_block_processor"), DATA_BLOCK_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "structure_void_processor"), STRUCTURE_VOID_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "flood_with_water_processor"), FLOOD_WITH_WATER_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "replace_air_only_processor"), REPLACE_AIR_ONLY_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "replace_liquids_only_processor"), REPLACE_LIQUIDS_ONLY_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "spawner_randomizing_processor"), SPAWNER_RANDOMIZING_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "fill_end_portal_frame_processor"), FILL_END_PORTAL_FRAME_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "remove_floating_blocks_processor"), REMOVE_FLOATING_BLOCKS_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "close_off_fluid_sources_processor"), CLOSE_OFF_FLUID_SOURCES_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "close_off_air_sources_processor"), CLOSE_OFF_AIR_SOURCES_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "random_replace_with_properties_processor"), RANDOM_REPLACE_WITH_PROPERTIES_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "waterlogging_fix_processor"), WATERLOGGING_FIX_PROCESSOR);

        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "wall_vine_post_processor"), WALL_VINE_POST_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(RepurposedStructures.MODID, "ceiling_vine_post_processor"), CEILING_VINE_POST_PROCESSOR);
    }
}
