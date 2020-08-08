package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;


public class PyramidFloorPiece {

    public static void func_207617_a(TemplateManager templateManager, BlockPos position, Rotation rotationIn, List<StructurePiece> structurePieceList, Random random, Block block, NoFeatureConfig p_207617_5_) {
        structurePieceList.add(new PyramidFloorPiece.Piece(templateManager, position, block, rotationIn));
    }

    public static class Piece extends TemplateStructurePiece {
        private final Rotation rotation;
        private final Block block;

        public Piece(TemplateManager templateManager, BlockPos position, Block blockIn, Rotation rotationIn) {
            super(StructurePieces.PYRAMID_FLOOR_PIECE, 0);
            this.templatePosition = position;
            this.rotation = rotationIn;
            this.block = blockIn;
            this.func_207614_a(templateManager);
        }


        public Piece(TemplateManager templateManager, CompoundNBT data) {
            super(StructurePieces.PYRAMID_FLOOR_PIECE, data);
            this.rotation = Rotation.valueOf(data.getString("Rot"));
            this.block = Registry.BLOCK.getOrDefault(new ResourceLocation(data.getString("Block")));
            this.func_207614_a(templateManager);
        }


        private void func_207614_a(TemplateManager templateManager) {
            Template template = templateManager.getTemplateDefaulted(new ResourceLocation("blank"));
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setCenterOffset(new BlockPos(21,16,21)).setMirror(Mirror.NONE).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            this.setup(template, this.templatePosition, placementsettings);
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void readAdditional(CompoundNBT tagCompound) {
            super.readAdditional(tagCompound);
            tagCompound.putString("Rot", this.rotation.name());
            tagCompound.putString("Block", Registry.BLOCK.getKey(this.block).toString());
        }

        @Override
        public boolean generate(ISeedReader world, StructureManager structureAccessor, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            // Get size of pyramid and lowers to below the floor.
            this.templatePosition = this.templatePosition.down(2);
            BlockPos rotatedPos = this.placeSettings.getCenterOffset().rotate(this.rotation);
            BlockPos minPos;
            BlockPos maxPos;

            // Rotate the bounds of the base to match pyramid
            if (this.rotation == Rotation.CLOCKWISE_90) {
                minPos = new BlockPos(rotatedPos.getX()+1, 0,0);
                maxPos = new BlockPos(1, 0, rotatedPos.getZ());
            } else if (this.rotation == Rotation.CLOCKWISE_180) {
                minPos = new BlockPos(rotatedPos.getX()+1, 0,rotatedPos.getZ()+1);
                maxPos = new BlockPos(1, 0, 1);
            } else if (this.rotation == Rotation.COUNTERCLOCKWISE_90) {
                minPos = new BlockPos(0, 0,rotatedPos.getZ()+1);
                maxPos = new BlockPos(rotatedPos.getX(), 0, 1);
            } else{
                minPos = new BlockPos(0, 0,0);
                maxPos = new BlockPos(rotatedPos.getX(), 0, rotatedPos.getZ());
            }

            //Start at most negative corner
            BlockPos.Mutable mutable = new BlockPos.Mutable().setPos(this.templatePosition.getX() + minPos.getX(), this.templatePosition.getY(), this.templatePosition.getZ() + minPos.getZ());
            BlockState blockState;

            IBlockReader blockview;
            for(; mutable.getX() < this.templatePosition.getX() + maxPos.getX() && mutable.getY() > 1; mutable.move(1, 0,0)){
                for(; mutable.getZ() < this.templatePosition.getZ() + maxPos.getZ() && mutable.getY() > 1; mutable.move(0, 0,1)){
                    blockview = generator.getColumnSample(mutable.getX(), mutable.getZ());

                    //Fills column (works even in Nether)
                    blockState = blockview.getBlockState(mutable);
                    while((blockState.getMaterial() == Material.AIR ||
                            blockState.getBlock() instanceof FlowingFluidBlock) &&
                            mutable.getY() > 1)
                    {
                        world.setBlockState(mutable, this.block.getDefaultState(), 3);
                        mutable.move(Direction.DOWN);
                        blockState = blockview.getBlockState(mutable);
                    }

                    //Reset on Y
                    mutable.setPos(mutable.getX(), this.templatePosition.getY(), mutable.getZ());
                }
                //Reset on Y and Z
                mutable.setPos(mutable.getX(), this.templatePosition.getY(), this.templatePosition.getZ() + minPos.getZ());
            }

            return true;
        }

        @Override
        protected void handleDataMarker(String metadata, BlockPos pos, IWorld world, Random random, MutableBoundingBox boundingBox) {
        }
    }
}
