package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class BuriableStructure extends AbstractBaseStructure<NoFeatureConfig> {

    private final ResourceLocation startPool;
    public BuriableStructure(ResourceLocation startPool) {
        super(NoFeatureConfig.CODEC);
        this.startPool = startPool;
        RSStructures.RS_STRUCTURE_START_PIECES.add(startPool);
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return BuriableStructure.Start::new;
    }

    public class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            BlockPos blockPos = new BlockPos(chunkX * 16, chunkGenerator.getSeaLevel(), chunkZ * 16);
            JigsawManager.addPieces(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(
                            Registry.TEMPLATE_POOL_REGISTRY).get(startPool),
                            1),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockPos,
                    this.pieces,
                    this.random,
                    true,
                    false);

            this.pieces.get(1).getBoundingBox().expand(this.pieces.get(0).getBoundingBox());
            this.calculateBoundingBox();

            Rotation rotation = this.pieces.get(0).getRotation();
            BlockPos maxCorner = new BlockPos(this.pieces.get(0).getBoundingBox().getXSpan(), 0, this.pieces.get(0).getBoundingBox().getZSpan()).rotate(rotation);

            int highestLandPos = chunkGenerator.getBaseHeight(blockPos.getX() + maxCorner.getX(), blockPos.getZ() + maxCorner.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
            highestLandPos = Math.min(highestLandPos, chunkGenerator.getBaseHeight(blockPos.getX(), blockPos.getZ() + maxCorner.getZ(), Heightmap.Type.WORLD_SURFACE_WG));
            highestLandPos = Math.min(highestLandPos, chunkGenerator.getBaseHeight(blockPos.getX() + maxCorner.getX(), blockPos.getZ(), Heightmap.Type.WORLD_SURFACE_WG));
            highestLandPos = Math.min(highestLandPos, chunkGenerator.getBaseHeight(blockPos.getX(), blockPos.getZ(), Heightmap.Type.WORLD_SURFACE_WG));

            this.moveInsideHeights(this.random, highestLandPos-15, highestLandPos-14);
        }
    }
}