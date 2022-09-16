package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EndPortalFrameBlock;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

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
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader worldView, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {
        if (structureBlockInfoWorld.state.is(Blocks.END_PORTAL_FRAME)) {
            BlockPos worldPos = structureBlockInfoWorld.pos;
            RandomSource random = structurePlacementData.getRandom(worldPos);

            return new StructureTemplate.StructureBlockInfo(
                    structureBlockInfoWorld.pos,
                    structureBlockInfoWorld.state.setValue(EndPortalFrameBlock.HAS_EYE, random.nextFloat() < probability),
                    structureBlockInfoWorld.nbt);
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.FILL_END_PORTAL_FRAME_PROCESSOR;
    }
}
