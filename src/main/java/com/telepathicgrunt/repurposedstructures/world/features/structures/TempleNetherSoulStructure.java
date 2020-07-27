package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;


public class TempleNetherSoulStructure extends StructureFeature<NoFeatureConfig> {
    static {
        ImmutableList<StructureProcessor> randomizationList = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SOUL_SOIL, 0.1F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.SOUL_SAND.getDefaultState()),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SOUL_SAND, 0.05F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.SOUL_SOIL.getDefaultState()))));

        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_soul"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                        new SinglePoolElement(RepurposedStructures.MODID+":temples/temple_nether_soul", randomizationList), 1)),
                        StructurePool.Projection.RIGID));
    }

    public TempleNetherSoulStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    @Override
    public StructureStartFactory<NoFeatureConfig> getStructureStartFactory() {
        return TempleNetherSoulStructure.Start::new;
    }

    public static class Start extends AbstractNetherStructure.AbstractStart{
        ResourceLocation NETHER_TEMPLE_POOL = new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_soul");

        public Start(StructureFeature<NoFeatureConfig> structureFeature, int x, int z, BlockBox blockBox, int referenceIn, long seed) {
            super(structureFeature, x, z, blockBox, referenceIn, seed);
        }

        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int i, int j, Biome biome, NoFeatureConfig NoFeatureConfig) {
            BlockPos blockPos = new BlockPos(i * 16, 35, j * 16);
            GeneralJigsawGenerator.addPieces(chunkGenerator, structureManager, blockPos, this.children, this.random, NETHER_TEMPLE_POOL, 1);
            this.setBoundingBoxFromChildren();

            BlockPos lowestLandPos = getLowestLand(chunkGenerator);
            if (lowestLandPos.getY() >= 108 || lowestLandPos.getY() <= 33) {
                this.method_14976(this.random, 16, 17);
            }
            else {
                this.method_14976(this.random, lowestLandPos.getY() - 16, lowestLandPos.getY() - 15);
            }
        }
    }
}