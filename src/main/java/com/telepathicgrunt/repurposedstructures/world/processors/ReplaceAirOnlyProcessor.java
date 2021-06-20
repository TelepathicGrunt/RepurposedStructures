package com.telepathicgrunt.repurposedstructures.world.processors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.BlockState;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

import java.util.HashSet;

/**
 * For mimicking the dungeon look where they cannot replace air.
 */
public class ReplaceAirOnlyProcessor extends StructureProcessor {

    public static final Codec<ReplaceAirOnlyProcessor> CODEC  = RecordCodecBuilder.create((instance) -> instance.group(
            BlockState.CODEC.listOf()
                    .xmap(Sets::newHashSet, Lists::newArrayList)
                    .optionalFieldOf("blocks_to_always_place", new HashSet<>())
                    .forGetter((config) -> config.blocksToAlwaysPlace))
            .apply(instance, instance.stable(ReplaceAirOnlyProcessor::new)));

    public final HashSet<BlockState> blocksToAlwaysPlace;
    private ReplaceAirOnlyProcessor(HashSet<BlockState> blocksToAlwaysPlace) {
        this.blocksToAlwaysPlace = blocksToAlwaysPlace;
    }

    @Override
    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfoLocal, Structure.StructureBlockInfo structureBlockInfoWorld, StructurePlacementData structurePlacementData) {

        if(!blocksToAlwaysPlace.contains(structureBlockInfoWorld.state)){
            BlockPos position = structureBlockInfoWorld.pos;
            BlockState worldState = worldView.getBlockState(position);
            BlockState aboveWorldState = worldView.getBlockState(position.up());

            if (worldState.isAir() &&
                !structureBlockInfoWorld.state.hasBlockEntity() &&
                !aboveWorldState.hasBlockEntity())
            {
                structureBlockInfoWorld = new Structure.StructureBlockInfo(structureBlockInfoWorld.pos, worldState, null);
            }
            // Do not replace other dungeon's chests/spawners
            else if(worldState.hasBlockEntity()){
                structureBlockInfoWorld = new Structure.StructureBlockInfo(structureBlockInfoWorld.pos, worldState, null);
            }
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.REPLACE_AIR_ONLY_PROCESSOR;
    }
}
