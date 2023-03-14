package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

/**
 * For removing stuff like floating tall grass or kelp
 */
public class RemoveFloatingBlocksProcessor extends StructureProcessor {

    public static final Codec<RemoveFloatingBlocksProcessor> CODEC = Codec.unit(RemoveFloatingBlocksProcessor::new);
    private RemoveFloatingBlocksProcessor() { }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(structureBlockInfoWorld.pos);
        if(levelReader instanceof WorldGenRegion worldGenRegion && !worldGenRegion.getCenter().equals(new ChunkPos(mutable))) {
            return structureBlockInfoWorld;
        }

        // attempts to remove invalid floating plants
        ChunkAccess cachedChunk = levelReader.getChunk(mutable);
        if(structureBlockInfoWorld.state.isAir() || structureBlockInfoWorld.state.getMaterial().isLiquid()) {

            // set the block in the world so that canPlaceAt's result changes
            cachedChunk.setBlockState(mutable, structureBlockInfoWorld.state, false);
            mutable.move(Direction.UP);
            BlockState aboveWorldState = levelReader.getBlockState(mutable);

            // detects the invalidly placed blocks
            while(mutable.getY() < levelReader.getHeight() && !aboveWorldState.canSurvive(levelReader, mutable)) {
                cachedChunk.setBlockState(mutable, structureBlockInfoWorld.state, false);
                mutable.move(Direction.UP);
                aboveWorldState = levelReader.getBlockState(mutable);
            }

            for (Direction direction : Direction.Plane.HORIZONTAL) {
                mutable.set(structureBlockInfoWorld.pos);
                mutable.move(direction);
                ChunkPos chunkPos = new ChunkPos(mutable);
                ChunkAccess chunkAccess2 = cachedChunk;
                if (!chunkPos.equals(cachedChunk.getPos())) {
                    chunkAccess2 = levelReader.getChunk(mutable);
                }
                BlockState sideBlock = chunkAccess2.getBlockState(mutable);
                if (!sideBlock.canSurvive(levelReader, mutable)) {
                    chunkAccess2.setBlockState(mutable, structureBlockInfoWorld.state, false);
                }
            }
        }

        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.REMOVE_FLOATING_BLOCKS_PROCESSOR.get();
    }
}
