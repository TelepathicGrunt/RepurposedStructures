package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
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
                    blockState.getMaterial() == Material.DIRT ||
                    blockState.is(Blocks.INFESTED_CHISELED_STONE_BRICKS) ||
                    blockState.is(Blocks.INFESTED_CRACKED_STONE_BRICKS) ||
                    blockState.is(Blocks.INFESTED_STONE_BRICKS) ||
                    blockState.is(Blocks.INFESTED_MOSSY_STONE_BRICKS) ||
                    blockState.is(Blocks.IRON_BARS);
        }
    };


    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetAndLengthConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt(7) - 3,
                    random.nextInt(5) - 1,
                    random.nextInt(7) - 3
            );

            if(!FORTRESS_BLOCKS.test(world.getBlockState(mutable)) || !world.isEmptyBlock(mutable.below()) ||
                    // This seems to sometimes deadlock only on Forge. But not Fabric.
                    //!world.getLevel().structureFeatureManager().getStructureAt(mutable, true, config.targetStructure).isValid()
                    // Alternative. Won't follow the structure's bounds perfectly tho...
                    !world.startsForFeature(SectionPos.of(mutable), config.targetStructure).findAny().isPresent()
            ){
                continue;
            }

            // create hole in fortress block for vine
            world.setBlock(mutable, Blocks.CAVE_AIR.defaultBlockState(), 3);
            BlockPos.Mutable vineMutablePos = new BlockPos.Mutable().set(mutable);
            BlockState neighboringBlock = world.getBlockState(vineMutablePos);
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                vineMutablePos.set(mutable).move(direction);
                // no floating vines
                while(neighboringBlock.getMaterial() == Material.REPLACEABLE_PLANT){
                    world.setBlock(vineMutablePos, Blocks.CAVE_AIR.defaultBlockState(), 3);
                    neighboringBlock = world.getBlockState(vineMutablePos.move(Direction.DOWN));
                }
            }

            BlockPos.Mutable replacingPlantMutable = new BlockPos.Mutable().set(mutable);
            BlockState plantState = world.getBlockState(replacingPlantMutable.move(Direction.UP));
            while(plantState.getMaterial() == Material.REPLACEABLE_PLANT){
                world.setBlock(replacingPlantMutable, Blocks.AIR.defaultBlockState(), 3);
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
                if (world.isEmptyBlock(vineMutablePos)) {
                    for (Direction direction : Direction.Plane.HORIZONTAL) {
                        mutable.set(vineMutablePos).move(direction);
                        ChunkPos newChunkPos = new ChunkPos(mutable);
                        // Prevent floating vines at chunk borders
                        if(newChunkPos.x != currentChunkPos.x || newChunkPos.z != currentChunkPos.z) continue;

                        currentBlockstate = Blocks.VINE.defaultBlockState().setValue(VineBlock.getPropertyForFace(direction), Boolean.TRUE);
                        aboveBlockstate = world.getBlockState(vineMutablePos.above());

                        if (currentBlockstate.canSurvive(world, vineMutablePos)) {
                            //places topmost vine that can face upward
                            world.setBlock(vineMutablePos, currentBlockstate.setValue(VineBlock.UP, aboveBlockstate.canOcclude()), 2);
                            length++;
                            break;
                        }
                        else if (aboveBlockstate.is(Blocks.VINE)) {
                            //places rest of the vine as long as vine is above
                            world.setBlock(vineMutablePos, aboveBlockstate.setValue(VineBlock.UP, false), 2);
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