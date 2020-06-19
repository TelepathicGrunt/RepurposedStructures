package com.telepathicgrunt.repurposedstructures.world.features;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraftforge.common.Tags;


public class WellMossyStone extends WellAbstract
{
    private static final float ORE_CHANCE = 0.12f;
    private static final ResourceLocation MOSSY_WELL_ORE_RL = new ResourceLocation("repurposed_structures:mossy_well_ores");
    private static final ResourceLocation MOSSY_WELL_RL = new ResourceLocation(RepurposedStructures.MODID + ":wells/mossy");

    public WellMossyStone(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
	super(config);
    }


    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
	// move to top land block below position
	BlockPos.Mutable mutable = new BlockPos.Mutable(position);
	for (mutable.move(Direction.UP); (world.isAirBlock(mutable) || !world.getFluidState(mutable).isEmpty()) && mutable.getY() > 2;) {
	    mutable.move(Direction.DOWN);
	}

	// check to make sure spot is valid and not a single block ledge
	Block block = world.getBlockState(mutable).getBlock();
	if ((Tags.Blocks.SAND.contains(block) || block == Blocks.CLAY || Tags.Blocks.DIRT.contains(block))
		&& (!world.isAirBlock(mutable.down()) || !world.isAirBlock(mutable.down(2)))) {
	    // Creates the well centered on our spot
	    mutable.move(Direction.DOWN);
	    Template template = this.generateTemplate(MOSSY_WELL_RL, world, random, mutable);
	    this.handleDataBlocks(MOSSY_WELL_ORE_RL, template, world, random, mutable, Blocks.COBBLESTONE, ORE_CHANCE);

	    // turns some of the stony blocks into mossy versions and waterlogs blocks below sealevel
	    BlockPos offset = new BlockPos(-template.getSize().getX() / 2, 0, -template.getSize().getZ() / 2);
	    BlockPos.getAllInBox(template
		    .getMutableBoundingBox(this.placementsettings, mutable.add(offset)))
	    		.forEach(pos -> mossifyBlocks(world, random, pos));
	    BlockPos.getAllInBox(template
		    .getMutableBoundingBox(this.placementsettings, mutable.add(offset)))
			.forEach(pos -> waterlogBlocks(world, pos));
	    
	    return true;
	}

	return false;
    }
    
    private static void mossifyBlocks(IWorld world, Random random, BlockPos position) {
	Block block = world.getBlockState(position).getBlock();
	if(block == Blocks.STONE_BRICKS && random.nextFloat() < 0.6f) {
	    world.setBlockState(position, Blocks.MOSSY_STONE_BRICKS.getDefaultState(), 2);
	}
	else if(block == Blocks.STONE_BRICK_WALL && random.nextFloat() < 0.6f) {
	    world.setBlockState(position, Blocks.MOSSY_STONE_BRICK_WALL.getDefaultState(), 2);
	}
	else if(block == Blocks.STONE_BRICK_SLAB && random.nextFloat() < 0.6f) {
	    world.setBlockState(position, Blocks.MOSSY_STONE_BRICK_SLAB.getDefaultState(), 2);
	}
	else if(block == Blocks.COBBLESTONE && random.nextFloat() < 0.5f) {
	    world.setBlockState(position, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 2);
	}
    }
    
    private static void waterlogBlocks(IWorld world, BlockPos position) {
	BlockState blockstate = world.getBlockState(position);
	if(position.getY() < world.getSeaLevel() && blockstate.has(BlockStateProperties.WATERLOGGED)) {
	    world.setBlockState(position, blockstate.with(BlockStateProperties.WATERLOGGED, true), 2);
	}
    }
}