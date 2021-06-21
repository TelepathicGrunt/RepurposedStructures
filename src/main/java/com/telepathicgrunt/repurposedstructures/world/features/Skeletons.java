package com.telepathicgrunt.repurposedstructures.world.features;

import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.features.configs.GenericMobConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;


public class Skeletons extends Feature<GenericMobConfig> {

    public Skeletons() {
        super(GenericMobConfig.CODEC);
    }

    @Override
    public boolean generate(FeatureContext<GenericMobConfig> context) {

        SkeletonEntity skeletonEntity = EntityType.SKELETON.create(context.getWorld().toServerWorld());

        // Do this first as this attaches a bow automatically. We may want to override the bow later.
        skeletonEntity.initialize(context.getWorld(), context.getWorld().getLocalDifficulty(context.getOrigin()), SpawnReason.STRUCTURE, null, null);

        if(context.getConfig().heldItem != null){
            ItemStack heldItem = new ItemStack(context.getConfig().heldItem);
            skeletonEntity.setStackInHand(Hand.MAIN_HAND, GeneralUtils.enchantRandomly(context.getRandom(), heldItem, 0.333F));
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
        skeletonEntity.refreshPositionAndAngles(
                (double)context.getOrigin().getX() + 0.5D,
                context.getOrigin().getY(),
                (double)context.getOrigin().getZ() + 0.5D,
                0.0F,
                0.0F);

        skeletonEntity.setHealth(context.getConfig().health);
        skeletonEntity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(context.getConfig().speedModifier);
        context.getWorld().spawnEntityAndPassengers(skeletonEntity);
        return true;
    }

}