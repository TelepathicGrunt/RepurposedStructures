package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.misc.PieceLimitedJigsawManager;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.List;
import java.util.Map;


public class RSNetherStrongholdStructure extends AdvancedJigsawStructure {
    private static final Pool<SpawnSettings.SpawnEntry> MONSTER_SPAWNS = Pool.of(Lists.newArrayList(
            new SpawnSettings.SpawnEntry(EntityType.BLAZE, 10, 2, 3),
            new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 3, 4, 4),
            new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 10, 5, 5),
            new SpawnSettings.SpawnEntry(EntityType.SKELETON, 2, 5, 5),
            new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 3, 4, 4)));

    public RSNetherStrongholdStructure(Identifier poolID, int structureSize, Map<Identifier, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces, int maxY, int minY) {
        super(poolID, structureSize, MONSTER_SPAWNS, requiredPieces, maxY, minY);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig featureConfig, HeightLimitView heightLimitView) {
        int radius = 2817;
        int xBlockPos = chunkPos1.getStartX();
        int zBlockPos = chunkPos1.getStartZ();
        return (xBlockPos * xBlockPos) + (zBlockPos * zBlockPos) > radius * radius;
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return RSNetherStrongholdStructure.Start::new;
    }

    public class Start extends MainStart {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
            BlockPos.Mutable blockpos = new BlockPos.Mutable(chunkPos1.getStartX(), 0, chunkPos1.getStartZ());

            // -5 so that the start piece's bottom 2 jigsaw blocks can spawn extra pieces and the rest of the stronghold wont go as high as start stairway
            blockpos.move(Direction.UP, maxY - 5);

            PieceLimitedJigsawManager.assembleJigsawStructure(
                    dynamicRegistryManager,
                    new StructurePoolFeatureConfig(() -> dynamicRegistryManager.get(Registry.STRUCTURE_POOL_KEY).get(startPool), structureSize),
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.children,
                    this.random,
                    false,
                    false,
                    heightLimitView,
                    requiredPieces,
                    maxY,
                    minY);

            this.setBoundingBoxFromChildren();
        }
    }
}