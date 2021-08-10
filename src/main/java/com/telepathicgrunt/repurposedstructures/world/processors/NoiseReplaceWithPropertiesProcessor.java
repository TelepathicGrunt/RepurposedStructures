package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.utils.OpenSimplex2F;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

/**
 * Replace blocks randomly with noise generator but preserve the properties of the block
 */
public class NoiseReplaceWithPropertiesProcessor extends StructureProcessor {

    public static final Codec<NoiseReplaceWithPropertiesProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Registry.BLOCK.fieldOf("input_block").forGetter(config -> config.inputBlock),
            Registry.BLOCK.fieldOf("output_block").forGetter(config -> config.outputBlock),
            Codec.floatRange(0, 1).fieldOf("threshold").forGetter(config -> config.threshold),
            Codec.FLOAT.fieldOf("xz_scale").forGetter(config -> config.xzScale),
            Codec.FLOAT.fieldOf("y_scale").forGetter(config -> config.yScale)
    ).apply(instance, instance.stable(NoiseReplaceWithPropertiesProcessor::new)));

    private final Block inputBlock;
    private final Block outputBlock;
    private final float threshold;
    private final float xzScale;
    private final float yScale;
    protected long seed;
    private OpenSimplex2F noiseGenerator = null;

    public NoiseReplaceWithPropertiesProcessor(Block inputBlock, Block outputBlock, float threshold, float xzScale, float yScale) {
        this.inputBlock = inputBlock;
        this.outputBlock = outputBlock;
        this.threshold = threshold;
        this.xzScale = xzScale;
        this.yScale = yScale;
    }

    public void setSeed(long seed) {
        if (this.seed != seed || noiseGenerator == null) {
            noiseGenerator = new OpenSimplex2F(seed);
            this.seed = seed;
        }
    }

    @Override
    public Template.BlockInfo processBlock(IWorldReader worldReader, BlockPos pos, BlockPos pos2, Template.BlockInfo infoIn1, Template.BlockInfo infoIn2, PlacementSettings settings) {
        setSeed(worldReader instanceof WorldGenRegion ? ((WorldGenRegion) worldReader).getSeed() : 0);

        if(infoIn2.state.getBlock() == inputBlock){
            BlockPos worldPos = infoIn2.pos;
            double noiseVal = noiseGenerator.noise3_Classic(worldPos.getX() * xzScale, worldPos.getY() * yScale, worldPos.getZ() * xzScale);

            if((noiseVal / 2D) + 0.5D < threshold){
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
        return RSProcessors.NOISE_REPLACE_WITH_PROPERTIES_PROCESSOR;
    }
}
