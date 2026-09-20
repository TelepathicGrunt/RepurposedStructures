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
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

/**
 * Replace blocks randomly with noise generator but preserve the properties of the block
 */
public class PieceChanceReplaceBlockProcessor implements StructureProcessor {

    public static final MapCodec<PieceChanceReplaceBlockProcessor> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("input_block").forGetter(config -> config.inputBlock),
            BlockState.CODEC.fieldOf("output_block").forGetter(config -> config.outputBlock),
            Codec.floatRange(0, 1).fieldOf("probability").forGetter(config -> config.probability),
            Codec.INT.fieldOf("seed_random_addition").forGetter(config -> config.seedRandomAddition)
    ).apply(instance, instance.stable(PieceChanceReplaceBlockProcessor::new)));

    private final Block inputBlock;
    private final BlockState outputBlock;
    private final float probability;
    private final int seedRandomAddition;

    public PieceChanceReplaceBlockProcessor(Block inputBlock, BlockState outputBlock, float probability, int seedRandomAddition) {
        this.inputBlock = inputBlock;
        this.outputBlock = outputBlock;
        this.probability = probability;
        this.seedRandomAddition = seedRandomAddition;
    }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos targetPosition, BlockPos referencePos, BlockPos templateRelativePos, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {
        if(structureBlockInfoWorld.state().getBlock() == inputBlock) {
            RandomSource randomSource = structurePlacementData.getRandom(targetPosition.above(seedRandomAddition));
            if (randomSource.nextFloat() < probability) {
                return new StructureTemplate.StructureBlockInfo(structureBlockInfoWorld.pos(), outputBlock, null);
            }
        }
        return structureBlockInfoWorld;
    }

    @Override
    public MapCodec<? extends StructureProcessor> codec() {
        return RSProcessors.PIECE_CHANCE_REPLACE_BLOCK_PROCESSOR.get();
    }
}
