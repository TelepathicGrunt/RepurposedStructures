package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;


public class OutpostNetherStructure extends AbstractBaseStructure<NoFeatureConfig> {
    private final ResourceLocation START_POOL;
    private static final List<MobSpawnInfo.Spawners> MONSTER_SPAWNS = Lists.newArrayList(new MobSpawnInfo.Spawners(EntityType.PIGLIN, 10, 1, 1));

    public OutpostNetherStructure(ResourceLocation pieceRL) {
        super(NoFeatureConfig.CODEC);
        START_POOL = pieceRL;
        RSStructures.RS_STRUCTURE_START_PIECES.add(START_POOL);
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return OutpostNetherStructure.Start::new;
    }

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return MONSTER_SPAWNS;
    }

    public class Start extends AbstractNetherStructure.AbstractStart{

        public Start(Structure<NoFeatureConfig> structureFeature, int x, int z, MutableBoundingBox blockBox, int referenceIn, long seed) {
            super(structureFeature, x, z, blockBox, referenceIn, seed);
        }

        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int x, int z, Biome biome, NoFeatureConfig NoFeatureConfig) {
            BlockPos blockPos = new BlockPos(x * 16, 0, z * 16);
            JigsawManager.addPieces(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(
                            Registry.TEMPLATE_POOL_REGISTRY).get(START_POOL),
                            11),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockPos,
                    this.pieces,
                    this.random,
                    true,
                    false);
            this.calculateBoundingBox();

            BlockPos lowestLandPos = getHighestLand(chunkGenerator);
            if (lowestLandPos.getY() >= chunkGenerator.getGenDepth() - 20 || lowestLandPos.getY() <= chunkGenerator.getSeaLevel() + 5) {
                this.moveInsideHeights(this.random, chunkGenerator.getSeaLevel() - 13, chunkGenerator.getSeaLevel() - 12);
            }
            else {
                this.moveInsideHeights(this.random, lowestLandPos.getY() - 15, lowestLandPos.getY() - 14);
            }
        }
    }
}