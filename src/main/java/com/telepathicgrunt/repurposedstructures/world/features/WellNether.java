package com.telepathicgrunt.repurposedstructures.world.features;

import java.util.Collection;
import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.StructureMode;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraftforge.common.Tags;


public class WellNether extends WellAbstract
{
    private static final float COMMON_ORE_CHANCE = 0.5f;
    private static final float RARE_ORE_CHANCE = 0.08f;
    private static final ResourceLocation NETHER_WELL_ORE_RL = new ResourceLocation("repurposed_structures:nether_well_ores");
    private static final ResourceLocation NETHER_WELL_RL = new ResourceLocation(RepurposedStructures.MODID + ":wells/nether");


    public WellNether(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
	super(config);
    }


    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
	// move to top land block below position
	BlockPos.Mutable mutable = new BlockPos.Mutable(position);
	for (mutable.move(Direction.UP); world.isAirBlock(mutable) && mutable.getY() > 2;) {
	    mutable.move(Direction.DOWN);
	}

	// check to make sure spot is valid and not a single block ledge
	Block block = world.getBlockState(mutable).getBlock();
	if ((Tags.Blocks.NETHERRACK.contains(block) || 
		block == Blocks.SOUL_SAND || 
		block == Blocks.GRAVEL || 
		block == world.getBiome(mutable).getSurfaceBuilderConfig().getTop().getBlock()) && 
		(!world.isAirBlock(mutable.down()) || !world.isAirBlock(mutable.down(2)))) 
	{
	    // Creates the well centered on our spot
	    mutable.move(Direction.DOWN);
	    Template template = this.generateTemplate(NETHER_WELL_RL, world, random, mutable);
	    this.handleDataBlocks(NETHER_WELL_ORE_RL, template, world, random, mutable, Blocks.NETHERRACK, 0);

	    return true;
	}

	return false;
    }


    protected void handleDataBlocks(ResourceLocation templateOresRL, Template template, IWorld world, Random random, BlockPos position, Block defaultBlock, float oreChance) {
	// Replace the Data blocks with ores or bells
	Tag<Block> ORE_TAG = BlockTags.getCollection().getOrCreate(templateOresRL);
	Collection<Block> allOreBlocks = ORE_TAG.getAllElements();
	BlockPos offset = new BlockPos(-template.getSize().getX() / 2, 0, -template.getSize().getZ() / 2);
	for (Template.BlockInfo template$blockinfo : template.func_215381_a(position.add(offset), placementsettings, Blocks.STRUCTURE_BLOCK)) {
	    if (template$blockinfo.nbt != null) {
		StructureMode structuremode = StructureMode.valueOf(template$blockinfo.nbt.getString("mode"));
		if (structuremode == StructureMode.DATA) {
		    addBells(template$blockinfo.nbt.getString("metadata"), template$blockinfo.pos, world, random, allOreBlocks);
		    addOres(template$blockinfo.nbt.getString("metadata"), template$blockinfo.pos, world, random, allOreBlocks, defaultBlock, oreChance);
		    addSpace(template$blockinfo.nbt.getString("metadata"), template$blockinfo.pos, world);
		}
	    }
	}
    }


    /**
     * Replaces the "ores" data block with blocks specified in the ore tag.
     */
    protected static void addOres(String function, BlockPos position, IWorld world, Random random, Collection<Block> allOreBlocks, Block defaultBlock, float oreChance) {
	if (function.equals("ores")) {
	    float chance = random.nextFloat();
	    if (!allOreBlocks.isEmpty() && chance < RARE_ORE_CHANCE) {
		world.setBlockState(position, ((Block) allOreBlocks.toArray()[0]).getDefaultState(), 2);
	    }
	    else if (allOreBlocks.size() > 1 && chance - RARE_ORE_CHANCE < COMMON_ORE_CHANCE) {
		world.setBlockState(position, ((Block) allOreBlocks.toArray()[random.nextInt(allOreBlocks.size() - 1) + 1]).getDefaultState(), 2);
	    }
	    else {
		world.setBlockState(position, defaultBlock.getDefaultState(), 2);
	    }
	}
    }
    
    /**
     * sets 'space' data blocks to air or lava based on sea level so terrain blocks wont be placed weirdly inside well The space will be done in a + shape centered on the data block
     */
    protected static void addSpace(String function, BlockPos position, IWorld world) {
	if (function.equals("space")) {
	    BlockState blockstate;
	    if (position.getY() < world.getSeaLevel()) {
		blockstate = Blocks.LAVA.getDefaultState();
	    }
	    else {
		blockstate = Blocks.AIR.getDefaultState();
	    }
	    
	    for (int x = -1; x <= 1; x++) {
		for (int z = -1; z <= 1; z++) {
		    if (x * z == 0) {
			if (position.getY() < world.getSeaLevel()) {
			    world.setBlockState(position.add(x, 0, z), blockstate, 2);
			}
			else {
			    world.setBlockState(position.add(x, 0, z), blockstate, 2);
			}
		    }
		}
	    }
	}
    }
}