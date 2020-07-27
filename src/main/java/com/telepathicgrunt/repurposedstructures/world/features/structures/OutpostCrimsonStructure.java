package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.List;


public class OutpostCrimsonStructure extends StructureFeature<NoFeatureConfig> {
    // Special thanks to /r/l-ll-ll-l_IsDisLoss for allowing me to mimic his Nether Outpost design!

    static {
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new ResourceLocation(RepurposedStructures.MODID,"outposts/crimson/base_plates"), new ResourceLocation("empty"),
                ImmutableList.of(Pair.of(
                        new SinglePoolElement(RepurposedStructures.MODID+":outposts/crimson/base_plate"), 1)),
                StructurePool.Projection.RIGID));

        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new ResourceLocation(RepurposedStructures.MODID,"outposts/crimson/towers"), new ResourceLocation("empty"),
                ImmutableList.of(
                        Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":outposts/crimson/tower"), 1),
                        Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":outposts/crimson/tower_glowing"), 1)),
                StructurePool.Projection.RIGID));

        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new ResourceLocation(RepurposedStructures.MODID,"outposts/crimson/plates"), new ResourceLocation("empty"),
                ImmutableList.of(Pair.of(
                        new SinglePoolElement(RepurposedStructures.MODID+":outposts/crimson/plate"), 1)),
                StructurePool.Projection.RIGID));

        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new ResourceLocation(RepurposedStructures.MODID,"outposts/crimson/features"), new ResourceLocation("empty"),
                ImmutableList.of(
                        Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":outposts/crimson/cage1"), 1),
                        Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":outposts/crimson/cage2"), 1),
                        Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":outposts/crimson/logs"), 1),
                        Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":outposts/crimson/tent1"), 1),
                        Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":outposts/crimson/tent2"), 1),
                        Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":outposts/crimson/targets"), 1)
                ),
                StructurePool.Projection.RIGID));
    }

    private static final List<Biome.SpawnEntry> MONSTER_SPAWNS = Lists.newArrayList(new Biome.SpawnEntry(EntityType.PIGLIN, 10, 1, 1));

    public OutpostCrimsonStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    @Override
    public StructureStartFactory<NoFeatureConfig> getStructureStartFactory() {
        return OutpostCrimsonStructure.Start::new;
    }

    @Override
    public List<Biome.SpawnEntry> getMonsterSpawns() {
        return MONSTER_SPAWNS;
    }

    public static class Start extends AbstractNetherStructure.AbstractStart{
        ResourceLocation NETHER_OUTPOST_POOL = new ResourceLocation(RepurposedStructures.MODID,"outposts/crimson/base_plates");

        public Start(StructureFeature<NoFeatureConfig> structureFeature, int x, int z, BlockBox blockBox, int referenceIn, long seed) {
            super(structureFeature, x, z, blockBox, referenceIn, seed);
        }

        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int x, int z, Biome biome, NoFeatureConfig NoFeatureConfig) {
            BlockPos blockPos = new BlockPos(x * 16, 0, z * 16);
            GeneralJigsawGenerator.addPieces(chunkGenerator, structureManager, blockPos, this.children, this.random, NETHER_OUTPOST_POOL, 11);
            this.setBoundingBoxFromChildren();

            BlockPos lowestLandPos = getHighestLand(chunkGenerator);
            if (lowestLandPos.getY() >= 108 || lowestLandPos.getY() <= 37) {
                this.method_14976(this.random, 19, 20);
            }
            else {
                this.method_14976(this.random, lowestLandPos.getY()-15, lowestLandPos.getY()-14);
            }
        }
    }
}