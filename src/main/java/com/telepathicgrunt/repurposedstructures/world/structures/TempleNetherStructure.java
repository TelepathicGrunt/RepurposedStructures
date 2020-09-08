package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.GeneralJigsawGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class TempleNetherStructure extends Structure<NoFeatureConfig> {

    private final ResourceLocation START_POOL;
    private static boolean INITIALIZED_POOLS = false;

    public TempleNetherStructure(Codec<NoFeatureConfig> config, ResourceLocation pieceRL) {
        super(config);
        START_POOL = pieceRL;
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return TempleNetherStructure.Start::new;
    }

    public class Start extends AbstractNetherStructure.AbstractStart{
        public Start(Structure<NoFeatureConfig> structureFeature, int x, int z, MutableBoundingBox blockBox, int referenceIn, long seed) {
            super(structureFeature, x, z, blockBox, referenceIn, seed);
        }

        public void init(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int x, int z, Biome biome, NoFeatureConfig NoFeatureConfig) {

            BlockPos blockPos = new BlockPos(x * 16, 35, z * 16);
            GeneralJigsawGenerator.addPieces(dynamicRegistryManager, chunkGenerator, structureManager, blockPos, this.components, this.rand, dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN).getOrDefault(START_POOL), 1);
            this.recalculateStructureSize();

            BlockPos lowestLandPos = getLowestLand(chunkGenerator);
            if (lowestLandPos.getY() >= 108 || lowestLandPos.getY() <= 33) {
                this.func_214626_a(this.rand, 16, 17);
            }
            else {
                this.func_214626_a(this.rand, lowestLandPos.getY() - 16, lowestLandPos.getY() - 15);
            }
        }
    }
}