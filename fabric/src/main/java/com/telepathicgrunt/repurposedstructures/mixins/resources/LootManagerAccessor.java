package com.telepathicgrunt.repurposedstructures.mixins.resources;

import net.minecraft.world.level.storage.loot.LootDataId;
import net.minecraft.world.level.storage.loot.LootDataManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(LootDataManager.class)
public interface LootManagerAccessor {
    @Accessor("elements")
    Map<LootDataId<?>, ?> repurposedstructures_getTables();
}
