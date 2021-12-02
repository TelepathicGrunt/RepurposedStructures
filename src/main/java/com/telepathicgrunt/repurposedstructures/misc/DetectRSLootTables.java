package com.telepathicgrunt.repurposedstructures.misc;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.reflect.TypeToken;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DetectRSLootTables implements LootItemCondition {
    public static final LootItemConditionType DETECT_RS_LOOT_TABLES = new LootItemConditionType(new DetectRSLootTables.Serializer());
    private final Set<ResourceLocation> blacklistedLootTableIds;

    private DetectRSLootTables(final Set<ResourceLocation> blacklistedLootTableIds) {
        this.blacklistedLootTableIds = blacklistedLootTableIds;
    }

    @Override
    public LootItemConditionType getType() {
        return DETECT_RS_LOOT_TABLES;
    }

    @Override
    public boolean test(LootContext lootContext) {
        ResourceLocation lootTableID = lootContext.getQueriedLootTableId();
        return !this.blacklistedLootTableIds.contains(lootTableID);
    }

    public static Builder builder(final Set<ResourceLocation> blacklistedLootTableIds) {
        return new Builder(blacklistedLootTableIds);
    }

    public static class Builder implements LootItemCondition.Builder {
        private final Set<ResourceLocation> blacklistedLootTableIds;

        public Builder(Set<ResourceLocation> blacklistedLootTableIds) {
            if (blacklistedLootTableIds == null) throw new IllegalArgumentException("Blacklisted loot table must not be null. Use \"blacklisted_loot_tables\":[] to denote no blacklist.");
            this.blacklistedLootTableIds = blacklistedLootTableIds;
        }

        @Override
        public LootItemCondition build() {
            return new DetectRSLootTables(this.blacklistedLootTableIds);
        }
    }

    public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<DetectRSLootTables> {
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>(){}.getType();

        @Override
        public void serialize(JsonObject object, DetectRSLootTables instance, JsonSerializationContext ctx) {
            object.addProperty("loot_table_id", instance.blacklistedLootTableIds.toString());
        }

        @Override
        public DetectRSLootTables deserialize(JsonObject object, JsonDeserializationContext ctx) {
            List<String> unconvertedLootTableStrings = gson.fromJson(
                    GsonHelper.getAsJsonArray(object, "blacklisted_loot_tables"), type);

            Set<ResourceLocation> convertedLootTableStrings = unconvertedLootTableStrings.stream()
                    .map(ResourceLocation::new).collect(Collectors.toSet());

            return new DetectRSLootTables(convertedLootTableStrings);
        }
    }
}