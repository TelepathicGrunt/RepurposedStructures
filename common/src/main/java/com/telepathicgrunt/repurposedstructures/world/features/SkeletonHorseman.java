package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.mixins.entities.EntityAccessor;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.equine.SkeletonHorse;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.List;
import java.util.Optional;


public record SkeletonHorseman(
        Optional<Item> heldItem,
        Optional<Item> helmet,
        Optional<Item> chestplate,
        Optional<Item> leggings,
        Optional<Item> boots,
        float speedModifier,
        int health
) implements Feature {

    public static final MapCodec<SkeletonHorseman> CODEC = RecordCodecBuilder.mapCodec((skeletonHorsemanInstance) -> skeletonHorsemanInstance.group(
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("held_item").forGetter(skeletonHorseman -> skeletonHorseman.heldItem),
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("helmet").forGetter(skeletonHorseman -> skeletonHorseman.helmet),
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("chestplate").forGetter(skeletonHorseman -> skeletonHorseman.chestplate),
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("leggings").forGetter(skeletonHorseman -> skeletonHorseman.leggings),
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("boots").forGetter(skeletonHorseman -> skeletonHorseman.boots),
            Codec.floatRange(0, Float.MAX_VALUE).fieldOf("speed_modifier").forGetter(skeletonHorseman -> skeletonHorseman.speedModifier),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("health").forGetter(skeletonHorseman -> skeletonHorseman.health)
    ).apply(skeletonHorsemanInstance, SkeletonHorseman::new));

    @Override
    public MapCodec<SkeletonHorseman> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        SkeletonHorse skeletonHorseEntity = EntityTypes.SKELETON_HORSE.create(level.getLevel(), EntitySpawnReason.STRUCTURE);
        if (skeletonHorseEntity == null) {
            return false;
        }
        skeletonHorseEntity.setPersistenceRequired();
        skeletonHorseEntity.setPos(
                (double)origin.getX() + 0.5D,
                origin.getY(),
                (double)origin.getZ() + 0.5D);
        skeletonHorseEntity.finalizeSpawn(level, level.getCurrentDifficultyAt(origin), EntitySpawnReason.STRUCTURE, null);

        Skeleton skeletonEntity = EntityTypes.SKELETON.create(level.getLevel(), EntitySpawnReason.STRUCTURE);
        if (skeletonEntity != null) {
            // Do this first as this attaches a bow automatically. We may want to override the bow later.
            skeletonEntity.finalizeSpawn(level, level.getCurrentDifficultyAt(origin), EntitySpawnReason.STRUCTURE, null);

            heldItem.ifPresent(item -> {
                ItemStack heldItem = new ItemStack(item);
                skeletonEntity.setItemInHand(InteractionHand.MAIN_HAND, GeneralUtils.enchantRandomly(level.registryAccess(), random, heldItem, 0.1F));
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
                    (double)origin.getX() + 0.5D,
                    origin.getY() + 1,
                    (double)origin.getZ() + 0.5D);

            if (skeletonHorseEntity.getPassengers().isEmpty()) {
                ((EntityAccessor)skeletonHorseEntity).repurposedstructures$setPassengers(ImmutableList.of(skeletonEntity));
            }
            else {
                List<Entity> list = Lists.newArrayList(skeletonHorseEntity.getPassengers());
                list.add(skeletonEntity);
                ((EntityAccessor)skeletonHorseEntity).repurposedstructures$setPassengers(ImmutableList.copyOf(list));
            }
        }

        level.addFreshEntityWithPassengers(skeletonHorseEntity);
        return true;
    }
}