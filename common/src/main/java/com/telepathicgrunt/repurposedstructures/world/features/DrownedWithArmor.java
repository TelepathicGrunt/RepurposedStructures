package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.MapCodec;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.zombie.Drowned;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;


public record DrownedWithArmor() implements Feature {

    public static final MapCodec<DrownedWithArmor> CODEC = MapCodec.unit(DrownedWithArmor::new);

    @Override
    public MapCodec<DrownedWithArmor> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        // only spawn drowned if in water
        if(!level.getBlockState(origin).getFluidState().is(FluidTags.WATER)) return false;

        // move down to spawn at the jigsaw block calling this
        BlockPos position = origin.below();

        Drowned drownedEntity = EntityTypes.DROWNED.create(level.getLevel(), EntitySpawnReason.STRUCTURE);
        if (drownedEntity == null) {
            return false;
        }

        if(random.nextFloat() < 0.45F) {
            ItemStack stoneSword = new ItemStack(Items.STONE_SWORD);

            // enchant sword
            drownedEntity.setItemInHand(InteractionHand.MAIN_HAND, GeneralUtils.enchantRandomly(level.registryAccess(), random, stoneSword, 0.25F));
            drownedEntity.setDropChance(EquipmentSlot.MAINHAND, 0.4f);
            drownedEntity.setLeftHanded(random.nextFloat() < 0.05F);
        }

        if(level.getRandom().nextFloat() < 0.4f) {
            drownedEntity.setItemSlot(EquipmentSlot.HEAD, level.getRandom().nextFloat() < 0.2f ? Items.IRON_HELMET.getDefaultInstance() : Items.CHAINMAIL_HELMET.getDefaultInstance());
        }
        if(level.getRandom().nextFloat() < 0.4f) {
            drownedEntity.setItemSlot(EquipmentSlot.CHEST, level.getRandom().nextFloat() < 0.2f ? Items.IRON_CHESTPLATE.getDefaultInstance() : Items.CHAINMAIL_CHESTPLATE.getDefaultInstance());
        }
        if(level.getRandom().nextFloat() < 0.4f) {
            drownedEntity.setItemSlot(EquipmentSlot.LEGS, level.getRandom().nextFloat() < 0.2f ? Items.IRON_LEGGINGS.getDefaultInstance() : Items.CHAINMAIL_LEGGINGS.getDefaultInstance());
        }
        if(level.getRandom().nextFloat() < 0.4f) {
            drownedEntity.setItemSlot(EquipmentSlot.FEET, level.getRandom().nextFloat() < 0.2f ? Items.IRON_BOOTS.getDefaultInstance() : Items.CHAINMAIL_BOOTS.getDefaultInstance());
        }

        drownedEntity.setPersistenceRequired();
        drownedEntity.setPos(
                (double)position.getX() + 0.5D,
                position.getY(),
                (double)position.getZ() + 0.5D);
        drownedEntity.finalizeSpawn(level, level.getCurrentDifficultyAt(position), EntitySpawnReason.STRUCTURE, null);
        level.addFreshEntityWithPassengers(drownedEntity);
        return true;
    }
}