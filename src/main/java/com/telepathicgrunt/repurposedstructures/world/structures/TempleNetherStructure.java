package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RSFeatures;
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


public class TempleNetherStructure extends StructureFeature<DefaultFeatureConfig> {
    private final Identifier START_POOL;

    public TempleNetherStructure(Identifier pieceID) {
        super(DefaultFeatureConfig.CODEC);
        START_POOL = pieceID;
        RSFeatures.RS_STRUCTURE_START_PIECES.add(START_POOL);
    }

    @Override
    public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return TempleNetherStructure.Start::new;
    }

    public class Start extends AbstractNetherStructure.AbstractStart{
        public Start(StructureFeature<DefaultFeatureConfig> structureFeature, int x, int z, BlockBox blockBox, int referenceIn, long seed) {
            super(structureFeature, x, z, blockBox, referenceIn, seed);
        }

        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int x, int z, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            BlockPos blockPos = new BlockPos(x * 16, 35, z * 16);
            GeneralJigsawGenerator.addPieces(dynamicRegistryManager, chunkGenerator, structureManager, blockPos, this.children, this.random, dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN).get(START_POOL), 1);
            this.setBoundingBoxFromChildren();

            BlockPos lowestLandPos = getLowestLand(chunkGenerator);
            if (lowestLandPos.getY() >= 108 || lowestLandPos.getY() <= 33) {
                this.method_14976(this.random, 16, 17);
            }
            else {
                this.method_14976(this.random, lowestLandPos.getY() - 16, lowestLandPos.getY() - 15);
            }
        }
    }
}