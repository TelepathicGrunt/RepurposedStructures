package com.telepathicgrunt.repurposedstructures.misc.neoforge.lootmanager;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.ArrayList;
import java.util.HashSet;

public class DetectRSLootTables implements LootItemCondition {
    public static final MapCodec<DetectRSLootTables> CODEC = RecordCodecBuilder.mapCodec((configInstance) -> configInstance.group(
            Identifier.CODEC.listOf().fieldOf("blacklisted_loot_tables").xmap(HashSet::new, ArrayList::new).forGetter(config -> config.blacklistedLootTableIds)
    ).apply(configInstance, DetectRSLootTables::new));

    private final HashSet<Identifier> blacklistedLootTableIds;

    private DetectRSLootTables(final HashSet<Identifier> blacklistedLootTableIds) {
        this.blacklistedLootTableIds = blacklistedLootTableIds;
    }

    @Override
    public boolean test(LootContext lootContext) {
        Identifier lootTableID = lootContext.getQueriedLootTableId();
        return lootTableID.getNamespace().equals(RepurposedStructures.MODID) && !this.blacklistedLootTableIds.contains(lootTableID);
    }

    @Override
    public MapCodec<? extends LootItemCondition> codec() {
        return CODEC;
    }
}