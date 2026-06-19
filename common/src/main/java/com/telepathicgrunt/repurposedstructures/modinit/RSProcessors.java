package com.telepathicgrunt.repurposedstructures.modinit;

import com.mojang.serialization.MapCodec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.registry.RegistryEntry;
import com.telepathicgrunt.repurposedstructures.services.ResourcefulRegistriesService;
import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistry;
import com.telepathicgrunt.repurposedstructures.world.processors.AirProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.BlockRemovalPostProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.BottomPillarProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.CappedStructureSurfaceProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.CeilingVinePostProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.CloseOffAirSourcesProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.CloseOffFluidSourcesProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.CoralAliveDeadProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.EndGatewayProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.FillEndPortalFrameProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.FloodWithWaterProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.ForcePlaceMushroomBlocksProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.MineshaftSkyViewProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.NoiseReplaceWithPropertiesProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.PillarProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.PostProcessListProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.RandomReplaceWithPropertiesProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.RemoveFloatingBlocksProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.ReplaceAirOnlyProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.ReplaceLiquidOnlyProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.SpawnerRandomizingProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.StructureVoidProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.SuperGravityProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.TickBlocksProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.WallVinePostProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.WaterlogWhenReplacingWaterProcessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;

public final class RSProcessors {
    public static final ResourcefulRegistry<MapCodec<? extends StructureProcessor>> STRUCTURE_PROCESSOR = ResourcefulRegistriesService.INSTANCE.create(BuiltInRegistries.STRUCTURE_PROCESSOR, RepurposedStructures.MODID);

    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> AIR_PROCESSOR = STRUCTURE_PROCESSOR.register("air_processor", () -> AirProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> BLOCK_REMOVAL_POST_PROCESSOR = STRUCTURE_PROCESSOR.register("block_removal_post_processor", () -> BlockRemovalPostProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> END_GATEWAY_PROCESSOR = STRUCTURE_PROCESSOR.register("end_gateway_processor", () -> EndGatewayProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> TICK_BLOCKS_PROCESSOR = STRUCTURE_PROCESSOR.register("tick_blocks_processor", () -> TickBlocksProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> MINESHAFT_SKY_VIEW_PROCESSOR = STRUCTURE_PROCESSOR.register("mineshaft_sky_view_processor", () -> MineshaftSkyViewProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> PILLAR_PROCESSOR = STRUCTURE_PROCESSOR.register("pillar_processor", () -> PillarProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> BOTTOM_PILLAR_PROCESSOR = STRUCTURE_PROCESSOR.register("bottom_pillar_processor", () -> BottomPillarProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> STRUCTURE_VOID_PROCESSOR = STRUCTURE_PROCESSOR.register("structure_void_processor", () -> StructureVoidProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> FLOOD_WITH_WATER_PROCESSOR = STRUCTURE_PROCESSOR.register("flood_with_water_processor", () -> FloodWithWaterProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> REPLACE_AIR_ONLY_PROCESSOR = STRUCTURE_PROCESSOR.register("replace_air_only_processor", () -> ReplaceAirOnlyProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> REPLACE_LIQUIDS_ONLY_PROCESSOR = STRUCTURE_PROCESSOR.register("replace_liquids_only_processor", () -> ReplaceLiquidOnlyProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> SPAWNER_RANDOMIZING_PROCESSOR = STRUCTURE_PROCESSOR.register("spawner_randomizing_processor", () -> SpawnerRandomizingProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> FILL_END_PORTAL_FRAME_PROCESSOR = STRUCTURE_PROCESSOR.register("fill_end_portal_frame_processor", () -> FillEndPortalFrameProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> REMOVE_FLOATING_BLOCKS_PROCESSOR = STRUCTURE_PROCESSOR.register("remove_floating_blocks_processor", () -> RemoveFloatingBlocksProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> CLOSE_OFF_FLUID_SOURCES_PROCESSOR = STRUCTURE_PROCESSOR.register("close_off_fluid_sources_processor", () -> CloseOffFluidSourcesProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> CLOSE_OFF_AIR_SOURCES_PROCESSOR = STRUCTURE_PROCESSOR.register("close_off_air_sources_processor", () -> CloseOffAirSourcesProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> RANDOM_REPLACE_WITH_PROPERTIES_PROCESSOR = STRUCTURE_PROCESSOR.register("random_replace_with_properties_processor", () -> RandomReplaceWithPropertiesProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> NOISE_REPLACE_WITH_PROPERTIES_PROCESSOR = STRUCTURE_PROCESSOR.register("noise_replace_with_properties_processor", () -> NoiseReplaceWithPropertiesProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> WATERLOGGING_WHEN_REPLACING_WATER_PROCESSOR = STRUCTURE_PROCESSOR.register("waterlogging_when_replacing_water_processor", () -> WaterlogWhenReplacingWaterProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> SUPER_GRAVITY_PROCESSOR = STRUCTURE_PROCESSOR.register("super_gravity_processor", () -> SuperGravityProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> FORCE_PLACE_MUSHROOM_BLOCKS_PROCESSOR = STRUCTURE_PROCESSOR.register("force_place_mushroom_blocks_processor", () -> ForcePlaceMushroomBlocksProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> CORAL_ALIVE_DEAD_PROCESSOR = STRUCTURE_PROCESSOR.register("coral_alive_dead_processor", () -> CoralAliveDeadProcessor.CODEC);

    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> STRUCTURE_SURFACE_PROCESSOR = STRUCTURE_PROCESSOR.register("structure_surface_processor", () -> CappedStructureSurfaceProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> POST_PROCESS_LIST_PROCESSOR = STRUCTURE_PROCESSOR.register("post_process_list_processor", () -> PostProcessListProcessor.CODEC);

    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> WALL_VINE_POST_PROCESSOR = STRUCTURE_PROCESSOR.register("wall_vine_post_processor", () -> WallVinePostProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>> CEILING_VINE_POST_PROCESSOR = STRUCTURE_PROCESSOR.register("ceiling_vine_post_processor", () -> CeilingVinePostProcessor.CODEC);
}
