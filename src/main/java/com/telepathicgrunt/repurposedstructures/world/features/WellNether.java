package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.StructureMode;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.Template;

import java.util.Collection;
import java.util.Random;


public class WellNether extends WellAbstract {
    private static final float COMMON_ORE_CHANCE = 0.5f;
    private static final float RARE_ORE_CHANCE = 0.08f;
    private static final ResourceLocation NETHER_WELL_ORE_RL = new ResourceLocation("repurposed_structures:nether_well_ores");
    private static final ResourceLocation NETHER_WELL_RL = new ResourceLocation(RepurposedStructures.MODID, "wells/nether");


    public WellNether(Codec<NoFeatureConfig> config) {
        super(config);
    }

    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
        if(GeneralUtils.isWorldBlacklisted(world)) return false;
        // move to top land block below position
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(position);
        for (mutable.move(Direction.UP); mutable.getY() > 32;) {

            if(world.isEmptyBlock(mutable) && mutable.getY() > 32){
                mutable.move(Direction.DOWN);
                continue;
            }

            // check to make sure spot is valid and not a single block ledge
            BlockState blockState = world.getBlockState(mutable);
            Block block = blockState.getBlock();
            if ((BlockTags.INFINIBURN_NETHER.contains(block) ||
                    BlockTags.VALID_SPAWN.contains(block) ||
                    BlockTags.WITHER_IMMUNE.contains(block) ||
                    blockState.getMaterial() == Material.SAND ||
                    blockState.getMaterial() == Material.STONE ||
                    blockState.getMaterial() == Material.DIRT ||
                    (world.getBiome(mutable).getGenerationSettings().getSurfaceBuilderConfig().getTopMaterial() != null &&
                            blockState.is(world.getBiome(mutable).getGenerationSettings().getSurfaceBuilderConfig().getTopMaterial().getBlock()))) &&
                    !world.isEmptyBlock(mutable.below()) &&
                    world.isEmptyBlock(mutable.above(3)) &&
                    !world.isEmptyBlock(mutable.north(2).below()) &&
                    !world.isEmptyBlock(mutable.west(2).below()) &&
                    !world.isEmptyBlock(mutable.east(2).below()) &&
                    !world.isEmptyBlock(mutable.south(2).below())
                    )
            {
                // Creates the well centered on our spot
                mutable.move(Direction.DOWN);
                Template template = this.generateTemplate(NETHER_WELL_RL, world, random, mutable);
                if(template != null) {
                    this.handleDataBlocks(NETHER_WELL_ORE_RL, template, world, random, mutable, Blocks.NETHERRACK, 0);
                }
                return true;
            }
            else{
                mutable.move(Direction.DOWN);
            }
        }

        return false;
    }


    protected void handleDataBlocks(ResourceLocation templateOresRL, Template template, ISeedReader world, Random random, BlockPos position, Block defaultBlock, float oreChance) {
        // Replace the Data blocks with ores or bells
        ITag<Block> ORE_TAG = BlockTags.getAllTags().getTagOrEmpty(templateOresRL);
        Collection<Block> allOreBlocks = ORE_TAG.getValues();
        BlockPos offset = new BlockPos(-template.getSize().getX() / 2, 0, -template.getSize().getZ() / 2);
        for (Template.BlockInfo template$blockinfo : template.filterBlocks(position.offset(offset), placementsettings, Blocks.STRUCTURE_BLOCK)) {
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
    protected static void addOres(String function, BlockPos position, ISeedReader world, Random random, Collection<Block> allOreBlocks, Block defaultBlock, float oreChance) {
        if (function.equals("ores")) {
            float chance = random.nextFloat();
            if (!allOreBlocks.isEmpty() && chance < RARE_ORE_CHANCE) {
                world.setBlock(position, ((Block) allOreBlocks.toArray()[0]).defaultBlockState(), 2);
            } else if (allOreBlocks.size() > 1 && chance - RARE_ORE_CHANCE < COMMON_ORE_CHANCE) {
                world.setBlock(position, ((Block) allOreBlocks.toArray()[random.nextInt(allOreBlocks.size() - 1) + 1]).defaultBlockState(), 2);
            } else {
                world.setBlock(position, defaultBlock.defaultBlockState(), 2);
            }
        }
    }

    /**
     * sets 'space' data blocks to air or lava based on sea level so terrain blocks wont be placed weirdly inside well The space will be done in a + shape centered on the data block
     */
    protected static void addSpace(String function, BlockPos position, ISeedReader world) {
        if (function.equals("space")) {
            BlockState blockstate;
            if (position.getY() < 32) {
                blockstate = Blocks.LAVA.defaultBlockState();
            } else {
                blockstate = Blocks.AIR.defaultBlockState();
            }

            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    if (x * z == 0) {
                        if (position.getY() < world.getSeaLevel()) {
                            world.setBlock(position.offset(x, 0, z), blockstate, 2);
                        } else {
                            world.setBlock(position.offset(x, 0, z), blockstate, 2);
                        }
                    }
                }
            }
        }
    }
}