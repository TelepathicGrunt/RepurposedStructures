package com.telepathicgrunt.repurposedstructures.world.features;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.structure.Structure;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;


public class WellSnow extends WellAbstract
{
    private static final float ORE_CHANCE = 0.3f;
    private static final Identifier SNOW_WELL_ORE_RL = new Identifier("repurposed_structures:snow_well_ores");
    private static final Identifier SNOW_WELL_RL = new Identifier(RepurposedStructures.MODID + ":wells/snow");

    public WellSnow(Codec<DefaultFeatureConfig>config) {
	super(config);
    }

    public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockPos position, DefaultFeatureConfig config)
    {
	// move to top land block below position
	BlockPos.Mutable mutable = new BlockPos.Mutable().set(position);
	for (mutable.move(Direction.UP); world.isAir(mutable) && mutable.getY() > 2;) {
	    mutable.move(Direction.DOWN);
	}

	// check to make sure spot is valid and not a single block ledge
	Block block = world.getBlockState(mutable).getBlock();
	if ((block == Blocks.SNOW_BLOCK || isDirt(block)) && (!world.isAir(mutable.down()) || !world.isAir(mutable.down(2)))) {
	    // Creates the well centered on our spot
	    mutable.move(Direction.DOWN);
	    Structure template = this.generateTemplate(SNOW_WELL_RL, world, random, mutable);
	    this.handleDataBlocks(SNOW_WELL_ORE_RL, template, world, random, mutable, Blocks.STONE, ORE_CHANCE);

	    return true;
	}

	return false;
    }

}