package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.GeneralJigsawGenerator;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.OutpostNetherPools;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.List;


public class OutpostNetherStructure extends StructureFeature<DefaultFeatureConfig> {
    private final Identifier PIECE_RL;
    private static boolean INITIALIZED_POOLS = false;
    private static final List<Biome.SpawnEntry> MONSTER_SPAWNS = Lists.newArrayList(new Biome.SpawnEntry(EntityType.PIGLIN, 10, 1, 1));

    public OutpostNetherStructure(Codec<DefaultFeatureConfig> config, Identifier pieceRL) {
        super(config);
        PIECE_RL = pieceRL;
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return OutpostNetherStructure.Start::new;
    }

    @Override
    public List<Biome.SpawnEntry> getMonsterSpawns() {
        return MONSTER_SPAWNS;
    }

    public class Start extends AbstractNetherStructure.AbstractStart{
        public Start(StructureFeature<DefaultFeatureConfig> structureFeature, int x, int z, BlockBox blockBox, int referenceIn, long seed) {
            super(structureFeature, x, z, blockBox, referenceIn, seed);
        }

        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int x, int z, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            if(!INITIALIZED_POOLS){
                OutpostNetherPools.initPools();
                INITIALIZED_POOLS = true;
            }
            BlockPos blockPos = new BlockPos(x * 16, 0, z * 16);
            GeneralJigsawGenerator.addPieces(chunkGenerator, structureManager, blockPos, this.children, this.random, PIECE_RL, 11);
            this.setBoundingBoxFromChildren();

            BlockPos lowestLandPos = getHighestLand(chunkGenerator);
            if (lowestLandPos.getY() >= 108 || lowestLandPos.getY() <= 37) {
                this.method_14976(this.random, 19, 20);
            }
            else {
                this.method_14976(this.random, lowestLandPos.getY()-15, lowestLandPos.getY()-14);
            }
        }
    }
}