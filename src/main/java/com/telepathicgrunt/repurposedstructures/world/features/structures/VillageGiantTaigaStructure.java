package com.telepathicgrunt.repurposedstructures.world.features.structures;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.structure.VillagePieces;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class VillageGiantTaigaStructure extends Structure<NoFeatureConfig>
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
    public VillageGiantTaigaStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
	super(config);
    }


    @Override
    protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ) {
	int maxDistance = RepurposedStructures.RSVillagesConfig.giantTaigaVillageSpawnrate.get();
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
	((SharedSeedRandom) random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), targetChunkX, targetChunkZ, 14983239);
	targetChunkX = targetChunkX * maxDistance;
	targetChunkZ = targetChunkZ * maxDistance;
	targetChunkX = targetChunkX + random.nextInt(maxDistance - minDistance);
	targetChunkZ = targetChunkZ + random.nextInt(maxDistance - minDistance);
	return new ChunkPos(targetChunkX, targetChunkZ);
    }


    @Override
    public boolean canBeGenerated(BiomeManager biomeManager, ChunkGenerator<?> chunkGenerator, Random random, int chunkPosX, int chunkPosZ, Biome biome) {
	ChunkPos chunkpos = this.getStartPositionForPosition(chunkGenerator, random, chunkPosX, chunkPosZ, 0, 0);
	if (chunkPosX == chunkpos.x && chunkPosZ == chunkpos.z && chunkGenerator.hasStructure(biome, this)) {

	    for (int x = -1; x <= 1; x++) {
		for (int z = -1; z <= 1; z++) {
		    
		    if (!chunkGenerator.hasStructure(biomeManager.getBiome(new BlockPos((chunkPosX + x) * 16, 60, (chunkPosZ + z) * 16)), this)) 
		    {
			return false;
		    }
		}
	    }

	    return true;
	}
	return false;
    }

    public Structure.IStartFactory getStartFactory() {
	return VillageGiantTaigaStructure.Start::new;
    }


    public String getStructureName() {
	return RepurposedStructures.MODID + ":village_giant_taiga";
    }


    public int getSize() {
	return 8;
    }

    public static class Start extends MarginedStructureStart
    {
	public Start(Structure<?> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
	    super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
	}


	public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biomeIn) {
	    BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
	    VillagePieces.addPieces(generator, templateManager, blockpos, this.components, this.rand, new VillageConfig(RepurposedStructures.MODID + ":village/giant_taiga/town_centers", 6));
	    this.recalculateStructureSize();
	}
    }
}