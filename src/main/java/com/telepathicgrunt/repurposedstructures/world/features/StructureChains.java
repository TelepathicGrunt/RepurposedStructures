package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ConnectingBlock;
import net.minecraft.block.LanternBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class StructureChains extends Feature<StructureTargetConfig> {

    public StructureChains(Codec<StructureTargetConfig> config) {
        super(config);
    }


    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt(11) - 5,
                    random.nextInt(3) - 1,
                    random.nextInt(11) - 5
            );

            if(!world.getBlockState(mutable).isAir() || !world.toServerWorld().getStructureAccessor().getStructureAt(mutable, true, config.targetStructure).hasChildren()){
                continue;
            }

            // generates chains from given position down 1-8 blocks if path is clear and the given position is valid
            int length = 0;
            BlockState aboveBlockstate;
            boolean exitEarly = false;

            for (; mutable.getY() > 3 && length < random.nextInt(random.nextInt(random.nextInt(8) + 1) + 1) + 1; mutable.move(Direction.DOWN)) {
                if (world.isAir(mutable)) {
                    aboveBlockstate = world.getBlockState(mutable.up());

                    if (aboveBlockstate.isSideSolidFullSquare(world, mutable.up(), Direction.DOWN) || aboveBlockstate.isOf(Blocks.CHAIN)) {
                        world.setBlockState(mutable, Blocks.CHAIN.getDefaultState(), 2);
                        length++;
                    }
                }
                else {
                    exitEarly = true;
                }
            }

            if(exitEarly) continue;

            //attaches lantern at end at a rare chance
            if(mutable.getY() != 3 && random.nextFloat() < 0.075f && world.isAir(mutable)){
                if(world.getBiome(mutable).getCategory() == Biome.Category.NETHER){
                    world.setBlockState(mutable, Blocks.SOUL_LANTERN.getDefaultState().with(LanternBlock.HANGING, true), 2);
                }
                else{
                    world.setBlockState(mutable, Blocks.LANTERN.getDefaultState().with(LanternBlock.HANGING, true), 2);
                }
            }
        }

        return true;
    }
}