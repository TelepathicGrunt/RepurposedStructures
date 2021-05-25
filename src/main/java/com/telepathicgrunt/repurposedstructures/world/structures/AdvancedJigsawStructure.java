package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.PieceLimitedJigsawManager;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.CheckerboardBiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.common.util.Lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdvancedJigsawStructure extends AbstractBaseStructure<NoFeatureConfig> {

    protected final ResourceLocation startPool;
    protected final Lazy<Integer> structureSize;
    protected final Lazy<Integer> biomeRange;
    protected final List<MobSpawnInfo.Spawners> monsterSpawns;
    protected final List<MobSpawnInfo.Spawners> creatureSpawns;
    protected final Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces;
    protected final Lazy<Integer> maxY;
    protected final Lazy<Integer> minY;
    protected final Lazy<Integer> verticalRange;
    protected final boolean clipOutOfBoundsPieces;


    public AdvancedJigsawStructure(ResourceLocation poolID, Lazy<Integer> structureSize, Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces, Lazy<Integer> maxY, Lazy<Integer> minY) {
        this(poolID, structureSize, Lazy.of(() -> 0), new ArrayList<>(), new ArrayList<>(), requiredPieces, maxY, minY, true, null);
    }

    public AdvancedJigsawStructure(ResourceLocation poolID, Lazy<Integer> structureSize, List<MobSpawnInfo.Spawners> monsterSpawns, Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces, Lazy<Integer> maxY, Lazy<Integer> minY) {
        this(poolID, structureSize, Lazy.of(() -> 0), monsterSpawns, new ArrayList<>(), requiredPieces, maxY, minY, true, null);
    }

    public AdvancedJigsawStructure(ResourceLocation poolID, Lazy<Integer> structureSize, Lazy<Integer> biomeRange, List<MobSpawnInfo.Spawners> monsterSpawns, Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces, Lazy<Integer> maxY, Lazy<Integer> minY, boolean clipOutOfBoundsPieces, Lazy<Integer> verticalRange) {
        this(poolID, structureSize, biomeRange, monsterSpawns, new ArrayList<>(), requiredPieces, maxY, minY, clipOutOfBoundsPieces, verticalRange);
    }

    public AdvancedJigsawStructure(ResourceLocation poolID, Lazy<Integer> structureSize, Lazy<Integer> biomeRange,
                                   List<MobSpawnInfo.Spawners> monsterSpawns, List<MobSpawnInfo.Spawners> creatureSpawns,
                                   Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces, Lazy<Integer> maxY, Lazy<Integer> minY,
                                   boolean clipOutOfBoundsPieces, Lazy<Integer> verticalRange)
    {
        super(NoFeatureConfig.CODEC);

        this.startPool = poolID;
        this.structureSize = structureSize;
        this.biomeRange = biomeRange;
        this.monsterSpawns = monsterSpawns;
        this.creatureSpawns = creatureSpawns;
        this.requiredPieces = requiredPieces;
        this.maxY = maxY;
        this.minY = minY;
        this.clipOutOfBoundsPieces = clipOutOfBoundsPieces;
        this.verticalRange = verticalRange;

        RSStructures.RS_STRUCTURE_START_PIECES.add(startPool);
    }

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return monsterSpawns;
    }

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultCreatureSpawnList() {
        return creatureSpawns;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig noFeatureConfig) {
        if(!(biomeSource instanceof CheckerboardBiomeProvider)) {
            int range = biomeRange.get();
            for (int curChunkX = chunkX - range; curChunkX <= chunkX + range; curChunkX++) {
                for (int curChunkZ = chunkZ - range; curChunkZ <= chunkZ + range; curChunkZ++) {
                    if (!biomeSource.getNoiseBiome(curChunkX << 2, 64, curChunkZ << 2).getGenerationSettings().isValidStart(this)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return MainStart::new;
    }

    public class MainStart extends MarginedStructureStart<NoFeatureConfig> {

        public MainStart(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig noFeatureConfig) {
            BlockPos.Mutable blockpos = new BlockPos.Mutable(chunkX * 16, 0, chunkZ * 16);
            if(maxY.get() - minY.get() <= 0){
                RepurposedStructures.LOGGER.error("MinY should always be less than MaxY or else a crash will occur or no pieces will spawn. Problematic structure is:" + Registry.STRUCTURE_FEATURE.getId(this.getFeature()));
            }
            int structureStartHeight = random.nextInt(maxY.get() - minY.get()) + minY.get();
            blockpos.move(Direction.UP, structureStartHeight);

            int topClipOff;
            int bottomClipOff;
            if(verticalRange == null){
                // Help make sure the Jigsaw Blocks have room to spawn new pieces if structure is right on edge of maxY or minY
                topClipOff = clipOutOfBoundsPieces ? maxY.get() + 5 : Integer.MAX_VALUE;
                bottomClipOff = clipOutOfBoundsPieces ? minY.get() - 5 : Integer.MIN_VALUE;
            }
            else{
                topClipOff = structureStartHeight + verticalRange.get();
                bottomClipOff = structureStartHeight + verticalRange.get();
            }

            PieceLimitedJigsawManager.assembleJigsawStructure(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
                            .get(startPool),
                            structureSize.get()),
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.pieces,
                    this.random,
                    false,
                    false,
                    requiredPieces,
                    topClipOff,
                    bottomClipOff);

            this.calculateBoundingBox();
        }
    }
}