package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.StrongholdGeneratorAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.StructurePieceAccessor;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.FluidState;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.state.property.Property;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
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

import java.util.*;


public class RSStrongholdPieces {

    public enum Type {
        NORMAL, NETHER;

        public static Type byId(int id) {
            return id >= 0 && id < values().length ? values()[id] : NORMAL;
        }
    }
    
    private static final Identifier NETHER_STRONGHOLD_BOOKSHELF_RL = new Identifier("repurposed_structures:nether_stronghold_bookshelves");

    private static final List<RSStrongholdPieces.PieceWeight> PIECE_WEIGHTS = new ArrayList<RSStrongholdPieces.PieceWeight>();

    private static List<RSStrongholdPieces.PieceWeight> structurePieceList;
    private static Class<? extends RSStrongholdPieces.Stronghold> strongComponentType;
    static int totalWeight;
    private static final Map<Block, Block> NETHER_BLOCK_MAP;

    //setup maps and lists
    static {
        NETHER_BLOCK_MAP = new HashMap<>();
        NETHER_BLOCK_MAP.put(Blocks.STONE_BRICKS, Blocks.NETHER_BRICKS);
        NETHER_BLOCK_MAP.put(Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB);
        NETHER_BLOCK_MAP.put(Blocks.STONE_BRICK_STAIRS, Blocks.NETHER_BRICK_STAIRS);
        NETHER_BLOCK_MAP.put(Blocks.COBBLESTONE, Blocks.SOUL_SAND);
        NETHER_BLOCK_MAP.put(Blocks.COBBLESTONE_STAIRS, Blocks.RED_NETHER_BRICK_STAIRS);
        NETHER_BLOCK_MAP.put(Blocks.COBBLESTONE_SLAB, Blocks.RED_NETHER_BRICK_SLAB);
        NETHER_BLOCK_MAP.put(Blocks.STONE_SLAB, Blocks.RED_NETHER_BRICK_SLAB);
        NETHER_BLOCK_MAP.put(Blocks.IRON_BARS, Blocks.NETHER_BRICK_FENCE);
        NETHER_BLOCK_MAP.put(Blocks.OAK_PLANKS, Blocks.DARK_OAK_PLANKS);
        NETHER_BLOCK_MAP.put(Blocks.OAK_FENCE, Blocks.DARK_OAK_FENCE);
        NETHER_BLOCK_MAP.put(Blocks.WATER, Blocks.LAVA);
        NETHER_BLOCK_MAP.put(Blocks.WALL_TORCH, Blocks.REDSTONE_WALL_TORCH);
        NETHER_BLOCK_MAP.put(Blocks.TORCH, Blocks.REDSTONE_TORCH);


        PIECE_WEIGHTS.add(new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.Straight.class, 40, 0));
        PIECE_WEIGHTS.add(new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.Prison.class, 5, 8));
        PIECE_WEIGHTS.add(new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.LeftTurn.class, 20, 0));
        PIECE_WEIGHTS.add(new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.RightTurn.class, 20, 0));
        PIECE_WEIGHTS.add(new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.RoomCrossing.class, 10, 9));
        PIECE_WEIGHTS.add(new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.StairsStraight.class, 5, 7));
        PIECE_WEIGHTS.add(new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.Stairs.class, 5, 7));
        PIECE_WEIGHTS.add(new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.Crossing.class, 5, 7));
        PIECE_WEIGHTS.add(new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.ChestCorridor.class, 5, 16));
        PIECE_WEIGHTS.add(new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.Library.class, 10, 6) {
            @Override
            public boolean canSpawnMoreStructures(int distanceFromStart) {
                return super.canSpawnMoreStructures(distanceFromStart) && distanceFromStart > 4;
            }
        });
        PIECE_WEIGHTS.add(new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.PortalRoom.class, 20, 1) {
            @Override
            public boolean canSpawnMoreStructures(int distanceFromStart) {
                return this.instancesSpawned < 1 && distanceFromStart > 5;
            }

            @Override
            public boolean canSpawnMoreStructures() {
                return this.instancesSpawned < 1;
            }
        });

        for(StrongholdGenerator.PieceSetting piece : StrongholdGeneratorAccessor.getALL_PIECE_SETTINGS()){
            try {
                if(!Registry.STRUCTURE_PIECE.getId((StructurePieceType) piece.pieceType.getDeclaredConstructors()[0].newInstance())
                        .getNamespace().equals("minecraft"))
                {
                    PIECE_WEIGHTS.add(new RSStrongholdPieces.PieceWeight(piece.pieceType, piece.field_15278, piece.limit));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * sets up Arrays with the Structure pieces and their weights
     */
    public static void prepareStructurePieces() {
        structurePieceList = Lists.<RSStrongholdPieces.PieceWeight>newArrayList();

        for (RSStrongholdPieces.PieceWeight structurestrongholdpieces$pieceweight : PIECE_WEIGHTS) {
            structurestrongholdpieces$pieceweight.instancesSpawned = 0;
            structurePieceList.add(structurestrongholdpieces$pieceweight);
        }

        strongComponentType = null;
    }


    private static boolean canAddStructurePieces() {
        boolean flag = false;
        totalWeight = 0;

        for (RSStrongholdPieces.PieceWeight structurestrongholdpieces$pieceweight : structurePieceList) {
            if (structurestrongholdpieces$pieceweight.instancesLimit > 0) {
                String name = structurestrongholdpieces$pieceweight.pieceClass.getSimpleName();
                if (name.equals("PortalRoom")) {
                    if (structurestrongholdpieces$pieceweight.instancesSpawned < 1) flag = true;
                } else {
                    int maxLimit = (int) (structurestrongholdpieces$pieceweight.instancesLimit * (RepurposedStructures.RSStrongholdsConfig.strongholdSizeSH * 0.01D));

                    if (structurestrongholdpieces$pieceweight.instancesSpawned <= maxLimit) flag = true;
                }
            }

            totalWeight += structurestrongholdpieces$pieceweight.pieceWeight;
        }

        return flag;
    }


    private static RSStrongholdPieces.Stronghold findAndCreatePieceFactory(Class<? extends StrongholdGenerator.Piece> pieceClass, List<StructurePiece> piecesList, Random random, int xStart, int yStart, int zStart, Direction direction, int distanceFromStart, Type stronghold$type) {
        RSStrongholdPieces.Stronghold structurestrongholdpieces$stronghold = null;

        if (pieceClass == RSStrongholdPieces.Straight.class) {
            structurestrongholdpieces$stronghold = RSStrongholdPieces.Straight.createPiece(piecesList, random, xStart, yStart, zStart, direction, distanceFromStart, stronghold$type);
        } else if (pieceClass == RSStrongholdPieces.Prison.class) {
            structurestrongholdpieces$stronghold = RSStrongholdPieces.Prison.createPiece(piecesList, random, xStart, yStart, zStart, direction, distanceFromStart, stronghold$type);
        } else if (pieceClass == RSStrongholdPieces.LeftTurn.class) {
            structurestrongholdpieces$stronghold = RSStrongholdPieces.LeftTurn.createPiece(piecesList, random, xStart, yStart, zStart, direction, distanceFromStart, stronghold$type);
        } else if (pieceClass == RSStrongholdPieces.RightTurn.class) {
            structurestrongholdpieces$stronghold = RSStrongholdPieces.RightTurn.createPiece(piecesList, random, xStart, yStart, zStart, direction, distanceFromStart, stronghold$type);
        } else if (pieceClass == RSStrongholdPieces.RoomCrossing.class) {
            structurestrongholdpieces$stronghold = RSStrongholdPieces.RoomCrossing.createPiece(piecesList, random, xStart, yStart, zStart, direction, distanceFromStart, stronghold$type);
        } else if (pieceClass == RSStrongholdPieces.StairsStraight.class) {
            structurestrongholdpieces$stronghold = RSStrongholdPieces.StairsStraight.createPiece(piecesList, random, xStart, yStart, zStart, direction, distanceFromStart, stronghold$type);
        } else if (pieceClass == RSStrongholdPieces.Stairs.class) {
            structurestrongholdpieces$stronghold = RSStrongholdPieces.Stairs.createPiece(piecesList, random, xStart, yStart, zStart, direction, distanceFromStart, stronghold$type);
        } else if (pieceClass == RSStrongholdPieces.Crossing.class) {
            structurestrongholdpieces$stronghold = RSStrongholdPieces.Crossing.createPiece(piecesList, random, xStart, yStart, zStart, direction, distanceFromStart, stronghold$type);
        } else if (pieceClass == RSStrongholdPieces.ChestCorridor.class) {
            structurestrongholdpieces$stronghold = RSStrongholdPieces.ChestCorridor.createPiece(piecesList, random, xStart, yStart, zStart, direction, distanceFromStart, stronghold$type);
        } else if (pieceClass == RSStrongholdPieces.Library.class) {
            structurestrongholdpieces$stronghold = RSStrongholdPieces.Library.createPiece(piecesList, random, xStart, yStart, zStart, direction, distanceFromStart, stronghold$type);
        } else if (pieceClass == RSStrongholdPieces.PortalRoom.class) {
            structurestrongholdpieces$stronghold = RSStrongholdPieces.PortalRoom.createPiece(piecesList, random, xStart, yStart, zStart, direction, distanceFromStart, stronghold$type);
        }

        return structurestrongholdpieces$stronghold;
    }


    private static RSStrongholdPieces.Stronghold generatePieceFromSmallDoor(RSStrongholdPieces.EntranceStairs p_175955_0_, List<StructurePiece> p_175955_1_, Random random, int x, int y, int z, Direction p_175955_6_, int componentType) {
        Type stronghold$type = ((RSStrongholdPieces.Stronghold) p_175955_0_).strongholdType;
        if (!canAddStructurePieces()) {
            return null;
        } else {
            if (strongComponentType != null) {
                RSStrongholdPieces.Stronghold structurestrongholdpieces$stronghold = findAndCreatePieceFactory(strongComponentType, p_175955_1_, random, x, y, z, p_175955_6_, componentType, stronghold$type);
                strongComponentType = null;

                if (structurestrongholdpieces$stronghold != null) {
                    return structurestrongholdpieces$stronghold;
                }
            }

            int j = 0;

            while (j < 5) {
                ++j;
                int randomWeight = random.nextInt(totalWeight);

                for (RSStrongholdPieces.PieceWeight structurestrongholdpieces$pieceweight : structurePieceList) {
                    randomWeight -= structurestrongholdpieces$pieceweight.pieceWeight;

                    if (randomWeight < 0) {
                        if (!structurestrongholdpieces$pieceweight.canSpawnMoreStructures(componentType) || structurestrongholdpieces$pieceweight == p_175955_0_.strongholdPieceWeight) {
                            break;
                        }

                        RSStrongholdPieces.Stronghold structurestrongholdpieces$stronghold1 = findAndCreatePieceFactory(structurestrongholdpieces$pieceweight.pieceClass, p_175955_1_, random, x, y, z, p_175955_6_, componentType, stronghold$type);

                        if (structurestrongholdpieces$stronghold1 != null) {
                            ++structurestrongholdpieces$pieceweight.instancesSpawned;
                            p_175955_0_.strongholdPieceWeight = structurestrongholdpieces$pieceweight;

                            if (!structurestrongholdpieces$pieceweight.canSpawnMoreStructures()) {
                                structurePieceList.remove(structurestrongholdpieces$pieceweight);
                            }

                            return structurestrongholdpieces$stronghold1;
                        }
                    }
                }
            }

            BlockBox mutableboundingbox = RSStrongholdPieces.Corridor.findPieceBox(p_175955_1_, random, x, y, z, p_175955_6_);

            if (mutableboundingbox != null && mutableboundingbox.minY > 1) {
                return new RSStrongholdPieces.Corridor(componentType, mutableboundingbox, p_175955_6_, stronghold$type);
            } else {
                return null;
            }
        }
    }


    private static StructurePiece generateAndAddPiece(RSStrongholdPieces.EntranceStairs p_175953_0_, List<StructurePiece> p_175953_1_, Random p_175953_2_, int p_175953_3_, int p_175953_4_, int p_175953_5_, Direction p_175953_6_, int distanceFromStart) {
        int maxComponents = (int) (50 * (RepurposedStructures.RSStrongholdsConfig.strongholdSizeSH * 0.01D));
        if (distanceFromStart > maxComponents) {
            return null;
        } else if (Math.abs(p_175953_3_ - p_175953_0_.getBoundingBox().minX) <= 112 && Math.abs(p_175953_5_ - p_175953_0_.getBoundingBox().minZ) <= 112) {
            StructurePiece StructurePiece = generatePieceFromSmallDoor(p_175953_0_, p_175953_1_, p_175953_2_, p_175953_3_, p_175953_4_, p_175953_5_, p_175953_6_, distanceFromStart + 1);

            if (StructurePiece != null) {
                p_175953_1_.add(StructurePiece);
                p_175953_0_.pendingChildren.add(StructurePiece);
            }

            return StructurePiece;
        } else {
            return null;
        }
    }

    public static class ChestCorridor extends RSStrongholdPieces.Stronghold {
        public ChestCorridor(int componentType, Random p_i45582_2_, BlockBox p_i45582_3_, Direction p_i45582_4_, Type strongholdType) {
            super(StructurePieces.STRONGHOLD_CHEST_CORRIDOR, componentType, strongholdType);
            this.setOrientation(p_i45582_4_);
            this.entryDoor = this.getRandomDoor(p_i45582_2_);
            this.boundingBox = p_i45582_3_;
        }


        public ChestCorridor(StructureManager p_i50140_1_, CompoundTag p_i50140_2_) {
            super(StructurePieces.STRONGHOLD_CHEST_CORRIDOR, p_i50140_2_);
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag tagCompound) {
            super.toNbt(tagCompound);
        }


        @Override
        public void placeJigsaw(StructurePiece component, List<StructurePiece> piecesList, Random rand) {
            this.getNextComponentNormal((RSStrongholdPieces.EntranceStairs) component, piecesList, rand, 1, 1);
        }


        public static RSStrongholdPieces.ChestCorridor createPiece(List<StructurePiece> p_175868_0_, Random p_175868_1_, int x, int y, int z, Direction p_175868_5_, int componentType, Type strongholdType) {
            BlockBox mutableboundingbox = BlockBox.rotated(x, y, z, -1, -1, 0, 5, 5, 7, p_175868_5_);
            return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.getOverlappingPiece(p_175868_0_, mutableboundingbox) == null ? new RSStrongholdPieces.ChestCorridor(componentType, p_175868_1_, mutableboundingbox, p_175868_5_, strongholdType) : null;
        }


        @Override
        public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox structureBoundingBoxIn, ChunkPos chunkPos, BlockPos blockPos) {
            RSStrongholdPieces.Stones randomStrongholdBlocks = new RSStrongholdPieces.Stones(this.strongholdType);
            this.fillWithOutline(world, structureBoundingBoxIn, 0, 0, 0, 4, 4, 6, false, random, randomStrongholdBlocks);
            this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 1, 1, 0);
            this.placeDoor(world, random, structureBoundingBoxIn, RSStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 6);
            this.fillWithOutline(world, structureBoundingBoxIn, 3, 1, 2, 3, 1, 4, Blocks.STONE_BRICKS.getDefaultState(), Blocks.STONE_BRICKS.getDefaultState(), false);
            this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 3, 1, 1, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 3, 1, 5, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 3, 2, 2, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 3, 2, 4, structureBoundingBoxIn);

            for (int i = 2; i <= 4; ++i) {
                this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 2, 1, i, structureBoundingBoxIn);
            }

            if (structureBoundingBoxIn.contains(new BlockPos(this.applyXTransform(3, 3), this.applyYTransform(2), this.applyZTransform(3, 3)))) {
                if (RepurposedStructures.RSStrongholdsConfig.lootChestsSH && random.nextInt(3) == 0) {
                    this.addChest(world, structureBoundingBoxIn, random, 3, 2, 3, getHallwayChestLoot());
                }
            }

            return true;
        }
    }

    public static class Corridor extends RSStrongholdPieces.Stronghold {
        private int steps;


        public Corridor(int componentType, BlockBox p_i50137_2_, Direction p_i50137_3_, Type strongholdType) {
            super(StructurePieces.STRONGHOLD_CORRIDOR, componentType, strongholdType);
            this.setOrientation(p_i50137_3_);
            this.boundingBox = p_i50137_2_;
            this.steps = p_i50137_3_ != Direction.NORTH && p_i50137_3_ != Direction.SOUTH ? p_i50137_2_.getBlockCountX() : p_i50137_2_.getBlockCountZ();
        }


        public Corridor(StructureManager p_i50138_1_, CompoundTag p_i50138_2_) {
            super(StructurePieces.STRONGHOLD_CORRIDOR, p_i50138_2_);
            this.steps = p_i50138_2_.getInt("Steps");
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag tagCompound) {
            super.toNbt(tagCompound);
            tagCompound.putInt("Steps", this.steps);
        }


        public static BlockBox findPieceBox(List<StructurePiece> p_175869_0_, Random p_175869_1_, int p_175869_2_, int p_175869_3_, int p_175869_4_, Direction p_175869_5_) {
            BlockBox mutableboundingbox = BlockBox.rotated(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, 4, p_175869_5_);
            StructurePiece structurePiece = StructurePiece.getOverlappingPiece(p_175869_0_, mutableboundingbox);

            if (structurePiece == null) {
                return null;
            } else {
                if (structurePiece.getBoundingBox().minY == mutableboundingbox.minY) {
                    for (int j = 3; j >= 1; --j) {
                        mutableboundingbox = BlockBox.rotated(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, j - 1, p_175869_5_);

                        if (!structurePiece.getBoundingBox().intersects(mutableboundingbox)) {
                            return BlockBox.rotated(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, j, p_175869_5_);
                        }
                    }
                }

                return null;
            }
        }


        @Override
        public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox structureBoundingBoxIn, ChunkPos chunkPos, BlockPos blockPos) {
            for (int i = 0; i < this.steps; ++i) {
                this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 0, 0, i, structureBoundingBoxIn);
                this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 1, 0, i, structureBoundingBoxIn);
                this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 2, 0, i, structureBoundingBoxIn);
                this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 3, 0, i, structureBoundingBoxIn);
                this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 4, 0, i, structureBoundingBoxIn);

                for (int j = 1; j <= 3; ++j) {
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 0, j, i, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.AIR.getDefaultState(), 1, j, i, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.AIR.getDefaultState(), 2, j, i, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.AIR.getDefaultState(), 3, j, i, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 4, j, i, structureBoundingBoxIn);
                }

                this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 0, 4, i, structureBoundingBoxIn);
                this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 1, 4, i, structureBoundingBoxIn);
                this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 2, 4, i, structureBoundingBoxIn);
                this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 3, 4, i, structureBoundingBoxIn);
                this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 4, 4, i, structureBoundingBoxIn);
            }

            return true;
        }

    }

    public static class Crossing extends RSStrongholdPieces.Stronghold {
        private boolean leftLow;
        private boolean leftHigh;
        private boolean rightLow;
        private boolean rightHigh;


        public Crossing(int componentType, Random p_i45580_2_, BlockBox p_i45580_3_, Direction p_i45580_4_, Type strongholdType) {
            super(StructurePieces.STRONGHOLD_CROSSING, componentType, strongholdType);
            this.setOrientation(p_i45580_4_);
            this.entryDoor = this.getRandomDoor(p_i45580_2_);
            this.boundingBox = p_i45580_3_;
            this.leftLow = p_i45580_2_.nextBoolean();
            this.leftHigh = p_i45580_2_.nextBoolean();
            this.rightLow = p_i45580_2_.nextBoolean();
            this.rightHigh = p_i45580_2_.nextInt(3) > 0;
        }


        public Crossing(StructureManager p_i50136_1_, CompoundTag p_i50136_2_) {
            super(StructurePieces.STRONGHOLD_CROSSING, p_i50136_2_);
            this.leftLow = p_i50136_2_.getBoolean("leftLow");
            this.leftHigh = p_i50136_2_.getBoolean("leftHigh");
            this.rightLow = p_i50136_2_.getBoolean("rightLow");
            this.rightHigh = p_i50136_2_.getBoolean("rightHigh");
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag tagCompound) {
            super.toNbt(tagCompound);
            tagCompound.putBoolean("leftLow", this.leftLow);
            tagCompound.putBoolean("leftHigh", this.leftHigh);
            tagCompound.putBoolean("rightLow", this.rightLow);
            tagCompound.putBoolean("rightHigh", this.rightHigh);
        }


        @Override
        public void placeJigsaw(StructurePiece component, List<StructurePiece> piecesList, Random rand) {
            int i = 3;
            int j = 5;
            Direction enumfacing = this.getFacing();

            if (enumfacing == Direction.WEST || enumfacing == Direction.NORTH) {
                i = 8 - i;
                j = 8 - j;
            }

            this.getNextComponentNormal((RSStrongholdPieces.EntranceStairs) component, piecesList, rand, 5, 1);

            if (this.leftLow) {
                this.getNextComponentX((RSStrongholdPieces.EntranceStairs) component, piecesList, rand, i, 1);
            }

            if (this.leftHigh) {
                this.getNextComponentX((RSStrongholdPieces.EntranceStairs) component, piecesList, rand, j, 7);
            }

            if (this.rightLow) {
                this.getNextComponentZ((RSStrongholdPieces.EntranceStairs) component, piecesList, rand, i, 1);
            }

            if (this.rightHigh) {
                this.getNextComponentZ((RSStrongholdPieces.EntranceStairs) component, piecesList, rand, j, 7);
            }
        }


        public static RSStrongholdPieces.Crossing createPiece(List<StructurePiece> p_175866_0_, Random p_175866_1_, int x, int y, int z, Direction p_175866_5_, int componentType, Type strongholdType) {
            BlockBox mutableboundingbox = BlockBox.rotated(x, y, z, -4, -3, 0, 10, 9, 11, p_175866_5_);
            return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.getOverlappingPiece(p_175866_0_, mutableboundingbox) == null ? new RSStrongholdPieces.Crossing(componentType, p_175866_1_, mutableboundingbox, p_175866_5_, strongholdType) : null;
        }


        @Override
        public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox structureBoundingBoxIn, ChunkPos chunkPos, BlockPos blockPos) {
            RSStrongholdPieces.Stones randomStrongholdBlocks = new RSStrongholdPieces.Stones(this.strongholdType);
            this.fillWithOutline(world, structureBoundingBoxIn, 0, 0, 0, 9, 8, 10, false, random, randomStrongholdBlocks);
            this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 4, 3, 0);

            if (this.leftLow) {
                this.fillWithOutline(world, structureBoundingBoxIn, 0, 3, 1, 0, 5, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            }

            if (this.rightLow) {
                this.fillWithOutline(world, structureBoundingBoxIn, 9, 3, 1, 9, 5, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            }

            if (this.leftHigh) {
                this.fillWithOutline(world, structureBoundingBoxIn, 0, 5, 7, 0, 7, 9, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            }

            if (this.rightHigh) {
                this.fillWithOutline(world, structureBoundingBoxIn, 9, 5, 7, 9, 7, 9, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            }

            this.fillWithOutline(world, structureBoundingBoxIn, 5, 1, 10, 7, 3, 10, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithOutline(world, structureBoundingBoxIn, 1, 2, 1, 8, 2, 6, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 4, 1, 5, 4, 4, 9, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 8, 1, 5, 8, 4, 9, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 1, 4, 7, 3, 4, 9, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 1, 3, 5, 3, 3, 6, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 1, 3, 4, 3, 3, 4, Blocks.STONE_SLAB.getDefaultState(), Blocks.STONE_SLAB.getDefaultState(), false);
            this.fillWithOutline(world, structureBoundingBoxIn, 1, 4, 6, 3, 4, 6, Blocks.STONE_SLAB.getDefaultState(), Blocks.STONE_SLAB.getDefaultState(), false);
            this.fillWithOutline(world, structureBoundingBoxIn, 5, 1, 7, 7, 1, 8, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 5, 1, 9, 7, 1, 9, Blocks.STONE_SLAB.getDefaultState(), Blocks.STONE_SLAB.getDefaultState(), false);
            this.fillWithOutline(world, structureBoundingBoxIn, 5, 2, 7, 7, 2, 7, Blocks.STONE_SLAB.getDefaultState(), Blocks.STONE_SLAB.getDefaultState(), false);
            this.fillWithOutline(world, structureBoundingBoxIn, 4, 5, 7, 4, 5, 9, Blocks.STONE_SLAB.getDefaultState(), Blocks.STONE_SLAB.getDefaultState(), false);
            this.fillWithOutline(world, structureBoundingBoxIn, 8, 5, 7, 8, 5, 9, Blocks.STONE_SLAB.getDefaultState(), Blocks.STONE_SLAB.getDefaultState(), false);
            this.fillWithOutline(world, structureBoundingBoxIn, 5, 5, 7, 7, 5, 9, Blocks.STONE_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.DOUBLE), Blocks.STONE_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.DOUBLE), false);
            this.addBlock(world, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.SOUTH), 6, 5, 6, structureBoundingBoxIn);
            return true;
        }
    }

    public static class LeftTurn extends RSStrongholdPieces.Turn {
        public LeftTurn(int componentType, Random p_i45579_2_, BlockBox p_i45579_3_, Direction p_i45579_4_, Type strongholdType) {
            super(StructurePieces.STRONGHOLD_LEFT_TURN, componentType, strongholdType);
            this.setOrientation(p_i45579_4_);
            this.entryDoor = this.getRandomDoor(p_i45579_2_);
            this.boundingBox = p_i45579_3_;
        }


        public LeftTurn(StructureManager p_i50134_1_, CompoundTag p_i50134_2_) {
            super(StructurePieces.STRONGHOLD_LEFT_TURN, p_i50134_2_);
        }


        @Override
        public void placeJigsaw(StructurePiece component, List<StructurePiece> piecesList, Random rand) {
            Direction enumfacing = this.getFacing();

            if (enumfacing != Direction.NORTH && enumfacing != Direction.EAST) {
                this.getNextComponentZ((RSStrongholdPieces.EntranceStairs) component, piecesList, rand, 1, 1);
            } else {
                this.getNextComponentX((RSStrongholdPieces.EntranceStairs) component, piecesList, rand, 1, 1);
            }
        }


        public static RSStrongholdPieces.LeftTurn createPiece(List<StructurePiece> p_175867_0_, Random p_175867_1_, int x, int y, int z, Direction p_175867_5_, int componentType, Type strongholdType) {
            BlockBox mutableboundingbox = BlockBox.rotated(x, y, z, -1, -1, 0, 5, 5, 5, p_175867_5_);
            return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.getOverlappingPiece(p_175867_0_, mutableboundingbox) == null ? new RSStrongholdPieces.LeftTurn(componentType, p_175867_1_, mutableboundingbox, p_175867_5_, strongholdType) : null;
        }


        @Override
        public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox structureBoundingBoxIn, ChunkPos chunkPos, BlockPos blockPos) {
            RSStrongholdPieces.Stones randomStrongholdBlocks = new RSStrongholdPieces.Stones(this.strongholdType);
            this.fillWithOutline(world, structureBoundingBoxIn, 0, 0, 0, 4, 4, 4, false, random, randomStrongholdBlocks);
            this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 1, 1, 0);
            Direction enumfacing = this.getFacing();

            if (enumfacing != Direction.NORTH && enumfacing != Direction.EAST) {
                this.fillWithOutline(world, structureBoundingBoxIn, 4, 1, 1, 4, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            } else {
                this.fillWithOutline(world, structureBoundingBoxIn, 0, 1, 1, 0, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            }

            return true;
        }
    }

    public static class Library extends RSStrongholdPieces.Stronghold {
        private boolean isLargeRoom;


        public Library(int componentType, Random p_i45578_2_, BlockBox p_i45578_3_, Direction p_i45578_4_, Type strongholdType) {
            super(StructurePieces.STRONGHOLD_LIBRARY, componentType, strongholdType);
            this.setOrientation(p_i45578_4_);
            this.entryDoor = this.getRandomDoor(p_i45578_2_);
            this.boundingBox = p_i45578_3_;
            this.isLargeRoom = p_i45578_3_.getBlockCountY() > 6;
        }


        public Library(StructureManager p_i50133_1_, CompoundTag p_i50133_2_) {
            super(StructurePieces.STRONGHOLD_LIBRARY, p_i50133_2_);
            this.isLargeRoom = p_i50133_2_.getBoolean("Tall");
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag tagCompound) {
            super.toNbt(tagCompound);
            tagCompound.putBoolean("Tall", this.isLargeRoom);
        }


        public static RSStrongholdPieces.Library createPiece(List<StructurePiece> p_175864_0_, Random p_175864_1_, int x, int y, int z, Direction p_175864_5_, int componentType, Type strongholdType) {
            BlockBox mutableboundingbox = BlockBox.rotated(x, y, z, -4, -1, 0, 14, 11, 15, p_175864_5_);

            if (!canStrongholdGoDeeper(mutableboundingbox) || StructurePiece.getOverlappingPiece(p_175864_0_, mutableboundingbox) != null) {
                mutableboundingbox = BlockBox.rotated(x, y, z, -4, -1, 0, 14, 6, 15, p_175864_5_);

                if (!canStrongholdGoDeeper(mutableboundingbox) || StructurePiece.getOverlappingPiece(p_175864_0_, mutableboundingbox) != null) {
                    return null;
                }
            }

            return new RSStrongholdPieces.Library(componentType, p_175864_1_, mutableboundingbox, p_175864_5_, strongholdType);
        }


        private BlockState pickBookshelfBlock(Random random) {
            BlockState bookshelfBlock = Blocks.BOOKSHELF.getDefaultState();

            if (this.strongholdType == Type.NETHER) {
                Tag<Block> BOOKSHELF_TAG = BlockTags.getContainer().getOrCreate(NETHER_STRONGHOLD_BOOKSHELF_RL);
                Collection<Block> allBookshelfBlocks = BOOKSHELF_TAG.values();

                if (!allBookshelfBlocks.isEmpty())
                    bookshelfBlock = ((Block) allBookshelfBlocks.toArray()[random.nextInt(allBookshelfBlocks.size())]).getDefaultState();
            }

            return bookshelfBlock;
        }


        @Override
        public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox structureBoundingBoxIn, ChunkPos chunkPos, BlockPos blockPos) {
            int i = 11;

            if (!this.isLargeRoom) {
                i = 6;
            }

            RSStrongholdPieces.Stones randomStrongholdBlocks = new RSStrongholdPieces.Stones(this.strongholdType);
            this.fillWithOutline(world, structureBoundingBoxIn, 0, 0, 0, 13, i - 1, 14, false, random, randomStrongholdBlocks);
            this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 4, 1, 0);
            this.fillWithOutlineUnderSealevel(world, structureBoundingBoxIn, random, 0.07F, 2, 1, 1, 11, 4, 13, Blocks.COBWEB.getDefaultState(), Blocks.COBWEB.getDefaultState(), false, false);


            for (int l = 1; l <= 13; ++l) {
                if ((l - 1) % 4 == 0) {
                    this.fillWithOutline(world, structureBoundingBoxIn, 1, 1, l, 1, 4, l, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false);
                    this.fillWithOutline(world, structureBoundingBoxIn, 12, 1, l, 12, 4, l, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false);
                    this.addBlock(world, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.EAST), 2, 3, l, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.WEST), 11, 3, l, structureBoundingBoxIn);
                    if (this.isLargeRoom) {
                        this.fillWithOutline(world, structureBoundingBoxIn, 1, 6, l, 1, 9, l, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false);
                        this.fillWithOutline(world, structureBoundingBoxIn, 12, 6, l, 12, 9, l, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false);
                    }
                } else {
                    this.fillWithOutline(world, structureBoundingBoxIn, 1, 1, l, 1, 4, l, pickBookshelfBlock(random), pickBookshelfBlock(random), false);
                    this.fillWithOutline(world, structureBoundingBoxIn, 12, 1, l, 12, 4, l, pickBookshelfBlock(random), pickBookshelfBlock(random), false);
                    if (this.isLargeRoom) {
                        this.fillWithOutline(world, structureBoundingBoxIn, 1, 6, l, 1, 9, l, pickBookshelfBlock(random), pickBookshelfBlock(random), false);
                        this.fillWithOutline(world, structureBoundingBoxIn, 12, 6, l, 12, 9, l, pickBookshelfBlock(random), pickBookshelfBlock(random), false);
                    }
                }
            }

            for (int l1 = 3; l1 < 12; l1 += 2) {
                this.fillWithOutline(world, structureBoundingBoxIn, 3, 1, l1, 4, 3, l1, pickBookshelfBlock(random), pickBookshelfBlock(random), false);
                this.fillWithOutline(world, structureBoundingBoxIn, 6, 1, l1, 7, 3, l1, pickBookshelfBlock(random), pickBookshelfBlock(random), false);
                this.fillWithOutline(world, structureBoundingBoxIn, 9, 1, l1, 10, 3, l1, pickBookshelfBlock(random), pickBookshelfBlock(random), false);
            }

            if (this.isLargeRoom) {
                this.fillWithOutline(world, structureBoundingBoxIn, 1, 5, 1, 3, 5, 13, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false);
                this.fillWithOutline(world, structureBoundingBoxIn, 10, 5, 1, 12, 5, 13, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false);
                this.fillWithOutline(world, structureBoundingBoxIn, 4, 5, 1, 9, 5, 2, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false);
                this.fillWithOutline(world, structureBoundingBoxIn, 4, 5, 12, 9, 5, 13, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false);
                this.addBlock(world, Blocks.OAK_PLANKS.getDefaultState(), 9, 5, 11, structureBoundingBoxIn);
                this.addBlock(world, Blocks.OAK_PLANKS.getDefaultState(), 8, 5, 11, structureBoundingBoxIn);
                this.addBlock(world, Blocks.OAK_PLANKS.getDefaultState(), 9, 5, 10, structureBoundingBoxIn);
                BlockState iblockstate5 = Blocks.OAK_FENCE.getDefaultState().with(HorizontalConnectingBlock.WEST, Boolean.valueOf(true)).with(HorizontalConnectingBlock.EAST, Boolean.valueOf(true));
                BlockState iblockstate = Blocks.OAK_FENCE.getDefaultState().with(HorizontalConnectingBlock.NORTH, Boolean.valueOf(true)).with(HorizontalConnectingBlock.SOUTH, Boolean.valueOf(true));
                this.fillWithOutline(world, structureBoundingBoxIn, 3, 6, 3, 3, 6, 11, iblockstate, iblockstate, false);
                this.fillWithOutline(world, structureBoundingBoxIn, 10, 6, 3, 10, 6, 9, iblockstate, iblockstate, false);
                this.fillWithOutline(world, structureBoundingBoxIn, 4, 6, 2, 9, 6, 2, iblockstate5, iblockstate5, false);
                this.fillWithOutline(world, structureBoundingBoxIn, 4, 6, 12, 7, 6, 12, iblockstate5, iblockstate5, false);
                this.addBlock(world, Blocks.OAK_FENCE.getDefaultState().with(HorizontalConnectingBlock.NORTH, Boolean.valueOf(true)).with(HorizontalConnectingBlock.EAST, Boolean.valueOf(true)), 3, 6, 2, structureBoundingBoxIn);
                this.addBlock(world, Blocks.OAK_FENCE.getDefaultState().with(HorizontalConnectingBlock.SOUTH, Boolean.valueOf(true)).with(HorizontalConnectingBlock.EAST, Boolean.valueOf(true)), 3, 6, 12, structureBoundingBoxIn);
                this.addBlock(world, Blocks.OAK_FENCE.getDefaultState().with(HorizontalConnectingBlock.NORTH, Boolean.valueOf(true)).with(HorizontalConnectingBlock.WEST, Boolean.valueOf(true)), 10, 6, 2, structureBoundingBoxIn);

                for (int i1 = 0; i1 <= 2; ++i1) {
                    this.addBlock(world, Blocks.OAK_FENCE.getDefaultState().with(HorizontalConnectingBlock.SOUTH, Boolean.valueOf(true)).with(HorizontalConnectingBlock.WEST, Boolean.valueOf(true)), 8 + i1, 6, 12 - i1, structureBoundingBoxIn);
                    if (i1 != 2) {
                        this.addBlock(world, Blocks.OAK_FENCE.getDefaultState().with(HorizontalConnectingBlock.NORTH, Boolean.valueOf(true)).with(HorizontalConnectingBlock.EAST, Boolean.valueOf(true)), 8 + i1, 6, 11 - i1, structureBoundingBoxIn);
                    }
                }

                BlockState iblockstate6 = Blocks.LADDER.getDefaultState().with(LadderBlock.FACING, Direction.SOUTH);
                this.addBlock(world, iblockstate6, 10, 1, 13, structureBoundingBoxIn);
                this.addBlock(world, iblockstate6, 10, 2, 13, structureBoundingBoxIn);
                this.addBlock(world, iblockstate6, 10, 3, 13, structureBoundingBoxIn);
                this.addBlock(world, iblockstate6, 10, 4, 13, structureBoundingBoxIn);
                this.addBlock(world, iblockstate6, 10, 5, 13, structureBoundingBoxIn);
                this.addBlock(world, iblockstate6, 10, 6, 13, structureBoundingBoxIn);
                this.addBlock(world, iblockstate6, 10, 7, 13, structureBoundingBoxIn);
                BlockState iblockstate1 = Blocks.OAK_FENCE.getDefaultState().with(HorizontalConnectingBlock.EAST, Boolean.valueOf(true));
                this.addBlock(world, iblockstate1, 6, 9, 7, structureBoundingBoxIn);
                BlockState iblockstate2 = Blocks.OAK_FENCE.getDefaultState().with(HorizontalConnectingBlock.WEST, Boolean.valueOf(true));
                this.addBlock(world, iblockstate2, 7, 9, 7, structureBoundingBoxIn);
                this.addBlock(world, iblockstate1, 6, 8, 7, structureBoundingBoxIn);
                this.addBlock(world, iblockstate2, 7, 8, 7, structureBoundingBoxIn);
                BlockState iblockstate3 = iblockstate.with(HorizontalConnectingBlock.WEST, Boolean.valueOf(true)).with(HorizontalConnectingBlock.EAST, Boolean.valueOf(true));
                this.addBlock(world, iblockstate3, 6, 7, 7, structureBoundingBoxIn);
                this.addBlock(world, iblockstate3, 7, 7, 7, structureBoundingBoxIn);
                this.addBlock(world, iblockstate1, 5, 7, 7, structureBoundingBoxIn);
                this.addBlock(world, iblockstate2, 8, 7, 7, structureBoundingBoxIn);
                this.addBlock(world, iblockstate1.with(HorizontalConnectingBlock.NORTH, Boolean.valueOf(true)), 6, 7, 6, structureBoundingBoxIn);
                this.addBlock(world, iblockstate1.with(HorizontalConnectingBlock.SOUTH, Boolean.valueOf(true)), 6, 7, 8, structureBoundingBoxIn);
                this.addBlock(world, iblockstate2.with(HorizontalConnectingBlock.NORTH, Boolean.valueOf(true)), 7, 7, 6, structureBoundingBoxIn);
                this.addBlock(world, iblockstate2.with(HorizontalConnectingBlock.SOUTH, Boolean.valueOf(true)), 7, 7, 8, structureBoundingBoxIn);
                BlockState iblockstate4 = Blocks.TORCH.getDefaultState();
                this.addBlock(world, iblockstate4, 5, 8, 7, structureBoundingBoxIn);
                this.addBlock(world, iblockstate4, 8, 8, 7, structureBoundingBoxIn);
                this.addBlock(world, iblockstate4, 6, 8, 6, structureBoundingBoxIn);
                this.addBlock(world, iblockstate4, 6, 8, 8, structureBoundingBoxIn);
                this.addBlock(world, iblockstate4, 7, 8, 6, structureBoundingBoxIn);
                this.addBlock(world, iblockstate4, 7, 8, 8, structureBoundingBoxIn);
            }

            if (RepurposedStructures.RSStrongholdsConfig.lootChestsSH) {
                this.addChest(world, structureBoundingBoxIn, random, 3, 3, 5, LootTables.STRONGHOLD_LIBRARY_CHEST);
            }

            if (this.isLargeRoom) {
                this.addBlock(world, Blocks.AIR.getDefaultState(), 12, 9, 1, structureBoundingBoxIn);

                if (RepurposedStructures.RSStrongholdsConfig.lootChestsSH) {
                    this.addChest(world, structureBoundingBoxIn, random, 12, 8, 1, LootTables.STRONGHOLD_LIBRARY_CHEST);
                }
            }

            return true;
        }
    }

    static class PieceWeight {
        public Class<? extends StrongholdGenerator.Piece> pieceClass;
        public final int pieceWeight;
        public int instancesSpawned;
        public int instancesLimit;


        public PieceWeight(Class<? extends StrongholdGenerator.Piece> p_i2076_1_, int p_i2076_2_, int p_i2076_3_) {
            this.pieceClass = p_i2076_1_;
            this.pieceWeight = p_i2076_2_;
            this.instancesLimit = p_i2076_3_;
        }


        public boolean canSpawnMoreStructures(int distanceFromStart) {
            int maxLimit = (int) (this.instancesLimit * (RepurposedStructures.RSStrongholdsConfig.strongholdSizeSH * 0.01D));

            return this.instancesLimit == 0 || this.instancesSpawned < maxLimit;
        }


        public boolean canSpawnMoreStructures() {
            int maxLimit = (int) (this.instancesLimit * (RepurposedStructures.RSStrongholdsConfig.strongholdSizeSH * 0.01D));

            return this.instancesLimit == 0 || this.instancesSpawned < maxLimit;
        }
    }

    public static class PortalRoom extends RSStrongholdPieces.Stronghold {
        public PortalRoom(int componentType, BlockBox p_i50131_2_, Direction p_i50131_3_, Type strongholdType) {
            super(StructurePieces.STRONGHOLD_PORTAL_ROOM, componentType, strongholdType);
            this.setOrientation(p_i50131_3_);
            this.boundingBox = p_i50131_2_;
        }


        public PortalRoom(StructureManager p_i50132_1_, CompoundTag p_i50132_2_) {
            super(StructurePieces.STRONGHOLD_PORTAL_ROOM, p_i50132_2_);
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag tagCompound) {
            super.toNbt(tagCompound);
        }


        @Override
        public void placeJigsaw(StructurePiece component, List<StructurePiece> piecesList, Random rand) {
            if (component != null) {
                ((RSStrongholdPieces.EntranceStairs) component).strongholdPortalRoom = this;
            }
        }


        public static RSStrongholdPieces.PortalRoom createPiece(List<StructurePiece> p_175865_0_, Random p_175865_1_, int x, int y, int z, Direction p_175865_5_, int componentType, Type strongholdType) {
            BlockBox mutableboundingbox = BlockBox.rotated(x, y, z, -4, -1, 0, 11, 8, 16, p_175865_5_);
            return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.getOverlappingPiece(p_175865_0_, mutableboundingbox) == null ? new RSStrongholdPieces.PortalRoom(componentType, mutableboundingbox, p_175865_5_, strongholdType) : null;
        }


        public static RSStrongholdPieces.PortalRoom createPiece(List<StructurePiece> p_175865_0_, Random p_175865_1_, int x, int y, int z, Direction p_175865_5_, Type strongholdType) {
            BlockBox mutableboundingbox = BlockBox.rotated(x, y, z, -4, -1, 0, 11, 8, 16, p_175865_5_);
            return new RSStrongholdPieces.PortalRoom(1, mutableboundingbox, p_175865_5_, strongholdType);
        }


        @Override
        public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox structureBoundingBoxIn, ChunkPos chunkPos, BlockPos blockPos) {
            RSStrongholdPieces.Stones randomStrongholdBlocks = new RSStrongholdPieces.Stones(this.strongholdType);
            this.fillWithOutline(world, structureBoundingBoxIn, 0, 0, 0, 10, 7, 15, false, random, randomStrongholdBlocks);
            this.placeDoor(world, random, structureBoundingBoxIn, RSStrongholdPieces.Stronghold.Door.GRATES, 4, 1, 0);
            int i = 6;
            this.fillWithOutline(world, structureBoundingBoxIn, 1, i, 1, 1, i, 14, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 9, i, 1, 9, i, 14, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 2, i, 1, 8, i, 2, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 2, i, 14, 8, i, 14, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 1, 1, 1, 2, 1, 4, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 8, 1, 1, 9, 1, 4, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 1, 1, 1, 1, 1, 3, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
            this.fillWithOutline(world, structureBoundingBoxIn, 9, 1, 1, 9, 1, 3, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
            this.fillWithOutline(world, structureBoundingBoxIn, 3, 1, 8, 7, 1, 12, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 4, 1, 9, 6, 1, 11, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);

            for (int j = 3; j < 14; j += 2) {
                this.fillWithOutline(world, structureBoundingBoxIn, 0, 3, j, 0, 4, j, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
                this.fillWithOutline(world, structureBoundingBoxIn, 10, 3, j, 10, 4, j, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
            }

            for (int i1 = 2; i1 < 9; i1 += 2) {
                this.fillWithOutline(world, structureBoundingBoxIn, i1, 3, 15, i1, 4, 15, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
            }

            BlockState iblockstate3 = Blocks.STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
            this.fillWithOutline(world, structureBoundingBoxIn, 4, 1, 5, 6, 1, 7, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 4, 2, 6, 6, 2, 7, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 4, 3, 7, 6, 3, 7, false, random, randomStrongholdBlocks);

            for (int k = 4; k <= 6; ++k) {
                this.addBlock(world, iblockstate3, k, 1, 4, structureBoundingBoxIn);
                this.addBlock(world, iblockstate3, k, 2, 5, structureBoundingBoxIn);
                this.addBlock(world, iblockstate3, k, 3, 6, structureBoundingBoxIn);
            }

            BlockState iblockstate4 = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.NORTH);
            BlockState iblockstate = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.SOUTH);
            BlockState iblockstate1 = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.EAST);
            BlockState iblockstate2 = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.WEST);
            boolean flag = true;
            boolean[] aboolean = new boolean[12];

            for (int l = 0; l < aboolean.length; ++l) {
                aboolean[l] = random.nextFloat() > 0.9F;
                flag &= aboolean[l];
            }

            this.addBlock(world, iblockstate4.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[0])), 4, 3, 8, structureBoundingBoxIn);
            this.addBlock(world, iblockstate4.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[1])), 5, 3, 8, structureBoundingBoxIn);
            this.addBlock(world, iblockstate4.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[2])), 6, 3, 8, structureBoundingBoxIn);
            this.addBlock(world, iblockstate.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[3])), 4, 3, 12, structureBoundingBoxIn);
            this.addBlock(world, iblockstate.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[4])), 5, 3, 12, structureBoundingBoxIn);
            this.addBlock(world, iblockstate.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[5])), 6, 3, 12, structureBoundingBoxIn);
            this.addBlock(world, iblockstate1.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[6])), 3, 3, 9, structureBoundingBoxIn);
            this.addBlock(world, iblockstate1.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[7])), 3, 3, 10, structureBoundingBoxIn);
            this.addBlock(world, iblockstate1.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[8])), 3, 3, 11, structureBoundingBoxIn);
            this.addBlock(world, iblockstate2.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[9])), 7, 3, 9, structureBoundingBoxIn);
            this.addBlock(world, iblockstate2.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[10])), 7, 3, 10, structureBoundingBoxIn);
            this.addBlock(world, iblockstate2.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[11])), 7, 3, 11, structureBoundingBoxIn);

            if (flag) {
                BlockState iblockstate5 = Blocks.END_PORTAL.getDefaultState();
                this.addBlock(world, iblockstate5, 4, 3, 9, structureBoundingBoxIn);
                this.addBlock(world, iblockstate5, 5, 3, 9, structureBoundingBoxIn);
                this.addBlock(world, iblockstate5, 6, 3, 9, structureBoundingBoxIn);
                this.addBlock(world, iblockstate5, 4, 3, 10, structureBoundingBoxIn);
                this.addBlock(world, iblockstate5, 5, 3, 10, structureBoundingBoxIn);
                this.addBlock(world, iblockstate5, 6, 3, 10, structureBoundingBoxIn);
                this.addBlock(world, iblockstate5, 4, 3, 11, structureBoundingBoxIn);
                this.addBlock(world, iblockstate5, 5, 3, 11, structureBoundingBoxIn);
                this.addBlock(world, iblockstate5, 6, 3, 11, structureBoundingBoxIn);
            }

            i = this.applyYTransform(3);
            BlockPos blockpos = new BlockPos(this.applyXTransform(5, 6), i, this.applyZTransform(5, 6));

            if (structureBoundingBoxIn.contains(blockpos)) {
                world.setBlockState(blockpos, Blocks.SPAWNER.getDefaultState(), 2);
                BlockEntity tileentity = world.getBlockEntity(blockpos);

                if (tileentity instanceof MobSpawnerBlockEntity) {
                    ((MobSpawnerBlockEntity) tileentity).getLogic().setEntityId(getSpawnerEntity(random));
                }
            }

            return true;
        }
    }

    public static class Prison extends RSStrongholdPieces.Stronghold {
        public Prison(int componentType, Random p_i45576_2_, BlockBox p_i45576_3_, Direction p_i45576_4_, Type strongholdType) {
            super(StructurePieces.STRONGHOLD_PRISON, componentType, strongholdType);
            this.setOrientation(p_i45576_4_);
            this.entryDoor = this.getRandomDoor(p_i45576_2_);
            this.boundingBox = p_i45576_3_;
        }


        public Prison(StructureManager p_i50130_1_, CompoundTag p_i50130_2_) {
            super(StructurePieces.STRONGHOLD_PRISON, p_i50130_2_);
        }


        @Override
        public void placeJigsaw(StructurePiece component, List<StructurePiece> piecesList, Random rand) {
            this.getNextComponentNormal((RSStrongholdPieces.EntranceStairs) component, piecesList, rand, 1, 1);
        }


        public static RSStrongholdPieces.Prison createPiece(List<StructurePiece> p_175860_0_, Random p_175860_1_, int x, int y, int z, Direction p_175860_5_, int componentType, Type strongholdType) {
            BlockBox mutableboundingbox = BlockBox.rotated(x, y, z, -1, -1, 0, 9, 5, 11, p_175860_5_);
            return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.getOverlappingPiece(p_175860_0_, mutableboundingbox) == null ? new RSStrongholdPieces.Prison(componentType, p_175860_1_, mutableboundingbox, p_175860_5_, strongholdType) : null;
        }


        @Override
        public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox structureBoundingBoxIn, ChunkPos chunkPos, BlockPos blockPos) {
            RSStrongholdPieces.Stones randomStrongholdBlocks = new RSStrongholdPieces.Stones(this.strongholdType);
            this.fillWithOutline(world, structureBoundingBoxIn, 0, 0, 0, 8, 4, 10, false, random, randomStrongholdBlocks);
            this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 1, 1, 0);
            this.fillWithOutline(world, structureBoundingBoxIn, 1, 1, 10, 3, 3, 10, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithOutline(world, structureBoundingBoxIn, 4, 1, 1, 4, 3, 1, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 4, 1, 3, 4, 3, 3, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 4, 1, 7, 4, 3, 7, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 4, 1, 9, 4, 3, 9, false, random, randomStrongholdBlocks);
            this.fillWithOutline(world, structureBoundingBoxIn, 4, 1, 4, 4, 3, 6, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
            this.fillWithOutline(world, structureBoundingBoxIn, 5, 1, 5, 7, 3, 5, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
            this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), 4, 3, 2, structureBoundingBoxIn);
            this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), 4, 3, 8, structureBoundingBoxIn);
            BlockState iblockstate = Blocks.IRON_DOOR.getDefaultState().with(DoorBlock.FACING, Direction.WEST);
            BlockState iblockstate1 = Blocks.IRON_DOOR.getDefaultState().with(DoorBlock.FACING, Direction.WEST).with(DoorBlock.HALF, DoubleBlockHalf.UPPER);
            this.addBlock(world, iblockstate, 4, 1, 2, structureBoundingBoxIn);
            this.addBlock(world, iblockstate1, 4, 2, 2, structureBoundingBoxIn);
            this.addBlock(world, iblockstate, 4, 1, 8, structureBoundingBoxIn);
            this.addBlock(world, iblockstate1, 4, 2, 8, structureBoundingBoxIn);
            return true;
        }
    }

    public static class RightTurn extends RSStrongholdPieces.Turn {
        public RightTurn(int componentType, Random p_i50127_2_, BlockBox p_i50127_3_, Direction p_i50127_4_, Type strongholdType) {
            super(StructurePieces.STRONGHOLD_RIGHT_TURN, componentType, strongholdType);
            this.setOrientation(p_i50127_4_);
            this.entryDoor = this.getRandomDoor(p_i50127_2_);
            this.boundingBox = p_i50127_3_;
        }


        public RightTurn(StructureManager p_i50128_1_, CompoundTag p_i50128_2_) {
            super(StructurePieces.STRONGHOLD_RIGHT_TURN, p_i50128_2_);
        }


        @Override
        public void placeJigsaw(StructurePiece component, List<StructurePiece> piecesList, Random rand) {
            Direction enumfacing = this.getFacing();

            if (enumfacing != Direction.NORTH && enumfacing != Direction.EAST) {
                this.getNextComponentX((RSStrongholdPieces.EntranceStairs) component, piecesList, rand, 1, 1);
            } else {
                this.getNextComponentZ((RSStrongholdPieces.EntranceStairs) component, piecesList, rand, 1, 1);
            }

        }


        @Override
        public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox structureBoundingBoxIn, ChunkPos chunkPos, BlockPos blockPos) {
            RSStrongholdPieces.Stones randomStrongholdBlocks = new RSStrongholdPieces.Stones(this.strongholdType);
            this.fillWithOutline(world, structureBoundingBoxIn, 0, 0, 0, 4, 4, 4, false, random, randomStrongholdBlocks);
            this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 1, 1, 0);
            Direction enumfacing = this.getFacing();

            if (enumfacing != Direction.NORTH && enumfacing != Direction.EAST) {
                this.fillWithOutline(world, structureBoundingBoxIn, 0, 1, 1, 0, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            } else {
                this.fillWithOutline(world, structureBoundingBoxIn, 4, 1, 1, 4, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            }

            return true;
        }


        public static RSStrongholdPieces.RightTurn createPiece(List<StructurePiece> p_214824_0_, Random p_214824_1_, int x, int y, int z, Direction p_214824_5_, int componentType, Type strongholdType) {
            BlockBox mutableboundingbox = BlockBox.rotated(x, y, z, -1, -1, 0, 5, 5, 5, p_214824_5_);
            return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.getOverlappingPiece(p_214824_0_, mutableboundingbox) == null ? new RSStrongholdPieces.RightTurn(componentType, p_214824_1_, mutableboundingbox, p_214824_5_, strongholdType) : null;
        }
    }

    public static class RoomCrossing extends RSStrongholdPieces.Stronghold {
        protected int roomType;


        public RoomCrossing(int componentType, Random p_i45575_2_, BlockBox p_i45575_3_, Direction p_i45575_4_, Type strongholdType) {
            super(StructurePieces.STRONGHOLD_ROOM_CROSSING, componentType, strongholdType);
            this.setOrientation(p_i45575_4_);
            this.entryDoor = this.getRandomDoor(p_i45575_2_);
            this.boundingBox = p_i45575_3_;
            this.roomType = p_i45575_2_.nextInt(5);
        }


        public RoomCrossing(StructureManager p_i50125_1_, CompoundTag p_i50125_2_) {
            super(StructurePieces.STRONGHOLD_ROOM_CROSSING, p_i50125_2_);
            this.roomType = p_i50125_2_.getInt("Type");
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag tagCompound) {
            super.toNbt(tagCompound);
            tagCompound.putInt("Type", this.roomType);
        }


        @Override
        public void placeJigsaw(StructurePiece component, List<StructurePiece> piecesList, Random rand) {
            this.getNextComponentNormal((RSStrongholdPieces.EntranceStairs) component, piecesList, rand, 4, 1);
            this.getNextComponentX((RSStrongholdPieces.EntranceStairs) component, piecesList, rand, 1, 4);
            this.getNextComponentZ((RSStrongholdPieces.EntranceStairs) component, piecesList, rand, 1, 4);
        }


        public static RSStrongholdPieces.RoomCrossing createPiece(List<StructurePiece> p_175859_0_, Random p_175859_1_, int x, int y, int z, Direction p_175859_5_, int componentType, Type strongholdType) {
            BlockBox mutableboundingbox = BlockBox.rotated(x, y, z, -4, -1, 0, 11, 7, 11, p_175859_5_);
            return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.getOverlappingPiece(p_175859_0_, mutableboundingbox) == null ? new RSStrongholdPieces.RoomCrossing(componentType, p_175859_1_, mutableboundingbox, p_175859_5_, strongholdType) : null;
        }


        @Override
        public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox structureBoundingBoxIn, ChunkPos chunkPos, BlockPos blockPos) {
            RSStrongholdPieces.Stones randomStrongholdBlocks = new RSStrongholdPieces.Stones(this.strongholdType);
            this.fillWithOutline(world, structureBoundingBoxIn, 0, 0, 0, 10, 6, 10, false, random, randomStrongholdBlocks);
            this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 4, 1, 0);
            this.fillWithOutline(world, structureBoundingBoxIn, 4, 1, 10, 6, 3, 10, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithOutline(world, structureBoundingBoxIn, 0, 1, 4, 0, 3, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithOutline(world, structureBoundingBoxIn, 10, 1, 4, 10, 3, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            BlockPos blockpos;

            switch (this.roomType) {
                case 0:

                    blockpos = new BlockPos(this.applyXTransform(5, 5), this.applyYTransform(1), this.applyZTransform(5, 5));

                    if (structureBoundingBoxIn.contains(blockpos)) {

                        if (RepurposedStructures.RSStrongholdsConfig.allowExtraSpawnersSH) {
                            world.setBlockState(blockpos, Blocks.SPAWNER.getDefaultState(), 2);
                            BlockEntity tileentity = world.getBlockEntity(blockpos);

                            if (tileentity instanceof MobSpawnerBlockEntity) {
                                ((MobSpawnerBlockEntity) tileentity).getLogic().setEntityId(getSpawnerEntity(random));
                            }
                        } else {
                            world.setBlockState(blockpos, Blocks.STONE_BRICKS.getDefaultState(), 2);
                        }
                    }

                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 5, 2, 5, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 5, 3, 5, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.WEST), 4, 3, 5, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.EAST), 6, 3, 5, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.SOUTH), 5, 3, 4, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.NORTH), 5, 3, 6, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.STONE_SLAB.getDefaultState(), 4, 1, 4, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.STONE_SLAB.getDefaultState(), 4, 1, 5, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.STONE_SLAB.getDefaultState(), 4, 1, 6, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.STONE_SLAB.getDefaultState(), 6, 1, 4, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.STONE_SLAB.getDefaultState(), 6, 1, 5, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.STONE_SLAB.getDefaultState(), 6, 1, 6, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.STONE_SLAB.getDefaultState(), 5, 1, 4, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.STONE_SLAB.getDefaultState(), 5, 1, 6, structureBoundingBoxIn);
                    break;

                case 1:
                    for (int i1 = 0; i1 < 5; ++i1) {
                        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 3, 1, 3 + i1, structureBoundingBoxIn);
                        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 7, 1, 3 + i1, structureBoundingBoxIn);
                        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 3 + i1, 1, 3, structureBoundingBoxIn);
                        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 3 + i1, 1, 7, structureBoundingBoxIn);
                    }

                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 5, 2, 5, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 5, 3, 5, structureBoundingBoxIn);

                    blockpos = new BlockPos(this.applyXTransform(5, 5), this.applyYTransform(1), this.applyZTransform(5, 5));

                    if (structureBoundingBoxIn.contains(blockpos)) {
                        if (RepurposedStructures.RSStrongholdsConfig.allowExtraSpawnersSH) {
                            world.setBlockState(blockpos, Blocks.SPAWNER.getDefaultState(), 2);
                            BlockEntity tileentity = world.getBlockEntity(blockpos);

                            if (tileentity instanceof MobSpawnerBlockEntity) {
                                ((MobSpawnerBlockEntity) tileentity).getLogic().setEntityId(getSpawnerEntity(random));
                            }
                        } else {
                            world.setBlockState(blockpos, Blocks.STONE_BRICKS.getDefaultState(), 2);
                        }
                    }

                    this.addBlock(world, Blocks.WATER.getDefaultState(), 5, 4, 5, structureBoundingBoxIn);
                    break;

                case 2:
                case 3:

                    for (int i = 1; i <= 9; ++i) {
                        this.addBlock(world, Blocks.COBBLESTONE.getDefaultState(), 1, 3, i, structureBoundingBoxIn);
                        this.addBlock(world, Blocks.COBBLESTONE.getDefaultState(), 9, 3, i, structureBoundingBoxIn);
                    }

                    for (int j = 1; j <= 9; ++j) {
                        this.addBlock(world, Blocks.COBBLESTONE.getDefaultState(), j, 3, 1, structureBoundingBoxIn);
                        this.addBlock(world, Blocks.COBBLESTONE.getDefaultState(), j, 3, 9, structureBoundingBoxIn);
                    }

                    this.addBlock(world, Blocks.COBBLESTONE.getDefaultState(), 5, 1, 4, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.COBBLESTONE.getDefaultState(), 5, 1, 6, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.COBBLESTONE.getDefaultState(), 5, 3, 4, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.COBBLESTONE.getDefaultState(), 5, 3, 6, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.COBBLESTONE.getDefaultState(), 4, 1, 5, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.COBBLESTONE.getDefaultState(), 6, 1, 5, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.COBBLESTONE.getDefaultState(), 4, 3, 5, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.COBBLESTONE.getDefaultState(), 6, 3, 5, structureBoundingBoxIn);

                    for (int k = 1; k <= 3; ++k) {
                        this.addBlock(world, Blocks.COBBLESTONE.getDefaultState(), 4, k, 4, structureBoundingBoxIn);
                        this.addBlock(world, Blocks.COBBLESTONE.getDefaultState(), 6, k, 4, structureBoundingBoxIn);
                        this.addBlock(world, Blocks.COBBLESTONE.getDefaultState(), 4, k, 6, structureBoundingBoxIn);
                        this.addBlock(world, Blocks.COBBLESTONE.getDefaultState(), 6, k, 6, structureBoundingBoxIn);
                    }

                    blockpos = new BlockPos(this.applyXTransform(5, 5), this.applyYTransform(1), this.applyZTransform(5, 5));

                    if (structureBoundingBoxIn.contains(blockpos)) {

                        if (RepurposedStructures.RSStrongholdsConfig.allowExtraSpawnersSH) {
                            world.setBlockState(blockpos, Blocks.SPAWNER.getDefaultState(), 2);
                            BlockEntity tileentity = world.getBlockEntity(blockpos);

                            if (tileentity instanceof MobSpawnerBlockEntity) {
                                ((MobSpawnerBlockEntity) tileentity).getLogic().setEntityId(getSpawnerEntity(random));
                            }
                            this.addBlock(world, Blocks.TORCH.getDefaultState(), 5, 2, 5, structureBoundingBoxIn);
                        } else {
                            this.addBlock(world, Blocks.TORCH.getDefaultState(), 5, 1, 5, structureBoundingBoxIn);
                        }
                    }


                    for (int l = 2; l <= 8; ++l) {
                        this.addBlock(world, Blocks.OAK_PLANKS.getDefaultState(), 2, 3, l, structureBoundingBoxIn);
                        this.addBlock(world, Blocks.OAK_PLANKS.getDefaultState(), 3, 3, l, structureBoundingBoxIn);
                        if (l <= 3 || l >= 7) {
                            this.addBlock(world, Blocks.OAK_PLANKS.getDefaultState(), 4, 3, l, structureBoundingBoxIn);
                            this.addBlock(world, Blocks.OAK_PLANKS.getDefaultState(), 5, 3, l, structureBoundingBoxIn);
                            this.addBlock(world, Blocks.OAK_PLANKS.getDefaultState(), 6, 3, l, structureBoundingBoxIn);
                        }

                        this.addBlock(world, Blocks.OAK_PLANKS.getDefaultState(), 7, 3, l, structureBoundingBoxIn);
                        this.addBlock(world, Blocks.OAK_PLANKS.getDefaultState(), 8, 3, l, structureBoundingBoxIn);
                    }

                    BlockState iblockstate = Blocks.LADDER.getDefaultState().with(LadderBlock.FACING, Direction.WEST);
                    this.addBlock(world, iblockstate, 9, 1, 3, structureBoundingBoxIn);
                    this.addBlock(world, iblockstate, 9, 2, 3, structureBoundingBoxIn);
                    this.addBlock(world, iblockstate, 9, 3, 3, structureBoundingBoxIn);

                    if (RepurposedStructures.RSStrongholdsConfig.lootChestsSH) {
                        this.addChest(world, structureBoundingBoxIn, random, 3, 4, 8, getStorageChestLoot());
                        this.addChest(world, structureBoundingBoxIn, random, 5, 4, 2, getStorageChestLoot());
                        this.addChest(world, structureBoundingBoxIn, random, 6, 4, 8, getStorageChestLoot());
                        this.addChest(world, structureBoundingBoxIn, random, 8, 4, 4, getStorageChestLoot());
                    }
            }

            return true;
        }
    }

    public static class Stairs extends RSStrongholdPieces.Stronghold {
        private boolean source;


        public Stairs(StructurePieceType p_i50120_1_, int componentType, Random p_i50120_3_, int x, int z, Type strongholdType) {
            super(p_i50120_1_, componentType, strongholdType);
            this.source = true;
            this.setOrientation(Direction.Type.HORIZONTAL.random(p_i50120_3_));
            this.entryDoor = RSStrongholdPieces.Stronghold.Door.OPENING;
            if (this.getFacing().getAxis() == Direction.Axis.Z) {
                this.boundingBox = new BlockBox(x, 64, z, x + 5 - 1, 74, z + 5 - 1);
            } else {
                this.boundingBox = new BlockBox(x, 64, z, x + 5 - 1, 74, z + 5 - 1);
            }

        }


        public Stairs(int componentType, Random p_i45574_2_, BlockBox p_i45574_3_, Direction p_i45574_4_, Type strongholdType) {
            super(StructurePieces.STRONGHOLD_STAIRS, componentType, strongholdType);
            this.source = false;
            this.setOrientation(p_i45574_4_);
            this.entryDoor = this.getRandomDoor(p_i45574_2_);
            this.boundingBox = p_i45574_3_;
        }


        public Stairs(StructurePieceType p_i50121_1_, CompoundTag p_i50121_2_) {
            super(p_i50121_1_, p_i50121_2_);
            this.source = p_i50121_2_.getBoolean("Source");
        }


        public Stairs(StructureManager templateManager, CompoundTag data) {
            this(StructurePieces.STRONGHOLD_STAIRS, data);
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag tagCompound) {
            super.toNbt(tagCompound);
            tagCompound.putBoolean("Source", this.source);
        }


        @Override
        public void placeJigsaw(StructurePiece component, List<StructurePiece> piecesList, Random rand) {
            if (this.source) {
                RSStrongholdPieces.strongComponentType = RSStrongholdPieces.Crossing.class;
            }

            this.getNextComponentNormal((RSStrongholdPieces.EntranceStairs) component, piecesList, rand, 1, 1);
        }


        public static RSStrongholdPieces.Stairs createPiece(List<StructurePiece> p_175863_0_, Random p_175863_1_, int x, int y, int z, Direction p_175863_5_, int componentType, Type strongholdType) {
            BlockBox mutableboundingbox = BlockBox.rotated(x, y, z, -1, -7, 0, 5, 11, 5, p_175863_5_);
            return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.getOverlappingPiece(p_175863_0_, mutableboundingbox) == null ? new RSStrongholdPieces.Stairs(componentType, p_175863_1_, mutableboundingbox, p_175863_5_, strongholdType) : null;
        }


        @Override
        public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox structureBoundingBoxIn, ChunkPos chunkPos, BlockPos blockPos) {
            RSStrongholdPieces.Stones randomStrongholdBlocks = new RSStrongholdPieces.Stones(this.strongholdType);
            this.fillWithOutline(world, structureBoundingBoxIn, 0, 0, 0, 4, 10, 4, false, random, randomStrongholdBlocks);
            this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 1, 7, 0);
            this.placeDoor(world, random, structureBoundingBoxIn, RSStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 4);
            this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 2, 6, 1, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 1, 5, 1, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_SLAB.getDefaultState(), 1, 6, 1, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 1, 5, 2, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 1, 4, 3, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_SLAB.getDefaultState(), 1, 5, 3, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 2, 4, 3, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 3, 3, 3, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_SLAB.getDefaultState(), 3, 4, 3, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 3, 3, 2, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 3, 2, 1, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_SLAB.getDefaultState(), 3, 3, 1, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 2, 2, 1, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 1, 1, 1, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_SLAB.getDefaultState(), 1, 2, 1, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 1, 1, 2, structureBoundingBoxIn);
            this.addBlock(world, Blocks.STONE_SLAB.getDefaultState(), 1, 1, 3, structureBoundingBoxIn);
            return true;
        }
    }

    public static class EntranceStairs extends RSStrongholdPieces.Stairs {
        public RSStrongholdPieces.PieceWeight strongholdPieceWeight;
        public RSStrongholdPieces.PortalRoom strongholdPortalRoom;
        public List<StructurePiece> pendingChildren = Lists.<StructurePiece>newArrayList();


        public EntranceStairs(Random p_i50117_1_, int x, int z, Type strongholdType) {
            super(StructurePieces.STRONGHOLD_ENTRANCE_STAIRS, 0, p_i50117_1_, x, z, strongholdType);
        }


        public EntranceStairs(StructureManager p_i50118_1_, CompoundTag p_i50118_2_) {
            super(StructurePieces.STRONGHOLD_ENTRANCE_STAIRS, p_i50118_2_);
        }
    }

    public static class StairsStraight extends RSStrongholdPieces.Stronghold {
        public StairsStraight(int componentType, Random random, BlockBox box, Direction direction, Type strongholdType) {
            super(StructurePieces.STRONGHOLD_STAIRS_STRAIGHT, componentType, strongholdType);
            this.setOrientation(direction);
            this.entryDoor = this.getRandomDoor(random);
            this.boundingBox = box;
        }


        public StairsStraight(StructureManager tmeplateManager, CompoundTag data) {
            super(StructurePieces.STRONGHOLD_STAIRS_STRAIGHT, data);
        }


        @Override
        public void placeJigsaw(StructurePiece piece, List<StructurePiece> piecesList, Random random) {
            this.getNextComponentNormal((RSStrongholdPieces.EntranceStairs) piece, piecesList, random, 1, 1);
        }


        public static RSStrongholdPieces.StairsStraight createPiece(List<StructurePiece> piecesList, Random random, int x, int y, int z, Direction direction, int componentType, Type strongholdType) {
            BlockBox mutableboundingbox = BlockBox.rotated(x, y, z, -1, -7, 0, 5, 11, 8, direction);
            return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.getOverlappingPiece(piecesList, mutableboundingbox) == null ? new RSStrongholdPieces.StairsStraight(componentType, random, mutableboundingbox, direction, strongholdType) : null;
        }


        @Override
        public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox structureBoundingBoxIn, ChunkPos chunkPos, BlockPos blockPos) {
            RSStrongholdPieces.Stones randomStrongholdBlocks = new RSStrongholdPieces.Stones(this.strongholdType);
            this.fillWithOutline(world, structureBoundingBoxIn, 0, 0, 0, 4, 10, 7, false, random, randomStrongholdBlocks);
            this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 1, 7, 0);
            this.placeDoor(world, random, structureBoundingBoxIn, RSStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 7);
            BlockState iblockstate = Blocks.STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.SOUTH);

            for (int i = 0; i < 6; ++i) {
                this.addBlock(world, iblockstate, 1, 6 - i, 1 + i, structureBoundingBoxIn);
                this.addBlock(world, iblockstate, 2, 6 - i, 1 + i, structureBoundingBoxIn);
                this.addBlock(world, iblockstate, 3, 6 - i, 1 + i, structureBoundingBoxIn);

                if (i < 5) {
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 1, 5 - i, 1 + i, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 2, 5 - i, 1 + i, structureBoundingBoxIn);
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 3, 5 - i, 1 + i, structureBoundingBoxIn);
                }
            }

            return true;
        }
    }

    static class Stones extends StructurePiece.BlockRandomizer {
        private static final Map<BlockState, BlockState> INFESTED_STONE_LOOKUP;

        static {
            INFESTED_STONE_LOOKUP = new HashMap<BlockState, BlockState>();
            INFESTED_STONE_LOOKUP.put(Blocks.STONE_BRICKS.getDefaultState(), Blocks.INFESTED_STONE_BRICKS.getDefaultState());
            INFESTED_STONE_LOOKUP.put(Blocks.STONE.getDefaultState(), Blocks.INFESTED_STONE.getDefaultState());
            INFESTED_STONE_LOOKUP.put(Blocks.MOSSY_COBBLESTONE.getDefaultState(), Blocks.INFESTED_MOSSY_STONE_BRICKS.getDefaultState());
            INFESTED_STONE_LOOKUP.put(Blocks.CRACKED_STONE_BRICKS.getDefaultState(), Blocks.INFESTED_CRACKED_STONE_BRICKS.getDefaultState());
            INFESTED_STONE_LOOKUP.put(Blocks.COBBLESTONE.getDefaultState(), Blocks.INFESTED_COBBLESTONE.getDefaultState());
            INFESTED_STONE_LOOKUP.put(Blocks.CHISELED_STONE_BRICKS.getDefaultState(), Blocks.INFESTED_CHISELED_STONE_BRICKS.getDefaultState());
            INFESTED_STONE_LOOKUP.put(Blocks.MOSSY_STONE_BRICKS.getDefaultState(), Blocks.MOSSY_STONE_BRICKS.getDefaultState());
        }

        private Type type = null;

        private Stones(Type type) {
            this.type = type;
        }


        @Override
        public void setBlock(Random rand, int x, int y, int z, boolean notAir) {
            if (notAir) {
                if (type == Type.NETHER) {
                    float chance = rand.nextFloat();
                    if (chance < 0.05F) {
                        this.block = Blocks.MAGMA_BLOCK.getDefaultState();
                    } else if (chance < 0.15F) {
                        this.block = Blocks.BLACK_TERRACOTTA.getDefaultState();
                    } else if (chance < 0.35F) {
                        this.block = Blocks.RED_NETHER_BRICKS.getDefaultState();
                    } else {
                        this.block = Blocks.NETHER_BRICKS.getDefaultState();
                    }
                } else {
                    float chance = rand.nextFloat();
                    if (chance < 0.2F) {
                        this.block = Blocks.MOSSY_STONE_BRICKS.getDefaultState();
                    } else if (chance < 0.5F) {
                        this.block = Blocks.CRACKED_STONE_BRICKS.getDefaultState();
                    } else {
                        this.block = Blocks.STONE_BRICKS.getDefaultState();
                    }


                    chance = rand.nextFloat();
                    float silverfishThreshold = (float) (RepurposedStructures.RSStrongholdsConfig.stonebrick.silverfishSpawnrateSH / 100);
                    if (chance < silverfishThreshold) {
                        this.block = INFESTED_STONE_LOOKUP.get(this.block);
                    }
                }
            } else {
                this.block = Blocks.CAVE_AIR.getDefaultState();
            }
        }
    }

    public static class Straight extends RSStrongholdPieces.Stronghold {
        private boolean expandsX;
        private boolean expandsZ;


        public Straight(int conponentType_, Random random, BlockBox box, Direction direction, Type strongholdType) {
            super(StructurePieces.STRONGHOLD_STRAIGHT, conponentType_, strongholdType);
            this.setOrientation(direction);
            this.entryDoor = this.getRandomDoor(random);
            this.boundingBox = box;
            this.expandsX = random.nextInt(2) == 0;
            this.expandsZ = random.nextInt(2) == 0;
        }


        public Straight(StructureManager template, CompoundTag data) {
            super(StructurePieces.STRONGHOLD_STRAIGHT, data);
            this.expandsX = data.getBoolean("Left");
            this.expandsZ = data.getBoolean("Right");
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag tagCompound) {
            super.toNbt(tagCompound);
            tagCompound.putBoolean("Left", this.expandsX);
            tagCompound.putBoolean("Right", this.expandsZ);
        }


        @Override
        public void placeJigsaw(StructurePiece component, List<StructurePiece> piecesList, Random rand) {
            this.getNextComponentNormal((RSStrongholdPieces.EntranceStairs) component, piecesList, rand, 1, 1);

            if (this.expandsX) {
                this.getNextComponentX((RSStrongholdPieces.EntranceStairs) component, piecesList, rand, 1, 2);
            }

            if (this.expandsZ) {
                this.getNextComponentZ((RSStrongholdPieces.EntranceStairs) component, piecesList, rand, 1, 2);
            }
        }


        public static RSStrongholdPieces.Straight createPiece(List<StructurePiece> piecesList, Random random, int xStart, int yStart, int zStart, Direction direction, int distanceFromStart, Type strongholdType) {
            BlockBox mutableboundingbox = BlockBox.rotated(xStart, yStart, zStart, -1, -1, 0, 5, 5, 7, direction);
            return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.getOverlappingPiece(piecesList, mutableboundingbox) == null ? new RSStrongholdPieces.Straight(distanceFromStart, random, mutableboundingbox, direction, strongholdType) : null;
        }


        @Override
        public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox structureBoundingBoxIn, ChunkPos chunkPos, BlockPos blockPos) {
            RSStrongholdPieces.Stones randomStrongholdBlocks = new RSStrongholdPieces.Stones(this.strongholdType);
            this.fillWithOutline(world, structureBoundingBoxIn, 0, 0, 0, 4, 4, 6, false, random, randomStrongholdBlocks);
            this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 1, 1, 0);
            this.placeDoor(world, random, structureBoundingBoxIn, RSStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 6);
            BlockState iblockstate = Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.EAST);
            BlockState iblockstate1 = Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.WEST);
            this.addBlockWithRandomThreshold(world, structureBoundingBoxIn, random, 0.1F, 1, 2, 1, iblockstate);
            this.addBlockWithRandomThreshold(world, structureBoundingBoxIn, random, 0.1F, 3, 2, 1, iblockstate1);
            this.addBlockWithRandomThreshold(world, structureBoundingBoxIn, random, 0.1F, 1, 2, 5, iblockstate);
            this.addBlockWithRandomThreshold(world, structureBoundingBoxIn, random, 0.1F, 3, 2, 5, iblockstate1);

            if (this.expandsX) {
                this.fillWithOutline(world, structureBoundingBoxIn, 0, 1, 2, 0, 3, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            }

            if (this.expandsZ) {
                this.fillWithOutline(world, structureBoundingBoxIn, 4, 1, 2, 4, 3, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            }

            return true;
        }
    }

    abstract static class Stronghold extends StrongholdGenerator.Piece {
        protected RSStrongholdPieces.Stronghold.Door entryDoor = RSStrongholdPieces.Stronghold.Door.OPENING;
        protected Type strongholdType;


        protected Stronghold(StructurePieceType pieceType, int componentType, Type strongholdType) {
            super(pieceType, componentType);
            this.strongholdType = strongholdType;
        }


        public Stronghold(StructurePieceType pieceType, CompoundTag data) {
            super(pieceType, data);
            this.entryDoor = RSStrongholdPieces.Stronghold.Door.valueOf(data.getString("EntryDoor"));
            this.strongholdType = Type.byId(data.getInt("SHT"));
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag data) {
            data.putString("EntryDoor", this.entryDoor.name());
            data.putInt("SHT", this.strongholdType.ordinal());
        }


        protected void placeDoor(ServerWorldAccess world, Random random, BlockBox mutableBox, RSStrongholdPieces.Stronghold.Door spawnDoor, int xStart, int yStart, int zStart) {
            switch (spawnDoor) {
                case OPENING:
                    this.fillWithOutline(world, mutableBox, xStart, yStart, zStart, xStart + 3 - 1, yStart + 3 - 1, zStart, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
                    break;

                case WOOD_DOOR:
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), xStart, yStart, zStart, mutableBox);
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), xStart, yStart + 1, zStart, mutableBox);
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), xStart, yStart + 2, zStart, mutableBox);
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), xStart + 1, yStart + 2, zStart, mutableBox);
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), xStart + 2, yStart + 2, zStart, mutableBox);
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), xStart + 2, yStart + 1, zStart, mutableBox);
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), xStart + 2, yStart, zStart, mutableBox);
                    if (this.strongholdType == Type.NETHER) {
                        this.addBlock(world, Blocks.NETHER_BRICK_FENCE.getDefaultState(), xStart + 1, yStart, zStart, mutableBox);
                        this.addBlock(world, Blocks.NETHER_BRICK_FENCE.getDefaultState(), xStart + 1, yStart + 1, zStart, mutableBox);
                    } else {
                        this.addBlock(world, Blocks.OAK_DOOR.getDefaultState(), xStart + 1, yStart, zStart, mutableBox);
                        this.addBlock(world, Blocks.OAK_DOOR.getDefaultState().with(DoorBlock.HALF, DoubleBlockHalf.UPPER), xStart + 1, yStart + 1, zStart, mutableBox);
                    }
                    break;

                case GRATES:
                    this.addBlock(world, Blocks.AIR.getDefaultState(), xStart + 1, yStart, zStart, mutableBox);
                    this.addBlock(world, Blocks.AIR.getDefaultState(), xStart + 1, yStart + 1, zStart, mutableBox);
                    this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), xStart, yStart, zStart, mutableBox);
                    this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), xStart, yStart + 1, zStart, mutableBox);
                    this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), xStart, yStart + 2, zStart, mutableBox);
                    this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), xStart + 1, yStart + 2, zStart, mutableBox);
                    this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), xStart + 2, yStart + 2, zStart, mutableBox);
                    this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), xStart + 2, yStart + 1, zStart, mutableBox);
                    this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), xStart + 2, yStart, zStart, mutableBox);
                    break;

                case IRON_DOOR:
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), xStart, yStart, zStart, mutableBox);
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), xStart, yStart + 1, zStart, mutableBox);
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), xStart, yStart + 2, zStart, mutableBox);
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), xStart + 1, yStart + 2, zStart, mutableBox);
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), xStart + 2, yStart + 2, zStart, mutableBox);
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), xStart + 2, yStart + 1, zStart, mutableBox);
                    this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), xStart + 2, yStart, zStart, mutableBox);
                    this.addBlock(world, Blocks.IRON_DOOR.getDefaultState(), xStart + 1, yStart, zStart, mutableBox);
                    this.addBlock(world, Blocks.IRON_DOOR.getDefaultState().with(DoorBlock.HALF, DoubleBlockHalf.UPPER), xStart + 1, yStart + 1, zStart, mutableBox);
                    this.addBlock(world, Blocks.STONE_BUTTON.getDefaultState().with(HorizontalFacingBlock.FACING, Direction.NORTH), xStart + 2, yStart + 1, zStart + 1, mutableBox);
                    this.addBlock(world, Blocks.STONE_BUTTON.getDefaultState().with(HorizontalFacingBlock.FACING, Direction.SOUTH), xStart + 2, yStart + 1, zStart - 1, mutableBox);
            }
        }


        protected RSStrongholdPieces.Stronghold.Door getRandomDoor(Random random) {
            int i = random.nextInt(5);

            switch (i) {
                case 0:
                case 1:
                default:
                    return RSStrongholdPieces.Stronghold.Door.OPENING;

                case 2:
                    return RSStrongholdPieces.Stronghold.Door.WOOD_DOOR;

                case 3:
                    return RSStrongholdPieces.Stronghold.Door.GRATES;

                case 4:
                    return RSStrongholdPieces.Stronghold.Door.IRON_DOOR;
            }
        }


        protected BlockState getBlockOfCorrectType(BlockState blockstate) {
            if (this.strongholdType == Type.NETHER && NETHER_BLOCK_MAP.containsKey(blockstate.getBlock())) {
                BlockState newBlockstate = NETHER_BLOCK_MAP.get(blockstate.getBlock()).getDefaultState();

                for(Property<?> property : blockstate.getProperties()){
                    if(newBlockstate.contains(property)){
                        newBlockstate = syncBlockProperties(newBlockstate, blockstate, property);
                    }
                }

                return newBlockstate;
            }

            return blockstate;
        }

        private static <T extends Comparable<T>> BlockState syncBlockProperties(BlockState newBlock, BlockState oldBlock, Property<T> property){
            return newBlock.with(property, oldBlock.get(property));
        }


        protected void addBlock(WorldAccess world, BlockState block, int x, int y, int z, BlockBox blockBox) {
            block = getBlockOfCorrectType(block);
            BlockPos blockPos = new BlockPos(this.applyXTransform(x, z), this.applyYTransform(y), this.applyZTransform(x, z));
            if (blockBox.contains(blockPos)) {
                if (((StructurePieceAccessor)this).getMirror() != BlockMirror.NONE) {
                    block = block.mirror(((StructurePieceAccessor)this).getMirror());
                }

                if (((StructurePieceAccessor)this).getRotation() != BlockRotation.NONE) {
                    block = block.rotate(((StructurePieceAccessor)this).getRotation());
                }

                world.setBlockState(blockPos, block, 2);
                FluidState fluidState = world.getFluidState(blockPos);
                if (!fluidState.isEmpty()) {
                    world.getFluidTickScheduler().schedule(blockPos, fluidState.getFluid(), 0);
                }

                if (((StructurePieceAccessor)this).getBLOCKS_NEEDING_POST_PROCESSING().contains(block.getBlock())) {
                    world.getChunk(blockPos).markBlockForPostProcessing(blockPos);
                }
            }
        }

        protected EntityType<?> getSpawnerEntity(Random random) {
            if (this.strongholdType == Type.NETHER) {
                float chance = random.nextFloat();
                if(chance < 0.8){
                    return EntityType.BLAZE;
                }
                else if(chance <= 1){
                    return EntityType.ZOGLIN;
                }
                else{
                    //disabled until mojang fixes log spam with them
                    return EntityType.ZOMBIFIED_PIGLIN;
                }
            } else {
                return EntityType.SILVERFISH;
            }
        }


        protected Identifier getHallwayChestLoot() {
            if (this.strongholdType == Type.NETHER) {
                return new Identifier(RepurposedStructures.MODID + ":chests/stronghold_nether_hallway");
            } else {
                return LootTables.STRONGHOLD_CORRIDOR_CHEST;
            }
        }


        protected Identifier getStorageChestLoot() {
            if (this.strongholdType == Type.NETHER) {
                return new Identifier(RepurposedStructures.MODID + ":chests/stronghold_nether_storage_room");
            } else {
                return LootTables.STRONGHOLD_CORRIDOR_CHEST;
            }
        }


        protected StructurePiece getNextComponentNormal(RSStrongholdPieces.EntranceStairs p_74986_1_, List<StructurePiece> p_74986_2_, Random p_74986_3_, int p_74986_4_, int p_74986_5_) {
            Direction enumfacing = this.getFacing();

            if (enumfacing != null) {
                switch (enumfacing) {
                    case NORTH:
                        return RSStrongholdPieces.generateAndAddPiece(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX + p_74986_4_, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ - 1, enumfacing, this.getLength());

                    case SOUTH:
                        return RSStrongholdPieces.generateAndAddPiece(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX + p_74986_4_, this.boundingBox.minY + p_74986_5_, this.boundingBox.maxZ + 1, enumfacing, this.getLength());

                    case WEST:
                        return RSStrongholdPieces.generateAndAddPiece(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ + p_74986_4_, enumfacing, this.getLength());

                    case EAST:
                        return RSStrongholdPieces.generateAndAddPiece(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ + p_74986_4_, enumfacing, this.getLength());

                    default:
                        break;
                }
            }

            return null;
        }


        protected StructurePiece getNextComponentX(RSStrongholdPieces.EntranceStairs p_74989_1_, List<StructurePiece> p_74989_2_, Random p_74989_3_, int p_74989_4_, int p_74989_5_) {
            Direction enumfacing = this.getFacing();

            if (enumfacing != null) {
                switch (enumfacing) {
                    case NORTH:
                        return RSStrongholdPieces.generateAndAddPiece(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ + p_74989_5_, Direction.WEST, this.getLength());

                    case SOUTH:
                        return RSStrongholdPieces.generateAndAddPiece(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ + p_74989_5_, Direction.WEST, this.getLength());

                    case WEST:
                        return RSStrongholdPieces.generateAndAddPiece(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX + p_74989_5_, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ - 1, Direction.NORTH, this.getLength());

                    case EAST:
                        return RSStrongholdPieces.generateAndAddPiece(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX + p_74989_5_, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ - 1, Direction.NORTH, this.getLength());

                    default:
                        break;
                }
            }

            return null;
        }


        protected StructurePiece getNextComponentZ(RSStrongholdPieces.EntranceStairs p_74987_1_, List<StructurePiece> p_74987_2_, Random p_74987_3_, int p_74987_4_, int p_74987_5_) {
            Direction enumfacing = this.getFacing();

            if (enumfacing != null) {
                switch (enumfacing) {
                    case NORTH:
                        return RSStrongholdPieces.generateAndAddPiece(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74987_4_, this.boundingBox.minZ + p_74987_5_, Direction.EAST, this.getLength());

                    case SOUTH:
                        return RSStrongholdPieces.generateAndAddPiece(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74987_4_, this.boundingBox.minZ + p_74987_5_, Direction.EAST, this.getLength());

                    case WEST:
                        return RSStrongholdPieces.generateAndAddPiece(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.minX + p_74987_5_, this.boundingBox.minY + p_74987_4_, this.boundingBox.maxZ + 1, Direction.SOUTH, this.getLength());

                    case EAST:
                        return RSStrongholdPieces.generateAndAddPiece(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.minX + p_74987_5_, this.boundingBox.minY + p_74987_4_, this.boundingBox.maxZ + 1, Direction.SOUTH, this.getLength());

                    default:
                        break;
                }
            }

            return null;
        }


        protected static boolean canStrongholdGoDeeper(BlockBox box) {
            return box != null && box.minY > 10;
        }

        public static enum Door {
            OPENING, WOOD_DOOR, GRATES, IRON_DOOR;
        }
    }

    public abstract static class Turn extends RSStrongholdPieces.Stronghold {
        protected Turn(StructurePieceType pieceType, int componentType, Type strongholdType) {
            super(pieceType, componentType, strongholdType);
        }


        public Turn(StructurePieceType pieceType, CompoundTag data) {
            super(pieceType, data);
        }
    }
}
