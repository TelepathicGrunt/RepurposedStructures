package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.Blocks;
import net.minecraft.block.Waterloggable;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.Chunk;

public class WaterloggingFixProcessor extends StructureProcessor {

    public static final Codec<WaterloggingFixProcessor> CODEC = Codec.unit(WaterloggingFixProcessor::new);

    private WaterloggingFixProcessor() { }

    @Override
    public Structure.StructureBlockInfo process(WorldView worldReader, BlockPos pos, BlockPos pos2, Structure.StructureBlockInfo infoIn1, Structure.StructureBlockInfo infoIn2, StructurePlacementData settings) {

        // ONLY RUN THIS IF STRUCTURE'S BLOCK IS A DRY WATERLOGGABLE BLOCK
        ChunkPos currentChunkPos = new ChunkPos(infoIn2.pos);
        if (infoIn2.state.getBlock() instanceof Waterloggable && !infoIn2.state.get(Properties.WATERLOGGED)) {
            Chunk currentChunk = worldReader.getChunk(currentChunkPos.x, currentChunkPos.z);
            if (worldReader.getFluidState(infoIn2.pos).isIn(FluidTags.WATER)) {
                currentChunk.setBlockState(infoIn2.pos, Blocks.STONE.getDefaultState(), false);
            }
        }

        return infoIn2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.WATERLOGGING_FIX_PROCESSOR;
    }
}
