package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.utils.OpenSimplex2F;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

/**
 * Replace blocks randomly with noise generator but preserve the properties of the block
 */
public class NoiseReplaceWithPropertiesProcessor implements StructureProcessor {

    public static final MapCodec<NoiseReplaceWithPropertiesProcessor> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("input_block").forGetter(config -> config.inputBlock),
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("output_block").forGetter(config -> config.outputBlock),
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
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos targetPosition, BlockPos referencePos, BlockPos templateRelativePos, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {
        setSeed(level instanceof WorldGenRegion ? ((WorldGenRegion) level).getSeed() : 0);

        if(structureBlockInfoWorld.state().getBlock() == inputBlock) {
            BlockPos worldPos = structureBlockInfoWorld.pos();
            double noiseVal = noiseGenerator.noise3_Classic(worldPos.getX() * xzScale, worldPos.getY() * yScale, worldPos.getZ() * xzScale);

            if((noiseVal / 2D) + 0.5D < threshold) {
                BlockState newBlockState = outputBlock.defaultBlockState();
                newBlockState = GeneralUtils.copyBlockProperties(structureBlockInfoWorld.state(), newBlockState);
                return new StructureTemplate.StructureBlockInfo(structureBlockInfoWorld.pos(), newBlockState, structureBlockInfoWorld.nbt());
            }
        }
        return structureBlockInfoWorld;
    }

    @Override
    public MapCodec<? extends StructureProcessor> codec() {
        return RSProcessors.NOISE_REPLACE_WITH_PROPERTIES_PROCESSOR.get();
    }
}
