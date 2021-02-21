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
        public void init(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            BlockPos blockPos = new BlockPos(chunkX * 16, chunkGenerator.getSeaLevel(), chunkZ * 16);
            JigsawManager.method_30419(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.get(
                            Registry.TEMPLATE_POOL_WORLDGEN).getOrDefault(startPool),
                            1),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockPos,
                    this.components,
                    this.rand,
                    true,
                    false);

            this.components.get(1).getBoundingBox().expandTo(this.components.get(0).getBoundingBox());
            this.recalculateStructureSize();

            Rotation rotation = this.components.get(0).getRotation();
            BlockPos maxCorner = new BlockPos(this.components.get(0).getBoundingBox().getXSize(), 0, this.components.get(0).getBoundingBox().getZSize()).rotate(rotation);

            int highestLandPos = chunkGenerator.func_222529_a(blockPos.getX() + maxCorner.getX(), blockPos.getZ() + maxCorner.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
            highestLandPos = Math.min(highestLandPos, chunkGenerator.func_222529_a(blockPos.getX(), blockPos.getZ() + maxCorner.getZ(), Heightmap.Type.WORLD_SURFACE_WG));
            highestLandPos = Math.min(highestLandPos, chunkGenerator.func_222529_a(blockPos.getX() + maxCorner.getX(), blockPos.getZ(), Heightmap.Type.WORLD_SURFACE_WG));
            highestLandPos = Math.min(highestLandPos, chunkGenerator.func_222529_a(blockPos.getX(), blockPos.getZ(), Heightmap.Type.WORLD_SURFACE_WG));

            this.func_214626_a(this.rand, highestLandPos-15, highestLandPos-14);
        }
    }
}