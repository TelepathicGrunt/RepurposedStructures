package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.Blocks;
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
    private static final Identifier NETHER_PYRAMID_ID = new Identifier(RepurposedStructures.MODID + ":temples/nether_pyramid");

    public static void func_207617_a(StructureManager templateManager, BlockPos position, BlockRotation rotationIn, List<StructurePiece> structurePieceList, Random random, DefaultFeatureConfig p_207617_5_) {
        structurePieceList.add(new NetherPyramidPiece.Piece(templateManager, NETHER_PYRAMID_ID, position, rotationIn, 0));
    }

    public static class Piece extends SimpleStructurePiece {
        private final Identifier pieceRL;
        private final BlockRotation rotation;

        public Piece(StructureManager templateManager, Identifier pieceRL, BlockPos position, BlockRotation rotationIn, int heightOffset) {
            super(StructurePieces.NETHER_PYRAMID_PIECE, 0);
            this.pieceRL = pieceRL;
            this.pos = position;
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
            StructurePlacementData placementsettings = (new StructurePlacementData()).setRotation(this.rotation).setPosition(new BlockPos(21,16,21)).setMirror(BlockMirror.NONE).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
            this.setStructureData(template, this.pos, placementsettings);
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag tagCompound) {
            super.toNbt(tagCompound);
            tagCompound.putString("Template", this.pieceRL.toString());
            tagCompound.putString("Rot", this.rotation.name());
        }


        @Override
        public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            boundingBox = new BlockBox(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX+21, boundingBox.maxY+16, boundingBox.maxZ+21);
            BlockPos.Mutable mutable = new BlockPos.Mutable().set(this.boundingBox.getCenter().getX(), 32, this.boundingBox.getCenter().getZ());
            while(mutable.getY() > 33){
                if(!world.isAir(mutable)){
                    mutable.move(Direction.DOWN);
                    continue;
                }
                else if(world.getBlockState(mutable.add(5,2,5)).getMaterial() == Material.AIR &&
                        world.getBlockState(mutable.add(-5,2,5)).getMaterial() == Material.AIR &&
                        world.getBlockState(mutable.add(5,2,-5)).getMaterial() == Material.AIR &&
                        world.getBlockState(mutable.add(-5,2,-5)).getMaterial() == Material.AIR){
                    break;
                }
                mutable.move(Direction.DOWN);
            }

            this.pos = this.pos.add(0, mutable.getY() - 93, 0);
            super.generate(world, structureAccessor, generator, random, boundingBox, chunkPos, blockPos);


            mutable.set(boundingBox.minX, this.pos.getY()-2, boundingBox.minZ).move(Direction.DOWN);
            for(; mutable.getX() < boundingBox.maxX && mutable.getY() > 1; mutable.move(1, 0,0)){
                for(; mutable.getZ() < boundingBox.maxZ && mutable.getY() > 1; mutable.move(0, 0,1)){
                    while(!world.getBlockState(mutable).isOpaque() && mutable.getY() > 1){
                        world.setBlockState(mutable, Blocks.BLACKSTONE.getDefaultState(), 2);
                        mutable.move(Direction.DOWN);
                    }
                    mutable.set(mutable.getX(), this.pos.getY(), mutable.getZ());
                }
                mutable.set(mutable.getX(), this.pos.getY(), this.pos.getZ());
            }

            return true;
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, WorldAccess world, Random random, BlockBox boundingBox) {

        }
    }
}
