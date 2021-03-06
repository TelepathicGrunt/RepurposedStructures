package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.Template;

import java.util.Random;


public class WellMossyStone extends WellAbstract {
    private static final float ORE_CHANCE = 0.12f;
    private static final ResourceLocation MOSSY_WELL_ORE_RL = new ResourceLocation("repurposed_structures:mossy_well_ores");
    private static final ResourceLocation MOSSY_WELL_RL = new ResourceLocation(RepurposedStructures.MODID, "wells/mossy");

    public WellMossyStone(Codec<NoFeatureConfig> config) {
        super(config);
    }


    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
        if(GeneralUtils.isWorldBlacklisted(world)) return false;
        // move to top land block below position
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(position);
        for (mutable.move(Direction.UP); (world.isEmptyBlock(mutable) || !world.getFluidState(mutable).isEmpty()) && mutable.getY() > 2; ) {
            mutable.move(Direction.DOWN);
        }

        // check to make sure spot is valid and not a single block ledge
        BlockState block = world.getBlockState(mutable);
        if ((BlockTags.SAND.contains(block.getBlock()) || block.is(Blocks.CLAY) || isDirt(block.getBlock()))
                && (!world.isEmptyBlock(mutable.below()) || !world.isEmptyBlock(mutable.below(2)))) {
            // Creates the well centered on our spot
            mutable.move(Direction.DOWN);
            Template template = this.generateTemplate(MOSSY_WELL_RL, world, random, mutable);
            if(template != null) {
                this.handleDataBlocks(MOSSY_WELL_ORE_RL, template, world, random, mutable, Blocks.COBBLESTONE, ORE_CHANCE);

                // turns some of the stony blocks into mossy versions and waterlogs blocks below sealevel
                BlockPos offset = new BlockPos(-template.getSize().getX() / 2, 0, -template.getSize().getZ() / 2);
                BlockPos.betweenClosedStream(template
                        .getBoundingBox(this.placementsettings, mutable.offset(offset)))
                        .forEach(pos -> mossifyBlocks(world, random, pos));
                BlockPos.betweenClosedStream(template
                        .getBoundingBox(this.placementsettings, mutable.offset(offset)))
                        .forEach(pos -> waterlogBlocks(world, pos));
            }

            return true;
        }

        return false;
    }

    private static void mossifyBlocks(ISeedReader world, Random random, BlockPos position) {
        BlockState block = world.getBlockState(position);
        if (block.is(Blocks.STONE_BRICKS) && random.nextFloat() < 0.6f) {
            world.setBlock(position, Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 2);
        } else if (block.is(Blocks.STONE_BRICK_WALL) && random.nextFloat() < 0.6f) {
            world.setBlock(position, Blocks.MOSSY_STONE_BRICK_WALL.defaultBlockState(), 2);
        } else if (block.is(Blocks.STONE_BRICK_SLAB) && random.nextFloat() < 0.6f) {
            world.setBlock(position, Blocks.MOSSY_STONE_BRICK_SLAB.defaultBlockState(), 2);
        } else if (block.is(Blocks.COBBLESTONE) && random.nextFloat() < 0.5f) {
            world.setBlock(position, Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 2);
        }
    }

    private static void waterlogBlocks(ISeedReader world, BlockPos position) {
        BlockState blockstate = world.getBlockState(position);
        if (position.getY() < world.getSeaLevel() && blockstate.hasProperty(BlockStateProperties.WATERLOGGED)) {
            world.setBlock(position, blockstate.setValue(BlockStateProperties.WATERLOGGED, true), 2);
        }
    }
}