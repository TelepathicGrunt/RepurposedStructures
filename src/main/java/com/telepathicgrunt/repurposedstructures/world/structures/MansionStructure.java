package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSMansionConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionPieces;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionStructurePiece;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.CheckerboardColumnBiomeSource;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.material.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


public class MansionStructure <C extends RSMansionConfig> extends AbstractBaseStructure<C> {

    public MansionStructure(Codec<C> codec) {
        super(codec, MansionStructure::isMansionFeatureChunk, MansionStructure::generateMansionPieces, MansionStructure::afterPlace);
    }

    protected static <CC extends RSMansionConfig> boolean isMansionFeatureChunk(PieceGeneratorSupplier.Context<CC> context) {
        ChunkPos chunkPos = context.chunkPos();
        CC config = context.config();

        if (!(context.biomeSource() instanceof CheckerboardColumnBiomeSource)) {
            for (int curChunkX = chunkPos.x - config.biomeRadius; curChunkX <= chunkPos.x + config.biomeRadius; curChunkX++) {
                for (int curChunkZ = chunkPos.z - config.biomeRadius; curChunkZ <= chunkPos.z + config.biomeRadius; curChunkZ++) {
                    int yValue = context.chunkGenerator().getFirstFreeHeight(curChunkX << 4, curChunkZ << 4, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
                    Holder<Biome> biome = context.biomeSource().getNoiseBiome(curChunkX << 2, yValue >> 2, curChunkZ << 2, context.chunkGenerator().climateSampler());
                    if (!context.validBiome().test(biome)) {
                        return false;
                    }
                }
            }
        }

        //cannot be near other specified structure
        for (ResourceKey<StructureSet> structureSetToAvoid : config.structureSetToAvoid) {
            if (context.chunkGenerator().hasFeatureChunkInRange(structureSetToAvoid, context.seed(), chunkPos.x, chunkPos.z, config.structureAvoidRadius)) {
                return false;
            }
        }

        return true;
    }

    public static <CC extends RSMansionConfig> Optional<PieceGenerator<CC>> generateMansionPieces(PieceGeneratorSupplier.Context<CC> context) {

        CC config = context.config();
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
            MansionPieces.createMansionLayout(context.registryAccess(), context.structureManager(), blockPos, blockRotation, list, random, context.config());
            list.forEach(piece -> {
                if (piece instanceof PoolElementStructurePiece poolElementStructurePiece) {
                    structurePiecesBuilder.addPiece(new MansionStructurePiece(poolElementStructurePiece, config.mansionType, config.foundationBlock, config.pillarOnlyToLand));
                }
                else {
                    structurePiecesBuilder.addPiece(piece);
                }
            });
        });
    }

    private static void afterPlace(WorldGenLevel level, StructureFeatureManager structureFeatureManager, ChunkGenerator chunkGenerator, Random random, BoundingBox boundingBox, ChunkPos chunkPos, PiecesContainer piecesContainer) {
        if (!piecesContainer.isEmpty() && piecesContainer.pieces().get(0) instanceof MansionStructurePiece mansionStructurePiece) {
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
            BoundingBox box = piecesContainer.calculateBoundingBox();
            int structureBottomY = box.minY();
            int terrainY = Integer.MIN_VALUE;

            for(int x = box.minX(); x <= box.maxX(); ++x) {
                for(int z = box.minZ(); z <= box.maxZ(); ++z) {
                    if (chunkPos.x != x >> 4 || chunkPos.z != z >> 4) {
                        continue;
                    }

                    mutableBlockPos.set(x, structureBottomY, z);
                    if(mansionStructurePiece.pillarOnlyToLand) {
                        terrainY = GeneralUtils.getFirstLandYFromPos(level, mutableBlockPos.below());
                        if(terrainY <= chunkGenerator.getMinY()) {
                            continue;
                        }
                    }

                    if (!level.isEmptyBlock(mutableBlockPos) && box.isInside(mutableBlockPos) && piecesContainer.isInsidePiece(mutableBlockPos)) {
                        for(int currentY = structureBottomY - 1; currentY > chunkGenerator.getMinY(); --currentY) {
                            if(mansionStructurePiece.pillarOnlyToLand) {
                                if(currentY <= terrainY) {
                                    break;
                                }
                            }

                            BlockPos blockPos2 = new BlockPos(x, currentY, z);
                            Material material = level.getBlockState(blockPos2).getMaterial();
                            if (!level.isEmptyBlock(blockPos2) &&
                                !material.isLiquid() &&
                                material != Material.REPLACEABLE_PLANT &&
                                material != Material.REPLACEABLE_FIREPROOF_PLANT &&
                                material != Material.REPLACEABLE_WATER_PLANT)
                            {
                                break;
                            }

                            level.setBlock(blockPos2, mansionStructurePiece.foundationBlock, 2);
                        }
                    }
                }
            }
        }
    }
}