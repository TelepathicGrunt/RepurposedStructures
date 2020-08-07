package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.*;


public class PyramidNetherStructure extends Structure<NoFeatureConfig> {
    // Special thanks to /r/l-ll-ll-l_IsDisLoss for allowing me to mimic his nether pyramid design!
    static {
        ImmutableList<StructureProcessor> randomizationList = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.04F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.field_235412_nv_.getDefaultState()),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235384_mx_, 0.3F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()))));

        JigsawManager.REGISTRY.register(
                new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"temples/pyramid_nether"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                        new SingleJigsawPiece(RepurposedStructures.MODID+":temples/pyramid_nether", randomizationList), 1)),
                        JigsawPattern.PlacementBehaviour.RIGID));
    }

    public PyramidNetherStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return PyramidNetherStructure.Start::new;
    }

    public static class Start extends AbstractNetherStructure.AbstractStart {
        ResourceLocation NETHER_PYRAMID_POOL = new ResourceLocation(RepurposedStructures.MODID,"temples/pyramid_nether");

        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void init(ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX * 16, 35, chunkZ * 16);
            GeneralJigsawGenerator.addPieces(chunkGenerator, structureManager, blockpos, this.components, this.rand, NETHER_PYRAMID_POOL, 1);
            //PyramidFloorPiece.func_207617_a(structureManager, blockpos, this.components.get(0).getRotation(), this.components, random, Blocks.field_235406_np_, NoFeatureConfig);
            //this.components.get(1).getBoundingBox().encompass(this.components.get(0).getBoundingBox());
            this.recalculateStructureSize();

            BlockPos highestLandPos = getHighestLand(chunkGenerator);
            this.func_214626_a(this.rand, highestLandPos.getY()-16, highestLandPos.getY()-15);
        }
    }
}