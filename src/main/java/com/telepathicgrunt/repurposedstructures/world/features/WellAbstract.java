package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.Attachment;
import net.minecraft.block.enums.StructureBlockMode;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.Structure;
import net.minecraft.structure.Structure.StructureBlockInfo;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Collection;
import java.util.Random;


public abstract class WellAbstract extends Feature<DefaultFeatureConfig> {
    private StructureManager templatemanager = null;
    protected StructurePlacementData placementsettings = (new StructurePlacementData()).setMirror(BlockMirror.NONE).setRotation(BlockRotation.NONE).setIgnoreEntities(false).setChunkPosition((ChunkPos) null);

    public WellAbstract() {
        super(DefaultFeatureConfig.CODEC);
    }


    protected Structure generateTemplate(Identifier templateRL, StructureWorldAccess world, Random random, BlockPos position) {

        // cache to save time and speed
        if (templatemanager == null) templatemanager = ((ServerWorld) world.toServerWorld()).getStructureManager();

        // Dont cache this as templatemanager already does caching behind the scenes and users might
        // override the file later with datapacks in world somehow. (maybe)
        Structure template = templatemanager.getStructure(templateRL);

        if (template == null) {
            RepurposedStructures.LOGGER.warn(templateRL.toString() + " NTB does not exist!");
            return null;
        }

        // Creates the well centered on our spot
        BlockPos offset = new BlockPos(-template.getSize().getX() / 2, 0, -template.getSize().getZ() / 2);
        template.place(world, position.add(offset), placementsettings, random);
        return template;
    }


    protected void handleDataBlocks(Identifier templateOresRL, Structure template, StructureWorldAccess world, Random random, BlockPos position, Block defaultBlock, float oreChance) {
        // Replace the Data blocks with ores or bells
        Tag<Block> ORE_TAG = BlockTags.getTagGroup().getTagOrEmpty(templateOresRL);
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
     * Replaces the "bell" data block sometimes with bells.
     */
    protected static void addBells(String function, BlockPos position, StructureWorldAccess world, Random random, Collection<Block> allOreBlocks) {
        if (function.equals("bell")) {
            if (RepurposedStructures.RSAllConfig.RSWellsConfig.canHaveBells && random.nextInt(100) == 0) {
                world.setBlockState(position, Blocks.BELL.getDefaultState().with(Properties.ATTACHMENT, Attachment.CEILING), 2);
            } else {
                world.setBlockState(position, Blocks.AIR.getDefaultState(), 2);
            }
        }
    }


    /**
     * Replaces the "ores" data block with blocks specified in the ore tag.
     */
    protected static void addOres(String function, BlockPos position, StructureWorldAccess world, Random random, Collection<Block> allOreBlocks, Block defaultBlock, float oreChance) {
        if (function.equals("ores")) {
            if (!allOreBlocks.isEmpty() && random.nextFloat() < oreChance) {
                world.setBlockState(position, ((Block) allOreBlocks.toArray()[random.nextInt(allOreBlocks.size())]).getDefaultState(), 2);
            } else {
                world.setBlockState(position, defaultBlock.getDefaultState(), 2);
            }
        }
    }


    /**
     * sets 'space' data blocks to air or water based on sea level so terrain blocks wont be placed weirdly inside well The space will be done in a + shape centered on the data block
     */
    protected static void addSpace(String function, BlockPos position, StructureWorldAccess world) {
        if (function.equals("space")) {
            BlockState blockstate;
            if (position.getY() < world.getSeaLevel()) {
                blockstate = Blocks.WATER.getDefaultState();
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