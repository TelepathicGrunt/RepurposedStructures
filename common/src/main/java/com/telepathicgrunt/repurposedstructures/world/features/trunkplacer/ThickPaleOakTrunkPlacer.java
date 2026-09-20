package com.telepathicgrunt.repurposedstructures.world.features.trunkplacer;

import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class ThickPaleOakTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<ThickPaleOakTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            i -> trunkPlacerParts(i).apply(i, ThickPaleOakTrunkPlacer::new));

    public ThickPaleOakTrunkPlacer(final int baseHeight, final int heightRandA, final int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return RSTrunkPlacerTypes.THICK_PALE_OAK_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(
            final WorldGenLevel level,
            final BiConsumer<BlockPos, BlockState> trunkSetter,
            final RandomSource random,
            final int treeHeight,
            final BlockPos origin,
            final TreeFeature tree
    ) {
        List<FoliagePlacer.FoliageAttachment> attachments = Lists.newArrayList();
        BlockPos below = origin.below();
        placeBelowTrunkBlock(level, trunkSetter, random, below, tree);
        placeBelowTrunkBlock(level, trunkSetter, random, below.east(), tree);
        placeBelowTrunkBlock(level, trunkSetter, random, below.south(), tree);
        placeBelowTrunkBlock(level, trunkSetter, random, below.south().east(), tree);
        Direction leanDirection = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        int leanHeight = treeHeight - random.nextInt(4);
        int leanSteps = 2 - random.nextInt(3);
        int x = origin.getX();
        int y = origin.getY();
        int z = origin.getZ();
        int tx = x;
        int tz = z;
        int yLevel = y + treeHeight - 1;

        for (int dy = 0; dy < treeHeight; dy++) {
            if (dy >= leanHeight && leanSteps > 0) {
                tx += leanDirection.getStepX();
                tz += leanDirection.getStepZ();
                leanSteps--;
            }

            int yy = y + dy;
            BlockPos blockPos = new BlockPos(tx, yy, tz);
            if (TreeFeature.isAirOrLeaves(level, blockPos)) {
                this.placeLog(level, trunkSetter, random, blockPos, tree);
                this.placeLog(level, trunkSetter, random, blockPos.east(), tree);
                this.placeLog(level, trunkSetter, random, blockPos.south(), tree);
                this.placeLog(level, trunkSetter, random, blockPos.east().south(), tree);
            }
        }

        attachments.add(new FoliagePlacer.FoliageAttachment(new BlockPos(tx, yLevel, tz), 0, true));

        for (int xOffset = -1; xOffset <= 2; xOffset++) {
            for (int zOffset = -1; zOffset <= 2; zOffset++) {
                if (xOffset < 0 || xOffset > 1 || zOffset < 0 || zOffset > 1) {
                    boolean isCorner =
                            (xOffset == -1 && zOffset == -1) ||
                            (xOffset == -1 && zOffset == 2) ||
                            (xOffset == 2 && zOffset == -1) ||
                            (xOffset == 2 && zOffset == 2);

                    int length = isCorner ? random.nextInt(3) : random.nextInt(2) + 3;

                    for (int branchY = 0; branchY < length; branchY++) {
                        this.placeLog(level, trunkSetter, random, new BlockPos(x + xOffset, yLevel - branchY - 1, z + zOffset), tree);
                    }

                    attachments.add(new FoliagePlacer.FoliageAttachment(new BlockPos(x + xOffset, yLevel, z + zOffset), 0, false));
                }
            }
        }

        return attachments;
    }
}
