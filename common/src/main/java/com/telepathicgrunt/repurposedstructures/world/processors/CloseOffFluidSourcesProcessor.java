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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.material.FluidState;

import java.util.List;

/**
 * Will help enclose the structure in solid blocks rather than allow fluid source blocks to be floating.
 * Best for Nether Structures with Cave Air marking the insides that should never be exposed to lava.
 */
public class CloseOffFluidSourcesProcessor implements StructureProcessor {

    public static final MapCodec<CloseOffFluidSourcesProcessor> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            Codec.mapPair(BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block"), Codec.intRange(1, Integer.MAX_VALUE).fieldOf("weight"))
                    .codec().listOf().fieldOf("weighted_list_of_replacement_blocks")
                    .forGetter(processor -> processor.weightedReplacementBlocks),
            Codec.BOOL.fieldOf("ignore_down").orElse(false).forGetter(processor -> processor.ignoreDown),
            Codec.BOOL.fieldOf("if_air_in_world").orElse(false).forGetter(processor -> processor.ifAirInWorld)
    ).apply(instance, instance.stable(CloseOffFluidSourcesProcessor::new)));

    private final List<Pair<Block, Integer>> weightedReplacementBlocks;
    private final boolean ignoreDown;
    private final boolean ifAirInWorld;

    public CloseOffFluidSourcesProcessor(List<Pair<Block, Integer>> weightedReplacementBlocks, boolean ignoreDown, boolean ifAirInWorld) {
        this.weightedReplacementBlocks = weightedReplacementBlocks;
        this.ignoreDown = ignoreDown;
        this.ifAirInWorld = ifAirInWorld;
    }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos targetPosition, BlockPos referencePos, BlockPos templateRelativePos, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {

        ChunkPos currentChunkPos = ChunkPos.containing(structureBlockInfoWorld.pos());
        if(structureBlockInfoWorld.state().is(Blocks.STRUCTURE_VOID) || !structureBlockInfoWorld.state().getFluidState().isEmpty()) {
            return structureBlockInfoWorld;
        }

        if(level instanceof WorldGenRegion worldGenRegion && !worldGenRegion.getCenter().equals(currentChunkPos)) {
            return structureBlockInfoWorld;
        }

        if(!GeneralUtils.isFullCube(structureBlockInfoWorld.state()) || !structureBlockInfoWorld.state().blocksMotion()) {
            ChunkAccess currentChunk = level.getChunk(currentChunkPos.x(), currentChunkPos.z());

            if(ifAirInWorld && !currentChunk.getBlockState(structureBlockInfoWorld.pos()).isAir()) return structureBlockInfoWorld;

            // Remove fluid sources in adjacent horizontal blocks across chunk boundaries and above as well
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
            for (Direction direction : Direction.values()) {
                if(ignoreDown && direction == Direction.DOWN) continue;

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

                    FluidState fluidState = levelChunkSection.getFluidState(
                            SectionPos.sectionRelative(mutable.getX()),
                            SectionPos.sectionRelative(mutable.getY()),
                            SectionPos.sectionRelative(mutable.getZ()));

                    if (fluidState.isSource()) {
                        RandomSource random = structurePlacementData.getRandom(structureBlockInfoWorld.pos());
                        Block replacementBlock = GeneralUtils.getRandomEntry(weightedReplacementBlocks, random);
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
        return RSProcessors.CLOSE_OFF_FLUID_SOURCES_PROCESSOR.get();
    }
}
