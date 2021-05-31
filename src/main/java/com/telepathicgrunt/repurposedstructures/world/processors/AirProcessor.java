package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.Block;
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
public class AirProcessor extends StructureProcessor {

    public static final Codec<AirProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Registry.BLOCK.listOf().fieldOf("ignore_block").orElse(new ArrayList<>()).xmap(HashSet::new, ArrayList::new).forGetter(config -> config.blocksToIgnore)
    ).apply(instance, instance.stable(AirProcessor::new)));

    private final HashSet<Block> blocksToIgnore;

    private AirProcessor(HashSet<Block> blocksToIgnore) {
        this.blocksToIgnore = blocksToIgnore;
    }

    @Override
    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfoLocal, Structure.StructureBlockInfo structureBlockInfoWorld, StructurePlacementData structurePlacementData) {
        if (structureBlockInfoWorld.state.isAir()) {
            Chunk chunk = worldView.getChunk(structureBlockInfoWorld.pos);
            if(!blocksToIgnore.contains(chunk.getBlockState(structureBlockInfoWorld.pos).getBlock())){
                chunk.setBlockState(structureBlockInfoWorld.pos, structureBlockInfoWorld.state, false);
            }
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.AIR_PROCESSOR;
    }
}
