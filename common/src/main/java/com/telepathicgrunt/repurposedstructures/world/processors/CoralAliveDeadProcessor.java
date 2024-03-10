package com.telepathicgrunt.repurposedstructures.world.processors;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Kill coral in colder areas
 */
public class CoralAliveDeadProcessor extends StructureProcessor {

    public static final Codec<CoralAliveDeadProcessor> CODEC = Codec.unit(CoralAliveDeadProcessor::new);
    private static final Map<Block, Block> ALIVE_TO_DEAD_CORAL = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(Blocks.BRAIN_CORAL, Blocks.DEAD_BRAIN_CORAL),
            new AbstractMap.SimpleEntry<>(Blocks.FIRE_CORAL, Blocks.DEAD_FIRE_CORAL),
            new AbstractMap.SimpleEntry<>(Blocks.BUBBLE_CORAL, Blocks.DEAD_BUBBLE_CORAL),
            new AbstractMap.SimpleEntry<>(Blocks.HORN_CORAL, Blocks.DEAD_HORN_CORAL),
            new AbstractMap.SimpleEntry<>(Blocks.TUBE_CORAL, Blocks.DEAD_TUBE_CORAL),
            new AbstractMap.SimpleEntry<>(Blocks.BRAIN_CORAL_FAN, Blocks.DEAD_BRAIN_CORAL_FAN),
            new AbstractMap.SimpleEntry<>(Blocks.FIRE_CORAL_FAN, Blocks.DEAD_FIRE_CORAL_FAN),
            new AbstractMap.SimpleEntry<>(Blocks.BUBBLE_CORAL_FAN, Blocks.DEAD_BUBBLE_CORAL_FAN),
            new AbstractMap.SimpleEntry<>(Blocks.HORN_CORAL_FAN, Blocks.DEAD_HORN_CORAL_FAN),
            new AbstractMap.SimpleEntry<>(Blocks.TUBE_CORAL_FAN, Blocks.DEAD_TUBE_CORAL_FAN),
            new AbstractMap.SimpleEntry<>(Blocks.BRAIN_CORAL_WALL_FAN, Blocks.DEAD_BRAIN_CORAL_WALL_FAN),
            new AbstractMap.SimpleEntry<>(Blocks.FIRE_CORAL_WALL_FAN, Blocks.DEAD_FIRE_CORAL_WALL_FAN),
            new AbstractMap.SimpleEntry<>(Blocks.BUBBLE_CORAL_WALL_FAN, Blocks.DEAD_BUBBLE_CORAL_WALL_FAN),
            new AbstractMap.SimpleEntry<>(Blocks.HORN_CORAL_WALL_FAN, Blocks.DEAD_HORN_CORAL_WALL_FAN),
            new AbstractMap.SimpleEntry<>(Blocks.TUBE_CORAL_WALL_FAN, Blocks.DEAD_TUBE_CORAL_WALL_FAN),
            new AbstractMap.SimpleEntry<>(Blocks.BRAIN_CORAL_BLOCK, Blocks.DEAD_BRAIN_CORAL_BLOCK),
            new AbstractMap.SimpleEntry<>(Blocks.FIRE_CORAL_BLOCK, Blocks.DEAD_FIRE_CORAL_BLOCK),
            new AbstractMap.SimpleEntry<>(Blocks.BUBBLE_CORAL_BLOCK, Blocks.DEAD_BUBBLE_CORAL_BLOCK),
            new AbstractMap.SimpleEntry<>(Blocks.HORN_CORAL_BLOCK, Blocks.DEAD_HORN_CORAL_BLOCK),
            new AbstractMap.SimpleEntry<>(Blocks.TUBE_CORAL_BLOCK, Blocks.DEAD_TUBE_CORAL_BLOCK)
    );

    private CoralAliveDeadProcessor() {}

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader worldView, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {

        if (ALIVE_TO_DEAD_CORAL.containsKey(structureBlockInfoWorld.state().getBlock())) {
            Holder<Biome> biome = worldView.getBiome(structureBlockInfoWorld.pos());
            float biomeTemp = biome.value().getBaseTemperature();
            String biomeNamespace = biome.unwrapKey().get().location().getNamespace();
            String biomePath = biome.unwrapKey().get().location().getPath();

            // Neutral temp
            if (!GeneralUtils.nameMatch(biomePath, "hot", "tropic", "warm", "cold", "chilly", "frozen", "snow", "ice", "frost") ||
                (!GeneralUtils.nameExactMatch(biomeNamespace, "minecraft")
                && biomeTemp >= 0.5f
                && biomeTemp < 0.9f))
            {
                if (structurePlacementData.getRandom(structureBlockInfoWorld.pos()).nextFloat() < 0.5f) {
                    return new StructureTemplate.StructureBlockInfo(
                            structureBlockInfoWorld.pos(),
                            GeneralUtils.copyBlockProperties(
                                    structureBlockInfoWorld.state(),
                                    ALIVE_TO_DEAD_CORAL.get(structureBlockInfoWorld.state().getBlock()).defaultBlockState()),
                            structureBlockInfoWorld.nbt());
                }
            }
            // Cold temp
            else if (GeneralUtils.nameMatch(biomePath, "cold", "chilly") ||
                    (!GeneralUtils.nameExactMatch(biomeNamespace, "minecraft")
                    && biomeTemp >= 0.0f
                    && biomeTemp < 0.5f))
            {
                return new StructureTemplate.StructureBlockInfo(
                        structureBlockInfoWorld.pos(),
                        GeneralUtils.copyBlockProperties(
                                structureBlockInfoWorld.state(),
                                ALIVE_TO_DEAD_CORAL.get(structureBlockInfoWorld.state().getBlock()).defaultBlockState()),
                        structureBlockInfoWorld.nbt());
            }
            // Frozen temp
            else if (GeneralUtils.nameMatch(biomePath, "frozen", "snow", "ice", "frost") ||
                    (!GeneralUtils.nameExactMatch(biomeNamespace, "minecraft")
                    && biomeTemp < 0.0f))
            {
                return new StructureTemplate.StructureBlockInfo(
                        structureBlockInfoWorld.pos(),
                        GeneralUtils.copyBlockProperties(
                                structureBlockInfoWorld.state(),
                                ALIVE_TO_DEAD_CORAL.get(structureBlockInfoWorld.state().getBlock()).defaultBlockState()),
                        structureBlockInfoWorld.nbt());
            }
        }

        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.CORAL_ALIVE_DEAD_PROCESSOR.get();
    }
}
