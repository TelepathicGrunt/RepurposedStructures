package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RSStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSIglooPieces;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class RSIglooStructure extends AbstractBaseStructure {

    private final ResourceLocation START_POOL;
    public RSIglooStructure(Codec<NoFeatureConfig> config, ResourceLocation topPieceRL) {
        super(config);
        START_POOL = topPieceRL;
        RSStructures.RS_STRUCTURE_START_PIECES.add(START_POOL);
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    public class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            int x = chunkX * 16;
            int z = chunkZ * 16;
            BlockPos blockpos = new BlockPos(x, 90, z);
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            RSIglooPieces.func_207617_a(structureManager, START_POOL, Blocks.PODZOL, blockpos, rotation, this.components, this.rand, IFeatureConfig.NO_FEATURE_CONFIG);
            this.recalculateStructureSize();
        }
    }
}