package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

/**
 * FOR ELEMENTS USING legacy_single_pool_element AND WANTS AIR TO REPLACE TERRAIN.
 */
public class BubbleColumnProcessor extends StructureProcessor {

    public static final Codec<BubbleColumnProcessor> CODEC = Codec.unit(BubbleColumnProcessor::new);

    private BubbleColumnProcessor() {}

    @Override
    public Template.BlockInfo processBlock(IWorldReader worldView, BlockPos pos, BlockPos blockPos, Template.BlockInfo structureBlockInfoLocal, Template.BlockInfo structureBlockInfoWorld, PlacementSettings structurePlacementData) {
        if(structureBlockInfoWorld.state.getBlock() == Blocks.BUBBLE_COLUMN){
            IChunk chunk = worldView.getChunk(structureBlockInfoWorld.pos);
            chunk.getBlockTicks().scheduleTick(structureBlockInfoWorld.pos,  structureBlockInfoWorld.state.getBlock(), 0);
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected IStructureProcessorType<?> getType() {
        return RSProcessors.BUBBLE_COLUMN_PROCESSOR;
    }
}
