package com.telepathicgrunt.repurposedstructures.world.features;

import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
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

        if(random.nextFloat() < 0.45F){
            ItemStack stoneSword = new ItemStack(Items.STONE_SWORD);

            // enchant sword
            drownedEntity.setStackInHand(Hand.MAIN_HAND, GeneralUtils.enchantRandomly(random, stoneSword, 0.25F));
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

        drownedEntity.setPersistent();
        drownedEntity.refreshPositionAndAngles(
                (double)position.getX() + 0.5D,
                position.getY(),
                (double)position.getZ() + 0.5D,
                0.0F,
                0.0F);
        drownedEntity.initialize(world, world.getLocalDifficulty(position), SpawnReason.STRUCTURE, null, null);
        world.spawnEntityAndPassengers(drownedEntity);
        return true;
    }
}