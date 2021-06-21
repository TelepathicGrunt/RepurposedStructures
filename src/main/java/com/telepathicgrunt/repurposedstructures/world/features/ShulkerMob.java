package com.telepathicgrunt.repurposedstructures.world.features;

import com.telepathicgrunt.repurposedstructures.mixin.entities.ShulkerEntityInvoker;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;


public class ShulkerMob extends Feature<DefaultFeatureConfig> {

    public ShulkerMob() {
        super(DefaultFeatureConfig.CODEC);
    }

    /**
     * This is necessary due to https://bugs.mojang.com/browse/MC-108149
     * TLDR: trying to spawn Shulker mobs from nbt files will not work and they will teleport back
     * to the original world position that they were saved at instead of the new structure's position.
     */
    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        // move down to spawn at the jigsaw block calling this
        BlockPos position = context.getOrigin().down();

        ShulkerEntity shulkerEntity = EntityType.SHULKER.create(context.getWorld().toServerWorld());
        shulkerEntity.setPersistent();
        shulkerEntity.updatePosition(
                (double)position.getX() + 0.5D,
                position.getY(),
                (double)position.getZ() + 0.5D);

        Direction shulkerAttachment = Direction.UP;
        for(Direction direction : Direction.values()) {

            BlockState blockStateCurrentSpot = context.getWorld().getBlockState(position);
            BlockState blockStateAttachmentSpot = context.getWorld().getBlockState(position.offset(direction));

            if (blockStateCurrentSpot.isAir() && blockStateAttachmentSpot.isOpaque()) {
                shulkerAttachment = direction;
                break;
            }
        }

        ((ShulkerEntityInvoker)shulkerEntity).repurposedstructures_callSetAttachedFace(shulkerAttachment);
        context.getWorld().spawnEntityAndPassengers(shulkerEntity);
        return true;
    }
}