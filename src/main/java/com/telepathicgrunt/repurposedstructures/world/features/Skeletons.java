package com.telepathicgrunt.repurposedstructures.world.features;

import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.features.configs.GenericMobConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class Skeletons extends Feature<GenericMobConfig> {

    public Skeletons() {
        super(GenericMobConfig.CODEC);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, GenericMobConfig config) {

        SkeletonEntity skeletonEntity = EntityType.SKELETON.create(world.getLevel());

        // Do this first as this attaches a bow automatically. We may want to override the bow later.
        skeletonEntity.finalizeSpawn(world, world.getCurrentDifficultyAt(position), SpawnReason.STRUCTURE, null, null);

        if(config.heldItem != null){
            ItemStack heldItem = new ItemStack(config.heldItem);
            skeletonEntity.setItemInHand(Hand.MAIN_HAND, GeneralUtils.enchantRandomly(random, heldItem, 0.333F));
            skeletonEntity.setLeftHanded(random.nextFloat() < 0.05F);
        }

        if(config.helmet != null) {
            skeletonEntity.setItemSlot(EquipmentSlotType.HEAD, GeneralUtils.enchantRandomly(random, config.helmet.getDefaultInstance(), 0.075F));
        }
        if(config.chestplate != null){
            skeletonEntity.setItemSlot(EquipmentSlotType.CHEST, GeneralUtils.enchantRandomly(random, config.chestplate.getDefaultInstance(), 0.075F));
        }
        if(config.leggings != null){
            skeletonEntity.setItemSlot(EquipmentSlotType.LEGS, GeneralUtils.enchantRandomly(random, config.leggings.getDefaultInstance(), 0.075F));
        }
        if(config.boots != null){
            skeletonEntity.setItemSlot(EquipmentSlotType.FEET, GeneralUtils.enchantRandomly(random, config.boots.getDefaultInstance(), 0.075F));
        }

        skeletonEntity.setPersistenceRequired();
        skeletonEntity.absMoveTo(
                (double)position.getX() + 0.5D,
                position.getY(),
                (double)position.getZ() + 0.5D,
                0.0F,
                0.0F);

        skeletonEntity.setHealth(config.health);
        skeletonEntity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(config.speedModifier);
        world.addFreshEntityWithPassengers(skeletonEntity);
        return true;
    }

}