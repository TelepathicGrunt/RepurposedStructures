package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PyramidFloorPiece;
import net.minecraft.block.Blocks;
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
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class PyramidBadlandsStructure extends AbstractBaseStructure {

    private final ResourceLocation START_POOL;
    public PyramidBadlandsStructure(Codec<NoFeatureConfig> config) {
        super(config);
        START_POOL = new ResourceLocation(RepurposedStructures.MODID + ":temples/pyramid_badlands");
        RSStructures.RS_STRUCTURE_START_PIECES.add(START_POOL);
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return PyramidBadlandsStructure.Start::new;
    }

    public class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            BlockPos blockPos = new BlockPos(chunkX * 16, 62, chunkZ * 16);
            JigsawManager.method_30419(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.get(
                            Registry.TEMPLATE_POOL_WORLDGEN).getOrDefault(START_POOL),
                            1),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockPos,
                    this.components,
                    this.rand,
                    true,
                    false);
            PyramidFloorPiece.func_207617_a(structureManager, blockPos, this.components.get(0).getRotation(), this.components, rand, Blocks.RED_SANDSTONE, NoFeatureConfig);

            //put the floor placing before the pit.
            StructurePiece temp = this.components.get(1);
            this.components.remove(1);
            this.components.add(temp);

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