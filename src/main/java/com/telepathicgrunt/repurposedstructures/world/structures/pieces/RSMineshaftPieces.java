package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructurePieces;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.block.enums.RailShape;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.ChestMinecartEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.List;
import java.util.Random;


public class RSMineshaftPieces {

    private static final Identifier ICY_CHEST_ID =  new Identifier(RepurposedStructures.MODID, "chests/mineshaft/icy");
    private static final Identifier JUNGLE_CHEST_ID =  new Identifier(RepurposedStructures.MODID, "chests/mineshaft/jungle");
    private static final Identifier TAIGA_CHEST_ID =  new Identifier(RepurposedStructures.MODID, "chests/mineshaft/taiga");
    private static final Identifier DESERT_CHEST_ID =  new Identifier(RepurposedStructures.MODID, "chests/mineshaft/desert");
    private static final Identifier END_CHEST_ID =  new Identifier(RepurposedStructures.MODID, "chests/mineshaft/end");
    private static final Identifier NETHER_CHEST_ID =  new Identifier(RepurposedStructures.MODID, "chests/mineshaft/nether");
    private static final Identifier OCEAN_CHEST_ID =  new Identifier(RepurposedStructures.MODID, "chests/mineshaft/ocean");
    private static final Identifier STONE_CHEST_ID =  new Identifier(RepurposedStructures.MODID, "chests/mineshaft/stone");
    private static final Identifier SAVANNA_CHEST_ID =  new Identifier(RepurposedStructures.MODID, "chests/mineshaft/savanna");
    private static final Identifier SWAMP_OR_DARK_FOREST_CHEST_ID =  new Identifier(RepurposedStructures.MODID, "chests/mineshaft/swamp_dark_forest");
    private static final Identifier BIRCH_CHEST_ID =  new Identifier(RepurposedStructures.MODID, "chests/mineshaft/birch");
    private static final Identifier ICY_SPAWNER_ID = new Identifier(RepurposedStructures.MODID, "mineshaft_icy");
    private static final Identifier BIRCH_SPAWNER_ID = new Identifier(RepurposedStructures.MODID, "mineshaft_birch");
    private static final Identifier JUNGLE_SPAWNER_ID = new Identifier(RepurposedStructures.MODID, "mineshaft_jungle");
    private static final Identifier TAIGA_SPAWNER_ID = new Identifier(RepurposedStructures.MODID, "mineshaft_taiga");
    private static final Identifier DESERT_SPAWNER_ID = new Identifier(RepurposedStructures.MODID, "mineshaft_desert");
    private static final Identifier STONE_SPAWNER_ID = new Identifier(RepurposedStructures.MODID, "mineshaft_stone");
    private static final Identifier SAVANNA_SPAWNER_ID = new Identifier(RepurposedStructures.MODID, "mineshaft_savanna");
    private static final Identifier SWAMPORDARKFOREST_SPAWNER_ID = new Identifier(RepurposedStructures.MODID, "mineshaft_swamp_or_dark_forest");
    private static final Identifier END_SPAWNER_ID = new Identifier(RepurposedStructures.MODID, "mineshaft_end");
    private static final Identifier NETHER_SPAWNER_ID = new Identifier(RepurposedStructures.MODID, "mineshaft_nether");
    private static final Identifier OCEAN_SPAWNER_ID = new Identifier(RepurposedStructures.MODID, "mineshaft_ocean");


    public enum Type {
        ICY, BIRCH, JUNGLE, TAIGA, DESERT, STONE, SAVANNA, SWAMPORDARKFOREST, END, NETHER, OCEAN;

        public static RSMineshaftPieces.Type byId(int id) {
            return id >= 0 && id < values().length ? values()[id] : BIRCH;
        }
    }

    private static RSMineshaftPieces.Piece createRandomShaftPiece(List<StructurePiece> p_189940_0_, Random p_189940_1_, int p_189940_2_, int p_189940_3_, int p_189940_4_, Direction p_189940_5_, int p_189940_6_, RSMineshaftPieces.Type type) {
        int i = p_189940_1_.nextInt(100);

        if (i >= 80) {
            BlockBox MutableBoundingBox = RSMineshaftPieces.Cross.findCrossing(p_189940_0_, p_189940_1_, p_189940_2_, p_189940_3_, p_189940_4_, p_189940_5_);

            if (MutableBoundingBox != null) {
                return new RSMineshaftPieces.Cross(p_189940_6_, MutableBoundingBox, p_189940_5_, type);
            }
        } else if (i >= 70) {
            BlockBox MutableBoundingBox1 = RSMineshaftPieces.Stairs.findStairs(p_189940_0_, p_189940_1_, p_189940_2_, p_189940_3_, p_189940_4_, p_189940_5_);

            if (MutableBoundingBox1 != null) {
                return new RSMineshaftPieces.Stairs(p_189940_6_, MutableBoundingBox1, p_189940_5_, type);
            }
        } else {
            BlockBox MutableBoundingBox2 = RSMineshaftPieces.Corridor.findCorridorSize(p_189940_0_, p_189940_1_, p_189940_2_, p_189940_3_, p_189940_4_, p_189940_5_);

            if (MutableBoundingBox2 != null) {
                return new RSMineshaftPieces.Corridor(p_189940_6_, p_189940_1_, MutableBoundingBox2, p_189940_5_, type);
            }
        }

        return null;
    }


    private static RSMineshaftPieces.Piece generateAndAddPiece(StructurePiece p_189938_0_, List<StructurePiece> p_189938_1_, Random p_189938_2_, int p_189938_3_, int p_189938_4_, int p_189938_5_, Direction p_189938_6_, int p_189938_7_) {
        if (p_189938_7_ > 8) {
            return null;
        } else if (Math.abs(p_189938_3_ - p_189938_0_.getBoundingBox().minX) <= 80 && Math.abs(p_189938_5_ - p_189938_0_.getBoundingBox().minZ) <= 80) {
            RSMineshaftPieces.Type mapgenmineshaft$type = ((RSMineshaftPieces.Piece) p_189938_0_).mineShaftType;
            RSMineshaftPieces.Piece structuremineshaftpieces$peice = createRandomShaftPiece(p_189938_1_, p_189938_2_, p_189938_3_, p_189938_4_, p_189938_5_, p_189938_6_, p_189938_7_ + 1, mapgenmineshaft$type);

            if (structuremineshaftpieces$peice != null) {
                p_189938_1_.add(structuremineshaftpieces$peice);
                structuremineshaftpieces$peice.placeJigsaw(p_189938_0_, p_189938_1_, p_189938_2_);
            }

            return structuremineshaftpieces$peice;
        } else {
            return null;
        }
    }

    public static class Corridor extends RSMineshaftPieces.Piece {
        private final boolean hasRails;
        private final boolean attemptSpawnerCreation;
        private boolean spawnerPlaced;
        private final int sectionCount;

