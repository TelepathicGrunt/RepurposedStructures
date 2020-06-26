package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class JungleVillageVines extends Feature<DefaultFeatureConfig> {

    public JungleVillageVines(Codec<DefaultFeatureConfig> configFactory) {
        super(configFactory);
    }


    @Override
    public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockPos position, DefaultFeatureConfig config) {
        if (structureAccessor.getStructuresWithChildren(ChunkSectionPos.from(position), RSFeatures.JUNGLE_VILLAGE).findAny().isPresent()) {
            RSFeatures.SHORT_VINES.generate(world, structureAccessor, chunkGenerator, random, position, DefaultFeatureConfig.DEFAULT);
            return true;
        }
        return false;
    }
}