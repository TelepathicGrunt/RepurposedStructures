package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

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
    public Template.BlockInfo processBlock(IWorldReader worldView, BlockPos pos, BlockPos blockPos, Template.BlockInfo structureBlockInfoLocal, Template.BlockInfo structureBlockInfoWorld, PlacementSettings structurePlacementData) {
        if (structureBlockInfoWorld.state.is(Blocks.END_PORTAL_FRAME)) {
            BlockPos worldPos = structureBlockInfoWorld.pos;
            Random random = new SharedSeedRandom();
            random.setSeed(worldPos.asLong() * worldPos.getY());

            return new Template.BlockInfo(
                    structureBlockInfoWorld.pos,
                    structureBlockInfoWorld.state.setValue(EndPortalFrameBlock.HAS_EYE, random.nextFloat() < probability),
                    structureBlockInfoWorld.nbt);
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected IStructureProcessorType<?> getType() {
        return RSProcessors.FILL_END_PORTAL_FRAME_PROCESSOR;
    }
}
