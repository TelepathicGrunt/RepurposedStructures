package com.telepathicgrunt.repurposedstructures.world.features.structures;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.telepathicgrunt.repurposedstructures.RSConfig;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.features.RSFeatures;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class RSMineshaftStructure extends Structure<RSMineshaftConfig>
{
	public RSMineshaftStructure(Function<Dynamic<?>, ? extends RSMineshaftConfig> p_i51427_1_)
	{
		super(p_i51427_1_);
	}


	@Override
	public boolean func_225558_a_(BiomeManager biomeManager, ChunkGenerator<?> chunkGenerator, Random random, int chunkPosX, int chunkPosZ, Biome biome)
	{
		((SharedSeedRandom) random).setLargeFeatureSeed(chunkGenerator.getSeed(), chunkPosX, chunkPosZ);

		if (chunkGenerator.hasStructure(biome, this))
		{
			return random.nextDouble() < (RSConfig.mineshaftSpawnrate) / 10000D;
		}
		else
		{
			return false;
		}
	}


	@Override
	public Structure.IStartFactory getStartFactory()
	{
		return RSMineshaftStructure.Start::new;
	}


	@Override
	public String getStructureName()
	{
		return RepurposedStructures.MODID + ":mineshaft";
	}


	@Override
	public int getSize()
	{
		return 8;
	}

	public static class Start extends StructureStart
	{
		private RSMineshaftStructure.Type type;


		public Start(Structure<?> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn)
		{
			super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
		}


		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn)
		{
			RSMineshaftConfig mineshaftconfig = generator.getStructureConfig(biomeIn, RSFeatures.MINESHAFT);
			this.type = mineshaftconfig.type;
			RSMineshaftPieces.Room structuremineshaftpiecesua$room = new RSMineshaftPieces.Room(0, this.rand, (chunkX << 4) + 2, (chunkZ << 4) + 2, this.type);
			this.components.add(structuremineshaftpiecesua$room);

			structuremineshaftpiecesua$room.buildComponent(structuremineshaftpiecesua$room, this.components, this.rand);
			this.recalculateStructureSize();
            this.func_214628_a(generator.getSeaLevel(), this.rand, 10);
		}
	}

	public static enum Type
	{
		ICEY, BIRCH, JUNGLE, TAIGA, DESERT, STONE, SAVANNA, SWAMPORDARKFOREST, END, HELL, OCEAN;

		public static RSMineshaftStructure.Type byId(int id)
		{
			return id >= 0 && id < values().length ? values()[id] : BIRCH;
		}
	}
}
