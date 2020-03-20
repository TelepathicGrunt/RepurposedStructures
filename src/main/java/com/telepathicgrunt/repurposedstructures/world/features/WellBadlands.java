package com.telepathicgrunt.repurposedstructures.world.features;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.Tags;

public class WellBadlands extends Feature<NoFeatureConfig> {
   private static final BlockState RED_SANDSTONE_SLAB = Blocks.RED_SANDSTONE_SLAB.getDefaultState();
   private static final BlockState RED_SANDSTONE = Blocks.RED_SANDSTONE.getDefaultState();
   private static final BlockState STONE = Blocks.STONE.getDefaultState();
   private static final BlockState GOLD_ORE = Blocks.GOLD_ORE.getDefaultState();
   private static final BlockState WATER = Blocks.WATER.getDefaultState();

   public WellBadlands(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
      super(config);
   }

   public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
      
	  BlockPos.Mutable mutable = new BlockPos.Mutable(position.up());
	  for(position = position.up(); world.isAirBlock(position) && position.getY() > 2; position = position.down()) {
         ;
      }
	  position = mutable;

      Block block = world.getBlockState(mutable).getBlock();
      if (Tags.Blocks.SAND.contains(block) || Tags.Blocks.DIRT.contains(block)) {
         for(int x = -2; x <= 2; ++x) {
            for(int z = -2; z <= 2; ++z) {
               if (world.isAirBlock(mutable.down()) && world.isAirBlock(mutable.down(2))) {
                  return false;
               }
            }
         }

         for(int y = -1; y <= 0; ++y) {
            for(int x = -2; x <= 2; ++x) {
               for(int z = -2; z <= 2; ++z) {
                  world.setBlockState(mutable.add(x, y, z), RED_SANDSTONE, 2);
               }
            }
         }
         

         world.setBlockState(mutable, WATER, 2);
         if(random.nextFloat() < 0.1f)
         {
             world.setBlockState(mutable.down(), GOLD_ORE, 2);
         }
         else
         {
             world.setBlockState(mutable.down(), STONE, 2);
         }

         
         for(Direction direction : Direction.Plane.HORIZONTAL) {
        	mutable.setPos(position).move(direction);
            world.setBlockState(mutable, WATER, 2);
            
            mutable.move(Direction.DOWN);
            if(random.nextFloat() < 0.1f)
            {
                world.setBlockState(mutable, GOLD_ORE, 2);
            }
            else
            {
                world.setBlockState(mutable, STONE, 2);
            }
         }
         mutable.setPos(position);
         
         
         for(int x = -2; x <= 2; ++x) {
            for(int z = -2; z <= 2; ++z) {
               if (x == -2 || x == 2 || z == -2 || z == 2) {
                  world.setBlockState(mutable.add(x, 1, z), RED_SANDSTONE, 2);
               }
            }
         }

         world.setBlockState(mutable.add(2, 1, 0), RED_SANDSTONE_SLAB, 2);
         world.setBlockState(mutable.add(-2, 1, 0), RED_SANDSTONE_SLAB, 2);
         world.setBlockState(mutable.add(0, 1, 2), RED_SANDSTONE_SLAB, 2);
         world.setBlockState(mutable.add(0, 1, -2), RED_SANDSTONE_SLAB, 2);

         for(int x = -1; x <= 1; ++x) {
            for(int z = -1; z <= 1; ++z) {
               if (x == 0 && z == 0) {
                  world.setBlockState(mutable.add(x, 4, z), RED_SANDSTONE, 2);
               } else {
                  world.setBlockState(mutable.add(x, 4, z), RED_SANDSTONE_SLAB, 2);
               }
            }
         }

         for(int y = 1; y <= 3; ++y) {
            world.setBlockState(mutable.add(-1, y, -1), RED_SANDSTONE, 2);
            world.setBlockState(mutable.add(-1, y, 1), RED_SANDSTONE, 2);
            world.setBlockState(mutable.add(1, y, -1), RED_SANDSTONE, 2);
            world.setBlockState(mutable.add(1, y, 1), RED_SANDSTONE, 2);
         }

         return true;
      }
      
      return false;
   }
}