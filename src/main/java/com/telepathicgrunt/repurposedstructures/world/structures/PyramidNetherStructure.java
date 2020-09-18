package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RSFeatures;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.GeneralJigsawGenerator;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;


public class PyramidNetherStructure extends StructureFeature<DefaultFeatureConfig> {
    // Special thanks to /r/l-ll-ll-l_IsDisLoss for allowing me to mimic his nether pyramid design!

    private final Identifier START_POOL;
    public PyramidNetherStructure() {
        super(DefaultFeatureConfig.CODEC);
        START_POOL = new Identifier(RepurposedStructures.MODID, "temples/pyramid_nether");
        RSFeatures.RS_STRUCTURE_START_PIECES.add(START_POOL);
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return PyramidNetherStructure.Start::new;
    }

    public class Start extends AbstractNetherStructure.AbstractStart {

        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX * 16, 35, chunkZ * 16);
            GeneralJigsawGenerator.addPieces(dynamicRegistryManager, chunkGenerator, structureManager, blockpos, this.children, this.random, dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN).get(START_POOL), 1);
            //PyramidFloorPiece.func_207617_a(structureManager, blockpos, this.children.get(0).getRotation(), this.children, random, Blocks.BLACKSTONE, defaultFeatureConfig);
            //this.children.get(1).getBoundingBox().encompass(this.children.get(0).getBoundingBox());
            this.setBoundingBoxFromChildren();

            BlockPos highestLandPos = getHighestLand(chunkGenerator);
            this.method_14976(this.random, highestLandPos.getY()-16, highestLandPos.getY()-15);
        }
    }
}