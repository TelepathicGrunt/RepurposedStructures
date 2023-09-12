package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO: rename in 1.21
public class CappedStructureSurfaceProcessor extends StructureProcessor {

    public static final Codec<CappedStructureSurfaceProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
        StructureProcessorType.SINGLE_CODEC.fieldOf("delegate").forGetter((cappedProcessor) -> { return cappedProcessor.delegate; }),
        Codec.BOOL.fieldOf("allow_void_sides").orElse(false).forGetter((cappedProcessor) -> { return cappedProcessor.allowVoidSides; })
    ).apply(instance, CappedStructureSurfaceProcessor::new));

    private static final Pair<StructureTemplate.StructureBlockInfo, Integer> DEFAULT_AIR_BLOCK = Pair.of(new StructureTemplate.StructureBlockInfo(BlockPos.ZERO, Blocks.AIR.defaultBlockState(), null), 0);
    private static final Pair<StructureTemplate.StructureBlockInfo, Integer> DEFAULT_SOLID_BLOCK = Pair.of(new StructureTemplate.StructureBlockInfo(BlockPos.ZERO, Blocks.STONE.defaultBlockState(), null), 0);

    private final StructureProcessor delegate;
    private final boolean allowVoidSides;

    public CappedStructureSurfaceProcessor(StructureProcessor structureProcessor, boolean allowVoidSides) {
        this.delegate = structureProcessor;
        this.allowVoidSides = allowVoidSides;
    }

    @Override
    public final List<StructureTemplate.StructureBlockInfo> finalizeProcessing(ServerLevelAccessor serverLevelAccessor, BlockPos nbtOriginPos, BlockPos chunkCenter, List<StructureTemplate.StructureBlockInfo> nbtOriginBlockInfo, List<StructureTemplate.StructureBlockInfo> worldOriginBlockInfo, StructurePlaceSettings structurePlaceSettings) {
        if (!worldOriginBlockInfo.isEmpty()) {
            if (nbtOriginBlockInfo.size() != worldOriginBlockInfo.size()) {
                int listSize = nbtOriginBlockInfo.size();
                Util.logAndPauseIfInIde("Original block info list not in sync with processed list, skipping processing. Original size: " + listSize + ", Processed size: " + worldOriginBlockInfo.size());
            }
            else {
                BoundingBox boundingBox = structurePlaceSettings.getBoundingBox() == null ? BoundingBox.infinite() : structurePlaceSettings.getBoundingBox();

                Map<BlockPos, Pair<StructureTemplate.StructureBlockInfo, Integer>> nbtPosToData = new Object2ObjectArrayMap<>();
                for (int index = 0; index < worldOriginBlockInfo.size(); index++) {
                    StructureTemplate.StructureBlockInfo info = worldOriginBlockInfo.get(index);
                    if (boundingBox.isInside(info.pos())) {
                        nbtPosToData.put(info.pos(), Pair.of(info, index));
                    }
                }
                List<BlockPos> shuffledPositionList = new ArrayList<>(nbtPosToData.keySet());
                Collections.shuffle(shuffledPositionList);
                Iterator<BlockPos> iterator = shuffledPositionList.iterator();

                while (iterator.hasNext()) {
                    BlockPos currentPosition = iterator.next();
                    Pair<StructureTemplate.StructureBlockInfo, Integer> currentInfo = nbtPosToData.get(currentPosition);
                    StructureTemplate.StructureBlockInfo structureBlockInfoOriginalNbtOrigin = nbtOriginBlockInfo.get(currentInfo.getSecond());
                    StructureTemplate.StructureBlockInfo structureBlockInfoWorld = worldOriginBlockInfo.get(currentInfo.getSecond());
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
                    BlockState belowState = nbtPosToData.getOrDefault(belowPos, DEFAULT_SOLID_BLOCK).getFirst().state();
                    BlockState aboveState = nbtPosToData.getOrDefault(abovePos, DEFAULT_AIR_BLOCK).getFirst().state();
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
                        worldOriginBlockInfo.set(currentInfo.getSecond(), structureBlockInfo3);
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
