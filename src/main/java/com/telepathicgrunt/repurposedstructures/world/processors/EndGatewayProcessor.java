package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.SectionPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

public class EndGatewayProcessor extends StructureProcessor {

    public static final Codec<EndGatewayProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BlockPos.CODEC.optionalFieldOf("exit_position").forGetter(config -> config.exitPos)
    ).apply(instance, instance.stable(EndGatewayProcessor::new)));

    private final Optional<BlockPos> exitPos;

    private EndGatewayProcessor(Optional<BlockPos> exitPos) {
        this.exitPos = exitPos;
    }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {

        if (structureBlockInfoWorld.state.is(Blocks.END_GATEWAY)) {
            if (this.exitPos.isPresent()) {
                CompoundTag compoundTag = structureBlockInfoWorld.nbt == null ? new CompoundTag() : structureBlockInfoWorld.nbt;
                compoundTag.put("ExitPortal", NbtUtils.writeBlockPos(this.exitPos.get()));

                return new StructureTemplate.StructureBlockInfo(
                        structureBlockInfoWorld.pos,
                        structureBlockInfoWorld.state,
                        compoundTag
                );
            }

            BlockPos currentPos = structureBlockInfoWorld.pos;
            ChunkAccess currentChunk = levelReader.getChunk(currentPos);
            int terrainY = currentChunk.getHeight(Heightmap.Types.MOTION_BLOCKING, 0, 0);
            if (terrainY <= currentChunk.getMinBuildHeight() || terrainY >= currentChunk.getMaxBuildHeight()) {
                terrainY = currentChunk.getMinBuildHeight() + 1;
                currentChunk.setBlockState(new BlockPos(0, currentChunk.getMinBuildHeight(), 0), Blocks.OBSIDIAN.defaultBlockState(), false);
            }
            CompoundTag compoundTag = structureBlockInfoWorld.nbt == null ? new CompoundTag() : structureBlockInfoWorld.nbt;
            compoundTag.put("ExitPortal", NbtUtils.writeBlockPos(new BlockPos(0, terrainY, 0)));

            return new StructureTemplate.StructureBlockInfo(
                    structureBlockInfoWorld.pos,
                    structureBlockInfoWorld.state,
                    compoundTag
            );
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.END_GATEWAY_PROCESSOR;
    }
}
