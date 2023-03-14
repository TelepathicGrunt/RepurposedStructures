package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.material.Fluids;

/**
 * FOR ELEMENTS USING legacy_single_pool_element AND WANTS AIR TO REPLACE TERRAIN.
 */
public class FloodWithWaterProcessor extends StructureProcessor {

    public static final Codec<FloodWithWaterProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.INT.fieldOf("flood_level").forGetter(config -> config.floodLevel)
    ).apply(instance, instance.stable(FloodWithWaterProcessor::new)));

    private final int floodLevel;

    private FloodWithWaterProcessor(int floodLevel) {
        this.floodLevel = floodLevel;
    }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {
        if(structureBlockInfoWorld.state.getFluidState().is(FluidTags.WATER)) {
            tickWaterFluid(levelReader, structureBlockInfoWorld);
            return structureBlockInfoWorld;
        }

        if(levelReader instanceof WorldGenRegion worldGenRegion && !worldGenRegion.getCenter().equals(new ChunkPos(structureBlockInfoWorld.pos))) {
            return structureBlockInfoWorld;
        }

        if (structureBlockInfoWorld.pos.getY() <= floodLevel) {
            boolean flooded = false;
            if(structureBlockInfoWorld.state.isAir() || structureBlockInfoWorld.state.is(BlockTags.FLOWER_POTS) || structureBlockInfoWorld.state.is(BlockTags.BUTTONS)) {
                structureBlockInfoWorld = new StructureTemplate.StructureBlockInfo(structureBlockInfoWorld.pos, Blocks.WATER.defaultBlockState(), null);
                tickWaterFluid(levelReader, structureBlockInfoWorld);
                flooded = true;
            }
            else if(structureBlockInfoWorld.state.hasProperty(BlockStateProperties.WATERLOGGED)) {
                structureBlockInfoWorld = new StructureTemplate.StructureBlockInfo(structureBlockInfoWorld.pos, structureBlockInfoWorld.state.setValue(BlockStateProperties.WATERLOGGED, true), structureBlockInfoWorld.nbt);
                tickWaterFluid(levelReader, structureBlockInfoWorld);
                flooded = true;
            }
            else if(structureBlockInfoWorld.state.getBlock() instanceof BushBlock) {
                structureBlockInfoWorld = new StructureTemplate.StructureBlockInfo(structureBlockInfoWorld.pos, Blocks.WATER.defaultBlockState(), null);
                tickWaterFluid(levelReader, structureBlockInfoWorld);
                flooded = true;
            }

            if(flooded) {
                // enclose the new water block with cracked stonebrick
                ChunkPos currentChunkPos = new ChunkPos(structureBlockInfoWorld.pos);
                ChunkAccess currentChunk = levelReader.getChunk(currentChunkPos.x, currentChunkPos.z);
                BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
                for (Direction direction : Direction.values()) {
                    if(direction == Direction.UP) continue;

                    mutable.set(structureBlockInfoWorld.pos).move(direction);
                    if (currentChunkPos.x != mutable.getX() >> 4 || currentChunkPos.z != mutable.getZ() >> 4) {
                        currentChunk = levelReader.getChunk(mutable);
                        currentChunkPos = new ChunkPos(mutable);
                    }

                    BlockState neighboringBlock = currentChunk.getBlockState(mutable);
                    if (!neighboringBlock.canOcclude() && neighboringBlock.getFluidState().isEmpty()) {
                        currentChunk.setBlockState(mutable, Blocks.CRACKED_STONE_BRICKS.defaultBlockState(), false);
                    }
                }
            }
        }
        return structureBlockInfoWorld;
    }

    private void tickWaterFluid(LevelReader worldView, StructureTemplate.StructureBlockInfo structureBlockInfoWorld) {
        ((LevelAccessor)worldView).scheduleTick(structureBlockInfoWorld.pos, Fluids.WATER, 1);
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.FLOOD_WITH_WATER_PROCESSOR.get();
    }
}
