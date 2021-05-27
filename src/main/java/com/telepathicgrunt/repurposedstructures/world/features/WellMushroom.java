package com.telepathicgrunt.repurposedstructures.world.features;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.Structure;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;


public class WellMushroom extends WellAbstract {
    private static final float ORE_CHANCE = 0.5f;
    private static final Identifier MUSHROOM_WELL_ORE_RL = new Identifier("repurposed_structures:mushroom_well_ores");
    private static final Identifier MUSHROOM_WELL_RL = new Identifier(RepurposedStructures.MODID, "wells/mushroom");

    public WellMushroom() {
        super(MUSHROOM_WELL_RL);
    }

    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, DefaultFeatureConfig config) {
        if(GeneralUtils.isWorldBlacklisted(world)) return false;

        // move to top land block below position
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(position);
        for (mutable.move(Direction.UP); world.isAir(mutable) && mutable.getY() > 2; ) {
            mutable.move(Direction.DOWN);
        }

        // check to make sure spot is valid and not a single block ledge
        BlockState block = world.getBlockState(mutable);
        if ((block.isOf(Blocks.MYCELIUM) || isSoil(block.getBlock())) && (!world.isAir(mutable.down()) || !world.isAir(mutable.down(2)))) {
            // Creates the well centered on our spot
            mutable.move(Direction.DOWN);
            Structure template = this.generateTemplate(MUSHROOM_WELL_RL, world, random, mutable);
            if(template != null) {
                this.handleDataBlocks(MUSHROOM_WELL_ORE_RL, template, world, random, mutable, Blocks.STONE, ORE_CHANCE);
            }

            return true;
        }

        return false;
    }

}