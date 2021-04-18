package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

import java.util.Random;

/**
 * FOR ELEMENTS USING legacy_single_pool_element AND WANTS AIR TO REPLACE TERRAIN.
 */
public class WallVinePostProcessor extends StructureProcessor {
    public static final Codec<WallVinePostProcessor> CODEC = Codec.FLOAT.fieldOf("probability")
            .xmap(WallVinePostProcessor::new, (wallVinePostProcessor) -> wallVinePostProcessor.probability).codec();

    private final float probability;
    public WallVinePostProcessor(float probability) {
        this.probability = probability;
    }

    @Override
    public Template.BlockInfo process(IWorldReader worldView, BlockPos pos, BlockPos blockPos, Template.BlockInfo structureBlockInfoLocal, Template.BlockInfo structureBlockInfoWorld, PlacementSettings structurePlacementData) {
        // Place vines only in air space
        if (structureBlockInfoWorld.state.isAir()) {
            Random random = new SharedSeedRandom();
            random.setSeed(structureBlockInfoWorld.pos.toLong() * structureBlockInfoWorld.pos.getY());
            IChunk centerChunk = worldView.getChunk(structureBlockInfoWorld.pos);
            BlockState centerState = centerChunk.getBlockState(structureBlockInfoWorld.pos);
            if(random.nextFloat() < probability && centerState.isAir()){

                BlockPos.Mutable mutable = new BlockPos.Mutable();
                for(Direction facing : Direction.Plane.HORIZONTAL){

                    mutable.setPos(structureBlockInfoWorld.pos).move(facing);
                    BlockState worldState = worldView.getChunk(mutable).getBlockState(mutable);

                    // Vines only get placed facing the side of 1 full block.
                    if(!worldState.isIn(Blocks.SPAWNER) && Block.doesSideFillSquare(worldState.getCollisionShape(worldView, pos), facing.getOpposite())){
                        BlockState vineBlock = Blocks.VINE.getDefaultState().with(VineBlock.getPropertyFor(facing), true);
                        centerChunk.setBlockState(structureBlockInfoWorld.pos, vineBlock, false);
                        break;
                    }
                }
            }
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected IStructureProcessorType<?> getType() {
        return RSProcessors.WALL_VINE_POST_PROCESSOR;
    }
}
