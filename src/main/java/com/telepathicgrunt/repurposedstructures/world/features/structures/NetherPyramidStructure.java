package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.network.packet.s2c.play.EntityS2CPacket;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;


public class NetherPyramidStructure extends StructureFeature<DefaultFeatureConfig> {
    // Special thanks to /r/l-ll-ll-l_IsDisLoss for allowing me to mimic his nether pyramid design!

    public NetherPyramidStructure(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return NetherPyramidStructure.Start::new;
    }

    public static class Start extends StructureStart<DefaultFeatureConfig> {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }


        @Override
        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX * 16, 90, chunkZ * 16);
            NetherPyramidPiece.func_207617_a(structureManager, blockpos, BlockRotation.NONE, this.children, this.random, FeatureConfig.DEFAULT);
            this.setBoundingBoxFromChildren();



            BlockView blockView = chunkGenerator.getColumnSample(blockpos.getX()+10, blockpos.getZ()+21);
            BlockPos.Mutable mutable = new BlockPos.Mutable().set(this.boundingBox.getCenter().getX(), 108, this.boundingBox.getCenter().getZ());
            BlockState blockstate;
            while(mutable.getY() > 33){
                blockstate = blockView.getBlockState(mutable);
                if(!blockstate.isSolidBlock(blockView, mutable)){
                    mutable.move(Direction.DOWN);
                    continue;
                }
                else if(blockView.getBlockState(mutable.add(0,3,0)).getMaterial() == Material.AIR &&
                                (BlockTags.INFINIBURN_NETHER.contains(blockstate.getBlock()) ||
                                BlockTags.VALID_SPAWN.contains(blockstate.getBlock()) ||
                                BlockTags.SAND.contains(blockstate.getBlock()) ||
                                BlockTags.NYLIUM.contains(blockstate.getBlock()) ||
                                BlockTags.ICE.contains(blockstate.getBlock()) ||
                                BlockTags.PLANKS.contains(blockstate.getBlock()) ||
                                BlockTags.STONE_BRICKS.contains(blockstate.getBlock()) ||
                                BlockTags.WITHER_IMMUNE.contains(blockstate.getBlock()) ||
                                BlockTags.WOOL.contains(blockstate.getBlock()) ||
                                blockstate.getMaterial() == Material.AGGREGATE ||
                                blockstate.getMaterial() == Material.STONE ||
                                blockstate.getMaterial() == Material.SOIL)){
                    break;
                }
                mutable.move(Direction.DOWN);
            }

            this.method_14976(this.random, mutable.getY(), mutable.getY()+1);
        }
    }
}