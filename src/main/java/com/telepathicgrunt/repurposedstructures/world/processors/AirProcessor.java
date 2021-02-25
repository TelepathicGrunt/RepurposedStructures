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
 * FOR ELEMENTS USING legacy_single_pool_element AND WANTS AIR TO REPLACE TERRAIN.
 */
public class AirProcessor extends StructureProcessor {

    public static final AirProcessor INSTANCE = new AirProcessor();
    public static final Codec<AirProcessor> CODEC = Codec.unit(() -> INSTANCE);
    private AirProcessor() { }

    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfoRelative, Structure.StructureBlockInfo structureBlockInfo2Global, StructurePlacementData structurePlacementData) {
        if (structureBlockInfo2Global.state.isOf(Blocks.AIR)) {
            worldView.getChunk(structureBlockInfo2Global.pos).setBlockState(structureBlockInfo2Global.pos, Blocks.AIR.getDefaultState(), false);
        }
        return structureBlockInfo2Global;
    }

    protected StructureProcessorType<?> getType() {
        return RSProcessors.AIR_PROCESSORS;
    }
}
