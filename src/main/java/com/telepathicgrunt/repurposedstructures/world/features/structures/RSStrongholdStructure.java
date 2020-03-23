package com.telepathicgrunt.repurposedstructures.world.features.structures;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;

import net.minecraft.util.Direction;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StrongholdStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class RSStrongholdStructure extends StrongholdStructure
{
	public RSStrongholdStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> config)
	{
		super(config);
	}


	@Override
	protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ)
	{
		int maxDistance = RepurposedStructures.RSConfig.strongholdSpawnrate.get();
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
		((SharedSeedRandom) random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), targetChunkX, targetChunkZ, 148523564);
		targetChunkX = targetChunkX * maxDistance;
		targetChunkZ = targetChunkZ * maxDistance;
		targetChunkX = targetChunkX + random.nextInt(maxDistance - minDistance);
		targetChunkZ = targetChunkZ + random.nextInt(maxDistance - minDistance);
		return new ChunkPos(targetChunkX, targetChunkZ);
	}


	@Override
	public boolean func_225558_a_(BiomeManager biomeManager, ChunkGenerator<?> chunkGenerator, Random random, int chunkPosX, int chunkPosZ, Biome biome)
	{

		if (RepurposedStructures.RSConfig.useVanillaStronghold.get())
		{
			return super.func_225558_a_(biomeManager, chunkGenerator, random, chunkPosX, chunkPosZ, biome);
		}
		else
		{
			ChunkPos chunkpos = this.getStartPositionForPosition(chunkGenerator, random, chunkPosX, chunkPosZ, 0, 0);
			if (chunkPosX == chunkpos.x && chunkPosZ == chunkpos.z)
			{
				if (chunkGenerator.hasStructure(biome, this))
				{
					return true;
				}
			}
		}

		return false;
	}


	@Override
	public BlockPos findNearest(World world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, BlockPos position, int radius, boolean skipExistingChunks)
	{
		if (RepurposedStructures.RSConfig.useVanillaStronghold.get())
		{
			return super.findNearest(world, chunkGenerator, position, radius, skipExistingChunks);
		}
		else
		{
			if (!chunkGenerator.getBiomeProvider().hasStructure(this))
			{
				return null;
			}
			else
			{
				int chunkX = position.getX() >> 4;
				int chunkZ = position.getZ() >> 4;
				int currentRadius = 0;

				for (SharedSeedRandom sharedseedrandom = new SharedSeedRandom(); currentRadius <= radius; ++currentRadius)
				{
					for (int x = -currentRadius; x <= currentRadius; ++x)
					{
						boolean validXPos = x == -currentRadius || x == currentRadius;

						for (int z = -currentRadius; z <= currentRadius; ++z)
						{
							boolean validZPos = z == -currentRadius || z == currentRadius;
							if (validXPos || validZPos)
							{
								ChunkPos chunkpos = this.getStartPositionForPosition(chunkGenerator, sharedseedrandom, chunkX, chunkZ, x, z);
								StructureStart structurestart = world.getChunk(chunkpos.x, chunkpos.z, ChunkStatus.STRUCTURE_STARTS).getStructureStart(this.getStructureName());
								if (structurestart != null && structurestart.isValid())
								{
									if (skipExistingChunks && structurestart.isRefCountBelowMax())
									{
										structurestart.incrementRefCount();
										return structurestart.getPos();
									}

									if (!skipExistingChunks)
									{
										return structurestart.getPos();
									}
								}

								if (currentRadius == 0)
								{
									break;
								}
							}
						}

						if (currentRadius == 0)
						{
							break;
						}
					}
				}

				return null;
			}
		}
	}


	@Override
	public Structure.IStartFactory getStartFactory()
	{
		if (RepurposedStructures.RSConfig.useVanillaStronghold.get())
		{
			return super.getStartFactory();
		}
		else
		{
			return RSStrongholdStructure.Start::new;
		}
	}


	@Override
	public String getStructureName()
	{
		return "Stronghold";
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
			RSStrongholdPieces.prepareStructurePieces();
			RSStrongholdStructure.Type type = biomeIn.getCategory() == Category.NETHER && RepurposedStructures.RSConfig.allowNetherStronghold.get() ? RSStrongholdStructure.Type.NETHER : RSStrongholdStructure.Type.NORMAL;
			RSStrongholdPieces.EntranceStairs strongholdpieces$entrancestairs = new RSStrongholdPieces.EntranceStairs(this.rand, (chunkX << 4) + 2, (chunkZ << 4) + 2, type);
			this.components.add(strongholdpieces$entrancestairs);
			strongholdpieces$entrancestairs.buildComponent(strongholdpieces$entrancestairs, this.components, this.rand);
			List<StructurePiece> list = strongholdpieces$entrancestairs.pendingChildren;

			while (!list.isEmpty())
			{
				int i = this.rand.nextInt(list.size());
				StructurePiece structurepiece = list.remove(i);
				structurepiece.buildComponent(strongholdpieces$entrancestairs, this.components, this.rand);
			}

			if (strongholdpieces$entrancestairs.strongholdPortalRoom == null)
			{
				MutableBoundingBox box = this.components.get(this.components.size() - 1).getBoundingBox();
				RSStrongholdPieces.Stronghold portalRoom = RSStrongholdPieces.PortalRoom.createPiece(this.components, this.rand, box.minX, box.minY + 1, box.minZ, Direction.NORTH, type);
				this.components.add(portalRoom);
				strongholdpieces$entrancestairs.pendingChildren.add(portalRoom);
				list = strongholdpieces$entrancestairs.pendingChildren;

				while (!list.isEmpty())
				{
					int i = this.rand.nextInt(list.size());
					StructurePiece structurepiece = list.remove(i);
					structurepiece.buildComponent(strongholdpieces$entrancestairs, this.components, this.rand);
				}
			}

			this.recalculateStructureSize();
			this.func_214628_a(generator.getSeaLevel(), this.rand, 10);

		}
	}

	public static enum Type
	{
		NORMAL, NETHER;

		public static RSStrongholdStructure.Type byId(int id)
		{
			return id >= 0 && id < values().length ? values()[id] : NORMAL;
		}
	}
}