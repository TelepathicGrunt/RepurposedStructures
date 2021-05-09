package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class TempleNetherStructure extends Structure<NoFeatureConfig> {

    private final ResourceLocation START_POOL;

    public TempleNetherStructure(ResourceLocation pieceRL) {
        super(NoFeatureConfig.CODEC);
        START_POOL = pieceRL;
        RSStructures.RS_STRUCTURE_START_PIECES.add(START_POOL);
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return TempleNetherStructure.Start::new;
    }

    public class Start extends AbstractNetherStructure.AbstractStart{
        public Start(Structure<NoFeatureConfig> structureFeature, int x, int z, MutableBoundingBox blockBox, int referenceIn, long seed) {
            super(structureFeature, x, z, blockBox, referenceIn, seed);
        }

        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int x, int z, Biome biome, NoFeatureConfig NoFeatureConfig) {

            BlockPos blockPos = new BlockPos(x * 16, chunkGenerator.getSeaLevel() + 3, z * 16);
            JigsawManager.addPieces(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(
                            Registry.TEMPLATE_POOL_REGISTRY).get(START_POOL),
                            1),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockPos,
                    this.pieces,
                    this.random,
                    true,
                    false);
            this.calculateBoundingBox();

            BlockPos lowestLandPos = getLowestLand(chunkGenerator);
            if (lowestLandPos.getY() >= chunkGenerator.getGenDepth() - 20 || lowestLandPos.getY() <= chunkGenerator.getSeaLevel() + 1) {
                this.moveInsideHeights(this.random, chunkGenerator.getSeaLevel() - 16, chunkGenerator.getSeaLevel() - 15);
            }
            else {
                this.moveInsideHeights(this.random, lowestLandPos.getY() - 16, lowestLandPos.getY() - 15);
            }
        }
    }
}