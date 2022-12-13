package com.telepathicgrunt.repurposedstructures.world.features;

import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;


public class DrownedWithArmor extends Feature<NoneFeatureConfiguration> {

    public DrownedWithArmor() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {

        // only spawn drowned if in water
        if(!context.level().getBlockState(context.origin()).getFluidState().is(FluidTags.WATER)) return false;

        // move down to spawn at the jigsaw block calling this
        BlockPos position = context.origin().below();

        Drowned drownedEntity = EntityType.DROWNED.create(context.level().getLevel());
        if(drownedEntity == null) return false;

        if(context.random().nextFloat() < 0.45F) {
            ItemStack stoneSword = new ItemStack(Items.STONE_SWORD);

            // enchant sword
            drownedEntity.setItemInHand(InteractionHand.MAIN_HAND, GeneralUtils.enchantRandomly(context.random(), stoneSword, 0.25F));
            drownedEntity.setDropChance(EquipmentSlot.MAINHAND, 0.4f);
            drownedEntity.setLeftHanded(context.random().nextFloat() < 0.05F);
        }

        if(context.level().getRandom().nextFloat() < 0.4f) {
            drownedEntity.setItemSlot(EquipmentSlot.HEAD, context.level().getRandom().nextFloat() < 0.2f ? Items.IRON_HELMET.getDefaultInstance() : Items.CHAINMAIL_HELMET.getDefaultInstance());
        }
        if(context.level().getRandom().nextFloat() < 0.4f) {
            drownedEntity.setItemSlot(EquipmentSlot.CHEST, context.level().getRandom().nextFloat() < 0.2f ? Items.IRON_CHESTPLATE.getDefaultInstance() : Items.CHAINMAIL_CHESTPLATE.getDefaultInstance());
        }
        if(context.level().getRandom().nextFloat() < 0.4f) {
            drownedEntity.setItemSlot(EquipmentSlot.LEGS, context.level().getRandom().nextFloat() < 0.2f ? Items.IRON_LEGGINGS.getDefaultInstance() : Items.CHAINMAIL_LEGGINGS.getDefaultInstance());
        }
        if(context.level().getRandom().nextFloat() < 0.4f) {
            drownedEntity.setItemSlot(EquipmentSlot.FEET, context.level().getRandom().nextFloat() < 0.2f ? Items.IRON_BOOTS.getDefaultInstance() : Items.CHAINMAIL_BOOTS.getDefaultInstance());
        }

        drownedEntity.setPersistenceRequired();
        drownedEntity.moveTo(
                (double)position.getX() + 0.5D,
                position.getY(),
                (double)position.getZ() + 0.5D,
                0.0F,
                0.0F);
        drownedEntity.finalizeSpawn(context.level(), context.level().getCurrentDifficultyAt(position), MobSpawnType.STRUCTURE, null, null);
        context.level().addFreshEntityWithPassengers(drownedEntity);
        return true;
    }
}