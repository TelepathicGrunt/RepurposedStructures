package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.ArrayList;
import java.util.HashSet;


public record MineshaftSupport(
        HashSet<Block> archBlocks, 
        BlockState pillarState,
        BlockState fenceState, 
        Block targetFloorState, 
        boolean waterBased, 
        boolean archOnly
) implements Feature {

    public static final MapCodec<MineshaftSupport> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            BuiltInRegistries.BLOCK.byNameCodec().listOf().fieldOf("arch_blocks").xmap(HashSet::new, ArrayList::new).forGetter(feature -> feature.archBlocks),
            BlockState.CODEC.fieldOf("pillar_state").forGetter(feature -> feature.pillarState),
            BlockState.CODEC.fieldOf("fence_state").forGetter(feature -> feature.fenceState),
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("target_floor_block").forGetter(feature -> feature.targetFloorState),
            Codec.BOOL.fieldOf("is_water_based").orElse(false).forGetter(feature -> feature.waterBased),
            Codec.BOOL.fieldOf("arch_only").orElse(false).forGetter(feature -> feature.archOnly)
    ).apply(instance, MineshaftSupport::new));

    @Override
    public MapCodec<MineshaftSupport> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        // start at jigsaw block pos
        BlockPos jigsawPos = origin.below();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(jigsawPos);
        WorldGenLevel world = level;
        ChunkAccess chunk = world.getChunk(mutable);
        BlockState removalState = waterBased ? Blocks.WATER.defaultBlockState() : Blocks.CAVE_AIR.defaultBlockState();

        // Repair arch if we can at this spot (doesn't repair all arches. That requires a redesign of these jigsaw mineshafts)
        if (archBlocks.contains(world.getBlockState(mutable.above(3)).getBlock())) {
            for (int i = 0; i <= 1; i++) {
                if (chunk.getBlockState(mutable.move(Direction.UP)).getBlock() != fenceState.getBlock()) {
                    StructurePostProcessConnectiveBlocks.placeConnectBlock(level, random, mutable, chunk.getPos(), chunk, fenceState);
                    blockOffAirIfWaterBased(mutable, world);
                }
            }
            return true;
        } else {
            for (int i = 0; i <= 2; i++) {
                BlockState checkArchState = chunk.getBlockState(mutable.move(Direction.UP));
                if (i < 2 ? checkArchState.getBlock() == fenceState.getBlock() : archBlocks.contains(checkArchState.getBlock())) {
                    chunk.setBlockState(mutable, removalState, Block.UPDATE_CLIENTS);
                    blockOffAirIfWaterBased(mutable, world);
                }
            }
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                mutable.move(direction);
                if (ChunkPos.containing(mutable).equals(chunk.getPos())) {
                    BlockState checkArchState = chunk.getBlockState(mutable);
                    if (archBlocks.contains(checkArchState.getBlock())) {
                        chunk.setBlockState(mutable, removalState, Block.UPDATE_CLIENTS);
                        blockOffAirIfWaterBased(mutable, world);
                    }
                }

                mutable.move(direction.getOpposite());
            }
        }

        if (archOnly) {
            return true;
        }

        // Only do support if floor block is placed
        mutable.set(jigsawPos);
        if (world.getBlockState(mutable).is(targetFloorState)) {
            if (world.canSeeSkyFromBelowWater(mutable.above())) {
                return false;
            }

            BlockState tempBlock;
            boolean canMakePillar = false;
            mutable.move(Direction.DOWN);
            while (mutable.getY() > chunk.getMinY()) {
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
                BlockState pillarBlockFinal = pillarState;
                while (pillarPos.getY() > mutable.getY()) {
                    if (pillarBlockFinal.hasProperty(BlockStateProperties.WATERLOGGED)) {
                        pillarBlockFinal = pillarBlockFinal.setValue(BlockStateProperties.WATERLOGGED, chunk.getBlockState(pillarPos).getFluidState().is(FluidTags.WATER));
                    }
                    chunk.setBlockState(pillarPos, pillarBlockFinal, Block.UPDATE_CLIENTS);
                    pillarPos.move(Direction.DOWN);
                }
            } else {
                mutable.set(jigsawPos);
                if (!chunk.getBlockState(mutable.above(waterBased ? 4 : 3)).canOcclude()) {

                    boolean canMakeChain = false;
                    mutable.move(Direction.UP);
                    while (mutable.getY() < world.getMaxY()) {
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
                        BlockState fenceBlockFinal = fenceState;
                        if (fenceBlockFinal.hasProperty(BlockStateProperties.WATERLOGGED)) {
                            fenceBlockFinal = fenceBlockFinal.setValue(BlockStateProperties.WATERLOGGED, chunk.getBlockState(chainPos).getFluidState().is(FluidTags.WATER));
                        }
                        chunk.setBlockState(chainPos, fenceBlockFinal, Block.UPDATE_CLIENTS);
                        chainPos.move(Direction.UP);
                        while (chainPos.getY() < mutable.getY()) {
                            chunk.setBlockState(
                                    chainPos,
                                    Blocks.IRON_CHAIN.defaultBlockState()
                                            .setValue(BlockStateProperties.WATERLOGGED,
                                                    chunk.getBlockState(chainPos).getFluidState().is(FluidTags.WATER)),
                                    Block.UPDATE_CLIENTS);
                            chainPos.move(Direction.UP);
                        }
                    }
                }
            }
        }

        return true;
    }

    private void blockOffAirIfWaterBased(BlockPos.MutableBlockPos mutable, WorldGenLevel world) {
        if (waterBased) {
            for (Direction direction : Direction.values()) {
                mutable.move(direction);
                if (world.getBlockState(mutable).isAir()) {
                    world.setBlock(mutable, targetFloorState.defaultBlockState(), 3);
                }
                mutable.move(direction.getOpposite());
            }
        }
    }

    private boolean canReplace(BlockState state) {
        return state.isAir() ||
                (!state.getFluidState().isEmpty() && !state.getFluidState().is(FluidTags.LAVA)) ||
                state.is(RSTags.MINESHAFT_SUPPORT_REPLACEABLES);
    }
}