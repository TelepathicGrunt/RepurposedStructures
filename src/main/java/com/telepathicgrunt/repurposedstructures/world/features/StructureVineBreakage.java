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
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

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
    public boolean generate(FeatureContext<StructureTargetAndLengthConfig> context) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int i = 0; i < context.getConfig().attempts; i++){
            mutable.set(context.getOrigin()).move(
                    context.getRandom().nextInt(7) - 3,
                    context.getRandom().nextInt(5) - 1,
                    context.getRandom().nextInt(7) - 3
            );

            if(!FORTRESS_BLOCKS.test(context.getWorld().getBlockState(mutable)) || !context.getWorld().isAir(mutable.down()) || !context.getWorld().toServerWorld().getStructureAccessor().getStructureAt(mutable, true, context.getConfig().targetStructure).hasChildren()){
                continue;
            }

            // create hole in fortress block for vine
            context.getWorld().setBlockState(mutable, Blocks.CAVE_AIR.getDefaultState(), 3);
            BlockPos.Mutable vineMutablePos = new BlockPos.Mutable().set(mutable);
            BlockState neighboringBlock = context.getWorld().getBlockState(vineMutablePos);
            for (Direction direction : Direction.Type.HORIZONTAL) {
                vineMutablePos.set(mutable).move(direction);
                // no floating vines
                while(neighboringBlock.getMaterial() == Material.REPLACEABLE_PLANT){
                    context.getWorld().setBlockState(vineMutablePos, Blocks.CAVE_AIR.getDefaultState(), 3);
                    neighboringBlock = context.getWorld().getBlockState(vineMutablePos.move(Direction.DOWN));
                }
            }

            BlockPos.Mutable replacingPlantMutable = new BlockPos.Mutable().set(mutable);
            BlockState plantState = context.getWorld().getBlockState(replacingPlantMutable.move(Direction.UP));
            while(plantState.getMaterial() == Material.REPLACEABLE_PLANT){
                context.getWorld().setBlockState(replacingPlantMutable, Blocks.AIR.getDefaultState(), 3);
                plantState = context.getWorld().getBlockState(replacingPlantMutable.move(Direction.UP));
            }

            // generates vines from given position down length number of blocks if path is clear and the given position is valid
            int length = 0;
            vineMutablePos.set(mutable);
            ChunkPos currentChunkPos = new ChunkPos(vineMutablePos);
            BlockState currentBlockstate;
            BlockState aboveBlockstate;
            // Biased towards max length
            int maxLength = context.getConfig().length - context.getRandom().nextInt(context.getRandom().nextInt(context.getConfig().length) + 1);

            for (; length < maxLength; vineMutablePos.move(Direction.DOWN)) {
                if (context.getWorld().isAir(vineMutablePos)) {
                    for (Direction direction : Direction.Type.HORIZONTAL) {
                        mutable.set(vineMutablePos).move(direction);
                        ChunkPos newChunkPos = new ChunkPos(mutable);
                        // Prevent floating vines at chunk borders
                        if(newChunkPos.x != currentChunkPos.x || newChunkPos.z != currentChunkPos.z) continue;

                        currentBlockstate = Blocks.VINE.getDefaultState().with(VineBlock.getFacingProperty(direction), Boolean.TRUE);
                        aboveBlockstate = context.getWorld().getBlockState(vineMutablePos.up());

                        if (currentBlockstate.canPlaceAt(context.getWorld(), vineMutablePos)) {
                            //places topmost vine that can face upward
                            context.getWorld().setBlockState(vineMutablePos, currentBlockstate.with(VineBlock.UP, aboveBlockstate.isOpaque()), 2);
                            length++;
                            break;
                        }
                        else if (aboveBlockstate.isOf(Blocks.VINE)) {
                            //places rest of the vine as long as vine is above
                            context.getWorld().setBlockState(vineMutablePos, aboveBlockstate.with(VineBlock.UP, false), 2);
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