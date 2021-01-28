package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.FortressJunglePieces;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;


public class FortressJungleStructure extends AbstractBaseStructure<NoFeatureConfig> {
    public FortressJungleStructure() {
        super(NoFeatureConfig.CODEC);
    }

    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long l, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig NoFeatureConfig) {
        int radius = 4;
        for (int curChunkX = chunkX - radius; curChunkX <= chunkX + radius; curChunkX += radius) {
            for (int curChunkZ = chunkZ - radius; curChunkZ <= chunkZ + radius; curChunkZ += radius) {
                if (!biomeSource.getBiomeForNoiseGen(curChunkX << 2, 60, curChunkZ << 2).getGenerationSettings().hasStructureFeature(RSStructures.JUNGLE_FORTRESS.get())) {
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

    private static final List<MobSpawnInfo.Spawners> MONSTER_SPAWNS =
            Lists.newArrayList(
                    new MobSpawnInfo.Spawners(EntityType.WITHER_SKELETON, 27, 1, 1),
                    new MobSpawnInfo.Spawners(EntityType.DROWNED, 27, 1, 4)
            );

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return MONSTER_SPAWNS;
    }


    public static class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }


        @Override
        public void init(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
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