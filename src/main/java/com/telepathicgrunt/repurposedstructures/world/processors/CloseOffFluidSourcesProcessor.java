package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.SectionPos;
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
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
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
public class CloseOffFluidSourcesProcessor extends StructureProcessor {

    public static final Codec<CloseOffFluidSourcesProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.mapPair(Registry.BLOCK.byNameCodec().fieldOf("block"), Codec.intRange(1, Integer.MAX_VALUE).fieldOf("weight"))
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
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader, BlockPos pos, BlockPos pos2, StructureTemplate.StructureBlockInfo infoIn1, StructureTemplate.StructureBlockInfo infoIn2, StructurePlaceSettings settings) {

        ChunkPos currentChunkPos = new ChunkPos(infoIn2.pos);
        if(infoIn2.state.is(Blocks.STRUCTURE_VOID) || !infoIn2.state.getFluidState().isEmpty()) {
            return infoIn2;
        }

        if(levelReader instanceof WorldGenRegion worldGenRegion && !worldGenRegion.getCenter().equals(currentChunkPos)) {
            return infoIn2;
        }

        if(!GeneralUtils.isFullCube(levelReader, infoIn2.pos, infoIn2.state) || !infoIn2.state.getMaterial().blocksMotion()) {
            ChunkAccess currentChunk = levelReader.getChunk(currentChunkPos.x, currentChunkPos.z);

            if(ifAirInWorld && !currentChunk.getBlockState(infoIn2.pos).isAir()) return infoIn2;

            // Remove fluid sources in adjacent horizontal blocks across chunk boundaries and above as well
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
            for (Direction direction : Direction.values()) {
                if(ignoreDown && direction == Direction.DOWN) continue;

                mutable.set(infoIn2.pos).move(direction);
                if (mutable.getY() < currentChunk.getMinBuildHeight() || mutable.getY() >= currentChunk.getMaxBuildHeight()) {
                    continue;
                }

                if (currentChunkPos.x != mutable.getX() >> 4 || currentChunkPos.z != mutable.getZ() >> 4) {
                    currentChunk = levelReader.getChunk(mutable);
                    currentChunkPos = new ChunkPos(mutable);
                }

                // Copy what vanilla ores do.
                // This bypasses the PaletteContainer's lock as it was throwing `Accessing PalettedContainer from multiple threads` crash
                // even though everything seemed to be safe and fine.
                LevelHeightAccessor levelHeightAccessor = currentChunk.getHeightAccessorForGeneration();
                if(levelReader instanceof WorldGenLevel && mutable.getY() >= levelHeightAccessor.getMinBuildHeight() && mutable.getY() < levelHeightAccessor.getMaxBuildHeight()) {
                    int sectionYIndex = currentChunk.getSectionIndex(mutable.getY());
                    LevelChunkSection levelChunkSection = currentChunk.getSection(sectionYIndex);
                    if (levelChunkSection == null) continue;

                    FluidState fluidState = levelChunkSection.getFluidState(
                            SectionPos.sectionRelative(mutable.getX()),
                            SectionPos.sectionRelative(mutable.getY()),
                            SectionPos.sectionRelative(mutable.getZ()));

                    if (fluidState.isSource()) {
                        RandomSource random = new WorldgenRandom(new LegacyRandomSource(0L));
                        random.setSeed(mutable.asLong() * mutable.getY());

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

        return infoIn2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.CLOSE_OFF_FLUID_SOURCES_PROCESSOR;
    }
}
