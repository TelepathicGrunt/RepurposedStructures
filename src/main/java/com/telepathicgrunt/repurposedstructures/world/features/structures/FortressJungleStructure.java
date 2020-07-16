package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RSFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.List;


public class FortressJungleStructure extends StructureFeature<DefaultFeatureConfig> {
    public FortressJungleStructure(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long l, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {
        int x = chunkX >> 4;
        int z = chunkZ >> 4;

        for (int curChunkX = chunkX - 2; curChunkX <= chunkX + 2; curChunkX += 2) {
            for (int curChunkZ = chunkZ - 2; curChunkZ <= chunkZ + 2; curChunkZ += 2) {
                if (biomeSource.getBiomeForNoiseGen(curChunkX * 16, 60, curChunkZ * 16).hasStructureFeature(RSFeatures.JUNGLE_FORTRESS)) {
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

    private static final List<Biome.SpawnEntry> MONSTER_SPAWNS =
            Lists.newArrayList(new Biome.SpawnEntry(EntityType.WITHER_SKELETON, 27, 1, 1));

    @Override
    public List<Biome.SpawnEntry> getMonsterSpawns() {
        return MONSTER_SPAWNS;
    }


    public static class Start extends StructureStart<DefaultFeatureConfig> {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }


        @Override
        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            FortressJunglePieces.Start fortresspieces$start = new FortressJunglePieces.Start(this.random, (chunkX << 4) + 2, (chunkZ << 4) + 2);
            this.children.add(fortresspieces$start);

            fortresspieces$start.placeJigsaw(fortresspieces$start, this.children, this.random);
            List<StructurePiece> list = fortresspieces$start.pendingChildren;

            while (!list.isEmpty()) {
                int i = this.random.nextInt(list.size());
                StructurePiece structurepiece = list.remove(i);
                structurepiece.placeJigsaw(fortresspieces$start, this.children, this.random);
            }

            this.setBoundingBoxFromChildren();
            this.method_14976(this.random, 55, 60);
        }
    }
}