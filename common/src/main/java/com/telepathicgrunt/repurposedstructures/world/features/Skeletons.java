package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.Optional;


public record Skeletons(
        Optional<Item> heldItem,
        Optional<Item> helmet,
        Optional<Item> chestplate,
        Optional<Item> leggings,
        Optional<Item> boots,
        float speedModifier,
        int health
) implements Feature {

    public static final MapCodec<Skeletons> CODEC = RecordCodecBuilder.mapCodec((skeletonsInstance) -> skeletonsInstance.group(
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("held_item").forGetter(skeletons -> skeletons.heldItem),
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("helmet").forGetter(skeletons -> skeletons.helmet),
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("chestplate").forGetter(skeletons -> skeletons.chestplate),
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("leggings").forGetter(skeletons -> skeletons.leggings),
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("boots").forGetter(skeletons -> skeletons.boots),
            Codec.floatRange(0, Float.MAX_VALUE).fieldOf("speed_modifier").forGetter(skeletons -> skeletons.speedModifier),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("health").forGetter(skeletons -> skeletons.health)
    ).apply(skeletonsInstance, Skeletons::new));

    @Override
    public MapCodec<Skeletons> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        Skeleton skeletonEntity = EntityTypes.SKELETON.create(level.getLevel(), EntitySpawnReason.STRUCTURE);
        if (skeletonEntity == null) {
            return false;
        }

        skeletonEntity.setPersistenceRequired();
        skeletonEntity.setPos(
                (double) origin.getX() + 0.5D,
                origin.getY(),
                (double) origin.getZ() + 0.5D);

        // Do this first as this attaches a bow automatically. We may want to override the bow later.
        skeletonEntity.finalizeSpawn(level, level.getCurrentDifficultyAt(origin), EntitySpawnReason.STRUCTURE, null);

        heldItem.ifPresent(item -> {
            ItemStack heldItem = new ItemStack(item);
            skeletonEntity.setItemInHand(InteractionHand.MAIN_HAND, GeneralUtils.enchantRandomly(level.registryAccess(), random, heldItem, 0.333F));
            skeletonEntity.setLeftHanded(random.nextFloat() < 0.05F);
        });
        helmet.ifPresent(item -> skeletonEntity.setItemSlot(EquipmentSlot.HEAD, GeneralUtils.enchantRandomly(level.registryAccess(), random, item.getDefaultInstance(), 0.075F)));
        chestplate.ifPresent(item -> skeletonEntity.setItemSlot(EquipmentSlot.CHEST, GeneralUtils.enchantRandomly(level.registryAccess(), random, item.getDefaultInstance(), 0.075F)));
        leggings.ifPresent(item -> skeletonEntity.setItemSlot(EquipmentSlot.LEGS, GeneralUtils.enchantRandomly(level.registryAccess(), random, item.getDefaultInstance(), 0.075F)));
        boots.ifPresent(item -> skeletonEntity.setItemSlot(EquipmentSlot.FEET, GeneralUtils.enchantRandomly(level.registryAccess(), random, item.getDefaultInstance(), 0.075F)));

        skeletonEntity.setHealth(health);
        skeletonEntity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(health);
        skeletonEntity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(speedModifier);

        skeletonEntity.setPersistenceRequired();

        // Ensure mods touching finalizeSpawn does not move entity.
        skeletonEntity.setPos(
                (double) origin.getX() + 0.5D,
                origin.getY(),
                (double) origin.getZ() + 0.5D);

        level.addFreshEntityWithPassengers(skeletonEntity);
        return true;
    }

}