package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.GeneralJigsawGenerator;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.TempleNetherPools;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;


public class TempleNetherStructure extends StructureFeature<DefaultFeatureConfig> {
    private final StructurePool START_POOL;
    private static boolean INITIALIZED_POOLS = false;

    public TempleNetherStructure(Codec<DefaultFeatureConfig> config, Identifier pieceID) {
        super(config);
        START_POOL = BuiltinRegistries.STRUCTURE_POOL.get(pieceID);
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
            if(!INITIALIZED_POOLS){
                TempleNetherPools.initPools();
                INITIALIZED_POOLS = true;
            }

            BlockPos blockPos = new BlockPos(x * 16, 35, z * 16);
            GeneralJigsawGenerator.addPieces(dynamicRegistryManager, chunkGenerator, structureManager, blockPos, this.children, this.random, START_POOL, 1);
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