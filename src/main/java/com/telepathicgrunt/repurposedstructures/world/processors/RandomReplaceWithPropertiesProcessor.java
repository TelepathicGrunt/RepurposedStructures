package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.state.property.Property;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkRandom;

import java.util.List;
import java.util.Random;

/**
 * Replace blocks randomly but preserve the properties of the block
 */
public class RandomReplaceWithPropertiesProcessor extends StructureProcessor {

    public static final Codec<RandomReplaceWithPropertiesProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Registry.BLOCK.fieldOf("input_block").forGetter(config -> config.inputBlock),
            Registry.BLOCK.fieldOf("output_block").forGetter(config -> config.outputBlock),
            Codec.floatRange(0, 1).fieldOf("probability").forGetter(config -> config.probability)
    ).apply(instance, instance.stable(RandomReplaceWithPropertiesProcessor::new)));

    private final Block inputBlock;
    private final Block outputBlock;
    private final float probability;

    public RandomReplaceWithPropertiesProcessor(Block inputBlock, Block outputBlock, float probability) {
        this.inputBlock = inputBlock;
        this.outputBlock = outputBlock;
        this.probability = probability;
    }

    @Override
    public Structure.StructureBlockInfo process(WorldView worldReader, BlockPos pos, BlockPos pos2, Structure.StructureBlockInfo infoIn1, Structure.StructureBlockInfo infoIn2, StructurePlacementData settings) {
        if(infoIn2.state.getBlock().is(inputBlock)){
            BlockPos worldPos = infoIn2.pos;
            Random random = new ChunkRandom();
            random.setSeed(worldPos.asLong() * worldPos.getY());

            if(random.nextFloat() < probability){
                BlockState newBlockState = outputBlock.getDefaultState();
                for(Property<?> property : infoIn2.state.getProperties()){
                    if(newBlockState.contains(property)){
                        newBlockState = getStateWithProperty(newBlockState, infoIn2.state, property);
                    }
                }
                return new Structure.StructureBlockInfo(infoIn2.pos, newBlockState, infoIn2.tag);
            }
        }
        return infoIn2;
    }

    private <T extends Comparable<T>> BlockState getStateWithProperty(BlockState state, BlockState stateToCopy, Property<T> property){
        return state.with(property, stateToCopy.get(property));
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.RANDOM_REPLACE_WITH_PROPERTIES_PROCESSOR;
    }
}
