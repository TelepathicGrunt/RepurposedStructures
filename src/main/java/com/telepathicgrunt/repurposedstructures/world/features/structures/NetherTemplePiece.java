package com.telepathicgrunt.repurposedstructures.world.features.structures;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeverBlock;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.block.RepeaterBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TripWireBlock;
import net.minecraft.block.TripWireHookBlock;
import net.minecraft.block.VineBlock;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.RedstoneSide;
import net.minecraft.util.Direction;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.ScatteredStructurePiece;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTables;

public class NetherTemplePiece extends ScatteredStructurePiece {
   private boolean placedMainChest;
   private boolean placedHiddenChest;
   private boolean placedTrap1;
   private boolean placedTrap2;
   private static final NetherTemplePiece.Selector MOSS_STONE_SELECTOR = new NetherTemplePiece.Selector();

   public NetherTemplePiece(Random random, int x, int z) {
      super(IStructurePieceType.TEJP, random, x, 64, z, 12, 10, 15);
   }

   public NetherTemplePiece(TemplateManager p_i51350_1_, CompoundNBT p_i51350_2_) {
      super(IStructurePieceType.TEJP, p_i51350_2_);
      this.placedMainChest = p_i51350_2_.getBoolean("placedMainChest");
      this.placedHiddenChest = p_i51350_2_.getBoolean("placedHiddenChest");
      this.placedTrap1 = p_i51350_2_.getBoolean("placedTrap1");
      this.placedTrap2 = p_i51350_2_.getBoolean("placedTrap2");
   }

   /**
    * (abstract) Helper method to read subclass data from NBT
    */
   protected void readAdditional(CompoundNBT tagCompound) {
      super.readAdditional(tagCompound);
      tagCompound.putBoolean("placedMainChest", this.placedMainChest);
      tagCompound.putBoolean("placedHiddenChest", this.placedHiddenChest);
      tagCompound.putBoolean("placedTrap1", this.placedTrap1);
      tagCompound.putBoolean("placedTrap2", this.placedTrap2);
   }

