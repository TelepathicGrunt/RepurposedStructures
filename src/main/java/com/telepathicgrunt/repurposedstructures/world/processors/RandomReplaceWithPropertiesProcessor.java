package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.Property;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

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
    public Template.BlockInfo processBlock(IWorldReader worldReader, BlockPos pos, BlockPos pos2, Template.BlockInfo infoIn1, Template.BlockInfo infoIn2, PlacementSettings settings) {
        if(infoIn2.state.getBlock().is(inputBlock)){
            BlockPos worldPos = infoIn2.pos;
            Random random = new SharedSeedRandom();
            random.setSeed(worldPos.asLong() * worldPos.getY());

            if(random.nextFloat() < probability){
                BlockState newBlockState = outputBlock.defaultBlockState();
                for(Property<?> property : infoIn2.state.getProperties()){
                    if(newBlockState.hasProperty(property)){
                        newBlockState = getStateWithProperty(newBlockState, infoIn2.state, property);
                    }
                }
                return new Template.BlockInfo(infoIn2.pos, newBlockState, infoIn2.nbt);
            }
        }
        return infoIn2;
    }

    private <T extends Comparable<T>> BlockState getStateWithProperty(BlockState state, BlockState stateToCopy, Property<T> property){
        return state.setValue(property, stateToCopy.getValue(property));
    }

    @Override
    protected IStructureProcessorType<?> getType() {
        return RSProcessors.RANDOM_REPLACE_WITH_PROPERTIES_PROCESSOR;
    }
}
