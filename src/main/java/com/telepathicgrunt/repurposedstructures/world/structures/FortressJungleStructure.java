package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RSFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;


public class FortressJungleStructure extends AbstractBaseStructure {
    public FortressJungleStructure(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long l, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig NoFeatureConfig) {
        for (int curChunkX = chunkX - 2; curChunkX <= chunkX + 2; curChunkX += 2) {
            for (int curChunkZ = chunkZ - 2; curChunkZ <= chunkZ + 2; curChunkZ += 2) {
                if (!biomeSource.getBiomeForNoiseGen(curChunkX << 2, 60, curChunkZ << 2).hasStructure(RSFeatures.JUNGLE_FORTRESS)) {
                    return false;
                }
            }
        }

        return true;
    }


    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return FortressJungleStructure.Start::new;
    }

    private static final List<Biome.SpawnListEntry> MONSTER_SPAWNS =
            Lists.newArrayList(new Biome.SpawnListEntry(EntityType.WITHER_SKELETON, 27, 1, 1));

    @Override
    public List<Biome.SpawnListEntry> getSpawnList() {
        return MONSTER_SPAWNS;
    }


    public static class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }


        @Override
        public void init(ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            FortressJunglePieces.Start fortresspieces$start = new FortressJunglePieces.Start(this.rand, (chunkX << 4) + 2, (chunkZ << 4) + 2);
            this.components.add(fortresspieces$start);

            fortresspieces$start.buildComponent(fortresspieces$start, this.components, this.rand);
            List<StructurePiece> list = fortresspieces$start.pendingChildren;

            while (!list.isEmpty()) {
                int i = this.rand.nextInt(list.size());
                StructurePiece structurepiece = list.remove(i);
                structurepiece.buildComponent(fortresspieces$start, this.components, this.rand);
            }

            this.recalculateStructureSize();
            this.func_214626_a(this.rand, 55, 60);
        }
    }
}