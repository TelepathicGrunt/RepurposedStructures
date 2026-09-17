package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.MapCodec;
import com.telepathicgrunt.repurposedstructures.mixins.entities.ShulkerEntityInvoker;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;


public record ShulkerMob() implements Feature {

    public static final MapCodec<ShulkerMob> CODEC = MapCodec.unit(ShulkerMob::new);

    @Override
    public MapCodec<ShulkerMob> codec() {
        return CODEC;
    }

    /**
     * This is necessary due to https://bugs.mojang.com/browse/MC-108149
     * TLDR: trying to spawn Shulker mobs from nbt files will not work and they will teleport back
     * to the original world position that they were saved at instead of the new structure's position.
     */
    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {
        // move down to spawn at the jigsaw block calling this
        BlockPos position = origin.below();

        Shulker shulkerEntity = EntityTypes.SHULKER.create(level.getLevel(), EntitySpawnReason.STRUCTURE);
        if (shulkerEntity == null) {
            return false;
        }
        shulkerEntity.setPersistenceRequired();
        shulkerEntity.setPos(
                (double)position.getX() + 0.5D,
                position.getY(),
                (double)position.getZ() + 0.5D);

        Direction shulkerAttachment = Direction.UP;
        for(Direction direction : Direction.values()) {

            BlockState blockStateCurrentSpot = level.getBlockState(position);
            BlockState blockStateAttachmentSpot = level.getBlockState(position.relative(direction));

            if (blockStateCurrentSpot.isAir() && blockStateAttachmentSpot.canOcclude()) {
                shulkerAttachment = direction;
                break;
            }
        }

        ((ShulkerEntityInvoker)shulkerEntity).repurposedstructures$callSetAttachFace(shulkerAttachment);
        level.addFreshEntityWithPassengers(shulkerEntity);
        return true;
    }
}