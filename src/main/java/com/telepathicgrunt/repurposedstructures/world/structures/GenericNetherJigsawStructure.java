package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSGenericNetherConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.Optional;

public class GenericNetherJigsawStructure <C extends RSGenericNetherConfig> extends GenericJigsawStructure<C> {

    public GenericNetherJigsawStructure(Codec<C> codec) {
        super(codec, GenericNetherJigsawStructure::isGenericFeatureChunk, GenericNetherJigsawStructure::generateNetherPieces);
    }

    public static <CC extends RSGenericNetherConfig> Optional<PieceGenerator<CC>> generateNetherPieces(PieceGeneratorSupplier.Context<CC> context) {
        CC config = context.config();
        BlockPos blockpos = new BlockPos(context.chunkPos().getMinBlockX(), config.setFixedYSpawn, context.chunkPos().getMinBlockZ());

        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(config.startPool, config.size),
                config.startPool.unwrapKey().get().location(),
                blockpos,
                !config.doNotUseHeightmap,
                !config.doNotUseHeightmap,
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                config.poolsThatIgnoreBoundaries,
                (structurePiecesBuilder, pieces) -> {
                    GeneralUtils.centerAllPieces(blockpos, pieces);
                    pieces.get(0).move(0, config.centerYOffset, 0);

                    WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
                    random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
                    BlockPos placementPos;

                    if(config.highestLandSearch) {
                        placementPos = GeneralUtils.getHighestLand(context.chunkGenerator(), structurePiecesBuilder.getBoundingBox(), context.heightAccessor(), !config.cannotSpawnInLiquid);
                    }
                    else{
                        placementPos = GeneralUtils.getLowestLand(context.chunkGenerator(), structurePiecesBuilder.getBoundingBox(), context.heightAccessor(), !config.cannotSpawnInLiquid);
                    }

                    if (placementPos.getY() >= GeneralUtils.getMaxTerrainLimit(context.chunkGenerator()) || placementPos.getY() <= context.chunkGenerator().getSeaLevel() + 1) {
                        structurePiecesBuilder.moveInsideHeights(random, context.chunkGenerator().getSeaLevel() + config.ledgeSpotOffset, context.chunkGenerator().getSeaLevel() + (config.ledgeSpotOffset + 1));
                    }
                    else {
                        structurePiecesBuilder.moveInsideHeights(random, placementPos.getY() + config.liquidSpotOffset, placementPos.getY() + (config.liquidSpotOffset + 1));
                    }
                });
    }
}