        public Corridor(StructureManager p_i50456_1_, CompoundTag p_i50456_2_) {
            super(RSStructurePieces.MINESHAFT_CORRIDOR_RS, p_i50456_2_);
            this.hasRails = p_i50456_2_.getBoolean("hr");
            this.attemptSpawnerCreation = p_i50456_2_.getBoolean("sc");
            this.spawnerPlaced = p_i50456_2_.getBoolean("hps");
            this.sectionCount = p_i50456_2_.getInt("Num");
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag tagCompound) {
            super.toNbt(tagCompound);
            tagCompound.putBoolean("hr", this.hasRails);
            tagCompound.putBoolean("sc", this.attemptSpawnerCreation);
            tagCompound.putBoolean("hps", this.spawnerPlaced);
            tagCompound.putInt("Num", this.sectionCount);
        }


        public Corridor(int p_i47140_1_, Random p_i47140_2_, BlockBox p_i47140_3_, Direction p_i47140_4_, RSMineshaftPieces.Type p_i47140_5_) {
            super(RSStructurePieces.MINESHAFT_CORRIDOR_RS, p_i47140_1_, p_i47140_5_);
            this.setOrientation(p_i47140_4_);
            this.boundingBox = p_i47140_3_;
            this.hasRails = p_i47140_2_.nextInt(3) == 0;
            if (this.mineShaftType == RSMineshaftPieces.Type.END) {
                this.attemptSpawnerCreation = !this.hasRails && p_i47140_2_.nextInt(5) == 0;
            } else {
                this.attemptSpawnerCreation = !this.hasRails && p_i47140_2_.nextInt(20) == 0;
            }
            if (this.getFacing() != null && this.getFacing().getAxis() == Direction.Axis.Z) {
                this.sectionCount = p_i47140_3_.getBlockCountZ() / 5;
            } else {
                this.sectionCount = p_i47140_3_.getBlockCountX() / 5;
            }

        }


        public static BlockBox findCorridorSize(List<StructurePiece> p_175814_0_, Random rand, int x, int y, int z, Direction facing) {
            BlockBox MutableBoundingBox = new BlockBox(x, y, z, x, y + 2, z);
            int i;

            for (i = rand.nextInt(3) + 2; i > 0; --i) {
                int j = i * 5;

                switch (facing) {
                    case NORTH:
                    default:
                        MutableBoundingBox.maxX = x + 2;
                        MutableBoundingBox.minZ = z - (j - 1);
                        break;

                    case SOUTH:
                        MutableBoundingBox.maxX = x + 2;
                        MutableBoundingBox.maxZ = z + (j - 1);
                        break;

                    case WEST:
                        MutableBoundingBox.minX = x - (j - 1);
                        MutableBoundingBox.maxZ = z + 2;
                        break;

                    case EAST:
                        MutableBoundingBox.maxX = x + (j - 1);
                        MutableBoundingBox.maxZ = z + 2;
                }

                if (StructurePiece.getOverlappingPiece(p_175814_0_, MutableBoundingBox) == null) {
                    break;
                }
            }

            return i > 0 ? MutableBoundingBox : null;
        }


