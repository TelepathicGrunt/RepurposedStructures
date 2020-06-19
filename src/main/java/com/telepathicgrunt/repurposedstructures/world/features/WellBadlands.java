package com.telepathicgrunt.repurposedstructures.world.features;

import java.util.Collection;
import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BellAttachment;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.StructureMode;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;


public class WellBadlands extends Feature<NoFeatureConfig>
{
    private static final float ORE_CHANCE = 0.15f;
    private static final ResourceLocation BADLANDS_WELL_ORE_RL = new ResourceLocation(RepurposedStructures.MODID+":badlands_well_ores");
    private static final ResourceLocation BADLANDS_WELL_RL = new ResourceLocation(RepurposedStructures.MODID+":wells/badlands");
    TemplateManager templatemanager = null;

    public WellBadlands(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
	super(config);
    }


    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
	// move to top land block below position
	BlockPos.Mutable mutable = new BlockPos.Mutable(position);
	for (mutable.move(Direction.UP); world.isAirBlock(mutable) && mutable.getY() > 2;) {
	    mutable.move(Direction.DOWN);
	}
	position = mutable;

	//check to make sure spot is valid and not a single block ledge
	Block block = world.getBlockState(mutable).getBlock();
	if ((Tags.Blocks.SAND.contains(block) || Tags.Blocks.DIRT.contains(block)) && 
		(!world.isAirBlock(mutable.down()) || !world.isAirBlock(mutable.down(2)))) 
	{
	    
	    //cache to save time and speed
	    if(templatemanager == null)
		templatemanager = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager();
	    
	    //Dont cache this as templatemanager already does caching behind the scenes and users might
	    //override the file later with datapacks in world somehow. (maybe)
	    Template template = templatemanager.getTemplate(BADLANDS_WELL_RL);

	    if (template == null) {
		RepurposedStructures.LOGGER.warn("Badlands Well NTB does not exist!");
		return false;
	    }
	    
	    //Creates the well centered on our spot
	    mutable.move(Direction.DOWN);
	    BlockPos offset = new BlockPos(template.getSize().getX()/2, 0, template.getSize().getZ()/2);
	    PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setCenterOffset(mutable).setIgnoreEntities(false).setChunk((ChunkPos) null);
	    template.addBlocksToWorld(world, mutable.add(offset), placementsettings);

	    
	    //Replace the Data blocks with ores or bells
	    Tag<Block> ORE_TAG = BlockTags.getCollection().getOrCreate(BADLANDS_WELL_ORE_RL);
	    Collection<Block> allOreBlocks = ORE_TAG.getAllElements();
	    for (Template.BlockInfo template$blockinfo : template.func_215381_a(mutable.add(offset), placementsettings, Blocks.STRUCTURE_BLOCK)) {
		if (template$blockinfo.nbt != null) {
		    StructureMode structuremode = StructureMode.valueOf(template$blockinfo.nbt.getString("mode"));
		    if (structuremode == StructureMode.DATA) {
			addBells(template$blockinfo.nbt.getString("metadata"), template$blockinfo.pos, world, random, allOreBlocks);
			addOres(template$blockinfo.nbt.getString("metadata"), template$blockinfo.pos, world, random, allOreBlocks);
		    }
		}
	    }
	    
	    return true;
	}

	return false;
    }

    /**
     * Replaces the "bell" data block sometimes with bells.
     */
    private static void addBells(String function, BlockPos position, IWorld world, Random random, Collection<Block> allOreBlocks) {
	if(function.equals("bell")) {
	    if (RepurposedStructures.RSWellsConfig.canHaveBells.get() && random.nextInt(100) == 0) {
		world.setBlockState(position, Blocks.BELL.getDefaultState()
			.with(BlockStateProperties.BELL_ATTACHMENT, BellAttachment.CEILING), 2);
	    }
	    else {
		world.setBlockState(position, Blocks.AIR.getDefaultState(), 2);
	    }
	}
    }
    
    
    /**
     * Replaces the "ores" data block with blocks specified in the badlands_well_ores tag.
     */
    private static void addOres(String function, BlockPos position, IWorld world, Random random, Collection<Block> allOreBlocks) {
	if(function.equals("ores")) {
	    if (!allOreBlocks.isEmpty() && random.nextFloat() < ORE_CHANCE) {
		world.setBlockState(position, ((Block) allOreBlocks.toArray()[random.nextInt(allOreBlocks.size())]).getDefaultState(), 2);
	    }
	    else {
		world.setBlockState(position, Blocks.STONE.getDefaultState(), 2);
	    }
	}
    }
}