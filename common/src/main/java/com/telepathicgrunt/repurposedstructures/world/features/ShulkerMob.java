package com.telepathicgrunt.repurposedstructures.world.features;

import com.telepathicgrunt.repurposedstructures.mixins.entities.ShulkerEntityInvoker;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;


public class ShulkerMob extends Feature<NoneFeatureConfiguration> {

    public ShulkerMob() {
        super(NoneFeatureConfiguration.CODEC);
    }

    /**
     * This is necessary due to https://bugs.mojang.com/browse/MC-108149
     * TLDR: trying to spawn Shulker mobs from nbt files will not work and they will teleport back
     * to the original world position that they were saved at instead of the new structure's position.
     */
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        // move down to spawn at the jigsaw block calling this
        BlockPos position = context.origin().below();

        Shulker shulkerEntity = EntityType.SHULKER.create(context.level().getLevel());
        shulkerEntity.setPersistenceRequired();
        shulkerEntity.absMoveTo(
                (double)position.getX() + 0.5D,
                position.getY(),
                (double)position.getZ() + 0.5D);

        Direction shulkerAttachment = Direction.UP;
        for(Direction direction : Direction.values()) {

            BlockState blockStateCurrentSpot = context.level().getBlockState(position);
            BlockState blockStateAttachmentSpot = context.level().getBlockState(position.relative(direction));

            if (blockStateCurrentSpot.isAir() && blockStateAttachmentSpot.canOcclude()) {
                shulkerAttachment = direction;
                break;
            }
        }

        ((ShulkerEntityInvoker)shulkerEntity).repurposedstructures_callSetAttachFace(shulkerAttachment);
        context.level().addFreshEntityWithPassengers(shulkerEntity);
        return true;
    }
}