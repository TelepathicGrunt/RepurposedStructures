package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.misc.PieceLimitedJigsawManager;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.entity.EntityType;
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
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.common.util.Lazy;

import java.util.List;
import java.util.Map;


public class RSNetherStrongholdStructure extends AdvancedJigsawStructure {
    private static final List<MobSpawnInfo.Spawners> MONSTER_SPAWNS = Lists.newArrayList(
            new MobSpawnInfo.Spawners(EntityType.BLAZE, 10, 2, 3),
            new MobSpawnInfo.Spawners(EntityType.ZOMBIFIED_PIGLIN, 3, 4, 4),
            new MobSpawnInfo.Spawners(EntityType.WITHER_SKELETON, 10, 5, 5),
            new MobSpawnInfo.Spawners(EntityType.SKELETON, 2, 5, 5),
            new MobSpawnInfo.Spawners(EntityType.MAGMA_CUBE, 3, 4, 4));

    public RSNetherStrongholdStructure(ResourceLocation poolID, Lazy<Integer> structureSize, Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces, Lazy<Integer> maxY, Lazy<Integer> minY) {
        super(poolID, structureSize, MONSTER_SPAWNS, requiredPieces, maxY, minY);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int x, int z, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        return (x * x) + (z * z) > 31000;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    public class Start extends MainStart {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig noFeatureConfig) {
            BlockPos.Mutable blockpos = new BlockPos.Mutable(chunkX * 16, 0, chunkZ * 16);

            // -5 so that the start piece's bottom 2 jigsaw blocks can spawn extra pieces and the rest of the stronghold wont go as high as start stairway
            blockpos.move(Direction.UP, maxY.get() - 5);

            PieceLimitedJigsawManager.assembleJigsawStructure(
                    dynamicRegistryManager,
                    new VillageConfig(() ->
                            dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(startPool),
                            structureSize.get()),
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.pieces,
                    this.random,
                    false,
                    false,
                    requiredPieces,
                    maxY.get(),
                    minY.get());

            this.calculateBoundingBox();
        }
    }
}