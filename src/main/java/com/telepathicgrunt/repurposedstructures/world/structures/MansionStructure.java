package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionPieces;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;


public class MansionStructure extends AbstractBaseStructure<NoFeatureConfig> {

    protected final MansionPieces.MansionTemplate.MANSIONTYPE type;
    public MansionStructure(MansionPieces.MansionTemplate.MANSIONTYPE type) {
        super(NoFeatureConfig.CODEC);
        this.type = type;
    }

    @Override
    protected boolean isUniformDistribution() {
        return false;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeProvider biomeProvider, long seed, SharedSeedRandom random, int chunkX, int chunkZ, Biome biome1, ChunkPos chunkPos, NoFeatureConfig config) {
        for(Biome biome2 : biomeProvider.getBiomesInArea(chunkX * 16 + 9, chunkGenerator.getSeaLevel(), chunkZ * 16 + 9, 32)) {
            if (!biome2.getGenerationSettings().hasStructureFeature(this)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return MansionStructure.Start::new;
    }

    public class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            Rotation rotation = Rotation.randomRotation(this.rand);
            int i = 5;
            int j = 5;
            if (rotation == Rotation.CLOCKWISE_90) {
                i = -5;
            } else if (rotation == Rotation.CLOCKWISE_180) {
                i = -5;
                j = -5;
            } else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                j = -5;
            }

            int k = (chunkX << 4) + 7;
            int l = (chunkZ << 4) + 7;
            int i1 = chunkGenerator.func_222531_c(k, l, Heightmap.Type.WORLD_SURFACE_WG);
            int j1 = chunkGenerator.func_222531_c(k, l + j, Heightmap.Type.WORLD_SURFACE_WG);
            int k1 = chunkGenerator.func_222531_c(k + i, l, Heightmap.Type.WORLD_SURFACE_WG);
            int l1 = chunkGenerator.func_222531_c(k + i, l + j, Heightmap.Type.WORLD_SURFACE_WG);
            int i2 = Math.min(Math.min(i1, j1), Math.min(k1, l1));
            BlockPos blockpos = new BlockPos(chunkX * 16 + 8, i2 + 1, chunkZ * 16 + 8);
            List<MansionPieces.MansionTemplate> list = Lists.newLinkedList();
            MansionPieces.generateMansion(structureManager, blockpos, rotation, list, this.rand, type);
            this.components.addAll(list);
            this.recalculateStructureSize();
        }


        public void generateStructure(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGenerator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos) {
            super.generateStructure(world, structureManager, chunkGenerator, random, boundingBox, chunkPos);
            int i = this.bounds.minY;

            for(int j = boundingBox.minX; j <= boundingBox.maxX; ++j) {
                for(int k = boundingBox.minZ; k <= boundingBox.maxZ; ++k) {
                    BlockPos blockpos = new BlockPos(j, i, k);
                    if (!world.isAirBlock(blockpos) && this.bounds.isVecInside(blockpos)) {
                        boolean flag = false;

                        for(StructurePiece structurepiece : this.components) {
                            if (structurepiece.getBoundingBox().isVecInside(blockpos)) {
                                flag = true;
                                break;
                            }
                        }

                        if (flag) {
                            for(int l = i - 1; l > 1; --l) {
                                BlockPos blockpos1 = new BlockPos(j, l, k);
                                if (!world.isAirBlock(blockpos1) && !world.getBlockState(blockpos1).getMaterial().isLiquid()) {
                                    break;
                                }

                                world.setBlockState(blockpos1, type.getFoundationBlock(), 2);
                            }
                        }
                    }
                }
            }
        }
    }
}