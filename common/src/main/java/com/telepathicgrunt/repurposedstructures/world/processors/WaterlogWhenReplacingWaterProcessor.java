package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.MapCodec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class WaterlogWhenReplacingWaterProcessor implements StructureProcessor {

    public static final MapCodec<WaterlogWhenReplacingWaterProcessor> CODEC = MapCodec.unit(WaterlogWhenReplacingWaterProcessor::new);

    private WaterlogWhenReplacingWaterProcessor() { }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos targetPosition, BlockPos referencePos, BlockPos templateRelativePos, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {
        if (structureBlockInfoWorld.state().hasProperty(BlockStateProperties.WATERLOGGED)) {
            if (level instanceof WorldGenRegion worldGenRegion && !worldGenRegion.getCenter().equals(ChunkPos.containing(structureBlockInfoWorld.pos()))) {
                return structureBlockInfoWorld;
            }

            BlockState blockState = level.getChunk(structureBlockInfoWorld.pos()).getBlockState(structureBlockInfoWorld.pos());
            boolean isWater = blockState.getFluidState().is(FluidTags.WATER);

            if (isWater) {
                ChunkAccess chunk = level.getChunk(structureBlockInfoWorld.pos());
                int minY = chunk.getMinY();
                int maxY = chunk.getMaxY();
                int currentY = structureBlockInfoWorld.pos().getY();
                if (currentY >= minY && currentY <= maxY) {
                    ((LevelAccessor) level).scheduleTick(structureBlockInfoWorld.pos(), structureBlockInfoWorld.state().getBlock(), 0);
                }
            }

            return new StructureTemplate.StructureBlockInfo(
                    structureBlockInfoWorld.pos(),
                    structureBlockInfoWorld.state().setValue(BlockStateProperties.WATERLOGGED, isWater),
                    structureBlockInfoWorld.nbt());
        }

        return structureBlockInfoWorld;
    }

    @Override
    public MapCodec<? extends StructureProcessor> codec() {
        return RSProcessors.WATERLOGGING_WHEN_REPLACING_WATER_PROCESSOR.get();
    }
}
