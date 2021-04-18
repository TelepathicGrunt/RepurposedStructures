package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkRandom;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * RUN ONLY AFTER THE NBT PIECE IS PLACED INTO THE WORLD
 */
public class CeilingVinePostProcessor extends StructureProcessor {

    public static final Codec<CeilingVinePostProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.FLOAT.fieldOf("probability").stable().forGetter((ceilingVinePostProcessor) -> ceilingVinePostProcessor.probability),
            BlockState.CODEC.fieldOf("blockstate").forGetter((ceilingVinePostProcessor) -> ceilingVinePostProcessor.blockState))
        .apply(instance, instance.stable(CeilingVinePostProcessor::new)));

    private final float probability;
    private final BlockState blockState;
    public CeilingVinePostProcessor(float probability, BlockState blockState) {
        this.probability = probability;
        this.blockState = blockState;
    }

    @Override
    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfoLocal, Structure.StructureBlockInfo structureBlockInfoWorld, StructurePlacementData structurePlacementData) {
        // Place vines only in air space
        if (structureBlockInfoWorld.state.isAir()) {

            Random random = new ChunkRandom();
            random.setSeed(structureBlockInfoWorld.pos.asLong() * structureBlockInfoWorld.pos.getY());
            Chunk centerChunk = worldView.getChunk(structureBlockInfoWorld.pos);
            BlockState centerState = centerChunk.getBlockState(structureBlockInfoWorld.pos);
            BlockPos abovePos = structureBlockInfoWorld.pos.up();
            BlockState aboveState = centerChunk.getBlockState(abovePos);

            if(random.nextFloat() < probability &&
                centerState.isAir() &&
                Block.isFaceFullSquare(aboveState.getCollisionShape(worldView, abovePos), Direction.DOWN)){

                BlockPos.Mutable mutable = new BlockPos.Mutable();
                List<Direction> shuffledDirectionList = Direction.Type.HORIZONTAL.stream().collect(Collectors.toList());
                Collections.shuffle(shuffledDirectionList);
                for(Direction facing : shuffledDirectionList){
                    mutable.set(structureBlockInfoWorld.pos).move(facing);
                    BlockState worldState = worldView.getChunk(mutable).getBlockState(mutable);

                    // Vines only get placed if side block is empty and top block is solid.
                    if(!worldState.isOpaque()){
                        // side block to hold vine
                        worldView.getChunk(mutable).setBlockState(mutable, blockState, false);

                        // ceiling vine
                        BlockState vineBlock = Blocks.VINE.getDefaultState().with(VineBlock.getFacingProperty(facing), true).with(VineBlock.UP, true);
                        mutable.move(facing.getOpposite()); // Move back to center
                        centerChunk.setBlockState(mutable, vineBlock, false);

                        // hanging vines
                        vineBlock = vineBlock.with(VineBlock.UP, false);
                        for(int depth = random.nextInt(4); depth < 3; depth++){
                            mutable.move(Direction.DOWN);
                            if(!centerChunk.getBlockState(mutable).isAir()){
                                break;
                            }
                            centerChunk.setBlockState(mutable, vineBlock, false);
                        }

                        break;
                    }
                }
            }
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.CEILING_VINE_POST_PROCESSOR;
    }
}
