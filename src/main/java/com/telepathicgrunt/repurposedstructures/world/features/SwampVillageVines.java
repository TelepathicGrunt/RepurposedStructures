package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Random;


public class SwampVillageVines extends Feature<DefaultFeatureConfig> {

    public SwampVillageVines(Codec<DefaultFeatureConfig> configFactory) {
        super(configFactory);
    }


    @Override
    public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockPos position, DefaultFeatureConfig config) {
        if (world.isAir(position) && structureAccessor.getStructuresWithChildren(ChunkSectionPos.from(position), RSFeatures.SWAMP_VILLAGE).findAny().isPresent()) {
            RSFeatures.SHORT_VINES.generate(world, structureAccessor, chunkGenerator, random, position, DefaultFeatureConfig.DEFAULT);
            return true;
        }
        return false;
    }
}