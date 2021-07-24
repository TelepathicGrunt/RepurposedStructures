package com.telepathicgrunt.repurposedstructures.world.processors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

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
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader worldView, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {

        if(!blocksToAlwaysPlace.contains(structureBlockInfoWorld.state)){
            BlockPos position = structureBlockInfoWorld.pos;
            BlockState worldState = worldView.getBlockState(position);
            BlockState aboveWorldState = worldView.getBlockState(position.above());

            if (worldState.isAir() &&
                !structureBlockInfoWorld.state.hasBlockEntity() &&
                !aboveWorldState.hasBlockEntity())
            {
                structureBlockInfoWorld = new StructureTemplate.StructureBlockInfo(structureBlockInfoWorld.pos, worldState, null);
            }
            // Do not replace other dungeon's chests/spawners
            else if(worldState.hasBlockEntity()){
                structureBlockInfoWorld = new StructureTemplate.StructureBlockInfo(structureBlockInfoWorld.pos, worldState, null);
            }
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.REPLACE_AIR_ONLY_PROCESSOR;
    }
}
