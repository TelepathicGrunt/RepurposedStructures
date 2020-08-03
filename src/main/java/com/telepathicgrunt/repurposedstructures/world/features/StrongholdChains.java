package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RSFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;
import java.util.function.Predicate;


public class StrongholdChains extends Feature<NoFeatureConfig> {

    public StrongholdChains(Codec<NoFeatureConfig> configFactory) {
        super(configFactory);
    }

    private static final Predicate<BlockState> STRONGHOLD_BLOCKS = (blockState) -> {
        if (blockState == null) {
            return false;
        } else {
            return  blockState.isIn(Blocks.NETHER_BRICKS)  ||
                    blockState.isIn(Blocks.RED_NETHER_BRICKS)  ||
                    blockState.isIn(Blocks.MAGMA_BLOCK)  ||
                    blockState.isIn(Blocks.field_235406_np_)  ||
                    blockState.isIn(Blocks.field_235410_nt_)  ||
                    blockState.isIn(Blocks.field_235411_nu_)  ||
                    blockState.isIn(Blocks.field_235412_nv_)  ||
                    blockState.isIn(Blocks.field_235413_nw_)  ||
                    blockState.isIn(Blocks.field_235387_nA_)  ||
                    blockState.isIn(Blocks.BLACK_TERRACOTTA)  ||
                    blockState.isIn(Blocks.COBBLESTONE)  ||
                    blockState.isIn(Blocks.STONE_BRICKS)  ||
                    blockState.isIn(Blocks.CHISELED_STONE_BRICKS)  ||
                    blockState.isIn(Blocks.CRACKED_STONE_BRICKS)  ||
                    blockState.isIn(Blocks.MOSSY_STONE_BRICKS)  ||
                    blockState.isIn(Blocks.INFESTED_CHISELED_STONE_BRICKS) ||
                    blockState.isIn(Blocks.INFESTED_CRACKED_STONE_BRICKS) ||
                    blockState.isIn(Blocks.INFESTED_STONE_BRICKS) ||
                    blockState.isIn(Blocks.INFESTED_MOSSY_STONE_BRICKS);
        }
    };

    @Override
    public boolean generate(ISeedReader world, StructureManager structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
        if (!world.isAirBlock(position) ||
                (!structureAccessor.getStructuresWithChildren(SectionPos.from(position), RSFeatures.STONEBRICK_STRONGHOLD).findAny().isPresent() &&
                 !structureAccessor.getStructuresWithChildren(SectionPos.from(position), RSFeatures.NETHER_STRONGHOLD).findAny().isPresent()))
        {
           return false;
        }

        //move up if above is air (only goes 10 blocks up before giving up
        BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable().setPos(position);
        for(int i = 0; i < 10 && world.isAirBlock(blockpos$Mutable.up()) && blockpos$Mutable.getY() < 255; i++){
            blockpos$Mutable.move(Direction.UP);
        }

        // generates chains from given position down 1-8 blocks if path is clear and the given position is valid
        int length = 0;
        BlockState aboveBlockstate;

        for (; blockpos$Mutable.getY() > 3 && length < random.nextInt(random.nextInt(random.nextInt(8) + 1) + 1) + 1; blockpos$Mutable.move(Direction.DOWN)) {
            if (world.isAirBlock(blockpos$Mutable)) {
                aboveBlockstate = world.getBlockState(blockpos$Mutable.up());

                if (STRONGHOLD_BLOCKS.test(aboveBlockstate) || aboveBlockstate.isIn(Blocks.field_235341_dI_)) {
                    world.setBlockState(blockpos$Mutable, Blocks.field_235341_dI_.getDefaultState(), 2);
                    length++;
                }
            } else {
                return true;
            }
        }

        //attaches lantern at end at a rare chance
        if(random.nextFloat() < 0.075f && world.isAirBlock(blockpos$Mutable)){
            if(world.getBiome(blockpos$Mutable).getCategory() == Biome.Category.NETHER){
                world.setBlockState(blockpos$Mutable, Blocks.field_235366_md_.getDefaultState().with(LanternBlock.HANGING, true), 2);
            }
            else{
                world.setBlockState(blockpos$Mutable, Blocks.LANTERN.getDefaultState().with(LanternBlock.HANGING, true), 2);
            }
        }

        return true;
    }
}