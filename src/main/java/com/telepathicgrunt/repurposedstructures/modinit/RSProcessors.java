package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.processors.AirProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.BiomeSurfaceProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.BubbleColumnProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.CeilingVinePostProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.CloseOffAirSourcesProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.CloseOffFluidSourcesProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.DataBlockProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.FillEndPortalFrameProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.FloodWithWaterProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.NoiseReplaceWithPropertiesProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.RandomReplaceWithPropertiesProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.RemoveFloatingBlocksProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.ReplaceAirOnlyProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.ReplaceLiquidOnlyProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.SpawnerRandomizingProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.StructureVoidProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.WallVinePostProcessor;
import com.telepathicgrunt.repurposedstructures.world.processors.WaterloggingFixProcessor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;

public final class RSProcessors {
    private RSProcessors() {}

    public static IStructureProcessorType<AirProcessor> AIR_PROCESSOR = () -> AirProcessor.CODEC;
    public static IStructureProcessorType<BubbleColumnProcessor> BUBBLE_COLUMN_PROCESSOR = () -> BubbleColumnProcessor.CODEC;
    public static IStructureProcessorType<DataBlockProcessor> DATA_BLOCK_PROCESSOR = () -> DataBlockProcessor.CODEC;
    public static IStructureProcessorType<BiomeSurfaceProcessor> BIOME_SURFACE_PROCESSOR = () -> BiomeSurfaceProcessor.CODEC;
    public static IStructureProcessorType<StructureVoidProcessor> STRUCTURE_VOID_PROCESSOR = () -> StructureVoidProcessor.CODEC;
    public static IStructureProcessorType<FloodWithWaterProcessor> FLOOD_WITH_WATER_PROCESSOR = () -> FloodWithWaterProcessor.CODEC;
    public static IStructureProcessorType<WaterloggingFixProcessor> WATER_FIX_PROCESSOR = () -> WaterloggingFixProcessor.CODEC;
    public static IStructureProcessorType<ReplaceAirOnlyProcessor> REPLACE_AIR_ONLY_PROCESSOR = () -> ReplaceAirOnlyProcessor.CODEC;
    public static IStructureProcessorType<ReplaceLiquidOnlyProcessor> REPLACE_LIQUIDS_ONLY_PROCESSOR = () -> ReplaceLiquidOnlyProcessor.CODEC;
    public static IStructureProcessorType<SpawnerRandomizingProcessor> SPAWNER_RANDOMIZING_PROCESSOR = () -> SpawnerRandomizingProcessor.CODEC;
    public static IStructureProcessorType<FillEndPortalFrameProcessor> FILL_END_PORTAL_FRAME_PROCESSOR = () -> FillEndPortalFrameProcessor.CODEC;
    public static IStructureProcessorType<RemoveFloatingBlocksProcessor> REMOVE_FLOATING_BLOCKS_PROCESSOR = () -> RemoveFloatingBlocksProcessor.CODEC;
    public static IStructureProcessorType<CloseOffFluidSourcesProcessor> CLOSE_OFF_FLUID_SOURCES_PROCESSOR = () -> CloseOffFluidSourcesProcessor.CODEC;
    public static IStructureProcessorType<CloseOffAirSourcesProcessor> CLOSE_OFF_AIR_SOURCES_PROCESSOR = () -> CloseOffAirSourcesProcessor.CODEC;
    public static IStructureProcessorType<RandomReplaceWithPropertiesProcessor> RANDOM_REPLACE_WITH_PROPERTIES_PROCESSOR = () -> RandomReplaceWithPropertiesProcessor.CODEC;
    public static IStructureProcessorType<NoiseReplaceWithPropertiesProcessor> NOISE_REPLACE_WITH_PROPERTIES_PROCESSOR = () -> NoiseReplaceWithPropertiesProcessor.CODEC;

    public static IStructureProcessorType<WallVinePostProcessor> WALL_VINE_POST_PROCESSOR = () -> WallVinePostProcessor.CODEC;
    public static IStructureProcessorType<CeilingVinePostProcessor> CEILING_VINE_POST_PROCESSOR = () -> CeilingVinePostProcessor.CODEC;

    public static void registerProcessors() {
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "air_processor"), AIR_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "bubble_column_processor"), BUBBLE_COLUMN_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "data_block_processor"), DATA_BLOCK_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "water_fix_processor"), WATER_FIX_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "biome_surface_processor"), BIOME_SURFACE_PROCESSOR);
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

        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "wall_vine_post_processor"), WALL_VINE_POST_PROCESSOR);
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(RepurposedStructures.MODID, "ceiling_vine_post_processor"), CEILING_VINE_POST_PROCESSOR);
    }
}
