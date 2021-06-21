package com.telepathicgrunt.repurposedstructures.world.features;

import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;


public class DrownedWithArmor extends Feature<DefaultFeatureConfig> {

    public DrownedWithArmor() {
        super(DefaultFeatureConfig.CODEC);
    }


    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {

        // only spawn drowned if in water
        if(!context.getWorld().getBlockState(context.getOrigin()).getFluidState().isIn(FluidTags.WATER)) return false;

        // move down to spawn at the jigsaw block calling this
        BlockPos position = context.getOrigin().down();

        DrownedEntity drownedEntity = EntityType.DROWNED.create(context.getWorld().toServerWorld());
        if(drownedEntity == null) return false;

        if(context.getRandom().nextFloat() < 0.45F){
            ItemStack stoneSword = new ItemStack(Items.STONE_SWORD);

            // enchant sword
            drownedEntity.setStackInHand(Hand.MAIN_HAND, GeneralUtils.enchantRandomly(context.getRandom(), stoneSword, 0.25F));
            drownedEntity.setEquipmentDropChance(EquipmentSlot.MAINHAND, 0.4f);
            drownedEntity.setLeftHanded(context.getRandom().nextFloat() < 0.05F);
        }

        if(context.getWorld().getRandom().nextFloat() < 0.4f){
            drownedEntity.equipStack(EquipmentSlot.HEAD, context.getWorld().getRandom().nextFloat() < 0.2f ? Items.IRON_HELMET.getDefaultStack() : Items.CHAINMAIL_HELMET.getDefaultStack());
        }
        if(context.getWorld().getRandom().nextFloat() < 0.4f){
            drownedEntity.equipStack(EquipmentSlot.CHEST, context.getWorld().getRandom().nextFloat() < 0.2f ? Items.IRON_CHESTPLATE.getDefaultStack() : Items.CHAINMAIL_CHESTPLATE.getDefaultStack());
        }
        if(context.getWorld().getRandom().nextFloat() < 0.4f){
            drownedEntity.equipStack(EquipmentSlot.LEGS, context.getWorld().getRandom().nextFloat() < 0.2f ? Items.IRON_LEGGINGS.getDefaultStack() : Items.CHAINMAIL_LEGGINGS.getDefaultStack());
        }
        if(context.getWorld().getRandom().nextFloat() < 0.4f){
            drownedEntity.equipStack(EquipmentSlot.FEET, context.getWorld().getRandom().nextFloat() < 0.2f ? Items.IRON_BOOTS.getDefaultStack() : Items.CHAINMAIL_BOOTS.getDefaultStack());
        }

        drownedEntity.setPersistent();
        drownedEntity.refreshPositionAndAngles(
                (double)position.getX() + 0.5D,
                position.getY(),
                (double)position.getZ() + 0.5D,
                0.0F,
                0.0F);
        drownedEntity.initialize(context.getWorld(), context.getWorld().getLocalDifficulty(position), SpawnReason.STRUCTURE, null, null);
        context.getWorld().spawnEntityAndPassengers(drownedEntity);
        return true;
    }
}