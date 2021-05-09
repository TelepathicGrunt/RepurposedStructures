package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;


public class WitherSkeletonWithBow extends Feature<NoFeatureConfig> {

    public WitherSkeletonWithBow(Codec<NoFeatureConfig> configFactory) {
        super(configFactory);
    }


    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {

        // move down to spawn at the jigsaw block calling this
        position = position.below();

        WitherSkeletonEntity witherEntity = EntityType.WITHER_SKELETON.create(world.getLevel());
        witherEntity.setPersistenceRequired();
        witherEntity.absMoveTo(
                (double)position.getX() + 0.5D,
                position.getY(),
                (double)position.getZ() + 0.5D,
                0.0F,
                0.0F);

        witherEntity.getAttribute(Attributes.FOLLOW_RANGE)
                .addPermanentModifier(new AttributeModifier(
                        "Random spawn bonus",
                        (random.nextGaussian() * 0.3D) + 0.5D,
                        AttributeModifier.Operation.MULTIPLY_BASE));

        ItemStack bow = new ItemStack(Items.BOW);
        bow.enchant(Enchantments.FLAMING_ARROWS, 1);
        bow.enchant(Enchantments.PUNCH_ARROWS, 2);
        bow.enchant(Enchantments.POWER_ARROWS, 2);
        bow.enchant(Enchantments.VANISHING_CURSE, 1);
        bow.enchant(Enchantments.BINDING_CURSE, 1);
        witherEntity.setItemInHand(Hand.MAIN_HAND, bow);
        witherEntity.setDropChance(EquipmentSlotType.MAINHAND, 0.5f);
        witherEntity.setLeftHanded(random.nextFloat() < 0.05F);

        world.addFreshEntityWithPassengers(witherEntity);
        return true;
    }
}