package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;


public class SwampVillageVines extends Feature<NoFeatureConfig> {

    public SwampVillageVines(Codec<NoFeatureConfig> configFactory) {
        super(configFactory);
    }


    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
        if(GeneralUtils.isWorldBlacklisted(world)) return false;
        if (world.isEmptyBlock(position) && world.startsForFeature(SectionPos.of(position), RSStructures.SWAMP_VILLAGE.get()).findAny().isPresent()) {
            RSFeatures.SHORT_VINES.get().place(world, chunkGenerator, random, position, NoFeatureConfig.NONE);
            return true;
        }
        return false;
    }
}