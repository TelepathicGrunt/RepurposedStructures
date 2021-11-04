package com.telepathicgrunt.repurposedstructures.data;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.data.loot.ChestLootTables;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.EmptyLootEntry;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.functions.EnchantRandomly;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.loot.functions.SetDamage;
import net.minecraft.loot.functions.SetNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;

public class ChestLootTableProvider extends ChestLootTables {

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {

        consumer.accept(new ResourceLocation(RepurposedStructures.MODID, "chests/bastions/underground/bridge"),
            LootTable.lootTable().withPool(
                LootPool.lootPool().setRolls(ConstantRange.exactly(1)).bonusRolls(1, 1)
                        .add(EmptyLootEntry.emptyItem().setWeight(10))
                        .add(ItemLootEntry.lootTableItem(Items.SMITHING_TABLE).setWeight(3))
                        .add(ItemLootEntry.lootTableItem(Items.GRINDSTONE).setWeight(3))
                        .add(ItemLootEntry.lootTableItem(Items.ANVIL))
            ).withPool(
                LootPool.lootPool().setRolls(RandomValueRange.between(2, 3)).bonusRolls(1, 1)
                        .add(EmptyLootEntry.emptyItem().setWeight(5))
                        .add(ItemLootEntry.lootTableItem(Items.BOW).apply(SetDamage.setDamage(RandomValueRange.between(0.1f, 0.5f))).apply(EnchantRandomly.randomApplicableEnchantment()))
                        .add(ItemLootEntry.lootTableItem(Items.TIPPED_ARROW).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(7, 24))).apply(SetNBT.setTag(getPotionNbt("minecraft:harming"))))
                        .add(ItemLootEntry.lootTableItem(Items.TIPPED_ARROW).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(6, 18))).apply(SetNBT.setTag(getPotionNbt("minecraft:strong_harming"))))
                        .add(ItemLootEntry.lootTableItem(Items.REDSTONE_ORE).apply(SetCount.setCount(RandomValueRange.between(8, 12))))
                        .add(ItemLootEntry.lootTableItem(Items.OBSIDIAN).apply(SetCount.setCount(RandomValueRange.between(3, 8))))
                        .add(ItemLootEntry.lootTableItem(Items.REDSTONE_BLOCK))
                        .add(ItemLootEntry.lootTableItem(Items.REDSTONE).apply(SetCount.setCount(RandomValueRange.between(8, 22))))
                        .add(ItemLootEntry.lootTableItem(Items.IRON_INGOT).apply(SetCount.setCount(RandomValueRange.between(4, 9))))
                        .add(ItemLootEntry.lootTableItem(Items.STONE_SWORD))
                        .add(ItemLootEntry.lootTableItem(Items.STONE_SWORD).apply(EnchantRandomly.randomApplicableEnchantment()))
                        .add(ItemLootEntry.lootTableItem(Items.CHAINMAIL_CHESTPLATE).apply(EnchantRandomly.randomApplicableEnchantment()))
                        .add(ItemLootEntry.lootTableItem(Items.CHAINMAIL_HELMET).apply(EnchantRandomly.randomApplicableEnchantment()))
                        .add(ItemLootEntry.lootTableItem(Items.CHAINMAIL_LEGGINGS).apply(EnchantRandomly.randomApplicableEnchantment()))
                        .add(ItemLootEntry.lootTableItem(Items.CHAINMAIL_BOOTS).apply(EnchantRandomly.randomApplicableEnchantment()))
                        .add(ItemLootEntry.lootTableItem(Items.IRON_HORSE_ARMOR))
                        .add(ItemLootEntry.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE))
            ).withPool(
                    LootPool.lootPool().setRolls(RandomValueRange.between(2, 4))
                            .add(ItemLootEntry.lootTableItem(Items.BONE).apply(SetCount.setCount(RandomValueRange.between(1, 6))))
                            .add(ItemLootEntry.lootTableItem(Items.STRING).apply(SetCount.setCount(RandomValueRange.between(1, 6))))
                            .add(ItemLootEntry.lootTableItem(Items.LEATHER).apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                            .add(ItemLootEntry.lootTableItem(Items.ARROW).apply(SetCount.setCount(RandomValueRange.between(5, 17))))
                            .add(ItemLootEntry.lootTableItem(Items.IRON_NUGGET).apply(SetCount.setCount(RandomValueRange.between(1, 6))))
                            .add(ItemLootEntry.lootTableItem(Items.REDSTONE).apply(SetCount.setCount(RandomValueRange.between(7, 19))))
                            .add(ItemLootEntry.lootTableItem(Items.ROTTEN_FLESH).apply(SetCount.setCount(RandomValueRange.between(3, 7))))
            ).withPool(
                    LootPool.lootPool().setRolls(ConstantRange.exactly(0)).bonusRolls(1, 1)
                            .add(ItemLootEntry.lootTableItem(Items.PINK_BANNER).apply(SetNBT.setTag(getTransBannerNBT())))
                            .add(EmptyLootEntry.emptyItem().setWeight(16))
                            .add(ItemLootEntry.lootTableItem(Items.BLACK_BANNER).setWeight(4).apply(SetNBT.setTag(getBannerNBT("{Patterns:[{Pattern:cr,Color:0},{Pattern:cr,Color:15},{Pattern:cbo,Color:0},{Pattern:cbo,Color:15},{Pattern:bo,Color:0},{Pattern:bo,Color:0},{Pattern:mr,Color:8},{Pattern:mr,Color:8},{Pattern:mr,Color:15},{Pattern:bo,Color:15},{Pattern:mc,Color:7},{Pattern:mc,Color:15}]}"))))
                            .add(ItemLootEntry.lootTableItem(Items.SKELETON_SKULL).setWeight(4).apply(SetCount.setCount(RandomValueRange.between(1, 6))))
            )
        );
    }


    private CompoundNBT getTransBannerNBT() {
        CompoundNBT bannerNBT = new CompoundNBT();
        try {
            bannerNBT.put("BlockEntityTag", JsonToNBT.parseTag("{Patterns:[{Pattern:cs,Color:0},{Pattern:cs,Color:0},{Pattern:cs,Color:0},{Pattern:bo,Color:3},{Pattern:bo,Color:3},{Pattern:bo,Color:3}]}"));
        }
        catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
        return bannerNBT;
    }

    private CompoundNBT getBannerNBT(String tag) {
        CompoundNBT bannerNBT = new CompoundNBT();
        try {
            bannerNBT.put("BlockEntityTag", JsonToNBT.parseTag(tag));
        }
        catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
        return bannerNBT;
    }

    private CompoundNBT getPotionNbt(String tag) {
        CompoundNBT potionNBT = new CompoundNBT();
        potionNBT.putString("Potion", tag);
        return potionNBT;
    }
}
