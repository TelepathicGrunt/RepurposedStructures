package com.telepathicgrunt.repurposedstructures.mixin.resources;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;

@Mixin(LootTables.class)
public interface LootManagerAccessor {
    @Accessor("tables")
    Map<ResourceLocation, LootTable> repurposedstructures_getTables();
}
