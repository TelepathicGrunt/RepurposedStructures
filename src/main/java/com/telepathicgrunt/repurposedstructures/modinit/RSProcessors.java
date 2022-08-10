package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.processors.AirProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.CeilingVinePostProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.CloseOffAirSourcesProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.CloseOffFluidSourcesProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.EndGatewayProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.FillEndPortalFrameProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.FloodWithWaterProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.MineshaftSkyViewProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.NoiseReplaceWithPropertiesProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.PillarProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.RandomReplaceWithPropertiesProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.RemoveFloatingBlocksProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.ReplaceAirOnlyProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.ReplaceLiquidOnlyProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.SpawnerRandomizingProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.StructureVoidProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.TickBlocksProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.WallVinePostProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.WaterloggingFixProcessor;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public final class RSProcessors {
    private RSProcessors() {}

    public static StructureProcessorType<AirProcessor> AIR_PROCESSOR = () -> AirProcessor.CODEC;
    public static StructureProcessorType<EndGatewayProcessor> END_GATEWAY_PROCESSOR = () -> EndGatewayProcessor.CODEC;
    public static StructureProcessorType<TickBlocksProcessor> TICK_BLOCKS_PROCESSOR = () -> TickBlocksProcessor.CODEC;
    public static StructureProcessorType<MineshaftSkyViewProcessor> MINESHAFT_SKY_VIEW_PROCESSOR = () -> MineshaftSkyViewProcessor.CODEC;
    public static StructureProcessorType<PillarProcessor> PILLAR_PROCESSOR = () -> PillarProcessor.CODEC;
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
    public static StructureProcessorType<NoiseReplaceWithPropertiesProcessor> NOISE_REPLACE_WITH_PROPERTIES_PROCESSOR = () -> NoiseReplaceWithPropertiesProcessor.CODEC;
    public static StructureProcessorType<WaterloggingFixProcessor> WATERLOGGING_FIX_PROCESSOR = () -> WaterloggingFixProcessor.CODEC;

    public static StructureProcessorType<WallVinePostProcessor> WALL_VINE_POST_PROCESSOR = () -> WallVinePostProcessor.CODEC;
    public static StructureProcessorType<CeilingVinePostProcessor> CEILING_VINE_POST_PROCESSOR = () -> CeilingVinePostProcessor.CODEC;

    public static void registerProcessors() {
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "air_processor"), AIR_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "end_gateway_processor"), END_GATEWAY_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "tick_blocks_processor"), TICK_BLOCKS_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "mineshaft_sky_view_processor"), MINESHAFT_SKY_VIEW_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "pillar_processor"), PILLAR_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "structure_void_processor"), STRUCTURE_VOID_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "flood_with_water_processor"), FLOOD_WITH_WATER_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "replace_air_only_processor"), REPLACE_AIR_ONLY_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "replace_liquids_only_processor"), REPLACE_LIQUIDS_ONLY_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "spawner_randomizing_processor"), SPAWNER_RANDOMIZING_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "fill_end_portal_frame_processor"), FILL_END_PORTAL_FRAME_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "remove_floating_blocks_processor"), REMOVE_FLOATING_BLOCKS_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "close_off_fluid_sources_processor"), CLOSE_OFF_FLUID_SOURCES_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "close_off_air_sources_processor"), CLOSE_OFF_AIR_SOURCES_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "random_replace_with_properties_processor"), RANDOM_REPLACE_WITH_PROPERTIES_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "noise_replace_with_properties_processor"), NOISE_REPLACE_WITH_PROPERTIES_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "waterlogging_fix_processor"), WATERLOGGING_FIX_PROCESSOR);

        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "wall_vine_post_processor"), WALL_VINE_POST_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "ceiling_vine_post_processor"), CEILING_VINE_POST_PROCESSOR);
    }
}
