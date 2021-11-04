package com.telepathicgrunt.repurposedstructures.mixin.datagen;

import net.minecraft.loot.IRandomRange;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.ILootFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(LootPool.class)
public interface LootPoolAccessor {
    @Accessor
    String getName();

    @Accessor
    List<LootEntry> getEntries();

    @Accessor
    List<ILootCondition> getConditions();

    @Accessor
    ILootFunction[] getFunctions();

    @Accessor
    IRandomRange getRolls();

    @Accessor
    RandomValueRange getBonusRolls();
}
