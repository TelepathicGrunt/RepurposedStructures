package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;


public class BuriableStructure extends AbstractBaseStructure<DefaultFeatureConfig> {

    private final Identifier startPool;
    private final int offsetAmount;
    private final boolean onLand;

    public BuriableStructure(Identifier startPool) {
        this(startPool, 14, true);
    }

    public BuriableStructure(Identifier startPool, boolean onLand) {
        this(startPool, 14, onLand);
    }

    public BuriableStructure(Identifier startPool, int offsetAmount) {
        this(startPool, offsetAmount, true);
    }

    public BuriableStructure(Identifier startPool, int offsetAmount, boolean onLand) {
        super(DefaultFeatureConfig.CODEC);
        this.startPool = startPool;
        RSStructures.RS_STRUCTURE_START_PIECES.add(this.startPool);
        this.offsetAmount = offsetAmount;
        this.onLand = onLand;
    }


    @Override
    public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return BuriableStructure.Start::new;
    }

    public class Start extends StructureStart<DefaultFeatureConfig> {

        public Start(StructureFeature<DefaultFeatureConfig> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
            BlockPos blockpos = new BlockPos(chunkPos1.getStartX(), chunkGenerator.getSeaLevel(), chunkPos1.getStartZ());
            StructurePoolBasedGenerator.method_30419(
                    dynamicRegistryManager,
                    new StructurePoolFeatureConfig(() -> dynamicRegistryManager.get(Registry.STRUCTURE_POOL_KEY).get(startPool), 11),
                    PoolStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this,
                    random,
                    false,
                    false,
                    heightLimitView);
            this.setBoundingBoxFromChildren();

            BlockRotation rotation = this.children.get(0).getRotation();
            BlockPos maxCorner = new BlockPos(this.children.get(0).getBoundingBox().getBlockCountX(), 0, this.children.get(0).getBoundingBox().getBlockCountZ()).rotate(rotation);

            Heightmap.Type heightMapToUse = onLand ? Heightmap.Type.WORLD_SURFACE_WG : Heightmap.Type.OCEAN_FLOOR_WG;

            int highestLandPos = chunkGenerator.getHeight(blockpos.getX() + maxCorner.getX(), blockpos.getZ() + maxCorner.getZ(), heightMapToUse, heightLimitView);
            highestLandPos = Math.min(highestLandPos, chunkGenerator.getHeight(blockpos.getX(), blockpos.getZ() + maxCorner.getZ(), heightMapToUse, heightLimitView));
            highestLandPos = Math.min(highestLandPos, chunkGenerator.getHeight(blockpos.getX() + maxCorner.getX(), blockpos.getZ(), heightMapToUse, heightLimitView));
            highestLandPos = Math.min(highestLandPos, chunkGenerator.getHeight(blockpos.getX(), blockpos.getZ(), heightMapToUse, heightLimitView));

            this.randomUpwardTranslation(this.random, highestLandPos-(offsetAmount+1), highestLandPos-offsetAmount);
        }
    }
}