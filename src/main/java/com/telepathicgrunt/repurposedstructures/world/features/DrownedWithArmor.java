package com.telepathicgrunt.repurposedstructures.world.features;

import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class DrownedWithArmor extends Feature<NoFeatureConfig> {

    public DrownedWithArmor() {
        super(NoFeatureConfig.CODEC);
    }


    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {

        // only spawn drowned if in water
        if(!world.getBlockState(position).getFluidState().is(FluidTags.WATER)) return false;

        // move down to spawn at the jigsaw block calling this
        position = position.below();

        DrownedEntity drownedEntity = EntityType.DROWNED.create(world.getLevel());

        if(random.nextFloat() < 0.45F){
            ItemStack stoneSword = new ItemStack(Items.STONE_SWORD);

            // enchant sword
            drownedEntity.setItemInHand(Hand.MAIN_HAND, GeneralUtils.enchantRandomly(random, stoneSword, 0.25F));
            drownedEntity.setDropChance(EquipmentSlotType.MAINHAND, 0.4f);
            drownedEntity.setLeftHanded(random.nextFloat() < 0.05F);
        }

        if(world.getRandom().nextFloat() < 0.4f){
            drownedEntity.setItemSlot(EquipmentSlotType.HEAD, world.getRandom().nextFloat() < 0.2f ? Items.IRON_HELMET.getDefaultInstance() : Items.CHAINMAIL_HELMET.getDefaultInstance());
        }
        if(world.getRandom().nextFloat() < 0.4f){
            drownedEntity.setItemSlot(EquipmentSlotType.CHEST, world.getRandom().nextFloat() < 0.2f ? Items.IRON_CHESTPLATE.getDefaultInstance() : Items.CHAINMAIL_CHESTPLATE.getDefaultInstance());
        }
        if(world.getRandom().nextFloat() < 0.4f){
            drownedEntity.setItemSlot(EquipmentSlotType.LEGS, world.getRandom().nextFloat() < 0.2f ? Items.IRON_LEGGINGS.getDefaultInstance() : Items.CHAINMAIL_LEGGINGS.getDefaultInstance());
        }
        if(world.getRandom().nextFloat() < 0.4f){
            drownedEntity.setItemSlot(EquipmentSlotType.FEET, world.getRandom().nextFloat() < 0.2f ? Items.IRON_BOOTS.getDefaultInstance() : Items.CHAINMAIL_BOOTS.getDefaultInstance());
        }

        drownedEntity.setPersistenceRequired();
        drownedEntity.absMoveTo(
                (double)position.getX() + 0.5D,
                position.getY(),
                (double)position.getZ() + 0.5D,
                0.0F,
                0.0F);
        drownedEntity.finalizeSpawn(world, world.getCurrentDifficultyAt(position), SpawnReason.STRUCTURE, null, null);
        world.addFreshEntityWithPassengers(drownedEntity);
        return true;
    }
}