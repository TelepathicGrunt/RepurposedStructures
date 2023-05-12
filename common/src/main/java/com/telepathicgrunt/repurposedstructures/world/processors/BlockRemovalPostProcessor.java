package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BlockRemovalPostProcessor extends StructureProcessor {

    public static final Codec<BlockRemovalPostProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BuiltInRegistries.BLOCK.byNameCodec().listOf().fieldOf("remove_blocks").orElse(new ArrayList<>()).xmap(HashSet::new, ArrayList::new).forGetter(config -> config.removeBlocks)
    ).apply(instance, instance.stable(BlockRemovalPostProcessor::new)));

    private final HashSet<Block> removeBlocks;

    private BlockRemovalPostProcessor(HashSet<Block> removeBlocks) {
        this.removeBlocks = removeBlocks;
    }

    @Override
    public List<StructureTemplate.StructureBlockInfo> finalizeProcessing(ServerLevelAccessor serverLevelAccessor, BlockPos blockPos, BlockPos blockPos2, List<StructureTemplate.StructureBlockInfo> list, List<StructureTemplate.StructureBlockInfo> list2, StructurePlaceSettings structurePlaceSettings) {
        if (removeBlocks.isEmpty()) {
            return list2;
        }

        for(int i = list2.size() - 1; i >= 0; i--) {
            if (removeBlocks.contains(list2.get(i).state().getBlock())) {
                list2.remove(i);
            }
        }
        return list2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.BLOCK_REMOVAL_POST_PROCESSOR.get();
    }
}
