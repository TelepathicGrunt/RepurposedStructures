package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.GeneralJigsawGenerator;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PyramidFloorPiece;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.ArrayList;


public class PyramidBadlandsStructure extends StructureFeature<DefaultFeatureConfig> {
    private static boolean INITIALIZED_POOLS = false;
    private static void initPools() {
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(new Identifier(RepurposedStructures.MODID,"temples/pyramid_badlands"), new Identifier("empty"), ImmutableList.of(Pair.of(
                        new SinglePoolElement(RepurposedStructures.MODID+":temples/pyramid_badlands_body", new ArrayList<>()), 1)),
                        StructurePool.Projection.RIGID));

        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(new Identifier(RepurposedStructures.MODID,"temples/pyramid_badlands_pit"), new Identifier("empty"), ImmutableList.of(Pair.of(
                        new SinglePoolElement(RepurposedStructures.MODID+":temples/pyramid_badlands_pit", new ArrayList<>()), 1)),
                        StructurePool.Projection.RIGID));
    }

    public PyramidBadlandsStructure(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    @Override
    public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return PyramidBadlandsStructure.Start::new;
    }

    public static class Start extends StructureStart<DefaultFeatureConfig> {
        private static final Identifier BADLANDS_PYRAMID_POOL = new Identifier(RepurposedStructures.MODID,"temples/pyramid_badlands");

        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            if(!INITIALIZED_POOLS){
                initPools();
                INITIALIZED_POOLS = true;
            }
            BlockPos blockpos = new BlockPos(chunkX * 16, 62, chunkZ * 16);
            GeneralJigsawGenerator.addPieces(chunkGenerator, structureManager, blockpos, this.children, this.random, BADLANDS_PYRAMID_POOL, 1);
            PyramidFloorPiece.func_207617_a(structureManager, blockpos, this.children.get(0).getRotation(), this.children, random, Blocks.RED_SANDSTONE, defaultFeatureConfig);

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