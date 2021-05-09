package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.Template;

import java.util.Random;


public class WellSnow extends WellAbstract {
    private static final float ORE_CHANCE = 0.3f;
    private static final ResourceLocation SNOW_WELL_ORE_RL = new ResourceLocation("repurposed_structures:snow_well_ores");
    private static final ResourceLocation SNOW_WELL_RL = new ResourceLocation(RepurposedStructures.MODID, "wells/snow");

    public WellSnow(Codec<NoFeatureConfig> config) {
        super(config);
    }

    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
        if(GeneralUtils.isWorldBlacklisted(world)) return false;
        // move to top land block below position
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(position);
        for (mutable.move(Direction.UP); world.isEmptyBlock(mutable) && mutable.getY() > 2; ) {
            mutable.move(Direction.DOWN);
        }

        // check to make sure spot is valid and not a single block ledge
        BlockState block = world.getBlockState(mutable);
        if ((block.is(Blocks.SNOW_BLOCK) || isDirt(block.getBlock())) && (!world.isEmptyBlock(mutable.below()) || !world.isEmptyBlock(mutable.below(2)))) {
            // Creates the well centered on our spot
            mutable.move(Direction.DOWN);
            Template template = this.generateTemplate(SNOW_WELL_RL, world, random, mutable);
            if(template != null) {
                this.handleDataBlocks(SNOW_WELL_ORE_RL, template, world, random, mutable, Blocks.STONE, ORE_CHANCE);
            }
            return true;
        }

        return false;
    }

}