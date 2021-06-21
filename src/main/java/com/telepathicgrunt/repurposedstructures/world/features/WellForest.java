package com.telepathicgrunt.repurposedstructures.world.features;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.Structure;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;


public class WellForest extends WellAbstract {
    private static final float ORE_CHANCE = 0.3f;
    private static final Identifier FOREST_WELL_ORE_RL = new Identifier("repurposed_structures:forest_well_ores");
    private static final Identifier FOREST_WELL_RL = new Identifier(RepurposedStructures.MODID, "wells/forest");

    public WellForest() {
        super(FOREST_WELL_RL);
    }


    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        if(GeneralUtils.isWorldBlacklisted(context.getWorld())) return false;
        // move to top land block below position
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(context.getOrigin());
        for (mutable.move(Direction.UP); context.getWorld().isAir(mutable) && mutable.getY() > 2; ) {
            mutable.move(Direction.DOWN);
        }

        // check to make sure spot is valid and not a single block ledge
        BlockState block = context.getWorld().getBlockState(mutable);
        if (isSoil(block) && (!context.getWorld().isAir(mutable.down()) || !context.getWorld().isAir(mutable.down(2)))) {

            // Creates the well centered on our spot
            mutable.move(Direction.DOWN, 5);
            Structure template = this.generateTemplate(FOREST_WELL_RL, context.getWorld(), context.getRandom(), mutable);
            if(template != null) {
                this.handleDataBlocks(FOREST_WELL_ORE_RL, template, context.getWorld(), context.getGenerator(), context.getRandom(), mutable, Blocks.STONE, ORE_CHANCE);
            }

            return true;
        }

        return false;
    }
}