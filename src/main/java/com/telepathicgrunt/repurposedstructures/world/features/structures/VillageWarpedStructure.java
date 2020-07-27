package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RSFeatures;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class VillageWarpedStructure extends AbstractNetherVillageStructure {
    public VillageWarpedStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig NoFeatureConfig) {
        return true;
    }

    @Override
    public StructureFeature<NoFeatureConfig> getVillageInstance() {
        return RSFeatures.WARPED_VILLAGE;
    }

    public StructureStartFactory<NoFeatureConfig> getStructureStartFactory() {
        return VillageWarpedStructure.Start::new;
    }

    public static class Start extends NetherAbstractStart {
        public Start(StructureFeature<NoFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public static ResourceLocation VILLAGE_ResourceLocation = new ResourceLocation(RepurposedStructures.MODID + ":village/warped/town_centers");

        @Override
        public ResourceLocation getResourceLocation() {
            return VILLAGE_ResourceLocation;
        }

        @Override
        public int getSize() {
            return 6;
        }

        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            super.init(chunkGenerator, structureManager, chunkX, chunkZ, biome, NoFeatureConfig);

            BlockPos lowestLandPos = getHighestLand(chunkGenerator);
            if (lowestLandPos.getY() >= 108 || lowestLandPos.getY() <= 33) {
                this.method_14976(this.random, 20, 21);
            }
            else {
                this.method_14976(this.random, lowestLandPos.getY() - 13, lowestLandPos.getY() - 12);
            }
        }

    }
}