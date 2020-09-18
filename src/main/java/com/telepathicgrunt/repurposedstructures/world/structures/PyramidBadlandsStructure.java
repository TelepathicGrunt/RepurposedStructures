package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RSFeatures;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.GeneralJigsawGenerator;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PyramidFloorPiece;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;


public class PyramidBadlandsStructure extends StructureFeature<DefaultFeatureConfig> {

    private final Identifier START_POOL;
    public PyramidBadlandsStructure() {
        super(DefaultFeatureConfig.CODEC);
        START_POOL = new Identifier(RepurposedStructures.MODID, "temples/pyramid_badlands");
        RSFeatures.RS_STRUCTURE_START_PIECES.add(START_POOL);
    }

    @Override
    public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return PyramidBadlandsStructure.Start::new;
    }

    public class Start extends StructureStart<DefaultFeatureConfig> {

        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX * 16, 62, chunkZ * 16);
            GeneralJigsawGenerator.addPieces(dynamicRegistryManager, chunkGenerator, structureManager, blockpos, this.children, this.random, dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN).get(START_POOL), 1);
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