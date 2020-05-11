package com.telepathicgrunt.repurposedstructures.world.features.structures;

import java.util.List;
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
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class JungleFortressStructure extends Structure<NoFeatureConfig>
{
    /**
     * --------------------------------------------------------------------------
     * |									|
     * |	HELLO READERS! IF YOU'RE HERE, YOU'RE PROBABLY			|
     * |	LOOKING FOR A TUTORIAL ON HOW TO DO STRUCTURES			|
     * |									|
     * -------------------------------------------------------------------------
     * 
     * Don't worry, I actually have a structure tutorial
     * mod already setup for you to check out! It's full
     * of comments on what does what and how to make structures.
     * 
     * Here's the link! https://github.com/TelepathicGrunt/StructureTutorialMod
     * 
     * Good luck and have fun modding!
     */
	public JungleFortressStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51427_1_)
	{
		super(p_i51427_1_);
	}


	@Override
	protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ)
	{
		int maxDistance = RepurposedStructures.RSConfig.jungleFortressSpawnrate.get();
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
		((SharedSeedRandom) random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), targetChunkX, targetChunkZ, 143525587);
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
			for (Biome nearbyBiome : chunkGenerator.getBiomeProvider().getBiomes(chunkPosX * 16 + 9, chunkGenerator.getSeaLevel(), chunkPosZ * 16 + 9, 32))
			{
				if (!chunkGenerator.hasStructure(nearbyBiome, this))
				{
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}


	@Override
	public Structure.IStartFactory getStartFactory()
	{
		return JungleFortressStructure.Start::new;
	}


	@Override
	public String getStructureName()
	{
		return RepurposedStructures.MODID + ":fortress_jungle";
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
			JungleFortressPieces.Start fortresspieces$start = new JungleFortressPieces.Start(this.rand, (chunkX << 4) + 2, (chunkZ << 4) + 2);
			this.components.add(fortresspieces$start);

			fortresspieces$start.buildComponent(fortresspieces$start, this.components, this.rand);
			List<StructurePiece> list = fortresspieces$start.pendingChildren;

			while (!list.isEmpty())
			{
				int i = this.rand.nextInt(list.size());
				StructurePiece structurepiece = list.remove(i);
				structurepiece.buildComponent(fortresspieces$start, this.components, this.rand);
			}

			this.recalculateStructureSize();
			this.func_214626_a(this.rand, 58, 63);
		}
	}
}