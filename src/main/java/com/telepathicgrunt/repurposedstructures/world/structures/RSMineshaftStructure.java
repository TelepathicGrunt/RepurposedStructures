package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.BlockView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Map;


public class RSMineshaftStructure extends AdvancedJigsawStructure {

    protected final double probability;
    protected final ENVIRONMENT_CHECK environmentCheck;
    public enum ENVIRONMENT_CHECK {
        NONE,
        LIQUID,
        AIR
    }

    public RSMineshaftStructure(Identifier poolID, int structureSize, Map<Identifier, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces, int maxY, int minY, float probability, ENVIRONMENT_CHECK environmentCheck) {
        super(poolID, structureSize, requiredPieces, maxY, minY);
        this.probability = probability;
        this.environmentCheck = environmentCheck;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int x, int z, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig featureConfig) {
        StructureConfig structureConfig = chunkGenerator.getStructuresConfig().getForType(this);
        if(structureConfig != null) {
            chunkRandom.setCarverSeed(seed + structureConfig.getSalt(), x, z);
            double d = (probability / 10000D);
            return chunkRandom.nextDouble() < d;
        }
        return false;
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return Start::new;
    }


    public class Start extends MainStart {

        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            super.init(dynamicRegistryManager, chunkGenerator, structureManager, chunkX, chunkZ, biome, defaultFeatureConfig);

            // Turned off because it has a massive performance impact. Really, really bad...
            // Profiler results: https://imgur.com/c6EKAhE
//            if(environmentCheck != ENVIRONMENT_CHECK.NONE){
//                // Prevent Mineshaft from touching ocean water or regular air.
//                // Won't stop touching against carver or feature liquids/air.
//                BlockPos.Mutable mutable = new BlockPos.Mutable();
//                for(int i = this.children.size() - 1; i >= 0; i--){
//                    StructurePiece piece = this.children.get(i);
//                    BlockBox boundingBox = piece.getBoundingBox();
//                    if(isEnvironmentInvalidInBounds(chunkGenerator, mutable, boundingBox, environmentCheck)){
//                        this.children.remove(i);
//                    }
//                }
//            }
        }

        private boolean isEnvironmentInvalidInBounds(ChunkGenerator chunkGenerator, BlockPos.Mutable mutable, BlockBox boundingBox, ENVIRONMENT_CHECK environmentCheck) {
            for(int x = boundingBox.minX - 1; x <= boundingBox.maxX + 1; x++){
                for(int z = boundingBox.minZ - 1; z <= boundingBox.maxZ + 1; z++){
                    BlockView columnSample = chunkGenerator.getColumnSample(x, z);

                    for(int y = boundingBox.minY - 1; y <= boundingBox.maxY + 1; y++){
                        if(environmentCheck == ENVIRONMENT_CHECK.LIQUID){
                            if(!columnSample.getBlockState(mutable.set(x, y, z)).getFluidState().isEmpty()){
                                return true;
                            }
                        }
                        else if(environmentCheck == ENVIRONMENT_CHECK.AIR){
                            if(columnSample.getBlockState(mutable.set(x, y, z)).isAir()){
                                return true;
                            }
                        }
                    }
                }
            }

             return false;
        }
    }
}