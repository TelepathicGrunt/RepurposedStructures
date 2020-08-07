package com.telepathicgrunt.repurposedstructures.world.structures;

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
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;


public class TempleNetherWastelandStructure extends StructureFeature<DefaultFeatureConfig> {
    static {
        ImmutableList<StructureProcessor> randomizationList = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.015F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.MAGMA_BLOCK.getDefaultState()),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.12F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.BLACK_TERRACOTTA.getDefaultState()),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.22F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.RED_NETHER_BRICKS.getDefaultState()),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.2F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_NETHER_BRICKS.getDefaultState()))));

        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(new Identifier(RepurposedStructures.MODID,"temples/temple_nether_wasteland"), new Identifier("empty"), ImmutableList.of(Pair.of(
                        new SinglePoolElement(RepurposedStructures.MODID+":temples/temple_nether_wasteland", randomizationList), 1)),
                        StructurePool.Projection.RIGID));
    }

    public TempleNetherWastelandStructure(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    @Override
    public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return TempleNetherWastelandStructure.Start::new;
    }

    public static class Start extends AbstractNetherStructure.AbstractStart{
        Identifier NETHER_TEMPLE_POOL = new Identifier(RepurposedStructures.MODID,"temples/temple_nether_wasteland");

        public Start(StructureFeature<DefaultFeatureConfig> structureFeature, int x, int z, BlockBox blockBox, int referenceIn, long seed) {
            super(structureFeature, x, z, blockBox, referenceIn, seed);
        }

        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int x, int z, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            BlockPos blockPos = new BlockPos(x * 16, 35, z * 16);
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