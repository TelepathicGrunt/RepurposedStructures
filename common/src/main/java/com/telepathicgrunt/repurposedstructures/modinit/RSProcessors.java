package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.registry.RegistryEntry;
import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistries;
import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistry;
import com.telepathicgrunt.repurposedstructures.world.processors.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public final class RSProcessors {
    public static final ResourcefulRegistry<StructureProcessorType<?>> STRUCTURE_PROCESSOR = ResourcefulRegistries.create(BuiltInRegistries.STRUCTURE_PROCESSOR, RepurposedStructures.MODID);

    public static final RegistryEntry<StructureProcessorType<AirProcessor>, StructureProcessorType<AirProcessor>> AIR_PROCESSOR = STRUCTURE_PROCESSOR.register("air_processor", () -> () -> AirProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<BlockRemovalPostProcessor>, StructureProcessorType<BlockRemovalPostProcessor>> BLOCK_REMOVAL_POST_PROCESSOR = STRUCTURE_PROCESSOR.register("block_removal_post_processor", () -> () -> BlockRemovalPostProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<EndGatewayProcessor>, StructureProcessorType<EndGatewayProcessor>> END_GATEWAY_PROCESSOR = STRUCTURE_PROCESSOR.register("end_gateway_processor", () -> () -> EndGatewayProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<TickBlocksProcessor>, StructureProcessorType<TickBlocksProcessor>> TICK_BLOCKS_PROCESSOR = STRUCTURE_PROCESSOR.register("tick_blocks_processor", () -> () -> TickBlocksProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<MineshaftSkyViewProcessor>, StructureProcessorType<MineshaftSkyViewProcessor>> MINESHAFT_SKY_VIEW_PROCESSOR = STRUCTURE_PROCESSOR.register("mineshaft_sky_view_processor", () -> () -> MineshaftSkyViewProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<PillarProcessor>, StructureProcessorType<PillarProcessor>> PILLAR_PROCESSOR = STRUCTURE_PROCESSOR.register("pillar_processor", () -> () -> PillarProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<StructureVoidProcessor>, StructureProcessorType<StructureVoidProcessor>> STRUCTURE_VOID_PROCESSOR = STRUCTURE_PROCESSOR.register("structure_void_processor", () -> () -> StructureVoidProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<FloodWithWaterProcessor>, StructureProcessorType<FloodWithWaterProcessor>> FLOOD_WITH_WATER_PROCESSOR = STRUCTURE_PROCESSOR.register("flood_with_water_processor", () -> () -> FloodWithWaterProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<ReplaceAirOnlyProcessor>, StructureProcessorType<ReplaceAirOnlyProcessor>> REPLACE_AIR_ONLY_PROCESSOR = STRUCTURE_PROCESSOR.register("replace_air_only_processor", () -> () -> ReplaceAirOnlyProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<ReplaceLiquidOnlyProcessor>, StructureProcessorType<ReplaceLiquidOnlyProcessor>> REPLACE_LIQUIDS_ONLY_PROCESSOR = STRUCTURE_PROCESSOR.register("replace_liquids_only_processor", () -> () -> ReplaceLiquidOnlyProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<SpawnerRandomizingProcessor>, StructureProcessorType<SpawnerRandomizingProcessor>> SPAWNER_RANDOMIZING_PROCESSOR = STRUCTURE_PROCESSOR.register("spawner_randomizing_processor", () -> () -> SpawnerRandomizingProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<FillEndPortalFrameProcessor>, StructureProcessorType<FillEndPortalFrameProcessor>> FILL_END_PORTAL_FRAME_PROCESSOR = STRUCTURE_PROCESSOR.register("fill_end_portal_frame_processor", () -> () -> FillEndPortalFrameProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<RemoveFloatingBlocksProcessor>, StructureProcessorType<RemoveFloatingBlocksProcessor>> REMOVE_FLOATING_BLOCKS_PROCESSOR = STRUCTURE_PROCESSOR.register("remove_floating_blocks_processor", () -> () -> RemoveFloatingBlocksProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<CloseOffFluidSourcesProcessor>, StructureProcessorType<CloseOffFluidSourcesProcessor>> CLOSE_OFF_FLUID_SOURCES_PROCESSOR = STRUCTURE_PROCESSOR.register("close_off_fluid_sources_processor", () -> () -> CloseOffFluidSourcesProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<CloseOffAirSourcesProcessor>, StructureProcessorType<CloseOffAirSourcesProcessor>> CLOSE_OFF_AIR_SOURCES_PROCESSOR = STRUCTURE_PROCESSOR.register("close_off_air_sources_processor", () -> () -> CloseOffAirSourcesProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<RandomReplaceWithPropertiesProcessor>, StructureProcessorType<RandomReplaceWithPropertiesProcessor>> RANDOM_REPLACE_WITH_PROPERTIES_PROCESSOR = STRUCTURE_PROCESSOR.register("random_replace_with_properties_processor", () -> () -> RandomReplaceWithPropertiesProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<NoiseReplaceWithPropertiesProcessor>, StructureProcessorType<NoiseReplaceWithPropertiesProcessor>> NOISE_REPLACE_WITH_PROPERTIES_PROCESSOR = STRUCTURE_PROCESSOR.register("noise_replace_with_properties_processor", () -> () -> NoiseReplaceWithPropertiesProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<WaterloggingFixProcessor>, StructureProcessorType<WaterloggingFixProcessor>> WATERLOGGING_FIX_PROCESSOR = STRUCTURE_PROCESSOR.register("waterlogging_fix_processor", () -> () -> WaterloggingFixProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<SuperGravityProcessor>, StructureProcessorType<SuperGravityProcessor>> SUPER_GRAVITY_PROCESSOR = STRUCTURE_PROCESSOR.register("super_gravity_processor", () -> () -> SuperGravityProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<ForcePlaceMushroomBlocksProcessor>, StructureProcessorType<ForcePlaceMushroomBlocksProcessor>> FORCE_PLACE_MUSHROOM_BLOCKS_PROCESSOR = STRUCTURE_PROCESSOR.register("force_place_mushroom_blocks_processor", () -> () -> ForcePlaceMushroomBlocksProcessor.CODEC);

    public static final RegistryEntry<StructureProcessorType<CappedStructureSurfaceProcessor>, StructureProcessorType<CappedStructureSurfaceProcessor>> CAPPED_STRUCTURE_SURFACE_PROCESSOR = STRUCTURE_PROCESSOR.register("capped_structure_surface_processor", () -> () -> CappedStructureSurfaceProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<PostProcessListProcessor>, StructureProcessorType<PostProcessListProcessor>> POST_PROCESS_LIST_PROCESSOR = STRUCTURE_PROCESSOR.register("post_process_list_processor", () -> () -> PostProcessListProcessor.CODEC);

    public static final RegistryEntry<StructureProcessorType<WallVinePostProcessor>, StructureProcessorType<WallVinePostProcessor>> WALL_VINE_POST_PROCESSOR = STRUCTURE_PROCESSOR.register("wall_vine_post_processor", () -> () -> WallVinePostProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<CeilingVinePostProcessor>, StructureProcessorType<CeilingVinePostProcessor>> CEILING_VINE_POST_PROCESSOR = STRUCTURE_PROCESSOR.register("ceiling_vine_post_processor", () -> () -> CeilingVinePostProcessor.CODEC);
}
