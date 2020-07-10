package com.telepathicgrunt.repurposedstructures.world.features.structures;

import net.minecraft.block.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.List;
import java.util.Random;


public class PyramidFloorPiece {

    public static void func_207617_a(StructureManager templateManager, BlockPos position, BlockRotation rotationIn, List<StructurePiece> structurePieceList, Random random, Block block, DefaultFeatureConfig p_207617_5_) {
        structurePieceList.add(new PyramidFloorPiece.Piece(templateManager, position, block, rotationIn));
    }

    public static class Piece extends SimpleStructurePiece {
        private final BlockRotation rotation;
        private final Block block;

        public Piece(StructureManager templateManager, BlockPos position, Block blockIn, BlockRotation rotationIn) {
            super(StructurePieces.PYRAMID_FLOOR_PIECE, 0);
            this.pos = position;
            this.rotation = rotationIn;
            this.block = blockIn;
            this.func_207614_a(templateManager);
        }


        public Piece(StructureManager templateManager, CompoundTag data) {
            super(StructurePieces.PYRAMID_FLOOR_PIECE, data);
            this.rotation = BlockRotation.valueOf(data.getString("Rot"));
            this.block = Registry.BLOCK.get(new Identifier(data.getString("block")));
            this.func_207614_a(templateManager);
        }


        private void func_207614_a(StructureManager templateManager) {
            Structure template = templateManager.getStructureOrBlank(new Identifier("blank"));
            StructurePlacementData placementsettings = (new StructurePlacementData()).setRotation(this.rotation).setPosition(new BlockPos(21,16,21)).setMirror(BlockMirror.NONE).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
            this.setStructureData(template, this.pos, placementsettings);
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag tagCompound) {
            super.toNbt(tagCompound);
        }


        @Override
        public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            this.pos = this.pos.down(2);
            BlockPos rotatedPos = this.placementData.getPosition().rotate(this.rotation);
            BlockPos minPos;
            BlockPos maxPos;

            if (this.rotation == BlockRotation.CLOCKWISE_90) {
                minPos = new BlockPos(rotatedPos.getX()+1, rotatedPos.getY(),0);
                maxPos = new BlockPos(1, rotatedPos.getY(), rotatedPos.getZ());
            } else if (this.rotation == BlockRotation.CLOCKWISE_180) {
                minPos = new BlockPos(rotatedPos.getX()+1, rotatedPos.getY(),rotatedPos.getZ()+1);
                maxPos = new BlockPos(1, rotatedPos.getY(), 1);
            } else if (this.rotation == BlockRotation.COUNTERCLOCKWISE_90) {
                minPos = new BlockPos(0, rotatedPos.getY(),rotatedPos.getZ()+1);
                maxPos = new BlockPos(rotatedPos.getX(), rotatedPos.getY(), 1);
            } else{
                minPos = new BlockPos(0, rotatedPos.getY(),0);
                maxPos = new BlockPos(rotatedPos.getX(), rotatedPos.getY(), rotatedPos.getZ());
            }

           BlockPos.Mutable mutable = new BlockPos.Mutable().set(this.pos.getX()+minPos.getX(), this.pos.getY(), this.pos.getZ()+minPos.getZ());

            for(; mutable.getX() < this.pos.getX() + maxPos.getX() && mutable.getY() > 1; mutable.move(1, 0,0)){
                for(; mutable.getZ() < this.pos.getZ() + maxPos.getZ() && mutable.getY() > 1; mutable.move(0, 0,1)){
                    while((world.getBlockState(mutable).getMaterial() == Material.AIR ||
                            world.getBlockState(mutable).getBlock() instanceof FluidBlock) &&
                            mutable.getY() > 1)
                    {
                        world.setBlockState(mutable, this.block.getDefaultState(), 2);
                        mutable.move(Direction.DOWN);
                    }
                    mutable.set(mutable.getX(), this.pos.getY(), mutable.getZ());
                }
                mutable.set(mutable.getX(), this.pos.getY(), this.pos.getZ() + minPos.getZ());
            }

            return true;
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, WorldAccess world, Random random, BlockBox boundingBox) {
        }
    }
}
