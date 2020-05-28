package com.telepathicgrunt.repurposedstructures.world.features.structures;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;

import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class IglooStoneStructure extends Structure<NoFeatureConfig>
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
    public IglooStoneStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
	super(config);
    }


    @Override
    protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ) {
	int maxDistance = RepurposedStructures.RSConfig.grassyIglooSpawnrate.get();
	int minDistance = (int) (maxDistance * 0.75f);
	if (minDistance == 0) {
	    minDistance = 1;
	}
	int k = x + maxDistance * spacingOffsetsX;
	int l = z + maxDistance * spacingOffsetsZ;
	int i1 = k < 0 ? k - maxDistance + 1 : k;
	int j1 = l < 0 ? l - maxDistance + 1 : l;
	int targetChunkX = i1 / maxDistance;
	int targetChunkZ = j1 / maxDistance;
	((SharedSeedRandom) random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), targetChunkX, targetChunkZ, 148525457);
	targetChunkX = targetChunkX * maxDistance;
	targetChunkZ = targetChunkZ * maxDistance;
	targetChunkX = targetChunkX + random.nextInt(maxDistance - minDistance);
	targetChunkZ = targetChunkZ + random.nextInt(maxDistance - minDistance);
	return new ChunkPos(targetChunkX, targetChunkZ);
    }


    @Override
    public boolean canBeGenerated(BiomeManager biomeManager, ChunkGenerator<?> chunkGenerator, Random random, int chunkPosX, int chunkPosZ, Biome biome) {

	ChunkPos chunkpos = this.getStartPositionForPosition(chunkGenerator, random, chunkPosX, chunkPosZ, 0, 0);
	if (chunkPosX == chunkpos.x && chunkPosZ == chunkpos.z) {
	    if (chunkGenerator.hasStructure(biome, this)) {
		return true;
	    }
	}

	return false;
    }


    @Override
    public Structure.IStartFactory getStartFactory() {
	return IglooStoneStructure.Start::new;
    }


    @Override
    public String getStructureName() {
	return RepurposedStructures.MODID + ":igloo_stone";
    }


    @Override
    public int getSize() {
	return 8;
    }

    public static class Start extends StructureStart
    {
	private static ResourceLocation TOP_PIECE_RL = new ResourceLocation(RepurposedStructures.MODID + ":stone_igloo/top");

	public Start(Structure<?> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
	    super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
	}


	@Override
	public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
	    int x = chunkX * 16;
	    int z = chunkZ * 16;
	    BlockPos blockpos = new BlockPos(x, 90, z);
	    Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
	    RSIglooPieces.func_207617_a(templateManagerIn, TOP_PIECE_RL, Blocks.PODZOL, blockpos, rotation, this.components, this.rand, IFeatureConfig.NO_FEATURE_CONFIG);
	    this.recalculateStructureSize();
	}
    }
}