package com.telepathicgrunt.repurposedstructures.world.features;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
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

        WitherSkeleton witherEntity = EntityTypes.WITHER_SKELETON.create(context.level().getLevel(), EntitySpawnReason.STRUCTURE);
        if (witherEntity == null) {
            return false;
        }
        witherEntity.setPersistenceRequired();
        witherEntity.setPos(
                (double)position.getX() + 0.5D,
                position.getY(),
                (double)position.getZ() + 0.5D);

        witherEntity.getAttribute(Attributes.FOLLOW_RANGE)
                .addPermanentModifier(new AttributeModifier(
                        Identifier.fromNamespaceAndPath(RepurposedStructures.MODID, "random_spawn_bonus"),
                        (context.random().nextGaussian() * 0.3D) + 0.5D,
                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        ItemStack bow = new ItemStack(Items.BOW);
        Registry<Enchantment> enchantmentRegistry = context.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        enchantmentRegistry.get(Enchantments.FLAME).ifPresent(enchant -> bow.enchant(enchant, 1));
        enchantmentRegistry.get(Enchantments.PUNCH).ifPresent(enchant -> bow.enchant(enchant, 2));
        enchantmentRegistry.get(Enchantments.POWER).ifPresent(enchant -> bow.enchant(enchant, 2));
        enchantmentRegistry.get(Enchantments.VANISHING_CURSE).ifPresent(enchant -> bow.enchant(enchant, 1));
        enchantmentRegistry.get(Enchantments.BINDING_CURSE).ifPresent(enchant -> bow.enchant(enchant, 1));
        witherEntity.setItemInHand(InteractionHand.MAIN_HAND, bow);
        witherEntity.setDropChance(EquipmentSlot.MAINHAND, 0.5f);
        witherEntity.setLeftHanded(context.random().nextFloat() < 0.05F);

        context.level().addFreshEntityWithPassengers(witherEntity);
        return true;
    }
}