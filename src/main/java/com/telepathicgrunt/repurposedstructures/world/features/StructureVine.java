package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetLengthRangeConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class StructureVine extends Feature<StructureTargetLengthRangeConfig> {

    public StructureVine(Codec<StructureTargetLengthRangeConfig> config) {
        super(config);
    }


    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetLengthRangeConfig config) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt((config.range * 2) + 1) - config.range,
                    random.nextInt(5) - 1,
                    random.nextInt((config.range * 2) + 1) - config.range
            );

            if(!world.isEmptyBlock(mutable) ||
                    // This seems to sometimes deadlock only on Forge. But not Fabric. What the fuck?
                    //!world.getLevel().structureFeatureManager().getStructureAt(mutable, true, config.targetStructure).isValid()
                    // Alternative. Won't follow the structure's bounds perfectly tho...
                    !world.startsForFeature(SectionPos.of(mutable), config.targetStructure).findAny().isPresent()
            ){
                continue;
            }

            // generates vines from given position down length number of blocks if path is clear and the given position is valid
            int length = 0;
            BlockPos.Mutable vineMutablePos = new BlockPos.Mutable().set(mutable);
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
                }
                else {
                    break;
                }
            }
        }

        return true;
    }
}