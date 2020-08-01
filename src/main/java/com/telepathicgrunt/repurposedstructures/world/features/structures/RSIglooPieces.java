package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Map;
import java.util.Random;


public class RSIglooPieces {
    private static final ResourceLocation IGLOO_MIDDLE_RL = new ResourceLocation("igloo/middle");
    private static final ResourceLocation IGLOO_BOTTOM_RL = new ResourceLocation("igloo/bottom");
    private static final Map<ResourceLocation, BlockPos> OFFSET_1 = ImmutableMap.of(IGLOO_MIDDLE_RL, new BlockPos(1, 3, 1), IGLOO_BOTTOM_RL, new BlockPos(3, 6, 7));
    private static final Map<ResourceLocation, BlockPos> OFFSET_2 = ImmutableMap.of(IGLOO_MIDDLE_RL, new BlockPos(2, -3, 4), IGLOO_BOTTOM_RL, new BlockPos(0, -3, -2));

    public static void func_207617_a(TemplateManager templateManager, ResourceLocation topPieceRL, Block floorBlock, BlockPos position, Rotation rotationIn, List<StructurePiece> pieceList, Random random, NoFeatureConfig p_207617_5_) {
        if (random.nextDouble() < 0.5D) {
            int basementY = random.nextInt(8) + 4;
            pieceList.add(new RSIglooPieces.Piece(templateManager, IGLOO_BOTTOM_RL, position, rotationIn, basementY * 3, null));

            for (int middleY = 0; middleY < basementY - 1; ++middleY) {
                pieceList.add(new RSIglooPieces.Piece(templateManager, IGLOO_MIDDLE_RL, position, rotationIn, middleY * 3, null));
            }
        }

        pieceList.add(new RSIglooPieces.Piece(templateManager, topPieceRL, position, rotationIn, 0, floorBlock));
    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation pieceRL;
        private final Rotation rotation;
        private final Block floorBlock;

        public Piece(TemplateManager templateManager, ResourceLocation pieceRL, BlockPos position, Rotation rotationIn, int heightOffset, Block floorBlockIn) {
            super(StructurePieces.RS_IGLOO_PIECE, 0);
            this.pieceRL = pieceRL;
            BlockPos blockpos = OFFSET_2.containsKey(this.pieceRL) ? OFFSET_2.get(this.pieceRL) : new BlockPos(0, -1, 0);
            this.templatePosition = position.add(blockpos.getX(), blockpos.getY() - heightOffset, blockpos.getZ());
            this.rotation = rotationIn;
            this.func_207614_a(templateManager);
            this.floorBlock = floorBlockIn;
        }


        public Piece(TemplateManager templateManager, CompoundNBT data) {
            super(StructurePieces.RS_IGLOO_PIECE, data);
            this.pieceRL = new ResourceLocation(data.getString("Template"));
            this.rotation = Rotation.valueOf(data.getString("Rot"));
            this.func_207614_a(templateManager);
            this.floorBlock = Registry.BLOCK.getOrDefault(new ResourceLocation(data.getString("FloorBlock")));
        }


        private void func_207614_a(TemplateManager templateManager) {
            Template template = templateManager.getTemplateDefaulted(this.pieceRL);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset(OFFSET_1.containsKey(this.pieceRL) ? OFFSET_1.get(this.pieceRL) : new BlockPos(3, 4, 5)).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            this.setup(template, this.templatePosition, placementsettings);
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void readAdditional(CompoundNBT tagCompound) {
            super.readAdditional(tagCompound);
            tagCompound.putString("Template", this.pieceRL.toString());
            tagCompound.putString("Rot", this.rotation.name());
            tagCompound.putString("FloorBlock", this.floorBlock == null ? "" : Registry.BLOCK.getKey(this.floorBlock).toString());
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
            if ("chest".equals(function)) {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                TileEntity tileentity = worldIn.getTileEntity(pos.down());
                if (tileentity instanceof ChestTileEntity) {
                    ((ChestTileEntity) tileentity).setLootTable(LootTables.CHESTS_IGLOO_CHEST, rand.nextLong());
                }

            }
        }


        @Override
        public boolean generate(ISeedReader world, StructureManager structureAccessor, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            BlockPos offset = OFFSET_1.containsKey(this.pieceRL) ? OFFSET_1.get(this.pieceRL) : new BlockPos(3, 4, 5);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset(offset).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            BlockPos blockpos = OFFSET_2.containsKey(this.pieceRL) ? OFFSET_2.get(this.pieceRL) : new BlockPos(0, -1, 0);
            BlockPos blockpos1 = this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(3 - blockpos.getX(), 0, 0 - blockpos.getZ())));
            int terrainSurfaceY = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
            BlockPos blockpos2 = this.templatePosition;
            this.templatePosition = this.templatePosition.add(0, terrainSurfaceY - 90 - 1, 0);
            boolean flag = super.generate(world, structureAccessor, generator, random, boundingBox, chunkPos, blockPos);

            if (floorBlock != null) {
                BlockPos blockpos3 = this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(3, 0, 5)));
                BlockState blockstate = world.getBlockState(blockpos3.down());
                if (blockstate.getBlock() != Blocks.LADDER) {
                    world.setBlockState(blockpos3.up(), floorBlock.getDefaultState(), 3);
                    world.setBlockState(blockpos3, floorBlock.getDefaultState(), 3);
                }
            }

            this.templatePosition = blockpos2;
            return flag;
        }
    }
}
