package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.*;


public class FortressJunglePieces {
    private static final ResourceLocation JF_HALLWAY_CHEST_RL = new ResourceLocation(RepurposedStructures.MODID + ":chests/fortress_jungle_hallway_chest");
    private static final ResourceLocation JF_SHRINE_CHEST_RL = new ResourceLocation(RepurposedStructures.MODID + ":chests/fortress_jungle_shrine_chest");
    private static final ResourceLocation JF_CENTER_CHEST_RL = new ResourceLocation(RepurposedStructures.MODID + ":chests/fortress_jungle_center_chest");
    private static final ResourceLocation JF_PLANT_TAG_RL = new ResourceLocation("repurposed_structures:jungle_fortress_staircase_plants");
    private static final ResourceLocation JF_SOIL_TAG_RL = new ResourceLocation("repurposed_structures:jungle_fortress_staircase_soils");
    private static final ResourceLocation SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID + ":fortress_jungle");

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


    private static FortressJunglePieces.Piece findAndCreateBridgePieceFactory(FortressJunglePieces.PieceWeight p_175887_0_, List<StructurePiece> structurePieceList, Random random, int x, int y, int z, Direction direction, int depth) {
        Class<? extends FortressJunglePieces.Piece> oclass = p_175887_0_.weightClass;
        FortressJunglePieces.Piece structurenetherbridgepieces$piece = null;

        if (oclass == FortressJunglePieces.Straight.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Straight.createPiece(structurePieceList, random, x, y, z, direction, depth);
        } else if (oclass == FortressJunglePieces.Crossing3.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Crossing3.createPiece(structurePieceList, random, x, y, z, direction, depth);
        } else if (oclass == FortressJunglePieces.Crossing.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Crossing.createPiece(structurePieceList, random, x, y, z, direction, depth);
        } else if (oclass == FortressJunglePieces.Stairs.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Stairs.createPiece(structurePieceList, random, x, y, z, depth, direction);
        } else if (oclass == FortressJunglePieces.Throne.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Throne.createPiece(structurePieceList, random, x, y, z, depth, direction);
        } else if (oclass == FortressJunglePieces.Entrance.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Entrance.createPiece(structurePieceList, random, x, y, z, direction, depth);
        } else if (oclass == FortressJunglePieces.Corridor5.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Corridor5.createPiece(structurePieceList, random, x, y, z, direction, depth);
        } else if (oclass == FortressJunglePieces.Corridor2.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Corridor2.createPiece(structurePieceList, random, x, y, z, direction, depth);
        } else if (oclass == FortressJunglePieces.Corridor.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Corridor.createPiece(structurePieceList, random, x, y, z, direction, depth);
        } else if (oclass == FortressJunglePieces.Corridor3.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Corridor3.createPiece(structurePieceList, random, x, y, z, direction, depth);
        } else if (oclass == FortressJunglePieces.Corridor4.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Corridor4.createPiece(structurePieceList, random, x, y, z, direction, depth);
        } else if (oclass == FortressJunglePieces.Crossing2.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.Crossing2.createPiece(structurePieceList, random, x, y, z, direction, depth);
        } else if (oclass == FortressJunglePieces.MushroomRoom.class) {
            structurenetherbridgepieces$piece = FortressJunglePieces.MushroomRoom.createPiece(structurePieceList, random, x, y, z, direction, depth);
        }

        return structurenetherbridgepieces$piece;
    }

    public static class Corridor extends FortressJunglePieces.Piece {

        public Corridor(int p_i45615_1_, Random rand, MutableBoundingBox p_i45615_3_, Direction p_i45615_4_) {
            super(StructurePieces.JUNGLE_FORTRESS_CORRIDOR_1, p_i45615_1_);
            this.setCoordBaseMode(p_i45615_4_);
            this.boundingBox = p_i45615_3_;
        }


        public Corridor(TemplateManager p_i50272_1_, CompoundNBT p_i50272_2_) {
            super(StructurePieces.JUNGLE_FORTRESS_CORRIDOR_1, p_i50272_2_);
        }


        @Override
        public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentX((FortressJunglePieces.Start) componentIn, listIn, rand, 0, 1, true);
        }


        @SuppressWarnings("ConstantConditions")
        public static FortressJunglePieces.Corridor createPiece(List<StructurePiece> structurePieceList, Random random, int x, int y, int z, Direction direction, int depth) {
            MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(x, y, z, -1, 0, 0, 5, 7, 5, direction);
            return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(structurePieceList, mutableBoundingBox) == null ? new FortressJunglePieces.Corridor(depth, random, mutableBoundingBox, direction) : null;
        }


        @Override
        public boolean generate(ISeedReader world, StructureManager structureAccessor, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            this.fillWithRandomBlocks(world, boundingBox, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 4, 5, 4, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, true).with(FourWayBlock.EAST, true), random);
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, true).with(FourWayBlock.SOUTH, true), random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 2, 0, 4, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 3, 1, 4, 4, 1, iblockstate1, iblockstate1, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 3, 3, 4, 4, 3, iblockstate1, iblockstate1, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 0, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 4, 3, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 3, 4, 1, 4, 4, iblockstate, iblockstate, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 3, 3, 4, 3, 4, 4, iblockstate, iblockstate, false, random);

            if (RepurposedStructures.RSMainConfig.lootChestsJF.get() && random.nextInt(9) == 0 && boundingBox.isVecInside(new BlockPos(this.getXWithOffset(3, 3), this.getYWithOffset(2), this.getZWithOffset(3, 3)))) {
                this.generateChest(world, boundingBox, random, 3, 2, 3, JF_HALLWAY_CHEST_RL);
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

        public Corridor2(int p_i45613_1_, Random rand, MutableBoundingBox p_i45613_3_, Direction p_i45613_4_) {
            super(StructurePieces.JUNGLE_FORTRESS_CORRIDOR_2, p_i45613_1_);
            this.setCoordBaseMode(p_i45613_4_);
            this.boundingBox = p_i45613_3_;
        }


        public Corridor2(TemplateManager p_i50266_1_, CompoundNBT p_i50266_2_) {
            super(StructurePieces.JUNGLE_FORTRESS_CORRIDOR_2, p_i50266_2_);
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void readAdditional(CompoundNBT tagCompound) {
            super.readAdditional(tagCompound);
        }


        @Override
        public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentZ((FortressJunglePieces.Start) componentIn, listIn, rand, 0, 1, true);
        }


        @SuppressWarnings("ConstantConditions")
        public static FortressJunglePieces.Corridor2 createPiece(List<StructurePiece> structurePieceList, Random random, int x, int y, int z, Direction direction, int depth) {
            MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(x, y, z, -1, 0, 0, 5, 7, 5, direction);
            return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(structurePieceList, mutableBoundingBox) == null ? new FortressJunglePieces.Corridor2(depth, random, mutableBoundingBox, direction) : null;
        }


        @Override
        public boolean generate(ISeedReader world, StructureManager structureAccessor, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            this.fillWithRandomBlocks(world, boundingBox, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 4, 5, 4, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState(), random).with(FourWayBlock.WEST, true).with(FourWayBlock.EAST, true);
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState(), random).with(FourWayBlock.NORTH, true).with(FourWayBlock.SOUTH, true);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 0, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 3, 1, 0, 4, 1, iblockstate1, iblockstate1, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 3, 3, 0, 4, 3, iblockstate1, iblockstate1, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 4, 2, 0, 4, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 2, 4, 4, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 3, 4, 1, 4, 4, iblockstate, iblockstate, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 3, 3, 4, 3, 4, 4, iblockstate, iblockstate, false, random);

            if (RepurposedStructures.RSMainConfig.lootChestsJF.get() && random.nextInt(9) == 0 && boundingBox.isVecInside(new BlockPos(this.getXWithOffset(1, 3), this.getYWithOffset(2), this.getZWithOffset(1, 3)))) {
                this.generateChest(world, boundingBox, random, 1, 2, 3, JF_HALLWAY_CHEST_RL);
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
        public Corridor3(int p_i50280_1_, MutableBoundingBox p_i50280_2_, Direction p_i50280_3_) {
            super(StructurePieces.JUNGLE_FORTRESS_CORRIDOR_3, p_i50280_1_);
            this.setCoordBaseMode(p_i50280_3_);
            this.boundingBox = p_i50280_2_;
        }


        public Corridor3(TemplateManager p_i50281_1_, CompoundNBT p_i50281_2_) {
            super(StructurePieces.JUNGLE_FORTRESS_CORRIDOR_3, p_i50281_2_);
        }


        @Override
        public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentNormal((FortressJunglePieces.Start) componentIn, listIn, rand, 1, 0, true);
        }


        @SuppressWarnings("ConstantConditions")
        public static FortressJunglePieces.Corridor3 createPiece(List<StructurePiece> structurePieceList, Random random, int x, int y, int z, Direction direction, int depth) {
            MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(x, y, z, -1, -7, 0, 5, 14, 10, direction);
            return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(structurePieceList, mutableBoundingBox) == null ? new FortressJunglePieces.Corridor3(depth, mutableBoundingBox, direction) : null;
        }


        @Override
        public boolean generate(ISeedReader world, StructureManager structureAccessor, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.SOUTH), random);
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, true).with(FourWayBlock.SOUTH, true), random);

            for (int i = 0; i <= 9; ++i) {
                int j = Math.max(1, 7 - i);
                int k = Math.min(Math.max(j + 5, 14 - i), 13);
                this.fillWithRandomBlocks(world, boundingBox, 0, 0, i, 4, j, i, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
                this.fillWithRandomBlocks(world, boundingBox, 1, j + 1, i, 3, k - 1, i, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);

                if (i <= 6) {
                    this.setBlockState(world, iblockstate, 1, j + 1, i, boundingBox);
                    this.setBlockState(world, iblockstate, 2, j + 1, i, boundingBox);
                    this.setBlockState(world, iblockstate, 3, j + 1, i, boundingBox);
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
        public Corridor4(int p_i50277_1_, MutableBoundingBox p_i50277_2_, Direction p_i50277_3_) {
            super(StructurePieces.JUNGLE_FORTRESS_CORRIDOR_4, p_i50277_1_);
            this.setCoordBaseMode(p_i50277_3_);
            this.boundingBox = p_i50277_2_;
        }


        public Corridor4(TemplateManager p_i50278_1_, CompoundNBT p_i50278_2_) {
            super(StructurePieces.JUNGLE_FORTRESS_CORRIDOR_4, p_i50278_2_);
        }


        @Override
        public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            int i = 1;
            Direction enumfacing = this.getCoordBaseMode();

            if (enumfacing == Direction.WEST || enumfacing == Direction.NORTH) {
                i = 5;
            }

            this.getNextComponentX((FortressJunglePieces.Start) componentIn, listIn, rand, 0, i, rand.nextInt(8) > 0);
            this.getNextComponentZ((FortressJunglePieces.Start) componentIn, listIn, rand, 0, i, rand.nextInt(8) > 0);
        }


        @SuppressWarnings("ConstantConditions")
        public static FortressJunglePieces.Corridor4 createPiece(List<StructurePiece> p_175880_0_, Random p_175880_1_, int p_175880_2_, int p_175880_3_, int p_175880_4_, Direction p_175880_5_, int p_175880_6_) {
            MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(p_175880_2_, p_175880_3_, p_175880_4_, -3, 0, 0, 9, 7, 9, p_175880_5_);
            return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(p_175880_0_, mutableBoundingBox) == null ? new FortressJunglePieces.Corridor4(p_175880_6_, mutableBoundingBox, p_175880_5_) : null;
        }


        @Override
        public boolean generate(ISeedReader world, StructureManager structureAccessor, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, true).with(FourWayBlock.SOUTH, true), random);
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, true).with(FourWayBlock.EAST, true), random);
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
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.EAST, true).with(FourWayBlock.SOUTH, true), random), 0, 3, 8, boundingBox);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, true).with(FourWayBlock.SOUTH, true), random), 8, 3, 8, boundingBox);
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
        public Corridor5(int p_i50268_1_, MutableBoundingBox p_i50268_2_, Direction p_i50268_3_) {
            super(StructurePieces.JUNGLE_FORTRESS_CORRIDOR_5, p_i50268_1_);
            this.setCoordBaseMode(p_i50268_3_);
            this.boundingBox = p_i50268_2_;
        }


        public Corridor5(TemplateManager p_i50269_1_, CompoundNBT p_i50269_2_) {
            super(StructurePieces.JUNGLE_FORTRESS_CORRIDOR_5, p_i50269_2_);
        }


        @Override
        public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentNormal((FortressJunglePieces.Start) componentIn, listIn, rand, 1, 0, true);
        }


        @SuppressWarnings("ConstantConditions")
        public static FortressJunglePieces.Corridor5 createPiece(List<StructurePiece> structurePieceList, Random random, int x, int y, int z, Direction direction, int depth) {
            MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(x, y, z, -1, 0, 0, 5, 7, 5, direction);
            return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(structurePieceList, mutableBoundingBox) == null ? new FortressJunglePieces.Corridor5(depth, mutableBoundingBox, direction) : null;
        }


        @Override
        public boolean generate(ISeedReader world, StructureManager structureAccessor, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            this.fillWithRandomBlocks(world, boundingBox, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 4, 5, 4, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, true).with(FourWayBlock.SOUTH, true), random);
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
        public Crossing(int p_i50258_1_, MutableBoundingBox p_i50258_2_, Direction p_i50258_3_) {
            super(StructurePieces.JUNGLE_FORTRESS_CROSSING_1, p_i50258_1_);
            this.setCoordBaseMode(p_i50258_3_);
            this.boundingBox = p_i50258_2_;
        }


        public Crossing(TemplateManager p_i50259_1_, CompoundNBT p_i50259_2_) {
            super(StructurePieces.JUNGLE_FORTRESS_CROSSING_1, p_i50259_2_);
        }


        @Override
        public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentNormal((FortressJunglePieces.Start) componentIn, listIn, rand, 2, 0, false);
            this.getNextComponentX((FortressJunglePieces.Start) componentIn, listIn, rand, 0, 2, false);
            this.getNextComponentZ((FortressJunglePieces.Start) componentIn, listIn, rand, 0, 2, false);
        }


        @SuppressWarnings("ConstantConditions")
        public static FortressJunglePieces.Crossing createPiece(List<StructurePiece> structurePieceList, Random random, int x, int y, int z, Direction direction, int depth) {
            MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(x, y, z, -2, 0, 0, 7, 9, 7, direction);
            return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(structurePieceList, mutableBoundingBox) == null ? new FortressJunglePieces.Crossing(depth, mutableBoundingBox, direction) : null;
        }


        @Override
        public boolean generate(ISeedReader world, StructureManager structureAccessor, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
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
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, true).with(FourWayBlock.EAST, true), random);
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, true).with(FourWayBlock.SOUTH, true), random);
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
        public Crossing2(int p_i50273_1_, MutableBoundingBox p_i50273_2_, Direction p_i50273_3_) {
            super(StructurePieces.JUNGLE_FORTRESS_CROSSING_2, p_i50273_1_);
            this.setCoordBaseMode(p_i50273_3_);
            this.boundingBox = p_i50273_2_;
        }


        public Crossing2(TemplateManager p_i50274_1_, CompoundNBT p_i50274_2_) {
            super(StructurePieces.JUNGLE_FORTRESS_CROSSING_2, p_i50274_2_);
        }


        @Override
        public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentNormal((FortressJunglePieces.Start) componentIn, listIn, rand, 1, 0, true);
            this.getNextComponentX((FortressJunglePieces.Start) componentIn, listIn, rand, 0, 1, true);
            this.getNextComponentZ((FortressJunglePieces.Start) componentIn, listIn, rand, 0, 1, true);
        }


        @SuppressWarnings("ConstantConditions")
        public static FortressJunglePieces.Crossing2 createPiece(List<StructurePiece> structurePieceList, Random random, int x, int y, int z, Direction direction, int depth) {
            MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(x, y, z, -1, 0, 0, 5, 7, 5, direction);
            return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(structurePieceList, mutableBoundingBox) == null ? new FortressJunglePieces.Crossing2(depth, mutableBoundingBox, direction) : null;
        }


        @Override
        public boolean generate(ISeedReader world, StructureManager structureAccessor, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
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

        protected Crossing3(IStructurePieceType p_i50287_1_, CompoundNBT p_i50287_2_) {
            super(p_i50287_1_, p_i50287_2_);
        }


        public Crossing3(TemplateManager p_i50288_1_, CompoundNBT p_i50288_2_) {
            this(StructurePieces.JUNGLE_FORTRESS_CROSSING_3, p_i50288_2_);
        }


        public Crossing3(int p_i50286_1_, MutableBoundingBox p_i50286_2_, Direction p_i50286_3_) {
            super(StructurePieces.JUNGLE_FORTRESS_CROSSING_3, p_i50286_1_);
            this.setCoordBaseMode(p_i50286_3_);
            this.boundingBox = p_i50286_2_;
        }


        protected Crossing3(Random p_i2042_1_, int p_i2042_2_, int p_i2042_3_) {
            super(StructurePieces.JUNGLE_FORTRESS_CROSSING_3, 0);
            this.setCoordBaseMode(Direction.Plane.HORIZONTAL.random(p_i2042_1_));
            if (this.getCoordBaseMode().getAxis() == Direction.Axis.Z) {
                this.boundingBox = new MutableBoundingBox(p_i2042_2_, 64, p_i2042_3_, p_i2042_2_ + 19 - 1, 73, p_i2042_3_ + 19 - 1);
            } else {
                this.boundingBox = new MutableBoundingBox(p_i2042_2_, 64, p_i2042_3_, p_i2042_2_ + 19 - 1, 73, p_i2042_3_ + 19 - 1);
            }
        }


        @Override
        public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentNormal((FortressJunglePieces.Start) componentIn, listIn, rand, 8, 3, false);
            this.getNextComponentX((FortressJunglePieces.Start) componentIn, listIn, rand, 3, 8, false);
            this.getNextComponentZ((FortressJunglePieces.Start) componentIn, listIn, rand, 3, 8, false);
        }


        @SuppressWarnings("ConstantConditions")
        public static FortressJunglePieces.Crossing3 createPiece(List<StructurePiece> structurePieceList, Random random, int x, int y, int z, Direction direction, int depth) {
            MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(x, y, z, -8, -3, 0, 19, 10, 19, direction);
            return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(structurePieceList, mutableBoundingBox) == null ? new FortressJunglePieces.Crossing3(depth, mutableBoundingBox, direction) : null;
        }


        @Override
        public boolean generate(ISeedReader world, StructureManager structureAccessor, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
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

            this.fillWithWater(world, boundingBox, 0, 0, 0, 18, 5, 11);
            return true;
        }
    }

    public static class End extends FortressJunglePieces.Piece {
        private final int fillSeed;


        public End(int p_i45621_1_, Random p_i45621_2_, MutableBoundingBox p_i45621_3_, Direction p_i45621_4_) {
            super(StructurePieces.JUNGLE_FORTRESS_END, p_i45621_1_);
            this.setCoordBaseMode(p_i45621_4_);
            this.boundingBox = p_i45621_3_;
            this.fillSeed = p_i45621_2_.nextInt();
        }


        public End(TemplateManager p_i50285_1_, CompoundNBT p_i50285_2_) {
            super(StructurePieces.JUNGLE_FORTRESS_END, p_i50285_2_);
            this.fillSeed = p_i50285_2_.getInt("Seed");
        }


        @SuppressWarnings("ConstantConditions")
        public static FortressJunglePieces.End createPiece(List<StructurePiece> structurePieceList, Random random, int x, int y, int z, Direction direction, int depth) {
            MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(x, y, z, -1, -3, 0, 5, 10, 8, direction);
            return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(structurePieceList, mutableBoundingBox) == null ? new FortressJunglePieces.End(depth, random, mutableBoundingBox, direction) : null;
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void readAdditional(CompoundNBT tagCompound) {
            super.readAdditional(tagCompound);
            tagCompound.putInt("Seed", this.fillSeed);
        }


        @Override
        public boolean generate(ISeedReader world, StructureManager structureAccessor, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
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
        public Entrance(int p_i45617_1_, Random rand, MutableBoundingBox p_i45617_3_, Direction p_i45617_4_) {
            super(StructurePieces.JUNGLE_FORTRESS_ENTRANCE, p_i45617_1_);
            this.setCoordBaseMode(p_i45617_4_);
            this.boundingBox = p_i45617_3_;
        }


        public Entrance(TemplateManager p_i50276_1_, CompoundNBT p_i50276_2_) {
            super(StructurePieces.JUNGLE_FORTRESS_ENTRANCE, p_i50276_2_);
        }


        @Override
        public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentNormal((FortressJunglePieces.Start) componentIn, listIn, rand, 5, 3, true);
        }


        @SuppressWarnings("ConstantConditions")
        public static FortressJunglePieces.Entrance createPiece(List<StructurePiece> structurePieceList, Random random, int x, int y, int z, Direction direction, int depth) {
            MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(x, y, z, -5, -3, 0, 13, 14, 13, direction);
            return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(structurePieceList, mutableBoundingBox) == null ? new FortressJunglePieces.Entrance(depth, random, mutableBoundingBox, direction) : null;
        }


        @Override
        public boolean generate(ISeedReader world, StructureManager structureAccessor, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
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
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, true).with(FourWayBlock.EAST, true), random);
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, true).with(FourWayBlock.SOUTH, true), random);

            for (int i = 1; i <= 11; i += 2) {
                this.fillWithRandomBlocks(world, boundingBox, i, 10, 0, i, 11, 0, iblockstate, iblockstate, false, random);
                this.fillWithRandomBlocks(world, boundingBox, i, 10, 12, i, 11, 12, iblockstate, iblockstate, false, random);
                this.fillWithRandomBlocks(world, boundingBox, 0, 10, i, 0, 11, i, iblockstate1, iblockstate1, false, random);
                this.fillWithRandomBlocks(world, boundingBox, 12, 10, i, 12, 11, i, iblockstate1, iblockstate1, false, random);
                this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), i, 13, 0, boundingBox);
                this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), i, 13, 12, boundingBox);
                this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), 0, 13, i, boundingBox);
                this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), 12, 13, i, boundingBox);
                this.setBlockState(world, iblockstate, i + 1, 13, 0, boundingBox);
                this.setBlockState(world, iblockstate, i + 1, 13, 12, boundingBox);
                this.setBlockState(world, iblockstate1, 0, 13, i + 1, boundingBox);
                this.setBlockState(world, iblockstate1, 12, 13, i + 1, boundingBox);
            }

            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, true).with(FourWayBlock.EAST, true), random), 0, 13, 0, boundingBox);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.SOUTH, true).with(FourWayBlock.EAST, true), random), 0, 13, 12, boundingBox);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.SOUTH, true).with(FourWayBlock.WEST, true), random), 12, 13, 12, boundingBox);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, true).with(FourWayBlock.WEST, true), random), 12, 13, 0, boundingBox);

            for (int k = 3; k <= 9; k += 2) {
                this.fillWithRandomBlocks(world, boundingBox, 1, 7, k, 1, 8, k, iblockstate.with(FourWayBlock.WEST, true), iblockstate.with(FourWayBlock.WEST, true), false, random);
                this.fillWithRandomBlocks(world, boundingBox, 11, 7, k, 11, 8, k, iblockstate.with(FourWayBlock.EAST, true), iblockstate.with(FourWayBlock.EAST, true), false, random);
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
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), 6, 0, 6, boundingBox);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.LAVA.getDefaultState(), random), 6, 5, 6, boundingBox);
            BlockPos blockpos = new BlockPos(this.getXWithOffset(6, 6), this.getYWithOffset(5), this.getZWithOffset(6, 6));

            if (RepurposedStructures.RSMainConfig.lootChestsJF.get()) {
                this.generateChest(world, boundingBox, random, 6, 5, 8, JF_CENTER_CHEST_RL);
            }

            if (boundingBox.isVecInside(blockpos)) {
                world.getPendingFluidTicks().scheduleTick(blockpos, Fluids.LAVA, 0);
            }

            this.fillWithWater(world, boundingBox, 0, 0, 0, 12, 13, 12);
            return true;
        }
    }

    public static class MushroomRoom extends FortressJunglePieces.Piece {
        public MushroomRoom(int p_i50264_1_, MutableBoundingBox p_i50264_2_, Direction p_i50264_3_) {
            super(StructurePieces.JUNGLE_FORTRESS_MUSHROOM_ROOM, p_i50264_1_);
            this.setCoordBaseMode(p_i50264_3_);
            this.boundingBox = p_i50264_2_;
        }


        public MushroomRoom(TemplateManager p_i50265_1_, CompoundNBT p_i50265_2_) {
            super(StructurePieces.JUNGLE_FORTRESS_MUSHROOM_ROOM, p_i50265_2_);
        }


        @Override
        public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentNormal((FortressJunglePieces.Start) componentIn, listIn, rand, 5, 3, true);
            this.getNextComponentNormal((FortressJunglePieces.Start) componentIn, listIn, rand, 5, 11, true);
        }


        @SuppressWarnings("ConstantConditions")
        public static FortressJunglePieces.MushroomRoom createPiece(List<StructurePiece> structurePieceList, Random random, int x, int y, int z, Direction direction, int depth) {
            MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(x, y, z, -5, -3, 0, 13, 14, 13, direction);
            return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(structurePieceList, mutableBoundingBox) == null ? new FortressJunglePieces.MushroomRoom(depth, mutableBoundingBox, direction) : null;
        }


        @Override
        public boolean generate(ISeedReader world, StructureManager structureAccessor, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
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
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, true).with(FourWayBlock.EAST, true), random);
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, true).with(FourWayBlock.SOUTH, true), random);
            BlockState iblockstate2 = iblockstate1.with(FourWayBlock.WEST, true);
            BlockState iblockstate3 = iblockstate1.with(FourWayBlock.EAST, true);

            for (int i = 1; i <= 11; i += 2) {
                this.fillWithRandomBlocks(world, boundingBox, i, 10, 0, i, 11, 0, iblockstate, iblockstate, false, random);
                this.fillWithRandomBlocks(world, boundingBox, i, 10, 12, i, 11, 12, iblockstate, iblockstate, false, random);
                this.fillWithRandomBlocks(world, boundingBox, 0, 10, i, 0, 11, i, iblockstate1, iblockstate1, false, random);
                this.fillWithRandomBlocks(world, boundingBox, 12, 10, i, 12, 11, i, iblockstate1, iblockstate1, false, random);
                this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), i, 13, 0, boundingBox);
                this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), i, 13, 12, boundingBox);
                this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), 0, 13, i, boundingBox);
                this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), 12, 13, i, boundingBox);
                this.setBlockState(world, iblockstate, i + 1, 13, 0, boundingBox);
                this.setBlockState(world, iblockstate, i + 1, 13, 12, boundingBox);
                this.setBlockState(world, iblockstate1, 0, 13, i + 1, boundingBox);
                this.setBlockState(world, iblockstate1, 12, 13, i + 1, boundingBox);
            }

            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, true).with(FourWayBlock.EAST, true), random), 0, 13, 0, boundingBox);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.SOUTH, true).with(FourWayBlock.EAST, true), random), 0, 13, 12, boundingBox);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.SOUTH, true).with(FourWayBlock.WEST, true), random), 12, 13, 12, boundingBox);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, true).with(FourWayBlock.WEST, true), random), 12, 13, 0, boundingBox);

            for (int j1 = 3; j1 <= 9; j1 += 2) {
                this.fillWithRandomBlocks(world, boundingBox, 1, 7, j1, 1, 8, j1, iblockstate2, iblockstate2, false, random);
                this.fillWithRandomBlocks(world, boundingBox, 11, 7, j1, 11, 8, j1, iblockstate3, iblockstate3, false, random);
            }

            BlockState iblockstate4 = getStoneVariantBlockState(Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH), random);

            for (int j = 0; j <= 6; ++j) {
                int k = j + 4;

                for (int l = 5; l <= 7; ++l) {
                    this.setBlockState(world, iblockstate4, l, 5 + j, k, boundingBox);
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
                this.setBlockState(world, iblockstate4, k1, 12, 11, boundingBox);
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
            this.setBlockState(world, iblockstate6, 4, 5, 2, boundingBox);
            this.setBlockState(world, iblockstate6, 4, 5, 3, boundingBox);
            this.setBlockState(world, iblockstate6, 4, 5, 9, boundingBox);
            this.setBlockState(world, iblockstate6, 4, 5, 10, boundingBox);
            this.setBlockState(world, iblockstate5, 8, 5, 2, boundingBox);
            this.setBlockState(world, iblockstate5, 8, 5, 3, boundingBox);
            this.setBlockState(world, iblockstate5, 8, 5, 9, boundingBox);
            this.setBlockState(world, iblockstate5, 8, 5, 10, boundingBox);

            this.fillWithRandomBlocks(world, boundingBox, 3, 4, 4, 4, 4, 8, Blocks.SOUL_SAND.getDefaultState(), Blocks.SOUL_SAND.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 8, 4, 4, 9, 4, 8, Blocks.SOUL_SAND.getDefaultState(), Blocks.SOUL_SAND.getDefaultState(), false, random);

            if(this.getYWithOffset(5) >= world.getWorld().getSeaLevel()){
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
        protected Piece(IStructurePieceType p_i50260_1_, int p_i50260_2_) {
            super(p_i50260_1_, p_i50260_2_);
        }


        public Piece(IStructurePieceType p_i50261_1_, CompoundNBT p_i50261_2_) {
            super(p_i50261_1_, p_i50261_2_);
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void readAdditional(CompoundNBT tagCompound) {
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


        private FortressJunglePieces.Piece generatePiece(FortressJunglePieces.Start startPiece, List<FortressJunglePieces.PieceWeight> pieceWeights, List<StructurePiece> structurePieceList, Random random, int x, int y, int z, Direction direction, int depth) {
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

                        FortressJunglePieces.Piece structurenetherbridgepieces$piece = FortressJunglePieces.findAndCreateBridgePieceFactory(structurenetherbridgepieces$pieceweight, structurePieceList, random, x, y, z, direction, depth);

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

            return FortressJunglePieces.End.createPiece(structurePieceList, random, x, y, z, direction, depth);
        }


        private StructurePiece generateAndAddPiece(FortressJunglePieces.Start startPiece, List<StructurePiece> structurePieces, Random random, int x, int y, int z, Direction direction, int depth, boolean secondaryList) {
            if (Math.abs(x - startPiece.getBoundingBox().minX) <= 112 && Math.abs(z - startPiece.getBoundingBox().minZ) <= 112) {
                List<FortressJunglePieces.PieceWeight> list = startPiece.primaryWeights;

                if (secondaryList) {
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


        protected StructurePiece getNextComponentNormal(FortressJunglePieces.Start structurePiece, List<StructurePiece> structurePieceList, Random random, int x, int z, boolean useSecondaryList) {
            Direction enumfacing = this.getCoordBaseMode();

            if (enumfacing != null) {
                switch (enumfacing) {
                    case NORTH:
                        return this.generateAndAddPiece(structurePiece, structurePieceList, random, this.boundingBox.minX + x, this.boundingBox.minY + z, this.boundingBox.minZ - 1, enumfacing, this.getComponentType(), useSecondaryList);

                    case SOUTH:
                        return this.generateAndAddPiece(structurePiece, structurePieceList, random, this.boundingBox.minX + x, this.boundingBox.minY + z, this.boundingBox.maxZ + 1, enumfacing, this.getComponentType(), useSecondaryList);

                    case WEST:
                        return this.generateAndAddPiece(structurePiece, structurePieceList, random, this.boundingBox.minX - 1, this.boundingBox.minY + z, this.boundingBox.minZ + x, enumfacing, this.getComponentType(), useSecondaryList);

                    case EAST:
                        return this.generateAndAddPiece(structurePiece, structurePieceList, random, this.boundingBox.maxX + 1, this.boundingBox.minY + z, this.boundingBox.minZ + x, enumfacing, this.getComponentType(), useSecondaryList);

                    default:
                        break;
                }
            }

            return null;
        }


        protected StructurePiece getNextComponentX(FortressJunglePieces.Start piece, List<StructurePiece> structurePieces, Random random, int x, int z, boolean useSecondaryList) {
            Direction enumfacing = this.getCoordBaseMode();

            if (enumfacing != null) {
                switch (enumfacing) {
                    case NORTH:
                    case SOUTH:
                        return this.generateAndAddPiece(piece, structurePieces, random, this.boundingBox.minX - 1, this.boundingBox.minY + x, this.boundingBox.minZ + z, Direction.WEST, this.getComponentType(), useSecondaryList);

                    case WEST:
                    case EAST:
                        return this.generateAndAddPiece(piece, structurePieces, random, this.boundingBox.minX + z, this.boundingBox.minY + x, this.boundingBox.minZ - 1, Direction.NORTH, this.getComponentType(), useSecondaryList);

                    default:
                        break;
                }
            }

            return null;
        }


        protected StructurePiece getNextComponentZ(FortressJunglePieces.Start start, List<StructurePiece> structurePieces, Random random, int x, int z, boolean useSecondaryList) {
            Direction enumfacing = this.getCoordBaseMode();

            if (enumfacing != null) {
                switch (enumfacing) {
                    case NORTH:
                    case SOUTH:
                        return this.generateAndAddPiece(start, structurePieces, random, this.boundingBox.maxX + 1, this.boundingBox.minY + x, this.boundingBox.minZ + z, Direction.EAST, this.getComponentType(), useSecondaryList);

                    case WEST:
                    case EAST:
                        return this.generateAndAddPiece(start, structurePieces, random, this.boundingBox.minX + z, this.boundingBox.minY + x, this.boundingBox.maxZ + 1, Direction.SOUTH, this.getComponentType(), useSecondaryList);

                    default:
                        break;
                }
            }

            return null;
        }


        protected static boolean isAboveGround(MutableBoundingBox p_74964_0_) {
            return p_74964_0_ != null && p_74964_0_.minY > 10;
        }


        protected BlockState getStoneVariantBlockState(BlockState blockstateIn, Random rand) {
            Block block = blockstateIn.getBlock();

            if (blockstateIn.isIn(Blocks.NETHER_BRICKS)) {
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
                float silverfishThreshold = (float) (RepurposedStructures.RSMainConfig.silverfishSpawnrateJF.get() / 100);
                if (chance < silverfishThreshold) {
                    newBlockState = INFESTED_STONE_LOOKUP.get(newBlockState);
                }


                return newBlockState;
            }
            else if (blockstateIn.isIn(Blocks.NETHER_BRICK_FENCE)) {
                return Blocks.IRON_BARS.getDefaultState().with(FourWayBlock.NORTH, blockstateIn.get(FourWayBlock.NORTH)).with(FourWayBlock.EAST, Boolean.valueOf(blockstateIn.get(FourWayBlock.EAST))).with(FourWayBlock.SOUTH, Boolean.valueOf(blockstateIn.get(FourWayBlock.SOUTH))).with(FourWayBlock.WEST, Boolean.valueOf(blockstateIn.get(FourWayBlock.WEST)));
            }
            else if (blockstateIn.isIn(Blocks.NETHER_BRICK_STAIRS)) {

                float chance = rand.nextFloat();
                if (chance < 0.8f) {
                    // 80%
                    return Blocks.STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, blockstateIn.get(StairsBlock.FACING));
                } else {
                    // 20%
                    return Blocks.MOSSY_STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, blockstateIn.get(StairsBlock.FACING));
                }
            }
            else if (blockstateIn.isIn(Blocks.SOUL_SAND)) {
                ITag<Block> ORE_TAG = BlockTags.getCollection().getOrCreate(JF_SOIL_TAG_RL);
                Collection<Block> allSoilBlocks = ORE_TAG.values();
                BlockState soilBlock = null;

                if (!allSoilBlocks.isEmpty())
                    soilBlock = ((Block) allSoilBlocks.toArray()[rand.nextInt(allSoilBlocks.size())]).getDefaultState();

                if (soilBlock != null && soilBlock.contains(BlockStateProperties.MOISTURE_0_7))
                    soilBlock = soilBlock.with(BlockStateProperties.MOISTURE_0_7, rand.nextInt(8));

                return soilBlock != null ? soilBlock : Blocks.COARSE_DIRT.getDefaultState();
            }
            else if (blockstateIn.isIn(Blocks.NETHER_WART)) {
                ITag<Block> ORE_TAG = BlockTags.getCollection().getOrCreate(JF_PLANT_TAG_RL);
                Collection<Block> allPlantBlocks = ORE_TAG.values();
                float chance = rand.nextFloat();

                if (!allPlantBlocks.isEmpty() && chance < 0.4f) {
                    BlockState plantBlock = ((Block) allPlantBlocks.toArray()[rand.nextInt(allPlantBlocks.size())]).getDefaultState();

                    if (plantBlock.contains(BlockStateProperties.AGE_0_25))
                        plantBlock = plantBlock.with(BlockStateProperties.AGE_0_25, rand.nextInt(26));
                    else if (plantBlock.contains(BlockStateProperties.AGE_0_15))
                        plantBlock = plantBlock.with(BlockStateProperties.AGE_0_15, rand.nextInt(16));
                    else if (plantBlock.contains(BlockStateProperties.AGE_0_7))
                        plantBlock = plantBlock.with(BlockStateProperties.AGE_0_7, rand.nextInt(8));
                    else if (plantBlock.contains(BlockStateProperties.AGE_0_5))
                        plantBlock = plantBlock.with(BlockStateProperties.AGE_0_5, rand.nextInt(6));
                    else if (plantBlock.contains(BlockStateProperties.AGE_0_3))
                        plantBlock = plantBlock.with(BlockStateProperties.AGE_0_3, rand.nextInt(4));
                    else if (plantBlock.contains(BlockStateProperties.AGE_0_2))
                        plantBlock = plantBlock.with(BlockStateProperties.AGE_0_2, rand.nextInt(3));
                    else if (plantBlock.contains(BlockStateProperties.AGE_0_1))
                        plantBlock = plantBlock.with(BlockStateProperties.AGE_0_1, rand.nextInt(2));

                    return plantBlock;
                } else {
                    return Blocks.CAVE_AIR.getDefaultState();
                }
            } else if (blockstateIn.isIn(Blocks.LAVA)) {
                return Blocks.WATER.getDefaultState();
            }

            return block.getDefaultState();
        }


        /**
         * Fill the given area with the selected random blocks
         */
        protected void fillWithRandomBlocks(ISeedReader world, MutableBoundingBox boundingboxIn, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, BlockState boundaryBlockState, BlockState insideBlockState, boolean existingOnly, Random rand) {
            for (int y = yMin; y <= yMax; ++y) {
                for (int x = xMin; x <= xMax; ++x) {
                    for (int z = zMin; z <= zMax; ++z) {
                        if (!existingOnly || this.getBlockStateFromPos(world, x, y, z, boundingboxIn).getMaterial() != Material.AIR) {
                            if (y != yMin && y != yMax && x != xMin && x != xMax && z != zMin && z != zMax) {
                                this.setBlockState(world, insideBlockState, x, y, z, boundingboxIn);
                            } else {
                                this.setBlockState(world, getStoneVariantBlockState(boundaryBlockState.getBlock().getDefaultState(), rand), x, y, z, boundingboxIn);
                            }
                        }
                    }
                }
            }

        }

        protected void fillWithWater(ISeedReader world, MutableBoundingBox boundingboxIn, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax) {
            BlockPos.Mutable blockPos;

            for (int y = yMin; y <= yMax; ++y) {
                for (int x = xMin; x <= xMax; ++x) {
                    for (int z = zMin; z <= zMax; ++z) {
                        blockPos = new BlockPos.Mutable(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));

                        if (blockPos.getY() < world.getSeaLevel())
                        {
                            if(world.getBlockState(blockPos).getMaterial() == Material.AIR){
                                this.setBlockState(world, Blocks.WATER.getDefaultState(), x, y, z, boundingboxIn);
                                world.getPendingFluidTicks().scheduleTick(blockPos, Fluids.WATER, 0);
                            }
                            else if(world.getBlockState(blockPos).getProperties().contains(BlockStateProperties.WATERLOGGED)){
                                world.setBlockState(blockPos, world.getBlockState(blockPos).with(BlockStateProperties.WATERLOGGED, true),3);
                                world.getPendingFluidTicks().scheduleTick(blockPos, Fluids.WATER, 0);
                            }
                        }
                    }
                }
            }
        }

        /**
         * Replaces air and liquid from given position downwards. Stops when hitting anything else than air or liquid
         */
        protected void replaceAirAndLiquidDownwardsRandomBlocks(ISeedReader world, BlockState blockstateIn, int x, int y, int z, MutableBoundingBox boundingboxIn, Random rand) {
            int i = this.getXWithOffset(x, z);
            int j = this.getYWithOffset(y);
            int k = this.getZWithOffset(x, z);
            if (boundingboxIn.isVecInside(new BlockPos(i, j, k))) {
                while ((world.isAirBlock(new BlockPos(i, j, k)) || world.getBlockState(new BlockPos(i, j, k)).getMaterial().isLiquid()) && j > 1) {
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
        public Stairs(int p_i50255_1_, MutableBoundingBox p_i50255_2_, Direction p_i50255_3_) {
            super(StructurePieces.JUNGLE_FORTRESS_STAIRS, p_i50255_1_);
            this.setCoordBaseMode(p_i50255_3_);
            this.boundingBox = p_i50255_2_;
        }


        public Stairs(TemplateManager p_i50256_1_, CompoundNBT p_i50256_2_) {
            super(StructurePieces.JUNGLE_FORTRESS_STAIRS, p_i50256_2_);
        }


        @Override
        public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentZ((FortressJunglePieces.Start) componentIn, listIn, rand, 6, 2, false);
        }


        @SuppressWarnings("ConstantConditions")
        public static FortressJunglePieces.Stairs createPiece(List<StructurePiece> structurePieceList, Random random, int x, int y, int z, int depth, Direction direction) {
            MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(x, y, z, -2, 0, 0, 7, 11, 7, direction);
            return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(structurePieceList, mutableBoundingBox) == null ? new FortressJunglePieces.Stairs(depth, mutableBoundingBox, direction) : null;
        }


        @Override
        public boolean generate(ISeedReader world, StructureManager structureAccessor, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            this.fillWithRandomBlocks(world, boundingBox, 0, 0, 0, 6, 1, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 6, 10, 6, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 0, 1, 8, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 5, 2, 0, 6, 8, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 2, 1, 0, 8, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 6, 2, 1, 6, 8, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            this.fillWithRandomBlocks(world, boundingBox, 1, 2, 6, 5, 8, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, true).with(FourWayBlock.EAST, true), random);
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, true).with(FourWayBlock.SOUTH, true), random);
            this.fillWithRandomBlocks(world, boundingBox, 0, 3, 2, 0, 5, 4, iblockstate1, iblockstate1, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 6, 3, 2, 6, 5, 2, iblockstate1, iblockstate1, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 6, 3, 4, 6, 5, 4, iblockstate1, iblockstate1, false, random);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), 5, 2, 5, boundingBox);
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


        public Start(TemplateManager p_i50253_1_, CompoundNBT p_i50253_2_) {
            super(StructurePieces.JUNGLE_FORTRESS_START, p_i50253_2_);
        }
    }

    public static class Straight extends FortressJunglePieces.Piece {
        public Straight(int p_i45620_1_, Random p_i45620_2_, MutableBoundingBox p_i45620_3_, Direction p_i45620_4_) {
            super(StructurePieces.JUNGLE_FORTRESS_STRAIGHT, p_i45620_1_);
            this.setCoordBaseMode(p_i45620_4_);
            this.boundingBox = p_i45620_3_;
        }


        public Straight(TemplateManager p_i50283_1_, CompoundNBT p_i50283_2_) {
            super(StructurePieces.JUNGLE_FORTRESS_STRAIGHT, p_i50283_2_);
        }


        @Override
        public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            this.getNextComponentNormal((FortressJunglePieces.Start) componentIn, listIn, rand, 1, 3, false);
        }


        @SuppressWarnings("ConstantConditions")
        public static FortressJunglePieces.Straight createPiece(List<StructurePiece> structurePieceList, Random random, int x, int y, int z, Direction direction, int depth) {
            MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(x, y, z, -1, -3, 0, 5, 10, 19, direction);
            return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(structurePieceList, mutableBoundingBox) == null ? new FortressJunglePieces.Straight(depth, random, mutableBoundingBox, direction) : null;
        }


        @Override
        public boolean generate(ISeedReader world, StructureManager structureAccessor, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
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
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, true).with(FourWayBlock.SOUTH, true), random);
            BlockState iblockstate2 = iblockstate1.with(FourWayBlock.EAST, true);
            BlockState iblockstate3 = iblockstate1.with(FourWayBlock.WEST, true);

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


        public Throne(int p_i50262_1_, Random rand, MutableBoundingBox p_i50262_2_, Direction p_i50262_3_) {
            super(StructurePieces.JUNGLE_FORTRESS_THRONE, p_i50262_1_);
            this.setCoordBaseMode(p_i50262_3_);
            this.boundingBox = p_i50262_2_;
        }


        public Throne(TemplateManager p_i50263_1_, CompoundNBT p_i50263_2_) {
            super(StructurePieces.JUNGLE_FORTRESS_THRONE, p_i50263_2_);
            this.hasSpawner = p_i50263_2_.getBoolean("Mob");
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void readAdditional(CompoundNBT tagCompound) {
            super.readAdditional(tagCompound);
            tagCompound.putBoolean("Mob", this.hasSpawner);
        }


        @SuppressWarnings("ConstantConditions")
        public static FortressJunglePieces.Throne createPiece(List<StructurePiece> structurePieceList, Random random, int x, int y, int z, int depth, Direction direction) {
            MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(x, y, z, -2, 0, 0, 7, 8, 9, direction);
            return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(structurePieceList, mutableBoundingBox) == null ? new FortressJunglePieces.Throne(depth, random, mutableBoundingBox, direction) : null;
        }


        @Override
        public boolean generate(ISeedReader world, StructureManager structureAccessor, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
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
            BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, true).with(FourWayBlock.EAST, true), random);
            BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, true).with(FourWayBlock.SOUTH, true), random);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, true), random), 1, 6, 3, boundingBox);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.EAST, true), random), 5, 6, 3, boundingBox);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.EAST, true).with(FourWayBlock.NORTH, true), random), 0, 6, 3, boundingBox);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, true).with(FourWayBlock.NORTH, true), random), 6, 6, 3, boundingBox);
            this.fillWithRandomBlocks(world, boundingBox, 0, 6, 4, 0, 6, 7, iblockstate1, iblockstate1, false, random);
            this.fillWithRandomBlocks(world, boundingBox, 6, 6, 4, 6, 6, 7, iblockstate1, iblockstate1, false, random);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.EAST, true).with(FourWayBlock.SOUTH, true), random), 0, 6, 8, boundingBox);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, true).with(FourWayBlock.SOUTH, true), random), 6, 6, 8, boundingBox);
            this.fillWithRandomBlocks(world, boundingBox, 1, 6, 8, 5, 6, 8, iblockstate, iblockstate, false, random);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.EAST, true), random), 1, 7, 8, boundingBox);
            this.fillWithRandomBlocks(world, boundingBox, 2, 7, 8, 4, 7, 8, iblockstate, iblockstate, false, random);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, true), random), 5, 7, 8, boundingBox);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.EAST, true), random), 2, 8, 8, boundingBox);
            this.setBlockState(world, iblockstate, 3, 8, 8, boundingBox);
            this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, true), random), 4, 8, 8, boundingBox);

            if (!this.hasSpawner) {
                BlockPos blockpos = new BlockPos(this.getXWithOffset(3, 5), this.getYWithOffset(5), this.getZWithOffset(3, 5));

                if (boundingBox.isVecInside(blockpos)) {
                    this.hasSpawner = true;

                    // mob spawner
                    world.setBlockState(blockpos.down(2), Blocks.SPAWNER.getDefaultState(), 2);
                    TileEntity tileentity2 = world.getTileEntity(blockpos.down(2));

                    if (tileentity2 instanceof MobSpawnerTileEntity) {
                        ((MobSpawnerTileEntity) tileentity2).getSpawnerBaseLogic()
                                    .setEntityType(RepurposedStructures.mobSpawnerManager.getSpawnerMob(SPAWNER_ID, random));
                    }
                }
            }

            for (int i = 0; i <= 6; ++i) {
                for (int j = 0; j <= 6; ++j) {
                    this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, boundingBox, random);
                }
            }

            if (RepurposedStructures.RSMainConfig.lootChestsJF.get()) {
                this.generateChest(world, boundingBox, random, 3, 5, 7, FortressJunglePieces.JF_SHRINE_CHEST_RL);
            }

            this.fillWithWater(world, boundingBox, 0, 0, 0, 6, 7, 8);
            return true;
        }
    }
}
