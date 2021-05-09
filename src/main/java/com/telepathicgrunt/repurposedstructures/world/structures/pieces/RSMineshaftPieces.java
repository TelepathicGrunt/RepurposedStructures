package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructurePieces;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.minecart.ChestMinecartEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.RailShape;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;


public class RSMineshaftPieces {

    private static final ResourceLocation ICY_CHEST_ID =  new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/icy");
    private static final ResourceLocation JUNGLE_CHEST_ID =  new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/jungle");
    private static final ResourceLocation TAIGA_CHEST_ID =  new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/taiga");
    private static final ResourceLocation DESERT_CHEST_ID =  new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/desert");
    private static final ResourceLocation END_CHEST_ID =  new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/end");
    private static final ResourceLocation NETHER_CHEST_ID =  new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/nether");
    private static final ResourceLocation CRIMSON_CHEST_ID =  new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/crimson");
    private static final ResourceLocation WARPED_CHEST_ID =  new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/warped");
    private static final ResourceLocation OCEAN_CHEST_ID =  new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/ocean");
    private static final ResourceLocation STONE_CHEST_ID =  new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/stone");
    private static final ResourceLocation SAVANNA_CHEST_ID =  new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/savanna");
    private static final ResourceLocation SWAMP_OR_DARK_FOREST_CHEST_ID =  new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/swamp_dark_forest");
    private static final ResourceLocation BIRCH_CHEST_ID =  new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/birch");
    private static final ResourceLocation ICY_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, "mineshaft_icy");
    private static final ResourceLocation BIRCH_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, "mineshaft_birch");
    private static final ResourceLocation JUNGLE_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, "mineshaft_jungle");
    private static final ResourceLocation TAIGA_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, "mineshaft_taiga");
    private static final ResourceLocation DESERT_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, "mineshaft_desert");
    private static final ResourceLocation STONE_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, "mineshaft_stone");
    private static final ResourceLocation SAVANNA_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, "mineshaft_savanna");
    private static final ResourceLocation SWAMPORDARKFOREST_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, "mineshaft_swamp_or_dark_forest");
    private static final ResourceLocation END_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, "mineshaft_end");
    private static final ResourceLocation NETHER_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, "mineshaft_nether");
    private static final ResourceLocation OCEAN_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, "mineshaft_ocean");
    private static final ResourceLocation CRIMSON_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, "mineshaft_crimson");
    private static final ResourceLocation WARPED_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, "mineshaft_warped");


    public enum Type {
        ICY, BIRCH, JUNGLE, TAIGA, DESERT, STONE, SAVANNA, SWAMPORDARKFOREST, END, NETHER, OCEAN, CRIMSON, WARPED;

        public static RSMineshaftPieces.Type byId(int id) {
            return id >= 0 && id < values().length ? values()[id] : BIRCH;
        }
    }

    private static RSMineshaftPieces.Piece createRandomShaftPiece(List<StructurePiece> p_189940_0_, Random p_189940_1_, int p_189940_2_, int p_189940_3_, int p_189940_4_, Direction p_189940_5_, int p_189940_6_, RSMineshaftPieces.Type type) {
        int i = p_189940_1_.nextInt(100);

        if (i >= 80) {
            MutableBoundingBox MutableBoundingBox = RSMineshaftPieces.Cross.findCrossing(p_189940_0_, p_189940_1_, p_189940_2_, p_189940_3_, p_189940_4_, p_189940_5_);

            if (MutableBoundingBox != null) {
                return new RSMineshaftPieces.Cross(p_189940_6_, MutableBoundingBox, p_189940_5_, type);
            }
        } else if (i >= 70) {
            MutableBoundingBox MutableBoundingBox1 = RSMineshaftPieces.Stairs.findStairs(p_189940_0_, p_189940_1_, p_189940_2_, p_189940_3_, p_189940_4_, p_189940_5_);

            if (MutableBoundingBox1 != null) {
                return new RSMineshaftPieces.Stairs(p_189940_6_, MutableBoundingBox1, p_189940_5_, type);
            }
        } else {
            MutableBoundingBox MutableBoundingBox2 = RSMineshaftPieces.Corridor.findCorridorSize(p_189940_0_, p_189940_1_, p_189940_2_, p_189940_3_, p_189940_4_, p_189940_5_);

            if (MutableBoundingBox2 != null) {
                return new RSMineshaftPieces.Corridor(p_189940_6_, p_189940_1_, MutableBoundingBox2, p_189940_5_, type);
            }
        }

        return null;
    }


    private static RSMineshaftPieces.Piece generateAndAddPiece(StructurePiece p_189938_0_, List<StructurePiece> p_189938_1_, Random p_189938_2_, int p_189938_3_, int p_189938_4_, int p_189938_5_, Direction p_189938_6_, int p_189938_7_) {
        if (p_189938_7_ > 8) {
            return null;
        } else if (Math.abs(p_189938_3_ - p_189938_0_.getBoundingBox().x0) <= 80 && Math.abs(p_189938_5_ - p_189938_0_.getBoundingBox().z0) <= 80) {
            RSMineshaftPieces.Type mapgenmineshaft$type = ((RSMineshaftPieces.Piece) p_189938_0_).mineShaftType;
            RSMineshaftPieces.Piece structuremineshaftpieces$peice = createRandomShaftPiece(p_189938_1_, p_189938_2_, p_189938_3_, p_189938_4_, p_189938_5_, p_189938_6_, p_189938_7_ + 1, mapgenmineshaft$type);

            if (structuremineshaftpieces$peice != null) {
                p_189938_1_.add(structuremineshaftpieces$peice);
                structuremineshaftpieces$peice.addChildren(p_189938_0_, p_189938_1_, p_189938_2_);
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

        public Corridor(TemplateManager p_i50456_1_, CompoundNBT p_i50456_2_) {
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
        protected void addAdditionalSaveData(CompoundNBT tagCompound) {
            super.addAdditionalSaveData(tagCompound);
            tagCompound.putBoolean("hr", this.hasRails);
            tagCompound.putBoolean("sc", this.attemptSpawnerCreation);
            tagCompound.putBoolean("hps", this.spawnerPlaced);
            tagCompound.putInt("Num", this.sectionCount);
        }


        public Corridor(int p_i47140_1_, Random p_i47140_2_, MutableBoundingBox p_i47140_3_, Direction p_i47140_4_, RSMineshaftPieces.Type p_i47140_5_) {
            super(RSStructurePieces.MINESHAFT_CORRIDOR_RS, p_i47140_1_, p_i47140_5_);
            this.setOrientation(p_i47140_4_);
            this.boundingBox = p_i47140_3_;
            this.hasRails = p_i47140_2_.nextInt(3) == 0;
            if (this.mineShaftType == Type.END) {
                this.attemptSpawnerCreation = !this.hasRails && p_i47140_2_.nextInt(5) == 0;
            } else {
                this.attemptSpawnerCreation = !this.hasRails && p_i47140_2_.nextInt(20) == 0;
            }
            if (this.getOrientation() != null && this.getOrientation().getAxis() == Direction.Axis.Z) {
                this.sectionCount = p_i47140_3_.getZSpan() / 5;
            } else {
                this.sectionCount = p_i47140_3_.getXSpan() / 5;
            }

        }


        public static MutableBoundingBox findCorridorSize(List<StructurePiece> p_175814_0_, Random rand, int x, int y, int z, Direction facing) {
            MutableBoundingBox MutableBoundingBox = new MutableBoundingBox(x, y, z, x, y + 2, z);
            int i;

            for (i = rand.nextInt(3) + 2; i > 0; --i) {
                int j = i * 5;

                switch (facing) {
                    case NORTH:
                    default:
                        MutableBoundingBox.x1 = x + 2;
                        MutableBoundingBox.z0 = z - (j - 1);
                        break;

                    case SOUTH:
                        MutableBoundingBox.x1 = x + 2;
                        MutableBoundingBox.z1 = z + (j - 1);
                        break;

                    case WEST:
                        MutableBoundingBox.x0 = x - (j - 1);
                        MutableBoundingBox.z1 = z + 2;
                        break;

                    case EAST:
                        MutableBoundingBox.x1 = x + (j - 1);
                        MutableBoundingBox.z1 = z + 2;
                }

                if (StructurePiece.findCollisionPiece(p_175814_0_, MutableBoundingBox) == null) {
                    break;
                }
            }

            return i > 0 ? MutableBoundingBox : null;
        }


        @Override
        public void addChildren(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            int i = this.getGenDepth();
            int j = rand.nextInt(4);
            Direction enumfacing = this.getOrientation();

            if (enumfacing != null) {
                switch (enumfacing) {
                    case NORTH:
                    default:
                        if (j <= 1) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0, this.boundingBox.y0 - 1 + rand.nextInt(3), this.boundingBox.z0 - 1, enumfacing, i);
                        } else if (j == 2) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 - 1, this.boundingBox.y0 - 1 + rand.nextInt(3), this.boundingBox.z0, Direction.WEST, i);
                        } else {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x1 + 1, this.boundingBox.y0 - 1 + rand.nextInt(3), this.boundingBox.z0, Direction.EAST, i);
                        }

                        break;

                    case SOUTH:
                        if (j <= 1) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0, this.boundingBox.y0 - 1 + rand.nextInt(3), this.boundingBox.z1 + 1, enumfacing, i);
                        } else if (j == 2) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 - 1, this.boundingBox.y0 - 1 + rand.nextInt(3), this.boundingBox.z1 - 3, Direction.WEST, i);
                        } else {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x1 + 1, this.boundingBox.y0 - 1 + rand.nextInt(3), this.boundingBox.z1 - 3, Direction.EAST, i);
                        }

                        break;

                    case WEST:
                        if (j <= 1) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 - 1, this.boundingBox.y0 - 1 + rand.nextInt(3), this.boundingBox.z0, enumfacing, i);
                        } else if (j == 2) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0, this.boundingBox.y0 - 1 + rand.nextInt(3), this.boundingBox.z0 - 1, Direction.NORTH, i);
                        } else {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0, this.boundingBox.y0 - 1 + rand.nextInt(3), this.boundingBox.z1 + 1, Direction.SOUTH, i);
                        }

                        break;

                    case EAST:
                        if (j <= 1) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x1 + 1, this.boundingBox.y0 - 1 + rand.nextInt(3), this.boundingBox.z0, enumfacing, i);
                        } else if (j == 2) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x1 - 3, this.boundingBox.y0 - 1 + rand.nextInt(3), this.boundingBox.z0 - 1, Direction.NORTH, i);
                        } else {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x1 - 3, this.boundingBox.y0 - 1 + rand.nextInt(3), this.boundingBox.z1 + 1, Direction.SOUTH, i);
                        }
                }
            }

            if (i < 8) {
                if (enumfacing != Direction.NORTH && enumfacing != Direction.SOUTH) {
                    for (int i1 = this.boundingBox.x0 + 3; i1 + 3 <= this.boundingBox.x1; i1 += 5) {
                        int j1 = rand.nextInt(5);

                        if (j1 == 0) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, i1, this.boundingBox.y0, this.boundingBox.z0 - 1, Direction.NORTH, i + 1);
                        } else if (j1 == 1) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, i1, this.boundingBox.y0, this.boundingBox.z1 + 1, Direction.SOUTH, i + 1);
                        }
                    }
                } else {
                    for (int k = this.boundingBox.z0 + 3; k + 3 <= this.boundingBox.z1; k += 5) {
                        int l = rand.nextInt(5);

                        if (l == 0) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 - 1, this.boundingBox.y0, k, Direction.WEST, i + 1);
                        } else if (l == 1) {
                            RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x1 + 1, this.boundingBox.y0, k, Direction.EAST, i + 1);
                        }
                    }
                }
            }
        }


        @Override
        protected boolean createChest(ISeedReader world, MutableBoundingBox boundingBox, Random random, int x, int y, int z, ResourceLocation lootTableId) {
            BlockPos blockpos = new BlockPos(this.getWorldX(x, z), this.getWorldY(y), this.getWorldZ(x, z));
            Material currentMaterial = world.getBlockState(blockpos).getMaterial();

            if (boundingBox.isInside(blockpos) && (currentMaterial == Material.AIR || currentMaterial == Material.WATER)) {
                BlockState blockstate;
                if (currentMaterial == Material.AIR) {
                    blockstate = Blocks.RAIL.defaultBlockState().setValue(RailBlock.SHAPE, random.nextBoolean() ? RailShape.NORTH_SOUTH : RailShape.EAST_WEST);
                } else {
                    blockstate = Blocks.OAK_TRAPDOOR.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true);
                }

                this.placeBlock(world, blockstate, x, y, z, boundingBox);
                ChestMinecartEntity entityminecartchest = new ChestMinecartEntity(world.getLevel(), blockpos.getX() + 0.5F, blockpos.getY() + 0.5F, blockpos.getZ() + 0.5F);
                entityminecartchest.setLootTable(lootTableId, random.nextLong());
                world.addFreshEntity(entityminecartchest);
                return true;
            } else {
                return false;
            }
        }


        @Override
        public boolean postProcess(ISeedReader world, StructureManager structureAccessor, ChunkGenerator chunkGenerator, Random random, MutableBoundingBox box, ChunkPos chunkPos, BlockPos blockPos) {
            boolean isOceanType = this.mineShaftType == Type.OCEAN;
            boolean isNetherType = this.mineShaftType == Type.NETHER || this.mineShaftType == Type.CRIMSON || this.mineShaftType == Type.WARPED;
            if (!isNetherType && (isOceanType ? this.isAirInStructureBoundingBox(world, box) : this.edgesLiquid(world, box))) {
                return false;
            } else {
                int offsetInSection = this.sectionCount * 5 - 1;
                BlockState iblockstate = this.getFloorBlock();
                this.generateBox(world, box, 0, 0, 0, 2, 1, offsetInSection, getFillingBlock(), getFillingBlock(), false);
                this.generateMaybeBox(world, box, random, 0.8F, 0, 2, 0, 2, 2, offsetInSection, getFillingBlock(), getFillingBlock(), false, false);

                // prevent floating lava
                if (isNetherType) {
                    BlockState liquidReplacementBlock = getFloorBlock();

                    if(this.mineShaftType == Type.CRIMSON){
                        liquidReplacementBlock = Blocks.CRIMSON_HYPHAE.defaultBlockState();
                    }
                    else if(this.mineShaftType == Type.WARPED){
                        liquidReplacementBlock = Blocks.WARPED_HYPHAE.defaultBlockState();
                    }

                    replaceFloatingLiquids(world, box, -1, -1, -1, 3, 3, offsetInSection + 1, liquidReplacementBlock);
                }

                if (this.attemptSpawnerCreation) {
                    if (isOceanType || this.mineShaftType == Type.NETHER || this.mineShaftType == Type.CRIMSON || this.mineShaftType == Type.WARPED) {
                        this.generateMaybeBox(world, box, random, 0.6F, 0, 0, 0, 2, 0, offsetInSection, getDecorativeBlock(random), getDecorativeBlock(random), false, true);
                    }
                    else if(this.mineShaftType == Type.END){
                        this.generateMaybeBox(world, box, random, 0.6F, 0, 0, 0, 2, 0, offsetInSection, getDecorativeBlock(random), getDecorativeBlock(random), false, true);

                        // can only place chorus fruit on end stone
                        for (int x = 0; x <= 2; ++x) {
                            for (int z = 0; z <= offsetInSection; ++z) {
                                if (this.getBlock(world, x, 0, z, box).is(Blocks.CHORUS_FLOWER)) {
                                    this.placeBlock(world, Blocks.END_STONE.defaultBlockState(), x, -1, z, box);
                                }
                            }
                        }
                    }
                    else {
                        this.generateMaybeBox(world, box, random, 0.6F, 0, 0, 0, 2, 1, offsetInSection, getDecorativeBlock(random), getFillingBlock(), false, true);
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

                    if (RepurposedStructures.RSMineshaftsConfig.lootChestsMS.get()) {
                        if (random.nextInt(50) == 0) {
                            this.createChest(world, box, random, 2, 0, k1 - 1, getChestLoot());
                        }

                        if (random.nextInt(50) == 0) {
                            this.createChest(world, box, random, 0, 0, k1 + 1, getChestLoot());
                        }
                    }

                    if (this.attemptSpawnerCreation && (!this.spawnerPlaced || (this.mineShaftType == Type.END && random.nextBoolean()))) {
                        int l1 = this.getWorldY(0);
                        int i2 = k1 - 1 + random.nextInt(3);
                        int j2 = this.getWorldX(1, i2);
                        int k2 = this.getWorldZ(1, i2);
                        BlockPos blockpos = new BlockPos(j2, l1, k2);

                        if (box.isInside(blockpos) && this.isInterior(world, 1, 0, i2, box)) {
                            this.spawnerPlaced = true;
                            world.setBlock(blockpos, Blocks.SPAWNER.defaultBlockState(), 2);
                            TileEntity tileentity = world.getBlockEntity(blockpos);

                            if (tileentity instanceof MobSpawnerTileEntity) {
                                ((MobSpawnerTileEntity) tileentity).getSpawner().setEntityId(getSpawnerMob(random));
                            }
                        }
                    }

                    //wall of glass
                    if (this.mineShaftType == Type.END && random.nextFloat() < 0.3f) {
                        this.generateBox(world, box, 0, 0, 0, 2, 2, 0, Blocks.PURPLE_STAINED_GLASS_PANE.defaultBlockState().setValue(PaneBlock.EAST, true).setValue(PaneBlock.WEST, true), Blocks.PURPLE_STAINED_GLASS_PANE.defaultBlockState().setValue(PaneBlock.NORTH, true).setValue(PaneBlock.SOUTH, true), false);
                    }
                }

                for (int x = 0; x <= 2; ++x) {
                    for (int z = 0; z <= offsetInSection; ++z) {
                        BlockState spaceForFloor = this.getBlock(world, x, -1, z, box);

                        if (isOceanType ? spaceForFloor.getMaterial() == Material.WATER : spaceForFloor.getMaterial() == Material.AIR) {
                            this.placeBlock(world, iblockstate, x, -1, z, box);
                        }
                        else if(this.mineShaftType == Type.CRIMSON){
                            this.placeBlock(world, Blocks.CRIMSON_NYLIUM.defaultBlockState(), x, -1, z, box);
                        }
                        else if(this.mineShaftType == Type.WARPED){
                            this.placeBlock(world, Blocks.WARPED_NYLIUM.defaultBlockState(), x, -1, z, box);
                        }
                    }
                }

                if (this.hasRails) {
                    BlockState blockstate = getRailBlock();

                    for (int j3 = 0; j3 <= offsetInSection; ++j3) {
                        BlockState spaceForRails = this.getBlock(world, 1, -1, j3, box);

                        if (isOceanType ? spaceForRails.getMaterial() != Material.WATER : spaceForRails.getMaterial() != Material.AIR) {
                            float f = this.isInterior(world, 1, 0, j3, box) ? 0.7F : 0.9F;
                            this.maybeGenerateBlock(world, box, random, f, 1, 0, j3, blockstate);
                        }
                    }
                }

                if (this.mineShaftType == Type.JUNGLE) {
                    fillWithVines(world, random, box, 15, 0, 0, 0, 2, 2, offsetInSection, Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
                }
                else if (this.mineShaftType == Type.SWAMPORDARKFOREST) {
                    fillWithVines(world, random, box, 7, 0, 0, 0, 2, 2, offsetInSection, Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
                }
                else if(this.mineShaftType == Type.WARPED){
                    fillWithTwistedVines(world, random, box, 8, 0, 0, 0, 2, 2, offsetInSection);
                }
                else if(this.mineShaftType == Type.CRIMSON){
                    fillWithWeepingVines(world, random, box, 8, 0, 0, 0, 2, 2, offsetInSection);
                }

                return true;
            }
        }


        private void placeSupport(ISeedReader world, MutableBoundingBox boundingBox, int x, int y2, int z, int y, int x2, Random random) {

            BlockState iblockstate = this.getArchTopBlock();
            BlockState iblockstate2 = getFillingBlock();
            this.generateBox(world, boundingBox, x, y2, z, x, y - 1, z, this.getArchSupportBlock(random), iblockstate2, false);
            this.generateBox(world, boundingBox, x2, y2, z, x2, y - 1, z, this.getArchSupportBlock(random), iblockstate2, false);
            this.generateBox(world, boundingBox, x, y, z, x2, y, z, iblockstate, iblockstate2, false);

            if (this.mineShaftType == Type.END) {
                if (random.nextFloat() < 0.08F) {
                    this.maybeGenerateBlock(world, boundingBox, random, 1F, x, y, z - 1, Blocks.END_ROD.defaultBlockState().setValue(DirectionalBlock.FACING, Direction.SOUTH));
                    this.maybeGenerateBlock(world, boundingBox, random, 1F, x, y, z + 1, Blocks.END_ROD.defaultBlockState().setValue(DirectionalBlock.FACING, Direction.NORTH));
                }

                if (random.nextFloat() < 0.08F) {
                    this.maybeGenerateBlock(world, boundingBox, random, 1F, x + 2, y, z - 1, Blocks.END_ROD.defaultBlockState().setValue(DirectionalBlock.FACING, Direction.SOUTH));
                    this.maybeGenerateBlock(world, boundingBox, random, 1F, x + 2, y, z + 1, Blocks.END_ROD.defaultBlockState().setValue(DirectionalBlock.FACING, Direction.NORTH));
                }
            }
            else if (this.mineShaftType == Type.NETHER) {
                if (random.nextFloat() < 0.3f) {
                    this.maybeGenerateBlock(world, boundingBox, random, 0.45F, x + 1, y, z, Blocks.SHROOMLIGHT.defaultBlockState());
                } else {
                    this.maybeGenerateBlock(world, boundingBox, random, 0.1F, x + 1, y, z - 1, Blocks.SOUL_WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING, Direction.SOUTH));
                    this.maybeGenerateBlock(world, boundingBox, random, 0.1F, x + 1, y, z + 1, Blocks.SOUL_WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING, Direction.NORTH));
                }
            }
            else if (this.mineShaftType == Type.CRIMSON || this.mineShaftType == Type.WARPED) {
                if(this.mineShaftType == Type.CRIMSON){
                    this.maybeGenerateBlock(world, boundingBox, random, 0.8F, x, y, z, Blocks.CRIMSON_HYPHAE.defaultBlockState());
                    this.maybeGenerateBlock(world, boundingBox, random, 0.8F, x + 2, y, z, Blocks.CRIMSON_HYPHAE.defaultBlockState());
                }
                else {
                    this.maybeGenerateBlock(world, boundingBox, random, 0.8F, x, y, z, Blocks.WARPED_HYPHAE.defaultBlockState());
                    this.maybeGenerateBlock(world, boundingBox, random, 0.8F, x + 2, y, z, Blocks.WARPED_HYPHAE.defaultBlockState());
                }
                this.maybeGenerateBlock(world, boundingBox, random, 0.1F, x, y, z, Blocks.SHROOMLIGHT.defaultBlockState());
                this.maybeGenerateBlock(world, boundingBox, random, 0.40F, x + 1, y, z, Blocks.SHROOMLIGHT.defaultBlockState());
                this.maybeGenerateBlock(world, boundingBox, random, 0.1F, x + 2, y, z, Blocks.SHROOMLIGHT.defaultBlockState());
            }
            else if (this.mineShaftType == Type.OCEAN) {
                this.maybeGenerateBlock(world, boundingBox, random, 0.2F, x + 1, y, z, Blocks.SEA_LANTERN.defaultBlockState());
            }
            else if (this.mineShaftType == Type.ICY) {
                this.maybeGenerateBlock(world, boundingBox, random, 0.08F, x + 1, y, z - 1, Blocks.REDSTONE_WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING, Direction.SOUTH));
                this.maybeGenerateBlock(world, boundingBox, random, 0.08F, x + 1, y, z + 1, Blocks.REDSTONE_WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING, Direction.NORTH));
            }
            else {
                this.maybeGenerateBlock(world, boundingBox, random, 0.08F, x + 1, y, z - 1, Blocks.WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING, Direction.SOUTH));
                this.maybeGenerateBlock(world, boundingBox, random, 0.08F, x + 1, y, z + 1, Blocks.WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING, Direction.NORTH));
            }
        }


        private void placeDecoration(ISeedReader world, MutableBoundingBox box, Random random, float probability, int x, int y, int z) {
            if (world.registryAccess().registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY).getKey(world.dimensionType()) != DimensionType.OVERWORLD_EFFECTS || this.isInterior(world, x, y, z, box)) {
                BlockState decorativeBlock = getDecorativeBlock(random);
                if (!decorativeBlock.is(Blocks.COBWEB)) {
                    y = 0;
                }
                this.maybeGenerateBlock(world, box, random, probability, x, y, z, decorativeBlock);

                // can only place chorus fruit on end stone
                //Also places it in a grown state
                if (this.getBlock(world, x, y, z, box).is(Blocks.CHORUS_FLOWER)) {
                    this.placeBlock(world, Blocks.END_STONE.defaultBlockState(), x, -1, z, box);
                    this.placeBlock(world, Blocks.CHORUS_PLANT.defaultBlockState().setValue(SixWayBlock.UP, true).setValue(SixWayBlock.DOWN, true), x, 0, z, box);
                    this.placeBlock(world, Blocks.CHORUS_PLANT.defaultBlockState().setValue(SixWayBlock.SOUTH, true).setValue(SixWayBlock.NORTH, true).setValue(SixWayBlock.EAST, true).setValue(SixWayBlock.WEST, true).setValue(SixWayBlock.DOWN, true), x, 1, z, box);

                    if (random.nextBoolean()) {
                        if (this.getBlock(world, x - 1, 1, z, box).isAir()) {
                            this.placeBlock(world, Blocks.CHORUS_FLOWER.defaultBlockState().setValue(ChorusFlowerBlock.AGE, 5), x - 1, 1, z, box);
                        } else {
                            this.placeBlock(world, Blocks.CHORUS_FLOWER.defaultBlockState().setValue(ChorusFlowerBlock.AGE, 5), x, 1, z, box);
                        }
                    } else {
                        if (this.getBlock(world, x + 1, 1, z, box).isAir()) {
                            this.placeBlock(world, Blocks.CHORUS_FLOWER.defaultBlockState().setValue(ChorusFlowerBlock.AGE, 5), x + 1, 1, z, box);
                        } else {
                            this.placeBlock(world, Blocks.CHORUS_FLOWER.defaultBlockState().setValue(ChorusFlowerBlock.AGE, 5), x, 1, z, box);
                        }
                    }
                }
            }
        }

    }

    public static class Cross extends RSMineshaftPieces.Piece {
        private final Direction corridorDirection;
        private final boolean isMultipleFloors;

        public Cross(TemplateManager p_i50454_1_, CompoundNBT p_i50454_2_) {
            super(RSStructurePieces.MINESHAFT_CROSSING_RS, p_i50454_2_);
            this.isMultipleFloors = p_i50454_2_.getBoolean("tf");
            this.corridorDirection = Direction.from2DDataValue(p_i50454_2_.getInt("D"));
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void addAdditionalSaveData(CompoundNBT tagCompound) {
            super.addAdditionalSaveData(tagCompound);
            tagCompound.putBoolean("tf", this.isMultipleFloors);
            tagCompound.putInt("D", this.corridorDirection.get2DDataValue());
        }


        public Cross(int p_i50455_1_, MutableBoundingBox p_i50455_2_, Direction p_i50455_3_, RSMineshaftPieces.Type p_i50455_4_) {
            super(RSStructurePieces.MINESHAFT_CROSSING_RS, p_i50455_1_, p_i50455_4_);
            this.corridorDirection = p_i50455_3_;
            this.boundingBox = p_i50455_2_;
            this.isMultipleFloors = p_i50455_2_.getYSpan() > 3;
        }


        public static MutableBoundingBox findCrossing(List<StructurePiece> listIn, Random rand, int x, int y, int z, Direction facing) {
            MutableBoundingBox MutableBoundingBox = new MutableBoundingBox(x, y, z, x, y + 2, z);

            if (rand.nextInt(4) == 0) {
                MutableBoundingBox.y1 += 4;
            }

            switch (facing) {
                case NORTH:
                default:
                    MutableBoundingBox.x0 = x - 1;
                    MutableBoundingBox.x1 = x + 3;
                    MutableBoundingBox.z0 = z - 4;
                    break;

                case SOUTH:
                    MutableBoundingBox.x0 = x - 1;
                    MutableBoundingBox.x1 = x + 3;
                    MutableBoundingBox.z1 = z + 3 + 1;
                    break;

                case WEST:
                    MutableBoundingBox.x0 = x - 4;
                    MutableBoundingBox.z0 = z - 1;
                    MutableBoundingBox.z1 = z + 3;
                    break;

                case EAST:
                    MutableBoundingBox.x1 = x + 3 + 1;
                    MutableBoundingBox.z0 = z - 1;
                    MutableBoundingBox.z1 = z + 3;
            }

            return StructurePiece.findCollisionPiece(listIn, MutableBoundingBox) != null ? null : MutableBoundingBox;
        }


        @Override
        public void addChildren(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            int i = this.getGenDepth();

            switch (this.corridorDirection) {
                case NORTH:
                default:
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 + 1, this.boundingBox.y0, this.boundingBox.z0 - 1, Direction.NORTH, i);
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 - 1, this.boundingBox.y0, this.boundingBox.z0 + 1, Direction.WEST, i);
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x1 + 1, this.boundingBox.y0, this.boundingBox.z0 + 1, Direction.EAST, i);
                    break;

                case SOUTH:
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 + 1, this.boundingBox.y0, this.boundingBox.z1 + 1, Direction.SOUTH, i);
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 - 1, this.boundingBox.y0, this.boundingBox.z0 + 1, Direction.WEST, i);
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x1 + 1, this.boundingBox.y0, this.boundingBox.z0 + 1, Direction.EAST, i);
                    break;

                case WEST:
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 + 1, this.boundingBox.y0, this.boundingBox.z0 - 1, Direction.NORTH, i);
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 + 1, this.boundingBox.y0, this.boundingBox.z1 + 1, Direction.SOUTH, i);
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 - 1, this.boundingBox.y0, this.boundingBox.z0 + 1, Direction.WEST, i);
                    break;

                case EAST:
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 + 1, this.boundingBox.y0, this.boundingBox.z0 - 1, Direction.NORTH, i);
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 + 1, this.boundingBox.y0, this.boundingBox.z1 + 1, Direction.SOUTH, i);
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x1 + 1, this.boundingBox.y0, this.boundingBox.z0 + 1, Direction.EAST, i);
            }

            if (this.isMultipleFloors) {
                if (rand.nextBoolean()) {
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 + 1, this.boundingBox.y0 + 3 + 1, this.boundingBox.z0 - 1, Direction.NORTH, i);
                }

                if (rand.nextBoolean()) {
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 - 1, this.boundingBox.y0 + 3 + 1, this.boundingBox.z0 + 1, Direction.WEST, i);
                }

                if (rand.nextBoolean()) {
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x1 + 1, this.boundingBox.y0 + 3 + 1, this.boundingBox.z0 + 1, Direction.EAST, i);
                }

                if (rand.nextBoolean()) {
                    RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 + 1, this.boundingBox.y0 + 3 + 1, this.boundingBox.z1 + 1, Direction.SOUTH, i);
                }
            }
        }


        @Override
        public boolean postProcess(ISeedReader world, StructureManager structureAccessor, ChunkGenerator chunkGenerator, Random random, MutableBoundingBox box, ChunkPos chunkPos, BlockPos blockPos) {
            boolean isOceanType = this.mineShaftType == Type.OCEAN;
            boolean isNetherType = this.mineShaftType == Type.NETHER || this.mineShaftType == Type.CRIMSON || this.mineShaftType == Type.WARPED;
            if (!isNetherType && (isOceanType ? this.isAirInStructureBoundingBox(world, box) : this.edgesLiquid(world, box))) {
                return false;
            } else {
                BlockState iblockstate = this.getFloorBlock();

                if (this.isMultipleFloors) {
                    this.generateBox(world, box, this.boundingBox.x0 + 1, this.boundingBox.y0, this.boundingBox.z0, this.boundingBox.x1 - 1, this.boundingBox.y0 + 3 - 1, this.boundingBox.z1, getFillingBlock(), getFillingBlock(), false);
                    this.generateBox(world, box, this.boundingBox.x0, this.boundingBox.y0, this.boundingBox.z0 + 1, this.boundingBox.x1, this.boundingBox.y0 + 3 - 1, this.boundingBox.z1 - 1, getFillingBlock(), getFillingBlock(), false);
                    this.generateBox(world, box, this.boundingBox.x0 + 1, this.boundingBox.y1 - 2, this.boundingBox.z0, this.boundingBox.x1 - 1, this.boundingBox.y1, this.boundingBox.z1, getFillingBlock(), getFillingBlock(), false);
                    this.generateBox(world, box, this.boundingBox.x0, this.boundingBox.y1 - 2, this.boundingBox.z0 + 1, this.boundingBox.x1, this.boundingBox.y1, this.boundingBox.z1 - 1, getFillingBlock(), getFillingBlock(), false);
                    this.generateBox(world, box, this.boundingBox.x0 + 1, this.boundingBox.y0 + 3, this.boundingBox.z0 + 1, this.boundingBox.x1 - 1, this.boundingBox.y0 + 3, this.boundingBox.z1 - 1, getFillingBlock(), getFillingBlock(), false);
                } else {
                    this.generateBox(world, box, this.boundingBox.x0 + 1, this.boundingBox.y0, this.boundingBox.z0, this.boundingBox.x1 - 1, this.boundingBox.y1, this.boundingBox.z1, getFillingBlock(), getFillingBlock(), false);
                    this.generateBox(world, box, this.boundingBox.x0, this.boundingBox.y0, this.boundingBox.z0 + 1, this.boundingBox.x1, this.boundingBox.y1, this.boundingBox.z1 - 1, getFillingBlock(), getFillingBlock(), false);
                }

                // prevent floating lava
                if (isNetherType) {
                    BlockState liquidReplacementBlock = getFloorBlock();

                    if(this.mineShaftType == Type.CRIMSON){
                        liquidReplacementBlock = Blocks.CRIMSON_HYPHAE.defaultBlockState();
                    }
                    else if(this.mineShaftType == Type.WARPED){
                        liquidReplacementBlock = Blocks.WARPED_HYPHAE.defaultBlockState();
                    }

                    if (this.isMultipleFloors) {
                        replaceFloatingLiquids(world, box, this.boundingBox.x0 - 1, this.boundingBox.y0 - 1, this.boundingBox.z0 - 1, this.boundingBox.x1 + 1, this.boundingBox.y1 + 1, this.boundingBox.z1 + 1, liquidReplacementBlock);
                    }
                    else {
                        replaceFloatingLiquids(world, box, this.boundingBox.x0 - 1, this.boundingBox.y0 - 1, this.boundingBox.z0 - 1, this.boundingBox.x1 + 1, this.boundingBox.y1 + 1, this.boundingBox.z1 + 1, liquidReplacementBlock);
                    }
                }

                this.placeSupportPillar(world, box, this.boundingBox.x0 + 1, this.boundingBox.y0, this.boundingBox.z0 + 1, this.boundingBox.y1, isOceanType);
                this.placeSupportPillar(world, box, this.boundingBox.x0 + 1, this.boundingBox.y0, this.boundingBox.z1 - 1, this.boundingBox.y1, isOceanType);
                this.placeSupportPillar(world, box, this.boundingBox.x1 - 1, this.boundingBox.y0, this.boundingBox.z0 + 1, this.boundingBox.y1, isOceanType);
                this.placeSupportPillar(world, box, this.boundingBox.x1 - 1, this.boundingBox.y0, this.boundingBox.z1 - 1, this.boundingBox.y1, isOceanType);

                for (int i = this.boundingBox.x0; i <= this.boundingBox.x1; ++i) {
                    for (int j = this.boundingBox.z0; j <= this.boundingBox.z1; ++j) {
                        if (this.getBlock(world, i, this.boundingBox.y0 - 1, j, box).getMaterial() == (isOceanType ? Material.WATER : Material.AIR) && this.isInterior(world, i, this.boundingBox.y0 - 1, j, box)) {
                            this.placeBlock(world, iblockstate, i, this.boundingBox.y0 - 1, j, box);
                        }
                    }
                }

                if (this.mineShaftType == Type.JUNGLE) {
                    fillWithVines(world, random, box, 15, this.boundingBox.x0, this.boundingBox.y0, this.boundingBox.z0, this.boundingBox.x1, this.boundingBox.y1, this.boundingBox.z1, Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
                }
                else if (this.mineShaftType == Type.SWAMPORDARKFOREST) {
                    fillWithVines(world, random, box, 7, this.boundingBox.x0, this.boundingBox.y0, this.boundingBox.z0, this.boundingBox.x1, this.boundingBox.y1, this.boundingBox.z1, Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
                }
                else if(this.mineShaftType == Type.WARPED){
                    fillWithTwistedVines(world, random, box, 8, this.boundingBox.x0, this.boundingBox.y0, this.boundingBox.z0, this.boundingBox.x1, this.boundingBox.y1, this.boundingBox.z1);
                    this.generateBox(world, box, this.boundingBox.x0 + 1, this.boundingBox.y0 - 1, this.boundingBox.z0, this.boundingBox.x1 - 1, this.boundingBox.y0 - 1, this.boundingBox.z1, Blocks.WARPED_NYLIUM.defaultBlockState(), Blocks.WARPED_NYLIUM.defaultBlockState(), false);
                    this.generateBox(world, box, this.boundingBox.x0, this.boundingBox.y0 - 1, this.boundingBox.z0 + 1, this.boundingBox.x1, this.boundingBox.y0 - 1, this.boundingBox.z1 - 1, Blocks.WARPED_NYLIUM.defaultBlockState(), Blocks.WARPED_NYLIUM.defaultBlockState(), false);
                }
                else if(this.mineShaftType == Type.CRIMSON){
                    fillWithWeepingVines(world, random, box, 8, this.boundingBox.x0, this.boundingBox.y0, this.boundingBox.z0, this.boundingBox.x1, this.boundingBox.y1, this.boundingBox.z1);
                    this.generateBox(world, box, this.boundingBox.x0 + 1, this.boundingBox.y0 - 1, this.boundingBox.z0, this.boundingBox.x1 - 1, this.boundingBox.y0 - 1, this.boundingBox.z1, Blocks.CRIMSON_NYLIUM.defaultBlockState(), Blocks.CRIMSON_NYLIUM.defaultBlockState(), false);
                    this.generateBox(world, box, this.boundingBox.x0, this.boundingBox.y0 - 1, this.boundingBox.z0 + 1, this.boundingBox.x1, this.boundingBox.y0 - 1, this.boundingBox.z1 - 1, Blocks.CRIMSON_NYLIUM.defaultBlockState(), Blocks.CRIMSON_NYLIUM.defaultBlockState(), false);
                }

                return true;
            }
        }


        private void placeSupportPillar(ISeedReader world, MutableBoundingBox box, int x, int miny, int z, int maxy, boolean isOceanType) {
            if (this.getBlock(world, x, maxy + 1, z, box).getMaterial() != (isOceanType ? Material.WATER : Material.AIR)) {
                this.generateBox(world, box, x, miny, z, x, maxy, z, this.getFloorBlock().is(Blocks.GRASS_BLOCK) ? Blocks.MOSSY_STONE_BRICKS.defaultBlockState() : this.getFloorBlock(), getFillingBlock(), false);
            }
        }
    }

    public static class Room extends RSMineshaftPieces.Piece {
        private final List<MutableBoundingBox> roomsLinkedToTheRoom = Lists.newLinkedList();

        public Room(int p_i47137_1_, Random p_i47137_2_, int p_i47137_3_, int p_i47137_4_, RSMineshaftPieces.Type p_i47137_5_) {
            super(RSStructurePieces.MINESHAFT_ROOM_RS, p_i47137_1_, p_i47137_5_);
            this.mineShaftType = p_i47137_5_;
            this.boundingBox = new MutableBoundingBox(p_i47137_3_, 50, p_i47137_4_, p_i47137_3_ + 7 + p_i47137_2_.nextInt(6), 54 + p_i47137_2_.nextInt(6), p_i47137_4_ + 7 + p_i47137_2_.nextInt(6));
        }


        public Room(TemplateManager p_i50451_1_, CompoundNBT p_i50451_2_) {
            super(RSStructurePieces.MINESHAFT_ROOM_RS, p_i50451_2_);
            ListNBT listnbt = p_i50451_2_.getList("Entrances", 11);

            for (int i = 0; i < listnbt.size(); ++i) {
                this.roomsLinkedToTheRoom.add(new MutableBoundingBox(listnbt.getIntArray(i)));
            }
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void addAdditionalSaveData(CompoundNBT tagCompound) {
            super.addAdditionalSaveData(tagCompound);
            ListNBT listnbt = new ListNBT();

            for (MutableBoundingBox mutableboundingbox : this.roomsLinkedToTheRoom) {
                listnbt.add(mutableboundingbox.createTag());
            }

            tagCompound.put("Entrances", listnbt);
        }


        @Override
        public void addChildren(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            int i = this.getGenDepth();
            int k = this.boundingBox.getYSpan() - 3 - 1;
            if (k <= 0) {
                k = 1;
            }

            int l;
            for (int j = 0; j < this.boundingBox.getXSpan(); j = l + 4) {
                l = j + rand.nextInt(this.boundingBox.getXSpan());
                if (l + 3 > this.boundingBox.getXSpan()) {
                    break;
                }

                RSMineshaftPieces.Piece structuremineshaftpieces$peice = RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 + l, this.boundingBox.y0 + rand.nextInt(k) + 1, this.boundingBox.z0 - 1, Direction.NORTH, i);

                if (structuremineshaftpieces$peice != null) {
                    MutableBoundingBox MutableBoundingBox = structuremineshaftpieces$peice.getBoundingBox();
                    this.roomsLinkedToTheRoom.add(new MutableBoundingBox(MutableBoundingBox.x0, MutableBoundingBox.y0, this.boundingBox.z0, MutableBoundingBox.x1, MutableBoundingBox.y1, this.boundingBox.z0 + 1));
                }
            }

            for (int i1 = 0; i1 < this.boundingBox.getXSpan(); i1 = l + 4) {
                l = i1 + rand.nextInt(this.boundingBox.getXSpan());

                if (l + 3 > this.boundingBox.getXSpan()) {
                    break;
                }

                RSMineshaftPieces.Piece structuremineshaftpieces$peice1 = RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 + l, this.boundingBox.y0 + rand.nextInt(k) + 1, this.boundingBox.z1 + 1, Direction.SOUTH, i);

                if (structuremineshaftpieces$peice1 != null) {
                    MutableBoundingBox MutableBoundingBox1 = structuremineshaftpieces$peice1.getBoundingBox();
                    this.roomsLinkedToTheRoom.add(new MutableBoundingBox(MutableBoundingBox1.x0, MutableBoundingBox1.y0, this.boundingBox.z1 - 1, MutableBoundingBox1.x1, MutableBoundingBox1.y1, this.boundingBox.z1));
                }
            }

            for (int j1 = 0; j1 < this.boundingBox.getZSpan(); j1 = l + 4) {
                l = j1 + rand.nextInt(this.boundingBox.getZSpan());

                if (l + 3 > this.boundingBox.getZSpan()) {
                    break;
                }

                RSMineshaftPieces.Piece structuremineshaftpieces$peice2 = RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 - 1, this.boundingBox.y0 + rand.nextInt(k) + 1, this.boundingBox.z0 + l, Direction.WEST, i);

                if (structuremineshaftpieces$peice2 != null) {
                    MutableBoundingBox MutableBoundingBox2 = structuremineshaftpieces$peice2.getBoundingBox();
                    this.roomsLinkedToTheRoom.add(new MutableBoundingBox(this.boundingBox.x0, MutableBoundingBox2.y0, MutableBoundingBox2.z0, this.boundingBox.x0 + 1, MutableBoundingBox2.y1, MutableBoundingBox2.z1));
                }
            }

            for (int k1 = 0; k1 < this.boundingBox.getZSpan(); k1 = l + 4) {
                l = k1 + rand.nextInt(this.boundingBox.getZSpan());

                if (l + 3 > this.boundingBox.getZSpan()) {
                    break;
                }

                StructurePiece StructurePiece = RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x1 + 1, this.boundingBox.y0 + rand.nextInt(k) + 1, this.boundingBox.z0 + l, Direction.EAST, i);

                if (StructurePiece != null) {
                    MutableBoundingBox MutableBoundingBox3 = StructurePiece.getBoundingBox();
                    this.roomsLinkedToTheRoom.add(new MutableBoundingBox(this.boundingBox.x1 - 1, MutableBoundingBox3.y0, MutableBoundingBox3.z0, this.boundingBox.x1, MutableBoundingBox3.y1, MutableBoundingBox3.z1));
                }
            }
        }


        @Override
        public boolean postProcess(ISeedReader world, StructureManager structureAccessor, ChunkGenerator chunkGenerator, Random random, MutableBoundingBox box, ChunkPos chunkPos, BlockPos blockPos) {
            BlockState flooring;

            if (this.mineShaftType == Type.NETHER) {
                flooring = Blocks.SOUL_SAND.defaultBlockState();
            }
            else if (this.mineShaftType == Type.CRIMSON) {
                flooring = Blocks.CRIMSON_NYLIUM.defaultBlockState();
            }
            else if (this.mineShaftType == Type.WARPED) {
                flooring = Blocks.WARPED_NYLIUM.defaultBlockState();
            }
            else if (this.mineShaftType == Type.END) {
                flooring = Blocks.END_STONE_BRICKS.defaultBlockState();
            }
            else if (this.mineShaftType == Type.OCEAN) {
                flooring = Blocks.CLAY.defaultBlockState();
            }
            else {
                flooring = getFloorBlock().getMaterial() == Material.WOOD ? Blocks.COARSE_DIRT.defaultBlockState() : getFloorBlock();
            }

            this.generateBox(world, box, this.boundingBox.x0, this.boundingBox.y0, this.boundingBox.z0, this.boundingBox.x1, this.boundingBox.y0, this.boundingBox.z1, flooring, getFillingBlock(), false);
            this.generateBox(world, box, this.boundingBox.x0, this.boundingBox.y0 + 1, this.boundingBox.z0, this.boundingBox.x1, Math.min(this.boundingBox.y0 + 3, this.boundingBox.y1), this.boundingBox.z1, getFillingBlock(), getFillingBlock(), false);

            //nether_wart floor
            if (this.mineShaftType == Type.NETHER) {
                this.generateMaybeBox(world, box, world.getRandom(), 0.3f, this.boundingBox.x0, this.boundingBox.y0 + 1, this.boundingBox.z0, this.boundingBox.x1, this.boundingBox.y0 + 1, this.boundingBox.z1, Blocks.NETHER_WART.defaultBlockState().setValue(NetherWartBlock.AGE, 2), Blocks.NETHER_WART.defaultBlockState().setValue(NetherWartBlock.AGE, 2), false, false);
            }

            for (MutableBoundingBox MutableBoundingBox : this.roomsLinkedToTheRoom) {
                this.generateBox(world, box, MutableBoundingBox.x0, MutableBoundingBox.y1 - 2, MutableBoundingBox.z0, MutableBoundingBox.x1, MutableBoundingBox.y1, MutableBoundingBox.z1, getFillingBlock(), getFillingBlock(), false);
            }

            this.generateUpperHalfSphere(world, box, this.boundingBox.x0, this.boundingBox.y0 + 4, this.boundingBox.z0, this.boundingBox.x1, this.boundingBox.y1, this.boundingBox.z1, getFillingBlock(), false);

            // prevent floating lava
            if (this.mineShaftType == Type.NETHER || this.mineShaftType == Type.CRIMSON || this.mineShaftType == Type.WARPED) {
                BlockState liquidReplacementBlock = getFloorBlock();

                if(this.mineShaftType == Type.CRIMSON){
                    liquidReplacementBlock = Blocks.CRIMSON_HYPHAE.defaultBlockState();
                }
                else if(this.mineShaftType == Type.WARPED){
                    liquidReplacementBlock = Blocks.WARPED_HYPHAE.defaultBlockState();
                }

                replaceFloatingLiquids(world, box, this.boundingBox.x0 - 1, this.boundingBox.y0, this.boundingBox.z0 - 1, this.boundingBox.x1 + 1, this.boundingBox.y1 + 4, this.boundingBox.z1 + 1, liquidReplacementBlock);
            }

            //vines
            if (this.mineShaftType == Type.JUNGLE) {
                fillWithVines(world, random, box, 15, this.boundingBox.x0, this.boundingBox.y0, this.boundingBox.z0, this.boundingBox.x1, this.boundingBox.y1 + 4, this.boundingBox.z1, Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
            }
            else if (this.mineShaftType == Type.SWAMPORDARKFOREST) {
                fillWithVines(world, random, box, 7, this.boundingBox.x0, this.boundingBox.y0, this.boundingBox.z0, this.boundingBox.x1, this.boundingBox.y1 + 4, this.boundingBox.z1, Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
            }
            else if(this.mineShaftType == Type.WARPED){
                fillWithTwistedVines(world, random, box, 8, this.boundingBox.x0, this.boundingBox.y0, this.boundingBox.z0, this.boundingBox.x1, this.boundingBox.y1 + 4, this.boundingBox.z1);
            }
            else if(this.mineShaftType == Type.CRIMSON){
                fillWithWeepingVines(world, random, box, 8, this.boundingBox.x0, this.boundingBox.y0, this.boundingBox.z0, this.boundingBox.x1, this.boundingBox.y1 + 4, this.boundingBox.z1);
            }

            return true;
        }


        @Override
        public void move(int x, int y, int z) {
            super.move(x, y, z);

            for (MutableBoundingBox MutableBoundingBox : this.roomsLinkedToTheRoom) {
                MutableBoundingBox.move(x, y, z);
            }
        }
    }

    public static class Stairs extends RSMineshaftPieces.Piece {
        public Stairs(int p_i50449_1_, MutableBoundingBox p_i50449_2_, Direction p_i50449_3_, RSMineshaftPieces.Type p_i50449_4_) {
            super(RSStructurePieces.MINESHAFT_STAIRS_RS, p_i50449_1_, p_i50449_4_);
            this.setOrientation(p_i50449_3_);
            this.boundingBox = p_i50449_2_;
        }


        public Stairs(TemplateManager p_i50450_1_, CompoundNBT p_i50450_2_) {
            super(RSStructurePieces.MINESHAFT_STAIRS_RS, p_i50450_2_);
        }


        public static MutableBoundingBox findStairs(List<StructurePiece> listIn, Random rand, int x, int y, int z, Direction facing) {
            MutableBoundingBox MutableBoundingBox = new MutableBoundingBox(x, y - 5, z, x, y + 2, z);

            switch (facing) {
                case NORTH:
                default:
                    MutableBoundingBox.x1 = x + 2;
                    MutableBoundingBox.z0 = z - 8;
                    break;

                case SOUTH:
                    MutableBoundingBox.x1 = x + 2;
                    MutableBoundingBox.z1 = z + 8;
                    break;

                case WEST:
                    MutableBoundingBox.x0 = x - 8;
                    MutableBoundingBox.z1 = z + 2;
                    break;

                case EAST:
                    MutableBoundingBox.x1 = x + 8;
                    MutableBoundingBox.z1 = z + 2;
            }

            return StructurePiece.findCollisionPiece(listIn, MutableBoundingBox) != null ? null : MutableBoundingBox;
        }


        @Override
        public void addChildren(StructurePiece componentIn, List<StructurePiece> listIn, Random rand) {
            int i = this.getGenDepth();
            Direction enumfacing = this.getOrientation();

            if (enumfacing != null) {
                switch (enumfacing) {
                    case NORTH:
                    default:
                        RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0, this.boundingBox.y0, this.boundingBox.z0 - 1, Direction.NORTH, i);
                        break;

                    case SOUTH:
                        RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0, this.boundingBox.y0, this.boundingBox.z1 + 1, Direction.SOUTH, i);
                        break;

                    case WEST:
                        RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x0 - 1, this.boundingBox.y0, this.boundingBox.z0, Direction.WEST, i);
                        break;

                    case EAST:
                        RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.x1 + 1, this.boundingBox.y0, this.boundingBox.z0, Direction.EAST, i);
                }
            }
        }


        @Override
        public boolean postProcess(ISeedReader world, StructureManager structureAccessor, ChunkGenerator chunkGenerator, Random random, MutableBoundingBox box, ChunkPos chunkPos, BlockPos blockPos) {
            boolean isOceanType = this.mineShaftType == Type.OCEAN;
            boolean isNetherType = this.mineShaftType == Type.NETHER || this.mineShaftType == Type.CRIMSON || this.mineShaftType == Type.WARPED;
            if (!isNetherType && (isOceanType ? this.isAirInStructureBoundingBox(world, box) : this.edgesLiquid(world, box))) {
                return false;
            } else {
                this.generateBox(world, box, 0, 5, 0, 2, 7, 1, getFillingBlock(), getFillingBlock(), false);
                this.generateBox(world, box, 0, 0, 7, 2, 2, 8, getFillingBlock(), getFillingBlock(), false);

                for (int i = 0; i < 5; ++i) {
                    this.generateBox(world, box, 0, 5 - i - (i < 4 ? 1 : 0), 2 + i, 2, 7 - i, 2 + i, getFillingBlock(), getFillingBlock(), false);
                }

                // prevent floating lava
                if (isNetherType) {
                    BlockState liquidReplacementBlock = getFloorBlock();

                    if(this.mineShaftType == Type.CRIMSON){
                        liquidReplacementBlock = Blocks.CRIMSON_HYPHAE.defaultBlockState();
                    }
                    else if(this.mineShaftType == Type.WARPED){
                        liquidReplacementBlock = Blocks.WARPED_HYPHAE.defaultBlockState();
                    }

                    replaceFloatingLiquids(world, box, -1, 4, -1, 3, 8, 3, liquidReplacementBlock);
                    replaceFloatingLiquids(world, box, -1, -1, 6, 3, 3, 9, liquidReplacementBlock);

                    for (int i = 0; i < 5; ++i) {
                        replaceFloatingLiquids(world, box, -1, 5 - i - (i < 4 ? 1 : 0) - 1, 1 + i, 3, 8 - i, 3 + i, liquidReplacementBlock);
                    }
                }

                if (this.mineShaftType == Type.JUNGLE) {
                    fillWithVines(world, random, box, 15, 0, 0, 0, 2, 7, 8, Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
                }
                else if (this.mineShaftType == Type.SWAMPORDARKFOREST) {
                    fillWithVines(world, random, box, 7, 0, 0, 0, 2, 7, 8, Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
                }
                else if(this.mineShaftType == Type.WARPED){
                    fillWithTwistedVines(world, random, box, 8, 0, 0, 0, 2, 7, 8);
                    this.generateBox(world, box, 0, 4, 0, 2, 4, 1, Blocks.WARPED_NYLIUM.defaultBlockState(), Blocks.WARPED_NYLIUM.defaultBlockState(), false);
                    this.generateBox(world, box, 0, -1, 7, 2, -1, 8, Blocks.WARPED_NYLIUM.defaultBlockState(), Blocks.WARPED_NYLIUM.defaultBlockState(), false);
                    for (int i = 0; i < 5; ++i) {
                        this.generateBox(world, box, 0, 5 - i - (i < 4 ? 1 : 0) - 1, 2 + i, 2, 5 - i - (i < 4 ? 1 : 0) - 1, 2 + i, Blocks.WARPED_NYLIUM.defaultBlockState(), Blocks.WARPED_NYLIUM.defaultBlockState(), false);
                    }
                }
                else if(this.mineShaftType == Type.CRIMSON){
                    fillWithWeepingVines(world, random, box, 8, 0, 0, 0, 2, 7, 8);
                    this.generateBox(world, box, 0, 4, 0, 2, 4, 1, Blocks.CRIMSON_NYLIUM.defaultBlockState(), Blocks.CRIMSON_NYLIUM.defaultBlockState(), false);
                    this.generateBox(world, box, 0, -1, 7, 2, -1, 8, Blocks.CRIMSON_NYLIUM.defaultBlockState(), Blocks.CRIMSON_NYLIUM.defaultBlockState(), false);
                    for (int i = 0; i < 5; ++i) {
                        this.generateBox(world, box, 0, 5 - i - (i < 4 ? 1 : 0) - 1, 2 + i, 2, 5 - i - (i < 4 ? 1 : 0) - 1, 2 + i, Blocks.CRIMSON_NYLIUM.defaultBlockState(), Blocks.CRIMSON_NYLIUM.defaultBlockState(), false);
                    }
                }

                return true;
            }
        }
    }

    abstract static class Piece extends StructurePiece {
        protected RSMineshaftPieces.Type mineShaftType;

        public Piece(IStructurePieceType piece, int componentType, RSMineshaftPieces.Type mineshaftType) {
            super(piece, componentType);
            this.mineShaftType = mineshaftType;
        }


        public Piece(IStructurePieceType piece, CompoundNBT data) {
            super(piece, data);
            this.mineShaftType = Type.byId(data.getInt("MST"));
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void addAdditionalSaveData(CompoundNBT data) {
            data.putInt("MST", this.mineShaftType.ordinal());
        }


        /**
         * checks the entire StructureBoundingBox for Liquids
         */
        protected boolean isAirInStructureBoundingBox(IBlockReader worldIn, MutableBoundingBox boundingboxIn) {
            int xMin = Math.max(this.boundingBox.x0 - 1, boundingboxIn.x0);
            int yMin = Math.max(this.boundingBox.y0 - 1, boundingboxIn.y0);
            int zMin = Math.max(this.boundingBox.z0 - 1, boundingboxIn.z0);
            int xMax = Math.min(this.boundingBox.x1 + 1, boundingboxIn.x1);
            int yMax = Math.min(this.boundingBox.y1 + 1, boundingboxIn.y1);
            int zMax = Math.min(this.boundingBox.z1 + 1, boundingboxIn.z1);
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

        protected void fillWithWeepingVines(ISeedReader world, Random random, MutableBoundingBox boundingbox, int count, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax) {
            int vineLength;

            for(int currentCount = 0; currentCount < count; currentCount++){
                int x = random.nextInt(Math.max((xMax - xMin) + 1, 1)) + xMin;
                int z = random.nextInt(Math.max((zMax - zMin) + 1, 1)) + zMin;

                vineLength = 0;
                for (int y = yMax; y >= yMin; y--) {
                    BlockState aboveBlockState = this.getBlock(world, x, y + 1, z, boundingbox);
                    if (aboveBlockState.canOcclude() || aboveBlockState.is(Blocks.WEEPING_VINES_PLANT)) {
                        if (this.getBlock(world, x, y, z, boundingbox).isAir()) {
                            vineLength++;
                            if(random.nextInt(2) == 0 || vineLength >= 4 || !this.getBlock(world, x, y - 1, z, boundingbox).isAir()){
                                this.setBlockStateSimple(world, Blocks.WEEPING_VINES.defaultBlockState(), x, y, z, boundingbox);
                                break;
                            }
                            else{
                                this.setBlockStateSimple(world, Blocks.WEEPING_VINES_PLANT.defaultBlockState(), x, y, z, boundingbox);
                            }
                        }
                    }
                }
            }
        }

        protected void fillWithTwistedVines(ISeedReader world, Random random, MutableBoundingBox boundingbox, int count, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax) {
            int vineLength;

            for(int currentCount = 0; currentCount < count; currentCount++){
                int x = random.nextInt(Math.max((xMax - xMin) + 1, 1)) + xMin;
                int z = random.nextInt(Math.max((zMax - zMin) + 1, 1)) + zMin;

                vineLength = 0;

                for (int y = yMin; y <= yMax; y++) {
                    BlockState belowBlockState = this.getBlock(world, x, y - 1, z, boundingbox);
                    if (belowBlockState.canOcclude() || belowBlockState.is(Blocks.TWISTING_VINES_PLANT)) {
                        if (this.getBlock(world, x, y, z, boundingbox).isAir()) {
                            vineLength++;
                            if(random.nextInt(2) == 0 || vineLength >= 4 || !this.getBlock(world, x, y + 1, z, boundingbox).isAir()){
                                this.setBlockStateSimple(world, Blocks.TWISTING_VINES.defaultBlockState(), x, y, z, boundingbox);
                                break;
                            }
                            else{
                                this.setBlockStateSimple(world, Blocks.TWISTING_VINES_PLANT.defaultBlockState(), x, y, z, boundingbox);
                            }
                        }
                    }
                }
            }
        }

        protected void fillWithVines(ISeedReader world, Random random, MutableBoundingBox boundingbox, int count, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, BlockState sideBlock) {
            BlockState vineBlock;
            int vineLength;
            boolean placedSideBlock = false;

            for(int currentCount = 0; currentCount < count; currentCount++){
                int x = random.nextInt(Math.max((xMax - xMin) + 1, 1)) + xMin;
                int z = random.nextInt(Math.max((zMax - zMin) + 1, 1)) + zMin;

                Direction facing = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                int xOffset = x + facing.getStepX();
                int zOffset = z + facing.getStepZ();
                if(xOffset > xMax || xOffset < xMin || zOffset > zMax || zOffset < zMin){
                    continue;
                }

                vineBlock = Blocks.VINE.defaultBlockState().setValue(VineBlock.PROPERTY_BY_DIRECTION.get(facing), true);
                vineLength = 0;

                for (int y = yMax; y >= yMin; --y) {
                    if (this.getBlock(world, x, y, z, boundingbox).isAir()) {
                        BlockState aboveBlockState = this.getBlock(world, x, y + 1, z, boundingbox);
                        BlockState sideBlockState = this.getBlock(world, xOffset, y, zOffset, boundingbox);
                        BlockState sideAboveBlockState = this.getBlock(world, xOffset, y + 1, zOffset, boundingbox);

                        if(sideBlockState.isAir() && sideAboveBlockState.canOcclude()){
                            this.setBlockStateSimple(world, sideBlock, xOffset, y, zOffset, boundingbox);
                            placedSideBlock = true;
                        }

                        BlockState newVineBlock = vineBlock.updateShape(Direction.UP, vineBlock, world, new BlockPos(this.getWorldX(x, z), this.getWorldY(y), this.getWorldZ(x, z)), new BlockPos(0, 0, 0));
                        if (!newVineBlock.isAir()) {
                            vineLength++;
                            this.setBlockStateSimple(world, newVineBlock.setValue(VineBlock.UP, aboveBlockState.canOcclude()), x, y, z, boundingbox);
                        }
                        else if (aboveBlockState.is(Blocks.VINE) &&
                                ((facing == Direction.NORTH && aboveBlockState.getValue(VineBlock.NORTH)) ||
                                (facing == Direction.WEST && aboveBlockState.getValue(VineBlock.WEST)) ||
                                (facing == Direction.EAST && aboveBlockState.getValue(VineBlock.EAST)) ||
                                (facing == Direction.SOUTH && aboveBlockState.getValue(VineBlock.SOUTH))))
                        {
                            vineLength++;
                            this.setBlockStateSimple(world, vineBlock, x, y, z, boundingbox);
                        }
                        else if(aboveBlockState.canOcclude()){
                            for(Direction side : Direction.Plane.HORIZONTAL){
                                int xOffset2 = x + side.getStepX();
                                int zOffset2 = z + side.getStepZ();
                                if(this.getBlock(world, xOffset2, y, zOffset2, boundingbox).is(Blocks.VINE)){
                                    this.setBlockStateSimple(world, Blocks.VINE.defaultBlockState().setValue(VineBlock.UP, aboveBlockState.canOcclude()), x, y, z, boundingbox);
                                    break;
                                }
                            }
                        }
                        else if(placedSideBlock){
                            this.setBlockStateSimple(world, Blocks.AIR.defaultBlockState(), xOffset, y, zOffset, boundingbox);
                        }

                        placedSideBlock = false;
                    }

                    if (random.nextInt(3) == 0 || vineLength == 4) {
                        break;
                    }
                }
            }
        }

        protected void setBlockStateSimple(ISeedReader worldIn, BlockState blockstateIn, int x, int y, int z, MutableBoundingBox boundingboxIn) {
            BlockPos blockpos = new BlockPos(this.getWorldX(x, z), this.getWorldY(y), this.getWorldZ(x, z));
            if (boundingboxIn.isInside(blockpos)) {
                worldIn.setBlock(blockpos, blockstateIn, 2);
            }
        }

        protected void replaceFloatingLiquids(ISeedReader world, MutableBoundingBox box, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, BlockState mainBlock) {
            for(int y = minY; y <= maxY; y++) {
                for(int x = minX; x <= maxX; x++) {
                    for(int z = minZ; z <= maxZ; z++) {
                        if (!this.getBlock(world, x, y, z, box).getFluidState().isEmpty()) {
                            if(this.getBlock(world, x + 1, y, z, box).isAir() ||
                                this.getBlock(world, x, y + 1, z, box).isAir() ||
                                this.getBlock(world, x, y, z + 1, box).isAir() ||
                                this.getBlock(world, x - 1, y, z, box).isAir() ||
                                this.getBlock(world, x, y - 1, z, box).isAir() ||
                                this.getBlock(world, x, y, z - 1, box).isAir())
                            {
                                setBlockStateSimple(world, mainBlock, x, y, z, box);
                            }
                        }
                    }
                }
            }
        }

        protected BlockState getArchTopBlock() {
            switch (this.mineShaftType) {
                case ICY:
                    return Blocks.PACKED_ICE.defaultBlockState();

                case JUNGLE:
                    return Blocks.JUNGLE_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.X);

                case TAIGA:
                    return Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.X);

                case DESERT:
                    return Blocks.CHISELED_SANDSTONE.defaultBlockState();

                case END:
                    return Blocks.PURPUR_PILLAR.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z);

                case NETHER:
                    return Blocks.NETHER_BRICKS.defaultBlockState();

                case CRIMSON:
                    return Blocks.CRIMSON_PLANKS.defaultBlockState();

                case WARPED:
                    return Blocks.WARPED_PLANKS.defaultBlockState();

                case OCEAN:
                    return Blocks.DARK_PRISMARINE.defaultBlockState();

                case STONE:
                    return Blocks.STONE.defaultBlockState();

                case SAVANNA:
                    return Blocks.ACACIA_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.X);

                case SWAMPORDARKFOREST:
                    return Blocks.DARK_OAK_PLANKS.defaultBlockState();

                case BIRCH:
                default:
                    return Blocks.STRIPPED_BIRCH_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.X);
            }
        }


        protected ResourceLocation getChestLoot() {
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

                case CRIMSON:
                    return CRIMSON_CHEST_ID;

                case WARPED:
                    return WARPED_CHEST_ID;

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
                    return Blocks.ICE.defaultBlockState();

                case JUNGLE:
                    return Blocks.JUNGLE_PLANKS.defaultBlockState();

                case TAIGA:
                    return Blocks.SPRUCE_PLANKS.defaultBlockState();

                case DESERT:
                    return Blocks.SMOOTH_SANDSTONE.defaultBlockState();

                case END:
                    return Blocks.PURPUR_BLOCK.defaultBlockState();

                case NETHER:
                    return Blocks.NETHER_BRICKS.defaultBlockState();

                case CRIMSON:
                    return Blocks.CRIMSON_PLANKS.defaultBlockState();

                case WARPED:
                    return Blocks.WARPED_PLANKS.defaultBlockState();

                case OCEAN:
                    return Blocks.PRISMARINE_BRICKS.defaultBlockState();

                case STONE:
                    return Blocks.ANDESITE.defaultBlockState();

                case SAVANNA:
                    return Blocks.ACACIA_PLANKS.defaultBlockState();

                case SWAMPORDARKFOREST:
                    return Blocks.GRASS_BLOCK.defaultBlockState();

                case BIRCH:
                default:
                    return Blocks.BIRCH_PLANKS.defaultBlockState();
            }
        }


        protected BlockState getArchSupportBlock(Random random) {
            switch (this.mineShaftType) {
                case ICY:
                    return Blocks.ICE.defaultBlockState();

                case JUNGLE:
                    return Blocks.JUNGLE_FENCE.defaultBlockState();

                case TAIGA:
                    return Blocks.SPRUCE_FENCE.defaultBlockState();

                case DESERT:
                    return Blocks.SANDSTONE_WALL.defaultBlockState();

                case END:
                    return Blocks.PURPUR_PILLAR.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y);

                case NETHER:
                    return Blocks.NETHER_BRICK_WALL.defaultBlockState();

                case CRIMSON:
                    return Blocks.CRIMSON_FENCE.defaultBlockState();

                case WARPED:
                    return Blocks.WARPED_FENCE.defaultBlockState();

                case OCEAN:
                    return Blocks.PRISMARINE_WALL.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true);

                case STONE:
                    return random.nextInt(10) < 3 ? Blocks.MOSSY_COBBLESTONE_WALL.defaultBlockState() : Blocks.COBBLESTONE_WALL.defaultBlockState();

                case SAVANNA:
                    return Blocks.ACACIA_FENCE.defaultBlockState();

                case SWAMPORDARKFOREST:
                    return Blocks.DARK_OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y);

                case BIRCH:
                default:
                    return Blocks.BIRCH_FENCE.defaultBlockState();
            }
        }


        protected BlockState getFillingBlock() {
            if (this.mineShaftType == Type.OCEAN) {
                return Blocks.WATER.defaultBlockState();
            }
            return Blocks.CAVE_AIR.defaultBlockState();
        }


        protected BlockState getRailBlock() {
            if (this.mineShaftType == Type.OCEAN) {
                return Blocks.OAK_TRAPDOOR.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true);
            }
            return Blocks.RAIL.defaultBlockState().setValue(RailBlock.SHAPE, RailShape.NORTH_SOUTH);
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

                case CRIMSON:
                    return RepurposedStructures.mobSpawnerManager.getSpawnerMob(CRIMSON_SPAWNER_ID, random);

                case WARPED:
                    return RepurposedStructures.mobSpawnerManager.getSpawnerMob(WARPED_SPAWNER_ID, random);

                case BIRCH:
                default:
                    return RepurposedStructures.mobSpawnerManager.getSpawnerMob(BIRCH_SPAWNER_ID, random);
            }
        }


        protected BlockState getDecorativeBlock(Random random) {
            switch (this.mineShaftType) {
                case OCEAN:
                    return random.nextBoolean() ? Blocks.SEAGRASS.defaultBlockState() : Blocks.TALL_SEAGRASS.defaultBlockState();

                case ICY:
                    return Blocks.ICE.defaultBlockState();

                case NETHER:
                    return Blocks.FIRE.defaultBlockState();

                case CRIMSON:
                    return Blocks.CRIMSON_ROOTS.defaultBlockState();

                case WARPED:
                    return Blocks.WARPED_ROOTS.defaultBlockState();

                case END:
                    return Blocks.CHORUS_FLOWER.defaultBlockState().setValue(ChorusFlowerBlock.AGE, 5);

                default:
                    return Blocks.COBWEB.defaultBlockState();
            }
        }
    }
}
