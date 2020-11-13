package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

import java.util.Set;

public class GenericJigsawStructure extends AbstractBaseStructure {
    private final ResourceLocation START_POOL;
    private final int STRUCTURE_SIZE;
    private final int CENTER_OFFSET;
    private final int BIOME_RANGE;
    private final int STRUCTURE_BLACKLIST_RANGE;
    private final Set<RSStructureTagMap.STRUCTURE_TAGS> AVOID_STRUCTURES_SET;

    public GenericJigsawStructure(Codec<NoFeatureConfig> config, ResourceLocation poolRL, int structureSize, int centerOffset, int biomeRange, int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet) {
        super(config);
        START_POOL = poolRL;
        STRUCTURE_SIZE = structureSize;
        CENTER_OFFSET = centerOffset;
        BIOME_RANGE = biomeRange;
        STRUCTURE_BLACKLIST_RANGE = structureBlacklistRange;
        AVOID_STRUCTURES_SET = avoidStructuresSet;
        RSStructures.RS_STRUCTURE_START_PIECES.add(START_POOL);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig NoFeatureConfig) {
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
                    for(Structure<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(tag)){
                        StructureSeparationSettings structureConfig = chunkGenerator.getStructuresConfig().getForType(structureFeature);
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
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return GenericJigsawStructure.MainStart::new;
    }

    public class MainStart extends MarginedStructureStart<NoFeatureConfig> {
        public MainStart(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void init(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {

            BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
            JigsawManager.method_30419(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN)
                            .getOrDefault(START_POOL),
                            STRUCTURE_SIZE),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.components,
                    this.rand,
                    true,
                    true);
            this.recalculateStructureSize();
            this.components.get(0).offset(0, CENTER_OFFSET, 0);
        }
    }
}