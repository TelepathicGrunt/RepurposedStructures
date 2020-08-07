package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
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
import net.minecraft.util.math.Direction;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;


public class TempleNetherCrimsonStructure extends StructureFeature<DefaultFeatureConfig> {
    static {
        ImmutableList<StructureProcessor> randomizationList = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_HYPHAE, 0.02F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.NETHER_WART_BLOCK.getDefaultState()),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_HYPHAE, 0.3F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_HYPHAE.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.X)),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_HYPHAE, 0.4F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_HYPHAE.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.Y)),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_HYPHAE, 0.5F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_HYPHAE.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.Z)),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_NYLIUM, 0.15F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.NETHER_WART_BLOCK.getDefaultState()),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_STEM, 0.22F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_HYPHAE.getDefaultState()))));

        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(new Identifier(RepurposedStructures.MODID,"temples/temple_nether_crimson"), new Identifier("empty"), ImmutableList.of(Pair.of(
                        new SinglePoolElement(RepurposedStructures.MODID+":temples/temple_nether_crimson", randomizationList), 1)),
                        StructurePool.Projection.RIGID));
    }

    public TempleNetherCrimsonStructure(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return TempleNetherCrimsonStructure.Start::new;
    }

    public static class Start extends AbstractNetherStructure.AbstractStart{
        Identifier NETHER_TEMPLE_POOL = new Identifier(RepurposedStructures.MODID,"temples/temple_nether_crimson");

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
                this.method_14976(this.random, lowestLandPos.getY() - 16, lowestLandPos.getY() - 15);
            }
        }
    }
}