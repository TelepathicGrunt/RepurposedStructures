package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RSFeatures;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class SwampVillageVines extends Feature<NoFeatureConfig> {

    public SwampVillageVines(Codec<NoFeatureConfig> configFactory) {
        super(configFactory);
    }


    @Override
    public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
        if (world.isAir(position) && structureAccessor.getStructuresWithChildren(ChunkSectionPos.from(position), RSFeatures.SWAMP_VILLAGE).findAny().isPresent()) {
            RSFeatures.SHORT_VINES.generate(world, structureAccessor, chunkGenerator, random, position, NoFeatureConfig.DEFAULT);
            return true;
        }
        return false;
    }
}