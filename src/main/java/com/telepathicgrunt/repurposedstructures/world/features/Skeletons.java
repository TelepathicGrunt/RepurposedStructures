package com.telepathicgrunt.repurposedstructures.world.features;

import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.features.configs.GenericMobConfig;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;


public class Skeletons extends Feature<GenericMobConfig> {

    public Skeletons() {
        super(GenericMobConfig.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<GenericMobConfig> context) {

        Skeleton skeletonEntity = EntityType.SKELETON.create(context.level().getLevel());

        // Do this first as this attaches a bow automatically. We may want to override the bow later.
        skeletonEntity.finalizeSpawn(context.level(), context.level().getCurrentDifficultyAt(context.origin()), MobSpawnType.STRUCTURE, null, null);

        if(context.config().heldItem != null){
            ItemStack heldItem = new ItemStack(context.config().heldItem);
            skeletonEntity.setItemInHand(InteractionHand.MAIN_HAND, GeneralUtils.enchantRandomly(context.random(), heldItem, 0.333F));
            skeletonEntity.setLeftHanded(context.random().nextFloat() < 0.05F);
        }

        if(context.config().helmet != null){
            skeletonEntity.setItemSlot(EquipmentSlot.HEAD, GeneralUtils.enchantRandomly(context.random(), context.config().helmet.getDefaultInstance(), 0.075F));
        }
        if(context.config().chestplate != null){
            skeletonEntity.setItemSlot(EquipmentSlot.CHEST, GeneralUtils.enchantRandomly(context.random(), context.config().chestplate.getDefaultInstance(), 0.075F));
        }
        if(context.config().leggings != null){
            skeletonEntity.setItemSlot(EquipmentSlot.LEGS, GeneralUtils.enchantRandomly(context.random(), context.config().leggings.getDefaultInstance(), 0.075F));
        }
        if(context.config().boots != null){
            skeletonEntity.setItemSlot(EquipmentSlot.FEET, GeneralUtils.enchantRandomly(context.random(), context.config().boots.getDefaultInstance(), 0.075F));
        }

        skeletonEntity.setPersistenceRequired();
        skeletonEntity.moveTo(
                (double)context.origin().getX() + 0.5D,
                context.origin().getY(),
                (double)context.origin().getZ() + 0.5D,
                0.0F,
                0.0F);

        skeletonEntity.setHealth(context.config().health);
        skeletonEntity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(context.config().health);
        skeletonEntity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(context.config().speedModifier);
        context.level().addFreshEntityWithPassengers(skeletonEntity);
        return true;
    }

}