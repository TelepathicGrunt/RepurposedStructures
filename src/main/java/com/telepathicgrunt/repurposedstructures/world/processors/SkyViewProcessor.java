package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * FOR ELEMENTS USING legacy_single_pool_element AND WANTS AIR TO REPLACE TERRAIN.
 */
public class SkyViewProcessor extends StructureProcessor {

    public static final Codec<SkyViewProcessor> CODEC = Codec.unit(SkyViewProcessor::new);

    private SkyViewProcessor() {

    }

    @Override
    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfoLocal, Structure.StructureBlockInfo structureBlockInfoWorld, StructurePlacementData structurePlacementData) {
        if (structureBlockInfoWorld.state != null && !structureBlockInfoWorld.state.isOf(Blocks.STRUCTURE_VOID)) {
            if(worldView.isSkyVisibleAllowingSea(structureBlockInfoWorld.pos)){
                return null;
            }
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.SKY_VIEW_PROCESSOR;
    }
}
