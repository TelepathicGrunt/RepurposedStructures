package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.GeneralJigsawGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;


public class OutpostNetherStructure extends Structure<NoFeatureConfig> {
    private final ResourceLocation PIECE_RL;
    private static boolean INITIALIZED_POOLS = false;
    private static final List<Biome.SpawnListEntry> MONSTER_SPAWNS = Lists.newArrayList(new Biome.SpawnListEntry(EntityType.field_233591_ai_, 10, 1, 1));

    public OutpostNetherStructure(Codec<NoFeatureConfig> config, ResourceLocation pieceRL) {
        super(config);
        PIECE_RL = pieceRL;
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return OutpostNetherStructure.Start::new;
    }

    @Override
    public List<Biome.SpawnListEntry> getSpawnList() {
        return MONSTER_SPAWNS;
    }

    public class Start extends AbstractNetherStructure.AbstractStart{

        public Start(Structure<NoFeatureConfig> structureFeature, int x, int z, MutableBoundingBox blockBox, int referenceIn, long seed) {
            super(structureFeature, x, z, blockBox, referenceIn, seed);
        }

        public void init(ChunkGenerator chunkGenerator, TemplateManager structureManager, int x, int z, Biome biome, NoFeatureConfig NoFeatureConfig) {
            if(!INITIALIZED_POOLS){
                OutpostNetherPools.initPools();
                INITIALIZED_POOLS = true;
            }
            BlockPos blockPos = new BlockPos(x * 16, 0, z * 16);
            GeneralJigsawGenerator.addPieces(chunkGenerator, structureManager, blockPos, this.components, this.rand, PIECE_RL, 11);
            this.recalculateStructureSize();

            BlockPos lowestLandPos = getHighestLand(chunkGenerator);
            if (lowestLandPos.getY() >= 108 || lowestLandPos.getY() <= 37) {
                this.func_214626_a(this.rand, 19, 20);
            }
            else {
                this.func_214626_a(this.rand, lowestLandPos.getY()-15, lowestLandPos.getY()-14);
            }
        }
    }
}