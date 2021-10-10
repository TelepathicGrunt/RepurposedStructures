package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionPieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.CheckerboardColumnBiomeSource;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.List;
import java.util.Random;


public class MansionStructure extends AbstractBaseStructure<NoneFeatureConfiguration> {

    protected final MansionPieces.Piece.MANSIONTYPE type;
    public MansionStructure(MansionPieces.Piece.MANSIONTYPE type) {
        super(NoneFeatureConfiguration.CODEC);
        this.type = type;
    }

    @Override
    protected boolean linearSeparation() {
        return false;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, WorldgenRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, NoneFeatureConfiguration defaultFeatureConfig, LevelHeightAccessor heightLimitView) {
        if(!(biomeSource instanceof CheckerboardColumnBiomeSource)) {
            int biomeRange = 2;
            for (int curChunkX = chunkPos1.x - biomeRange; curChunkX <= chunkPos1.x + biomeRange; curChunkX++) {
                for (int curChunkZ = chunkPos1.z - biomeRange; curChunkZ <= chunkPos1.z + biomeRange; curChunkZ++) {
                    if (!biomeSource.getNoiseBiome(curChunkX << 2, 64, curChunkZ << 2).getGenerationSettings().isValidStart(this)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return MansionStructure.Start::new;
    }

    public class Start extends StructureStart<NoneFeatureConfiguration> {
        public Start(StructureFeature<NoneFeatureConfiguration> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
        }

        @Override
        public void generatePieces(RegistryAccess dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, NoneFeatureConfiguration defaultFeatureConfig, LevelHeightAccessor heightLimitView) {
            Rotation blockRotation = Rotation.getRandom(this.random);
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

            int centerX = chunkPos1.getMiddleBlockX();
            int centerZ = chunkPos1.getMiddleBlockZ();
            int firstHeight = chunkGenerator.getFirstOccupiedHeight(centerX, centerZ, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
            int secondHeight = chunkGenerator.getFirstOccupiedHeight(centerX, centerZ + zOffset, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
            int thirdHeight = chunkGenerator.getFirstOccupiedHeight(centerX + xOffset, centerZ, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
            int forthheight = chunkGenerator.getFirstOccupiedHeight(centerX + xOffset, centerZ + zOffset, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
            int finalheight = Math.min(Math.min(firstHeight, secondHeight), Math.min(thirdHeight, forthheight));
            if(finalheight <= chunkGenerator.getMinY())
                return;

            BlockPos blockPos = new BlockPos(chunkPos1.getMiddleBlockX(), finalheight + 1, chunkPos1.getMiddleBlockZ());
            List<MansionPieces.Piece> list = Lists.newLinkedList();
            MansionPieces.createMansionLayout(structureManager, blockPos, blockRotation, list, this.random, type);
            this.pieces.addAll(list);
            this.getBoundingBox();
        }

        public void placeInChunk(WorldGenLevel world, StructureFeatureManager structureAccessor, ChunkGenerator chunkGenerator, Random random, BoundingBox box, ChunkPos chunkPos) {
            super.placeInChunk(world, structureAccessor, chunkGenerator, random, box, chunkPos);
            int structureBottomY = this.createBoundingBox().minY();
            int terrainY = Integer.MIN_VALUE;

            for(int x = box.minX(); x <= box.maxX(); ++x) {
                for(int z = box.minZ(); z <= box.maxZ(); ++z) {
                    BlockPos blockPos = new BlockPos(x, structureBottomY, z);
                    if(RepurposedStructures.RSAllConfig.RSMansionsConfig.pillarOnlyToLand) {
                        terrainY = GeneralUtils.getFirstLandYFromPos(world, blockPos.below());
                        if(terrainY <= chunkGenerator.getMinY()) {
                            continue;
                        }
                    }

                    if (!world.isEmptyBlock(blockPos) && this.createBoundingBox().isInside(blockPos)) {
                        boolean bl = false;
                        for (StructurePiece structurePiece : this.pieces) {
                            if (structurePiece.getBoundingBox().isInside(blockPos)) {
                                bl = true;
                                break;
                            }
                        }

                        if (bl) {
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

                                world.setBlock(blockPos2, type.getFoundationBlock(), 2);
                            }
                        }
                    }
                }
            }

        }
    }
}