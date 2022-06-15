package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
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
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader worldView, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {
        // Place vines only in air space
        if (structureBlockInfoWorld.state.isAir()) {

            RandomSource random = new WorldgenRandom(new LegacyRandomSource(0L));
            random.setSeed(structureBlockInfoWorld.pos.asLong() * structureBlockInfoWorld.pos.getY());
            ChunkAccess centerChunk = worldView.getChunk(structureBlockInfoWorld.pos);
            BlockState centerState = centerChunk.getBlockState(structureBlockInfoWorld.pos);
            if(random.nextFloat() < probability && centerState.isAir()) {

                BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
                for(Direction facing : Direction.Plane.HORIZONTAL) {

                    mutable.set(structureBlockInfoWorld.pos).move(facing);
                    BlockState worldState = worldView.getChunk(mutable).getBlockState(mutable);

                    // Vines only get placed facing the side of 1 full block.
                    if(!worldState.is(Blocks.SPAWNER) && Block.isFaceFull(worldState.getCollisionShape(worldView, pos), facing.getOpposite())) {
                        BlockState vineBlock = Blocks.VINE.defaultBlockState().setValue(VineBlock.getPropertyForFace(facing), true);
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
