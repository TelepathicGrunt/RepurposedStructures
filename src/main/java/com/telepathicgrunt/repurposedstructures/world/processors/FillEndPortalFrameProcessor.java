package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.ChunkRandom;

import java.util.Random;

/**
 * FOR RANDOMIZING THE END PORTAL FRAMES WITH EYES OF ENDER
 */
public class FillEndPortalFrameProcessor extends StructureProcessor {

    public static final Codec<FillEndPortalFrameProcessor> CODEC = Codec.unit(FillEndPortalFrameProcessor::new);
    private FillEndPortalFrameProcessor() { }

    @Override
    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfoLocal, Structure.StructureBlockInfo structureBlockInfoWorld, StructurePlacementData structurePlacementData) {
        if (structureBlockInfoWorld.state.isOf(Blocks.END_PORTAL_FRAME)) {
            BlockPos worldPos = structureBlockInfoWorld.pos;
            Random random = new ChunkRandom();
            random.setSeed(worldPos.asLong() * worldPos.getY());

            return new Structure.StructureBlockInfo(
                    structureBlockInfoWorld.pos,
                    structureBlockInfoWorld.state.with(EndPortalFrameBlock.EYE, random.nextFloat() < 0.1F),
                    structureBlockInfoWorld.tag);
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.FILL_END_PORTAL_FRAME_PROCESSOR;
    }
}
