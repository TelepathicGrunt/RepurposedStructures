package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

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
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader worldReader, BlockPos pos, BlockPos pos2, StructureTemplate.StructureBlockInfo infoIn1, StructureTemplate.StructureBlockInfo infoIn2, StructurePlaceSettings settings) {
        if(infoIn2.state.getBlock() == inputBlock){
            BlockPos worldPos = infoIn2.pos;
            Random random = new WorldgenRandom();
            random.setSeed(worldPos.asLong() * worldPos.getY());

            if(random.nextFloat() < probability){
                BlockState newBlockState = outputBlock.defaultBlockState();
                for(Property<?> property : infoIn2.state.getProperties()){
                    if(newBlockState.hasProperty(property)){
                        newBlockState = getStateWithProperty(newBlockState, infoIn2.state, property);
                    }
                }
                return new StructureTemplate.StructureBlockInfo(infoIn2.pos, newBlockState, infoIn2.nbt);
            }
        }
        return infoIn2;
    }

    private <T extends Comparable<T>> BlockState getStateWithProperty(BlockState state, BlockState stateToCopy, Property<T> property){
        return state.setValue(property, stateToCopy.getValue(property));
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.RANDOM_REPLACE_WITH_PROPERTIES_PROCESSOR;
    }
}
