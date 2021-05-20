package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.block.Block;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkRandom;

import java.util.List;
import java.util.Random;

/**
 * Will help enclose the structure in solid blocks rather than allow fluid source blocks to be floating.
 * Best for Ocean Structures with water marking the insides that should never be exposed to air.
 */
public class CloseOffAirSourcesProcessor extends StructureProcessor {

    public static final Codec<CloseOffAirSourcesProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.mapPair(Registry.BLOCK.fieldOf("block"), Codec.intRange(1, Integer.MAX_VALUE).fieldOf("weight"))
                    .codec().listOf().fieldOf("weighted_list_of_replacement_blocks")
                    .forGetter(processor -> processor.weightedReplacementBlocks))
            .apply(instance, instance.stable(CloseOffAirSourcesProcessor::new)));

    private final List<Pair<Block, Integer>> weightedReplacementBlocks;

    public CloseOffAirSourcesProcessor(List<Pair<Block, Integer>> weightedReplacementBlocks) {
        this.weightedReplacementBlocks = weightedReplacementBlocks;
    }

    @Override
    public Structure.StructureBlockInfo process(WorldView worldReader, BlockPos pos, BlockPos pos2, Structure.StructureBlockInfo infoIn1, Structure.StructureBlockInfo infoIn2, StructurePlacementData settings) {

        ChunkPos currentChunkPos = new ChunkPos(infoIn2.pos);
        if(!infoIn2.state.getFluidState().isEmpty()){
            Chunk currentChunk = worldReader.getChunk(currentChunkPos.x, currentChunkPos.z);

            // Remove fluid sources in adjacent horizontal blocks across chunk boundaries and above as well
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            for (Direction direction : Direction.values()) {

                mutable.set(infoIn2.pos).move(direction);
                if (currentChunkPos.x != mutable.getX() >> 4 || currentChunkPos.z != mutable.getZ() >> 4) {
                    currentChunk = worldReader.getChunk(mutable);
                    currentChunkPos = new ChunkPos(mutable);
                }

                if (currentChunk.getBlockState(mutable).isAir()) {
                    Random random = new ChunkRandom();
                    random.setSeed(mutable.asLong() * mutable.getY());

                    Block replacementBlock = GeneralUtils.getRandomEntry(weightedReplacementBlocks, random);
                    currentChunk.setBlockState(mutable, replacementBlock.getDefaultState(), false);
                }
            }
        }

        return infoIn2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.CLOSE_OFF_AIR_SOURCES_PROCESSOR;
    }
}
