package com.telepathicgrunt.repurposedstructures.world.features.structures;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

/**
 * Used for taking the place of old structure registry names so using
 * old worlds on new version won't corrupt the world.
 */
//TODO: Remove dummy structure in 1.16
public class DummyMineshaftStructure extends Structure<NoFeatureConfig>
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
    public DummyMineshaftStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
	super(config);
    }


    @Override
    protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ) {
	return new ChunkPos(0, 0);
    }


    @Override
    public boolean canBeGenerated(BiomeManager biomeManager, ChunkGenerator<?> chunkGenerator, Random random, int chunkPosX, int chunkPosZ, Biome biome) {
	return false;
    }


    @Override
    public Structure.IStartFactory getStartFactory() {
	return DummyMineshaftStructure.Start::new;
    }


    @Override
    public String getStructureName() {
	return RepurposedStructures.MODID + ":mineshaft";
    }


    @Override
    public int getSize() {
	return 0;
    }

    public static class Start extends StructureStart
    {
	public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seed) {
	    super(structure, chunkX, chunkZ, mutableBoundingBox, referenceIn, seed);
	}


	@Override
	public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome) {
	}
    }
}