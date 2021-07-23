package com.telepathicgrunt.repurposedstructures.world.features;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;


public class WitherSkeletonWithBow extends Feature<NoneFeatureConfiguration> {

    public WitherSkeletonWithBow() {
        super(NoneFeatureConfiguration.CODEC);
    }


    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {

        // move down to spawn at the jigsaw block calling this
        BlockPos position = context.origin().below();

        WitherSkeleton witherEntity = EntityType.WITHER_SKELETON.create(context.level().getLevel());
        witherEntity.setPersistenceRequired();
        witherEntity.moveTo(
                (double)position.getX() + 0.5D,
                position.getY(),
                (double)position.getZ() + 0.5D,
                0.0F,
                0.0F);

        witherEntity.getAttribute(Attributes.FOLLOW_RANGE)
                .addPermanentModifier(new AttributeModifier(
                        "Random spawn bonus",
                        (context.random().nextGaussian() * 0.3D) + 0.5D,
                        AttributeModifier.Operation.MULTIPLY_BASE));

        ItemStack bow = new ItemStack(Items.BOW);
        bow.enchant(Enchantments.FLAMING_ARROWS, 1);
        bow.enchant(Enchantments.PUNCH_ARROWS, 2);
        bow.enchant(Enchantments.POWER_ARROWS, 2);
        bow.enchant(Enchantments.VANISHING_CURSE, 1);
        bow.enchant(Enchantments.BINDING_CURSE, 1);
        witherEntity.setItemInHand(InteractionHand.MAIN_HAND, bow);
        witherEntity.setDropChance(EquipmentSlot.MAINHAND, 0.5f);
        witherEntity.setLeftHanded(context.random().nextFloat() < 0.05F);

        context.level().addFreshEntityWithPassengers(witherEntity);
        return true;
    }
}