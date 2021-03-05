package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructurePieces;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.*;


public class FortressJunglePieces {
    private static final Identifier JF_HALLWAY_CHEST_RL = new Identifier(RepurposedStructures.MODID, "chests/fortress/jungle_hallway_chest");
    private static final Identifier JF_SHRINE_CHEST_RL = new Identifier(RepurposedStructures.MODID, "chests/fortress/jungle_shrine_chest");
    private static final Identifier JF_CENTER_CHEST_RL = new Identifier(RepurposedStructures.MODID, "chests/fortress/jungle_center_chest");
    private static final Identifier JF_PLANT_TAG_RL = new Identifier("repurposed_structures:jungle_fortress_staircase_plants");
    private static final Identifier JF_SOIL_TAG_RL = new Identifier("repurposed_structures:jungle_fortress_staircase_soils");
    private static final Identifier SPAWNER_ID = new Identifier(RepurposedStructures.MODID, "fortress_jungle");

    private static final Map<BlockState, BlockState> INFESTED_STONE_LOOKUP;

    static {
        INFESTED_STONE_LOOKUP = new HashMap<>();
        INFESTED_STONE_LOOKUP.put(Blocks.STONE_BRICKS.getDefaultState(), Blocks.INFESTED_STONE_BRICKS.getDefaultState());
        INFESTED_STONE_LOOKUP.put(Blocks.STONE.getDefaultState(), Blocks.INFESTED_STONE.getDefaultState());
        INFESTED_STONE_LOOKUP.put(Blocks.MOSSY_COBBLESTONE.getDefaultState(), Blocks.INFESTED_MOSSY_STONE_BRICKS.getDefaultState());
        INFESTED_STONE_LOOKUP.put(Blocks.CRACKED_STONE_BRICKS.getDefaultState(), Blocks.INFESTED_CRACKED_STONE_BRICKS.getDefaultState());
        INFESTED_STONE_LOOKUP.put(Blocks.COBBLESTONE.getDefaultState(), Blocks.INFESTED_COBBLESTONE.getDefaultState());
        INFESTED_STONE_LOOKUP.put(Blocks.CHISELED_STONE_BRICKS.getDefaultState(), Blocks.INFESTED_CHISELED_STONE_BRICKS.getDefaultState());
        INFESTED_STONE_LOOKUP.put(Blocks.MOSSY_STONE_BRICKS.getDefaultState(), Blocks.MOSSY_STONE_BRICKS.getDefaultState());
    }

    private static final FortressJunglePieces.PieceWeight[] PRIMARY_COMPONENTS = new FortressJunglePieces.PieceWeight[]{new FortressJunglePieces.PieceWeight(FortressJunglePieces.Straight.class, 30, 0, true), new FortressJunglePieces.PieceWeight(FortressJunglePieces.Crossing3.class, 10, 4), new FortressJunglePieces.PieceWeight(FortressJunglePieces.Crossing.class, 10, 4), new FortressJunglePieces.PieceWeight(FortressJunglePieces.Stairs.class, 10, 3), new FortressJunglePieces.PieceWeight(FortressJunglePieces.Throne.class, 5, 2), new FortressJunglePieces.PieceWeight(FortressJunglePieces.Entrance.class, 5, 1)};
    private static final FortressJunglePieces.PieceWeight[] SECONDARY_COMPONENTS = new FortressJunglePieces.PieceWeight[]{new FortressJunglePieces.PieceWeight(FortressJunglePieces.Corridor5.class, 25, 0, true), new FortressJunglePieces.PieceWeight(FortressJunglePieces.Crossing2.class, 15, 5), new FortressJunglePieces.PieceWeight(FortressJunglePieces.Corridor2.class, 5, 10), new FortressJunglePieces.PieceWeight(FortressJunglePieces.Corridor.class, 5, 10), new FortressJunglePieces.PieceWeight(FortressJunglePieces.Corridor3.class, 10, 3, true), new FortressJunglePieces.PieceWeight(FortressJunglePieces.Corridor4.class, 7, 2),
            new FortressJunglePieces.PieceWeight(FortressJunglePieces.MushroomRoom.class, 5, 2)};