        @Override
        public void placeJigsaw(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            int i = this.getLength();
            int j = rand.nextInt(4);
            Direction enumfacing = this.getFacing();

            if (enumfacing != null) {
                switch (enumfacing) {
                    case NORTH:
                    default:
                        if (j <= 1) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ - 1, enumfacing, i);
                        } else if (j == 2) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, Direction.WEST, i);
                        } else {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, Direction.EAST, i);
                        }

                        break;

                    case SOUTH:
                        if (j <= 1) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ + 1, enumfacing, i);
                        } else if (j == 2) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ - 3, Direction.WEST, i);
                        } else {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ - 3, Direction.EAST, i);
                        }

                        break;

                    case WEST:
                        if (j <= 1) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, enumfacing, i);
                        } else if (j == 2) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ - 1, Direction.NORTH, i);
                        } else {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ + 1, Direction.SOUTH, i);
                        }

                        break;

                    case EAST:
                        if (j <= 1) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, enumfacing, i);
                        } else if (j == 2) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX - 3, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ - 1, Direction.NORTH, i);
                        } else {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX - 3, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ + 1, Direction.SOUTH, i);
                        }
                }
            }

            if (i < 8) {
                if (enumfacing != Direction.NORTH && enumfacing != Direction.SOUTH) {
                    for (int i1 = this.boundingBox.minX + 3; i1 + 3 <= this.boundingBox.maxX; i1 += 5) {
                        int j1 = rand.nextInt(5);

                        if (j1 == 0) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, i1, this.boundingBox.minY, this.boundingBox.minZ - 1, Direction.NORTH, i + 1);
                        } else if (j1 == 1) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, i1, this.boundingBox.minY, this.boundingBox.maxZ + 1, Direction.SOUTH, i + 1);
                        }
                    }
                } else {
                    for (int k = this.boundingBox.minZ + 3; k + 3 <= this.boundingBox.maxZ; k += 5) {
                        int l = rand.nextInt(5);

                        if (l == 0) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, k, Direction.WEST, i + 1);
                        } else if (l == 1) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, k, Direction.EAST, i + 1);
                        }
                    }
                }
            }
        }


        @Override
        protected boolean addChest(StructureWorldAccess world, BlockBox boundingBox, Random random, int x, int y, int z, Identifier lootTableId) {
            BlockPos blockpos = new BlockPos(this.applyXTransform(x, z), this.applyYTransform(y), this.applyZTransform(x, z));
            Material currentMaterial = world.getBlockState(blockpos).getMaterial();

            if (boundingBox.contains(blockpos) && (currentMaterial == Material.AIR || currentMaterial == Material.WATER)) {
                BlockState blockstate;
                if (currentMaterial == Material.AIR) {
                    blockstate = Blocks.RAIL.getDefaultState().with(RailBlock.SHAPE, random.nextBoolean() ? RailShape.NORTH_SOUTH : RailShape.EAST_WEST);
                } else {
                    blockstate = Blocks.OAK_TRAPDOOR.getDefaultState().with(Properties.WATERLOGGED, true);
                }

                this.addBlock(world, blockstate, x, y, z, boundingBox);
                ChestMinecartEntity entityminecartchest = new ChestMinecartEntity(world.toServerWorld(), blockpos.getX() + 0.5F, blockpos.getY() + 0.5F, blockpos.getZ() + 0.5F);
                entityminecartchest.setLootTable(lootTableId, random.nextLong());
                world.spawnEntity(entityminecartchest);
                return true;
            } else {
                return false;
            }
        }


        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox box, ChunkPos chunkPos, BlockPos blockPos) {
            boolean isOceanType = this.mineShaftType == RSMineshaftPieces.Type.OCEAN;
            if (isOceanType ? this.isAirInStructureBoundingBox(world, box) : this.method_14937(world, box)) {
                return false;
            } else {
                int offsetInSection = this.sectionCount * 5 - 1;
                BlockState iblockstate = this.getFloorBlock();
                this.fillWithOutline(world, box, 0, 0, 0, 2, 1, offsetInSection, getFillingBlock(), getFillingBlock(), false);
                this.fillWithOutlineUnderSeaLevel(world, box, random, 0.8F, 0, 2, 0, 2, 2, offsetInSection, getFillingBlock(), getFillingBlock(), false, false);

                if (this.attemptSpawnerCreation) {
                    if (isOceanType || this.mineShaftType == RSMineshaftPieces.Type.NETHER || this.mineShaftType == RSMineshaftPieces.Type.END) {
                        this.fillWithOutlineUnderSeaLevel(world, box, random, 0.6F, 0, 0, 0, 2, 0, offsetInSection, getDecorativeBlock(random), getDecorativeBlock(random), false, true);

                        // can only place chorus fruit on end stone
                        for (int x = 0; x <= 2; ++x) {
                            for (int z = 0; z <= offsetInSection; ++z) {
                                if (this.getBlockAt(world, x, 0, z, box).isOf(Blocks.CHORUS_FLOWER)) {
                                    this.addBlock(world, Blocks.END_STONE.getDefaultState(), x, -1, z, box);
                                }
                            }
                        }

                    } else {
                        this.fillWithOutlineUnderSeaLevel(world, box, random, 0.6F, 0, 0, 0, 2, 1, offsetInSection, getDecorativeBlock(random), getFillingBlock(), false, true);
                    }
                }

                for (int j1 = 0; j1 < this.sectionCount; ++j1) {
                    int k1 = 2 + j1 * 5;
                    this.placeSupport(world, box, 0, 0, k1, 2, 2, random);
                    this.placeDecoration(world, box, random, 0.1F, 0, 2, k1 - 1);
                    this.placeDecoration(world, box, random, 0.1F, 2, 2, k1 - 1);
                    this.placeDecoration(world, box, random, 0.1F, 0, 2, k1 + 1);
                    this.placeDecoration(world, box, random, 0.1F, 2, 2, k1 + 1);
                    this.placeDecoration(world, box, random, 0.05F, 0, 2, k1 - 2);
                    this.placeDecoration(world, box, random, 0.05F, 2, 2, k1 - 2);
                    this.placeDecoration(world, box, random, 0.05F, 0, 2, k1 + 2);
                    this.placeDecoration(world, box, random, 0.05F, 2, 2, k1 + 2);

                    if (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.lootChestsMS) {
                        if (random.nextInt(50) == 0) {
                            this.addChest(world, box, random, 2, 0, k1 - 1, getChestLoot());
                        }

                        if (random.nextInt(50) == 0) {
                            this.addChest(world, box, random, 0, 0, k1 + 1, getChestLoot());
                        }
                    }

                    if (this.attemptSpawnerCreation && (!this.spawnerPlaced || (this.mineShaftType == RSMineshaftPieces.Type.END && random.nextBoolean()))) {
                        int l1 = this.applyYTransform(0);
                        int i2 = k1 - 1 + random.nextInt(3);
                        int j2 = this.applyXTransform(1, i2);
                        int k2 = this.applyZTransform(1, i2);
                        BlockPos blockpos = new BlockPos(j2, l1, k2);

                        if (box.contains(blockpos) && this.isUnderSeaLevel(world, 1, 0, i2, box)) {
                            this.spawnerPlaced = true;
                            world.setBlockState(blockpos, Blocks.SPAWNER.getDefaultState(), 2);
                            BlockEntity tileentity = world.getBlockEntity(blockpos);

                            if (tileentity instanceof MobSpawnerBlockEntity) {
                                ((MobSpawnerBlockEntity) tileentity).getLogic().setEntityId(getSpawnerMob(random));
                            }
                        }
                    }

                    //wall of glass
                    if (this.mineShaftType == RSMineshaftPieces.Type.END && random.nextFloat() < 0.3f) {
                        this.fillWithOutline(world, box, 0, 0, 0, 2, 2, 0, Blocks.PURPLE_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true), Blocks.PURPLE_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, true).with(PaneBlock.SOUTH, true), false);
                    }
                }

                for (int x = 0; x <= 2; ++x) {
                    for (int z = 0; z <= offsetInSection; ++z) {
                        BlockState spaceForFloor = this.getBlockAt(world, x, -1, z, box);

                        if (isOceanType ? spaceForFloor.getMaterial() == Material.WATER : spaceForFloor.getMaterial() == Material.AIR) {
                            this.addBlock(world, iblockstate, x, -1, z, box);
                        }
                    }
                }

                if (this.hasRails) {
                    BlockState blockstate = getRailBlock();

                    for (int j3 = 0; j3 <= offsetInSection; ++j3) {
                        BlockState spaceForRails = this.getBlockAt(world, 1, -1, j3, box);

                        if (isOceanType ? spaceForRails.getMaterial() != Material.WATER : spaceForRails.getMaterial() != Material.AIR) {
                            float f = this.isUnderSeaLevel(world, 1, 0, j3, box) ? 0.7F : 0.9F;
                            this.addBlockWithRandomThreshold(world, box, random, f, 1, 0, j3, blockstate);
                        }
                    }
                }

                if (this.mineShaftType == RSMineshaftPieces.Type.JUNGLE) {
                    fillWithVines(world, random, box, 5, 0, 0, 0, 2, 2, offsetInSection);
                } else if (this.mineShaftType == RSMineshaftPieces.Type.SWAMPORDARKFOREST) {
                    fillWithVines(world, random, box, 2, 0, 0, 0, 2, 2, offsetInSection);
                }

                return true;
            }
        }


        private void placeSupport(StructureWorldAccess world, BlockBox boundingBox, int x, int y2, int z, int y, int x2, Random random) {

            BlockState iblockstate = this.getArchTopBlock();
            BlockState iblockstate2 = getFillingBlock();
            this.fillWithOutline(world, boundingBox, x, y2, z, x, y - 1, z, this.getArchSupportBlock(random), iblockstate2, false);
            this.fillWithOutline(world, boundingBox, x2, y2, z, x2, y - 1, z, this.getArchSupportBlock(random), iblockstate2, false);
            this.fillWithOutline(world, boundingBox, x, y, z, x2, y, z, iblockstate, iblockstate2, false);

            if (this.mineShaftType == Type.END) {
                if (random.nextFloat() < 0.08F) {
                    this.addBlockWithRandomThreshold(world, boundingBox, random, 1F, x, y, z - 1, Blocks.END_ROD.getDefaultState().with(FacingBlock.FACING, Direction.SOUTH));
                    this.addBlockWithRandomThreshold(world, boundingBox, random, 1F, x, y, z + 1, Blocks.END_ROD.getDefaultState().with(FacingBlock.FACING, Direction.NORTH));
                }

                if (random.nextFloat() < 0.08F) {
                    this.addBlockWithRandomThreshold(world, boundingBox, random, 1F, x + 2, y, z - 1, Blocks.END_ROD.getDefaultState().with(FacingBlock.FACING, Direction.SOUTH));
                    this.addBlockWithRandomThreshold(world, boundingBox, random, 1F, x + 2, y, z + 1, Blocks.END_ROD.getDefaultState().with(FacingBlock.FACING, Direction.NORTH));
                }
            } else if (this.mineShaftType == Type.NETHER) {
                if (random.nextFloat() < 0.3f) {
                    this.addBlockWithRandomThreshold(world, boundingBox, random, 0.45F, x + 1, y, z, Blocks.SHROOMLIGHT.getDefaultState());
                } else {
                    this.addBlockWithRandomThreshold(world, boundingBox, random, 0.1F, x + 1, y, z - 1, Blocks.SOUL_WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.SOUTH));
                    this.addBlockWithRandomThreshold(world, boundingBox, random, 0.1F, x + 1, y, z + 1, Blocks.SOUL_WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.NORTH));
                }
            } else if (this.mineShaftType == Type.OCEAN) {
                this.addBlockWithRandomThreshold(world, boundingBox, random, 0.2F, x + 1, y, z, Blocks.SEA_LANTERN.getDefaultState());
            } else if (this.mineShaftType == Type.ICY) {
                this.addBlockWithRandomThreshold(world, boundingBox, random, 0.08F, x + 1, y, z - 1, Blocks.REDSTONE_WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.SOUTH));
                this.addBlockWithRandomThreshold(world, boundingBox, random, 0.08F, x + 1, y, z + 1, Blocks.REDSTONE_WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.NORTH));
            } else {
                this.addBlockWithRandomThreshold(world, boundingBox, random, 0.08F, x + 1, y, z - 1, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.SOUTH));
                this.addBlockWithRandomThreshold(world, boundingBox, random, 0.08F, x + 1, y, z + 1, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.NORTH));
            }

        }


        private void placeDecoration(StructureWorldAccess world, BlockBox box, Random random, float probability, int x, int y, int z) {
            if (world.getRegistryManager().get(Registry.DIMENSION_TYPE_KEY).getId(world.getDimension()) != DimensionType.OVERWORLD_ID || this.isUnderSeaLevel(world, x, y, z, box)) {
                BlockState decorativeBlock = getDecorativeBlock(random);
                if (!decorativeBlock.isOf(Blocks.COBWEB)) {
                    y = 0;
                }
                this.addBlockWithRandomThreshold(world, box, random, probability, x, y, z, decorativeBlock);

                // can only place chorus fruit on end stone
                //Also places it in a grown state
                if (this.getBlockAt(world, x, y, z, box).isOf(Blocks.CHORUS_FLOWER)) {
                    this.addBlock(world, Blocks.END_STONE.getDefaultState(), x, -1, z, box);
                    this.addBlock(world, Blocks.CHORUS_PLANT.getDefaultState().with(ConnectingBlock.UP, true).with(ConnectingBlock.DOWN, true), x, 0, z, box);
                    this.addBlock(world, Blocks.CHORUS_PLANT.getDefaultState().with(ConnectingBlock.SOUTH, true).with(ConnectingBlock.NORTH, true).with(ConnectingBlock.EAST, true).with(ConnectingBlock.WEST, true).with(ConnectingBlock.DOWN, true), x, 1, z, box);

                    if (random.nextBoolean()) {
                        if (this.getBlockAt(world, x - 1, 1, z, box).isAir()) {
                            this.addBlock(world, Blocks.CHORUS_FLOWER.getDefaultState().with(ChorusFlowerBlock.AGE, 5), x - 1, 1, z, box);
                        } else {
                            this.addBlock(world, Blocks.CHORUS_FLOWER.getDefaultState().with(ChorusFlowerBlock.AGE, 5), x, 1, z, box);
                        }
                    } else {
                        if (this.getBlockAt(world, x + 1, 1, z, box).isAir()) {
                            this.addBlock(world, Blocks.CHORUS_FLOWER.getDefaultState().with(ChorusFlowerBlock.AGE, 5), x + 1, 1, z, box);
                        } else {
                            this.addBlock(world, Blocks.CHORUS_FLOWER.getDefaultState().with(ChorusFlowerBlock.AGE, 5), x, 1, z, box);
                        }
                    }
                }
            }
        }

    }

    public static class Cross extends RSMineshaftPieces.Piece {
        private final Direction corridorDirection;
        private final boolean isMultipleFloors;

        public Cross(StructureManager p_i50454_1_, CompoundTag p_i50454_2_) {
            super(RSStructurePieces.MINESHAFT_CROSSING_RS, p_i50454_2_);
            this.isMultipleFloors = p_i50454_2_.getBoolean("tf");
            this.corridorDirection = Direction.fromHorizontal(p_i50454_2_.getInt("D"));
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag tagCompound) {
            super.toNbt(tagCompound);
            tagCompound.putBoolean("tf", this.isMultipleFloors);
            tagCompound.putInt("D", this.corridorDirection.getHorizontal());
        }


        public Cross(int p_i50455_1_, BlockBox p_i50455_2_, Direction p_i50455_3_, RSMineshaftPieces.Type p_i50455_4_) {
            super(RSStructurePieces.MINESHAFT_CROSSING_RS, p_i50455_1_, p_i50455_4_);
            this.corridorDirection = p_i50455_3_;
            this.boundingBox = p_i50455_2_;
            this.isMultipleFloors = p_i50455_2_.getBlockCountY() > 3;
        }


        public static BlockBox findCrossing(List<StructurePiece> listIn, Random rand, int x, int y, int z, Direction facing) {
            BlockBox MutableBoundingBox = new BlockBox(x, y, z, x, y + 2, z);

            if (rand.nextInt(4) == 0) {
                MutableBoundingBox.maxY += 4;
            }

            switch (facing) {
                case NORTH:
                default:
                    MutableBoundingBox.minX = x - 1;
                    MutableBoundingBox.maxX = x + 3;
                    MutableBoundingBox.minZ = z - 4;
                    break;

                case SOUTH:
                    MutableBoundingBox.minX = x - 1;
                    MutableBoundingBox.maxX = x + 3;
                    MutableBoundingBox.maxZ = z + 3 + 1;
                    break;

                case WEST:
                    MutableBoundingBox.minX = x - 4;
                    MutableBoundingBox.minZ = z - 1;
                    MutableBoundingBox.maxZ = z + 3;
                    break;

                case EAST:
                    MutableBoundingBox.maxX = x + 3 + 1;
                    MutableBoundingBox.minZ = z - 1;
                    MutableBoundingBox.maxZ = z + 3;
            }

            return StructurePiece.getOverlappingPiece(listIn, MutableBoundingBox) != null ? null : MutableBoundingBox;
        }


        @Override
        public void placeJigsaw(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            int i = this.getLength();

            switch (this.corridorDirection) {
                case NORTH:
                default:
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, Direction.NORTH, i);
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, Direction.WEST, i);
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, Direction.EAST, i);
                    break;

                case SOUTH:
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, Direction.SOUTH, i);
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, Direction.WEST, i);
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, Direction.EAST, i);
                    break;

                case WEST:
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, Direction.NORTH, i);
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, Direction.SOUTH, i);
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, Direction.WEST, i);
                    break;

                case EAST:
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, Direction.NORTH, i);
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, Direction.SOUTH, i);
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, Direction.EAST, i);
            }

            if (this.isMultipleFloors) {
                if (rand.nextBoolean()) {
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ - 1, Direction.NORTH, i);
                }

                if (rand.nextBoolean()) {
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ + 1, Direction.WEST, i);
                }

                if (rand.nextBoolean()) {
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ + 1, Direction.EAST, i);
                }

                if (rand.nextBoolean()) {
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.maxZ + 1, Direction.SOUTH, i);
                }
            }
        }


        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox box, ChunkPos chunkPos, BlockPos blockPos) {
            boolean isOceanType = this.mineShaftType == RSMineshaftPieces.Type.OCEAN;
            if (isOceanType ? this.isAirInStructureBoundingBox(world, box) : this.method_14937(world, box)) {
                return false;
            } else {
                BlockState iblockstate = this.getFloorBlock();

                if (this.isMultipleFloors) {
                    this.fillWithOutline(world, box, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.minY + 3 - 1, this.boundingBox.maxZ, getFillingBlock(), getFillingBlock(), false);
                    this.fillWithOutline(world, box, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.minY + 3 - 1, this.boundingBox.maxZ - 1, getFillingBlock(), getFillingBlock(), false);
                    this.fillWithOutline(world, box, this.boundingBox.minX + 1, this.boundingBox.maxY - 2, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.maxZ, getFillingBlock(), getFillingBlock(), false);
                    this.fillWithOutline(world, box, this.boundingBox.minX, this.boundingBox.maxY - 2, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ - 1, getFillingBlock(), getFillingBlock(), false);
                    this.fillWithOutline(world, box, this.boundingBox.minX + 1, this.boundingBox.minY + 3, this.boundingBox.minZ + 1, this.boundingBox.maxX - 1, this.boundingBox.minY + 3, this.boundingBox.maxZ - 1, getFillingBlock(), getFillingBlock(), false);
                } else {
                    this.fillWithOutline(world, box, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.maxZ, getFillingBlock(), getFillingBlock(), false);
                    this.fillWithOutline(world, box, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ - 1, getFillingBlock(), getFillingBlock(), false);
                }

                this.placeSupportPillar(world, box, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxY, isOceanType);
                this.placeSupportPillar(world, box, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 1, this.boundingBox.maxY, isOceanType);
                this.placeSupportPillar(world, box, this.boundingBox.maxX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxY, isOceanType);
                this.placeSupportPillar(world, box, this.boundingBox.maxX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 1, this.boundingBox.maxY, isOceanType);

                for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; ++i) {
                    for (int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; ++j) {
                        if (this.getBlockAt(world, i, this.boundingBox.minY - 1, j, box).getMaterial() == (isOceanType ? Material.WATER : Material.AIR) && this.isUnderSeaLevel(world, i, this.boundingBox.minY - 1, j, box)) {
                            this.addBlock(world, iblockstate, i, this.boundingBox.minY - 1, j, box);
                        }
                    }
                }

                if (this.mineShaftType == RSMineshaftPieces.Type.JUNGLE) {
                    fillWithVines(world, random, box, 5, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ);
                } else if (this.mineShaftType == RSMineshaftPieces.Type.SWAMPORDARKFOREST) {
                    fillWithVines(world, random, box, 2, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ);
                }

                return true;
            }
        }


        private void placeSupportPillar(StructureWorldAccess world, BlockBox box, int x, int miny, int z, int maxy, boolean isOceanType) {
            if (this.getBlockAt(world, x, maxy + 1, z, box).getMaterial() != (isOceanType ? Material.WATER : Material.AIR)) {
                this.fillWithOutline(world, box, x, miny, z, x, maxy, z, this.getFloorBlock().isOf(Blocks.GRASS_BLOCK) ? Blocks.MOSSY_STONE_BRICKS.getDefaultState() : this.getFloorBlock(), getFillingBlock(), false);
            }
        }
    }

    public static class Room extends RSMineshaftPieces.Piece {
        private final List<BlockBox> roomsLinkedToTheRoom = Lists.newLinkedList();

        public Room(int p_i47137_1_, Random p_i47137_2_, int p_i47137_3_, int p_i47137_4_, RSMineshaftPieces.Type p_i47137_5_) {
            super(RSStructurePieces.MINESHAFT_ROOM_RS, p_i47137_1_, p_i47137_5_);
            this.mineShaftType = p_i47137_5_;
            this.boundingBox = new BlockBox(p_i47137_3_, 50, p_i47137_4_, p_i47137_3_ + 7 + p_i47137_2_.nextInt(6), 54 + p_i47137_2_.nextInt(6), p_i47137_4_ + 7 + p_i47137_2_.nextInt(6));
        }


        public Room(StructureManager p_i50451_1_, CompoundTag p_i50451_2_) {
            super(RSStructurePieces.MINESHAFT_ROOM_RS, p_i50451_2_);
            ListTag listnbt = p_i50451_2_.getList("Entrances", 11);

            for (int i = 0; i < listnbt.size(); ++i) {
                this.roomsLinkedToTheRoom.add(new BlockBox(listnbt.getIntArray(i)));
            }
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag tagCompound) {
            super.toNbt(tagCompound);
            ListTag listnbt = new ListTag();

            for (BlockBox mutableboundingbox : this.roomsLinkedToTheRoom) {
                listnbt.add(mutableboundingbox.toNbt());
            }

            tagCompound.put("Entrances", listnbt);
        }


        @Override
        public void placeJigsaw(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            int i = this.getLength();
            int k = this.boundingBox.getBlockCountY() - 3 - 1;
            if (k <= 0) {
                k = 1;
            }

            int l;
            for (int j = 0; j < this.boundingBox.getBlockCountX(); j = l + 4) {
                l = j + rand.nextInt(this.boundingBox.getBlockCountX());
                if (l + 3 > this.boundingBox.getBlockCountX()) {
                    break;
                }

                RSMineshaftPieces.Piece structuremineshaftpieces$peice = RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + l, this.boundingBox.minY + rand.nextInt(k) + 1, this.boundingBox.minZ - 1, Direction.NORTH, i);

                if (structuremineshaftpieces$peice != null) {
                    BlockBox MutableBoundingBox = structuremineshaftpieces$peice.getBoundingBox();
                    this.roomsLinkedToTheRoom.add(new BlockBox(MutableBoundingBox.minX, MutableBoundingBox.minY, this.boundingBox.minZ, MutableBoundingBox.maxX, MutableBoundingBox.maxY, this.boundingBox.minZ + 1));
                }
            }

            for (int i1 = 0; i1 < this.boundingBox.getBlockCountX(); i1 = l + 4) {
                l = i1 + rand.nextInt(this.boundingBox.getBlockCountX());

                if (l + 3 > this.boundingBox.getBlockCountX()) {
                    break;
                }

                RSMineshaftPieces.Piece structuremineshaftpieces$peice1 = RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + l, this.boundingBox.minY + rand.nextInt(k) + 1, this.boundingBox.maxZ + 1, Direction.SOUTH, i);

                if (structuremineshaftpieces$peice1 != null) {
                    BlockBox MutableBoundingBox1 = structuremineshaftpieces$peice1.getBoundingBox();
                    this.roomsLinkedToTheRoom.add(new BlockBox(MutableBoundingBox1.minX, MutableBoundingBox1.minY, this.boundingBox.maxZ - 1, MutableBoundingBox1.maxX, MutableBoundingBox1.maxY, this.boundingBox.maxZ));
                }
            }

            for (int j1 = 0; j1 < this.boundingBox.getBlockCountZ(); j1 = l + 4) {
                l = j1 + rand.nextInt(this.boundingBox.getBlockCountZ());

                if (l + 3 > this.boundingBox.getBlockCountZ()) {
                    break;
                }

                RSMineshaftPieces.Piece structuremineshaftpieces$peice2 = RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY + rand.nextInt(k) + 1, this.boundingBox.minZ + l, Direction.WEST, i);

                if (structuremineshaftpieces$peice2 != null) {
                    BlockBox MutableBoundingBox2 = structuremineshaftpieces$peice2.getBoundingBox();
                    this.roomsLinkedToTheRoom.add(new BlockBox(this.boundingBox.minX, MutableBoundingBox2.minY, MutableBoundingBox2.minZ, this.boundingBox.minX + 1, MutableBoundingBox2.maxY, MutableBoundingBox2.maxZ));
                }
            }

            for (int k1 = 0; k1 < this.boundingBox.getBlockCountZ(); k1 = l + 4) {
                l = k1 + rand.nextInt(this.boundingBox.getBlockCountZ());

                if (l + 3 > this.boundingBox.getBlockCountZ()) {
                    break;
                }

                StructurePiece StructurePiece = RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + rand.nextInt(k) + 1, this.boundingBox.minZ + l, Direction.EAST, i);

                if (StructurePiece != null) {
                    BlockBox MutableBoundingBox3 = StructurePiece.getBoundingBox();
                    this.roomsLinkedToTheRoom.add(new BlockBox(this.boundingBox.maxX - 1, MutableBoundingBox3.minY, MutableBoundingBox3.minZ, this.boundingBox.maxX, MutableBoundingBox3.maxY, MutableBoundingBox3.maxZ));
                }
            }
        }


        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox box, ChunkPos chunkPos, BlockPos blockPos) {
            BlockState flooring;

            if (this.mineShaftType == RSMineshaftPieces.Type.NETHER) {
                flooring = Blocks.SOUL_SAND.getDefaultState();
            } else if (this.mineShaftType == RSMineshaftPieces.Type.END) {
                flooring = Blocks.END_STONE_BRICKS.getDefaultState();
            } else if (this.mineShaftType == RSMineshaftPieces.Type.OCEAN) {
                flooring = Blocks.CLAY.getDefaultState();
            } else {
                flooring = getFloorBlock().getMaterial() == Material.WOOD ? Blocks.COARSE_DIRT.getDefaultState() : getFloorBlock();
            }

            this.fillWithOutline(world, box, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.minY, this.boundingBox.maxZ, flooring, getFillingBlock(), false);
            this.fillWithOutline(world, box, this.boundingBox.minX, this.boundingBox.minY + 1, this.boundingBox.minZ, this.boundingBox.maxX, Math.min(this.boundingBox.minY + 3, this.boundingBox.maxY), this.boundingBox.maxZ, getFillingBlock(), getFillingBlock(), false);

            //nether_wart floor
            if (this.mineShaftType == RSMineshaftPieces.Type.NETHER) {
                this.fillWithOutlineUnderSeaLevel(world, box, world.getRandom(), 0.3f, this.boundingBox.minX, this.boundingBox.minY + 1, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.minY + 1, this.boundingBox.maxZ, Blocks.NETHER_WART.getDefaultState().with(NetherWartBlock.AGE, 2), Blocks.NETHER_WART.getDefaultState().with(NetherWartBlock.AGE, 2), false, false);
            }

            for (BlockBox MutableBoundingBox : this.roomsLinkedToTheRoom) {
                this.fillWithOutline(world, box, MutableBoundingBox.minX, MutableBoundingBox.maxY - 2, MutableBoundingBox.minZ, MutableBoundingBox.maxX, MutableBoundingBox.maxY, MutableBoundingBox.maxZ, getFillingBlock(), getFillingBlock(), false);
            }

            this.method_14919(world, box, this.boundingBox.minX, this.boundingBox.minY + 4, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ, getFillingBlock(), false);

            //vines
            if (this.mineShaftType == RSMineshaftPieces.Type.JUNGLE) {
                fillWithVines(world, random, box, 5, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.maxY + 4, this.boundingBox.maxZ);
            } else if (this.mineShaftType == RSMineshaftPieces.Type.SWAMPORDARKFOREST) {
                fillWithVines(world, random, box, 2, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.maxY + 4, this.boundingBox.maxZ);
            }

            return true;
        }


        @Override
        public void translate(int x, int y, int z) {
            super.translate(x, y, z);

            for (BlockBox MutableBoundingBox : this.roomsLinkedToTheRoom) {
                MutableBoundingBox.offset(x, y, z);
            }
        }
    }

    public static class Stairs extends RSMineshaftPieces.Piece {
        public Stairs(int p_i50449_1_, BlockBox p_i50449_2_, Direction p_i50449_3_, RSMineshaftPieces.Type p_i50449_4_) {
            super(RSStructurePieces.MINESHAFT_STAIRS_RS, p_i50449_1_, p_i50449_4_);
            this.setOrientation(p_i50449_3_);
            this.boundingBox = p_i50449_2_;
        }


        public Stairs(StructureManager p_i50450_1_, CompoundTag p_i50450_2_) {
            super(RSStructurePieces.MINESHAFT_STAIRS_RS, p_i50450_2_);
        }


        public static BlockBox findStairs(List<StructurePiece> listIn, Random rand, int x, int y, int z, Direction facing) {
            BlockBox MutableBoundingBox = new BlockBox(x, y - 5, z, x, y + 2, z);

            switch (facing) {
                case NORTH:
                default:
                    MutableBoundingBox.maxX = x + 2;
                    MutableBoundingBox.minZ = z - 8;
                    break;

                case SOUTH:
                    MutableBoundingBox.maxX = x + 2;
                    MutableBoundingBox.maxZ = z + 8;
                    break;

                case WEST:
                    MutableBoundingBox.minX = x - 8;
                    MutableBoundingBox.maxZ = z + 2;
                    break;

                case EAST:
                    MutableBoundingBox.maxX = x + 8;
                    MutableBoundingBox.maxZ = z + 2;
            }

            return StructurePiece.getOverlappingPiece(listIn, MutableBoundingBox) != null ? null : MutableBoundingBox;
        }


        @Override
        public void placeJigsaw(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            int i = this.getLength();
            Direction enumfacing = this.getFacing();

            if (enumfacing != null) {
                switch (enumfacing) {
                    case NORTH:
                    default:
                        RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, Direction.NORTH, i);
                        break;

                    case SOUTH:
                        RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, Direction.SOUTH, i);
                        break;

                    case WEST:
                        RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, Direction.WEST, i);
                        break;

                    case EAST:
                        RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, Direction.EAST, i);
                }
            }
        }


        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox box, ChunkPos chunkPos, BlockPos blockPos) {
            boolean isOceanType = this.mineShaftType == RSMineshaftPieces.Type.OCEAN;
            if (isOceanType ? this.isAirInStructureBoundingBox(world, box) : this.method_14937(world, box)) {
                return false;
            } else {
                this.fillWithOutline(world, box, 0, 5, 0, 2, 7, 1, getFillingBlock(), getFillingBlock(), false);
                this.fillWithOutline(world, box, 0, 0, 7, 2, 2, 8, getFillingBlock(), getFillingBlock(), false);

                for (int i = 0; i < 5; ++i) {
                    this.fillWithOutline(world, box, 0, 5 - i - (i < 4 ? 1 : 0), 2 + i, 2, 7 - i, 2 + i, getFillingBlock(), getFillingBlock(), false);
                }

                if (this.mineShaftType == RSMineshaftPieces.Type.JUNGLE) {
                    fillWithVines(world, random, box, 5, 0, 0, 0, 2, 7, 8);
                } else if (this.mineShaftType == RSMineshaftPieces.Type.SWAMPORDARKFOREST) {
                    fillWithVines(world, random, box, 2, 0, 0, 0, 2, 7, 8);
                }

                return true;
            }
        }
    }

    abstract static class Piece extends StructurePiece {
        protected RSMineshaftPieces.Type mineShaftType;

        public Piece(StructurePieceType piece, int componentType, RSMineshaftPieces.Type mineshaftType) {
            super(piece, componentType);
            this.mineShaftType = mineshaftType;
        }


        public Piece(StructurePieceType piece, CompoundTag data) {
            super(piece, data);
            this.mineShaftType = RSMineshaftPieces.Type.byId(data.getInt("MST"));
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void toNbt(CompoundTag data) {
            data.putInt("MST", this.mineShaftType.ordinal());
        }


        /**
         * checks the entire StructureBoundingBox for Liquids
         */
        protected boolean isAirInStructureBoundingBox(BlockView worldIn, BlockBox boundingboxIn) {
            int xMin = Math.max(this.boundingBox.minX - 1, boundingboxIn.minX);
            int yMin = Math.max(this.boundingBox.minY - 1, boundingboxIn.minY);
            int zMin = Math.max(this.boundingBox.minZ - 1, boundingboxIn.minZ);
            int xMax = Math.min(this.boundingBox.maxX + 1, boundingboxIn.maxX);
            int yMax = Math.min(this.boundingBox.maxY + 1, boundingboxIn.maxY);
            int zMax = Math.min(this.boundingBox.maxZ + 1, boundingboxIn.maxZ);
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

            for (int x = xMin; x <= xMax; ++x) {
                for (int z = zMin; z <= zMax; ++z) {
                    if (worldIn.getBlockState(blockpos$mutable.set(x, yMin, z)).getMaterial() == Material.AIR) {
                        return true;
                    }

                    if (worldIn.getBlockState(blockpos$mutable.set(x, yMax, z)).getMaterial() == Material.AIR) {
                        return true;
                    }
                }
            }

            for (int x = xMin; x <= xMax; ++x) {
                for (int y = yMin; y <= yMax; ++y) {
                    if (worldIn.getBlockState(blockpos$mutable.set(x, y, zMin)).getMaterial() == Material.AIR) {
                        return true;
                    }

                    if (worldIn.getBlockState(blockpos$mutable.set(x, y, zMax)).getMaterial() == Material.AIR) {
                        return true;
                    }
                }
            }

            for (int z = zMin; z <= zMax; ++z) {
                for (int y = yMin; y <= yMax; ++y) {
                    if (worldIn.getBlockState(blockpos$mutable.set(xMin, y, z)).getMaterial() == Material.AIR) {
                        return true;
                    }

                    if (worldIn.getBlockState(blockpos$mutable.set(xMax, y, z)).getMaterial() == Material.AIR) {
                        return true;
                    }
                }
            }

            return false;
        }


        @SuppressWarnings("deprecation")
        protected void fillWithVines(StructureWorldAccess world, Random random, BlockBox boundingbox, int rarity, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax) {
            BlockState vineBlock;
            int vineLength;

            for (int x = xMin; x <= xMax; ++x) {
                for (int z = zMin; z <= zMax; ++z) {
                    if (random.nextInt(rarity) == 0) {
                        break;
                    }

                    vineBlock = Blocks.VINE.getDefaultState().with(VineBlock.FACING_PROPERTIES.get(Direction.Type.HORIZONTAL.random(random)), true);
                    vineLength = 0;

                    for (int y = yMax; y >= yMin; --y) {
                        BlockState aboveBlockState = this.getBlockAt(world, x, y + 1, z, boundingbox);
                        if (this.getBlockAt(world, x, y, z, boundingbox).isAir()) {
                            if ((aboveBlockState.isOpaque() || aboveBlockState.isOf(Blocks.VINE))) {
                                vineLength++;
                                if (aboveBlockState.isOpaque()) {
                                    this.setVineBlockState(world, vineBlock.with(VineBlock.UP, true), x, y, z, boundingbox);
                                } else {
                                    this.setVineBlockState(world, vineBlock, x, y, z, boundingbox);
                                }
                            } else if (Blocks.VINE.canPlaceAt(vineBlock, world, new BlockPos(this.applyXTransform(x, z), this.applyYTransform(y), this.applyZTransform(x, z)))) {
                                this.setVineBlockState(world, vineBlock, x, y, z, boundingbox);
                            }
                        }

                        if (random.nextInt(3) == 0 || vineLength == 4) {
                            break;
                        }
                    }
                }
            }

        }


        protected void setVineBlockState(StructureWorldAccess worldIn, BlockState blockstateIn, int x, int y, int z, BlockBox boundingboxIn) {
            BlockPos blockpos = new BlockPos(this.applyXTransform(x, z), this.applyYTransform(y), this.applyZTransform(x, z));
            if (boundingboxIn.contains(blockpos)) {
                worldIn.setBlockState(blockpos, blockstateIn, 2);
            }
        }


        protected BlockState getArchTopBlock() {
            switch (this.mineShaftType) {
                case ICY:
                    return Blocks.PACKED_ICE.getDefaultState();

                case JUNGLE:
                    return Blocks.JUNGLE_LOG.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.X);

                case TAIGA:
                    return Blocks.STRIPPED_SPRUCE_LOG.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.X);

                case DESERT:
                    return Blocks.CHISELED_SANDSTONE.getDefaultState();

                case END:
                    return Blocks.PURPUR_PILLAR.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.Z);

                case NETHER:
                    return Blocks.NETHER_BRICKS.getDefaultState();

                case OCEAN:
                    return Blocks.DARK_PRISMARINE.getDefaultState();

                case STONE:
                    return Blocks.STONE.getDefaultState();

                case SAVANNA:
                    return Blocks.ACACIA_LOG.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.X);

                case SWAMPORDARKFOREST:
                    return Blocks.DARK_OAK_PLANKS.getDefaultState();

                case BIRCH:
                default:
                    return Blocks.STRIPPED_BIRCH_LOG.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.X);
            }
        }


        protected Identifier getChestLoot() {
            switch (this.mineShaftType) {
                case ICY:
                    return ICY_CHEST_ID;

                case JUNGLE:
                    return JUNGLE_CHEST_ID;

                case TAIGA:
                    return TAIGA_CHEST_ID;

                case DESERT:
                    return DESERT_CHEST_ID;

                case END:
                    return END_CHEST_ID;

                case NETHER:
                    return NETHER_CHEST_ID;

                case OCEAN:
                    return OCEAN_CHEST_ID;

                case STONE:
                    return STONE_CHEST_ID;

                case SAVANNA:
                    return SAVANNA_CHEST_ID;

                case SWAMPORDARKFOREST:
                    return SWAMP_OR_DARK_FOREST_CHEST_ID;

                case BIRCH:
                default:
                    return BIRCH_CHEST_ID;
            }
        }

        // cannot be a rotatable block
        // The crossing part has a null rotation and will try to force it on the
        // rotatable block which will cause a crash
        protected BlockState getFloorBlock() {
            switch (this.mineShaftType) {
                case ICY:
                    return Blocks.ICE.getDefaultState();

                case JUNGLE:
                    return Blocks.JUNGLE_PLANKS.getDefaultState();

                case TAIGA:
                    return Blocks.SPRUCE_PLANKS.getDefaultState();

                case DESERT:
                    return Blocks.SMOOTH_SANDSTONE.getDefaultState();

                case END:
                    return Blocks.PURPUR_BLOCK.getDefaultState();

                case NETHER:
                    return Blocks.NETHER_BRICKS.getDefaultState();

                case OCEAN:
                    return Blocks.PRISMARINE_BRICKS.getDefaultState();

                case STONE:
                    return Blocks.ANDESITE.getDefaultState();

                case SAVANNA:
                    return Blocks.ACACIA_PLANKS.getDefaultState();

                case SWAMPORDARKFOREST:
                    return Blocks.GRASS_BLOCK.getDefaultState();

                case BIRCH:
                default:
                    return Blocks.BIRCH_PLANKS.getDefaultState();
            }
        }


        protected BlockState getArchSupportBlock(Random random) {
            switch (this.mineShaftType) {
                case ICY:
                    return Blocks.ICE.getDefaultState();

                case JUNGLE:
                    return Blocks.JUNGLE_FENCE.getDefaultState();

                case TAIGA:
                    return Blocks.SPRUCE_FENCE.getDefaultState();

                case DESERT:
                    return Blocks.SANDSTONE_WALL.getDefaultState();

                case END:
                    return Blocks.PURPUR_PILLAR.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.Y);

                case NETHER:
                    return Blocks.NETHER_BRICK_WALL.getDefaultState();

                case OCEAN:
                    return Blocks.PRISMARINE_WALL.getDefaultState().with(Properties.WATERLOGGED, true);

                case STONE:
                    return random.nextInt(10) < 3 ? Blocks.MOSSY_COBBLESTONE_WALL.getDefaultState() : Blocks.COBBLESTONE_WALL.getDefaultState();

                case SAVANNA:
                    return Blocks.ACACIA_FENCE.getDefaultState();

                case SWAMPORDARKFOREST:
                    return Blocks.DARK_OAK_LOG.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.Y);

                case BIRCH:
                default:
                    return Blocks.BIRCH_FENCE.getDefaultState();
            }
        }


        protected BlockState getFillingBlock() {
            if (this.mineShaftType == Type.OCEAN) {
                return Blocks.WATER.getDefaultState();
            }
            return Blocks.CAVE_AIR.getDefaultState();
        }


        protected BlockState getRailBlock() {
            if (this.mineShaftType == Type.OCEAN) {
                return Blocks.OAK_TRAPDOOR.getDefaultState().with(Properties.WATERLOGGED, true);
            }
            return Blocks.RAIL.getDefaultState().with(RailBlock.SHAPE, RailShape.NORTH_SOUTH);
        }


        protected EntityType<?> getSpawnerMob(Random random) {
            switch (this.mineShaftType) {

                case ICY:
                    return RepurposedStructures.mobSpawnerManager.getSpawnerMob(ICY_SPAWNER_ID, random);

                case JUNGLE:
                    return RepurposedStructures.mobSpawnerManager.getSpawnerMob(JUNGLE_SPAWNER_ID, random);

                case TAIGA:
                    return RepurposedStructures.mobSpawnerManager.getSpawnerMob(TAIGA_SPAWNER_ID, random);

                case DESERT:
                    return RepurposedStructures.mobSpawnerManager.getSpawnerMob(DESERT_SPAWNER_ID, random);

                case END:
                    return RepurposedStructures.mobSpawnerManager.getSpawnerMob(END_SPAWNER_ID, random);

                case NETHER:
                    return RepurposedStructures.mobSpawnerManager.getSpawnerMob(NETHER_SPAWNER_ID, random);

                case OCEAN:
                    return RepurposedStructures.mobSpawnerManager.getSpawnerMob(OCEAN_SPAWNER_ID, random);

                case STONE:
                    return RepurposedStructures.mobSpawnerManager.getSpawnerMob(STONE_SPAWNER_ID, random);

                case SAVANNA:
                    return RepurposedStructures.mobSpawnerManager.getSpawnerMob(SAVANNA_SPAWNER_ID, random);

                case SWAMPORDARKFOREST:
                    return RepurposedStructures.mobSpawnerManager.getSpawnerMob(SWAMPORDARKFOREST_SPAWNER_ID, random);

                case BIRCH:
                default:
                    return RepurposedStructures.mobSpawnerManager.getSpawnerMob(BIRCH_SPAWNER_ID, random);
            }
        }


        protected BlockState getDecorativeBlock(Random random) {
            switch (this.mineShaftType) {
                case OCEAN:
                    return random.nextBoolean() ? Blocks.SEAGRASS.getDefaultState() : Blocks.TALL_SEAGRASS.getDefaultState();

                case ICY:
                    return Blocks.ICE.getDefaultState();

                case NETHER:
                    return Blocks.FIRE.getDefaultState();

                case END:
                    return Blocks.CHORUS_FLOWER.getDefaultState().with(ChorusFlowerBlock.AGE, 5);

                default:
                    return Blocks.COBWEB.getDefaultState();
            }
        }
    }
}
