package com.telepathicgrunt.repurposedstructures.world.features;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class DrownedWithArmor extends Feature<DefaultFeatureConfig> {

    public DrownedWithArmor() {
        super(DefaultFeatureConfig.CODEC);
    }


    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, DefaultFeatureConfig config) {

        // only spawn drowned if in water
        if(!world.getBlockState(position).getFluidState().isIn(FluidTags.WATER)) return false;

        // move down to spawn at the jigsaw block calling this
        position = position.down();

        DrownedEntity drownedEntity = EntityType.DROWNED.create(world.toServerWorld());
        drownedEntity.setPersistent();
        drownedEntity.refreshPositionAndAngles(
                (double)position.getX() + 0.5D,
                position.getY(),
                (double)position.getZ() + 0.5D,
                0.0F,
                0.0F);


        if(random.nextFloat() < 0.45F){
            ItemStack stoneSword = new ItemStack(Items.STONE_SWORD);

            // enchant sword
            if(random.nextFloat() < 0.25F){
                List<Enchantment> list = Registry.ENCHANTMENT.stream().filter(Enchantment::isAvailableForRandomSelection)
                        .filter((enchantmentToCheck) -> enchantmentToCheck.isAcceptableItem(stoneSword)).collect(Collectors.toList());
                Enchantment enchantment = list.get(random.nextInt(list.size()));
                // bias towards weaker enchantments
                int enchantmentLevel = random.nextInt(MathHelper.nextInt(random, enchantment.getMinLevel(), enchantment.getMaxLevel()) + 1);
                stoneSword.addEnchantment(enchantment, enchantmentLevel);
            }

            drownedEntity.setStackInHand(Hand.MAIN_HAND, stoneSword);
            drownedEntity.setEquipmentDropChance(EquipmentSlot.MAINHAND, 0.4f);
            drownedEntity.setLeftHanded(random.nextFloat() < 0.05F);
        }

        if(world.getRandom().nextFloat() < 0.4f){
            drownedEntity.equipStack(EquipmentSlot.HEAD, world.getRandom().nextFloat() < 0.2f ? Items.IRON_HELMET.getDefaultStack() : Items.CHAINMAIL_HELMET.getDefaultStack());
        }
        if(world.getRandom().nextFloat() < 0.4f){
            drownedEntity.equipStack(EquipmentSlot.CHEST, world.getRandom().nextFloat() < 0.2f ? Items.IRON_CHESTPLATE.getDefaultStack() : Items.CHAINMAIL_CHESTPLATE.getDefaultStack());
        }
        if(world.getRandom().nextFloat() < 0.4f){
            drownedEntity.equipStack(EquipmentSlot.LEGS, world.getRandom().nextFloat() < 0.2f ? Items.IRON_LEGGINGS.getDefaultStack() : Items.CHAINMAIL_LEGGINGS.getDefaultStack());
        }
        if(world.getRandom().nextFloat() < 0.4f){
            drownedEntity.equipStack(EquipmentSlot.FEET, world.getRandom().nextFloat() < 0.2f ? Items.IRON_BOOTS.getDefaultStack() : Items.CHAINMAIL_BOOTS.getDefaultStack());
        }

        drownedEntity.refreshPositionAndAngles((double)position.getX() + 0.5D, position.getY(), (double)position.getZ() + 0.5D, 0.0F, 0.0F);
        drownedEntity.initialize(world, world.getLocalDifficulty(position), SpawnReason.STRUCTURE, null, null);
        world.spawnEntityAndPassengers(drownedEntity);
        return true;
    }
}