package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.VineBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.function.Predicate;


public class StructureVineBreakage extends Feature<StructureTargetAndLengthConfig> {

    public StructureVineBreakage(Codec<StructureTargetAndLengthConfig> config) {
        super(config);
    }

    private static final Predicate<BlockState> FORTRESS_BLOCKS = (blockState) -> {
        if (blockState == null) {
            return false;
        } else {
            return blockState.getMaterial() == Material.STONE ||
                    blockState.getMaterial() == Material.SOIL ||
                    blockState.isOf(Blocks.INFESTED_CHISELED_STONE_BRICKS) ||
                    blockState.isOf(Blocks.INFESTED_CRACKED_STONE_BRICKS) ||
                    blockState.isOf(Blocks.INFESTED_STONE_BRICKS) ||
                    blockState.isOf(Blocks.INFESTED_MOSSY_STONE_BRICKS) ||
                    blockState.isOf(Blocks.IRON_BARS);
        }
    };


    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetAndLengthConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt(7) - 3,
                    random.nextInt(5) - 1,
                    random.nextInt(7) - 3
            );

            if(!FORTRESS_BLOCKS.test(world.getBlockState(mutable)) || !world.isAir(mutable.down()) || !world.toServerWorld().getStructureAccessor().getStructureAt(mutable, true, config.targetStructure).hasChildren()){
                continue;
            }

            // create hole in fortress block for vine
            world.setBlockState(mutable, Blocks.CAVE_AIR.getDefaultState(), 3);
            BlockPos.Mutable vineMutablePos = new BlockPos.Mutable().set(mutable);
            BlockState neighboringBlock = world.getBlockState(vineMutablePos);
            for (Direction direction : Direction.Type.HORIZONTAL) {
                vineMutablePos.set(mutable).move(direction);
                // no floating vines
                while(neighboringBlock.getMaterial() == Material.REPLACEABLE_PLANT){
                    world.setBlockState(vineMutablePos, Blocks.CAVE_AIR.getDefaultState(), 3);
                    neighboringBlock = world.getBlockState(vineMutablePos.move(Direction.DOWN));
                }
            }

            BlockPos.Mutable replacingPlantMutable = new BlockPos.Mutable().set(mutable);
            BlockState plantState = world.getBlockState(replacingPlantMutable.move(Direction.UP));
            while(plantState.getMaterial() == Material.REPLACEABLE_PLANT){
                world.setBlockState(replacingPlantMutable, Blocks.AIR.getDefaultState(), 3);
                plantState = world.getBlockState(replacingPlantMutable.move(Direction.UP));
            }

            // generates vines from given position down length number of blocks if path is clear and the given position is valid
            int length = 0;
            vineMutablePos.set(mutable);
            ChunkPos currentChunkPos = new ChunkPos(vineMutablePos);
            BlockState currentBlockstate;
            BlockState aboveBlockstate;
            // Biased towards max length
            int maxLength = config.length - random.nextInt(random.nextInt(config.length) + 1);

            for (; length < maxLength; vineMutablePos.move(Direction.DOWN)) {
                if (world.isAir(vineMutablePos)) {
                    for (Direction direction : Direction.Type.HORIZONTAL) {
                        mutable.set(vineMutablePos).move(direction);
                        ChunkPos newChunkPos = new ChunkPos(mutable);
                        // Prevent floating vines at chunk borders
                        if(newChunkPos.x != currentChunkPos.x || newChunkPos.z != currentChunkPos.z) continue;

                        currentBlockstate = Blocks.VINE.getDefaultState().with(VineBlock.getFacingProperty(direction), Boolean.TRUE);
                        aboveBlockstate = world.getBlockState(vineMutablePos.up());

                        if (currentBlockstate.canPlaceAt(world, vineMutablePos)) {
                            //places topmost vine that can face upward
                            world.setBlockState(vineMutablePos, currentBlockstate.with(VineBlock.UP, aboveBlockstate.isOpaque()), 2);
                            length++;
                            break;
                        }
                        else if (aboveBlockstate.isOf(Blocks.VINE)) {
                            //places rest of the vine as long as vine is above
                            world.setBlockState(vineMutablePos, aboveBlockstate.with(VineBlock.UP, false), 2);
                            length++;
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }

        return true;
    }
}