package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.StructureBlockMode;
import net.minecraft.structure.Structure;
import net.minecraft.structure.Structure.StructureBlockInfo;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Collection;
import java.util.Random;


public class WellNether extends WellAbstract {
    private static final float COMMON_ORE_CHANCE = 0.5f;
    private static final float RARE_ORE_CHANCE = 0.08f;
    private static final Identifier NETHER_WELL_ORE_RL = new Identifier("repurposed_structures:nether_well_ores");
    private static final Identifier NETHER_WELL_RL = new Identifier(RepurposedStructures.MODID + ":wells/nether");


    public WellNether(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockPos position, DefaultFeatureConfig config) {
        // move to top land block below position
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(position);
        for (mutable.move(Direction.UP); world.isAir(mutable) && mutable.getY() > 2; ) {
            mutable.move(Direction.DOWN);
        }

        // check to make sure spot is valid and not a single block ledge
        Block block = world.getBlockState(mutable).getBlock();
        if ((BlockTags.INFINIBURN_NETHER.contains(block) ||
                block == Blocks.SOUL_SAND ||
                block == Blocks.GRAVEL ||
                block == Blocks.NETHERRACK ||
                block == world.getBiome(mutable).getSurfaceConfig().getTopMaterial().getBlock()) &&
                (!world.isAir(mutable.down()) || !world.isAir(mutable.down(2)))) {
            // Creates the well centered on our spot
            mutable.move(Direction.DOWN);
            Structure template = this.generateTemplate(NETHER_WELL_RL, world, random, mutable);
            this.handleDataBlocks(NETHER_WELL_ORE_RL, template, world, random, mutable, Blocks.NETHERRACK, 0);

            return true;
        }

        return false;
    }


    protected void handleDataBlocks(Identifier templateOresRL, Structure template, ServerWorldAccess world, Random random, BlockPos position, Block defaultBlock, float oreChance) {
        // Replace the Data blocks with ores or bells
        Tag<Block> ORE_TAG = BlockTags.getContainer().getOrCreate(templateOresRL);
        Collection<Block> allOreBlocks = ORE_TAG.values();
        BlockPos offset = new BlockPos(-template.getSize().getX() / 2, 0, -template.getSize().getZ() / 2);
        for (StructureBlockInfo template$blockinfo : template.getInfosForBlock(position.add(offset), placementsettings, Blocks.STRUCTURE_BLOCK)) {
            if (template$blockinfo.tag != null) {
                StructureBlockMode structuremode = StructureBlockMode.valueOf(template$blockinfo.tag.getString("mode"));
                if (structuremode == StructureBlockMode.DATA) {
                    addBells(template$blockinfo.tag.getString("metadata"), template$blockinfo.pos, world, random, allOreBlocks);
                    addOres(template$blockinfo.tag.getString("metadata"), template$blockinfo.pos, world, random, allOreBlocks, defaultBlock, oreChance);
                    addSpace(template$blockinfo.tag.getString("metadata"), template$blockinfo.pos, world);
                }
            }
        }
    }


    /**
     * Replaces the "ores" data block with blocks specified in the ore tag.
     */
    protected static void addOres(String function, BlockPos position, ServerWorldAccess world, Random random, Collection<Block> allOreBlocks, Block defaultBlock, float oreChance) {
        if (function.equals("ores")) {
            float chance = random.nextFloat();
            if (!allOreBlocks.isEmpty() && chance < RARE_ORE_CHANCE) {
                world.setBlockState(position, ((Block) allOreBlocks.toArray()[0]).getDefaultState(), 2);
            } else if (allOreBlocks.size() > 1 && chance - RARE_ORE_CHANCE < COMMON_ORE_CHANCE) {
                world.setBlockState(position, ((Block) allOreBlocks.toArray()[random.nextInt(allOreBlocks.size() - 1) + 1]).getDefaultState(), 2);
            } else {
                world.setBlockState(position, defaultBlock.getDefaultState(), 2);
            }
        }
    }

    /**
     * sets 'space' data blocks to air or lava based on sea level so terrain blocks wont be placed weirdly inside well The space will be done in a + shape centered on the data block
     */
    protected static void addSpace(String function, BlockPos position, ServerWorldAccess world) {
        if (function.equals("space")) {
            BlockState blockstate;
            if (position.getY() < world.getSeaLevel()) {
                blockstate = Blocks.LAVA.getDefaultState();
            } else {
                blockstate = Blocks.AIR.getDefaultState();
            }

            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    if (x * z == 0) {
                        if (position.getY() < world.getSeaLevel()) {
                            world.setBlockState(position.add(x, 0, z), blockstate, 2);
                        } else {
                            world.setBlockState(position.add(x, 0, z), blockstate, 2);
                        }
                    }
                }
            }
        }
    }
}