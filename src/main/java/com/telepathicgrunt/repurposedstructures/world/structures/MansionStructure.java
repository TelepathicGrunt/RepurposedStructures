package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionPieces;
import net.minecraft.block.Blocks;
import net.minecraft.structure.*;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.List;
import java.util.Random;
import java.util.Set;


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
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long l, ChunkRandom chunkRandom, int i, int j, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {
        Set<Biome> set = biomeSource.getBiomesInArea(i * 16 + 9, chunkGenerator.getSeaLevel(), j * 16 + 9, 32);
        return set.stream().allMatch(biome1 -> biome1.getGenerationSettings().hasStructureFeature(this));
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return MansionStructure.Start::new;
    }

    public class Start extends StructureStart<DefaultFeatureConfig> {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int i, int j, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            BlockRotation blockRotation = BlockRotation.random(this.random);
            int k = 5;
            int l = 5;
            if (blockRotation == BlockRotation.CLOCKWISE_90) {
                k = -5;
            } else if (blockRotation == BlockRotation.CLOCKWISE_180) {
                k = -5;
                l = -5;
            } else if (blockRotation == BlockRotation.COUNTERCLOCKWISE_90) {
                l = -5;
            }

            int m = (i << 4) + 7;
            int n = (j << 4) + 7;
            int o = chunkGenerator.getHeightInGround(m, n, Heightmap.Type.WORLD_SURFACE_WG);
            int p = chunkGenerator.getHeightInGround(m, n + l, Heightmap.Type.WORLD_SURFACE_WG);
            int q = chunkGenerator.getHeightInGround(m + k, n, Heightmap.Type.WORLD_SURFACE_WG);
            int r = chunkGenerator.getHeightInGround(m + k, n + l, Heightmap.Type.WORLD_SURFACE_WG);
            int s = Math.min(Math.min(o, p), Math.min(q, r));
            if (s >= 60) {
                BlockPos blockPos = new BlockPos(i * 16 + 8, s + 1, j * 16 + 8);
                List<MansionPieces.Piece> list = Lists.newLinkedList();
                MansionPieces.createMansionLayout(structureManager, blockPos, blockRotation, list, this.random, type);
                this.children.addAll(list);
                this.setBoundingBoxFromChildren();
            }
        }

        public void generateStructure(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox box, ChunkPos chunkPos) {
            super.generateStructure(world, structureAccessor, chunkGenerator, random, box, chunkPos);
            int i = this.boundingBox.minY;

            for(int j = box.minX; j <= box.maxX; ++j) {
                for(int k = box.minZ; k <= box.maxZ; ++k) {
                    BlockPos blockPos = new BlockPos(j, i, k);
                    if (!world.isAir(blockPos) && this.boundingBox.contains(blockPos)) {
                        boolean bl = false;
                        for (StructurePiece structurePiece : this.children) {
                            if (structurePiece.getBoundingBox().contains(blockPos)) {
                                bl = true;
                                break;
                            }
                        }

                        if (bl) {
                            for(int l = i - 1; l > 1; --l) {
                                BlockPos blockPos2 = new BlockPos(j, l, k);
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