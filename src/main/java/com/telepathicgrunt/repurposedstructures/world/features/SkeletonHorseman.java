package com.telepathicgrunt.repurposedstructures.world.features;

import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.features.configs.GenericMobConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;


public class SkeletonHorseman extends Feature<GenericMobConfig> {

    public SkeletonHorseman() {
        super(GenericMobConfig.CODEC);
    }

    @Override
    public boolean generate(FeatureContext<GenericMobConfig> context) {

        SkeletonHorseEntity skeletonHorseEntity = EntityType.SKELETON_HORSE.create(context.getWorld().toServerWorld());
        skeletonHorseEntity.setPersistent();
        skeletonHorseEntity.refreshPositionAndAngles(
                (double)context.getOrigin().getX() + 0.5D,
                context.getOrigin().getY(),
                (double)context.getOrigin().getZ() + 0.5D,
                0.0F,
                0.0F);
        skeletonHorseEntity.initialize(context.getWorld(), context.getWorld().getLocalDifficulty(context.getOrigin()), SpawnReason.STRUCTURE, null, null);
        SkeletonEntity skeletonEntity = EntityType.SKELETON.create(context.getWorld().toServerWorld());

        // Do this first as this attaches a bow automatically. We may want to override the bow later.
        skeletonEntity.initialize(context.getWorld(), context.getWorld().getLocalDifficulty(context.getOrigin()), SpawnReason.STRUCTURE, null, null);


        if(context.getConfig().heldItem != null){
            ItemStack heldItem = new ItemStack(context.getConfig().heldItem);
            skeletonEntity.setStackInHand(Hand.MAIN_HAND, GeneralUtils.enchantRandomly(context.getRandom(), heldItem, 0.1F));
            skeletonEntity.setLeftHanded(context.getRandom().nextFloat() < 0.05F);
        }

        if(context.getConfig().helmet != null){
            skeletonEntity.equipStack(EquipmentSlot.HEAD, GeneralUtils.enchantRandomly(context.getRandom(), context.getConfig().helmet.getDefaultStack(), 0.075F));
        }
        if(context.getConfig().chestplate != null){
            skeletonEntity.equipStack(EquipmentSlot.CHEST, GeneralUtils.enchantRandomly(context.getRandom(), context.getConfig().chestplate.getDefaultStack(), 0.075F));
        }
        if(context.getConfig().leggings != null){
            skeletonEntity.equipStack(EquipmentSlot.LEGS, GeneralUtils.enchantRandomly(context.getRandom(), context.getConfig().leggings.getDefaultStack(), 0.075F));
        }
        if(context.getConfig().boots != null){
            skeletonEntity.equipStack(EquipmentSlot.FEET, GeneralUtils.enchantRandomly(context.getRandom(), context.getConfig().boots.getDefaultStack(), 0.075F));
        }

        skeletonEntity.setPersistent();
        skeletonEntity.setHealth(context.getConfig().health);
        skeletonEntity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(context.getConfig().speedModifier);
        skeletonEntity.refreshPositionAndAngles(
                (double)context.getOrigin().getX() + 0.5D,
                context.getOrigin().getY() + 1,
                (double)context.getOrigin().getZ() + 0.5D,
                0.0F,
                0.0F);
        skeletonEntity.startRiding(skeletonHorseEntity);

        context.getWorld().spawnEntityAndPassengers(skeletonHorseEntity);
        return true;
    }
}