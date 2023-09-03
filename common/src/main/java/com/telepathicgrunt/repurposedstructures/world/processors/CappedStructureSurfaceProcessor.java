package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class CappedStructureSurfaceProcessor extends StructureProcessor {

    public static final Codec<CappedStructureSurfaceProcessor> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(
            StructureProcessorType.SINGLE_CODEC.fieldOf("delegate").forGetter((cappedProcessor) -> { return cappedProcessor.delegate; }),
            IntProvider.POSITIVE_CODEC.fieldOf("limit").forGetter((cappedProcessor) -> { return cappedProcessor.limit; }),
            Codec.BOOL.fieldOf("allow_void_sides").orElse(false).forGetter((cappedProcessor) -> { return cappedProcessor.allowVoidSides; })
        ).apply(instance, CappedStructureSurfaceProcessor::new);
    });

    private static final StructureTemplate.StructureBlockInfo DEFAULT_AIR_BLOCK = new StructureTemplate.StructureBlockInfo(BlockPos.ZERO, Blocks.AIR.defaultBlockState(), null);
    private static final StructureTemplate.StructureBlockInfo DEFAULT_SOLID_BLOCK = new StructureTemplate.StructureBlockInfo(BlockPos.ZERO, Blocks.STONE.defaultBlockState(), null);

    private final StructureProcessor delegate;
    private final IntProvider limit;
    private final boolean allowVoidSides;

    public CappedStructureSurfaceProcessor(StructureProcessor structureProcessor, IntProvider intProvider, boolean allowVoidSides) {
        this.delegate = structureProcessor;
        this.limit = intProvider;
        this.allowVoidSides = allowVoidSides;
    }

    @Override
    public final List<StructureTemplate.StructureBlockInfo> finalizeProcessing(ServerLevelAccessor serverLevelAccessor, BlockPos nbtOriginPos, BlockPos chunkCenter, List<StructureTemplate.StructureBlockInfo> nbtOriginBlockInfo, List<StructureTemplate.StructureBlockInfo> worldOriginBlockInfo, StructurePlaceSettings structurePlaceSettings) {
        if (this.limit.getMaxValue() != 0 && !worldOriginBlockInfo.isEmpty()) {
            if (nbtOriginBlockInfo.size() != worldOriginBlockInfo.size()) {
                int listSize = nbtOriginBlockInfo.size();
                Util.logAndPauseIfInIde("Original block info list not in sync with processed list, skipping processing. Original size: " + listSize + ", Processed size: " + worldOriginBlockInfo.size());
            }
            else {
                RandomSource randomSource = RandomSource.create(serverLevelAccessor.getLevel().getSeed()).forkPositional().at(nbtOriginPos);
                int countCap = Math.min(this.limit.sample(randomSource), worldOriginBlockInfo.size());
                if (countCap >= 1) {
                    Map<BlockPos, StructureTemplate.StructureBlockInfo> nbtPosToData = new Object2ObjectArrayMap<>();
                    for (StructureTemplate.StructureBlockInfo info : worldOriginBlockInfo) {
                        nbtPosToData.put(info.pos(), info);
                    }
                    IntArrayList intArrayList = Util.toShuffledList(IntStream.range(0, worldOriginBlockInfo.size()), randomSource);
                    IntIterator intIterator = intArrayList.intIterator();
                    int executedRuns = 0;

                    while (intIterator.hasNext() && executedRuns < countCap) {
                        int currentIndex = intIterator.nextInt();
                        StructureTemplate.StructureBlockInfo structureBlockInfoOriginalNbtOrigin = nbtOriginBlockInfo.get(currentIndex);
                        StructureTemplate.StructureBlockInfo structureBlockInfoWorld = worldOriginBlockInfo.get(currentIndex);
                        if (structureBlockInfoWorld == null || structureBlockInfoWorld.state().isAir() || !structureBlockInfoWorld.state().getFluidState().isEmpty()) {
                            continue;
                        }

                        BlockPos belowPos = structureBlockInfoWorld.pos().below();
                        BlockPos abovePos = structureBlockInfoWorld.pos().above();
                        if (!this.allowVoidSides && belowPos.getY() < 0) {
                            continue;
                        }
                        if (!this.allowVoidSides && (!nbtPosToData.containsKey(belowPos) || !nbtPosToData.containsKey(abovePos))) {
                            continue;
                        }
                        BlockState belowState = nbtPosToData.getOrDefault(belowPos, DEFAULT_SOLID_BLOCK).state();
                        BlockState aboveState = nbtPosToData.getOrDefault(abovePos, DEFAULT_AIR_BLOCK).state();
                        if ((!belowState.canOcclude() || belowState.is(Blocks.JIGSAW)) ||
                            (aboveState.canOcclude() && !aboveState.is(Blocks.JIGSAW)))
                        {
                            continue;
                        }

                        StructureTemplate.StructureBlockInfo structureBlockInfo3 = this.delegate.processBlock(
                                serverLevelAccessor,
                                structureBlockInfoOriginalNbtOrigin.pos(),
                                structureBlockInfoWorld.pos(),
                                structureBlockInfoOriginalNbtOrigin,
                                structureBlockInfoWorld,
                                structurePlaceSettings);

                        if (structureBlockInfo3 != null && !structureBlockInfoWorld.equals(structureBlockInfo3)) {
                            ++executedRuns;
                            worldOriginBlockInfo.set(currentIndex, structureBlockInfo3);
                        }
                    }
                }
            }
        }

        return worldOriginBlockInfo;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.CAPPED_STRUCTURE_SURFACE_PROCESSOR.get();
    }
}
