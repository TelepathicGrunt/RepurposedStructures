package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.processors.*;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class RSProcessors {
    public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSOR = DeferredRegister.create(Registry.STRUCTURE_PROCESSOR_REGISTRY, RepurposedStructures.MODID);

    public static final RegistryObject<StructureProcessorType<AirProcessor>> AIR_PROCESSOR = STRUCTURE_PROCESSOR.register("air_processor", () -> () -> AirProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<EndGatewayProcessor>> END_GATEWAY_PROCESSOR = STRUCTURE_PROCESSOR.register("end_gateway_processor", () -> () -> EndGatewayProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<TickBlocksProcessor>> TICK_BLOCKS_PROCESSOR = STRUCTURE_PROCESSOR.register("tick_blocks_processor", () -> () -> TickBlocksProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<MineshaftSkyViewProcessor>> MINESHAFT_SKY_VIEW_PROCESSOR = STRUCTURE_PROCESSOR.register("mineshaft_sky_view_processor", () -> () -> MineshaftSkyViewProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<PillarProcessor>> PILLAR_PROCESSOR = STRUCTURE_PROCESSOR.register("pillar_processor", () -> () -> PillarProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<StructureVoidProcessor>> STRUCTURE_VOID_PROCESSOR = STRUCTURE_PROCESSOR.register("structure_void_processor", () -> () -> StructureVoidProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<FloodWithWaterProcessor>> FLOOD_WITH_WATER_PROCESSOR = STRUCTURE_PROCESSOR.register("flood_with_water_processor", () -> () -> FloodWithWaterProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<ReplaceAirOnlyProcessor>> REPLACE_AIR_ONLY_PROCESSOR = STRUCTURE_PROCESSOR.register("replace_air_only_processor", () -> () -> ReplaceAirOnlyProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<ReplaceLiquidOnlyProcessor>> REPLACE_LIQUIDS_ONLY_PROCESSOR = STRUCTURE_PROCESSOR.register("replace_liquids_only_processor", () -> () -> ReplaceLiquidOnlyProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<SpawnerRandomizingProcessor>> SPAWNER_RANDOMIZING_PROCESSOR = STRUCTURE_PROCESSOR.register("spawner_randomizing_processor", () -> () -> SpawnerRandomizingProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<FillEndPortalFrameProcessor>> FILL_END_PORTAL_FRAME_PROCESSOR = STRUCTURE_PROCESSOR.register("fill_end_portal_frame_processor", () -> () -> FillEndPortalFrameProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<RemoveFloatingBlocksProcessor>> REMOVE_FLOATING_BLOCKS_PROCESSOR = STRUCTURE_PROCESSOR.register("remove_floating_blocks_processor", () -> () -> RemoveFloatingBlocksProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<CloseOffFluidSourcesProcessor>> CLOSE_OFF_FLUID_SOURCES_PROCESSOR = STRUCTURE_PROCESSOR.register("close_off_fluid_sources_processor", () -> () -> CloseOffFluidSourcesProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<CloseOffAirSourcesProcessor>> CLOSE_OFF_AIR_SOURCES_PROCESSOR = STRUCTURE_PROCESSOR.register("close_off_air_sources_processor", () -> () -> CloseOffAirSourcesProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<RandomReplaceWithPropertiesProcessor>> RANDOM_REPLACE_WITH_PROPERTIES_PROCESSOR = STRUCTURE_PROCESSOR.register("random_replace_with_properties_processor", () -> () -> RandomReplaceWithPropertiesProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<NoiseReplaceWithPropertiesProcessor>> NOISE_REPLACE_WITH_PROPERTIES_PROCESSOR = STRUCTURE_PROCESSOR.register("noise_replace_with_properties_processor", () -> () -> NoiseReplaceWithPropertiesProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<WaterloggingFixProcessor>> WATERLOGGING_FIX_PROCESSOR = STRUCTURE_PROCESSOR.register("waterlogging_fix_processor", () -> () -> WaterloggingFixProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<SuperGravityProcessor>> SUPER_GRAVITY_PROCESSOR = STRUCTURE_PROCESSOR.register("super_gravity_processor", () -> () -> SuperGravityProcessor.CODEC);

    public static final RegistryObject<StructureProcessorType<WallVinePostProcessor>> WALL_VINE_POST_PROCESSOR = STRUCTURE_PROCESSOR.register("wall_vine_post_processor", () -> () -> WallVinePostProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<CeilingVinePostProcessor>> CEILING_VINE_POST_PROCESSOR = STRUCTURE_PROCESSOR.register("ceiling_vine_post_processor", () -> () -> CeilingVinePostProcessor.CODEC);
}
