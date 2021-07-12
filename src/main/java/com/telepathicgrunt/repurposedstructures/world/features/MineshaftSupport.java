package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.features.configs.MineshaftSupportConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.Material;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;


public class MineshaftSupport extends Feature<MineshaftSupportConfig> {

    public MineshaftSupport(Codec<MineshaftSupportConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(FeatureContext<MineshaftSupportConfig> context) {

        // start at jigsaw block pos
        BlockPos jigsawPos = context.getOrigin().down();
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(jigsawPos);
        StructureWorldAccess world = context.getWorld();
        Chunk chunk = world.getChunk(mutable);

        // Repair arch if we can at this spot (doesn't repair all arches. That requires a redesign of these jigsaw mineshafts)
        if (chunk.getBlockState(mutable.up(3)).isOpaque()) {
            for(int  i = 0; i <= 1; i++){
                if(!chunk.getBlockState(mutable.move(Direction.UP)).getMaterial().blocksMovement()){
                    chunk.setBlockState(mutable, context.getConfig().fenceState, false);
                }
            }
        }

        // Only do support if floor block is placed
        if(world.getBlockState(mutable).isOf(context.getConfig().targetFloorState)) {
            if (world.isSkyVisibleAllowingSea(mutable.up())) {
                return false;
            }

            BlockState tempBlock;
            boolean canMakePillar = false;
            mutable.move(Direction.DOWN);
            while (mutable.getY() > chunk.getBottomY()) {
                tempBlock = chunk.getBlockState(mutable);

                // Move down for every spot we can replace with pillar.
                if (canReplace(tempBlock)) {
                    mutable.move(Direction.DOWN);
                }

                // exit. Pillar cannot be made
                else if (jigsawPos.getY() - mutable.getY() > 20 || tempBlock.isOf(Blocks.LAVA) || tempBlock.isOf(Blocks.RAIL)) {
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
                BlockPos.Mutable pillarPos = new BlockPos.Mutable().set(jigsawPos).move(Direction.DOWN);
                BlockState pillarBlockFinal = context.getConfig().pillarState;
                while (pillarPos.getY() > mutable.getY()) {
                    if (pillarBlockFinal.contains(Properties.WATERLOGGED)) {
                        pillarBlockFinal = pillarBlockFinal.with(Properties.WATERLOGGED, chunk.getBlockState(pillarPos).getFluidState().isIn(FluidTags.WATER));
                    }
                    chunk.setBlockState(pillarPos, pillarBlockFinal, false);
                    pillarPos.move(Direction.DOWN);
                }
            } else {
                mutable.set(jigsawPos);
                if (!chunk.getBlockState(mutable.up(context.getConfig().waterBased ? 4 : 3)).isOpaque()) {

                    boolean canMakeChain = false;
                    mutable.move(Direction.UP);
                    while (mutable.getY() < world.getTopY()) {
                        tempBlock = chunk.getBlockState(mutable);

                        // Move up for every spot we can replace with chain.
                        if (canReplace(tempBlock)) {
                            mutable.move(Direction.UP);
                        }

                        // exit. Chain cannot be made
                        else if (mutable.getY() - jigsawPos.getY() > 50 || !Block.sideCoversSmallSquare(world, mutable, Direction.DOWN) || (tempBlock.getBlock() instanceof FallingBlock)) {
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
                        BlockPos.Mutable chainPos = new BlockPos.Mutable().set(jigsawPos).move(Direction.UP);
                        BlockState fenceBlockFinal = context.getConfig().fenceState;
                        if (fenceBlockFinal.contains(Properties.WATERLOGGED)) {
                            fenceBlockFinal = fenceBlockFinal.with(Properties.WATERLOGGED, chunk.getBlockState(chainPos).getFluidState().isIn(FluidTags.WATER));
                        }
                        chunk.setBlockState(chainPos, fenceBlockFinal, false);
                        chainPos.move(Direction.UP);
                        while (chainPos.getY() < mutable.getY()) {
                            chunk.setBlockState(
                                    chainPos,
                                    Blocks.CHAIN.getDefaultState()
                                            .with(Properties.WATERLOGGED,
                                                    chunk.getBlockState(chainPos).getFluidState().isIn(FluidTags.WATER)),
                                    false);
                            chainPos.move(Direction.UP);
                        }
                    }
                }
            }
        }

        return true;
    }


    protected boolean canReplace(BlockState state) {
        return state.isAir() ||
                (state.getMaterial().isLiquid() && !state.getFluidState().isIn(FluidTags.LAVA)) ||
                state.getMaterial().equals(Material.REPLACEABLE_PLANT) ||
                state.isOf(Blocks.COBWEB);
    }
}