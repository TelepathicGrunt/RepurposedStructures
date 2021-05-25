package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.block.Block;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.Direction;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

import java.util.List;
import java.util.Random;

/**
 * Will help enclose the structure in solid blocks rather than allow fluid source blocks to be floating.
 * Best for Nether Structures with Cave Air marking the insides that should never be exposed to lava.
 */
public class CloseOffFluidSourcesProcessor extends StructureProcessor {

    public static final Codec<CloseOffFluidSourcesProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.mapPair(Registry.BLOCK.fieldOf("block"), Codec.intRange(1, Integer.MAX_VALUE).fieldOf("weight"))
                    .codec().listOf().fieldOf("weighted_list_of_replacement_blocks")
                    .forGetter(processor -> processor.weightedReplacementBlocks),
            Codec.BOOL.fieldOf("ignore_down").orElse(false).forGetter(processor -> processor.ignoreDown))
            .apply(instance, instance.stable(CloseOffFluidSourcesProcessor::new)));

    private final List<Pair<Block, Integer>> weightedReplacementBlocks;
    private final boolean ignoreDown;

    public CloseOffFluidSourcesProcessor(List<Pair<Block, Integer>> weightedReplacementBlocks, boolean ignoreDown) {
        this.weightedReplacementBlocks = weightedReplacementBlocks;
        this.ignoreDown = ignoreDown;
    }

    @Override
    public Template.BlockInfo processBlock(IWorldReader worldReader, BlockPos pos, BlockPos pos2, Template.BlockInfo infoIn1, Template.BlockInfo infoIn2, PlacementSettings settings) {

        ChunkPos currentChunkPos = new ChunkPos(infoIn2.pos);
        if(!infoIn2.state.getFluidState().isEmpty()) return infoIn2;

        if(!GeneralUtils.isFullCube(worldReader, infoIn2.pos, infoIn2.state) || !infoIn2.state.getMaterial().blocksMotion()){
            IChunk currentChunk = worldReader.getChunk(currentChunkPos.x, currentChunkPos.z);

            // Remove fluid sources in adjacent horizontal blocks across chunk boundaries and above as well
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            for (Direction direction : Direction.values()) {
                if(ignoreDown && direction == Direction.DOWN) continue;

                mutable.set(infoIn2.pos).move(direction);
                if (currentChunkPos.x != mutable.getX() >> 4 || currentChunkPos.z != mutable.getZ() >> 4) {
                    currentChunk = worldReader.getChunk(mutable);
                    currentChunkPos = new ChunkPos(mutable);
                }

                FluidState fluidState = currentChunk.getFluidState(mutable);
                if (fluidState.isSource()) {
                    Random random = new SharedSeedRandom();
                    random.setSeed(mutable.asLong() * mutable.getY());

                    Block replacementBlock = GeneralUtils.getRandomEntry(weightedReplacementBlocks, random);
                    currentChunk.setBlockState(mutable, replacementBlock.defaultBlockState(), false);
                }
            }
        }

        return infoIn2;
    }

    @Override
    protected IStructureProcessorType<?> getType() {
        return RSProcessors.CLOSE_OFF_FLUID_SOURCES_PROCESSOR;
    }
}
