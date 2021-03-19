package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.Blocks;
import net.minecraft.block.Waterloggable;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.Chunk;

/**
 * FOR ELEMENTS USING legacy_single_pool_element AND WANTS AIR TO REPLACE TERRAIN.
 */
public class WaterloggingFixProcessor extends StructureProcessor {

    public static final WaterloggingFixProcessor INSTANCE = new WaterloggingFixProcessor();
    public static final Codec<WaterloggingFixProcessor> CODEC = Codec.unit(() -> INSTANCE);
    private WaterloggingFixProcessor() { }

    @Override
    public Structure.StructureBlockInfo process(WorldView worldReader, BlockPos pos, BlockPos pos2, Structure.StructureBlockInfo infoIn1, Structure.StructureBlockInfo infoIn2, StructurePlacementData settings) {

        // Workaround for https://bugs.mojang.com/browse/MC-130584
        // Due to a hardcoded field in Templates, any waterloggable blocks in structures replacing water in the world will become waterlogged.
        // Idea of workaround is detect if we are placing a waterloggable block and if so, remove the water in the world instead.
        ChunkPos currentChunk = new ChunkPos(infoIn2.pos);
        if(infoIn2.state.getBlock() instanceof Waterloggable){
            if(worldReader.getFluidState(infoIn2.pos).isIn(FluidTags.WATER)){
                worldReader.getChunk(currentChunk.x, currentChunk.z).setBlockState(infoIn2.pos, Blocks.AIR.getDefaultState(), false);
            }

            // Needed was waterloggable blocks will get waterlogged from neighboring chunk's water too.
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            for(Direction direction : Direction.Type.HORIZONTAL){
                mutable.set(infoIn2.pos).move(direction);

                if(currentChunk.x != mutable.getX() >> 4 || currentChunk.z != mutable.getZ() >> 4){
                    Chunk sideChunk = worldReader.getChunk(mutable);
                    if(sideChunk.getFluidState(mutable).isIn(FluidTags.WATER)) {
                        sideChunk.setBlockState(mutable, Blocks.DIRT.getDefaultState(), false);
                    }
                }
            }
        }

        return infoIn2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.WATER_FIX_PROCESSORS;
    }
}