   public boolean func_225577_a_(IWorld p_225577_1_, ChunkGenerator<?> p_225577_2_, Random p_225577_3_, MutableBoundingBox p_225577_4_, ChunkPos p_225577_5_) {
      if (!this.isInsideBounds(p_225577_1_, p_225577_4_, 0)) {
         return false;
      } else {
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 0, -4, 0, this.width - 1, 0, this.depth - 1, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 2, 1, 2, 9, 2, 2, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 2, 1, 12, 9, 2, 12, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 2, 1, 3, 2, 2, 11, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 9, 1, 3, 9, 2, 11, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 1, 3, 1, 10, 6, 1, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 1, 3, 13, 10, 6, 13, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 1, 3, 2, 1, 6, 12, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 10, 3, 2, 10, 6, 12, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 2, 3, 2, 9, 3, 12, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 2, 6, 2, 9, 6, 12, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 3, 7, 3, 8, 7, 11, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 4, 8, 4, 7, 8, 10, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithAir(p_225577_1_, p_225577_4_, 3, 1, 3, 8, 2, 11);
         this.fillWithAir(p_225577_1_, p_225577_4_, 4, 3, 6, 7, 3, 9);
         this.fillWithAir(p_225577_1_, p_225577_4_, 2, 4, 2, 9, 5, 12);
         this.fillWithAir(p_225577_1_, p_225577_4_, 4, 6, 5, 7, 6, 9);
         this.fillWithAir(p_225577_1_, p_225577_4_, 5, 7, 6, 6, 7, 8);
         this.fillWithAir(p_225577_1_, p_225577_4_, 5, 1, 2, 6, 2, 2);
         this.fillWithAir(p_225577_1_, p_225577_4_, 5, 2, 12, 6, 2, 12);
         this.fillWithAir(p_225577_1_, p_225577_4_, 5, 5, 1, 6, 5, 1);
         this.fillWithAir(p_225577_1_, p_225577_4_, 5, 5, 13, 6, 5, 13);
         this.setBlockState(p_225577_1_, Blocks.AIR.getDefaultState(), 1, 5, 5, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.AIR.getDefaultState(), 10, 5, 5, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.AIR.getDefaultState(), 1, 5, 9, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.AIR.getDefaultState(), 10, 5, 9, p_225577_4_);

         for(int i = 0; i <= 14; i += 14) {
            this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 2, 4, i, 2, 5, i, false, p_225577_3_, MOSS_STONE_SELECTOR);
            this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 4, 4, i, 4, 5, i, false, p_225577_3_, MOSS_STONE_SELECTOR);
            this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 7, 4, i, 7, 5, i, false, p_225577_3_, MOSS_STONE_SELECTOR);
            this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 9, 4, i, 9, 5, i, false, p_225577_3_, MOSS_STONE_SELECTOR);
         }

         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 5, 6, 0, 6, 6, 0, false, p_225577_3_, MOSS_STONE_SELECTOR);

         for(int l = 0; l <= 11; l += 11) {
            for(int j = 2; j <= 12; j += 2) {
               this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, l, 4, j, l, 5, j, false, p_225577_3_, MOSS_STONE_SELECTOR);
            }

            this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, l, 6, 5, l, 6, 5, false, p_225577_3_, MOSS_STONE_SELECTOR);
            this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, l, 6, 9, l, 6, 9, false, p_225577_3_, MOSS_STONE_SELECTOR);
         }

         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 2, 7, 2, 2, 9, 2, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 9, 7, 2, 9, 9, 2, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 2, 7, 12, 2, 9, 12, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 9, 7, 12, 9, 9, 12, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 4, 9, 4, 4, 9, 4, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 7, 9, 4, 7, 9, 4, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 4, 9, 10, 4, 9, 10, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 7, 9, 10, 7, 9, 10, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 5, 9, 7, 6, 9, 7, false, p_225577_3_, MOSS_STONE_SELECTOR);
         BlockState blockstate3 = Blocks.COBBLESTONE_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.EAST);
         BlockState blockstate4 = Blocks.COBBLESTONE_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.WEST);
         BlockState blockstate = Blocks.COBBLESTONE_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.SOUTH);
         BlockState blockstate1 = Blocks.COBBLESTONE_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
         this.setBlockState(p_225577_1_, blockstate1, 5, 9, 6, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate1, 6, 9, 6, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate, 5, 9, 8, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate, 6, 9, 8, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate1, 4, 0, 0, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate1, 5, 0, 0, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate1, 6, 0, 0, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate1, 7, 0, 0, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate1, 4, 1, 8, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate1, 4, 2, 9, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate1, 4, 3, 10, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate1, 7, 1, 8, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate1, 7, 2, 9, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate1, 7, 3, 10, p_225577_4_);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 4, 1, 9, 4, 1, 9, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 7, 1, 9, 7, 1, 9, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 4, 1, 10, 7, 2, 10, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 5, 4, 5, 6, 4, 5, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.setBlockState(p_225577_1_, blockstate3, 4, 4, 5, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate4, 7, 4, 5, p_225577_4_);

         for(int k = 0; k < 4; ++k) {
            this.setBlockState(p_225577_1_, blockstate, 5, 0 - k, 6 + k, p_225577_4_);
            this.setBlockState(p_225577_1_, blockstate, 6, 0 - k, 6 + k, p_225577_4_);
            this.fillWithAir(p_225577_1_, p_225577_4_, 5, 0 - k, 7 + k, 6, 0 - k, 9 + k);
         }

         this.fillWithAir(p_225577_1_, p_225577_4_, 1, -3, 12, 10, -1, 13);
         this.fillWithAir(p_225577_1_, p_225577_4_, 1, -3, 1, 3, -1, 13);
         this.fillWithAir(p_225577_1_, p_225577_4_, 1, -3, 1, 9, -1, 5);

         for(int i1 = 1; i1 <= 13; i1 += 2) {
            this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 1, -3, i1, 1, -2, i1, false, p_225577_3_, MOSS_STONE_SELECTOR);
         }

         for(int j1 = 2; j1 <= 12; j1 += 2) {
            this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 1, -1, j1, 3, -1, j1, false, p_225577_3_, MOSS_STONE_SELECTOR);
         }

         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 2, -2, 1, 5, -2, 1, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 7, -2, 1, 9, -2, 1, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 6, -3, 1, 6, -3, 1, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 6, -1, 1, 6, -1, 1, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.setBlockState(p_225577_1_, Blocks.TRIPWIRE_HOOK.getDefaultState().with(TripWireHookBlock.FACING, Direction.EAST).with(TripWireHookBlock.ATTACHED, Boolean.valueOf(true)), 1, -3, 8, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.TRIPWIRE_HOOK.getDefaultState().with(TripWireHookBlock.FACING, Direction.WEST).with(TripWireHookBlock.ATTACHED, Boolean.valueOf(true)), 4, -3, 8, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.TRIPWIRE.getDefaultState().with(TripWireBlock.EAST, Boolean.valueOf(true)).with(TripWireBlock.WEST, Boolean.valueOf(true)).with(TripWireBlock.ATTACHED, Boolean.valueOf(true)), 2, -3, 8, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.TRIPWIRE.getDefaultState().with(TripWireBlock.EAST, Boolean.valueOf(true)).with(TripWireBlock.WEST, Boolean.valueOf(true)).with(TripWireBlock.ATTACHED, Boolean.valueOf(true)), 3, -3, 8, p_225577_4_);
         BlockState blockstate5 = Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.NORTH, RedstoneSide.SIDE).with(RedstoneWireBlock.SOUTH, RedstoneSide.SIDE);
         this.setBlockState(p_225577_1_, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.SOUTH, RedstoneSide.SIDE), 5, -3, 7, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate5, 5, -3, 6, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate5, 5, -3, 5, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate5, 5, -3, 4, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate5, 5, -3, 3, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate5, 5, -3, 2, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.NORTH, RedstoneSide.SIDE).with(RedstoneWireBlock.WEST, RedstoneSide.SIDE), 5, -3, 1, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.EAST, RedstoneSide.SIDE), 4, -3, 1, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 3, -3, 1, p_225577_4_);
         if (!this.placedTrap1) {
            this.placedTrap1 = this.createDispenser(p_225577_1_, p_225577_4_, p_225577_3_, 3, -2, 1, Direction.NORTH, LootTables.CHESTS_JUNGLE_TEMPLE_DISPENSER);
         }

         this.setBlockState(p_225577_1_, Blocks.VINE.getDefaultState().with(VineBlock.SOUTH, Boolean.valueOf(true)), 3, -2, 2, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.TRIPWIRE_HOOK.getDefaultState().with(TripWireHookBlock.FACING, Direction.NORTH).with(TripWireHookBlock.ATTACHED, Boolean.valueOf(true)), 7, -3, 1, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.TRIPWIRE_HOOK.getDefaultState().with(TripWireHookBlock.FACING, Direction.SOUTH).with(TripWireHookBlock.ATTACHED, Boolean.valueOf(true)), 7, -3, 5, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.TRIPWIRE.getDefaultState().with(TripWireBlock.NORTH, Boolean.valueOf(true)).with(TripWireBlock.SOUTH, Boolean.valueOf(true)).with(TripWireBlock.ATTACHED, Boolean.valueOf(true)), 7, -3, 2, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.TRIPWIRE.getDefaultState().with(TripWireBlock.NORTH, Boolean.valueOf(true)).with(TripWireBlock.SOUTH, Boolean.valueOf(true)).with(TripWireBlock.ATTACHED, Boolean.valueOf(true)), 7, -3, 3, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.TRIPWIRE.getDefaultState().with(TripWireBlock.NORTH, Boolean.valueOf(true)).with(TripWireBlock.SOUTH, Boolean.valueOf(true)).with(TripWireBlock.ATTACHED, Boolean.valueOf(true)), 7, -3, 4, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.EAST, RedstoneSide.SIDE), 8, -3, 6, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.WEST, RedstoneSide.SIDE).with(RedstoneWireBlock.SOUTH, RedstoneSide.SIDE), 9, -3, 6, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.NORTH, RedstoneSide.SIDE).with(RedstoneWireBlock.SOUTH, RedstoneSide.UP), 9, -3, 5, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 9, -3, 4, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.NORTH, RedstoneSide.SIDE), 9, -2, 4, p_225577_4_);
         if (!this.placedTrap2) {
            this.placedTrap2 = this.createDispenser(p_225577_1_, p_225577_4_, p_225577_3_, 9, -2, 3, Direction.WEST, LootTables.CHESTS_JUNGLE_TEMPLE_DISPENSER);
         }

         this.setBlockState(p_225577_1_, Blocks.VINE.getDefaultState().with(VineBlock.EAST, Boolean.valueOf(true)), 8, -1, 3, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.VINE.getDefaultState().with(VineBlock.EAST, Boolean.valueOf(true)), 8, -2, 3, p_225577_4_);
         if (!this.placedMainChest) {
            this.placedMainChest = this.generateChest(p_225577_1_, p_225577_4_, p_225577_3_, 8, -3, 3, LootTables.CHESTS_JUNGLE_TEMPLE);
         }

         this.setBlockState(p_225577_1_, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 9, -3, 2, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 8, -3, 1, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 4, -3, 5, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 5, -2, 5, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 5, -1, 5, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 6, -3, 5, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 7, -2, 5, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 7, -1, 5, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 8, -3, 5, p_225577_4_);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 9, -1, 1, 9, -1, 5, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithAir(p_225577_1_, p_225577_4_, 8, -3, 8, 10, -1, 10);
         this.setBlockState(p_225577_1_, Blocks.CHISELED_STONE_BRICKS.getDefaultState(), 8, -2, 11, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.CHISELED_STONE_BRICKS.getDefaultState(), 9, -2, 11, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.CHISELED_STONE_BRICKS.getDefaultState(), 10, -2, 11, p_225577_4_);
         BlockState blockstate2 = Blocks.LEVER.getDefaultState().with(LeverBlock.HORIZONTAL_FACING, Direction.NORTH).with(LeverBlock.FACE, AttachFace.WALL);
         this.setBlockState(p_225577_1_, blockstate2, 8, -2, 12, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate2, 9, -2, 12, p_225577_4_);
         this.setBlockState(p_225577_1_, blockstate2, 10, -2, 12, p_225577_4_);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 8, -3, 8, 8, -3, 10, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.fillWithRandomizedBlocks(p_225577_1_, p_225577_4_, 10, -3, 8, 10, -3, 10, false, p_225577_3_, MOSS_STONE_SELECTOR);
         this.setBlockState(p_225577_1_, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 10, -2, 9, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.NORTH, RedstoneSide.SIDE), 8, -2, 9, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.SOUTH, RedstoneSide.SIDE), 8, -2, 10, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.REDSTONE_WIRE.getDefaultState(), 10, -1, 9, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.STICKY_PISTON.getDefaultState().with(PistonBlock.FACING, Direction.UP), 9, -2, 8, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.STICKY_PISTON.getDefaultState().with(PistonBlock.FACING, Direction.WEST), 10, -2, 8, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.STICKY_PISTON.getDefaultState().with(PistonBlock.FACING, Direction.WEST), 10, -1, 8, p_225577_4_);
         this.setBlockState(p_225577_1_, Blocks.REPEATER.getDefaultState().with(RepeaterBlock.HORIZONTAL_FACING, Direction.NORTH), 10, -2, 10, p_225577_4_);
         if (!this.placedHiddenChest) {
            this.placedHiddenChest = this.generateChest(p_225577_1_, p_225577_4_, p_225577_3_, 9, -3, 10, LootTables.CHESTS_JUNGLE_TEMPLE);
         }

         return true;
      }
   }

   static class Selector extends StructurePiece.BlockSelector {
      private Selector() {
      }

      /**
       * picks Block Ids and Metadata (Silverfish)
       */
      public void selectBlocks(Random rand, int x, int y, int z, boolean wall) {
         if (rand.nextFloat() < 0.4F) {
            this.blockstate = Blocks.COBBLESTONE.getDefaultState();
         } else {
            this.blockstate = Blocks.MOSSY_COBBLESTONE.getDefaultState();
         }

      }
   }
}