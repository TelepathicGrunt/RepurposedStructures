package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PillarProcessor extends StructureProcessor {
    private static final ResourceLocation EMPTY_RL = new ResourceLocation("minecraft", "empty");

    public static final Codec<PillarProcessor> CODEC  = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.mapPair(BlockState.CODEC.fieldOf("trigger"), BlockState.CODEC.fieldOf("replacement"))
                    .codec().listOf()
                    .xmap((list) -> list.stream().collect(Collectors.toMap(Pair::getFirst, Pair::getSecond)),
                            (map) -> map.entrySet().stream().map((entry) -> Pair.of(entry.getKey(), entry.getValue())).collect(Collectors.toList()))
                    .fieldOf("pillar_trigger_and_replacements")
                    .forGetter((processor) -> processor.pillarTriggerAndReplacementBlocks),
            ResourceLocation.CODEC.optionalFieldOf("pillar_processor_list", EMPTY_RL).forGetter(processor -> processor.processorList),
            Direction.CODEC.optionalFieldOf("direction", Direction.DOWN).forGetter(processor -> processor.direction),
            BlockState.CODEC.optionalFieldOf("original_replaced_block").forGetter(processor -> processor.originalReplacedBlock),
            Codec.INT.optionalFieldOf("pillar_length", 1000).forGetter(config -> config.pillarLength),
            Codec.BOOL.optionalFieldOf("forced_placement", false).forGetter(config -> config.forcePlacement))
    .apply(instance, instance.stable(PillarProcessor::new)));

    public final Map<BlockState, BlockState> pillarTriggerAndReplacementBlocks;
    public final Optional<BlockState> originalReplacedBlock;
    public final ResourceLocation processorList;
    public final Direction direction;
    public final int pillarLength;
    public final boolean forcePlacement;

    private PillarProcessor(Map<BlockState, BlockState> pillarTriggerAndReplacementBlocks,
                            ResourceLocation processorList,
                            Direction direction,
                            Optional<BlockState> originalReplacedBlock,
                            int pillarLength,
                            boolean forcePlacement)
    {
        this.pillarTriggerAndReplacementBlocks = pillarTriggerAndReplacementBlocks;
        this.processorList = processorList;
        this.direction = direction;
        this.originalReplacedBlock = originalReplacedBlock;
        this.pillarLength = pillarLength;
        this.forcePlacement = forcePlacement;
    }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader, BlockPos templateOffset, BlockPos worldOffset, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {

        BlockState blockState = structureBlockInfoWorld.state();
        if (pillarTriggerAndReplacementBlocks.containsKey(blockState)) {
            BlockPos worldPos = structureBlockInfoWorld.pos();

            BlockState replacementState = pillarTriggerAndReplacementBlocks.get(blockState);
            BlockState originalReplacementState = originalReplacedBlock.orElse(replacementState);
            BlockPos.MutableBlockPos currentPos = new BlockPos.MutableBlockPos().set(worldPos);
            StructureProcessorList structureProcessorList = null;
            if(processorList != null && !processorList.equals(EMPTY_RL)) {
                structureProcessorList = levelReader.registryAccess().registryOrThrow(Registries.PROCESSOR_LIST).get(processorList);
            }

            if(levelReader instanceof WorldGenRegion worldGenRegion && !worldGenRegion.getCenter().equals(new ChunkPos(currentPos))) {
                return getReturnBlock(worldPos, originalReplacementState);
            }

            int terrainY = Integer.MIN_VALUE;
            if(direction == Direction.DOWN && !forcePlacement) {
                terrainY = GeneralUtils.getFirstLandYFromPos(levelReader, worldPos);
                if(terrainY <= levelReader.getMinBuildHeight() && pillarLength + 2 >= worldPos.getY() - levelReader.getMinBuildHeight()) {
                    // Replaces the data block itself
                    return getReturnBlock(worldPos, originalReplacementState);
                }
            }

            // Creates the pillars in the world that replaces air and liquids
            BlockState currentBlock = levelReader.getBlockState(worldPos.below());
            while(((forcePlacement && currentBlock.getBlock().defaultDestroyTime() >= 0) || !currentBlock.canOcclude()) &&
                (forcePlacement || currentPos.getY() >= terrainY) &&
                !levelReader.isOutsideBuildHeight(currentPos.getY()) &&
                currentPos.closerThan(worldPos, pillarLength)
            ) {
                StructureTemplate.StructureBlockInfo newPillarState1 = new StructureTemplate.StructureBlockInfo(currentPos.subtract(worldPos).offset(templateOffset), replacementState, null);
                StructureTemplate.StructureBlockInfo newPillarState2 = new StructureTemplate.StructureBlockInfo(currentPos.immutable(), replacementState, null);

                if(structureProcessorList != null) {
                    for(StructureProcessor processor : structureProcessorList.list()) {
                        if(newPillarState2 == null) {
                            break;
                        }
                        newPillarState2 = processor.processBlock(levelReader, newPillarState1.pos(), newPillarState2.pos(), newPillarState1, newPillarState2, structurePlacementData);
                    }
                }

                if(newPillarState2 != null) {
                    levelReader.getChunk(currentPos).setBlockState(currentPos, newPillarState2.state(), false);
                }

                currentPos.move(direction);
                currentBlock = levelReader.getBlockState(currentPos);
            }

            // Replaces the data block itself
            return getReturnBlock(worldPos, originalReplacementState);
        }

        return structureBlockInfoWorld;
    }

    private static StructureTemplate.StructureBlockInfo getReturnBlock(BlockPos worldPos, BlockState originalReplacementState) {
        return originalReplacementState == null || originalReplacementState.is(Blocks.STRUCTURE_VOID) ?
                null : new StructureTemplate.StructureBlockInfo(worldPos, originalReplacementState, null);
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.PILLAR_PROCESSOR.get();
    }
}
