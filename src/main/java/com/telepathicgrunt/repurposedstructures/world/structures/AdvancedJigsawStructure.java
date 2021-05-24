package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.misc.PieceLimitedJigsawManager;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.CheckerboardBiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdvancedJigsawStructure extends AbstractBaseStructure<DefaultFeatureConfig> {

    protected final Identifier startPool;
    protected final int structureSize;
    protected final int biomeRange;
    protected final List<SpawnSettings.SpawnEntry> monsterSpawns;
    protected final List<SpawnSettings.SpawnEntry> creatureSpawns;
    protected final Map<Identifier, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces;
    protected final int maxY;
    protected final int minY;
    protected final boolean clipOutOfBoundsPieces;


    public AdvancedJigsawStructure(Identifier poolID, int structureSize, Map<Identifier, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces, int maxY, int minY) {
        this(poolID, structureSize, 0, new ArrayList<>(), new ArrayList<>(), requiredPieces, maxY, minY, true);
    }

    public AdvancedJigsawStructure(Identifier poolID, int structureSize, List<SpawnSettings.SpawnEntry> monsterSpawns, Map<Identifier, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces, int maxY, int minY) {
        this(poolID, structureSize, 0, monsterSpawns, new ArrayList<>(), requiredPieces, maxY, minY, true);
    }

    public AdvancedJigsawStructure(Identifier poolID, int structureSize, int biomeRange, List<SpawnSettings.SpawnEntry> monsterSpawns, Map<Identifier, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces, int maxY, int minY, boolean clipOutOfBoundsPieces) {
        this(poolID, structureSize, biomeRange, monsterSpawns, new ArrayList<>(), requiredPieces, maxY, minY, clipOutOfBoundsPieces);
    }

    public AdvancedJigsawStructure(Identifier poolID, int structureSize, int biomeRange,
                                   List<SpawnSettings.SpawnEntry> monsterSpawns, List<SpawnSettings.SpawnEntry> creatureSpawns,
                                   Map<Identifier, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces, int maxY, int minY,
                                   boolean clipOutOfBoundsPieces)
    {
        super(DefaultFeatureConfig.CODEC);

        this.startPool = poolID;
        this.structureSize = structureSize;
        this.biomeRange = biomeRange;
        this.monsterSpawns = monsterSpawns;
        this.creatureSpawns = creatureSpawns;
        this.requiredPieces = requiredPieces;
        this.maxY = maxY;
        this.minY = minY;
        this.clipOutOfBoundsPieces = clipOutOfBoundsPieces;

        RSStructures.RS_STRUCTURE_START_PIECES.add(startPool);
    }

    @Override
    public List<SpawnSettings.SpawnEntry> getMonsterSpawns() {
        return monsterSpawns;
    }

    @Override
    public List<SpawnSettings.SpawnEntry> getCreatureSpawns() {
        return creatureSpawns;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {
        if(!(biomeSource instanceof CheckerboardBiomeSource)) {
            for (int curChunkX = chunkX - biomeRange; curChunkX <= chunkX + biomeRange; curChunkX++) {
                for (int curChunkZ = chunkZ - biomeRange; curChunkZ <= chunkZ + biomeRange; curChunkZ++) {
                    if (!biomeSource.getBiomeForNoiseGen(curChunkX << 2, 64, curChunkZ << 2).getGenerationSettings().hasStructureFeature(this)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return AdvancedJigsawStructure.MainStart::new;
    }

    public class MainStart extends MarginedStructureStart<DefaultFeatureConfig> {

        public MainStart(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            BlockPos.Mutable blockpos = new BlockPos.Mutable(chunkX * 16, 0, chunkZ * 16);
            if(maxY - minY <= 0){
                RepurposedStructures.LOGGER.error("MinY should always be less than MaxY or else a crash will occur or no pieces will spawn. Problematic structure is:" + Registry.STRUCTURE_FEATURE.getId(this.getFeature()));
            }
            int structureStartHeight = random.nextInt(maxY - minY) + minY;
            blockpos.move(Direction.UP, structureStartHeight);

            PieceLimitedJigsawManager.assembleJigsawStructure(
                    dynamicRegistryManager,
                    new StructurePoolFeatureConfig(() -> dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN).get(startPool), structureSize),
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.children,
                    this.random,
                    false,
                    false,
                    requiredPieces,
                    // Help make sure the Jigsaw Blocks have room to spawn new pieces if structure is right on edge of maxY or minY
                    clipOutOfBoundsPieces ? maxY + 5 : Integer.MAX_VALUE,
                    clipOutOfBoundsPieces ? minY - 5 : Integer.MIN_VALUE);

            this.setBoundingBoxFromChildren();

//            // For jungle fortress. Needs better refactoring
//            if(maxY == Integer.MAX_VALUE && minY == Integer.MIN_VALUE){
//                this.randomUpwardTranslation(this.random, chunkGenerator.getSeaLevel() - 12, chunkGenerator.getSeaLevel() - 7);
//            }
        }
    }
}