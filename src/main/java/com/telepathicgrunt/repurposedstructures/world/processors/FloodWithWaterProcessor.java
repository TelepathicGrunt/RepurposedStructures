package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

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
    public Template.BlockInfo processBlock(IWorldReader worldView, BlockPos pos, BlockPos blockPos, Template.BlockInfo structureBlockInfoLocal, Template.BlockInfo structureBlockInfoWorld, PlacementSettings structurePlacementData) {
        if(structureBlockInfoWorld.state.getFluidState().is(FluidTags.WATER)){
            tickWaterFluid(worldView, structureBlockInfoWorld);
            return structureBlockInfoWorld;
        }

        if (structureBlockInfoWorld.pos.getY() <= floodLevel) {
            boolean flooded = false;
            if(structureBlockInfoWorld.state.isAir()){
                structureBlockInfoWorld = new Template.BlockInfo(structureBlockInfoWorld.pos, Blocks.WATER.defaultBlockState(), null);
                tickWaterFluid(worldView, structureBlockInfoWorld);
                flooded = true;
            }
            else if(structureBlockInfoWorld.state.hasProperty(BlockStateProperties.WATERLOGGED)){
                structureBlockInfoWorld = new Template.BlockInfo(structureBlockInfoWorld.pos, structureBlockInfoWorld.state.setValue(BlockStateProperties.WATERLOGGED, true), structureBlockInfoWorld.nbt);
                tickWaterFluid(worldView, structureBlockInfoWorld);
                flooded = true;
            }
            else if(structureBlockInfoWorld.state.getBlock() instanceof BushBlock){
                structureBlockInfoWorld = new Template.BlockInfo(structureBlockInfoWorld.pos, Blocks.WATER.defaultBlockState(), null);
                tickWaterFluid(worldView, structureBlockInfoWorld);
                flooded = true;
            }

            if(flooded){
                // enclose the new water block with cracked stonebrick
                ChunkPos currentChunkPos = new ChunkPos(structureBlockInfoWorld.pos);
                IChunk currentChunk = worldView.getChunk(currentChunkPos.x, currentChunkPos.z);
                BlockPos.Mutable mutable = new BlockPos.Mutable();
                for (Direction direction : Direction.values()) {
                    if(direction == Direction.UP) continue;

                    mutable.set(structureBlockInfoWorld.pos).move(direction);
                    if (currentChunkPos.x != mutable.getX() >> 4 || currentChunkPos.z != mutable.getZ() >> 4) {
                        currentChunk = worldView.getChunk(mutable);
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

    private void tickWaterFluid(IWorldReader worldView, Template.BlockInfo structureBlockInfoWorld) {
        IChunk currentChunk = worldView.getChunk(structureBlockInfoWorld.pos);
        currentChunk.getLiquidTicks().scheduleTick(structureBlockInfoWorld.pos, Fluids.WATER, 1);
    }

    @Override
    protected IStructureProcessorType<?> getType() {
        return RSProcessors.FLOOD_WITH_WATER_PROCESSOR;
    }
}
