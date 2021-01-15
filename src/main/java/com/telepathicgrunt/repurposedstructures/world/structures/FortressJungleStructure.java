package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.FortressJunglePieces;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.List;


public class FortressJungleStructure extends AbstractBaseStructure {
    public FortressJungleStructure() {
        super(DefaultFeatureConfig.CODEC);
    }

    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long l, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {
        int radius = 4;
        for (int curChunkX = chunkX - radius; curChunkX <= chunkX + radius; curChunkX += radius) {
            for (int curChunkZ = chunkZ - radius; curChunkZ <= chunkZ + radius; curChunkZ += radius) {
                if (!biomeSource.getBiomeForNoiseGen(curChunkX << 2, 60, curChunkZ << 2).getGenerationSettings().hasStructureFeature(RSStructures.JUNGLE_FORTRESS)) {
                    return false;
                }
            }
        }

        return true;
    }


    @Override
    public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return FortressJungleStructure.Start::new;
    }

    private static final List<SpawnSettings.SpawnEntry> MONSTER_SPAWNS =
            Lists.newArrayList(
                    new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 27, 1, 1),
                    new SpawnSettings.SpawnEntry(EntityType.DROWNED, 27, 1, 4)
            );

    @Override
    public List<SpawnSettings.SpawnEntry> getMonsterSpawns() {
        return MONSTER_SPAWNS;
    }


    public static class Start extends StructureStart<DefaultFeatureConfig> {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }


        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            FortressJunglePieces.Start fortresspieces$start = new FortressJunglePieces.Start(this.random, (chunkX << 4) + 2, (chunkZ << 4) + 2);
            this.children.add(fortresspieces$start);

            fortresspieces$start.fillOpenings(fortresspieces$start, this.children, this.random);
            List<StructurePiece> list = fortresspieces$start.pendingChildren;

            while (!list.isEmpty()) {
                int i = this.random.nextInt(list.size());
                StructurePiece structurepiece = list.remove(i);
                structurepiece.fillOpenings(fortresspieces$start, this.children, this.random);
            }

            this.setBoundingBoxFromChildren();
            this.randomUpwardTranslation(this.random, 55, 60);
        }
    }
}