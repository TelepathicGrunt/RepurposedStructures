package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class VinesShort extends Feature<DefaultFeatureConfig> {

    public VinesShort(Codec<DefaultFeatureConfig> configFactory) {
        super(configFactory);
    }


    @Override
    public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockPos position, DefaultFeatureConfig config) {
        if (!world.isAir(position)) {
            return false;
        }

        // generates vines from given position down 4-6 blocks if path is clear and the given position is valid
        // Also won't generate vines below Y = 15.
        int length = 0;
        BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable().set(position);

        for (; blockpos$Mutable.getY() > 15 && length < random.nextInt(3) + 4; blockpos$Mutable.move(Direction.DOWN)) {
            if (world.isAir(blockpos$Mutable)) {
                for (Direction direction : Direction.Type.HORIZONTAL) {
                    BlockState iblockstate = Blocks.VINE.getDefaultState().with(VineBlock.getFacingProperty(direction), Boolean.valueOf(true));
                    if (iblockstate.canPlaceAt(world, blockpos$Mutable)) {
                        if (world.getBlockState(blockpos$Mutable.up()).isSideSolidFullSquare(world, blockpos$Mutable, Direction.UP)) {
                            world.setBlockState(blockpos$Mutable, iblockstate.with(VineBlock.UP, true), 2);
                        } else {
                            world.setBlockState(blockpos$Mutable, iblockstate, 2);
                        }
                        length++;
                        break;
                    } else if (world.getBlockState(blockpos$Mutable.up()).getBlock() == Blocks.VINE) {
                        world.setBlockState(blockpos$Mutable, world.getBlockState(blockpos$Mutable.up()), 2);
                        length++;
                        break;
                    }
                }
            } else {
                break;
            }
        }

        return true;
    }
}