    private static FortressJunglePieces.Piece findAndCreateBridgePieceFactory(FortressJunglePieces.PieceWeight p_175887_0_, List<StructurePiece> p_175887_1_, Random p_175887_2_, int p_175887_3_, int p_175887_4_, int p_175887_5_, Direction p_175887_6_, int p_175887_7_) {
        Class<? extends FortressJunglePieces.Piece> oclass = p_175887_0_.weightClass;
        FortressJunglePieces.Piece structurenetherbridgepieces$piece = null;

        if (oclass == FortressJunglePieces.Straight.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Straight.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == FortressJunglePieces.Crossing3.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Crossing3.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == FortressJunglePieces.Crossing.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Crossing.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == FortressJunglePieces.Stairs.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Stairs.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_7_, p_175887_6_);
        } else if (oclass == FortressJunglePieces.Throne.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Throne.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_7_, p_175887_6_);
        } else if (oclass == FortressJunglePieces.Entrance.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Entrance.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == FortressJunglePieces.Corridor5.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Corridor5.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == FortressJunglePieces.Corridor2.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Corridor2.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == FortressJunglePieces.Corridor.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Corridor.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == FortressJunglePieces.Corridor3.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Corridor3.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == FortressJunglePieces.Corridor4.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Corridor4.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == FortressJunglePieces.Crossing2.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Crossing2.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        } else if (oclass == FortressJunglePieces.MushroomRoom.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.MushroomRoom.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
        }

        return structurenetherbridgepieces$piece;
    }

    public static class Corridor extends FortressJunglePieces.Piece {

        public Corridor(int p_i45615_1_, Random rand, BlockBox p_i45615_3_, Direction p_i45615_4_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_CORRIDOR_1, p_i45615_1_);
            this.setOrientation(p_i45615_4_);
            this.boundingBox = p_i45615_3_;
        }


        public Corridor(StructureManager p_i50272_1_, CompoundTag p_i50272_2_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_CORRIDOR_1, p_i50272_2_);
        }


        @Override
        public void fillOpenings(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentX((FortressJunglePieces.Start) componentIn, listIn, rand, 0, 1, true);
        }


        public static FortressJunglePieces.Corridor createPiece(List<StructurePiece> p_175879_0_, Random p_175879_1_, int p_175879_2_, int p_175879_3_, int p_175879_4_, Direction p_175879_5_, int p_175879_6_) {
            BlockBox mutableBoundingBox = BlockBox.rotated(p_175879_2_, p_175879_3_, p_175879_4_, -1, 0, 0, 5, 7, 5, p_175879_5_);
            return isAboveGround(mutableBoundingBox) && StructurePiece.getOverlappingPiece(p_175879_0_, mutableBoundingBox) == null ? new FortressJunglePieces.Corridor(p_175879_6_, p_175879_1_, mutableBoundingBox, p_175879_5_) : null;
        }


        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            this.fillWithRandomBlocks(world, boundingBox, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 4, 5, 4, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.WEST, true).with(HorizontalConnectingBlock.EAST, true), random);
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.NORTH, true).with(HorizontalConnectingBlock.SOUTH, true), random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 2, 0, 4, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 3, 1, 4, 4, 1, iblockstate1, iblockstate1, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 3, 3, 4, 4, 3, iblockstate1, iblockstate1, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 0, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 4, 3, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 3, 4, 1, 4, 4, iblockstate, iblockstate, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 3, 3, 4, 3, 4, 4, iblockstate, iblockstate, false, random);

            if (RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.lootChests && random.nextInt(5) == 0 && boundingBox.contains(new BlockPos(this.applyXTransform(3, 3), this.applyYTransform(2), this.applyZTransform(3, 3)))) {
                this.addChest(world, boundingBox, random, 3, 2, 3, JF_HALLWAY_CHEST_RL);
            }

            this.fillWithRandomBlocks(world, boundingBox, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 4; ++j) {
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, boundingBox, random);
                }
            }

            this.fillWithWater(world, boundingBox, 0, 0, 0, 4, 5, 4);
            return true;
        }
    }

    public static class Corridor2 extends FortressJunglePieces.Piece {

        public Corridor2(int p_i45613_1_, Random rand, BlockBox p_i45613_3_, Direction p_i45613_4_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_CORRIDOR_2, p_i45613_1_);
            this.setOrientation(p_i45613_4_);
            this.boundingBox = p_i45613_3_;
        }


        public Corridor2(StructureManager p_i50266_1_, CompoundTag p_i50266_2_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_CORRIDOR_2, p_i50266_2_);
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag tagCompound) {
            super.toNbt(tagCompound);
        }


        @Override
        public void fillOpenings(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentZ((FortressJunglePieces.Start) componentIn, listIn, rand, 0, 1, true);
        }


        public static FortressJunglePieces.Corridor2 createPiece(List<StructurePiece> p_175876_0_, Random p_175876_1_, int p_175876_2_, int p_175876_3_, int p_175876_4_, Direction p_175876_5_, int p_175876_6_) {
            BlockBox mutableBoundingBox = BlockBox.rotated(p_175876_2_, p_175876_3_, p_175876_4_, -1, 0, 0, 5, 7, 5, p_175876_5_);
            return isAboveGround(mutableBoundingBox) && StructurePiece.getOverlappingPiece(p_175876_0_, mutableBoundingBox) == null ? new FortressJunglePieces.Corridor2(p_175876_6_, p_175876_1_, mutableBoundingBox, p_175876_5_) : null;
        }


        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            this.fillWithRandomBlocks(world, boundingBox, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 4, 5, 4, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState(), random).with(HorizontalConnectingBlock.WEST, true).with(HorizontalConnectingBlock.EAST, true);
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState(), random).with(HorizontalConnectingBlock.NORTH, true).with(HorizontalConnectingBlock.SOUTH, true);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 0, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 3, 1, 0, 4, 1, iblockstate1, iblockstate1, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 3, 3, 0, 4, 3, iblockstate1, iblockstate1, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 2, 0, 4, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 2, 4, 4, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 3, 4, 1, 4, 4, iblockstate, iblockstate, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 3, 3, 4, 3, 4, 4, iblockstate, iblockstate, false, random);

            if (RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.lootChests && random.nextInt(5) == 0 && boundingBox.contains(new BlockPos(this.applyXTransform(1, 3), this.applyYTransform(2), this.applyZTransform(1, 3)))) {
                this.addChest(world, boundingBox, random, 1, 2, 3, JF_HALLWAY_CHEST_RL);
            }

            this.fillWithRandomBlocks(world, boundingBox, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 4; ++j) {
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, boundingBox, random);
                }
            }

            this.fillWithWater(world, boundingBox, 0, 0, 0, 4, 6, 4);
            return true;
        }
    }

    public static class Corridor3 extends FortressJunglePieces.Piece {
        public Corridor3(int p_i50280_1_, BlockBox p_i50280_2_, Direction p_i50280_3_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_CORRIDOR_3, p_i50280_1_);
            this.setOrientation(p_i50280_3_);
            this.boundingBox = p_i50280_2_;
        }


        public Corridor3(StructureManager p_i50281_1_, CompoundTag p_i50281_2_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_CORRIDOR_3, p_i50281_2_);
        }


        @Override
        public void fillOpenings(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentNormal((FortressJunglePieces.Start) componentIn, listIn, rand, 1, 0, true);
        }


        public static FortressJunglePieces.Corridor3 createPiece(List<StructurePiece> p_175883_0_, Random p_175883_1_, int p_175883_2_, int p_175883_3_, int p_175883_4_, Direction p_175883_5_, int p_175883_6_) {
            BlockBox mutableBoundingBox = BlockBox.rotated(p_175883_2_, p_175883_3_, p_175883_4_, -1, -7, 0, 5, 14, 10, p_175883_5_);
            return isAboveGround(mutableBoundingBox) && StructurePiece.getOverlappingPiece(p_175883_0_, mutableBoundingBox) == null ? new FortressJunglePieces.Corridor3(p_175883_6_, mutableBoundingBox, p_175883_5_) : null;
        }


        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.SOUTH), random);
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.NORTH, true).with(HorizontalConnectingBlock.SOUTH, true), random);

            for (int i = 0; i <= 9; ++i) {
                int j = Math.max(1, 7 - i);
                int k = Math.min(Math.max(j + 5, 14 - i), 13);
                this.fillWithRandomBlocks(world, boundingBox, 0, 0, i, 4, j, i, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
                this.fillWithRandomBlocks(world, boundingBox, 1, j + 1, i, 3, k - 1, i, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);

                if (i <= 6) {
                    this.addBlock(world, iblockstate, 1, j + 1, i, boundingBox);
                    this.addBlock(world, iblockstate, 2, j + 1, i, boundingBox);
                    this.addBlock(world, iblockstate, 3, j + 1, i, boundingBox);
                }

                this.fillWithRandomBlocks(world, boundingBox, 0, k, i, 4, k, i, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
                this.fillWithRandomBlocks(world, boundingBox, 0, j + 1, i, 0, k - 1, i, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
                this.fillWithRandomBlocks(world, boundingBox, 4, j + 1, i, 4, k - 1, i, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

                if ((i & 1) == 0) {
                    this.fillWithRandomBlocks(world, boundingBox, 0, j + 2, i, 0, j + 3, i, iblockstate1, iblockstate1, false, random);
                    this.fillWithRandomBlocks(world, boundingBox, 4, j + 2, i, 4, j + 3, i, iblockstate1, iblockstate1, false, random);
                }

                for (int i1 = 0; i1 <= 4; ++i1) {
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i1, -1, i, boundingBox, random);
                }
            }

            this.fillWithWater(world, boundingBox, 0, 0, 0, 4, 9, 9);
            return true;
        }
    }

    public static class Corridor4 extends FortressJunglePieces.Piece {
        public Corridor4(int p_i50277_1_, BlockBox p_i50277_2_, Direction p_i50277_3_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_CORRIDOR_4, p_i50277_1_);
            this.setOrientation(p_i50277_3_);
            this.boundingBox = p_i50277_2_;
        }


        public Corridor4(StructureManager p_i50278_1_, CompoundTag p_i50278_2_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_CORRIDOR_4, p_i50278_2_);
        }


        @Override
        public void fillOpenings(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            int i = 1;
            Direction enumfacing = this.getFacing();

            if (enumfacing == Direction.WEST || enumfacing == Direction.NORTH) {
                i = 5;
            }

            this.getNextComponentX((FortressJunglePieces.Start) componentIn, listIn, rand, 0, i, rand.nextInt(8) > 0);
            this.getNextComponentZ((FortressJunglePieces.Start) componentIn, listIn, rand, 0, i, rand.nextInt(8) > 0);
        }


        public static FortressJunglePieces.Corridor4 createPiece(List<StructurePiece> p_175880_0_, Random p_175880_1_, int p_175880_2_, int p_175880_3_, int p_175880_4_, Direction p_175880_5_, int p_175880_6_) {
            BlockBox mutableBoundingBox = BlockBox.rotated(p_175880_2_, p_175880_3_, p_175880_4_, -3, 0, 0, 9, 7, 9, p_175880_5_);
            return isAboveGround(mutableBoundingBox) && StructurePiece.getOverlappingPiece(p_175880_0_, mutableBoundingBox) == null ? new FortressJunglePieces.Corridor4(p_175880_6_, mutableBoundingBox, p_175880_5_) : null;
        }


        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.NORTH, true).with(HorizontalConnectingBlock.SOUTH, true), random);
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.WEST, true).with(HorizontalConnectingBlock.EAST, true), random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 0, 0, 8, 1, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 8, 5, 8, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 6, 0, 8, 6, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 2, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 6, 2, 0, 8, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 3, 0, 1, 4, 0, iblockstate1, iblockstate1, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 7, 3, 0, 7, 4, 0, iblockstate1, iblockstate1, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 4, 8, 2, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 1, 4, 2, 2, 4, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 6, 1, 4, 7, 2, 4, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 3, 8, 7, 3, 8, iblockstate1, iblockstate1, false, random);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.EAST, true).with(HorizontalConnectingBlock.SOUTH, true), random), 0, 3, 8, boundingBox);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.WEST, true).with(HorizontalConnectingBlock.SOUTH, true), random), 8, 3, 8, boundingBox);
            this.fillWithRandomBlocks(world, boundingBox, 0, 3, 6, 0, 3, 7, iblockstate, iblockstate, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 8, 3, 6, 8, 3, 7, iblockstate, iblockstate, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 3, 4, 0, 5, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 8, 3, 4, 8, 5, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 3, 5, 2, 5, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 6, 3, 5, 7, 5, 5, Blocks.NETHER_BRICKS.getDefaultState(), getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 4, 5, 1, 5, 5, iblockstate1, iblockstate1, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 7, 4, 5, 7, 5, 5, iblockstate1, iblockstate1, false, random);

            for (int i = 0; i <= 5; ++i) {
                for (int j = 0; j <= 8; ++j) {
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), j, -1, i, boundingBox, random);
                }
            }

            this.fillWithWater(world, boundingBox, 0, 0, 0, 8, 5, 8);
            return true;
        }
    }

    public static class Corridor5 extends FortressJunglePieces.Piece {
        public Corridor5(int p_i50268_1_, BlockBox p_i50268_2_, Direction p_i50268_3_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_CORRIDOR_5, p_i50268_1_);
            this.setOrientation(p_i50268_3_);
            this.boundingBox = p_i50268_2_;
        }


        public Corridor5(StructureManager p_i50269_1_, CompoundTag p_i50269_2_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_CORRIDOR_5, p_i50269_2_);
        }


        @Override
        public void fillOpenings(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentNormal((FortressJunglePieces.Start) componentIn, listIn, rand, 1, 0, true);
        }


        public static FortressJunglePieces.Corridor5 createPiece(List<StructurePiece> p_175877_0_, Random p_175877_1_, int p_175877_2_, int p_175877_3_, int p_175877_4_, Direction p_175877_5_, int p_175877_6_) {
            BlockBox mutableBoundingBox = BlockBox.rotated(p_175877_2_, p_175877_3_, p_175877_4_, -1, 0, 0, 5, 7, 5, p_175877_5_);
            return isAboveGround(mutableBoundingBox) && StructurePiece.getOverlappingPiece(p_175877_0_, mutableBoundingBox) == null ? new FortressJunglePieces.Corridor5(p_175877_6_, mutableBoundingBox, p_175877_5_) : null;
        }


        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            this.fillWithRandomBlocks(world, boundingBox, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 4, 5, 4, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.NORTH, true).with(HorizontalConnectingBlock.SOUTH, true), random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 0, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 2, 0, 4, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 3, 1, 0, 4, 1, iblockstate, iblockstate, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 3, 3, 0, 4, 3, iblockstate, iblockstate, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 3, 1, 4, 4, 1, iblockstate, iblockstate, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 3, 3, 4, 4, 3, iblockstate, iblockstate, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 4; ++j) {
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, boundingBox, random);
                }
            }

            this.fillWithWater(world, boundingBox, 0, 0, 0, 4, 6, 4);
            return true;
        }
    }

    public static class Crossing extends FortressJunglePieces.Piece {
        public Crossing(int p_i50258_1_, BlockBox p_i50258_2_, Direction p_i50258_3_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_CROSSING_1, p_i50258_1_);
            this.setOrientation(p_i50258_3_);
            this.boundingBox = p_i50258_2_;
        }


        public Crossing(StructureManager p_i50259_1_, CompoundTag p_i50259_2_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_CROSSING_1, p_i50259_2_);
        }


        @Override
        public void fillOpenings(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentNormal((FortressJunglePieces.Start) componentIn, listIn, rand, 2, 0, false);
            this.getNextComponentX((FortressJunglePieces.Start) componentIn, listIn, rand, 0, 2, false);
            this.getNextComponentZ((FortressJunglePieces.Start) componentIn, listIn, rand, 0, 2, false);
        }


        public static FortressJunglePieces.Crossing createPiece(List<StructurePiece> p_175873_0_, Random p_175873_1_, int p_175873_2_, int p_175873_3_, int p_175873_4_, Direction p_175873_5_, int p_175873_6_) {
            BlockBox mutableBoundingBox = BlockBox.rotated(p_175873_2_, p_175873_3_, p_175873_4_, -2, 0, 0, 7, 9, 7, p_175873_5_);
            return isAboveGround(mutableBoundingBox) && StructurePiece.getOverlappingPiece(p_175873_0_, mutableBoundingBox) == null ? new FortressJunglePieces.Crossing(p_175873_6_, mutableBoundingBox, p_175873_5_) : null;
        }


        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            this.fillWithRandomBlocks(world, boundingBox, 0, 0, 0, 6, 1, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 6, 7, 6, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 1, 6, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 6, 1, 6, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 5, 2, 0, 6, 6, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 5, 2, 6, 6, 6, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 0, 6, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 5, 0, 6, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 6, 2, 0, 6, 6, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 6, 2, 5, 6, 6, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.WEST, true).with(HorizontalConnectingBlock.EAST, true), random);
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.NORTH, true).with(HorizontalConnectingBlock.SOUTH, true), random);
            this.fillWithRandomBlocks(world, boundingBox, 2, 6, 0, 4, 6, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 2, 5, 0, 4, 5, 0, iblockstate, iblockstate, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 2, 6, 6, 4, 6, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 2, 5, 6, 4, 5, 6, iblockstate, iblockstate, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 6, 2, 0, 6, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 5, 2, 0, 5, 4, iblockstate1, iblockstate1, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 6, 6, 2, 6, 6, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 6, 5, 2, 6, 5, 4, iblockstate1, iblockstate1, false, random);

            for (int i = 0; i <= 6; ++i) {
                for (int j = 0; j <= 6; ++j) {
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, boundingBox, random);
                }
            }

            this.fillWithWater(world, boundingBox, 0, 0, 0, 6, 6, 6);
            return true;
        }
    }

    public static class Crossing2 extends FortressJunglePieces.Piece {
        public Crossing2(int p_i50273_1_, BlockBox p_i50273_2_, Direction p_i50273_3_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_CROSSING_2, p_i50273_1_);
            this.setOrientation(p_i50273_3_);
            this.boundingBox = p_i50273_2_;
        }


        public Crossing2(StructureManager p_i50274_1_, CompoundTag p_i50274_2_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_CROSSING_2, p_i50274_2_);
        }


        @Override
        public void fillOpenings(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentNormal((FortressJunglePieces.Start) componentIn, listIn, rand, 1, 0, true);
            this.getNextComponentX((FortressJunglePieces.Start) componentIn, listIn, rand, 0, 1, true);
            this.getNextComponentZ((FortressJunglePieces.Start) componentIn, listIn, rand, 0, 1, true);
        }


        public static FortressJunglePieces.Crossing2 createPiece(List<StructurePiece> p_175878_0_, Random p_175878_1_, int p_175878_2_, int p_175878_3_, int p_175878_4_, Direction p_175878_5_, int p_175878_6_) {
            BlockBox mutableBoundingBox = BlockBox.rotated(p_175878_2_, p_175878_3_, p_175878_4_, -1, 0, 0, 5, 7, 5, p_175878_5_);
            return isAboveGround(mutableBoundingBox) && StructurePiece.getOverlappingPiece(p_175878_0_, mutableBoundingBox) == null ? new FortressJunglePieces.Crossing2(p_175878_6_, mutableBoundingBox, p_175878_5_) : null;
        }


        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            this.fillWithRandomBlocks(world, boundingBox, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 4, 5, 4, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 0, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 2, 0, 4, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 4, 0, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 2, 4, 4, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 4; ++j) {
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, boundingBox, random);
                }
            }

            this.fillWithWater(world, boundingBox, 0, 0, 0, 4, 6, 4);
            return true;
        }
    }

    public static class Crossing3 extends FortressJunglePieces.Piece {

        protected Crossing3(StructurePieceType p_i50287_1_, CompoundTag p_i50287_2_) {
            super(p_i50287_1_, p_i50287_2_);
        }


        public Crossing3(StructureManager p_i50288_1_, CompoundTag p_i50288_2_) {
            this(RSStructurePieces.JUNGLE_FORTRESS_CROSSING_3, p_i50288_2_);
        }


        public Crossing3(int p_i50286_1_, BlockBox p_i50286_2_, Direction p_i50286_3_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_CROSSING_3, p_i50286_1_);
            this.setOrientation(p_i50286_3_);
            this.boundingBox = p_i50286_2_;
        }


        protected Crossing3(Random p_i2042_1_, int p_i2042_2_, int p_i2042_3_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_CROSSING_3, 0);
            this.setOrientation(Direction.Type.HORIZONTAL.random(p_i2042_1_));
            if (this.getFacing().getAxis() == Direction.Axis.Z) {
                this.boundingBox = new BlockBox(p_i2042_2_, 64, p_i2042_3_, p_i2042_2_ + 19 - 1, 73, p_i2042_3_ + 19 - 1);
            } else {
                this.boundingBox = new BlockBox(p_i2042_2_, 64, p_i2042_3_, p_i2042_2_ + 19 - 1, 73, p_i2042_3_ + 19 - 1);
            }

        }


        @Override
        public void fillOpenings(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentNormal((FortressJunglePieces.Start) componentIn, listIn, rand, 8, 3, false);
            this.getNextComponentX((FortressJunglePieces.Start) componentIn, listIn, rand, 3, 8, false);
            this.getNextComponentZ((FortressJunglePieces.Start) componentIn, listIn, rand, 3, 8, false);
        }


        public static FortressJunglePieces.Crossing3 createPiece(List<StructurePiece> p_175885_0_, Random p_175885_1_, int p_175885_2_, int p_175885_3_, int p_175885_4_, Direction p_175885_5_, int p_175885_6_) {
            BlockBox mutableBoundingBox = BlockBox.rotated(p_175885_2_, p_175885_3_, p_175885_4_, -8, -3, 0, 19, 10, 19, p_175885_5_);
            return isAboveGround(mutableBoundingBox) && StructurePiece.getOverlappingPiece(p_175885_0_, mutableBoundingBox) == null ? new FortressJunglePieces.Crossing3(p_175885_6_, mutableBoundingBox, p_175885_5_) : null;
        }


        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            this.fillWithRandomBlocks(world, boundingBox, 7, 3, 0, 11, 4, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 3, 7, 18, 4, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 8, 5, 0, 10, 7, 18, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 5, 8, 18, 7, 10, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 7, 5, 0, 7, 5, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 7, 5, 11, 7, 5, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 11, 5, 0, 11, 5, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 11, 5, 11, 11, 5, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 5, 7, 7, 5, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 11, 5, 7, 18, 5, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 5, 11, 7, 5, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 11, 5, 11, 18, 5, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 7, 2, 0, 11, 2, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 7, 2, 13, 11, 2, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 7, 0, 0, 11, 1, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 7, 0, 15, 11, 1, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

            for (int i = 7; i <= 11; ++i) {
                for (int j = 0; j <= 2; ++j) {
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, boundingBox, random);
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, 18 - j, boundingBox, random);
                }
            }

            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 7, 5, 2, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 13, 2, 7, 18, 2, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 0, 7, 3, 1, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 15, 0, 7, 18, 1, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

            for (int k = 0; k <= 2; ++k) {
                for (int l = 7; l <= 11; ++l) {
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), k, -1, l, boundingBox, random);
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), 18 - k, -1, l, boundingBox, random);
                }
            }

            this.fillWithWater(world, boundingBox, 0, 0, 0, 18, 7, 18);
            return true;
        }
    }

    public static class End extends FortressJunglePieces.Piece {
        private final int fillSeed;


        public End(int p_i45621_1_, Random p_i45621_2_, BlockBox p_i45621_3_, Direction p_i45621_4_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_END, p_i45621_1_);
            this.setOrientation(p_i45621_4_);
            this.boundingBox = p_i45621_3_;
            this.fillSeed = p_i45621_2_.nextInt();
        }


        public End(StructureManager p_i50285_1_, CompoundTag p_i50285_2_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_END, p_i50285_2_);
            this.fillSeed = p_i50285_2_.getInt("Seed");
        }


        public static FortressJunglePieces.End createPiece(List<StructurePiece> p_175884_0_, Random p_175884_1_, int p_175884_2_, int p_175884_3_, int p_175884_4_, Direction p_175884_5_, int p_175884_6_) {
            BlockBox mutableBoundingBox = BlockBox.rotated(p_175884_2_, p_175884_3_, p_175884_4_, -1, -3, 0, 5, 10, 8, p_175884_5_);
            return isAboveGround(mutableBoundingBox) && StructurePiece.getOverlappingPiece(p_175884_0_, mutableBoundingBox) == null ? new FortressJunglePieces.End(p_175884_6_, p_175884_1_, mutableBoundingBox, p_175884_5_) : null;
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag tagCompound) {
            super.toNbt(tagCompound);
            tagCompound.putInt("Seed", this.fillSeed);
        }


        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            Random seededRandom = new Random(this.fillSeed);

            for (int i = 0; i <= 4; ++i) {
                for (int j = 3; j <= 4; ++j) {
                    int k = seededRandom.nextInt(8);
                    this.fillWithRandomBlocks(world, boundingBox, i, j, 0, i, j, k, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
                }
            }

            int l = seededRandom.nextInt(8);
            this.fillWithRandomBlocks(world, boundingBox, 0, 5, 0, 0, 5, l, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            l = seededRandom.nextInt(8);
            this.fillWithRandomBlocks(world, boundingBox, 4, 5, 0, 4, 5, l, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

            for (l = 0; l <= 4; ++l) {
                int i1 = seededRandom.nextInt(5);
                this.fillWithRandomBlocks(world, boundingBox, l, 2, 0, l, 2, i1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            }

            for (l = 0; l <= 4; ++l) {
                for (int j1 = 0; j1 <= 1; ++j1) {
                    int k1 = seededRandom.nextInt(3);
                    this.fillWithRandomBlocks(world, boundingBox, l, j1, 0, l, j1, k1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
                }
            }

            this.fillWithWater(world, boundingBox, 0, 0, 0, 7, 5, 7);
            return true;
        }
    }

    public static class Entrance extends FortressJunglePieces.Piece {
        public Entrance(int p_i45617_1_, Random rand, BlockBox p_i45617_3_, Direction p_i45617_4_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_ENTRANCE, p_i45617_1_);
            this.setOrientation(p_i45617_4_);
            this.boundingBox = p_i45617_3_;
        }


        public Entrance(StructureManager p_i50276_1_, CompoundTag p_i50276_2_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_ENTRANCE, p_i50276_2_);
        }


        @Override
        public void fillOpenings(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentNormal((FortressJunglePieces.Start) componentIn, listIn, rand, 5, 3, true);
        }


        public static FortressJunglePieces.Entrance createPiece(List<StructurePiece> p_175881_0_, Random p_175881_1_, int p_175881_2_, int p_175881_3_, int p_175881_4_, Direction p_175881_5_, int p_175881_6_) {
            BlockBox mutableBoundingBox = BlockBox.rotated(p_175881_2_, p_175881_3_, p_175881_4_, -5, -3, 0, 13, 14, 13, p_175881_5_);
            return isAboveGround(mutableBoundingBox) && StructurePiece.getOverlappingPiece(p_175881_0_, mutableBoundingBox) == null ? new FortressJunglePieces.Entrance(p_175881_6_, p_175881_1_, mutableBoundingBox, p_175881_5_) : null;
        }


        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            this.fillWithRandomBlocks(world, boundingBox, 0, 3, 0, 12, 4, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 5, 0, 12, 13, 12, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 5, 0, 1, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 11, 5, 0, 12, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 2, 5, 11, 4, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 8, 5, 11, 10, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 5, 9, 11, 7, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 2, 5, 0, 4, 12, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 8, 5, 0, 10, 12, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 5, 9, 0, 7, 12, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 2, 11, 2, 10, 12, 10, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 5, 8, 0, 7, 8, 0, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState(), random), getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState(), random), false, random);
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.WEST, true).with(HorizontalConnectingBlock.EAST, true), random);
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.NORTH, true).with(HorizontalConnectingBlock.SOUTH, true), random);

            for (int i = 1; i <= 11; i += 2) {
                this.fillWithRandomBlocks(world, boundingBox, i, 10, 0, i, 11, 0, iblockstate, iblockstate, false, random);
                this.fillWithRandomBlocks(world, boundingBox, i, 10, 12, i, 11, 12, iblockstate, iblockstate, false, random);
                this.fillWithRandomBlocks(world, boundingBox, 0, 10, i, 0, 11, i, iblockstate1, iblockstate1, false, random);
                this.fillWithRandomBlocks(world, boundingBox, 12, 10, i, 12, 11, i, iblockstate1, iblockstate1, false, random);
                this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), i, 13, 0, boundingBox);
                this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), i, 13, 12, boundingBox);
                this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), 0, 13, i, boundingBox);
                this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), 12, 13, i, boundingBox);
                this.addBlock(world, iblockstate, i + 1, 13, 0, boundingBox);
                this.addBlock(world, iblockstate, i + 1, 13, 12, boundingBox);
                this.addBlock(world, iblockstate1, 0, 13, i + 1, boundingBox);
                this.addBlock(world, iblockstate1, 12, 13, i + 1, boundingBox);
            }

            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.NORTH, true).with(HorizontalConnectingBlock.EAST, true), random), 0, 13, 0, boundingBox);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.SOUTH, true).with(HorizontalConnectingBlock.EAST, true), random), 0, 13, 12, boundingBox);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.SOUTH, true).with(HorizontalConnectingBlock.WEST, true), random), 12, 13, 12, boundingBox);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.NORTH, true).with(HorizontalConnectingBlock.WEST, true), random), 12, 13, 0, boundingBox);

            for (int k = 3; k <= 9; k += 2) {
                this.fillWithRandomBlocks(world, boundingBox, 1, 7, k, 1, 8, k, iblockstate.with(HorizontalConnectingBlock.WEST, true), iblockstate.with(HorizontalConnectingBlock.WEST, true), false, random);
                this.fillWithRandomBlocks(world, boundingBox, 11, 7, k, 11, 8, k, iblockstate.with(HorizontalConnectingBlock.EAST, true), iblockstate.with(HorizontalConnectingBlock.EAST, true), false, random);
            }

            this.fillWithRandomBlocks(world, boundingBox, 4, 2, 0, 8, 2, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 4, 12, 2, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 0, 0, 8, 1, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 0, 9, 8, 1, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 0, 4, 3, 1, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 9, 0, 4, 12, 1, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

            for (int l = 4; l <= 8; ++l) {
                for (int j = 0; j <= 2; ++j) {
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), l, -1, j, boundingBox, random);
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), l, -1, 12 - j, boundingBox, random);
                }
            }

            for (int i1 = 0; i1 <= 2; ++i1) {
                for (int j1 = 4; j1 <= 8; ++j1) {
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i1, -1, j1, boundingBox, random);
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), 12 - i1, -1, j1, boundingBox, random);
                }
            }

            this.fillWithRandomBlocks(world, boundingBox, 5, 5, 5, 7, 5, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 6, 1, 6, 6, 4, 6, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), 6, 0, 6, boundingBox);
            this.addBlock(world, getStoneVariantBlockState(Blocks.LAVA.getDefaultState(), random), 6, 5, 6, boundingBox);
            BlockPos blockpos = new BlockPos(this.applyXTransform(6, 6), this.applyYTransform(5), this.applyZTransform(6, 6));

            if (RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.lootChests) {
                this.addChest(world, boundingBox, random, 6, 5, 8, JF_CENTER_CHEST_RL);
            }

            if (boundingBox.contains(blockpos)) {
                world.getFluidTickScheduler().schedule(blockpos, Fluids.LAVA, 0);
            }

            this.fillWithWater(world, boundingBox, 0, 0, 0, 12, 13, 12);
            return true;
        }
    }

    public static class MushroomRoom extends FortressJunglePieces.Piece {
        public MushroomRoom(int p_i50264_1_, BlockBox p_i50264_2_, Direction p_i50264_3_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_MUSHROOM_ROOM, p_i50264_1_);
            this.setOrientation(p_i50264_3_);
            this.boundingBox = p_i50264_2_;
        }


        public MushroomRoom(StructureManager p_i50265_1_, CompoundTag p_i50265_2_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_MUSHROOM_ROOM, p_i50265_2_);
        }


        @Override
        public void fillOpenings(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentNormal((FortressJunglePieces.Start) componentIn, listIn, rand, 5, 3, true);
            this.getNextComponentNormal((FortressJunglePieces.Start) componentIn, listIn, rand, 5, 11, true);
        }


        public static FortressJunglePieces.MushroomRoom createPiece(List<StructurePiece> p_175875_0_, Random p_175875_1_, int p_175875_2_, int p_175875_3_, int p_175875_4_, Direction p_175875_5_, int p_175875_6_) {
            BlockBox mutableBoundingBox = BlockBox.rotated(p_175875_2_, p_175875_3_, p_175875_4_, -5, -3, 0, 13, 14, 13, p_175875_5_);
            return isAboveGround(mutableBoundingBox) && StructurePiece.getOverlappingPiece(p_175875_0_, mutableBoundingBox) == null ? new FortressJunglePieces.MushroomRoom(p_175875_6_, mutableBoundingBox, p_175875_5_) : null;
        }


        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            this.fillWithRandomBlocks(world, boundingBox, 0, 3, 0, 12, 4, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 5, 0, 12, 13, 12, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 5, 0, 1, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 11, 5, 0, 12, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 2, 5, 11, 4, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 8, 5, 11, 10, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 5, 9, 11, 7, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 2, 5, 0, 4, 12, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 8, 5, 0, 10, 12, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 5, 9, 0, 7, 12, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 2, 11, 2, 10, 12, 10, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.WEST, true).with(HorizontalConnectingBlock.EAST, true), random);
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.NORTH, true).with(HorizontalConnectingBlock.SOUTH, true), random);
            BlockState iblockstate2 = iblockstate1.with(HorizontalConnectingBlock.WEST, true);
            BlockState iblockstate3 = iblockstate1.with(HorizontalConnectingBlock.EAST, true);

            for (int i = 1; i <= 11; i += 2) {
                this.fillWithRandomBlocks(world, boundingBox, i, 10, 0, i, 11, 0, iblockstate, iblockstate, false, random);
                this.fillWithRandomBlocks(world, boundingBox, i, 10, 12, i, 11, 12, iblockstate, iblockstate, false, random);
                this.fillWithRandomBlocks(world, boundingBox, 0, 10, i, 0, 11, i, iblockstate1, iblockstate1, false, random);
                this.fillWithRandomBlocks(world, boundingBox, 12, 10, i, 12, 11, i, iblockstate1, iblockstate1, false, random);
                this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), i, 13, 0, boundingBox);
                this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), i, 13, 12, boundingBox);
                this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), 0, 13, i, boundingBox);
                this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), 12, 13, i, boundingBox);
                this.addBlock(world, iblockstate, i + 1, 13, 0, boundingBox);
                this.addBlock(world, iblockstate, i + 1, 13, 12, boundingBox);
                this.addBlock(world, iblockstate1, 0, 13, i + 1, boundingBox);
                this.addBlock(world, iblockstate1, 12, 13, i + 1, boundingBox);
            }

            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.NORTH, true).with(HorizontalConnectingBlock.EAST, true), random), 0, 13, 0, boundingBox);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.SOUTH, true).with(HorizontalConnectingBlock.EAST, true), random), 0, 13, 12, boundingBox);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.SOUTH, true).with(HorizontalConnectingBlock.WEST, true), random), 12, 13, 12, boundingBox);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.NORTH, true).with(HorizontalConnectingBlock.WEST, true), random), 12, 13, 0, boundingBox);

            for (int j1 = 3; j1 <= 9; j1 += 2) {
                this.fillWithRandomBlocks(world, boundingBox, 1, 7, j1, 1, 8, j1, iblockstate2, iblockstate2, false, random);
                this.fillWithRandomBlocks(world, boundingBox, 11, 7, j1, 11, 8, j1, iblockstate3, iblockstate3, false, random);
            }

            BlockState iblockstate4 = getStoneVariantBlockState(Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH), random);

            for (int j = 0; j <= 6; ++j) {
                int k = j + 4;

                for (int l = 5; l <= 7; ++l) {
                    this.addBlock(world, iblockstate4, l, 5 + j, k, boundingBox);
                }

                if (k >= 5 && k <= 8) {
                    this.fillWithRandomBlocks(world, boundingBox, 5, 5, k, 7, j + 4, k, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
                } else if (k >= 9 && k <= 10) {
                    this.fillWithRandomBlocks(world, boundingBox, 5, 8, k, 7, j + 4, k, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
                }

                if (j >= 1) {
                    this.fillWithRandomBlocks(world, boundingBox, 5, 6 + j, k, 7, 9 + j, k, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
                }
            }

            for (int k1 = 5; k1 <= 7; ++k1) {
                this.addBlock(world, iblockstate4, k1, 12, 11, boundingBox);
            }

            this.fillWithRandomBlocks(world, boundingBox, 5, 6, 7, 5, 7, 7, iblockstate, iblockstate, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 7, 6, 7, 7, 7, 7, iblockstate, iblockstate, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 5, 13, 12, 7, 13, 12, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 2, 5, 2, 3, 5, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 2, 5, 9, 3, 5, 10, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 2, 5, 4, 2, 5, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 9, 5, 2, 10, 5, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 9, 5, 9, 10, 5, 10, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 10, 5, 4, 10, 5, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            BlockState iblockstate5 = iblockstate4.with(StairsBlock.FACING, Direction.EAST);
            BlockState iblockstate6 = iblockstate4.with(StairsBlock.FACING, Direction.WEST);
            this.addBlock(world, iblockstate6, 4, 5, 2, boundingBox);
            this.addBlock(world, iblockstate6, 4, 5, 3, boundingBox);
            this.addBlock(world, iblockstate6, 4, 5, 9, boundingBox);
            this.addBlock(world, iblockstate6, 4, 5, 10, boundingBox);
            this.addBlock(world, iblockstate5, 8, 5, 2, boundingBox);
            this.addBlock(world, iblockstate5, 8, 5, 3, boundingBox);
            this.addBlock(world, iblockstate5, 8, 5, 9, boundingBox);
            this.addBlock(world, iblockstate5, 8, 5, 10, boundingBox);

            this.fillWithRandomBlocks(world, boundingBox, 3, 4, 4, 4, 4, 8, Blocks.SOUL_SAND.getDefaultState(), Blocks.SOUL_SAND.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 8, 4, 4, 9, 4, 8, Blocks.SOUL_SAND.getDefaultState(), Blocks.SOUL_SAND.getDefaultState(), false, random);

            if(this.applyYTransform(5) >= world.toServerWorld().getSeaLevel()){
                this.fillWithRandomBlocks(world, boundingBox, 3, 5, 4, 4, 5, 8, Blocks.NETHER_WART.getDefaultState(), Blocks.NETHER_WART.getDefaultState(), false, random);
                this.fillWithRandomBlocks(world, boundingBox, 8, 5, 4, 9, 5, 8, Blocks.NETHER_WART.getDefaultState(), Blocks.NETHER_WART.getDefaultState(), false, random);
            }

            this.fillWithRandomBlocks(world, boundingBox, 4, 2, 0, 8, 2, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 4, 12, 2, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 0, 0, 8, 1, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 0, 9, 8, 1, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 0, 4, 3, 1, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 9, 0, 4, 12, 1, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

            for (int l1 = 4; l1 <= 8; ++l1) {
                for (int i1 = 0; i1 <= 2; ++i1) {
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), l1, -1, i1, boundingBox, random);
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), l1, -1, 12 - i1, boundingBox, random);
                }
            }

            for (int i2 = 0; i2 <= 2; ++i2) {
                for (int j2 = 4; j2 <= 8; ++j2) {
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i2, -1, j2, boundingBox, random);
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), 12 - i2, -1, j2, boundingBox, random);
                }
            }

            this.fillWithWater(world, boundingBox, 0, 0, 0, 12, 13, 12);
            return true;
        }
    }

    abstract static class Piece extends StructurePiece {
        protected Piece(StructurePieceType p_i50260_1_, int p_i50260_2_) {
            super(p_i50260_1_, p_i50260_2_);
        }


        public Piece(StructurePieceType p_i50261_1_, CompoundTag p_i50261_2_) {
            super(p_i50261_1_, p_i50261_2_);
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag tagCompound) {
        }


        private int getTotalWeight(List<FortressJunglePieces.PieceWeight> p_74960_1_) {
            boolean flag = false;
            int i = 0;

            for (FortressJunglePieces.PieceWeight structurenetherbridgepieces$pieceweight : p_74960_1_) {
                if (structurenetherbridgepieces$pieceweight.maxPlaceCount > 0 && structurenetherbridgepieces$pieceweight.placeCount < structurenetherbridgepieces$pieceweight.maxPlaceCount) {
                    flag = true;
                }

                i += structurenetherbridgepieces$pieceweight.weight;
            }

            return flag ? i : -1;
        }


        private FortressJunglePieces.Piece generatePiece(FortressJunglePieces.Start startPiece, List<FortressJunglePieces.PieceWeight> pieceWeights, List<StructurePiece> structurePieces, Random random, int x, int y, int z, Direction direction, int depth) {
            int i = this.getTotalWeight(pieceWeights);
            boolean flag = i > 0 && depth <= 30;
            int j = 0;

            while (j < 5 && flag) {
                ++j;
                int k = random.nextInt(i);

                for (FortressJunglePieces.PieceWeight structurenetherbridgepieces$pieceweight : pieceWeights) {
                    k -= structurenetherbridgepieces$pieceweight.weight;

                    if (k < 0) {
                        if (!structurenetherbridgepieces$pieceweight.doPlace(depth) || structurenetherbridgepieces$pieceweight == startPiece.fortressPieceWeight && !structurenetherbridgepieces$pieceweight.allowInRow) {
                            break;
                        }

                        FortressJunglePieces.Piece structurenetherbridgepieces$piece = FortressJunglePieces.findAndCreateBridgePieceFactory(structurenetherbridgepieces$pieceweight, structurePieces, random, x, y, z, direction, depth);

                        if (structurenetherbridgepieces$piece != null) {
                            ++structurenetherbridgepieces$pieceweight.placeCount;
                            startPiece.fortressPieceWeight = structurenetherbridgepieces$pieceweight;

                            if (!structurenetherbridgepieces$pieceweight.isValid()) {
                                pieceWeights.remove(structurenetherbridgepieces$pieceweight);
                            }

                            return structurenetherbridgepieces$piece;
                        }
                    }
                }
            }

            return FortressJunglePieces.End.createPiece(structurePieces, random, x, y, z, direction, depth);
        }


        private StructurePiece generateAndAddPiece(FortressJunglePieces.Start startPiece, List<StructurePiece> structurePieces, Random random, int x, int y, int z, Direction direction, int depth, boolean useSecondaryList) {
            if (Math.abs(x - startPiece.getBoundingBox().minX) <= 112 && Math.abs(z - startPiece.getBoundingBox().minZ) <= 112) {
                List<FortressJunglePieces.PieceWeight> list = startPiece.primaryWeights;

                if (useSecondaryList) {
                    list = startPiece.secondaryWeights;
                }

                StructurePiece StructurePiece = this.generatePiece(startPiece, list, structurePieces, random, x, y, z, direction, depth + 1);

                if (StructurePiece != null) {
                    structurePieces.add(StructurePiece);
                    startPiece.pendingChildren.add(StructurePiece);
                }

                return StructurePiece;
            } else {
                return FortressJunglePieces.End.createPiece(structurePieces, random, x, y, z, direction, depth);
            }
        }


        protected StructurePiece getNextComponentNormal(FortressJunglePieces.Start p_74963_1_, List<StructurePiece> p_74963_2_, Random p_74963_3_, int p_74963_4_, int p_74963_5_, boolean p_74963_6_) {
            Direction enumfacing = this.getFacing();

            if (enumfacing != null) {
                switch (enumfacing) {
                    case NORTH:
                        return this.generateAndAddPiece(p_74963_1_, p_74963_2_, p_74963_3_, this.boundingBox.minX + p_74963_4_, this.boundingBox.minY + p_74963_5_, this.boundingBox.minZ - 1, enumfacing, this.getChainLength(), p_74963_6_);

                    case SOUTH:
                        return this.generateAndAddPiece(p_74963_1_, p_74963_2_, p_74963_3_, this.boundingBox.minX + p_74963_4_, this.boundingBox.minY + p_74963_5_, this.boundingBox.maxZ + 1, enumfacing, this.getChainLength(), p_74963_6_);

                    case WEST:
                        return this.generateAndAddPiece(p_74963_1_, p_74963_2_, p_74963_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74963_5_, this.boundingBox.minZ + p_74963_4_, enumfacing, this.getChainLength(), p_74963_6_);

                    case EAST:
                        return this.generateAndAddPiece(p_74963_1_, p_74963_2_, p_74963_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74963_5_, this.boundingBox.minZ + p_74963_4_, enumfacing, this.getChainLength(), p_74963_6_);

                    default:
                        break;
                }
            }

            return null;
        }


        protected StructurePiece getNextComponentX(FortressJunglePieces.Start start, List<StructurePiece> structurePieces, Random random, int x, int z, boolean useSecondaryList) {
            Direction enumfacing = this.getFacing();

            if (enumfacing != null) {
                switch (enumfacing) {
                    case NORTH:
                    case SOUTH:
                        return this.generateAndAddPiece(start, structurePieces, random, this.boundingBox.minX - 1, this.boundingBox.minY + x, this.boundingBox.minZ + z, Direction.WEST, this.getChainLength(), useSecondaryList);

                    case WEST:
                    case EAST:
                        return this.generateAndAddPiece(start, structurePieces, random, this.boundingBox.minX + z, this.boundingBox.minY + x, this.boundingBox.minZ - 1, Direction.NORTH, this.getChainLength(), useSecondaryList);

                    default:
                        break;
                }
            }

            return null;
        }


        protected StructurePiece getNextComponentZ(FortressJunglePieces.Start start, List<StructurePiece> structurePieces, Random random, int x, int z, boolean useSecondaryList) {
            Direction enumfacing = this.getFacing();

            if (enumfacing != null) {
                switch (enumfacing) {
                    case NORTH:
                    case SOUTH:
                        return this.generateAndAddPiece(start, structurePieces, random, this.boundingBox.maxX + 1, this.boundingBox.minY + x, this.boundingBox.minZ + z, Direction.EAST, this.getChainLength(), useSecondaryList);

                    case WEST:
                    case EAST:
                        return this.generateAndAddPiece(start, structurePieces, random, this.boundingBox.minX + z, this.boundingBox.minY + x, this.boundingBox.maxZ + 1, Direction.SOUTH, this.getChainLength(), useSecondaryList);

                    default:
                        break;
                }
            }

            return null;
        }


        protected static boolean isAboveGround(BlockBox p_74964_0_) {
            return p_74964_0_ != null && p_74964_0_.minY > 10;
        }


        protected BlockState getStoneVariantBlockState(BlockState blockstateIn, Random rand) {
            Block block = blockstateIn.getBlock();

            if (blockstateIn.isOf(Blocks.NETHER_BRICKS)) {
                BlockState newBlockState;
                float chance = rand.nextFloat();
                if (chance < 0.50f) {
                    // 50%
                    newBlockState = Blocks.MOSSY_STONE_BRICKS.getDefaultState();
                } else if (chance < 0.75f) {
                    // 25%
                    newBlockState = Blocks.CRACKED_STONE_BRICKS.getDefaultState();
                } else if (chance < 0.95f) {
                    // 20%
                    newBlockState = Blocks.STONE_BRICKS.getDefaultState();
                } else {
                    // 5%
                    newBlockState = Blocks.CHISELED_STONE_BRICKS.getDefaultState();
                }


                chance = rand.nextFloat();
                float silverfishThreshold = (float) (RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.silverfishSpawnrate / 100);
                if (chance < silverfishThreshold) {
                    newBlockState = INFESTED_STONE_LOOKUP.get(newBlockState);
                }


                return newBlockState;
            }
            else if (blockstateIn.isOf(Blocks.NETHER_BRICK_FENCE)) {
                return Blocks.IRON_BARS.getDefaultState().with(HorizontalConnectingBlock.NORTH, blockstateIn.get(HorizontalConnectingBlock.NORTH)).with(HorizontalConnectingBlock.EAST, blockstateIn.get(HorizontalConnectingBlock.EAST)).with(HorizontalConnectingBlock.SOUTH, blockstateIn.get(HorizontalConnectingBlock.SOUTH)).with(HorizontalConnectingBlock.WEST, blockstateIn.get(HorizontalConnectingBlock.WEST));
            }
            else if (blockstateIn.isOf(Blocks.NETHER_BRICK_STAIRS)) {

                float chance = rand.nextFloat();
                if (chance < 0.8f) {
                    // 80%
                    return Blocks.STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, blockstateIn.get(StairsBlock.FACING));
                } else {
                    // 20%
                    return Blocks.MOSSY_STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, blockstateIn.get(StairsBlock.FACING));
                }
            }
            else if (blockstateIn.isOf(Blocks.SOUL_SAND)) {
                Tag<Block> ORE_TAG = BlockTags.getTagGroup().getTagOrEmpty(JF_SOIL_TAG_RL);
                Collection<Block> allSoilBlocks = ORE_TAG.values();
                BlockState soilBlock = null;

                if (!allSoilBlocks.isEmpty())
                    soilBlock = ((Block) allSoilBlocks.toArray()[rand.nextInt(allSoilBlocks.size())]).getDefaultState();

                if (soilBlock != null && soilBlock.contains(Properties.MOISTURE))
                    soilBlock = soilBlock.with(Properties.MOISTURE, rand.nextInt(8));

                return soilBlock != null ? soilBlock : Blocks.COARSE_DIRT.getDefaultState();
            }
            else if (blockstateIn.isOf(Blocks.NETHER_WART)) {
                Tag<Block> ORE_TAG = BlockTags.getTagGroup().getTagOrEmpty(JF_PLANT_TAG_RL);
                Collection<Block> allPlantBlocks = ORE_TAG.values();
                float chance = rand.nextFloat();

                if (!allPlantBlocks.isEmpty() && chance < 0.4f) {
                    BlockState plantBlock = ((Block) allPlantBlocks.toArray()[rand.nextInt(allPlantBlocks.size())]).getDefaultState();

                    if (plantBlock.contains(Properties.AGE_25))
                        plantBlock = plantBlock.with(Properties.AGE_25, rand.nextInt(26));
                    else if (plantBlock.contains(Properties.AGE_15))
                        plantBlock = plantBlock.with(Properties.AGE_15, rand.nextInt(16));
                    else if (plantBlock.contains(Properties.AGE_7))
                        plantBlock = plantBlock.with(Properties.AGE_7, rand.nextInt(8));
                    else if (plantBlock.contains(Properties.AGE_5))
                        plantBlock = plantBlock.with(Properties.AGE_5, rand.nextInt(6));
                    else if (plantBlock.contains(Properties.AGE_3))
                        plantBlock = plantBlock.with(Properties.AGE_3, rand.nextInt(4));
                    else if (plantBlock.contains(Properties.AGE_2))
                        plantBlock = plantBlock.with(Properties.AGE_2, rand.nextInt(3));
                    else if (plantBlock.contains(Properties.AGE_1))
                        plantBlock = plantBlock.with(Properties.AGE_1, rand.nextInt(2));

                    return plantBlock;
                } else {
                    return Blocks.CAVE_AIR.getDefaultState();
                }
            } else if (blockstateIn.isOf(Blocks.LAVA)) {
                return Blocks.WATER.getDefaultState();
            }

            return block.getDefaultState();
        }


        /**
         * Fill the given area with the selected random blocks
         */
        protected void fillWithRandomBlocks(StructureWorldAccess world, BlockBox boundingboxIn, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, BlockState boundaryBlockState, BlockState insideBlockState, boolean existingOnly, Random rand) {
            for (int y = yMin; y <= yMax; ++y) {
                for (int x = xMin; x <= xMax; ++x) {
                    for (int z = zMin; z <= zMax; ++z) {
                        if (!existingOnly || this.getBlockAt(world, x, y, z, boundingboxIn).getMaterial() != Material.AIR) {
                            if (y != yMin && y != yMax && x != xMin && x != xMax && z != zMin && z != zMax) {
                                this.addBlock(world, insideBlockState, x, y, z, boundingboxIn);
                            } else {
                                this.addBlock(world, getStoneVariantBlockState(boundaryBlockState.getBlock().getDefaultState(), rand), x, y, z, boundingboxIn);
                            }
                        }
                    }
                }
            }

        }

        protected void fillWithWater(StructureWorldAccess world, BlockBox boundingboxIn, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax) {
            BlockPos.Mutable blockPos;

            for (int y = yMin; y <= yMax; ++y) {
                for (int x = xMin; x <= xMax; ++x) {
                    for (int z = zMin; z <= zMax; ++z) {
                        blockPos = new BlockPos.Mutable(this.applyXTransform(x, z), this.applyYTransform(y), this.applyZTransform(x, z));

                        if (blockPos.getY() < world.getSeaLevel())
                        {
                            if(world.getBlockState(blockPos).getMaterial() == Material.AIR){
                                this.addBlock(world, Blocks.WATER.getDefaultState(), x, y, z, boundingboxIn);

                                if(world.getRandom().nextFloat() < 0.005f && !world.getBlockState(blockPos.up()).isOpaque()){
                                    DrownedEntity drowned = EntityType.DROWNED.create(world.toServerWorld());
                                    drowned.setPersistent();

                                    if(world.getRandom().nextFloat() < 0.4f){
                                        drowned.equipStack(EquipmentSlot.HEAD, world.getRandom().nextFloat() < 0.4f ? Items.IRON_HELMET.getDefaultStack() : Items.CHAINMAIL_HELMET.getDefaultStack());
                                    }
                                    if(world.getRandom().nextFloat() < 0.4f){
                                        drowned.equipStack(EquipmentSlot.CHEST, world.getRandom().nextFloat() < 0.4f ? Items.IRON_CHESTPLATE.getDefaultStack() : Items.CHAINMAIL_CHESTPLATE.getDefaultStack());
                                    }
                                    if(world.getRandom().nextFloat() < 0.4f){
                                        drowned.equipStack(EquipmentSlot.LEGS, world.getRandom().nextFloat() < 0.4f ? Items.IRON_LEGGINGS.getDefaultStack() : Items.CHAINMAIL_LEGGINGS.getDefaultStack());
                                    }
                                    if(world.getRandom().nextFloat() < 0.4f){
                                        drowned.equipStack(EquipmentSlot.FEET, world.getRandom().nextFloat() < 0.4f ? Items.IRON_BOOTS.getDefaultStack() : Items.CHAINMAIL_BOOTS.getDefaultStack());
                                    }

                                    drowned.refreshPositionAndAngles((double)blockPos.getX() + 0.5D, blockPos.getY(), (double)blockPos.getZ() + 0.5D, 0.0F, 0.0F);
                                    drowned.initialize(world, world.getLocalDifficulty(blockPos), SpawnReason.STRUCTURE, null, null);
                                    world.spawnEntityAndPassengers(drowned);
                                }
                            }
                            else if(world.getBlockState(blockPos).getProperties().contains(Properties.WATERLOGGED)){
                                world.setBlockState(blockPos, world.getBlockState(blockPos).with(Properties.WATERLOGGED, true),3);
                                world.getFluidTickScheduler().schedule(blockPos, Fluids.WATER, 0);
                            }
                        }
                    }
                }
            }
        }

        /**
         * Replaces air and liquid from given position downwards. Stops when hitting anything else than air or liquid
         */
        protected void replaceAirAndLiquidDownwardsRandomBlocks(StructureWorldAccess world, BlockState blockstateIn, int x, int y, int z, BlockBox boundingboxIn, Random rand) {
            int i = this.applyXTransform(x, z);
            int j = this.applyYTransform(y);
            int k = this.applyZTransform(x, z);
            if (boundingboxIn.contains(new BlockPos(i, j, k))) {
                while ((world.isAir(new BlockPos(i, j, k)) || world.getBlockState(new BlockPos(i, j, k)).getMaterial().isLiquid()) && j > 1) {
                    world.setBlockState(new BlockPos(i, j, k), getStoneVariantBlockState(blockstateIn.getBlock().getDefaultState(), rand), 2);
                    --j;
                }

            }
        }
    }

    static class PieceWeight {
        public Class<? extends FortressJunglePieces.Piece> weightClass;
        public final int weight;
        public int placeCount;
        public int maxPlaceCount;
        public boolean allowInRow;


        public PieceWeight(Class<? extends FortressJunglePieces.Piece> weightClass, int weight, int maximumCount, boolean allowMultipleInRow) {
            this.weightClass = weightClass;
            this.weight = weight;
            this.maxPlaceCount = maximumCount;
            this.allowInRow = allowMultipleInRow;
        }


        public PieceWeight(Class<? extends FortressJunglePieces.Piece> weightClass, int weight, int maximumCount) {
            this(weightClass, weight, maximumCount, false);
        }


        public boolean doPlace(int p_78822_1_) {
            return this.maxPlaceCount == 0 || this.placeCount < this.maxPlaceCount;
        }


        public boolean isValid() {
            return this.maxPlaceCount == 0 || this.placeCount < this.maxPlaceCount;
        }
    }

    public static class Stairs extends FortressJunglePieces.Piece {
        public Stairs(int p_i50255_1_, BlockBox p_i50255_2_, Direction p_i50255_3_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_STAIRS, p_i50255_1_);
            this.setOrientation(p_i50255_3_);
            this.boundingBox = p_i50255_2_;
        }


        public Stairs(StructureManager p_i50256_1_, CompoundTag p_i50256_2_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_STAIRS, p_i50256_2_);
        }


        @Override
        public void fillOpenings(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentZ((FortressJunglePieces.Start) componentIn, listIn, rand, 6, 2, false);
        }


        public static FortressJunglePieces.Stairs createPiece(List<StructurePiece> p_175872_0_, Random p_175872_1_, int p_175872_2_, int p_175872_3_, int p_175872_4_, int p_175872_5_, Direction p_175872_6_) {
            BlockBox mutableBoundingBox = BlockBox.rotated(p_175872_2_, p_175872_3_, p_175872_4_, -2, 0, 0, 7, 11, 7, p_175872_6_);
            return isAboveGround(mutableBoundingBox) && StructurePiece.getOverlappingPiece(p_175872_0_, mutableBoundingBox) == null ? new FortressJunglePieces.Stairs(p_175872_5_, mutableBoundingBox, p_175872_6_) : null;
        }


        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            this.fillWithRandomBlocks(world, boundingBox, 0, 0, 0, 6, 1, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 6, 10, 6, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 1, 8, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 5, 2, 0, 6, 8, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 1, 0, 8, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 6, 2, 1, 6, 8, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 2, 6, 5, 8, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.WEST, true).with(HorizontalConnectingBlock.EAST, true), random);
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.NORTH, true).with(HorizontalConnectingBlock.SOUTH, true), random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 3, 2, 0, 5, 4, iblockstate1, iblockstate1, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 6, 3, 2, 6, 5, 2, iblockstate1, iblockstate1, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 6, 3, 4, 6, 5, 4, iblockstate1, iblockstate1, false, random);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), 5, 2, 5, boundingBox);
            this.fillWithRandomBlocks(world, boundingBox, 4, 2, 5, 4, 3, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 3, 2, 5, 3, 4, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 2, 2, 5, 2, 5, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 2, 5, 1, 6, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 7, 1, 5, 7, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 6, 8, 2, 6, 8, 4, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 2, 6, 0, 4, 8, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 2, 5, 0, 4, 5, 0, iblockstate, iblockstate, false, random);

            for (int i = 0; i <= 6; ++i) {
                for (int j = 0; j <= 6; ++j) {
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, boundingBox, random);
                }
            }

            this.fillWithWater(world, boundingBox, 0, 0, 0, 6, 10, 6);
            return true;
        }
    }

    public static class Start extends FortressJunglePieces.Crossing3 {
        public FortressJunglePieces.PieceWeight fortressPieceWeight;
        public List<FortressJunglePieces.PieceWeight> primaryWeights;
        public List<FortressJunglePieces.PieceWeight> secondaryWeights;
        public List<StructurePiece> pendingChildren = Lists.newArrayList();


        public Start(Random random, int x, int z) {
            super(random, x, z);
            this.primaryWeights = Lists.newArrayList();

            for (FortressJunglePieces.PieceWeight structurenetherbridgepieces$pieceweight : FortressJunglePieces.PRIMARY_COMPONENTS) {
                structurenetherbridgepieces$pieceweight.placeCount = 0;
                this.primaryWeights.add(structurenetherbridgepieces$pieceweight);
            }

            this.secondaryWeights = Lists.newArrayList();

            for (FortressJunglePieces.PieceWeight structurenetherbridgepieces$pieceweight1 : FortressJunglePieces.SECONDARY_COMPONENTS) {
                structurenetherbridgepieces$pieceweight1.placeCount = 0;
                this.secondaryWeights.add(structurenetherbridgepieces$pieceweight1);
            }
        }


        public Start(StructureManager p_i50253_1_, CompoundTag p_i50253_2_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_START, p_i50253_2_);
        }
    }

    public static class Straight extends FortressJunglePieces.Piece {
        public Straight(int p_i45620_1_, Random p_i45620_2_, BlockBox p_i45620_3_, Direction p_i45620_4_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_STRAIGHT, p_i45620_1_);
            this.setOrientation(p_i45620_4_);
            this.boundingBox = p_i45620_3_;
        }


        public Straight(StructureManager p_i50283_1_, CompoundTag p_i50283_2_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_STRAIGHT, p_i50283_2_);
        }


        @Override
        public void fillOpenings(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentNormal((FortressJunglePieces.Start) componentIn, listIn, rand, 1, 3, false);
        }


        public static FortressJunglePieces.Straight createPiece(List<StructurePiece> p_175882_0_, Random p_175882_1_, int p_175882_2_, int p_175882_3_, int p_175882_4_, Direction p_175882_5_, int p_175882_6_) {
            BlockBox mutableBoundingBox = BlockBox.rotated(p_175882_2_, p_175882_3_, p_175882_4_, -1, -3, 0, 5, 10, 19, p_175882_5_);
            return isAboveGround(mutableBoundingBox) && StructurePiece.getOverlappingPiece(p_175882_0_, mutableBoundingBox) == null ? new FortressJunglePieces.Straight(p_175882_6_, p_175882_1_, mutableBoundingBox, p_175882_5_) : null;
        }


        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            this.fillWithRandomBlocks(world, boundingBox, 0, 3, 0, 4, 4, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 5, 0, 3, 7, 18, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 5, 0, 0, 5, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 5, 0, 4, 5, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 4, 2, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 13, 4, 2, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 0, 0, 4, 1, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 0, 15, 4, 1, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

            for (int i = 0; i <= 4; ++i) {
                for (int j = 0; j <= 2; ++j) {
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, boundingBox, random);
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, 18 - j, boundingBox, random);
                }
            }
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.NORTH, true).with(HorizontalConnectingBlock.SOUTH, true), random);
            BlockState iblockstate2 = iblockstate1.with(HorizontalConnectingBlock.EAST, true);
            BlockState iblockstate3 = iblockstate1.with(HorizontalConnectingBlock.WEST, true);

            this.fillWithRandomBlocks(world, boundingBox, 0, 1, 1, 0, 4, 1, iblockstate2, iblockstate2, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 3, 4, 0, 4, 4, iblockstate2, iblockstate2, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 3, 14, 0, 4, 14, iblockstate2, iblockstate2, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 1, 17, 0, 4, 17, iblockstate2, iblockstate2, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 1, 1, 4, 4, 1, iblockstate3, iblockstate3, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 3, 4, 4, 4, 4, iblockstate3, iblockstate3, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 3, 14, 4, 4, 14, iblockstate3, iblockstate3, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 1, 17, 4, 4, 17, iblockstate3, iblockstate3, false, random);

            this.fillWithWater(world, boundingBox, 0, 0, 0, 4, 7, 18);
            return true;
        }
    }

    public static class Throne extends FortressJunglePieces.Piece {
        private boolean hasSpawner;


        public Throne(int p_i50262_1_, Random rand, BlockBox p_i50262_2_, Direction p_i50262_3_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_THRONE, p_i50262_1_);
            this.setOrientation(p_i50262_3_);
            this.boundingBox = p_i50262_2_;
        }


        public Throne(StructureManager p_i50263_1_, CompoundTag p_i50263_2_) {
            super(RSStructurePieces.JUNGLE_FORTRESS_THRONE, p_i50263_2_);
            this.hasSpawner = p_i50263_2_.getBoolean("Mob");
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag tagCompound) {
            super.toNbt(tagCompound);
            tagCompound.putBoolean("Mob", this.hasSpawner);
        }


        public static FortressJunglePieces.Throne createPiece(List<StructurePiece> p_175874_0_, Random p_175874_1_, int p_175874_2_, int p_175874_3_, int p_175874_4_, int p_175874_5_, Direction p_175874_6_) {
            BlockBox mutableBoundingBox = BlockBox.rotated(p_175874_2_, p_175874_3_, p_175874_4_, -2, 0, 0, 7, 8, 9, p_175874_6_);
            return isAboveGround(mutableBoundingBox) && StructurePiece.getOverlappingPiece(p_175874_0_, mutableBoundingBox) == null ? new FortressJunglePieces.Throne(p_175874_5_, p_175874_1_, mutableBoundingBox, p_175874_6_) : null;
        }


        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 6, 7, 7, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 0, 0, 5, 1, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 2, 1, 5, 2, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 3, 2, 5, 3, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 4, 3, 5, 4, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 2, 0, 1, 4, 2, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 5, 2, 0, 5, 4, 2, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 5, 2, 1, 5, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 5, 5, 2, 5, 5, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 5, 3, 0, 5, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 6, 5, 3, 6, 5, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 5, 8, 5, 5, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.WEST, true).with(HorizontalConnectingBlock.EAST, true), random);
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.NORTH, true).with(HorizontalConnectingBlock.SOUTH, true), random);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.WEST, true), random), 1, 6, 3, boundingBox);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.EAST, true), random), 5, 6, 3, boundingBox);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.EAST, true).with(HorizontalConnectingBlock.NORTH, true), random), 0, 6, 3, boundingBox);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.WEST, true).with(HorizontalConnectingBlock.NORTH, true), random), 6, 6, 3, boundingBox);
            this.fillWithRandomBlocks(world, boundingBox, 0, 6, 4, 0, 6, 7, iblockstate1, iblockstate1, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 6, 6, 4, 6, 6, 7, iblockstate1, iblockstate1, false, random);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.EAST, true).with(HorizontalConnectingBlock.SOUTH, true), random), 0, 6, 8, boundingBox);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.WEST, true).with(HorizontalConnectingBlock.SOUTH, true), random), 6, 6, 8, boundingBox);
            this.fillWithRandomBlocks(world, boundingBox, 1, 6, 8, 5, 6, 8, iblockstate, iblockstate, false, random);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.EAST, true), random), 1, 7, 8, boundingBox);
            this.fillWithRandomBlocks(world, boundingBox, 2, 7, 8, 4, 7, 8, iblockstate, iblockstate, false, random);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.WEST, true), random), 5, 7, 8, boundingBox);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.EAST, true), random), 2, 8, 8, boundingBox);
            this.addBlock(world, iblockstate, 3, 8, 8, boundingBox);
            this.addBlock(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(HorizontalConnectingBlock.WEST, true), random), 4, 8, 8, boundingBox);

            if (!this.hasSpawner) {
                BlockPos blockpos = new BlockPos(this.applyXTransform(3, 5), this.applyYTransform(5), this.applyZTransform(3, 5));

                if (boundingBox.contains(blockpos)) {
                    this.hasSpawner = true;

                    // mob spawner
                    world.setBlockState(blockpos.down(2), Blocks.SPAWNER.getDefaultState(), 2);
                    BlockEntity tileentity2 = world.getBlockEntity(blockpos.down(2));

                    if (tileentity2 instanceof MobSpawnerBlockEntity) {
                        ((MobSpawnerBlockEntity) tileentity2).getLogic()
                                    .setEntityId(RepurposedStructures.mobSpawnerManager.getSpawnerMob(SPAWNER_ID, random));
                    }
                }
            }

            for (int i = 0; i <= 6; ++i) {
                for (int j = 0; j <= 6; ++j) {
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, boundingBox, random);
                }
            }

            if (RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.lootChests) {
                this.addChest(world, boundingBox, random, 3, 5, 7, FortressJunglePieces.JF_SHRINE_CHEST_RL);
            }

            this.fillWithWater(world, boundingBox, 0, 0, 0, 6, 7, 8);
            return true;
        }
    }
}
