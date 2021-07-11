package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.Blocks;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

/**
 * FOR ELEMENTS THAT CANNOT SPAWN IN VIEW OF THE SKY
 */
public class MineshaftSkyViewProcessor extends StructureProcessor {

    public static final Codec<MineshaftSkyViewProcessor> CODEC = Codec.unit(MineshaftSkyViewProcessor::new);

    private MineshaftSkyViewProcessor() {

    }

    @Override
    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfoLocal, Structure.StructureBlockInfo structureBlockInfoWorld, StructurePlacementData structurePlacementData) {

        // Mimic Mineshaft rails visible even in sky if block below is solid
        if(structureBlockInfoWorld.state.isOf(Blocks.RAIL) && worldView.getBlockState(structureBlockInfoWorld.pos.down()).isOpaque()){
            return structureBlockInfoWorld;
        }

        // Mimic Mineshaft air still carving even when visible to sky
        if (!structureBlockInfoWorld.state.isOf(Blocks.CAVE_AIR)) {
            if(worldView.isSkyVisibleAllowingSea(structureBlockInfoWorld.pos)){
                return new Structure.StructureBlockInfo(structureBlockInfoWorld.pos, Blocks.CAVE_AIR.getDefaultState(), null);
            }
        }

        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.MINESHAFT_SKY_VIEW_PROCESSOR;
    }
}
