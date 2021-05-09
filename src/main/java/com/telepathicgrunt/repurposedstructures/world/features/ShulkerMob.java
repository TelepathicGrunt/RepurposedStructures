package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.ShulkerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;


public class ShulkerMob extends Feature<NoFeatureConfig> {

    public ShulkerMob(Codec<NoFeatureConfig> configFactory) {
        super(configFactory);
    }


    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
        // move down to spawn at the jigsaw block calling this
        position = position.below();

        ShulkerEntity shulkerEntity = EntityType.SHULKER.create(world.getLevel());
        shulkerEntity.setPersistenceRequired();
        shulkerEntity.setPos(
                (double)position.getX() + 0.5D,
                position.getY(),
                (double)position.getZ() + 0.5D);

        shulkerEntity.setAttachPosition(position);
        world.addFreshEntityWithPassengers(shulkerEntity);
        return true;
    }
}