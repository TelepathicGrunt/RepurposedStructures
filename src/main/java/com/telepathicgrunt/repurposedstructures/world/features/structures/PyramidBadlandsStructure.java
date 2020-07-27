package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.ArrayList;


public class PyramidBadlandsStructure extends StructureFeature<NoFeatureConfig> {
    public PyramidBadlandsStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }
    static {
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(new ResourceLocation(RepurposedStructures.MODID,"temples/pyramid_badlands"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                        new SinglePoolElement(RepurposedStructures.MODID+":temples/pyramid_badlands_body", new ArrayList<>()), 1)),
                        StructurePool.Projection.RIGID));

        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(new ResourceLocation(RepurposedStructures.MODID,"temples/pyramid_badlands_pit"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                        new SinglePoolElement(RepurposedStructures.MODID+":temples/pyramid_badlands_pit", new ArrayList<>()), 1)),
                        StructurePool.Projection.RIGID));
    }


    @Override
    public StructureFeature.StructureStartFactory<NoFeatureConfig> getStructureStartFactory() {
        return PyramidBadlandsStructure.Start::new;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {
        ResourceLocation BADLANDS_PYRAMID_POOL = new ResourceLocation(RepurposedStructures.MODID,"temples/pyramid_badlands");

        public Start(StructureFeature<NoFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX * 16, 62, chunkZ * 16);
            GeneralJigsawGenerator.addPieces(chunkGenerator, structureManager, blockpos, this.children, this.random, BADLANDS_PYRAMID_POOL, 1);
            PyramidFloorPiece.func_207617_a(structureManager, blockpos, this.children.get(0).getRotation(), this.children, random, Blocks.RED_SANDSTONE, NoFeatureConfig);

            //put the floor placing before the pit.
            StructurePiece temp = this.children.get(1);
            this.children.remove(1);
            this.children.add(temp);

            this.children.get(1).getBoundingBox().encompass(this.children.get(0).getBoundingBox());
            this.setBoundingBoxFromChildren();

            BlockRotation rotation = this.children.get(0).getRotation();
            BlockPos maxCorner = new BlockPos(this.children.get(0).getBoundingBox().getBlockCountX(), 0, this.children.get(0).getBoundingBox().getBlockCountZ()).rotate(rotation);

            int highestLandPos = chunkGenerator.getHeight(blockpos.getX() + maxCorner.getX(), blockpos.getZ() + maxCorner.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
            highestLandPos = Math.min(highestLandPos, chunkGenerator.getHeight(blockpos.getX(), blockpos.getZ() + maxCorner.getZ(), Heightmap.Type.WORLD_SURFACE_WG));
            highestLandPos = Math.min(highestLandPos, chunkGenerator.getHeight(blockpos.getX() + maxCorner.getX(), blockpos.getZ(), Heightmap.Type.WORLD_SURFACE_WG));
            highestLandPos = Math.min(highestLandPos, chunkGenerator.getHeight(blockpos.getX(), blockpos.getZ(), Heightmap.Type.WORLD_SURFACE_WG));

            this.method_14976(this.random, highestLandPos-15, highestLandPos-14);
        }
    }
}