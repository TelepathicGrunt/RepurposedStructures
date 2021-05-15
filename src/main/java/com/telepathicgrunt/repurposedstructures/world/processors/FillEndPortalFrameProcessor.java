package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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

    public static final Codec<FillEndPortalFrameProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.FLOAT.fieldOf("probability_per_block").stable().forGetter((processor) -> processor.probability))
            .apply(instance, instance.stable(FillEndPortalFrameProcessor::new)));

    private final float probability;
    public FillEndPortalFrameProcessor(Float probability) {
        this.probability = probability;
    }

    @Override
    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfoLocal, Structure.StructureBlockInfo structureBlockInfoWorld, StructurePlacementData structurePlacementData) {
        if (structureBlockInfoWorld.state.isOf(Blocks.END_PORTAL_FRAME)) {
            BlockPos worldPos = structureBlockInfoWorld.pos;
            Random random = new ChunkRandom();
            random.setSeed(worldPos.asLong() * worldPos.getY());

            return new Structure.StructureBlockInfo(
                    structureBlockInfoWorld.pos,
                    structureBlockInfoWorld.state.with(EndPortalFrameBlock.EYE, random.nextFloat() < probability),
                    structureBlockInfoWorld.tag);
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.FILL_END_PORTAL_FRAME_PROCESSOR;
    }
}
