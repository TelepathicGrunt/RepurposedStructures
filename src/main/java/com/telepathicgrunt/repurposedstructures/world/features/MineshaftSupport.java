package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.MineshaftSupportConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.material.Material;


public class MineshaftSupport extends Feature<MineshaftSupportConfig> {

    public MineshaftSupport(Codec<MineshaftSupportConfig> config) {
        super(config);
    }

    @Override
    public boolean place(FeaturePlaceContext<MineshaftSupportConfig> context) {

        // start at jigsaw block pos
        BlockPos jigsawPos = context.origin().below();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(jigsawPos);
        WorldGenLevel world = context.level();
        ChunkAccess chunk = world.getChunk(mutable);
        BlockState removalState = context.config().waterBased ? Blocks.WATER.defaultBlockState() : Blocks.CAVE_AIR.defaultBlockState();

        // Repair arch if we can at this spot (doesn't repair all arches. That requires a redesign of these jigsaw mineshafts)
        if (context.config().archBlocks.contains(world.getBlockState(mutable.above(3)).getBlock())) {
            for(int i = 0; i <= 1; i++) {
                if(chunk.getBlockState(mutable.move(Direction.UP)).getBlock() != context.config().fenceState.getBlock()) {
                    StructurePostProcessConnectiveBlocks.placeConnectBlock(context, mutable, chunk.getPos(), chunk, context.config().fenceState);
                    blockOffAirIfWaterBased(context, mutable, world);
                }
            }
            return true;
        }
        else {
            for(int i = 0; i <= 2; i++) {
                BlockState checkArchState = chunk.getBlockState(mutable.move(Direction.UP));
                if(i < 2 ? checkArchState.getBlock() == context.config().fenceState.getBlock() : context.config().archBlocks.contains(checkArchState.getBlock())) {
                    chunk.setBlockState(mutable, removalState, false);
                    blockOffAirIfWaterBased(context, mutable, world);
                }
            }
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                mutable.move(direction);
                if(new ChunkPos(mutable).equals(chunk.getPos())) {
                    BlockState checkArchState = chunk.getBlockState(mutable);
                    if(context.config().archBlocks.contains(checkArchState.getBlock())) {
                        chunk.setBlockState(mutable, removalState, false);
                        blockOffAirIfWaterBased(context, mutable, world);
                    }
                }
                mutable.move(direction.getOpposite());
            }
        }

        if(context.config().archOnly) {
            return true;
        }

        // Only do support if floor block is placed
        mutable.set(jigsawPos);
        if(world.getBlockState(mutable).is(context.config().targetFloorState)) {
            if (world.canSeeSkyFromBelowWater(mutable.above())) {
                return false;
            }

            BlockState tempBlock;
            boolean canMakePillar = false;
            mutable.move(Direction.DOWN);
            while (mutable.getY() > chunk.getMinBuildHeight()) {
                tempBlock = chunk.getBlockState(mutable);

                // Move down for every spot we can replace with pillar.
                if (canReplace(tempBlock)) {
                    mutable.move(Direction.DOWN);
                }

                // exit. Pillar cannot be made
                else if (jigsawPos.getY() - mutable.getY() > 20 || tempBlock.is(Blocks.LAVA) || tempBlock.is(Blocks.RAIL)) {
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
                BlockPos.MutableBlockPos pillarPos = new BlockPos.MutableBlockPos().set(jigsawPos).move(Direction.DOWN);
                BlockState pillarBlockFinal = context.config().pillarState;
                while (pillarPos.getY() > mutable.getY()) {
                    if (pillarBlockFinal.hasProperty(BlockStateProperties.WATERLOGGED)) {
                        pillarBlockFinal = pillarBlockFinal.setValue(BlockStateProperties.WATERLOGGED, chunk.getBlockState(pillarPos).getFluidState().is(FluidTags.WATER));
                    }
                    chunk.setBlockState(pillarPos, pillarBlockFinal, false);
                    pillarPos.move(Direction.DOWN);
                }
            }
            else {
                mutable.set(jigsawPos);
                if (!chunk.getBlockState(mutable.above(context.config().waterBased ? 4 : 3)).canOcclude()) {

                    boolean canMakeChain = false;
                    mutable.move(Direction.UP);
                    while (mutable.getY() < world.getMaxBuildHeight()) {
                        tempBlock = chunk.getBlockState(mutable);

                        // Move up for every spot we can replace with chain.
                        if (canReplace(tempBlock)) {
                            mutable.move(Direction.UP);
                        }

                        // exit. Chain cannot be made
                        else if (mutable.getY() - jigsawPos.getY() > 50 || !Block.canSupportCenter(world, mutable, Direction.DOWN) || (tempBlock.getBlock() instanceof FallingBlock)) {
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
                        BlockPos.MutableBlockPos chainPos = new BlockPos.MutableBlockPos().set(jigsawPos).move(Direction.UP);
                        BlockState fenceBlockFinal = context.config().fenceState;
                        if (fenceBlockFinal.hasProperty(BlockStateProperties.WATERLOGGED)) {
                            fenceBlockFinal = fenceBlockFinal.setValue(BlockStateProperties.WATERLOGGED, chunk.getBlockState(chainPos).getFluidState().is(FluidTags.WATER));
                        }
                        chunk.setBlockState(chainPos, fenceBlockFinal, false);
                        chainPos.move(Direction.UP);
                        while (chainPos.getY() < mutable.getY()) {
                            chunk.setBlockState(
                                    chainPos,
                                    Blocks.CHAIN.defaultBlockState()
                                            .setValue(BlockStateProperties.WATERLOGGED,
                                                    chunk.getBlockState(chainPos).getFluidState().is(FluidTags.WATER)),
                                    false);
                            chainPos.move(Direction.UP);
                        }
                    }
                }
            }
        }

        return true;
    }

    private void blockOffAirIfWaterBased(FeaturePlaceContext<MineshaftSupportConfig> context, BlockPos.MutableBlockPos mutable, WorldGenLevel world) {
        if (context.config().waterBased) {
            for(Direction direction : Direction.values()) {
                mutable.move(direction);
                if(world.getBlockState(mutable).isAir()) {
                    world.setBlock(mutable, context.config().targetFloorState.defaultBlockState(), 3);
                }
                mutable.move(direction.getOpposite());
            }
        }
    }

    protected boolean canReplace(BlockState state) {
        return state.isAir() ||
                (state.getMaterial().isLiquid() && !state.getFluidState().is(FluidTags.LAVA)) ||
                state.getMaterial().equals(Material.REPLACEABLE_PLANT) ||
                state.getMaterial().equals(Material.REPLACEABLE_WATER_PLANT) ||
                state.getMaterial().equals(Material.REPLACEABLE_FIREPROOF_PLANT) ||
                state.is(Blocks.COBWEB);
    }
}