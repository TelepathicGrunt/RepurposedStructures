package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.GeneralJigsawGenerator;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PyramidFloorPiece;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.ArrayList;


public class PyramidBadlandsStructure extends Structure<NoFeatureConfig> {

    private static boolean INITIALIZED_POOLS = false;
    private static void initPools() {
        JigsawManager.REGISTRY.register(
                new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"temples/pyramid_badlands"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                        new SingleJigsawPiece(RepurposedStructures.MODID+":temples/pyramid_badlands_body", new ArrayList<>()), 1)),
                        JigsawPattern.PlacementBehaviour.RIGID));

        JigsawManager.REGISTRY.register(
                new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"temples/pyramid_badlands_pit"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                        new SingleJigsawPiece(RepurposedStructures.MODID+":temples/pyramid_badlands_pit", new ArrayList<>()), 1)),
                        JigsawPattern.PlacementBehaviour.RIGID));
    }

    public PyramidBadlandsStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return PyramidBadlandsStructure.Start::new;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {
        private static final ResourceLocation BADLANDS_PYRAMID_POOL = new ResourceLocation(RepurposedStructures.MODID,"temples/pyramid_badlands");

        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void init(ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            if(!INITIALIZED_POOLS){
                initPools();
                INITIALIZED_POOLS = true;
            }
            BlockPos blockpos = new BlockPos(chunkX * 16, 62, chunkZ * 16);
            GeneralJigsawGenerator.addPieces(chunkGenerator, structureManager, blockpos, this.components, this.rand, BADLANDS_PYRAMID_POOL, 1);
            PyramidFloorPiece.func_207617_a(structureManager, blockpos, this.components.get(0).getRotation(), this.components, rand, Blocks.RED_SANDSTONE, NoFeatureConfig);

            //put the floor placing before the pit.
            StructurePiece temp = this.components.get(1);
            this.components.remove(1);
            this.components.add(temp);

            this.components.get(1).getBoundingBox().expandTo(this.components.get(0).getBoundingBox());
            this.recalculateStructureSize();

            Rotation rotation = this.components.get(0).getRotation();
            BlockPos maxCorner = new BlockPos(this.components.get(0).getBoundingBox().getXSize(), 0, this.components.get(0).getBoundingBox().getZSize()).rotate(rotation);

            int highestLandPos = chunkGenerator.func_222529_a(blockpos.getX() + maxCorner.getX(), blockpos.getZ() + maxCorner.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
            highestLandPos = Math.min(highestLandPos, chunkGenerator.func_222529_a(blockpos.getX(), blockpos.getZ() + maxCorner.getZ(), Heightmap.Type.WORLD_SURFACE_WG));
            highestLandPos = Math.min(highestLandPos, chunkGenerator.func_222529_a(blockpos.getX() + maxCorner.getX(), blockpos.getZ(), Heightmap.Type.WORLD_SURFACE_WG));
            highestLandPos = Math.min(highestLandPos, chunkGenerator.func_222529_a(blockpos.getX(), blockpos.getZ(), Heightmap.Type.WORLD_SURFACE_WG));

            this.func_214626_a(this.rand, highestLandPos-15, highestLandPos-14);
        }
    }
}