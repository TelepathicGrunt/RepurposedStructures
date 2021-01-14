package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GenericJigsawStructure extends AbstractBaseStructure {

    private final Identifier START_POOL;
    private final int STRUCTURE_SIZE;
    private final int CENTER_OFFSET;
    private final int BIOME_RANGE;
    private final int STRUCTURE_BLACKLIST_RANGE;
    private final Set<RSStructureTagMap.STRUCTURE_TAGS> AVOID_STRUCTURES_SET;
    private final List<SpawnSettings.SpawnEntry> MONSTER_SPAWNS;
    private final List<SpawnSettings.SpawnEntry> CREATURE_SPAWNS;

    public GenericJigsawStructure(Identifier poolID, int structureSize, int centerOffset, int biomeRange,
                                  int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet)
    {
        super(DefaultFeatureConfig.CODEC);
        START_POOL = poolID;
        STRUCTURE_SIZE = structureSize;
        CENTER_OFFSET = centerOffset;
        BIOME_RANGE = biomeRange;
        STRUCTURE_BLACKLIST_RANGE = structureBlacklistRange;
        AVOID_STRUCTURES_SET = avoidStructuresSet;
        RSStructures.RS_STRUCTURE_START_PIECES.add(START_POOL);
        MONSTER_SPAWNS = new ArrayList<>();
        CREATURE_SPAWNS = new ArrayList<>();
    }

    public GenericJigsawStructure(Identifier poolID, int structureSize, int centerOffset, int biomeRange,
                                  int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet,
                                  List<SpawnSettings.SpawnEntry> monster_spawns, List<SpawnSettings.SpawnEntry> creature_spawns)
    {
        super(DefaultFeatureConfig.CODEC);
        START_POOL = poolID;
        STRUCTURE_SIZE = structureSize;
        CENTER_OFFSET = centerOffset;
        BIOME_RANGE = biomeRange;
        STRUCTURE_BLACKLIST_RANGE = structureBlacklistRange;
        AVOID_STRUCTURES_SET = avoidStructuresSet;
        RSStructures.RS_STRUCTURE_START_PIECES.add(START_POOL);
        MONSTER_SPAWNS = monster_spawns;
        CREATURE_SPAWNS = creature_spawns;
    }


    @Override
    public List<SpawnSettings.SpawnEntry> getMonsterSpawns() {
        return MONSTER_SPAWNS;
    }

    @Override
    public List<SpawnSettings.SpawnEntry> getCreatureSpawns() {
        return CREATURE_SPAWNS;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {
         for (int curChunkX = chunkX - BIOME_RANGE; curChunkX <= chunkX + BIOME_RANGE; curChunkX++) {
            for (int curChunkZ = chunkZ - BIOME_RANGE; curChunkZ <= chunkZ + BIOME_RANGE; curChunkZ++) {
                if (!biomeSource.getBiomeForNoiseGen(curChunkX << 2, 60, curChunkZ << 2).getGenerationSettings().hasStructureFeature(this)) {
                    return false;
                }
            }
        }

        //cannot be near other specified structure
        for (int curChunkX = chunkX - STRUCTURE_BLACKLIST_RANGE; curChunkX <= chunkX + STRUCTURE_BLACKLIST_RANGE; curChunkX++) {
            for (int curChunkZ = chunkZ - STRUCTURE_BLACKLIST_RANGE; curChunkZ <= chunkZ + STRUCTURE_BLACKLIST_RANGE; curChunkZ++) {
                for(RSStructureTagMap.STRUCTURE_TAGS tag : AVOID_STRUCTURES_SET){
                    for(StructureFeature<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(tag)){
                        StructureConfig structureConfig = chunkGenerator.getStructuresConfig().getForType(structureFeature);
                        if(structureConfig != null){
                            ChunkPos chunkPos2 = structureFeature.getStartChunk(structureConfig, seed, chunkRandom, curChunkX, curChunkZ);
                            if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return GenericJigsawStructure.MainStart::new;
    }

    public class MainStart extends MarginedStructureStart<DefaultFeatureConfig> {
        public MainStart(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
            StructurePoolBasedGenerator.method_30419(
                    dynamicRegistryManager,
                    new StructurePoolFeatureConfig(() -> dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN).get(START_POOL), STRUCTURE_SIZE),
                    PoolStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.children,
                    this.random,
                    true,
                    true);
            this.setBoundingBoxFromChildren();
            this.children.get(0).translate(0, CENTER_OFFSET, 0);
        }
    }
}