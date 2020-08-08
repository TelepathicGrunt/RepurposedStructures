package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.GeneralJigsawGenerator;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.TempleNetherPools;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class TempleNetherStructure extends Structure<NoFeatureConfig> {

    private final ResourceLocation PIECE_RL;
    private static boolean INITIALIZED_POOLS = false;

    public TempleNetherStructure(Codec<NoFeatureConfig> config, ResourceLocation pieceRL) {
        super(config);
        PIECE_RL = pieceRL;
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return TempleNetherStructure.Start::new;
    }

    public class Start extends AbstractNetherStructure.AbstractStart{
        public Start(Structure<NoFeatureConfig> structureFeature, int x, int z, MutableBoundingBox blockBox, int referenceIn, long seed) {
            super(structureFeature, x, z, blockBox, referenceIn, seed);
        }

        public void init(ChunkGenerator chunkGenerator, TemplateManager structureManager, int x, int z, Biome biome, NoFeatureConfig NoFeatureConfig) {
            if(!INITIALIZED_POOLS){
                TempleNetherPools.initPools();
                INITIALIZED_POOLS = true;
            }

            BlockPos blockPos = new BlockPos(x * 16, 35, z * 16);
            GeneralJigsawGenerator.addPieces(chunkGenerator, structureManager, blockPos, this.components, this.rand, PIECE_RL, 1);
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