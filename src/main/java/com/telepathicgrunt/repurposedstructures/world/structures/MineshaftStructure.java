package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.utils.Mutable;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.AdvancedJigsawStructureCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.MineshaftCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;


public class MineshaftStructure extends AdvancedJigsawStructure {

    public MineshaftStructure(Predicate<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>, Optional<PieceGenerator<NoneFeatureConfiguration>>> pieceCreationPredicate) {
        super(locationCheckPredicate, pieceCreationPredicate);
    }

    // Need this constructor wrapper so we can hackly call `this` in the predicates that Minecraft requires in constructors
    public static MineshaftStructure create(MineshaftCodeConfig mineshaftCodeConfig) {
        final Mutable<MineshaftStructure> box = new Mutable<>();
        final MineshaftStructure finalInstance = new MineshaftStructure(
                (context) -> box.getValue().isFeatureChunk(context, mineshaftCodeConfig),
                (context) -> box.getValue().generatePieces(context, mineshaftCodeConfig)
        );
        box.setValue(finalInstance);
        return finalInstance;
    }

    protected boolean isFeatureChunk(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, MineshaftCodeConfig config) {
        StructureFeatureConfiguration structureConfig = context.chunkGenerator().getSettings().getConfig(this);
        if(structureConfig != null) {
            WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
            random.setLargeFeatureSeed(context.seed() + structureConfig.salt(), context.chunkPos().x, context.chunkPos().z);
            double d = (config.probability.get() / 10000D);
            return random.nextDouble() < d;
        }
        return false;
    }

    @Override
    public Optional<PieceGenerator<NoneFeatureConfiguration>> generatePieces(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, AdvancedJigsawStructureCodeConfig config) {
        BlockPos.MutableBlockPos blockpos = new BlockPos.MutableBlockPos(context.chunkPos().getMinBlockX(), 0, context.chunkPos().getMinBlockZ());
        if(config.maxY.get() - config.minY.get() <= 0) {
            RepurposedStructures.LOGGER.error("MinY should always be less than MaxY or else a crash will occur or no pieces will spawn. Problematic structure is:" + Registry.STRUCTURE_FEATURE.getKey(this));
        }
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
        random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
        int structureStartHeight = random.nextInt(config.maxY.get() - config.minY.get()) + config.minY.get();
        blockpos.move(Direction.UP, structureStartHeight);

        int topClipOff;
        int bottomClipOff;
        if(config.verticalRange == null) {
            // Help make sure the Jigsaw Blocks have room to spawn new pieces if structure is right on edge of maxY or topYLimit
            topClipOff = config.clipOutOfBoundsPieces ? config.maxY.get() + 5 : Integer.MAX_VALUE;
            bottomClipOff = config.clipOutOfBoundsPieces ? config.minY.get() - 5 : Integer.MIN_VALUE;
        }
        else {
            topClipOff = structureStartHeight + config.verticalRange.get();
            bottomClipOff = structureStartHeight - config.verticalRange.get();
        }

        ResourceLocation structureID = ForgeRegistries.STRUCTURE_FEATURES.getKey(this);
        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(() -> context.registryAccess().registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(config.startPool), config.structureSize.get()),
                structureID,
                blockpos,
                false,
                false,
                topClipOff,
                bottomClipOff,
                (structurePiecesBuilder, pieces) -> {
                    int justBelowTerrain = getTerrainHeight(context.chunkPos().getMiddleBlockPosition(0), context.chunkGenerator(), context.heightAccessor()) - 15;
                    int finalJustBelowTerrain = Math.max(justBelowTerrain, bottomClipOff);
                    Optional<PoolElementStructurePiece> topPiece = pieces.stream().max(Comparator.comparingInt(piece -> piece.getBoundingBox().maxY()));
                    if(topPiece.isPresent() && finalJustBelowTerrain < topClipOff && finalJustBelowTerrain < topPiece.get().getBoundingBox().maxY()) {
                        pieces.forEach(piece -> piece.move(0, finalJustBelowTerrain - topPiece.get().getBoundingBox().maxY(), 0));
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