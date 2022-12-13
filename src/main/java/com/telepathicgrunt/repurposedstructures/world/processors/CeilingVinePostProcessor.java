package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.Collections;
import java.util.List;
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
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader worldView, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {
        // Place vines only in air space
        if (structureBlockInfoWorld.state.isAir()) {

            RandomSource random = new WorldgenRandom(new LegacyRandomSource(0L));
            random.setSeed(structureBlockInfoWorld.pos.asLong() * structureBlockInfoWorld.pos.getY());
            ChunkAccess centerChunk = worldView.getChunk(structureBlockInfoWorld.pos);
            BlockState centerState = centerChunk.getBlockState(structureBlockInfoWorld.pos);
            BlockPos abovePos = structureBlockInfoWorld.pos.above();
            BlockState aboveState = centerChunk.getBlockState(abovePos);

            if(random.nextFloat() < probability &&
                centerState.isAir() &&
                Block.isFaceFull(aboveState.getCollisionShape(worldView, abovePos), Direction.DOWN)) {

                BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
                List<Direction> shuffledDirectionList = Direction.Plane.HORIZONTAL.stream().collect(Collectors.toList());
                Collections.shuffle(shuffledDirectionList);
                for(Direction facing : shuffledDirectionList) {
                    mutable.set(structureBlockInfoWorld.pos).move(facing);
                    BlockState worldState = worldView.getChunk(mutable).getBlockState(mutable);

                    // Vines only get placed if side block is empty and top block is solid.
                    if(!worldState.canOcclude()) {
                        // side block to hold vine
                        worldView.getChunk(mutable).setBlockState(mutable, blockState, false);

                        // ceiling vine
                        BlockState vineBlock = Blocks.VINE.defaultBlockState().setValue(VineBlock.getPropertyForFace(facing), true).setValue(VineBlock.UP, true);
                        mutable.move(facing.getOpposite()); // Move back to center
                        centerChunk.setBlockState(mutable, vineBlock, false);

                        // hanging vines
                        vineBlock = vineBlock.setValue(VineBlock.UP, false);
                        for(int depth = random.nextInt(4); depth < 3; depth++) {
                            mutable.move(Direction.DOWN);
                            if(!centerChunk.getBlockState(mutable).isAir()) {
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
