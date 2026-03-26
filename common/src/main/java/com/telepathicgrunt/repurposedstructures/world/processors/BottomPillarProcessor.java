package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.Optional;

public class BottomPillarProcessor extends StructureProcessor {
    private static final Identifier EMPTY_RL = Identifier.fromNamespaceAndPath("minecraft", "empty");

    public static final MapCodec<BottomPillarProcessor> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            Identifier.CODEC.optionalFieldOf("pillar_processor_list", EMPTY_RL).forGetter(processor -> processor.processorList),
            Codec.INT.optionalFieldOf("pillar_length", 1000).forGetter(config -> config.pillarLength),
            Codec.BOOL.optionalFieldOf("forced_placement", false).forGetter(config -> config.forcePlacement))
    .apply(instance, instance.stable(BottomPillarProcessor::new)));

    public final Identifier processorList;
    public final int pillarLength;
    public final boolean forcePlacement;

    private BottomPillarProcessor(Identifier processorList, int pillarLength, boolean forcePlacement)
    {
        this.processorList = processorList;
        this.pillarLength = pillarLength;
        this.forcePlacement = forcePlacement;
    }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader, BlockPos templateOffset, BlockPos worldOffset, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {

        BlockState blockState = structureBlockInfoWorld.state();
        if (structureBlockInfoLocal.pos().getY() == 0) {
            BlockPos worldPos = structureBlockInfoWorld.pos();

            BlockState replacementState = blockState;
            BlockState originalReplacementState = blockState;
            BlockPos.MutableBlockPos currentPos = new BlockPos.MutableBlockPos().set(worldPos);
            Optional<Holder.Reference<StructureProcessorList>> structureProcessorList = Optional.empty();
            if(processorList != null && !processorList.equals(EMPTY_RL)) {
                structureProcessorList = levelReader.registryAccess().lookupOrThrow(Registries.PROCESSOR_LIST).get(processorList);
            }

            if(levelReader instanceof WorldGenRegion worldGenRegion && !worldGenRegion.getCenter().equals(ChunkPos.containing(currentPos))) {
                return structureBlockInfoWorld;
            }

            int terrainY = Integer.MIN_VALUE;
            if(!forcePlacement) {
                terrainY = GeneralUtils.getFirstLandYFromPos(levelReader, worldPos);
                if(terrainY <= levelReader.getMinY() && pillarLength + 2 >= worldPos.getY() - levelReader.getMinY()) {
                    return structureBlockInfoWorld;
                }
            }

            // Creates the pillars in the world that replaces air and liquids
            ChunkAccess chunkAccess = levelReader.getChunk(worldPos);
            currentPos.move(Direction.DOWN);
            BlockState currentBlock = chunkAccess.getBlockState(currentPos);
            boolean currentLayer = isSpotValidForReplacement(levelReader, currentBlock, currentPos, terrainY, worldPos);
            currentPos.move(Direction.DOWN);
            BlockState secondBottomBlock = chunkAccess.getBlockState(currentPos);
            boolean skipInitialLayer = isSpotValidForReplacement(levelReader, secondBottomBlock, currentPos, terrainY, worldPos);
            currentPos.move(Direction.UP);
            while (skipInitialLayer || currentLayer) {
                skipInitialLayer = false;

                StructureTemplate.StructureBlockInfo newPillarState1 = new StructureTemplate.StructureBlockInfo(currentPos.subtract(worldPos).offset(templateOffset), replacementState, null);
                StructureTemplate.StructureBlockInfo newPillarState2 = new StructureTemplate.StructureBlockInfo(currentPos.immutable(), replacementState, null);

                if(structureProcessorList.isPresent()) {
                    for(StructureProcessor processor : structureProcessorList.get().value().list()) {
                        if(newPillarState2 == null) {
                            break;
                        }
                        newPillarState2 = processor.processBlock(levelReader, newPillarState1.pos(), newPillarState2.pos(), newPillarState1, newPillarState2, structurePlacementData);
                    }
                }

                if(newPillarState2 != null) {
                    chunkAccess.setBlockState(currentPos, newPillarState2.state(), Block.UPDATE_CLIENTS);
                }

                currentPos.move(Direction.DOWN);
                currentBlock = chunkAccess.getBlockState(currentPos);
                currentLayer = isSpotValidForReplacement(levelReader, currentBlock, currentPos, terrainY, worldPos);
            }
        }

        return structureBlockInfoWorld;
    }

    private boolean isSpotValidForReplacement(LevelReader levelReader, BlockState currentBlock, BlockPos.MutableBlockPos currentPos, int terrainY, BlockPos worldPos) {
        return ((forcePlacement && currentBlock.getBlock().defaultDestroyTime() >= 0) || !currentBlock.canOcclude()) &&
                !levelReader.isOutsideBuildHeight(currentPos.getY()) &&
                currentPos.closerThan(worldPos, pillarLength);
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.BOTTOM_PILLAR_PROCESSOR.get();
    }
}
