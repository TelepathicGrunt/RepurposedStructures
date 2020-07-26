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
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;


public class TempleNetherBasaltStructure extends StructureFeature<DefaultFeatureConfig> {
    static {
        ImmutableList<StructureProcessor> randomizationList = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.013F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.MAGMA_BLOCK.getDefaultState()),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.014F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.015F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.OBSIDIAN.getDefaultState()),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.02F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.BASALT.getDefaultState()),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.01F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.GILDED_BLACKSTONE.getDefaultState()))));

        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(new Identifier(RepurposedStructures.MODID,"temples/temple_nether_basalt"), new Identifier("empty"), ImmutableList.of(Pair.of(
                        new SinglePoolElement(RepurposedStructures.MODID+":temples/temple_nether_basalt", randomizationList), 1)),
                        StructurePool.Projection.RIGID));
    }

    public TempleNetherBasaltStructure(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return TempleNetherBasaltStructure.Start::new;
    }

    public static class Start extends AbstractNetherStructure.AbstractStart{
        Identifier NETHER_TEMPLE_POOL = new Identifier(RepurposedStructures.MODID,"temples/temple_nether_basalt");

        public Start(StructureFeature<DefaultFeatureConfig> structureFeature, int x, int z, BlockBox blockBox, int referenceIn, long seed) {
            super(structureFeature, x, z, blockBox, referenceIn, seed);
        }

        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int i, int j, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            BlockPos blockPos = new BlockPos(i * 16, 35, j * 16);
            GeneralJigsawGenerator.addPieces(chunkGenerator, structureManager, blockPos, this.children, this.random, NETHER_TEMPLE_POOL, 1);
            this.setBoundingBoxFromChildren();

            BlockPos lowestLandPos = getLowestLand(chunkGenerator);
            if (lowestLandPos.getY() >= 108 || lowestLandPos.getY() <= 33) {
                this.method_14976(this.random, 16, 17);
            }
            else {
                this.method_14976(this.random, lowestLandPos.getY() - 17, lowestLandPos.getY() - 16);
            }
        }
    }
}