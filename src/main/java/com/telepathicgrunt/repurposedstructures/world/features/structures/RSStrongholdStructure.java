//package com.telepathicgrunt.repurposedstructures.world.features.structures;
//
//import java.util.List;
//import java.util.Random;
//import java.util.function.Function;
//
//import com.mojang.datafixers.Dynamic;
//import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
//import net.minecraft.structure.StructureManager;
//import net.minecraft.structure.StructurePiece;
//import net.minecraft.structure.StructureStart;
//import net.minecraft.util.math.BlockBox;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.ChunkPos;
//import net.minecraft.util.math.Direction;
//import net.minecraft.world.World;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.biome.Biome.Category;
//import net.minecraft.world.biome.source.BiomeAccess;
//import net.minecraft.world.chunk.ChunkStatus;
//import net.minecraft.world.gen.ChunkRandom;
//import net.minecraft.world.gen.chunk.ChunkGenerator;
//import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
//import net.minecraft.world.gen.feature.DefaultFeatureConfig;
//import net.minecraft.world.gen.feature.StrongholdFeature;
//import net.minecraft.world.gen.feature.StructureFeature;
//
//
//public class RSStrongholdStructure extends StrongholdFeature
//{
//    /**
//     * --------------------------------------------------------------------------
//     * |									|
//     * |	HELLO READERS! IF YOU'RE HERE, YOU'RE PROBABLY			|
//     * |	LOOKING FOR A TUTORIAL ON HOW TO DO STRUCTURES			|
//     * |									|
//     * -------------------------------------------------------------------------
//     *
//     * Don't worry, I actually have a structure tutorial
//     * mod already setup for you to check out! It's full
//     * of comments on what does what and how to make structures.
//     *
//     * Here's the link! https://github.com/TelepathicGrunt/StructureTutorialMod
//     *
//     * Good luck and have fun modding!
//     */
//    public RSStrongholdStructure(Codec<DefaultFeatureConfig> config) {
//	super(config);
//    }
//
//
//    @Override
//    protected ChunkPos getStart(ChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ) {
//	int maxDistance = RepurposedStructures.RSStrongholdsConfig.strongholdSpawnrate.get();
//	int minDistance = (int) (maxDistance * 0.75f);
//	if (minDistance == 0) {
//	    minDistance = 1;
//	}
//	int k = x + maxDistance * spacingOffsetsX;
//	int l = z + maxDistance * spacingOffsetsZ;
//	int i1 = k < 0 ? k - maxDistance + 1 : k;
//	int j1 = l < 0 ? l - maxDistance + 1 : l;
//	int targetChunkX = i1 / maxDistance;
//	int targetChunkZ = j1 / maxDistance;
//	((ChunkRandom) random).setStructureSeed(chunkGenerator.getSeed(), targetChunkX, targetChunkZ, 148523564);
//	targetChunkX = targetChunkX * maxDistance;
//	targetChunkZ = targetChunkZ * maxDistance;
//	targetChunkX = targetChunkX + random.nextInt(maxDistance - minDistance);
//	targetChunkZ = targetChunkZ + random.nextInt(maxDistance - minDistance);
//	return new ChunkPos(targetChunkX, targetChunkZ);
//    }
//
//
//    @Override
//    public boolean shouldStartAt(BiomeAccess biomeManager, ChunkGenerator<?> chunkGenerator, Random random, int chunkPosX, int chunkPosZ, Biome biome) {
//
//	if (RepurposedStructures.RSStrongholdsConfig.useVanillaStronghold.get()) {
//	    return super.shouldStartAt(biomeManager, chunkGenerator, random, chunkPosX, chunkPosZ, biome);
//	}
//	else {
//	    ChunkPos chunkpos = this.getStart(chunkGenerator, random, chunkPosX, chunkPosZ, 0, 0);
//	    if (chunkPosX == chunkpos.x && chunkPosZ == chunkpos.z) {
//		if (chunkGenerator.hasStructure(biome, this)) {
//		    return true;
//		}
//	    }
//	}
//
//	return false;
//    }
//
//
//    @Override
//    public BlockPos locateStructure(World world, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, BlockPos position, int radius, boolean skipExistingChunks) {
//	if (RepurposedStructures.RSStrongholdsConfig.useVanillaStronghold.get()) {
//	    return super.locateStructure(world, chunkGenerator, position, radius, skipExistingChunks);
//	}
//	else {
//	    if (!chunkGenerator.getBiomeSource().hasStructureFeature(this)) {
//		return null;
//	    }
//	    else {
//		int chunkX = position.getX() >> 4;
//		int chunkZ = position.getZ() >> 4;
//		int currentRadius = 0;
//
//		for (ChunkRandom sharedseedrandom = new ChunkRandom(); currentRadius <= radius; ++currentRadius) {
//		    for (int x = -currentRadius; x <= currentRadius; ++x) {
//			boolean validXPos = x == -currentRadius || x == currentRadius;
//
//			for (int z = -currentRadius; z <= currentRadius; ++z) {
//			    boolean validZPos = z == -currentRadius || z == currentRadius;
//			    if (validXPos || validZPos) {
//				ChunkPos chunkpos = this.getStart(chunkGenerator, sharedseedrandom, chunkX, chunkZ, x, z);
//				StructureStart structurestart = world.getChunk(chunkpos.x, chunkpos.z, ChunkStatus.STRUCTURE_STARTS).getStructureStart(this.getName());
//				if (structurestart != null && structurestart.hasChildren()) {
//				    if (skipExistingChunks && structurestart.isInExistingChunk()) {
//					structurestart.incrementReferences();
//					return structurestart.getPos();
//				    }
//
//				    if (!skipExistingChunks) {
//					return structurestart.getPos();
//				    }
//				}
//
//				if (currentRadius == 0) {
//				    break;
//				}
//			    }
//			}
//
//			if (currentRadius == 0) {
//			    break;
//			}
//		    }
//		}
//
//		return null;
//	    }
//	}
//    }
//
//
//    @Override
//    public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
//	if (RepurposedStructures.RSStrongholdsConfig.useVanillaStronghold.get()) {
//	    return super.getStructureStartFactory();
//	}
//	else {
//	    return RSStrongholdStructure.Start::new;
//	}
//    }
//
//
//    @Override
//    public String getName() {
//	return "Stronghold";
//    }
//
//
//    @Override
//    public int getRadius() {
//	return 8;
//    }
//
//    public static class Start extends StructureStart<DefaultFeatureConfig>
//    {
//	public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
//	    super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
//	}
//
//
//	@Override
//	public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
//	    RSStrongholdPieces.prepareStructurePieces();
//	    RSStrongholdStructure.Type type = biomeIn.getCategory() == Category.NETHER && RepurposedStructures.RSStrongholdsConfig.allowNetherStronghold.get() ? RSStrongholdStructure.Type.NETHER : RSStrongholdStructure.Type.NORMAL;
//	    RSStrongholdPieces.EntranceStairs strongholdpieces$entrancestairs = new RSStrongholdPieces.EntranceStairs(this.random, (chunkX << 4) + 2, (chunkZ << 4) + 2, type);
//	    this.children.add(strongholdpieces$entrancestairs);
//	    strongholdpieces$entrancestairs.placeJigsaw(strongholdpieces$entrancestairs, this.children, this.random);
//	    List<StructurePiece> list = strongholdpieces$entrancestairs.pendingChildren;
//
//	    while (!list.isEmpty()) {
//		int i = this.random.nextInt(list.size());
//		StructurePiece structurepiece = list.remove(i);
//		structurepiece.placeJigsaw(strongholdpieces$entrancestairs, this.children, this.random);
//	    }
//
//	    if (strongholdpieces$entrancestairs.strongholdPortalRoom == null) {
//		BlockBox box = this.children.get(this.children.size() - 1).getBoundingBox();
//		RSStrongholdPieces.Stronghold portalRoom = RSStrongholdPieces.PortalRoom.createPiece(this.children, this.random, box.minX, box.minY + 1, box.minZ, Direction.NORTH, type);
//		this.children.add(portalRoom);
//		strongholdpieces$entrancestairs.pendingChildren.add(portalRoom);
//		list = strongholdpieces$entrancestairs.pendingChildren;
//
//		while (!list.isEmpty()) {
//		    int i = this.random.nextInt(list.size());
//		    StructurePiece structurepiece = list.remove(i);
//		    structurepiece.placeJigsaw(strongholdpieces$entrancestairs, this.children, this.random);
//		}
//	    }
//
//	    this.setBoundingBoxFromChildren();
//	    int lowestBounds = this.bounds.minY - 2;
//	    int maxYConfig = 0;
//	    int minYConfig = 0;
//
//	    if(type == RSStrongholdStructure.Type.NORMAL) {
//		maxYConfig = RepurposedStructures.RSStrongholdsConfig.normalStrongholdMaxHeight.get();
//		minYConfig = RepurposedStructures.RSStrongholdsConfig.normalStrongholdMinHeight.get();
//	    }
//	    else if(type == RSStrongholdStructure.Type.NETHER) {
//		maxYConfig = RepurposedStructures.RSStrongholdsConfig.netherStrongholdMaxHeight.get();
//		minYConfig = RepurposedStructures.RSStrongholdsConfig.netherStrongholdMinHeight.get();
//	    }
//
//
//	    int minimum = minYConfig;
//	    int maximum = Math.max(maxYConfig, minimum)+1;
//
//	    // Sets stronghold's bottom most y to a random range between min and max y config.
//	    int offset = this.random.nextInt(maximum-minimum) + minimum;
//	    int offset2 = 0;
//
//	    //apply first offset to be able to do some calculations in next few lines
//	    this.bounds.offset(0, offset - lowestBounds, 0);
//
//	    // If the stronghold's max y is over the config's max y, lower the stronghold as
//	    // much as possible without hitting bedrock.
//	    if(this.bounds.maxY > maxYConfig) {
//		int heightDiff = maxYConfig - this.bounds.maxY;
//		offset2 = Math.max(heightDiff, -this.bounds.minY);
//	    }
//
//	    // Apply the final offsets
//	    this.bounds.offset(0, offset2, 0);
//	    for (StructurePiece structurepiece : this.children) {
//		structurepiece.translate(0, offset + offset2 - lowestBounds, 0);
//	    }
//	}
//    }
//
//    public static enum Type {
//	NORMAL, NETHER;
//
//	public static RSStrongholdStructure.Type byId(int id) {
//	    return id >= 0 && id < values().length ? values()[id] : NORMAL;
//	}
//    }
//}