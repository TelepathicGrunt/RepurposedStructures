package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * FOR ELEMENTS USING legacy_single_pool_element AND WANTS AIR TO REPLACE TERRAIN.
 */
public class AirProcessor extends StructureProcessor {

    public static final Codec<AirProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Registry.BLOCK.listOf().fieldOf("ignore_block").orElse(new ArrayList<>()).xmap(HashSet::new, ArrayList::new).forGetter(config -> config.blocksToIgnore)
    ).apply(instance, instance.stable(AirProcessor::new)));

    private final HashSet<Block> blocksToIgnore;

    private AirProcessor(HashSet<Block> blocksToIgnore) {
        this.blocksToIgnore = blocksToIgnore;
    }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader worldView, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {
        if (structureBlockInfoWorld.state.isAir()) {
            BlockPos currentPos = structureBlockInfoWorld.pos;
            ChunkAccess chunk = worldView.getChunk(currentPos);
            if (currentPos.getY() >= chunk.getMinBuildHeight() && currentPos.getY() < chunk.getMaxBuildHeight()){
                if(!blocksToIgnore.contains(chunk.getBlockState(currentPos).getBlock())){

                    // Copy what vanilla ores do.
                    // This bypasses the PaletteContainer's lock as it was throwing `Accessing PalettedContainer from multiple threads` crash
                    // even though everything seemed to be safe and fine.
                    int sectionYIndex = chunk.getSectionIndex(currentPos.getY());
                    LevelChunkSection levelChunkSection = chunk.getOrCreateSection(sectionYIndex);
                    if (levelChunkSection != LevelChunk.EMPTY_SECTION) {

                        levelChunkSection.setBlockState(
                                SectionPos.sectionRelative(currentPos.getX()),
                                SectionPos.sectionRelative(currentPos.getY()),
                                SectionPos.sectionRelative(currentPos.getZ()),
                                structureBlockInfoWorld.state,
                                false);
                    }
                }
            }
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.AIR_PROCESSOR;
    }
}
