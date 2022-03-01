package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSMineshaftConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;


public class MineshaftStructure <C extends RSMineshaftConfig> extends AdvancedJigsawStructure<C> {

    public MineshaftStructure(Codec<C> codec) {
        super(codec, MineshaftStructure::isMineshaftFeatureChunk, MineshaftStructure::generateMineshaftPieces);
    }

    public MineshaftStructure(Codec<C> codec, Predicate<PieceGeneratorSupplier.Context<C>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<C>, Optional<PieceGenerator<C>>> pieceCreationPredicate) {
        super(codec, locationCheckPredicate, pieceCreationPredicate);
    }

    protected static <CC extends RSMineshaftConfig> boolean isMineshaftFeatureChunk(PieceGeneratorSupplier.Context<CC> context) {
        WorldgenRandom worldgenRandom = new WorldgenRandom(new LegacyRandomSource(0L));
        worldgenRandom.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
        double chance = (context.config()).probability;
        if(worldgenRandom.nextDouble() >= chance) {
            return false;
        }

        Holder<Biome> biomeAtSpot = context.chunkGenerator().getNoiseBiome(QuartPos.fromBlock(context.chunkPos().getMiddleBlockX()), QuartPos.fromBlock(50), QuartPos.fromBlock(context.chunkPos().getMiddleBlockZ()));
        return context.validBiome().test(biomeAtSpot) && AdvancedJigsawStructure.isAdvancedFeatureChunk(context);
    }

    public static <CC extends RSMineshaftConfig> Optional<PieceGenerator<CC>> generateMineshaftPieces(PieceGeneratorSupplier.Context<CC> context) {
        BlockPos.MutableBlockPos blockpos = new BlockPos.MutableBlockPos(context.chunkPos().getMinBlockX(), 0, context.chunkPos().getMinBlockZ());
        CC config = context.config();

        if(config.maxY - config.minY <= 0) {
            RepurposedStructures.LOGGER.error("MinY should always be less than MaxY or else a crash will occur or no pieces will spawn. Problematic structure is:" + config.startPool.unwrapKey().get().location());
        }
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
        random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
        int structureStartHeight = random.nextInt(config.maxY - config.minY) + config.minY;
        blockpos.move(Direction.UP, structureStartHeight);

        int topClipOff;
        int bottomClipOff;
        if(config.verticalRange.isEmpty()) {
            // Help make sure the Jigsaw Blocks have room to spawn new pieces if structure is right on edge of maxY or topYLimit
            topClipOff = config.clipOutOfBoundsPieces ? config.maxY + 5 : Integer.MAX_VALUE;
            bottomClipOff = config.clipOutOfBoundsPieces ? config.minY - 5 : Integer.MIN_VALUE;
        }
        else {
            topClipOff = structureStartHeight + config.verticalRange.get();
            bottomClipOff = structureStartHeight - config.verticalRange.get();
        }

        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(config.startPool, config.size),
                GeneralUtils.getCsfNameForConfig(config, context.registryAccess()),
                blockpos,
                false,
                false,
                topClipOff,
                bottomClipOff,
                config.poolsThatIgnoreBoundaries,
                (structurePiecesBuilder, pieces) -> {
                    int justBelowTerrain = getTerrainHeight(context.chunkPos().getMiddleBlockPosition(0), context.chunkGenerator(), context.heightAccessor()) - 15;
                    int finalJustBelowTerrain = Math.max(justBelowTerrain, bottomClipOff);
                    Optional<PoolElementStructurePiece> topPiece = pieces.stream().max(Comparator.comparingInt(piece -> piece.getBoundingBox().maxY()));
                    if(topPiece.isPresent() && finalJustBelowTerrain < topClipOff && finalJustBelowTerrain < topPiece.get().getBoundingBox().maxY()) {
                        int topPieceMaxY = topPiece.get().getBoundingBox().maxY();
                        pieces.forEach(piece -> piece.move(0, finalJustBelowTerrain - topPieceMaxY, 0));
                    }
                });
    }

    private static int getTerrainHeight(BlockPos centerPos, ChunkGenerator chunkGenerator, LevelHeightAccessor heightLimitView) {
        int height = chunkGenerator.getFirstOccupiedHeight(centerPos.getX(), centerPos.getZ(), Heightmap.Types.OCEAN_FLOOR_WG, heightLimitView);

        BlockPos pos = new BlockPos(centerPos.getX(), GeneralUtils.getMaxTerrainLimit(chunkGenerator), centerPos.getZ());
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for(Direction direction : Direction.Plane.HORIZONTAL) {
            mutable.set(pos).move(direction, 16);
            height = Math.min(height, chunkGenerator.getFirstOccupiedHeight(mutable.getX(), mutable.getZ(), Heightmap.Types.OCEAN_FLOOR_WG, heightLimitView));
        }

        return height;
    }
}