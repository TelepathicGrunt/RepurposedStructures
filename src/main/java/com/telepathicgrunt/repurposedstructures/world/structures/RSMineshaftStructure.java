package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.common.util.Lazy;

import java.util.Map;


public class RSMineshaftStructure extends AdvancedJigsawStructure {

    protected final Lazy<Float> probability;
    protected final ENVIRONMENT_CHECK environmentCheck;
    public enum ENVIRONMENT_CHECK {
        NONE,
        LIQUID,
        AIR
    }

    public RSMineshaftStructure(ResourceLocation poolID, Lazy<Integer> structureSize, Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces, Lazy<Integer> maxY, Lazy<Integer> minY, Lazy<Float> probability, ENVIRONMENT_CHECK environmentCheck) {
        super(poolID, structureSize, requiredPieces, maxY, minY);
        this.probability = probability;
        this.environmentCheck = environmentCheck;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int x, int z, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        StructureSeparationSettings structureConfig = chunkGenerator.getSettings().getConfig(this);
        if(structureConfig != null) {
            chunkRandom.setLargeFeatureSeed(seed + structureConfig.salt(), x, z);
            double d = (probability.get() / 10000D);
            return chunkRandom.nextDouble() < d;
        }
        return false;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }


    public class Start extends MainStart {

        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig noFeatureConfig) {
            super.generatePieces(dynamicRegistryManager, chunkGenerator, structureManager, chunkX, chunkZ, biome, noFeatureConfig);

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

        private boolean isEnvironmentInvalidInBounds(ChunkGenerator chunkGenerator, BlockPos.Mutable mutable, MutableBoundingBox boundingBox, ENVIRONMENT_CHECK environmentCheck) {
            for(int x = boundingBox.x0 - 1; x <= boundingBox.x1 + 1; x++){
                for(int z = boundingBox.z0 - 1; z <= boundingBox.z1 + 1; z++){
                    IBlockReader columnSample = chunkGenerator.getBaseColumn(x, z);

                    for(int y = boundingBox.y0 - 1; y <= boundingBox.y1 + 1; y++){
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