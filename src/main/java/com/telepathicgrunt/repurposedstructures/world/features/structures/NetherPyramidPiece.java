package com.telepathicgrunt.repurposedstructures.world.features.structures;

import net.minecraft.block.Material;
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
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.List;
import java.util.Random;


public class NetherPyramidPiece {
    private static final Identifier NETHER_PYRAMID_ID = new Identifier("temples/nether_pyramid");

    public static void func_207617_a(StructureManager templateManager, BlockPos position, BlockRotation rotationIn, List<StructurePiece> structurePieceList, Random random, DefaultFeatureConfig p_207617_5_) {
        structurePieceList.add(new NetherPyramidPiece.Piece(templateManager, NETHER_PYRAMID_ID, position, rotationIn, 0));
    }

    public static class Piece extends SimpleStructurePiece {
        private final Identifier pieceRL;
        private final BlockRotation rotation;

        public Piece(StructureManager templateManager, Identifier pieceRL, BlockPos position, BlockRotation rotationIn, int heightOffset) {
            super(StructurePieces.NETHER_PYRAMID_PIECE, 0);
            this.pieceRL = pieceRL;
            BlockPos blockpos = new BlockPos(0,0,0);
            this.pos = position.add(blockpos.getX(), blockpos.getY() - heightOffset, blockpos.getZ());
            this.rotation = rotationIn;
            this.func_207614_a(templateManager);
        }


        public Piece(StructureManager templateManager, CompoundTag data) {
            super(StructurePieces.NETHER_PYRAMID_PIECE, data);
            this.pieceRL = new Identifier(data.getString("Template"));
            this.rotation = BlockRotation.valueOf(data.getString("Rot"));
            this.func_207614_a(templateManager);
        }


        private void func_207614_a(StructureManager templateManager) {
            Structure template = templateManager.getStructureOrBlank(this.pieceRL);
            StructurePlacementData placementsettings = (new StructurePlacementData()).setRotation(this.rotation).setMirror(BlockMirror.NONE).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
            this.setStructureData(template, this.pos, placementsettings);
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void toNbt(CompoundTag tagCompound) {
            super.toNbt(tagCompound);
            tagCompound.putString("Template", this.pieceRL.toString());
            tagCompound.putString("Rot", this.rotation.name());
        }



        public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {

            BlockPos.Mutable mutable = new BlockPos.Mutable().set(this.boundingBox.getCenter().getX(), 32, this.boundingBox.getCenter().getZ());
            while(mutable.getY() <= 108){
                if(!world.isAir(mutable)){
                    mutable.move(Direction.UP);
                    continue;
                }
                else if(world.getBlockState(mutable.add(6,1,6)).getMaterial() == Material.AIR &&
                        world.getBlockState(mutable.add(-6,1,6)).getMaterial() == Material.AIR &&
                        world.getBlockState(mutable.add(6,1,-6)).getMaterial() == Material.AIR &&
                        world.getBlockState(mutable.add(-6,1,-6)).getMaterial() == Material.AIR){
                    break;
                }
                mutable.move(Direction.UP);
            }

            if(mutable.getY() <= 108){
                mutable.set(mutable.getX(), 32, mutable.getZ());
            }

            this.pos = this.pos.add(0, mutable.getY() - 3, 0);
            super.generate(world, structureAccessor, generator, random, boundingBox, chunkPos, blockPos);
            this.pos = mutable;

            return true;
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, WorldAccess world, Random random, BlockBox boundingBox) {

        }
    }
}
