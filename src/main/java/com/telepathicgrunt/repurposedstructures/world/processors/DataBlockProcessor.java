package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.argument.BlockArgumentParser;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

import java.util.Random;

import static java.lang.Integer.parseInt;

/**
 * POOL ENTRY MUST BE USING legacy_single_pool_element OR ELSE THE STRUCTURE BLOCK IS REMOVED BEFORE THIS PROCESSOR RUNS.
 */
public class DataBlockProcessor extends StructureProcessor {

    private enum DATA_PROCESSOR_MODE {
        PILLARS("-");

        private final String symbol;
        DATA_PROCESSOR_MODE(String symbol) {
            this.symbol = symbol;
        }
    }

    public static final Codec<DataBlockProcessor> CODEC = Codec.unit(DataBlockProcessor::new);
    private DataBlockProcessor() { }

    @Override
    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfoLocal, Structure.StructureBlockInfo structureBlockInfoWorld, StructurePlacementData structurePlacementData) {
        BlockState blockState = structureBlockInfoWorld.state;
        if (blockState.isOf(Blocks.STRUCTURE_BLOCK)) {
            String metadata = structureBlockInfoWorld.tag.getString("metadata");
            BlockPos worldPos = structureBlockInfoWorld.pos;

            try {
                // Pillar mode activated
                if(metadata.contains(DATA_PROCESSOR_MODE.PILLARS.symbol)){
                    String[] splitString = metadata.split(DATA_PROCESSOR_MODE.PILLARS.symbol);

                    // Parses the data block's name field to get direction, blockstate, and depth
                    Direction direction = Direction.valueOf(splitString[0].toUpperCase());
                    BlockArgumentParser blockArgumentParser = new BlockArgumentParser(new StringReader(splitString[1]), false);
                    blockArgumentParser.parse(true);
                    BlockState replacementState = blockArgumentParser.getBlockState();
                    BlockState currentBlock = worldView.getBlockState(worldPos);
                    BlockPos.Mutable currentPos = new BlockPos.Mutable().set(worldPos);
                    int depth = splitString.length > 2 ? parseInt(splitString[2]) + 1 : 256;

                    // Creates the pillars in the world that replaces air and liquids
                    while((currentBlock.isAir() || currentBlock.getMaterial().isLiquid()) &&
                            currentPos.getY() <= worldView.getDimension().getLogicalHeight() &&
                            currentPos.getY() >= 0 &&
                            currentPos.isWithinDistance(worldPos, depth)
                    ){
                        worldView.getChunk(currentPos).setBlockState(currentPos, replacementState, false);
                        currentPos.move(direction);
                        currentBlock = worldView.getBlockState(currentPos);
                    }

                    // Replaces the data block itself
                    return replacementState.isOf(Blocks.STRUCTURE_VOID) ? null : new Structure.StructureBlockInfo(worldPos, replacementState, null);
                }
            }
            catch (CommandSyntaxException var11) {
                throw new RuntimeException(var11);
            }
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.DATA_BLOCK_PROCESSORS;
    }
}
