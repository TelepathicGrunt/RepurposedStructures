package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSBuriableConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.Optional;


public class BuriableStructure <C extends RSBuriableConfig> extends AbstractBaseStructure<C> {

    public BuriableStructure(Codec<C> codec) {
        super(codec, BuriableStructure::isFeatureChunk, BuriableStructure::generatePieces);
    }


    protected static <CC extends RSBuriableConfig> boolean isFeatureChunk(PieceGeneratorSupplier.Context<CC> context) {
        CC config = context.config();
        if(config.cannotSpawnInWater) {
            BlockPos cornerOfSpawnChunk = context.chunkPos().getWorldPosition();
            int landHeight = context.chunkGenerator().getFirstOccupiedHeight(cornerOfSpawnChunk.getX(), cornerOfSpawnChunk.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
            NoiseColumn columnOfBlocks = context.chunkGenerator().getBaseColumn(cornerOfSpawnChunk.getX(), cornerOfSpawnChunk.getZ(), context.heightAccessor());
            BlockState topBlock = columnOfBlocks.getBlock(cornerOfSpawnChunk.getY() + landHeight);
            return topBlock.getFluidState().isEmpty();
        }

        return true;
    }

    public static <CC extends RSBuriableConfig> Optional<PieceGenerator<CC>> generatePieces(PieceGeneratorSupplier.Context<CC> context) {
        BlockPos blockpos = new BlockPos(context.chunkPos().getMinBlockX(), context.chunkGenerator().getSeaLevel(), context.chunkPos().getMinBlockZ());
        CC config = context.config();

        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(config.startPool, config.size),
                GeneralUtils.getCsfNameForConfig(config, context.registryAccess()),
                blockpos,
                false,
                false,
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                (structurePiecesBuilder, pieces) -> {
                    GeneralUtils.centerAllPieces(blockpos, pieces);
                    Heightmap.Types heightMapToUse = config.useOceanHeightmap ? Heightmap.Types.OCEAN_FLOOR_WG : Heightmap.Types.WORLD_SURFACE_WG;

                    BoundingBox box = pieces.get(0).getBoundingBox();
                    int highestLandPos = context.chunkGenerator().getFirstOccupiedHeight(box.minX(), box.minZ(), heightMapToUse, context.heightAccessor());
                    highestLandPos = Math.min(highestLandPos, context.chunkGenerator().getFirstOccupiedHeight(box.minX(), box.maxZ(), heightMapToUse, context.heightAccessor()));
                    highestLandPos = Math.min(highestLandPos, context.chunkGenerator().getFirstOccupiedHeight(box.maxX(), box.minZ(), heightMapToUse, context.heightAccessor()));
                    highestLandPos = Math.min(highestLandPos, context.chunkGenerator().getFirstOccupiedHeight(box.maxX(), box.maxZ(), heightMapToUse, context.heightAccessor()));

                    if(config.useOceanHeightmap) {
                        int maxHeightForSubmerging = context.chunkGenerator().getSeaLevel() - box.getYSpan();
                        highestLandPos = Math.min(highestLandPos, maxHeightForSubmerging);
                    }

                    WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
                    random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
                    int heightDiff = highestLandPos - box.minY();
                    for(StructurePiece structurePiece : pieces) {
                        structurePiece.move(0, heightDiff + (config.offsetAmount), 0);
                    }
                });
    }
}