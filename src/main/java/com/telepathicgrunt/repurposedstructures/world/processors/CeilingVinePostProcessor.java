package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * FOR ELEMENTS USING legacy_single_pool_element AND WANTS AIR TO REPLACE TERRAIN.
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
    public Template.BlockInfo processBlock(IWorldReader worldView, BlockPos pos, BlockPos blockPos, Template.BlockInfo structureBlockInfoLocal, Template.BlockInfo structureBlockInfoWorld, PlacementSettings structurePlacementData) {
        // Place vines only in air space
        if (structureBlockInfoWorld.state.isAir()) {
            Random random = new SharedSeedRandom();
            random.setSeed(structureBlockInfoWorld.pos.asLong() * structureBlockInfoWorld.pos.getY());
            IChunk centerChunk = worldView.getChunk(structureBlockInfoWorld.pos);
            BlockState centerState = centerChunk.getBlockState(structureBlockInfoWorld.pos);
            BlockPos abovePos = structureBlockInfoWorld.pos.above();
            BlockState aboveState = centerChunk.getBlockState(abovePos);

            if(random.nextFloat() < probability &&
                centerState.isAir() &&
                Block.isFaceFull(aboveState.getCollisionShape(worldView, abovePos), Direction.DOWN)){

                BlockPos.Mutable mutable = new BlockPos.Mutable();
                List<Direction> shuffledDirectionList = Direction.Plane.HORIZONTAL.stream().collect(Collectors.toList());
                Collections.shuffle(shuffledDirectionList);
                for(Direction facing : shuffledDirectionList){
                    mutable.set(structureBlockInfoWorld.pos).move(facing);
                    BlockState worldState = worldView.getChunk(mutable).getBlockState(mutable);

                    // Vines only get placed if side block is empty and top block is solid.
                    if(!worldState.canOcclude()){
                        // side block to hold vine
                        worldView.getChunk(mutable).setBlockState(mutable, blockState, false);

                        // ceiling vine
                        BlockState vineBlock = Blocks.VINE.defaultBlockState().setValue(VineBlock.getPropertyForFace(facing), true).setValue(VineBlock.UP, true);
                        mutable.move(facing.getOpposite()); // Move back to center
                        centerChunk.setBlockState(mutable, vineBlock, false);

                        // hanging vines
                        vineBlock = vineBlock.setValue(VineBlock.UP, false);
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
    protected IStructureProcessorType<?> getType() {
        return RSProcessors.CEILING_VINE_POST_PROCESSOR;
    }
}
