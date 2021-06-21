package com.telepathicgrunt.repurposedstructures.mixin.resources;

import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootTable;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(LootManager.class)
public interface LootManagerAccessor {
    @Accessor("tables")
    Map<Identifier, LootTable> repurposedstructures_getTables();
}
