package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class VillageSwampStructure extends VillageBaseStructure {

    public VillageSwampStructure(Identifier poolID, int structureSize) {
        super(poolID, structureSize);
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return VillageSwampStructure.Start::new;
    }

    public class Start extends MainStart{
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            super.init(dynamicRegistryManager, chunkGenerator, structureManager, chunkX, chunkZ, biome, defaultFeatureConfig);
            this.children.get(0).translate(0, -1, 0);
        }
    }
}