package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.material.Fluid;

import java.util.List;

/**
 * Will help enclose the structure in solid blocks rather than allow fluid source blocks to be floating.
 * Best for Ocean Structures with water marking the insides that should never be exposed to air.
 */
public class CloseOffAirSourcesProcessor implements StructureProcessor {

    public static final MapCodec<CloseOffAirSourcesProcessor> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            Codec.mapPair(BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block"), Codec.intRange(1, Integer.MAX_VALUE).fieldOf("weight"))
                    .codec().listOf().fieldOf("weighted_list_of_replacement_blocks")
                    .forGetter(processor -> processor.weightedReplacementBlocks))
            .apply(instance, instance.stable(CloseOffAirSourcesProcessor::new)));

    private final List<Pair<Block, Integer>> weightedReplacementBlocks;

    public CloseOffAirSourcesProcessor(List<Pair<Block, Integer>> weightedReplacementBlocks) {
        this.weightedReplacementBlocks = weightedReplacementBlocks;
    }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos targetPosition, BlockPos referencePos, BlockPos templateRelativePos, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {

        ChunkPos currentChunkPos = ChunkPos.containing(structureBlockInfoWorld.pos());
        if(level instanceof WorldGenRegion worldGenRegion && !worldGenRegion.getCenter().equals(currentChunkPos)) {
            return structureBlockInfoWorld;
        }

        if(!structureBlockInfoWorld.state().getFluidState().isEmpty()) {
            ChunkAccess currentChunk = level.getChunk(currentChunkPos.x(), currentChunkPos.z());
            Fluid currentFluid = structureBlockInfoWorld.state().getFluidState().getType();

            // Remove fluid sources in adjacent horizontal blocks across chunk boundaries and above as well
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
            for (Direction direction : Direction.values()) {

                mutable.set(structureBlockInfoWorld.pos()).move(direction);
                if (mutable.getY() < currentChunk.getMinY() || mutable.getY() >= currentChunk.getMaxY()) {
                    continue;
                }

                if (currentChunkPos.x() != mutable.getX() >> 4 || currentChunkPos.z() != mutable.getZ() >> 4) {
                    currentChunk = level.getChunk(mutable);
                    currentChunkPos = ChunkPos.containing(mutable);
                }

                // Copy what vanilla ores do.
                // This bypasses the PaletteContainer's lock as it was throwing `Accessing PalettedContainer from multiple threads` crash
                // even though everything seemed to be safe and fine.
                LevelHeightAccessor levelHeightAccessor = currentChunk.getHeightAccessorForGeneration();
                if(level instanceof WorldGenLevel && mutable.getY() >= levelHeightAccessor.getMinY() && mutable.getY() < levelHeightAccessor.getMaxY()) {
                    int sectionYIndex = currentChunk.getSectionIndex(mutable.getY());
                    LevelChunkSection levelChunkSection = currentChunk.getSection(sectionYIndex);
                    if (levelChunkSection == null) continue;

                    BlockState neighboringState = levelChunkSection.getBlockState(
                            SectionPos.sectionRelative(mutable.getX()),
                            SectionPos.sectionRelative(mutable.getY()),
                            SectionPos.sectionRelative(mutable.getZ()));

                    if (neighboringState.isAir() || (neighboringState.getBlock() instanceof LiquidBlock && !currentFluid.equals(neighboringState.getFluidState().getType()))) {
                        Block replacementBlock;
                        if(weightedReplacementBlocks.size() == 1) {
                            replacementBlock = weightedReplacementBlocks.get(0).getFirst();
                        }
                        else{
                            RandomSource random = structurePlacementData.getRandom(structureBlockInfoWorld.pos());
                            replacementBlock = GeneralUtils.getRandomEntry(weightedReplacementBlocks, random);
                        }

                        levelChunkSection.setBlockState(
                                SectionPos.sectionRelative(mutable.getX()),
                                SectionPos.sectionRelative(mutable.getY()),
                                SectionPos.sectionRelative(mutable.getZ()),
                                replacementBlock.defaultBlockState(),
                                false);
                    }
                }
            }
        }

        return structureBlockInfoWorld;
    }

    @Override
    public MapCodec<? extends StructureProcessor> codec() {
        return RSProcessors.CLOSE_OFF_AIR_SOURCES_PROCESSOR.get();
    }
}
