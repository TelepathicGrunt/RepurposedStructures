package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.world.WaterloggingFixWorldSavedData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class WaterloggingFixProcessor extends StructureProcessor {

    public static final Codec<WaterloggingFixProcessor> CODEC = Codec.unit(WaterloggingFixProcessor::new);

    private WaterloggingFixProcessor() { }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader worldReader, BlockPos pos, BlockPos pos2, StructureTemplate.StructureBlockInfo infoIn1, StructureTemplate.StructureBlockInfo infoIn2, StructurePlaceSettings settings) {

        // ONLY RUN THIS IF STRUCTURE'S BLOCK IS A DRY WATERLOGGABLE BLOCK
        if (infoIn2.state.hasProperty(BlockStateProperties.WATERLOGGED) && !infoIn2.state.getValue(BlockStateProperties.WATERLOGGED)) {
            Level level = worldReader instanceof WorldGenLevel worldGenLevel ? worldGenLevel.getLevel() : (Level)worldReader;
            WaterloggingFixWorldSavedData worldSavedData = WaterloggingFixWorldSavedData.get(level);
            worldSavedData.addBlockPosToUnwaterlog(infoIn2.pos);
        }

        return infoIn2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.WATERLOGGING_FIX_PROCESSOR;
    }
}
