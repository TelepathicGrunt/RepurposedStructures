package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSGenericConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LandBasedEndStructure <C extends RSGenericConfig> extends GenericJigsawStructure<C> {

    public LandBasedEndStructure(Codec<C> codec) {
        super(codec, LandBasedEndStructure::isLandFeatureChunk, LandBasedEndStructure::generateLandPieces);
    }

    protected static <CC extends RSGenericConfig> boolean isLandFeatureChunk(PieceGeneratorSupplier.Context<CC> context) {
        int radius = 1000;
        int xBlockPos = context.chunkPos().getMinBlockX();
        int zBlockPos = context.chunkPos().getMinBlockZ();
        if((xBlockPos * xBlockPos) + (zBlockPos * zBlockPos) <= radius * radius) {
            return false;
        }

        return GenericJigsawStructure.isGenericFeatureChunk(context) && getTerrainHeight(context.chunkPos(), context.chunkGenerator(), context.heightAccessor()) >= Math.min(GeneralUtils.getMaxTerrainLimit(context.chunkGenerator()), 50);
    }

    // must be on land
    private static int getTerrainHeight(ChunkPos chunkPos1, ChunkGenerator chunkGenerator, LevelHeightAccessor heightLimitView) {
        int xPos = chunkPos1.x << 4;
        int zPos = chunkPos1.z << 4;
        int height = chunkGenerator.getFirstOccupiedHeight(xPos, zPos, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);

        BlockPos pos = new BlockPos(xPos, GeneralUtils.getMaxTerrainLimit(chunkGenerator), zPos);
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for(Direction direction : Direction.Plane.HORIZONTAL) {
            mutable.set(pos).move(direction, 16);
            height = Math.min(height, chunkGenerator.getFirstOccupiedHeight(mutable.getX(), mutable.getZ(), Heightmap.Types.WORLD_SURFACE_WG, heightLimitView));
        }

        return height;
    }

    public static <CC extends RSGenericConfig> Optional<PieceGenerator<CC>> generateLandPieces(PieceGeneratorSupplier.Context<CC> context) {
        CC config = context.config();
        BlockPos blockpos = new BlockPos(context.chunkPos().getMinBlockX(), config.setFixedYSpawn, context.chunkPos().getMinBlockZ());

        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(config.startPool, config.size),
                GeneralUtils.getCsfNameForConfig(config, context.registryAccess()),
                blockpos,
                !config.doNotUseHeightmap,
                !config.doNotUseHeightmap,
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                config.poolsThatIgnoreBoundaries,
                (structurePiecesBuilder, pieces) -> {
                    GeneralUtils.centerAllPieces(blockpos, pieces);

                    BoundingBox box = pieces.get(0).getBoundingBox();
                    BlockPos centerPos = new BlockPos(box.getCenter());
                    int radius = (int) Math.sqrt((box.getLength().getX() * box.getLength().getX()) + (box.getLength().getZ() * box.getLength().getZ())) / 2;

                    List<Integer> landHeights = new ArrayList<>();
                    for(int xOffset = -radius; xOffset <= radius; xOffset += (radius/2)) {
                        for(int zOffset = -radius; zOffset <= radius; zOffset += (radius/2)) {
                            int landHeight = context.chunkGenerator().getFirstOccupiedHeight(centerPos.getX() + xOffset, centerPos.getZ() + zOffset, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
                            landHeights.add(landHeight);
                        }
                    }

                    // Offset structure to average land around it
                    int avgHeight = (int) Math.max(landHeights.stream().mapToInt(Integer::intValue).average().orElse(0), 50);
                    int parentHeight = pieces.get(0).getBoundingBox().minY();
                    int offsetAmount = (avgHeight - parentHeight) + config.centerYOffset;
                    pieces.forEach(child -> child.move(0, offsetAmount, 0));
                });
    }
}