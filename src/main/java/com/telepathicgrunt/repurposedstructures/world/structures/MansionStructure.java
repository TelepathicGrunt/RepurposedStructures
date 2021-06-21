package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionPieces;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.CheckerboardBiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.List;
import java.util.Random;


public class MansionStructure extends AbstractBaseStructure<DefaultFeatureConfig> {

    protected final MansionPieces.Piece.MANSIONTYPE type;
    public MansionStructure(MansionPieces.Piece.MANSIONTYPE type) {
        super(DefaultFeatureConfig.CODEC);
        this.type = type;
    }

    @Override
    protected boolean isUniformDistribution() {
        return false;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
        if(!(biomeSource instanceof CheckerboardBiomeSource)) {
            int biomeRange = 2;
            for (int curChunkX = chunkPos1.x - biomeRange; curChunkX <= chunkPos1.x + biomeRange; curChunkX++) {
                for (int curChunkZ = chunkPos1.z - biomeRange; curChunkZ <= chunkPos1.z + biomeRange; curChunkZ++) {
                    if (!biomeSource.getBiomeForNoiseGen(curChunkX << 2, 64, curChunkZ << 2).getGenerationSettings().hasStructureFeature(this)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return MansionStructure.Start::new;
    }

    public class Start extends StructureStart<DefaultFeatureConfig> {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
            BlockRotation blockRotation = BlockRotation.random(this.random);
            int xOffset = 5;
            int zOffset = 5;
            if (blockRotation == BlockRotation.CLOCKWISE_90) {
                xOffset = -5;
            } else if (blockRotation == BlockRotation.CLOCKWISE_180) {
                xOffset = -5;
                zOffset = -5;
            } else if (blockRotation == BlockRotation.COUNTERCLOCKWISE_90) {
                zOffset = -5;
            }

            int centerX = chunkPos1.getCenterX();
            int centerZ = chunkPos1.getCenterZ();
            int firstHeight = chunkGenerator.getHeightInGround(centerX, centerZ, Heightmap.Type.WORLD_SURFACE_WG, heightLimitView);
            int secondHeight = chunkGenerator.getHeightInGround(centerX, centerZ + zOffset, Heightmap.Type.WORLD_SURFACE_WG, heightLimitView);
            int thirdHeight = chunkGenerator.getHeightInGround(centerX + xOffset, centerZ, Heightmap.Type.WORLD_SURFACE_WG, heightLimitView);
            int forthheight = chunkGenerator.getHeightInGround(centerX + xOffset, centerZ + zOffset, Heightmap.Type.WORLD_SURFACE_WG, heightLimitView);
            int finalheight = Math.min(Math.min(firstHeight, secondHeight), Math.min(thirdHeight, forthheight));
            BlockPos blockPos = new BlockPos(chunkPos1.getCenterX(), finalheight + 1, chunkPos1.getCenterZ());
            List<MansionPieces.Piece> list = Lists.newLinkedList();
            MansionPieces.createMansionLayout(structureManager, blockPos, blockRotation, list, this.random, type);
            this.children.addAll(list);
            this.setBoundingBoxFromChildren();
        }

        public void generateStructure(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox box, ChunkPos chunkPos) {
            super.generateStructure(world, structureAccessor, chunkGenerator, random, box, chunkPos);
            int structureBottomY = this.calculateBoundingBox().getMinY();

            for(int x = box.getMinX(); x <= box.getMaxX(); ++x) {
                for(int z = box.getMinZ(); z <= box.getMaxZ(); ++z) {
                    BlockPos blockPos = new BlockPos(x, structureBottomY, z);
                    if (!world.isAir(blockPos) && this.calculateBoundingBox().contains(blockPos)) {
                        boolean bl = false;
                        for (StructurePiece structurePiece : this.children) {
                            if (structurePiece.getBoundingBox().contains(blockPos)) {
                                bl = true;
                                break;
                            }
                        }

                        if (bl) {
                            for(int currentY = structureBottomY - 1; currentY > 1; --currentY) {
                                BlockPos blockPos2 = new BlockPos(x, currentY, z);
                                if (!world.isAir(blockPos2) && !world.getBlockState(blockPos2).getMaterial().isLiquid()) {
                                    break;
                                }

                                world.setBlockState(blockPos2, type.getFoundationBlock(), 2);
                            }
                        }
                    }
                }
            }

        }
    }
}