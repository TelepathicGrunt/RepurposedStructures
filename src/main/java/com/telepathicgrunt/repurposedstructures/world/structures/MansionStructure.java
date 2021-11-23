package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.MansionCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionPieces;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.CheckerboardColumnBiomeSource;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;


public class MansionStructure extends AbstractBaseStructure<NoneFeatureConfiguration> {

    public MansionStructure(Predicate<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>, Optional<PieceGenerator<NoneFeatureConfiguration>>> pieceCreationPredicate, PostPlacementProcessor postPlacementProcessor) {
        super(NoneFeatureConfiguration.CODEC, locationCheckPredicate, pieceCreationPredicate, postPlacementProcessor);
    }

    // Need this constructor wrapper so we can hackly call `this` in the predicates that Minecraft requires in constructors
    public static MansionStructure create(MansionCodeConfig mansionCodeConfig) {
        final Mutable<MansionStructure> box = new MutableObject<>();
        final MansionStructure finalInstance = new MansionStructure(
                (context) -> box.getValue().isFeatureChunk(context, mansionCodeConfig),
                (context) -> box.getValue().generatePieces(context, mansionCodeConfig),
                (w, sfm, cg, r, bb, c, pc) -> box.getValue().afterPlace(w, sfm, cg, r, bb, c, pc, mansionCodeConfig)
        );
        box.setValue(finalInstance);
        return finalInstance;
    }

    @Override
    protected boolean linearSeparation() {
        return false;
    }

    protected boolean isFeatureChunk(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, MansionCodeConfig config) {
        ChunkPos chunkPos = context.chunkPos();

        if(!(context.biomeSource() instanceof CheckerboardColumnBiomeSource)) {
            int biomeRange = 2;
            for (int curChunkX = chunkPos.x - biomeRange; curChunkX <= chunkPos.x + biomeRange; curChunkX++) {
                for (int curChunkZ = chunkPos.z - biomeRange; curChunkZ <= chunkPos.z + biomeRange; curChunkZ++) {
                    int yValue = context.chunkGenerator().getFirstFreeHeight(curChunkX << 4, curChunkZ << 4, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
                    if (!context.validBiome().test(context.biomeSource().getNoiseBiome(curChunkX << 2, yValue >> 2, curChunkZ << 2, context.chunkGenerator().climateSampler()))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public Optional<PieceGenerator<NoneFeatureConfiguration>> generatePieces(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, MansionCodeConfig config) {

        ChunkPos chunkPos = context.chunkPos();
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
        random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
        Rotation blockRotation = Rotation.getRandom(random);

        int xOffset = 5;
        int zOffset = 5;
        if (blockRotation == Rotation.CLOCKWISE_90) {
            xOffset = -5;
        } else if (blockRotation == Rotation.CLOCKWISE_180) {
            xOffset = -5;
            zOffset = -5;
        } else if (blockRotation == Rotation.COUNTERCLOCKWISE_90) {
            zOffset = -5;
        }

        int centerX = chunkPos.getMiddleBlockX();
        int centerZ = chunkPos.getMiddleBlockZ();
        int firstHeight = context.chunkGenerator().getFirstOccupiedHeight(centerX, centerZ, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
        int secondHeight = context.chunkGenerator().getFirstOccupiedHeight(centerX, centerZ + zOffset, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
        int thirdHeight = context.chunkGenerator().getFirstOccupiedHeight(centerX + xOffset, centerZ, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
        int forthheight = context.chunkGenerator().getFirstOccupiedHeight(centerX + xOffset, centerZ + zOffset, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
        int finalheight = Math.min(Math.min(firstHeight, secondHeight), Math.min(thirdHeight, forthheight));

        if(finalheight <= context.chunkGenerator().getMinY())
            return Optional.empty();

        return Optional.of((structurePiecesBuilder, contextx) -> {
            BlockPos blockPos = new BlockPos(chunkPos.getMiddleBlockX(), finalheight + 1, chunkPos.getMiddleBlockZ());
            List<StructurePiece> list = new ArrayList<>();
            MansionPieces.createMansionLayout(context.registryAccess(), context.structureManager(), blockPos, blockRotation, list, random, config.type);
        });
    }

    private void afterPlace(WorldGenLevel world, StructureFeatureManager structureFeatureManager, ChunkGenerator chunkGenerator, Random random, BoundingBox boundingBox, ChunkPos chunkPos, PiecesContainer piecesContainer, MansionCodeConfig config) {
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        BoundingBox box = piecesContainer.calculateBoundingBox();
        int structureBottomY = box.minY();
        int terrainY = Integer.MIN_VALUE;

        for(int x = box.minX(); x <= box.maxX(); ++x) {
            for(int z = box.minZ(); z <= box.maxZ(); ++z) {
                mutableBlockPos.set(x, structureBottomY, z);
                if(RepurposedStructures.RSAllConfig.RSMansionsConfig.pillarOnlyToLand) {
                    terrainY = GeneralUtils.getFirstLandYFromPos(world, mutableBlockPos.below());
                    if(terrainY <= chunkGenerator.getMinY()) {
                        continue;
                    }
                }

                if (!world.isEmptyBlock(mutableBlockPos) && box.isInside(mutableBlockPos) && piecesContainer.isInsidePiece(mutableBlockPos)) {
                    for(int currentY = structureBottomY - 1; currentY > chunkGenerator.getMinY(); --currentY) {
                        if(RepurposedStructures.RSAllConfig.RSMansionsConfig.pillarOnlyToLand) {
                            if(currentY <= terrainY) {
                                break;
                            }
                        }

                        BlockPos blockPos2 = new BlockPos(x, currentY, z);
                        if (!world.isEmptyBlock(blockPos2) && !world.getBlockState(blockPos2).getMaterial().isLiquid()) {
                            break;
                        }

                        world.setBlock(blockPos2, config.type.getFoundationBlock(), 2);
                    }
                }
            }
        }

    }
}