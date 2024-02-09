package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class SimpleBlockWithFluidTick extends Feature<SimpleBlockConfiguration> {

    public SimpleBlockWithFluidTick(Codec<SimpleBlockConfiguration> config) {
        super(config);
    }

    @Override
    public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> context) {
        SimpleBlockConfiguration simpleBlockConfiguration = context.config();
        WorldGenLevel worldGenLevel = context.level();
        BlockPos blockPos = context.origin();
        BlockState blockState = simpleBlockConfiguration.toPlace().getState(context.random(), blockPos);
        if (!blockState.canSurvive(worldGenLevel, blockPos)) {
            return false;
        }

        if (blockState.getBlock() instanceof DoublePlantBlock) {
            if (!worldGenLevel.isEmptyBlock(blockPos.above())) return false;
            DoublePlantBlock.placeAt(worldGenLevel, blockState, blockPos, 2);
            return true;
        }
        else {
            worldGenLevel.setBlock(blockPos, blockState, 2);
            if (!blockState.getFluidState().isEmpty()) {
                worldGenLevel.scheduleTick(blockPos, blockState.getFluidState().getType(), 0);
            }
        }
        return true;
    }
}