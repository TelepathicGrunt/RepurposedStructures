package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.List;
import java.util.Map;
import java.util.Random;


public class RSIglooPieces {
    private static final Identifier IGLOO_MIDDLE_RL = new Identifier("igloo/middle");
    private static final Identifier IGLOO_BOTTOM_RL = new Identifier("igloo/bottom");
    private static final Map<Identifier, BlockPos> OFFSET_1 = ImmutableMap.of(IGLOO_MIDDLE_RL, new BlockPos(1, 3, 1), IGLOO_BOTTOM_RL, new BlockPos(3, 6, 7));
    private static final Map<Identifier, BlockPos> OFFSET_2 = ImmutableMap.of(IGLOO_MIDDLE_RL, new BlockPos(2, -3, 4), IGLOO_BOTTOM_RL, new BlockPos(0, -3, -2));

    public static void func_207617_a(StructureManager templateManager, Identifier topPieceRL, Block floorBlock, BlockPos position, BlockRotation rotationIn, List<StructurePiece> pieceList, Random random, DefaultFeatureConfig p_207617_5_) {
        if (random.nextDouble() < 0.5D) {
            int basementY = random.nextInt(8) + 4;
            pieceList.add(new RSIglooPieces.Piece(templateManager, IGLOO_BOTTOM_RL, position, rotationIn, basementY * 3, null));

            for (int middleY = 0; middleY < basementY - 1; ++middleY) {
                pieceList.add(new RSIglooPieces.Piece(templateManager, IGLOO_MIDDLE_RL, position, rotationIn, middleY * 3, null));
            }
        }

        pieceList.add(new RSIglooPieces.Piece(templateManager, topPieceRL, position, rotationIn, 0, floorBlock));
    }

    public static class Piece extends SimpleStructurePiece {
        private final Identifier pieceRL;
        private final BlockRotation rotation;
        private final Block floorBlock;

        public Piece(StructureManager templateManager, Identifier pieceRL, BlockPos position, BlockRotation rotationIn, int heightOffset, Block floorBlockIn) {
            super(StructurePieces.RS_IGLOO_PIECE, 0);
            this.pieceRL = pieceRL;
            BlockPos blockpos = OFFSET_2.containsKey(this.pieceRL) ? OFFSET_2.get(this.pieceRL) : new BlockPos(0, -1, 0);
            this.pos = position.add(blockpos.getX(), blockpos.getY() - heightOffset, blockpos.getZ());
            this.rotation = rotationIn;
            this.initializeStructureData(templateManager);
            this.floorBlock = floorBlockIn;
        }


        public Piece(StructureManager templateManager, CompoundTag data) {
            super(StructurePieces.RS_IGLOO_PIECE, data);
            this.pieceRL = new Identifier(data.getString("Template"));
            this.rotation = BlockRotation.valueOf(data.getString("Rot"));
            this.initializeStructureData(templateManager);
            this.floorBlock = Registry.BLOCK.get(new Identifier(data.getString("FloorBlock")));
        }


        private void initializeStructureData(StructureManager templateManager) {
            Structure template = templateManager.getStructureOrBlank(this.pieceRL);
            StructurePlacementData placementsettings = (new StructurePlacementData()).setRotation(this.rotation).setMirror(BlockMirror.NONE).setPosition(OFFSET_1.containsKey(this.pieceRL) ? OFFSET_1.get(this.pieceRL) : new BlockPos(3, 4, 5)).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
            this.setStructureData(template, this.pos, placementsettings);
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void toNbt(CompoundTag tagCompound) {
            super.toNbt(tagCompound);
            tagCompound.putString("Template", this.pieceRL.toString());
            tagCompound.putString("Rot", this.rotation.name());
            tagCompound.putString("FloorBlock", this.floorBlock == null ? "" : Registry.BLOCK.getId(this.floorBlock).toString());
        }


        protected void handleMetadata(String function, BlockPos pos, WorldAccess worldIn, Random rand, BlockBox sbb) {
            if ("chest".equals(function)) {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                BlockEntity tileentity = worldIn.getBlockEntity(pos.down());
                if (tileentity instanceof ChestBlockEntity) {
                    ((ChestBlockEntity) tileentity).setLootTable(LootTables.IGLOO_CHEST_CHEST, rand.nextLong());
                }

            }
        }


        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            BlockPos offset = OFFSET_1.containsKey(this.pieceRL) ? OFFSET_1.get(this.pieceRL) : new BlockPos(3, 4, 5);
            StructurePlacementData placementsettings = (new StructurePlacementData()).setRotation(this.rotation).setMirror(BlockMirror.NONE).setPosition(offset).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
            BlockPos blockpos = OFFSET_2.containsKey(this.pieceRL) ? OFFSET_2.get(this.pieceRL) : new BlockPos(0, -1, 0);
            BlockPos blockpos1 = this.pos.add(Structure.transform(placementsettings, new BlockPos(3 - blockpos.getX(), 0, -blockpos.getZ())));
            int terrainSurfaceY = world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
            BlockPos blockpos2 = this.pos;
            this.pos = this.pos.add(0, terrainSurfaceY - 90 - 1, 0);
            boolean flag = super.generate(world, structureAccessor, generator, random, boundingBox, chunkPos, blockPos);

            if (floorBlock != null) {
                BlockPos blockpos3 = this.pos.add(Structure.transform(placementsettings, new BlockPos(3, 0, 5)));
                BlockState blockstate = world.getBlockState(blockpos3.down());
                if (blockstate.getBlock() != Blocks.LADDER) {
                    world.setBlockState(blockpos3.up(), floorBlock.getDefaultState(), 3);
                    world.setBlockState(blockpos3, floorBlock.getDefaultState(), 3);
                }
            }

            this.pos = blockpos2;
            return flag;
        }
    }
}
