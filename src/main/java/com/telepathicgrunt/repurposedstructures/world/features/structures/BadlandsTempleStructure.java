package com.telepathicgrunt.repurposedstructures.world.features.structures;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class BadlandsTempleStructure extends Structure<NoFeatureConfig>
{
	public BadlandsTempleStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> config)
	{
		super(config);
	}


	@Override
	protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ)
	{
		int maxDistance = RepurposedStructures.RSConfig.badlandsTempleSpawnrate.get();
		int minDistance = (int) (maxDistance * 0.75f);
		if (minDistance == 0)
		{
			minDistance = 1;
		}
		int k = x + maxDistance * spacingOffsetsX;
		int l = z + maxDistance * spacingOffsetsZ;
		int i1 = k < 0 ? k - maxDistance + 1 : k;
		int j1 = l < 0 ? l - maxDistance + 1 : l;
		int targetChunkX = i1 / maxDistance;
		int targetChunkZ = j1 / maxDistance;
		((SharedSeedRandom) random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), targetChunkX, targetChunkZ, 399117345);
		targetChunkX = targetChunkX * maxDistance;
		targetChunkZ = targetChunkZ * maxDistance;
		targetChunkX = targetChunkX + random.nextInt(maxDistance - minDistance);
		targetChunkZ = targetChunkZ + random.nextInt(maxDistance - minDistance);
		return new ChunkPos(targetChunkX, targetChunkZ);
	}


	@Override
	public boolean func_225558_a_(BiomeManager biomeManager, ChunkGenerator<?> chunkGenerator, Random random, int chunkPosX, int chunkPosZ, Biome biome)
	{
		ChunkPos chunkpos = this.getStartPositionForPosition(chunkGenerator, random, chunkPosX, chunkPosZ, 0, 0);
		if (chunkPosX == chunkpos.x && chunkPosZ == chunkpos.z)
		{
			if (chunkGenerator.hasStructure(biome, this))
			{
				return true;
			}
		}

		return false;
	}


	@Override
	public Structure.IStartFactory getStartFactory()
	{
		return BadlandsTempleStructure.Start::new;
	}


	@Override
	public String getStructureName()
	{
		return RepurposedStructures.MODID + ":badlands_temple";
	}


	@Override
	public int getSize()
	{
		return 8;
	}

	public static class Start extends StructureStart
	{
		public Start(Structure<?> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn)
		{
			super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
		}


		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn)
		{
			BadlandsTemplePiece netherTemplePiece = new BadlandsTemplePiece(this.rand, chunkX * 16, chunkZ * 16);
			this.components.add(netherTemplePiece);
			this.recalculateStructureSize();
			this.func_214626_a(this.rand, 63, 64);
		}
	}
}