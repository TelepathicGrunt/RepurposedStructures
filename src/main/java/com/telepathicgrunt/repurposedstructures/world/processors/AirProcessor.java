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

    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfoLocal, Structure.StructureBlockInfo structureBlockInfoWorld, StructurePlacementData structurePlacementData) {
        if (structureBlockInfoWorld.state.isOf(Blocks.AIR)) {
            worldView.getChunk(structureBlockInfoWorld.pos).setBlockState(structureBlockInfoWorld.pos, Blocks.AIR.getDefaultState(), false);
        }
        return structureBlockInfoWorld;
    }

    protected StructureProcessorType<?> getType() {
        return RSProcessors.AIR_PROCESSORS;
    }
}
