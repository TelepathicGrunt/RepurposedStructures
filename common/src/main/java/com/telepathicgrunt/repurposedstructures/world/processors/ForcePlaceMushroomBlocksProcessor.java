package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.MapCodec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

/**
 * For fixing https://bugs.mojang.com/browse/MC-213695 bug
 */
public class ForcePlaceMushroomBlocksProcessor implements StructureProcessor {

    public static final MapCodec<ForcePlaceMushroomBlocksProcessor> CODEC = MapCodec.unit(ForcePlaceMushroomBlocksProcessor::new);
    private ForcePlaceMushroomBlocksProcessor() { }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos targetPosition, BlockPos referencePos, BlockPos templateRelativePos, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(structureBlockInfoWorld.pos());
        if (structureBlockInfoWorld.state().getBlock() instanceof MushroomBlock) {
            level.getChunk(mutable).setBlockState(mutable, structureBlockInfoWorld.state(), Block.UPDATE_CLIENTS);
            return null;
        }

        return structureBlockInfoWorld;
    }

    @Override
    public MapCodec<? extends StructureProcessor> codec() {
        return RSProcessors.FORCE_PLACE_MUSHROOM_BLOCKS_PROCESSOR.get();
    }
}
