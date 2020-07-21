package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RSFeatures;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.VillageGenerator;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class VillageNetherStructure extends AbstractNetherVillageStructure {
    public VillageNetherStructure(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long l, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {
        return true;
    }

    @Override
    public StructureFeature<DefaultFeatureConfig> getVillageInstance() {
        return RSFeatures.NETHER_BRICK_OUTPOST;
    }

    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return VillageNetherStructure.Start::new;
    }

    public static class Start extends NetherAbstractStart {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public static Identifier VILLAGE_IDENTIFIER = new Identifier(RepurposedStructures.MODID + ":village/crimson/town_centers");

        @Override
        public Identifier getIdentifier() {
            return VILLAGE_IDENTIFIER;
        }

        @Override
        public int getSize() {
            return 6;
        }

        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            super.init(chunkGenerator, structureManager, chunkX, chunkZ, biome, defaultFeatureConfig);

            BlockPos lowestLandPos = getHighestLand(chunkGenerator);
            if (lowestLandPos.getY() >= 108 || lowestLandPos.getY() <= 33) {
                this.method_14976(this.random, 20, 21);
            }
            else {
                this.method_14976(this.random, lowestLandPos.getY() - 13, lowestLandPos.getY() - 12);
            }
        }

    }
}