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


public class RSMineshaftBirchStructure extends Structure<NoFeatureConfig>
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
    public RSMineshaftBirchStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
	super(config);
    }


    @Override
    protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ) {
	int xChunk = x + spacingOffsetsX;
	int zChunk = z + spacingOffsetsZ;
	((SharedSeedRandom) random).setLargeFeatureSeed(chunkGenerator.getSeed() + 1, xChunk, zChunk);
	if (random.nextDouble() < (RepurposedStructures.RSConfig.birchMineshaftSpawnrate.get() / 10000D)) {
	    return new ChunkPos(xChunk, zChunk);
	}

	return new ChunkPos(Integer.MAX_VALUE, Integer.MAX_VALUE); // always will fail
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
	return RSMineshaftBirchStructure.Start::new;
    }


    @Override
    public String getStructureName() {
	return RepurposedStructures.MODID + ":mineshaft_birch";
    }


    @Override
    public int getSize() {
	return 8;
    }

    public static class Start extends StructureStart
    {
	public Start(Structure<?> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
	    super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
	}


	@Override
	public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
	    RSMineshaftPieces.Room structuremineshaftpiecesua$room = new RSMineshaftPieces.Room(0, this.rand, (chunkX << 4) + 2, (chunkZ << 4) + 2, RSMineshaftPieces.Type.BIRCH);
	    this.components.add(structuremineshaftpiecesua$room);

	    structuremineshaftpiecesua$room.buildComponent(structuremineshaftpiecesua$room, this.components, this.rand);
	    this.recalculateStructureSize();
	    this.func_214628_a(generator.getSeaLevel(), this.rand, 10);
	}
    }

}
