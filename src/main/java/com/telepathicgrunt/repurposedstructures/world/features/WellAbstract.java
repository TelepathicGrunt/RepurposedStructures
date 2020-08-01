package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BellAttachment;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.StructureMode;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

import java.util.Collection;
import java.util.Random;


public abstract class WellAbstract extends Feature<NoFeatureConfig> {
    private TemplateManager templatemanager = null;
    protected PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null);

    public WellAbstract(Codec<NoFeatureConfig> config) {
        super(config);
    }


    protected Template generateTemplate(ResourceLocation templateRL, ISeedReader world, Random random, BlockPos position) {

        // cache to save time and speed
        if (templatemanager == null) templatemanager = ((ServerWorld) world.getWorld()).getStructureTemplateManager();

        // Dont cache this as templatemanager already does caching behind the scenes and users might
        // override the file later with datapacks in world somehow. (maybe)
        Template template = templatemanager.getTemplateDefaulted(templateRL);

        if (template == null) {
            RepurposedStructures.LOGGER.warn(templateRL.toString() + " NTB does not exist!");
            return null;
        }

        // Creates the well centered on our spot
        BlockPos offset = new BlockPos(-template.getSize().getX() / 2, 0, -template.getSize().getZ() / 2);
        template.place(world, position.add(offset), placementsettings, random);
        return template;
    }


    protected void handleDataBlocks(ResourceLocation templateOresRL, Template template, ISeedReader world, Random random, BlockPos position, Block defaultBlock, float oreChance) {
        // Replace the Data blocks with ores or bells
        ITag<Block> ORE_TAG = BlockTags.getCollection().getOrCreate(templateOresRL);
        Collection<Block> allOreBlocks = ORE_TAG.values();
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
     * Replaces the "bell" data block sometimes with bells.
     */
    protected static void addBells(String function, BlockPos position, ISeedReader world, Random random, Collection<Block> allOreBlocks) {
        if (function.equals("bell")) {
            if (RepurposedStructures.RSWellsConfig.canHaveBells.get() && random.nextInt(100) == 0) {
                world.setBlockState(position, Blocks.BELL.getDefaultState().with(BlockStateProperties.BELL_ATTACHMENT, BellAttachment.CEILING), 2);
            } else {
                world.setBlockState(position, Blocks.AIR.getDefaultState(), 2);
            }
        }
    }


    /**
     * Replaces the "ores" data block with blocks specified in the ore tag.
     */
    protected static void addOres(String function, BlockPos position, ISeedReader world, Random random, Collection<Block> allOreBlocks, Block defaultBlock, float oreChance) {
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
    protected static void addSpace(String function, BlockPos position, ISeedReader world) {
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