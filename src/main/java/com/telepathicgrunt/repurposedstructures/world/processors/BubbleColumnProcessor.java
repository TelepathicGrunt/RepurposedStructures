package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

/**
 * FOR ELEMENTS USING legacy_single_pool_element AND WANTS AIR TO REPLACE TERRAIN.
 */
public class BubbleColumnProcessor extends StructureProcessor {

    public static final Codec<BubbleColumnProcessor> CODEC = Codec.unit(BubbleColumnProcessor::new);

    private BubbleColumnProcessor() {}

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader worldView, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {
        if(structureBlockInfoWorld.state.getBlock() == Blocks.BUBBLE_COLUMN){
            ChunkAccess chunk = worldView.getChunk(structureBlockInfoWorld.pos);

            // We use chunk sections instead of the world's min and max y value because a structure piece can generate
            // right on the edge of the lowest or highest chunk section and cut into a non-existent section.
            // Checking the world's min and max y would prevent the fluid tick crashing when assigned a position in a
            // non-existent chunk section but it may still crash Cubic Chunks or cause weird behaviors with it.
            // Using chunk sections will help make sure this processor works without crashing Cubic Chunks hopefully lol.
            if(chunk.getSections().length != 0 &&
                chunk.getSections()[0].bottomBlockY() > structureBlockInfoWorld.pos.getY() &&
                chunk.getSections()[chunk.getSections().length - 1].bottomBlockY() + 15 < structureBlockInfoWorld.pos.getY())
            {
                chunk.getBlockTicks().scheduleTick(structureBlockInfoWorld.pos,  structureBlockInfoWorld.state.getBlock(), 0);
            }
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.BUBBLE_COLUMN_PROCESSOR;
    }
}
