package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionPieces;
import javafx.geometry.BoundingBox;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.CheckerboardBiomeProvider;
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
    protected boolean linearSeparation() {
        return false;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig defaultFeatureConfig) {
        if(!(biomeSource instanceof CheckerboardBiomeProvider)) {
            int biomeRange = 2;
            for (int curChunkX = chunkX - biomeRange; curChunkX <= chunkX + biomeRange; curChunkX++) {
                for (int curChunkZ = chunkZ - biomeRange; curChunkZ <= chunkZ + biomeRange; curChunkZ++) {
                    if (!biomeSource.getNoiseBiome(curChunkX << 2, 64, curChunkZ << 2).getGenerationSettings().isValidStart(this)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    public class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox box, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, box, referenceIn, seedIn);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig defaultFeatureConfig) {
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

            int centerX = chunkX << 4;
            int centerZ = chunkZ << 4;
            int firstHeight = chunkGenerator.getFirstOccupiedHeight(centerX, centerZ, Heightmap.Type.WORLD_SURFACE_WG);
            int secondHeight = chunkGenerator.getFirstOccupiedHeight(centerX, centerZ + zOffset, Heightmap.Type.WORLD_SURFACE_WG);
            int thirdHeight = chunkGenerator.getFirstOccupiedHeight(centerX + xOffset, centerZ, Heightmap.Type.WORLD_SURFACE_WG);
            int forthheight = chunkGenerator.getFirstOccupiedHeight(centerX + xOffset, centerZ + zOffset, Heightmap.Type.WORLD_SURFACE_WG);
            int finalheight = Math.min(Math.min(firstHeight, secondHeight), Math.min(thirdHeight, forthheight));
            BlockPos blockPos = new BlockPos(centerX, finalheight + 1, centerZ);
            List<MansionPieces.MansionTemplate> list = Lists.newLinkedList();
            MansionPieces.generateMansion(structureManager, blockPos, blockRotation, list, this.random, type);
            this.pieces.addAll(list);
            this.calculateBoundingBox();
        }

        @Override
        public void placeInChunk(ISeedReader world, StructureManager structureAccessor, ChunkGenerator chunkGenerator, Random random, MutableBoundingBox box, ChunkPos chunkPos) {
            super.placeInChunk(world, structureAccessor, chunkGenerator, random, box, chunkPos);
            int structureBottomY = this.getBoundingBox().y0;

            for(int x = box.x0; x <= box.x1; ++x) {
                for(int z = box.z0; z <= box.z1; ++z) {
                    BlockPos blockPos = new BlockPos(x, structureBottomY, z);
                    if (!world.isEmptyBlock(blockPos) && this.getBoundingBox().isInside(blockPos)) {
                        boolean bl = false;
                        for (StructurePiece structurePiece : this.pieces) {
                            if (structurePiece.getBoundingBox().isInside(blockPos)) {
                                bl = true;
                                break;
                            }
                        }

                        if (bl) {
                            for(int currentY = structureBottomY - 1; currentY > 1; --currentY) {
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