package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetLengthRangeConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;


public class StructureVine extends Feature<StructureTargetLengthRangeConfig> {

    public StructureVine(Codec<StructureTargetLengthRangeConfig> config) {
        super(config);
    }


    @Override
    public boolean generate(FeatureContext<StructureTargetLengthRangeConfig> context) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int i = 0; i < context.getConfig().attempts; i++){
            mutable.set(context.getOrigin()).move(
                    context.getRandom().nextInt((context.getConfig().range * 2) + 1) - context.getConfig().range,
                    context.getRandom().nextInt(5) - 1,
                    context.getRandom().nextInt((context.getConfig().range * 2) + 1) - context.getConfig().range
            );

            if(!context.getWorld().isAir(mutable)){
                continue;
            }

            // generates vines from given position down length number of blocks if path is clear and the given position is valid
            int length = 0;
            BlockPos.Mutable vineMutablePos = new BlockPos.Mutable().set(mutable);
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
                }
                else {
                    break;
                }
            }
        }

        return true;
    }
}