package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.Chunk;

/**
 * POOL ENTRY MUST BE USING legacy_single_pool_element OR ELSE THE STRUCTURE BLOCK IS REMOVED BEFORE THIS PROCESSOR RUNS.
 */
public class PillarChainDataBlockProcessor extends StructureProcessor {

    public static final Codec<PillarChainDataBlockProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Registry.BLOCK.fieldOf("replacement_block").forGetter(processor -> processor.replacementBlock),
            Registry.BLOCK.fieldOf("fence_block").forGetter(processor -> processor.fenceBlock),
            Registry.BLOCK.fieldOf("pillar_block").forGetter(processor -> processor.pillarBlock))
            .apply(instance, instance.stable(PillarChainDataBlockProcessor::new)));

    private final Block replacementBlock;
    private final Block fenceBlock;
    private final Block pillarBlock;

    private PillarChainDataBlockProcessor(Block replacementBlock, Block fenceBlock, Block pillarBlock) {
        this.replacementBlock = replacementBlock;
        this.fenceBlock = fenceBlock;
        this.pillarBlock = pillarBlock;
    }

    @Override
    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfoLocal, Structure.StructureBlockInfo structureBlockInfoWorld, StructurePlacementData structurePlacementData) {
        BlockState blockState = structureBlockInfoWorld.state;
        if (blockState.isOf(Blocks.STRUCTURE_BLOCK)) {
            String metadata = structureBlockInfoWorld.nbt.getString("metadata");
            Chunk chunk = worldView.getChunk(structureBlockInfoWorld.pos);

            // Pillar And Chain mode activated (generally for mineshafts)
            if(metadata.contains("pillarAndChains")) {
                if(worldView.isSkyVisibleAllowingSea(structureBlockInfoWorld.pos)){
                    return null;
                }

                BlockPos worldPos = structureBlockInfoWorld.pos;
                BlockState tempBlock;
                BlockPos.Mutable scanPos = new BlockPos.Mutable().set(worldPos);

                boolean canMakePillar = false;
                while (scanPos.getY() > chunk.getBottomY()) {
                    tempBlock = chunk.getBlockState(scanPos);

                    // Move down for every spot we can replace with pillar.
                    if (canReplace(tempBlock)) {
                        scanPos.move(Direction.DOWN);
                    }

                    // exit. Pillar cannot be made
                    else if (worldPos.getY() - scanPos.getY() > 20 || tempBlock.isOf(Blocks.LAVA) || tempBlock.isOf(Blocks.RAIL)) {
                        break;
                    }

                    // We hit a valid surface we can pillar to.
                    else {
                        canMakePillar = true;
                        break;
                    }
                }

                // Start making pillar
                if (canMakePillar) {
                    BlockPos.Mutable tempPos = new BlockPos.Mutable().set(worldPos).move(Direction.DOWN);
                    BlockState pillarBlockFinal = pillarBlock.getDefaultState();
                    while (tempPos.getY() > scanPos.getY()) {
                        if(pillarBlockFinal.contains(Properties.WATERLOGGED)){
                            pillarBlockFinal = pillarBlockFinal.with(Properties.WATERLOGGED, chunk.getBlockState(tempPos).getFluidState().isIn(FluidTags.WATER));
                        }
                        chunk.setBlockState(tempPos, pillarBlockFinal, false);
                        tempPos.move(Direction.DOWN);
                    }
                }
                else {
                    scanPos.set(worldPos);
                    if(!chunk.getBlockState(scanPos.up(3)).isOpaque()){

                        boolean canMakeChain = false;
                        while (scanPos.getY() < worldView.getTopY()) {
                            tempBlock = chunk.getBlockState(scanPos);

                            // Move up for every spot we can replace with chain.
                            if (canReplace(tempBlock)) {
                                scanPos.move(Direction.UP);
                            }

                            // exit. Chain cannot be made
                            else if (scanPos.getY() - worldPos.getY() > 50 || !Block.sideCoversSmallSquare(worldView, pos, Direction.DOWN) || (tempBlock.getBlock() instanceof FallingBlock)) {
                                break;
                            }

                            // We hit a valid surface we can chain to.
                            else {
                                canMakeChain = true;
                                break;
                            }
                        }

                        // Start making chain
                        if (canMakeChain) {
                            BlockPos.Mutable tempPos = new BlockPos.Mutable().set(worldPos).move(Direction.UP);
                            BlockState fenceBlockFinal = fenceBlock.getDefaultState();
                            if(fenceBlockFinal.contains(Properties.WATERLOGGED)){
                                fenceBlockFinal = fenceBlockFinal.with(Properties.WATERLOGGED, chunk.getBlockState(tempPos).getFluidState().isIn(FluidTags.WATER));
                            }
                            chunk.setBlockState(tempPos, fenceBlockFinal, false);
                            tempPos.move(Direction.UP);
                            while (tempPos.getY() < scanPos.getY()) {
                                chunk.setBlockState(
                                        tempPos,
                                        Blocks.CHAIN.getDefaultState()
                                                .with(Properties.WATERLOGGED,
                                                        chunk.getBlockState(tempPos).getFluidState().isIn(FluidTags.WATER)),
                                        false);
                                tempPos.move(Direction.UP);
                            }
                        }
                    }
                }

                // Replaces the data block itself
                return replacementBlock == null || replacementBlock == Blocks.STRUCTURE_VOID ? null : new Structure.StructureBlockInfo(worldPos, replacementBlock.getDefaultState(), null);
            }
        }
        return structureBlockInfoWorld;
    }

    protected boolean canReplace(BlockState state) {
        return state.isAir() ||
                state.getMaterial().isLiquid() ||
                state.isOf(Blocks.GLOW_LICHEN) ||
                state.isOf(Blocks.SEAGRASS) ||
                state.isOf(Blocks.TALL_SEAGRASS);
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.PILLAR_CHAIN_DATA_BLOCK_PROCESSOR;
    }
}
