package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.features.RSFeatures;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.VillageGenerator;
import net.minecraft.structure.VillageStructureStart;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class VillageSwampStructure extends StructureFeature<DefaultFeatureConfig>
{
    public VillageSwampStructure(Codec<DefaultFeatureConfig> config) {
	super(config);
    }


	protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long l, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {

		for (int curChunkX = chunkX - 1; curChunkX <= chunkX + 1; curChunkX++) {
			for (int curChunkZ = chunkZ - 1; curChunkZ <= chunkZ + 1; curChunkZ++) {
				if (!biomeSource.getBiomeForNoiseGen(curChunkX * 16, 60, curChunkZ * 16).hasStructureFeature(RSFeatures.SWAMP_VILLAGE)) {
					return false;
				}
			}
		}

		return true;
	}

    public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
	return VillageSwampStructure.Start::new;
    }


    public String getName() {
	return RepurposedStructures.MODID + ":village_swamp";
    }


    public static class Start extends VillageStructureStart<DefaultFeatureConfig>
    {
		public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
			super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
		}


		public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
			BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
			VillageGenerator.addPieces(chunkGenerator, structureManager, blockpos, this.children, this.random, new StructurePoolFeatureConfig(new Identifier(RepurposedStructures.MODID + ":village/swamp/town_centers"), 6));
			this.setBoundingBoxFromChildren();
		}
    }
}