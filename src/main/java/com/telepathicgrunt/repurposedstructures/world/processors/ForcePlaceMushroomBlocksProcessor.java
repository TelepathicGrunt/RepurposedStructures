package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

/**
 * For fixing https://bugs.mojang.com/browse/MC-213695 bug
 */
public class ForcePlaceMushroomBlocksProcessor extends StructureProcessor {

    public static final Codec<ForcePlaceMushroomBlocksProcessor> CODEC = Codec.unit(ForcePlaceMushroomBlocksProcessor::new);
    private ForcePlaceMushroomBlocksProcessor() { }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(structureBlockInfoWorld.pos);
        if (structureBlockInfoWorld.state.getBlock() instanceof MushroomBlock) {
            levelReader.getChunk(mutable).setBlockState(mutable, structureBlockInfoWorld.state, false);
            return null;
        }

        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.FORCE_PLACE_MUSHROOM_BLOCKS_PROCESSOR;
    }
}
