package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.StructureProcessorListAccessor;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.GeneralJigsawGenerator;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.*;
import net.minecraft.structure.processor.*;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;


public class PyramidNetherStructure extends StructureFeature<DefaultFeatureConfig> {
    // Special thanks to /r/l-ll-ll-l_IsDisLoss for allowing me to mimic his nether pyramid design!

    private static boolean INITIALIZED_POOLS = false;
    private static void initPools() {
        StructureProcessorList randomizationList = StructureProcessorListAccessor.getRegister(RepurposedStructures.MODID+":temples/pyramid_nether_randomizer",
                ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.04F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WEEPING_VINES, 0.3F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState())))));

        StructurePools.register(
                new StructurePool(new Identifier(RepurposedStructures.MODID,"temples/pyramid_nether"), new Identifier("empty"), ImmutableList.of(Pair.of(
                        StructurePoolElement.method_30426(RepurposedStructures.MODID+":temples/pyramid_nether", randomizationList), 1)),
                        StructurePool.Projection.RIGID));
    }

    private final StructurePool START_POOL;
    public PyramidNetherStructure(Codec<DefaultFeatureConfig> config) {
        super(config);
        START_POOL = BuiltinRegistries.STRUCTURE_POOL.get(new Identifier(RepurposedStructures.MODID + ":temples/pyramid_nether"));
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return PyramidNetherStructure.Start::new;
    }

    public class Start extends AbstractNetherStructure.AbstractStart {

        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            if(!INITIALIZED_POOLS){
                initPools();
                INITIALIZED_POOLS = true;
            }
            BlockPos blockpos = new BlockPos(chunkX * 16, 35, chunkZ * 16);
            GeneralJigsawGenerator.addPieces(dynamicRegistryManager, chunkGenerator, structureManager, blockpos, this.children, this.random, START_POOL, 1);
            //PyramidFloorPiece.func_207617_a(structureManager, blockpos, this.children.get(0).getRotation(), this.children, random, Blocks.BLACKSTONE, defaultFeatureConfig);
            //this.children.get(1).getBoundingBox().encompass(this.children.get(0).getBoundingBox());
            this.setBoundingBoxFromChildren();

            BlockPos highestLandPos = getHighestLand(chunkGenerator);
            this.method_14976(this.random, highestLandPos.getY()-16, highestLandPos.getY()-15);
        }
    }
}