package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.List;

public class PostProcessListProcessor extends StructureProcessor {

    public static final Codec<PostProcessListProcessor> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(
            StructureProcessorType.SINGLE_CODEC.listOf().fieldOf("delegates").forGetter((processor) -> { return processor.delegates; })
        ).apply(instance, PostProcessListProcessor::new);
    });

    private final List<StructureProcessor> delegates;

    public PostProcessListProcessor(List<StructureProcessor> structureProcessors) {
        this.delegates = structureProcessors;
    }

    @Override
    public final List<StructureTemplate.StructureBlockInfo> finalizeProcessing(ServerLevelAccessor serverLevelAccessor, BlockPos nbtOriginPos, BlockPos chunkCenter, List<StructureTemplate.StructureBlockInfo> nbtOriginBlockInfo, List<StructureTemplate.StructureBlockInfo> worldOriginBlockInfo, StructurePlaceSettings structurePlaceSettings) {
        if (!worldOriginBlockInfo.isEmpty()) {
            if (nbtOriginBlockInfo.size() != worldOriginBlockInfo.size()) {
                int listSize = nbtOriginBlockInfo.size();
                Util.logAndPauseIfInIde("Original block info list not in sync with processed list, skipping processing. Original size: " + listSize + ", Processed size: " + worldOriginBlockInfo.size());
            }
            else {
                for (int i = 0; i < worldOriginBlockInfo.size(); i++) {
                    StructureTemplate.StructureBlockInfo structureBlockInfoOriginalNbtOrigin = nbtOriginBlockInfo.get(i);
                    StructureTemplate.StructureBlockInfo structureBlockInfoWorld = worldOriginBlockInfo.get(i);

                    StructureTemplate.StructureBlockInfo structureBlockInfoResult = structureBlockInfoWorld;

                    for (StructureProcessor processor : this.delegates) {
                        structureBlockInfoResult = processor.processBlock(
                                serverLevelAccessor,
                                structureBlockInfoOriginalNbtOrigin.pos(),
                                structureBlockInfoResult.pos(),
                                structureBlockInfoOriginalNbtOrigin,
                                structureBlockInfoResult,
                                structurePlaceSettings);

                        if (structureBlockInfoResult == null) {
                            break;
                        }
                    }

                    if (structureBlockInfoResult != null && !structureBlockInfoWorld.equals(structureBlockInfoResult)) {
                        worldOriginBlockInfo.set(i, structureBlockInfoResult);
                    }
                }
            }
        }

        return worldOriginBlockInfo;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.POST_PROCESS_LIST_PROCESSOR.get();
    }
}
