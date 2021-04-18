package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
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

import java.util.Random;

/**
 * RUN ONLY AFTER THE NBT PIECE IS PLACED INTO THE WORLD
 */
public class WallVinePostProcessor extends StructureProcessor {
    public static final Codec<WallVinePostProcessor> CODEC = Codec.FLOAT.fieldOf("probability")
            .xmap(WallVinePostProcessor::new, (wallVinePostProcessor) -> wallVinePostProcessor.probability).codec();

    private final float probability;
    public WallVinePostProcessor(float probability) {
        this.probability = probability;
    }

    @Override
    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfoLocal, Structure.StructureBlockInfo structureBlockInfoWorld, StructurePlacementData structurePlacementData) {
        // Place vines only in air space
        if (structureBlockInfoWorld.state.isAir()) {

            Random random = new ChunkRandom();
            random.setSeed(structureBlockInfoWorld.pos.asLong() * structureBlockInfoWorld.pos.getY());
            Chunk centerChunk = worldView.getChunk(structureBlockInfoWorld.pos);
            BlockState centerState = centerChunk.getBlockState(structureBlockInfoWorld.pos);
            if(random.nextFloat() < probability && centerState.isAir()){

                BlockPos.Mutable mutable = new BlockPos.Mutable();
                for(Direction facing : Direction.Type.HORIZONTAL){

                    mutable.set(structureBlockInfoWorld.pos).move(facing);
                    BlockState worldState = worldView.getChunk(mutable).getBlockState(mutable);

                    // Vines only get placed facing the side of 1 full block.
                    if(!worldState.isOf(Blocks.SPAWNER) && Block.isFaceFullSquare(worldState.getCollisionShape(worldView, pos), facing.getOpposite())){
                        BlockState vineBlock = Blocks.VINE.getDefaultState().with(VineBlock.getFacingProperty(facing), true);
                        centerChunk.setBlockState(structureBlockInfoWorld.pos, vineBlock, false);
                        break;
                    }
                }
            }
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.WALL_VINE_POST_PROCESSOR;
    }
}
